package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class TransportAnalysis implements Serializable
{
  private int transportAnalysisId = 0;
  private String connectionLabel = "";
  private String transportType = "";
  private String analysisNotes = "";
  private String reportComplete = "";
  
  public int getTransportAnalysisId()
  {
    return transportAnalysisId;
  }
  public void setTransportAnalysisId(int transportAnalysisId)
  {
    this.transportAnalysisId = transportAnalysisId;
  }
  public String getConnectionLabel()
  {
    return connectionLabel;
  }
  public void setConnectionLabel(String connectionLabel)
  {
    this.connectionLabel = connectionLabel;
  }
  public String getTransportType()
  {
    return transportType;
  }
  public void setTransportType(String transportType)
  {
    this.transportType = transportType;
  }
  public String getAnalysisNotes()
  {
    return analysisNotes;
  }
  public void setAnalysisNotes(String analysisNotes)
  {
    this.analysisNotes = analysisNotes;
  }
  public String getReportComplete()
  {
    return reportComplete;
  }
  public void setReportComplete(String reportComplete)
  {
    this.reportComplete = reportComplete;
  }
}
