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

public class CGoodData extends PentagonBox
{
  public CGoodData() {
    super(BOX_NAME_C_GOOD_DATA);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Messages that were accepted by the IIS should return that data when queried. This test verifies that patient "
        + "was accepted and that all the vaccinations submitted are returned. It does not check every field submitted, rather "
        + "it verifies basic patient information is returned and that basic vaccination information was returned. "
        + "This measure helps to determine if the positive acknowledgement is accurate or not. </p> ");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Data NOT Returned for Accepted Message</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
              + "and resultStoreStatus = 'a-nr' and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(2, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Data Returned for Accepted Message</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
              + "and resultStoreStatus = 'a-r' and testType = 'query' order by testCaseCategory");
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
        + "go beyond this primary responsibility. However this test verifies that if the IIS indicated positive acceptance that the "
        + "basic patient and vaccination information was accepted and can be returned in a query. </p>");
    out.println("<p class=\"pentagon\">To verify this, the testing process compares the original update message with the query response and "
        + "confirms that certain fields are returned. </p>");
    printCalculatingEssentialDataReturnedExplanation(out);
    out.println("<p class=\"pentagon\">The score is the percentage of accepted messages (whether good or bad) "
        + "that could retrieved via a query and had the basic data as shown above.</p>");
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
          if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED))
          {
            count++;
            countPass++;
          } else if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_NOT_RETURNED))
          {
            count++;
          }
        }
      }
    }
    if (count > 0)
    {
      pentagonReport.setScoreCGoodData((int) (100.0 * countPass / count));
    }
  }
}
