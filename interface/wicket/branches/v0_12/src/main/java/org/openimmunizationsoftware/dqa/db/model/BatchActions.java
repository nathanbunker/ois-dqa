package org.openimmunizationsoftware.dqa.db.model;

/**
 * Batch Actions represent an count of the number of actions that 
 * were encountered while processing a batch of messages.
 * @author nathan
 *
 */
public class BatchActions
{
  private int batchActionsId = 0;
  private MessageBatch messageBatch = null;
  private IssueAction issueAction = null;
  private int actionCount = 0;

  public BatchActions() {
    // default
  }
  
  public void inc(BatchActions batchActions)
  {
    this.actionCount += batchActions.getActionCount();
  }

  public BatchActions(IssueAction issueAction, MessageBatch messageBatch) {
    this.messageBatch = messageBatch;
    this.issueAction = issueAction;
  }

  public int getBatchActionsId()
  {
    return batchActionsId;
  }

  public void setBatchActionsId(int batchActionsId)
  {
    this.batchActionsId = batchActionsId;
  }

  public MessageBatch getMessageBatch()
  {
    return messageBatch;
  }

  public void setMessageBatch(MessageBatch messageBatch)
  {
    this.messageBatch = messageBatch;
  }

  public IssueAction getIssueAction()
  {
    return issueAction;
  }

  public void setIssueAction(IssueAction issueAction)
  {
    this.issueAction = issueAction;
  }

  public int getActionCount()
  {
    return actionCount;
  }

  public void incActionCount()
  {
    actionCount++;
  }

  public void setActionCount(int actionCount)
  {
    this.actionCount = actionCount;
  }
}
