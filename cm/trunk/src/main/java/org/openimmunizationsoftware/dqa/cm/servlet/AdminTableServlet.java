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
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;

@SuppressWarnings("serial")
public class AdminTableServlet extends BaseServlet
{
  public AdminTableServlet() {
    super("Admin Table");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  public static final String PARAM_ACTION = "action";
  public static final String PARAM_TABLE_LABEL = "tableLabel";
  public static final String PARAM_ENFORCE_UNIQUE = "enforceUnique";

  public static final String PARAM_RELEASE_ID = "releaseId";
  public static final String ACTION_CHANGE_VERSION = "Change Version";
  public static final String ACTION_PROPOSE_INCUDE = "Propose Include";
  public static final String ACTION_PROPOSE_REMOVE = "Propose Remove";
  public static final String ACTION_REMOVE = "Remove";
  public static final String ACTION_INCLUDE = "Include";
  public static final String ACTION_UPDATE = "Update";

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
    try
    {
      CodeTableInstance codeTableInstance = null;
      if (req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID) != null)
      {
        int codeTableInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID));
        codeTableInstance = CodeTableInstanceLogic.getCodeTableInstance(codeTableInstanceId, dataSession);
      }
      String action = req.getParameter(PARAM_ACTION);
      if (action != null)
      {
        if (action.equals(ACTION_CHANGE_VERSION))
        {
          int releaseId = readInt(PARAM_RELEASE_ID, req);
          userSession.setReleaseVersion(ReleaseVersionLogic.getReleaseVersion(releaseId, dataSession));
        } else if (action.equals(ACTION_UPDATE))
        {
          if (req.getParameter(PARAM_TABLE_LABEL).equals(""))
          {
            userSession.setMessageError("Table label must be specified");
          } else
          {
            codeTableInstance.setTableLabel(req.getParameter(PARAM_TABLE_LABEL));
            codeTableInstance.setEnforceUniqe(req.getParameter(PARAM_ENFORCE_UNIQUE) != null);
            CodeTableInstanceLogic.updateCodeTableInstance(codeTableInstance, dataSession);
          }
        } else if (action.equals(ACTION_INCLUDE))
        {
          codeTableInstance.setInclusionStatus(InclusionStatus.INCLUDE);
          CodeTableInstanceLogic.updateCodeTableInstance(codeTableInstance, dataSession);
        } else if (action.equals(ACTION_REMOVE))
        {
          codeTableInstance.setInclusionStatus(InclusionStatus.REMOVE);
          CodeTableInstanceLogic.updateCodeTableInstance(codeTableInstance, dataSession);
        } else if (action.equals(ACTION_PROPOSE_INCUDE))
        {
          codeTableInstance.setInclusionStatus(InclusionStatus.PROPOSED_INCLUDE);
          CodeTableInstanceLogic.updateCodeTableInstance(codeTableInstance, dataSession);
        } else if (action.equals(ACTION_PROPOSE_REMOVE))
        {
          codeTableInstance.setInclusionStatus(InclusionStatus.PROPOSED_REMOVE);
          CodeTableInstanceLogic.updateCodeTableInstance(codeTableInstance, dataSession);
        }
      }

      createHeader(webSession);

      out.println("<div class=\"leftColumn\">");

      List<ReleaseVersion> releaseVersionList = ReleaseVersionLogic.getReleaseVersions(ReleaseStatus.PROPOSED, dataSession);
      ReleaseVersion releaseVersion = userSession.getReleaseVersion();
      if (releaseVersion.getReleaseStatus() != ReleaseStatus.PROPOSED)
      {
        releaseVersion = releaseVersionList.get(0);
      }

      out.println("<div class=\"topBox\">");
      printReleaseVersionForm(dataSession, out, releaseVersion);
      out.println("</div>");

      printCodeTables(codeTableInstance, releaseVersion, "adminTable?", webSession);
      out.println("</div>");

      out.println("<div class=\"centerColumn\">");
      if (codeTableInstance != null)
      {
        printTopBoxForTable(codeTableInstance, userSession);
      }
      out.println("</div>");

      out.println("<div class=\"rightColumn\">");
      out.println("</div>");

      out.println("    <span class=\"cmVersion\">software version " + SoftwareVersion.VERSION + "</span>");
    } catch (Exception e)
    {
      handleError(e, webSession);
    } finally
    {
      createFooter(webSession);
    }
  }

  public void printReleaseVersionForm(Session dataSession, PrintWriter out, ReleaseVersion releaseVersion)
  {
    out.println("  <form action=\"adminTable\" method=\"POST\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Release Version</caption>");
    out.println("    <tr>");
    out.println("      <td>");
    out.println("        <select name=\"" + PARAM_RELEASE_ID + "\">");
    for (ReleaseVersion rv : ReleaseVersionLogic.getReleaseVersions(ReleaseStatus.PROPOSED, dataSession))
    {
      if (releaseVersion.equals(rv))
      {
        out.println("          <option value=\"" + rv.getReleaseId() + "\" selected=\"true\">" + rv.getVersion() + "</a>");
      } else
      {
        out.println("          <option value=\"" + rv.getReleaseId() + "\">" + rv.getVersion() + "</a>");
      }
    }
    out.println("        </select>");
    out.println("      </td>");
    out.println("      <td>");
    out.println("        <input type=\"submit\" name=\"action\" value=\"" + ACTION_CHANGE_VERSION + "\"/><br/>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("  </form>");
  }

  public void printTopBoxForTable(CodeTableInstance codeTableInstance, UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
    Session dataSession = userSession.getDataSession();
    out.println("<div class=\"topBox\">");
    out.println("  <form action=\"adminTable\" method=\"POST\">");
    out.println("  <input type=\"hidden\" name=\"" + PARAM_CODE_TABLE_INSTANCE_ID + "\" value=\"" + codeTableInstance.getTableInstanceId() + "\"/>");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>" + codeTableInstance.getTableLabel() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Table Id</th>");
    out.println("      <td>" + codeTableInstance.getTable().getTableId() + "</td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Table Label</th>");
    out.println("      <td>");
    out.println("        <input type=\"text\" size=\"30\" name=\"" + PARAM_TABLE_LABEL + "\" value=\"" + codeTableInstance.getTableLabel() + "\"/>");
    out.println("      </td>");
    out.println("    </tr>");
    if (codeTableInstance.getTable().getContextTable() != null)
    {
      CodeTableInstance contextCodeTableInstance = CodeTableLogic.getCodeTableInstance(codeTableInstance.getTable().getContextTable(),
          userSession.getReleaseVersion(), dataSession);
      out.println("    <tr>");
      out.println("      <th>Context Table</th>");
      out.println("      <td>" + contextCodeTableInstance.getTableLabel() + "</td>");
      out.println("    </tr>");
    }
    out.println("    <tr>");
    out.println("      <th>Enforce Unique</th>");
    out.println("      <td>");
    if (codeTableInstance.isEnforceUniqe())
    {
      out.println("        <input type=\"checkbox\" name=\"" + PARAM_ENFORCE_UNIQUE + "\" value=\"true\" checked=\"true\">");
    } else
    {
      out.println("        <input type=\"checkbox\" name=\"" + PARAM_ENFORCE_UNIQUE + "\" value=\"true\">");
    }
    out.println("        <span class=\"formButtonFloat\"><input type=\"submit\" name=\"action\" value=\"" + ACTION_UPDATE + "\"/></span>");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Inclusion Status</th>");
    out.println("      <td>" + codeTableInstance.getInclusionStatus());
    if (codeTableInstance.getInclusionStatus() == InclusionStatus.INCLUDE)
    {
      out.println("        <span class=\"formButtonFloat\"><input type=\"submit\" name=\"action\" value=\"" + ACTION_PROPOSE_REMOVE + "\"/></span>");
    } else if (codeTableInstance.getInclusionStatus() == InclusionStatus.REMOVE)
    {
      out.println("        <span class=\"formButtonFloat\"><input type=\"submit\" name=\"action\" value=\"" + ACTION_PROPOSE_INCUDE + "\"/></span>");
    } else
    {
      out.println("        <span class=\"formButtonFloat\">");
      out.println("          <input type=\"submit\" name=\"action\" value=\"" + ACTION_INCLUDE + "\"/>");
      out.println("          <input type=\"submit\" name=\"action\" value=\"" + ACTION_REMOVE + "\"/>");
      out.println("        </span>");
    }
    out.println("      </td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("  </form>");
    out.println("</div>");
  }

}
