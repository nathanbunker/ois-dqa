package org.openimmunizationsoftware.dqa.db.model;

public class ReportVaccineGroup
{
  private int reportVaccineGroupId = 0;
  private VaccineGroup vaccineGroup = null;
  private String groupStatus = "";
  private SubmitterProfile profile = null;

  public SubmitterProfile getProfile()
  {
    return profile;
  }

  public void setProfile(SubmitterProfile profile)
  {
    this.profile = profile;
  }

  public int getReportVaccineGroupId()
  {
    return reportVaccineGroupId;
  }

  public void setReportVaccineGroupId(int reportVaccineGroupId)
  {
    this.reportVaccineGroupId = reportVaccineGroupId;
  }

  public VaccineGroup getVaccineGroup()
  {
    return vaccineGroup;
  }

  public void setVaccineGroup(VaccineGroup vaccineGroup)
  {
    this.vaccineGroup = vaccineGroup;
  }

  public String getGroupStatus()
  {
    return groupStatus;
  }

  public void setGroupStatus(String groupStatus)
  {
    this.groupStatus = groupStatus;
  }
}
