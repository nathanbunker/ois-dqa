package org.openimmunizationsoftware.dqa.db.model;

public class BatchCodeReceived
{
  private int batchCodeReceivedId = 0;
  private MessageBatch messageBatch = null;
  private CodeReceived codeReceived = null;
  private int receivedCount = 0;

  public BatchCodeReceived() {
    // default
  }

  public BatchCodeReceived(CodeReceived codeReceived, MessageBatch messageBatch) {
    this.codeReceived = codeReceived;
    this.messageBatch = messageBatch;
  }

  public int getBatchCodeReceivedId()
  {
    return batchCodeReceivedId;
  }

  public void setBatchCodeReceivedId(int batchCodeReceivedId)
  {
    this.batchCodeReceivedId = batchCodeReceivedId;
  }

  public MessageBatch getMessageBatch()
  {
    return messageBatch;
  }

  public void setMessageBatch(MessageBatch messageBatch)
  {
    this.messageBatch = messageBatch;
  }

  public CodeReceived getCodeReceived()
  {
    return codeReceived;
  }

  public void setCodeReceived(CodeReceived codeReceived)
  {
    this.codeReceived = codeReceived;
  }

  public int getReceivedCount()
  {
    return receivedCount;
  }

  public void setReceivedCount(int receivedCount)
  {
    this.receivedCount = receivedCount;
  }

  public void inc(BatchCodeReceived batchCodeReceived)
  {
    this.receivedCount += batchCodeReceived.getReceivedCount();
  }

  public void incReceivedCount()
  {
    this.receivedCount++;
  }
}
