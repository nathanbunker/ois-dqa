package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public class UserSetting implements Serializable
{
  private int userSettingId = 0;
  private User user = null;
  private String settingKey = "";
  private String settingValue = "";

  public int getUserSettingId()
  {
    return userSettingId;
  }

  public void setUserSettingId(int userSettingId)
  {
    this.userSettingId = userSettingId;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public String getSettingKey()
  {
    return settingKey;
  }

  public void setSettingKey(String settingKey)
  {
    this.settingKey = settingKey;
  }

  public String getSettingValue()
  {
    return settingValue;
  }

  public void setSettingValue(String settingValue)
  {
    this.settingValue = settingValue;
  }
}
