package org.openimmunizationsoftware.dqa.model;

import org.hibernate.annotations.Entity;

@Entity
public class DqaOrganization
{
  private int orgId = 0;
  private String orgLabel = "";
  private int orgParentId = 0;
  private int primaryProfileId = 0;
  
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
  public int getOrgParentId()
  {
    return orgParentId;
  }
  public void setOrgParentId(int orgParentId)
  {
    this.orgParentId = orgParentId;
  }
  public int getPrimaryProfileId()
  {
    return primaryProfileId;
  }
  public void setPrimaryProfileId(int primaryProfileId)
  {
    this.primaryProfileId = primaryProfileId;
  }
  
}
