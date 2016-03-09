package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ReleaseVersion implements Serializable
{
  private int releaseId = 0;
  private int majorVersionNum = 0;
  private int minorVersionNum = 0;
  private Date startedDate = null;
  private Date releasedDate = null;
  private Date retiredDate = null;
  private ReleaseStatus releaseStatus = null;

  public int getReleaseId()
  {
    return releaseId;
  }

  public void setReleaseId(int releaseId)
  {
    this.releaseId = releaseId;
  }
  
  public String getVersion()
  {
    return majorVersionNum + "." + minorVersionNum;
  }

  public int getMajorVersionNum()
  {
    return majorVersionNum;
  }

  public void setMajorVersionNum(int majorVersionNum)
  {
    this.majorVersionNum = majorVersionNum;
  }

  public int getMinorVersionNum()
  {
    return minorVersionNum;
  }

  public void setMinorVersionNum(int minorVersionNum)
  {
    this.minorVersionNum = minorVersionNum;
  }

  public Date getStartedDate()
  {
    return startedDate;
  }

  public void setStartedDate(Date startedDate)
  {
    this.startedDate = startedDate;
  }

  public Date getReleasedDate()
  {
    return releasedDate;
  }

  public void setReleasedDate(Date releasedDate)
  {
    this.releasedDate = releasedDate;
  }

  public Date getRetiredDate()
  {
    return retiredDate;
  }

  public void setRetiredDate(Date retiredDate)
  {
    this.retiredDate = retiredDate;
  }

  public ReleaseStatus getReleaseStatus()
  {
    return releaseStatus;
  }

  public void setReleaseStatus(ReleaseStatus releaseStatus)
  {
    this.releaseStatus = releaseStatus;
  }

  public String getReleaseStatusString()
  {
    if (releaseStatus == null)
    {
      return null;
    }
    return releaseStatus.getId();
  }

  public void setReleaseStatusString(String releaseStatusString)
  {
    this.releaseStatus = ReleaseStatus.get(releaseStatusString);
  }
}
