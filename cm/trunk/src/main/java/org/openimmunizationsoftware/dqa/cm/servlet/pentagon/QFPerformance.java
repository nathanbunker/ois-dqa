package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.TestReportServlet;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;

public class QFPerformance extends PentagonBoxHelper
{
  public QFPerformance(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Ideally IIS should respond quickly to queries received. External systems depend on "
        + "the IIS to return data to support clinical decisions. The longer that a clinician has to wait for this "
        + "information the less useful this information will be and the less it may be used. Many IIS "
        + "are able to return query responses within 1 second. Full score is given to average responses less than "
        + "or equal to 1 second, and partial score is given to average responses less than 2 seconds. While performance is "
        + "very important this measure can only begin to measure performance. A complete performance and load test is beyond "
        + "the scope of this testing and should conducted separately. </p>");
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    int average = (int) (((double) pentagonReport.getTestConducted().getPerQueryTotal()) / pentagonReport.getTestConducted().getPerQueryCount() + 0.5);
    out.println("<h4 class=\"pentagon\">Response Time</h4>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">Average: " + TestReportServlet.createTime(average) + "</li>");
    out.println("  <li class=\"pentagon\">Minimum: " + TestReportServlet.createTime(pentagonReport.getTestConducted().getPerQueryMin()) + "</li>");
    out.println("  <li class=\"pentagon\">Maximum: " + TestReportServlet.createTime(pentagonReport.getTestConducted().getPerQueryMax()) + "</li>");
    out.println("  <li class=\"pentagon\">Std Dev: " + TestReportServlet.createTime(pentagonReport.getTestConducted().getPerQueryStd()) + "</li>");
    out.println("</ul>");
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">If the average response time for queries is less than 1.05 seconds then a score of 100% is given.</li>");
    out.println(
        "  <li class=\"pentagon\">If the average response time for queries is less than 2.00 seconds then the score is negation of the average response time"
            + "minus 2 seconds and then divided by 1 second. </li>");
    out.println("  <li class=\"pentagon\">If the average response time is 2.00 seconds or more, the score is set to 0.</li>");
    out.println("</ul>");
    out.println("<p class=\"pentagon\">For example, if the average response time is 1.25 seconds the score is calculated as follows: "
        + "-((1.25 - 2.00) / 1.00) = 0.75 = 75%</p>");

  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    TestConducted testConducted = pentagonReport.getTestConducted();
    int perQueryCount = testConducted.getPerQueryCount();
    int perQueryTotal = testConducted.getPerQueryTotal();
    if (perQueryTotal > 0)
    {
      int millisecondsPerQuery = Math.round(((float) perQueryTotal) / perQueryCount);
      if (millisecondsPerQuery < 1050)
      {
        pentagonBox.setReportScore(100);
      } else if (millisecondsPerQuery > 2000)
      {
        pentagonBox.setReportScore(0);
      } else
      {
        pentagonBox.setReportScore((int) Math.round(-100 * ((millisecondsPerQuery - 2000.0) / 1000)));
      }
    }
  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Improvement in performance requires "
        + "a thorough review of current processing procedure to determine what is happening to delay the processing of this message. In "
        + "some cases the delays may be architectural and can not be avoided, in others they may be due to how the test system is "
        + "configured. The meaning and importance of this section is thus left to the IIS to determine. </p>");
    
  }
}
