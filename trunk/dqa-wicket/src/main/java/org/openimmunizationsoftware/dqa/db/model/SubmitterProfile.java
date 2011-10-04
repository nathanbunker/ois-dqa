package org.openimmunizationsoftware.dqa.db.model;

import java.util.HashMap;
import java.util.Random;


import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.manager.CodesReceived;

public class SubmitterProfile
{
  public static final int MASTER_HL7 = 1;
  public static final int TEST_HL7 = 101;
  
  public static final String DATA_FORMAT_HL7V2 = "HL7v2";

  public static final String PROFILE_STATUS_CLOSED = "Closed";
  public static final String PROFILE_STATUS_HOLD = "Hold";
  public static final String PROFILE_STATUS_PROD = "Prod";
  public static final String PROFILE_STATUS_SETUP = "Setup";
  public static final String PROFILE_STATUS_TEMPLATE = "Template";
  public static final String PROFILE_STATUS_VALIDATE = "Validate";
  public static final String PROFILE_STATUS_TEST = "Test";
  
  public static final String TRANSFER_PRIORITY_HIGH = "High";
  public static final String TRANSFER_PRIORITY_HIGHEST = "Highest";
  public static final String TRANSFER_PRIORITY_LOW = "Low";
  public static final String TRANSFER_PRIORITY_LOWEST = "Lowest";
  public static final String TRANSFER_PRIORITY_NORMAL = "Normal";
  
  private String accessKey = "";
  private CodesReceived codesReceived = null;
  private String dataFormat = "";
  private Organization organization = null;
  private HashMap<PotentialIssue, PotentialIssueStatus> potentialIssueStatusMap = null;
  private String profileCode = "";
  private int profileId = 0;
  private String profileLabel = "";
  private String profileStatus = "";
  private String transferPriority = "";
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof SubmitterProfile)
    {
      return ((SubmitterProfile) obj).getProfileId() == profileId; 
    }
    return super.equals(obj);
  }

  public String getAccessKey()
  {
    return accessKey;
  }

  public CodesReceived getCodesReceived(Session session)
  {
    if (codesReceived == null)
    {
      codesReceived = CodesReceived.getCodesReceived(this, session);
    }
    return codesReceived;
  }
  
  public void saveCodesReceived(Session session)
  {
    if (codesReceived != null)
    {
      codesReceived.saveCodesReceived(session);
    }
  }
  
  public void registerCodeReceived(CodeReceived codeReceived, Session session)
  {
    getCodesReceived(session).registerCodeReceived(codeReceived);
  }
  
  public String getDataFormat()
  {
    return dataFormat;
  }
  public Organization getOrganization()
  {
    return organization;
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
  
  public HashMap<PotentialIssue, PotentialIssueStatus> getPotentialIssueStatusMap()
  {
    return potentialIssueStatusMap;
  }
  public String getProfileCode()
  {
    return profileCode;
  }
  public int getProfileId()
  {
    return profileId;
  }
  public String getProfileLabel()
  {
    return profileLabel;
  }
  public String getProfileStatus()
  {
    return profileStatus;
  }
  public String getTransferPriority()
  {
    return transferPriority;
  }
  @Override
  public int hashCode()
  {
    return profileId;
  }
  public boolean isProfileStatusClosed()
  {
    return PROFILE_STATUS_CLOSED.equals(profileStatus);
  }
  public boolean isProfileStatusHold()
  {
    return PROFILE_STATUS_HOLD.equals(profileStatus);
  }
  public boolean isProfileStatusProd()
  {
    return PROFILE_STATUS_PROD.equals(profileStatus);
  }
  public boolean isProfileStatusSetup()
  {
    return PROFILE_STATUS_SETUP.equals(profileStatus);
  }
  public boolean isProfileStatusTemplate()
  {
    return PROFILE_STATUS_TEMPLATE.equals(profileStatus);
  }
  public boolean isProfileStatusTest()
  {
    return PROFILE_STATUS_TEST.equals(profileStatus);
  }
  public void setAccessKey(String accessKey)
  {
    this.accessKey = accessKey;
  }
  
  public void setDataFormat(String dataFormat)
  {
    this.dataFormat = dataFormat;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }
  
  public void setPotentialIssueStatusMap(HashMap<PotentialIssue, PotentialIssueStatus> potentialIssueStatusMap)
  {
    this.potentialIssueStatusMap = potentialIssueStatusMap;
  }
  
  public void setProfileCode(String profileCode)
  {
    this.profileCode = profileCode;
  }
  
  public void setProfileId(int profileId)
  {
    this.profileId = profileId;
  }
  
  public void setProfileLabel(String profileLabel)
  {
    this.profileLabel = profileLabel;
  }
  
  public void setProfileStatus(String profileStatus)
  {
    this.profileStatus = profileStatus;
  }
  
  public void setTransferPriority(String transferPriority)
  {
    this.transferPriority = transferPriority;
  }

  private static Random random;
  
  private static char[] randomCharacters = "ABCDEFGHJKMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789".toCharArray();
  
  public void generateAccessKey()
  {
    if (random == null)
    {
      random = new Random();
    }
    accessKey = "";
    for (int i = 0; i < 16; i++)
    {
      int pos = random.nextInt(randomCharacters.length);
      accessKey += randomCharacters[pos];
    }
  }
}
