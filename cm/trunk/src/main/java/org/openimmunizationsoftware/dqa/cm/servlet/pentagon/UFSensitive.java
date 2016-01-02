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

public class UFSensitive extends PentagonBoxHelper
{
  public UFSensitive(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">IIS must be ensuring that received messages meet basic standards by performing basic checks before "
        + "accepting incoming data. Problems that are found should be messaged back to the sender in the acknowledgement message. "
        + "Doing so gives sending systems the opportunity to correct mistakes and resend. This test can also help confirm that the IIS"
        + "is not vulnerable to the receipt of bad data. Success is measured by noting if the introduction of the issue results"
        + "in a substantive change in the acknowledgment message. Due to the different ways IIS message back issues, this measure is "
        + "somewhat crude and should not be depended on to absolutely establish that an IIS is sensitive to any particular issue.  </p>");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - IIS Does Not Recognize Issue</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - IIS Seems to Recognize Issue</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is calculated as the percentage of messages with issues that returned a different "
        + "response from the IIS. </p>");
  }

  @Override
  public void calculateScore( Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap =  pentagonReport.getTestSectionMap();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED);
      pentagonBox.setReportScore(testSection.getScoreLevel1());
    }

  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Ensure that critical issues are recognized in update messages and reported in the acknowledgement message. "
        + "Ideally the submitter should be told about all issues that are identified in the update message so that corrective action can be taken."
        + "The examples in this section can be used as a starting point to look at issues an IIS will likely want to be senstive to.  </p>");
  }
}
