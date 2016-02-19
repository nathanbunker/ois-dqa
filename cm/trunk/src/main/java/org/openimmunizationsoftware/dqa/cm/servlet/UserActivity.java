package org.openimmunizationsoftware.dqa.cm.servlet;

public class UserActivity implements Comparable<UserActivity>
{
  private long lastWebRequestTimeMillis = 0;
  private String userName = "";
  
  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public long getLastWebRequestTimeMillis()
  {
    return lastWebRequestTimeMillis;
  }

  public void setLastWebRequestTimeMillis(long lastWebRequestTimestamp)
  {
    this.lastWebRequestTimeMillis = lastWebRequestTimestamp;
  }

  @Override
  public int hashCode()
  {
    return userName.hashCode();
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof UserActivity)
    {
      UserActivity ua = (UserActivity) obj;
      return ua.getUserName().equals(this.getUserName());
    }
    return super.equals(obj);
  }

  @Override
  public int compareTo(UserActivity ua)
  {
    if (getLastWebRequestTimeMillis() > ua.getLastWebRequestTimeMillis())
    {
      return -1;
    } else if (getLastWebRequestTimeMillis() < ua.getLastWebRequestTimeMillis())
    {
      return 1;
    }
    return 0;
  }
  
}
