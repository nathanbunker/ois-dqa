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
    super("UFPerformance");
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
    out.println("<h3>Response Time</h3>");
    out.println("<ul>");
    out.println("  <li>Average: " + TestReportServlet.createTime(average) + "</li>");
    out.println("  <li>Minimum: " + TestReportServlet.createTime(testConducted.getPerUpdateMin()) + "</li>");
    out.println("  <li>Maximum: " + TestReportServlet.createTime(testConducted.getPerUpdateMax()) + "</li>");
    out.println("  <li>Std Dev: " + TestReportServlet.createTime(testConducted.getPerUpdateStd()) + "</li>");
    out.println("</ul>");
  }

  
  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    // TODO Auto-generated method stub
    
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
