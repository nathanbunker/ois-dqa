package org.openimmunizationsoftware.dqa.db.model;


public class PotentialIssue
{
  public static final String CHANGE_PRIORITY_MUST = "Must";
  public static final String CHANGE_PRIORITY_SHOULD = "Should";
  public static final String CHANGE_PRIORITY_CAN = "Can";
  public static final String CHANGE_PRIORITY_MAY = "May";
  public static final String CHANGE_PRIORITY_BLOCKED = "Blocked";

  public static final String ISSUE_TYPE_EXCEPTION = "exception";
  public static final String ISSUE_TYPE_IS_BEFORE_BIRTH = "is before birth";
  public static final String ISSUE_TYPE_IS_DEPRECATE = "is deprecated";  
  public static final String ISSUE_TYPE_IS_IN_FUTURE = "is in future";
  public static final String ISSUE_TYPE_IS_INCOMPLETE = "is incomplete";
  public static final String ISSUE_TYPE_IS_INVALID = "is invalid";
  public static final String ISSUE_TYPE_IS_IGNORED = "is ignored";
  public static final String ISSUE_TYPE_IS_MISSING = "is missing";
  public static final String ISSUE_TYPE_IS_REPEATED = "is repeated";
  public static final String ISSUE_TYPE_IS_UNRECOGNIZED = "is unrecognized";
  public static final String ISSUE_TYPE_IS_VALUED_AS = "is valued as";
  
  public PotentialIssue()
  {
    //default
  }
  
  
  private int issueId = 0;
  private String targetObject = "";
  private String targetField = "";
  private String issueType = "";
  private String fieldValue = "";
  private IssueAction defaultIssueAction = null;
  private String changePriority = "";
  
  public int getIssueId()
  {
    return issueId;
  }
  public void setIssueId(int issueId)
  {
    this.issueId = issueId;
  }
  public String getTargetObject()
  {
    return targetObject;
  }
  public void setTargetObject(String targetObject)
  {
    this.targetObject = targetObject;
  }
  public String getTargetField()
  {
    return targetField;
  }
  public void setTargetField(String targetField)
  {
    this.targetField = targetField;
  }
  public String getIssueType()
  {
    return issueType;
  }
  public void setIssueType(String issueType)
  {
    this.issueType = issueType;
  }
  public String getFieldValue()
  {
    return fieldValue;
  }
  public void setFieldValue(String fieldValue)
  {
    this.fieldValue = fieldValue;
  }
  public IssueAction getDefaultIssueAction()
  {
    return defaultIssueAction;
  }
  public void setDefaultIssueAction(IssueAction defaultIssueAction)
  {
    this.defaultIssueAction = defaultIssueAction;
  }
  public String getChangePriority()
  {
    return changePriority;
  }
  public void setChangePriority(String changePriority)
  {
    this.changePriority = changePriority;
  }
  
  public String getDisplayText()
  {
    if (fieldValue == null || fieldValue.equals(""))
    {
      return targetObject + " " + targetField + " " + issueType;
    }
    return  targetObject + " " + targetField + " " + issueType + " " + fieldValue; 
  }
}
