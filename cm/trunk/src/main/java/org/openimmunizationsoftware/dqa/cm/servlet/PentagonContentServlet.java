package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
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
    }
    out.close();
  }
}
