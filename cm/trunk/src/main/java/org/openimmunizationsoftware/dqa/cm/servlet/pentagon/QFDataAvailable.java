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

public class QFDataAvailable extends PentagonBox
{
  public QFDataAvailable() {
    super(BOX_NAME_QF_DATA_AVAILABLE);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Submitting systems depend on the IIS to store all data submitted and return it when queried. "
        + "IIS that do not return data when queried will lose the confidence and support of submitters which will lead to further "
        + "loss of data completeness and credibility of the IIS. This test verifies that data that was submitted and accepted by the IIS "
        + "is available when queried.</p>");

  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Data Not Returned</h3>");
      Query query = dataSession.createQuery("from TestMessage where testSection.testConducted = ? and testCaseAssertResult = 'MATCH' "
          + "and testType = 'query' and resultStoreStatus = 'a-nr' order by testCaseCategory");
      query.setParameter(0, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFailForQuery(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Data Returned</h3>");
      out.println("<p class=\"pentagon\">Test cases where data was returned are not shown. </p>");
    }

  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">For every query submitted during testing where:</p>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">The original VXU message(s) were accepted, and </li>");
    out.println("  <li class=\"pentagon\">The query test case expected to receive back an exact match, and </p>");
    out.println("  <li class=\"pentagon\">The IIS returned the essential patient and vaccination information submitted </p>");
    out.println("</li>");
    List<Object[]> objectsList = doCounts(testConducted, dataSession);
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
        out.println("    <td class=\"pentagon\">" + (long) objects[1] + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
    out.println("<p class=\"pentagon\">The following table shows the counts of the number of messages that returned data in comparison </p>");
    out.println("<p class=\"pentagon\">This test expects that the IIS is able to return essential information include basic patient information "
        + "and the basic information for every vaccination submitted. "
        + "Therefore this test does not verify that every field submitted in the original VXU is returned. Because IIS have varying "
        + "policies on the amount of details returned this test does not explore these policy differences. Rather it assumes that "
        + "at the IIS must only return the core patient identify informatin and basic immunization history. </p>");
  }

  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    List<Object[]> objectsList = doCounts(testConducted, dataSession);
    long countTotal = 0;
    long countNoMatch = 0;
    for (Object[] objects : objectsList)
    {
      if (((String) objects[0]).equals("a-nr"))
      {
        countNoMatch = (long) objects[1];
      }
      countTotal += (long) objects[1];
    }
    if (countTotal > 0)
    {
      pentagonReport.setScoreQFDataAvailable((int)( 100.0 * countNoMatch / countTotal));
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
}
