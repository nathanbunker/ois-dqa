package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.CentralControl;
import org.openimmunizationsoftware.dqa.cm.SoftwareVersion;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.logic.HashManager;
import org.openimmunizationsoftware.dqa.cm.logic.MailManager;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.model.Application;
import org.openimmunizationsoftware.dqa.cm.model.ApplicationUser;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserLog;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet
{

  public static final String USER_SESSION = "userSession";
  public static final String PARAM_CODE_TABLE_INSTANCE_ID = "codeTableInstanceId";
  public static final String PARAM_ATTRIBUTE_INSTANCE_ID = "attributeInstanceId";
  public static final String PARAM_ATTRIBUTE_TYPE_ID = "attributeTypeId";

  public static final String ATTRIBUTE_TEST_PARTICIPANT = "testParticipant";
  public static final String ATTRIBUTE_TEST_CONDUCTED = "testConducted";
  public static final String ATTRIBUTE_TEST_MESSAGE = "testMessage";

  private String servletTitle = "";
  private static String systemWideMessage = "";
  private static Set<UserActivity> userActivitySet = new HashSet<UserActivity>();

  protected static final String ERROR_EMAIL = "nbunker@immregistries.org";

  public static Set<UserActivity> getUserActivitySet()
  {
    return userActivitySet;
  }

  public static String getSystemWideMessage()
  {
    return systemWideMessage;
  }

  public static void setSystemWideMessage(String systemWideMessage)
  {
    BaseServlet.systemWideMessage = systemWideMessage;
  }

  protected void sendToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    RequestDispatcher dispatcher = req.getRequestDispatcher("home?" + HomeServlet.PARAM_VIEW + "=" + HomeServlet.VIEW_DEFAULT);
    dispatcher.forward(req, resp);
  }

  protected void sendToApplication(HttpServletRequest req, HttpServletResponse resp, Application application) throws ServletException, IOException
  {
    if (application.isApplicationAart())
    {

      RequestDispatcher dispatcher = req
          .getRequestDispatcher("testReport?" + TestReportServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_PENTAGON_REPORTS);
      dispatcher.forward(req, resp);
    } else
    {
      RequestDispatcher dispatcher = req.getRequestDispatcher("home");
      dispatcher.forward(req, resp);
    }
  }

  protected BaseServlet(String servletTitle) {
    this.servletTitle = servletTitle;
  }

  protected HttpSession setup(HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    HttpSession webSession = req.getSession(true);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    if (userSession == null)
    {
      userSession = initUserSession(webSession);
    }
    if (userSession.getDataSession() != null)
    {
      userSession.getDataSession().clear();
    }
    userSession.setOut(new PrintWriter(resp.getOutputStream()));
    if (userSession.isLoggedIn())
    {
      UserActivity userActivity = userSession.getUserActivity();
      userActivity.setUserName(userSession.getUser().getUserName());
      userActivity.setLastWebRequestTimeMillis(System.currentTimeMillis());
      userActivitySet.remove(userActivity);
      userActivitySet.add(userActivity);
      UserLog userLog = new UserLog();
      userLog.setUser(userSession.getUser());
      userLog.setStartTime(new Date());
      userLog.setServletName(req.getRequestURI());
      @SuppressWarnings("unchecked")
      Enumeration<String> e = req.getParameterNames();
      while (e.hasMoreElements())
      {
        String name = e.nextElement();
        String[] values = req.getParameterValues(name);
        for (String value : values)
        {
          userLog.addRequestParameters(name, value);
        }
      }
      userSession.setUserLog(userLog);
    }
    userSession.setHeaderCreated(false);
    resp.setContentType("text/html");
    return webSession;
  }

  protected HttpSession setupSkinny(HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    HttpSession webSession = req.getSession(true);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    if (userSession == null)
    {
      userSession = initUserSession(webSession);
    }
    userSession.getDataSession().clear();
    userSession.setHeaderCreated(false);
    return webSession;
  }

  public UserSession initUserSession(HttpSession webSession)
  {
    UserSession userSession = new UserSession();
    SessionFactory factory = CentralControl.getSessionFactory();
    Session dataSession = factory.openSession();
    userSession.setDataSession(dataSession);
    webSession.setAttribute(USER_SESSION, userSession);
    userSession.setReleaseVersion(ReleaseVersionLogic.getCurrentReleaseVersion(dataSession));
    return userSession;
  }

  protected void logout(HttpSession webSession)
  {
    PrintWriter out = null;
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    if (userSession != null)
    {
      webSession.removeAttribute(USER_SESSION);
      userSession.getDataSession().close();
      out = userSession.getOut();
    }
    userSession = initUserSession(webSession);
    userSession.setOut(out);
  }

  protected void login(String emailAddress, String password, HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    User user = UserLogic.getUserWithEmailAddress(emailAddress, userSession.getDataSession());

    if (user == null || !validatePassword(user, password))
    {
      userSession.setMessageError("Unable to login, unrecognized email or password");
    } else
    {
      if (!user.getPassword().startsWith("1000:"))
      {
        try
        {
          String passwordHashed = HashManager.generateStrongPasswordHash(user.getPassword());
          Transaction tx = userSession.getDataSession().beginTransaction();
          user.setPassword(passwordHashed);
          userSession.getDataSession().update(user);
          tx.commit();
        } catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      userSession.setMessageConfirmation("Welcome " + user.getUserName() + "!");
      userSession.setUser(user);
      userSession.setReleaseVersion(ReleaseVersionLogic.getProposedReleaseVersion(userSession.getDataSession()));
    }
  }

  protected boolean validatePassword(User user, String password)
  {
    String passwordHashed = user.getPassword();

    if (passwordHashed.startsWith("1000:"))
    {
      try
      {
        return HashManager.validatePassword(password, passwordHashed);
      } catch (Exception e)
      {
        e.printStackTrace();
        return passwordHashed.equals(password);
      }
    } else
    {
      return passwordHashed.equals(password);
    }
  }

  public int readInt(String param, HttpServletRequest req)
  {
    return Integer.parseInt(req.getParameter(param));
  }
  
  protected void createHeader(HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    Application defaultApplication = (Application) dataSession.get(Application.class, 1);
    createHeader(webSession, defaultApplication);
  }

  protected void createHeaderNoMenu(HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    Application defaultApplication = (Application) dataSession.get(Application.class, 1);
    createHeaderNoMenu(webSession, defaultApplication);
  }

  protected void handleError(Throwable e, HttpSession webSession)
  {
    e.printStackTrace();
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    if (userSession != null)
    {
      PrintWriter out = userSession.getOut();
      if (out != null)
      {
        if (!userSession.isHeaderCreated())
        {
          createHeader(webSession);
        }
        out.println("<div class=\"leftColumn\">");
        out.println("<h2>Oops!</h2>");
        out.println("<p>Our apologies, an unexpected internal problem ocurred. Information about the problem "
            + "has been sent for review by the technical support system. "
            + "We are sorry for the inconvience but your request could not be completed. </p>");
        out.println("</div>");
      }
      if (userSession.getUserLog() != null)
      {
        userSession.getUserLog().setException(e);
      }
    }

  }

  protected void createHeader(HttpSession webSession, Application defaultApplication)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    PrintWriter out = userSession.getOut();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
    out.println("<html>");
    out.println("  <head>");
    out.println("    <meta charset=\"UTF-8\">");
    Application application = defaultApplication;
    ApplicationUser applicationUser = null;
    if (userSession.getUser() != null && userSession.getUser().getApplicationUser() != null)
    {
      applicationUser = userSession.getUser().getApplicationUser();
      application = applicationUser.getApplication();
    }
    out.println("    <title>" + application.getApplicationAcronym() + " - " + servletTitle + "</title>");
    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
    out.println("  </head>");
    out.println("  <body>");
    if (applicationUser != null && applicationUser.getAgreement() != null)
    {

      SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
      String link = "home?" + HomeServlet.PARAM_VIEW + "=" + HomeServlet.VIEW_AGREE_SIGNED + "&" + HomeServlet.PARAM_APPLICATION_ID + "="
          + application.getApplicationId();
      out.println("    <span class=\"aggreement\"><a href=\"" + link + "\">This use of this information is restricted to the Terms and Conditions "
          + applicationUser.getAgreementSignature() + " agreed to on " + sdf.format(applicationUser.getAgreementDate()) + ". </a></span>");
    }

    if (getSystemWideMessage() != null && !getSystemWideMessage().equals(""))
    {
      out.println("<div class=\"systemWideMessage\">System Wide Message: " + getSystemWideMessage() + "</div>");
    }
    out.println("    <div class=\"menu\">");
    if (application.isApplicationDqacm())
    {
      out.println("     <a href=\"home\" class=\"menuLink\">home</a>");
      out.println("     |");
      out.println("     <a href=\"home?" + HomeServlet.PARAM_VIEW + "=" + HomeServlet.VIEW_SEARCH + "\" class=\"menuLink\">search</a>");
      if (userSession.isAdmin())
      {
        out.println("     |");
        out.println("     <a href=\"adminUser\" class=\"menuLink\">users</a>");
        out.println("     |");
        out.println("     <a href=\"adminTable\" class=\"menuLink\">tables</a>");
        out.println("     |");
        out.println("     <a href=\"admin\" class=\"menuLink\">admin</a>");
      }
    } else if (application.isApplicationAart())
    {
      out.println("     <a href=\"testReport?" + HomeServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_HOME + "\" class=\"menuLink\">home</a>");
      out.println("     |");
      out.println(
          "     <a href=\"testReport?" + HomeServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_PENTAGON_REPORTS + "\" class=\"menuLink\">report</a>");
      if (userSession.getUser() != null && userSession.getUser().getApplicationUser() != null
          && userSession.getUser().getApplicationUser().getUserType() == UserType.ADMIN)
      {
        out.println("     |");
        out.println("     <a href=\"manualManage?" + HomeServlet.PARAM_VIEW + "=" + ManualManageServlet.VIEW_HL7_DOWNLOAD
            + "\" class=\"menuLink\">messages</a>");
        out.println("     |");
        out.println("     <a href=\"manualManage?" + HomeServlet.PARAM_VIEW + "=" + ManualManageServlet.VIEW_HL7_TESTERS
            + "\" class=\"menuLink\">testers</a>");
        out.println("     |");
        out.println("     <a href=\"manualManage?" + HomeServlet.PARAM_VIEW + "=" + ManualManageServlet.VIEW_HL7_REPORTS
            + "\" class=\"menuLink\">schedule</a>");
        out.println("     |");
        out.println("     <a href=\"profile\" class=\"menuLink\">profile</a>");
        out.println("     |");
        out.println("     <a href=\"adminUser\" class=\"menuLink\">users</a>");
        out.println("     |");
        out.println("     <a href=\"admin\" class=\"menuLink\">admin</a>");
        out.println("     |");
        out.println("     <a href=\"tools\" class=\"menuLink\">tools</a>");
      }
    } else
    {
      out.println("     <a href=\"home\" class=\"menuLink\">home</a>");
    }

    out.println("      <span class=\"appTitle\">");
    out.println("        <span class=\"logo\">" + application.getApplicationAcronym() + "</span> ");
    out.println(application.getApplicationLabel());
    out.println("      </span>");
    out.println("    </div>");
    if (userSession.getMessageError() != null)
    {
      out.println("<div class=\"messageError\">" + userSession.getMessageError() + "</div>");
      userSession.setMessageError(null);
    }
    if (userSession.getMessageConfirmation() != null)
    {
      out.println("<div class=\"messageConfirmation\">" + userSession.getMessageConfirmation() + "</div>");
      userSession.setMessageConfirmation(null);
    }
    out.println("    <div class=\"contents\">");
    userSession.setHeaderCreated(true);
  }
  
  protected void createHeaderNoMenu(HttpSession webSession, Application defaultApplication)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    PrintWriter out = userSession.getOut();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
    out.println("<html>");
    out.println("  <head>");
    out.println("    <meta charset=\"UTF-8\">");
    Application application = defaultApplication;
    ApplicationUser applicationUser = null;
    if (userSession.getUser() != null && userSession.getUser().getApplicationUser() != null)
    {
      applicationUser = userSession.getUser().getApplicationUser();
      application = applicationUser.getApplication();
    }
    out.println("    <title>" + application.getApplicationAcronym() + " - " + servletTitle + "</title>");
    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
    out.println("  </head>");
    out.println("  <body>");
    out.println("    <div class=\"contents\">");
    userSession.setHeaderCreated(true);
  }

  protected void createHeaderForGuide(HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    PrintWriter out = userSession.getOut();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
    out.println("<html>");
    out.println("  <head>");
    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
    out.println("  </head>");
    out.println("  <body class=\"guideContents\">");
    if (userSession.getMessageError() != null)
    {
      out.println("<div class=\"messageError\">" + userSession.getMessageError() + "</div>");
      userSession.setMessageError(null);
    }
    if (userSession.getMessageConfirmation() != null)
    {
      out.println("<div class=\"messageConfirmation\">" + userSession.getMessageConfirmation() + "</div>");
      userSession.setMessageConfirmation(null);
    }
    out.println("    <div>");

  }

  protected void createFooter(HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    if (userSession != null)
    {
      PrintWriter out = userSession.getOut();
      out.println("    </div>");
      out.println("    <span class=\"cmVersion\">Software Version " + SoftwareVersion.VERSION + "</span>");
      out.println("  </body>");
      out.println("</html>");
      out.close();
      out = null;
      try
      {
        if (userSession.getUserLog() != null)
        {
          Session dataSession = userSession.getDataSession();
          UserLog userLog = userSession.getUserLog();
          if (dataSession != null)
          {
            Transaction trans = dataSession.beginTransaction();
            userLog.setEndTime(new Date());
            userLog.setResponseMs((int) (userLog.getEndTime().getTime() - userLog.getStartTime().getTime()));
            dataSession.save(userLog);
            trans.commit();
          }
          if (userLog.getException() != null)
          {
            Throwable exception = userLog.getException();
            MailManager mailManager = new MailManager(dataSession);
            StringBuilder message = new StringBuilder();
            message.append("<h2>" + exception.getMessage() + "</h2>");
            message.append("<pre>");
            message.append(userLog.getExceptionText());
            message.append("</pre>");
            message.append("<h2>Other Details</h2>");
            message.append("<p>User: " + userLog.getUser().getUserName() + " (" + userLog.getUser().getUserId() + ")</p> ");
            message.append("<p>Servlet: " + userLog.getServletName() + "</p> ");
            if (userLog.getRequestParameters() != null && userLog.getRequestParameters().length() > 0)
            {
              message.append("<p>Parameters: " + userLog.getRequestParameters() + "</p> ");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");
            message.append("<p>Start Time: " + sdf.format(userLog.getStartTime()) + "</p>");
            message.append("<p>End Time: " + sdf.format(userLog.getEndTime()) + "</p>");
            message.append("<p>Elapsed Time (ms): " + userLog.getResponseMs() + "</p>");
            try
            {
              mailManager.sendEmail("AART/DQAcm Exception Occurred", message.toString(), ERROR_EMAIL);
            } catch (Exception e)
            {
              e.printStackTrace();
            }
          }
        }
      } catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    userSession.setHeaderCreated(false);
  }

  public void printCodeTables(CodeTableInstance codeTableInstance, ReleaseVersion releaseVersion, String baseLink, HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    out.println("<table width=\"100%\">");
    out.println("  <caption>Code Tables</caption>");
    out.println("  <tr>");
    out.println("    <th>Id</th>");
    out.println("    <th>Name</th>");
    out.println("  </tr>");
    List<CodeTableInstance> codeTableInstanceList = CodeTableLogic.getCodeTables(releaseVersion, dataSession);
    for (CodeTableInstance cti : codeTableInstanceList)
    {
      String includeStatusLabel = "";
      if (releaseVersion.getReleaseStatus() != ReleaseStatus.PROPOSED)
      {
        // if release or deprecated do not show anything but current tables
        if (cti.getInclusionStatus() != InclusionStatus.INCLUDE)
        {
          continue;
        }
      }
      if (cti.getInclusionStatus() == InclusionStatus.REMOVE)
      {
        includeStatusLabel = " <span class=\"inclusionStatusLabel\">removed</span>";
      }
      if (cti.getInclusionStatus() == InclusionStatus.PROPOSED_INCLUDE)
      {
        includeStatusLabel = " <span class=\"inclusionStatusLabel\">proposed include</span>";
      }
      if (cti.getInclusionStatus() == InclusionStatus.PROPOSED_REMOVE)
      {
        includeStatusLabel = " <span class=\"inclusionStatusLabel\">proposed remove</span>";
      }
      String link = baseLink + PARAM_CODE_TABLE_INSTANCE_ID + "=" + cti.getTableInstanceId();
      String classString = "";
      if (codeTableInstance != null && codeTableInstance.equals(cti))
      {
        classString = "selected";
      }
      if (cti.getInclusionStatus() == InclusionStatus.REMOVE)
      {
        classString += " strike";
      }
      String issueCountLabel = "";
      if (userSession.canEdit() && cti.getIssueCount() > 0)
      {
        if (cti.getIssueCount() == 1)
        {
          issueCountLabel = " <span class=\"issueCountLabel\">" + cti.getIssueCount() + " issue</span>";
        } else
        {
          issueCountLabel = " <span class=\"issueCountLabel\">" + cti.getIssueCount() + " issues</span>";
        }
      }

      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + cti.getTable().getTableId() + "</a></td>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + cti.getTableLabel() + "</a>" + issueCountLabel
          + includeStatusLabel + "</td>");
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  protected static String n(String s)
  {
    if (s == null)
    {
      return "";
    }
    return s;
  }
}
