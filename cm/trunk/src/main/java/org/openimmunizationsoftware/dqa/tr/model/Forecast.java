package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;
import java.util.Date;

public class Forecast implements Serializable
{
  private int forecastId = 0;
  private ForecastField forecastField = null;
  private TestMessage testMessage = null;
  private String forecastType = "";
  private String scheduleName = "";
  private String seriesName = "";
  private String seriesDoseCount = "";
  private String doseNumber = "";
  private Date dateEarliest = null;
  private Date dateDue = null;
  private Date dateOverdue = null;
  private Date dateLatest = null;
  private String seriesStatus = "";
  private String reasonCode = "";

  public int getForecastId()
  {
    return forecastId;
  }

  public void setForecastId(int forecastId)
  {
    this.forecastId = forecastId;
  }

  public ForecastField getForecastField()
  {
    return forecastField;
  }

  public void setForecastField(ForecastField forecastField)
  {
    this.forecastField = forecastField;
  }

  public TestMessage getTestMessage()
  {
    return testMessage;
  }

  public void setTestMessage(TestMessage testMessage)
  {
    this.testMessage = testMessage;
  }

  public String getForecastType()
  {
    return forecastType;
  }

  public void setForecastType(String forecastType)
  {
    this.forecastType = forecastType;
  }

  public String getScheduleName()
  {
    return scheduleName;
  }

  public void setScheduleName(String scheduleName)
  {
    this.scheduleName = scheduleName;
  }

  public String getSeriesName()
  {
    return seriesName;
  }

  public void setSeriesName(String seriesName)
  {
    this.seriesName = seriesName;
  }

  public String getSeriesDoseCount()
  {
    return seriesDoseCount;
  }

  public void setSeriesDoseCount(String seriesDoseCount)
  {
    this.seriesDoseCount = seriesDoseCount;
  }

  public String getDoseNumber()
  {
    return doseNumber;
  }

  public void setDoseNumber(String doseNumber)
  {
    this.doseNumber = doseNumber;
  }

  public Date getDateEarliest()
  {
    return dateEarliest;
  }

  public void setDateEarliest(Date dateEarliest)
  {
    this.dateEarliest = dateEarliest;
  }

  public Date getDateDue()
  {
    return dateDue;
  }

  public void setDateDue(Date dateDue)
  {
    this.dateDue = dateDue;
  }

  public Date getDateOverdue()
  {
    return dateOverdue;
  }

  public void setDateOverdue(Date dateOverdue)
  {
    this.dateOverdue = dateOverdue;
  }

  public Date getDateLatest()
  {
    return dateLatest;
  }

  public void setDateLatest(Date dateLatest)
  {
    this.dateLatest = dateLatest;
  }

  public String getSeriesStatus()
  {
    return seriesStatus;
  }

  public void setSeriesStatus(String seriesStatus)
  {
    this.seriesStatus = seriesStatus;
  }

  public String getReasonCode()
  {
    return reasonCode;
  }

  public void setReasonCode(String reasonCode)
  {
    this.reasonCode = reasonCode;
  }
}
