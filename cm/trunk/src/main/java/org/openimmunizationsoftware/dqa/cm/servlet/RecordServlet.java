package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.CentralControl;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.Comparison;
import org.openimmunizationsoftware.dqa.tr.model.ComparisonField;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class RecordServlet extends BaseServlet implements RecordServletInterface {

  public RecordServlet() {
    super("Home");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SessionFactory factory = CentralControl.getSessionFactory();
    Session dataSession = factory.openSession();
    PrintWriter out = new PrintWriter(resp.getOutputStream());
    resp.setContentType("text/plain");
    try {
      String organizationName = readValue(req, PARAM_TPAR_ORGANIZATION_NAME, 250);
      String connectionLabel = readValue(req, PARAM_TC_CONNECTION_LABEL, 250);
      Date testStartedTime = readValueDate(req, PARAM_TC_TEST_STARTED_TIME);
      if (!connectionLabel.equals("") && testStartedTime != null) {
        TestConducted testConducted = null;
        {
          Query query = dataSession.createQuery("from TestConducted where connectionLabel = ? and testStartedTime = ?");
          query.setParameter(0, connectionLabel);
          query.setParameter(1, testStartedTime);
          List<TestConducted> testConductedList = query.list();
          if (testConductedList.size() > 0) {
            testConducted = testConductedList.get(0);
          }
        }
        {
          if (testConducted == null) {
            testConducted = new TestConducted();
            testConducted.setConnectionLabel(connectionLabel);
            testConducted.setTestStartedTime(testStartedTime);
          }
          testConducted.setConnectionType(readValue(req, PARAM_TC_CONNECTION_TYPE, 250));
          testConducted.setConnectionUrl(readValue(req, PARAM_TC_CONNECTION_URL, 250));
          testConducted.setConnectionAckType(readValue(req, PARAM_TC_CONNECTION_ACK_TYPE, 250));
          testConducted.setConnectionConfig(readValue(req, PARAM_TC_CONNECTION_CONFIG));
          testConducted.setQueryType(readValue(req, PARAM_TC_QUERY_TYPE, 250));
          testConducted.setQueryEnabled(readValue(req, PARAM_TC_QUERY_ENABLED, 1));
          testConducted.setQueryEnabled(readValue(req, PARAM_TC_QUERY_PAUSE, 1));
          testConducted.setCompleteTest(readValueBoolean(req, PARAM_TC_COMPLETE_TEST));
          testConducted.setTestLog(readValue(req, PARAM_TC_TEST_LOG));
          testConducted.setTestStatus(readValue(req, PARAM_TC_TEST_STATUS, 250));
          testConducted.setTestFinishedTime(readValueDate(req, PARAM_TC_TEST_FINISHED_TIME));
          testConducted.setCountUpdate(readValueInt(req, PARAM_TC_COUNT_UPDATE));
          testConducted.setCountQuery(readValueInt(req, PARAM_TC_COUNT_QUERY));
          testConducted.setProfileBaseName(readValue(req, PARAM_TC_PROFILE_BASE_NAME, 250));
          testConducted.setProfileCompareName(readValue(req, PARAM_TC_PROFILE_COMPARE_NAME, 250));
          testConducted.setScoreOverall(readValueInt(req, PARAM_TC_SCORE_OVERALL));
          testConducted.setScoreInterop(readValueInt(req, PARAM_TC_SCORE_INTEROP));
          testConducted.setScoreCoded(readValueInt(req, PARAM_TC_SCORE_CODED));
          testConducted.setScoreLocal(readValueInt(req, PARAM_TC_SCORE_LOCAL));
          testConducted.setScoreNational(readValueInt(req, PARAM_TC_SCORE_NATIONAL));
          testConducted.setScoreTolerance(readValueInt(req, PARAM_TC_SCORE_TOLERANCE));
          testConducted.setScoreEhr(readValueInt(req, PARAM_TC_SCORE_EHR));
          testConducted.setScorePerform(readValueInt(req, PARAM_TC_SCORE_PERFORM));
          testConducted.setScoreAck(readValueInt(req, PARAM_TC_SCORE_ACK));
          testConducted.setPerQueryTotal(readValueInt(req, PARAM_TC_PER_QUERY_TOTAL));
          testConducted.setPerQueryCount(readValueInt(req, PARAM_TC_PER_QUERY_COUNT));
          testConducted.setPerQueryMin(readValueInt(req, PARAM_TC_PER_QUERY_MIN));
          testConducted.setPerQueryMax(readValueInt(req, PARAM_TC_PER_QUERY_MAX));
          testConducted.setPerQueryStd(readValueFloat(req, PARAM_TC_PER_QUERY_STD));
          testConducted.setPerUpdateTotal(readValueInt(req, PARAM_TC_PER_UPDATE_TOTAL));
          testConducted.setPerUpdateCount(readValueInt(req, PARAM_TC_PER_UPDATE_COUNT));
          testConducted.setPerUpdateMin(readValueInt(req, PARAM_TC_PER_UPDATE_MIN));
          testConducted.setPerUpdateMax(readValueInt(req, PARAM_TC_PER_UPDATE_MAX));
          testConducted.setPerUpdateStd(readValueFloat(req, PARAM_TC_PER_UPDATE_STD));
          testConducted.setLatestTest(testConducted.isCompleteTest());
          Transaction transaction = dataSession.beginTransaction();
          dataSession.saveOrUpdate(testConducted);
          transaction.commit();

          if (testConducted.isCompleteTest()) {
            Query query = dataSession.createQuery("from TestConducted where connectionLabel = ? and latestTest = ?");
            query.setParameter(0, testConducted.getConnectionLabel());
            query.setParameter(1, true);
            List<TestConducted> latestList = query.list();
            for (TestConducted latest : latestList) {
              if (latest != testConducted) {
                latest.setLatestTest(false);
                transaction = dataSession.beginTransaction();
                dataSession.saveOrUpdate(testConducted);
                transaction.commit();
              }
            }
          }
        }
        String testSectionType = readValue(req, PARAM_TS_TEST_SECTION_TYPE, 250);
        if (!testSectionType.equals("")) {
          TestSection testSection = null;
          {
            {
              Query query = dataSession.createQuery("from TestSection where testConducted = ? and testSectionType = ?");
              query.setParameter(0, testConducted);
              query.setParameter(1, testSectionType);
              List<TestSection> testSectionList = query.list();
              if (testSectionList.size() > 0) {
                testSection = testSectionList.get(0);
              }
            }
            if (testSection == null) {
              testSection = new TestSection();
              testSection.setTestConducted(testConducted);
              testSection.setTestSectionType(testSectionType);
            }
            testSection.setTestEnabled(readValueBoolean(req, PARAM_TS_TEST_ENABLED));
            testSection.setScoreLevel1(readValueInt(req, PARAM_TS_SCORE_LEVEL1));
            testSection.setScoreLevel2(readValueInt(req, PARAM_TS_SCORE_LEVEL2));
            testSection.setScoreLevel3(readValueInt(req, PARAM_TS_SCORE_LEVEL3));
            testSection.setProgressLevel1(readValueInt(req, PARAM_TS_PROGRESS_LEVEL1));
            testSection.setProgressLevel2(readValueInt(req, PARAM_TS_PROGRESS_LEVEL2));
            testSection.setProgressLevel3(readValueInt(req, PARAM_TS_PROGRESS_LEVEL3));
            testSection.setCountLevel1(readValueInt(req, PARAM_TS_COUNT_LEVEL1));
            testSection.setCountLevel2(readValueInt(req, PARAM_TS_COUNT_LEVEL2));
            testSection.setCountLevel3(readValueInt(req, PARAM_TS_COUNT_LEVEL3));

            Transaction transaction = dataSession.beginTransaction();
            dataSession.saveOrUpdate(testSection);
            transaction.commit();
          }
          String testType = readValue(req, PARAM_TM_TEST_TYPE, 250);
          if (!testType.equals("")) {
            TestMessage testMessage = new TestMessage();
            testMessage.setTestSection(testSection);
            testMessage.setTestPosition(readValueInt(req, PARAM_TM_TEST_POSITION));
            testMessage.setTestType(testType);
            testMessage.setTestCaseSet(readValue(req, PARAM_TM_TEST_CASE_SET, 250));
            testMessage.setTestCaseNumber(readValue(req, PARAM_TM_TEST_CASE_NUMBER, 250));
            testMessage.setTestCaseCategory(readValue(req, PARAM_TM_TEST_CASE_CATEGORY, 250));
            testMessage.setTestCaseDescription(readValue(req, PARAM_TM_TEST_CASE_DESCRIPTION, 250));
            testMessage.setTestCaseAssertResult(readValue(req, PARAM_TM_TEST_CASE_ASSERT_RESULT, 250));
            testMessage.setPrepPatientType(readValue(req, PARAM_TM_PREP_PATIENT_TYPE, 250));
            testMessage.setPrepTransformsQuick(readValue(req, PARAM_TM_PREP_TRANSFORMS_QUICK));
            testMessage.setPrepTransformsCustom(readValue(req, PARAM_TM_PREP_TRANSFORMS_CUSTOM));
            testMessage.setPrepTransformsAddition(readValue(req, PARAM_TM_PREP_TRANSFORMS_ADDITION));
            testMessage.setPrepTransformsCauseIssue(readValue(req, PARAM_TM_PREP_TRANSFORMS_CAUSE_ISSUE));
            testMessage.setPrepCauseIssueNames(readValue(req, PARAM_TM_PREP_CAUSE_ISSUE_NAMES));
            testMessage.setPrepHasIssue(readValue(req, PARAM_TM_PREP_HAS_ISSUE, 1));
            testMessage.setPrepMajorChagnesMade(readValue(req, PARAM_TM_PREP_MAJOR_CHANGES_MADE, 1));
            testMessage.setPrepNotExpectedToConform(readValue(req, PARAM_TM_PREP_NOT_EXPECTED_TO_CONFORM, 1));
            testMessage.setPrepMessageAcceptStatusDebug(readValue(req, PARAM_TM_PREP_MESSAGE_ACCEPT_STATUS_DEBUG, 250));
            testMessage.setPrepScenarioName(readValue(req, PARAM_TM_PREP_SCENARIO_NAME, 250));
            testMessage.setPrepMessageDerivedFrom(readValue(req, PARAM_TM_PREP_MESSAGE_DERIVED_FROM));
            testMessage.setPrepMessageOriginal(readValue(req, PARAM_TM_PREP_MESSAGE_ORIGINAL));
            testMessage.setPrepMessageActual(readValue(req, PARAM_TM_PREP_MESSAGE_ACTUAL));
            testMessage.setResultMessageActual(readValue(req, PARAM_TM_RESULT_MESSAGE_ACTUAL));
            testMessage.setResultStatus(readValue(req, PARAM_TM_RESULT_STATUS, 250));
            testMessage.setResultAccepted(readValueBoolean(req, PARAM_TM_RESULT_ACCEPTED));
            testMessage.setResultExceptionText(readValue(req, PARAM_TM_RESULT_EXECEPTION_TEXT));
            testMessage.setResultAcceptedMessage(readValue(req, PARAM_TM_RESULT_ACCEPTED_MESSAGE, 250));
            testMessage.setResultResponseType(readValue(req, PARAM_TM_RESULT_RESPONSE_TYPE, 250));
            testMessage.setResultAckType(readValue(req, PARAM_TM_RESULT_ACK_TYPE, 250));
            testMessage.setForecastTestPanelCaseId(readValueInt(req, PARAM_TM_FORECAST_TEST_PANEL_CASE_ID));
            testMessage.setForecastTestPanelId(readValueInt(req, PARAM_TM_FORECAST_TEST_PANEL_ID));

            {
              Transaction transaction = dataSession.beginTransaction();
              dataSession.save(testMessage);
              transaction.commit();
            }

            {
              int position = 1;
              String fieldName = readValue(req, PARAM_C_FIELD_NAME + position, 250);
              while (!fieldName.equals("")) {
                ComparisonField comparisonField = null;
                {
                  String priorityLabel = readValue(req, PARAM_C_PRIORITY_LABEL + position, 250);
                  if (!priorityLabel.equals(""))
                  {
                    Query query = dataSession.createQuery("from ComparisonField where fieldName = ? and priorityLabel = ?");
                    query.setParameter(0, fieldName);
                    query.setParameter(1, priorityLabel);
                    List<ComparisonField> comparisonFieldList = query.list();
                    if (comparisonFieldList.size() > 0) {
                      comparisonField = comparisonFieldList.get(0);
                      String fieldLabel = readValue(req, PARAM_C_FIELD_LABEL + position, 250);
                      if (!fieldLabel.equals(comparisonField.getFieldLabel())) {
                        comparisonField.setFieldLabel(fieldLabel);
                        Transaction transaction = dataSession.beginTransaction();
                        dataSession.update(comparisonField);
                        transaction.commit();
                      }
                    } else {
                      comparisonField = new ComparisonField();
                      comparisonField.setFieldName(fieldName);
                      comparisonField.setPriorityLabel(priorityLabel);
                      Transaction transaction = dataSession.beginTransaction();
                      dataSession.save(comparisonField);
                      transaction.commit();
                    }
                  }
                }
                
                Comparison comparison = new Comparison();
                comparison.setComparisonField(comparisonField);
                comparison.setTestMessage(testMessage);
                comparison.setValueOriginal(readValue(req, PARAM_C_VALUE_ORIGINAL + position, 250));
                comparison.setValueCompare(readValue(req, PARAM_C_VALUE_COMPARE + position, 250));
                comparison.setComparisonStatus(readValue(req, PARAM_C_COMPARISON_STATUS + position, 250));
                Transaction transaction = dataSession.beginTransaction();
                dataSession.save(comparison);
                transaction.commit();
                position++;
                fieldName = readValue(req, PARAM_C_FIELD_NAME + position, 250);
              }
            }
          }
        }
      } else if (!organizationName.equals("")) {
        TestParticipant testParticipant = null;
        {
          Query query = dataSession.createQuery("from TestParticipant where organizationName = ?");
          query.setParameter(0, organizationName);
          List<TestParticipant> testParticipantList = query.list();
          if (testParticipantList.size() > 0) {
            testParticipant = testParticipantList.get(0);
          }
        }
        if (testParticipant == null) {
          testParticipant = new TestParticipant();
          testParticipant.setOrganizationName(organizationName);
        }
        testParticipant.setConnectionLabel(readValue(req, PARAM_TPAR_CONNECTION_LABEL, 250));
        testParticipant.setMapRow(readValueInt(req, PARAM_TPAR_MAP_ROW));
        testParticipant.setMapCol(readValueInt(req, PARAM_TPAR_MAP_COL));
        testParticipant.setPlatformLabel(readValue(req, PARAM_TPAR_PLATFORM_LABEL, 250));
        testParticipant.setVendorLabel(readValue(req, PARAM_TPAR_VENDOR_LABEL, 250));
        testParticipant.setInternalComments(readValue(req, PARAM_TPAR_INTERNAL_COMMENTS));
        testParticipant.setPhase1Participation(readValue(req, PARAM_TPAR_PHASE1_PARTICIPATION, 250));
        testParticipant.setPhase1Status(readValue(req, PARAM_TPAR_PHASE1_STATUS, 250));
        testParticipant.setPhase1Comments(readValue(req, PARAM_TPAR_PHASE1_COMMENTS));
        testParticipant.setPhase2Participation(readValue(req, PARAM_TPAR_PHASE2_PARTICIPATION, 250));
        testParticipant.setPhase2Status(readValue(req, PARAM_TPAR_PHASE2_STATUS, 250));
        testParticipant.setPhase2Comments(readValue(req, PARAM_TPAR_PHASE2_COMMENTS, 250));
        testParticipant.setIhsStatus(readValue(req, PARAM_TPAR_IHS_STATUS, 250));
        testParticipant.setGuideStatus(readValue(req, PARAM_TPAR_GUIDE_STATUS, 250));
        testParticipant.setGuideName(readValue(req, PARAM_TPAR_GUIDE_NAME, 250));
        testParticipant.setConnectStatus(readValue(req, PARAM_TPAR_CONNECT_STATUS, 250));
        testParticipant.setGeneralComments(readValue(req, PARAM_TPAR_GENERAL_COMMENTS));
        testParticipant.setTransportType(readValue(req, PARAM_TPAR_TRANSPORT_TYPE, 250));
        testParticipant.setQuerySupport(readValue(req, PARAM_TPAR_QUERY_SUPPORT, 250));
        testParticipant.setNistStatus(readValue(req, PARAM_TPAR_NIST_STATUS, 250));
        testParticipant.setAccessPasscode(readValue(req, PARAM_TPAR_ACCESS_PASSCODE, 250));
        Transaction transaction = dataSession.beginTransaction();
        dataSession.saveOrUpdate(testParticipant);
        transaction.commit();
      }

      out.println("ok");
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
    finally
    {
      dataSession.close();
    }
    out.close();
  }

  private boolean readValueBoolean(HttpServletRequest req, String field) {
    String value = req.getParameter(field);
    if (value == null) {
      return false;
    }
    return value.equals(VALUE_YES);
  }

  private int readValueInt(HttpServletRequest req, String field) {
    String value = req.getParameter(field);
    if (value == null) {
      return 0;
    }
    return Integer.parseInt(value);
  }

  private float readValueFloat(HttpServletRequest req, String field) {
    String value = req.getParameter(field);
    if (value == null) {
      return 0;
    }
    return Float.parseFloat(value);
  }

  private String readValue(HttpServletRequest req, String field) {
    String value = req.getParameter(field);
    if (value == null) {
      return "";
    }
    return value;
  }

  private String readValue(HttpServletRequest req, String field, int length) {
    String value = req.getParameter(field);
    if (value == null) {
      return "";
    }
    if (value.length() > length) {
      return value.substring(0, length);
    }
    return value;
  }

  private Date readValueDate(HttpServletRequest req, String field) {
    String dateValue = req.getParameter(field);
    Date date = null;
    if (dateValue != null && !dateValue.equals("")) {
      try {
        date = VALUE_DATE_FORMAT.parse(dateValue);
      } catch (ParseException pe) {
        pe.printStackTrace();
      }
    }
    return date;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    createHeader(webSession);
    out.println("<form method=\"POST\" action=\"record\">");

    out.println("<div class=\"leftColumn\">");
    out.println("<table width=\"100%\">");
    out.println("  <caption>Test Conducted</caption>");
    for (String field : PARAMS_TC) {
      out.println("  <tr>");
      out.println("    <td>" + field.substring(3) + "</td>");
      out.println("    <td><input type=\"text\" name=\"" + field + "\"/></td>");
      out.println("  </tr>");
    }
    out.println("</table>");
    out.println("<br/>");
    out.println("<table width=\"100%\">");
    out.println("  <caption>Test Section</caption>");
    for (String field : PARAMS_TS) {
      out.println("  <tr>");
      out.println("    <td>" + field.substring(3) + "</td>");
      out.println("    <td><input type=\"text\" name=\"" + field + "\"/></td>");
      out.println("  </tr>");
    }
    out.println("</table>");
    out.println("</div>");

    out.println("<div class=\"centerColumn\">");
    out.println("<table width=\"100%\">");
    out.println("  <caption>Test Message</caption>");
    for (String field : PARAMS_TM) {
      out.println("  <tr>");
      out.println("    <td>" + field.substring(3) + "</td>");
      out.println("    <td><input type=\"text\" name=\"" + field + "\"/></td>");
      out.println("  </tr>");
    }
    out.println("</table>");
    out.println("</div>");

    out.println("<div class=\"rightColumn\">");
    out.println("<table width=\"100%\">");
    out.println("  <caption>Test Profile</caption>");
    for (String field : PARAMS_TP) {
      out.println("  <tr>");
      out.println("    <td>" + field.substring(3) + "</td>");
      out.println("    <td><input type=\"text\" name=\"" + field + "\"/></td>");
      out.println("  </tr>");
    }
    out.println("</table>");
    out.println("<input type=\"submit\" name=\"action\" value=\"Submit\"/>");
    out.println("</div>");

    out.println("</form>");
    createFooter(webSession);
  }

}
