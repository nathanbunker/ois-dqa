package org.openimmunizationsoftware.dqa.db.model;

import java.util.Date;

public class BatchReport
{
  private static Date max(Date d1, Date d2)
  {
    if (d1 == null)
    {
      return d2;
    } else if (d2 == null)
    {
      return d1;
    } else if (d1.after(d2))
    {
      return d1;
    }
    return d2;
  }
  private static Date min(Date d1, Date d2)
  {
    if (d1 == null)
    {
      return d2;
    } else if (d2 == null)
    {
      return d1;
    } else if (d1.before(d2))
    {
      return d1;
    }
    return d2;
  }
  private int completenessPatientScore = 0;
  private int completenessScore = 0;
  private int completenessVaccinationScore = 0;
  private int completenessVaccineGroupScore = 0;
  private MessageBatch messageBatch = null;
  private int messageCount = 0;
  private int messageWithAdminCount = 0;
  private int nextOfKinCount = 0;
  private int overallScore = 0;
  private int patientCount = 0;
  private int patientUnderageCount = 0;
  private int qualityErrorScore = 0;
  private int qualityScore = 0;
  private int qualityWarnScore = 0;
  private int batchReportId = 0;
  private double timelinessAverage = 0.0;
  private int timelinessCount2Days = 0;
  private int timelinessCount30Days = 0;
  private int timelinessCount7Days = 0;
  private Date timelinessDateFirst = null;
  private Date timelinessDateLast = null;
  private int timelinessScore = 0;
  private int timelinessScore2Days = 0;
  private int timelinessScore30Days = 0;
  private int timelinessScore7Days = 0;
  private int vaccinationAdministeredCount = 0;
  private int vaccinationDeleteCount = 0;
  private int vaccinationHistoricalCount = 0;

  private int vaccinationNotAdministeredCount = 0;

  public void addToCounts(BatchReport report)
  {
    // private Map<IssueAction, BatchActions> batchActionsMap = new
    // HashMap<IssueAction, BatchActions>();
    // private Map<PotentialIssue, BatchIssues> batchIssuesMap = new
    // HashMap<PotentialIssue, BatchIssues>();

    messageCount += report.getMessageCount();
    messageWithAdminCount += report.getMessageWithAdminCount();
    nextOfKinCount += report.getNextOfKinCount();
    patientCount += report.getPatientCount();
    patientUnderageCount += report.getPatientUnderageCount();
    double a1 = this.getMessageWithAdminCount();
    double a2 = report.getMessageWithAdminCount();
    double s1 = this.getTimelinessAverage();
    double s2 = report.getTimelinessAverage();
    if ((a1 + a2) == 0)
    {
      timelinessAverage = 0.0;
    } else
    {
      timelinessAverage = (a1 * s1 + s2 * a2) / (a1 + a2);
    }
    timelinessCount2Days += report.getTimelinessCount2Days();
    timelinessCount30Days += report.getTimelinessCount30Days();
    timelinessCount7Days += report.getTimelinessCount7Days();
    timelinessDateFirst = min(timelinessDateFirst, report.getTimelinessDateFirst());
    timelinessDateLast = max(timelinessDateLast, report.getTimelinessDateLast());
    vaccinationAdministeredCount += report.getVaccinationAdministeredCount();
    vaccinationDeleteCount += report.getVaccinationDeleteCount();
    vaccinationHistoricalCount += report.getVaccinationHistoricalCount();
    vaccinationNotAdministeredCount += report.getVaccinationNotAdministeredCount();
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

  public int getCompletenessVaccineGroupScore()
  {
    return completenessVaccineGroupScore;
  }

  public MessageBatch getMessageBatch()
  {
    return messageBatch;
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

  public int getQualityErrorScore()
  {
    return qualityErrorScore;
  }

  public int getQualityScore()
  {
    return qualityScore;
  }

  public int getQualityWarnScore()
  {
    return qualityWarnScore;
  }

  public int getBatchReportId()
  {
    return batchReportId;
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

  public int getTimelinessScore2Days()
  {
    return timelinessScore2Days;
  }

  public int getTimelinessScore30Days()
  {
    return timelinessScore30Days;
  }

  public int getTimelinessScore7Days()
  {
    return timelinessScore7Days;
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

  public void setCompletenessVaccineGroupScore(int completenessVaccineGroupScore)
  {
    this.completenessVaccineGroupScore = completenessVaccineGroupScore;
  }

  public void setMessageBatch(MessageBatch messageBatch)
  {
    this.messageBatch = messageBatch;
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

  public void setQualityErrorScore(int qualityErrorScore)
  {
    this.qualityErrorScore = qualityErrorScore;
  }

  public void setQualityScore(int qualityScore)
  {
    this.qualityScore = qualityScore;
  }

  public void setQualityWarnScore(int qualityWarnScore)
  {
    this.qualityWarnScore = qualityWarnScore;
  }

  public void setBatchReportId(int reportId)
  {
    this.batchReportId = reportId;
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

  public void setTimelinessScore2Days(int timelinessScore2Days)
  {
    this.timelinessScore2Days = timelinessScore2Days;
  }

  public void setTimelinessScore30Days(int timelinessScore30Days)
  {
    this.timelinessScore30Days = timelinessScore30Days;
  }

  public void setTimelinessScore7Days(int timelinessScore7Days)
  {
    this.timelinessScore7Days = timelinessScore7Days;
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
