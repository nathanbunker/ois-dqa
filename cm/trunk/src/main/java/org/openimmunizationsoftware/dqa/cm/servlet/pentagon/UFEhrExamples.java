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

public class UFEhrExamples extends PentagonBox
{

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println(
        "<p class=\"pentagon\">Certified EHR systems are able to produce messages that meet NIST standards, but may contain minor variations "
            + "that are not seen in the NIST examples. This section contains examples from EHR systems. While an attempt has been made to ensure that "
            + "these are good messages, this test does not assert that an IIS ought to be able accept all of these. Rather an IIS should use "
            + "this test to help find issues that were not identified in any of the other testing scenarios. In addition this test list will "
            + "grow as additional examples are found.  </p>");

  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - EHR Example Was Not Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testCaseDescription like ? order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, testConducted);
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE + "%");
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - EHR Example Was Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testCaseDescription like ?  order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
      query.setParameter(1, testConducted);
      query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE + "%");
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
    pentagonReport.setScoreUFEhrExamples(testConducted.getScoreEhr());
  }
}
