package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

public class UFTolerant extends PentagonBoxHelper
{
  public UFTolerant(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">HL7 requires that receiving systems, such as IIS, give a certain amount of tolerance to received message. "
        + "Ideally IIS would always receive perfect and consistent messages, but when it does not, it should still try its best to process "
        + "messages with minor issues. These tests all have minor issues that do not directly affect data quality and which "
        + "IIS would be expected to cope with. Still this section does not assert that IIS ought to accept every tolerant message proposed here. "
        + "A few of these messages may not be accepted by IIS for good reasons. IIS should review closely the ones they do not accept and "
        + "determine if it is possible to be tolerant of these issues. </p>");
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h4 class=\"pentagon\">Fail - Message Was Not Accepted</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testCaseDescription like ? order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, pentagonReport.getTestConducted());
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK + "%");
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - Message Was Accepted</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testCaseDescription like ?  order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, pentagonReport.getTestConducted());
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK + "%");
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }
  
  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is calculated as the percentage of messages that returned a postive response from the IIS. </p>");
  }

  
  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    pentagonBox.setReportScore(pentagonReport.getTestConducted().getScoreTolerance());
  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">A primary goal of an IIS should be to improve the quality of data being submitted. Many of the issues in these test "
        + "messages are not related to data quality and most IIS should be able to still process them. However, this list is not definitive and there is not "
        + "agreement with which issues the IIS should be tolerant. For this reason it is expected that IIS will be tolerant to most if not all issues "
        + "listed here. </p>");
  }
}
