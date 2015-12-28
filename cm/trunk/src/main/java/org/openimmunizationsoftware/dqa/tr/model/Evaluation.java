package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class Evaluation implements Serializable
{
  private int evaluationdId = 0;
  private EvaluationField evaluationField = null;
  private TestMessage testMessage = null;
  private String evaluationType = "";
  private String scheduleName = "";
  private String doseNumber = "";
  private String doseValidity = "";
  private String seriesName = "";
  private String seriesDoseCount = "";
  private String seriesStatus = "";
  private String reasonCode = "";

  public int getEvaluationdId()
  {
    return evaluationdId;
  }

  public void setEvaluationdId(int evaluationdId)
  {
    this.evaluationdId = evaluationdId;
  }

  public EvaluationField getEvaluationField()
  {
    return evaluationField;
  }

  public void setEvaluationField(EvaluationField evaluationField)
  {
    this.evaluationField = evaluationField;
  }

  public TestMessage getTestMessage()
  {
    return testMessage;
  }

  public void setTestMessage(TestMessage testMessage)
  {
    this.testMessage = testMessage;
  }

  public String getEvaluationType()
  {
    return evaluationType;
  }

  public void setEvaluationType(String evaluationType)
  {
    this.evaluationType = evaluationType;
  }

  public String getScheduleName()
  {
    return scheduleName;
  }

  public void setScheduleName(String scheduleName)
  {
    this.scheduleName = scheduleName;
  }

  public String getDoseNumber()
  {
    return doseNumber;
  }

  public void setDoseNumber(String doseNumber)
  {
    this.doseNumber = doseNumber;
  }

  public String getDoseValidity()
  {
    return doseValidity;
  }

  public void setDoseValidity(String doseValidity)
  {
    this.doseValidity = doseValidity;
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
