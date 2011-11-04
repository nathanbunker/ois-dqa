package org.openimmunizationsoftware.dqa.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openimmunizationsoftware.dqa.db.model.received.NextOfKin;
import org.openimmunizationsoftware.dqa.db.model.received.Patient;
import org.openimmunizationsoftware.dqa.db.model.received.Vaccination;


public class MessageReceived
{
  private long receivedId = 0l;
  private SubmitterProfile profile = null;
  private Date receivedDate = new Date();
  private String requestText = null;
  private String responseText = null;
  private IssueAction issueAction = null;
  private SubmitStatus submitStatus = null;
  private List<NextOfKin> nextOfKins = new ArrayList<NextOfKin>();
  private Patient patient = new Patient();
  private List<Vaccination> vaccinations = new ArrayList<Vaccination>();
  private List<IssueFound> issuesFound = new ArrayList<IssueFound>();
  
  public List<IssueFound> getIssuesFound()
  {
    return issuesFound;
  }
  
  public boolean hasErrors()
  {
    for (IssueFound issueFound : issuesFound)
    {
      if (issueFound.getIssueAction().equals(IssueAction.ERROR))
      {
        return true;
      }
    }
    return false;
  }
  
  public List<NextOfKin> getNextOfKins()
  {
    return nextOfKins;
  }
  
  public void setNextOfKins(List<NextOfKin> nextOfKins)
  {
    this.nextOfKins = nextOfKins;
  }
  public Patient getPatient()
  {
    return patient;
  }
  public void setPatient(Patient patient)
  {
    this.patient = patient;
  }
  public List<Vaccination> getVaccinations()
  {
    return vaccinations;
  }
  public void setVaccinations(List<Vaccination> vaccinations)
  {
    this.vaccinations = vaccinations;
  }
  public long getReceivedId()
  {
    return receivedId;
  }
  public void setReceivedId(long receivedId)
  {
    this.receivedId = receivedId;
  }
  public SubmitterProfile getProfile()
  {
    return profile;
  }
  public void setProfile(SubmitterProfile profile)
  {
    this.profile = profile;
  }
  public Date getReceivedDate()
  {
    return receivedDate;
  }
  public void setReceivedDate(Date receivedDate)
  {
    this.receivedDate = receivedDate;
  }
  public String getRequestText()
  {
    return requestText;
  }
  public void setRequestText(String requestText)
  {
    this.requestText = requestText;
  }
  public String getResponseText()
  {
    return responseText;
  }
  public void setResponseText(String responseText)
  {
    this.responseText = responseText;
  }
  public IssueAction getIssueAction()
  {
    return issueAction;
  }
  public void setIssueAction(IssueAction issueAction)
  {
    this.issueAction = issueAction;
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
