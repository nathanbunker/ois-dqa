package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class CAckConform extends PentagonBox
{
  public CAckConform() {
    super(BOX_NAME_C_ACK_CONFORM);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Acknowledgment messages returned should conform to the CDC Implementation Guide and HL7 requirements. "
        + "Non-conformant messages may be incorrectly read by the testing system. "
        + "Confidence in the results of this report increases when acknowledgments are properly formatted. "
        + "Conformance is verified by NIST software. Please note that this section measures a similar concept to "
        + "Update Conformance: Acks Conform, but with a different purpose and scoring metric. In this section "
        + "each message is expected to return a conforming ACK without any errors. </p> ");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score >= 100)
    {
      out.println("<h3 class=\"pentagon\">Acks Conform</h3>");
    } else
    {
      out.println("<h3 class=\"pentagon\">Conformance Problems Found</h3>");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Accepted</th>");
      out.println("    <th class=\"pentagon\">Test Case</th>");
      out.println("  </tr>");
      int countOk = 0;
      int countTotal = 0;
      Query query = dataSession.createQuery("from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) "
          + "and testSection.testConducted = ? and testType = 'update' order by testSection.testSectionType, test_case_category ");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(2, testConducted);
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
          } else
          {
            out.println("  <tr class=\"pentagon\">");
            if (testMessage.isResultAccepted())
            {
              out.println("    <td class=\"pentagon\" style=\"text-align: center; \">Yes</td>");
            } else
            {
              out.println("    <td class=\"pentagon\" style=\"text-align: center; \">No</td>");
            }
            out.println("    <td class=\"pentagon\">" + createLink(testMessage) + "</td>");
            out.println("  </tr>");
          }
        }
      }
      out.println("</table>");
      out.println("<h3 class=\"pentagon\">Acks Conform</h3>");
      if (countOk == 0)
      {
        out.println("<p class=\"pentagon\">None of the acknowledgement messages conform. </p>");
      } else
      {
        out.println("<table class=\"pentagon\">");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Accepted</th>");
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
              if (testMessage.isResultAccepted())
              {
                out.println("    <td class=\"pentagon\" style=\"text-align: center; \">Yes</td>");
              } else
              {
                out.println("    <td class=\"pentagon\" style=\"text-align: center; \">No</td>");
              }
              out.println("    <td class=\"pentagon\">" + createLink(testMessage) + "</td>");
              out.println("  </tr>");
            }
          }
        }
        out.println("</table>");
      }
      if (countTotal == 0)
      {
        out.println("<h3 class=\"pentagon\">Problem Validating for Conformance</h3>");
        out.println("<p class=\"pentagon\">Score can not be calculated because tests were not validated for conformance. </p>");
      } else if (countTotal < testMessageList.size())
      {
        out.println("<h3 class=\"pentagon\">Problem Validating for Conformance</h3>");
        out.println("<p class=\"pentagon\">" + (testMessageList.size() - countTotal) + "/" + testMessageList.size()
            + " were not validated for conformance.</p>");
      }

    }
  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Each acknowledgement message returned for Good and Bad messages is check for conformance "
        + "against the National Institute of Standards and Technology (NIST) immunization validation service. "
        + "Each message that has has not error conformance identified are included in the passing list. "
        + "The score final score for this section is the percentage of passing divided by the total number of messages.  </p>");
    out.println("<h4 class=\"pentagon\">How To Improve Score</h4>");
    out.println("<p class=\"pentagon\">Update the IIS ACK message so that it conforms to the CDC Guide. "
        + "Focus on the errors that appear the most often. Be sure to verify the response messages using the " + "NIST test site. </p>");
  }

  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
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
        pentagonReport.setScoreCAckConform(((int) 100.0 * countOk / countTotal));
      }
    }

  }
}
