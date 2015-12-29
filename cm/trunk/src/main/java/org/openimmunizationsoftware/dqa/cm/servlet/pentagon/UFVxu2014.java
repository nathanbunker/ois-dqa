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

public class UFVxu2014 extends PentagonBox
{
  public UFVxu2014()
  {
    super(BOX_NAME_UF_VXU2014);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score == 0)
    {
      out.println("<p class=\"pentagon\">NIST 2014 certified systems must be able to create seven test messages in order to pass certification. "
          + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
          + "be prepared to receive all of these messages. <br/><br/> </p>");
    } else if (score == 100)
    {
      out.println("<p class=\"pentagon\">NIST 2014 certified systems must be able to create seven test messages in order to pass certification. "
          + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS can"
          + "process these messages. <br/><br/> </p>");
    } else
    {
      out.println("<p class=\"pentagon\">NIST 2014 certified systems must be able to create seven test messages in order to pass certification. "
          + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
          + "be prepared to receive all of these messages. Some IIS may not be able to process all the information (e.g. Varicella "
          + "history-of-disease, refusuals) but should at the very least accept the other data in the message and not return a hard"
          + "error. </p>");
    }

  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - NIST 2014 Example Was Not Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - NIST 2014 Example Was Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      query.setParameter(1, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }
  
  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is calculated as the percentage of NIST 2014 VXU messages were accepted. </p>");
    out.println("<h4 class=\"pentagon\">How To Improve Score</h4>");
    out.println("<p class=\"pentagon\">Ensure IIS is able to understand and properly accept NIST 2014 test messages. "
        + "Even if IIS does not accept Refusals or History-of-Disease it should not return an error. An error causes this test "
        + "to fail and indicates to the sender to correct and resend. However in this case senders who comply with NIST 2014 "
        + "certification should not be asked to correct and resend when their messages contain good data and conform to the "
        + "national stanard. The IIS may return a Warning or Informational error indicating that certain non-critical data was not "
        + "processed, but it should not return an error. </p>");
  }

  
  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      pentagonReport.setScoreUFVxu2014(testSection.getScoreLevel1());
    }   
    
  }
}
