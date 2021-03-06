package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.SoftwareVersion;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.logic.LoadCdcDataLogic;
import org.openimmunizationsoftware.dqa.cm.logic.LoadCdcDataLogic.LoadResult;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.logic.thread.DeleteProposedVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.ReleaseNewVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.UpdateIssueCountThread;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileManager;

@SuppressWarnings("serial")
public class AdminServlet extends BaseServlet
{
  public AdminServlet() {
    super("Admin");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  public static final String PARAM_ACTION = "action";
  public static final String PARAM_RELEASE_ID = "releaseId";
  public static final String PARAM_CDC_TEXT = "cdcText";
  public static final String PARAM_SYSTEM_WIDE_MESSAGE = "systemWideMessage";
  
  public static final String ACTION_LOAD_CODES = "Load Codes";
  public static final String ACTION_RELEASE_VERSION = "Release Version";
  public static final String ACTION_DELETE_VERSION = "Delete Version";
  public static final String ACTION_UPDATE_ISSUE_COUNTS = "Update Issue Counts";
  public static final String ACTION_RECTIFY_PROFILE_FIELDS = "Rectify Profile Fields";
  public static final String ACTION_SET_SYSTEM_WIDE_MESSAGE = "Set System Wide Message";

  private static ReleaseNewVersionThread releaseNewVersionThread = null;
  private static DeleteProposedVersionThread deleteProposedVersionThread = null;
  private static UpdateIssueCountThread updateIssueCountThread = null;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    List<LoadResult> loadResultList = null;
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    if (!userSession.isAdmin())
    {
      sendToHome(req, resp);
      return;
    }
    try
    {
      String action = req.getParameter(PARAM_ACTION);
      if (action != null)
      {
        if (action.equals(ACTION_RELEASE_VERSION) && (releaseNewVersionThread == null || releaseNewVersionThread.isComplete()))
        {
          releaseVersion(req, userSession, dataSession);
        } else if (action.equals(ACTION_UPDATE_ISSUE_COUNTS) && (updateIssueCountThread == null || updateIssueCountThread.isComplete()))
        {
          updateIssueCounts(req, userSession, dataSession);
        } else if (action.equals(ACTION_DELETE_VERSION))
        {
          deleteVersion(req, userSession, dataSession);
        } else if (action.equals(ACTION_LOAD_CODES))
        {
          loadResultList = loadCodes(req, loadResultList, userSession, dataSession);
        } else if (action.equals(ACTION_SET_SYSTEM_WIDE_MESSAGE))
        {
          setSystemWideMessage(req.getParameter(PARAM_SYSTEM_WIDE_MESSAGE));
        } else if (action.equals(ACTION_RECTIFY_PROFILE_FIELDS))
        {
          ProfileManager.rectifyProfileFields(dataSession);
        }
      }

      createHeader(webSession);
      
      out.println("<div class=\"leftColumn\">");
      printUserActivity(userSession, out);
      out.println("</div>");

      out.println("<div class=\"centerColumn\">");
      printReleaseMaintenance(out, dataSession);
      printUpdateCdcTables(out, dataSession);
      printSystemWideMessageTable(out, dataSession);
      out.println("</div>");

      out.println("<div class=\"rightColumn\">");
      printLoadedCodes(loadResultList, out);
      // out.println("<p><a href=\"setup\">Initial DQAcm Setup</a></p>");
      out.println("<p><a href=\"admin?action=" + ACTION_RECTIFY_PROFILE_FIELDS + "\">Rectify Profile Field</a></p>");
      out.println("</div>");

    } catch (Exception e)
    {
      handleError(e, webSession);
    } finally
    {
      createFooter(webSession);
    }
  }

  public void printLoadedCodes(List<LoadResult> loadResultList, PrintWriter out)
  {
    if (loadResultList != null)
    {
      out.println("<table width=\"100%\">");
      out.println("  <caption>Codes Loaded</caption>");
      out.println("  <tr>");
      out.println("    <th>Value</th>");
      out.println("    <th>Label</th>");
      out.println("    <th>Status</th>");
      out.println("  </tr>");
      for (LoadResult loadResult : loadResultList)
      {
        out.println("  <tr>");
        if (loadResult.getCodeInstance() == null)
        {
          out.println("    <td colspan=\"2\">No Code Instance</td>");
          out.println("    <td>" + loadResult.getLoadStatus() + "</td>");
        } else
        {
          out.println("    <td>" + (loadResult.getCodeInstance().getCode() == null ? "###" : loadResult.getCodeInstance().getCode().getCodeValue())
              + "</td>");
          out.println("    <td>" + loadResult.getCodeInstance().getCodeLabel() + "</td>");
          out.println("    <td>" + loadResult.getLoadStatus() + "</td>");
        }
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public List<LoadResult> loadCodes(HttpServletRequest req, List<LoadResult> loadResultList, UserSession userSession, Session dataSession)
  {
    CodeTableInstance codeTableInstance = null;
    if (!req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID).equals(""))
    {
      int codeTableInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID));
      codeTableInstance = CodeTableInstanceLogic.getCodeTableInstance(codeTableInstanceId, dataSession);
      String data = req.getParameter(PARAM_CDC_TEXT);
      loadResultList = LoadCdcDataLogic.loadCdcData(codeTableInstance, userSession.getUser(), data, dataSession);
    }
    return loadResultList;
  }

  public void deleteVersion(HttpServletRequest req, UserSession userSession, Session dataSession)
  {
    ReleaseVersion rv = ReleaseVersionLogic.getReleaseVersion(readInt(PARAM_RELEASE_ID, req), dataSession);
    deleteProposedVersionThread = new DeleteProposedVersionThread(userSession.getUser(), rv);
    deleteProposedVersionThread.start();
  }

  public void updateIssueCounts(HttpServletRequest req, UserSession userSession, Session dataSession)
  {
    ReleaseVersion rv = ReleaseVersionLogic.getReleaseVersion(readInt(PARAM_RELEASE_ID, req), dataSession);
    updateIssueCountThread = new UpdateIssueCountThread(rv, userSession.getUser());
    updateIssueCountThread.start();
  }

  public void releaseVersion(HttpServletRequest req, UserSession userSession, Session dataSession)
  {
    ReleaseVersion rv = ReleaseVersionLogic.getReleaseVersion(readInt(PARAM_RELEASE_ID, req), dataSession);
    releaseNewVersionThread = new ReleaseNewVersionThread(rv, userSession.getUser());
    releaseNewVersionThread.start();
  }

  public void printUserActivity(UserSession userSession, PrintWriter out)
  {
    List<UserActivity> userSessionActivityList = new ArrayList<UserActivity>(getUserActivitySet());
    Collections.sort(userSessionActivityList);
    out.println("<table width=\"100%\">");
    out.println("  <caption>Last User Activity</caption>");
    out.println("  <tr>");
    out.println("    <th>User</th>");
    out.println("    <th>Date/Time</th>");
    out.println("  </tr>");
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a z");
    for (UserActivity userActivity : userSessionActivityList)
    {
      Date activityDate = new Date(userActivity.getLastWebRequestTimeMillis());
      out.println("  <tr>");
      out.println("    <td>" + userActivity.getUserName() + "</td>");
      out.println("    <td>" + sdf.format(activityDate) + "</td>");
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  public void printReleaseMaintenance(PrintWriter out, Session dataSession)
  {
    out.println("  <form action=\"admin\" method=\"POST\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Release Maintenance</caption>");
    out.println("    <tr>");
    out.println("      <td>Release</td>");
    out.println("      <td>");
    out.println("        <select name=\"" + PARAM_RELEASE_ID + "\">");
    for (ReleaseVersion rv : ReleaseVersionLogic.getReleaseVersions(ReleaseStatus.PROPOSED, dataSession))
    {
      out.println("          <option value=\"" + rv.getReleaseId() + "\">" + rv.getVersion() + "</a>");
    }
    out.println("        </select>");
    out.println("      </td>");
    out.println("      <td>");
    if (updateIssueCountThread == null || updateIssueCountThread.isComplete())
    {
      out.println("        <input type=\"submit\" name=\"action\" value=\"" + ACTION_UPDATE_ISSUE_COUNTS + "\"/><br/>");
    }
    if (releaseNewVersionThread == null || releaseNewVersionThread.isComplete())
    {
      out.println("        <input type=\"submit\" name=\"action\" value=\"" + ACTION_RELEASE_VERSION + "\"/>");
    }
    if (deleteProposedVersionThread == null || deleteProposedVersionThread.isComplete())
    {
      out.println("        <input type=\"submit\" name=\"action\" value=\"" + ACTION_DELETE_VERSION + "\"/>");
    }
    out.println("      </td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("  </form>");
    out.println("  <br/>");
    if (updateIssueCountThread != null)
    {
      updateIssueCountThread.printOutput(out);
    }
    if (releaseNewVersionThread != null)
    {
      releaseNewVersionThread.printOutput(out);
    }
    if (deleteProposedVersionThread != null)
    {
      deleteProposedVersionThread.printOutput(out);
    }
  }

  public void printUpdateCdcTables(PrintWriter out, Session dataSession)
  {
    out.println("  <form action=\"admin\" method=\"POST\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Update CDC Tables</caption>");
    out.println("    <tr>");
    out.println("      <td>Table</td>");
    out.println("      <td>");
    out.println("        <select name=\"" + PARAM_CODE_TABLE_INSTANCE_ID + "\">");
    ReleaseVersion releaseVersion = ReleaseVersionLogic.getProposedReleaseVersion(dataSession);
    List<CodeTableInstance> codeTableInstanceList = CodeTableLogic.getCodeTables(releaseVersion, dataSession);
    for (CodeTableInstance cti : codeTableInstanceList)
    {
      out.println("          <option value=\"" + cti.getTableInstanceId() + "\">" + cti.getTableLabel() + "</a>");
    }
    out.println("        </select>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <td>Text from CDC</td>");
    out.println("      <td><textarea name=\"" + PARAM_CDC_TEXT + "\" cols=\"20\" rows=\"3\"></textarea></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <td colspan=\"2\">");
    out.println("        <input type=\"submit\" name=\"action\" value=\"" + ACTION_LOAD_CODES + "\"/>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("  </form>");
    out.println("  <br/>");
  }

  public void printSystemWideMessageTable(PrintWriter out, Session dataSession)
  {
    out.println("  <form action=\"admin\" method=\"POST\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>System Wide Message</caption>");
    out.println("    <tr>");
    out.println("      <td>Message</td>");
    out.println("      <td>");
    out.println("        <input type=\"text\" name=\"" + PARAM_SYSTEM_WIDE_MESSAGE + "\" value=\"" + getSystemWideMessage() + "\" size=\"30\"/>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <td colspan=\"2\">");
    out.println("        <input type=\"submit\" name=\"action\" value=\"" + ACTION_SET_SYSTEM_WIDE_MESSAGE + "\"/>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("  </form>");
    out.println("  <br/>");
  }

}
