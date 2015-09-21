package org.openimmunizationsoftware.pt.model;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * ProjectNextActionType generated by hbm2java
 */
public class ProjectNextActionType implements java.io.Serializable
{
  
  public static String WILL = "D";
  public static String WILL_CONTACT = "C";
  public static String WAITING = "W";
  public static String WILL_RUN_ERRAND = "E";
  public static String COMMITTED_TO = "T";
  public static String ASKS_TO = "A";
  public static String MIGHT = "M";
  public static String GOAL = "G";
  public static String OVERDUE_TO = "O";
  

  private String nextActionType;
  private String nextActionLabel;

  public ProjectNextActionType() {
  }

  public ProjectNextActionType(String nextActionType, String nextActionLabel) {
    this.nextActionType = nextActionType;
    this.nextActionLabel = nextActionLabel;
  }

  public String getNextActionType()
  {
    return this.nextActionType;
  }

  public void setNextActionType(String nextActionType)
  {
    this.nextActionType = nextActionType;
  }

  public String getNextActionLabel()
  {
    return this.nextActionLabel;
  }

  public void setNextActionLabel(String nextActionLabel)
  {
    this.nextActionLabel = nextActionLabel;
  }

}