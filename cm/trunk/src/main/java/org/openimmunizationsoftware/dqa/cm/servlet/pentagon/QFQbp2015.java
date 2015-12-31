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

public class QFQbp2015 extends PentagonBox
{

  public QFQbp2015() {
    super(BOX_NAME_QF_QBP2015);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">NIST 2015 certified systems must be able to query IIS for an evaluation and forecast for a patient record.  "
        + "As part of the certification process NIST created test scenarios after carefully reading the requirements in the CDC Implementation Guide "
        + "release 1.5. IIS should be prepared to respond to these queries. In preparation for these queries data has been submitted to the IIS. "
        + "Now this data is being queried back and this test verifies that the correct responses are being returned.</p>");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Match Not Found</h3>");
      Query query = dataSession
          .createQuery("from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' "
              + "and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFailForQuery(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Match Found</h3>");
      Query query = dataSession
          .createQuery("from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' "
              + "and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPassForQuery(out, testMessageList);
    }
  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">This section tests to see if the IIS can respond with the required information for a given query "
        + "based on what data was submitted in the update step. In order to receive a positive score the IIS must both pass the test "
        + "expectation</p>");
    out.println("<p class=\"pentagon\">The score is the percentage of messages that meets this criteria.  </p>");
    out.println("<h4 class=\"pentagon\">How To Improve Score</h4>");
    out.println("<p class=\"pentagon\">Provide full support for both Z34 and Z44 queries. Ensure that deduplication process is configured to "
        + "match patients within 15 minutes of being submitted to the IIS. </p>");
    out.println("<p class=\"pentagon\">Note: Some IIS can not return a list of possible matches due to policy reasons. This test set "
        + "does include a scenario where the IIS is expected to return a list of possible matches. IIS who operate under this "
        + "policy restriction will not be able to pass this test. While there are good reasons for an IIS to have such a policy it "
        + "does impact interoperability and so is scored in this section. IIS that can not support this should still be able to "
        + "meet all the other requirements and still receive a fairly high score, and the failure can be noted so that "
        + "trading partners are aware of this unavoidable limitation. </p>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap =  pentagonReport.getTestSectionMap();
    TestConducted testConducted = pentagonReport.getTestConducted();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT) != null)
    {
      int countTotal = 0;
      int countPass = 0;
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and testType = 'query' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT);
      query.setParameter(1, testConducted);
      List<TestMessage> testMessageList = query.list();
      for (TestMessage testMessage : testMessageList)
      {
        countTotal++;
        if (testMessage.getResultStatus().equalsIgnoreCase("PASS"))
        {
          countPass++;
        }
      }

      if (countTotal > 0)
      {
        pentagonReport.setScoreQFQbp2015(((int) 100.0 * countPass / countTotal));
      }
    }
  }
}
