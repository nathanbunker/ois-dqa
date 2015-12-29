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

public class CBadData extends PentagonBox
{

  public CBadData() {
    super(BOX_NAME_C_BAD_DATA);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Messages that were NOT accepted by the IIS are not expected to return that all data when queried. "
        + " This test expects that something important, like the a vaccine or patient information is not stored in the IIS. "
        + "This test has practical limitations as an IIS may indicate a message was not accepted, but accept it anyways. "
        + "However, doing this reduces the confidence in the meaning of an acknowledgement. Ideally an IIS "
        + "should use the ACK to provide a view of what data was really accept.  </p> ");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Data Returned for NOT Accepted Message</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
              + "and resultStoreStatus = 'na-r' and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(2, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Data NOT Returned for NOT Accepted Message</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
              + "and resultStoreStatus = 'na-nr' and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(2, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">This test is based on the premise that the primary responsibility of an IIS is to store "
        + "patient and vaccination history in order to capture a complete picture of a patient. IIS have many other functions that "
        + "go beyond this primary responsibility. However this test verifies that if the IIS did NOT indicated positive acceptance that either the "
        + "basic patient or vaccination information is not available to returned by query. This test helps to verify the impact and meaning "
        + "when an update is rejected. </p>");
    out.println("<p class=\"pentagon\">To verify this, the testing process compares the original update message with the query response and "
        + "confirms that certain fields are returned. As IIS have varying standards and policies for what data is actually returned, this "
        + "comparison is only done on a few core fields that would reasonably be expected to be returned. The following fields are " + "used: </p>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">Patient");
    out.println("    <ul class=\"pentagon\">");
    out.println("      <li class=\"pentagon\">Last Name</li>");
    out.println("      <li class=\"pentagon\">First Name</li>");
    out.println("      <li class=\"pentagon\">Middle Name (only if sent and also returned)</li>");
    out.println("      <li class=\"pentagon\">Date of Birth</li>");
    out.println("    </ul>");
    out.println("  </li>");
    out.println("  <li class=\"pentagon\">Vaccination (only fully administered, ignoring refusals and history-of-disease, etc.) ");
    out.println("    <ul class=\"pentagon\">");
    out.println("      <li class=\"pentagon\">Administration Date</li>");
    out.println("      <li class=\"pentagon\">Vaccination Code (CVX or NDC)</li>");
    out.println("    </ul>");
    out.println("  </li>");
    out.println("</ul>");
    out.println("<p class=\"pentagon\">The score is the percentage of rejected messages (whether good or bad) "
        + "that could not be completely retrieved via a query.</p>");
    out.println("<h4 class=\"pentagon\">How To Improve Score</h4>");
    out.println("<p class=\"pentagon\">Review process for acknowledging messages to ensure that if important vaccination or patient information is "
        + "not accepted by the IIS that this is reflected in the acknowledgement message when possible. The test cases selected for this section "
        + "were especially chosen as ones that either ought to be accepted or had such problems that it is reasonable to assume that an IIS should "
        + "identify these upon receipt and return an acknowledgment indicating the message that the submitter should correct and resend. </p>");

  }

  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    int count = 0;
    int countPass = 0;
    for (String sectionNames : new String[] { RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC,
        RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED })
    {
      TestSection testSection = testSectionMap.get(sectionNames);
      Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
      query.setParameter(0, testSection);
      List<TestMessage> testMessageList = query.list();
      for (TestMessage testMessage : testMessageList)
      {
        if (testMessage.getResultStoreStatus() != null)
        {
          if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_NOT_ACCEPTED_RETURNED))
          {
            count++;
          } else if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_NOT_ACCEPTED_NOT_RETURNED))
          {
            count++;
            countPass++;
          }
        }
      }
    }
    if (count > 0)
    {
      pentagonReport.setScoreCBadData((int) (100.0 * countPass / count));
    }

  }
}
