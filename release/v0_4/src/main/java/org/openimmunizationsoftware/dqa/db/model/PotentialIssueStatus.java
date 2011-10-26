package org.openimmunizationsoftware.dqa.db.model;

public class PotentialIssueStatus
{
  private int potentialIssueStatusId = 0;
  private PotentialIssue issue = null;
  private SubmitterProfile profile = null;
  private IssueAction action = null;
  private int expectMin = 0;
  private int expectMax = 100;
  
  public PotentialIssueStatus()
  {
    // default
  }
  
  public PotentialIssueStatus(PotentialIssue potentialIssue, SubmitterProfile profile)
  {
    issue = potentialIssue;
    this.profile = profile;
    action = issue.getDefaultIssueAction();
    expectMin = 0;
    expectMax = 100;
  }

  public int getPotentialIssueStatusId()
  {
    return potentialIssueStatusId;
  }

  public void setPotentialIssueStatusId(int potentialIssueStatusId)
  {
    this.potentialIssueStatusId = potentialIssueStatusId;
  }

  public PotentialIssue getIssue()
  {
    return issue;
  }

  public void setIssue(PotentialIssue issue)
  {
    this.issue = issue;
  }

  public SubmitterProfile getProfile()
  {
    return profile;
  }

  public void setProfile(SubmitterProfile profile)
  {
    this.profile = profile;
  }

  public IssueAction getAction()
  {
    return action;
  }

  public void setAction(IssueAction action)
  {
    this.action = action;
  }

  public int getExpectMin()
  {
    return expectMin;
  }

  public void setExpectMin(int expectMin)
  {
    this.expectMin = expectMin;
  }

  public int getExpectMax()
  {
    return expectMax;
  }

  public void setExpectMax(int expectMax)
  {
    this.expectMax = expectMax;
  }

}