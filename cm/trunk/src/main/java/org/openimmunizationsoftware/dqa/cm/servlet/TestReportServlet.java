package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.Assertion;
import org.openimmunizationsoftware.dqa.tr.model.Comparison;
import org.openimmunizationsoftware.dqa.tr.model.ComparisonField;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class TestReportServlet extends HomeServlet
{

  private static final String PARAM_TEST_CONDUCTED_ID = "testConductedId";
  private static final String PARAM_CONNECTION_LABEL = "connectionLabel";
  private static final String PARAM_TEST_MESSAGE_ID = "testMessageId";
  private static final String PARAM_TEST_PARTICIPANT_ID = "testParticipantId";
  private static final String PARAM_COMPARISON_FIELD_ID = "comparisonFieldId";

  private static final String ACTION_VIEW_DETAIL = "View Detail";
  private static final String ACTION_VIEW_CLOSE = "Close";

  public static final String VIEW_BASIC = RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC;
  public static final String VIEW_INTERMEDIATE = RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE;
  public static final String VIEW_ADVANCED = RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED;
  public static final String VIEW_PROFILING = RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING;
  public static final String VIEW_EXCEPTIONAL = RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL;
  public static final String VIEW_FORECAST_PREP = RecordServletInterface.VALUE_TEST_SECTION_TYPE_FORECAST_PREP;
  public static final String VIEW_FORECAST = RecordServletInterface.VALUE_TEST_SECTION_TYPE_FORECAST;
  public static final String VIEW_CONFORMANCE = RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE;
  public static final String VIEW_CONFORMANCE_2015 = RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015;
  public static final String VIEW_ONC_2015 = RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015;
  public static final String VIEW_NOT_ACCEPTED = RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED;

  public static final String VIEW_HOME = "home";
  public static final String VIEW_REPORTS = "reports";
  public static final String VIEW_DETAIL = "detail";
  public static final String VIEW_STATUS = "status";
  public static final String VIEW_CONNECTION = "connection";
  public static final String VIEW_INTEROP = "interop";
  public static final String VIEW_CODED_VALUES = "coded";
  public static final String VIEW_TOLERANCE = "tolerance";
  public static final String VIEW_EHR = "ehr";
  public static final String VIEW_PERFORMANCE = "performance";
  public static final String VIEW_CONFORM = "conform";
  public static final String VIEW_LOCAL = "local";
  public static final String VIEW_NATIONAL = "national";
  public static final String VIEW_DASHBOARD = "dashboard";

  public static final String VIEW_TEST_MESSAGES = "testMessages";
  public static final String VIEW_FIELD_COMPARISON = "fieldComparison";
  public static final String VIEW_MAP = "map";

  public static final String PARAM_MAP_STATUS = "mapStatus";
  public static final String PARAM_MAP_FILTER = "mapFilter";

  public static final String MAP_STATUS_PHASE1_PARTICIPATION = "Phase 1 Participation";
  public static final String MAP_STATUS_PHASE1_STATUS = "Phase 1 Status";
  public static final String MAP_STATUS_PHASE2_PARTICIPATION = "Phase 2 Participation";
  public static final String MAP_STATUS_PHASE2_STATUS = "Phase 2 Status";
  public static final String MAP_STATUS_CONNECTED = "Connected";
  public static final String MAP_STATUS_GUIDE = "Guide";
  public static final String MAP_STATUS_NIST = "NIST";
  public static final String MAP_STATUS_IHS = "IHS";
  public static final String MAP_STATUS_TRANSPORT = "Transport";
  public static final String MAP_STATUS_QUERY = "Query";
  public static final String MAP_STATUS_REPORT_RESULTS = "Report Results";

  public static final String[] MAP_STATUS = { MAP_STATUS_PHASE1_PARTICIPATION, MAP_STATUS_PHASE1_STATUS, MAP_STATUS_PHASE2_PARTICIPATION,
      MAP_STATUS_PHASE2_STATUS, MAP_STATUS_CONNECTED, MAP_STATUS_GUIDE, MAP_STATUS_NIST, MAP_STATUS_IHS, MAP_STATUS_TRANSPORT, MAP_STATUS_QUERY,
      MAP_STATUS_REPORT_RESULTS };

  private static final Map<String, String[]> MAP_STATUS_PASSING_VALUES = new HashMap<String, String[]>();

  static
  {
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_PHASE1_PARTICIPATION, new String[] { "Yes - Direct", "Yes - Report Only" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_PHASE1_STATUS, new String[] { "Complete" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_PHASE2_PARTICIPATION, new String[] { "Yes - AIRA & NIST", "Yes - AIRA Only" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_PHASE2_STATUS, new String[] { "Complete" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_CONNECTED, new String[] { "Connected" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_GUIDE, new String[] { "IIS Guide Recorded", "Use National Guide", "See Envision Guide" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_NIST, new String[] { "Tested" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_IHS, new String[] { "Yes" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_TRANSPORT, new String[] { "SOAP" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_QUERY, new String[] { "QBP" });
    MAP_STATUS_PASSING_VALUES.put(MAP_STATUS_REPORT_RESULTS, new String[] { "Recently Ran", "Up-to-date" });
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    if (userSession.getUser() == null || userSession.getUser().getApplicationUser() == null
        || !userSession.getUser().getApplicationUser().getApplication().isApplicationDqais())
    {
      sendToHome(req, resp);
      return;
    }
    String view = req.getParameter(PARAM_VIEW);
    if (view == null)
    {
      view = VIEW_HOME;
    }
    String connectionLabel = req.getParameter(PARAM_CONNECTION_LABEL);
    if (connectionLabel == null)
    {
      connectionLabel = "";
    }
    TestConducted testConducted = (TestConducted) webSession.getAttribute(ATTRIBUTE_TEST_CONDUCTED);

    int testConnectedId = 0;
    {
      String testConnectedIdString = req.getParameter(PARAM_TEST_CONDUCTED_ID);
      if (testConnectedIdString != null)
      {
        testConnectedId = Integer.parseInt(testConnectedIdString);
      }
      if (testConnectedId != 0)
      {
        testConducted = (TestConducted) dataSession.get(TestConducted.class, testConnectedId);
      }
      if (testConducted != null)
      {
        if (!connectionLabel.equals("") && !testConducted.getConnectionLabel().equals(connectionLabel))
        {
          webSession.removeAttribute(ATTRIBUTE_TEST_CONDUCTED);
          testConducted = null;
        }
      }
    }

    TestParticipant testParticipantSelected = (TestParticipant) webSession.getAttribute(ATTRIBUTE_TEST_PARTICIPANT);
    if (req.getParameter(PARAM_TEST_PARTICIPANT_ID) != null)
    {
      int testParticipantId = Integer.parseInt(req.getParameter(PARAM_TEST_PARTICIPANT_ID));
      testParticipantSelected = (TestParticipant) dataSession.get(TestParticipant.class, testParticipantId);
      connectionLabel = testParticipantSelected.getConnectionLabel();
    }
    if (testParticipantSelected != null)
    {
      webSession.setAttribute(ATTRIBUTE_TEST_PARTICIPANT, testParticipantSelected);
    } else
    {
      webSession.removeAttribute(ATTRIBUTE_TEST_PARTICIPANT);
    }

    if (testConducted == null && !connectionLabel.equals(""))
    {
      testConducted = getLatestTestConducted(connectionLabel, dataSession);
    }

    if (testConducted != null && testParticipantSelected != null)
    {
      if (!testConducted.getConnectionLabel().equals(testParticipantSelected.getConnectionLabel()))
      {
        testConducted = null;
      }
    }

    if (testConducted != null)
    {
      webSession.setAttribute(ATTRIBUTE_TEST_CONDUCTED, testConducted);
    } else
    {
      webSession.removeAttribute(ATTRIBUTE_TEST_CONDUCTED);
    }

    TestMessage testMessage = null;
    {
      String testMessageIdString = req.getParameter(PARAM_TEST_MESSAGE_ID);
      if (testMessageIdString != null)
      {
        testMessage = (TestMessage) dataSession.get(TestMessage.class, Integer.parseInt(testMessageIdString));
        if (testMessage != null)
        {
          webSession.setAttribute(ATTRIBUTE_TEST_MESSAGE, testMessage);
        } else
        {
          webSession.removeAttribute(ATTRIBUTE_TEST_MESSAGE);
        }
      } else
      {
        testMessage = (TestMessage) webSession.getAttribute(ATTRIBUTE_TEST_MESSAGE);
        if (testMessage != null && testConducted != null && !testMessage.getTestSection().getTestConducted().equals(testConducted))
        {
          webSession.removeAttribute(ATTRIBUTE_TEST_MESSAGE);
          testMessage = null;
        }
      }
    }

    if (testConducted != null && connectionLabel.equals(""))
    {
      connectionLabel = testConducted.getConnectionLabel();
    }

    ComparisonField comparisonField = null;
    if (req.getParameter(PARAM_COMPARISON_FIELD_ID) != null)
    {
      int comparisonFieldId = Integer.parseInt(req.getParameter(PARAM_COMPARISON_FIELD_ID));
      comparisonField = (ComparisonField) dataSession.get(ComparisonField.class, comparisonFieldId);
    }

    try
    {
      createHeader(webSession);
      if (view.equals(VIEW_HOME))
      {
        out.println("<div class=\"leftColumn\">");
        out.println("<div class=\"topBox\">");
        printLogin(userSession.getUser(), userSession);
        out.println("</div>");
        out.println("</div>");
      } else if (view.equals(VIEW_REPORTS))
      {
        out.println("<div class=\"leftColumn\">");
        {
          Query query = dataSession
              .createQuery("select connectionLabel, max(testStartedTime) from TestConducted group by connectionLabel order by connectionLabel");
          List<Object[]> connectionLabelAndDateList = query.list();
          out.println("<table width=\"100%\">");
          out.println("  <caption>Reports</caption>");
          out.println("  <tr>");
          out.println("    <th>Connection</th>");
          out.println("    <th>Date</th>");
          out.println("  </tr>");
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
          for (Object[] connectionLabelAndDate : connectionLabelAndDateList)
          {
            String string = (String) connectionLabelAndDate[0];
            Date date = (Date) connectionLabelAndDate[1];
            String link = "testReport?" + PARAM_VIEW + "=" + VIEW_REPORTS + "&" + PARAM_CONNECTION_LABEL + "=" + URLEncoder.encode(string, "UTF-8");
            String selected = "";
            if (connectionLabel.equals(string))
            {
              selected = " class=\"selected\"";
            }
            out.println("  <tr>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + string + "</a></td>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + sdf.format(date) + "</a></td>");
            out.println("  </tr>");
          }
          out.println("</table>");
        }
        out.println("</div>");

        printReport(connectionLabel, testConducted, VIEW_REPORTS, userSession);
      } else if (view.equals(VIEW_FIELD_COMPARISON) && testMessage != null)
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);

        {
          Query query = dataSession.createQuery("from Comparison where testMessage = ? order by comparisonField.fieldName");
          query.setParameter(0, testMessage);
          List<Comparison> comparisonList = query.list();
          String lastSegment = "XXXX";
          for (Comparison c : comparisonList)
          {
            ComparisonField cf = c.getComparisonField();
            String currentSegment = cf.getFieldName();
            {
              int pos = currentSegment.indexOf("-");
              if (pos != -1)
              {
                currentSegment = currentSegment.substring(0, pos);
              }
            }
            if (!currentSegment.equals(lastSegment))
            {
              if (!lastSegment.equals("XXXX"))
              {
                out.println("</table>");
                out.println("<br/>");
              }
              out.println("<table width=\"100%\">");
              out.println("  <caption>" + currentSegment + "</caption>");
              lastSegment = currentSegment;
            }

            String link = "testReport?" + PARAM_VIEW + "=" + VIEW_FIELD_COMPARISON + "&" + PARAM_COMPARISON_FIELD_ID + "="
                + cf.getComparisonFieldId();
            String selected = "";
            if (cf == comparisonField)
            {
              selected = " class=\"selected\"";
            }
            out.println("  <tr>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + cf.getFieldName() + "</a></td>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + cf.getFieldLabel() + "</a></td>");
            out.println("  </tr>");
          }
          if (!lastSegment.equals("XXXX"))
          {
            out.println("</table>");
            out.println("<br/>");
          }
        }
        out.println("</div>");
        if (comparisonField != null)
        {
          out.println("<div class=\"rightFullColumn\">");
          printComparisons(comparisonField, testMessage, userSession);
          out.println("</div>");
        }
      } else if (view.equals(VIEW_TEST_MESSAGES))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);

        Map<String, TestSection> testSectionMap = createTestSectionMap(testConducted, dataSession);
        for (String testSectionType : TEST_SECTIONS_TO_DISPLAY)
        {
          TestSection testSection = testSectionMap.get(testSectionType);
          if (testSection != null)
          {
            Query query = dataSession.createQuery("from TestMessage where testSection = ? order by testCaseCategory");
            query.setParameter(0, testSection);
            List<TestMessage> testMessageList = query.list();
            if (testMessageList.size() > 0)
            {
              out.println("<table width=\"100%\">");
              out.println("  <caption>" + testSectionType + "</caption>");
              for (TestMessage tm : testMessageList)
              {
                String link = "testReport?" + PARAM_VIEW + "=" + VIEW_TEST_MESSAGES + "&" + PARAM_TEST_MESSAGE_ID + "=" + tm.getTestMessageId();
                String selected = "";
                if (tm == testMessage)
                {
                  selected = " class=\"selected\"";
                }
                out.println("  <tr>");
                out.println("    <td" + selected + "><a href=\"" + link + "\">" + tm.getTestCaseCategory() + "</a></td>");
                out.println("    <td" + selected + "><a href=\"" + link + "\">" + tm.getTestCaseDescription() + "</a></td>");
                out.println("  </tr>");
              }
              out.println("</table>");
              out.println("<br/>");
            }
          }
        }
        out.println("</div>");
        out.println("<div class=\"rightFullColumn\">");
        if (testMessage != null)
        {
          out.println("<h3>" + testMessage.getTestCaseDescription() + "</h3>");
          out.println(
              "<p>Here is an example of one of the messages that was submitted to each IIS. (Every IIS received the same message with slightly different data and perhaps additional modifications required by the IIS.)</p>");
          out.println("<pre>" + addHovers(testMessage.getPrepMessageActual()) + "</pre>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testConducted.latestTest = ? and testCaseCategory = ? order by testSection.testConducted.connectionLabel");
          query.setParameter(0, true);
          query.setParameter(1, testMessage.getTestCaseCategory());
          List<TestMessage> exampleTestMessageList = query.list();

          {
            out.println("<table>");
            out.println("  <caption>Tests Conducted</caption>");
            out.println("  <tr>");
            out.println("    <th>Connection</th>");
            out.println("    <th>Date</th>");
            out.println("    <th>Status</th>");
            out.println("  </tr>");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            for (TestMessage exampleTm : exampleTestMessageList)
            {
              String link = "testReport?" + PARAM_VIEW + "=" + VIEW_BASIC + "&" + PARAM_CONNECTION_LABEL + "="
                  + URLEncoder.encode(exampleTm.getTestSection().getTestConducted().getConnectionLabel(), "UTF-8") + "&" + PARAM_TEST_MESSAGE_ID + "="
                  + exampleTm.getTestMessageId();
              out.println("  <tr>");
              String label = exampleTm.getTestSection().getTestConducted().getConnectionLabel();
              out.println("    <td><a href=\"" + link + "\">" + label + "</a></td>");
              out.println("    <td>" + sdf.format(exampleTm.getTestSection().getTestConducted().getTestStartedTime()) + "</td>");
              if (exampleTm.isResultAccepted())
              {
                out.println("    <td class=\"pass\">Accepted</td>");
              } else
              {
                out.println("    <td class=\"fail\">NOT Accepted</td>");
              }
              out.println("  </tr>");
            }
            out.println("</table>");
            out.println("<br/>");
          }

          for (TestMessage exampleTm : exampleTestMessageList)
          {
            String label = exampleTm.getTestSection().getTestConducted().getConnectionLabel();
            if (exampleTm.isResultAccepted())
            {
              label += " <span class=\"pass\">Accepted</span>";
            } else
            {
              label += " <span class=\"fail\">NOT Accepted</span>";
            }
            out.println("<h3>" + label + "</h3>");
            out.println("<pre>" + addHovers(exampleTm.getResultMessageActual()) + "</pre>");
          }
        }
        out.println("</div>");
      } else if (view.equals(VIEW_DASHBOARD) && testParticipantSelected != null)
      {
        out.println("<div class=\"leftColumn\">");
        out.println("<table width=\"100%\">");
        out.println("  <caption>" + testParticipantSelected.getOrganizationName() + "</caption>");
        out.println("  <tr>");
        out.println("    <th>Platform</th>");
        out.println("    <td>" + testParticipantSelected.getPlatformLabel() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>Vendor</th>");
        out.println("    <td>" + testParticipantSelected.getVendorLabel() + "</td>");
        out.println("  </tr>");
        if (!testParticipantSelected.getGeneralComments().equals(""))
        {
          out.println("  <tr>");
          out.println("    <td colspan=\"2\">" + testParticipantSelected.getGeneralComments() + "</td>");
          out.println("  </tr>");
        }
        if (!testParticipantSelected.getInternalComments().equals(""))
        {
          out.println("  <tr>");
          out.println("    <td colspan=\"2\">" + testParticipantSelected.getInternalComments() + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br/>");

        out.println("<table width=\"100%\">");
        out.println("  <caption>Phase I</caption>");
        out.println("  <tr>");
        out.println("    <th>Participation</th>");
        out.println("    <td>" + testParticipantSelected.getPhase1Participation() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>Status</th>");
        out.println("    <td>" + testParticipantSelected.getPhase1Status() + "</td>");
        out.println("  </tr>");
        if (!testParticipantSelected.getPhase1Comments().equals(""))
        {
          out.println("  <tr>");
          out.println("    <td colspan=\"2\">" + testParticipantSelected.getPhase1Comments() + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br/>");

        out.println("<table width=\"100%\">");
        out.println("  <caption>Phase II</caption>");
        out.println("  <tr>");
        out.println("    <th>Participation</th>");
        out.println("    <td>" + testParticipantSelected.getPhase2Participation() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>Status</th>");
        out.println("    <td>" + testParticipantSelected.getPhase2Status() + "</td>");
        out.println("  </tr>");
        if (!testParticipantSelected.getPhase2Comments().equals(""))
        {
          out.println("  <tr>");
          out.println("    <td colspan=\"2\">" + testParticipantSelected.getPhase2Comments() + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br/>");

        out.println("<table width=\"100%\">");
        out.println("  <caption>Status</caption>");
        out.println("  <tr>");
        out.println("    <th>Guide</th>");
        out.println("    <td>" + testParticipantSelected.getGuideStatus() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>Transport</th>");
        out.println("    <td>" + testParticipantSelected.getTransportType() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>Connect</th>");
        out.println("    <td>" + testParticipantSelected.getConnectStatus() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>Query</th>");
        out.println("    <td>" + testParticipantSelected.getQuerySupport() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>NIST</th>");
        out.println("    <td>" + testParticipantSelected.getNistStatus() + "</td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("</div>");

        printReport(connectionLabel, testConducted, VIEW_DASHBOARD, userSession);

      } else if (view.equals(VIEW_MAP) || (view.equals(VIEW_DASHBOARD) && testParticipantSelected == null))
      {

        String mapStatusSelected = req.getParameter(PARAM_MAP_STATUS);
        if (mapStatusSelected == null)
        {
          mapStatusSelected = MAP_STATUS_PHASE1_PARTICIPATION;
        }
        Map<String, List<String>> filterOptionListMap = new HashMap<String, List<String>>();
        Map<String, Map<String, Integer>> filterOptionCountMapMap = new HashMap<String, Map<String, Integer>>();
        Map<String, String> filterStatusMap = new HashMap<String, String>();

        for (int i = 0; i < MAP_STATUS.length; i++)
        {
          String mapStatus = MAP_STATUS[i];
          filterOptionListMap.put(mapStatus, new ArrayList<String>());
          filterOptionCountMapMap.put(mapStatus, new HashMap<String, Integer>());
          String filterValue = req.getParameter(PARAM_MAP_FILTER + i);
          if (filterValue == null)
          {
            filterValue = "";
          }
          filterStatusMap.put(mapStatus, filterValue);
        }

        int maxCols = RecordServletInterface.MAP_COLS_MAX;
        int maxRows = RecordServletInterface.MAP_ROWS_MAX;
        TestParticipant[][] testParticipantGrid = new TestParticipant[maxCols][maxRows];
        Query query = dataSession.createQuery("from TestParticipant where mapRow > 0 and mapCol > 0");
        List<TestParticipant> testParticipantList = query.list();
        for (TestParticipant testParticipant : testParticipantList)
        {
          if (testParticipant.getMapCol() <= maxCols && testParticipant.getMapRow() <= maxRows)
          {
            Map<String, String> filterValueMap = createFilterValueMap(testParticipant, dataSession);
            boolean okayToShow = true;
            for (int i = 0; i < MAP_STATUS.length; i++)
            {
              String mapStatus = MAP_STATUS[i];
              if (!filterStatusMap.get(mapStatus).equals(""))
              {
                if (!filterValueMap.get(mapStatus).equals(filterStatusMap.get(mapStatus)))
                {
                  okayToShow = false;
                  break;
                }
              }
            }
            if (okayToShow)
            {
              testParticipant.setFilterValueMap(filterValueMap);
              testParticipantGrid[testParticipant.getMapCol() - 1][testParticipant.getMapRow() - 1] = testParticipant;
              for (int i = 0; i < MAP_STATUS.length; i++)
              {
                String mapStatus = MAP_STATUS[i];
                String statusValue = filterValueMap.get(mapStatus);
                addToList(filterOptionListMap, mapStatus, statusValue);
                Map<String, Integer> filterOptionCountMap = filterOptionCountMapMap.get(mapStatus);
                Integer count = filterOptionCountMap.get(statusValue);
                if (count == null)
                {
                  count = new Integer(1);
                } else
                {
                  count = count + 1;
                }
                filterOptionCountMap.put(statusValue, count);
              }
            }
          }
        }
        for (int i = 0; i < MAP_STATUS.length; i++)
        {
          String mapStatus = MAP_STATUS[i];
          Collections.sort(filterOptionListMap.get(mapStatus));
        }

        out.println("<div class=\"leftColumn\">");
        out.println("<form method=\"GET\" action=\"testReport\">");
        out.println("<input type=\"hidden\" name=\"" + PARAM_VIEW + "\" value=\"" + VIEW_MAP + "\"/>");
        out.println("<table width=\"100%\">");
        out.println("  <caption>Show Dashboard</caption>");
        for (int i = 0; i < MAP_STATUS.length; i++)
        {
          out.println("  <tr>");
          String mapStatus = MAP_STATUS[i];
          out.println("    <td>");
          out.println("      <center><input type=\"submit\" name=\"" + PARAM_MAP_STATUS + "\" value=\"" + mapStatus + "\"/></center>");
          out.println("    </td>");
          i++;
          if (i < MAP_STATUS.length)
          {
            mapStatus = MAP_STATUS[i];
            out.println("    <td>");
            out.println("      <center><input type=\"submit\" name=\"" + PARAM_MAP_STATUS + "\" value=\"" + mapStatus + "\"/></center>");
            out.println("    </td>");
          } else
          {
            out.println("    <td></td>");
          }
          out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br/>");

        out.println("<table width=\"100%\">");
        out.println("  <caption>Filter By</caption>");
        for (int i = 0; i < MAP_STATUS.length; i++)
        {
          String mapStatus = MAP_STATUS[i];
          List<String> filterOptionList = filterOptionListMap.get(mapStatus);
          out.println("  <tr>");
          out.println("    <td>" + mapStatus + "</td>");
          out.println("    <td>");
          out.println("      <select name=\"" + PARAM_MAP_FILTER + i + "\">");
          out.println("        <option value=\"\">-</option>");
          for (String filterOption : filterOptionList)
          {
            if (filterStatusMap.get(mapStatus).equals(filterOption))
            {
              out.println("        <option value=\"" + filterOption + "\" selected=\"true\">" + filterOption + " ("
                  + filterOptionCountMapMap.get(mapStatus).get(filterOption) + ")</option>");
            } else
            {
              out.println("        <option value=\"" + filterOption + "\">" + filterOption + " ("
                  + filterOptionCountMapMap.get(mapStatus).get(filterOption) + ")</option>");
            }
          }
          out.println("      </select>");
          out.println("    </td>");
          out.println("  </tr>");
        }
        out.println("</table>");
        for (int i = 0; i < MAP_STATUS.length; i++)
        {
          String mapStatus = MAP_STATUS[i];
          List<String> filterOptionList = filterOptionListMap.get(mapStatus);
          out.println("<br/>");
          out.println("<table>");
          out.println("  <caption>" + mapStatus + "</caption>");
          for (String filterOption : filterOptionList)
          {
            out.println("  <tr>");
            out.println("    <td>" + filterOption + "</td>");
            out.println("    <td>" + filterOptionCountMapMap.get(mapStatus).get(filterOption) + "</td>");
            out.println("  </tr>");
          }
          out.println("</table>");
        }
        out.println("</div>");

        out.println("<div class=\"rightFullColumn\">");
        {
          out.println("<table>");
          out.println("  <caption>" + mapStatusSelected + "</caption>");
          for (int row = 0; row < maxRows; row++)
          {
            out.println("  <tr>");
            for (int col = 0; col < maxCols; col++)
            {
              if (testParticipantGrid[col][row] == null)
              {
                out.println("    <td class=\"mapEmpty\"></td>");
              } else
              {
                TestParticipant testParticipant = testParticipantGrid[col][row];
                Map<String, String> filterValueMap = testParticipant.getFilterValueMap();
                String mapValue = filterValueMap.get(mapStatusSelected);
                if (mapValue.equals(""))
                {
                  out.println("    <td class=\"map\">");
                } else
                {
                  boolean pass = false;
                  for (String passValue : MAP_STATUS_PASSING_VALUES.get(mapStatusSelected))
                  {
                    if (passValue.equalsIgnoreCase(mapValue))
                    {
                      pass = true;
                      break;
                    }
                  }
                  if (pass)
                  {
                    out.println("    <td class=\"mapPass\">");
                  } else
                  {
                    out.println("    <td class=\"mapFail\">");
                  }
                }
                String link = "testReport?" + PARAM_VIEW + "=" + VIEW_DASHBOARD + "&" + PARAM_TEST_PARTICIPANT_ID + "="
                    + testParticipant.getTestParticipantId();
                if (testParticipant.getOrganizationName().length() == 2)
                {
                  out.println("<font size=\"+2\"><b><a href=\"" + link + "\">" + testParticipant.getOrganizationName() + "</a></b></font>");
                } else
                {
                  out.println("<font size=\"+1\"><b><a href=\"" + link + "\">" + testParticipant.getOrganizationName() + "</a></font></b>");
                }
                if (!mapStatusSelected.equals(""))
                {
                  out.println("<br/>");
                  out.println("<br/>");
                  out.println("<font size=\"-1\">" + mapValue + "</font>");
                }
                out.println("</td>");
              }
            }
            out.println("  </tr>");
          }
        }
        out.println("</div>");

      } else if (view.equals(VIEW_CONNECTION))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);
        out.println("</div>");

        out.println("<div class=\"rightFullColumn\">");
        {
          out.println("<table>");
          out.println("  <caption>" + testConducted.getConnectionLabel() + "</caption>");
          out.println("  <tr>");
          out.println("    <th>Connection Type</th>");
          out.println("    <td>" + testConducted.getConnectionType() + "</td>");
          out.println("  </tr>");
          out.println("  <tr>");
          out.println("    <th>URL</th>");
          out.println("    <td>" + testConducted.getConnectionUrl() + "</td>");
          out.println("  </tr>");
          out.println("  <tr>");
          out.println("    <th>Ack Type</th>");
          out.println("    <td>" + testConducted.getConnectionAckType() + "</td>");
          out.println("  </tr>");
          out.println("</table>");
          out.println("<pre>" + testConducted.getConnectionConfig() + "</pre>");
          out.println("<br/>");
        }
        out.println("</div>");
      } else if (view.equals(VIEW_STATUS))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);
        out.println("</div>");
        out.println("<div class=\"rightFullColumn\">");
        {
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm aa zz");
          out.println("<table>");
          out.println("  <caption>Test Conducted</caption>");
          out.println("  <tr>");
          out.println("    <th>Start Time</th>");
          out.println("    <td>" + sdf.format(testConducted.getTestStartedTime()) + "</td>");
          out.println("  </tr>");
          out.println("  <tr>");
          out.println("    <th>Finish Time</th>");
          if (testConducted.getTestFinishedTime() == null)
          {
            out.println("    <td></td>");
          } else
          {
            out.println("    <td>" + sdf.format(testConducted.getTestFinishedTime()) + "</td>");
          }
          out.println("  </tr>");
          out.println("  <tr>");
          out.println("    <th>Update Count</th>");
          out.println("    <td>" + testConducted.getCountUpdate() + "</td>");
          out.println("  </tr>");
          out.println("  <tr>");
          out.println("    <th>Query Enabled</th>");
          out.println("    <td>" + testConducted.getQueryEnabled() + "</td>");
          out.println("  </tr>");
          if (testConducted.getQueryEnabled().equals("Y"))
          {
            out.println("  <tr>");
            out.println("    <th>Query Type</th>");
            out.println("    <td>" + testConducted.getQueryType() + "</td>");
            out.println("  </tr>");
            out.println("  <tr>");
            out.println("    <th>Query Pause</th>");
            out.println("    <td>" + testConducted.getQueryPause() + "</td>");
            out.println("  </tr>");
            out.println("  <tr>");
            out.println("    <th>Query Count</th>");
            out.println("    <td>" + testConducted.getCountQuery() + "</td>");
            out.println("  </tr>");
          }
          out.println("  <tr>");
          out.println("    <th>Local Profile</th>");
          out.println("    <td>" + testConducted.getProfileBaseName() + "</td>");
          out.println("  </tr>");
          out.println("  <tr>");
          out.println("    <th>National Profile</th>");
          out.println("    <td>" + testConducted.getProfileCompareName() + "</td>");
          out.println("  </tr>");
          out.println("</table>");
          out.println("<br/>");
          out.println("<pre>" + testConducted.getTestLog() + "</pre>");
        }
        out.println("</div>");
      } else if (view.equals(VIEW_PERFORMANCE))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);
        out.println("</div>");
        out.println("<div class=\"centerColumn\">");
        {
          if (testConducted.getPerUpdateCount() > 0)
          {
            int average = (int) (((double) testConducted.getPerUpdateTotal()) / testConducted.getPerUpdateCount() + 0.5);
            out.println("<table width=\"100%\">");
            out.println("  <caption>Update Performance</caption>");
            out.println("  <tr>");
            out.println("    <th>Average</th>");
            out.println("    <td>" + createTime(average) + "</td>");
            out.println("  </tr>");
            out.println("  <tr>");
            out.println("    <th>Min</th>");
            out.println("    <td>" + createTime(testConducted.getPerUpdateMin()) + "</td>");
            out.println("  </tr>");
            out.println("  <tr>");
            out.println("    <th>Max</th>");
            out.println("    <td>" + createTime(testConducted.getPerUpdateMax()) + "</td>");
            out.println("  </tr>");
            out.println("  <tr>");
            out.println("    <th>Std Dev</th>");
            out.println("    <td>" + createTime(testConducted.getPerUpdateStd()) + "</td>");
            out.println("  </tr>");
            out.println("</table>");
          }
        }
        out.println("</div>");
      } else if (view.equals(VIEW_INTEROP))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);
        TestSection testSection = null;
        String testSectionType = RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC;
        if (view.equals(VIEW_TOLERANCE))
        {
          testSectionType = RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL;
        }
        testSection = getTestSection(testConducted, testSection, testSectionType, dataSession);
        if (testSection != null)
        {
          Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = ? order by testPosition");
          query.setParameter(0, testSection);
          query.setParameter(1, RecordServletInterface.VALUE_TEST_TYPE_UPDATE);
          List<TestMessage> testMessageList = query.list();
          out.println("<table width=\"100%\">");
          out.println("  <caption>Update Messages</caption>");
          out.println("  <tr>");
          out.println("    <th>Test Case</th>");
          out.println("    <th>Changed</th>");
          out.println("    <th>Accepted</th>");
          out.println("  </tr>");
          for (TestMessage tm : testMessageList)
          {

            String selected = "";
            if (tm == testMessage)
            {
              selected = " class=\"selected\"";
            }
            String link = "testReport?" + PARAM_VIEW + "=" + view + "&" + PARAM_TEST_MESSAGE_ID + "=" + tm.getTestMessageId();
            out.println("  <tr>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + tm.getTestCaseCategory() + "</a></td>");
            if (tm.getPrepMajorChagnesMade().equals("Y"))
            {
              out.println("    <td class=\"fail\">Yes</td>");
            } else if (tm.getPrepMajorChagnesMade().equals("N"))
            {
              out.println("    <td class=\"pass\">No</td>");
            } else
            {
              out.println("    <td></td>");
            }
            if (tm.getResultStatus().equals("FAIL"))
            {
              out.println("    <td class=\"fail\">No</td>");
            } else if (tm.getResultStatus().equals("PASS"))
            {
              out.println("    <td class=\"pass\">Yes</td>");
            } else if (tm.getResultStatus().equals("PASS"))
            {
              out.println("    <td></td>");
            }
            out.println("  </tr>");
          }
          out.println("</table>");
        }

        out.println("</div>");
        out.println("<div class=\"rightFullColumn\">");
        printTestMessageDetails(testMessage, userSession);
        out.println("</div>");
      } else if (view.equals(VIEW_TOLERANCE) || view.equals(VIEW_EHR))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);
        TestSection testSection = null;
        String testSectionType = RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL;
        testSection = getTestSection(testConducted, testSection, testSectionType, dataSession);
        if (testSection != null)
        {
          Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = ? order by testPosition");
          query.setParameter(0, testSection);
          query.setParameter(1, RecordServletInterface.VALUE_TEST_TYPE_UPDATE);
          List<TestMessage> testMessageList = query.list();
          out.println("<table width=\"100%\">");
          out.println("  <caption>Update Messages</caption>");
          out.println("  <tr>");
          out.println("    <th>Test Case</th>");
          out.println("    <th>Changed</th>");
          out.println("    <th>Accepted</th>");
          out.println("  </tr>");
          for (TestMessage tm : testMessageList)
          {
            String description = null;
            if (view.equals(VIEW_TOLERANCE)
                && tm.getTestCaseDescription().startsWith(RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK))
            {
              description = tm.getTestCaseDescription().substring(RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK.length()).trim();
            } else if (view.equals(VIEW_EHR)
                && tm.getTestCaseDescription().startsWith(RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE))
            {
              description = tm.getTestCaseDescription().substring(RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE.length()).trim();
            }
            if (description != null)
            {
              String selected = "";
              if (tm == testMessage)
              {
                selected = " class=\"selected\"";
              }
              String link = "testReport?" + PARAM_VIEW + "=" + view + "&" + PARAM_TEST_MESSAGE_ID + "=" + tm.getTestMessageId();
              out.println("  <tr>");
              out.println("    <td" + selected + "><a href=\"" + link + "\">" + description + "</a></td>");
              if (tm.getPrepMajorChagnesMade().equals("Y"))
              {
                out.println("    <td class=\"fail\">Yes</td>");
              } else if (tm.getPrepMajorChagnesMade().equals("N"))
              {
                out.println("    <td class=\"pass\">No</td>");
              } else
              {
                out.println("    <td></td>");
              }
              if (tm.getResultStatus().equals("FAIL"))
              {
                out.println("    <td class=\"fail\">No</td>");
              } else if (tm.getResultStatus().equals("PASS"))
              {
                out.println("    <td class=\"pass\">Yes</td>");
              } else if (tm.getResultStatus().equals("PASS"))
              {
                out.println("    <td></td>");
              }
              out.println("  </tr>");
            }
          }
          out.println("</table>");
        }

        out.println("</div>");
        out.println("<div class=\"rightFullColumn\">");
        printTestMessageDetails(testMessage, userSession);
        out.println("</div>");
      } else if (view.equals(VIEW_CODED_VALUES))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);
        TestSection testSection = null;
        String testSectionType = RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE;
        testSection = getTestSection(testConducted, testSection, testSectionType, dataSession);
        if (testSection != null)
        {
          Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = ? order by testPosition");
          query.setParameter(0, testSection);
          query.setParameter(1, RecordServletInterface.VALUE_TEST_TYPE_UPDATE);
          List<TestMessage> testMessageList = query.list();
          String lastField = "";
          for (TestMessage tm : testMessageList)
          {
            String currentField = tm.getTestCaseDescription();
            String label = currentField;
            {
              int pos = currentField.indexOf(" is ");
              if (pos != -1)
              {
                label = currentField.substring(pos + 4);
                currentField = currentField.substring(0, pos);
              }
            }
            if (!currentField.equals(lastField))
            {
              if (!lastField.equals(""))
              {
                out.println("</table>");
                out.println("<br/>");
              }
              out.println("<table width=\"100%\">");
              out.println("  <caption>" + currentField + "</caption>");
              out.println("  <tr>");
              out.println("    <th>Field</th>");
              out.println("    <th>Changed</th>");
              out.println("    <th>Accepted</th>");
              out.println("  </tr>");
              lastField = currentField;
            }
            String selected = "";
            if (tm == testMessage)
            {
              selected = " class=\"selected\"";
            }
            String link = "testReport?" + PARAM_VIEW + "=" + view + "&" + PARAM_TEST_MESSAGE_ID + "=" + tm.getTestMessageId();
            out.println("  <tr>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + label + "</a></td>");
            if (tm.getPrepMajorChagnesMade().equals("Y"))
            {
              out.println("    <td class=\"fail\">Yes</td>");
            } else if (tm.getPrepMajorChagnesMade().equals("N"))
            {
              out.println("    <td class=\"pass\">No</td>");
            } else
            {
              out.println("    <td></td>");
            }
            if (tm.getResultStatus().equals("FAIL"))
            {
              out.println("    <td class=\"fail\">No</td>");
            } else if (tm.getResultStatus().equals("PASS"))
            {
              out.println("    <td class=\"pass\">Yes</td>");
            } else if (tm.getResultStatus().equals("PASS"))
            {
              out.println("    <td></td>");
            }
            out.println("  </tr>");
          }
          if (!lastField.equals(""))
          {
            out.println("</table>");
            out.println("<br/>");
          }
        }

        out.println("</div>");
        out.println("<div class=\"rightFullColumn\">");
        printTestMessageDetails(testMessage, userSession);
        out.println("</div>");
      } else if (view.equals(VIEW_BASIC) || view.equals(VIEW_INTERMEDIATE) || view.equals(VIEW_ADVANCED) || view.equals(VIEW_PROFILING)
          || view.equals(VIEW_EXCEPTIONAL) || view.equals(VIEW_FORECAST_PREP) || view.equals(VIEW_FORECAST) || view.equals(VIEW_ONC_2015)
          || view.equals(VIEW_NOT_ACCEPTED))
      {
        out.println("<div class=\"leftColumn\">");
        printTestConductedNavigationBox(testConducted, userSession);
        TestSection testSection = null;
        testSection = getTestSection(testConducted, testSection, view, dataSession);
        if (testSection != null)
        {
          printMessages(view, testMessage, testSection, RecordServletInterface.VALUE_TEST_TYPE_PREP, "Prep Messages", userSession);
          printMessages(view, testMessage, testSection, RecordServletInterface.VALUE_TEST_TYPE_UPDATE, "Update Messages", userSession);
          printMessages(view, testMessage, testSection, RecordServletInterface.VALUE_TEST_TYPE_QUERY, "Query Messages", userSession);
        }

        out.println("</div>");
        out.println("<div class=\"rightFullColumn\">");
        if (testMessage != null)
        {
          printTestMessageDetails(testMessage, userSession);
        }
        out.println("</div>");
      }

    } catch (Exception e)
    {
      e.printStackTrace();
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }
    createFooter(webSession);
  }

  private TestSection getTestSection(TestConducted testConducted, TestSection testSection, String testSectionType, Session dataSession)
  {
    {
      Query query = dataSession.createQuery("from TestSection where testConducted = ? and testSectionType = ?");
      query.setParameter(0, testConducted);
      query.setParameter(1, testSectionType);
      List<TestSection> testSectionList = query.list();
      if (testSectionList.size() > 0)
      {
        testSection = testSectionList.get(0);
      }
    }
    return testSection;
  }

  private void printComparisons(ComparisonField comparisonField, TestMessage testMessage, UserSession userSession) throws UnsupportedEncodingException
  {
    PrintWriter out = userSession.getOut();
    Session dataSession = userSession.getDataSession();
    List<Comparison> comparisonList;
    {
      Query query = dataSession.createQuery("from Comparison where comparisonField = ? and testMessage.testSection.testConducted.latestTest = ? "
          + "and testMessage.testCaseCategory = ? " + "order by testMessage.testSection.testConducted.connectionLabel");
      query.setParameter(0, comparisonField);
      query.setParameter(1, true);
      query.setParameter(2, testMessage.getTestCaseCategory());
      comparisonList = query.list();
    }
    if (comparisonList.size() > 0)
    {
      out.println("<table>");
      out.println("  <caption>" + comparisonField.getFieldName() + " " + comparisonField.getFieldLabel() + "</caption>");
      out.println("  <tr>");
      out.println("    <th>Connection</th>");
      out.println("    <th>Date</th>");
      out.println("    <th>Original</th>");
      out.println("    <th>Compare</th>");
      out.println("    <th>Status</th>");
      out.println("  </tr>");

      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      for (Comparison comparison : comparisonList)
      {
        TestMessage exampleTm = comparison.getTestMessage();
        String link = "testReport?" + PARAM_VIEW + "=" + VIEW_BASIC + "&" + PARAM_CONNECTION_LABEL + "="
            + URLEncoder.encode(exampleTm.getTestSection().getTestConducted().getConnectionLabel(), "UTF-8") + "&" + PARAM_TEST_MESSAGE_ID + "="
            + exampleTm.getTestMessageId();
        out.println("  <tr>");
        String label = exampleTm.getTestSection().getTestConducted().getConnectionLabel();
        out.println("    <td><a href=\"" + link + "\">" + label + "</a></td>");
        out.println("    <td>" + sdf.format(exampleTm.getTestSection().getTestConducted().getTestStartedTime()) + "</td>");
        out.println("    <td>" + comparison.getValueOriginal() + "</td>");
        out.println("    <td>" + comparison.getValueCompare() + "</td>");
        if (comparison.getComparisonStatus().equals(RecordServletInterface.VALUE_COMPARISON_STATUS_PASS))
        {
          out.println("    <td class=\"pass\">Pass</td>");
        } else if (comparison.getComparisonStatus().equals(RecordServletInterface.VALUE_COMPARISON_STATUS_FAIL))
        {
          out.println("    <td class=\"fail\">Fail</td>");
        } else if (comparison.getComparisonStatus().equals(RecordServletInterface.VALUE_COMPARISON_STATUS_NOT_TESTED))
        {
          out.println("    <td>Not Tested</td>");
        } else
        {
          out.println("    <td></td>");
        }
        out.println("  </tr>");
      }
      out.println("</table>");
      out.println("<br/>");
    }
  }

  private TestConducted getLatestTestConducted(String connectionLabel, Session dataSession)
  {
    TestConducted testConducted = null;
    List<TestConducted> testConductedList = null;
    Query query = dataSession.createQuery("from TestConducted where connectionLabel = ? and latestTest = ? order by testStartedTime desc");
    query.setParameter(0, connectionLabel);
    query.setParameter(1, true);
    testConductedList = query.list();
    if (testConductedList.size() > 0)
    {
      testConducted = testConductedList.get(0);
    }
    return testConducted;
  }

  private void printReport(String connectionLabel, TestConducted testConducted, String view, UserSession userSession)
      throws UnsupportedEncodingException
  {
    PrintWriter out = userSession.getOut();
    Session dataSession = userSession.getDataSession();
    {
      out.println("<div class=\"centerColumn\">");
      printTestConductedNavigationBox(testConducted, userSession);
      if (!connectionLabel.equals(""))
      {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm aa zz");
        List<TestConducted> testConductedList = null;
        Query query = dataSession.createQuery("from TestConducted where connectionLabel = ? order by testStartedTime desc");
        query.setParameter(0, connectionLabel);
        testConductedList = query.list();
        if (testConductedList.size() > 0)
        {
          out.println("<table width=\"100%\">");
          out.println("  <caption>Tests Conducted</caption>");
          out.println("  <tr>");
          out.println("    <th>Date</th>");
          out.println("    <th>Status</th>");
          out.println("    <th>Score</th>");
          out.println("    <th>Count</th>");
          out.println("  </tr>");
          for (TestConducted tc : testConductedList)
          {
            String selected = "";
            if (tc.equals(testConducted))
            {
              selected = " class=\"selected\"";
            }
            String link = "testReport?" + PARAM_VIEW + "=" + view + "&" + PARAM_CONNECTION_LABEL + "="
                + URLEncoder.encode(tc.getConnectionLabel(), "UTF-8") + "&" + PARAM_TEST_CONDUCTED_ID + "=" + tc.getTestConductedId();
            out.println("  <tr>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + sdf.format(tc.getTestStartedTime()) + "</a></td>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + tc.getTestStatus() + "</a></td>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + tc.getScoreOverall() + "</a></td>");
            out.println("    <td" + selected + "><a href=\"" + link + "\">" + tc.getCountUpdate() + "/" + tc.getCountQuery() + "</a></td>");
            out.println("  </tr>");
          }
          out.println("</table>");
        }
      }
      out.println("</div>");
    }

    out.println("<div class=\"rightColumn\">");
    if (testConducted != null)
    {
      printTestConductedOverview(testConducted, userSession);
    }
    out.println("</div>");
  }

  private Map<String, String> createFilterValueMap(TestParticipant testParticipant, Session dataSession)
  {
    Map<String, String> filterValueMap = new HashMap<String, String>();
    {
      filterValueMap.put(MAP_STATUS_PHASE1_PARTICIPATION, testParticipant.getPhase1Participation());
      filterValueMap.put(MAP_STATUS_PHASE1_STATUS, testParticipant.getPhase1Status());
      filterValueMap.put(MAP_STATUS_PHASE2_PARTICIPATION, testParticipant.getPhase2Participation());
      filterValueMap.put(MAP_STATUS_PHASE2_STATUS, testParticipant.getPhase2Status());
      filterValueMap.put(MAP_STATUS_CONNECTED, testParticipant.getConnectStatus());
      filterValueMap.put(MAP_STATUS_GUIDE, testParticipant.getGuideStatus());
      filterValueMap.put(MAP_STATUS_NIST, testParticipant.getNistStatus());
      filterValueMap.put(MAP_STATUS_IHS, testParticipant.getIhsStatus());
      filterValueMap.put(MAP_STATUS_TRANSPORT, testParticipant.getTransportType());
      filterValueMap.put(MAP_STATUS_QUERY, testParticipant.getQuerySupport());
      if (testParticipant.getReportRunStatus().equals(""))
      {

        TestConducted testConducted = getLatestTestConducted(testParticipant.getConnectionLabel(), dataSession);
        if (testConducted == null)
        {
          filterValueMap.put(MAP_STATUS_REPORT_RESULTS, "Never Ran");
        } else
        {
          Calendar calendar = Calendar.getInstance();
          calendar.add(Calendar.DAY_OF_MONTH, -4);
          Date recentDate = calendar.getTime();
          calendar.add(Calendar.DAY_OF_MONTH, 7);
          calendar.add(Calendar.MONTH, -1);
          Date overdueDate = calendar.getTime();
          int scoreOverall = testConducted.getScoreOverall();
          if (recentDate.before(testConducted.getTestStartedTime()))
          {
            testParticipant.setReportRunStatus("Up-to-date");
          } else if (overdueDate.before(testConducted.getTestStartedTime()))
          {
            testParticipant.setReportRunStatus("Recently Ran");
          } else if (scoreOverall >= 70)
          {
            testParticipant.setReportRunStatus("Overdue");
          }
          filterValueMap.put(MAP_STATUS_REPORT_RESULTS, testParticipant.getReportRunStatus());
        }
      }
    }
    return filterValueMap;
  }

  private void addToList(Map<String, List<String>> filterOptionListMap, String mapStatus, String statusValue)
  {
    List<String> filterOptionList = filterOptionListMap.get(mapStatus);
    if (!statusValue.equals("") && !filterOptionList.contains(statusValue))
    {
      filterOptionList.add(statusValue);
    }
  }

  private void printTestMessageDetails(TestMessage testMessage, UserSession userSession) throws IOException
  {
    PrintWriter out = userSession.getOut();
    Session dataSession = userSession.getDataSession();
    if (testMessage != null)
    {
      out.println("<h3>" + testMessage.getTestCaseDescription() + "</h3>");
      out.println("<pre>" + addHovers(testMessage.getPrepMessageActual()) + "</pre>");
      if (testMessage.isResultAccepted())
      {
        out.println("<h3 class=\"pass\">Accepted</h3>");
      } else
      {
        out.println("<h3 class=\"fail\">NOT Accepted</h3>");
      }
      out.println("<pre>" + addHovers(testMessage.getResultMessageActual()) + "</pre>");

      {
        Query query = dataSession.createQuery("from Assertion where testMessage = ? order by location_path");
        query.setParameter(0, testMessage);
        List<Assertion> assertionList = query.list();
        if (assertionList.size() > 0)
        {
          out.println("<table width=\"100%\">");
          out.println("  <caption>ONC 2015 Validation of Response</caption>");
          out.println("  <tr>");
          out.println("    <th>Location</th>");
          out.println("    <th>Result</th>");
          out.println("    <th>Type</th>");
          out.println("    <th>Description</th>");
          out.println("  </tr>");
          for (Assertion assertion : assertionList)
          {
            String classString = " class=\"pass\"";
            if (assertion.getAssertionResult().equalsIgnoreCase("ERROR"))
            {
              classString = " class=\"fail\"";
            }
            out.println("  <tr>");
            out.println("    <td" + classString + ">" + assertion.getLocationPath() + "</td>");
            out.println("    <td" + classString + ">" + assertion.getAssertionResult() + "</td>");
            out.println("    <td" + classString + ">" + assertion.getAssertionField().getAssertionType() + "</td>");
            out.println("    <td" + classString + ">" + assertion.getAssertionField().getAssertionDescription() + "</td>");
            out.println("  </tr>");
          }
          out.println("</table>");
          out.println("<br/>");
        }
      }

      Query query = dataSession.createQuery("from Comparison where testMessage = ? order by comparisonField.fieldName");
      query.setParameter(0, testMessage);
      List<Comparison> comparisonList = query.list();
      if (comparisonList.size() > 0)
      {
        out.println("<table width=\"100%\">");
        out.println("  <caption>Field Comparison</caption>");
        out.println("  <tr>");
        out.println("    <th>Field</th>");
        out.println("    <th>HL7</th>");
        out.println("    <th>Core Data</th>");
        out.println("    <th>Original Value</th>");
        out.println("    <th>Compare Value</th>");
        out.println("    <th>Status</th>");
        out.println("  </tr>");
        for (Comparison comparison : comparisonList)
        {
          String link = "testReport?" + PARAM_VIEW + "=" + VIEW_FIELD_COMPARISON + "&" + PARAM_COMPARISON_FIELD_ID + "="
              + comparison.getComparisonField().getComparisonFieldId();
          String classString = "";
          if (comparison.getComparisonStatus().equals(RecordServletInterface.VALUE_COMPARISON_STATUS_FAIL))
          {
            classString = " class=\"fail\"";
          } else if (comparison.getComparisonStatus().equals(RecordServletInterface.VALUE_COMPARISON_STATUS_PASS))
          {
            classString = " class=\"pass\"";
          }
          out.println("  <tr>");
          out.println("    <td" + classString + "><a href=\"" + link + "\">" + comparison.getComparisonField().getFieldLabel() + "</a></td>");
          out.println("    <td" + classString + ">" + comparison.getComparisonField().getFieldName() + "</td>");
          out.println("    <td" + classString + ">" + comparison.getComparisonField().getPriorityLabel() + "</td>");
          out.println("    <td" + classString + ">" + comparison.getValueOriginal() + "</td>");
          out.println("    <td" + classString + ">" + comparison.getValueCompare() + "</td>");
          out.println("    <td" + classString + ">" + comparison.getComparisonStatus() + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
      }

      String link = "testReport?" + PARAM_VIEW + "=" + VIEW_TEST_MESSAGES + "&" + PARAM_TEST_MESSAGE_ID + "=" + testMessage.getTestMessageId();
      out.println("<p><a href=\"" + link + "\">Compare</a></p>");
    }
  }

  private void printMessages(String view, TestMessage testMessage, TestSection testSection, String testType, String label, UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
    Session dataSession = userSession.getDataSession();
    Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = ? order by testPosition");
    query.setParameter(0, testSection);
    query.setParameter(1, testType);
    List<TestMessage> testMessageList = query.list();
    if (testMessageList.size() > 0)
    {
      out.println("<table width=\"100%\">");
      out.println("  <caption>" + label + "</caption>");
      out.println("  <tr>");
      out.println("    <th>Test Case</th>");
      out.println("    <th>Changed</th>");
      out.println("    <th>Accepted</th>");
      out.println("  </tr>");
      for (TestMessage tm : testMessageList)
      {

        String selected = "";
        if (tm == testMessage)
        {
          selected = " class=\"selected\"";
        }
        String link = "testReport?" + PARAM_VIEW + "=" + view + "&" + PARAM_TEST_MESSAGE_ID + "=" + tm.getTestMessageId();
        out.println("  <tr>");
        out.println("    <td" + selected + "><a href=\"" + link + "\">" + tm.getTestCaseCategory() + "</a></td>");
        if (tm.getPrepMajorChagnesMade().equals("Y"))
        {
          out.println("    <td class=\"fail\">Yes</td>");
        } else if (tm.getPrepMajorChagnesMade().equals("N"))
        {
          out.println("    <td class=\"pass\">No</td>");
        } else
        {
          out.println("    <td></td>");
        }
        if (tm.getResultStatus().equals("FAIL"))
        {
          out.println("    <td class=\"fail\">No</td>");
        } else if (tm.getResultStatus().equals("PASS"))
        {
          out.println("    <td class=\"pass\">Yes</td>");
        } else if (tm.getResultStatus().equals("PASS"))
        {
          out.println("    <td></td>");
        }
        out.println("  </tr>");
      }
      out.println("</table>");
      out.println("<br/>");
    }
  }

  private void printTestConductedNavigationBox(TestConducted testConducted, UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
    Session dataSession = userSession.getDataSession();
    out.println("<div class=\"topBox\">");
    if (testConducted != null)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm aa zz");
      out.println("<table width=\"100%\">");
      out.println("  <caption>" + testConducted.getConnectionLabel() + "</caption>");
      {
        String link = "testReport?" + PARAM_VIEW + "=" + VIEW_DEFAULT;
        out.println("  <tr>");
        out.println("    <th>Date</th>");
        out.println("    <td><a href=\"" + link + "\">" + sdf.format(testConducted.getTestStartedTime()) + "</a></td>");
        out.println("  </tr>");
      }
      {
        String link = "testReport?" + PARAM_VIEW + "=" + VIEW_CONNECTION;
        out.println("  <tr>");
        out.println("    <th>Connection</th>");
        out.println("    <td><a href=\"" + link + "\">" + testConducted.getConnectionType() + "</a></td>");
        out.println("  </tr>");
      }
      {
        String link = "testReport?" + PARAM_VIEW + "=" + VIEW_STATUS;
        out.println("  <tr>");
        out.println("    <th>Status</th>");
        out.println("    <td><a href=\"" + link + "\">" + testConducted.getTestStatus() + "</a></td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
    out.println("</div>");
  }

  private static final String[] TEST_SECTIONS_TO_DISPLAY = { RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_FORECAST_PREP, RecordServletInterface.VALUE_TEST_SECTION_TYPE_FORECAST,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE, RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015 };

  private void printTestConductedOverview(TestConducted testConducted, UserSession userSession)
  {
    PrintWriter out = userSession.getOut();
    Session dataSession = userSession.getDataSession();
    Map<String, TestSection> testSectionMap = createTestSectionMap(testConducted, dataSession);
    out.println("<table width=\"100%\">");
    out.println("  <caption>Test Areas</caption>");
    out.println("  <tr>");
    out.println("    <th>Area</th>");
    out.println("    <th>Level 1</th>");
    out.println("    <th>Level 2</th>");
    out.println("    <th>Level 3</th>");
    out.println("  </tr>");
    for (String testSectionType : TEST_SECTIONS_TO_DISPLAY)
    {
      TestSection testSection = testSectionMap.get(testSectionType);
      if (testSection != null)
      {
        out.println("  <tr>");
        {
          String link = "testReport?" + PARAM_VIEW + "=" + testSectionType;
          out.println("    <td><a href=\"" + link + "\">" + testSection.getTestSectionType() + "</a></td>");
        }
        printProgressOrScore(testSection.getProgressLevel1(), testSection.getScoreLevel1(), out);
        printProgressOrScore(testSection.getProgressLevel2(), testSection.getScoreLevel2(), out);
        printProgressOrScore(testSection.getProgressLevel3(), testSection.getScoreLevel3(), out);
        out.println("  </tr>");
      }
    }
    out.println("</table>");
    out.println("<br/>");

    out.println("<table width=\"100%\">");
    out.println("  <caption>Overall Score: " + testConducted.getScoreOverall() + "%</caption>");
    out.println("  <tr>");
    out.println("    <th>Section</th>");
    out.println("    <th>Score</th>");
    out.println("    <th>Problem</th>");
    out.println("    <th>Working</th>");
    out.println("  </tr>");
    printScoreLine("Interoperability", testConducted.getScoreInterop(), VIEW_INTEROP, out);
    printScoreLine("Coded Values", testConducted.getScoreCoded(), VIEW_CODED_VALUES, out);
    printScoreLine("Tolerance", testConducted.getScoreTolerance(), VIEW_TOLERANCE, out);
    printScoreLine("EHR Examples", testConducted.getScoreEhr(), VIEW_EHR, out);
    printScoreLine("Performance", testConducted.getScorePerform(), VIEW_PERFORMANCE, out);
    printScoreLine("Ack Conformance", testConducted.getScoreAck(), VIEW_CONFORMANCE, out);
    out.println("</table>");
    out.println("<br/>");

    out.println("<table width=\"100%\">");
    out.println("  <caption>Assumption Testing</caption>");
    out.println("  <tr>");
    out.println("    <th>Section</th>");
    out.println("    <th>Score</th>");
    out.println("    <th>Problem</th>");
    out.println("    <th>Working</th>");
    out.println("  </tr>");
    printScoreLine("Local Requirement Implementation", testConducted.getScoreLocal(), VIEW_LOCAL, out);
    printScoreLine("National Compatibility", testConducted.getScoreNational(), VIEW_NATIONAL, out);
    out.println("</table>");

  }

  private Map<String, TestSection> createTestSectionMap(TestConducted testConducted, Session dataSession)
  {
    Map<String, TestSection> testSectionMap = new HashMap<String, TestSection>();
    {
      Query query = dataSession.createQuery("from TestSection where testConducted = ? and testEnabled = ?");
      query.setParameter(0, testConducted);
      query.setParameter(1, true);
      List<TestSection> testSectionList = query.list();
      for (TestSection testSection : testSectionList)
      {
        testSectionMap.put(testSection.getTestSectionType(), testSection);
      }
    }
    return testSectionMap;
  }

  private void printProgressOrScore(int progress, int score, PrintWriter out)
  {
    String classStyle = "";
    String status = "-";
    if (progress == 100)
    {
      if (score == 100)
      {
        classStyle = "pass";
        status = score + "% pass";
      } else if (score < 0)
      {
        status = "complete";
      } else
      {
        classStyle = "fail";
        status = score + "% pass";
      }
    } else if (progress <= 0)
    {
      status = "-";
    } else
    {
      status = progress + "%...";
    }
    out.println("    <td class=\"" + classStyle + "\">" + status + "</td>");
  }

  private void printScoreLine(String label, int score, String view, PrintWriter out)
  {
    {
      out.println("  <tr>");
      {
        String link = "testReport?" + PARAM_VIEW + "=" + view;
        out.println("    <td><a href=\"" + link + "\">" + label + "</a></td>");
      }
      out.println("    <td>" + score + "%</td>");
      {
        String message = "Problem";
        if (score >= 90)
        {
          message = "Excellent";
        } else if (score >= 80)
        {
          message = "Well";
        } else if (score >= 70)
        {
          message = "Okay";
        } else if (score >= 60)
        {
          message = "Poor";
        } else if (score >= 40)
        {
          message = "Prob";
        }
        if (score >= 60)
        {
          out.println("    <td>&nbsp;</td>");
          out.println("    <td>");
          out.println("<div style=\"width: " + ((int) (score)) + "px; text-align: center;\" class=\"pass\">" + message + "</div>");
          out.println("    </td>");
        } else
        {
          out.println("    <td>");
          out.println("<div style=\"width: " + ((int) ((100 - score))) + "px; text-align: center; position: relative; float: right;\" class=\"fail\">"
              + message + "</div>");
          out.println("    </td>");
          out.println("    <td>&nbsp;</td>");
        }

      }
      // <div style="width: 200px; text-align: center;"
      // class="pass">Excellent</div>
      out.println("  </tr>");
    }
  }

  private String createTime(double ms)
  {
    if (ms < 500)
    {
      return ((int) ms) + " ms";
    } else
    {
      String seconds = String.valueOf((int) ((ms + 50) / 100));
      if (seconds.length() == 1)
      {
        seconds = "0" + seconds;
      }
      return seconds.substring(0, seconds.length() - 1) + "." + seconds.substring(seconds.length() - 1) + " s";
    }
  }

  private String createYesNo(String yesno)
  {
    if (yesno.equalsIgnoreCase("Y"))
    {
      return "Yes";
    } else if (yesno.equalsIgnoreCase("N"))
    {
      return "No";
    }
    return "";
  }

  private static String addHovers(String message) throws IOException
  {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = new BufferedReader(new StringReader(message));
    String line;
    String segmentName = "";
    int fieldCount = 0;
    while ((line = reader.readLine()) != null)
    {
      if (!line.startsWith("   ") && line.length() > 3 && line.charAt(3) == '|')
      {
        segmentName = line.substring(0, 3);
        fieldCount = 0;
      }
      for (int i = 0; i < line.length(); i++)
      {
        char c = line.charAt(i);
        if (line.charAt(i) == '|')
        {
          if (fieldCount > 0)
          {
            sb.append("</a>");
          } else
          {
            if (segmentName.equals("MSH") || segmentName.equals("FHS") || segmentName.equals("BHS"))
            {
              fieldCount++;
            }
          }
          fieldCount++;
          sb.append("<a class=\"hl7\" title=\"" + segmentName + "-" + fieldCount + "\">");
        }
        sb.append(line.charAt(i));
      }
      if (fieldCount > 0)
      {
        sb.append("</a>");
      }
      sb.append("\n");
    }
    reader.close();
    return sb.toString();
  }

}
