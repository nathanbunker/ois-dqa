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

public class QFForecaster extends PentagonBoxHelper
{
  public QFForecaster(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
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
      if (testMessage.getResultStoreStatus() != null && testMessage.getResultStatus().equals("PASS"))
      {
        if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED))
        {
          if (testMessage.getResultForecastStatus().equals(RecordServletInterface.VALUE_RESULT_FORECAST_STATUS_INCLUDED))
            passList.add(testMessage);
          else
            failList.add(testMessage);
        } else
          failList.add(testMessage);
      } else
          failList.add(testMessage);
    }

      
    // Failed Tests
    if(failList.size() > 0)
    {
      out.println("<h4 class=\"pentagon\">Fail - Forecast Not Returned</h4>");
      printTestMessageListFailForQuery(out, failList);
    }

    // Passed Tests
    if(passList.size() > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - Forecast Returned</h4>");
      printTestMessageListPassForQuery(out, passList);
    }
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">This section is a quick set of tests to see if IIS return "
              + "clinical decision support during a QBP/RSP interaction.  Each test first submits "
              + "one or more VXU messages to set up test scenarios.  These test patients are then "
              + "retrieved through a QBP/RSP message and a determination is made if clinical decision "
              + "support is include.</p>");
    out.println("<p class=\"pentagon\">This section does not consider the RSP conformance nor does it "
              + "investigate the accuracy of the clinical decision support. RSP conformance is measured "
              + "in the Query Conformance Test Area.  Accuracy of the clinical decision support is not currently "
              + "being assessed, but initial discovery is taking place outside of this process using results from "
              + "from this section.</p>");
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is the percentage of the number of test cases with a forecast returned out of the total test cases in this section.</p>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    int countPass = 0;
    List<TestMessage> testMessageList = getTestMessages(dataSession, pentagonReport);
    for (TestMessage testMessage : testMessageList)
    {
      if (testMessage.getResultStoreStatus() != null && testMessage.getResultStatus().equals("PASS")
          && testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED)
          && testMessage.getResultForecastStatus().equals(RecordServletInterface.VALUE_RESULT_FORECAST_STATUS_INCLUDED))
      {
        countPass++;
      }
    }
    if (testMessageList.size() > 0)
    {
      pentagonBox.setReportScore((int) (100.0 * countPass / testMessageList.size()));
    }

  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\"> This section score can be improved by returning clinical decision support "
              + "when queried.</p>");
  }
  
  private List<TestMessage> getTestMessages(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap =  pentagonReport.getTestSectionMap();
    TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_FORECASTER_ENGAGED);
    Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
    query.setParameter(0, testSection);
    return query.list();
  }
}
