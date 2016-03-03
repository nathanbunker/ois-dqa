package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.PentagonServlet;
import org.openimmunizationsoftware.dqa.cm.servlet.TestReportServlet;
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

  public abstract void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession);

  public abstract void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession);

  public abstract void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
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
      out.println("    <th class=\"pentagon\">Accepted</th>");
      out.println("    <th class=\"pentagon\">Description</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + (testMessage.isResultAccepted() ? "Yes" : "No") + "</td>");
        out.println("    <td class=\"pentagon\"><a class=\"pentagonTestMessageFail\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
            + testMessage.getTestMessageId() + "', '" + testMessage.getTestCaseDescription() + "');\">" + testMessage.getTestCaseDescription()
            + "</a></td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public static enum Show {
    DESCRIPTION, CONNECTION_LABEL, TEST_DATE
  }

  public static void printTestMessageListPass(PrintWriter out, List<TestMessage> testMessageList, UserSession userSession)
  {
    printTestMessageListPass(out, testMessageList, Show.DESCRIPTION, false, userSession);
  }

  public static void printTestMessageListPass(PrintWriter out, List<TestMessage> testMessageList, Show show, boolean hardLink,
      UserSession userSession)
  {
    if (testMessageList.size() > 0)
    {
      if (testMessageList.get(0).getTestType().equals(RecordServletInterface.VALUE_TEST_TYPE_QUERY))
      {
        out.println("<table class=\"pentagon\">");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Result Type</th>");
        out.println("    <th class=\"pentagon\">VXU and RSP Results</th>");
        out.println("    <th class=\"pentagon\">Forecast Status</th>");
        if (show == Show.DESCRIPTION)
        {
          out.println("    <th class=\"pentagon\">Description</th>");
        } else if (show == Show.CONNECTION_LABEL)
        {
          out.println("    <th class=\"pentagon\">System</th>");
        } else if (show == Show.TEST_DATE)
        {
          out.println("    <th class=\"pentagon\">Run Date</th>");
        }

        out.println("  </tr>");
        for (TestMessage testMessage : testMessageList)
        {
          if (!TestReportServlet.SHOW_ANONYMOUS_REPORTS)
          {
            if (!testMessage.getTestSection().getTestConducted().getTestParticipant().canViewConnectionLabel(userSession))
            {
              continue;
            }
          }
          out.println("  <tr class=\"pentagon\">");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultQueryType() + "</td>");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultStoreStatusForDisplay() + "</td>");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultForecastStatus() + "</td>");
          String testMessageLink;
          if (hardLink)
          {
            testMessageLink = createHardLink(testMessage, show, userSession);
          } else
          {
            testMessageLink = createAjaxLink(testMessage, show, userSession);
          }
          out.println("    <td class=\"pentagon\">" + testMessageLink + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
      } else
      {
        out.println("<table class=\"pentagon\">");
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Accepted</th>");
        out.println("    <th class=\"pentagon\">Description</th>");
        out.println("  </tr>");
        for (TestMessage testMessage : testMessageList)
        {
          if (!TestReportServlet.SHOW_ANONYMOUS_REPORTS)
          {
            if (!testMessage.getTestSection().getTestConducted().getTestParticipant().canViewConnectionLabel(userSession))
            {
              continue;
            }
          }
          out.println("  <tr class=\"pentagon\">");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + (testMessage.isResultAccepted() ? "Yes" : "No") + "</td>");
          String testMessageLink;
          if (hardLink)
          {
            testMessageLink = createHardLink(testMessage, show, userSession);
          } else
          {
            testMessageLink = createAjaxLink(testMessage, show, userSession);
          }
          out.println("    <td class=\"pentagon\">" + testMessageLink + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");

      }
    }
  }

  public static String createAjaxLink(TestMessage testMessage, UserSession userSession)
  {
    return createAjaxLink(testMessage, Show.DESCRIPTION, userSession);
  }

  public static String createAjaxLink(TestMessage testMessage, Show show, UserSession userSession)
  {
    String testMessageLink = createAjaxLink(testMessage, testMessage.getTestCaseDescription(), show, userSession);
    return testMessageLink;
  }

  public static String createExternalLink(String url, String label)
  {
    return "<a class=\"pentagonTestMessgePass\" target=\"_blank\" href=\"" + url + "\">" + label + "</a>";
  }

  public static String createAjaxLink(TestMessage testMessage, String label, UserSession userSession)
  {
    return createAjaxLink(testMessage, label, Show.DESCRIPTION, userSession);
  }

  public static String createAjaxLink(TestMessage testMessage, String label, Show show, UserSession userSession)
  {
    if (show != null)
    {
      if (show == Show.CONNECTION_LABEL)
      {
        return "<a class=\"pentagonTestMessagePass\" href=\"javascript: void(0);\" onclick=\"loadDetails('" + testMessage.getTestMessageId() + "', '"
            + testMessage.getTestCaseDescription() + "');\">"
            + testMessage.getTestSection().getTestConducted().getTestParticipant().getConnectionLabel(userSession) + "</a>";
      }
      if (show == Show.TEST_DATE)
      {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a z");
        return "<a class=\"pentagonTestMessagePass\" href=\"javascript: void(0);\" onclick=\"loadDetails('" + testMessage.getTestMessageId() + "', '"
            + testMessage.getTestCaseDescription() + "');\">" + sdf.format(testMessage.getTestSection().getTestConducted().getTestStartedTime())
            + "</a>";
      }
    }
    return "<a class=\"pentagonTestMessagePass\" href=\"javascript: void(0);\" onclick=\"loadDetails('" + testMessage.getTestMessageId() + "', '"
        + testMessage.getTestCaseDescription() + "');\">" + label + "</a>";
  }

  public static String createHardLink(TestMessage testMessage, Show show, UserSession userSession)
  {
    if (show != null)
    {
      if (show == Show.CONNECTION_LABEL)
      {
        return "<a class=\"pentagonTestMessagePass\" href=\"pentagon?" + PentagonServlet.PARAM_TEST_CONDUCTED_ID + "="
            + testMessage.getTestSection().getTestConducted().getTestConductedId() + "&" + PentagonServlet.PARAM_TEST_MESSAGE_ID + "="
            + testMessage.getTestMessageId() + "\">"
            + testMessage.getTestSection().getTestConducted().getTestParticipant().getConnectionLabel(userSession) + "</a>";
      }
      if (show == Show.TEST_DATE)
      {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a z");
        return "<a class=\"pentagonTestMessagePass\" href=\"pentagon?" + PentagonServlet.PARAM_TEST_CONDUCTED_ID + "="
            + testMessage.getTestSection().getTestConducted().getTestConductedId() + "&" + PentagonServlet.PARAM_TEST_MESSAGE_ID + "="
            + testMessage.getTestMessageId() + "\">" + sdf.format(testMessage.getTestSection().getTestConducted().getTestStartedTime()) + "</a>";
      }
    }
    return "<a class=\"pentagonTestMessagePass\" href=\"pentagon?" + PentagonServlet.PARAM_TEST_CONDUCTED_ID + "="
        + testMessage.getTestSection().getTestConducted().getTestConductedId() + "&" + PentagonServlet.PARAM_TEST_MESSAGE_ID + "="
        + testMessage.getTestMessageId() + "\">" + testMessage.getTestCaseDescription() + "</a>";
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
      out.println("    <th class=\"pentagon\">Response Type</th>");
      out.println("    <th class=\"pentagon\">VXU and RSP Results</th>");
      out.println("    <th class=\"pentagon\">Test Case Description</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultQueryType() + "</td>");
        out.println("    <td class=\"pentagon\">" + testMessage.getResultStoreStatusForDisplay() + "</td>");
        out.println("    <td class=\"pentagon\"><a class=\"" + classString + "\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
            + testMessage.getTestMessageId() + "', '" + testMessage.getTestCaseDescription() + "');\">" + testMessage.getTestCaseDescription()
            + "</a></td>");
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

  public void printDiscoveryProcessForConstraintOrConflict(PrintWriter out)
  {
    out.println("<h4 class=\"pentagon\">Discovery Process for These Tests</h4>");
    out.println(
        "<p class=\"pentagon\">Local Requirements are gathered by reviewing and documenting your local IG.  These differences are then used to create a set of 2 test cases for each difference. "
            + "One test case with the field populated and one test case without the field populated. In this way the test can determine whether a field: "
            + "<ul class=\"pentagon\">" + "  <li>Must be present</li> " + "  <li>May or may not be present</li> " + "  <li>Must be absent</li> "
            + "</ul></p>");
    out.println(
        "<p class=\"pentagon\">By doing this testing verification can be better understood about R, RE, O, and X requirements and potential difference between IG Documentatin and an actual IIS Interface.</p>");

    out.println("<h4 class=\"pentagon\">Conditional Predicates</h4>");
    out.println(
        "<p class=\"pentagon\">Implementation Guides use a special conditional predicates (e.g., C(R/X), C(R/O)) to indicate status for fields that must be populated when certain conditions are met. "
            + "This testing process AIRA does not directly support testing conditional fields as documented in IG's. Rather, conditional requirements are expressed by two or more tests that together "
            + "express the conditional requirements. For example, PID-25 Birth Order has a status of C(RE/O). When PID-30 is valued Y, indicating the patient is part of a multiple birth, "
            + "then PID-25 has an usage of RE and the birth order must be populated if known. Otherwise birth order is an optional field. In the AIRA testing process this field is represented by two "
            + "different tests: " + "<ul class=\"pentagon\">" + "  <li>PID-25 Single, status O</li> " + "  <li>PID-25 Multiple, status RE</li> "
            + "</ul></p>"
            + "<p class=\"pentagon\">By doing this, AIRA can test the requirements for this field in both situations. Implementation guide authors should continue to use "
            + "conditional predicates but be aware that this report breaks them down in this manner.</p>");

    out.println("<h4 class=\"pentagon\">Special Usage Values</h4>");
    out.println(
        "<p class=\"pentagon\">The usage values (e.g.: R, RE, O, X) indicate the ideal and the intended use of the field but does not capture certain aspects of "
            + "implemented systems that are sometimes documented in the implementation guide and which are certainly noticed when interacting with "
            + "real HL7 interfaces. To capture some of these concepts the AIRA testing process has invented the following temporary status codes: "
            + "<ul class=\"pentagon\">"
            + "  <li><b>R* Required, but not enforced:</b> The guide indicates the field is required but if the field is left empty the IIS will still "
            + "accept the message. For example, PID-1 is documented as required by the IIS but the IIS will accept messages with no value in "
            + "PID-1. Please note that IIS may accept a message even if a required field is not valued. The standard only indicates that an IIS "
            + "\"shall\" return an error (which can also be a warning) when a required field is empty. While it is recommended that the IIS at "
            + "least indicate that required fields were missing, the IIS is free to continue to accept part or all of the data that was sent.</li>"
            + "  <li><b>R! Required, and enforced even though it is contained in an RE or O element:</b> The testing process assumes that required "
            + "elements of required, but may be empty or optional fields can actually be left empty and the message will be accepted. (For example, "
            + "if the IIS requires the mother's maiden first name in PID-6 but PID-6 is RE, and the sender only submits the mother's maiden last "
            + "name, the IIS is expected to continue processing the rest of the message. If however, the message is not accepted when this field is "
            + "empty is empty is required as R! to indicate that the requirement for the field to be present does not follow the expected hierarchy. "
            + "Submitters should be careful to include this required field if any part of the containing element is sent, or otherwise leave the "
            + "entire field empty.</li>"
            + "  <li><b>RE* Required, but may be empty and is not read:</b> The guide indicates the field should be valued if known, but that the IIS is "
            + "not reading this field. For example, PID-22 Ethnic Group is documented as required if known by the IIS, but the IIS has not yet "
            + "implemented support for reading it. The IIS may or may not have plans to implement the field in the future.</li>"
            + "  <li><b>O* Optional, and is not read:</b> The guide indicates that the field may be valued but the IIS does not read the field. For "
            + "example, the PID-15 Primary Language field is documented as optional by the IIS but the IIS does not read this field. The data "
            + "may be sent but the data will never be recorded by the IIS.</li>"
            + "  <li><b>X* Not supported, but not enforced:</b> The guide indicates that a field must not be valued but the IIS will accept the message "
            + "even if it is valued. For example, PID-2 Patienet ID is documented as not supported, by the IIS, but the IIS will not generate "
            + "an error if PID-2 is valued. Please note that HL7 standards indicate that an IIS \"may\" generate an error if a not supported "
            + "field is valued. This means not supported fields are valued.</li>"
            + "  <li><b>[R], [RE], [O], [X]:</b> The brackets indicate the requirement was not documented at the local level and has been assumed from "
            + "the national level for the purposes of testing. IIS are not expected to document all requirements at the local level, in fact "
            + "IIS have been encouraged to keep local documentation to a minimum.</li>" + "</ul></p>");

  }
}
