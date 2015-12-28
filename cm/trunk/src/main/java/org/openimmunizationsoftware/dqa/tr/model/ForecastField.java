package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class ForecastField implements Serializable
{
  private int forecastFieldId = 0;
  private String vaccineCode = "";
  public int getForecastFieldId()
  {
    return forecastFieldId;
  }
  public void setForecastFieldId(int forecastFieldId)
  {
    this.forecastFieldId = forecastFieldId;
  }
  public String getVaccineCode()
  {
    return vaccineCode;
  }
  public void setVaccineCode(String vaccineCode)
  {
    this.vaccineCode = vaccineCode;
  }
}
