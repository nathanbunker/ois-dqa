package org.openimmunizationsoftware.dqa.cm.servlet;

import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CODE_LABEL;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CODE_STATUS;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CONTEXT_CODE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_HL7_CODE_TABLE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_INCLUSION_STATUS;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_INDICATES_TABLE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_USE_VALUE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.getAttributeType;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openimmunizationsoftware.dqa.cm.logic.AllowedValueLogic;
import org.openimmunizationsoftware.dqa.cm.logic.AttributeCommentLogic;
import org.openimmunizationsoftware.dqa.cm.logic.AttributeInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.AttributeValueLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeMasterLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableLogic;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AllowedValue;
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

public class AttributeServlet extends HomeServlet
{

  public AttributeServlet() {
    super();
  }

  protected static final String PARAM_POSITION_STATUS = "positionStatus";
  protected static final String PARAM_COMMENT_TEXT = "commentText";
  protected static final String PARAM_COMMENT_TEXT_NEW = "commentTextNew";
  protected static final String PARAM_VALUE_NEW = "valueNew";

  protected static final String ACTION_UPDATE = "Update";

  private String paramPositionStatus;
  private String paramCommentText;
  private String paramValueNew;
  private String paramCommentTextNew;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    setup(req, resp);
    if (!isLoggedIn())
    {
      sendToHome(req, resp);
      return;
    }
    try
    {
      CodeInstance codeInstance = null;
      AttributeInstance attributeInstance = null;
      {
        int attributeInstanceId = Integer.parseInt(req.getParameter(PARAM_ATTRIBUTE_INSTANCE_ID));
        attributeInstance = AttributeInstanceLogic.getAttributeInstance(attributeInstanceId, dataSession);
        codeInstance = attributeInstance.getCodeInstance();
      }
      CodeInstanceLogic.populateAttributeValueList(codeInstance, dataSession);
      String action = req.getParameter(PARAM_ACTION);
      readParams(req);
      if (action != null)
      {
        if (action.equals(ACTION_UPDATE))
        {

          if (paramPositionStatus.equals(""))
          {
            messageError = "Unable to update your position, you did not indicate your position.";
          } else if (paramCommentText.equals(""))
          {
            messageError = "Unable to update your position, you did not indicate the reason for your position.";
          } else if (!paramValueNew.equals("") && paramCommentTextNew.equals(""))
          {
            messageError = "Unable to update new proposed value, you did not indicate the reason for the new proposed value.";
          } else
          {
            AttributeComment attributeComment = new AttributeComment();
            attributeComment.setValue(attributeInstance.getValue());
            attributeComment.setUser(userSession.getUser());
            attributeComment.setCommentText(paramCommentText);
            attributeComment.setEntryDate(new Date());
            attributeComment.setPositionStatus(PositionStatus.get(paramPositionStatus));
            AttributeCommentLogic.saveAttributeComment(attributeComment, dataSession);
            AttributeInstanceLogic.updateAttributeInstanceAcceptStatus(attributeInstance, dataSession);

            if (!paramValueNew.equals(""))
            {
              AttributeValue attributeValueNew = AttributeValueLogic.getAttributeValue(attributeInstance.getValue().getCode(), paramValueNew,
                  dataSession);
              if (attributeValueNew == null)
              {
                attributeValueNew = new AttributeValue();
                attributeValueNew.setAttributeType(attributeInstance.getValue().getAttributeType());
                attributeValueNew.setCode(attributeInstance.getValue().getCode());
                attributeValueNew.setAttributeValue(paramValueNew);
                AttributeValueLogic.saveAttributeValue(attributeValueNew, dataSession);
              }
              AttributeInstance attributeInstanceNew = AttributeInstanceLogic.getAttributeInstance(userSession.getReleaseVersion(),
                  attributeValueNew, dataSession);
              if (attributeInstanceNew == null)
              {
                attributeInstanceNew = new AttributeInstance();
                attributeInstanceNew.setValue(attributeValueNew);
                attributeInstanceNew.setCodeInstance(codeInstance);
                attributeInstanceNew.setAcceptStatus(AcceptStatus.PROPOSED);
                AttributeInstanceLogic.saveAttributeInstance(attributeInstanceNew, dataSession);
              }
              AttributeComment attributeCommentNew = new AttributeComment();
              attributeCommentNew.setValue(attributeValueNew);
              attributeCommentNew.setUser(userSession.getUser());
              attributeCommentNew.setCommentText(paramCommentTextNew);
              attributeCommentNew.setEntryDate(new Date());
              attributeCommentNew.setPositionStatus(PositionStatus.FOR);
              AttributeCommentLogic.saveAttributeComment(attributeCommentNew, dataSession);
              AttributeInstanceLogic.updateAttributeInstanceAcceptStatus(attributeInstanceNew, dataSession);
            }
            paramPositionStatus = "";
            paramCommentText = "";
            paramValueNew = "";
            paramCommentTextNew = "";
            CodeInstanceLogic.populateAttributeValueList(codeInstance, dataSession);
            CodeInstanceLogic.populateTableValuesFromAttributeInstance(codeInstance, dataSession);
            CodeInstanceLogic.saveCodeInstance(codeInstance, dataSession);
          }

        }
      }
      createHeader();

      out.println("<div class=\"leftColumn\">");
      printTopBoxForCode(codeInstance);
      printCodeAttributes(codeInstance, attributeInstance, "attribute?");
      out.println("</div>");

      out.println("<div class=\"centerColumn\">");
      printTopBoxForComments(attributeInstance);
      printComments(attributeInstance);

      out.println("</div>");

      out.println("<div class=\"rightColumn\">");
      printTopBoxForCommentPosition(attributeInstance);
      printCommentForm(attributeInstance, req);
      out.println("</div>");

    } catch (Exception e)
    {
      e.printStackTrace();
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }
    createFooter();
  }

  public void populateTableValuesFromAttributeInstance(CodeInstance codeInstance)
  {
    String contextCode = codeInstance.getAttributeValue(getAttributeType(AT_CONTEXT_CODE, dataSession));
    if (contextCode != null)
    {
      int contextId = Integer.parseInt(contextCode);
      CodeMaster contextCodeMaster = CodeMasterLogic.getCodeMaster(contextId, dataSession);
      codeInstance.setContext(contextCodeMaster);
    }
    String indicatesTable = codeInstance.getAttributeValue(getAttributeType(AT_INDICATES_TABLE, dataSession));
    if (indicatesTable != null)
    {
      int indicatesTableId = Integer.parseInt(indicatesTable);
      CodeTable indicatesCodeTable = CodeTableLogic.getCodeTable(indicatesTableId, dataSession);
      codeInstance.setIndicatesTable(indicatesCodeTable);
    }
    String codeLabel = codeInstance.getAttributeValue(getAttributeType(AT_CODE_LABEL, dataSession));
    if (codeLabel != null)
    {
      codeInstance.setCodeLabel(codeLabel);
    }
    String useValue = codeInstance.getAttributeValue(getAttributeType(AT_USE_VALUE, dataSession));
    if (useValue != null)
    {
      codeInstance.setUseValue(useValue);
    }
    String codeStatus = codeInstance.getAttributeValue(getAttributeType(AT_CODE_STATUS, dataSession));
    if (codeStatus != null)
    {
      codeInstance.setCodeStatus(codeStatus);
    }
    String hl7CodeTable = codeInstance.getAttributeValue(getAttributeType(AT_HL7_CODE_TABLE, dataSession));
    if (hl7CodeTable != null)
    {
      codeInstance.setHl7CodeTable(hl7CodeTable);
    }
    String inclusionStatus = codeInstance.getAttributeValue(getAttributeType(AT_INCLUSION_STATUS, dataSession));
    if (inclusionStatus != null)
    {
      codeInstance.setInclusionStatus(InclusionStatus.get(inclusionStatus));
    }
  }

  public void readParams(HttpServletRequest req)
  {
    paramPositionStatus = req.getParameter(PARAM_POSITION_STATUS);
    paramCommentText = req.getParameter(PARAM_COMMENT_TEXT);
    paramValueNew = req.getParameter(PARAM_VALUE_NEW);
    paramCommentTextNew = req.getParameter(PARAM_COMMENT_TEXT_NEW);
    if (paramPositionStatus == null)
    {
      paramPositionStatus = "";
    }
    if (paramCommentText == null)
    {
      paramCommentText = "";
    }
    if (paramValueNew == null)
    {
      paramValueNew = "";
    }
    if (paramCommentTextNew == null)
    {
      paramCommentTextNew = "";
    }
  }

  public void printCommentForm(AttributeInstance attributeInstance, HttpServletRequest req)
  {

    String value = attributeInstance.getValue().getAttributeValue();

    AttributeType at = attributeInstance.getValue().getAttributeType();
    out.println("<script>");
    out.println("  <!-- ");
    out.println("    function openAgainst1() { ");
    out.println("      against1Div = document.getElementById('against1'); ");
    out.println("      against1Div.style.display = 'table-row';");
    out.println("    }");
    out.println("    function openAgainst2(field) { ");
    out.println("      against2Div = document.getElementById('against2'); ");
    out.println("      if (field.value != '') ");
    out.println("      { ");
    out.println("        against2Div.style.display = 'table-row';");
    out.println("      } else { ");
    out.println("        against2Div.style.display = 'none';");
    out.println("      }");
    out.println("    }");
    out.println("  --> ");
    out.println("</script>");
    out.println("<form action=\"attribute\" method=\"POST\">");
    out.println("  <input type=\"hidden\" name=\"" + PARAM_ATTRIBUTE_INSTANCE_ID + "\" value=\"" + attributeInstance.getAttributeInstanceId()
        + "\"/>");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Edit " + at.getAttributeLabel() + "</caption>");
    out.println("    <tr>");
    out.println("      <th>Current Value</th>");
    out.println("      <td>" + getValueLabel(attributeInstance) + "</td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>My Position</th>");
    out.println("      <td>");
    printRadioOption(paramPositionStatus, PositionStatus.FOR, "I agree", "");
    printRadioOption(paramPositionStatus, PositionStatus.RESEARCH, "I think research is needed", "");
    printRadioOption(paramPositionStatus, PositionStatus.QUESTION, "I have a question", "");
    printRadioOption(paramPositionStatus, PositionStatus.PROBLEM, "I see a problem", "");
    printRadioOption(paramPositionStatus, PositionStatus.AGAINST, "I disagree", " onChange=\"openAgainst1()\"");
    out.println("      </td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <th>Because</th>");
    out.println("      <td><textarea rows=\"2\" cols=\"30\" name=\"" + PARAM_COMMENT_TEXT + "\">" + paramCommentText + "</textarea></td>");
    out.println("    </tr>");
    String hiddenAreaClass = "";
    if (paramValueNew.length() == 0)
    {
      hiddenAreaClass = " class=\"hiddenArea\"";
    }
    out.println("    <tr" + hiddenAreaClass + " id=\"against1\">");
    out.println("      <th>Proposed value</th>");
    out.println("      <td>");
    if (at.getAttributeFormat() == AttributeFormat.CODE_MASTER)
    {
      out.println("        <select name=\"" + PARAM_VALUE_NEW + "\" onchange=\"openAgainst2(this)\">");
      out.println("          <option value=\"\">--select--</option>");
      CodeTableInstance codeTableInstance = CodeTableLogic.getCodeTableInstance(at.getRefTable(), userSession.getReleaseVersion(), dataSession);
      List<CodeInstance> codeInstanceList = CodeMasterLogic.getCodeValues(codeTableInstance, dataSession);
      for (CodeInstance ci : codeInstanceList)
      {
        String codeValue = ci.getCode().getCodeValue();
        if (codeValue.equals(value))
        {
          continue;
        }
        boolean selected = codeValue.equals(paramValueNew);
        out.println("          <option value=\"" + codeValue + "\"" + (selected ? " selected=\"true\"" : "") + ">" + codeValue + " - "
            + ci.getCodeLabel() + "</option>");
      }
      out.println("        </select>");
    } else if (at.getAttributeFormat() == AttributeFormat.CODE_TABLE)
    {
      out.println("        <select name=\"" + PARAM_VALUE_NEW + "\" onChange=\"openAgainst2(this)\">");
      out.println("          <option value=\"\">--select--</option>");
      List<CodeTableInstance> codeTableInstanceList = CodeTableLogic.getCodeTables(userSession.getReleaseVersion(), dataSession);
      for (CodeTableInstance cti : codeTableInstanceList)
      {
        if (Integer.toString(cti.getTable().getTableId()).equals(value))
        {
          continue;
        }
        out.println("          <option value=\"" + cti.getTable().getTableId() + "\""
            + (Integer.toString(cti.getTable().getTableId()).equals(paramValueNew) ? " selected=\"true\"" : "") + ">" + cti.getTable().getTableId()
            + " - " + cti.getTableLabel() + "</option>");
      }
      out.println("        </select>");
    } else if (at.getAttributeFormat() == AttributeFormat.DATE)
    {
      out.println("        <input type=\"text\" name=\"" + PARAM_VALUE_NEW + "\" size=\"10\" value=\"" + paramValueNew
          + "\" onkeyup=\"openAgainst2(this)\"/>");
    } else if (at.getAttributeFormat() == AttributeFormat.FREE_TEXT)
    {
      out.println("        <input type=\"text\" name=\"" + PARAM_VALUE_NEW + "\" size=\"30\" value=\"" + paramValueNew
          + "\" onkeyup=\"openAgainst2(this)\"/>");
    } else if (at.getAttributeFormat() == AttributeFormat.INTEGER)
    {
      out.println("        <input type=\"text\" name=\"" + PARAM_VALUE_NEW + "\" size=\"4\" value=\"" + paramValueNew
          + "\" onkeyup=\"openAgainst2(this)\"/>");
    } else if (at.getAttributeFormat() == AttributeFormat.LONG_TEXT)
    {
      out.println("        <textarea rows=\"2\" cols=\"30\" name=\"" + PARAM_VALUE_NEW + "\" onkeyup=\"openAgainst2(this)\">" + paramValueNew
          + "</textarea>");
    } else if (at.getAttributeFormat() == AttributeFormat.SELECT_TEXT)
    {
      out.println("        <select name=\"" + PARAM_VALUE_NEW + "\" onChange=\"openAgainst2(this)\">");
      out.println("          <option value=\"\">--select--</option>");
      List<AllowedValue> allowedValueList = AllowedValueLogic.getAllowedValueList(at, dataSession);
      for (AllowedValue allowedValue : allowedValueList)
      {
        if (allowedValue.getSavedValue().equals(value))
        {
          continue;
        }
        out.println("          <option value=\"" + allowedValue.getSavedValue() + "\""
            + (allowedValue.getSavedValue().equals(paramValueNew) ? " selected=\"true\"" : "") + ">" + allowedValue.getDisplayText() + "</option>");
      }
      out.println("        </select>");
    } else
    {
      out.println("        <input type=\"text\" name=\"" + PARAM_VALUE_NEW + "\" value=\"" + paramValueNew + "\" onkeyup=\"openAgainst2(this)\"/>");
    }
    out.println("      </td>");
    out.println("    </tr>");
    out.println("    <tr" + hiddenAreaClass + " id=\"against2\">");
    out.println("      <th>Because</th>");
    out.println("      <td><textarea rows=\"2\" cols=\"30\" name=\"" + PARAM_COMMENT_TEXT_NEW + "\"/>" + paramCommentTextNew + "</textarea></td>");
    out.println("    </tr>");
    out.println("    <tr>");
    out.println("      <td colspan=\"2\"><span class=\"formButtonFloat\"><input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\""
        + ACTION_UPDATE + "\"/></span></td>");
    out.println("    </tr>");
    out.println("  </table>");
    out.println("</form>");
  }

  public void printRadioOption(String positionStatus, PositionStatus ps, String text, String extraOption)
  {
    out.println("        <input type=\"radio\" name=\"" + PARAM_POSITION_STATUS + "\" value=\"" + ps.getId() + "\""
        + (positionStatus.equals(ps.getId()) ? " checked=\"true\"" : "") + extraOption + "/> " + text + " <br/>");
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
    out.println("  </table>");
    out.println("</div>");
  }

  public void printTopBoxForCommentPosition(AttributeInstance attributeInstance)
  {
    List<AttributeComment> attributeCommentList = AttributeCommentLogic.getAttributeCommentMostRecentList(attributeInstance, dataSession);
    out.println("<div class=\"topBox\">");
    out.println("  <table width=\"100%\">");
    out.println("    <caption>Position Summary</caption>");
    out.println("    <tr>");
    out.println("      <th>Name</th>");
    out.println("      <th>Position</th>");
    out.println("      <th>Comment</th>");
    out.println("    </tr>");
    for (AttributeComment attributeComment : attributeCommentList)
    {
      out.println("    <tr>");
      out.println("      <td>" + attributeComment.getUser().getUserName() + "</td>");
      out.println("      <td>" + attributeComment.getPositionStatus() + "</td>");
      out.println("      <td>" + attributeComment.getCommentText() + "</td>");
      out.println("    </tr>");
    }
    out.println("  </table>");
    out.println("</div>");
  }

}
