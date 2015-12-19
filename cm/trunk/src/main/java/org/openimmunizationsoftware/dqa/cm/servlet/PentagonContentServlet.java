package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.Comparison;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

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
    out.println(SHOW_DETAIL_CLOSE_BUTTON);

    if (testMessage != null)
    {
      if (testMessage.getTestType().equals("prep") || testMessage.getTestType().equals("update"))
      {
        out.println("<h2 class=\"pentagon\">" + testMessage.getTestCaseDescription() + "</h3>");
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageActual()) + "</pre>");
        if (testMessage.isResultAccepted())
        {
          out.println("<h3 class=\"pentagon\">Accepted</h3>");
        } else
        {
          out.println("<h3 class=\"pentagon\">NOT Accepted</h3>");
        }
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getResultMessageActual()) + "</pre>");
      } else if (testMessage.getTestType().equals("query"))
      {
        out.println("<h2 class=\"pentagon\">" + testMessage.getTestCaseDescription() + "</h3>");
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageActual()) + "</pre>");
        if (testMessage.getResultStoreStatus().equals("a-r") || testMessage.getResultStoreStatus().equals("na-r"))
        {
          out.println("<h3 class=\"pentagon\">Data Returned</h3>");
        } else if (testMessage.getResultStoreStatus().equals("a-nr") || testMessage.getResultStoreStatus().equals("na-nr"))
        {
          out.println("<h3 class=\"pentagon\">Not All Data Returned</h3>");
        } else
        {
          out.println("<h3 class=\"pentagon\">Response</h3>");
        }
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getResultMessageActual()) + "</pre>");
        if (!testMessage.getPrepMessageOriginal().equals(""))
        {
          if (testMessage.getResultStoreStatus().equals("a-r") || testMessage.getResultStoreStatus().equals("a-nr"))
          {
            out.println("<h3 class=\"pentagon\">Original Request Accepted</h3>");
          } else if (testMessage.getResultStoreStatus().equals("na-r") || testMessage.getResultStoreStatus().equals("na-nr"))
          {
            out.println("<h3 class=\"pentagon\">Original Request Not Accepted</h3>");
          } else
          {
            out.println("<h3 class=\"pentagon\">Original Request</h3>");
          }
          out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageOriginal()) + "</pre>");
        }
        Query query = dataSession.createQuery("from Comparison where testMessage = ? order by comparisonField.fieldName");
        query.setParameter(0, testMessage);
        List<Comparison> comparisonList = query.list();
        if (comparisonList.size() > 0)
        {
          out.println("<table class=\"pentagon\">");
          out.println("  <caption>Field Comparison</caption>");
          out.println("  <tr>");
          out.println("    <th>Field</th>");
          out.println("    <th>HL7</th>");
          out.println("    <th>Core Data</th>");
          out.println("    <th>Original Value</th>");
          out.println("    <th>Compare Value</th>");
          out.println("    <th>Status</th>");
          out.println("  </tr>");
          for (Comparison comparison : comparisonList)
          {
            String classString = "";
            if (comparison.getComparisonStatus().equals(RecordServletInterface.VALUE_COMPARISON_STATUS_FAIL))
            {
              classString = " class=\"fail\"";
            } else if (comparison.getComparisonStatus().equals(RecordServletInterface.VALUE_COMPARISON_STATUS_PASS))
            {
              classString = " class=\"pass\"";
            }
            out.println("  <tr>");
            out.println("    <td" + classString + ">" + comparison.getComparisonField().getFieldLabel() + "</td>");
            out.println("    <td" + classString + ">" + comparison.getComparisonField().getFieldName() + "</td>");
            out.println("    <td" + classString + ">" + comparison.getComparisonField().getPriorityLabel() + "</td>");
            out.println("    <td" + classString + ">" + comparison.getValueOriginal() + "</td>");
            out.println("    <td" + classString + ">" + comparison.getValueCompare() + "</td>");
            out.println("    <td" + classString + ">" + comparison.getComparisonStatus() + "</td>");
            out.println("  </tr>");
          }
          out.println("</table>");
        }
      }
    }
    out.close();
  }
}
