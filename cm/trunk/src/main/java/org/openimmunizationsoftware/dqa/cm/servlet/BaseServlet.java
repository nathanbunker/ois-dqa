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
import org.openimmunizationsoftware.dqa.cm.SoftwareVersion;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.logic.thread.UpdateCountThread;
import org.openimmunizationsoftware.dqa.cm.model.Application;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

public abstract class BaseServlet extends HttpServlet {

  public static final String PARAM_CODE_TABLE_INSTANCE_ID = "codeTableInstanceId";
  public static final String PARAM_ATTRIBUTE_INSTANCE_ID = "attributeInstanceId";
  public static final String PARAM_ATTRIBUTE_TYPE_ID = "attributeTypeId";

  private String servletTitle = "";
  protected HttpSession webSession = null;
  protected UserSession userSession = null;
  protected Session dataSession = null;
  protected PrintWriter out = null;
  protected String messageError = null;
  protected String messageConfirmation = null;

  public String getMessageError() {
    return messageError;
  }

  public void setMessageError(String messageError) {
    this.messageError = messageError;
  }

  public String getMessageConfirmation() {
    return messageConfirmation;
  }

  public void setMessageConfirmation(String messageConfirmation) {
    this.messageConfirmation = messageConfirmation;
  }

  public UserSession getUserSession() {
    return userSession;
  }

  public Session getDataSession() {
    return dataSession;
  }

  public PrintWriter getOut() {
    return out;
  }

  protected boolean isAdmin() {
    return userSession.getUser() != null && userSession.getUser().getApplicationUser() != null
        && userSession.getUser().getApplicationUser().getUserType() == UserType.ADMIN;
  }

  protected boolean isLoggedIn() {
    return userSession.getUser() != null;
  }

  protected void sendToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("home");
    dispatcher.forward(req, resp);
  }

  protected BaseServlet(String servletTitle) {
    this.servletTitle = servletTitle;
  }

  protected void setup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    webSession = req.getSession(true);
    userSession = (UserSession) webSession.getAttribute("userSession");
    if (userSession == null) {
      initUserSession();
    }
    dataSession = userSession.getDataSession();
    dataSession.clear();
    resp.setContentType("text/html");
    out = new PrintWriter(resp.getOutputStream());
  }

  public void initUserSession() {
    userSession = new UserSession();
    SessionFactory factory = CentralControl.getSessionFactory();
    dataSession = factory.openSession();
    userSession.setDataSession(dataSession);
    webSession.setAttribute("userSession", userSession);
    userSession.setReleaseVersion(ReleaseVersionLogic.getCurrentReleaseVersion(dataSession));
  }

  protected void logout() {
    initUserSession();
  }

  protected void login(String userName, String password) {
    User user = UserLogic.getUser(userName, dataSession);
    if (user == null || !user.getPassword().equals(password)) {
      messageError = "Unable to login, unrecognized user name or password";
    } else {
      messageConfirmation = "Welcome " + user.getUserName() + "!";
      userSession.setUser(user);
      userSession.setReleaseVersion(ReleaseVersionLogic.getProposedReleaseVersion(dataSession));
    }
  }

  public int readInt(String param, HttpServletRequest req) {
    return Integer.parseInt(req.getParameter(param));
  }

  protected void createHeader() {
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
    out.println("<html>");
    out.println("  <head>");
    Application application = (Application) dataSession.get(Application.class, 1);
    if (userSession.getUser() != null && userSession.getUser().getApplicationUser() != null) {
      application = userSession.getUser().getApplicationUser().getApplication();
    }
    out.println("    <title>" + application.getApplicationAcronym() + " - " + servletTitle + "</title>");
    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
    out.println("  </head>");
    out.println("  <body>");
    out.println("    <div class=\"menu\">");
    out.println("     <a href=\"home\" class=\"menuLink\">home</a>");
    out.println("     |");
    out.println("     <a href=\"home?" + HomeServlet.PARAM_VIEW + "=" + HomeServlet.VIEW_SEARCH + "\" class=\"menuLink\">search</a>");
    if (isAdmin()) {
      out.println("     |");
      out.println("     <a href=\"adminUser\" class=\"menuLink\">users</a>");
      out.println("     |");
      out.println("     <a href=\"adminTable\" class=\"menuLink\">tables</a>");
      out.println("     |");
      out.println("     <a href=\"admin\" class=\"menuLink\">admin</a>");
    }

    out.println("      <span class=\"appTitle\">");
    out.println("        <span class=\"logo\">" + application.getApplicationAcronym() + "</span> ");
    out.println(application.getApplicationLabel());
    out.println("      </span>");
    out.println("    </div>");
    if (messageError != null) {
      out.println("<div class=\"messageError\">" + messageError + "</div>");
      messageError = null;
    }
    if (messageConfirmation != null) {
      out.println("<div class=\"messageConfirmation\">" + messageConfirmation + "</div>");
      messageConfirmation = null;
    }
    out.println("    <div class=\"contents\">");

  }

  protected void createFooter() {
    out.println("    </div>");
    out.println("  </body>");
    out.println("</html>");
    out.close();
    out = null;
  }

  public void printCodeTables(CodeTableInstance codeTableInstance, ReleaseVersion releaseVersion, String baseLink) {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Code Tables</caption>");
    out.println("  <tr>");
    out.println("    <th>Id</th>");
    out.println("    <th>Name</th>");
    out.println("  </tr>");
    List<CodeTableInstance> codeTableInstanceList = CodeTableLogic.getCodeTables(releaseVersion, dataSession);
    for (CodeTableInstance cti : codeTableInstanceList) {
      String includeStatusLabel = "";
      if (releaseVersion.getReleaseStatus() != ReleaseStatus.PROPOSED) {
        // if release or deprecated do not show anything but current tables
        if (cti.getInclusionStatus() != InclusionStatus.INCLUDE) {
          continue;
        }
      }
      if (cti.getInclusionStatus() == InclusionStatus.REMOVE) {
        includeStatusLabel = " <span class=\"inclusionStatusLabel\">removed</span>";
      }
      if (cti.getInclusionStatus() == InclusionStatus.PROPOSED_INCLUDE) {
        includeStatusLabel = " <span class=\"inclusionStatusLabel\">proposed include</span>";
      }
      if (cti.getInclusionStatus() == InclusionStatus.PROPOSED_REMOVE) {
        includeStatusLabel = " <span class=\"inclusionStatusLabel\">proposed remove</span>";
      }
      String link = baseLink + PARAM_CODE_TABLE_INSTANCE_ID + "=" + cti.getTableInstanceId();
      String classString = "";
      if (codeTableInstance != null && codeTableInstance.equals(cti)) {
        classString = "selected";
      }
      if (cti.getInclusionStatus() == InclusionStatus.REMOVE) {
        classString += " strike";
      }
      String issueCountLabel = "";
      if (userSession.canEdit() && cti.getIssueCount() > 0) {
        if (cti.getIssueCount() == 1) {
          issueCountLabel = " <span class=\"issueCountLabel\">" + cti.getIssueCount() + " issue</span>";
        } else {
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
