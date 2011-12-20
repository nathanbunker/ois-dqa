package org.openimmunizationsoftware.dqa.db.model;

public class ReportType
{
  private int reportTypeId = 0;
  private String reportTypeLabel = "";

  public int getReportTypeId()
  {
    return reportTypeId;
  }

  public void setReportTypeId(int reportTypeId)
  {
    this.reportTypeId = reportTypeId;
  }

  public String getReportTypeLabel()
  {
    return reportTypeLabel;
  }

  public void setReportTypeLabel(String reportTypeLabel)
  {
    this.reportTypeLabel = reportTypeLabel;
  }
}
