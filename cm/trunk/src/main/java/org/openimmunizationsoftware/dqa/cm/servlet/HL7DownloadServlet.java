package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class HL7DownloadServlet extends HomeServlet
{

  public static final String PARAM_TEST_CONDUCTED_ID = "testConductedId";
  public static final String PARAM_TEST_SECTION_ID = "testSectionId";
  public static final String PARAM_TYPE = "type";
  public static final String TYPE_UPDATES = "updates";
  public static final String TYPE_ACKS = "acks";
  public static final String TYPE_QUERIES = "queries";
  public static final String TYPE_RESPONSES = "responses";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setupSkinny(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    userSession.setOut(new PrintWriter(resp.getOutputStream()));
    if (!userSession.isLoggedIn())
    {
      return;
    }
    PrintWriter out = userSession.getOut();
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String testConductedIdString = req.getParameter(PARAM_TEST_CONDUCTED_ID);
      String testSectionIdString = req.getParameter(PARAM_TEST_SECTION_ID);
      String type = req.getParameter(PARAM_TYPE);
      TestConducted testConducted = (TestConducted) dataSession.get(TestConducted.class, Integer.parseInt(testConductedIdString));
      boolean anonymize = !testConducted.getTestParticipant().canViewConnectionLabel(userSession);
      resp.setContentType("text/plain");
      if (testSectionIdString != null)
      {
        TestSection testSection = (TestSection) dataSession.get(TestSection.class, Integer.parseInt(testSectionIdString));
        String filename = "Test " + testConducted.getTestConductedId() + " " + testSection.getTestSectionType() + " " + type + " "
            + sdf.format(testConducted.getTestStartedTime()) + ".hl7.txt";
        resp.setHeader("content-disposition", "attachment; filename=\"" + filename + "\"");
        Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = ? order by testPosition");
        query.setParameter(0, testSection);
        if (type.equals(TYPE_UPDATES) || type.equals(TYPE_ACKS))
        {
          query.setParameter(1, "update");
        } else
        {
          query.setParameter(1, "query");
        }
        List<TestMessage> testMessageList = query.list();
        printMessages(out, type, anonymize, testMessageList);
      } else
      {
        String filename = "Test " + testConducted.getTestConductedId() + " " + type + " " + sdf.format(testConducted.getTestStartedTime()) + ".hl7.txt";
        resp.setHeader("content-disposition", "attachment; filename=\"" + filename + "\"");
        Query query = dataSession.createQuery("from TestMessage where testSection.testConducted = ? and testType = ? order by testSection.testSectionType, testPosition");
        query.setParameter(0, testConducted);
        if (type.equals(TYPE_UPDATES) || type.equals(TYPE_ACKS))
        {
          query.setParameter(1, "update");
        } else
        {
          query.setParameter(1, "query");
        }
        List<TestMessage> testMessageList = query.list();
        printMessages(out, type, anonymize, testMessageList);
      }

    } catch (Exception e)
    {
      e.printStackTrace();
      throw new ServletException(e);
    } 
    out.close();
  }

  public void printMessages(PrintWriter out, String type, boolean anonymize, List<TestMessage> testMessageList)
  {
    for (TestMessage testMessage : testMessageList)
    {
      String message = "";
      if (type.equals(TYPE_UPDATES) || type.equals(TYPE_QUERIES))
      {
        message = testMessage.getPrepMessageActual();
      } else if (type.equals(TYPE_ACKS) || type.equals(TYPE_RESPONSES))
      {
        message = testMessage.getResultMessageActual();
      }
      if (anonymize)
      {
        message = PentagonContentServlet.anonymizeHL7(message);
      }
      out.print(message);
    }
  }
}
