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

public class UFTolerant extends PentagonBox
{
  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">HL7 requires that receiving systems, such as IIS, give a certain amount of tolerance to received message. "
        + "Ideally IIS would always receive perfect and consistent messages, but when it does not, it should still try its best to process"
        + "messages with minor issues. The test examples in section all have minor issues that do not directly affect data quality and which "
        + "IIS would be expected to cope with. Still this test does not assert that IIS ought to accept every tolerant message proposed here. "
        + "A few of these messages may not be accepted by IIS for good reasons. IIS should review closely the ones they do not accept and "
        + "determine if is possible to be tolerant of these issues. </p>");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Message Was Not Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testCaseDescription like ? order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, testConducted);
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK + "%");
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Message Was Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testCaseDescription like ?  order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, testConducted);
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK + "%");
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }
  
  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    // TODO Auto-generated method stub
    
  }

  
  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    pentagonReport.setScoreUFTolerant(testConducted.getScoreTolerance());
  }
}
