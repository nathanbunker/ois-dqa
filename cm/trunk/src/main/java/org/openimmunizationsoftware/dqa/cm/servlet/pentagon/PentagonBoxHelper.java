package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

public abstract class PentagonBoxHelper
{
  protected PentagonRowHelper pentagonRowHelper = null;
  protected PentagonBox pentagonBox = null;
  protected String label = "";
  protected String title = "";
  protected int width = 0;
  protected int posX = 0;
  protected int poxY = 0;
  protected int size = 0;

  public PentagonRowHelper getPentagonRowHelper()
  {
    return pentagonRowHelper;
  }
  
  public PentagonBox getPentagonBox()
  {
    return pentagonBox;
  }

  public int getWidth()
  {
    return width;
  }

  public void setWidth(int width)
  {
    this.width = width;
  }

  public PentagonBoxHelper(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    this.pentagonBox = pentagonBox;
    this.pentagonRowHelper = pentagonRowHelper;
  }

  public String getBoxName()
  {
    return pentagonBox.getBoxName();
  }

  public int getScore()
  {
    return pentagonBox.getReportScore();
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public int getSize()
  {
    return size;
  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public int getPosX()
  {
    return posX;
  }

  public void setPosX(int posX)
  {
    this.posX = posX;
  }

  public int getPoxY()
  {
    return poxY;
  }

  public void setPoxY(int poxY)
  {
    this.poxY = poxY;
  }

  public int getWeight()
  {
    return pentagonBox.getReportWeight();
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public abstract void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession);

  public abstract void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession);

  public abstract void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession);

  public abstract void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession);

  
  public abstract void calculateScore(Session dataSession, PentagonReport pentagonReport);

  public void printTestMessageListFail(PrintWriter out, List<TestMessage> testMessageList)
  {
    if (testMessageList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Accepted</th>");
      out.println("    <th class=\"pentagon\">Description</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">Fail</td>");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + (testMessage.isResultAccepted() ? "Yes" : "No") + "</td>");
        out.println("    <td class=\"pentagon\"><a class=\"pentagonTestMessageFail\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
            + testMessage.getTestMessageId() + "', '" + testMessage.getTestCaseDescription() + "');\">" + testMessage.getTestCaseDescription()
            + "</a></td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public static void printTestMessageListPass(PrintWriter out, List<TestMessage> testMessageList)
  {
    if (testMessageList.size() > 0)
    {
      if (testMessageList.get(0).getTestType().equals(RecordServletInterface.VALUE_TEST_TYPE_QUERY))
      {
        out.println("<table class=\"pentagon\">");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Status</th>");
        out.println("    <th class=\"pentagon\">Result</th>");
        out.println("    <th class=\"pentagon\">Store Status</th>");
        out.println("    <th class=\"pentagon\">Forecast Status</th>");
        out.println("    <th class=\"pentagon\">Description</th>");
        out.println("  </tr>");
        for (TestMessage testMessage : testMessageList)
        {
          out.println("  <tr class=\"pentagon\">");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">Pass</td>");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultQueryType() + "</td>");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultStoreStatusForDisplay() + "</td>");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultForecastStatus() + "</td>");
          String testMessageLink = createLink(testMessage);
          out.println("    <td class=\"pentagon\">" + testMessageLink + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
      } else
      {
        out.println("<table class=\"pentagon\">");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Status</th>");
        out.println("    <th class=\"pentagon\">Accepted</th>");
        out.println("    <th class=\"pentagon\">Description</th>");
        out.println("  </tr>");
        for (TestMessage testMessage : testMessageList)
        {
          out.println("  <tr class=\"pentagon\">");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">Pass</td>");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + (testMessage.isResultAccepted() ? "Yes" : "No") + "</td>");
          String testMessageLink = createLink(testMessage);
          out.println("    <td class=\"pentagon\">" + testMessageLink + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");

      }
    }
  }

  public static String createLink(TestMessage testMessage)
  {
    String testMessageLink = createLink(testMessage, testMessage.getTestCaseDescription());
    return testMessageLink;
  }

  public static String createLink(TestMessage testMessage, String label)
  {
    String testMessageLink = "<a class=\"pentagonTestMessagePass\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
        + testMessage.getTestMessageId() + "', '" + testMessage.getTestCaseDescription() + "');\">" + label + "</a>";
    return testMessageLink;
  }

  public void printTestMessageListFailForQuery(PrintWriter out, List<TestMessage> testMessageList)
  {
    String classString = "pentagonTestMessageFail";
    printTestMessageListFailForQuery(out, testMessageList, classString);
  }

  public void printTestMessageListFailForQuery(PrintWriter out, List<TestMessage> testMessageList, String classString)
  {
    if (testMessageList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Response</th>");
      out.println("    <th class=\"pentagon\">Store Status</th>");
      out.println("    <th class=\"pentagon\">Description</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultStatus() + "</td>");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultQueryType() + "</td>");
        out.println("    <td class=\"pentagon\">" + testMessage.getResultStoreStatusForDisplay() + "</td>");
        out.println("    <td class=\"pentagon\"><a class=\"" + classString + "\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
            + testMessage.getTestMessageId() + "', '" + testMessage.getTestCaseDescription() + "');\">" + testMessage.getTestCaseDescription() + "</a></td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public void printTestMessageListPassForQuery(PrintWriter out, List<TestMessage> testMessageList)
  {
    String classString = "pentagonTestMessagePass";
    printTestMessageListFailForQuery(out, testMessageList, classString);
  }

  public void printCalculatingEssentialDataReturnedExplanation(PrintWriter out)
  {
    out.println("<h4 class=\"pentagon\">Calculating Essential Data Returned</h4>");
    out.println("<p class=\"pentagon\">IIS have varying standards and policies for what data is actually returned when queried so this "
        + "test is only done on a few core fields that would reasonably be expected to be returned. The following fields are compared: </p>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">Patient");
    out.println("    <ul class=\"pentagon\">");
    out.println("      <li class=\"pentagon\">Last Name</li>");
    out.println("      <li class=\"pentagon\">First Name</li>");
    out.println("      <li class=\"pentagon\">Middle Name (only if sent and also returned)</li>");
    out.println("      <li class=\"pentagon\">Date of Birth</li>");
    out.println("    </ul>");
    out.println("  </li>");
    out.println("  <li class=\"pentagon\">Vaccination (only fully administered or historical, ignoring refusals, history-of-disease, etc.) ");
    out.println("    <ul class=\"pentagon\">");
    out.println("      <li class=\"pentagon\">Administration Date</li>");
    out.println("      <li class=\"pentagon\">Vaccination Code (CVX or NDC)</li>");
    out.println("    </ul>");
    out.println("  </li>");
    out.println("</ul>");
  }

}
