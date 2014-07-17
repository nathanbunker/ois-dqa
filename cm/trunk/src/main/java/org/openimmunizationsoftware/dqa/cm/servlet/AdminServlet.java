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

  public static final String ACTION_RELEASE_VERSION = "Release Version";
  public static final String ACTION_DELETE_VERSION = "Delete Version";
  public static final String ACTION_UPDATE_ISSUE_COUNTS = "Update Issue Counts";

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
      } 
    }

    createHeader();

    out.println("<div class=\"leftColumn\">");
    out.println("</div>");


    out.println("<div class=\"centerColumn\">");
    printReleaseMaintenance();
    out.println("</div>");

    out.println("<div class=\"rightColumn\">");
    out.println("<p><a href=\"setup\">Initial DQAcm Setup</a></p>");
    out.println("</div>");

    out.println("    <span class=\"cmVersion\">software version " + SoftwareVersion.VERSION + "</span>");
    createFooter();

  }

  public void printReleaseMaintenance()
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

}
