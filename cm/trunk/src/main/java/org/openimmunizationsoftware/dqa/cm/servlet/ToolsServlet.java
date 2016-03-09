package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

@SuppressWarnings("serial")
public class ToolsServlet extends HomeServlet
{

  public static final String VIEW_DEFAULT = "default";
  public static final String VIEW_TEST_CASES = "View Test Cases";
  public static final String PARAM_TEST_CASE_CATEGORY = "testCaseCategory";
  public static final String PARAM_TEST_TYPE = "testType";
  public static final String PARAM_TEST_SECTION_TYPE = "testSectionType";

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
    String view = getView(req);
    try
    {
      createHeader(webSession);
      if (view.equals(VIEW_DEFAULT))
      {
        printDefault(out);
      } else if (view.equals(VIEW_TEST_CASES))
      {
        printViewTestCases(req, dataSession, out);
      }
    } catch (Exception e)
    {
      handleError(e, webSession);
    } finally
    {
      createFooter(webSession);
    }
  }

  public void printViewTestCases(HttpServletRequest req, Session dataSession, PrintWriter out) throws UnsupportedEncodingException
  {
    String testType = req.getParameter(PARAM_TEST_TYPE);
    if (testType == null)
    {
      testType = "update";
    }
    String testCaseCategory = req.getParameter(PARAM_TEST_CASE_CATEGORY);
    if (testCaseCategory == null)
    {
      testCaseCategory = "";
    }
    String testSectionType = req.getParameter(PARAM_TEST_SECTION_TYPE);
    if (testSectionType == null)
    {
      testSectionType = "";
    }
    out.println("<h3>Search Test Cases</h3>");
    out.println("<form method=\"GET\" action=\"tools\">");
    out.println("  Test Type:");
    out.println("  <input type=\"radio\" name=\"" + PARAM_TEST_TYPE + "\" value=\"update\"" + (testType.equals("update") ? " checked=\"true\"" : "")
        + "/> Update");
    out.println("  <input type=\"radio\" name=\"" + PARAM_TEST_TYPE + "\" value=\"query\"" + (testType.equals("query") ? " checked=\"true\"" : "")
        + "/> Query<br/>");
    out.println("  Global Test Id: <input type=\"text\" name=\"" + PARAM_TEST_CASE_CATEGORY + "\" value=\"" + testCaseCategory + "\"/>");
    out.println("  Global Test Id: <select name=\"" + PARAM_TEST_SECTION_TYPE + "\"/>");
    out.println("  <br/>");
    out.println("  <input type=\"submit\" name=\"" + PARAM_VIEW + "\" value=\"" + VIEW_TEST_CASES + "\"/>");
    out.println("</form>");
    if (!testCaseCategory.equals(""))
    {
      out.println("<table>");
      out.println("  <caption>Test Messages</caption>");
      out.println("  <tr>");
      out.println("    <th>Connection</th>");
      out.println("    <th>Date</th>");
      out.println("    <th>Result</th>");
      out.println("  </tr>");
      Query query = dataSession.createQuery(
          "from TestMessage where testCaseCategory = ? and testSection.testConducted.latestTest = 'Y' order by testSection.testConducted.testParticipant.connectionLabel");
      query.setParameter(0, testCaseCategory);
      @SuppressWarnings("unchecked")
      List<TestMessage> testMessageList = query.list();
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      for (TestMessage testMessage : testMessageList)
      {
        TestConducted testConducted = testMessage.getTestSection().getTestConducted();
        String link = createTestLink(testMessage);
        out.println("  <tr>");
        out.println("    <td><a href=\"" + link + "\">" + testConducted.getTestParticipant().getConnectionLabel() + "</a></td>");
        out.println("    <td>" + sdf.format(testConducted.getTestStartedTime()) + "</td>");
        out.println("    <td>" + testMessage.getResultStatus() + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public void printDefault(PrintWriter out)
  {
    out.println("<h3>Tools</h3>");
    out.println("<ul>");
    out.println("  <li><a href=\"testReport?" + HomeServlet.PARAM_VIEW + "=" + TestReportServlet.VIEW_MAP + "\">Old Dashboard</a></li>");
    out.println("  <li><a href=\"assertion\">Conformance Assertions</a></li>");
    out.println("  <li><a href=\"tools?" + HomeServlet.PARAM_VIEW + "=" + VIEW_TEST_CASES + "\">Search Test Cases</a></li>");
    out.println("</ul>");
  }

  public String getView(HttpServletRequest req)
  {
    String view = req.getParameter(PARAM_VIEW);
    if (view == null)
    {
      view = VIEW_DEFAULT;
    }
    return view;
  }

  public String createTestLink(TestMessage testMessage) throws UnsupportedEncodingException
  {
    String link = "testReport?" + TestReportServlet.PARAM_VIEW + "=" + URLEncoder.encode(testMessage.getTestSection().getTestSectionType(), "UTF-8")
        + "&" + TestReportServlet.PARAM_TEST_MESSAGE_ID + "=" + testMessage.getTestMessageId() + "&" + TestReportServlet.PARAM_TEST_CONDUCTED_ID + "="
        + testMessage.getTestSection().getTestConducted().getTestConductedId();
    return link;
  }
}
