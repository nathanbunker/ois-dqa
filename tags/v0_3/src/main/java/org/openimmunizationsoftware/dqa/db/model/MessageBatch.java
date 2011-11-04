package org.openimmunizationsoftware.dqa.db.model;

import java.util.Date;

public class MessageBatch
{
  private int batchId = 0;
  private String batchTitle = "";
  private BatchType batchType = null;
  private Date startDate = null;
  private Date endDate = null;
  private SubmitStatus submitStatus = null;
  
  public int getBatchId()
  {
    return batchId;
  }
  public void setBatchId(int batchId)
  {
    this.batchId = batchId;
  }
  public String getBatchTitle()
  {
    return batchTitle;
  }
  public void setBatchTitle(String batchTitle)
  {
    this.batchTitle = batchTitle;
  }
  public BatchType getBatchType()
  {
    return batchType;
  }
  public void setBatchType(BatchType batchType)
  {
    this.batchType = batchType;
  }
  public Date getStartDate()
  {
    return startDate;
  }
  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }
  public Date getEndDate()
  {
    return endDate;
  }
  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }
  public SubmitStatus getSubmitStatus()
  {
    return submitStatus;
  }
  public void setSubmitStatus(SubmitStatus submitStatus)
  {
    this.submitStatus = submitStatus;
  }
}
