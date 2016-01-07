package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.TestReportServlet;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class UFPerformance extends PentagonBoxHelper
{
  public UFPerformance(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession,PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println(
        "<p class=\"pentagon\">Ideally IIS should respond quickly to updates received. Rapid response reduces transmission times for large amounts"
            + "of data and provides good support for systems as they increase their integration with IIS. "
            + "This report includes a performance measure mostly because the information is available when testing and it has some "
            + "impact on the perception of how well the IIS is working. However, please note that performance makes a very small "
            + "contribution to the overall score.   </p>");
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    int average = (int) (((double) pentagonReport.getTestConducted().getPerUpdateTotal()) / pentagonReport.getTestConducted().getPerUpdateCount() + 0.5);
    out.println("<h4 class=\"pentagon\">Response Time</h4>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">Average: " + TestReportServlet.createTime(average) + "</li>");
    out.println("  <li class=\"pentagon\">Minimum: " + TestReportServlet.createTime(pentagonReport.getTestConducted().getPerUpdateMin()) + "</li>");
    out.println("  <li class=\"pentagon\">Maximum: " + TestReportServlet.createTime(pentagonReport.getTestConducted().getPerUpdateMax()) + "</li>");
    out.println("  <li class=\"pentagon\">Std Dev: " + TestReportServlet.createTime(pentagonReport.getTestConducted().getPerUpdateStd()) + "</li>");
    out.println("</ul>");
  }

  
  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">If the average response time for updates is less than 1.05 seconds then a score of 100% is given.</li>");
    out.println("  <li class=\"pentagon\">If the average response time for updates is less than 2.00 seconds then the score is negation of the average response time"
        + "minus 2 seconds and then divided by 1 second. </li>");
    out.println("  <li class=\"pentagon\">If the average response time is 2.00 seconds or more, the score is set to 0.</li>");
    out.println("</ul>");
    out.println("<p class=\"pentagon\">For example, if the average response time is 1.25 seconds the score is calculated as follows: "
        + "-((1.25 - 2.00) / 1.00) = 0.75 = 75%</p>");
  }

  
  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    int perUpdateCount = pentagonReport.getTestConducted().getPerUpdateCount();
    int perUpdateTotal = pentagonReport.getTestConducted().getPerUpdateTotal();
    if (perUpdateTotal > 0)
    {
      int millisecondsPerUpdate = Math.round(((float) perUpdateTotal) / perUpdateCount);
      if (millisecondsPerUpdate < 1050)
      {
        pentagonBox.setReportScore(100);
      } else if (millisecondsPerUpdate > 2000)
      {
        pentagonBox.setReportScore(0);
      } else
      {
        pentagonBox.setReportScore((int) Math.round(-100 * ((millisecondsPerUpdate - 2000.0) / 1000)));
      }
    }    
  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Performance is a relatively insignificant part of the final score for this "
        + "section. Faster response times are helpful but not critical for update functionality. Improvement in performance requires "
        + "a thorough review of current processing procedure to determine what is happening to delay the processing of this message. In "
        + "some cases the delays may be architectural and can not be avoided, in others they may be due to how the test system is "
        + "configured. The meaning and importance of this section is thus left to the IIS to determine. </p>");
  }
}
