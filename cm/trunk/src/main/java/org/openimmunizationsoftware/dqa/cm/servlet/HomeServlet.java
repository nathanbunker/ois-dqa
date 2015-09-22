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
import org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic;
import org.openimmunizationsoftware.dqa.cm.logic.AttributeValueLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeMasterLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AllowedValue;
import org.openimmunizationsoftware.dqa.cm.model.ApplicationUser;
import org.openimmunizationsoftware.dqa.cm.model.AttributeAssigned;
import org.openimmunizationsoftware.dqa.cm.model.AttributeComment;
import org.openimmunizationsoftware.dqa.cm.model.AttributeFormat;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;
import org.openimmunizationsoftware.dqa.cm.model.AttributeValue;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.PositionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class HomeServlet extends BaseServlet {

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

  protected static final String ACTION_SELECT_APPLICATION = "Select Application";
  protected static final String PARAM_APPLICATION_ID = "applicationId";

  protected static final String ACTION_SEARCH = "Search";
  protected static final String PARAM_CODE_VALUE = "codeValue";
  protected static final String PARAM_CODE_LABEL = "codeLabel";

  protected static final String PARAM_ACTION = "action";
  protected static final String PARAM_RELEASE_ID = "releaseId";
  protected static final String PARAM_CODE_INSTANCE_ID = "codeInstanceId";
  protected static final String PARAM_CONTEXT_CODE_INSTANCE_ID = "contextCodeInstanceId";
  protected static final String PARAM_VIEW = "view";

  protected static final String ACTION_ADD_VALUE = "Add Value";
  protected static final String PARAM_USE_VALUE = "useValue";
  protected static final String PARAM_CODE_STATUS = "codeStatus";
  protected static final String PARAM_HL7_CODE_TABLE = "hl7CodeTable";
  protected static final String PARAM_COMMENT_TEXT = "commentText";

  protected static final String VIEW_DEFAULT = "default";
  protected static final String VIEW_SEARCH = "search";
  protected static final String VIEW_TABLE = "table";
  protected static final String VIEW_CODE = "code";

  private String paramCodeValue = null;
  private String paramCodeLabel = null;
  private String paramUseValue = null;
  private String paramCodeStatus = null;
  private String paramHl7CodeTable = null;
  private String paramCommentText = null;
  private CodeInstance codeInstance = null;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    setup(req, resp);
    try {
      String action = req.getParameter(PARAM_ACTION);
      String view = req.getParameter(PARAM_VIEW);
      if (req.getParameter(PARAM_RELEASE_ID) != null) {
        int releaseVersionId = Integer.parseInt(req.getParameter(PARAM_RELEASE_ID));
        if (userSession.getReleaseVersion().getReleaseId() != releaseVersionId) {
          userSession.setSearchResultsWithCodeInstanceList(null);
          userSession.setCodeTableInstance(null);
        }
        userSession.setReleaseVersion(ReleaseVersionLogic.getReleaseVersion(releaseVersionId, dataSession));
      }
      if (req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID) != null) {
        int tableInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_TABLE_INSTANCE_ID));
        userSession.setCodeTableInstance(CodeTableInstanceLogic.getCodeTableInstance(tableInstanceId, dataSession));
      }
      readParams(req);
      if (view == null) {
        view = VIEW_DEFAULT;
      }
      if (action != null) {
        if (action.equals(ACTION_LOGIN)) {
          login(req.getParameter(PARAM_USER_NAME), req.getParameter(PARAM_PASSWORD));
        } else if (action.equals(ACTION_LOGOUT)) {
          logout();
        } else if (action.equals(ACTION_SELECT_APPLICATION)) {
          userSession.getUser().setApplicationUser(null);
          int applicationId = Integer.parseInt(req.getParameter(PARAM_APPLICATION_ID));
          for (ApplicationUser applicationUser : userSession.getUser().getApplicationUserList()) {
            if (applicationUser.getApplication().getApplicationId() == applicationId) {
              userSession.getUser().setApplicationUser(applicationUser);
              break;
            }
          }
        } else if (action.equals(ACTION_ADD_VALUE)) {
          if (paramCodeValue.equals("")) {
            messageError = "Code value is required, please specify the code you are documenting";
          } else if (paramCodeLabel.equals("")) {
            messageError = "Code label is required, please specify a human readable description";
          } else if (paramCodeStatus.equals("")) {
            messageError = "Code status is required, please select a code status";
          } else if (paramCommentText.equals("")) {
            messageError = "Comment is required, please indicate the reason for this code being added";
          } else {
            boolean notUnique = false;
            CodeTableInstance codeTableInstance = userSession.getCodeTableInstance();
            CodeTable codeTable = codeTableInstance.getTable();
            ReleaseVersion releaseVersion = userSession.getReleaseVersion();
            CodeInstance codeInstanceNew = null;
            CodeMaster codeMaster = null;
            if (codeTableInstance.isEnforceUniqe()) {
              codeMaster = CodeMasterLogic.getCodeMaster(codeTable, paramCodeValue, dataSession);
            }
            if (codeMaster == null) {
              codeMaster = new CodeMaster();
              codeMaster.setTable(codeTable);
              codeMaster.setCodeValue(paramCodeValue);
              CodeMasterLogic.saveCodeMaster(codeMaster, dataSession);
            }
            codeInstanceNew = CodeInstanceLogic.getCodeInstance(codeMaster, releaseVersion, dataSession);
            CodeMaster contextCodeMaster = null;
            if (paramUseValue.equals("")) {
              paramUseValue = paramCodeValue;
            }
            if (codeInstanceNew == null) {
              codeInstanceNew = new CodeInstance();
              codeInstanceNew.setCode(codeMaster);
              if (req.getParameter(PARAM_CONTEXT_CODE_INSTANCE_ID) != null) {
                int contextCodeInstanceId = readInt(PARAM_CONTEXT_CODE_INSTANCE_ID, req);
                contextCodeMaster = CodeInstanceLogic.getCodeInstance(contextCodeInstanceId, dataSession).getCode();
                codeInstanceNew.setContext(contextCodeMaster);
              }
              codeInstanceNew.setCodeLabel(paramCodeLabel);
              codeInstanceNew.setUseValue(paramUseValue);
              codeInstanceNew.setCodeStatus(paramCodeStatus);
              codeInstanceNew.setHl7CodeTable(paramHl7CodeTable);
              codeInstanceNew.setInclusionStatus(InclusionStatus.INCLUDE);
              codeInstanceNew.setTableInstance(codeTableInstance);
              CodeInstanceLogic.saveCodeInstance(codeInstanceNew, dataSession);
            } else {
              notUnique = true;
            }
            codeInstance = codeInstanceNew;
            CodeInstanceLogic.populateAttributeValueList(codeInstanceNew, dataSession);

            if (contextCodeMaster != null) {
              addAttributeComment("" + contextCodeMaster.getCodeId(), AttributeTypeLogic.AT_CONTEXT_CODE, "", codeMaster);
            }
            addAttributeComment(paramCodeLabel, AttributeTypeLogic.AT_CODE_LABEL, paramCommentText, codeMaster);
            addAttributeComment(paramUseValue, AttributeTypeLogic.AT_USE_VALUE, paramUseValue.equals(paramCodeValue) ? "Same as code value"
                : "Different than code value, value is being mapped", codeMaster);
            addAttributeComment(paramCodeStatus, AttributeTypeLogic.AT_CODE_STATUS, "Initially defined value", codeMaster);
            if (!paramHl7CodeTable.equals("")) {
              addAttributeComment(paramHl7CodeTable, AttributeTypeLogic.AT_HL7_CODE_TABLE, "Initially defined value", codeMaster);
            }
            addAttributeComment(InclusionStatus.INCLUDE.getId(), AttributeTypeLogic.AT_INCLUSION_STATUS, paramCommentText, codeMaster);

            if (notUnique) {
              messageConfirmation = "Code value is already defined, code value has been updated";
            } else {
              messageConfirmation = "Code value has been defined";
            }

            paramCodeValue = "";
            paramUseValue = "";
            paramCodeLabel = "";
            paramCodeStatus = "";
            paramHl7CodeTable = "";
            paramCommentText = "";
          }
        }
      }

      createHeader();

      if (userSession.getUser() != null && userSession.getUser().getApplicationUser() == null) {
        out.println("<div class=\"leftColumn\">");
        out.println("<div class=\"topBox\">");
        printLogin(userSession.getUser());
        out.println("</div>");
        out.println("</div>");
      } else if (view.equals(VIEW_DEFAULT)) {
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
        if (userSession.getSearchResultsWithCodeInstanceList() != null) {
          printSearchResults(null, Format.FULL);
        }
        out.println("</div>");

      } else if (view.equals(VIEW_TABLE)) {
        CodeTableInstance codeTableInstance = null;
        if (codeInstance != null) {
          codeTableInstance = codeInstance.getTableInstance();
        } else {
          codeTableInstance = userSession.getCodeTableInstance();
        }

        out.println("<div class=\"leftColumn\">");
        printTopBoxReleaseVersion(userSession.getReleaseVersion());
        printCodeTables(codeTableInstance, userSession.getReleaseVersion(), "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&");
        out.println("<p>&nbsp;</p>");
        out.println("</div>");

        if (codeTableInstance.getTable().getContextTable() == null) {
          out.println("<div class=\"centerColumn\">");
          printTopBoxForTable(codeTableInstance);
          printCodeValues(codeTableInstance, codeInstance, Format.FULL, VIEW_TABLE);
          out.println("<br/>");
          printCodeValueAddForm(codeTableInstance, null, VIEW_TABLE);
          out.println("</div>");

          if (codeInstance != null) {
            out.println("<div class=\"rightColumn\">");
            printTopBoxForCode(codeInstance);
            printCodeAttributes(codeInstance, null, null, "home?" + PARAM_VIEW + "=" + VIEW_CODE + "&");
            out.println("</div>");
          }
        } else {
          CodeInstance contextCodeInstance = null;
          if (req.getParameter(PARAM_CONTEXT_CODE_INSTANCE_ID) != null) {
            int contextCodeInstanceId = readInt(PARAM_CONTEXT_CODE_INSTANCE_ID, req);
            contextCodeInstance = CodeInstanceLogic.getCodeInstance(contextCodeInstanceId, dataSession);
          }

          out.println("<div class=\"centerColumn\">");
          out.println("<div class=\"topBox\">");
          out.println("</div>");

          printContextTableValues(codeTableInstance, codeTableInstance.getTable().getContextTable(), contextCodeInstance);
          out.println("</div>");

          if (contextCodeInstance != null) {
            out.println("<div class=\"rightColumn\">");
            printTopBoxForTable(codeTableInstance);
            printContextCodeValues(codeTableInstance, contextCodeInstance, codeInstance, Format.FULL, VIEW_CODE);
            out.println("<br/>");
            printCodeValueAddForm(codeTableInstance, contextCodeInstance, VIEW_TABLE);
            out.println("</div>");
          }
        }
      } else if (view.equals(VIEW_SEARCH)) {
        CodeInstance codeInstance = null;
        AttributeInstance attributeInstance = null;
        if (req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID) != null) {
          int attributeInstanceId = Integer.parseInt(req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID));
          attributeInstance = AttributeInstanceLogic.getAttributeInstance(attributeInstanceId, dataSession);
          codeInstance = attributeInstance.getCodeInstance();
        } else if (req.getParameter(PARAM_CODE_INSTANCE_ID) != null) {
          int codeInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_INSTANCE_ID));
          codeInstance = CodeInstanceLogic.getCodeInstance(codeInstanceId, dataSession);
        }

        out.println("<div class=\"leftColumn\">");
        printTopBoxSearch(req, action);

        if (codeInstance != null && userSession.getSearchResultsWithCodeInstanceList() != null) {
          printSearchResults(codeInstance, Format.SHORT);
        }

        out.println("</div>");

        if (codeInstance != null) {
          out.println("<div class=\"centerColumn\">");
          printTopBoxForCode(codeInstance);

          printCodeAttributes(codeInstance, attributeInstance, null, "home?" + PARAM_VIEW + "=" + VIEW_SEARCH + "&");
          out.println("</div>");
          if (attributeInstance != null) {
            out.println("<div class=\"rightColumn\">");
            printTopBoxForComments(attributeInstance);
            printComments(attributeInstance);
            out.println("</div>");
          }
        } else {
          if (userSession.getSearchResultsWithCodeInstanceList() != null) {
            out.println("<div class=\"centerColumn\">");
            printSearchResults(codeInstance, Format.FULL);
            out.println("</div>");
          }

        }

      } else if (view.equals(VIEW_CODE)) {
        CodeInstance codeInstance = null;
        AttributeInstance attributeInstance = null;
        if (req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID) != null) {
          int attributeInstanceId = Integer.parseInt(req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID));
          attributeInstance = AttributeInstanceLogic.getAttributeInstance(attributeInstanceId, dataSession);
          codeInstance = attributeInstance.getCodeInstance();
        } else if (req.getParameter(PARAM_CODE_INSTANCE_ID) != null) {
          int codeInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_INSTANCE_ID));
          codeInstance = CodeInstanceLogic.getCodeInstance(codeInstanceId, dataSession);
        }
        CodeTableInstance codeTableInstance = codeInstance.getTableInstance();
        CodeInstanceLogic.populateAttributeValueList(codeInstance, dataSession);
        CodeInstance contextCodeInstance = null;
        if (req.getParameter(PARAM_CONTEXT_CODE_INSTANCE_ID) != null) {
          int contextCodeInstanceId = readInt(PARAM_CONTEXT_CODE_INSTANCE_ID, req);
          contextCodeInstance = CodeInstanceLogic.getCodeInstance(contextCodeInstanceId, dataSession);
        } else if (codeInstance.getContext() != null) {
          contextCodeInstance = CodeInstanceLogic.getCodeInstance(codeInstance.getContext(), userSession.getReleaseVersion(), dataSession);
        }

        out.println("<div class=\"leftColumn\">");
        if (contextCodeInstance == null) {
          printTopBoxForTable(codeTableInstance);
          printCodeValues(codeTableInstance, codeInstance, Format.SHORT, VIEW_CODE);
          out.println("<br/>");
          printCodeValueAddForm(codeTableInstance, null, VIEW_CODE);
        } else {
          printTopBoxForTable(codeTableInstance);
          printContextCodeValues(codeTableInstance, contextCodeInstance, codeInstance, Format.SHORT, VIEW_CODE);
          out.println("<br/>");
          printCodeValueAddForm(codeTableInstance, contextCodeInstance, VIEW_TABLE);
        }
        out.println("</div>");
        out.println("<div class=\"centerColumn\">");
        printTopBoxForCode(codeInstance);
        printCodeAttributes(codeInstance, attributeInstance, null, "home?" + PARAM_VIEW + "=" + VIEW_CODE + "&");
        out.println("</div>");
        if (attributeInstance != null) {
          out.println("<div class=\"rightColumn\">");
          printTopBoxForComments(attributeInstance);
          printComments(attributeInstance);
          out.println("</div>");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }
    createFooter();
  }

  public void addAttributeComment(String value, int attributeTypeId, String comment, CodeMaster codeMaster) {
    {
      AttributeType attributeType = AttributeTypeLogic.getAttributeType(attributeTypeId, dataSession);
      AttributeValue attributeValue = AttributeValueLogic.getAttributeValue(codeMaster, attributeType, value, dataSession);
      if (attributeValue == null) {
        attributeValue = new AttributeValue();
        attributeValue.setAttributeType(attributeType);
        attributeValue.setCode(codeMaster);
        attributeValue.setAttributeValue(value);
        AttributeValueLogic.saveAttributeValue(attributeValue, dataSession);
      }
      AttributeInstance attributeInstance = AttributeInstanceLogic.getAttributeInstance(userSession.getReleaseVersion(), attributeValue, dataSession);
      if (attributeInstance == null) {
        attributeInstance = new AttributeInstance();
        attributeInstance.setValue(attributeValue);
        attributeInstance.setCodeInstance(codeInstance);
        attributeInstance.setAcceptStatus(AcceptStatus.PROPOSED);
        AttributeInstanceLogic.saveAttributeInstance(attributeInstance, dataSession);
      }
      AttributeComment attributeComment = new AttributeComment();
      attributeComment.setValue(attributeValue);
      attributeComment.setUser(userSession.getUser());
      attributeComment.setCommentText(comment);
      attributeComment.setEntryDate(new Date());
      attributeComment.setPositionStatus(PositionStatus.FOR);
      AttributeCommentLogic.saveAttributeComment(attributeComment, dataSession);
    }
  }

  public void readParams(HttpServletRequest req) {
    paramCodeValue = req.getParameter(PARAM_CODE_VALUE);
    paramCodeLabel = req.getParameter(PARAM_CODE_LABEL);
    paramUseValue = req.getParameter(PARAM_USE_VALUE);
    paramCodeStatus = req.getParameter(PARAM_CODE_STATUS);
    paramHl7CodeTable = req.getParameter(PARAM_HL7_CODE_TABLE);
    paramCommentText = req.getParameter(PARAM_COMMENT_TEXT);
    if (paramCodeValue == null) {
      paramCodeValue = "";
    }
    if (paramCodeLabel == null) {
      paramCodeLabel = "";
    }
    if (paramUseValue == null) {
      paramUseValue = "";
    }
    if (paramCodeStatus == null) {
      paramCodeStatus = "";
    }
    if (paramHl7CodeTable == null) {
      paramHl7CodeTable = "";
    }
    if (paramCommentText == null) {
      paramCommentText = "";
    }
    if (req.getParameter(PARAM_CODE_INSTANCE_ID) != null) {
      int codeInstanceId = Integer.parseInt(req.getParameter(PARAM_CODE_INSTANCE_ID));
      codeInstance = CodeInstanceLogic.getCodeInstance(codeInstanceId, dataSession);
    } else {
      codeInstance = null;
    }
  }

  public void printTopBoxSearch(HttpServletRequest req, String action) {
    String codeValue = userSession.getSearchCodeValue();
    String codeLabel = userSession.getSearchCodeLabel();
    if (action != null) {
      if (action.equals(ACTION_SEARCH)) {
        codeValue = req.getParameter(PARAM_CODE_VALUE);
        codeLabel = req.getParameter(PARAM_CODE_LABEL);
        userSession.setSearchCodeValue(codeValue);
        userSession.setSearchCodeLabel(codeLabel);
        if (!codeValue.equals("") || !codeLabel.equals("")) {
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
    out.println("    <td colspan=\"2\">");
    out.println("      <span class=\"formButtonFloat\">");
    out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_SEARCH + "\"/>");
    out.println("      </span>");
    out.println("    </td>");
    out.println("  </tr>");
    out.println("</table>");
    out.println("<input type=\"hidden\" name=\"" + PARAM_VIEW + "\" value=\"" + VIEW_SEARCH + "\"/></td>");
    out.println("</form>");
    out.println("</div>");

  }

  public void printSearchResults(CodeInstance codeInstance, Format format) {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Search Results</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    if (format == Format.FULL) {
      out.println("    <th>Table</th>");
    }
    out.println("  </tr>");
    for (CodeInstance ci : userSession.getSearchResultsWithCodeInstanceList()) {
      String link = "home?" + PARAM_VIEW + "=" + VIEW_SEARCH + "&" + PARAM_CODE_INSTANCE_ID + "=" + ci.getCodeInstanceId();
      String classString = "";
      if (codeInstance != null && codeInstance.equals(ci)) {
        classString = "selected";
      }

      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + chop(ci.getCode().getCodeValue(), 12) + "</a></td>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getCodeLabel() + "</a></td>");
      if (format == Format.FULL) {
        out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getTableInstance().getTableLabel() + "</a></td>");
      }
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  private static String chop(String s, int length) {
    if (s.length() > (length - 3)) {
      s = s.substring(0, (length - 3)) + "&hellip;";
    }
    return s;
  }

  public void printReleaseVersions(ReleaseVersion releaseVersion, Format format) {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Release Versions</caption>");
    out.println("  <tr>");
    out.println("    <th>Version</th>");
    if (format == Format.FULL) {
      out.println("    <th>Released</th>");
    }
    out.println("    <th>Status</th>");
    out.println("  </tr>");
    List<ReleaseVersion> releaseVersionList = ReleaseVersionLogic.getReleaseVersions(dataSession);
    for (ReleaseVersion rv : releaseVersionList) {
      String link = "home?" + PARAM_RELEASE_ID + "=" + rv.getReleaseId();
      String classString = "";
      if (releaseVersion != null && releaseVersion.equals(rv)) {
        classString = "selected";
      }
      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + rv.getVersion() + "</a></td>");
      if (format == Format.FULL) {
        printDateCell(rv.getReleasedDate(), classString);
      }
      out.println("    <td class=\"" + classString + "\">" + rv.getReleaseStatus() + "</td>");
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  private void printLogin(User user) {
    if (user == null) {
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
      out.println("    <td colspan=\"2\">");
      out.println("      <span class=\"formButtonFloat\">");
      out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_LOGIN + "\"/>");
      out.println("      </span>");
      out.println("    </td>");
      out.println("  </tr>");
      out.println("</table>");
      out.println("</form>");
    } else {
      out.println("<form method=\"POST\" action=\"home\">");
      out.println("<table width=\"100%\">");
      out.println("  <caption>" + user.getUserName() + "</caption>");
      for (ApplicationUser applicationUser : user.getApplicationUserList()) {
        String link = "home?" + PARAM_ACTION + "=" + ACTION_SELECT_APPLICATION + "&" + PARAM_APPLICATION_ID + "="
            + applicationUser.getApplication().getApplicationId();
        out.println("  <tr>");
        out.println("    <td><a href=\"" + link + "\">" + applicationUser.getApplication().getApplicationAcronym() + "</a></td>");
        out.println("    <td><a href=\"" + link + "\">" + applicationUser.getUserType() + "</a></td>");
        out.println("  </tr>");
      }
      out.println("  <tr>");
      out.println("    <td colspan=\"2\">");
      out.println("      <span class=\"formButtonFloat\">");
      out.println("        <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_LOGOUT + "\"/>");
      out.println("      </span>");
      out.println("    </td>");
      out.println("  </tr>");
      out.println("</table>");
      out.println("</form>");

    }
  }

  public void printDateCell(Date date, String classString) {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    if (date == null) {
      out.println("    <td class=\"" + classString + "\"></td>");
    } else {
      out.println("    <td class=\"" + classString + "\">" + sdf.format(date) + "</td>");
    }
  }

  public void printComments(AttributeInstance attributeInstance) {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Comments</caption>");
    List<AttributeComment> attributeCommentList = AttributeCommentLogic.getAttributeCommentList(attributeInstance, dataSession);
    for (AttributeComment ac : attributeCommentList) {
      out.println("  <tr>");
      out.println("    <td>");
      out.println("      <div class=\"userCommentHeader\">" + ac.getUser().getUserName());
      if (ac.getPositionStatus() == PositionStatus.AGAINST) {
        out.println("does not agree:");
      } else if (ac.getPositionStatus() == PositionStatus.FOR) {
        out.println("agrees:");
      } else if (ac.getPositionStatus() == PositionStatus.NEUTRAL) {
        out.println("writes:");
      } else if (ac.getPositionStatus() == PositionStatus.PROBLEM) {
        out.println("sees problem:");
      } else if (ac.getPositionStatus() == PositionStatus.QUESTION) {
        out.println("has a question:");
      } else if (ac.getPositionStatus() == PositionStatus.RESEARCH) {
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

  public void printTopBoxReleaseVersion(ReleaseVersion releaseVersion) {
    out.println("<div class=\"topBox\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Release " + releaseVersion.getVersion() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Started</th>");
    printDateCell(releaseVersion.getStartedDate(), "");
    out.println("    </tr>");
    if (releaseVersion.getReleasedDate() != null) {
      out.println("    <tr>");
      out.println("      <th>Released</th>");
      printDateCell(releaseVersion.getReleasedDate(), "");
      out.println("    </tr>");
    }
    if (releaseVersion.getRetiredDate() != null) {
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

  public void printCodeAttributes(CodeInstance codeInstance, AttributeInstance attributeInstance, AttributeType attributeType, String view) {
    out.println("<table width=\"100%\">");
    out.println("  <caption>Attributes</caption>");
    out.println("  <tr>");
    out.println("    <th>Attribute</th>");
    out.println("    <th>Value</th>");
    out.println("  </tr>");
    List<AttributeAssigned> attributeAssignedList = AttributeAssignedLogic.getAttributeAssignedList(codeInstance.getTableInstance().getTable(),
        dataSession);
    for (AttributeAssigned aa : attributeAssignedList) {
      AttributeType at = aa.getAttributeType();
      CodeInstanceLogic.populateAttributeValueList(codeInstance, dataSession);
      // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      List<AttributeInstance> attributeInstanceList = codeInstance.getAttributeTypeToValueMap().get(at);
      boolean showedOne = false;
      boolean acceptedOne = false;
      if (attributeInstanceList != null) {
        for (AttributeInstance ai : attributeInstanceList) {
          if (!userSession.canEdit() && ai.getAcceptStatus() == AcceptStatus.REJECTED) {
            continue;
          }
          String classString = "";
          if (attributeInstance != null && attributeInstance.equals(ai)) {
            classString = "selected";
          }
          if (ai.getAcceptStatus() == AcceptStatus.REJECTED) {
            classString += " strike";
          }
          String link = view + PARAM_ATTRIBUTE_INSTANCE_ID + "=" + ai.getAttributeInstanceId();
          String issueCountLabel = "";
          if (userSession.canEdit()) {
            if (ai.getAcceptStatus() != AcceptStatus.REJECTED && ai.getAcceptStatus() != AcceptStatus.CONFIRMED) {
              issueCountLabel = " <span class=\"issueCountLabel\">" + ai.getAcceptStatus() + "</span>";
            }
            if (acceptedOne && !aa.isAllowMultiple() && ai.getAcceptStatus() != AcceptStatus.REJECTED) {
              issueCountLabel += " <span class=\"issueCountLabel\">duplicate</span>";
            }
            if (ai.getAcceptStatus() != AcceptStatus.REJECTED) {
              acceptedOne = true;
            }
          }
          out.println("  <tr>");
          if (showedOne) {
            out.println("    <td class=\"" + classString + "\"></td>");
          } else {
            out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + at.getAttributeLabel() + "</a></td>");
          }
          /*
           * -- SELECT_TEXT "S" - "Select Text" -- INTEGER "I" - "Integer" --
           * CODE_MASTER "C" - "Code Master" -- CODE_TABLE "A" = "Code Table"
           */
          if (at.getAttributeFormat() == AttributeFormat.SELECT_TEXT) {
            AllowedValue allowedValue = AllowedValueLogic.getAllowedValue(at, getValueLabel(ai), dataSession);
            if (allowedValue != null) {
              out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + allowedValue.getDisplayText() + "</a>"
                  + issueCountLabel + "</td>");
            } else {
              out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + getValueLabel(ai) + "</a>" + issueCountLabel + "</td>");
            }
          } else {
            out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + getValueLabel(ai) + "</a>" + issueCountLabel + "</td>");
          }
          out.println("  </tr>");
          showedOne = true;
        }
      }
      if (!showedOne) {
        out.println("  <tr>");
        String classString = "";
        if (userSession.canEdit()) {
          if (attributeType != null && attributeType.equals(at)) {
            classString = "selected";
          }

          String link = "attribute?" + PARAM_CODE_INSTANCE_ID + "=" + codeInstance.getCodeInstanceId() + "&" + PARAM_ATTRIBUTE_TYPE_ID + "="
              + at.getAttributeTypeId();
          out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + at.getAttributeLabel() + "</a></td>");
        } else {
          out.println("    <td class=\"" + classString + "\">" + at.getAttributeLabel() + "</td>");
        }
        out.println("    <td class=\"" + classString + "\"></td>");
        out.println("  </tr>");
      }
    }

    out.println("</table>");
  }

  public void printTopBoxForComments(AttributeInstance attributeInstance) {
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
    if (isLoggedIn()) {
      out.println("    <tr>");
      out.println("      <td colspan=\"2\">");
      out.println("        <form action=\"attribute\" method=\"GET\">");
      out.println("          <input type=\"hidden\" name=\"" + PARAM_ATTRIBUTE_INSTANCE_ID + "\" value=\""
          + attributeInstance.getAttributeInstanceId() + "\"/>");
      out.println("          <span class=\"formButtonFloat\">");
      out.println("            <input type=\"submit\" name=\"submit\" value=\"Edit\"/>");
      out.println("          </span>");
      out.println("        </form></td>");
      out.println("    </tr>");
    }
    out.println("  </table>");
    out.println("</div>");
  }

  public String getValueLabel(AttributeInstance attributeInstance) {
    AttributeType at = attributeInstance.getValue().getAttributeType();
    if (at.getAttributeFormat() == AttributeFormat.CODE_MASTER) {
      String codeValue = attributeInstance.getValue().getAttributeValue();
      CodeInstance codeInstance = CodeInstanceLogic.getCodeInstance(at.getRefTable(), userSession.getReleaseVersion(), codeValue, dataSession);
      if (codeInstance != null) {
        return codeValue + " - " + codeInstance.getCodeLabel();
      } else {
        return codeValue;
      }
    } else if (at.getAttributeFormat() == AttributeFormat.CODE_TABLE) {
      int tableId = 0;
      try {
        tableId = Integer.parseInt(attributeInstance.getValue().getAttributeValue());
      } catch (NumberFormatException nfe) {
        return attributeInstance.getValue().getAttributeValue();
      }
      CodeTableInstance codeTableInstance = CodeTableInstanceLogic.getCodeTableInstance(tableId, dataSession);
      return tableId + " - " + codeTableInstance.getTableLabel();
    } else if (at.getAttributeFormat() == AttributeFormat.DATE) {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.FREE_TEXT) {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.INTEGER) {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.LONG_TEXT) {
      return attributeInstance.getValue().getAttributeValue();
    } else if (at.getAttributeFormat() == AttributeFormat.SELECT_TEXT) {
      String savedValue = attributeInstance.getValue().getAttributeValue();
      AllowedValue allowedValue = AllowedValueLogic.getAllowedValue(at, savedValue, dataSession);
      if (allowedValue != null) {
        return allowedValue.getDisplayText();
      }
      return savedValue;
    } else {
      return attributeInstance.getValue().getAttributeValue();
    }
  }

  public void printTopBoxForTable(CodeTableInstance codeTableInstance) {
    out.println("<div class=\"topBox\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>" + codeTableInstance.getTableLabel() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Table Id</th>");
    out.println("      <td>" + codeTableInstance.getTable().getTableId() + "</td>");
    out.println("    </tr>");
    if (codeTableInstance.getTable().getContextTable() != null) {
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
    if (isAdmin()) {
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

  public void printTopBoxForCode(CodeInstance codeInstance) {
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
    out.println("      <th>Code Id</th>");
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

  public void printContextTableValues(CodeTableInstance codeTableInstance, CodeTable contextTable, CodeInstance contextCodeInstance) {

    CodeTableInstance contextTableInstance = CodeTableLogic.getCodeTableInstance(contextTable, userSession.getReleaseVersion(), dataSession);
    List<CodeInstance> contextCodeInstanceList = CodeMasterLogic.getCodeValues(contextTableInstance, dataSession);

    out.println("<table width=\"100%\">");
    out.println("  <caption>Choose " + contextTableInstance.getTableLabel() + "</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    out.println("  </tr>");
    for (CodeInstance cci : contextCodeInstanceList) {
      String link = "home?" + PARAM_VIEW + "=" + VIEW_TABLE + "&" + PARAM_CODE_TABLE_INSTANCE_ID + "=" + codeTableInstance.getTableInstanceId() + "&"
          + PARAM_CONTEXT_CODE_INSTANCE_ID + "=" + cci.getCodeInstanceId();

      String classString = "";
      if (contextCodeInstance != null && contextCodeInstance.equals(cci)) {
        classString = "selected";
      }
      String codeStatusString = "";
      if (!cci.getCodeStatus().equals(CodeInstance.CODE_STATUS_VALID)) {
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
      String view) {

    List<CodeInstance> codeInstanceList = CodeMasterLogic.getCodeValues(codeTableInstance, contextCodeInstance, dataSession);
    out.println("<table width=\"100%\">");
    out.println("  <caption>Code Values for " + contextCodeInstance.getCodeLabel() + "</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    if (format == Format.FULL) {
      out.println("    <th>Use</th>");
    }
    out.println("  </tr>");
    for (CodeInstance ci : codeInstanceList) {
      String link = "home?" + PARAM_VIEW + "=" + view + "&" + PARAM_CODE_INSTANCE_ID + "=" + ci.getCodeInstanceId() + "&"
          + PARAM_CONTEXT_CODE_INSTANCE_ID + "=" + contextCodeInstance.getCodeInstanceId();

      String classString = "";
      if (codeInstance != null && codeInstance.equals(ci)) {
        classString = "selected";
      }
      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + chop(ci.getCode().getCodeValue(), 12) + "</a></td>");
      String codeStatusString = "";
      if (!ci.getCodeStatus().equals(CodeInstance.CODE_STATUS_VALID)) {
        codeStatusString = " <span class=\"codeStatusLabel\">" + ci.getCodeStatusLabel() + "</span>";
      }
      String issueCountLabel = "";
      if (userSession.canEdit() && ci.getIssueCount() > 0) {
        if (ci.getIssueCount() == 1) {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issue</span>";
        } else {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issues</span>";
        }
      }
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getCodeLabel() + codeStatusString + "</a>" + issueCountLabel
          + "</td>");
      if (format == Format.FULL) {
        if (ci.getUseValue() != null && !ci.getUseValue().trim().equals(ci.getCode().getCodeValue().trim())) {
          out.println("    <td class=\"" + classString + "\">" + ci.getUseValue() + "</td>");
        } else {
          out.println("    <td class=\"" + classString + "\"></td>");
        }
      }
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  public void printCodeValues(CodeTableInstance codeTableInstance, CodeInstance codeInstance, Format format, String view) {

    List<CodeInstance> codeInstanceList = CodeMasterLogic.getCodeValues(codeTableInstance, dataSession);
    out.println("<table width=\"100%\">");
    out.println("  <caption>Code Values</caption>");
    out.println("  <tr>");
    out.println("    <th>Value</th>");
    out.println("    <th>Label</th>");
    if (format == Format.FULL) {
      out.println("    <th>Use</th>");
    }
    out.println("  </tr>");
    for (CodeInstance ci : codeInstanceList) {
      String link = "home?" + PARAM_VIEW + "=" + view + "&" + PARAM_CODE_INSTANCE_ID + "=" + ci.getCodeInstanceId();
      String classString = "";
      if (codeInstance != null && codeInstance.equals(ci)) {
        classString = "selected";
      }

      if (ci.isInclusionStatusRemove()) {
        classString = classString + " strike";
      }
      out.println("  <tr>");
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + chop(ci.getCode().getCodeValue(), 12) + "</a></td>");
      String codeStatusString = "";
      if (!ci.getCodeStatus().equals(CodeInstance.CODE_STATUS_VALID)) {
        codeStatusString = " <span class=\"codeStatusLabel\">" + ci.getCodeStatusLabel() + codeStatusString + "</span>";
      }
      String issueCountLabel = "";
      if (userSession.canEdit() && ci.getIssueCount() > 0) {
        if (ci.getIssueCount() == 1) {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issue</span>";
        } else {
          issueCountLabel = " <span class=\"issueCountLabel\">" + ci.getIssueCount() + " issues</span>";
        }
      }
      out.println("    <td class=\"" + classString + "\"><a href=\"" + link + "\">" + ci.getCodeLabel() + codeStatusString + "</a>" + issueCountLabel
          + "</td>");
      if (format == Format.FULL) {
        if (ci.getUseValue() != null && !ci.getUseValue().trim().equals(ci.getCode().getCodeValue().trim())) {
          out.println("    <td class=\"" + classString + "\">" + ci.getUseValue() + "</td>");
        } else {
          out.println("    <td class=\"" + classString + "\"></td>");
        }
      }
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  public void printCodeValueAddForm(CodeTableInstance codeTableInstance, CodeInstance contextCodeInstance, String view) {
    out.println("<form action=\"home\" method=\"POST\">");
    out.println("<input type=\"hidden\" name=\"" + PARAM_CODE_TABLE_INSTANCE_ID + "\" value=\"" + codeTableInstance.getTableInstanceId() + "\"/>");
    out.println("<input type=\"hidden\" name=\"" + PARAM_VIEW + "\" value=\"" + view + "\"/>");
    if (contextCodeInstance != null) {
      out.println("<input type=\"hidden\" name=\"" + PARAM_CONTEXT_CODE_INSTANCE_ID + "\" value=\"" + contextCodeInstance.getCodeInstanceId()
          + "\"/>");
    }
    if (userSession.canEdit()) {
      out.println("  <table width=\"100%\">");
      out.println("    <caption>Add New Code Value</caption>");
      out.println("    <tr>");
      out.println("      <th>Value</th>");
      out.println("      <td><input type=\"text\" size=\"7\" name=\"" + PARAM_CODE_VALUE + "\" value=\"" + paramCodeValue + "\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Label</th>");
      out.println("      <td><input type=\"text\" size=\"30\" name=\"" + PARAM_CODE_LABEL + "\" value=\"" + paramCodeLabel + "\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Code Status</th>");
      out.println("      <td>");
      out.println("        <select name=\"" + PARAM_CODE_STATUS + "\"/>");
      out.println("          <option value=\"\">--select--</option>");
      AttributeType attributTypeCodeStatus = AttributeTypeLogic.getAttributeType(AttributeTypeLogic.AT_CODE_STATUS, dataSession);
      List<AllowedValue> allowedValueList = AllowedValueLogic.getAllowedValueList(attributTypeCodeStatus, dataSession);
      for (AllowedValue allowedValue : allowedValueList) {
        out.println("          <option value=\"" + allowedValue.getSavedValue() + "\""
            + (allowedValue.getSavedValue().equals(paramCodeStatus) ? " selected=\"true\"" : "") + ">" + allowedValue.getDisplayText() + "</option>");
      }
      out.println("        </select>");
      out.println("      </td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Use Value</th>");
      out.println("      <td><input type=\"text\" size=\"7\" name=\"" + PARAM_USE_VALUE + "\" value=\"" + paramUseValue + "\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>HL7 Code Table</th>");
      out.println("      <td><input type=\"text\" size=\"30\" name=\"" + PARAM_HL7_CODE_TABLE + "\" value=\"" + paramHl7CodeTable + "\"/></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <th>Comment</th>");
      out.println("      <td><textarea cols=\"30\" rows=\"3\" name=\"" + PARAM_COMMENT_TEXT + "\">" + paramCommentText + "</textarea></td>");
      out.println("    </tr>");
      out.println("    <tr>");
      out.println("      <td colspan=\"2\">");
      out.println("        <span class=\"formButtonFloat\">");
      out.println("          <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_ADD_VALUE + "\"/>");
      out.println("        </span>");
      out.println("      </td>");
      out.println("    </tr>");
      out.println("  </table>");
      out.println("</form>");
    }
  }
}
