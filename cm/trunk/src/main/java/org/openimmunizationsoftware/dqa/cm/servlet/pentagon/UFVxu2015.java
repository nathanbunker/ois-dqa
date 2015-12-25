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

public class UFVxu2015 extends PentagonBox
{

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score == 0)
    {
      out.println("<p class=\"pentagon\">NIST 2015 certified systems must be able to create seven test messages in order to pass certification. "
          + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
          + "be prepared to receive all of these messages. These messages are new and not all IIS yet support them. They are included "
          + "here in order for IIS to see what changes may need to be made to support these new messages. <br/><br/> </p>");
    } else if (score == 100)
    {
      out.println("<p class=\"pentagon\">NIST 2015 certified systems must be able to create seven test messages in order to pass certification. "
          + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS can"
          + "process these messages. <br/><br/> </p>");
    } else
    {
      out.println("<p class=\"pentagon\">NIST 2015 certified systems must be able to create seven test messages in order to pass certification. "
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
      out.println("<h3 class=\"pentagon\">Fail - NIST 2015 VXU Example Was Not Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015);
      query.setParameter(1, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - NIST 2015 VXU Example Was Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015);
      query.setParameter(1, testConducted);
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
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015);
      pentagonReport.setScoreUFVxu2015(testSection.getScoreLevel1());
    }    
  }
}
