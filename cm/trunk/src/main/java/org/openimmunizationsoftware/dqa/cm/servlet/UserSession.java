package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.Application;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;

public class UserSession implements Serializable
{
  private User user = null;
  private ReleaseVersion releaseVersion = null;
  private CodeTableInstance codeTableInstance = null;
  protected List<CodeInstance> searchResultsWithCodeInstanceList = null;
  private String searchCodeValue = "";
  private String searchCodeLabel = "";

  private transient Session dataSession = null;
  private transient PrintWriter out = null;
  private transient String messageError = null;
  private transient String messageConfirmation = null;
  private transient ProfileUsage profileUsageSelected = null;
  private transient UserActivity userActivity = new UserActivity();
  private transient UserSearchOptions userSearchOptions = null;

  public UserSearchOptions getUserSearchOptions()
  {
    if (userSearchOptions == null)
    {
      userSearchOptions = new UserSearchOptions();
    }
    return userSearchOptions;
  }

  public UserActivity getUserActivity()
  {
    return userActivity;
  }

  public ProfileUsage getProfileUsageSelected()
  {
    return profileUsageSelected;
  }

  public void setProfileUsageSelected(ProfileUsage profileUsageSelected)
  {
    this.profileUsageSelected = profileUsageSelected;
  }

  public void setOut(PrintWriter out)
  {
    this.out = out;
  }

  public String getMessageError()
  {
    return messageError;
  }

  public void setMessageError(String messageError)
  {
    this.messageError = messageError;
  }

  public String getMessageConfirmation()
  {
    return messageConfirmation;
  }

  public void setMessageConfirmation(String messageConfirmation)
  {
    this.messageConfirmation = messageConfirmation;
  }

  public PrintWriter getOut()
  {
    return out;
  }

  protected boolean isAdmin()
  {
    return user != null && user.getApplicationUser() != null && user.getApplicationUser().getUserType() == UserType.ADMIN;
  }

  protected boolean isLoggedIn()
  {
    return user != null;
  }

  public String getSearchCodeValue()
  {
    return searchCodeValue;
  }

  public void setSearchCodeValue(String searchCodeValue)
  {
    this.searchCodeValue = searchCodeValue;
  }

  public String getSearchCodeLabel()
  {
    return searchCodeLabel;
  }

  public void setSearchCodeLabel(String searchCodeLabel)
  {
    this.searchCodeLabel = searchCodeLabel;
  }

  public List<CodeInstance> getSearchResultsWithCodeInstanceList()
  {
    return searchResultsWithCodeInstanceList;
  }

  public void setSearchResultsWithCodeInstanceList(List<CodeInstance> searchResultsWithCodeInstanceList)
  {
    this.searchResultsWithCodeInstanceList = searchResultsWithCodeInstanceList;
  }

  public CodeTableInstance getCodeTableInstance()
  {
    return codeTableInstance;
  }

  public void setCodeTableInstance(CodeTableInstance codeTableInstance)
  {
    this.codeTableInstance = codeTableInstance;
  }

  public void setReleaseVersion(ReleaseVersion releaseVersion)
  {
    this.releaseVersion = releaseVersion;
  }

  public User getUser()
  {
    return user;
  }

  public boolean canEdit()
  {
    if (user == null || user.getApplicationUser() == null)
    {
      return false;
    }
    if (releaseVersion.getReleaseStatus() == ReleaseStatus.PROPOSED)
    {
      if (user.getApplicationUser().getUserType() == UserType.ADMIN || user.getApplicationUser().getUserType() == UserType.EXPERT)
      {
        return true;
      }
    }
    return false;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public ReleaseVersion getReleaseVersion()
  {
    return releaseVersion;
  }

  public org.hibernate.Session getDataSession()
  {

    return dataSession;
  }

  public void setDataSession(Session dataSession)
  {
    this.dataSession = dataSession;
  }

}
