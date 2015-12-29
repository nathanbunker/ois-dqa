package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.TestReportServlet;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class UFPerformance extends PentagonBox
{
  public UFPerformance()
  {
    super(BOX_NAME_UF_PERFORMANCE);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println(
        "<p class=\"pentagon\">Ideally IIS should respond quickly to updates received. Rapid response reduces transmission times for large amounts"
            + "of data and provides good support for systems as they increase their integration with IIS. "
            + "This report includes a performance measure mostly because the information is available when testing and it has some "
            + "impact on the perception of how well the IIS is working. However, please note that performance makes a very small "
            + "contribution to the overall score.   </p>");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    int average = (int) (((double) testConducted.getPerUpdateTotal()) / testConducted.getPerUpdateCount() + 0.5);
    out.println("<h3 class=\"pentagon\">Response Time</h3>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">Average: " + TestReportServlet.createTime(average) + "</li>");
    out.println("  <li class=\"pentagon\">Minimum: " + TestReportServlet.createTime(testConducted.getPerUpdateMin()) + "</li>");
    out.println("  <li class=\"pentagon\">Maximum: " + TestReportServlet.createTime(testConducted.getPerUpdateMax()) + "</li>");
    out.println("  <li class=\"pentagon\">Std Dev: " + TestReportServlet.createTime(testConducted.getPerUpdateStd()) + "</li>");
    out.println("</ul>");
  }

  
  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
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
    out.println("<h4 class=\"pentagon\">How To Improve Score</h4>");
    out.println("<p class=\"pentagon\">Performance is a relatively insignificant part of the final score for this "
        + "section. Faster response times are helpful but not critical for update functionality. Improvement in performance requires "
        + "a thorough review of current processing procedure to determine what is happening to delay the processing of this message. In "
        + "some cases the delays may be architectural and can not be avoided, in others they may be due to how the test system is "
        + "configured. The meaning and importance of this section is thus left to the IIS to determine. </p>");
  }

  
  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    int perUpdateCount = testConducted.getPerUpdateCount();
    int perUpdateTotal = testConducted.getPerUpdateTotal();
    if (perUpdateTotal > 0)
    {
      int millisecondsPerUpdate = Math.round(((float) perUpdateTotal) / perUpdateCount);
      if (millisecondsPerUpdate < 1050)
      {
        pentagonReport.setScoreUFPerformance(100);
      } else if (millisecondsPerUpdate > 2000)
      {
        pentagonReport.setScoreUFPerformance(0);
      } else
      {
        pentagonReport.setScoreUFPerformance((int) Math.round(-100 * ((millisecondsPerUpdate - 2000.0) / 1000)));
      }
    }    
  }
}
