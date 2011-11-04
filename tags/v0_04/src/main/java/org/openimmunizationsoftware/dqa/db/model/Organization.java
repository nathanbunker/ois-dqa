package org.openimmunizationsoftware.dqa.db.model;


public class Organization
{
  private int orgId = 0;
  private String orgLabel = "";
  private Organization parentOrganization = null;
  private SubmitterProfile primaryProfile = null;
  
  public int getOrgId()
  {
    return orgId;
  }
  public void setOrgId(int orgId)
  {
    this.orgId = orgId;
  }
  public String getOrgLabel()
  {
    return orgLabel;
  }
  public void setOrgLabel(String orgLabel)
  {
    this.orgLabel = orgLabel;
  }
  public Organization getParentOrganization()
  {
    return parentOrganization;
  }
  public void setParentOrganization(Organization parentOrganization)
  {
    this.parentOrganization = parentOrganization;
  }
  public SubmitterProfile getPrimaryProfile()
  {
    return primaryProfile;
  }
  public void setPrimaryProfile(SubmitterProfile primaryProfile)
  {
    this.primaryProfile = primaryProfile;
  }
}
