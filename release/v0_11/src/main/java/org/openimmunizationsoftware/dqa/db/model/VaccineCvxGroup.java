package org.openimmunizationsoftware.dqa.db.model;

public class VaccineCvxGroup
{
  private int cvxGroupId = 0;
  private VaccineCvx vaccineCvx = null;
  private VaccineGroup vaccineGroup = null;

  public int getCvxGroupId()
  {
    return cvxGroupId;
  }

  public VaccineCvx getVaccineCvx()
  {
    return vaccineCvx;
  }

  public VaccineGroup getVaccineGroup()
  {
    return vaccineGroup;
  }

  public void setCvxGroupId(int cvxGroupId)
  {
    this.cvxGroupId = cvxGroupId;
  }

  public void setVaccineCvx(VaccineCvx vaccineCvx)
  {
    this.vaccineCvx = vaccineCvx;
  }

  public void setVaccineGroup(VaccineGroup vaccineGroup)
  {
    this.vaccineGroup = vaccineGroup;
  }
}
