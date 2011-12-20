package org.openimmunizationsoftware.dqa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.db.model.BatchType;
import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.CodeTable;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.IssueFound;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.BatchReport;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.UserAccount;
import org.openimmunizationsoftware.dqa.manager.CodesReceived;
import org.openimmunizationsoftware.dqa.manager.FileImportManager;
import org.openimmunizationsoftware.dqa.manager.ManagerThread;
import org.openimmunizationsoftware.dqa.manager.ManagerThreadMulti;
import org.openimmunizationsoftware.dqa.manager.MessageReceivedManager;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.manager.WeeklyBatchManager;
import org.openimmunizationsoftware.dqa.manager.WeeklyExportManager;
import org.openimmunizationsoftware.dqa.parse.PrintBean;
import org.openimmunizationsoftware.dqa.parse.VaccinationUpdateParserHL7;
import org.openimmunizationsoftware.dqa.quality.QualityCollector;
import org.openimmunizationsoftware.dqa.validate.Validator;

public class IncomingServlet extends HttpServlet
{

  protected static FileImportManager fileImportManager = null;
  protected static WeeklyBatchManager weeklyBatchManager = null;
  protected static WeeklyExportManager weeklyExportManager = null;

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

    printManagerThread(fileImportManager, out);
    printManagerThread(weeklyBatchManager, out);
    printManagerThread(weeklyExportManager, out);
    out.println("    <hr>");
    out.println("    <p>Version " + SoftwareVersion.VERSION + "</p>");
    out.println("  </body>");
    out.println("</html>");
    out.close();
    out = null;
  }

  private void printManagerThread(ManagerThread mt, PrintWriter out)
  {
    if (mt.isKeepRunning())
    {
      out.println("      <h3>Process Deamon '" + mt.getLabel() + "' is running.</h3>");
    }
    if (mt.getLastException() != null)
    {
      out.println("      <h4>Last Exception</h4>");
      out.println("<pre>");
      mt.getLastException().printStackTrace(out);
      out.println("</pre>");
    }
    out.println("      <h4>Internal Log</h4>");
    out.println("<pre>");
    out.print(mt.getInternalLog());
    out.println("</pre>");
    out.println("      <h4>Progress Update</h4>");
    if (mt.getProgressStart() > 0)
    {
      out.println("<pre>");
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
      out.println("  + Started: " + sdf.format(mt.getProgressStart()));
      out.println("  + Count:   " + mt.getProgressCount());
      out.println("  + Rate:    " + ((float) mt.getProgressCount())
          / ((System.currentTimeMillis() - mt.getProgressStart()) / 1000.0));
      out.println("</pre>");
    }
    if (mt instanceof ManagerThreadMulti)
    {
      ManagerThreadMulti mtm = (ManagerThreadMulti) mt;
      for (ManagerThread mtChild : mtm.getManagerThreads())
      {
        printManagerThread(mtChild, out);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    String userId = req.getParameter("USERID");
    String password = req.getParameter("PASSWORD");
    String facilityId = req.getParameter("FACILITYID");
    boolean debug = req.getParameter("DEBUG") != null;
    resp.setContentType("text/plain");
    PrintWriter out = new PrintWriter(resp.getOutputStream());

    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    String accessDenied = null;
    if (userId == null || userId.equals(""))
    {
      accessDenied = "USERID was not specified and is required for real time submission";
    } else if (password == null || password.equals(""))
    {
      accessDenied = "PASSWORD was not specified and is required for real time submission";
    } else if (facilityId == null || facilityId.equals(""))
    {
      accessDenied = "FACILITYID was not specified and is required for real time submission";
    }
    SubmitterProfile profile = null;
    if (accessDenied == null)
    {
      boolean isEmail = false;
      int pos = userId.indexOf("@");
      isEmail = pos > 1 && pos < (userId.length() - 2);
      String emailUsername = "";
      String emailHost = "";
      if (isEmail)
      {
        emailUsername = userId.substring(0, pos);
        emailHost = userId.substring(pos + 1);
      }
      if (isEmail)
      {
        Query query = session.createQuery("from UserAccount where email = ?");
        query.setParameter(0, userId);
        List<UserAccount> userAccounts = query.list();
        UserAccount userAccount = null;
        if (userAccounts.size() > 0)
        {
          userAccount = userAccounts.get(0);
        } else
        {
          query = session.createQuery("from Organization where orgLocalCode = ?");
          query.setParameter(0, emailHost); // Use email host as the
                                            // organization name
          List<Organization> organizations = query.list();
          Organization organization = null;
          if (organizations.size() == 0)
          {
            organization = new Organization();
            organization.setOrgLabel(emailHost);
            organization.setOrgLocalCode(emailHost);
            organization.setParentOrganization((Organization) session.get(Organization.class, 1));
            session.save(organization);
          } else
          {
            organization = organizations.get(0);
          }
          // find unique user name
          String username = null;
          int i = 0;
          while (username == null)
          {
            username = (i == 0 ? emailUsername : emailUsername + i);
            query = session.createQuery("from UserAccount where username = ?");
            query.setParameter(0, username);
            if (query.list().size() > 0)
            {
              username = null;
              i++;
            }
          }
          userAccount = new UserAccount();
          userAccount.setAccountType(UserAccount.ACCOUNT_TYPE_SUBMITTER);
          userAccount.setEmail(userId);
          userAccount.setUsername(username);
          userAccount.setOrganization(organization);
          userAccount.setPassword(password);
          session.save(userAccount);
        }
        query = session.createQuery("from SubmitterProfile where organization = ? and profileLabel = ?");
        query.setParameter(0, userAccount.getOrganization());
        query.setParameter(1, facilityId);
        List<SubmitterProfile> submitterProfiles = query.list();
        if (submitterProfiles.size() == 0)
        {
          profile = new SubmitterProfile();
          profile.setAccessKey(password);
          profile.setDataFormat(SubmitterProfile.DATA_FORMAT_HL7V2);
          profile.setOrganization(userAccount.getOrganization());
          profile.setProfileCode(facilityId);
          profile.setProfileLabel(facilityId);
          profile.setProfileStatus(SubmitterProfile.PROFILE_STATUS_TEST);
          profile.setTransferPriority(SubmitterProfile.TRANSFER_PRIORITY_NORMAL);
          session.save(profile);
        } else
        {
          profile = submitterProfiles.get(0);
          if (!profile.getAccessKey().equals(password))
          {
            accessDenied = "Unrecognized USERID, PASSWORD or FACILITYID";
            profile = null;
          }
        }
      } else
      {
        Query query = session.createQuery("from SubmitterProfile where profileCode = ? and accessKey = ?");
        query.setParameter(0, userId);
        query.setParameter(1, password);
        List<SubmitterProfile> submitterProfiles = query.list();
        if (submitterProfiles.size() == 0)
        {
          accessDenied = "Authorization failed, invalid USERID or PASSWORD";
        } else
        {
          profile = submitterProfiles.get(0);
        }
      }
    }
    tx.commit();
    QualityCollector qualityCollector = null;
    if (accessDenied == null)
    {
      try
      {
        ProcessLocker.lock(profile);
        qualityCollector = new QualityCollector("Realtime HTTPS", BatchType.SUBMISSION, profile);
        String messageData = req.getParameter("MESSAGEDATA");
        debug = processStream(debug, session, profile, messageData, out, qualityCollector);
      } finally
      {
        ProcessLocker.unlock(profile);
      }
    } else
    {
      out.println(accessDenied);
    }
    if (debug)
    {
      if (qualityCollector != null)
      {
        printMessageBatch(out, qualityCollector);
      }
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

  private void printMessageBatch(PrintWriter out, QualityCollector qualityCollector)
  {
    MessageBatch mb = qualityCollector.getMessageBatch();
    BatchReport r = mb.getBatchReport();
    out.print("\r");
    out.print("\r");
    out.print("Message Batch Summary: \r");
    out.print("Message Count:       " + r.getMessageCount() + "\r");
    out.print("Patient Count:       " + r.getMessageCount() + "\r");
    out.print("Vaccination      \r");
    out.print(" + Administered:     " + r.getVaccinationAdministeredCount() + "\r");
    out.print(" + Historical:       " + r.getVaccinationHistoricalCount() + "\r");
    out.print(" + Not Administered: " + r.getVaccinationNotAdministeredCount() + "\r");
    out.print(" + Deleted:          " + r.getVaccinationDeleteCount() + "\r");
  }

  public boolean processStream(boolean debug, Session session, SubmitterProfile profile, String messageData,
      PrintWriter out, QualityCollector qualityCollector) throws IOException
  {
    VaccinationUpdateParserHL7 parser = new VaccinationUpdateParserHL7(profile);
    StringReader stringReader = new StringReader(messageData);
    BufferedReader in = new BufferedReader(stringReader);
    String line = null;
    StringBuilder sb = new StringBuilder();

    while ((line = in.readLine()) != null)
    {
      if (line.startsWith("MSH"))
      {
        if (sb.length() > 0)
        {
          debug = processMessage(parser, sb, debug, profile, session, out, qualityCollector);
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
      debug = processMessage(parser, sb, debug, profile, session, out, qualityCollector);
    }
    Transaction tx = session.beginTransaction();
    qualityCollector.close();
    session.save(qualityCollector.getMessageBatch());
    session.save(qualityCollector.getMessageBatch().getBatchReport());
    tx.commit();
    return debug;
  }

  private boolean processMessage(VaccinationUpdateParserHL7 parser, StringBuilder sb, boolean debug,
      SubmitterProfile profile, Session session, PrintWriter out, QualityCollector qualityCollector)
  {
    Transaction tx = session.beginTransaction();
    try
    {

      profile.initPotentialIssueStatus(session);
      MessageReceived messageReceived = new MessageReceived();
      messageReceived.setProfile(profile);
      messageReceived.setRequestText(sb.toString());
      parser.createVaccinationUpdateMessage(messageReceived);
      if (profile.isProfileStatusTest()
          && messageReceived.getMessageHeader().getProcessingStatusCode()
              .equals(org.openimmunizationsoftware.dqa.db.model.MessageHeader.PROCESSING_ID_DEBUGGING))
      {
        debug = true;
      }
      if (!messageReceived.hasErrors())
      {
        Validator validator = new Validator(profile, session);
        validator.validateVaccinationUpdateMessage(messageReceived, null);
      }
      qualityCollector.registerProcessedMessage(messageReceived);

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
          out.print("Processed message: " + qualityCollector.getMessageBatch().getBatchReport().getMessageCount()
              + "\r");
          List<IssueFound> issuesFound = messageReceived.getIssuesFound();
          boolean first = true;
          for (IssueFound issueFound : issuesFound)
          {
            if (issueFound.isError())
            {
              if (first)
              {
                out.print("Errors:\r");
                first = false;
              }
              printIssueFound(issueFound, out);
            }
          }
          first = true;
          for (IssueFound issueFound : issuesFound)
          {
            if (issueFound.isWarn())
            {
              if (first)
              {
                out.print("Warnings:\r");
                first = false;
              }
              printIssueFound(issueFound, out);
            }
          }
          first = true;
          for (IssueFound issueFound : issuesFound)
          {
            if (issueFound.isSkip())
            {
              if (first)
              {
                out.print("Skip:\r");
                first = false;
              }
              printIssueFound(issueFound, out);
            }
          }
          out.print("Message Data: \r");
          PrintBean.print(messageReceived, out);
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
      if (out != null)
      {
        out.print(ackMessage);
      } else
      {
        exception.printStackTrace();
      }
      if (debug)
      {
        exception.printStackTrace(out);
      }
      exception.printStackTrace();
      tx.rollback();
    }
    return debug;
  }

  private void printIssueFound(IssueFound issueFound, PrintWriter out)
  {
    out.print("  + ");
    out.print(issueFound.getDisplayText());
    out.print("\r");
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
