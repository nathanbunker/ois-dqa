package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.CentralControl;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TesterCommand;
import org.openimmunizationsoftware.dqa.tr.model.TesterStatus;

public class ManualServlet extends HttpServlet implements RecordServletInterface
{

  public static String PARAM_QUERY = "query";
  public static String PARAM_CONNECTION_LABEL = "connectionLabel";
  public static String PARAM_TEST_START_TIME = "testStartTime";
  public static String PARAM_TEST_SECTION_TYPE = "testSectionType";
  public static String PARAM_TEST_CASE_CATEGORY = "testCaseCategory";

  public static String QUERY_REPORTS = "reports";
  public static String QUERY_REQUEST = "request";
  public static String QUERY_RESPONSE = "response";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    SessionFactory factory = CentralControl.getSessionFactory();
    Session dataSession = factory.openSession();
    PrintWriter out = new PrintWriter(resp.getOutputStream());
    resp.setContentType("text/plain");
    String querySelected = req.getParameter(PARAM_QUERY);
    String update = req.getParameter(PARAM_TESTER_STATUS_UPDATE);
    try
    {
      if (update != null)
      {
        String testerName = req.getParameter(PARAM_TESTER_STATUS_TESTER_NAME);
        String testerStatusStatus = req.getParameter(PARAM_TESTER_STATUS_READY_STATUS);
        String testerStartedTimeString = req.getParameter(PARAM_TESTER_STATUS_TEST_STARTED_TIME);
        String etcQueryDate = req.getParameter(PARAM_TESTER_STATUS_ETC_QUERY_DATE);
        String etcUpdateDate = req.getParameter(PARAM_TESTER_STATUS_ETC_UPDATE_DATE);
        String connectionLabel = req.getParameter(PARAM_TESTER_STATUS_TEST_CONNECTION_LABEL);
        Date testerStartedTime = null;
        if (testerStartedTimeString != null)
        {
          testerStartedTime = VALUE_DATE_FORMAT.parse(testerStartedTimeString);
        }
        TesterStatus testerStatus = null;
        if (testerStartedTimeString == null)
        {
          Query query = dataSession.createQuery("from TesterStatus where testConducted is null and testerName = ?");
          query.setParameter(0, testerName);
          List<TesterStatus> testerStatusList = query.list();
          if (testerStatusList.size() > 0)
          {
            testerStatus = testerStatusList.get(0);
          }
        } else
        {
          Query query = dataSession.createQuery("from TesterStatus where testConducted.testStartedTime = ? and testerName = ?");
          query.setParameter(0, testerStartedTime);
          query.setParameter(1, testerName);
          List<TesterStatus> testerStatusList = query.list();
          if (testerStatusList.size() > 0)
          {
            testerStatus = testerStatusList.get(0);
          }
        }
        if (testerStatus == null)
        {
          testerStatus = new TesterStatus();
          testerStatus.setTesterName(testerName);
          if (testerStartedTime != null)
          {
            Query query = dataSession.createQuery("from TestConducted where testParticipant.connectionLabel = ? and testStartedTime = ?");
            query.setParameter(0, connectionLabel);
            query.setParameter(1, testerStartedTime);
            List<TestConducted> testConductedList = query.list();
            if (testConductedList.size() > 0)
            {
              testerStatus.setTestConducted(testConductedList.get(0));
            }
          }
        }
        testerStatus.setReadyStatus(testerStatusStatus);
        testerStatus.setStatusDate(new Date());
        if (etcQueryDate != null)
        {
          testerStatus.setEtcQueryDate(VALUE_DATE_FORMAT.parse(etcQueryDate));
        }
        if (etcUpdateDate != null)
        {
          testerStatus.setEtcUpdateDate(VALUE_DATE_FORMAT.parse(etcUpdateDate));
        }
        {
          Transaction transaction = dataSession.beginTransaction();
          dataSession.save(testerStatus);
          transaction.commit();
        }
        Query query = dataSession.createQuery("from TesterCommand where testerName = ? and runDate < ? order by runDate asc");
        query.setParameter(0, testerName);
        query.setParameter(1, new Date());
        List<TesterCommand> testerCommandList = query.list();
        if (testerCommandList.size() > 0)
        {
          TesterCommand testerCommand = testerCommandList.get(0);
          boolean deleteCommand = false;
          if (testerStatus.getReadyStatus().equals(PARAM_TESTER_STATUS_TESTER_STATUS_READY) && testerStatus.getTestConducted() == null
              && testerCommand.getCommandText().equals(PARAM_TESTER_ACTION_START))
          {
            if (testerCommand.getTestParticipant() != null)
            {
              if (testerCommand.getCommandOptions() != null && !testerCommand.getCommandOptions().equals(""))
              {
                out.println(PARAM_TESTER_ACTION_START + " " + testerCommand.getTestParticipant().getConnectionLabel() + " "
                    + testerCommand.getCommandOptions());
              } else
              {
                out.println(PARAM_TESTER_ACTION_START + " " + testerCommand.getTestParticipant().getConnectionLabel());
              }
            }
            deleteCommand = true;
          } else if (!testerStatus.getReadyStatus().equals(PARAM_TESTER_STATUS_TESTER_STATUS_READY)
              && testerCommand.getCommandText().equals(PARAM_TESTER_ACTION_STOP))
          {
            out.println(PARAM_TESTER_ACTION_STOP);
            deleteCommand = true;
          }
          if (deleteCommand)
          {
            Transaction transaction = dataSession.beginTransaction();
            dataSession.delete(testerCommand);
            transaction.commit();
          }
        }
      } else
      {
        String connectionLabel = req.getParameter(PARAM_CONNECTION_LABEL);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (querySelected.equals(QUERY_REPORTS))
        {
          Query query = dataSession.createQuery("from TestConducted where testParticipant.connectionLabel = ? order by testStartedTime desc");
          query.setParameter(0, connectionLabel);
          List<TestConducted> testConductedList = query.list();
          for (TestConducted testConducted : testConductedList)
          {
            out.println(sdf.format(testConducted.getTestStartedTime()));
          }
        } else if (querySelected.equals(QUERY_REQUEST) || querySelected.equals(QUERY_RESPONSE))
        {
          Date testStartTime = sdf.parse(req.getParameter(PARAM_TEST_START_TIME));
          String testSectionType = req.getParameter(PARAM_TEST_SECTION_TYPE);
          String testCaseCategory = req.getParameter(PARAM_TEST_CASE_CATEGORY);
          Query query = dataSession
              .createQuery("from TestMessage where testSection.testConducted.connectionLabel = ? and testSection.testConducted.testStartedTime = ? "
                  + "and testSection.testSectionType = ? and testCaseCategory = ?");
          query.setParameter(0, connectionLabel);
          query.setParameter(1, testStartTime);
          query.setParameter(2, testSectionType);
          query.setParameter(3, testCaseCategory);
          List<TestMessage> testMessageList = query.list();
          if (testMessageList.size() > 0)
          {
            TestMessage testMessage = testMessageList.get(0);
            if (!testMessage.isResultManualTest())
            {
              if (querySelected.equals(QUERY_REQUEST))
              {
                out.print(testMessage.getPrepMessageActual());
              } else if (querySelected.equals(QUERY_RESPONSE))
              {
                out.print(testMessage.getResultMessageActual());
              }
            }
          }
        }
      }
    } catch (Exception e)
    {
      e.printStackTrace();
      throw new ServletException(e);
    } finally
    {
      dataSession.close();
    }
    out.close();

  }
}
