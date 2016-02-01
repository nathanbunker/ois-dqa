package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.ReportUser;
import org.openimmunizationsoftware.dqa.cm.model.UserType;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;
import org.openimmunizationsoftware.dqa.tr.model.TesterCommand;
import org.openimmunizationsoftware.dqa.tr.model.TesterStatus;

public class ManualManageServlet extends HomeServlet
{

  public static final String VIEW_HL7_DOWNLOAD = "hl7Download";
  public static final String VIEW_HL7_TESTERS = "hl7Testers";
  public static final String VIEW_HL7_REPORTS = "hl7Reports";
  public static final String PARAM_TESTER_NAME = "testerName";
  public static final String PARAM_RUN_DATE = "runDate";
  public static final String PARAM_TESTER_COMMAND_ID = "testerCommandId";

  public static final String ACTION_START = "Start";
  public static final String ACTION_STOP = "Stop";
  public static final String ACTION_DELETE = "Delete";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    this.doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    if (userSession.getUser() == null || userSession.getUser().getApplicationUser() == null
        || !userSession.getUser().getApplicationUser().getApplication().isApplicationAart())
    {
      sendToHome(req, resp);
      return;
    }
    String view = req.getParameter(PARAM_VIEW);
    String action = req.getParameter(PARAM_ACTION);
    try
    {
      if (action != null)
      {
        if (view.equals(VIEW_HL7_TESTERS) || view.equals(VIEW_HL7_REPORTS))
        {
          if (action.equals(ACTION_START) || action.equals(ACTION_STOP))
          {
            doActionStartStop(req, userSession, dataSession, action);
          } else if (action.equals(ACTION_DELETE))
          {
            doActionDelete(req, userSession, dataSession);
          }
        }
      }
      createHeader(webSession);
      if (view.equals(VIEW_HL7_DOWNLOAD))
      {
        viewHl7Download(req, userSession, dataSession, out);
      } else if (view.equals(VIEW_HL7_REPORTS))
      {
        viewHl7Reports(dataSession, out);
      } else if (view.equals(VIEW_HL7_TESTERS))
      {
        viewHl7Testers(req, dataSession, out);
      }
      createFooter(webSession);
    } finally
    {
      out.close();
    }

  }

  private void viewHl7Testers(HttpServletRequest req, Session dataSession, PrintWriter out)
  {
    String testerNameSelected = req.getParameter(PARAM_TESTER_NAME);
    TesterStatus testerStatusSelected = null;
    List<TesterStatus> testerStatusList = getTesterStatusList(dataSession);
    {
      out.println("<div class=\"leftColumn\">");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Tester</th>");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Last Update</th>");
      out.println("    <th class=\"pentagon\">Testing</th>");
      out.println("  </tr>");
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      for (TesterStatus testerStatus : testerStatusList)
      {
        if (testerNameSelected != null && testerStatus.getTesterName().equals(testerNameSelected))
        {
          testerStatusSelected = testerStatus;
        }
        String link = "manualManage?" + PARAM_VIEW + "=" + VIEW_HL7_TESTERS + "&" + PARAM_TESTER_NAME + "=" + testerStatus.getTesterName();
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\"><a href=\"" + link + "\">" + testerStatus.getTesterName() + "</a></td>");
        long age = System.currentTimeMillis() - testerStatus.getStatusDate().getTime();
        if (age < 1000 * 60)
        {
          out.println("    <td class=\"pentagonPass\">" + testerStatus.getReadyStatus() + "</td>");
        } else
        {
          out.println("    <td class=\"pentagon\">Offline</td>");
        }
        out.println("    <td class=\"pentagon\">" + sdf.format(testerStatus.getStatusDate()) + "</td>");
        if (testerStatus.getTestConducted() == null)
        {
          out.println("    <td class=\"pentagon\">&nbsp;</td>");
        } else
        {
          out.println("    <td class=\"pentagon\">" + testerStatus.getTestConducted().getTestParticipant().getConnectionLabel() + "</td>");
        }
        out.println("  </tr>");
      }
      out.println("</table>");
      out.println("</div>");
    }
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    if (testerStatusSelected != null)
    {
      out.println("<div class=\"centerColumn\">");
      out.println("<h3>" + testerNameSelected + "</h3>");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <td class=\"pentagon\">" + testerStatusSelected.getReadyStatus() + "</td>");
      out.println("  </tr>");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Last Update</th>");
      out.println("    <td class=\"pentagon\">" + sdf.format(testerStatusSelected.getStatusDate()) + "</td>");
      out.println("  </tr>");
      if (testerStatusSelected.getTestConducted() != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Testing</th>");
        out.println("    <td class=\"pentagon\">" + testerStatusSelected.getTestConducted().getTestParticipant().getConnectionLabel() + "</td>");
        out.println("  </tr>");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Started Time</th>");
        out.println("    <td class=\"pentagon\">" + sdf.format(testerStatusSelected.getTestConducted().getTestStartedTime()) + "</td>");
        out.println("  </tr>");
      }
      if (testerStatusSelected.getEtcUpdateDate() != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">ETC Update</th>");
        out.println("    <td class=\"pentagon\">" + sdf.format(testerStatusSelected.getEtcUpdateDate()) + "</td>");
        out.println("  </tr>");
      }
      if (testerStatusSelected.getEtcQueryDate() != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">ETC Query</th>");
        out.println("    <td class=\"pentagon\">" + sdf.format(testerStatusSelected.getEtcQueryDate()) + "</td>");
        out.println("  </tr>");
      }
      if (testerStatusSelected.getTestConducted() != null && testerStatusSelected.getTestConducted().getTestFinishedTime() != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Finished Time</th>");
        out.println("    <td class=\"pentagon\">" + sdf.format(testerStatusSelected.getTestConducted().getTestFinishedTime()) + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
      out.println("<br/>");

      out.println("<form method=\"GET\" action=\"manualManage\">");
      out.println("<table class=\"pentagon\">");
      out.println("  <caption class=\"pentagon\">Send Command</caption>");
      out.println("  <tr>");
      out.println("    <th class=\"pentagon\">Testing</th>");
      out.println("    <td class=\"pentagon\">");
      out.println("      <select name=\"" + PARAM_TEST_PARTICIPANT_ID + "\">");
      out.println("        <option value=\"\">--select--</option>");
      {
        Query query = dataSession.createQuery("from TestParticipant order by connectionLabel");
        List<TestParticipant> testParticipantList = query.list();
        for (TestParticipant testParticipant : testParticipantList)
        {
          out.println(
              "        <option value=\"" + testParticipant.getTestParticipantId() + "\">" + testParticipant.getConnectionLabel() + "</option>");
        }
      }
      out.println("      </select>");
      out.println("    </td>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <th class=\"pentagon\">Effective Date & Time</th>");
      out.println(
          "    <td class=\"pentagon\"><input type=\"input\" name=\"" + PARAM_RUN_DATE + "\" value=\"" + sdf.format(new Date()) + "\"></td>");
      out.println("  </tr>");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <td class=\"pentagon\" colspan=\"2\">");
      out.println("      <input type=\"hidden\" name=\"" + PARAM_VIEW + "\" value=\"" + VIEW_HL7_TESTERS + "\">");
      out.println("      <input type=\"hidden\" name=\"" + PARAM_TESTER_NAME + "\" value=\"" + testerNameSelected + "\">");
      out.println("      <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_START + "\">");
      if (testerStatusSelected.getReadyStatus().equals(RecordServletInterface.PARAM_TESTER_STATUS_TESTER_STATUS_TESTING))
      {
        out.println("      <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_STOP + "\">");
      }
      out.println("    </td>");
      out.println("  </tr>");
      out.println("</table>");
      out.println("<br/>");
      out.println("</form>");

      {
        Query query = dataSession.createQuery("from TesterCommand where testerName = ? order by runDate asc");
        query.setParameter(0, testerNameSelected);
        List<TesterCommand> testerCommandList = query.list();
        if (testerCommandList.size() > 0)
        {
          out.println("<h3>Commands in Queue</h3>");
          out.println("<table class=\"pentagon\">");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Command</th>");
          out.println("    <th class=\"pentagon\">Testing</th>");
          out.println("    <th class=\"pentagon\">Run Date</th>");
          out.println("    <th class=\"pentagon\">Alter Command</th>");
          out.println("  </tr>");
          for (TesterCommand testerCommand : testerCommandList)
          {
            out.println("  <tr class=\"pentagon\">");
            out.println("    <td class=\"pentagon\">" + testerCommand.getCommandText() + "</td>");
            if (testerCommand.getTestParticipant() == null)
            {
              out.println("    <td class=\"pentagon\">null</td>");
            } else
            {
              out.println("    <td class=\"pentagon\">" + testerCommand.getTestParticipant().getConnectionLabel() + "</td>");

            }
            out.println("    <td class=\"pentagon\">" + sdf.format(testerCommand.getRunDate()) + "</td>");
            out.println("    <td class=\"pentagon\">");
            out.println("       <form method=\"POST\" action=\"manualManage\">");
            out.println("      <input type=\"hidden\" name=\"" + PARAM_VIEW + "\" value=\"" + VIEW_HL7_TESTERS + "\">");
            out.println("      <input type=\"hidden\" name=\"" + PARAM_TESTER_NAME + "\" value=\"" + testerNameSelected + "\">");
            out.println(
                "      <input type=\"hidden\" name=\"" + PARAM_TESTER_COMMAND_ID + "\" value=\"" + testerCommand.getTesterCommandId() + "\">");
            out.println("      <input type=\"submit\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_DELETE + "\">");
            out.println("       </form>");
            out.println("    </td>");
            out.println("  </tr>");
          }
          out.println("</table>");
        }
      }
      out.println("</div>");
    }
    if (testerNameSelected != null)
    {
      {
        Query query = dataSession.createQuery("from TesterStatus where testerName = ? order by statusDate desc");
        query.setParameter(0, (String) testerNameSelected);
        testerStatusList = query.list();
      }
      out.println("<div class=\"rightColumn\">");
      out.println("<h3>History</h3>");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Last Update</th>");
      out.println("    <th class=\"pentagon\">Testing</th>");
      out.println("  </tr>");
      for (TesterStatus testerStatus : testerStatusList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\">" + testerStatus.getReadyStatus() + "</td>");
        out.println("    <td class=\"pentagon\">" + sdf.format(testerStatus.getStatusDate()) + "</td>");
        if (testerStatus.getTestConducted() == null)
        {
          out.println("    <td class=\"pentagon\">&nbsp;</td>");
        } else
        {
          out.println("    <td class=\"pentagon\">" + testerStatus.getTestConducted().getTestParticipant().getConnectionLabel() + "</td>");
        }
        out.println("  </tr>");
      }
      out.println("</table>");
      out.println("</div>");
    }
    printRefreshScript(out, 30);
  }

  private void viewHl7Reports(Session dataSession, PrintWriter out)
  {
    List<TestParticipant> testParticipantList;
    {
      Query query = dataSession.createQuery("from TestParticipant order by connectionLabel");
      testParticipantList = query.list();
    }

    {
      String connected = "Connected";
      String manual = "Manual Reporter";
      List<TesterStatus> testerStatusList = getTesterStatusList(dataSession);
      printStatusTable(dataSession, out, testParticipantList, connected, manual, connected, testerStatusList);
      printStatusTable(dataSession, out, testParticipantList, connected, manual, manual, testerStatusList);
      printStatusTable(dataSession, out, testParticipantList, connected, manual, null, testerStatusList);
    }
    printRefreshScript(out, 300);
  }

  private void viewHl7Download(HttpServletRequest req, UserSession userSession, Session dataSession, PrintWriter out)
  {
    TestParticipant testParticipant = null;
    if (req.getParameter(PARAM_TEST_PARTICIPANT_ID) != null)
    {
      int testParticipantId = Integer.parseInt(req.getParameter(PARAM_TEST_PARTICIPANT_ID));
      testParticipant = (TestParticipant) dataSession.get(TestParticipant.class, testParticipantId);
    }
    TestConducted testConducted = null;
    if (req.getParameter(PARAM_TEST_CONDUCTED_ID) != null)
    {
      int testConnectedId = Integer.parseInt(req.getParameter(PARAM_TEST_CONDUCTED_ID));
      if (testConnectedId != 0)
      {
        testConducted = (TestConducted) dataSession.get(TestConducted.class, testConnectedId);
      }
    }
    out.println("<h3>Download HL7 Messages</h3>");
    List<TestParticipant> testParticipantList = null;

    if (userSession.getUser().getApplicationUser().getUserType() == UserType.ADMIN)
    {
      Query query = dataSession.createQuery("from TestParticipant order by connectionLabel");
      testParticipantList = query.list();
    } else
    {
      Query query = dataSession.createQuery("from ReportUser where user = ? and (reportRole = ? or reportRole = ?) ");
      query.setParameter(0, userSession.getUser());
      query.setParameter(1, UserType.ADMIN.getId());
      query.setParameter(2, UserType.EXPERT.getId());
      List<ReportUser> reportUserList = query.list();
      testParticipantList = new ArrayList<TestParticipant>();
      for (ReportUser reportUser : reportUserList)
      {
        testParticipantList.add(reportUser.getTestParticipant());
      }
    }
    if (testParticipantList.size() == 1)
    {
      testParticipant = testParticipantList.get(0);
    }

    if (testParticipantList != null)
    {
      out.println("<div class=\"leftColumn\">");

      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Participant</th>");
      out.println("  </tr>");
      for (TestParticipant tp : testParticipantList)
      {
        String link = "manualManage?" + PARAM_VIEW + "=" + VIEW_HL7_DOWNLOAD + "&" + PARAM_TEST_PARTICIPANT_ID + "=" + tp.getTestParticipantId();
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\"><a href=\"" + link + "\" class=\"pentagon\">" + tp.getConnectionLabel() + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
      out.println("</div>");
    }
    if (testParticipant != null)
    {
      out.println("<div class=\"centerColumn\">");
      List<TestConducted> testConductedList;
      {
        Query query = dataSession.createQuery("from TestConducted where testParticipant = ? order by testStartedTime desc");
        query.setParameter(0, testParticipant);
        testConductedList = query.list();
      }
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Date</th>");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Updates</th>");
      out.println("    <th class=\"pentagon\">Queries</th>");
      out.println("    <th class=\"pentagon\">Complete Test</th>");
      out.println("    <th class=\"pentagon\">Manual Prep</th>");
      out.println("  </tr>");
      for (TestConducted tc : testConductedList)
      {
        String link = "manualManage?" + PARAM_VIEW + "=" + VIEW_HL7_DOWNLOAD + "&" + PARAM_TEST_PARTICIPANT_ID + "="
            + tc.getTestParticipant().getTestParticipantId() + "&" + PARAM_TEST_CONDUCTED_ID + "=" + tc.getTestConductedId();
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\"><a href=\"" + link + "\" class=\"pentagon\">" + sdf.format(tc.getTestStartedTime()) + "</td>");
        out.println("    <td class=\"pentagon\">" + tc.getTestStatus() + "</td>");
        out.println("    <td class=\"pentagon\">" + tc.getCountUpdate() + "</td>");
        out.println("    <td class=\"pentagon\">" + tc.getCountQuery() + "</td>");
        if (tc.isCompleteTest())
        {
          out.println("    <td class=\"pentagon\">Yes</td>");
        } else
        {
          out.println("    <td class=\"pentagon\">&nbsp;</td>");
        }
        if (tc.isManualTest())
        {
          out.println("    <td class=\"pentagon\">Yes</td>");
        } else
        {
          out.println("    <td class=\"pentagon\">&nbsp;</td>");
        }
        out.println("  </tr>");
      }
      out.println("</table>");
      out.println("</div>");
    }
    if (testConducted != null)
    {
      out.println("<div class=\"rightColumn\">");
      List<TestSection> testSectionList;
      {
        Query query = dataSession.createQuery("from TestSection where testConducted = ? order by testSectionType");
        query.setParameter(0, testConducted);
        testSectionList = query.list();
      }
      PentagonContentServlet.showDownloads(out, testConducted, testSectionList);
      out.println("</div>");
    }
  }

  private void doActionStartStop(HttpServletRequest req, UserSession userSession, Session dataSession, String action)
  {
    TestParticipant testParticipant = null;
    {
      String testParticipantIdString = req.getParameter(PARAM_TEST_PARTICIPANT_ID);
      if (!testParticipantIdString.equals(""))
      {
        testParticipant = (TestParticipant) dataSession.get(TestParticipant.class, Integer.parseInt(testParticipantIdString));
      }
    }
    String testerNameSelected = req.getParameter(PARAM_TESTER_NAME);
    TesterCommand testerCommand = new TesterCommand();
    testerCommand.setTesterName(testerNameSelected);
    testerCommand.setTestParticipant(testParticipant);
    testerCommand.setCommandText(action);
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    try
    {
      if (req.getParameter(PARAM_RUN_DATE) != null && !req.getParameter(PARAM_RUN_DATE).equals(""))
      {
        testerCommand.setRunDate(sdf.parse(req.getParameter(PARAM_RUN_DATE)));
      } else
      {
        testerCommand.setRunDate(new Date());
      }
      Transaction transaction = dataSession.beginTransaction();
      dataSession.save(testerCommand);
      transaction.commit();
      userSession.setMessageConfirmation("Command to " + testerCommand.getCommandText() + " has been scheduled");
    } catch (ParseException pe)
    {
      userSession.setMessageError("Unable to parse run date");
    }
  }

  private void doActionDelete(HttpServletRequest req, UserSession userSession, Session dataSession)
  {
    TesterCommand testerCommand = (TesterCommand) dataSession.get(TesterCommand.class,
        Integer.parseInt(req.getParameter(PARAM_TESTER_COMMAND_ID)));
    Transaction transaction = dataSession.beginTransaction();
    dataSession.delete(testerCommand);
    transaction.commit();
    userSession.setMessageConfirmation("Command has been deleted");
  }

  public void printStatusTable(Session dataSession, PrintWriter out, List<TestParticipant> testParticipantList, String connectStatusConnected,
      String connectStatusManualReporter, String connectShow, List<TesterStatus> testerStatusList)
  {
    if (connectShow == null)
    {
      out.println("<h4 class=\"pentagon\">All Other</h4>");
    } else
    {
      out.println("<h4 class=\"pentagon\">" + connectShow + "</h4>");
    }
    out.println("<table class=\"pentagon\">");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\" rowSpan=\"2\">Connection</th>");
    out.println("    <th class=\"pentagon\" rowSpan=\"2\">Connect Status</th>");
    out.println("    <th class=\"pentagon\" rowSpan=\"2\">Latest Test</th>");
    out.println("    <th class=\"pentagon\" colspan=\"5\">Last Test Run</th>");
    out.println("    <th class=\"pentagon\" rowSpan=\"2\">Start</th>");
    out.println("  </tr>");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\" colspan=\"\">Date</th>");
    out.println("    <th class=\"pentagon\" colspan=\"\">Status</th>");
    out.println("    <th class=\"pentagon\" colspan=\"\">Updates</th>");
    out.println("    <th class=\"pentagon\" colspan=\"\">Queries</th>");
    out.println("    <th class=\"pentagon\" colspan=\"\">Time</th>");
    out.println("  </tr>");
    for (TestParticipant testParticipant : testParticipantList)
    {
      String connectStatus = testParticipant.getConnectStatus();
      if (connectShow == null)
      {
        if (connectStatus != null)
        {
          if (connectStatus.equalsIgnoreCase(connectStatusConnected) || connectStatus.equalsIgnoreCase(connectStatusManualReporter))
          {
            continue;
          }
        }
      } else
      {
        if (connectStatus == null || !connectStatus.equalsIgnoreCase(connectShow))
        {
          continue;
        }
      }
      List<TestConducted> testConductedList;
      TestConducted latestTest = null;
      {
        Query query = dataSession.createQuery("from TestConducted where testParticipant = ? order by testStartedTime desc");
        query.setParameter(0, testParticipant);
        testConductedList = query.list();
        for (TestConducted testConducted : testConductedList)
        {
          if (testConducted.isLatestTest())
          {
            latestTest = testConducted;
          }
        }
      }
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <td class=\"pentagon\">" + testParticipant.getConnectionLabel() + "</td>");
      out.println("    <td class=\"pentagon\">" + connectStatus + "</td>");
      if (latestTest == null)
      {
        out.println("    <td class=\"fail\">-</td>");
      } else
      {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        if (c.getTime().before(latestTest.getTestStartedTime()))
        {
          out.println("    <td class=\"pass\">" + sdf.format(latestTest.getTestStartedTime()) + "</td>");
        } else
        {
          out.println("    <td class=\"fail\">" + sdf.format(latestTest.getTestStartedTime()) + "</td>");
        }
      }
      if (testConductedList.size() == 0)
      {
        out.println("    <td class=\"pentagon\">-</td>");
        out.println("    <td class=\"pentagon\">-</td>");
        out.println("    <td class=\"pentagon\">-</td>");
        out.println("    <td class=\"pentagon\">-</td>");
        out.println("    <td class=\"pentagon\">-</td>");
      } else
      {
        TestConducted testConducted = testConductedList.get(0);
        out.println("    <td class=\"pentagon\">" + sdf.format(testConducted.getTestStartedTime()) + "</td>");
        out.println("    <td class=\"pentagon\">" + testConducted.getTestStatus() + "</td>");
        out.println("    <td class=\"pentagon\">" + testConducted.getCountUpdate() + "</td>");
        out.println("    <td class=\"pentagon\">" + testConducted.getCountQuery() + "</td>");
        if (testConducted.getTestFinishedTime() != null)
        {
          long timeElapsed = testConducted.getTestFinishedTime().getTime() - testConducted.getTestStartedTime().getTime();
          int minutes = (int) (0.5 + timeElapsed / 1000.0 / 60.0);
          int hours = minutes / 60;
          minutes = minutes % 60;
          out.println("    <td class=\"pentagon\">" + hours + ":" + (minutes < 10 ? "0" + minutes : "" + minutes) + "</td>");
        } else
        {
          out.println("    <td class=\"pentagon\">-</td>");
        }
        out.println("    <td class=\"pentagon\">");
        TesterCommand testerCommand = null;
        {
          Query query = dataSession.createQuery("from TesterCommand where testParticipant = ? and commandText = ?");
          query.setParameter(0, testParticipant);
          query.setParameter(1, ACTION_START);
          List<TesterCommand> testerCommandList = query.list();
          if (testerCommandList.size() > 0)
          {
            testerCommand = testerCommandList.get(0);
          }
        }

        if (testerCommand == null)
        {
          boolean canSchedule = true;
          for (TesterStatus testerStatus : testerStatusList)
          {
            if (testerStatus.getTestConducted() != null && testerStatus.getTestConducted().getTestParticipant() == testParticipant)
            {
              out.println(testerStatus.getTesterName() + " running now");
              canSchedule = false;
            }
          }
          if (canSchedule)
          {
            out.println("<form method=\"GET\" action=\"manualManage\">");
            out.println(
                "      <input type=\"hidden\" name=\"" + PARAM_TEST_PARTICIPANT_ID + "\" value=\"" + testParticipant.getTestParticipantId() + "\">");
            out.println("      <input type=\"hidden\" name=\"" + PARAM_VIEW + "\" value=\"" + VIEW_HL7_REPORTS + "\">");
            for (TesterStatus testerStatus : testerStatusList)
            {
              out.println("      <input type=\"submit\" name=\"" + PARAM_TESTER_NAME + "\" value=\"" + testerStatus.getTesterName() + "\">");
            }
            out.println("      <input type=\"hidden\" name=\"" + PARAM_ACTION + "\" value=\"" + ACTION_START + "\">");
            out.println("</form>");
          }
        } else
        {
          out.println(testerCommand.getTesterName());
        }
        out.println("    </td>");
      }
      out.println("  </tr>");
    }
    out.println("</table>");
    out.println("<br/>");
  }

  public List<TesterStatus> getTesterStatusList(Session dataSession)
  {
    List<TesterStatus> testerStatusList = new ArrayList<TesterStatus>();
    {
      Query query = dataSession.createQuery("select testerName from TesterStatus group by testerName order by testerName ");
      List<Object> testerNameList = query.list();
      for (Object testerName : testerNameList)
      {
        query = dataSession.createQuery("from TesterStatus where testerName = ? order by statusDate desc");
        query.setParameter(0, (String) testerName);
        List<TesterStatus> testerStatusItemList = query.list();
        if (testerStatusItemList.size() > 0)
        {
          testerStatusList.add(testerStatusItemList.get(0));
        }
      }
    }
    return testerStatusList;
  }

  public void printRefreshScript(PrintWriter out, int refreshCount)
  {
    out.println("<script>");
    out.println("  var refreshCount = 0;");
    out.println("  function checkRefresh()");
    out.println("  {");
    out.println("    refreshCount++;");
    out.println("    if (refreshCount > " + refreshCount + ")");
    out.println("    {");
    out.println("      window.location.href=\"manualManage?" + PARAM_VIEW + "=" + VIEW_HL7_TESTERS + "\"");
    out.println("    }");
    out.println("    else");
    out.println("    {");
    out.println("      setTimeout('checkRefresh()', 1000);");
    out.println("    }");
    out.println("  }");
    out.println("  checkRefresh(); ");
    out.println("</script>");
  }
}
