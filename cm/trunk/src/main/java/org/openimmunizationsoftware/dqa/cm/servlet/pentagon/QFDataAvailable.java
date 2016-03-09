package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

public class QFDataAvailable extends PentagonBoxHelper
{
  public QFDataAvailable(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Submitting systems depend on the IIS to store all data submitted and return it when queried. "
        + "IIS that do not return data when queried will lose the confidence and support of submitters which will lead to further "
        + "loss of data completeness and credibility of the IIS. This test verifies that data that was submitted and accepted by the IIS "
        + "is available when queried.</p>");

  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The following table summarizes the results of the test cases with failed test cases detailed below. "
              + "Due to the high volume of test cases, passed test cases are not displayed, but can be retrieved upon request.</p>");
    List<Object[]> objectsList = doCounts(pentagonReport.getTestConducted(), dataSession);
    if (objectsList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Count</th>");
      out.println("  </tr>");
      for (Object[] objects : objectsList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\">" + TestMessage.getResultStoreStatusForDisplay((String) objects[0]) + "</td>");
        Long l = (Long) objects[1];
        out.println("    <td class=\"pentagon\">" + l + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }

    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h4 class=\"pentagon\">Fail - Accepted Data Not Returned</h4>");
      Query query = dataSession.createQuery("from TestMessage where testSection.testConducted = ? and testCaseAssertResult = 'MATCH' "
          + "and testType = 'query' and resultStoreStatus = 'a-nr' order by testCaseCategory");
      query.setParameter(0, pentagonReport.getTestConducted());
      @SuppressWarnings("unchecked")
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFailForQuery(out, testMessageList);
    }

  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">For every query submitted during testing where:</p>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">The original VXU message(s) were accepted, and </li>");
    out.println("  <li class=\"pentagon\">The query test case expected to receive back an exact match, and </li>");
    out.println("  <li class=\"pentagon\">The IIS returned the essential patient and vaccination information submitted </li>");
    out.println("</ul>");
    printCalculatingEssentialDataReturnedExplanation(out);
    out.println("<p class=\"pentagon\">The score is the percentage of records returning essential data to previously accepted VXU submissions.</p>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    TestConducted testConducted = pentagonReport.getTestConducted();
    List<Object[]> objectsList = doCounts(testConducted, dataSession);
    long countTotal = 0;
    long countMatch = 0;
    for (Object[] objects : objectsList)
    {
      if (((String) objects[0]).equals("a-r"))
      {
        countMatch = (Long) objects[1];
      }
      countTotal += (Long) objects[1];
    }
    if (countTotal > 0)
    {
      pentagonBox.setReportScore((int)( 100.0 * countMatch / countTotal));
    }

  }

  public List<Object[]> doCounts(TestConducted testConducted, Session dataSession)
  {
    Query query = dataSession.createQuery(
        "select resultStoreStatus, count(*) from TestMessage tm where tm.testSection.testConducted = ? and tm.testCaseAssertResult = 'MATCH' "
            + "and tm.testType = 'query' and (tm.resultStoreStatus = 'a-nr' or tm.resultStoreStatus = 'a-r') group by tm.resultStoreStatus");
    query.setParameter(0, testConducted);
    @SuppressWarnings("unchecked")
    List<Object[]> objectsList = query.list();
    return objectsList;
  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Provide full support for both Z34 and Z44 queries. Ensure that full processing - including patient deduplication - "
        + " of a message occurs as soon as the message is received by the IIS. Lag time between acceptance of data and full processing into the IIS can lead "
        + "to erroneous results when queries are unable to find \"accepted\" data.</p>");
  }
}
