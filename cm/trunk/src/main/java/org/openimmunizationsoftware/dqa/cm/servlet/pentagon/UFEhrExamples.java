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
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class UFEhrExamples extends PentagonBoxHelper
{
  public UFEhrExamples(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println(
        "<p class=\"pentagon\">Certified EHR systems are able to produce messages that meet NIST standards, but may contain minor variations "
            + "that are not seen in the NIST examples. This section contains examples from EHR systems. While an attempt has been made to ensure that "
            + "these are good messages, this test does not assert that an IIS ought to be able accept all of these. Rather an IIS should use "
            + "this test to help find issues that were not identified in any of the other testing scenarios. The number of tests in this section will "
            + "continue to grow as additional EHR examples collected.  </p>");

  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h4 class=\"pentagon\">Fail - EHR Example Was Not Accepted</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testCaseDescription like ? order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, pentagonReport.getTestConducted());
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE + "%");
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - EHR Example Was Accepted</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testCaseDescription like ?  order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, pentagonReport.getTestConducted());
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE + "%");
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList, userSession);
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
    pentagonBox.setReportScore(pentagonReport.getTestConducted().getScoreEhr());
  }

  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Review the messages and determine if there are any changes needed to how the IIS processes these messages. "
        + "These messages were collected from certified EHR systems. </p>");
  }
}
