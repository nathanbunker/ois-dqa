package org.openimmunizationsoftware.dqa.cm.servlet;

import java.util.HashMap;
import java.util.Map;

import org.openimmunizationsoftware.dqa.cm.model.Application;
import org.openimmunizationsoftware.dqa.cm.model.MemberType;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

public class UserSearchOptions
{

  private String userName = "";
  private String emailAddress = "";
  private String organization = "";
  private MemberType memberType = null;
  private Map<Application, UserType> applicationUserTypeMap = new HashMap<Application, UserType>();
  
  public boolean isSearchOptionsSet()
  {
    return !userName.equals("") || !emailAddress.equals("") || !organization.equals("") || memberType != null || applicationUserTypeMap.size() > 0;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public void setEmailAddress(String email)
  {
    this.emailAddress = email;
  }

  public String getOrganization()
  {
    return organization;
  }

  public void setOrganization(String organization)
  {
    this.organization = organization;
  }

  public MemberType getMemberType()
  {
    return memberType;
  }

  public void setMemberType(MemberType memberType)
  {
    this.memberType = memberType;
  }

  public Map<Application, UserType> getApplicationUserTypeMap()
  {
    return applicationUserTypeMap;
  }

}
