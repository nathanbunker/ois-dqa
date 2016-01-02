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
  public void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Submitting systems depend on the IIS to store all data submitted and return it when queried. "
        + "IIS that do not return data when queried will lose the confidence and support of submitters which will lead to further "
        + "loss of data completeness and credibility of the IIS. This test verifies that data that was submitted and accepted by the IIS "
        + "is available when queried.</p>");

  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Data Not Returned</h3>");
      Query query = dataSession.createQuery("from TestMessage where testSection.testConducted = ? and testCaseAssertResult = 'MATCH' "
          + "and testType = 'query' and resultStoreStatus = 'a-nr' order by testCaseCategory");
      query.setParameter(0, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFailForQuery(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Data Returned</h3>");
      out.println("<p class=\"pentagon\">Test cases where data was returned are not shown. </p>");
    }

  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">For every query submitted during testing where:</p>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">The original VXU message(s) were accepted, and </li>");
    out.println("  <li class=\"pentagon\">The query test case expected to receive back an exact match, and </p>");
    out.println("  <li class=\"pentagon\">The IIS returned the essential patient and vaccination information submitted </p>");
    out.println("</ul>");
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
    printCalculatingEssentialDataReturnedExplanation(out);
    out.println("<p class=\"pentagon\">The following table shows the counts of the number of messages that returned data in comparison </p>");
    out.println("<p class=\"pentagon\">This test expects that the IIS is able to return essential information include basic patient information "
        + "and the basic information for every vaccination submitted. "
        + "Therefore this test does not verify that every field submitted in the original VXU is returned. Because IIS have varying "
        + "policies on the amount of details returned this test does not explore these policy differences. Rather it assumes that "
        + "at the IIS must only return the core patient identify informatin and basic immunization history. </p>");
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
    List<Object[]> objectsList = query.list();
    return objectsList;
  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub
    
  }
}
