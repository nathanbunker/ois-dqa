package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBox;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonRow;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.logic.PentagonReportLogic;
import org.openimmunizationsoftware.dqa.tr.model.Assertion;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

import com.mchange.v1.util.ArrayUtils;

public class PentagonContentServlet extends PentagonServlet
{

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
      out.println("<p>Not authorized</p>");
      out.close();
      return;
    }
    TestConducted testConducted = null;
    int testConnectedId = 0;
    String testConnectedIdString = req.getParameter(PARAM_TEST_CONDUCTED_ID);
    if (testConnectedIdString != null)
    {
      testConnectedId = Integer.parseInt(testConnectedIdString);
    }
    if (testConnectedId != 0)
    {
      testConducted = (TestConducted) dataSession.get(TestConducted.class, testConnectedId);
    }

    String boxName = req.getParameter(PARAM_BOX_NAME);
    if (boxName != null)
    {
      if (boxName.equals(BOX_NAME_REPORT_SELECT))
      {
        out.println("<h2 class=\"pentagon\">All Reports for " + testConducted.getConnectionLabel() + "</h2>");
        {
          List<TestConducted> testConductedList = getTestConductedList(dataSession, testConducted);
          printReportsRun(dataSession, out, testConductedList);
        }
        out.println("<h3 class=\"pentagon\">Other IIS Reports</h3>");
        {
          List<TestConducted> testConductedList = getOtherIISTestReports(dataSession);
          printReportsRun(dataSession, out, testConductedList);
        }
      } else if (boxName.equals(BOX_NAME_TEST_SECTIONS))
      {
        String selector = req.getParameter(PARAM_SELECTOR);
        if (selector == null || selector.equals(""))
        {
          out.println("<p class=\"pentagon\">All test messages are run in sections, each with their own labels. "
              + "These sections organize all the messages that are sent to the IIS. The results of these sections "
              + "are interpreted by the entire report. This allows a view into the details of all the test messages "
              + "there were sent. Most of these appear on the report in their sections. </p>");
          List<TestSection> testSectionList;
          {
            Query query = dataSession.createQuery("from TestSection where testConducted = ? order by testSectionType");
            query.setParameter(0, testConducted);
            testSectionList = query.list();
          }
          out.println("<table class=\"pentagon\">");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Test Areas</th>");
          out.println("    <th class=\"pentagon\">Updates</th>");
          out.println("    <th class=\"pentagon\">Queries</th>");
          out.println("  </tr>");
          for (TestSection testSection : testSectionList)
          {
            if (testSection.getTestSectionType().equals(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE))
            {
              continue;
            }
            if (testSection.getTestSectionType().equals(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015))
            {
              continue;
            }
            if (testSection.getTestSectionType().equals(RecordServletInterface.VALUE_TEST_SECTION_TYPE_PERFORMANCE))
            {
              continue;
            }
            out.println("  <tr class=\"pentagon\">");
            out.println("    <td class=\"pentagon\"><a href=\"javascript: void;\"  onClick=\"loadBoxContents('" + BOX_NAME_TEST_SECTIONS + "', '"
                + testSection.getTestSectionId() + "')\">" + testSection.getTestSectionType() + "</a></td>");
            out.println("    <td class=\"pentagon\">" + testSection.getCountLevel1() + "</td>");
            out.println("    <td class=\"pentagon\">" + testSection.getCountLevel2() + "</td>");
            out.println("  </tr>");
          }
          out.println("</table>");
        } else
        {
          int testSectionId = Integer.parseInt(selector);
          TestSection testSection = (TestSection) dataSession.get(TestSection.class, testSectionId);
          out.println("<h2 class=\"pentagon\">" + testSection.getTestSectionType() + " Test Cases</h2>");
          {
            out.println("<h3 class=\"pentagon\">Updates</h2>");
            Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'update' order by testCaseCategory");
            query.setParameter(0, testSection);
            List<TestMessage> testMessageList = query.list();
            PentagonBox.printTestMessageListPass(out, testMessageList);
          }
          {
            out.println("<h3 class=\"pentagon\">Queries</h2>");
            Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query' order by testCaseCategory");
            query.setParameter(0, testSection);
            List<TestMessage> testMessageList = query.list();
            PentagonBox.printTestMessageListPass(out, testMessageList);
          }
        }
      } else
      {
        PentagonReport pentagonReport = PentagonReportLogic.createOrReturnPentagonReport(testConducted, dataSession);
        List<PentagonRow> pentagonRowList = new ArrayList<PentagonRow>();
        pentagonRowList.add(PentagonRow.createConfidenceRow(pentagonReport));
        pentagonRowList.add(PentagonRow.createUpdateFunctionalityRow(pentagonReport));
        pentagonRowList.add(PentagonRow.createUpdateConformanceRow(pentagonReport));
        pentagonRowList.add(PentagonRow.createQueryFunctionalityRow(pentagonReport));
        pentagonRowList.add(PentagonRow.createQueryConformanceRow(pentagonReport));
        

        PentagonRow pentagonRow = null;
        PentagonBox pentagonBox = null;
        for (PentagonRow pr : pentagonRowList)
        {
          pentagonBox = pr.getPentagonBox(boxName);
          if (pentagonBox != null)
          {
            pentagonRow = pr;
            break;
          }
        }
        if (pentagonBox != null)
        {
          try
          {

            {
              out.println("<div id=\"boxDetailsOverview\">");
              out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px; \">");
              printScoreChart(out, pentagonBox.getScore());
              out.println("</span>");
              pentagonBox.printDescription(out, dataSession, testConducted, webSession, userSession);
              pentagonBox.printContents(out, dataSession, testConducted, webSession, userSession);
              out.println("</div>");
            }
            {
              out.println("<div id=\"boxDetailsCalculation\" style=\"display:none; \">");
              pentagonBox.printScoreExplanation(out, dataSession, testConducted, webSession, userSession);
              out.println("</div>");
            }
            {
              out.println("<div id=\"boxDetailsImportance\" style=\"display:none; \">");
              out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px; \">");
              printScoreChart(out, pentagonBox.getWeight());
              out.println("</span>");
              if (pentagonBox.getWeight() <= 5)
              {
                out.println("<p class=\"pentagon\">The " + pentagonBox.getTitle() + " Score has very little impact on the " + pentagonRow.getLabel()
                    + " Score. This suggests that improvement in this area should take lowest priority. </p>");
              } else if (pentagonBox.getWeight() <= 10)
              {
                out.println("<p class=\"pentagon\">The " + pentagonBox.getTitle() + " Score has some impact on the " + pentagonRow.getLabel()
                    + " Score. This suggests that improvement in this area should take low priority. </p>");
              } else if (pentagonBox.getWeight() <= 20)
              {
                out.println(
                    "<p class=\"pentagon\">The " + pentagonBox.getTitle() + " Score does impact the " + pentagonRow.getLabel() + " Score. </p>");
              } else if (pentagonBox.getWeight() <= 50)
              {
                out.println("<p class=\"pentagon\">The " + pentagonBox.getTitle() + " Score greatly impacts the " + pentagonRow.getLabel()
                    + " Score. Improvement in this area should be given a high priority. </p>");
              } else
              {
                out.println("<p class=\"pentagon\">The " + pentagonBox.getTitle() + " Score is the largest factor in the " + pentagonRow.getLabel()
                    + " Score. Improvement in this area should be given the highest priority. </p>");
              }
              out.println("<p class=\"pentagon\">The following lists shows the improvement potential, or score gap, for each section. "
                  + "This lists suggests a starting point for priorities. Items with the highest improvement potential should "
                  + "generally be given more attention and concern over areas with the lowest improvement potential. </p>");
              out.println("<p class=\"pentagon\"><b>Score Gap</b>: The percentage point improvement possible for the score if "
                  + "the section were improved to a score of 100%. </p>");

              List<PriorityWeight> priorityWeightList = new ArrayList();
              for (PentagonBox pb : pentagonRow)
              {
                PriorityWeight priorityWeight = new PriorityWeight();
                priorityWeight.importance = (pb.getWeight() / 100.0) * (100.0 - pb.getScore());
                priorityWeight.pentagonBox = pb;
                priorityWeightList.add(priorityWeight);
              }
              Collections.sort(priorityWeightList, new Comparator<PriorityWeight>() {
                @Override
                public int compare(PriorityWeight o1, PriorityWeight o2)
                {
                  if (o1.importance > o2.importance)
                  {
                    return -1;
                  } else if (o1.importance < o2.importance)
                  {
                    return 1;
                  }
                  return 0;
                }
              });
              ;
              out.println("<p class=\"pentagon\"></p>");
              out.println("<table class=\"pentagon\">");
              out.println("  <tr class=\"pentagon\">");
              out.println("    <th class=\"pentagon\">Score Gap</th>");
              out.println("    <th class=\"pentagon\">Section</th>");
              out.println("    <th class=\"pentagon\">Weight</th>");
              out.println("  </tr>");
              for (PriorityWeight pw : priorityWeightList)
              {
                String styleClass = "pentagonSelected";
                if (pw.pentagonBox.getBoxName().equals(pentagonBox.getBoxName()))
                {
                  styleClass = "pentagonSelected";
                } else
                {
                  styleClass = "pentagon";
                }
                out.println("  <tr class=\"pentagon\">");
                out.println("    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + Math.round(pw.importance) + "%</td>");
                out.println("    <td class=\"" + styleClass + "\">" + pw.pentagonBox.getTitle() + "</td>");
                out.println("    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + pw.pentagonBox.getWeight() + "%</td>");
                out.println("  </tr>");
              }
              out.println("</table>");
              out.println("</div>");
            }
            {
              out.println("<div id=\"boxDetailsHistory\" style=\"display:none; \">");
              List<TestConducted> testConductedList = getTestConductedList(dataSession, testConducted);
              if (testConductedList.size() > 0)
              {
                out.println("<p class=\"pentagon\">Over time this score can change. This section shows the score over time for this IIS. </p>");
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm aa zz");
                out.println("<table class=\"pentagon\">");
                out.println("  <tr class=\"pentagon\">");
                out.println("    <th class=\"pentagon\">Score</th>");
                out.println("    <th class=\"pentagon\">Date &amp; Time</th>");
                out.println("  </tr>");
                for (TestConducted testConductedDisplay : testConductedList)
                {
                  PentagonReport pentagonReportDisplay = PentagonReportLogic.createOrReturnPentagonReport(testConductedDisplay, dataSession);
                  String link = "pentagon?" + PARAM_TEST_CONDUCTED_ID + "=" + testConductedDisplay.getTestConductedId();
                  out.println("  <tr class=\"pentagon\">");
                  out.println("    <td class=\"pentagon\">" + pentagonReportDisplay.getScore(pentagonBox.getBoxName()) + "</td>");
                  out.println(
                      "    <td class=\"pentagon\"><a href=\"" + link + "\">" + sdf.format(testConductedDisplay.getTestStartedTime()) + "</a></td>");
                  out.println("  </tr>");
                }
                out.println("</table>");
              }
              out.println("</div>");
            }
            {
              out.println("<div id=\"boxDetailsComparison\" style=\"display:none; \">");
              out.println("<p class=\"pentagon\">How does this score compare with the scores from other IIS? </p>");
              List<TestConducted> testConductedList = getOtherIISTestReports(dataSession);
              out.println("<table class=\"pentagon\">");
              out.println("  <tr class=\"pentagon\">");
              out.println("    <th class=\"pentagon\">Score</th>");
              out.println("    <th class=\"pentagon\">IIS</th>");
              out.println("  </tr>");

              for (TestConducted testConductedDisplay : testConductedList)
              {
                out.println("  <tr class=\"pentagon\">");
                PentagonReport pentagonReportDisplay = PentagonReportLogic.createOrReturnPentagonReport(testConductedDisplay, dataSession);
                out.println("    <td class=\"pentagon\">" + pentagonReportDisplay.getScore(pentagonBox.getBoxName()) + "%</td>");
                {
                  String link = "pentagon?" + PARAM_TEST_CONDUCTED_ID + "=" + testConductedDisplay.getTestConductedId() + "&" + PARAM_CONNECTION_LABEL
                      + "=" + URLEncoder.encode(testConductedDisplay.getConnectionLabel(), "UTF-8");
                  out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + "\">" + testConductedDisplay.getConnectionLabel()
                      + "</a></td>");
                }
                out.println("  </tr>");
              }
              out.println("  </table>");
              out.println("</div>");
            }

          } catch (Exception e)
          {
            out.println("<p><em>Internal Error, unable to load content</em></p>");
            e.printStackTrace();
          }
        }
      }
    } else
    {

      ProfileUsage profileUsage = null;
      {
        String profileUsageIdString = req.getParameter(PARAM_PROFILE_USAGE_ID);
        if (profileUsageIdString != null && !profileUsageIdString.equals(""))
        {
          int profileUsageId = Integer.parseInt(profileUsageIdString);
          profileUsage = (ProfileUsage) dataSession.get(ProfileUsage.class, profileUsageId);
        }
      }

      if (profileUsage == null)
      {
        profileUsage = ProfileUsage.getBaseProfileUsage(dataSession);
      }

      TestMessage testMessage = null;
      int testMessageId = 0;
      String testMessageIdString = req.getParameter(PARAM_TEST_MESSAGE_ID);
      if (testMessageIdString != null)
      {
        testMessageId = Integer.parseInt(testMessageIdString);
      }
      if (testMessageId != 0)
      {
        testMessage = (TestMessage) dataSession.get(TestMessage.class, testMessageId);
      }
      out.println(
          "<a style=\"position: absolute; top: 5px; right: 5px; padding: 2px 5px; border-style: solid; border-width: 1px; \" onclick=\"hideReport('details')\" href=\"javascript: void(); \">Close</a>");

      printTestMessage(dataSession, out, profileUsage, testMessage);
    }
    out.close();
  }

  public List<TestConducted> getOtherIISTestReports(Session dataSession)
  {
    Query query = dataSession.createQuery("from TestConducted where latestTest = 'Y' order by connectionLabel");
    List<TestConducted> testConductedList = query.list();
    return testConductedList;
  }

  public static void printTestMessage(Session dataSession, PrintWriter out, ProfileUsage profileUsage, TestMessage testMessage) throws IOException
  {

    if (testMessage != null)
    {
      if (testMessage.getTestType().equals("prep") || testMessage.getTestType().equals("update"))
      {
        if (testMessage.getTestType().equals("prep"))
        {
          out.println("<h2 class=\"pentagon\">Prep Message</h3>");
          out.println(
              "<p class=\"pentagon\">This message is sent in before testing begins to ensure that a base message can be accepted. It is important that "
                  + "this message is accepted in order for the other tests to proceed, but the results of this particular test are not otherwise used. </p>");
        } else if (testMessage.getTestType().equals("update"))
        {
          out.println("<h2 class=\"pentagon\">Update Test</h3>");
          out.println(
              "<p class=\"pentagon\">This message is sent in before testing begins to ensure that a base message can be accepted. It is important that "
                  + "this message is accepted in order for the other tests to proceed, but the results of this particular test are not otherwise used. </p>");
          out.println("<table class=\"pentagon\">");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Test Id</th>");
          out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseNumber() + "</td>");
          out.println("  </tr>");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Description</th>");
          out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseDescription() + "</td>");
          out.println("  </tr>");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Expected Response</th>");
          out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseAssertResult() + "</td>");
          out.println("  </tr>");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Actual Response</th>");
          out.println("    <td class=\"pentagon\">" + testMessage.getResultAckType() + "</td>");
          out.println("  </tr>");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Test Result Status</th>");
          out.println("    <td class=\"pentagon\">" + testMessage.getResultStatus() + "</td>");
          out.println("  </tr>");
          out.println("</table>");
        }
        out.println("<h2 class=\"pentagon\">Update Message Submitted</h3>");
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageActual(), profileUsage) + "</pre>");
        if (testMessage.isResultAccepted())
        {
          out.println("<h3 class=\"pentagon\">Accepted by IIS</h3>");
        } else
        {
          out.println("<h3 class=\"pentagon\">NOT Accepted by IIS</h3>");
        }
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getResultMessageActual(), profileUsage) + "</pre>");
        printConformance(dataSession, out, testMessage);
        printAdditionalInfo(out, profileUsage, testMessage);

      } else if (testMessage.getTestType().equals("query"))
      {
        out.println("<h2 class=\"pentagon\">" + testMessage.getTestCaseDescription() + "</h3>");
        if (!testMessage.getPrepMessageDerivedFrom().equals(""))
        {
          out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageDerivedFrom(), profileUsage) + "</pre>");
          if (testMessage.getResultStoreStatus().equals("a-r") || testMessage.getResultStoreStatus().equals("a-nr"))
          {
            out.println("<h3 class=\"pentagon\">Update Accepted</h3>");
          } else if (testMessage.getResultStoreStatus().equals("na-r") || testMessage.getResultStoreStatus().equals("na-nr"))
          {
            out.println("<h3 class=\"pentagon\">Update Not Accepted</h3>");
          } else
          {
            out.println("<h3 class=\"pentagon\">Update</h3>");
          }
          out.println(
              "<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageOriginalResponse(), profileUsage) + "</pre>");
          out.println("<h3 class=\"pentagon\">Query</h3>");
        }
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageActual(), profileUsage) + "</pre>");
        out.println("<h3 class=\"pentagon\">" + testMessage.getResultQueryType() + " Returned</h3>");
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getResultMessageActual(), profileUsage) + "</pre>");
        out.println("<table class=\"pentagon\">");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Expected Response</th>");
        out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseAssertResult() + "</td>");
        out.println("  </tr>");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Actual Response</th>");
        out.println("    <td class=\"pentagon\">" + testMessage.getResultQueryType() + "</td>");
        out.println("  </tr>");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Store Status</th>");
        out.println("    <td class=\"pentagon\">" + testMessage.getResultStoreStatusForDisplay() + "</td>");
        out.println("  </tr>");
        out.println("</table>");

        printConformance(dataSession, out, testMessage);
        printAdditionalInfo(out, profileUsage, testMessage);

      }
    }
  }

  public List<TestConducted> getTestConductedList(Session dataSession, TestConducted testConducted)
  {
    Query query = dataSession.createQuery("from TestConducted where connectionLabel = ? and completeTest = 'Y' order by testStartedTime Desc");
    query.setParameter(0, testConducted.getConnectionLabel());
    List<TestConducted> testConductedList = query.list();
    return testConductedList;
  }

  public void printReportsRun(Session dataSession, PrintWriter out, List<TestConducted> testConductedList)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm aa zz");
    out.println("<table class=\"pentagon\">");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Score</th>");
    out.println("    <th class=\"pentagon\">Details</th>");
    out.println("  </tr>");

    for (TestConducted testConductedDisplay : testConductedList)
    {
      out.println("  <tr class=\"pentagon\">");
      PentagonReport pentagonReportDisplay = PentagonReportLogic.createOrReturnPentagonReport(testConductedDisplay, dataSession);
      out.println("    <td class=\"pentagon\">");
      out.println("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100\" height=\"100\">");
      {
        int offsetY = 0;
        int offsetX = 0;
        setupAndPrintSmallPentagon(out, pentagonReportDisplay, offsetY, offsetX);
      }
      out.println("</svg>");
      out.println("    </td>");

      out.println("    <td class=\"pentagon\">");
      out.println("     <h4 class=\"pentagon\">" + testConductedDisplay.getConnectionLabel() + "</h4>");
      out.println("     <p class=\"pentagon\">" + sdf.format(testConductedDisplay.getTestStartedTime()));
      try
      {
        String link = "pentagon?" + PARAM_TEST_CONDUCTED_ID + "=" + testConductedDisplay.getTestConductedId() + "&" + PARAM_CONNECTION_LABEL + "="
            + URLEncoder.encode(testConductedDisplay.getConnectionLabel(), "UTF-8");
        out.println("     <br/><a class=\"pentagon\" href=\"" + link + "\">Select</a>");
      } catch (UnsupportedEncodingException uie)
      {
        out.println("     <br/>Problem! " + uie.getMessage() + "</p>");
      }
      out.println("      </p>");
      out.println("    </td>");
      out.println("  </tr>");
    }
    out.println("  </table>");
  }

  public static void printAdditionalInfo(PrintWriter out, ProfileUsage profileUsage, TestMessage testMessage) throws IOException
  {
    out.println("<h3 class=\"pentagon\">How Test Message Was Prepared</h3>");
    if (!testMessage.getPrepMessageOriginal().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Starting Message</h4>");
      out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageOriginal(), profileUsage) + "</pre>");
    }
    if (!testMessage.getPrepPatientType().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Patient Type</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getPrepPatientType() + "</pre>");
    }
    if (!testMessage.getPrepTransformsQuick().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Quick Transforms</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getPrepTransformsQuick() + "</pre>");
    }
    if (!testMessage.getPrepTransformsCustom().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Custom Transforms</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getPrepTransformsCustom() + "</pre>");
    }
    if (!testMessage.getPrepTransformsCustom().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Cause Issues</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getPrepTransformsCauseIssue() + "</pre>");
    }
    if (!testMessage.getPrepTransformsAddition().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Additional Transforms</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getPrepTransformsAddition() + "</pre>");
    }
    if (!testMessage.getPrepCauseIssueNames().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Cause Issue Names</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getPrepCauseIssueNames() + "</pre>");
    }
    out.println("<table class=\"pentagon\">");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Test Case Id</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseCategory() + "</td>");
    out.println("  </tr>");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Scenario Name</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getPrepScenarioName() + "</td>");
    out.println("  </tr>");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Has Issue</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getPrepHasIssue() + "</td>");
    out.println("  </tr>");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Major Changes Made</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getPrepMajorChagnesMade() + "</td>");
    out.println("  </tr>");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Not Expected to Conform</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getPrepNotExpectedToConform() + "</td>");
    out.println("  </tr>");
    out.println("</table>");
    if (!testMessage.getPrepCauseIssueNames().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Message Accept Debug</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getPrepMessageAcceptStatusDebug() + "</pre>");
    }

    out.println("<h3 class=\"pentagon\">Results of Testing</h3>");

    if (!testMessage.getResultExceptionText().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Exception Encountered</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getResultExceptionText() + "</pre>");
    }
    out.println("<table class=\"pentagon\">");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Query Type</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getResultQueryType() + "</td>");
    out.println("  </tr>");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Store Status</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getResultStoreStatus() + "</td>");
    out.println("  </tr>");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Forecast</th>");
    out.println("    <td class=\"pentagon\">" + testMessage.getResultForecastStatus() + "</td>");
    out.println("  </tr>");
    out.println("</table>");

  }

  public static void printConformance(Session dataSession, PrintWriter out, TestMessage testMessage)
  {
    if (testMessage.getResultAckConformance() != null)
    {
      if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
      {
        out.println("<h3 class=\"pentagon\">Response Not Validated</h3>");
        out.println(
            "<p class=\"pentagon\">The response was not evaluated by NIST, this could be because it wasn't selected as part of this test run, "
                + "or because a technical issue prevented the test from connecting to NIST to obtain the validation results. </p>");
      } else
      {
        if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
        {
          out.println("<h3 class=\"pentagon\">Response Meets NIST Standards</h3>");
          out.println(
              "<p>The response was validated against the NIST validator for immunization messages and not error level issues were found. </p>");
        } else if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_ERROR))
        {
          out.println("<h3 class=\"pentagon\">Response Does NOT Meet NIST Standards</h3>");
          out.println(
              "<p class=\"pentagon\">The response was validated against the NIST validator for immunization messages and problems were found. "
                  + "Please review carefully. Badly formatted acknowledgement messages can cause interoperability issues.  </p>");
        }
        out.println("<table  class=\"pentagon\" width=\"100%\">");
        out.println("  <caption class=\"pentagon\">NIST 2015 Validation of Response</caption>");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Location</th>");
        out.println("    <th class=\"pentagon\">Result</th>");
        out.println("    <th class=\"pentagon\">Type</th>");
        out.println("    <th class=\"pentagon\">Description</th>");
        out.println("  </tr>");
        Query query = dataSession.createQuery("from Assertion where testMessage = ? order by location_path");
        query.setParameter(0, testMessage);
        List<Assertion> assertionList = query.list();
        for (Assertion assertion : assertionList)
        {
          String classString = " class=\"pentagonPass\"";
          if (assertion.getAssertionResult().equalsIgnoreCase("ERROR"))
          {
            classString = " class=\"pentagonFail\"";
          }
          out.println("  <tr>");
          out.println("    <td" + classString + ">" + assertion.getLocationPath() + "</td>");
          out.println("    <td" + classString + ">" + assertion.getAssertionResult() + "</td>");
          out.println("    <td" + classString + ">" + assertion.getAssertionField().getAssertionType() + "</td>");
          out.println("    <td" + classString + ">" + assertion.getAssertionField().getAssertionDescription() + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
      }
    }
  }

  private class PriorityWeight
  {
    double importance = 0;
    PentagonBox pentagonBox = null;
  }

}
