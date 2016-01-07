package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;

public class User implements Serializable {
  private int userId = 0;
  private String userName = "";
  private String password = "";
  private String emailAddress = "";
  private ApplicationUser applicationUser = null;
  private List<ApplicationUser> applicationUserList = null;
  private Map<String, UserSetting> userSettingMap = new HashMap<String, UserSetting>();
  private Map<TestParticipant, ReportUser> reportUserMap = new HashMap<TestParticipant, ReportUser>();
  
  public Map<TestParticipant, ReportUser> getReportUserMap()
  {
    return reportUserMap;
  }

  public Map<String, UserSetting> getUserSettingMap()
  {
    return userSettingMap;
  }

  public boolean isSystemUser()
  {
    return userId == 2 || userId == 3 || userId == 4;
  }

  public List<ApplicationUser> getApplicationUserList() {
    return applicationUserList;
  }

  public void setApplicationUserList(List<ApplicationUser> applicationUserList) {
    this.applicationUserList = applicationUserList;
  }

  public ApplicationUser getApplicationUser() {
    return applicationUser;
  }

  public void setApplicationUser(ApplicationUser applicationUser) {
    this.applicationUser = applicationUser;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

}
