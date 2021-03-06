package org.openimmunizationsoftware.dqa.db.model;

public class ReceiveQueue
{
  private int receiveQueueId = 0;
  private MessageBatch messageBatch = null;
  private MessageReceived messageReceived = null;
  private SubmitStatus submitStatus = null;
  
  public int getReceiveQueueId()
  {
    return receiveQueueId;
  }
  public void setReceiveQueueId(int receiveQueueId)
  {
    this.receiveQueueId = receiveQueueId;
  }
  public MessageBatch getMessageBatch()
  {
    return messageBatch;
  }
  public void setMessageBatch(MessageBatch messageBatch)
  {
    this.messageBatch = messageBatch;
  }
  public MessageReceived getMessageReceived()
  {
    return messageReceived;
  }
  public void setMessageReceived(MessageReceived messageReceived)
  {
    this.messageReceived = messageReceived;
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
