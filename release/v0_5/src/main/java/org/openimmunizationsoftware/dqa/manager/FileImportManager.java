package org.openimmunizationsoftware.dqa.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.wicket.util.file.File;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.Version;
import org.openimmunizationsoftware.dqa.db.model.BatchActions;
import org.openimmunizationsoftware.dqa.db.model.BatchCodeReceived;
import org.openimmunizationsoftware.dqa.db.model.BatchIssues;
import org.openimmunizationsoftware.dqa.db.model.BatchType;
import org.openimmunizationsoftware.dqa.db.model.BatchVaccineCvx;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.IssueFound;
import org.openimmunizationsoftware.dqa.db.model.KeyedSetting;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.ReceiveQueue;
import org.openimmunizationsoftware.dqa.db.model.SubmitStatus;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.parse.VaccinationUpdateParserHL7;
import org.openimmunizationsoftware.dqa.quality.QualityReport;
import org.openimmunizationsoftware.dqa.validate.Validator;

public class FileImportManager extends ManagerThread
{
  private static FileImportManager singleton = null;

  public static synchronized FileImportManager getFileImportManager()
  {
    if (singleton == null)
    {
      singleton = new FileImportManager();
      singleton.start();
    }
    return singleton;
  }

  private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
  private File processingFile = null;
  private PrintWriter processingOut = null;
  private File acceptedDir = null;
  private File receiveDir = null;
  private File submitDir = null;
  private SubmitterProfile profile = null;
  private VaccinationUpdateParserHL7 parser = null;
  private int messageCount = 0;
  private PrintWriter ackOut;
  private PrintWriter logOut;
  private PrintWriter reportOut;
  private PrintWriter errorsOut;
  private PrintWriter acceptedOut;
  private String profileCode = null;
  private MessageBatchManager messageBatchManager = null;

  private FileImportManager() {
    super("File Import Manager");
  }

  @Override
  public void run()
  {
    internalLog.append("Starting file import manager, loading settings\r");
    KeyedSettingManager ksm = KeyedSettingManager.getKeyedSettingManager();
    while (keepRunning)
    {
      internalLog.append("Looking to import\r");
      try
      {
        if (ksm.getKeyedValueBoolean(KeyedSetting.IN_FILE_ENABLE, false))
        {
          internalLog.append("Import enabled\r");
          String rootDirString = ksm.getKeyedValue(KeyedSetting.IN_FILE_DIR, "c:/data/in");
          File rootDir = new File(rootDirString);
          if (rootDir.exists() && rootDir.isDirectory())
          {
            internalLog.append("Root dir exists, begin processing\r");
            processDataDir(ksm, rootDir);
          } else
          {
            internalLog.append("Can't find root directory: " + rootDirString + "\r");
          }
        }
      } catch (Throwable e)
      {
        e.printStackTrace();
        lastException = e;
      }
      internalLog.append("Processing complete\r");
      try
      {
        synchronized (sdf)
        {
          sdf.wait(ksm.getKeyedValueInt(KeyedSetting.IN_FILE_WAIT, 15) * 1000);
        }
      } catch (InterruptedException ie)
      {
        keepRunning = false;
        return;
      }
      internalLog.setLength(0);
    }
  }

  private void processDataDir(KeyedSettingManager ksm, File rootDir) throws IOException, FileNotFoundException
  {
    internalLog.append("Looking in " + rootDir.getAbsolutePath() + "\r");
    String[] dirNames = rootDir.list(new FilenameFilter() {
      public boolean accept(java.io.File arg0, String arg1)
      {
        return new File(arg0, arg1).isDirectory();
      }
    });
    internalLog.append("Found " + dirNames.length + " directories to look in\r");
    for (String dirName : dirNames)
    {
      File dir = new File(rootDir, dirName);
      internalLog.append(" + " + dirName);
      if (dir.exists() && createProcessingLogFile(dir))
      {
        internalLog.append(" PROCESSING \r");
        createProcessingDirs(ksm, dir);
        profileCode = dir.getName();
        SessionFactory factory = OrganizationManager.getSessionFactory();
        Session session = factory.openSession();
        findProfile(profileCode, session);
        String[] filesToProcess = submitDir.list(new FilenameFilter() {
          public boolean accept(java.io.File dir, String name)
          {
            File file = new File(dir, name);
            return file.isFile() && file.canRead();
          }
        });
        procLog("Found " + filesToProcess.length + " files to process in " + submitDir.getAbsolutePath());
        for (String filename : filesToProcess)
        {
          lookToProcessFile(session, filename);
        }
        session.close();
        renameProcessLogfile(dir);
      } else
      {
        internalLog.append(" SKIPPING \r");
      }
    }
  }

  private void lookToProcessFile(Session session, String filename) throws FileNotFoundException, IOException
  {
    File inFile = new File(submitDir, filename);
    procLog("Looking at file " + filename);
    if (inFile.exists() && inFile.canRead())
    {
      long timeSinceLastChange = System.currentTimeMillis() - inFile.lastModified();
      if (timeSinceLastChange > 60 * 1000)
      {
        procLog("Processing file " + filename);
        processFile(session, filename, inFile);
      } else
      {
        procLog("Postponing processing, file recently changed");
      }
    } else
    {
      procLog("File does not exist or can not be read");
    }
  }

  private void processFile(Session session, String filename, File inFile) throws FileNotFoundException, IOException
  {
    createMessageBatch(session);
    BufferedReader in = new BufferedReader(new FileReader(inFile));
    String line = readRealFirstLine(in);
    if (line != null && (line.startsWith("MSH") || line.startsWith("FHS") || line.startsWith("BHS")))
    {
      StringBuilder message = new StringBuilder();
      openOutputs(filename);
      messageCount = 0;
      do
      {
        line = line.trim();
        acceptedOut.print(line);
        acceptedOut.print("\r");
        if (line.startsWith("MSH"))
        {
          if (message.length() > 0)
          {
            messageCount++;
            processMessage(message, profile, session);
          }
          message.setLength(0);
        } else if (line.startsWith("FHS") || line.startsWith("BHS") || line.startsWith("BTS") || line.startsWith("FTS"))
        {
          continue;
        }
        message.append(line);
        message.append("\r");
      } while ((line = in.readLine()) != null);
      if (message.length() > 0)
      {
        messageCount++;
        processMessage(message, profile, session);
      }
      printReport(inFile);
      closeOutputs(acceptedOut);
    }
    in.close();
    saveAndCloseBatch(session);
    inFile.delete();
  }

  private void saveAndCloseBatch(Session session)
  {
    messageBatchManager.close();
    Transaction tx = session.beginTransaction();
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
    tx.commit();
  }

  private void createMessageBatch(Session session)
  {
    Transaction tx = session.beginTransaction();
    messageBatchManager = new MessageBatchManager("File Import", BatchType.SUBMISSION, profile);
    session.save(messageBatchManager.getMessageBatch());
    tx.commit();
  }

  private void printReport(File inFile)
  {
    messageBatchManager.score();
    QualityReport qualityReport = new QualityReport(messageBatchManager, profile, reportOut);
    qualityReport.setFilename(inFile.getName());
    qualityReport.printReport();
  }

  /**
   * Reads the first lines of the file until it comes to a non empty line. This
   * is to handle situations where the first few lines are empty and the HL7
   * message does not immediately start.
   * 
   * @param in
   * @return
   * @throws IOException
   */
  private String readRealFirstLine(BufferedReader in) throws IOException
  {
    String line = null;
    while ((line = in.readLine()) != null)
    {
      line = line.trim();
      if (line.length() > 0)
      {
        break;
      }
    }
    return line;
  }

  private void openOutputs(String filename) throws IOException
  {
    File outFile = new File(acceptedDir, filename);
    acceptedOut = new PrintWriter(new FileWriter(outFile, true));
    ackOut = new PrintWriter(new FileWriter(new File(receiveDir, filename + ".ack.hl7")));
    logOut = new PrintWriter(new FileWriter(new File(receiveDir, filename + ".log.txt")));
    reportOut = new PrintWriter(new FileWriter(new File(receiveDir, filename + ".report.html")));
    errorsOut = new PrintWriter(new FileWriter(new File(receiveDir, filename + ".errors.txt")));
  }

  private void closeOutputs(PrintWriter acceptedOut)
  {
    logOut.println("Processing Complete");
    logOut.println("Software Label:   " + KeyedSettingManager.getApplication().getApplicationLabel());
    logOut.println("Software Type:    " + KeyedSettingManager.getApplication().getApplicationType());
    logOut.println("Software Version: " + Version.SOFTWARE_VERSION);
    acceptedOut.close();
    ackOut.close();
    logOut.close();
    reportOut.close();
    errorsOut.close();
  }

  private void processMessage(StringBuilder message, SubmitterProfile profile, Session session)
  {
    try
    {
      Transaction tx = session.beginTransaction();
      MessageReceived messageReceived = new MessageReceived();
      messageReceived.setProfile(profile);
      messageReceived.setRequestText(message.toString());
      parser.createVaccinationUpdateMessage(messageReceived);
      if (!messageReceived.hasErrors())
      {
        Validator validator = new Validator(profile, session);
        validator.validateVaccinationUpdateMessage(messageReceived);
      }
      IssueAction issueAction = determineIssueAction(messageReceived);
      messageReceived.setIssueAction(issueAction);
      messageBatchManager.registerProcessedMessage(messageReceived);

      String ackMessage = parser.makeAckMessage(messageReceived);
      messageReceived.setResponseText(ackMessage);
      MessageReceivedManager.saveMessageReceived(profile, messageReceived, session);
      saveInQueue(session, messageReceived);
      ackOut.print(ackMessage);
      printLogDetails(message, messageReceived, logOut, false);
      if (issueAction.isError())
      {
        printLogDetails(message, messageReceived, errorsOut, true);
      }
      tx.commit();
    } catch (Throwable exception)
    {
      String ackMessage = "MSH|^~\\&|||||201105231008000||ACK^|201105231008000|P|2.3.1|\r"
          + "MSA|AE|TODO|Exception occurred: " + exception.getMessage() + "|\r";
      ackOut.print(ackMessage);
      exception.printStackTrace(processingOut);
      exception.printStackTrace(logOut);
      exception.printStackTrace(errorsOut);
      exception.printStackTrace(reportOut);
    }
  }

  private IssueAction determineIssueAction(MessageReceived messageReceived)
  {
    IssueAction issueAction;
    if (messageReceived.getPatient().isSkipped())
    {
      issueAction = IssueAction.SKIP;
    } else if (messageReceived.hasErrors())
    {
      issueAction = IssueAction.ERROR;
    } else if (messageReceived.hasWarns())
    {
      issueAction = IssueAction.WARN;
    } else
    {
      issueAction = IssueAction.ACCEPT;
    }
    return issueAction;
  }

  private void saveInQueue(Session session, MessageReceived messageReceived)
  {
    ReceiveQueue receiveQueue = new ReceiveQueue();
    receiveQueue.setMessageBatch(messageBatchManager.getMessageBatch());
    receiveQueue.setMessageReceived(messageReceived);
    receiveQueue.setSubmitStatus(messageReceived.hasErrors() ? SubmitStatus.EXCLUDED : SubmitStatus.QUEUED);
    messageReceived.setSubmitStatus(receiveQueue.getSubmitStatus());
    session.save(receiveQueue);
  }

  private void printLogDetails(StringBuilder message, MessageReceived messageReceived, PrintWriter out,
      boolean printDetails)
  {
    try
    {
      out.println("Message " + messageCount);
      out.println(message);
      List<IssueFound> issuesFound = messageReceived.getIssuesFound();
      boolean first = true;
      for (IssueFound issueFound : issuesFound)
      {
        if (!issueFound.getIssueNegate() && issueFound.isError())
        {
          if (first)
          {
            out.println("Errors:");
            first = false;
          }
          out.println(" + " + issueFound.getDisplayText());
        }
      }
      first = true;
      for (IssueFound issueFound : issuesFound)
      {
        if (!issueFound.getIssueNegate() && issueFound.isWarn())
        {
          if (first)
          {
            out.println("Warnings:");
            first = false;
          }
          out.println(" + " + issueFound.getDisplayText());
        }
      }
      first = true;
      for (IssueFound issueFound : issuesFound)
      {
        if (!issueFound.getIssueNegate() && issueFound.isSkip())
        {
          if (first)
          {
            out.println("Skip:");
            first = false;
          }
          out.println(" + " + issueFound.getDisplayText());
        }
      }
      if (printDetails)
      {
        out.println("Message Data: ");
        printBean(out, messageReceived, "  ");
      }
      out.println();
      out.println();
    } catch (Exception e)
    {
      e.printStackTrace(out);
    }
  }

  private void findProfile(String profileCode, Session session)
  {
    procLog("Looking for submitter profile");
    Query query = session.createQuery("from SubmitterProfile where profileCode = ?");
    query.setParameter(0, profileCode);
    List<SubmitterProfile> submitterProfiles = query.list();
    if (submitterProfiles.size() == 0)
    {
      procLog("Submitter profile not found, creating new one");
      Transaction tx = session.beginTransaction();
      Organization organization = new Organization();
      organization.setOrgLabel(profileCode);
      organization.setParentOrganization((Organization) session.get(Organization.class, 1));
      profile = new SubmitterProfile();
      profile.setProfileLabel("HL7 File");
      profile.setProfileStatus(SubmitterProfile.PROFILE_STATUS_TEST);
      profile.setOrganization(organization);
      profile.setDataFormat(SubmitterProfile.DATA_FORMAT_HL7V2);
      profile.setTransferPriority(SubmitterProfile.TRANSFER_PRIORITY_NORMAL);
      profile.setProfileCode(profileCode);
      profile.generateAccessKey();
      session.save(organization);
      session.save(profile);
      organization.setPrimaryProfile(profile);
      tx.commit();
    } else
    {
      profile = submitterProfiles.get(0);
      procLog("Using profile " + profile.getProfileId() + " '" + profile.getProfileLabel() + "'");
    }
    parser = new VaccinationUpdateParserHL7(profile);
  }

  private void createProcessingDirs(KeyedSettingManager ksm, File dir)
  {
    acceptedDir = new File(dir, ksm.getKeyedValue(KeyedSetting.IN_FILE_ACCEPTED_DIR_NAME, "accepted"));
    if (!acceptedDir.exists())
    {
      procLog("Processed directory does not exist, creating");
      acceptedDir.mkdir();
    }
    receiveDir = new File(dir, ksm.getKeyedValue(KeyedSetting.IN_FILE_RECEIVE_DIR_NAME, "receive"));
    if (!receiveDir.exists())
    {
      procLog("Received directory does not exist, creating");
      receiveDir.mkdir();
    }
    submitDir = new File(dir, ksm.getKeyedValue(KeyedSetting.IN_FILE_SUBMIT_DIR_NAME, "submit"));
    if (!submitDir.exists())
    {
      procLog("Submit directory does not exist, creating");
      submitDir.mkdir();
    }
  }

  private void renameProcessLogfile(File dir)
  {
    procLog("processing complete");
    processingOut.close();
    File processedFile = new File(dir, "processed.txt");
    if (processedFile.exists())
    {
      processedFile.delete();
    }
    processingFile.renameTo(processedFile);
  }

  private boolean createProcessingLogFile(File dir) throws IOException
  {
    processingFile = new File(dir, "processing.txt");
    boolean okayToProcess = true;
    if (processingFile.exists())
    {
      long age = System.currentTimeMillis() - processingFile.lastModified();
      if (age < 60 * 60 * 1000)
      {
        // this folder might be being processed by another instance of this
        // application
        // this should not happen, but if it is then this process will give it
        // at least
        // an hour to complete. When the processing is complete the file is
        // renamed to
        // processed.txt
        okayToProcess = false;
      }
    }
    if (okayToProcess)
    {
      processingOut = new PrintWriter(new FileWriter(processingFile));
    }
    return okayToProcess;
  }

  private void procLog(String message)
  {
    processingOut.println(sdf.format(new Date()) + " " + message);
    processingOut.flush();
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
          && !method.getName().equals("getTableType") && !method.getName().equals("getCodeReceived")
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
            out.println("'");
            thisPrinted.add(fieldName);
          }
        } else if (method.getReturnType() == Date.class)
        {
          out.print(indent);
          out.print(fieldName);
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
          out.print(" = ");
          out.println(sdf.format((Date) returnValue));
          thisPrinted.add(fieldName);
        } else if (method.getReturnType() == List.class)
        {
          List list = (List) returnValue;
          for (int i = 0; i < list.size(); i++)
          {
            out.print(indent);
            out.print(fieldName);
            out.print(" #");
            out.println(i + 1);
            printBean(out, list.get(i), indent + "  ");
          }
        } else
        {
          out.print(indent);
          out.println(fieldName);
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
