package org.openimmunizationsoftware.dqa.validate;

import java.util.List;

import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.IssueFound;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.received.NextOfKin;
import org.openimmunizationsoftware.dqa.db.model.received.Patient;
import org.openimmunizationsoftware.dqa.db.model.received.Vaccination;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;


public abstract class ValidateMessage
{

  protected MessageReceived message = null;
  protected SubmitterProfile profile = null;
  protected Patient patient = null;
  protected Vaccination vaccination = null;
  protected NextOfKin nextOfKin = null;
  protected PotentialIssues pi = PotentialIssues.getPotentialIssues();
  protected int positionId = 0;
  protected List<IssueFound> issuesFound = null;

  protected ValidateMessage(SubmitterProfile profile) {
    this.profile = profile;
  }

  protected void registerError(PotentialIssue potentialIssue)
  {
    registerIssue(potentialIssue, IssueAction.ERROR);
  }

  protected void registerIssue(PotentialIssue potentialIssue)
  {
    if (potentialIssue != null)
    {
      registerIssue(potentialIssue, profile.getPotentialIssueStatus(potentialIssue).getAction(), null);
    }
  }
  protected void registerIssue(PotentialIssue potentialIssue, CodeReceived codeReceived)
    {
    if (potentialIssue != null)
    {
      registerIssue(potentialIssue, profile.getPotentialIssueStatus(potentialIssue).getAction(), codeReceived);
    }
  }

  protected void registerIssue(PotentialIssue potentialIssue, IssueAction issueAction)
  {    
    registerIssue(potentialIssue, issueAction, null);
  }
  
  protected void registerIssue(PotentialIssue potentialIssue, IssueAction issueAction, CodeReceived codeReceived)
  {
    if (potentialIssue != null)
    {
      IssueFound issueFound = new IssueFound();
      issueFound.setMessageReceived(message);
      issueFound.setPositionId(positionId);
      issueFound.setIssue(potentialIssue);
      issueFound.setIssueAction(issueAction);
      issueFound.setCodeReceived(codeReceived);
      issuesFound.add(issueFound);
    }
  }
  
  

}
