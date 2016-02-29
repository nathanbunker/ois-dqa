package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;

public class User implements Serializable
{

  public static final String USER_SETTING_EMAIL_MANAGER_REPLY = "emailManager.reply";
  public static final String USER_SETTING_EMAIL_MANAGER_SMTPS_PASSWORD = "emailManager.smtpsPassword";
  public static final String USER_SETTING_EMAIL_MANAGER_SMTPS_PORT = "emailManager.smtpsPort";
  public static final String USER_SETTING_EMAIL_MANAGER_SMTPS_HOST = "emailManager.smtpsHost";
  public static final String USER_SETTING_EMAIL_MANAGER_SMTPS_USERNAME = "emailManager.smtpsUsername";
  public static final String USER_SETTING_EMAIL_MANAGER_USE_SMTPS = "emailManager.useSmtps";
  public static final String USER_SETTING_EMAIL_MANAGER_ADDRESS = "emailManager.address";

  private int userId = 0;
  private String userName = "";
  private String password = "";
  private String emailAddress = "";
  private ApplicationUser applicationUser = null;
  private List<ApplicationUser> applicationUserList = null;
  private Map<String, UserSetting> userSettingMap = new HashMap<String, UserSetting>();
  private Map<TestParticipant, ReportUser> reportUserMap = new HashMap<TestParticipant, ReportUser>();
  private boolean resetPassword = false;
  private String organization = "";
  private String positionTitle = "";
  private String phoneNumber = "";
  private String adminComments = "";
  private MemberType memberType = null;

  public String getMemberTypeString()
  {
    return memberType == null ? null : memberType.getId();
  }

  public void setMemberTypeString(String memberTypeString)
  {
    this.memberType = MemberType.get(memberTypeString);
  }

  public MemberType getMemberType()
  {
    return memberType;
  }

  public void setMemberType(MemberType memberType)
  {
    this.memberType = memberType;
  }

  public String getOrganization()
  {
    return organization;
  }

  public void setOrganization(String organization)
  {
    this.organization = organization;
  }

  public String getPositionTitle()
  {
    return positionTitle;
  }

  public void setPositionTitle(String positionTitle)
  {
    this.positionTitle = positionTitle;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public String getAdminComments()
  {
    return adminComments;
  }

  public void setAdminComments(String adminComments)
  {
    this.adminComments = adminComments;
  }

  public boolean isResetPassword()
  {
    return resetPassword;
  }

  public void setResetPassword(boolean resetPassword)
  {
    this.resetPassword = resetPassword;
  }

  public Map<TestParticipant, ReportUser> getReportUserMap()
  {
    return reportUserMap;
  }

  public Map<String, UserSetting> getUserSettingMap()
  {
    return userSettingMap;
  }

  public String getUserSetting(String key, String defaultValue)
  {
    if (userSettingMap == null)
    {
      return defaultValue;
    }
    UserSetting value = userSettingMap.get(key);
    if (value == null)
    {
      return defaultValue;
    }
    return value.getSettingValue();
  }

  public int getUserSetting(String key, int defaultValue)
  {
    String value = getUserSetting(key,"");
    if (value.equals(""))
    {
      return defaultValue;
    }
    try
    {
      return Integer.parseInt(value);
    } catch (NumberFormatException nfe)
    {
      return defaultValue;
    }
  }

  public boolean getUserSetting(String key, boolean defaultValue)
  {
    String value = getUserSetting(key, "N");
    if (value.equals(""))
    {
      return defaultValue;
    }
    return value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("YES");
  }

  public boolean isSystemUser()
  {
    return userId == 2 || userId == 3 || userId == 4;
  }

  public List<ApplicationUser> getApplicationUserList()
  {
    return applicationUserList;
  }

  public void setApplicationUserList(List<ApplicationUser> applicationUserList)
  {
    this.applicationUserList = applicationUserList;
  }

  public ApplicationUser getApplicationUser()
  {
    return applicationUser;
  }

  public void setApplicationUser(ApplicationUser applicationUser)
  {
    this.applicationUser = applicationUser;
  }

  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress;
  }

}
