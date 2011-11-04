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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.db.model.BatchActions;
import org.openimmunizationsoftware.dqa.db.model.BatchCodeReceived;
import org.openimmunizationsoftware.dqa.db.model.BatchIssues;
import org.openimmunizationsoftware.dqa.db.model.BatchType;
import org.openimmunizationsoftware.dqa.db.model.BatchVaccineCvx;
import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.CodeTable;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.IssueFound;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.SubmitStatus;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.CodesReceived;
import org.openimmunizationsoftware.dqa.manager.FileImportManager;
import org.openimmunizationsoftware.dqa.manager.ManagerThread;
import org.openimmunizationsoftware.dqa.manager.MessageBatchManager;
import org.openimmunizationsoftware.dqa.manager.MessageReceivedManager;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.manager.WeeklyBatchManager;
import org.openimmunizationsoftware.dqa.manager.WeeklyExportManager;
import org.openimmunizationsoftware.dqa.parse.VaccinationUpdateParserHL7;
import org.openimmunizationsoftware.dqa.validate.Validator;

public class IncomingServlet extends HttpServlet
{

  protected static FileImportManager fileImportManager = null;
  protected static WeeklyBatchManager weeklyBatchManager = null;
  protected static WeeklyExportManager weeklyExportManager = null;

  private PrintWriter out = null;
  private MessageBatchManager messageBatchManager = null;

  @Override
  public void init() throws ServletException
  {
    fileImportManager = FileImportManager.getFileImportManager();
    weeklyBatchManager = WeeklyBatchManager.getWeeklyBatchManager();
    weeklyExportManager = WeeklyExportManager.getWeeklyExportManager();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    out = new PrintWriter(resp.getOutputStream());
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

    printManagerThread(fileImportManager);
    printManagerThread(weeklyBatchManager);
    printManagerThread(weeklyExportManager);
    out.println("    <hr>");
    out.println("    <p>Version 0.5</p>");
    out.println("  </body>");
    out.println("</html>");
    out.close();
    out = null;
  }

  private void printManagerThread(ManagerThread mt)
  {
    if (mt.isKeepRunning())
    {
      out.println("      <p>" + mt.getLabel() + " is running.<p>");
    }
    if (mt.getLastException() != null)
    {
      out.println("      <p>Last Exception</p>");
      out.println("<pre>");
      mt.getLastException().printStackTrace(out);
      out.println("</pre>");
    }
    out.println("      <p>Internal Log</p>");
    out.println("<pre>");
    out.print(mt.getInternalLog());
    out.println("</pre>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    String profileCode = req.getParameter("USERID");
    String accessKey = req.getParameter("PASSWORD");
    String profileId = req.getParameter("FACILITYID");
    boolean debug = req.getParameter("DEBUG") != null;
    resp.setContentType("text/plain");
    out = new PrintWriter(resp.getOutputStream());

    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    if (profileId == null || profileId.equals(""))
    {
      profileId = "1";
    }
    SubmitterProfile profile = null;
    String accessDenied = null;
    Query query = session.createQuery("from SubmitterProfile where profileCode = ? and accessKey = ?");
    query.setParameter(0, profileCode);
    query.setParameter(1, accessKey);
    List<SubmitterProfile> submitterProfiles = query.list();
    if (submitterProfiles.size() == 0)
    {
      accessDenied = "Authorization failed, invalid USERID or PASSWORD";
    } else
    {
      profile = submitterProfiles.get(0);
    }
    if (accessDenied == null)
    {
      String messageData = req.getParameter("MESSAGEDATA");
      processStream(debug, session, profile, messageData);
    } else
    {
      out.println(accessDenied);
    }
    if (debug)
    {
      printMessageBatch();
      out.print("\r");
      out.print("\r");
      CodesReceived cr = CodesReceived.getCodesReceived(profile, session);
      CodesReceived masterCr = CodesReceived.getCodesReceived();
      for (CodeTable codeTable : cr.getCodeTableList())
      {
        List<CodeReceived> codesReceived = cr.getCodesReceived(codeTable);
        if (codesReceived.size() > 0)
        {
          out.print("-- " + padSlash(codeTable.getTableLabel() + " ", 92) + "\r");
          out.print("\r");
          out.print("VALUES RECEIVED\r");
          out.print(pad("value", 20));
          out.print(pad("label", 30));
          out.print(pad("use instead", 20));
          out.print(pad("status", 15));
          out.print(pad("count", 7));
          out.print("\r");
          for (CodeReceived codeReceived : codesReceived)
          {
            out.print(pad(codeReceived.getReceivedValue(), 20));
            out.print(pad(codeReceived.getCodeLabel(), 30));
            if (codeReceived.getCodeValue() == null
                || (codeReceived.getCodeValue().equals(codeReceived.getReceivedValue())))
            {
              out.print(pad("", 20));
            } else
            {
              out.print(pad(codeReceived.getCodeValue(), 20));
            }
            out.print(pad(codeReceived.getCodeStatus().getCodeLabel(), 15));
            out.print(pad(String.valueOf(codeReceived.getReceivedCount()), 7));
            out.print("\r");
          }
          codesReceived = masterCr.getCodesReceived(codeTable);
          out.print("\r");
          out.print("MASTER VALUE LIST\r");
          out.print(pad("value", 20));
          out.print(pad("label", 30));
          out.print(pad("use instead", 20));
          out.print(pad("status", 15));
          out.print("\r");

          for (CodeReceived codeReceived : codesReceived)
          {
            out.print(pad(codeReceived.getReceivedValue(), 20));
            out.print(pad(codeReceived.getCodeLabel(), 30));
            if (codeReceived.getCodeValue() == null
                || (codeReceived.getCodeValue().equals(codeReceived.getReceivedValue())))
            {
              out.print(pad("", 20));
            } else
            {
              out.print(pad(codeReceived.getCodeValue(), 20));
            }
            out.print(pad(codeReceived.getCodeStatus().getCodeLabel(), 15));
            out.print("\r");
          }
          out.print("\r");
        }
      }
    }
    out.close();
    out = null;
    session.flush();
    session.close();
  }

  private void printMessageBatch()
  {
    MessageBatch mb = messageBatchManager.getMessageBatch();
    out.print("\r");
    out.print("\r");
    out.print("Message Batch Summary: \r");
    out.print("Message Count:       " + mb.getMessageCount() + "\r");
    out.print("Patient Count:       " + mb.getMessageCount() + "\r");
    out.print("Vaccination      \r");
    out.print(" + Administered:     " + mb.getVaccinationAdministeredCount() + "\r");
    out.print(" + Historical:       " + mb.getVaccinationHistoricalCount() + "\r");
    out.print(" + Not Administered: " + mb.getVaccinationNotAdministeredCount() + "\r");
    out.print(" + Deleted:          " + mb.getVaccinationDeleteCount() + "\r");
  }

  public void processStream(boolean debug, Session session, SubmitterProfile profile, String messageData)
      throws IOException
  {
    VaccinationUpdateParserHL7 parser = new VaccinationUpdateParserHL7(profile);
    StringReader stringReader = new StringReader(messageData);
    BufferedReader in = new BufferedReader(stringReader);
    String line = null;
    StringBuilder sb = new StringBuilder();
    messageBatchManager = new MessageBatchManager("Realtime HTTPS", BatchType.SUBMISSION, profile);

    while ((line = in.readLine()) != null)
    {
      if (line.startsWith("MSH"))
      {
        if (sb.length() > 0)
        {
          processMessage(parser, sb, debug, profile, session);
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
      processMessage(parser, sb, debug, profile, session);
    }
    Transaction tx = session.beginTransaction();
    messageBatchManager.close();
    session.save(messageBatchManager.getMessageBatch());
    tx.commit();
  }
  
  private void saveAndCloseBatch(Session session)
  {
    messageBatchManager.close();
    messageBatchManager.getMessageBatch().setSubmitStatus(SubmitStatus.QUEUED);
    session.save(messageBatchManager.getMessageBatch());
    MessageBatch messageBatch = messageBatchManager.getMessageBatch();
    for (BatchIssues batchIssues : messageBatch.getBatchIssuesMap().values())
    {
      session.save(batchIssues);
    }
    for (BatchActions batchActions : messageBatch.getBatchActionsMap().values())
    {
      session.save(batchActions);
    }
    for (BatchCodeReceived batchCodeReceived : messageBatch.getBatchCodeReceivedMap().values())
    {
      session.save(batchCodeReceived);
    }
    for (BatchVaccineCvx batchVaccineCvx : messageBatch.getBatchVaccineCvxMap().values())
    {
      session.save(batchVaccineCvx);
    }
  }



  private void processMessage(VaccinationUpdateParserHL7 parser, StringBuilder sb, boolean debug,
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
      messageBatchManager.registerProcessedMessage(messageReceived);

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
          out.print("Processed message: " + messageBatchManager.getMessageBatch().getMessageCount() + "\r");
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
              printIssueFound(issueFound);
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
              printIssueFound(issueFound);
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
              printIssueFound(issueFound);
            }
          }
          out.print("Message Data: \r");
          printBean(messageReceived, "  ");
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

  private void printIssueFound(IssueFound issueFound)
  {
    out.print("  + ");
    out.print(issueFound.getDisplayText());
    out.print("\r");
  }

  private List<String> printBean(Object object, String indent) throws IllegalAccessException, InvocationTargetException
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
            printBean(list.get(i), indent + "  ");
          }
        } else if (method.getReturnType() == CodeTable.Type.class)
        {
          // don't process
        } else
        {
          out.print(indent);
          out.print(fieldName);
          out.print("\r");
          List<String> returnPrints = printBean(returnValue, indent + "  ");
          for (String returnPrint : returnPrints)
          {
            subPrinted.add(fieldName + returnPrint);
          }
        }
      }
    }
    return thisPrinted;
  }

  private static final String PAD = "                                                                                                          ";

  private static String pad(String s, int size)
  {
    s += PAD;
    return s.substring(0, size - 1) + " ";
  }

  private static final String PAD_SLASH = "-----------------------------------------------------------------------------------------------------";

  private static String padSlash(String s, int size)
  {
    s += PAD_SLASH;
    return s.substring(0, size - 1) + "-";
  }
}
