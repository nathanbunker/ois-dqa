package org.openimmunizationsoftware.dqa.db.model;

public class BatchVaccineCvx
{
  private int batchVaccineCvxId = 0;
  private MessageBatch messageBatch = null;
  private VaccineCvx vaccineCvx = null;
  private int receivedCount = 0;
  
  public BatchVaccineCvx()
  {
    // defaults
  }
  
  public void inc(BatchVaccineCvx batchVaccineCvx)
  {
    this.receivedCount += batchVaccineCvx.getReceivedCount();
  }
  
  public void incReceivedCount()
  {
    receivedCount++;
  }
  
  public BatchVaccineCvx(VaccineCvx vaccineCvx, MessageBatch messageBatch)
  {
    this.vaccineCvx = vaccineCvx;
    this.messageBatch = messageBatch;
  }
  
  public int getBatchVaccineCvxId()
  {
    return batchVaccineCvxId;
  }
  public void setBatchVaccineCvxId(int batchVaccineCvxId)
  {
    this.batchVaccineCvxId = batchVaccineCvxId;
  }
  public MessageBatch getMessageBatch()
  {
    return messageBatch;
  }
  public void setMessageBatch(MessageBatch messageBatch)
  {
    this.messageBatch = messageBatch;
  }
  public VaccineCvx getVaccineCvx()
  {
    return vaccineCvx;
  }
  public void setVaccineCvx(VaccineCvx vaccineCvx)
  {
    this.vaccineCvx = vaccineCvx;
  }
  public int getReceivedCount()
  {
    return receivedCount;
  }
  public void setReceivedCount(int receivedCount)
  {
    this.receivedCount = receivedCount;
  }

}