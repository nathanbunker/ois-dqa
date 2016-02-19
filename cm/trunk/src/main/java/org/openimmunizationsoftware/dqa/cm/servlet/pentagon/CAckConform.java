package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class CAckConform extends PentagonBoxHelper
{
  public CAckConform(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Acknowledgment messages returned should conform to the CDC Implementation Guide and HL7 requirements. "
        + "Non-conformant messages may be incorrectly read and understood by submitting systems including this testing system. "
        + "Confidence in the results of this report increases when acknowledgments are properly formatted. "
        + "Conformance is verified by " + createExternalLink("http://hl7v2-iz-r1.5-testing.nist.gov/iztool/#/home", "NIST Immunization Test Suite") + ".</p>"
        + "<p class=\"pentagon\">The purpose of this section is to measure the ACK message for conformance. Two distinct set of test cases are used for this purpose."
        + "<ol class=\"pentagon\"><li>VXU messages most IIS accept and result in \"positive\" ACK messages.</li> "
        + "<li>VXU messages with intentional errors most IIS reject and result in \"negative\" ACK messages.</li></ol></p>"
        + "<p class=\"pentagon\">Despite distinct differences in the two types test cases, this section does not measure if an IIS accepted a good message or reject a bad message."
        + "It merely measures the ACK message against the standard via the NIST Immunization Test Suite. "
        + "Acceptance of good messages and rejection of bad messages are measured in other sections.</p>"
        + "<p class=\"pentagon\">Please note that this section measures a similar concept to "
        + "Update Conformance below, but with a different purpose and scoring metric and test cases.</p> ");
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    int countOk = 0;
    int countTotal = 0;
    List<TestMessage> testMessageList = null;

    // Get the data.
    Query query = dataSession.createQuery("from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) "
        + "and testSection.testConducted = ? and testType = 'update' order by testSection.testSectionType, test_case_category ");
    query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
    query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
    query.setParameter(2, pentagonReport.getTestConducted());
    testMessageList = query.list();

    // Determine what level of validation was done
    for (TestMessage testMessage : testMessageList)
    {
      if (testMessage.getResultAckConformance() != null
          && !testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
      {
        countTotal++;
        if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
        {
          countOk++;
        }
      }
    }

    if (countTotal < testMessageList.size())
    {
      out.println("<h4 class=\"pentagon\">Not Tested - Problem Validating for Conformance</h4>");
      out.println("<p class=\"pentagon\">The following test cases incurred problems when validating.<br>Click on each test case for further details.</p>");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Case</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        if (testMessage.getResultAckConformance() == null ||
            testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
        {
          out.println("  <tr class=\"pentagon\">");
          out.println("    <td class=\"pentagon\">" + createAjaxLink(testMessage, userSession) + "</td>");
          out.println("  </tr>");
        }
      }
      out.println("</table>");
    }

    if (pentagonBox.getReportScore() < 100 && countTotal > 0)
    {
      out.println("<h4 class=\"pentagon\">Fail - ACK Message Conformance Problems</h4>");
      out.println("<p class=\"pentagon\">The following test cases had ACK messages which failed NIST 2015 Conformance."
          + "<br>Click on each test case for further details.</p>");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Case</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        if (testMessage.getResultAckConformance() != null
            && !testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
        {
          if (!testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
          {
            out.println("  <tr class=\"pentagon\">");
            out.println("    <td class=\"pentagon\">" + createAjaxLink(testMessage, userSession) + "</td>");
            out.println("  </tr>");
          }
        }
      }
      out.println("</table>");
    }
    
    if (countOk > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - Conformant ACK Messages</h4>");
      out.println("<p class=\"pentagon\">The following test cases had ACK messages which passed NIST 2015 conformance. "
          + "<br>Click on each test case for further details.</p>");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Case</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        if (testMessage.getResultAckConformance() != null
            && !testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
        {
          if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
          {
            out.println("  <tr class=\"pentagon\">");
            out.println("    <td class=\"pentagon\">" + createAjaxLink(testMessage, userSession) + "</td>");
            out.println("  </tr>");
          }
        }
      }
      out.println("</table>");
    }
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Each ACK message returned is checked for conformance "
        + "against the National Institute of Standards and Technology (NIST) Immunization Test Suite. "
        + "The final score for this section is the number of conformant ACK messages divided by the total number of test cases.</p>" );
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC) != null
        && testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED) != null)
    {
      int countOk = 0;
      int countTotal = 0;
      TestSection testSectionBasic = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      TestSection testSectionNotAccepted = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      Query query = dataSession.createQuery("from TestMessage where (testSection = ? or testSection = ?) and testType = 'update' ");
      query.setParameter(0, testSectionBasic);
      query.setParameter(1, testSectionNotAccepted);
      List<TestMessage> testMessageList = query.list();
      for (TestMessage testMessage : testMessageList)
      {
        if (testMessage.getResultAckConformance() != null
            && !testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
        {
          countTotal++;
          if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
          {
            countOk++;
          }
        }
      }
      if (countTotal != 0)
      {
        pentagonBox.setReportScore(((int) 100.0 * countOk / countTotal));
      }
    }

  }

  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Updating the ACK message so that it conforms to the CDC Guide will help improve confidence in understanding if an IIS accepted a VXU message. "
        + "Focus on the ACK conformance problems that appear the most often first. Be sure to verify the ACK messages using the " + createExternalLink("http://hl7v2-iz-r1.5-testing.nist.gov/iztool/#/home", "NIST Immunization Test Suite") +".</p>");
  }
}
