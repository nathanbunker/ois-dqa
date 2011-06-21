package org.openimmunizationsoftware.dqa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.openimmunizationsoftware.dqa.manager.FileImportManager;
import org.openimmunizationsoftware.dqa.manager.MessageReceivedManager;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.parse.VaccinationUpdateParserHL7;
import org.openimmunizationsoftware.dqa.validate.Validator;

public class IncomingServlet extends HttpServlet
{
  
  private FileImportManager fileImportManager = null;
  
  @Override
  public void init() throws ServletException
  {
    fileImportManager = FileImportManager.getFileImportManager();
  }
  
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
    out.println("    ");
    
    if (fileImportManager.isKeepRunning())
    {
      out.println("      <p>File import manager is running.<p>");
    }
    if (fileImportManager.getLastException() != null)
    {
      out.println("      <p>Last Exception</p>");
      out.println("<pre>");
      fileImportManager.getLastException().printStackTrace(out);
      out.println("</pre>");
    }
    out.println("      <p>Internal Log</p>");
    out.println("<pre>");
    out.print(fileImportManager.getInternalLog());
    out.println("</pre>");
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

  public static void process(boolean debug, PrintWriter out, Session session, SubmitterProfile profile,
      String messageData) throws IOException
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

  private static void processMessage(PrintWriter out, VaccinationUpdateParserHL7 parser, StringBuilder sb,
      boolean debug, int messageCount, SubmitterProfile profile, Session session)
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
          boolean first = true;
          for (IssueFound issueFound : issuesFound)
          {
            if (!issueFound.getIssueNegate() && issueFound.isError())
            {
              if (first)
              {
                out.print("Errors:\r");
                first = false;
              }
              printIssueFound(out, issueFound);
            }
          }
          first = true;
          for (IssueFound issueFound : issuesFound)
          {
            if (!issueFound.getIssueNegate() && issueFound.isWarn())
            {
              if (first)
              {
                out.print("Warnings:\r");
                first = false;
              }
              printIssueFound(out, issueFound);
            }
          }
          first = true;
          for (IssueFound issueFound : issuesFound)
          {
            if (!issueFound.getIssueNegate() && issueFound.isSkip())
            {
              if (first)
              {
                out.print("Skip:\r");
                first = false;
              }
              printIssueFound(out, issueFound);
            }
          }
          out.print("Message Data: \r");
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

  private static void printIssueFound(PrintWriter out, IssueFound issueFound)
  {
    out.print("  + ");
    out.print(issueFound.getDisplayText());
    out.print("\r");
  }

  private static List<String> printBean(PrintWriter out, Object object, String indent) throws IllegalAccessException,
      InvocationTargetException
  {
    List<String> thisPrinted = new ArrayList<String>();
    List<String> subPrinted = new ArrayList<String>();
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
          && !method.getName().equals("getMessageReceived") && !method.getName().equals("getProfile")
          && !method.getName().equals("getRequestText") && !method.getName().equals("getResponseText")
          && !method.getName().equals("getIssuesFound") && !method.getReturnType().equals(Void.TYPE)
          && method.getParameterTypes().length == 0)
      {
        Object returnValue = method.invoke(object);
        String fieldName = method.getName().substring(3);
        if (returnValue == null || subPrinted.contains(fieldName))
        {
          // do nothing
        } else if (method.getReturnType() == String.class)
        {
          if (!((String) returnValue).equals(""))
          {
            out.print(indent);
            out.print(fieldName);
            out.print(" = '");
            out.print(returnValue);
            out.print("'");
            out.print("\r");
            thisPrinted.add(fieldName);
          }
        } else if (method.getReturnType() == Date.class)
        {
          out.print(indent);
          out.print(fieldName);
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
          out.print(" = ");
          out.print(sdf.format((Date) returnValue));
          out.print("\r");
          thisPrinted.add(fieldName);
        } else if (method.getReturnType() == List.class)
        {
          List list = (List) returnValue;
          for (int i = 0; i < list.size(); i++)
          {
            out.print(indent);
            out.print(fieldName);
            out.print(" #");
            out.print(i + 1);
            out.print("\r");
            printBean(out, list.get(i), indent + "  ");
          }
        } else
        {
          out.print(indent);
          out.print(fieldName);
          out.print("\r");
          List<String> returnPrints = printBean(out, returnValue, indent + "  ");
          for (String returnPrint : returnPrints)
          {
            subPrinted.add(fieldName + returnPrint);
          }
        }
      }
    }
    return thisPrinted;
  }

}
