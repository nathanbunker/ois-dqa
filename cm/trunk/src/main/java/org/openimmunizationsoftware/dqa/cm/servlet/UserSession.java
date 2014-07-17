package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.cm.CentralControl;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

public class UserSession implements Serializable
{
  private User user = null;
  private transient Session dataSession = null;
  private ReleaseVersion releaseVersion = null;
  private CodeTableInstance codeTableInstance = null;
  protected List<CodeInstance> searchResultsWithCodeInstanceList = null;
  private String searchCodeValue = "";
  private String searchCodeLabel = "";

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
    if (user == null)
    {
      return false;
    }
    if (releaseVersion.getReleaseStatus() == ReleaseStatus.PROPOSED)
    {
      if (user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.EXPERT)
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
