package org.openimmunizationsoftware.dqa.db.model;


public class UserAccount
{
  public static final String ACCOUNT_TYPE_ADMIN = "Admin";
  public static final String ACCOUNT_TYPE_SUBMITTER = "Submitter";
  
  private String username = "";
  private String password = "";
  private String accountType = "";
  private Organization organization = null;
  private String email = "";
  private boolean isAuthenticated = false;
  
  public boolean isAuthenticated()
  {
    return isAuthenticated;
  }
  public void setAuthenticated(boolean isAuthenticated)
  {
    this.isAuthenticated = isAuthenticated;
  }
  public String getUsername()
  {
    return username;
  }
  public void setUsername(String username)
  {
    this.username = username;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  public String getAccountType()
  {
    return accountType;
  }
  public void setAccountType(String accountType)
  {
    this.accountType = accountType;
  }
  public Organization getOrganization()
  {
    return organization;
  }
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }
  public String getEmail()
  {
    return email;
  }
  public void setEmail(String email)
  {
    this.email = email;
  }
  
}