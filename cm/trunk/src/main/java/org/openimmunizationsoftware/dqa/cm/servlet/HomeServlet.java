package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openimmunizationsoftware.dqa.cm.logic.AllowedValueLogic;
import org.openimmunizationsoftware.dqa.cm.logic.AttributeAssignedLogic;
import org.openimmunizationsoftware.dqa.cm.logic.AttributeCommentLogic;
import org.openimmunizationsoftware.dqa.cm.logic.AttributeInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeMasterLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AllowedValue;
import org.openimmunizationsoftware.dqa.cm.model.AttributeAssigned;
import org.openimmunizationsoftware.dqa.cm.model.AttributeComment;
import org.openimmunizationsoftware.dqa.cm.model.AttributeFormat;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.PositionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

public class HomeServlet extends BaseServlet
{

  private enum Format {
    SHORT, FULL
  }

  public HomeServlet() {
    super("Home");
  }

  protected static final String ACTION_LOGIN = "Login";
  protected static final String PARAM_USER_NAME = "userName";
  protected static final String PARAM_PASSWORD = "password";

  protected static final String ACTION_LOGOUT = "Logout";

  protected static final String ACTION_SEARCH = "Search";
  protected static final String PARAM_CODE_VALUE = "codeValue";
  protected static final String PARAM_CODE_LABEL = "codeLabel";

  protected static final String PARAM_ACTION = "action";
  protected static final String PARAM_RELEASE_ID = "releaseId";
  protected static final String PARAM_CODE_INSTANCE_ID = "codeInstanceId";
  protected static final String PARAM_CONTEXT_CODE_INSTANCE_ID = "contextCodeInstanceId";
  protected static final String PARAM_VIEW = "view";

  protected static final String VIEW_DEFAULT = "default";
  protected static final String VIEW_SEARCH = "search";
  protected static final String VIEW_TABLE = "table";
  protected static final String VIEW_CODE = "code";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    setup(req, resp);
    try
    {
      String action = req.getParameter(PARAM_ACTION);
      String view = req.getParameter(PARAM_VIEW);
      if (view == null)
      {
        view = VIEW_DEFAULT;
      }
      if (action != null)
      {
        if (action.equals(ACTION_LOGIN))
        {
          login(req.getParameter(PARAM_USER_NAME), req.getParameter(PARAM_PASSWORD));
        } else if (action.equals(ACTION_LOGOUT))
        {
          logout();
        }
      }
      if (req.getParameter(PARAM_RELEASE_ID) != null)
      {
        int releaseVersionId = Integer.parseInt(req.getParameter(PARAM_RELEASE_ID));
        if (userSession.getReleaseVersion().getReleaseId() != releaseVersionId)
        {
          userSession.setSearchResultsWithCodeInstanceList(null);
          userSession.setCodeTableInstance(null);
        }
        userSession.setReleaseVersion(ReleaseVersionLogic.getReleaseVersion(releaseVersionId, dataSession));
      }
      if (req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID) != null)
      {
        int tableInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID));
        userSession.setCodeTableInstance(CodeTableInstanceLogic.getCodeTableInstance(tableInstanceId, dataSession));
      }
      createHeader();

      if (view.equals(VIEW_DEFAULT))
      {
        out.println("<div class=\"leftColumn\">");
        out.println("<div class=\"topBox\">");
        printLogin(userSession.getUser());
        out.println("</div>");
        printReleaseVersions(userSession.getReleaseVersion(), Format.FULL);
        out.println("</div>");

        out.println("<div class=\"centerColumn\">");
        printTopBoxReleaseVersion(userSession.getReleaseVersion());

        printCodeTables(null, userSession.getReleaseVersion(), "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&");
        out.println("</div>");

        out.println("<div class=\"rightColumn\">");
        printTopBoxSearch(req, action);
        if (userSession.getSearchResultsWithCodeInstanceList() != null)
        {
          printSearchResults(null, Format.FULL);
        }
        out.println("</div>");

      } else if (view.equals(VIEW_TABLE))
      {
        CodeTableInstance codeTableInstance = null;
        CodeInstance codeInstance = null;
        if (req.getParameter(PARAM_CODE_INSTANCE_ID) != null)
        {
          int codeInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_INSTANCE_ID));
          codeInstance = CodeInstanceLogic.getCodeInstance(codeInstanceId, dataSession);
          codeTableInstance = codeInstance.getTableInstance();
        } else
        {
          codeTableInstance = userSession.getCodeTableInstance();
        }

        out.println("<div class=\"leftColumn\">");
        printTopBoxReleaseVersion(userSession.getReleaseVersion());
        printCodeTables(codeTableInstance, userSession.getReleaseVersion(), "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&");
        out.println("<p>&nbsp;</p>");
        out.println("</div>");

        if (codeTableInstance.getTable().getContextTable() == null)
        {
          out.println("<div class=\"centerColumn\">");
          printTopBoxForTable(codeTableInstance);
          printCodeValues(codeTableInstance, codeInstance, Format.FULL, VIEW_TABLE);
          out.println("</div>");

          if (codeInstance != null)
          {
            out.println("<div class=\"rightColumn\">");
            printTopBoxForCode(codeInstance);
            printCodeAttributes(codeInstance, null, "home?" + PARAM_VIEW + "=" + VIEW_CODE + "&");
            out.println("</div>");
          }
        } else
        {
          CodeInstance contextCodeInstance = null;
          if (req.getParameter(PARAM_CONTEXT_CODE_INSTANCE_ID) != null)
          {
            int contextCodeInstanceId = readInt(PARAM_CONTEXT_CODE_INSTANCE_ID, req);
            contextCodeInstance = CodeInstanceLogic.getCodeInstance(contextCodeInstanceId, dataSession);
          }

          out.println("<div class=\"centerColumn\">");
          out.println("<div class=\"topBox\">");
          out.println("</div>");

          printContextTableValues(codeTableInstance, codeTableInstance.getTable().getContextTable(), contextCodeInstance);
          out.println("</div>");

          if (contextCodeInstance != null)
          {
            out.println("<div class=\"rightColumn\">");

            printTopBoxForTable(codeTableInstance);

            printContextCodeValues(codeTableInstance, contextCodeInstance, codeInstance, Format.FULL, VIEW_CODE);
            out.println("</div>");
          }
        }
      } else if (view.equals(VIEW_SEARCH))
      {
        CodeInstance codeInstance = null;
        AttributeInstance attributeInstance = null;
        if (req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID) != null)
        {
          int attributeInstanceId = Integer.parseInt(req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID));
          attributeInstance = AttributeInstanceLogic.getAttributeInstance(attributeInstanceId, dataSession);
          codeInstance = attributeInstance.getCodeInstance();
        } else if (req.getParameter(PARAM_CODE_INSTANCE_ID) != null)
        {
          int codeInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_INSTANCE_ID));
          codeInstance = CodeInstanceLogic.getCodeInstance(codeInstanceId, dataSession);
        }

        out.println("<div class=\"leftColumn\">");
        printTopBoxSearch(req, action);

        if (codeInstance != null && userSession.getSearchResultsWithCodeInstanceList() != null)
        {
          printSearchResults(codeInstance, Format.SHORT);
        }

        out.println("</div>");

        if (codeInstance != null)
        {
          out.println("<div class=\"centerColumn\">");
          printTopBoxForCode(codeInstance);

          printCodeAttributes(codeInstance, attributeInstance, "home?" + PARAM_VIEW + "=" + VIEW_SEARCH + "&");
          out.println("</div>");
          if (attributeInstance != null)
          {
            out.println("<div class=\"rightColumn\">");
            printTopBoxForComments(attributeInstance);
            printComments(attributeInstance);
            out.println("</div>");
          }
        } else
        {
          if (userSession.getSearchResultsWithCodeInstanceList() != null)
          {
            out.println("<div class=\"centerColumn\">");
            printSearchResults(codeInstance, Format.FULL);
            out.println("</div>");
          }

        }

      } else if (view.equals(VIEW_CODE))
      {
        CodeInstance codeInstance = null;
        AttributeInstance attributeInstance = null;
        if (req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID) != null)
        {
          int attributeInstanceId = Integer.parseInt(req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID));
          attributeInstance = AttributeInstanceLogic.getAttributeInstance(attributeInstanceId, dataSession);
          codeInstance = attributeInstance.getCodeInstance();
        } else if (req.getParameter(PARAM_CODE_INSTANCE_ID) != null)
        {
          int codeInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_INSTANCE_ID));
          codeInstance = CodeInstanceLogic.getCodeInstance(codeInstanceId, dataSession);
        }
        CodeTableInstance codeTableInstance = codeInstance.getTableInstance();
        CodeInstanceLogic.populateAttributeValueList(codeInstance, dataSession);
        CodeInstance contextCodeInstance = null;
        if (req.getParameter(PARAM_CONTEXT_CODE_INSTANCE_ID) != null)
        {
          int contextCodeInstanceId = readInt(PARAM_CONTEXT_CODE_INSTANCE_ID, req);
          contextCodeInstance = CodeInstanceLogic.getCodeInstance(contextCodeInstanceId, dataSession);
        } else if (codeInstance.getContext() != null)
        {
          contextCodeInstance = CodeInstanceLogic.getCodeInstance(codeInstance.getContext(), userSession.getReleaseVersion(), dataSession);
        }

        out.println("<div class=\"leftColumn\">");
        if (contextCodeInstance == null)
        {
          printTopBoxForTable(codeTableInstance);
          printCodeValues(codeTableInstance, codeInstance, Format.SHORT, VIEW_CODE);
        } else
        {
          printTopBoxForTable(codeTableInstance);

          printContextCodeValues(codeTableInstance, contextCodeInstance, codeInstance, Format.SHORT, VIEW_CODE);
        }
        out.println("</div>");
        out.println("<div class=\"centerColumn\">");
        printTopBoxForCode(codeInstance);
        printCodeAttributes(codeInstance, attributeInstance, "home?" + PARAM_VIEW + "=" + VIEW_CODE + "&");
        out.println("</div>");
        if (attributeInstance != null)
        {
          out.println("<div class=\"rightColumn\">");
          printTopBoxForComments(attributeInstance);
          printComments(attributeInstance);
          out.println("</div>");
        }
      }
    } catch (Exception e)
    {
      e.printStackTrace();
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }
    createFooter();
  }

  public void printTopBoxSearch(HttpServletRequest req, String action)
  {
    String codeValue = userSession.getSearchCodeValue();
    String codeLabel = userSession.getSearchCodeLabel();
    if (action != null)
    {
      if (action.equals(ACTION_SEARCH))
      {
        codeValue = req.getParameter(PARAM_CODE_VALUE);
        codeLabel = req.getParameter(PARAM_CODE_LABEL);
        userSession.setSearchCodeValue(codeValue);
        userSession.setSearchCodeLabel(codeLabel);
        if (!codeValue.equals("") || !codeLabel.equals(""))
        {
          List<CodeInstance> codeInstanceList = CodeInstanceLogic.search(userSession.getReleaseVersion(), codeValue, codeLabel, dataSession);
          userSession.setSearchResultsWithCodeInstanceList(codeInstanceList);
        }
      }
    }

    out.println("<div class=\"topBox\">");
    out.println("<form method=\"GET\" action=\"home\">");
    out.println("<table width=\"100%\">");
    out.println("  <caption>Search</caption>");
    out.println("  <tr>");
    out.println("    <th>Code</th>");
    out.println("    <th>Label</th>");
    out.println("  </tr>");
    out.println("  <tr>");
    out.println("    <td><input type=\"text\" name=\"" + PARAM_CODE_VALUE + "\" size=\"7\" value=\"" + codeValue + "\"/></td>");
    out.println("    <td><input type=\"text\" name=\"" + PARAM_CODE_LABEL + "\" size=\"30\" value=\"" + codeLabel + "\"/></td>");
    out.println("  </tr>");
    out.println("  <tr>");
    out.println("    <td colspan=\"2\" align=\"right\"><input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_SEARCH + "\"/></td>");
    out.println("  </tr>");
    out.println("</table>");
    out.println("<input type=\"hidden\" name=\"" + PARAM_VIEW + "\" value=\"" + VIEW_SEARCH + "\"/></td>");
    out.println("</form>");
    out.println("</div>");

  }

  public void printSearchResults(CodeInstance codeInstance, Format format)
  {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Search Results</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    if (format == Format.FULL)
    {
      out.println("    <th>Table</th>");
    }
    out.println("  </tr>");
    for (CodeInstance ci : userSession.getSearchResultsWithCodeInstanceList())
    {
      String link = "home?" + PARAM_VIEW + "=" + VIEW_SEARCH + "&" + PARAM_CODE_INSTANCE_ID + "=" + ci.getCodeInstanceId();
      String classString = "";
      if (codeInstance != null && codeInstance.equals(ci))
      {
        classString = "selected";
      }

      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + chop(ci.getCode().getCodeValue(), 12) + "</a></td>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getCodeLabel() + "</a></td>");
      if (format == Format.FULL)
      {
        out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getTableInstance().getTableLabel() + "</a></td>");
      }
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  private static String chop(String s, int length)
  {
    if (s.length() > (length - 3))
    {
      s = s.substring(0, (length - 3)) + "&hellip;";
    }
    return s;
  }

  public void printReleaseVersions(ReleaseVersion releaseVersion, Format format)
  {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Release Versions</caption>");
    out.println("  <tr>");
    out.println("    <th>Version</th>");
    if (format == Format.FULL)
    {
      out.println("    <th>Released</th>");
    }
    out.println("    <th>Status</th>");
    out.println("  </tr>");
    List<ReleaseVersion> releaseVersionList = ReleaseVersionLogic.getReleaseVersions(dataSession);
    for (ReleaseVersion rv : releaseVersionList)
    {
      String link = "home?" + PARAM_RELEASE_ID + "=" + rv.getReleaseId();
      String classString = "";
      if (releaseVersion != null && releaseVersion.equals(rv))
      {
        classString = "selected";
      }
      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + rv.getVersion() + "</a></td>");
      if (format == Format.FULL)
      {
        printDateCell(rv.getReleasedDate(), classString);
      }
      out.println("    <td class=\"" + classString + "\">" + rv.getReleaseStatus() + "</td>");
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  private void printLogin(User user)
  {
    if (user == null)
    {
      out.println("<form method=\"POST\" action=\"home\">");
      out.println("<table width=\"100%\">");
      out.println("  <caption>Login</caption>");
      out.println("  <tr>");
      out.println("    <th>User Name</th>");
      out.println("    <th>Password</th>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <td><input type=\"text\" name=\"" + PARAM_USER_NAME + "\" size=\"15\"/></td>");
      out.println("    <td><input type=\"password\" name=\"" + PARAM_PASSWORD + "\" size=\"15\"/></td>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <td colspan=\"2\" align=\"right\"><input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_LOGIN + "\"/></td>");
      out.println("  </tr>");
      out.println("</table>");
      out.println("</form>");
    } else
    {
      out.println("<form method=\"POST\" action=\"home\">");
      out.println("<table width=\"100%\">");
      out.println("  <caption>" + user.getUserName() + "</caption>");
      out.println("  <tr>");
      out.println("    <th>Email Address</th>");
      out.println("    <th>User Type</th>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <td>" + user.getEmailAddress() + "</td>");
      out.println("    <td>" + user.getUserType() + "</td>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <td colspan=\"2\" align=\"right\"><input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_LOGOUT + "\"/></td>");
      out.println("  </tr>");
      out.println("</table>");
      out.println("</form>");

    }
  }

  public void printDateCell(Date date, String classString)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    if (date == null)
    {
      out.println("    <td class=\"" + classString + "\"></td>");
    } else
    {
      out.println("    <td class=\"" + classString + "\">" + sdf.format(date) + "</td>");
    }
  }

  public void printComments(AttributeInstance attributeInstance)
  {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Comments</caption>");
    List<AttributeComment> attributeCommentList = AttributeCommentLogic.getAttributeCommentList(attributeInstance, dataSession);
    for (AttributeComment ac : attributeCommentList)
    {
      out.println("  <tr>");
      out.println("    <td>");
      out.println("      <div class=\"userCommentHeader\">" + ac.getUser().getUserName());
      if (ac.getPositionStatus() == PositionStatus.AGAINST)
      {
        out.println("does not agree:");
      } else if (ac.getPositionStatus() == PositionStatus.FOR)
      {
        out.println("agrees:");
      } else if (ac.getPositionStatus() == PositionStatus.NEUTRAL)
      {
        out.println("writes:");
      } else if (ac.getPositionStatus() == PositionStatus.PROBLEM)
      {
        out.println("sees problem:");
      } else if (ac.getPositionStatus() == PositionStatus.QUESTION)
      {
        out.println("has a question:");
      } else if (ac.getPositionStatus() == PositionStatus.RESEARCH)
      {
        out.println("indicates research is needed:");
      }
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      out.println("      <span class=\"userCommentDate\">" + sdf.format(ac.getEntryDate()) + "</span>");
      out.println("     </div>");
      out.println("      " + ac.getCommentText() + "");
      out.println("    </td>");
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  public void printTopBoxReleaseVersion(ReleaseVersion releaseVersion)
  {
    out.println("<div class=\"topBox\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Release " + releaseVersion.getVersion() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Started</th>");
    printDateCell(releaseVersion.getStartedDate(), "");
    out.println("    </tr>");
    if (releaseVersion.getReleasedDate() != null)
    {
      out.println("    <tr>");
      out.println("      <th>Released</th>");
      printDateCell(releaseVersion.getReleasedDate(), "");
      out.println("    </tr>");
    }
    if (releaseVersion.getRetiredDate() != null)
    {
      out.println("    <tr>");
      out.println("      <th>Retired</th>");
      printDateCell(releaseVersion.getRetiredDate(), "");
      out.println("    </tr>");
    }
    out.println("    <tr>");
    out.println("      <th>Status</th>");
    out.println("      <td>" + releaseVersion.getReleaseStatus() + "</td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("</div>");
  }

  public void printCodeAttributes(CodeInstance codeInstance, AttributeInstance attributeInstance, String view)
  {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Attributes</caption>");
    out.println("  <tr>");
    out.println("    <th>Attribute</th>");
    out.println("    <th>Value</th>");
    out.println("  </tr>");
    List<AttributeAssigned> attributeAssignedList = AttributeAssignedLogic.getAttributeAssignedList(codeInstance.getTableInstance().getTable(),
        dataSession);
    for (AttributeAssigned aa : attributeAssignedList)
    {
      AttributeType at = aa.getAttributeType();
      // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      List<AttributeInstance> attributeInstanceList = codeInstance.getAttributeTypeToValueMap().get(at);
      boolean showedOne = false;
      boolean acceptedOne = false;
      if (attributeInstanceList != null)
      {
        for (AttributeInstance ai : attributeInstanceList)
        {
          if (!userSession.canEdit() && ai.getAcceptStatus() == AcceptStatus.REJECTED)
          {
            continue;
          }
          String classString = "";
          if (attributeInstance != null && attributeInstance.equals(ai))
          {
            classString = "selected";
          }
          if (ai.getAcceptStatus() == AcceptStatus.REJECTED)
          {
            classString += " strike";
          }
          String link = view + PARAM_ATTRIBUTE_INSTANCE_ID + "=" + ai.getAttributeInstanceId();
          String issueCountLabel = "";
          if (userSession.canEdit() && (ai.getAcceptStatus() != AcceptStatus.REJECTED && ai.getAcceptStatus() != AcceptStatus.CONFIRMED))
          {
            if (ai.getAcceptStatus() != AcceptStatus.REJECTED && ai.getAcceptStatus() != AcceptStatus.CONFIRMED)
            {
              issueCountLabel = " <span class=\"issueCountLabel\">" + ai.getAcceptStatus() + "</span>";
            }
            if (acceptedOne && !aa.isAllowMultiple())
            {
              issueCountLabel += " <span class=\"issueCountLabel\">duplicate</span>";
            }
            if (ai.getAcceptStatus() != AcceptStatus.REJECTED)
            {
              acceptedOne = true;
            }
          }
          out.println("  <tr>");
          if (showedOne)
          {
            out.println("    <td class=\"" + classString + "\"></td>");
          } else
          {
            out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + at.getAttributeLabel() + "</a></td>");
          }
          /*
           * -- SELECT_TEXT "S" - "Select Text" -- INTEGER "I" - "Integer" --
           * CODE_MASTER "C" - "Code Master" -- CODE_TABLE "A" = "Code Table"
           */
          if (at.getAttributeFormat() == AttributeFormat.SELECT_TEXT)
          {
            AllowedValue allowedValue = AllowedValueLogic.getAllowedValue(at, getValueLabel(ai), dataSession);
            if (allowedValue != null)
            {
              out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + allowedValue.getDisplayText() + "</a>"
                  + issueCountLabel + "</td>");
            } else
            {
              out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + getValueLabel(ai) + "</a>" + issueCountLabel + "</td>");
            }
          } else if (at.getAttributeFormat() == AttributeFormat.CODE_MASTER)
          {
            CodeMaster codeMaster = CodeMasterLogic.getCodeMaster(ai.getValue().getAttributeValueInt(), dataSession);
            CodeInstance ci = null;
            if (codeMaster != null)
            {
              ci = CodeInstanceLogic.getCodeInstance(codeMaster, userSession.getReleaseVersion(), dataSession);
            }
            if (ci != null)
            {
              out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getCodeLabel() + "</a>" + issueCountLabel + "</td>");
            } else
            {
              out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + getValueLabel(ai) + "</a>" + issueCountLabel + "</td>");
            }
          } else if (at.getAttributeFormat() == AttributeFormat.CODE_TABLE)
          {
            // TODO
            out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + getValueLabel(ai) + "</a>" + issueCountLabel + "</td>");
          } else
          {
            out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + getValueLabel(ai) + "</a>" + issueCountLabel + "</td>");
          }
          out.println("  </tr>");
          showedOne = true;
        }
      }
      if (!showedOne)
      {
        out.println("  <tr>");
        out.println("    <td>" + at.getAttributeLabel() + "</td>");
        out.println("    <td></td>");
        out.println("  </tr>");
      }
    }

    out.println("</table>");
  }

  public void printTopBoxForComments(AttributeInstance attributeInstance)
  {
    out.println("<div class=\"topBox\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>" + attributeInstance.getValue().getAttributeType().getAttributeLabel() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Value</th>");
    out.println("      <td>" + getValueLabel(attributeInstance) + "</td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Accept Status</th>");
    out.println("      <td>" + attributeInstance.getAcceptStatus() + "</td>");
    out.println("    </tr>");
    if (isLoggedIn())
    {
      out.println("    <tr>");
      out.println("      <td colspan=\"2\">");
      out.println("        <form action=\"attribute\" method=\"GET\">");
      out.println("          <input type=\"hidden\" name=\"" + PARAM_ATTRIBUTE_INSTANCE_ID + "\" value=\""
          + attributeInstance.getAttributeInstanceId() + "\"/>");
      out.println("          <span class=\"formButtonFloat\"><input type=\"submit\" name=\"submit\" value=\"Edit\"/></span>");
      out.println("        </form></td>");
      out.println("    </tr>");
    }
    out.println("  </table>");
    out.println("</div>");
  }

  public String getValueLabel(AttributeInstance attributeInstance)
  {
    AttributeType at = attributeInstance.getValue().getAttributeType();
    if (at.getAttributeFormat() == AttributeFormat.CODE_MASTER)
    {
      String codeValue = attributeInstance.getValue().getAttributeValue();
      CodeInstance codeInstance = CodeInstanceLogic.getCodeInstance(at.getRefTable(), userSession.getReleaseVersion(), codeValue, dataSession);
      return codeValue + " - " + codeInstance.getCodeLabel();
    } else if (at.getAttributeFormat() == AttributeFormat.CODE_TABLE)
    {
      int tableId = 0;
      try
      {
        tableId = Integer.parseInt(attributeInstance.getValue().getAttributeValue());
      } catch (NumberFormatException nfe)
      {
        return attributeInstance.getValue().getAttributeValue();
      }
      CodeTableInstance codeTableInstance = CodeTableInstanceLogic.getCodeTableInstance(tableId, dataSession);
      return tableId + " - " + codeTableInstance.getTableLabel();
    } else if (at.getAttributeFormat() == AttributeFormat.DATE)
    {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.FREE_TEXT)
    {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.INTEGER)
    {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.LONG_TEXT)
    {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.SELECT_TEXT)
    {
      String savedValue = attributeInstance.getValue().getAttributeValue();
      AllowedValue allowedValue = AllowedValueLogic.getAllowedValue(at, savedValue, dataSession);
      if (allowedValue != null)
      {
        return allowedValue.getDisplayText();
      }
      return savedValue;
    } else
    {
      return attributeInstance.getValue().getAttributeValue();
    }
  }

  public void printTopBoxForTable(CodeTableInstance codeTableInstance)
  {
    out.println("<div class=\"topBox\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>" + codeTableInstance.getTableLabel() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Table Id</th>");
    out.println("      <td>" + codeTableInstance.getTable().getTableId() + "</td>");
    out.println("    </tr>");
    if (codeTableInstance.getTable().getContextTable() != null)
    {
      CodeTableInstance contextCodeTableInstance = CodeTableLogic.getCodeTableInstance(codeTableInstance.getTable().getContextTable(),
          userSession.getReleaseVersion(), dataSession);
      String link = "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&" + PARAM_CODE_TABLE_INSTANCE_ID + "="
          + contextCodeTableInstance.getTableInstanceId();
      out.println("    <tr>");
      out.println("      <th>Context Table</th>");
      out.println("      <td><a href=\"" + link + "\">" + contextCodeTableInstance.getTableLabel() + "</a></td>");
      out.println("    </tr>");
    }
    out.println("    <tr>");
    out.println("      <th>Inclusion Status</th>");
    out.println("      <td>" + codeTableInstance.getInclusionStatus() + "</td>");
    out.println("    </tr>");
    if (isAdmin())
    {
      out.println("    <tr>");
      out.println("      <td colspan=\"2\">");
      out.println("        <form action=\"adminTable\" method=\"GET\">");
      out.println("          <input type=\"hidden\" name=\"" + PARAM_CODE_TABLE_INSTANCE_ID + "\" value=\"" + codeTableInstance.getTableInstanceId()
          + "\"/>");
      out.println("          <span class=\"formButtonFloat\"><input type=\"submit\" name=\"submit\" value=\"Edit\"/></span>");
      out.println("        </form></td>");
      out.println("    </tr>");
    }
    out.println("  </table>");
    out.println("</div>");
  }

  public void printTopBoxForCode(CodeInstance codeInstance)
  {
    out.println("<div class=\"topBox\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>" + codeInstance.getCodeLabel() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Table</th>");
    String link = "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&" + PARAM_CODE_TABLE_INSTANCE_ID + "="
        + codeInstance.getTableInstance().getTableInstanceId();
    out.println("      <td><a href=\"" + link + "\">" + codeInstance.getTableInstance().getTableLabel() + "</a></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Code Value</th>");
    out.println("      <td>" + codeInstance.getCode().getCodeValue() + "</td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>DQAcm Code Id</th>");
    out.println("      <td>" + codeInstance.getCode().getCodeId() + "</td>");
    out.println("    </tr>");
    // if (codeInstance.getContext() != null)
    // {
    // CodeInstance contextCodeInstance =
    // CodeInstanceLogic.getCodeInstance(codeInstance.getContext(),
    // userSession.getReleaseVersion(), dataSession);
    // link = "home?" + PARAM_VIEW + "=" + VIEW_CODE + "&" +
    // PARAM_CODE_INSTANCE_ID + "=" + contextCodeInstance.getCodeInstanceId();
    // out.println("    <tr>");
    // out.println("      <th>Context Code</th>");
    // out.println("      <td><a href=\"" + link + "\">" +
    // contextCodeInstance.getCodeLabel() + "</a></td>");
    // out.println("    </tr>");
    // }
    // if (codeInstance.getIndicatesTable() != null)
    // {
    // CodeTableInstance indicatesCodeTableInstance =
    // CodeTableLogic.getCodeTableInstance(codeInstance.getIndicatesTable(),
    // userSession.getReleaseVersion(), dataSession);
    // link = "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&" +
    // PARAM_TABLE_INSTANCE_ID + "=" +
    // indicatesCodeTableInstance.getTableInstanceId();
    // out.println("    <tr>");
    // out.println("      <th>Indicates Table</th>");
    // out.println("      <td><a href=\"" + link + "\">" +
    // indicatesCodeTableInstance.getTableLabel() + "</a></td>");
    // out.println("    </tr>");
    // }
    out.println("    <tr>");
    out.println("      <th>Inclusion Status</th>");
    out.println("      <td>" + codeInstance.getInclusionStatus() + "</td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("</div>");
  }

  public void printContextTableValues(CodeTableInstance codeTableInstance, CodeTable contextTable, CodeInstance contextCodeInstance)
  {

    CodeTableInstance contextTableInstance = CodeTableLogic.getCodeTableInstance(contextTable, userSession.getReleaseVersion(), dataSession);
    List<CodeInstance> contextCodeInstanceList = CodeMasterLogic.getCodeValues(contextTableInstance, dataSession);

    out.println("<table width=\"100%\">");
    out.println("  <caption>Choose " + contextTableInstance.getTableLabel() + "</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    out.println("  </tr>");
    for (CodeInstance cci : contextCodeInstanceList)
    {
      String link = "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&" + PARAM_CODE_TABLE_INSTANCE_ID + "=" + codeTableInstance.getTableInstanceId() + "&"
          + PARAM_CONTEXT_CODE_INSTANCE_ID + "=" + cci.getCodeInstanceId();

      String classString = "";
      if (contextCodeInstance != null && contextCodeInstance.equals(cci))
      {
        classString = "selected";
      }
      String codeStatusString = "";
      if (!cci.getCodeStatus().equals(CodeInstance.CODE_STATUS_VALID))
      {
        codeStatusString = " <span class=\"codeStatusLabel\">" + cci.getCodeStatusLabel() + "</span>";
      }

      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + chop(cci.getCode().getCodeValue(), 12) + "</a></td>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + cci.getCodeLabel() + "</a>" + codeStatusString + "</td>");
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  public void printContextCodeValues(CodeTableInstance codeTableInstance, CodeInstance contextCodeInstance, CodeInstance codeInstance, Format format,
      String view)
  {

    List<CodeInstance> codeInstanceList = CodeMasterLogic.getCodeValues(codeTableInstance, contextCodeInstance, dataSession);
    out.println("<table width=\"100%\">");
    out.println("  <caption>Code Values for " + contextCodeInstance.getCodeLabel() + "</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    if (format == Format.FULL)
    {
      out.println("    <th>Use</th>");
    }
    out.println("  </tr>");
    for (CodeInstance ci : codeInstanceList)
    {
      String link = "home?" + PARAM_VIEW + "=" + view + "&" + PARAM_CODE_INSTANCE_ID + "=" + ci.getCodeInstanceId() + "&"
          + PARAM_CONTEXT_CODE_INSTANCE_ID + "=" + contextCodeInstance.getCodeInstanceId();

      String classString = "";
      if (codeInstance != null && codeInstance.equals(ci))
      {
        classString = "selected";
      }
      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + chop(ci.getCode().getCodeValue(), 12) + "</a></td>");
      String codeStatusString = "";
      if (!ci.getCodeStatus().equals(CodeInstance.CODE_STATUS_VALID))
      {
        codeStatusString = " <span class=\"codeStatusLabel\">" + ci.getCodeStatusLabel() + "</span>";
      }
      String issueCountLabel = "";
      if (userSession.canEdit() && ci.getIssueCount() > 0)
      {
        if (ci.getIssueCount() == 1)
        {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issue</span>";
        } else
        {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issues</span>";
        }
      }
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getCodeLabel() + codeStatusString + "</a>" + issueCountLabel
          + "</td>");
      if (format == Format.FULL)
      {
        if (ci.getUseValue() != null && !ci.getUseValue().trim().equals(ci.getCode().getCodeValue().trim()))
        {
          out.println("    <td class=\"" + classString + "\">" + ci.getUseValue() + "</td>");
        } else
        {
          out.println("    <td class=\"" + classString + "\"></td>");
        }
      }
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  public void printCodeValues(CodeTableInstance codeTableInstance, CodeInstance codeInstance, Format format, String view)
  {

    List<CodeInstance> codeInstanceList = CodeMasterLogic.getCodeValues(codeTableInstance, dataSession);
    out.println("<table width=\"100%\">");
    out.println("  <caption>Code Values</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    if (format == Format.FULL)
    {
      out.println("    <th>Use</th>");
    }
    out.println("  </tr>");
    for (CodeInstance ci : codeInstanceList)
    {
      String link = "home?" + PARAM_VIEW + "=" + view + "&" + PARAM_CODE_INSTANCE_ID + "=" + ci.getCodeInstanceId();
      String classString = "";
      if (codeInstance != null && codeInstance.equals(ci))
      {
        classString = "selected";
      }

      if (ci.isInclusionStatusRemove())
      {
        classString = classString + " strike";
      }
      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + chop(ci.getCode().getCodeValue(), 12) + "</a></td>");
      String codeStatusString = "";
      if (!ci.getCodeStatus().equals(CodeInstance.CODE_STATUS_VALID))
      {
        codeStatusString = " <span class=\"codeStatusLabel\">" + ci.getCodeStatusLabel() + codeStatusString + "</span>";
      }
      String issueCountLabel = "";
      if (userSession.canEdit() && ci.getIssueCount() > 0)
      {
        if (ci.getIssueCount() == 1)
        {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issue</span>";
        } else
        {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issues</span>";
        }
      }
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getCodeLabel() + codeStatusString + "</a>" + issueCountLabel
          + "</td>");
      if (format == Format.FULL)
      {
        if (ci.getUseValue() != null && !ci.getUseValue().trim().equals(ci.getCode().getCodeValue().trim()))
        {
          out.println("    <td class=\"" + classString + "\">" + ci.getUseValue() + "</td>");
        } else
        {
          out.println("    <td class=\"" + classString + "\"></td>");
        }
      }
      out.println("  </tr>");
    }
    out.println("</table>");
  }
}
