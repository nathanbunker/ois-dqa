package org.openimmunizationsoftware.dqa.db.model;

import java.util.Date;

public class VaccineMvx
{
  private String mvxCode = "";
  private String mvxLabel = "";
  private Date validStartDate = null;
  private Date useStartDate = null;
  private Date useEndDate = null;
  private Date validEndDate = null;
  
  public String getMvxCode()
  {
    return mvxCode;
  }
  public void setMvxCode(String mvxCode)
  {
    this.mvxCode = mvxCode;
  }
  public String getMvxLabel()
  {
    return mvxLabel;
  }
  public void setMvxLabel(String mvxLabel)
  {
    this.mvxLabel = mvxLabel;
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

  }
