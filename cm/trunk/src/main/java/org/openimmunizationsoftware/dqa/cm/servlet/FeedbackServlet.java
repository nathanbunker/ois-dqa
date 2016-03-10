package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.logic.MailManager;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

@SuppressWarnings("serial")
public class FeedbackServlet extends HomeServlet
{

  public static final String PARAM_TEST_CONDUCTED_ID = "testConductedId";
  public static final String PARAM_FEEDBACK_TEXT = "feedbackText";

  public static final String PARAM_BOX_NAME = "boxName";
  public static final String PARAM_TEST_MESSAGE_ID = "testMessageId";

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
        || !userSession.getUser().getApplicationUser().getApplication().isApplicationAart())
    {
      out.println("<p class=\"pentagon\">Your session has expired. Please <a href=\"\">login to</a> start a new session. </p>");
      out.close();
      return;
    }
    try
    {
      TestConducted testConducted = (TestConducted) dataSession.get(TestConducted.class, Integer.parseInt(req.getParameter(PARAM_TEST_CONDUCTED_ID)));
      MailManager mailManager = new MailManager(dataSession);
      StringBuilder message = new StringBuilder();
      message.append("<p>Received feedback from " + userSession.getUser().getUserName() + " while they were looking at test conducted ("
          + testConducted.getTestConductedId() + ") for " + testConducted.getTestParticipant().getConnectionLabel() + ".</p>");
      message.append("<p> " + req.getParameter(PARAM_FEEDBACK_TEXT) + "</p>");
      String boxName = req.getParameter(PARAM_BOX_NAME);
      if (boxName != null && !boxName.equals(""))
      {
        message.append("<p>The user is looking at this box: " + boxName + "</p>");
      }
      String testMessageId = req.getParameter(PARAM_TEST_MESSAGE_ID);
      if (testMessageId != null && !testMessageId.equals(""))
      {
        TestMessage testMessage = (TestMessage) dataSession.get(TestMessage.class, Integer.parseInt(testMessageId));
        message.append("<p>The user is looking at this test message: " + testMessage.getTestCaseCategory() + ": "
            + testMessage.getTestCaseDescription() + " (" + testMessage.getTestMessageId() + ")</p>");
      }
      mailManager.sendEmail("AART Feedback", message.toString(), ERROR_EMAIL);
      out.println("<p class=\"pentagon\">Your message has been sent to the AART technical team. Thank you for your help! </p>");
    } catch (Exception e)
    {
      userSession.setHeaderCreated(true);
      handleError(e, webSession);
    } finally
    {
      out.close();
    }

  };
}
