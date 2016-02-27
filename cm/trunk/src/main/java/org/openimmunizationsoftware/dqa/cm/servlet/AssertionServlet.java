package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.AssertionField;
import org.openimmunizationsoftware.dqa.tr.model.AssertionIdentified;

public class AssertionServlet extends HomeServlet
{
  public static final String PARAM_ASSERTION_FIELD_ID = "assertionFieldId";
  public static final String PARAM_TEST_TYPE = "testType";
  public static final String PARAM_FILTER_BY = "filterBy";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    if (userSession.getUser() == null || userSession.getUser().getApplicationUser() == null
        || !userSession.getUser().getApplicationUser().getApplication().isApplicationAart() || !userSession.isAdmin())
    {
      sendToHome(req, resp);
      return;
    }
    try
    {
      createHeader(webSession);
      String assertionFieldIdString = req.getParameter(PARAM_ASSERTION_FIELD_ID);
      String testType = req.getParameter(PARAM_TEST_TYPE);
      if (testType == null)
      {
        testType = "update";
      }
      if (assertionFieldIdString == null)
      {

        String filterBy = req.getParameter("filterBy");
        if (filterBy != null && filterBy.equals(""))
        {
          filterBy = null;
        }
        SQLQuery query = dataSession.createSQLQuery("SELECT af.assertion_description, af.assertion_field_id, count(*) "
            + "  FROM test_conducted tc, pentagon_report pr, assertion_identified ai, assertion_field af "
            + "  WHERE tc.latest_test = 'Y' AND pr.test_conducted_id = tc.test_conducted_id AND ai.pentagon_report_id = pr.pentagon_report_id "
            + "      AND af.assertion_field_id = ai.assertion_field_id AND ai.test_type = ? AND ai.assertion_result = 'error' "
            + (filterBy == null ? "" : "AND af.assertion_description LIKE ? ") + "      group by af.assertion_description order by count(*) desc ");
        query.setParameter(0, testType);
        if (filterBy != null)
        {
          query.setParameter(1, "%" + filterBy + "%");
        }
        if (testType.equals("update"))
        {
          out.println("<h2>Conformance Assertions for Update</h2>");
          out.println("<p><a href=\"assertion?" + PARAM_TEST_TYPE + "=query\">Switch to Query</a></p>");
        } else if (testType.equals("query"))
        {
          out.println("<h2>Conformance Assertions for Query</h2>");
          out.println("<p><a href=\"assertion?" + PARAM_TEST_TYPE + "=update\">Switch to Update</a></p>");
        }
        out.println("<form method=\"GET\" action=\"assertion\">");
        out.println("  <input type=\"text\" name=\"" + PARAM_FILTER_BY + "\" value=\"" + n(req.getParameter(PARAM_FILTER_BY)) + "\"/>");
        out.println("  <input type=\"submit\" name=\"action\" value=\"Filter\"/>");
        out.println("</form>");
        out.println("<table>");
        out.println("  <caption>Conformance Assertions</caption>");
        out.println("  <tr>");
        out.println("    <th>Connection</th>");
        out.println("    <th>Date</th>");
        out.println("  </tr>");
        List assertionDescriptionList = query.list();
        for (Object o : assertionDescriptionList)
        {
          Object[] row = (Object[]) o;
          if (row.length > 2)
          {
            out.println("  <tr>");
            out.println("    <td width=\"600\"><a href=\"assertion?" + PARAM_ASSERTION_FIELD_ID + "=" + row[1] + "&" + PARAM_TEST_TYPE + "="
                + testType + "\">" + row[0] + "</a></td>");
            out.println("    <td>" + row[2] + "</td>");
            out.println("  </tr>");
          }
        }
        out.println("</table>");
      } else
      {
        AssertionField assertionField = (AssertionField) dataSession.get(AssertionField.class, Integer.parseInt(assertionFieldIdString));
        out.println("<table>");
        out.println("  <caption>Conformance Assertion</caption>");
        out.println("  <tr>");
        out.println("    <th>Description</th>");
        out.println("    <td>" + assertionField.getAssertionDescription() + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <th>Type</th>");
        out.println("    <td>" + assertionField.getAssertionType() + "</td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<br/>");
        out.println("<table>");
        out.println("  <caption>Example Test Cases</caption>");
        out.println("  <tr>");
        out.println("    <th>Connection Label</th>");
        out.println("    <th>Count</th>");
        out.println("  </tr>");
        Query query = dataSession.createQuery(
            "from AssertionIdentified where assertionField = ? and testMessage.testSection.testConducted.latestTest = 'Y' and testType = ? order by assertionCount desc");
        query.setParameter(0, assertionField);
        query.setParameter(1, testType);
        List<AssertionIdentified> assertionIdentifiedList = query.list();
        for (AssertionIdentified assertionIdentified : assertionIdentifiedList)
        {
          String connectionLabel = assertionIdentified.getPentagonReport().getTestConducted().getTestParticipant().getConnectionLabel();
          String link = TestReportServlet.createTestLink(assertionIdentified.getTestMessage()); 
          out.println("  <tr>");
          out.println("    <td><a href=\"" + link + "\">" + connectionLabel + "</a></td>");
          out.println("    <td>" + assertionIdentified.getAssertionCount() + "</td>");
          out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br/>");
      }
      createFooter(webSession);
    } catch (Exception e)
    {
      e.printStackTrace();
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }
    createFooter(webSession);
  }
}
