package org.openimmunizationsoftware.dqa.db.model;

import java.util.HashMap;


import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.manager.CodesReceived;

public class SubmitterProfile
{
  public static final String PROFILE_STATUS_SETUP = "Setup";
  public static final String PROFILE_STATUS_TEST = "Test";
  public static final String PROFILE_STATUS_PROD = "Prod";
  public static final String PROFILE_STATUS_HOLD = "Hold";
  public static final String PROFILE_STATUS_CLOSED = "Closed";
  
  public static final String DATA_FORMAT_HL7V2 = "HL7v2";
  
  public static final String TRANSFER_PRIORITY_NORMAL = "Normal";
  public static final String TRANSFER_PRIORITY_HIGH = "High";
  public static final String TRANSFER_PRIORITY_HIGHEST = "Highest";
  public static final String TRANSFER_PRIORITY_LOW = "Low";
  public static final String TRANSFER_PRIORITY_LOWEST = "Lowest";
  
  private int profileId = 0;
  private String profileLabel = "";
  private String profileStatus = "";
  private Organization organization = null;
  private String dataFormat = "";
  private String transferPriority = "";
  private String accessKey = "";
  private HashMap<PotentialIssue, PotentialIssueStatus> potentialIssueStatusMap = null;
  private CodesReceived codesReceived = null;
  
  public CodesReceived getCodesReceived(Session session)
  {
    if (codesReceived == null)
    {
      codesReceived = CodesReceived.getCodesReceived(this, session);
    }
    return codesReceived;
  }
  
  public HashMap<PotentialIssue, PotentialIssueStatus> getPotentialIssueStatusMap()
  {
    return potentialIssueStatusMap;
  }
  public void setPotentialIssueStatusMap(HashMap<PotentialIssue, PotentialIssueStatus> potentialIssueStatusMap)
  {
    this.potentialIssueStatusMap = potentialIssueStatusMap;
  }
  
  public PotentialIssueStatus getPotentialIssueStatus(PotentialIssue potentialIssue)
  {
    if (potentialIssueStatusMap == null)
    {
      potentialIssueStatusMap = new HashMap<PotentialIssue, PotentialIssueStatus>();
    }
    PotentialIssueStatus potentialIssueStatus = potentialIssueStatusMap.get(potentialIssue);
    if (potentialIssueStatus == null)
    {
      potentialIssueStatus = new PotentialIssueStatus(potentialIssue, this);
      potentialIssueStatusMap.put(potentialIssue, potentialIssueStatus);
    }
    return potentialIssueStatus;
  }
  
  public int getProfileId()
  {
    return profileId;
  }
  public void setProfileId(int profileId)
  {
    this.profileId = profileId;
  }
  public String getProfileLabel()
  {
    return profileLabel;
  }
  public void setProfileLabel(String profileLabel)
  {
    this.profileLabel = profileLabel;
  }
  public String getProfileStatus()
  {
    return profileStatus;
  }
  public void setProfileStatus(String profileStatus)
  {
    this.profileStatus = profileStatus;
  }
  public Organization getOrganization()
  {
    return organization;
  }
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }
  public String getDataFormat()
  {
    return dataFormat;
  }
  public void setDataFormat(String dataFormat)
  {
    this.dataFormat = dataFormat;
  }
  public String getTransferPriority()
  {
    return transferPriority;
  }
  public void setTransferPriority(String transferPriority)
  {
    this.transferPriority = transferPriority;
  }
  public String getAccessKey()
  {
    return accessKey;
  }
  public void setAccessKey(String accessKey)
  {
    this.accessKey = accessKey;
  }
}
