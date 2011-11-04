package org.openimmunizationsoftware.dqa.db.model;

/**
 * An application represents a single instance of the DQA running in production, test or development
 * in some location. This concept is used to manage the configuration differences between installed
 * applications. This allows any installed software to act as if it were a different application 
 * depending on the values below. This makes development, testing and deployment much easier. 
 * @author nathan
 *
 */
public class Application
{
  private int applicationId = 0;
  /**
   * The display name of the application
   */
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
  /**
   * @return Unique id for application, primary key in database
   */
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
