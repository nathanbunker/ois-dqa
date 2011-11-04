package org.openimmunizationsoftware.dqa.db.model;

public class Application
{
  private int applicationId = 0;
  private String applicationLabel = "";
  private String applicationType = "";
  private Boolean runThis = new Boolean(false);
  
  public Boolean getRunThis()
  {
    return runThis;
  }
  public void setRunThis(Boolean runThis)
  {
    this.runThis = runThis;
  }
  public int getApplicationId()
  {
    return applicationId;
  }
  public void setApplicationId(int applicationId)
  {
    this.applicationId = applicationId;
  }
  public String getApplicationLabel()
  {
    return applicationLabel;
  }
  public void setApplicationLabel(String applicationLabel)
  {
    this.applicationLabel = applicationLabel;
  }
  public String getApplicationType()
  {
    return applicationType;
  }
  public void setApplicationType(String applicationType)
  {
    this.applicationType = applicationType;
  }
  
}
