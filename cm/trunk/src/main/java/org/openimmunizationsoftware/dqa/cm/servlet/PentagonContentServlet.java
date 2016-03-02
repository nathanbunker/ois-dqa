package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.immunizationsoftware.dqa.transform.TestCaseMessage;
import org.immunizationsoftware.dqa.transform.Transformer;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBoxHelper;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBoxHelper.Show;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonRowHelper;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.logic.PentagonReportLogic;
import org.openimmunizationsoftware.dqa.tr.model.Assertion;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

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
      if (boxName.equals(BOX_NAME_HOW_TO_READ))
      {
        out.println("<p class=\"pentagon\">Need to explain the following concepts here:</p>");
        out.println("<ul class=\"pentagon\">");
        out.println("  <li class=\"pentagon\">Test Message</li>");
        out.println("  <li class=\"pentagon\">Report Box/Section</li>");
        out.println("  <li class=\"pentagon\">Report Row/Area</li>");
        out.println("</ul>");
      } else if (boxName.equals(BOX_NAME_REPORT_SELECT))
      {
        out.println("<div id=\"boxDetailsCalculation\">");
        out.println("<p class=\"pentagon\">Todo</p>");
        out.println("</div>");
        {
          out.println("<div id=\"boxDetailsHistory\">");
          List<TestConducted> testConductedList = getTestConductedList(dataSession, testConducted);
          printReportTable(dataSession, out, testConductedList, userSession);
          out.println("</div>");
        }
        {
          out.println("<div id=\"boxDetailsComparison\">");
          List<TestConducted> testConductedList = getOtherIISTestReports(dataSession);
          printReportsRun(dataSession, out, testConductedList, userSession);
          out.println("</div>");
        }

        out.println("<div id=\"boxDetailsOverview\">");
        out.println("</div>");

        out.println("<div id=\"boxDetailsDetails\">");
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
            out.println("    <td class=\"pentagon\"><a href=\"javascript: void;\" onClick=\"loadBoxContents('" + testSection.getTestSectionType()
                + "', '" + BOX_NAME_REPORT_SELECT + "', '" + testSection.getTestSectionId() + "', true)\">" + testSection.getTestSectionType()
                + "</a></td>");
            if (testSection.getCountLevel1() < 0)
            {
              out.println("    <td class=\"pentagon\">Not Run</td>");
            } else
            {
              out.println("    <td class=\"pentagon\">" + testSection.getCountLevel1() + "</td>");
            }
            if (testSection.getCountLevel2() < 0)
            {
              out.println("    <td class=\"pentagon\">Not Run</td>");
            } else
            {
              out.println("    <td class=\"pentagon\">" + testSection.getCountLevel2() + "</td>");
            }
            out.println("  </tr>");
          }
          out.println("</table>");
          out.println("<br/>");
          out.println("<h4 class=\"pentagon\">Download HL7</h4>");
          out.println("<p class=\"pentagon\">The HL7 messages sent and received can be downloaded and used for other testing purposes. </p>");
          showDownloads(out, testConducted, testSectionList);
        } else
        {
          int testSectionId = Integer.parseInt(selector);
          TestSection testSection = (TestSection) dataSession.get(TestSection.class, testSectionId);
          {
            out.println("<h3 class=\"pentagon\">Updates</h2>");
            Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'update' order by testCaseCategory");
            query.setParameter(0, testSection);
            List<TestMessage> testMessageList = query.list();
            PentagonBoxHelper.printTestMessageListPass(out, testMessageList, Show.DESCRIPTION, false, userSession);
          }
          {
            out.println("<h3 class=\"pentagon\">Queries</h2>");
            Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query' order by testCaseCategory");
            query.setParameter(0, testSection);
            List<TestMessage> testMessageList = query.list();
            PentagonBoxHelper.printTestMessageListPass(out, testMessageList, Show.DESCRIPTION, false, userSession);
          }
        }
        out.println("</div>");

        {
          out.println("<div id=\"boxDetailsImprove\">");
          PentagonReport pentagonReport = PentagonReportLogic.createOrReturnPentagonReport(testConducted, dataSession);
          List<PentagonRowHelper> pentagonRowList = new ArrayList<PentagonRowHelper>();
          pentagonRowList.add(PentagonRowHelper.createConfidenceRow(pentagonReport));
          pentagonRowList.add(PentagonRowHelper.createUpdateFunctionalityRow(pentagonReport));
          pentagonRowList.add(PentagonRowHelper.createUpdateConformanceRow(pentagonReport));
          pentagonRowList.add(PentagonRowHelper.createQueryFunctionalityRow(pentagonReport));
          pentagonRowList.add(PentagonRowHelper.createQueryConformanceRow(pentagonReport));

          List<PentagonBoxHelper> pbSorted = new ArrayList<PentagonBoxHelper>();
          for (PentagonRowHelper pentagonRowHelper : pentagonRowList)
          {
            for (PentagonBoxHelper pentagonBoxHelper : pentagonRowHelper)
            {
              if (pentagonBoxHelper.getPentagonBox().getPriorityOverall() > 0)
              {
                pbSorted.add(pentagonBoxHelper);
              }
            }
          }
          Collections.sort(pbSorted, new OverallComparator());
          out.println("<p class=\"pentagon\">This report has been designed for analysis purposes, but can be used to "
              + "help understand improvemments that could be made to impove both the IIS interface. "
              + "In this way this report can be used now to drive improvements in the IIS. "
              + "Below is a prioritization of issues based on the weight of each measurement in the report. "
              + "This prioritization is given as only a suggestion and a starting point for improving the IIS HL7 interface. </p>");

          for (PentagonBoxHelper pb : pbSorted)
          {
            if (pb.getPentagonBox().getPriorityRow() == 1)
            {
              out.println("<h3 class=\"pentagon\">#" + pb.getPentagonBox().getPriorityOverall() + " " + pb.getPentagonRowHelper().getLabel() + ": "
                  + pb.getTitle() + "</h3>");
              out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px; \">");
              printScoreChart(out, pb.getScore());
              out.println("</span>");
              if (pb.getScore() < 0)
              {
                out.println("<p class=\"pentagon\"><b>Section Score:</b> Not Evaluated</p>");
              } else
              {
                out.println("<p class=\"pentagon\"><b>Section Score:</b> " + pb.getScore() + "%</p>");
              }
              pb.printImprove(out, dataSession, pentagonReport, webSession, userSession);
              String improvementLevel = getImprovementLevelText(pb.getPentagonBox().getReportScoreGap());
              out.println("<p class=\"pentagon\"><b>Score Gap:</b> " + +pb.getPentagonBox().getReportScoreGap() + "%</p>");
              out.println("<p class=\"pentagon\">" + improvementLevel + " The score gap indicates how much improvement could be made to "
                  + "the row score if the Section Score were improved to 100%. </p>");
              out.println("<p class=\"pentagon\"><a class=\"pentagonMenuLink\" href=\"javascript: void(0);\" onClick=\"loadBoxContents('"
                  + pb.getPentagonRowHelper().getLabel() + ": " + pb.getTitle() + "', '" + pb.getBoxName()
                  + "', '', true)\">See Section Details</a></p>");
            }
          }

          out.println("<h3 class=\"pentagon\">Complete Priority Listing</h3>");
          out.println("<table class=\"pentagon\">");
          out.println("  <tr class=\"pentagon\">");
          out.println("    <th class=\"pentagon\">Priority</th>");
          out.println("    <th class=\"pentagon\">Score Gap</th>");
          out.println("    <th class=\"pentagon\">Section</th>");
          out.println("    <th class=\"pentagon\">Weight</th>");
          out.println("  </tr>");
          for (PentagonBoxHelper pb : pbSorted)
          {
            String styleClass = "pentagon";
            out.println("  <tr class=\"pentagon\">");
            out.println("    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + pb.getPentagonBox().getPriorityOverall() + "</td>");
            out.println("    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + pb.getPentagonBox().getReportScoreGap() + "%</td>");
            out.println("    <td class=\"" + styleClass + "\"><a class=\"pentagonMenuLink\" href=\"javascript: void(0);\" onClick=\"loadBoxContents('"
                + pb.getPentagonRowHelper().getLabel() + ": " + pb.getTitle() + "', '" + pb.getBoxName() + "', '', true)\">"
                + pb.getPentagonRowHelper().getLabel() + ": " + pb.getTitle() + "</a></td>");
            out.println("    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + pb.getPentagonBox().getReportWeight() + "%</td>");
            out.println("  </tr>");
          }
          out.println("</div>");
        }

      } else
      {
        PentagonReport pentagonReport = PentagonReportLogic.createOrReturnPentagonReport(testConducted, dataSession);
        List<PentagonRowHelper> pentagonRowList = new ArrayList<PentagonRowHelper>();
        pentagonRowList.add(PentagonRowHelper.createConfidenceRow(pentagonReport));
        pentagonRowList.add(PentagonRowHelper.createUpdateFunctionalityRow(pentagonReport));
        pentagonRowList.add(PentagonRowHelper.createUpdateConformanceRow(pentagonReport));
        pentagonRowList.add(PentagonRowHelper.createQueryFunctionalityRow(pentagonReport));
        pentagonRowList.add(PentagonRowHelper.createQueryConformanceRow(pentagonReport));

        PentagonRowHelper pentagonRowHelper = null;
        PentagonBoxHelper pentagonBoxHelper = null;
        for (PentagonRowHelper pr : pentagonRowList)
        {
          pentagonBoxHelper = pr.getPentagonBox(boxName);
          if (pentagonBoxHelper != null)
          {
            pentagonRowHelper = pr;
            break;
          }
        }
        if (pentagonBoxHelper != null)
        {
          try
          {
            {
              out.println("<div id=\"boxDetailsOverview\">");
              out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px; \">");
              printScoreChart(out, pentagonBoxHelper.getScore());
              out.println("</span>");
              pentagonBoxHelper.printOverview(out, dataSession, pentagonReport, webSession, userSession);
              out.println("</div>");
            }
            {
              out.println("<div id=\"boxDetailsDetails\">");
              pentagonBoxHelper.printDetails(out, dataSession, pentagonReport, webSession, userSession);
              out.println("</div>");
            }
            {
              out.println("<div id=\"boxDetailsCalculation\" style=\"display:none; \">");
              pentagonBoxHelper.printCalculation(out, dataSession, pentagonReport, webSession, userSession);
              out.println("</div>");
            }
            {
              out.println("<div id=\"boxDetailsImprove\" style=\"display:none; \">");

              int scoreGap = pentagonBoxHelper.getPentagonBox().getReportScoreGap();
              int position = pentagonBoxHelper.getPentagonBox().getPriorityRow();
              out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px; \">");
              printScoreChart(out, scoreGap);
              out.println("</span>");
              String improvementLevel = getImprovementLevelText(scoreGap);
              if (scoreGap == 0)
              {
                out.println("<p class=\"pentagon\">" + improvementLevel + "No further improvement is possible in this section. </p>");
              } else
              {
                if (position == 1)
                {
                  out.println("<p class=\"pentagon\">" + improvementLevel + "This section has the biggest potential for a improving the "
                      + pentagonRowHelper.getLabel() + " Score. If this section were improved to pass all tests it would raise the entire "
                      + pentagonRowHelper.getLabel() + " Score by " + scoreGap + " percentage points.</p>");
                } else if (position == 2)
                {
                  out.println("<p class=\"pentagon\">" + improvementLevel + "This section has the second biggest potential for a improving the "
                      + pentagonRowHelper.getLabel() + " Score. If this section were improved to pass all tests it would raise the entire "
                      + pentagonRowHelper.getLabel() + " Score by " + scoreGap + " percentage points.</p>");
                } else if (position == pentagonRowHelper.size())
                {
                  out.println("<p class=\"pentagon\">" + improvementLevel + "This section has the lowest potential for a improving the "
                      + pentagonRowHelper.getLabel() + " Score. If this section were improved to pass all tests it would raise the entire "
                      + pentagonRowHelper.getLabel() + " Score by " + scoreGap + " percentage points. "
                      + "It would be best to focus on other areas first. </p>");
                } else
                {
                  out.println("<p class=\"pentagon\">" + improvementLevel + "This section has some potential for a improving the "
                      + pentagonRowHelper.getLabel() + " Score. If this section were improved to pass all tests it would raise the entire "
                      + pentagonRowHelper.getLabel() + " Score by " + scoreGap + " percentage points. </p>");
                }
                pentagonBoxHelper.printImprove(out, dataSession, pentagonReport, webSession, userSession);
              }
              out.println("<p class=\"pentagon\">The following list shows the improvement potential for each section. "
                  + "This lists suggests a starting point for priorities. Items with the highest improvement potential should "
                  + "generally be given more attention and concern over areas with the lowest improvement potential. </p>");
              out.println("<p class=\"pentagon\"><b>Improvement Potential</b>: The percentage point improvement possible for the "
                  + pentagonRowHelper.getLabel().toLowerCase() + " score if " + "this section were improved to pass all tests. </p>");
              out.println("<p class=\"pentagon\"><b>Weight</b>: The total weight this section's score counts towards the overall "
                  + pentagonRowHelper.getLabel().toLowerCase() + " score. " + "This value is fixed by the Interoperability Testing Project. </p>");

              out.println("<table class=\"pentagon\">");
              out.println("  <tr class=\"pentagon\">");
              out.println("    <th class=\"pentagon\">Priority</th>");
              out.println("    <th class=\"pentagon\">Improvement Potential</th>");
              out.println("    <th class=\"pentagon\">Section</th>");
              out.println("    <th class=\"pentagon\">Weight</th>");
              out.println("  </tr>");
              List<PentagonBoxHelper> pbSorted = new ArrayList<PentagonBoxHelper>(pentagonRowHelper);
              Collections.sort(pbSorted, new WeightComparator());
              for (PentagonBoxHelper pb : pbSorted)
              {
                String styleClass = "pentagonSelected";
                if (pb.getPentagonBox().getBoxName().equals(pentagonBoxHelper.getBoxName()))
                {
                  styleClass = "pentagonSelected";
                } else
                {
                  styleClass = "pentagon";
                }
                out.println("  <tr class=\"pentagon\">");
                out.println("    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + pb.getPentagonBox().getPriorityRow() + "</td>");
                out.println(
                    "    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + pb.getPentagonBox().getReportScoreGap() + "%</td>");
                out.println(
                    "    <td class=\"" + styleClass + "\"><a class=\"pentagonMenuLink\" href=\"javascript: void(0);\" onClick=\"loadBoxContents('"
                        + pb.getPentagonRowHelper().getLabel() + ": " + pb.getTitle() + "', '" + pb.getBoxName() + "', '', true)\">" + pb.getTitle()
                        + "</a></td>");
                out.println("    <td class=\"" + styleClass + "\" style=\"text-align: center;\">" + pb.getPentagonBox().getReportWeight() + "%</td>");
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
                  if (pentagonReportDisplay.getScore(pentagonBoxHelper.getBoxName()) < 0)
                  {
                    out.println("    <td class=\"pentagon\">Not Run</td>");
                  } else
                  {
                    out.println("    <td class=\"pentagon\">" + pentagonReportDisplay.getScore(pentagonBoxHelper.getBoxName()) + "</td>");
                  }
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
              printIISSelectionList(dataSession, out, pentagonBoxHelper, userSession);
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
      boolean anonymize = !testMessage.getTestSection().getTestConducted().getTestParticipant().canViewConnectionLabel(userSession);

      printTestMessage(dataSession, out, profileUsage, testMessage, anonymize, userSession);
    }
    out.close();
  }

  public static void showDownloads(PrintWriter out, TestConducted testConducted, List<TestSection> testSectionList)
  {
    out.println("<table class=\"pentagon\">");
    out.println("  <tr class=\"pentagon\">");
    out.println("    <th class=\"pentagon\">Test Areas</th>");
    out.println("    <th class=\"pentagon\">Updates</th>");
    out.println("    <th class=\"pentagon\">Acks</th>");
    out.println("    <th class=\"pentagon\">Queries</th>");
    out.println("    <th class=\"pentagon\">Responses</th>");
    out.println("  </tr>");
    boolean queriesWereRun = false;
    for (TestSection testSection : testSectionList)
    {
      String link = "hl7Download?" + HL7DownloadServlet.PARAM_TEST_CONDUCTED_ID + "=" + testConducted.getTestConductedId() + "&"
          + HL7DownloadServlet.PARAM_TEST_SECTION_ID + "=" + testSection.getTestSectionId() + "&" + HL7DownloadServlet.PARAM_TYPE + "=";
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
      out.println("    <td class=\"pentagon\">" + testSection.getTestSectionType() + "</td>");
      if (testSection.getCountLevel1() < 0)
      {
        out.println("    <td class=\"pentagon\">-</td>");
        out.println("    <td class=\"pentagon\">-</td>");
      } else
      {
        out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_UPDATES + "\">Download "
            + testSection.getCountLevel1() + "</a></td>");
        out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_ACKS + "\">Download "
            + testSection.getCountLevel1() + "</a></td>");
      }
      if (testSection.getCountLevel2() < 0)
      {
        out.println("    <td class=\"pentagon\">-</td>");
        out.println("    <td class=\"pentagon\">-</td>");
      } else
      {
        queriesWereRun = true;
        out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_QUERIES + "\">Download "
            + testSection.getCountLevel2() + "</a></td>");
        out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_RESPONSES + "\">Download "
            + testSection.getCountLevel2() + "</a></td>");
      }
      out.println("  </tr>");
    }
    {
      String link = "hl7Download?" + HL7DownloadServlet.PARAM_TEST_CONDUCTED_ID + "=" + testConducted.getTestConductedId() + "&"
          + HL7DownloadServlet.PARAM_TYPE + "=";
      out.println("  <tr class=\"pentagon\">");
      out.println("    <td class=\"pentagon\">All Messages</td>");
      out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_UPDATES + "\">Download "
          + testConducted.getCountUpdate() + "</a></td>");
      out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_ACKS + "\">Download "
          + testConducted.getCountUpdate() + "</a></td>");
      if (!queriesWereRun)
      {
        out.println("    <td class=\"pentagon\">-</td>");
        out.println("    <td class=\"pentagon\">-</td>");
      } else
      {
        out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_QUERIES + "\">Download "
            + testConducted.getCountQuery() + "</a></td>");
        out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + HL7DownloadServlet.TYPE_RESPONSES + "\">Download "
            + testConducted.getCountQuery() + "</a></td>");
      }
      out.println("  </tr>");

    }
    out.println("</table>");
  }

  public void printIISSelectionList(Session dataSession, PrintWriter out, PentagonBoxHelper pentagonBoxHelper, UserSession userSession)
  {
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
      if (pentagonReportDisplay.getScore(pentagonBoxHelper.getBoxName()) < 0)
      {
        out.println("    <td class=\"pentagon\">Not Run</td>");
      } else
      {
        out.println("    <td class=\"pentagon\">" + pentagonReportDisplay.getScore(pentagonBoxHelper.getBoxName()) + "%</td>");
      }
      {
        String link = "pentagon?" + PARAM_TEST_CONDUCTED_ID + "=" + testConductedDisplay.getTestConductedId();
        out.println("    <td class=\"pentagon\"><a class=\"pentagon\" href=\"" + link + "\">"
            + testConductedDisplay.getTestParticipant().getConnectionLabel(userSession) + "</a></td>");
      }
      out.println("  </tr>");
    }
    out.println("  </table>");
  }

  public String getImprovementLevelText(int scoreGap)
  {
    String improvementLevel = "<b>No improvement possible:</b> ";
    if (scoreGap >= 10)
    {
      improvementLevel = "<b>Major improvement possible:</b> ";
    } else if (scoreGap >= 5)
    {
      improvementLevel = "<b>Improvement possible:</b> ";
    } else if (scoreGap > 0)
    {
      improvementLevel = "<b>Some improvement possible:</b> ";
    }
    return improvementLevel;
  }

  public static List<TestConducted> getOtherIISTestReports(Session dataSession)
  {
    Query query = dataSession.createQuery("from TestConducted where latestTest = 'Y' order by testParticipant.connectionLabel");
    List<TestConducted> testConductedList = query.list();
    return testConductedList;
  }

  public static void printTestMessage(Session dataSession, PrintWriter out, ProfileUsage profileUsage, TestMessage testMessage, boolean anonymize,
      UserSession userSession) throws IOException
  {
    if (testMessage != null)
    {
      printOverview(out, testMessage);
      boolean hidePatientId = !userSession.isAdmin();
      if (testMessage.getTestType().equals("prep") || testMessage.getTestType().equals("update"))
      {
        printHL7ForUpdate(out, profileUsage, testMessage, anonymize, hidePatientId);
      } else if (testMessage.getTestType().equals("query"))
      {
        printHl7ForQuery(out, profileUsage, testMessage, anonymize, hidePatientId);
      }
      printConformance(dataSession, out, testMessage);
      printPreparationDetails(out, profileUsage, testMessage);
      printCompareDetails(out, testMessage, userSession, dataSession);
      printHistoryDetails(out, testMessage, userSession, dataSession);

      out.println("<div id=\"detailsData\">");
      out.println("<p class=\"pentagon\">Here is where I'll put a view of the patient data that was sent or recieved back. </p>");
      out.println("</div>");
    }
  }

  public static void printHl7ForQuery(PrintWriter out, ProfileUsage profileUsage, TestMessage testMessage, boolean anonymize, boolean hidePatientId)
      throws IOException
  {
    String prepMessageDerivedFrom = testMessage.getPrepMessageDerivedFrom();
    String prepMessageOriginalResponse = testMessage.getPrepMessageOriginalResponse();
    String prepMessageActual = testMessage.getPrepMessageActual();
    String resultMessageActual = testMessage.getResultMessageActual();
    if (anonymize)
    {
      prepMessageDerivedFrom = anonymizeHL7(prepMessageDerivedFrom);
      prepMessageOriginalResponse = anonymizeHL7(prepMessageOriginalResponse);
      prepMessageActual = anonymizeHL7(prepMessageActual);
      resultMessageActual = anonymizeHL7(resultMessageActual);
    }
    if (hidePatientId)
    {
      prepMessageOriginalResponse = removePatientIdentifyingSegments(prepMessageOriginalResponse);
      resultMessageActual = removePatientIdentifyingSegments(resultMessageActual);
    }

    out.println("<div id=\"detailsHL7\">");
    if (!testMessage.getPrepMessageDerivedFrom().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Update" + (anonymize ? " *" : "") + "</h4>");
      out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(prepMessageDerivedFrom, profileUsage) + "</pre>");
      if (testMessage.getResultStoreStatus().equals("a-r") || testMessage.getResultStoreStatus().equals("a-nr"))
      {
        out.println("<h4 class=\"pentagon\">Accepted by IIS" + (anonymize ? " *" : "") + "</h4>");
      } else if (testMessage.getResultStoreStatus().equals("na-r") || testMessage.getResultStoreStatus().equals("na-nr"))
      {
        out.println("<h4 class=\"pentagon\">Rejected by IIS" + (anonymize ? " *" : "") + "</h4>");
      } else
      {
        out.println("<h4 class=\"pentagon\">Response from IIS" + (anonymize ? " *" : "") + "</h4>");
      }
      out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(prepMessageOriginalResponse, profileUsage) + "</pre>");
      out.println("<h4 class=\"pentagon\">Query" + (anonymize ? " *" : "") + "</h4>");
    }
    out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(prepMessageActual, profileUsage) + "</pre>");
    out.println("<h4 class=\"pentagon\">" + testMessage.getResultQueryType() + " Returned" + (anonymize ? " *" : "") + "</h4>");
    out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(resultMessageActual, profileUsage) + "</pre>");
    printAnonymizedStatement(out, anonymize);
    out.println("</div>");

  }

  public static void printAnonymizedStatement(PrintWriter out, boolean anonymize)
  {
    if (anonymize)
    {
      out.println("<p class=\"pentagon\">* Message has been modified to remove sensitive MSH fields: "
          + "MSH-3, MSH-4, MSH-5, MSH-6, MSH-8, MSH-22, and MSH-23. </p>");
    }
  }

  private static final String ANONYMIZE_TRANSFORMS = "clear MSH-3 \nclear MSH-4 \nclear MSH-5 \nclear MSH-6 \nclear MSH-7 \nclear MSH-22 \nclear MSH-23\n";

  public static final String anonymizeHL7(String hl7)
  {
    Transformer transformer = new Transformer();
    TestCaseMessage tcm = new TestCaseMessage();
    tcm.setOriginalMessage(hl7);
    tcm.setCustomTransformations(ANONYMIZE_TRANSFORMS);
    transformer.transform(tcm);
    return tcm.getMessageText();
  }

  private static final Set<String> ID_SEGMENTS_TO_REMOVE = new HashSet<String>();

  static
  {
    ID_SEGMENTS_TO_REMOVE.add("PID");
    ID_SEGMENTS_TO_REMOVE.add("NK1");
    ID_SEGMENTS_TO_REMOVE.add("GT1");
    ID_SEGMENTS_TO_REMOVE.add("GT2");
    ID_SEGMENTS_TO_REMOVE.add("IN1");
    ID_SEGMENTS_TO_REMOVE.add("IN2");
  };

  public static final String removePatientIdentifyingSegments(String message)
  {
    System.out.println("--> calling");
    System.out.println("--> " + message);
    StringBuilder sb = new StringBuilder();
    BufferedReader in = new BufferedReader(new StringReader(message));
    String line;
    try
    {
      while ((line = in.readLine()) != null)
      {
        String segmentName = line;
        if (line.length() > 3)
        {
          segmentName = line.substring(0, 3);
        }
        if (ID_SEGMENTS_TO_REMOVE.contains(segmentName))
        {
          sb.append(segmentName);
          for (int i = 3; i < line.length(); i++)
          {
            if (line.charAt(i) == '|')
            {
              sb.append('|');
            } else
            {
              sb.append('-');
            }
          }
          sb.append("\r");
        } else
        {
          sb.append(line);
          sb.append("\r");
        }
      }
    } catch (IOException ioe)
    {
      ioe.printStackTrace();
      // not expecting when reading string
    }
    System.out.println("--> " + sb.toString());
    return sb.toString();
  }

  public static void printHL7ForUpdate(PrintWriter out, ProfileUsage profileUsage, TestMessage testMessage, boolean anonymize, boolean hidePatientId)
      throws IOException
  {
    String prepMessageActual = testMessage.getPrepMessageActual();
    String resultMessageActual = testMessage.getResultMessageActual();
    if (anonymize)
    {
      prepMessageActual = anonymizeHL7(prepMessageActual);
      resultMessageActual = anonymizeHL7(resultMessageActual);
    }
    if (hidePatientId)
    {
      resultMessageActual = removePatientIdentifyingSegments(resultMessageActual);
    }

    out.println("<div id=\"detailsHL7\">");
    out.println("<h4 class=\"pentagon\">Update Message Submitted" + (anonymize ? " *" : "") + "</h4>");
    out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(prepMessageActual, profileUsage) + "</pre>");
    if (testMessage.isResultAccepted())
    {
      out.println("<h4 class=\"pentagon\">Accepted by IIS" + (anonymize ? " *" : "") + "</h4>");
    } else
    {
      out.println("<h4 class=\"pentagon\">NOT Accepted by IIS" + (anonymize ? " *" : "") + "</h4>");
    }
    out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(resultMessageActual, profileUsage) + "</pre>");
    printAnonymizedStatement(out, anonymize);
    out.println("</div>");
  }

  public static void printOverview(PrintWriter out, TestMessage testMessage)
  {
    out.println("<div id=\"detailsOverview\">");

    if (testMessage.getTestType().equals("prep") || testMessage.getTestType().equals("update"))
    {
      if (testMessage.getTestType().equals("prep"))
      {
        out.println(
            "<p class=\"pentagon\">This message is sent in before testing begins to ensure that a base message can be accepted. It is important that "
                + "this message is accepted in order for the other tests to proceed, but the results of this particular test are not otherwise used. </p>");
      } else
      {
        if (testMessage.getTestCaseAssertResult().startsWith("Accept"))
        {
          out.println(
              "<p class=\"pentagon\">This update message includes patient information that is being sent to the IIS for inclusion in the registry. "
                  + "The expectation is that this message will be accepted by the IIS.  </p>");
        } else
        {
          out.println(
              "<p class=\"pentagon\">This update message includes patient information that is being sent to the IIS for inclusion in the registry. "
                  + "However the test case expects that the IIS will find a problem with this message and will not accept it. </p>");
        }
      }
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">General Test Id</th>");
      out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseCategory() + "</td>");
      out.println("  </tr>");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Specific Test Id</th>");
      out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseNumber() + "</td>");
      out.println("  </tr>");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Type</th>");
      out.println("    <td class=\"pentagon\">" + testMessage.getTestType() + "</td>");
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
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Accepted</th>");
      out.println("    <td class=\"pentagon\">" + (testMessage.isResultAccepted() ? "Yes" : "No") + "</td>");
      out.println("  </tr>");
      out.println("</table>");
      if (testMessage.getResultStatus().equalsIgnoreCase("PASS"))
      {
        if (testMessage.getTestType().equals("prep"))
        {
          out.println("<p class=\"pentagon\">This test case was accepted. This means testing could proceed normally. </p>");
        } else
        {
          if (testMessage.getTestCaseAssertResult().startsWith("Accept"))
          {
            out.println("<p class=\"pentagon\">The IIS appeared to accept this message, just as was expected.</p>");
          } else
          {
            out.println("<p class=\"pentagon\">The IIS did not appear to accept this message, just as expected.. </p>");
          }
        }
      } else
      {
        if (testMessage.getTestType().equals("prep"))
        {
          out.println(
              "<p class=\"pentagon\">This test case was not accepted. This means that all or part of the test process could not proceed. </p>");
        } else
        {
          if (testMessage.getTestCaseAssertResult().startsWith("Accept"))
          {
            out.println(
                "<p class=\"pentagon\">The IIS appeared to accept this message. The test was expecting the IIS to find a problem and reject it. </p>");
          } else
          {
            out.println("<p class=\"pentagon\">The IIS did not appear to accept this message. The test was expecting the IIS to accept it. </p>");
          }
        }
      }

    } else if (testMessage.getTestType().equals("query"))
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Id</th>");
      out.println("    <td class=\"pentagon\">" + testMessage.getTestCaseNumber() + "</td>");
      out.println("  </tr>");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Test Type</th>");
      out.println("    <td class=\"pentagon\">" + testMessage.getTestType() + "</td>");
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
    }
    if (!testMessage.getResultExceptionText().equals(""))
    {
      out.println("<h4 class=\"pentagon\">Exception Encountered</h4>");
      out.println("<pre class=\"pentagon\">" + testMessage.getResultExceptionText() + "</pre>");
    }
    out.println("</div>");
  }

  public static List<TestConducted> getTestConductedList(Session dataSession, TestConducted testConducted)
  {
    Query query = dataSession
        .createQuery("from TestConducted where testParticipant.connectionLabel = ? and completeTest = 'Y' order by testStartedTime Desc");
    query.setParameter(0, testConducted.getTestParticipant().getConnectionLabel());
    List<TestConducted> testConductedList = query.list();
    return testConductedList;
  }

  public static void printReportsRun(Session dataSession, PrintWriter out, List<TestConducted> testConductedList, UserSession userSession)
  {
    List<TestConducted> testConductedListCanView = new ArrayList<TestConducted>();
    List<TestConducted> testConductedListAnonymous = new ArrayList<TestConducted>();
    for (TestConducted testConductedDisplay : testConductedList)
    {
      if (testConductedDisplay.getTestParticipant().canViewConnectionLabel(userSession))
      {
        testConductedListCanView.add(testConductedDisplay);
      } else
      {
        testConductedListAnonymous.add(testConductedDisplay);
      }
    }

    Collections.sort(testConductedListCanView, new Comparator<TestConducted>() {
      @Override
      public int compare(TestConducted tc1, TestConducted tc2)
      {
        return tc1.getTestParticipant().getConnectionLabel().compareTo(tc2.getTestParticipant().getConnectionLabel());
      }
    });

    if (testConductedListCanView.size() > 0)
    {
      if (testConductedListCanView.size() == 1)
      {
        out.println("<h4 class=\"pentagon\">Your IIS Report</h4>");
      } else
      {
        out.println("<h4 class=\"pentagon\">Your IIS Reports</h4>");
      }
      printReportTable(dataSession, out, testConductedListCanView, userSession);
    }
    if (TestReportServlet.SHOW_ANONYMOUS_REPORTS)
    {
      if (testConductedListAnonymous.size() > 0)
      {
        if (testConductedListAnonymous.size() == 1)
        {
          out.println("<h4 class=\"pentagon\">Other IIS Report</h4>");
        } else
        {
          out.println("<h4 class=\"pentagon\">Other IIS Reports</h4>");
        }
        printReportTable(dataSession, out, testConductedListAnonymous, userSession);
      }
    }
  }

  public static void printReportTable(Session dataSession, PrintWriter out, List<TestConducted> testConductedList, UserSession userSession)
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
      String link = "pentagon?" + PARAM_TEST_CONDUCTED_ID + "=" + testConductedDisplay.getTestConductedId();
      out.println("    <td class=\"pentagon\">");
      out.println("     <h4 class=\"pentagon\"><a class=\"pentagonMenuLink\" href=\"" + link + "\">"
          + testConductedDisplay.getTestParticipant().getConnectionLabel(userSession) + "</a></h4>");
      out.println("     <p class=\"pentagon\">Last Run: " + sdf.format(testConductedDisplay.getTestStartedTime()));
      out.println("      </p>");
      out.println("     <p><a class=\"pentagonMenuLink\" href=\"" + link + "\">View Report</a></p>");
      out.println("    </td>");
      out.println("  </tr>");
    }
    out.println("  </table>");
    out.println("  <br/>");
  }

  public static void printCompareDetails(PrintWriter out, TestMessage testMessage, UserSession userSession, Session dataSession) throws IOException
  {
    out.println("<div id=\"detailsComparison\">");
    Query query = dataSession.createQuery(
        "from TestMessage where testSection.testConducted.latestTest = ? and testCaseCategory = ? order by testSection.testConducted.testParticipant.connectionLabel");
    query.setParameter(0, true);
    query.setParameter(1, testMessage.getTestCaseCategory());
    List<TestMessage> exampleTestMessageList = query.list();
    PentagonBoxHelper.printTestMessageListPass(out, exampleTestMessageList, Show.CONNECTION_LABEL, true, userSession);
    out.println("</div>");
  }

  public static void printHistoryDetails(PrintWriter out, TestMessage testMessage, UserSession userSession, Session dataSession) throws IOException
  {
    out.println("<div id=\"detailsHistory\">");
    Query query = dataSession
        .createQuery("from TestMessage where testSection.testConducted.completeTest = ? and testSection.testConducted.testParticipant = ? "
            + "and testCaseCategory = ? order by testSection.testConducted.testParticipant.connectionLabel");
    query.setParameter(0, true);
    query.setParameter(1, testMessage.getTestSection().getTestConducted().getTestParticipant());
    query.setParameter(2, testMessage.getTestCaseCategory());
    List<TestMessage> exampleTestMessageList = query.list();
    PentagonBoxHelper.printTestMessageListPass(out, exampleTestMessageList, Show.TEST_DATE, true, userSession);
    out.println("</div>");
  }

  public static void printPreparationDetails(PrintWriter out, ProfileUsage profileUsage, TestMessage testMessage) throws IOException
  {
    out.println("<div id=\"detailsPreparation\">");
    out.println("<p class=\"pentagon\">Each test message is prepared dynamically through a process that creates consistently formatted "
        + "HL7 messages with unique patient and vaccination information. This allows for each test run to contain new and unique patients "
        + "that the IIS has not seen before. This page details the steps that are taken in order to arrive at the test message that "
        + "was used for this test case. </p>");
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
    out.println("</div>");
  }

  public static void printConformance(Session dataSession, PrintWriter out, TestMessage testMessage)
  {
    out.println("<div id=\"detailsConformance\">");
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
        out.println(
            "<p class=\"pentagon\">The National Institute of Standards and Technology (NIST) provides a service for verifying the conformance "
                + "of immunization messages to the HL7 and CDC standards. This section shows errors that were returned by NIST software. "
                + "For a complete list of all errors, warnings, and assertions please use NIST's tool. For the sake of brevity this section only "
                + "reports unique errors that were identified in the message. </p>");
        if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
        {
          out.println("<h4 class=\"pentagon\">Response Meets NIST Standards</h4>");
          out.println(
              "<p>The response was validated against the NIST validator for immunization messages and not error level issues were found. </p>");
        } else if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_ERROR))
        {
          out.println("<h4 class=\"pentagon\">Response Does NOT Meet NIST Standards</h4>");
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
    out.println("</div>");
  }

  private static final class WeightComparator implements Comparator<PentagonBoxHelper>
  {
    @Override
    public int compare(PentagonBoxHelper pbh1, PentagonBoxHelper pbh2)
    {
      if (pbh1.getPentagonBox().getPriorityRow() > pbh2.getPentagonBox().getPriorityRow())
      {
        return 1;
      } else if (pbh1.getPentagonBox().getPriorityRow() < pbh2.getPentagonBox().getPriorityRow())
      {
        return -11;
      }
      return 0;
    }
  }

  private static final class OverallComparator implements Comparator<PentagonBoxHelper>
  {
    @Override
    public int compare(PentagonBoxHelper pbh1, PentagonBoxHelper pbh2)
    {
      if (pbh1.getPentagonBox().getPriorityOverall() > pbh2.getPentagonBox().getPriorityOverall())
      {
        return 1;
      } else if (pbh1.getPentagonBox().getPriorityOverall() < pbh2.getPentagonBox().getPriorityOverall())
      {
        return -11;
      }
      return 0;
    }
  }

}
