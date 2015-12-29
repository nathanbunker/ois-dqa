package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class UFCodedValues extends PentagonBox
{
  public UFCodedValues() {
    super(BOX_NAME_UF_CODED_VALUES);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The CDC Implementation Guide defines coded values that must be supported by conformant systems. "
        + "This section tests whether these coded values can be placed in messages without causing messages to NOT be accepted. "
        + "It is important to note that the IIS is not being tested to see if the actual coded value is stored and supported by the IIS "
        + "but rather that a sending system may use this code when submitting data. </p>");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    if (score < 100)
    {
      out.println("<h3 class=\"pentagon\">Fail - Message Was Not Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE);
      query.setParameter(1, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFailCodes(out, testMessageList);
    }
    if (score > 0)
    {
      out.println("<h3 class=\"pentagon\">Pass - Coded Value Accepted</h3>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE);
      query.setParameter(1, testConducted);
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPassCodes(out, testMessageList);
    }
  }

  public void printTestMessageListPassCodes(PrintWriter out, List<TestMessage> testMessageList)
  {
    if (testMessageList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Code Table</th>");
      out.println("    <th class=\"pentagon\">Coded Values Accepted</th>");
      out.println("  </tr>");
      String tableNamePrevious = "";
      for (TestMessage testMessage : testMessageList)
      {
        String tableValue = testMessage.getTestCaseDescription();
        String tableName = tableValue;
        {
          int pos = tableValue.indexOf(" is ");
          if (pos > 0)
          {
            tableName = tableValue.substring(0, pos);
            tableValue = tableValue.substring(pos + 4);
          }
        }
        String testMessageLink = createLink(testMessage, tableValue.trim());
        if (!tableName.equals(tableNamePrevious))
        {
          if (!tableNamePrevious.equals(""))
          {
            out.println("</td>");
            out.println("  </tr>");
          }
          out.println("  <tr class=\"pentagon\">");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">Pass</td>");
          out.println("    <td class=\"pentagon\">" + tableName + "</td>");
          out.println("    <td class=\"pentagon\">");
          out.println(testMessageLink);
        } else
        {
          out.println(", " + testMessageLink);
        }
        tableNamePrevious = tableName;
      }
      out.println("</table>");
    }
  }

  
  public void printTestMessageListFailCodes(PrintWriter out, List<TestMessage> testMessageList)
  {
    if (testMessageList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Code Table</th>");
      out.println("    <th class=\"pentagon\">Coded Values Not Accepted</th>");
      out.println("  </tr>");
      String tableNamePrevious = "";
      for (TestMessage testMessage : testMessageList)
      {
        String tableValue = testMessage.getTestCaseDescription();
        String tableName = tableValue;
        {
          int pos = tableValue.indexOf(" is ");
          if (pos > 0)
          {
            tableName = tableValue.substring(0, pos);
            tableValue = tableValue.substring(pos + 4);
          }
        }
        String testMessageLink = createLink(testMessage, tableValue.trim());
        if (!tableName.equals(tableNamePrevious))
        {
          if (!tableNamePrevious.equals(""))
          {
            out.println("</td>");
            out.println("  </tr>");
          }
          out.println("  <tr class=\"pentagon\">");
          out.println("    <td class=\"pentagon\" style=\"text-align: center;\">Fail</td>");
          out.println("    <td class=\"pentagon\">" + tableName + "</td>");
          out.println("    <td class=\"pentagon\">");
          out.println(testMessageLink);
        } else
        {
          out.println(", " + testMessageLink);
        }
        tableNamePrevious = tableName;
      }
      out.println("</table>");
    }
  }
  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is calculated as the percentage of messages that returned a postive response from the IIS. </p>");
    out.println("<h4 class=\"pentagon\">How To Improve Score</h4>");
    out.println("<p class=\"pentagon\">Ensure that coded values defined in the national guide are recognized and properly handled by the IIS. "
        + "In general an IIS should not reject a message when a valid code is sent, even if the IIS is not prepared to save the data. "
        + "In most cases the IIS can accept the other data and return a positive acknowledgement, with minor issues listed with a Warning or "
        + "Informational error.   </p>");
  }

  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    pentagonReport.setScoreUFCodedValues(testConducted.getScoreCoded());
  }
}
