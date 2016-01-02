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

public class CGoodMessage extends PentagonBoxHelper
{

  public CGoodMessage(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() == 100)
    {
      out.println("<p class=\"pentagon\">All NIST 2014 test messages were accepted, as was expected. "
          + "Accepting all of these standard IIS messages increase confidence when reading the results of the rest "
          + "of the testing process. </p> <br/><br/><br/>");
    } else
    {
      out.println("<p class=\"pentagon\">Properly formatted and complete messages should be accepted by the IIS. "
          + "This measurement starts with the assumption that the IIS can accept good messages "
          + "and considers success when the IIS indicates the message was accepted.  Criteria for "
          + "determining whether a messages was accepted or not is determined by the configuration " + "used to connect to the IIS. </p> ");
    }

  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Good Message Was Not Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Good Message Was Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is simple percentage of the number of NIST 2014 example messages accepted out of the "
        + "total possible.   </p>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();
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
        + "In particular if the IIS indicates that there is an error in a message, the sender must correct and resend. "
        + "If an IIS is not able to accept certain types of data (such as reports of refusals or varicella-history-of-disease) "
        + "the issue should not issue an error. This is because the sender has not made a mistake and a resubmission will not "
        + "resolve the issue. Instead the IIS out to indicate a warning or informational message to indicate the data could "
        + "not be accepted, but continue to process the rest of the message. </p>");
  }

}