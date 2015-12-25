package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.Assertion;
import org.openimmunizationsoftware.dqa.tr.model.Comparison;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
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
    out.println(SHOW_DETAIL_CLOSE_BUTTON);

    if (testMessage != null)
    {
      if (testMessage.getTestType().equals("prep") || testMessage.getTestType().equals("update"))
      {
        out.println("<h2 class=\"pentagon\">" + testMessage.getTestCaseDescription() + "</h3>");
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageActual(), profileUsage) + "</pre>");
        if (testMessage.isResultAccepted())
        {
          out.println("<h3 class=\"pentagon\">Accepted</h3>");
        } else
        {
          out.println("<h3 class=\"pentagon\">NOT Accepted</h3>");
        }
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getResultMessageActual(), profileUsage) + "</pre>");
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

      } else if (testMessage.getTestType().equals("query"))
      {
        out.println("<h2 class=\"pentagon\">" + testMessage.getTestCaseDescription() + "</h3>");
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageActual(), profileUsage) + "</pre>");
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
        out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getResultMessageActual(), profileUsage) + "</pre>");
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
          out.println("<pre class=\"pentagon\">" + TestReportServlet.addHovers(testMessage.getPrepMessageOriginal(), profileUsage) + "</pre>");
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
