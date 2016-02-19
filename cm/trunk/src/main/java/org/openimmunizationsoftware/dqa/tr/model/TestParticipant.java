package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;
import java.util.Map;

import org.openimmunizationsoftware.dqa.cm.model.ReportUser;
import org.openimmunizationsoftware.dqa.cm.model.UserType;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;

public class TestParticipant implements Serializable
{

  private int testParticipantId = 0;
  private String organizationName = "";
  private String connectionLabel = "";
  private int mapRow = 0;
  private int mapCol = 0;
  private String platformLabel = "";
  private String vendorLabel = "";
  private String internalComments = "";
  private String phase1Participation = "";
  private String phase1Status = "";
  private String phase1Comments = "";
  private String phase2Participation = "";
  private String phase2Status = "";
  private String phase2Comments = "";
  private String ihsStatus = "";
  private String guideStatus = "";
  private String guideName = "";
  private String connectStatus = "";
  private String generalComments = "";
  private String transportType = "";
  private String querySupport = "";
  private String nistStatus = "";
  private String accessPasscode = "";
  private String publicIdCode = "";
  private Map<String, String> filterValueMap = null;
  private String reportRunStatus = "";
  private ProfileUsage profileUsage = null;

  public String getPublicIdCode()
  {
    return publicIdCode;
  }

  public void setPublicIdCode(String publicIdCode)
  {
    this.publicIdCode = publicIdCode;
  }

  public ProfileUsage getProfileUsage()
  {
    return profileUsage;
  }

  public void setProfileUsage(ProfileUsage profileUsage)
  {
    this.profileUsage = profileUsage;
  }

  public String getReportRunStatus()
  {
    return reportRunStatus;
  }

  public void setReportRunStatus(String reportRunStatus)
  {
    this.reportRunStatus = reportRunStatus;
  }

  public Map<String, String> getFilterValueMap()
  {
    return filterValueMap;
  }

  public void setFilterValueMap(Map<String, String> filterValueMap)
  {
    this.filterValueMap = filterValueMap;
  }

  public int getTestParticipantId()
  {
    return testParticipantId;
  }

  public void setTestParticipantId(int testParticipantId)
  {
    this.testParticipantId = testParticipantId;
  }

  public String getOrganizationName()
  {
    return organizationName;
  }

  public void setOrganizationName(String organizationName)
  {
    this.organizationName = organizationName;
  }

  public String getConnectionLabel()
  {
    return connectionLabel;
  }

  public void setConnectionLabel(String connectionLabel)
  {
    this.connectionLabel = connectionLabel;
  }

  public int getMapRow()
  {
    return mapRow;
  }

  public void setMapRow(int mapRow)
  {
    this.mapRow = mapRow;
  }

  public int getMapCol()
  {
    return mapCol;
  }

  public void setMapCol(int mapCol)
  {
    this.mapCol = mapCol;
  }

  public String getPlatformLabel()
  {
    return platformLabel;
  }

  public void setPlatformLabel(String platformLabel)
  {
    this.platformLabel = platformLabel;
  }

  public String getVendorLabel()
  {
    return vendorLabel;
  }

  public void setVendorLabel(String vendorLabel)
  {
    this.vendorLabel = vendorLabel;
  }

  public String getInternalComments()
  {
    return internalComments;
  }

  public void setInternalComments(String internalComments)
  {
    this.internalComments = internalComments;
  }

  public String getPhase1Participation()
  {
    return phase1Participation;
  }

  public void setPhase1Participation(String phase1Participation)
  {
    this.phase1Participation = phase1Participation;
  }

  public String getPhase1Status()
  {
    return phase1Status;
  }

  public void setPhase1Status(String phase1Status)
  {
    this.phase1Status = phase1Status;
  }

  public String getPhase1Comments()
  {
    return phase1Comments;
  }

  public void setPhase1Comments(String phase1Comments)
  {
    this.phase1Comments = phase1Comments;
  }

  public String getPhase2Participation()
  {
    return phase2Participation;
  }

  public void setPhase2Participation(String phase2Participation)
  {
    this.phase2Participation = phase2Participation;
  }

  public String getPhase2Status()
  {
    return phase2Status;
  }

  public void setPhase2Status(String phase2Status)
  {
    this.phase2Status = phase2Status;
  }

  public String getPhase2Comments()
  {
    return phase2Comments;
  }

  public void setPhase2Comments(String phase2Comments)
  {
    this.phase2Comments = phase2Comments;
  }

  public String getIhsStatus()
  {
    return ihsStatus;
  }

  public void setIhsStatus(String ihsStatus)
  {
    this.ihsStatus = ihsStatus;
  }

  public String getGuideStatus()
  {
    return guideStatus;
  }

  public void setGuideStatus(String guideStatus)
  {
    this.guideStatus = guideStatus;
  }

  public String getGuideName()
  {
    return guideName;
  }

  public void setGuideName(String guideName)
  {
    this.guideName = guideName;
  }

  public String getConnectStatus()
  {
    return connectStatus;
  }

  public void setConnectStatus(String connectStatus)
  {
    this.connectStatus = connectStatus;
  }

  public String getGeneralComments()
  {
    return generalComments;
  }

  public void setGeneralComments(String generalComments)
  {
    this.generalComments = generalComments;
  }

  public String getTransportType()
  {
    return transportType;
  }

  public void setTransportType(String transportType)
  {
    this.transportType = transportType;
  }

  public String getQuerySupport()
  {
    return querySupport;
  }

  public void setQuerySupport(String querySupport)
  {
    this.querySupport = querySupport;
  }

  public String getNistStatus()
  {
    return nistStatus;
  }

  public void setNistStatus(String nistStatus)
  {
    this.nistStatus = nistStatus;
  }

  public String getAccessPasscode()
  {
    return accessPasscode;
  }

  public void setAccessPasscode(String accessPasscode)
  {
    this.accessPasscode = accessPasscode;
  }

  @Override
  public int hashCode()
  {
    return getTestParticipantId();
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof TestParticipant)
    {
      TestParticipant otherTp = (TestParticipant) obj;
      return otherTp.getTestParticipantId() == this.getTestParticipantId();
    }
    return super.equals(obj);
  }

  public String getConnectionLabel(UserSession userSession)
  {
    boolean canViewConnectionLabel = canViewConnectionLabel(userSession);
    if (canViewConnectionLabel)
    {
      return getConnectionLabel();
    }
    if (publicIdCode == null || publicIdCode.equals(""))
    {
      return "Immunization Information System";
    }
    return "Immunization Information System " + publicIdCode;
  }

  public boolean canViewConnectionLabel(UserSession userSession)
  {
    if (userSession == null || userSession.getUser() == null)
    {
      return false;
    }
    if (userSession == null || userSession.getUser() == null || userSession.getUser().getApplicationUser().getUserType() == UserType.ADMIN)
    {
      return true;
    }
    ReportUser reportUser = userSession.getUser().getReportUserMap().get(this);
    if (reportUser != null && (reportUser.getReportRole() == UserType.ADMIN || reportUser.getReportRole() == UserType.EXPERT))
    {
      return true;
    }
    return false;
  }

}
