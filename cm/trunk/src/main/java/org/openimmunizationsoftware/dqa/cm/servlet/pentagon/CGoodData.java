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

public class CGoodData extends PentagonBoxHelper
{
  public CGoodData(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">This section is based on the premise that the primary responsibility of an IIS is to store "
        + "patient and vaccination history in order to capture a complete picture of a patient. IIS have many other functions that "
        + "go beyond this primary responsibility. However this test verifies that if the IIS indicated positive acceptance that the "
        + "basic patient and vaccination information was accepted and can be returned in a query. Data that was assumed to be accepted "
        + "based on the ACK message, but is unable to be returned through a query lowers confidence in the HL7 interface.</p>");
    out.println("<p class=\"pentagon\">To verify this, the testing process compares the original update message with the query response and "
        + "confirms that certain fields are returned. </p>");
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h4 class=\"pentagon\">Fail - Data NOT Returned for Accepted Message</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
              + "and resultStoreStatus = 'a-nr' and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(2, pentagonReport.getTestConducted());
      @SuppressWarnings("unchecked")
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFailForQuery(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - Data Returned for Accepted Message</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
              + "and resultStoreStatus = 'a-r' and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(2, pentagonReport.getTestConducted());
      @SuppressWarnings("unchecked")
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPassForQuery(out, testMessageList);
    }
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    printCalculatingEssentialDataReturnedExplanation(out);
    out.println("<p class=\"pentagon\">The score is the percentage of accepted messages "
        + "that could retrieved via a query and had the basic data as shown above.</p>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();
    int count = 0;
    int countPass = 0;
    for (String sectionNames : new String[] { RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC,
        RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED })
    {
      TestSection testSection = testSectionMap.get(sectionNames);
      Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
      query.setParameter(0, testSection);
      @SuppressWarnings("unchecked")
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
      pentagonBox.setReportScore((int) (100.0 * countPass / count));
    }
  }

  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Review process for acknowledging messages to ensure that if important vaccination or patient information is "
        + "not accepted by the IIS that this is reflected in the acknowledgement message when possible. The test cases selected for this section "
        + "were especially chosen as ones that either ought to be accepted or had such problems that it is reasonable to assume that an IIS should "
        + "identify these upon receipt and return an acknowledgment indicating the message that the submitter should correct and resend. </p>");
  }
}
