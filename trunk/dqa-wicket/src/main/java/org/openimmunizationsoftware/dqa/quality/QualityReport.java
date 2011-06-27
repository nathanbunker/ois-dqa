package org.openimmunizationsoftware.dqa.quality;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openimmunizationsoftware.dqa.db.model.BatchIssues;
import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.MessageBatchManager;

public class QualityReport
{
  private static final ToolTip RECEIVED_PATIENTS = new ToolTip("Patients",
      "The number of patients sent, one per message");
  private static final ToolTip RECEIVED_NEXT_OF_KINS = new ToolTip("Next-of-Kins",
      "The number of responsible and associate parties sent");

  private static final ToolTip RECEIVED_VACCINATIONS = new ToolTip("Vaccinations", "The number of vaccinations sent");
  private static final ToolTip RECEIVED_VACCINATIONS_ADMININISTERED = new ToolTip("Administered",
      "Number of vaccinations indicated as administered", true);
  private static final ToolTip RECEIVED_VACCINATIONS_HISTORICAL = new ToolTip("Historical",
      "Number of vaccinations not indicated as administered", true);
  private static final ToolTip RECEIVED_VACCINATIONS_DELETED = new ToolTip("Deleted",
      "Number of vaccinations indicated as deleted", true);
  private static final ToolTip RECEIVED_VACCINATIONS_NOT_ADMINISTERED = new ToolTip("Not Administered",
      "Number of vaccinations that are indicated as not administered.", true);
  private static final ToolTip STATUS_ACCEPTED = new ToolTip("Accepted",
      "Number of messages that were accepted without warnings or errors");
  private static final ToolTip STATUS_ERRORED = new ToolTip("Rejected with Errors",
      "Number of messages that were rejected because of errors");
  private static final ToolTip STATUS_WARNED = new ToolTip("Accepted with Warnings",
      "Number of messages that were accepted but had warnings");
  private static final ToolTip STATUS_SKIPPED = new ToolTip("Skipped",
      "Number of messages that are accepted without errors but will not be processed");

  private static final ToolTip SCORE_COMPLETENESS = new ToolTip("Completeness",
      "Indicates how consistently required, expected, and recommended fields are valued");
  private static final ToolTip SCORE_QUALITY = new ToolTip("Quality",
      "Indicates the level of errors and warnings generated");
  private static final ToolTip SCORE_TIMELINESS = new ToolTip("Timeliness",
      "Indicates the amount of time between administration of vaccinations and reporting them");
  private static final ToolTip SCORE_OVERALL = new ToolTip("Overall",
      "Indicates the overall quality for this file based on completeness, quality and timeliness");

  private static final ToolTip TIMELINESS_WITHIN_2_DAYS = new ToolTip(
      "2 Days",
      "Latest vaccination reported within 2 days (reports of previous administered vaccinations in same message and historical vaccinations not counted)");
  private static final ToolTip TIMELINESS_WITHIN_7_DAYS = new ToolTip(
      "7 Days",
      "Latest vaccination reported within 1 week (reports of previous administered vaccinations in same message and historical vaccinations not counted)");
  private static final ToolTip TIMELINESS_WITHIN_30_DAYS = new ToolTip(
      "30 Days",
      "Latest vaccination reported within 30 days (reports of previous administered vaccinations in same message and historical vaccinations not counted)");

  private MessageBatchManager messageBatchManager = null;
  private SubmitterProfile profile = null;
  private String filename = "";
  private PrintWriter out = null;

  private int messageCount = 0;
  private int nextOfKinCount = 0;
  private int patientCount = 0;
  private int vaccinationCount = 0;

  public QualityReport(MessageBatchManager messageBatchManager, SubmitterProfile profile, PrintWriter out) {
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

  private String makeDateRange(Date start, Date end)
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

  public void printReport()
  {
    MessageBatch messageBatch = messageBatchManager.getMessageBatch();
    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>Data Quality Report</title>");
    printCss();
    out.println("  </head>");
    out.println("  <body>");
    printTitleBar(messageBatch);
    printSummary(messageBatch);
    printCompleteness(messageBatch);
    printQuality();
    printTimeliness(messageBatch);
    printFooter();
    out.println("  </body>");
    out.println("<html>");
  }

  private void printFooter()
  {
    out.println("    <pre>Report generated: " + dateTime.format(new Date()) + "</pre>");
  }

  private void printCompleteness(MessageBatch messageBatch)
  {
    CompletenessScoring scoring = messageBatchManager.getCompletenessScoring();
    ScoringSet patientExpected = scoring.getScoringSet(CompletenessScoring.PATIENT_EXPECTED);
    ScoringSet patientOptional = scoring.getScoringSet(CompletenessScoring.PATIENT_OPTIONAL);
    ScoringSet patientRecommended = scoring.getScoringSet(CompletenessScoring.PATIENT_RECOMMENDED);
    ScoringSet patientRequired = scoring.getScoringSet(CompletenessScoring.PATIENT_REQUIRED);
    ScoringSet vaccinationExpected = scoring.getScoringSet(CompletenessScoring.VACCINATION_EXPECTED);
    ScoringSet vaccinationOptional = scoring.getScoringSet(CompletenessScoring.VACCINATION_OPTIONAL);
    ScoringSet vaccinationRecommended = scoring.getScoringSet(CompletenessScoring.VACCINATION_RECOMMENDED);
    ScoringSet vaccinationRequired = scoring.getScoringSet(CompletenessScoring.VACCINATION_REQUIRED);
    out.println("    <h2>Completeness</h2>");
    out.println("    <p>");
    out.println("      Completeness measures how many required, expected and ");
    out.println("      recommended fields have been received and also indicates");
    out.println("      if expected vaccinations have been reported. ");
    out.println("    </p>");

    out.println("    <h3>Patient Fields Received</h3>");
    printCompleteness(patientRequired, "Required");
    out.println("    <br>");
    printCompleteness(patientExpected, "Expected");
    out.println("    <br>");
    printCompleteness(patientRecommended, "Recommended");
    out.println("    <br>");
    printCompleteness(patientOptional, "Optional");
    out.println("    <br>");
    printCompletenessScoring(patientRequired, patientExpected, patientRecommended, messageBatch.getCompletenessPatientScore());

    out.println("    <h3>Vaccination Fields Received</h3>");
    printCompleteness(vaccinationRequired, "Required");
    out.println("    <br>");
    printCompleteness(vaccinationExpected, "Expected");
    out.println("    <br>");
    printCompleteness(vaccinationRecommended, "Recommended");
    out.println("    <br>");
    printCompleteness(vaccinationOptional, "Optional");
    out.println("    <br>");
    printCompletenessScoring(vaccinationRequired, vaccinationExpected, vaccinationRecommended, messageBatch.getCompletenessVaccinationScore());
  }

  private void printCompletenessScoring(ScoringSet required, ScoringSet expected, ScoringSet recommended, int score)
  {
    out.println("    <table>");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Fields</th>");
    out.println("        <th>Score</th>");
    out.println("        <th>Description</th>");
    out.println("        <th>Weight</th>");
    out.println("      </tr>");
    printScoreAndWeight(required, "Required");
    printScoreAndWeight(expected, "Expected");
    printScoreAndWeight(recommended, "Recommended");
    boolean showRed = score < 70;
    out.println("      <tr>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">Overall</td>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">" + score + "</td>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">" + getScoreDescription(score) + "</td>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">&nbsp;</td>");
    out.println("      </tr>");
    out.println("    </table>");
  }

  private void printScoreAndWeight(ScoringSet ss, String label)
  {
    int score = (int) (100 * ss.getScore() + 0.5);
    boolean showRed = score < 70;
    out.println("      <tr>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">" + label + "</th>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">" + score + "</th>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">" + getScoreDescription(score) + "</th>");
    out.println("        <td" + (showRed ? " class=\"alert\"" : "") + ">" + (int) (100 * ss.getWeight() + 0.5) + "</th>");
    out.println("      </tr>");
  }

  private void printCompleteness(ScoringSet scoringSet, String label)
  {
    List<CompletenessRow> completenessRowList = scoringSet.getCompletenessRow();
    out.println("    <table width=\"370\">");
    out.println("      <tr>");
    out.println("        <th width=\"40%\" align=\"left\">" + label + "</th>");
    out.println("        <th width=\"20%\" align=\"center\">Count</th>");
    out.println("        <th width=\"20%\" align=\"center\">Percent</th>");
    if (scoringSet.getWeight() > 0)
    {
      out.println("        <th width=\"20%\" align=\"center\">Weight</th>");
    }
    out.println("      </tr>");
    for (CompletenessRow completenessRow : completenessRowList)
    {
      if (completenessRow.getScoreWeight() > 0 || completenessRow.getCount() > 0)
      {
        print(completenessRow);
      }
    }
    out.println("    </table>");
  }

  private void printTitleBar(MessageBatch messageBatch)
  {
    out.println("    <h1>" + profile.getOrganization().getOrgLabel() + " Quality Report</h1>");
    out.println("    <table width=\"750\">");
    out.println("      <tr>");
    out.println("        <th>Batch Title</th>");
    out.println("        <th>Batch Type</th>");
    out.println("        <th>Profile</th>");
    if (filename != null)
    {
      out.println("        <th>File Name</th>");
    }
    out.println("        <th>Received</th>");
    out.println("      </tr>");
    if (filename != null)
    {
      printRow(messageBatch.getBatchTitle(), messageBatch.getBatchType().getTypeLabel(), profile.getProfileLabel(),
          filename, makeDateRange(messageBatch.getStartDate(), messageBatch.getEndDate()));
    } else
    {
      printRow(messageBatch.getBatchTitle(), messageBatch.getBatchType().getTypeLabel(), profile.getProfileLabel(),
          makeDateRange(messageBatch.getStartDate(), messageBatch.getEndDate()));

    }
    out.println("    </table>");
    out.println("    <p>");
    out.println("      Immunization data quality is measured in three main areas: ");    
    out.println("      Timeliness, Quality and Completeness.  ");    
    out.println("      Timeliness measures how quickly administered vaccinations are ");    
    out.println("      reported. ");    
    out.println("      Quality measures how accurate patient and vaccination data is ");    
    out.println("      recorded and whether it reflects expected practice.");
    out.println("      Completeness measures if fields necessary for registry functions ");    
    out.println("      been valued. ");    
    out.println("    </p>");
    out.println("    <br>");
    out.println("    <table width=\"350\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Measurement</th>");
    out.println("        <th align=\"center\">Score</th>");
    out.println("        <th align=\"center\">Description</th>");
    out.println("        <th align=\"center\">Weight</th>");
    out.println("      </tr>");
    printScore(SCORE_COMPLETENESS, messageBatch.getCompletenessScore(), "50%");
    printScore(SCORE_QUALITY, messageBatch.getQualityScore(), "40%");
    printScore(SCORE_TIMELINESS, messageBatch.getTimelinessScore(), "10%");
    printScore(SCORE_OVERALL, messageBatch.getOverallScore(), "&nbsp;");
    out.println("    </table>");
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

  private void printQuality()
  {
    out.println("    <h2>Quality</h2>");
    out.println("    <p>");
    out.println("      Quality  measures the number of errors and warnings that are encountered");
    out.println("      during processing. Total errors registry must account for less than ");
    out.println("      one percent of total number of ");
    out.println("      patients and vaccinations. ");
    out.println("      Total warnings registered are expected to account for ");
    out.println("      less than ten percent of the total patients and vaccinations. ");
    out.println("    </p>");
    if (messageBatchManager.getErrorIssues().size() > 0)
    {
      out.println("    <h3>Errors</h3>");
      printBatchIssues(messageBatchManager.getErrorIssues());
    }
    if (messageBatchManager.getWarnIssues().size() > 0)
    {
      out.println("    <h3>Warnings</h3>");
      printBatchIssues(messageBatchManager.getWarnIssues());
    }
    if (messageBatchManager.getSkipIssues().size() > 0)
    {
      out.println("    <h3>Skips</h3>");
      printBatchIssues(messageBatchManager.getSkipIssues());
    }
    if (messageBatchManager.getInvalidCodes().size() > 0)
    {
      out.println("    <h3>Invalid Codes</h3>");
      printCodeReceived(messageBatchManager.getInvalidCodes());
    }
    if (messageBatchManager.getUnrecognizedCodes().size() > 0)
    {
      out.println("    <h3>Unrecognized Codes</h3>");
      printCodeReceived(messageBatchManager.getUnrecognizedCodes());
    }
    if (messageBatchManager.getDeprecatedCodes().size() > 0)
    {
      out.println("    <h3>Deprecated Codes</h3>");
      printCodeReceived(messageBatchManager.getDeprecatedCodes());
    }
  }

  private void printTimeliness(MessageBatch messageBatch)
  {
    out.println("    <h2>Timeliness</h2>");
    out.println("    <p>");
    out.println("      Timeliness measures the number of days between the");
    out.println("      date a message was received and the most recent administered vaccination");
    out.println("      indicated in that message. ");
    out.println("      Submitters should send administered vaccinations as soon as possible after");
    out.println("      administration, normally once a week. ");
    out.println("    </p>");
    out.println("    <table width=\"350\">");
    out.println("      <tr>");
    out.println("        <th align=\"left\">Vaccination Received</th>");
    out.println("        <th align=\"center\">Count</th>");
    out.println("        <th align=\"center\">Percent</th>");
    out.println("      </tr>");
    printPer(TIMELINESS_WITHIN_2_DAYS, messageBatch.getTimelinessCount2Days(), messageBatch.getMessageWithAdminCount());
    printPer(TIMELINESS_WITHIN_7_DAYS, messageBatch.getTimelinessCount7Days(), messageBatch.getMessageWithAdminCount());
    printPer(TIMELINESS_WITHIN_30_DAYS, messageBatch.getTimelinessCount30Days(),
        messageBatch.getMessageWithAdminCount());
    out.println("    </table>");
    out.println("    <br>");
    out.println("    <table>");
    if (messageBatch.getTimelinessDateFirst() != null)
    {
      out.println("      <tr>");
      out.println("        <th align=\"left\">Earliest Date</th>");
      out.println("        <td>" + dateOnly.format(messageBatch.getTimelinessDateFirst()) + "</th>");
      out.println("      </tr>");
    }
    if (messageBatch.getTimelinessDateLast() != null)
    {
      out.println("      <tr>");
      out.println("        <th align=\"left\">Latest Date</th>");
      out.println("        <td>" + dateOnly.format(messageBatch.getTimelinessDateLast()) + "</th>");
      out.println("      </tr>");
    }
    out.println("      <tr>");
    out.println("        <th align=\"left\">Average Days</th>");
    DecimalFormat df = new DecimalFormat("0.0");
    out.println("        <td>" + df.format(messageBatch.getTimelinessAverage()) + "</th>");
    out.println("      </tr>");
    out.println("    </table>");
    out.println("    <h3>Scoring</h3>");
  }

  private void printScore(ToolTip tip, int score, String weight)
  {
    printRow(tip, score < 70, String.valueOf(score), getScoreDescription(score), weight);
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
    printRow(toolTip, outOfRange, String.valueOf(value), percent);
  }

  private void print(CompletenessRow row)
  {
    ToolTip toolTip = row.getToolTip();
    int value = row.getCount();
    int denominator = row.getDenominator();
    String percent = "&nbsp;";
    boolean outOfRange = false;
    if (row.getDenominator() > 0)
    {
      if (value == 0)
      {
        percent = "-";
      } else
      {
        int per = (int) ((100.0 * value) / denominator + 0.5);
        if (per < row.getOkayLow() || per > row.getOkayHigh())
        {
          outOfRange = true;
        }
        percent = String.valueOf(per) + "%";
      }
    }
    DecimalFormat df = new DecimalFormat("0.0");
    if (row.getScoreWeight() == 0)
    {
      printRow(toolTip, outOfRange, String.valueOf(value), percent);
    } else
    {
      printRow(toolTip, outOfRange, String.valueOf(value), percent, df.format(row.getScore()));
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
    out.println("      <tr>");
    out.println("        <td align=\"left\"" + (showRed ? " class=\"alert\"" : "") + ">" + tip.getHtml());
    out.println("        </td>");
    for (int i = 0; i < fields.length; i++)
    {
      out.println("        <td align=\"center\"" + (showRed ? " class=\"alert\"" : "") + ">" + fields[i] + "</td>");
    }
    out.println("      </tr>");
  }

  private void printCss()
  {
    out.println("    <style><!--");
    out.println("      body {font-family: Tahoma, Geneva, Sans-serif}");
    out.println("      p {width:700px; color:#2B3E42; background:#F7F3E8; padding:6px; border-style:dashed; border-width:1px; border-color:#2B3E42}");
    out.println("      h1 {color:#2B3E42}");
    out.println("      table {background:#D5E1DD; border-style:solid; border-width:1; border-color:#2B3E42; border-collapse:collapse}");
    out.println("      th {background:#77BED2; font-size:0.8em; color:#2B3E42: border-style:none; padding-left:5px; padding-right:5px;}");
    out.println("      td {border-style:solid; border-width:1; border-color:#747E80;margin:0px; padding-left:5px; padding-right:5px;}");
    out.println("      .alert {background: #F2583E}");
    out.println("      a:link {text-decoration:none; color:#350608}");
    out.println("      a:visited {text-decoration:none; color:#350608}");
    out.println("      a:hover {text-decoration:none; color:#350608} ");
    out.println("      a:active {text-decoration:none; color:#350608} ");
    out.println("      a.tooltip span {display:none; padding:2px 3px; margin-left:8px; width:130px;}");
    out.println("      a.tooltip:hover span{display:inline; position:absolute; background:#F7F3E8; border:1px solid #CCCCCC; color:#6C6C6C}");
    out.println("    --></style>");
  }

  private String getScoreDescription(int score)
  {
    if (score >= 90)
    {
      return "Excellent";
    } else if (score >= 80)
    {
      return "Good";
    } else if (score >= 70)
    {
      return "Okay";
    } else if (score >= 60)
    {
      return "Poor";
    } else
    {
      return "Problem";
    }
  }

}
