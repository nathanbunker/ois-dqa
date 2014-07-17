package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

import org.hibernate.Session;

public class User implements Serializable
{
  private int userId = 0;
  private String userName = "";
  private String password = "";
  private String emailAddress = "";
  private UserType userType = null;
  
  
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

  public UserType getUserType()
  {
    return userType;
  }

  public void setUserType(UserType userType)
  {
    this.userType = userType;
  }

  public String getUserTypeString()
  {
    if (userType == null)
    {
      return null;
    }
    return userType.getId();
  }

  public void setUserTypeString(String userTypeString)
  {
    this.userType = UserType.get(userTypeString);
  }

}
