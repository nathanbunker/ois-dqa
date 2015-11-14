package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.logic.LoadCdcDataLogic.LoadResult;
import org.openimmunizationsoftware.dqa.cm.logic.thread.DeleteProposedVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.ReleaseNewVersionThread;
import org.openimmunizationsoftware.dqa.cm.logic.thread.UpdateIssueCountThread;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileManager;

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

  public static final String ACTION_LOAD_CODES = "Load Codes";
  public static final String ACTION_RELEASE_VERSION = "Release Version";
  public static final String ACTION_DELETE_VERSION = "Delete Version";
  public static final String ACTION_UPDATE_ISSUE_COUNTS = "Update Issue Counts";
  public static final String ACTION_RECTIFY_PROFILE_FIELDS = "Rectify Profile Fields";

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
    String action = req.getParameter(PARAM_ACTION);
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
      } else if (action.equals(ACTION_LOAD_CODES))
      {
        CodeTableInstance codeTableInstance = null;
        if (!req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID).equals(""))
        {
          int codeTableInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID));
          codeTableInstance = CodeTableInstanceLogic.getCodeTableInstance(codeTableInstanceId, dataSession);
          String data = req.getParameter(PARAM_CDC_TEXT);
          loadResultList = LoadCdcDataLogic.loadCdcData(codeTableInstance, userSession.getUser(), data, dataSession);
        }
      } else if (action.equals(ACTION_RECTIFY_PROFILE_FIELDS))
      {
        ProfileManager.rectifyProfileFields(dataSession);
      }
    }

    createHeader(webSession);

    out.println("<div class=\"leftColumn\">");
    out.println("</div>");

    out.println("<div class=\"centerColumn\">");
    printReleaseMaintenance(out, dataSession);

    printUpdateCdcTables(out, dataSession);

    out.println("</div>");

    out.println("<div class=\"rightColumn\">");
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
    // out.println("<p><a href=\"setup\">Initial DQAcm Setup</a></p>");
    out.println("<p><a href=\"admin?action=" + ACTION_RECTIFY_PROFILE_FIELDS + "\">Rectify Profile Field</a></p>");
    out.println("</div>");

    out.println("    <span class=\"cmVersion\">software version " + SoftwareVersion.VERSION + "</span>");
    createFooter(webSession);

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
}
