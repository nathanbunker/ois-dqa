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
import javax.servlet.http.HttpServlet;
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
import org.openimmunizationsoftware.dqa.cm.model.MemberType;
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
  public static final String ACTION_SEARCH_USER = "Search User";
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
  public static final String PARAM_RESET_PASSWORD = "resetPassword";
  public static final String PARAM_USER_TYPE = "userType";
  public static final String PARAM_MEMBER_TYPE = "memberType";
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
      } else if (action.equals(ACTION_SEARCH_USER))
      {
        UserSearchOptions userSearchOptions = userSession.getUserSearchOptions();
        userSearchOptions.setEmailAddress(req.getParameter(PARAM_EMAIL_ADDRESS));
        if (req.getParameter(PARAM_MEMBER_TYPE).equals(""))
        {
          userSearchOptions.setMemberType(null);
        } else
        {
          userSearchOptions.setMemberType(MemberType.get(req.getParameter(PARAM_MEMBER_TYPE)));
        }
        userSearchOptions.setOrganization(req.getParameter(PARAM_ORGANIZATION));
        userSearchOptions.setUserName(req.getParameter(PARAM_USER_NAME));
        for (Application application : ApplicationLogic.getApplications(dataSession))
        {
          String userTypeString = req.getParameter(PARAM_USER_TYPE + "." + application.getApplicationId());
          if (userTypeString.equals(""))
          {
            userSearchOptions.getApplicationUserTypeMap().remove(application);
          } else
          {
            UserType userType = UserType.get(userTypeString);
            userSearchOptions.getApplicationUserTypeMap().put(application, userType);
          }
        }
      } else if (action.equals(ACTION_ADD_USER))
      {
        String userName = req.getParameter(PARAM_USER_NAME);
        if (userName.equals(""))
        {
          userSession.setMessageError("Unable to add user, user name not specified");
        } else
        {
          userBeingEdited = UserLogic.getUserWithUsername(userName, dataSession);
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
              userBeingEdited.setMemberTypeString(req.getParameter(PARAM_MEMBER_TYPE));
              userBeingEdited.setPhoneNumber(req.getParameter(PARAM_PHONE_NUMBER));
              userBeingEdited.setAdminComments(req.getParameter(PARAM_ADMIN_COMMENTS));
              userBeingEdited.setResetPassword(true);
              List<Application> applicationList = new ArrayList<Application>();
              applicationList.add(application);
              UserLogic.createUser(userBeingEdited, applicationList, userType, dataSession);

              userSession.setMessageConfirmation("User created, password is " + userBeingEdited.getPassword());
            }
          }
        }
      } else if (action.equals(ACTION_UPDATE_USER))
      {
        String userName = req.getParameter(PARAM_USER_NAME);
        if (!userName.equals(userBeingEdited.getUserName()))
        {
          User otherUser = UserLogic.getUserWithUsername(userName, dataSession);
          if (otherUser != null)
          {
            userSession.setMessageError("Unable to update user, user name is already in used by a different acount");
          }
        }
        String memberTypeString = req.getParameter(PARAM_MEMBER_TYPE);
        if (userSession.getMessageError() == null)
        {
          if (memberTypeString.equals(""))
          {
            userSession.setMessageError("Member type must be indicated. ");
          }
        }
        if (userSession.getMessageError() == null)
        {
          String emailAddress = req.getParameter(PARAM_EMAIL_ADDRESS);
          userBeingEdited.setEmailAddress(emailAddress);
          userBeingEdited.setUserName(userName);
          userBeingEdited.setOrganization(req.getParameter(PARAM_ORGANIZATION));
          userBeingEdited.setPositionTitle(req.getParameter(PARAM_POSITION_TITLE));
          userBeingEdited.setMemberTypeString(memberTypeString);
          userBeingEdited.setPhoneNumber(req.getParameter(PARAM_PHONE_NUMBER));
          userBeingEdited.setAdminComments(req.getParameter(PARAM_ADMIN_COMMENTS));
          userBeingEdited.setResetPassword(req.getParameter(PARAM_RESET_PASSWORD) != null);
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
    printSearchUser(userSession);
    printCreateUser(userSession);
    out.println("</div>");

    out.println("<div class=\"centerColumn\">");
    printUsers(userBeingEdited, userSession);
    out.println("</div>");

    out.println("<div class=\"rightColumn\">");
    printEditUser(userBeingEdited, userSession, req);
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

  public void printSearchUser(UserSession userSession)
  {
    UserSearchOptions userSearchOptions = userSession.getUserSearchOptions();
    PrintWriter out = userSession.getOut();
    out.println("  <form action=\"adminUser\" method=\"POST\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Search User</caption>");
    out.println("    <tr>");
    out.println("      <th>User Name</th>");
    out.println(
        "      <td><input type=\"text\" name=\"" + PARAM_USER_NAME + "\" size=\"30\" value=\"" + userSearchOptions.getUserName() + "\"/></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Email</th>");
    out.println("      <td><input type=\"text\" name=\"" + PARAM_EMAIL_ADDRESS + "\" size=\"30\" value=\"" + userSearchOptions.getEmailAddress()
        + "\"/></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Organization</th>");
    out.println("      <td><input type=\"text\" name=\"" + PARAM_ORGANIZATION + "\" size=\"30\" value=\"" + userSearchOptions.getOrganization()
        + "\"/></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Member Type</th>");
    out.println("      <td>");
    out.println("         <select name=\"" + PARAM_MEMBER_TYPE + "\">");
    MemberType memberTypeSelected = userSearchOptions.getMemberType();
    out.println("           <option value=\"\"" + (memberTypeSelected == null ? " selected=\"true\"" : "") + ">-select-</option>");
    for (MemberType memberType : MemberType.values())
    {
      out.println("           <option value=\"" + memberType.getId() + "\"" + (memberType == memberTypeSelected ? " selected=\"true\"" : "") + ">"
          + memberType.getLabel() + "</option>");
    }
    out.println("         </select>");
    out.println("      </td>");
    out.println("    </tr>");
    for (Application application : ApplicationLogic.getApplications(userSession.getDataSession()))
    {
      UserType userTypeSelected = userSearchOptions.getApplicationUserTypeMap().get(application);
      out.println("    <tr>");
      out.println("      <th>" + application.getApplicationAcronym() + " User Type</th>");
      out.println("      <td>");
      out.println("         <select name=\"" + PARAM_USER_TYPE + "." + application.getApplicationId() + "\">");
      out.println("           <option value=\"\"" + (userTypeSelected == null ? " selected=\"true\"" : "") + ">-select-</option>");
      for (UserType userType : UserType.values())
      {
        out.println("           <option value=\"" + userType.getId() + "\"" + (userType == userTypeSelected ? " selected=\"true\"" : "") + ">"
            + userType.getLabel() + "</option>");
      }
      out.println("         </select>");
      out.println("      </td>");
      out.println("    </tr>");
    }

    out.println("    <tr>");
    out.println("      <td colspan=\"2\" align=\"right\">");
    out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_SEARCH_USER + "\"/>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("  </form>");
    out.println("  <br/>");
  }

  public void printUsers(User userBeingEdited, UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
    List<User> userList;
    UserSearchOptions userSearchOptions = userSession.getUserSearchOptions();
    Session dataSession = userSession.getDataSession();
    boolean showingPending = false;
    if (!userSearchOptions.isSearchOptionsSet())
    {
      showingPending = true;
      userSearchOptions = new UserSearchOptions();
      for (Application application : ApplicationLogic.getApplications(userSession.getDataSession()))
      {
        userSearchOptions.getApplicationUserTypeMap().put(application, UserType.PENDING);
      }
    }
    userList = searchUsers(userSearchOptions, dataSession);

    out.println("  <table width=\"100%\">");
    if (showingPending)
    {
      out.println("    <caption>Pending Users</caption>");
    } else
    {
      out.println("    <caption>Users Matching Search</caption>");
    }
    out.println("    <tr>");
    out.println("      <th>Organization</th>");
    out.println("      <th>User Name</th>");
    // out.println(" <th>User Type</th>");
    out.println("    <tr>");
    for (User u : userList)
    {
      String link = "adminUser?userId=" + u.getUserId();
      String classString = "";
      if (u.equals(userBeingEdited))
      {
        classString = "selected";
      }
      out.println("    <tr>");
      out.println("      <td class=\"" + classString + "\"><a href=\"" + link + "\">" + n(u.getOrganization()) + "</a></td>");
      out.println("      <td class=\"" + classString + "\"><a href=\"" + link + "\">" + u.getUserName() + "</a></td>");
      out.println("    <tr>");
    }
    out.println("  </table>");
  }

  public static List<User> searchUsers(UserSearchOptions userSearchOptions, Session dataSession)
  {
    List<User> userList;
    if (userSearchOptions.getApplicationUserTypeMap().size() == 0)
    {
      StringBuilder queryString = new StringBuilder();
      queryString.append("from User where ");
      addSearchOptions(userSearchOptions, queryString, "");
      queryString.append("order by organization, userName");
      Query query = dataSession.createQuery(queryString.toString());
      setParameters(userSearchOptions, query);
      userList = query.list();
    } else
    {
      StringBuilder queryString = new StringBuilder();
      queryString.append("from ApplicationUser where ");
      boolean first = addSearchOptions(userSearchOptions, queryString, "user.");
      first = addAnd(queryString, first);
      queryString.append("(");
      first = true;
      for (Application application : userSearchOptions.getApplicationUserTypeMap().keySet())
      {
        first = addOr(queryString, first);
        queryString.append("(application = ? and userTypeString = ?) ");
      }
      queryString.append(") ");
      queryString.append("order by user.organization, user.userName");

      Query query = dataSession.createQuery(queryString.toString());
      int pos = setParameters(userSearchOptions, query);
      for (Application application : userSearchOptions.getApplicationUserTypeMap().keySet())
      {
        String userTypeString = userSearchOptions.getApplicationUserTypeMap().get(application).getId();
        query.setParameter(pos++, application);
        query.setParameter(pos++, userTypeString);
      }
      userList = new ArrayList<User>();
      List<ApplicationUser> applicationUserList = query.list();
      for (ApplicationUser applicationUser : applicationUserList)
      {
        if (!userList.contains(applicationUser.getUser()))
        {
          userList.add(applicationUser.getUser());
        }
      }
    }
    return userList;
  }

  public static int setParameters(UserSearchOptions userSearchOptions, Query query)
  {
    int pos = 0;
    if (!userSearchOptions.getUserName().equals(""))
    {
      query.setParameter(pos++, "%" + userSearchOptions.getUserName() + "%");
    }
    if (!userSearchOptions.getEmailAddress().equals(""))
    {
      query.setParameter(pos++, "%" + userSearchOptions.getEmailAddress() + "%");
    }
    if (!userSearchOptions.getOrganization().equals(""))
    {
      query.setParameter(pos++, "%" + userSearchOptions.getOrganization() + "%");
    }
    if (userSearchOptions.getMemberType() != null)
    {
      query.setParameter(pos++, userSearchOptions.getMemberType().getId());
    }
    return pos;
  }

  public static boolean addSearchOptions(UserSearchOptions userSearchOptions, StringBuilder queryString, String prepend)
  {
    boolean first = true;
    if (!userSearchOptions.getUserName().equals(""))
    {
      first = addAnd(queryString, first);
      queryString.append(prepend + "userName like ? ");
    }
    if (!userSearchOptions.getEmailAddress().equals(""))
    {
      first = addAnd(queryString, first);
      queryString.append(prepend + "emailAddress like ? ");
    }
    if (!userSearchOptions.getOrganization().equals(""))
    {
      first = addAnd(queryString, first);
      queryString.append(prepend + "organization like ? ");
    }
    if (userSearchOptions.getMemberType() != null)
    {
      first = addAnd(queryString, first);
      queryString.append(prepend + "memberTypeString = ? ");
    }
    return first;
  }

  public static boolean addAnd(StringBuilder queryString, boolean first)
  {
    if (!first)
    {
      queryString.append("and ");
    }
    return false;
  }

  public static boolean addOr(StringBuilder queryString, boolean first)
  {
    if (!first)
    {
      queryString.append("or ");
    }
    return false;
  }

  public void printEditUser(User userBeingEdited, UserSession userSession, HttpServletRequest req)
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
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>User Name</th>");
      out.println(
          "      <td><input type=\"text\" name=\"" + PARAM_USER_NAME + "\" value=\"" + userBeingEdited.getUserName() + "\" size=\"30\"/></td>");
      out.println("    <tr>");
      out.println("    <tr>");
      out.println("      <th>Email</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_EMAIL_ADDRESS + "\" value=\"" + userBeingEdited.getEmailAddress()
          + "\" size=\"30\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Organization</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_ORGANIZATION + "\" value=\"" + n(userBeingEdited.getOrganization())
          + "\" size=\"30\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Position Title</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_POSITION_TITLE + "\" value=\"" + n(userBeingEdited.getPositionTitle())
          + "\" size=\"30\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Member Type</th>");
      out.println("      <td>");
      out.println("         <select name=\"" + PARAM_MEMBER_TYPE + "\">");
      MemberType memberTypeSelected = userBeingEdited.getMemberType();
      out.println("           <option value=\"\"" + (memberTypeSelected == null ? " selected=\"true\"" : "") + ">-select-</option>");
      for (MemberType memberType : MemberType.values())
      {
        out.println("           <option value=\"" + memberType.getId() + "\"" + (memberType == memberTypeSelected ? " selected=\"true\"" : "") + ">"
            + memberType.getLabel() + "</option>");
      }
      out.println("         </select>");
      out.println("      </td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Phone</th>");
      out.println("      <td><input type=\"text\" name=\"" + PARAM_PHONE_NUMBER + "\" value=\"" + n(userBeingEdited.getPhoneNumber())
          + "\" size=\"12\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Comments</th>");
      out.println("      <td><textarea name=\"" + PARAM_ADMIN_COMMENTS + "\" rows=\"3\" cols=\"35\">" + n(userBeingEdited.getAdminComments())
          + "</textarea></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Reset Password</th>");
      out.println("      <td><input type=\"checkbox\" name=\"" + PARAM_RESET_PASSWORD + "\" value=\"Y\""
          + (userBeingEdited.isResetPassword() ? " checked=\"\"" : "") + "/></td>");
      out.println("    </tr>");
      boolean isAartUser = false;
      for (Application application : ApplicationLogic.getApplications(userSession.getDataSession()))
      {
        out.println("    <tr>");
        out.println("      <th>" + application.getApplicationAcronym() + " User Type</th>");
        out.println("      <td>");
        out.println("         <select name=\"" + PARAM_USER_TYPE + "." + application.getApplicationId() + "\">");
        UserType userTypeSelected = null;
        String userTypeString = req.getParameter(PARAM_USER_TYPE + "." + application.getApplicationId());
        if (userTypeString == null)
        {
          for (ApplicationUser applicationUser : userBeingEdited.getApplicationUserList())
          {
            if (applicationUser.getApplication() == application)
            {
              userTypeSelected = applicationUser.getUserType();
              break;
            }
          }
        } else
        {
          userTypeSelected = UserType.get(userTypeString);
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
        out.println("    </tr>");
      }
      out.println("    <tr>");
      out.println("      <td colspan=\"2\" align=\"right\">");
      out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_RESET_PASSWORD + "\"/>");
      out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_UPDATE_USER + "\"/>");
      out.println("      </td>");
      out.println("    </tr>");
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
