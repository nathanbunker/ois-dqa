package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.SoftwareVersion;
import org.openimmunizationsoftware.dqa.cm.logic.ApplicationLogic;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.logic.thread.DeleteProposedVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.ReleaseNewVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.UpdateIssueCountThread;
import org.openimmunizationsoftware.dqa.cm.model.Application;
import org.openimmunizationsoftware.dqa.cm.model.ApplicationUser;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.ReportUser;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;
import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;

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
  public static final String ACTION_UPDATE_REPORT_USER = "Update Report Access";
  public static final String ACTION_RESET_PASSWORD = "Reset Password";
  public static final String PARAM_USER_NAME = "userName";
  public static final String PARAM_EMAIL_ADDRESS = "emailAddress";
  public static final String PARAM_ORGANIZATION = "organization";
  public static final String PARAM_POSITION_TITLE = "positionTitle";
  public static final String PARAM_PHONE_NUMBER = "phoneNumber";
  public static final String PARAM_ADMIN_COMMENTS = "adminComments";
  public static final String PARAM_USER_TYPE = "userType";
  public static final String PARAM_APPLICATION_ID = "applicationId";
  public static final String PARAM_REPORT_ROLE = "reportRole";
  public static final String PARAM_TEST_PARTICIPANT_ID = "testParticipantId";

  private static ReleaseNewVersionThread releaseNewVersionThread = null;
  private static DeleteProposedVersionThread deleteProposedVersionThread = null;
  private static UpdateIssueCountThread updateIssueCountThread = null;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    if (!userSession.isAdmin())
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
          userSession.setMessageError("Unable to add user, user name not specified");
        } else
        {
          userBeingEdited = UserLogic.getUser(userName, dataSession);
          if (userBeingEdited != null)
          {
            userSession.setMessageError("Unable to add user, user name is already used");
          } else
          {
            String applicationIdString = req.getParameter(PARAM_APPLICATION_ID);
            String userTypeString = req.getParameter(PARAM_USER_TYPE);
            if (applicationIdString.equals(""))
            {
              userSession.setMessageError("Unable to add user, application was not selected");
            } else if (userTypeString.equals(""))
            {
              userSession.setMessageError("Unable to add user, user type was not selected");
            } else
            {
              int applicationId = Integer.parseInt(applicationIdString);
              Application application = ApplicationLogic.getApplication(applicationId, dataSession);
              UserType userType = UserType.get(userTypeString);
              userBeingEdited = new User();
              userBeingEdited.setUserName(userName);
              userBeingEdited.setEmailAddress(req.getParameter(PARAM_EMAIL_ADDRESS));
              userBeingEdited.setOrganization(req.getParameter(PARAM_ORGANIZATION));
              userBeingEdited.setPositionTitle(req.getParameter(PARAM_POSITION_TITLE));
              userBeingEdited.setPhoneNumber(req.getParameter(PARAM_PHONE_NUMBER));
              userBeingEdited.setAdminComments(req.getParameter(PARAM_ADMIN_COMMENTS));
              UserLogic.createUser(userBeingEdited, application, userType, dataSession);
              userSession.setMessageConfirmation("User created, password is " + userBeingEdited.getPassword());
            }
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
            userSession.setMessageError("Unable to update user, user name is already in used by a different acount");
          }
        }
        if (userSession.getMessageError() == null)
        {
          String emailAddress = req.getParameter(PARAM_EMAIL_ADDRESS);
          userBeingEdited.setEmailAddress(emailAddress);
          userBeingEdited.setUserName(userName);
          userBeingEdited.setOrganization(req.getParameter(PARAM_ORGANIZATION));
          userBeingEdited.setPositionTitle(req.getParameter(PARAM_POSITION_TITLE));
          userBeingEdited.setPhoneNumber(req.getParameter(PARAM_PHONE_NUMBER));
          userBeingEdited.setAdminComments(req.getParameter(PARAM_ADMIN_COMMENTS));
          UserLogic.updateUser(userBeingEdited, dataSession);

          for (Application application : ApplicationLogic.getApplications(dataSession))
          {
            String userTypeString = req.getParameter(PARAM_USER_TYPE + "." + application.getApplicationId());
            UserType userType = null;
            if (!userTypeString.equals(""))
            {
              userType = UserType.get(userTypeString);
            }
            UserLogic.createOrUpdateApplicationUser(userBeingEdited, application, userType, dataSession);
          }
          userSession.setMessageConfirmation("User updated");
        }
      } else if (action.equals(ACTION_RESET_PASSWORD))
      {
        UserLogic.resetPassword(userBeingEdited, dataSession);
        userSession.setMessageConfirmation("Password is now " + userBeingEdited.getPassword());
      } else if (action.equals(ACTION_UPDATE_REPORT_USER))
      {
        List<TestParticipant> testParticipantListSelected = new ArrayList<TestParticipant>(userBeingEdited.getReportUserMap().keySet());
        for (TestParticipant testParticipant : testParticipantListSelected)
        {
          ReportUser reportUser = userBeingEdited.getReportUserMap().get(testParticipant);
          String reportRoleString = req.getParameter(PARAM_REPORT_ROLE + "." + reportUser.getReportUserId());
          reportUser.setReportRoleString(reportRoleString);
        }
        String testParticipantIdString = req.getParameter(PARAM_TEST_PARTICIPANT_ID);
        String reportRoleString = req.getParameter(PARAM_REPORT_ROLE);
        if (!testParticipantIdString.equals("") && !reportRoleString.equals(""))
        {
          TestParticipant testParticipant = (TestParticipant) dataSession.get(TestParticipant.class, Integer.parseInt(testParticipantIdString));
          ReportUser reportUser = new ReportUser();
          reportUser.setUser(userBeingEdited);
          reportUser.setTestParticipant(testParticipant);
          reportUser.setAuthorizedByUser(userSession.getUser());
          reportUser.setAuthorizedDate(new Date());
          reportUser.setReportRoleString(reportRoleString);
          userBeingEdited.getReportUserMap().put(testParticipant, reportUser);
        }
        UserLogic.updateUser(userBeingEdited, dataSession);
      }
    }

    createHeader(webSession);

    out.println("<div class=\"leftColumn\">");
    printCreateUser(userSession);
    out.println("</div>");

    out.println("<div class=\"centerColumn\">");
    printUsers(userBeingEdited, userSession);
    out.println("</div>");

    out.println("<div class=\"rightColumn\">");
    printEditUser(userBeingEdited, userSession);
    out.println("</div>");

    out.println("    <span class=\"cmVersion\">software version " + SoftwareVersion.VERSION + "</span>");
    createFooter(webSession);

  }

  public void printCreateUser(UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
    out.println("  <form action=\"adminUser\" method=\"POST\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Create User</caption>");
    out.println("    <tr>");
    out.println("      <th>User Name</th>");
    out.println("      <td><input type=\"text\" name=\"" + PARAM_USER_NAME + "\" size=\"30\"/></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Email</th>");
    out.println("      <td><input type=\"text\" name=\"" + PARAM_EMAIL_ADDRESS + "\" size=\"30\"/></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Application</th>");
    out.println("      <td>");
    out.println("         <select name=\"" + PARAM_APPLICATION_ID + "\">");
    for (Application application : ApplicationLogic.getApplications(userSession.getDataSession()))
    {
      out.println("           <option value=\"" + application.getApplicationId() + "\">" + application.getApplicationAcronym() + "</option>");
    }
    out.println("         </select>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>User Type</th>");
    out.println("      <td>");
    out.println("         <select name=\"" + PARAM_USER_TYPE + "\">");
    for (UserType userType : UserType.values())
    {
      out.println("           <option value=\"" + userType.getId() + "\">" + userType.getLabel() + "</option>");
    }
    out.println("         </select>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <td colspan=\"2\" align=\"right\">");
    out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_ADD_USER + "\"/>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("  </form>");
    out.println("  <br/>");
  }

  public void printUsers(User userBeingEdited, UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Users</caption>");
    out.println("    <tr>");
    out.println("      <th>User Name</th>");
    // out.println(" <th>User Type</th>");
    out.println("    <tr>");
    List<User> userList = UserLogic.getUserList(userSession.getDataSession());
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
      // out.println(" <td class=\"" + classString + "\"><a href=\""
      // + link + "\">" + u.getUserType() + "</a></td>");
      out.println("    <tr>");
    }
    out.println("  </table>");
  }

  public void printEditUser(User userBeingEdited, UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
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
      out.println(
          "      <td><input type=\"text\" name=\"" + PARAM_USER_NAME + "\" value=\"" + userBeingEdited.getUserName() + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>Email</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_EMAIL_ADDRESS + "\" value=\"" + userBeingEdited.getEmailAddress()
          + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>Organization</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_ORGANIZATION + "\" value=\"" + n(userBeingEdited.getOrganization())
          + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>Position Title</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_POSITION_TITLE + "\" value=\"" + n(userBeingEdited.getPositionTitle())
          + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>Phone</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_PHONE_NUMBER + "\" value=\"" + n(userBeingEdited.getPhoneNumber())
          + "\" size=\"12\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>Comments</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_ADMIN_COMMENTS + "\" value=\"" + n(userBeingEdited.getAdminComments())
          + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      boolean isAartUser = false;
      for (Application application : ApplicationLogic.getApplications(userSession.getDataSession()))
      {
        out.println("    <tr>");
        out.println("      <th>" + application.getApplicationAcronym() + " User Type</th>");
        out.println("      <td>");
        out.println("         <select name=\"" + PARAM_USER_TYPE + "." + application.getApplicationId() + "\">");
        UserType userTypeSelected = null;
        for (ApplicationUser applicationUser : userBeingEdited.getApplicationUserList())
        {
          if (applicationUser.getApplication() == application)
          {
            userTypeSelected = applicationUser.getUserType();
            break;
          }
        }
        out.println("           <option value=\"\"" + (userTypeSelected == null ? " selected=\"true\"" : "") + ">none</option>");
        for (UserType userType : UserType.values())
        {
          out.println("           <option value=\"" + userType.getId() + "\"" + (userType == userTypeSelected ? " selected=\"true\"" : "") + ">"
              + userType.getLabel() + "</option>");
        }
        out.println("         </select>");
        out.println("      </td>");
        if (application.isApplicationAart() && userTypeSelected != null)
        {
          isAartUser = userTypeSelected == UserType.ADMIN || userTypeSelected == UserType.EXPERT;
        }
      }
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

      if (isAartUser)
      {
        out.println("  <form action=\"adminUser\" method=\"POST\">");
        out.println("  <input type=\"hidden\" name=\"" + PARAM_USER_ID + "\" value=\"" + userBeingEdited.getUserId() + "\"/>");
        out.println("  <table width=\"100%\">");
        out.println("    <caption>AART Report Access</caption>");
        out.println("    <tr>");
        out.println("      <th>Connection Label</th>");
        out.println("      <th>Report Role</th>");
        out.println("      <th>Granted By</th>");
        out.println("      <th>Date</th>");
        out.println("    </tr>");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        List<TestParticipant> testParticipantListSelected = new ArrayList<TestParticipant>(userBeingEdited.getReportUserMap().keySet());
        Collections.sort(testParticipantListSelected, new Comparator<TestParticipant>() {
          @Override
          public int compare(TestParticipant tp1, TestParticipant tp2)
          {
            return tp1.getConnectionLabel().compareTo(tp2.getConnectionLabel());
          }
        });
        for (TestParticipant testParticipant : testParticipantListSelected)
        {
          ReportUser reportUser = userBeingEdited.getReportUserMap().get(testParticipant);
          out.println("    <tr>");
          out.println("      <td>" + testParticipant.getConnectionLabel() + "</td>");
          out.println("      <td>");
          out.println("         <select name=\"" + PARAM_REPORT_ROLE + "." + reportUser.getReportUserId() + "\">");
          UserType reportRoleSelected = reportUser.getReportRole();
          for (UserType userType : UserType.values())
          {
            out.println("           <option value=\"" + userType.getId() + "\"" + (userType == reportRoleSelected ? " selected=\"true\"" : "") + ">"
                + userType.getLabel() + "</option>");
          }
          out.println("         </select>");
          out.println("      </td>");
          out.println("      <td>" + reportUser.getAuthorizedByUser().getUserName() + "</td>");
          out.println("      <td>" + sdf.format(reportUser.getAuthorizedDate()) + "</td>");
          out.println("    </tr>");
        }
        out.println("    <tr>");
        out.println("      <td>");
        List<TestParticipant> testParticipantList = null;
        {
          Query query = userSession.getDataSession().createQuery("from TestParticipant order by connectionLabel");
          testParticipantList = query.list();
        }

        out.println("         <select name=\"" + PARAM_TEST_PARTICIPANT_ID + "\">");
        out.println("           <option value=\"\">--select--</option>");
        for (TestParticipant tp : testParticipantList)
        {
          if (!testParticipantListSelected.contains(tp))
          {
            out.println("           <option value=\"" + tp.getTestParticipantId() + "\">" + tp.getConnectionLabel() + "</option>");
          }
        }
        out.println("      </td>");
        out.println("      <td>");
        out.println("         <select name=\"" + PARAM_REPORT_ROLE + "\">");
        out.println("           <option value=\"\">--select--</option>");
        for (UserType userType : UserType.values())
        {
          out.println("           <option value=\"" + userType.getId() + "\">" + userType.getLabel() + "</option>");
        }
        out.println("         </select>");
        out.println("      </td>");
        out.println("      <td colspan=\"2\"></td>");
        out.println("    </tr>");
        out.println("    <tr>");
        out.println("      <td colspan=\"4\" align=\"right\">");
        out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_UPDATE_REPORT_USER + "\"/>");
        out.println("      </td>");
        out.println("    <tr>");
        out.println("  </table>");
        out.println("  <br/>");
        out.println("  </form>");

      }
    }
  }

}
