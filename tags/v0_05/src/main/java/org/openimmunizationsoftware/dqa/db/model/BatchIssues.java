package org.openimmunizationsoftware.dqa.db.model;

public class BatchIssues
{
  private int batchIssuesId = 0;
  private MessageBatch messageBatch = null;
  private PotentialIssue issue = null;
  private int issueCountPos = 0;
  private int issueCountNeg = 0;
  
  public BatchIssues()
  {
    // default
  }
  
  public void inc(BatchIssues batchIssues)
  {
    this.issueCountPos += batchIssues.getIssueCountPos();
    this.issueCountNeg += batchIssues.getIssueCountNeg();
  }
  
  public BatchIssues(PotentialIssue issue, MessageBatch messageBatch)
  {
    this.issue = issue;
    this.messageBatch = messageBatch;
  }
  
  public int getBatchIssuesId()
  {
    return batchIssuesId;
  }
  public void setBatchIssuesId(int batchIssuesId)
  {
    this.batchIssuesId = batchIssuesId;
  }
  public MessageBatch getMessageBatch()
  {
    return messageBatch;
  }
  public void setMessageBatch(MessageBatch messageBatch)
  {
    this.messageBatch = messageBatch;
  }
  public PotentialIssue getIssue()
  {
    return issue;
  }
  public void setIssue(PotentialIssue issue)
  {
    this.issue = issue;
  }
  public int getIssueCountPos()
  {
    return issueCountPos;
  }
  public void incIssueCountPos()
  {
    issueCountPos++;
  }
  public void setIssueCountPos(int issueCountPos)
  {
    this.issueCountPos = issueCountPos;
  }
  public int getIssueCountNeg()
  {
    return issueCountNeg;
  }
  public void setIssueCountNeg(int issueCountNeg)
  {
    this.issueCountNeg = issueCountNeg;
  }
}
