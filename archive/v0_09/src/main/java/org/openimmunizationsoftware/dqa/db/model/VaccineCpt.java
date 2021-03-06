package org.openimmunizationsoftware.dqa.db.model;

import java.util.Date;

public class VaccineCpt
{
  private int cptId = 0;
  private String cptCode = "";
  private String cptLabel = "";
  private Date validStartDate = null;
  private Date useStartDate = null;
  private Date useEndDate = null;
  private Date validEndDate = null;
  private VaccineCvx cvx = null;
  
  public int getCptId()
  {
    return cptId;
  }
  public void setCptId(int cptId)
  {
    this.cptId = cptId;
  }
  public String getCptCode()
  {
    return cptCode;
  }
  public void setCptCode(String cptCode)
  {
    this.cptCode = cptCode;
  }
  public String getCptLabel()
  {
    return cptLabel;
  }
  public void setCptLabel(String cptLabel)
  {
    this.cptLabel = cptLabel;
  }
  public Date getValidStartDate()
  {
    return validStartDate;
  }
  public void setValidStartDate(Date validStartDate)
  {
    this.validStartDate = validStartDate;
  }
  public Date getUseStartDate()
  {
    return useStartDate;
  }
  public void setUseStartDate(Date useStartDate)
  {
    this.useStartDate = useStartDate;
  }
  public Date getUseEndDate()
  {
    return useEndDate;
  }
  public void setUseEndDate(Date useEndDate)
  {
    this.useEndDate = useEndDate;
  }
  public Date getValidEndDate()
  {
    return validEndDate;
  }
  public void setValidEndDate(Date validEndDate)
  {
    this.validEndDate = validEndDate;
  }
  public VaccineCvx getCvx()
  {
    return cvx;
  }
  public void setCvx(VaccineCvx cvx)
  {
    this.cvx = cvx;
  }
}
