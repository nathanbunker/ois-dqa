package org.openimmunizationsoftware.dqa.db.model;

import org.openimmunizationsoftware.dqa.quality.ReportDenominator;
import org.openimmunizationsoftware.dqa.quality.ToolTip;
import org.openimmunizationsoftware.dqa.quality.model.ModelScore;

public class PotentialIssue
{
  public static final String CHANGE_PRIORITY_BLOCKED = "Blocked";
  public static final String CHANGE_PRIORITY_CAN = "Can";
  public static final String CHANGE_PRIORITY_MAY = "May";
  public static final String CHANGE_PRIORITY_MUST = "Must";
  public static final String CHANGE_PRIORITY_SHOULD = "Should";

  public static final String ISSUE_TYPE_EXCEPTION = "exception";
  public static final String ISSUE_TYPE_IS_BEFORE_BIRTH = "is before birth";
  public static final String ISSUE_TYPE_IS_DEPRECATE = "is deprecated";
  public static final String ISSUE_TYPE_IS_IGNORED = "is ignored";
  public static final String ISSUE_TYPE_IS_IN_FUTURE = "is in future";
  public static final String ISSUE_TYPE_IS_INCOMPLETE = "is incomplete";
  public static final String ISSUE_TYPE_IS_INVALID = "is invalid";
  public static final String ISSUE_TYPE_IS_MISSING = "is missing";
  public static final String ISSUE_TYPE_IS_REPEATED = "is repeated";
  public static final String ISSUE_TYPE_IS_UNRECOGNIZED = "is unrecognized";
  public static final String ISSUE_TYPE_IS_VALUED_AS = "is valued as";
  
  private String changePriority = "";
  private IssueAction defaultIssueAction = null;
  private String fieldValue = "";
  private String issueDescription = "";
  private int issueId = 0;
  private String issueType = "";
  private String reportDenominator;
  private String targetField = "";
  private String targetObject = "";
  private ToolTip toolTip = null;

  public ToolTip getToolTip()
  {
    if (toolTip == null)
    {
      toolTip = new ToolTip(getDisplayText(), issueDescription);
    }
    return toolTip;
  }

  public PotentialIssue() {
    // default
  }

  public String getChangePriority()
  {
    return changePriority;
  }

  public IssueAction getDefaultIssueAction()
  {
    return defaultIssueAction;
  }

  public String getDisplayText()
  {
    StringBuilder displayText = new StringBuilder();

    displayText.append(targetObject + " " + targetField + " " + issueType);
    if (fieldValue != null && !fieldValue.equals(""))
    {
      displayText.append(" " + fieldValue);
    }
    return displayText.toString();
  }

  public String getFieldValue()
  {
    return fieldValue;
  }

  public String getIssueDescription()
  {
    return issueDescription;
  }

  public int getIssueId()
  {
    return issueId;
  }

  public String getIssueType()
  {
    return issueType;
  }

  public String getReportDenominator()
  {
    return reportDenominator;
  }

  public String getTargetField()
  {
    return targetField;
  }

  public String getTargetObject()
  {
    return targetObject;
  }

  public void setChangePriority(String changePriority)
  {
    this.changePriority = changePriority;
  }

  public void setDefaultIssueAction(IssueAction defaultIssueAction)
  {
    this.defaultIssueAction = defaultIssueAction;
  }

  public void setFieldValue(String fieldValue)
  {
    this.fieldValue = fieldValue;
  }

  public void setIssueDescription(String issueDescription)
  {
    this.issueDescription = issueDescription;
  }

  public void setIssueId(int issueId)
  {
    this.issueId = issueId;
  }

  public void setIssueType(String issueType)
  {
    this.issueType = issueType;
  }
  
  public void setReportDenominator(String reportDenominator)
  {
    this.reportDenominator = reportDenominator;
  }
  
  public void setTargetField(String targetField)
  {
    this.targetField = targetField;
  }

  public void setTargetObject(String targetObject)
  {
    this.targetObject = targetObject;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof PotentialIssue)
    {
      return issueId == ((PotentialIssue) obj).getIssueId();
    }
    return super.equals(obj);
  }
  
  @Override
  public int hashCode()
  {
    return issueId;
  }
  
}
