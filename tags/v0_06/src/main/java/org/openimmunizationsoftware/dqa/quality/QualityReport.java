package org.openimmunizationsoftware.dqa.quality;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openimmunizationsoftware.dqa.db.model.BatchIssues;
import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.VaccineCvx;
import org.openimmunizationsoftware.dqa.db.model.VaccineGroup;
import org.openimmunizationsoftware.dqa.manager.VaccineGroupManager;

public class QualityReport
{
  private static final boolean SCORE_TIMELINESS_2_DAYS = false;
  
  private static final ToolTip RECEIVED_PATIENTS = new ToolTip("Patients",
      "The number of patients sent, one per message").setEmphasize(true);
  private static final ToolTip RECEIVED_NEXT_OF_KINS = new ToolTip("Next-of-Kins",
      "The number of responsible and associate parties sent").setEmphasize(true);
  private static final ToolTip RECEIVED_VACCINATIONS = new ToolTip("Vaccinations", "The number of vaccinations sent")
      .setEmphasize(true).setLink("completeness.vaccinations");
  private static final ToolTip RECEIVED_VACCINATIONS_ADMININISTERED = new ToolTip("Administered",
      "Number of vaccinations indicated as administered", true).setLink("completeness.vaccineGroup");
  private static final ToolTip RECEIVED_VACCINATIONS_HISTORICAL = new ToolTip("Historical",
      "Number of vaccinations not indicated as administered", true);
  private static final ToolTip RECEIVED_VACCINATIONS_DELETED = new ToolTip("Deleted",
      "Number of vaccinations indicated as deleted", true);
  private static final ToolTip RECEIVED_VACCINATIONS_NOT_ADMINISTERED = new ToolTip("Not Administered",
      "Number of vaccinations that are indicated as not administered.", true);
  private static final ToolTip STATUS_ACCEPTED = new ToolTip("Accepted",
      "Number of messages that were accepted without warnings or errors");
  private static final ToolTip STATUS_ERRORED = new ToolTip("Rejected with Errors",
      "Number of messages that were rejected because of errors").setLink("quality.errors");
  private static final ToolTip STATUS_WARNED = new ToolTip("Accepted with Warnings",
      "Number of messages that were accepted but had warnings").setLink("quality.warnings");
  private static final ToolTip STATUS_SKIPPED = new ToolTip("Skipped",
      "Number of messages that are accepted without errors but will not be processed").setLink("quality.skips");

  private static final ToolTip SCORE_COMPLETENESS = new ToolTip("Completeness",
      "Indicates how consistently required, expected, and recommended fields are valued").setEmphasize(true).setLink(
      "#completeness");
  private static final ToolTip SCORE_COMPLETENESS_SCORE_PATIENT = new ToolTip("Patient",
      "Indicates the completeness of patient related fields", true).setLink("#completeness.patient");
  private static final ToolTip SCORE_COMPLETENESS_SCORE_VACCINATION = new ToolTip("Vaccination",
      "Indicates the completeness of vaccination related fields", true).setLink("#completeness.vaccination");
  private static final ToolTip SCORE_COMPLETENESS_SCORE_VACCINE_GROUP = new ToolTip("Vaccine Group",
      "Indicates the completess of the type of vaccines reported", true).setLink("#completeness.vaccineGroup");
  private static final ToolTip SCORE_QUALITY = new ToolTip("Quality",
      "Indicates the level of errors and warnings generated").setEmphasize(true).setLink("#quality");
  private static final ToolTip SCORE_QUALITY_SCORE_ERRORS = new ToolTip("Errors",
      "Indicates the level of errors found, message are expected to generate less than one percent error rate", true)
      .setLink("#quality.errors");
  private static final ToolTip SCORE_QUALITY_SCORE_WARNINGS = new ToolTip("Warnings",
      "Indicates the level of warnings found, message are expected to generate less than ten percent warning rate",
      true).setLink("#quality.warnings");
  private static final ToolTip SCORE_TIMELINESS = new ToolTip("Timeliness",
      "Indicates the amount of time between administration of vaccinations and reporting them").setEmphasize(true)
      .setLink("#timeliness");
  private static final ToolTip SCORE_TIMELINESS_SCORE_2_DAYS = new ToolTip("2 Days",
      "Indicates the number of messages received within 48 hours of administration", true).setLink("#timeliness");
  private static final ToolTip SCORE_TIMELINESS_SCORE_7_DAYS = new ToolTip("7 Days",
      "Indicates the number of messages received within a week of administration", true).setLink("#timeliness");
  private static final ToolTip SCORE_TIMELINESS_SCORE_30_DAYS = new ToolTip("30 Days",
      "Indicates the number of messages received within 4 weeks of administration", true).setLink("#timeliness");

  private static final ToolTip COMPLETENESS_SCORE_PATIENT = new ToolTip("Patient",
      "Indicates the completeness of patient related fields").setLink("#completeness.patient");
  private static final ToolTip COMPLETENESS_SCORE_VACCINATION = new ToolTip("Vaccination",
      "Indicates the completeness of vaccination related fields").setLink("#completeness.vaccination");
  private static final ToolTip COMPLETENESS_SCORE_VACCINE_GROUP = new ToolTip("Vaccine Group",
      "Indicates the completess of the type of vaccines reported").setLink("#completeness.vaccineGroup");

  private static final ToolTip QUALITY_SCORE_ERRORS = new ToolTip("Errors",
      "Indicates the level of errors found, message are expected to generate less than one percent error rate")
      .setLink("#quality.errors");
  private static final ToolTip QUALITY_SCORE_WARNINGS = new ToolTip("Warnings",
      "Indicates the level of warnings found, message are expected to generate less than ten percent warning rate")
      .setLink("#quality.warnings");

  private static final ToolTip QUALITY_SCORE_INVALID = new ToolTip("Unrecognized Codes",
      "Codes were received that were not acceptable, must use standard codes").setLink("#quality.invalidCodes");
  private static final ToolTip QUALITY_SCORE_UNRECOGNIZED = new ToolTip("Unrecognized Codes",
      "Codes were received that were not recognized, standard codes are recommended")
      .setLink("#quality.unrecognizedCodes");
  private static final ToolTip QUALITY_SCORE_DEPRECATED = new ToolTip("Unrecognized Codes",
      "Codes were recognized but standard codes are recommended").setLink("#quality.deprecatedCodes");

  private static final ToolTip TIMELINESS_SCORE_2_DAYS = new ToolTip("2 Days",
      "Indicates the number of messages received within 48 hours of administration");
  private static final ToolTip TIMELINESS_SCORE_7_DAYS = new ToolTip("7 Days",
      "Indicates the number of messages received within a week of administration");
  private static final ToolTip TIMELINESS_SCORE_30_DAYS = new ToolTip("30 Days",
      "Indicates the number of messages received within 4 weeks of administration");

  private static final ToolTip TIMELINESS_WITHIN_2_DAYS = new ToolTip(
      "2 Days",
      "Latest vaccination reported within 2 days (reports of previous administered vaccinations in same message and historical vaccinations not counted)");
  private static final ToolTip TIMELINESS_WITHIN_7_DAYS = new ToolTip(
      "7 Days",
      "Latest vaccination reported within 1 week (reports of previous administered vaccinations in same message and historical vaccinations not counted)");
  private static final ToolTip TIMELINESS_WITHIN_30_DAYS = new ToolTip(
      "30 Days",
      "Latest vaccination reported within 30 days (reports of previous administered vaccinations in same message and historical vaccinations not counted)");

  private static final ToolTip VACCINATION_GROUP_OTHER = new ToolTip("Undefined",
      "Vaccination is not classified as part of a particular vaccine group");

  private QualityCollector messageBatchManager = null;
  private SubmitterProfile profile = null;
  private String filename = "";
  private PrintWriter out = null;

  private int messageCount = 0;
  private int nextOfKinCount = 0;
  private int patientCount = 0;
  private int vaccinationCount = 0;

  public QualityReport(QualityCollector messageBatchManager, SubmitterProfile profile, PrintWriter out) {
    this.messageBatchManager = messageBatchManager;
    this.profile = profile;
    this.out = out;
  }

  public void setFilename(String filename)
  {
    this.filename = filename;
  }

  private SimpleDateFormat dateTime = new SimpleDateFormat("EEE, MMM d, yyyy h:mm aaa");
  private SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm aaa");
  private SimpleDateFormat dateOnly = new SimpleDateFormat("EEE, MMM d, yyyy");

  private String makeDateTimeRange(Date start, Date end)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(dateTime.format(start));
    long difference = end.getTime() - start.getTime();
    if (difference > 120 * 1000)
    {
      // more than two minutes, need to set ending time
      String endString = dateOnly.format(end);
      if (sb.toString().startsWith(endString))
      {
        // same starting date, just add ending time
        endString = timeOnly.format(end);
      }
      sb.append(" thru ");
      sb.append(endString);
    }
    return sb.toString();
  }

  private String makeDateRange(Date start, Date end)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(dateOnly.format(start));
    long difference = end.getTime() - start.getTime();
    if (difference > 120 * 1000)
    {
      String endString = dateOnly.format(end);
      if (sb.toString().equals(endString))
      {
        // same starting date, do nothing
        return sb.toString();
      }
      sb.append(" thru ");
      sb.append(endString);
    }
    return sb.toString();
  }

  public void printReport()
  {
    MessageBatch messageBatch = messageBatchManager.getMessageBatch();
    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>Data Quality Report</title>");
    printCss2();
    out.println("  </head>");
    out.println("  <body>");
    try
    {
      printTitleBar(messageBatch);
      printSummary(messageBatch);
      printCompleteness(messageBatch);
      printQuality(messageBatch);
      printTimeliness(messageBatch);
      printFooter();
    } catch (Exception e)
    {
      e.printStackTrace(out);
    }
    out.println("  </body>");
    out.println("<html>");
  }

  private void printFooter()
  {
    out.println("    <pre>Report generated: " + dateTime.format(new Date()) + "</pre>");
  }

  private void printCompleteness(MessageBatch messageBatch)
  {
    QualityScoring scoring = messageBatchManager.getCompletenessScoring();
    ScoringSet patientExpected = scoring.getScoringSet(QualityScoring.PATIENT_EXPECTED);
    ScoringSet patientOptional = scoring.getScoringSet(QualityScoring.PATIENT_OPTIONAL);
    ScoringSet patientRecommended = scoring.getScoringSet(QualityScoring.PATIENT_RECOMMENDED);
    ScoringSet patientRequired = scoring.getScoringSet(QualityScoring.PATIENT_REQUIRED);
    ScoringSet vaccinationExpected = scoring.getScoringSet(QualityScoring.VACCINATION_EXPECTED);
    ScoringSet vaccinationOptional = scoring.getScoringSet(QualityScoring.VACCINATION_OPTIONAL);
    ScoringSet vaccinationRecommended = scoring.getScoringSet(QualityScoring.VACCINATION_RECOMMENDED);
    ScoringSet vaccinationRequired = scoring.getScoringSet(QualityScoring.VACCINATION_REQUIRED);
    out.println("    <h2><a name=\"completeness\">Completeness</h2>");
    out.println("    <p>");
    out.println("      Completeness measures how many required, expected and ");
    out.println("      recommended fields have been received and also indicates");
    out.println("      if expected vaccinations have been reported. ");
    out.println("    </p>");
    out.println("    <h3>Score</h3>");
    printScoringSummary("Completeness", messageBatch.getCompletenessScore());
    out.println("    <table width=\"350\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Measurement</th>");
    out.println("        <th align=\"center\">Score</th>");
    out.println("        <th align=\"center\">Description</th>");
    out.println("        <th align=\"center\">Weight</th>");
    out.println("      </tr>");
    printScore(COMPLETENESS_SCORE_PATIENT, messageBatch.getCompletenessPatientScore(), "20%");
    printScore(COMPLETENESS_SCORE_VACCINATION, messageBatch.getCompletenessPatientScore(), "20%");
    printScore(COMPLETENESS_SCORE_VACCINE_GROUP, messageBatch.getCompletenessVaccineGroupScore(), "10%");
    out.println("    </table>");

    out.println("    <h3><a name=\"completeness.patient\">Patient</h3>");
    printCompletenessScoring("Patient", "#completeness.patient", patientRequired, patientExpected, patientRecommended,
        messageBatch.getCompletenessPatientScore(), 0.2);
    out.println("    <br/>");
    printCompleteness(patientRequired, "completeness.patient.required", "Required", 0.2 * 0.5);
    out.println("    <br/>");
    printCompleteness(patientExpected, "completeness.patient.expected", "Expected", 0.2 * 0.3);
    out.println("    <br/>");
    printCompleteness(patientRecommended, "completeness.patient.recommended", "Recommended", 0.2 * 0.2);
    out.println("    <br/>");
    printCompleteness(patientOptional, "completeness.patient.optional", "Optional", 0.0);
    out.println("    <br/>");

    out.println("    <h3><a name=\"completeness.vaccination\">Vaccination</h3>");
    printCompletenessScoring("Vaccination", "#completeness.vaccination", vaccinationRequired, vaccinationExpected,
        vaccinationRecommended, messageBatch.getCompletenessVaccinationScore(), 0.2);
    out.println("    <br/>");
    printCompleteness(vaccinationRequired, "completeness.vaccination.required", "Required", 0.2 * 0.5);
    out.println("    <br/>");
    printCompleteness(vaccinationExpected, "completeness.vaccination.expected", "Expected", 0.2 * 0.3);
    out.println("    <br/>");
    printCompleteness(vaccinationRecommended, "completeness.vaccination.recommended", "Recommended", 0.2 * 0.2);
    out.println("    <br/>");
    printCompleteness(vaccinationOptional, "completeness.vaccination.optional", "Optional", 0.0);

    out.println("    <h3><a name=\"completeness.vaccineGroup\">Vaccine Group</h3>");
    Set<VaccineCvx> vaccinesNotYetPrinted = new HashSet<VaccineCvx>(messageBatch.getBatchVaccineCvxMap().keySet());
    VaccineGroupManager vaccineGroupManager = VaccineGroupManager.getVaccineGroupManager();
    List<VaccineGroup> vaccineGroupList = vaccineGroupManager.getVaccineGroupList(VaccineGroup.GROUP_STATUS_EXPECTED);
    printVaccines("Expected", messageBatch, vaccinesNotYetPrinted, vaccineGroupList, false);
    out.println("    <br/>");
    vaccineGroupList = vaccineGroupManager.getVaccineGroupList(VaccineGroup.GROUP_STATUS_RECCOMMENDED);
    printVaccines("Recommended", messageBatch, vaccinesNotYetPrinted, vaccineGroupList, true);
    out.println("    <br/>");
    vaccineGroupList = vaccineGroupManager.getVaccineGroupList(VaccineGroup.GROUP_STATUS_OPTIONAL);
    printVaccines("Optional", messageBatch, vaccinesNotYetPrinted, vaccineGroupList, true);
    out.println("    <br/>");
    vaccineGroupList = vaccineGroupManager.getVaccineGroupList(VaccineGroup.GROUP_STATUS_NOT_EXPECTED);
    printVaccines("Unexpected", messageBatch, vaccinesNotYetPrinted, vaccineGroupList, true);
    if (vaccinesNotYetPrinted.size() > 0)
    {
      out.println("    <br/>");
      printVaccinesLeftover(messageBatch, vaccinesNotYetPrinted);
    }
  }

  private void printVaccines(String label, MessageBatch messageBatch, Set<VaccineCvx> vaccinesNotPrinted,
      List<VaccineGroup> vaccineGroupList, boolean skipZeroSize)
  {
    out.println("    <table width=\"650\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">" + label + "</th>");
    out.println("        <th align=\"left\">CVX</th>");
    out.println("        <th align=\"left\">Label</th>");
    out.println("        <th align=\"center\">Count</th>");
    out.println("        <th align=\"center\">Percent</th>");
    out.println("      </tr>");
    boolean printedAtLeastOneRow = false;
    for (VaccineGroup vaccineGroup : vaccineGroupList)
    {
      List<VaccineCvx> vaccinesToPrint = new ArrayList<VaccineCvx>();
      for (VaccineCvx vaccineCvx : vaccineGroup.getVaccineCvxList())
      {
        int count = messageBatchManager.getVaccineCvxCount(vaccineCvx);
        if (count > 0)
        {
          vaccinesToPrint.add(vaccineCvx);
        }
      }
      int size = vaccinesToPrint.size();
      if (size == 0)
      {
        if (skipZeroSize)
        {
          continue;
        }
      }
      ToolTip toolTip = vaccineGroup.getToolTip();
      printVaccinesRow(messageBatch, vaccinesNotPrinted, vaccinesToPrint, size, toolTip, size == 0);
      printedAtLeastOneRow = true;
    }
    if (!printedAtLeastOneRow)
    {
      out.println("      <tr>");
      out.println("        <td colspan=\"5\">None to Display</td>");
      out.println("      </tr>");
    }
    out.println("    </table>");
  }

  private void printVaccinesLeftover(MessageBatch messageBatch, Set<VaccineCvx> vaccinesNotPrinted)
  {
    out.println("    <table width=\"650\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Other</th>");
    out.println("        <th align=\"center\">CVX</th>");
    out.println("        <th align=\"left\">Label</th>");
    out.println("        <th align=\"center\">Count</th>");
    out.println("        <th align=\"center\">Percent</th>");
    out.println("      </tr>");
    List<VaccineCvx> vaccinesToPrint = new ArrayList<VaccineCvx>(vaccinesNotPrinted);
    ToolTip toolTip = VACCINATION_GROUP_OTHER;
    printVaccinesRow(messageBatch, vaccinesNotPrinted, vaccinesToPrint, vaccinesToPrint.size(), toolTip, true);
    out.println("    </table>");
  }

  private void printVaccinesRow(MessageBatch messageBatch, Set<VaccineCvx> vaccinesNotPrinted,
      List<VaccineCvx> vaccinesToPrint, int size, ToolTip toolTip, boolean outOfRange)
  {
    {
      out.println("      <tr>");
      out.println("        <td align=\"left\"" + (outOfRange ? " class=\"alert\"" : "") + " rowspan=\"" + size + "\">"
          + toolTip.getHtml());
      out.println("        </td>");
      boolean nextRow = false;
      for (VaccineCvx vaccineCvx : vaccinesToPrint)
      {
        int count = messageBatchManager.getVaccineCvxCount(vaccineCvx);
        int denominator = messageBatch.getVaccinationAdministeredCount();
        String percent = "&nbsp;";
        if (denominator != 0)
        {
          if (count == 0)
          {
            percent = "-";
          } else
          {
            int per = (int) ((100.0 * count) / denominator + 0.5);
            percent = String.valueOf(per) + "%";
          }
        }
        if (nextRow)
        {
          out.println("      </tr>");
          out.println("      <tr>");
        }
        String[] fields = { vaccineCvx.getCvxCode(), vaccineCvx.getCvxLabel(), num(count), percent };
        for (int i = 0; i < fields.length; i++)
        {
          String align = i == 1 ? "left" : "center";
          out.println("        <td align=\"" + align + "\"" + (outOfRange ? " class=\"alert\"" : "") + ">" + fields[i]
              + "</td>");
        }
        vaccinesNotPrinted.remove(vaccineCvx);
        nextRow = true;
      }
      if (!nextRow)
      {
        out.println("       <td colspan=\"4\"><span class=\"problem\">Problem: no vaccines received for this group</span></td>");
      }
      out.println("      </tr>");
    }
  }

  private void printCompletenessScoring(String label, String link, ScoringSet required, ScoringSet expected,
      ScoringSet recommended, int score, double weightFactor)
  {
    out.println("    <table width=\"350\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">" + label + " Fields</th>");
    out.println("        <th>Score</th>");
    out.println("        <th>Description</th>");
    out.println("        <th>Weight</th>");
    out.println("      </tr>");
    out.println("      <tr>");
    out.println("        <td class=\"highlight\">Overall</td>");
    out.println("        <td class=\"highlight\" align=\"center\">" + score + "</td>");
    out.println("        <td class=\"highlight\" align=\"center\">" + getScoreDescription(score) + "</td>");
    out.println("        <td class=\"highlight\">&nbsp;</td>");
    out.println("      </tr>");
    printScoreAndWeight(required, link + ".required", "Required", weightFactor);
    printScoreAndWeight(expected, link + ".expected", "Expected", weightFactor);
    printScoreAndWeight(recommended, link + ".recommended", "Recommended", weightFactor);
    out.println("    </table>");
  }

  private void printScoreAndWeight(ScoringSet ss, String link, String label, double weightFactor)
  {
    int score = (int) (100 * ss.getScore() + 0.5);
    boolean showRed = score < 70;
    out.println("      <tr>");
    out.println("        <td align=\"left\"" + (showRed ? " class=\"alert\"" : "") + "><a href=\"" + link + "\">"
        + label + "</a></td>");
    out.println("        <td align=\"center\"" + (showRed ? " class=\"alert\"" : "") + ">" + score + "</td>");
    out.println("        <td align=\"center\"" + (showRed ? " class=\"alert\"" : "") + ">" + getScoreDescription(score)
        + "</td>");
    out.println("        <td align=\"center\"" + (showRed ? " class=\"alert\"" : "") + ">"
        + (int) (100 * weightFactor * ss.getWeight() + 0.5) + "%</td>");
    out.println("      </tr>");
  }

  private void printCompleteness(ScoringSet scoringSet, String objectLabel, String typeLabel, double weightFactor)
  {
    List<CompletenessRow> completenessRowList = scoringSet.getCompletenessRow();
    out.println("    <table width=\"" + (scoringSet.getWeight() > 0 ? "480" : "320") + "\">");
    out.println("      <tr>");
    out.println("        <th width=\"180\" align=\"left\"><a name=\"" + objectLabel + "\"/>" + typeLabel + "</th>");
    out.println("        <th width=\"70\" align=\"center\">Count</th>");
    out.println("        <th width=\"70\" align=\"center\">Percent</th>");
    if (scoringSet.getWeight() > 0)
    {
      out.println("        <th width=\"90\" align=\"center\">Description</th>");
      out.println("        <th width=\"70\" align=\"center\">Weight</th>");
    }
    out.println("      </tr>");
    for (CompletenessRow completenessRow : completenessRowList)
    {
      if (completenessRow.getScoreWeight() > 0 || completenessRow.getCount() > 0)
      {
        print(completenessRow, scoringSet.getOverallWeight(), weightFactor);
      }
    }
    out.println("    </table>");
  }

  private void printTitleBar(MessageBatch messageBatch)
  {
    out.println("    <h1>" + profile.getOrganization().getOrgLabel() + " Quality Report</h1>");
    out.println("    <table width=\"720\">");
    out.println("      <tr>");
    out.println("        <th>Batch Title</th>");
    out.println("        <th>Batch Type</th>");
    out.println("        <th>Profile</th>");
    if (filename != null && !filename.equals(""))
    {
      out.println("        <th>File Name</th>");
    }
    out.println("        <th>Received</th>");
    out.println("      </tr>");
    if (filename != null && !filename.equals(""))
    {
      printRow(messageBatch.getBatchTitle(), messageBatch.getBatchType().getTypeLabel(), profile.getProfileLabel(),
          filename, makeDateTimeRange(messageBatch.getStartDate(), messageBatch.getEndDate()));
    } else
    {
      printRow(messageBatch.getBatchTitle(), messageBatch.getBatchType().getTypeLabel(), profile.getProfileLabel(),
          makeDateTimeRange(messageBatch.getStartDate(), messageBatch.getEndDate()));

    }
    out.println("    </table>");
    // out.println("    <p>");
    // out.println("      Immunization data quality is measured in three main areas: ");
    // out.println("      Timeliness, Quality and Completeness.  ");
    // out.println("      Timeliness measures how quickly administered vaccinations are ");
    // out.println("      reported. ");
    // out.println("      Quality measures how accurate patient and vaccination data is ");
    // out.println("      recorded and whether it reflects expected practice.");
    // out.println("      Completeness measures if fields necessary for registry functions ");
    // out.println("      been valued. ");
    // out.println("    </p>");
    out.println("    <h3>Scoring Summary</h3>");
    printScoringSummary("DQA", messageBatch.getOverallScore());
    out.println("    <table width=\"400\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Measurement</th>");
    out.println("        <th align=\"center\">Score</th>");
    out.println("        <th align=\"center\">Description</th>");
    out.println("        <th align=\"center\">Weight</th>");
    out.println("      </tr>");
    printScoreOverall(SCORE_COMPLETENESS, messageBatch.getCompletenessScore(), "50%");
    printScoreOverall(SCORE_COMPLETENESS_SCORE_PATIENT, messageBatch.getCompletenessPatientScore(), "20%");
    printScoreOverall(SCORE_COMPLETENESS_SCORE_VACCINATION, messageBatch.getCompletenessPatientScore(), "20%");
    printScoreOverall(SCORE_COMPLETENESS_SCORE_VACCINE_GROUP, messageBatch.getCompletenessVaccineGroupScore(), "10%");
    printScoreOverall(SCORE_QUALITY, messageBatch.getQualityScore(), "40%");
    printScoreOverall(SCORE_QUALITY_SCORE_ERRORS, messageBatch.getQualityErrorScore(), "20%");
    printScoreOverall(SCORE_QUALITY_SCORE_WARNINGS, messageBatch.getQualityWarnScore(), "20%");
    printScoreOverall(SCORE_TIMELINESS, messageBatch.getTimelinessScore(), "10%");
    printScoreOverall(SCORE_TIMELINESS_SCORE_30_DAYS, messageBatch.getTimelinessScore30Days(), "6%");
    printScoreOverall(SCORE_TIMELINESS_SCORE_7_DAYS, messageBatch.getTimelinessScore7Days(), "4%");
    if (SCORE_TIMELINESS_2_DAYS)
    {
      printScoreOverall(SCORE_TIMELINESS_SCORE_2_DAYS, messageBatch.getTimelinessScore2Days(), "1%");
    }
    out.println("    </table>");
  }

  private void printScoringSummary(String label, int score)
  {
    out.println("    <table>");
    out.println("      <tr>");
    out.println("        <th align=\"center\">" + label + " Score</th>");
    out.println("        <th align=\"center\">Description</th>");
    out.println("      </tr>");
    out.println("      <tr>");
    out.println("        <td align=\"center\"><span class=\"score\">" + score + "</span></th>");
    out.println("        <td align=\"center\"><span class=\"score\">" + getScoreDescription(score) + "</span></th>");
    out.println("      </tr>");
    out.println("    </table>");
    out.println("    <br/>");

  }

  private void printSummary(MessageBatch messageBatch)
  {
    out.println("    <h3>Data Received</h3>");
    out.println("    <table width=\"350\">");
    out.println("      <tr>");
    out.println("        <th width=\"60%\" align=\"left\">Received</th>");
    out.println("        <th width=\"20%\" align=\"center\">Count</th>");
    out.println("        <th width=\"20%\" align=\"center\">Percent</th>");
    out.println("      </tr>");
    messageCount = messageBatch.getMessageCount();
    patientCount = messageBatch.getPatientCount();
    vaccinationCount = messageBatch.getVaccinationCount();
    nextOfKinCount = messageBatch.getNextOfKinCount();
    printPer(RECEIVED_PATIENTS, patientCount, 0);
    printPer(RECEIVED_NEXT_OF_KINS, nextOfKinCount, 0);
    printPer(RECEIVED_VACCINATIONS, vaccinationCount, 0);
    printPer(RECEIVED_VACCINATIONS_ADMININISTERED, messageBatch.getVaccinationAdministeredCount(), vaccinationCount);
    printPer(RECEIVED_VACCINATIONS_HISTORICAL, messageBatch.getVaccinationHistoricalCount(), vaccinationCount);
    printPer(RECEIVED_VACCINATIONS_DELETED, messageBatch.getVaccinationDeleteCount(), vaccinationCount);
    printPer(RECEIVED_VACCINATIONS_NOT_ADMINISTERED, messageBatch.getVaccinationNotAdministeredCount(),
        vaccinationCount);
    out.println("    </table>");
    out.println("    <h3>Processing Status</h3>");
    out.println("    <table width=\"350\">");
    out.println("      <tr>");
    out.println("        <th width=\"60%\" align=\"left\">Status</th>");
    out.println("        <th width=\"20%\" align=\"center\">Count</th>");
    out.println("        <th width=\"20%\" align=\"center\">Percent</th>");
    out.println("      </tr>");
    printPer(STATUS_ACCEPTED, messageBatch.getBatchActions(IssueAction.ACCEPT).getActionCount(), patientCount, 90, 100);
    printPer(STATUS_WARNED, messageBatch.getBatchActions(IssueAction.WARN).getActionCount(), patientCount, 0, 10);
    printPer(STATUS_ERRORED, messageBatch.getBatchActions(IssueAction.ERROR).getActionCount(), patientCount, 0, 0);
    printPer(STATUS_SKIPPED, messageBatch.getBatchActions(IssueAction.SKIP).getActionCount(), patientCount, 0, 10);
    out.println("    </table>");
  }

  private void printQuality(MessageBatch messageBatch)
  {
    out.println("    <h2><a name=\"quality\">Quality</h2>");
    out.println("    <p>");
    out.println("      Quality  measures the number of errors and warnings that are encountered");
    out.println("      during processing. Total errors registry must account for less than ");
    out.println("      one percent of total number of ");
    out.println("      patients and vaccinations. ");
    out.println("      Total warnings registered are expected to account for ");
    out.println("      less than ten percent of the total patients and vaccinations. ");
    out.println("    </p>");
    out.println("    <h3>Quality Score</h3>");
    printScoringSummary("Quality", messageBatch.getQualityScore());
    out.println("    <table width=\"400\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Measurement</th>");
    out.println("        <th align=\"center\">Score</th>");
    out.println("        <th align=\"center\">Description</th>");
    out.println("        <th align=\"center\">Weight</th>");
    out.println("      </tr>");
    printScore(QUALITY_SCORE_ERRORS, messageBatch.getQualityErrorScore(), "20%");
    printScore(QUALITY_SCORE_WARNINGS, messageBatch.getQualityWarnScore(), "20%");
    out.println("    </table>");
    out.println("    <br/>");
    if (messageBatchManager.getInvalidCodes().size() > 0 || messageBatchManager.getUnrecognizedCodes().size() > 0
        || messageBatchManager.getErrorIssues().size() > 0)
    {
      out.println("    <table width=\"400\">");
      out.println("      <tr>");
      out.println("        <th align=\"left\">Coded Value Issues</th>");
      out.println("        <th align=\"center\">Count</th>");
      out.println("      </tr>");
      if (messageBatchManager.getInvalidCodes().size() > 0)
      {
        printRow(QUALITY_SCORE_INVALID, true, num(messageBatchManager.getInvalidCodes().size()));
      }
      if (messageBatchManager.getUnrecognizedCodes().size() > 0)
      {
        printRow(QUALITY_SCORE_UNRECOGNIZED, true, num(messageBatchManager.getUnrecognizedCodes().size()));
      }
      if (messageBatchManager.getDeprecatedCodes().size() > 0)
      {
        printRow(QUALITY_SCORE_DEPRECATED, true, num(messageBatchManager.getDeprecatedCodes().size()));
      }
      out.println("    </table>");
    }
    if (messageBatchManager.getErrorIssues().size() > 0)
    {
      out.println("    <h3><a name=\"quality.errors\">Errors</h3>");
      printBatchIssues(messageBatchManager.getErrorIssues());
    }
    if (messageBatchManager.getWarnIssues().size() > 0)
    {
      out.println("    <h3><a name=\"quality.warnings\">Warnings</h3>");
      printBatchIssues(messageBatchManager.getWarnIssues());
    }
    if (messageBatchManager.getSkipIssues().size() > 0)
    {
      out.println("    <h3><a name=\"quality.skips\">Skips</h3>");
      printBatchIssues(messageBatchManager.getSkipIssues());
    }
    if (messageBatchManager.getInvalidCodes().size() > 0)
    {
      out.println("    <h3><a name=\"quality.invalidCodes\">Invalid Codes</h3>");
      printCodeReceived(messageBatchManager.getInvalidCodes());
    }
    if (messageBatchManager.getUnrecognizedCodes().size() > 0)
    {
      out.println("    <h3><a name=\"quality.unrecognizedCodes\">Unrecognized Codes</h3>");
      printCodeReceived(messageBatchManager.getUnrecognizedCodes());
    }
    if (messageBatchManager.getDeprecatedCodes().size() > 0)
    {
      out.println("    <h3><a name=\"quality.deprecatedCodes\">Deprecated Codes</h3>");
      printCodeReceived(messageBatchManager.getDeprecatedCodes());
    }
  }

  private void printTimeliness(MessageBatch messageBatch)
  {
    out.println("    <h2><a name=\"timeliness\">Timeliness</h2>");
    out.println("    <p>");
    out.println("      Timeliness measures the number of days between the");
    out.println("      date a message was received and the most recent administered vaccination");
    out.println("      indicated in that message. ");
    out.println("      Submitters should send administered vaccinations as soon as possible after");
    out.println("      administration, normally once a week. ");
    out.println("    </p>");
    out.println("    <h3>Timeliness Score</h3>");
    printScoringSummary("Timeliness", messageBatch.getTimelinessScore());
    out.println("    <table width=\"400\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Measurement</th>");
    out.println("        <th align=\"center\">Score</th>");
    out.println("        <th align=\"center\">Description</th>");
    out.println("        <th align=\"center\">Weight</th>");
    out.println("      </tr>");
    printScore(TIMELINESS_SCORE_30_DAYS, messageBatch.getTimelinessScore30Days(), "6%");
    printScore(TIMELINESS_SCORE_7_DAYS, messageBatch.getTimelinessScore7Days(), "4%");
    if (SCORE_TIMELINESS_2_DAYS)
    {
      printScore(TIMELINESS_SCORE_2_DAYS, messageBatch.getTimelinessScore2Days(), "1%");
    }
    out.println("    </table>");
    out.println("    <br/>");
    out.println("    <table width=\"350\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Vaccination Received</th>");
    out.println("        <th align=\"center\">Count</th>");
    out.println("        <th align=\"center\">Percent</th>");
    out.println("      </tr>");
    printPer(TIMELINESS_WITHIN_30_DAYS, messageBatch.getTimelinessCount30Days(),
        messageBatch.getMessageWithAdminCount());
    printPer(TIMELINESS_WITHIN_7_DAYS, messageBatch.getTimelinessCount7Days(), messageBatch.getMessageWithAdminCount());
    printPer(TIMELINESS_WITHIN_2_DAYS, messageBatch.getTimelinessCount2Days(), messageBatch.getMessageWithAdminCount());
    out.println("    </table>");
    out.println("    <br/>");
    out.println("    <table>");
    out.println("      <tr>");
    out.println("        <th align=\"left\" colspan=\"2\">Timeliness of Vaccination Update</th>");
    out.println("      </tr>");
    if (messageBatch.getTimelinessDateFirst() != null && messageBatch.getTimelinessDateLast() != null)
    {
      out.println("      <tr>");
      out.println("        <th align=\"left\">Vaccination Admininistered</th>");
      out.println("        <td>"
          + makeDateRange(messageBatch.getTimelinessDateFirst(), messageBatch.getTimelinessDateLast()) + "</th>");
      out.println("      </tr>");
    }
    out.println("      <tr>");
    out.println("        <th align=\"left\">Batch Received</th>");
    out.println("        <td>" + makeDateRange(messageBatch.getStartDate(), messageBatch.getEndDate()) + "</th>");
    out.println("      </tr>");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Average Elapsed Days</th>");
    DecimalFormat df = new DecimalFormat("0.0");
    out.println("        <td>" + df.format(messageBatch.getTimelinessAverage()) + "</th>");
    out.println("      </tr>");
    out.println("    </table>");

  }

  private void printScore(ToolTip tip, int score, String weight)
  {
    printRow(tip, score < 70, String.valueOf(score), getScoreDescription(score), weight);
  }

  private void printScoreOverall(ToolTip tip, int score, String weight)
  {
    printRow(tip, false, String.valueOf(score), getScoreDescription(score), weight);
  }

  private void printCodeReceived(Set<CodeReceived> codeReceivedSet)
  {
    List<CodeReceived> codeReceivedList = new ArrayList<CodeReceived>(codeReceivedSet);
    Collections.sort(codeReceivedList, new Comparator<CodeReceived>() {
      public int compare(CodeReceived arg0, CodeReceived arg1)
      {
        int c = arg0.getTable().getTableLabel().compareTo(arg1.getTable().getTableLabel());
        if (c == 0)
        {
          return arg0.getReceivedValue().compareTo(arg1.getReceivedValue());
        }
        return c;
      }
    });
    out.println("    <table width=\"550\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Table</th>");
    out.println("        <th align=\"center\">Value</th>");
    out.println("        <th align=\"center\">Label</th>");
    out.println("        <th align=\"center\">Mapped To</th>");
    out.println("      </tr>");
    for (CodeReceived cr : codeReceivedList)
    {
      printRow(cr.getTable().getTableLabel(), cr.getReceivedValue(), cr.getCodeLabel(), cr.getCodeValue());
    }
    out.println("    </table>");
  }

  private void printBatchIssues(List<BatchIssues> batchIssuesList)
  {
    Collections.sort(batchIssuesList, new Comparator<BatchIssues>() {
      public int compare(BatchIssues arg0, BatchIssues arg1)
      {
        Integer count0 = arg0.getIssueCountPos();
        Integer count1 = arg1.getIssueCountPos();
        return count1.compareTo(count0);
      }
    });
    out.println("    <table width=\"550\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Description</th>");
    out.println("        <th align=\"center\">Count</th>");
    out.println("        <th align=\"center\">Percent</th>");
    out.println("      </tr>");
    for (BatchIssues batchIssues : batchIssuesList)
    {
      PotentialIssue issue = batchIssues.getIssue();
      int denominator = 0;
      if (issue.getReportDenominator().equals(PotentialIssue.REPORT_DENOMINATOR_MESSAGE_COUNT))
      {
        denominator = messageCount;
      } else if (issue.getReportDenominator().equals(PotentialIssue.REPORT_DENOMINATOR_PATIENT_COUNT))
      {
        denominator = patientCount;
      } else if (issue.getReportDenominator().equals(PotentialIssue.REPORT_DENOMINATOR_NEXT_OF_KIN_COUNT))
      {
        denominator = nextOfKinCount;
      } else if (issue.getReportDenominator().equals(PotentialIssue.REPORT_DENOMINATOR_VACCINATION_COUNT))
      {
        denominator = vaccinationCount;
      }
      printPer(issue.getToolTip(), batchIssues.getIssueCountPos(), denominator);
    }
    out.println("    </table>");
  }

  private void printPer(ToolTip toolTip, int value, int denominator)
  {
    printPer(toolTip, value, denominator, 0, 100);
  }

  private void printPer(ToolTip toolTip, int value, int denominator, int okayLow, int okayHigh)
  {
    String percent = "&nbsp;";
    boolean outOfRange = false;
    if (denominator != 0)
    {
      if (value == 0)
      {
        percent = "-";
      } else
      {
        int per = (int) ((100.0 * value) / denominator + 0.5);
        if (per < okayLow || per > okayHigh)
        {
          outOfRange = true;
        }
        percent = String.valueOf(per) + "%";
      }
    }
    printRow(toolTip, outOfRange, num(value), percent);
  }

  private void printVaccineGroup(ToolTip toolTip, int value, int denominator, int okayLow, int okayHigh)
  {
    String percent = "&nbsp;";
    boolean outOfRange = false;
    if (denominator != 0)
    {
      if (value == 0)
      {
        percent = "-";
      } else
      {
        int per = (int) ((100.0 * value) / denominator + 0.5);
        if (per < okayLow || per > okayHigh)
        {
          outOfRange = true;
        }
        percent = String.valueOf(per) + "%";
      }
    }
    printRow(toolTip, outOfRange, String.valueOf(value), percent);
  }

  private void print(CompletenessRow row, int overallWeight, double weightFactor)
  {
    ToolTip toolTip = row.getToolTip();
    int value = row.getCount();
    int denominator = row.getDenominator();
    String percent = "&nbsp;";
    boolean outOfRange = false;
    String description = "";
    if (row.getDenominator() > 0)
    {
      if (value == 0)
      {
        percent = "-";
        description = getScoreDescription(0);
      } else
      {
        int per = (int) ((100.0 * value) / denominator + 0.5);
        if (per < row.getOkayLow() || per > row.getOkayHigh())
        {
          outOfRange = true;
        }
        percent = String.valueOf(per) + "%";
        description = getScoreDescription(per);
      }
    }
    if (row.getScoreWeight() == 0)
    {
      printRow(toolTip, outOfRange, num(value), percent);
    } else
    {
      String scorePercent = "&nbsp;";
      if (overallWeight > 0)
      {
        Double score = ((double) row.getScoreWeight()) / overallWeight;
        if (score > 1.0)
        {
          score = 1.0;
        }
        if (score < -1.0)
        {
          score = -1.0;
        }
        score = 100.0 * score * weightFactor;
        DecimalFormat df = new DecimalFormat("0.0");
        scorePercent = df.format(score) + "%";
      }
      if (row.getScoreWeight() < 0)
      {
        outOfRange = true;
      }
      printRow(toolTip, outOfRange, num(value), percent, description, scorePercent);
    }
  }

  private void printRow(String... fields)
  {
    out.println("      <tr>");
    String align = "left";
    for (int i = 0; i < fields.length; i++)
    {
      out.println("        <td align=\"" + align + "\">" + (fields[i] == null ? "&nbsp;" : fields[i]) + "</td>");
      align = "center";
    }
    out.println("      </tr>");
  }

  private void printRow(ToolTip tip, boolean showRed, String... fields)
  {
    String cssClass = showRed ? " class=\"alert\"" : "";
    if (tip.isEmphasize())
    {
      cssClass = " class=\"highlight\"";
    }
    out.println("      <tr>");
    out.println("        <td align=\"left\"" + cssClass + ">" + tip.getHtml());
    out.println("        </td>");
    for (int i = 0; i < fields.length; i++)
    {
      out.println("        <td align=\"center\"" + cssClass + ">" + fields[i] + "</td>");
    }
    out.println("      </tr>");
  }

  private void printCss1()
  {
    out.println("    <style><!--");
    out.println("      body {font-family: Tahoma, Geneva, Sans-serif}");
    out.println("      p {width:700px; color:#2B3E42; background:#F7F3E8; padding:6px; border-style:dashed; border-width:1px; border-color:#2B3E42}");
    out.println("      h1 {color:#2B3E42; font-size:2.0em;}");
    out.println("      h2 {color:#2B3E42; font-size:2.0em;}");
    out.println("      h3 {color:#2B3E42; font-size:1.2em;}");
    out.println("      table {background:#D5E1DD; border-style:solid; border-width:2; border-color:#2B3E42; border-collapse:collapse}");
    out.println("      th {background:#2B3E42; font-size:0.8em; color:#D5E1DD; border-style:none; padding-left:5px; padding-right:5px;}");
    out.println("      td {border-style:solid; border-width:1; border-color:#747E80;margin:0px; padding-left:5px; padding-right:5px;}");
    out.println("      .score {font-size:1.5em;}");
    out.println("      .alert {}");
    out.println("      .highlight {background: #77BED2;}");
    out.println("      .poor {color:#F2583E; font-style:bold;}");
    out.println("      .problem {color:#F2583E; font-style:bold;}");
    out.println("      a:link {text-decoration:none; color:#350608}");
    out.println("      a:visited {text-decoration:none; color:#350608}");
    out.println("      a:hover {text-decoration:none; color:#350608} ");
    out.println("      a:active {text-decoration:none; color:#350608} ");
    out.println("      a.tooltip span {display:none; padding:2px 3px; margin-left:8px; width:130px;}");
    out.println("      a.tooltip:hover span{display:inline; position:absolute; background:#F7F3E8; border:1px solid #CCCCCC; color:#6C6C6C}");
    out.println("    --></style>");
  }

  // Yellow & grey
  // private static final String VERY_LIGHT = "#FFFFFF";
  // private static final String LIGHT = "#EEEEEE";
  // private static final String MEDIUM_LIGHT = "#F3E7A9;";
  // private static final String MEDIUM = "#F2C968";
  // private static final String DARK = "#A5A162";
  // private static final String BLACK = "#515230";
  // private static final String RED = "#FE4902";

  // too brown
  // private static final String VERY_LIGHT = "#FFFFFF";
  // private static final String LIGHT = "#FCF5EB";
  // private static final String MEDIUM_LIGHT = "#F4E6CC;";
  // private static final String MEDIUM = "#A25F08";
  // private static final String DARK = "#111111";
  // private static final String BLACK = "#000000";
  // private static final String RED = "#FE4902";

  // Very Green
  // private static final String VERY_LIGHT = "#FFFFFF";
  // private static final String LIGHT = "#C7EB6E";
  // private static final String MEDIUM_LIGHT = "#9DCE5C;";
  // private static final String MEDIUM = "#79B837";
  // private static final String DARK = "#111111";
  // private static final String BLACK = "#000000";
  // private static final String RED = "#CE3100";

  // Green & Grey
  // private static final String VERY_LIGHT = "#FFFFFF";
  // private static final String LIGHT = "#E5E5E5";
  // private static final String MEDIUM_LIGHT = " #C7EB6E;";
  // private static final String MEDIUM = "#9DCE5C";
  // private static final String DARK = "#79B837";
  // private static final String BLACK = "#000000";
  // private static final String RED = "#CE3100";

  // Steel
  private static final String VERY_LIGHT = "#FFFFFF";
  private static final String LIGHT = "#CCDDDD";
  private static final String MEDIUM_LIGHT = " #AAC4C4;";
  private static final String MEDIUM = "#93AAAB";
  private static final String DARK = "#749749";
  private static final String BLACK = "#000000";
  private static final String RED = "#CE3100";
  private static final String BLUE = "#0031CE";

  private void printCss2()
  {
    out.println("    <style><!--");
    out.println("      body {font-family: Tahoma, Geneva, Sans-serif}");
    out.println("      p {width:700px; color:" + DARK + "; background:" + VERY_LIGHT
        + "; padding:6px; border-style:dashed; border-width:1px; border-color:" + LIGHT + "}");
    out.println("      h1 {color:" + BLACK + "; font-size:2.0em;}");
    out.println("      h2 {color:" + BLACK + "; font-size:2.0em; page-break-before:always;}");
    out.println("      h3 {color:" + BLACK + "; font-size:1.2em;}");
    out.println("      table {background:" + LIGHT + "; border-style:solid; border-width:1; border-color:" + MEDIUM
        + "; border-collapse:collapse}");
    out.println("      th {background:" + MEDIUM + "; font-size:0.8em; color:" + BLACK
        + "; border-style:none; padding-left:5px; padding-right:5px;}");
    out.println("      td {border-style:solid; border-width:1; border-color:" + MEDIUM
        + ";margin:0px; padding-left:5px; padding-right:5px;}");
    out.println("      .score {font-size:1.5em;}");
    out.println("      .alert {}");
    out.println("      .highlight {background: " + MEDIUM_LIGHT + ";}");
    out.println("      .excellent {color:" + BLUE + "; font-style:bold;}");
    out.println("      .good {color:" + BLUE + "; font-style:bold;}");
    out.println("      .poor {color:" + RED + "; font-style:bold;}");
    out.println("      .problem {color:" + RED + "; font-style:bold;}");
    out.println("      a:link {text-decoration:none; color:" + BLACK + "}");
    out.println("      a:visited {text-decoration:none; color:" + BLACK + "}");
    out.println("      a:hover {text-decoration:none; color:" + BLACK + "} ");
    out.println("      a:active {text-decoration:none; color:" + BLACK + "} ");
    out.println("      a.tooltip span {display:none; padding:2px 3px; margin-left:8px; width:130px;}");
    out.println("      a.tooltip:hover span{display:inline; position:absolute; background:" + LIGHT
        + "; border:1px solid " + DARK + "; color:" + DARK + "}");
    out.println("    --></style>");
  }

  private String getScoreDescription(int score)
  {
    if (score >= 90)
    {
      return "<span class=\"excellent\">Excellent</span>";
    } else if (score >= 80)
    {
      return "<span class=\"good\">Good</span>";
    } else if (score >= 70)
    {
      return "<span class=\"okay\">Okay</span>";
    } else if (score >= 60)
    {
      return "<span class=\"poor\">Poor</span></span>";
    } else
    {
      return "<span class=\"problem\">Problem</span>";
    }
  }

  DecimalFormat df = new DecimalFormat("#,##0");

  private String num(int i)
  {
    return df.format(i);
  }

}
