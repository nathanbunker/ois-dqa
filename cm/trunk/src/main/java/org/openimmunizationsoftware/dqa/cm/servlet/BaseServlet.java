package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.cm.CentralControl;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.model.Application;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

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

  protected void sendToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    RequestDispatcher dispatcher = req.getRequestDispatcher("home");
    dispatcher.forward(req, resp);
  }

  protected void sendToApplication(HttpServletRequest req, HttpServletResponse resp, Application application) throws ServletException, IOException
  {
    if (application.isApplicationAart())
    {
      RequestDispatcher dispatcher = req.getRequestDispatcher("testReport?" + TestReportServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_MAP);
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
    userSession.getDataSession().clear();
    userSession.setOut(new PrintWriter(resp.getOutputStream()));
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
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    if (userSession != null)
    {
      webSession.removeAttribute(USER_SESSION);
      userSession.getDataSession().close();
    }
    webSession.invalidate();
  }

  protected void login(String userName, String password, HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    User user = UserLogic.getUser(userName, userSession.getDataSession());
    if (user == null || !user.getPassword().equals(password))
    {
      userSession.setMessageError("Unable to login, unrecognized user name or password");
    } else
    {
      userSession.setMessageConfirmation("Welcome " + user.getUserName() + "!");
      userSession.setUser(user);
      userSession.setReleaseVersion(ReleaseVersionLogic.getProposedReleaseVersion(userSession.getDataSession()));
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
    PrintWriter out = userSession.getOut();
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
    out.println("<html>");
    out.println("  <head>");
    Application application = (Application) dataSession.get(Application.class, 1);
    if (userSession.getUser() != null && userSession.getUser().getApplicationUser() != null)
    {
      application = userSession.getUser().getApplicationUser().getApplication();
    }
    out.println("    <title>" + application.getApplicationAcronym() + " - " + servletTitle + "</title>");
    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
    out.println("  </head>");
    out.println("  <body>");
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
      TestConducted testConducted = (TestConducted) webSession.getAttribute(ATTRIBUTE_TEST_CONDUCTED);
      out.println("     |");
      out.println("     <a href=\"testReport?" + HomeServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_MAP + "\" class=\"menuLink\">dashboard</a>");
      out.println("     |");
      out.println("     <a href=\"testReport?" + HomeServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_REPORTS + "\" class=\"menuLink\">reports</a>");
      if (testConducted != null)
      {
        out.println("     |");
        out.println("     <a href=\"testReport?" + HomeServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_TEST_MESSAGES
            + "\" class=\"menuLink\">tests</a>");
        TestMessage testMessage = (TestMessage) webSession.getAttribute(ATTRIBUTE_TEST_MESSAGE);
        if (testMessage != null)
        {
          out.println("     |");
          out.println("     <a href=\"testReport?" + HomeServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_FIELD_COMPARISON
              + "\" class=\"menuLink\">fields</a>");
        }
      }
      out.println("     |");
      out.println("     <a href=\"profile\" class=\"menuLink\">profile</a>");
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

  }

  protected void createFooter(HttpSession webSession)
  {
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    PrintWriter out = userSession.getOut();
    out.println("    </div>");
    out.println("  </body>");
    out.println("</html>");
    out.close();
    out = null;
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
}
