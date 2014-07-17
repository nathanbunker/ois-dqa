package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openimmunizationsoftware.dqa.cm.SoftwareVersion;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.logic.thread.DeleteProposedVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.ReleaseNewVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.UpdateIssueCountThread;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

public class AdminUserServlet extends BaseServlet
{
  public AdminUserServlet() {
    super("Admin User");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  public static final String PARAM_ACTION = "action";
  public static final String PARAM_RELEASE_ID = "releaseId";

  public static final String ACTION_RELEASE_VERSION = "Release Version";
  public static final String ACTION_DELETE_VERSION = "Delete Version";
  public static final String ACTION_UPDATE_ISSUE_COUNTS = "Update Issue Counts";

  public static final String ACTION_ADD_USER = "Add User";
  public static final String PARAM_USER_ID = "userId";
  public static final String ACTION_UPDATE_USER = "Update User";
  public static final String ACTION_RESET_PASSWORD = "Reset Password";
  public static final String PARAM_USER_NAME = "userName";
  public static final String PARAM_EMAIL_ADDRESS = "emailAddress";
  public static final String PARAM_USER_TYPE = "userType";

  private static ReleaseNewVersionThread releaseNewVersionThread = null;
  private static DeleteProposedVersionThread deleteProposedVersionThread = null;
  private static UpdateIssueCountThread updateIssueCountThread = null;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    setup(req, resp);
    if (!isAdmin())
    {
      sendToHome(req, resp);
      return;
    }
    String action = req.getParameter(PARAM_ACTION);
    User userBeingEdited = null;
    if (req.getParameter(PARAM_USER_ID) != null)
    {
      int userId = readInt(PARAM_USER_ID, req);
      userBeingEdited = UserLogic.getUser(userId, dataSession);
    }
    if (action != null)
    {
      if (action.equals(ACTION_RELEASE_VERSION) && (releaseNewVersionThread == null || releaseNewVersionThread.isComplete()))
      {
        ReleaseVersion rv = ReleaseVersionLogic.getReleaseVersion(readInt(PARAM_RELEASE_ID, req), dataSession);
        releaseNewVersionThread = new ReleaseNewVersionThread(rv, userSession.getUser());
        releaseNewVersionThread.start();
      } else if (action.equals(ACTION_UPDATE_ISSUE_COUNTS) && (updateIssueCountThread == null || updateIssueCountThread.isComplete()))
      {
        ReleaseVersion rv = ReleaseVersionLogic.getReleaseVersion(readInt(PARAM_RELEASE_ID, req), dataSession);
        updateIssueCountThread = new UpdateIssueCountThread(rv, userSession.getUser());
        updateIssueCountThread.start();
      } else if (action.equals(ACTION_DELETE_VERSION))
      {
        ReleaseVersion rv = ReleaseVersionLogic.getReleaseVersion(readInt(PARAM_RELEASE_ID, req), dataSession);
        deleteProposedVersionThread = new DeleteProposedVersionThread(userSession.getUser(), rv);
        deleteProposedVersionThread.start();
      } else if (action.equals(ACTION_ADD_USER))
      {
        String userName = req.getParameter(PARAM_USER_NAME);
        if (userName.equals(""))
        {
          messageError = "Unable to add user, user name not specified";
        } else
        {
          userBeingEdited = UserLogic.getUser(userName, dataSession);
          if (userBeingEdited != null)
          {
            messageError = "Unable to add user, user name is already used";
          } else
          {
            userBeingEdited = new User();
            userBeingEdited.setUserName(userName);
            userBeingEdited.setEmailAddress(req.getParameter(PARAM_EMAIL_ADDRESS));
            UserLogic.createUser(userBeingEdited, dataSession);
            messageConfirmation = "User created, password is " + userBeingEdited.getPassword();
          }
        }
      } else if (action.equals(ACTION_UPDATE_USER))
      {
        String userName = req.getParameter(PARAM_USER_NAME);
        if (!userName.equals(userBeingEdited.getUserName()))
        {
          User otherUser = UserLogic.getUser(userName, dataSession);
          if (otherUser != null)
          {
            messageError = "Unable to update user, user name is already in used by a different acount";
          }
        }
        if (messageError == null)
        {
          String emailAddress = req.getParameter(PARAM_EMAIL_ADDRESS);
          String userTypeString = req.getParameter(PARAM_USER_TYPE);
          userBeingEdited.setEmailAddress(emailAddress);
          userBeingEdited.setUserTypeString(userTypeString);
          userBeingEdited.setUserName(userName);
          UserLogic.updateUser(userBeingEdited, dataSession);
          messageConfirmation = "User updated";
        }
      } else if (action.equals(ACTION_RESET_PASSWORD))
      {
        UserLogic.resetPassword(userBeingEdited, dataSession);
        messageConfirmation = "Password is now " + userBeingEdited.getPassword();
      }
    }

    createHeader();

    out.println("<div class=\"leftColumn\">");
    printCreateUser();
    out.println("</div>");

    out.println("<div class=\"centerColumn\">");
    printUsers(userBeingEdited);
    out.println("</div>");

    out.println("<div class=\"rightColumn\">");
    printEditUser(userBeingEdited);
    out.println("</div>");

    out.println("    <span class=\"cmVersion\">software version " + SoftwareVersion.VERSION + "</span>");
    createFooter();

  }

  public void printCreateUser()
  {
    out.println("  <form action=\"adminUser\" method=\"POST\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Create User</caption>");
    out.println("    <tr>");
    out.println("      <th>User Name</th>");
    out.println("      <td><input type=\"text\" name=\"" + PARAM_USER_NAME + "\" size=\"30\"/></td>");
    out.println("    <tr>");
    out.println("    <tr>");
    out.println("      <th>Email</th>");
    out.println("      <td><input type=\"text\" name=\"" + PARAM_EMAIL_ADDRESS + "\" size=\"30\"/></td>");
    out.println("    <tr>");
    out.println("    <tr>");
    out.println("      <td colspan=\"2\" align=\"right\">");
    out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_ADD_USER + "\"/>");
    out.println("      </td>");
    out.println("    <tr>");
    out.println("  </table>");
    out.println("  </form>");
    out.println("  <br/>");
  }

  public void printUsers(User userBeingEdited)
  {
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Users</caption>");
    out.println("    <tr>");
    out.println("      <th>User Name</th>");
    out.println("      <th>User Type</th>");
    out.println("    <tr>");
    List<User> userList = UserLogic.getUserList(dataSession);
    for (User u : userList)
    {
      String link = "adminUser?userId=" + u.getUserId();
      String classString = "";
      if (u.equals(userBeingEdited))
      {
        classString = "selected";
      }
      out.println("    <tr>");
      out.println("      <td class=\"" + classString + "\"><a href=\"" + link + "\">" + u.getUserName() + "</a></td>");
      out.println("      <td class=\"" + classString + "\"><a href=\"" + link + "\">" + u.getUserType() + "</a></td>");
      out.println("    <tr>");
    }
    out.println("  </table>");
  }

  public void printEditUser(User userBeingEdited)
  {
    if (userBeingEdited != null)
    {
      out.println("  <form action=\"adminUser\" method=\"POST\">");
      out.println("  <input type=\"hidden\" name=\"" + PARAM_USER_ID + "\" value=\"" + userBeingEdited.getUserId() + "\"/>");
      out.println("  <table width=\"100%\">");
      out.println("    <caption>" + userBeingEdited.getUserName() + "</caption>");
      out.println("    <tr>");
      out.println("      <th>User Id</th>");
      out.println("      <td>" + userBeingEdited.getUserId() + "</td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>User Name</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_USER_NAME + "\" value=\"" + userBeingEdited.getUserName()
          + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>Emaile</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_EMAIL_ADDRESS + "\" value=\"" + userBeingEdited.getEmailAddress()
          + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>User Type</th>");
      out.println("      <td>");
      out.println("         <select name=\"" + PARAM_USER_TYPE + "\">");
      for (UserType userType : UserType.values())
      {
        out.println("           <option value=\"" + userType.getId() + "\"" + (userType == userBeingEdited.getUserType() ? " selected=\"true\"" : "")
            + ">" + userType.getLabel() + "</option>");
      }
      out.println("         </select>");
      out.println("      </td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <td colspan=\"2\" align=\"right\">");
      out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_RESET_PASSWORD + "\"/>");
      out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_UPDATE_USER + "\"/>");
      out.println("      </td>");
      out.println("    <tr>");
      out.println("  </table>");
      out.println("  <br/>");
      out.println("  </form>");
    }
  }

}
