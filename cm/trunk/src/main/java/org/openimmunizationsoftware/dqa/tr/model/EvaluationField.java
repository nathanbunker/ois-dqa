package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class EvaluationField implements Serializable
{
  private int evaluationFieldId = 0;
  private String componentCode = "";
  private String vaccineCode = "";
  private String vaccineDate = "";

  public int getEvaluationFieldId()
  {
    return evaluationFieldId;
  }

  public void setEvaluationFieldId(int evaluationFieldId)
  {
    this.evaluationFieldId = evaluationFieldId;
  }

  public String getComponentCode()
  {
    return componentCode;
  }

  public void setComponentCode(String componentCode)
  {
    this.componentCode = componentCode;
  }

  public String getVaccineCode()
  {
    return vaccineCode;
  }

  public void setVaccineCode(String vaccineCode)
  {
    this.vaccineCode = vaccineCode;
  }

  public String getVaccineDate()
  {
    return vaccineDate;
  }

  public void setVaccineDate(String vaccineDate)
  {
    this.vaccineDate = vaccineDate;
  }

}
