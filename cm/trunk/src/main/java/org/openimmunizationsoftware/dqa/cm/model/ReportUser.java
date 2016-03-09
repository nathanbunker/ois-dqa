package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.Date;

import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;

@SuppressWarnings("serial")
public class ReportUser implements Serializable
{
  private int reportUserId = 0;
  private User user = null;
  private TestParticipant testParticipant = null;
  private User authorizedByUser = null;
  private Date authorizedDate = null;
  private UserType reportRole  = null;
  
  public String getReportRoleString() {
    if (reportRole == null) {
      return null;
    }
    return reportRole.getId();
  }

  public void setReportRoleString(String reportRoleString) {
    this.reportRole = UserType.get(reportRoleString);
  }
  
  public int getReportUserId()
  {
    return reportUserId;
  }
  public void setReportUserId(int reportUserId)
  {
    this.reportUserId = reportUserId;
  }
  public User getUser()
  {
    return user;
  }
  public void setUser(User user)
  {
    this.user = user;
  }
  public TestParticipant getTestParticipant()
  {
    return testParticipant;
  }
  public void setTestParticipant(TestParticipant testParticipant)
  {
    this.testParticipant = testParticipant;
  }
  public User getAuthorizedByUser()
  {
    return authorizedByUser;
  }
  public void setAuthorizedByUser(User authorizedByUser)
  {
    this.authorizedByUser = authorizedByUser;
  }
  public Date getAuthorizedDate()
  {
    return authorizedDate;
  }
  public void setAuthorizedDate(Date authorizedDate)
  {
    this.authorizedDate = authorizedDate;
  }
  public UserType getReportRole()
  {
    return reportRole;
  }
  public void setReportRole(UserType reportRole)
  {
    this.reportRole = reportRole;
  }
}
