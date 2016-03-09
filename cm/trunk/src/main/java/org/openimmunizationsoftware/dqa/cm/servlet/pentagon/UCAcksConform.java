package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.logic.AssertionFieldLogic;
import org.openimmunizationsoftware.dqa.tr.logic.ConformanceCount;
import org.openimmunizationsoftware.dqa.tr.logic.PentagonReportLogic;
import org.openimmunizationsoftware.dqa.tr.model.AssertionField;
import org.openimmunizationsoftware.dqa.tr.model.AssertionIdentified;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class UCAcksConform extends PentagonBoxHelper
{
  public UCAcksConform(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The National Institute of Standards and Technology (NIST) has an Immunization Test Suite "
        + "for testing and verifying the format of HL7 messages used in immunization message. In cooperation with CDC and AIRA, "
        + "NIST has carefully reviewed the HL7 Standards and the CDC Guide to identify exactly how acknowledgement messags should "
        + "be formatted. This score reflects how many error level issues were identified in each acknowledgement message. </p>");
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() >= 100)
    {
      out.println("<h3 class=\"pentagon\">All Acks Conform</h3>");
    } else
    {
      printContentsConformanceProblems(out, dataSession, pentagonReport, "update");
    }
  }

  protected static void printContentsConformanceProblems(PrintWriter out, Session dataSession, PentagonReport pentagonReport, String testType)
  {
    out.println("<h4 class=\"pentagon\">Conformance Problems Found</h4>");
    List<AssertionIdentified> assertionIdentifiedList = AssertionFieldLogic.getAssertionIdentifiedListForErrors(dataSession, pentagonReport,
        testType);

    out.println("<table class=\"pentagon\">");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Count</th>");
    out.println("    <th class=\"pentagon\">Description</th>");
    out.println("  </tr>");
    for (AssertionIdentified assertionIdentified : assertionIdentifiedList)
    {
      AssertionField assertionField = assertionIdentified.getAssertionField();
      out.println("  <tr class=\"pentagon\">");
      out.println("    <td class=\"pentagon\">" + assertionIdentified.getAssertionCount() + "</td>");
      out.println("    <td class=\"pentagon\"><a class=\"pentagonTestMessageFail\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
          + assertionIdentified.getTestMessage().getTestMessageId() + "', '" + assertionIdentified.getTestMessage().getTestCaseDescription()
          + "');\">" + assertionField.getAssertionDescription() + "</a></td>");
      out.println("  </tr>");
    }
    out.println("</table>");
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    String testType = "update";
    printScoreExplanation(out, dataSession, pentagonReport, testType);
  }

  public static void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, String testType)
  {

    out.println("<p class=\"pentagon\">The score is determined by evaluating the number of errors identified by the NIST validator. </p>");
    out.println("<h4 class=\"pentagon\">Step 1: Set Maximum Score Possible</h4>");
    ConformanceCount conformanceCount = PentagonReportLogic.getConformanceCounts(pentagonReport, dataSession, testType);
    out.println("<p class=\"pentagon\"> Determine the percentage of tests where conformance was checked. </p>");
    out.println("<table class=\"pentagon\">");
    out.println("  <tr>");
    out.println("    <th class=\"pentagon\">Status</th>");
    out.println("    <th class=\"pentagon\">Count</th>");
    out.println("    <th class=\"pentagon\">Percent</th>");
    out.println("    <th class=\"pentagon\">Description</th>");
    out.println("  </tr>");
    if (conformanceCount.getCountTotal() > 0)
    {
      out.println("  <tr>");
      out.println("    <td class=\"pentagon\">Ok</td>");
      out.println("    <td class=\"pentagon\">" + conformanceCount.getCountOk() + "</td>");
      out.println("    <td class=\"pentagon\">" + conformanceCount.getCountOkPercentage() + "</td>");
      out.println("    <td class=\"pentagon\">No conformance errors identified</td>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <td class=\"pentagon\">Error</td>");
      out.println("    <td class=\"pentagon\">" + conformanceCount.getCountError() + "</td>");
      out.println("    <td class=\"pentagon\">" + conformanceCount.getCountErrorPercentage() + "</td>");
      out.println("    <td class=\"pentagon\">At least one conformance error identified</td>");
      out.println("  </tr>");
      if (conformanceCount.getCountNotRun() > 0)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\">Not Run</td>");
        out.println("    <td class=\"pentagon\">" + conformanceCount.getCountNotRun() + "</td>");
        out.println("    <td class=\"pentagon\">" + conformanceCount.getCountNotRunPercentage() + "</td>");
        out.println("    <td class=\"pentagon\">There was a problem reading conformance results from NIST or testing was not configured to run</td>");
        out.println("  </tr>");
      }
    }
    out.println("  <tr class=\"pentagon\">");
    out.println("    <td class=\"pentagon\">Total</td>");
    out.println("    <td class=\"pentagon\">" + conformanceCount.getCountTotal() + "</td>");
    out.println("  </tr>");
    out.println("</table>");
    out.println("<p class=\"pentagon\">Conformance was checked on " + conformanceCount.getCountRunPercentage()
        + " of messages, this is the maximum score possible. </p>");
    out.println("<h4 class=\"pentagon\">Step 2: Reduce Score for Errors</h4>");
    out.println("<p class=\"pentagon\">Now the final score is reduced by up to 20% for every conformance item that is identified. "
        + "The reduction percentage is determined by how many times the error was identified in test messages. "
        + "If the error is identified as many or more times than the number of messags checked the score is reduced by a full 20%. "
        + "(It is possible for an error to be found " + "more than once in a message especially if the problem is in a repeating segment.) "
        + "If the number of errors is 50% of the total then the reduction will be 10%. This means that errors that regularly appear will "
        + "significantly reduce the score, while rare errors will not. </p>");
    out.println("<p class=\"pentagon\">Below shows the breakdown of how the score was determined: </p>");
    out.println("<table class=\"pentagon\">");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Error Count</th>");
    out.println("    <th class=\"pentagon\">Percentage</th>");
    out.println("    <th class=\"pentagon\">Deduction</th>");
    out.println("    <th class=\"pentagon\">Score</th>");
    out.println("  </tr>");
    double scoreDouble = 100.0;
    if (conformanceCount.getCountNotRun() > 0)
    {
      scoreDouble = scoreDouble
          * ((conformanceCount.getCountTotal() - conformanceCount.getCountNotRun()) / (double) conformanceCount.getCountTotal());
    }
    NumberFormat formatter1 = new DecimalFormat("#0.0");
    NumberFormat formatter2 = new DecimalFormat("#0.000");
    List<AssertionIdentified> assertionIdentifiedList = AssertionFieldLogic.getAssertionIdentifiedListForErrors(dataSession, pentagonReport,
        testType);
    for (AssertionIdentified assertionIdentified : assertionIdentifiedList)
    {
      double percentage = 1.0;
      if (assertionIdentified.getAssertionCount() < conformanceCount.getCountTotal())
      {
        percentage = assertionIdentified.getAssertionCount() / (double) conformanceCount.getCountTotal();
      }
      double deduction = percentage * 0.2 * scoreDouble;
      scoreDouble = scoreDouble - deduction;
      out.println("  <tr class=\"pentagon\">");
      out.println("    <td class=\"pentagon\">" + assertionIdentified.getAssertionCount() + "</td>");
      out.println("    <td class=\"pentagon\">" + formatter1.format(percentage * 100) + "%</td>");
      out.println("    <td class=\"pentagon\">" + formatter2.format(deduction) + "%</td>");
      out.println("    <td class=\"pentagon\">" + formatter1.format(scoreDouble) + "%</td>");
      out.println("  </tr>");
    }
    out.println("</table>");
    out.println("<h4 class=\"pentagon\">Step 3: Final Score</h4>");
    out.println("<p class=\"pentagon\">This process yields a score of " + formatter1.format(scoreDouble)
        + "% which is rounded down to the nearest whole number for a final score of " + ((int) scoreDouble) + "%.</p>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();
    TestConducted testConducted = pentagonReport.getTestConducted();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015) != null)
    {
      String testType = "update";
      calculateScore(testConducted, dataSession, pentagonReport, testType);
    }
  }

  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, String testType)
  {
    ConformanceCount conformanceCount = PentagonReportLogic.getConformanceCounts(pentagonReport, dataSession, testType);
    if (conformanceCount.getCountTotal() > 0)
    {
      double scoreDouble = 100.0;
      if (conformanceCount.getCountNotRun() > 0)
      {
        scoreDouble = scoreDouble
            * ((conformanceCount.getCountTotal() - conformanceCount.getCountNotRun()) / (double) conformanceCount.getCountTotal());
      }
      List<AssertionIdentified> assertionIdentifiedList = AssertionFieldLogic.getAssertionIdentifiedListForErrors(dataSession, pentagonReport,
          testType);
      for (AssertionIdentified assertionIdentified : assertionIdentifiedList)
      {
        double percentage = 1.0;
        if (assertionIdentified.getAssertionCount() < conformanceCount.getCountTotal())
        {
          percentage = assertionIdentified.getAssertionCount() / (double) conformanceCount.getCountTotal();
        }
        double deduction = percentage * 0.2 * scoreDouble;
        scoreDouble = scoreDouble - deduction;
      }
      pentagonBox.setReportScore(((int) scoreDouble));
    }
  }

  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Update the IIS ACK message so that it conforms to the CDC Guide. "
        + "Focus on the errors that appear the most often. Be sure to verify the response messages using the NIST test site. </p>");
  }
}
