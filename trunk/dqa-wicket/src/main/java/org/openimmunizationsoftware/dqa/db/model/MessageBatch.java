package org.openimmunizationsoftware.dqa.db.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessageBatch
{
  private Map<IssueAction, BatchActions> batchActionsMap = new HashMap<IssueAction, BatchActions>();
  private int batchId = 0;
  private Map<PotentialIssue, BatchIssues> batchIssuesMap = new HashMap<PotentialIssue, BatchIssues>();
  private String batchTitle = "";
  private BatchType batchType = null;
  private int completenessPatientScore = 0;
  private int completenessScore = 0;
  private int completenessVaccinationScore = 0;
  private Date endDate = null;
  private int messageCount = 0;
  private int messageWithAdminCount = 0;
  private int nextOfKinCount = 0;
  private int overallScore = 0;
  private int patientCount = 0;
  private int patientUnderageCount = 0;
  private int qualityScore = 0;
  private Date startDate = null;
  private SubmitStatus submitStatus = null;
  private double timelinessAverage = 0.0;
  private int timelinessCount2Days = 0;
  private int timelinessCount30Days = 0;
  private int timelinessCount7Days = 0;
  private Date timelinessDateFirst = null;
  private Date timelinessDateLast = null;
  private int timelinessScore = 0;
  private int vaccinationAdministeredCount = 0;
  private int vaccinationDeleteCount = 0;
  private int vaccinationHistoricalCount = 0;
  private int vaccinationNotAdministeredCount = 0;

  public BatchActions getBatchActions(IssueAction issueAction)
  {
    BatchActions batchActions = batchActionsMap.get(issueAction);
    if (batchActions == null)
    {
      batchActions = new BatchActions(issueAction, this);
      batchActionsMap.put(issueAction, batchActions);
    }
    return batchActions;
  }

  public Map<IssueAction, BatchActions> getBatchActionsMap()
  {
    return batchActionsMap;
  }

  public int getBatchId()
  {
    return batchId;
  }

  public BatchIssues getBatchIssues(PotentialIssue potentialIssue)
  {
    BatchIssues batchIssues = batchIssuesMap.get(potentialIssue);
    if (batchIssues == null)
    {
      batchIssues = new BatchIssues(potentialIssue, this);
      batchIssuesMap.put(potentialIssue, batchIssues);
    }
    return batchIssues;
  }

  public Map<PotentialIssue, BatchIssues> getBatchIssuesMap()
  {
    return batchIssuesMap;
  }

  public String getBatchTitle()
  {
    return batchTitle;
  }

  public BatchType getBatchType()
  {
    return batchType;
  }

  public int getCompletenessPatientScore()
  {
    return completenessPatientScore;
  }

  public int getCompletenessScore()
  {
    return completenessScore;
  }

  public int getCompletenessVaccinationScore()
  {
    return completenessVaccinationScore;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public int getMessageCount()
  {
    return messageCount;
  }

  public int getMessageWithAdminCount()
  {
    return messageWithAdminCount;
  }

  public int getNextOfKinCount()
  {
    return nextOfKinCount;
  }

  public int getOverallScore()
  {
    return overallScore;
  }

  public int getPatientCount()
  {
    return patientCount;
  }

  public int getPatientUnderageCount()
  {
    return patientUnderageCount;
  }

  public int getQualityScore()
  {
    return qualityScore;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public SubmitStatus getSubmitStatus()
  {
    return submitStatus;
  }

  public double getTimelinessAverage()
  {
    return timelinessAverage;
  }

  public int getTimelinessCount2Days()
  {
    return timelinessCount2Days;
  }

  public int getTimelinessCount30Days()
  {
    return timelinessCount30Days;
  }

  public int getTimelinessCount7Days()
  {
    return timelinessCount7Days;
  }

  public Date getTimelinessDateFirst()
  {
    return timelinessDateFirst;
  }

  public Date getTimelinessDateLast()
  {
    return timelinessDateLast;
  }

  public int getTimelinessScore()
  {
    return timelinessScore;
  }

  public int getVaccinationAdministeredCount()
  {
    return vaccinationAdministeredCount;
  }

  public int getVaccinationCount()
  {
    return vaccinationAdministeredCount + vaccinationHistoricalCount + vaccinationDeleteCount
        + vaccinationNotAdministeredCount;
  }

  public int getVaccinationDeleteCount()
  {
    return vaccinationDeleteCount;
  }

  public int getVaccinationHistoricalCount()
  {
    return vaccinationHistoricalCount;
  }

  public int getVaccinationNotAdministeredCount()
  {
    return vaccinationNotAdministeredCount;
  }

  public void incBatchActionCount(IssueAction issueAction)
  {
    getBatchActions(issueAction).incActionCount();
  }

  public void incBatchIssueCount(PotentialIssue potentialIssue)
  {
    BatchIssues batchIssues = getBatchIssues(potentialIssue);
    batchIssues.incIssueCountPos();
  }

  public void incMessageCount()
  {
    this.messageCount++;
  }

  public void incMessageWithAdminCount()
  {
    this.messageWithAdminCount++;
  }

  public void incNextOfKinCount()
  {
    this.nextOfKinCount++;
  }

  public void incNextOfKinCount(int amount)
  {
    this.nextOfKinCount += amount;
  }

  public void incPatientCount()
  {
    this.patientCount++;
  }

  public void incPatientUnderageCount()
  {
    this.patientUnderageCount++;
  }

  public void incTimelinessCount2Days()
  {
    this.timelinessCount2Days++;
  }

  public void incTimelinessCount30Days()
  {
    this.timelinessCount30Days++;
  }

  public void incTimelinessCount7Days()
  {
    this.timelinessCount7Days++;
  }

  public void incVaccinationAdministeredCount()
  {
    this.vaccinationAdministeredCount++;
  }

  public void incVaccinationDeleteCount()
  {
    this.vaccinationDeleteCount++;
  }

  public void incVaccinationHistoricalCount()
  {
    vaccinationHistoricalCount++;
  }

  public void incVaccinationNotAdministeredCount()
  {
    this.vaccinationNotAdministeredCount++;
  }

  public void setBatchActions(BatchActions batchActions)
  {
    this.batchActionsMap.put(batchActions.getIssueAction(), batchActions);
  }

  public void setBatchId(int batchId)
  {
    this.batchId = batchId;
  }

  public void setBatchTitle(String batchTitle)
  {
    this.batchTitle = batchTitle;
  }

  public void setBatchType(BatchType batchType)
  {
    this.batchType = batchType;
  }

  public void setCompletenessPatientScore(int completenessPatientScore)
  {
    this.completenessPatientScore = completenessPatientScore;
  }

  public void setCompletenessScore(int completenessScore)
  {
    this.completenessScore = completenessScore;
  }

  public void setCompletenessVaccinationScore(int completenessVaccinationScore)
  {
    this.completenessVaccinationScore = completenessVaccinationScore;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }

  public void setMessageCount(int messageCount)
  {
    this.messageCount = messageCount;
  }

  public void setMessageWithAdminCount(int messageWithAdminCount)
  {
    this.messageWithAdminCount = messageWithAdminCount;
  }

  public void setNextOfKinCount(int nextOfKinCount)
  {
    this.nextOfKinCount = nextOfKinCount;
  }

  public void setOverallScore(int overallScore)
  {
    this.overallScore = overallScore;
  }

  public void setPatientCount(int patientCount)
  {
    this.patientCount = patientCount;
  }

  public void setPatientUnderageCount(int patientUnderageCount)
  {
    this.patientUnderageCount = patientUnderageCount;
  }

  public void setQualityScore(int qualityScore)
  {
    this.qualityScore = qualityScore;
  }

  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }

  public void setSubmitStatus(SubmitStatus submitStatus)
  {
    this.submitStatus = submitStatus;
  }

  public void setTimelinessAverage(double timelinessAverage)
  {
    this.timelinessAverage = timelinessAverage;
  }

  public void setTimelinessCount2Days(int timelinessCount2Days)
  {
    this.timelinessCount2Days = timelinessCount2Days;
  }

  public void setTimelinessCount30Days(int timelinessCount30Days)
  {
    this.timelinessCount30Days = timelinessCount30Days;
  }

  public void setTimelinessCount7Days(int timelinessCount7Days)
  {
    this.timelinessCount7Days = timelinessCount7Days;
  }

  public void setTimelinessDateFirst(Date timelinessDateFirst)
  {
    this.timelinessDateFirst = timelinessDateFirst;
  }

  public void setTimelinessDateLast(Date timelinessDateLast)
  {
    this.timelinessDateLast = timelinessDateLast;
  }

  public void setTimelinessScore(int timelinessScore)
  {
    this.timelinessScore = timelinessScore;
  }

  public void setVaccinationAdministeredCount(int vaccinationAdministeredCount)
  {
    this.vaccinationAdministeredCount = vaccinationAdministeredCount;
  }

  public void setVaccinationDeleteCount(int vaccinationDeleteCount)
  {
    this.vaccinationDeleteCount = vaccinationDeleteCount;
  }

  public void setVaccinationHistoricalCount(int vaccinationHistoricalCount)
  {
    this.vaccinationHistoricalCount = vaccinationHistoricalCount;
  }

  public void setVaccinationNotAdministeredCount(int vaccinationNotAdministeredCount)
  {
    this.vaccinationNotAdministeredCount = vaccinationNotAdministeredCount;
  }

}
