package org.openimmunizationsoftware.dqa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.IssueFound;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.MessageReceivedManager;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.parse.VaccinationUpdateParserHL7;
import org.openimmunizationsoftware.dqa.validate.Validator;


public class IncomingServlet extends HttpServlet
{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    PrintWriter out = new PrintWriter(resp.getOutputStream());
    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>DQA Incoming Interface</title>");
    out.println("  </head>");
    out.println("  <body>");
    out.println("    <h1>DQA Incoming Interface</h1>");
    out.println("    <form action=\"in\" method=\"POST\">");
    out.println("      <table border=\"0\">");
    out.println("        <tr>");
    out.println("          <td><code>USERID</code></td>");
    out.println("          <td><input name=\"USERID\" type=\"text\" value=\"\"></td>");
    out.println("        </tr>");
    out.println("        <tr>");
    out.println("          <td><code>PASSWORD</code></td>");
    out.println("          <td><input name=\"PASSWORD\" type=\"text\" value=\"\"></td>");
    out.println("        </tr>");
    out.println("        <tr>");
    out.println("          <td><code>FACILITYID</code></td>");
    out.println("          <td><input name=\"FACILITYID\" type=\"text\" value=\"\"></td>");
    out.println("        </tr>");
    out.println("      </table>");
    out.println("      <table border=\"0\">");
    out.println("        <tr>");
    out.println("          <td><code>MESSAGEDATA</code></td>");
    out.println("        </tr>");
    out.println("        <tr>");
    out.println("          <td><textarea name=\"MESSAGEDATA\" cols=\"120\" rows=\"15\"></textarea></td>");
    out.println("        </tr>");
    out.println("      </table>");
    out.println("      <input type=\"checkbox\" name=\"DEBUG\" value=\"T\">Debug");
    out.println("      <input name=\"submit\" value=\"Submit\" type=\"submit\">");
    out.println("    </form>");
    out.println("  </body>");
    out.println("</html>");
    out.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    // String userid = req.getParameter("USERID");
    // String password = req.getParameter("PASSWORD");
    String facilityid = req.getParameter("FACILITYID");
    boolean debug = req.getParameter("DEBUG") != null;
    resp.setContentType("text/plain");
    PrintWriter out = new PrintWriter(resp.getOutputStream());

    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    Organization organization = (Organization) session.get(Organization.class, Integer.parseInt(facilityid));
    SubmitterProfile profile = organization.getPrimaryProfile();

    String messageData = req.getParameter("MESSAGEDATA");
    process(debug, out, session, profile, messageData);
    out.close();
    session.flush();
    session.close();
  }

  public static void process(boolean debug, PrintWriter out, Session session, SubmitterProfile profile, String messageData)
      throws IOException
  {
    VaccinationUpdateParserHL7 parser = new VaccinationUpdateParserHL7(profile);
    StringReader stringReader = new StringReader(messageData);
    BufferedReader in = new BufferedReader(stringReader);
    String line = null;
    StringBuilder sb = new StringBuilder();
    int messageCount = 0;

    while ((line = in.readLine()) != null)
    {
      if (line.startsWith("MSH"))
      {
        if (sb.length() > 0)
        {
          messageCount++;
          processMessage(out, parser, sb, debug, messageCount, profile, session);
        }
        sb.setLength(0);
      } else if (line.startsWith("FHS") || line.startsWith("BHS") || line.startsWith("BTS") || line.startsWith("FTS"))
      {
        continue;
      }
      sb.append(line);
      sb.append("\r");
    }
    if (sb.length() > 0)
    {
      messageCount++;
      processMessage(out, parser, sb, debug, messageCount, profile, session);
    }
  }

  private static void processMessage(PrintWriter out, VaccinationUpdateParserHL7 parser, StringBuilder sb, boolean debug, int messageCount,
      SubmitterProfile profile, Session session)
  {
    try
    {
      Transaction tx = session.beginTransaction();
      MessageReceived messageReceived = new MessageReceived();
      messageReceived.setProfile(profile);
      messageReceived.setRequestText(sb.toString());
      parser.createVaccinationUpdateMessage(messageReceived);
      if (!messageReceived.hasErrors())
      {
        Validator validator = new Validator(profile, session);
        validator.validateVaccinationUpdateMessage(messageReceived);
      }
      
      String ackMessage = parser.makeAckMessage(messageReceived);
      messageReceived.setResponseText(ackMessage);
      messageReceived.setIssueAction(IssueAction.ACCEPT);
      MessageReceivedManager.saveMessageReceived(profile, messageReceived, session);
      out.print(ackMessage);
      if (debug)
      {
        try
        {
          out.print("-- DEBUG START -------------------------------------------------------\r");
          out.print("Processed message: " + messageCount + "\r");
          List<IssueFound> issuesFound = messageReceived.getIssuesFound();
          out.print("Errors:\r");
          for (IssueFound issueFound : issuesFound)
          {
            if (!issueFound.getIssueNegate() && issueFound.isError())
            {
              out.print("  + ");
              out.print(issueFound.getIssue().getDisplayText());
              if (issueFound.positionId > 1)
              {
                out.print(" (position #" + issueFound.positionId + ")");
              }
              out.print("\r");
            }
          }
          out.print("Warnings:\r");
          for (IssueFound issueFound : issuesFound)
          {
            if (!issueFound.getIssueNegate() && issueFound.isWarn())
            {
              out.print("  + ");
              out.print(issueFound.getIssue().getDisplayText());
              if (issueFound.positionId > 1)
              {
                out.print(" (position #" + issueFound.positionId + ")");
              }
              out.print("\r");
            }
          }
          out.print("Information:\r");
          for (IssueFound issueFound : issuesFound)
          {
            if (!issueFound.getIssueNegate() && issueFound.isAccept())
            {
              out.print("  + ");
              out.print(issueFound.getIssue().getDisplayText());
              if (issueFound.positionId > 1)
              {
                out.print(" (position #" + issueFound.positionId + ")");
              }
              out.print("\r");
            }
          }
          out.print("Skip:\r");
          for (IssueFound issueFound : issuesFound)
          {
            if (!issueFound.getIssueNegate() && issueFound.isSkip())
            {
              out.print("  + ");
              out.print(issueFound.getIssue().getDisplayText());
              if (issueFound.positionId > 1)
              {
                out.print(" (position #" + issueFound.positionId + ")");
              }
              out.print("\r");
            }
          }
          out.print("Bean Printout: \r");
          printBean(out, messageReceived, "  ");
          out.print("-- DEBUG END ---------------------------------------------------------\r");
        } catch (Exception e)
        {
          e.printStackTrace(out);
        }
      }
      tx.commit();
    } catch (Exception exception)
    {
      String ackMessage = "MSH|^~\\&|||||201105231008000||ACK^|201105231008000|P|2.3.1|\r"
          + "MSA|AE|TODO|Exception occurred: " + exception.getMessage() + "|\r";
      out.print(ackMessage);
      if (debug)
      {
        exception.printStackTrace(out);
      }
      exception.printStackTrace();
    }
  }

  private static void printBean(PrintWriter out, Object object, String indent) throws IllegalAccessException,
      InvocationTargetException
  {
    Method[] methods = object.getClass().getMethods();
    Arrays.sort(methods, new Comparator<Method>() {
      public int compare(Method o1, Method o2)
      {
        return o1.getName().compareTo(o2.getName());
      }
    });
    for (Method method : methods)
    {
      if (method.getName().startsWith("get") && !method.getName().equals("getClass")
          && !method.getName().equals("getMessageReceived") 
          && !method.getName().equals("getProfile")
          && !method.getName().equals("getIssuesFound")
          && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0)
      {
        Object returnValue = method.invoke(object);
        String fieldName = method.getName().substring(3);
        out.print(indent);
        out.print(fieldName);
        if (returnValue == null)
        {
          out.print("\r");
        } else if (method.getReturnType() == String.class)
        {
          if (!((String) returnValue).equals(""))
          {
            out.print(" = '");
            out.print(returnValue);
            out.print("'");
          }
          out.print("\r");
        } else if (method.getReturnType() == Date.class)
        {
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
          out.print(" = ");
          out.print(sdf.format((Date) returnValue));
          out.print("\r");
        } else if (method.getReturnType() == List.class)
        {
          List list = (List) returnValue;
          for (int i = 0; i < list.size(); i++)
          {
            if (i > 0)
            {
              out.print(indent);
              out.print(fieldName);
            }
            out.print(" #");
            out.print(i + 1);
            out.print("\r");
            printBean(out, list.get(i), indent + "  ");
          }
        } else
        {
          out.print("\r");
          printBean(out, returnValue, indent + "  ");
        }
      }
    }
  }
}
