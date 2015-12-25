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

public class QFDataAvailable extends PentagonBox
{

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    // TODO Auto-generated method stub
    
  }

  private static final String[] DATA_AVAILABLE_SECTIONS = { RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL, RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED };

  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    int count = 0;
    int countPass = 0;
    for (String sectionNames : DATA_AVAILABLE_SECTIONS)
    {
      TestSection testSection = testSectionMap.get(sectionNames);
      Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
      query.setParameter(0, testSection);
      List<TestMessage> testMessageList = query.list();
      for (TestMessage testMessage : testMessageList)
      {
        if (testMessage.getResultStoreStatus() != null)
        {
          if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED))
          {
            count++;
            countPass++;
          } else if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_NOT_RETURNED))
          {
            count++;
          }
        }
      }
    }
    if (count > 0)
    {
      pentagonReport.setScoreQFDataAvailable((int) (100.0 * countPass / count));
    }

  }
}
