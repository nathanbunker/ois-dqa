package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.ArrayList;
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

public class QFDeduplication extends PentagonBoxHelper
{
  public QFDeduplication(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    List<TestMessage> failList = new ArrayList();
    List<TestMessage> passList = new ArrayList();

    List<TestMessage> testMessageList = getTestMessages(dataSession, pentagonReport);
    for (TestMessage testMessage : testMessageList)
    {
      if (isPass(testMessage))
      {
        passList.add(testMessage);
      }
      else
      {
        failList.add(testMessage);
      }
    }

    // Failed Tests
    if(failList.size() > 0)
    {
      out.println("<h4 class=\"pentagon\">Fail - Deduplication Not Engaged</h4>");
      printTestMessageListFailForQuery(out, failList);
    }

    // Passed Tests
    if(passList.size() > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - Deduplication Engaged</h4>");
      printTestMessageListPassForQuery(out, passList);
    }
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">IIS must be able to merge patient and vaccination data together in order to create a consolidated "
        + "patient and vaccination history. This is a major and critical function for an IIS. This test set does not measure "
        + "deduplication directly, this type of test can not be done effectively using HL7. Instead it runs tests that verify that "
        + "some sort of deduplication system is engaged. Local variations are expected in this area so the results should be carefully reviewed "
        + "to determine what, if any action, is needed. </p>");
    
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is the percentage of records accepted and returned as expected.</p>");
  }

  @Override
  public void calculateScore( Session dataSession, PentagonReport pentagonReport)
  {
    int count = 0;
    int countPass = 0;
    {
      List<TestMessage> testMessageList = getTestMessages(dataSession, pentagonReport);
      for (TestMessage testMessage : testMessageList)
      {
        count++;
        if (isPass(testMessage))
        {
          countPass++;
        }
      }
    }
    if (count > 0)
    {
      pentagonBox.setReportScore((int) (100.0 * countPass / count));
    }

  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub
    
  }

  private List<TestMessage> getTestMessages(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap =  pentagonReport.getTestSectionMap();
    TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_DEDUPLICATION_ENGAGED);
    Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
    query.setParameter(0, testSection);
    return query.list();
  }
  
  private boolean isPass(TestMessage testMessage)
  {
    if (testMessage.getResultStoreStatus() != null && testMessage.getResultStatus().equals("PASS"))
    {
      if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED))
      {
        return true;  
      }
    }  
    return false;
  }
}
