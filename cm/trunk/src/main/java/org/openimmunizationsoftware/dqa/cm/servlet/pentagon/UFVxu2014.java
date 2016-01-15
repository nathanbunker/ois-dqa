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

public class UFVxu2014 extends PentagonBoxHelper
{
  public UFVxu2014(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">NIST 2014 certified systems must be able to create seven test messages in order to pass certification. "
        + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
        + "be prepared to receive all of these messages. Some IIS may not be able to process all the information (e.g. Varicella "
        + "history-of-disease, refusuals) but should at the very least accept the other data in the message and not return a hard "
        + "error. </p>");
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h4 class=\"pentagon\">Fail - NIST 2014 Example Was Not Accepted</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - NIST 2014 Example Was Accepted</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }
  
  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is calculated as the percentage of NIST 2014 VXU messages were accepted. </p>");
  }

  
  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap =  pentagonReport.getTestSectionMap();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      pentagonBox.setReportScore(testSection.getScoreLevel1());
    }   
    
  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Ensure that the Acknowledgement message accurately reflects the position of the IIS. "
        + "In particular if the IIS indicates that there is an error (ERR-4 valued E) in a message, the sender must correct and resend. "
        + "If an IIS is not able to accept certain types of data (such as reports of refusals or varicella-history-of-disease) "
        + "the ACK should not declare this as an error (ERR-4 valued E). This is because the sender has not made a mistake and resending will not "
        + "resolve the issue. Instead the IIS should indicate an informational or warning (ERR-4 valued I or W)response to indicate the data could "
        + "not be accepted, but continue to process the rest of the message. </p>");
  }
}
