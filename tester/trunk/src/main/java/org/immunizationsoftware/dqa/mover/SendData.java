package org.immunizationsoftware.dqa.mover;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.immunizationsoftware.dqa.tester.Transformer;
import org.immunizationsoftware.dqa.tester.connectors.Connector;

public class SendData extends Thread
{

  public enum ScanStatus {
    STARTING, LOOKING, PREPARING, SENDING, WAITING, PROBLEM, SENT, STOPPED;
  };

  public static final String SMM_PREFIX = "smm.";
  public static final String REJECTED_NAME = "rejected";
  public static final String PROBLEM_NAME = "problem";

  public static final String CONFIG_FILE_NAME = "smm.config.txt";
  public static final String LOCK_FILE_NAME = "smm.lock";
  public static final String LOGIN_FILE_NAME = "smm.login.html";
  public static final String KEYSTORE_FILE_NAME = "smm.jks";

  public static final String REQUEST_FOLDER = "request";
  public static final String RESPONSE_FOLDER = "response";
  public static final String REQUESTS_FOLDER = "requests";
  public static final String RESPONSES_FOLDER = "responses";
  public static final String SENT_FOLDER = "sent";
  public static final String UPDATE_FOLDER = "update";
  public static final String UPDATES_FOLDER = "updates";
  public static final String WORK_FOLDER = ".work";
  public static final String BACKUP_FOLDER = "backup";
  public static final String TEST_FOLDER = "test";
  public static final String GENERATED_FOLDER = "generated";
  public static final String TEST_CASES_FOLDER = "cases";

  private static final long LOCK_TIMEOUT = 2 * 60 * 60 * 1000; // two hours
  private static final long FILE_CHANGE_TIMEOUT = 0;

  private String stableSystemId = null;
  private Date upSinceDate = new Date();
  private int internalId = 0;

  public int getInternalId()
  {
    return internalId;
  }

  public void setInternalId(int internalId)
  {
    this.internalId = internalId;
  }

  public Date getUpSinceDate()
  {
    return upSinceDate;
  }

  public void setUpSinceDate(Date upSinceDate)
  {
    this.upSinceDate = upSinceDate;
  }

  public String getStableSystemId()
  {
    if (stableSystemId == null)
    {
      String baseValue = ManagerServlet.doHash(rootDir.getAbsolutePath());
      stableSystemId = ManagerServlet.getStableSystemId() + ":" + baseValue;
    }
    return stableSystemId;
  }

  private boolean okayToRun = true;

  public boolean isOkayToRun()
  {
    return okayToRun;
  }

  public void shutdown()
  {
    this.scanStatus = ScanStatus.STOPPED;
    okayToRun = false;
    synchronized (this)
    {
      this.interrupt();
    }
    if (statusReporter != null)
    {
      statusReporter.shutdown();
      statusReporter = null;
    }
    if (statusLogger != null)
    {
      statusLogger.close();
      statusLogger = null;
    }
  }

  @Override
  public void run()
  {
    while (okayToRun)
    {
      setupStatusReporter();
      waitAwhileMoreForProblem();
      if (okayToRun)
      {
        scanStatus = ScanStatus.LOOKING;
        if (configFile.exists() && configFile.isFile() && configFile.canRead())
        {
          try
          {
            setupConnector();
            if (obtainLock() && okayToRun)
            {
              createWorkingDirs();
              createTransformer();

              if (workDirIsEmpty())
              {
                if (lookForFilesToProcess())
                {
                  prepareDataToSend();
                }
              }
              sendData();
              setScanStatus(ScanStatus.WAITING);
              resetProblemRetryCount();
              deleteWorkingDir();
            }
          } catch (ApplicationShuttingDown asd)
          {
            // Application is shutting down, continue on out
          } catch (Throwable e)
          {
            handleException("Problem encountered while trying to send data", e);
          } finally
          {
            closeLoggerAndUnlock();
          }
        }
        waitAwhile();
      }
    }
    logShutdown();
  }

  private void setupStatusReporter()
  {
    if (statusReporter == null)
    {
      try
      {
        statusReporter = new StatusReporter(this);
      } catch (IOException ioe)
      {
        statusReporterException = ioe;
      }
      statusReporter.start();
    }
    if (statusReporter != null)
    {
      statusReporterException = statusReporter.getException();
    }
  }

  private void logShutdown()
  {
    try
    {
      StatusReporter statusReporter = new StatusReporter(this);
      statusReporter.updateSupportCenter(null, null);
    } catch (Throwable t)
    {
      t.printStackTrace();
    }
  }

  private ScanStatus scanStatus = ScanStatus.STARTING;

  private File rootDir = null;
  private File configFile = null;
  private File statusFile = null;
  private File lockFile = null;
  private FileOut problemFileOut = null;

  private File requestDir = null;
  private File requestFile = null;
  private FileOut errorFileOut = null;

  private File workDir = null;
  private File workFile = null;

  private File sentDir = null;
  private FileOut sentFileOut = null;

  private File responseDir = null;
  private FileOut responseFileOut = null;

  private File updateDir = null;
  private FileOut updateFileOut = null;

  private File backupDir = null;
  private FileOut backupFileOut = null;

  private Connector connector = null;
  private StatusLogger statusLogger = null;
  private StatusReporter statusReporter = null;
  private Throwable statusReporterException = null;
  private SendDataLocker sendDataLocker = null;
  private String filename = null;
  private String filenameStart = null;
  private String filenameEnd = null;
  private List<File> filesToProcessList = null;
  private int messageNumber = 0;
  private int fileSentCount = 0;
  private int fileErrorCount = 0;

  private int attemptCount = 0;
  private int sentCount = 0;
  private int errorCount = 0;

  public StatusReporter getStatusReporter()
  {
    return statusReporter;
  }

  public StatusLogger getStatusLogger()
  {
    return statusLogger;
  }

  public int getAttemptCount()
  {
    return attemptCount;
  }

  public int getSentCount()
  {
    return sentCount;
  }

  public int getErrorCount()
  {
    return errorCount;
  }

  public void incAttemptCount()
  {
    attemptCount++;
  }

  public void incSentCount()
  {
    sentCount++;
  }

  public void incErrorCount()
  {
    errorCount++;
  }

  private int retryCount = 0;
  private static final long SEC = 1000;
  private static final long MIN = 60 * SEC;
  private static final long HOUR = 60 * MIN;
  private static final long DAY = 24 * HOUR;
  private static final long[] retryWait = { 30 * SEC, 1 * MIN, 2 * MIN, 4 * MIN, 8 * MIN, 16 * MIN, 30 * MIN, HOUR, 2 * HOUR, 4 * HOUR, 8 * HOUR,
      12 * HOUR, DAY };
  private Transformer transformer = null;

  private int randomId = 0;

  public int getRandomId()
  {
    return randomId;
  }

  public Connector getConnector()
  {
    return connector;
  }

  public File getRootDir()
  {
    return rootDir;
  }

  public void setRootDir(File rootDir)
  {
    this.rootDir = rootDir;
  }

  public File getConfigFile()
  {
    return configFile;
  }

  public void setConfigFile(File configFile)
  {
    this.configFile = configFile;
  }

  public File getStatusFile()
  {
    return statusFile;
  }

  public void setStatusFile(File statusFile)
  {
    this.statusFile = statusFile;
  }

  public SendData(File rootDir) {
    this.rootDir = rootDir;
    this.configFile = new File(rootDir, CONFIG_FILE_NAME);
    Random random = new Random();
    randomId = random.nextInt(10000) + 1000;
  }

  public ScanStatus getScanStatus()
  {
    return scanStatus;
  }

  private void setScanStatus(ScanStatus scanStatus)
  {
    this.scanStatus = scanStatus;
    if (statusLogger != null)
    {
      statusLogger.updateScanStatus(scanStatus);
    }
  }

  private void createTransformer()
  {
    if (!connector.getCustomTransformations().equals(""))
    {
      transformer = new Transformer();
      statusLogger.logInfo("Custom transformations are defined, setting up transformer");
    }
  }

  private void deleteWorkingDir()
  {
    if (workDir.exists())
    {
      workDir.delete();
    }
  }

  private void closeLoggerAndUnlock()
  {
    if (statusLogger != null)
    {
      statusLogger.close();
    }
    if (sendDataLocker != null)
    {
      sendDataLocker.releaseLock();
      sendDataLocker = null;
    }
    transformer = null;
  }

  private void handleException(String message, Throwable e)
  {
    setScanStatus(ScanStatus.PROBLEM);
    if (statusLogger != null)
    {
      statusLogger.setSomethingInterestingHappened(true);
      statusLogger.logError(message, e);
    } else
    {
      System.out.println("Unable to send data");
      e.printStackTrace();
    }
  }

  private void resetProblemRetryCount()
  {
    retryCount = 0;
  }

  private void waitAwhile()
  {
    synchronized (this)
    {
      try
      {
        this.wait(ManagerServlet.getCheckInterval() * 1000);
      } catch (InterruptedException ie)
      {
        // continue
      }
    }
  }

  private void sendData() throws IOException, FileNotFoundException, Exception
  {
    if (workDir.exists())
    {
      setScanStatus(ScanStatus.SENDING);
      statusLogger.logDebug("Looking for files to send from working directory");
      String[] filesToSend = workDir.list();
      Arrays.sort(filesToSend);
      String lastRequestFilename = "";
      for (String fileToSend : filesToSend)
      {
        verifyOkayToKeepRunning();
        statusLogger.setSomethingInterestingHappened(true);
        workFile = new File(workDir, fileToSend);
        sendDataLocker.renewLock();
        String requestFilename = getOriginalFileName(fileToSend);
        if (!lastRequestFilename.equals(requestFilename))
        {
          fileSentCount = 0;
          fileErrorCount = 0;
          statusLogger.logDebug("Sending file: " + requestFilename);
        }
        if (workFile.isFile() && !requestFilename.equals(""))
        {
          fileSentCount++;
          readFilename(requestFilename);
          openSendingMessageInputs();
          try
          {
            StringBuilder sb = new StringBuilder();
            String line = null;
            BufferedReader in = new BufferedReader(new FileReader(workFile));
            while ((line = in.readLine()) != null)
            {
              sb.append(line);
              sb.append("\r");
            }
            in.close();
            String messageText = sb.toString();
            incAttemptCount();
            String responseText = connector.submitMessage(messageText, false);
            verifyOkayToKeepRunning();
            saveAndHandleResponse(messageText, responseText);
          } finally
          {
            closeSendingMethodInputs();
          }
        }
        workFile.delete();
        deleteRequestFile(lastRequestFilename, requestFilename);
        lastRequestFilename = requestFilename;
      }
      deleteRequestFile(lastRequestFilename, "");
    }
  }

  private void deleteRequestFile(String lastOriginalFilename, String originalFilename)
  {
    if (!lastOriginalFilename.equals("") && !lastOriginalFilename.equals(originalFilename))
    {
      File fileToDelete = new File(requestDir, lastOriginalFilename);
      if (fileToDelete.exists())
      {
        statusLogger.logFile(fileToDelete.getName(), ScanStatus.SENT, fileSentCount, fileErrorCount);
        String message = "File sent: " + fileToDelete.getName() + " Message count: " + fileSentCount;
        if (fileErrorCount > 0)
        {
          message += " Error count: " + fileErrorCount;
        }
        statusLogger.logDebug(message);
        fileToDelete.delete();
      }
    } else
    {
      statusLogger.logFile(originalFilename, ScanStatus.SENDING, fileSentCount, fileErrorCount);
    }
  }

  private void saveAndHandleResponse(String messageText, String responseText) throws IOException
  {
    String line;
    StringBuilder responseMessage = new StringBuilder();
    String responseMessageType = "";
    String acknowledgmentCode = null;
    String errorDescription = "";
    BufferedReader responseIn = new BufferedReader(new StringReader(responseText));
    while ((line = responseIn.readLine()) != null)
    {
      line = line.trim();
      if (line.length() < 3 || HL7.isFileBatchHeaderFooterSegment(line))
      {
        continue;
      }
      if (line.startsWith(HL7.MSH))
      {
        handleResponse(responseMessage, responseMessageType);
        responseMessage.setLength(0);
        responseMessageType = HL7.readField(line, 9);
      } else if (line.startsWith(HL7.MSA))
      {
        if (acknowledgmentCode == null)
        {
          acknowledgmentCode = HL7.readField(line, 1);
        }
        errorDescription = addToErrorDescription(errorDescription, HL7.readField(line, 3));
      } else if (line.startsWith(HL7.ERR))
      {
        String severity = HL7.readField(line, 4);
        if (severity.equals("E"))
        {
          errorDescription = addToErrorDescription(errorDescription, HL7.readField(line, 8));
        }
      }
      responseMessage.append(line);
      responseMessage.append("\r");
    }
    handleResponse(responseMessage, responseMessageType);
    incSentCount();

    sentFileOut.print(messageText);
    if (acknowledgmentCode == null || acknowledgmentCode.equals(HL7.AE) || acknowledgmentCode.equals(HL7.AR))
    {
      incErrorCount();
      fileErrorCount++;
      errorFileOut.printCommentLn("MESSAGE REJECTED");

      errorFileOut.printCommentLn(errorDescription);
      errorFileOut.printCommentLn("");
      errorFileOut.printCommentLn("REQUEST");
      errorFileOut.print(messageText);
      errorFileOut.printCommentLn("");
      errorFileOut.printCommentLn("RESPONSE");
      errorFileOut.print(responseMessage.toString());
      errorFileOut.println();
      errorFileOut.println();
    }
  }

  private void handleResponse(StringBuilder responseMessage, String responseMessageType) throws IOException
  {
    if (responseMessage.length() > 0)
    {
      if (responseMessageType.startsWith(HL7.ACK))
      {
        responseFileOut.print(responseMessage.toString());
      } else if (responseMessageType.startsWith(HL7.VXU))
      {
        updateFileOut.print(responseMessage.toString());
      } else
      {
        responseFileOut.print(responseMessage.toString());
      }
    }
  }

  private void prepareDataToSend() throws IOException
  {
    statusLogger.logDebug("Preparing to send data");
    statusLogger.setSomethingInterestingHappened(true);
    setScanStatus(ScanStatus.PREPARING);
    for (File fileToSend : filesToProcessList)
    {
      verifyOkayToKeepRunning();
      requestFile = fileToSend;
      try
      {
        sendDataLocker.renewLock();
        statusLogger.logFile(requestFile.getName(), ScanStatus.PREPARING, 0);
        statusLogger.logDebug("Preparing file: " + requestFile.getName());
        createWorkingFiles();
        prepareFile();
      } catch (Throwable t)
      {
        statusLogger.logError("Unable to send data for file: " + requestFile.getName(), t);
        problemFileOut = new FileOut(new File(filenameStart + "." + PROBLEM_NAME + "." + filenameEnd), false);
        problemFileOut.println("Unable to send the data: " + t.getMessage());
        problemFileOut.print(t);
        break;
      } finally
      {
        closeOutputs();
      }
    }
  }

  private void prepareFile() throws FileNotFoundException, IOException, Exception, TransmissionException
  {
    backupFileOut = new FileOut(new File(backupDir, filename), true);
    messageNumber = 0;
    StringBuilder message = new StringBuilder();
    BufferedReader in = new BufferedReader(new FileReader(requestFile));
    String line = readRealFirstLine(in);
    String messageType = null;
    boolean first = true;
    if (line != null && (line.startsWith(HL7.MSH) || line.startsWith(HL7.FHS) || line.startsWith(HL7.BHS)))
    {
      do
      {
        if (!first)
        {
          backupFileOut.print(line);
          backupFileOut.print("\r");
        }
        first = false;
        line = line.trim();
        if (line.startsWith("--- ") || line.length() < 3 || line.equals("---"))
        {
          // Is SMM comment or too short for HL7 segment
          continue;
        }
        if (line.startsWith(HL7.MSH))
        {
          moveMessageToWorking(message, messageType);
          message.setLength(0);
          messageType = HL7.readField(line, 9);
        } else if (line.startsWith(HL7.FHS) || line.startsWith(HL7.BHS) || line.startsWith(HL7.BTS) || line.startsWith(HL7.FTS))
        {
          continue;
        }
        message.append(line);
        message.append("\r");
      } while ((line = in.readLine()) != null);
    }
    moveMessageToWorking(message, messageType);
    statusLogger.logFile(requestFile.getName(), ScanStatus.WAITING, messageNumber);
    statusLogger.logDebug("File prepared: " + requestFile.getName() + " Message count: " + messageNumber);
    in.close();
    backupFileOut.close();
    backupFileOut = null;
  }

  private boolean workDirIsEmpty()
  {
    boolean workDirEmpty = !workDir.exists();
    if (workDirEmpty)
    {
      return workDirEmpty;
    }
    File[] filesInWorking = workDir.listFiles(new FileFilter() {
      public boolean accept(File file)
      {
        return file.isFile();
      }
    });
    workDirEmpty = filesInWorking.length == 0;
    return workDirEmpty;
  }

  private void setupConnector() throws Exception, FileNotFoundException, IOException
  {
    List<Connector> connectors = Connector.makeConnectors(readScript());
    if (connectors.size() == 0)
    {
      throw new Exception("Script does not define connection");
    }
    connector = connectors.get(0);
    connector.setThrowExceptions(true);
    if (statusLogger != null)
    {
      statusLogger.logDebug("Looking for data to send to: " + connector.getLabel());
    }
    readKeyStore();
  }

  private boolean obtainLock() throws TransmissionException, IOException
  {
    lockFile = new File(rootDir, LOCK_FILE_NAME);
    sendDataLocker = new SendDataLocker(lockFile, LOCK_TIMEOUT);
    if (!sendDataLocker.obtainLock())
    {
      sendDataLocker = null;
      return false;
    }
    statusLogger = new StatusLogger(rootDir, this);
    if (statusReporterException != null)
    {
      statusLogger.logError("Unable to update central support center", statusReporterException);
    }
    return true;
  }

  private void waitAwhileMoreForProblem()
  {
    if (scanStatus == ScanStatus.PROBLEM)
    {
      retryCount++;
      if (retryCount > retryWait.length)
      {
        retryCount = retryWait.length;
      }
      long waitTime = retryWait[retryCount - 1];
      Date waitUntil = new Date(System.currentTimeMillis() + waitTime);
      SimpleDateFormat sdf = new SimpleDateFormat(ManagerServlet.STANDARD_DATE_FORMAT);
      if (statusLogger != null)
      {
        statusLogger.logInfo("Problem sending data, will try again: " + sdf.format(waitUntil));
      }
      synchronized (this)
      {
        try
        {
          this.wait(waitTime);
        } catch (InterruptedException ie)
        {
          // continue
        }
      }
    }
  }

  private String addToErrorDescription(String currentDescription, String addition)
  {
    if (addition == null || addition.equals(""))
    {
      return currentDescription;
    }
    if (currentDescription.equals(""))
    {
      return addition;
    }
    return currentDescription + "; " + addition;
  }

  private void closeSendingMethodInputs()
  {
    if (errorFileOut != null)
    {
      errorFileOut.close();
      errorFileOut = null;
    }
    if (sentFileOut != null)
    {
      sentFileOut.close();
      sentFileOut = null;
    }
    if (updateFileOut != null)
    {
      updateFileOut.close();
      updateFileOut = null;
    }
    if (responseFileOut != null)
    {
      responseFileOut.close();
      responseFileOut = null;
    }
  }

  private void openSendingMessageInputs()
  {
    errorFileOut = new FileOut(new File(requestDir, filenameStart + "." + REJECTED_NAME + "." + filenameEnd), true);
    sentFileOut = new FileOut(new File(sentDir, filename), true);
    responseFileOut = new FileOut(new File(responseDir, filename), true);
    updateFileOut = new FileOut(new File(updateDir, filename), true);
  }

  private void moveMessageToWorking(StringBuilder message, String messageType) throws Exception, FileNotFoundException, TransmissionException
  {
    if (messageType != null && !messageType.startsWith(HL7.ACK) && message.length() > 0)
    {
      messageNumber++;
      String messageText = message.toString();
      messageText = transform(messageText);
      if (!workDir.exists())
      {
        workDir.mkdir();
      }
      File workFile = new File(workDir, filename + "-m" + getMessageNumberString());
      PrintWriter out = new PrintWriter(workFile);
      out.print(messageText);
      out.close();

    }
  }

  private String transform(String messageText)
  {
    if (transformer != null)
    {
      messageText = transformer.transform(connector, messageText);
    }
    return messageText;
  }

  private String getOriginalFileName(String filename)
  {
    int pos = filename.lastIndexOf("-m");
    if (pos != -1)
    {
      String originalFilename = filename.substring(0, pos);
      String numberPart = filename.substring(pos + 2);
      if (numberPart.length() >= 5)
      {
        boolean allNumbers = true;
        for (char c : numberPart.toCharArray())
        {
          if (c < '0' || c > '9')
          {
            allNumbers = false;
            break;
          }
        }
        if (allNumbers)
        {
          return originalFilename;
        }
      }
    }
    return "";
  }

  private void createWorkingDirs()
  {
    backupDir = createDir(rootDir, BACKUP_FOLDER);
    requestDir = createDir(rootDir, REQUEST_FOLDER, REQUESTS_FOLDER);
    workDir = new File(requestDir, WORK_FOLDER);
    responseDir = createDir(rootDir, RESPONSE_FOLDER, RESPONSES_FOLDER);
    updateDir = createDir(rootDir, UPDATE_FOLDER, UPDATES_FOLDER);
    sentDir = createDir(rootDir, SENT_FOLDER);
  }

  public File getGeneratedDir()
  {
    File testDir = getTestDir();
    File generatedDir = new File(testDir, GENERATED_FOLDER);
    if (!generatedDir.exists())
    {
      generatedDir.mkdir();
    }
    return generatedDir;
  }

  private File getTestDir()
  {
    File testDir = new File(rootDir, TEST_FOLDER);
    if (!testDir.exists())
    {
      testDir.mkdir();
    }
    return testDir;
  }

  public File getTestCaseDir()
  {
    File testDir = getTestDir();
    File testCaseDir = new File(testDir, TEST_CASES_FOLDER);
    if (!testCaseDir.exists())
    {
      testCaseDir.mkdir();
    }
    return testCaseDir;
  }

  private void readKeyStore()
  {
    File keyStoreFile = new File(rootDir, KEYSTORE_FILE_NAME);
    if (keyStoreFile.exists() && keyStoreFile.isFile())
    {
      String keyStorePassword = connector.getKeyStorePassword();
      if (keyStorePassword.equals(""))
      {
        keyStorePassword = "changeit";
      }
      try
      {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(keyStoreFile);
        try
        {
          keyStore.load(instream, keyStorePassword.toCharArray());
        } finally
        {
          instream.close();
        }
        connector.setKeyStore(keyStore);
      } catch (Exception e)
      {
        e.printStackTrace();
        if (statusLogger != null)
        {
          statusLogger.logError("Unable to load key store file with password '" + keyStorePassword + "'", e);
        }
      }
    }
  }

  private void createWorkingFiles()
  {
    readFilename();
    String newFilename = null;
    File newRequestFile = null;
    File sentFile = new File(sentDir, filename);
    File responseFile = new File(responseDir, filename);
    File updateFile = new File(updateDir, filename);
    int count = 1;
    while (sentFile.exists() || responseFile.exists() || updateFile.exists() || (newRequestFile != null && newRequestFile.exists()))
    {
      count++;
      newFilename = filenameStart + "(" + count + ")." + filenameEnd;
      newRequestFile = new File(requestDir, newFilename);
      sentFile = new File(sentDir, newFilename);
      responseFile = new File(responseDir, newFilename);
      updateFile = new File(updateDir, newFilename);
    }
    if (newRequestFile != null)
    {
      requestFile.renameTo(newRequestFile);
      requestFile = newRequestFile;
      filename = newFilename;
      filenameStart = filenameStart + "(" + count + ")";
    }

  }

  private void closeOutputs()
  {
    if (errorFileOut != null)
    {
      errorFileOut.close();
      errorFileOut = null;
    }
    if (problemFileOut != null)
    {
      problemFileOut.close();
      problemFileOut = null;
    }
  }

  private void readFilename()
  {
    readFilename(requestFile.getName());
  }

  private void readFilename(String f)
  {
    filename = f;
    int pos = filename.lastIndexOf('.');
    if (pos == -1)
    {
      filenameStart = filename;
      filenameEnd = "";
    } else
    {
      filenameStart = filename.substring(0, pos);
      pos++;
      if (pos >= filename.length())
      {
        filenameEnd = "";
      } else
      {
        filenameEnd = filename.substring(pos);
      }
    }
  }

  private boolean lookForFilesToProcess() throws IOException
  {
    statusLogger.logDebug("Looking for files to process");
    verifyOkayToKeepRunning();
    File[] filesToRead = requestDir.listFiles(new FileFilter() {
      public boolean accept(File file)
      {
        String name = file.getName();
        if (file.isFile() && isNotGeneratedName(name))
        {
          return true;
        }
        return false;
      }

      private boolean isNotGeneratedName(String name)
      {
        return name.indexOf("." + REJECTED_NAME + ".") == -1;
      }
    });

    filesToProcessList = new ArrayList<File>();

    for (File fileToRead : filesToRead)
    {
      verifyOkayToKeepRunning();
      long timeSinceLastChange = System.currentTimeMillis() - fileToRead.lastModified();
      statusLogger.logFile(fileToRead.getName(), ScanStatus.LOOKING, 0);
      statusLogger.logDebug(" + Considering '" + fileToRead.getName() + "'");
      long fileChangeTimeout = FILE_CHANGE_TIMEOUT;
      if (!fileToRead.canRead())
      {
        statusLogger.logFile(fileToRead.getName(), ScanStatus.PROBLEM, 0);
        statusLogger.logError("Not allowed to read file: " + fileToRead.getName());
      } else if (fileChangeTimeout > 0 && timeSinceLastChange < fileChangeTimeout)
      {
        statusLogger.logFile(fileToRead.getName(), ScanStatus.WAITING, 0);
        statusLogger.logInfo("File was recently modified, not processing yet: " + fileToRead.getName());
      } else if (!fileContainsHL7(fileToRead))
      {
        statusLogger.logFile(fileToRead.getName(), ScanStatus.PROBLEM, 0);
        statusLogger.logError("File does not contain HL7, not processing: " + fileToRead.getName());
      } else
      {
        statusLogger.logFile(fileToRead.getName(), ScanStatus.PREPARING, 0);
        statusLogger.logDebug("File found to send: " + fileToRead.getName());
        filesToProcessList.add(fileToRead);
      }
    }
    return filesToProcessList.size() > 0;
  }

  private void verifyOkayToKeepRunning()
  {
    if (!okayToRun)
    {
      throw new ApplicationShuttingDown();
    }
  }

  /**
   * Read the file before processing and ensure it looks like what we expect.
   * First non blank line should be file header segment or message header
   * segment. In addition if there is a file header segment then the last non
   * blank line is expected to be the trailing segment. Otherwise the file is
   * assumed to contain HL7 messages. It is important to note that this check
   * does not validate HL7 format, but is built to ensure that the entire file
   * has been transmitted when batch header/footers are sent and that the file
   * doesn't contain obvious non-HL7 content.
   * 
   * @param inFile
   * @return
   * @throws IOException
   */
  private boolean fileContainsHL7(File inFile) throws IOException
  {
    BufferedReader in = new BufferedReader(new FileReader(inFile));
    String line = readRealFirstLine(in);
    boolean okay;
    if (line.startsWith(HL7.FHS))
    {
      String lastLine = line;
      while ((line = in.readLine()) != null)
      {
        if (line.trim().length() > 0)
        {
          lastLine = line;
        }
      }
      okay = lastLine.startsWith(HL7.FTS);
      if (!okay)
      {
        statusLogger.logFile(inFile.getName(), ScanStatus.PROBLEM, 0);
        statusLogger.logWarn("File does not end with FTS segment as expected, not processing: " + inFile.getName());
      }
    } else if (line.startsWith(HL7.MSH))
    {
      statusLogger.logDebug("File does not start with FHS segment as expected: " + inFile.getName());
      okay = true;
    } else
    {
      okay = false;
      statusLogger.logFile(inFile.getName(), ScanStatus.PROBLEM, 0);
      statusLogger.logWarn("File does not appear to contain HL7 (Must start with FHS or MSH segment): " + inFile.getName());
    }
    in.close();
    return okay;
  }

  private File createDir(File dir, String preferredName, String alternateName)
  {
    File file = new File(dir, preferredName);
    if (!file.exists())
    {
      File alternateFile = new File(dir, alternateName);
      if (alternateFile.exists())
      {
        file = alternateFile;
      } else
      {
        file.mkdir();
        statusLogger.logInfo("Creating new folder: " + file.getName());
      }
    }
    return file;
  }

  private File createDir(File rootDir, String filename)
  {
    File file = new File(rootDir, filename);
    if (!file.exists())
    {
      file.mkdir();
      statusLogger.logInfo("Creating new folder: " + file.getName());
    }
    return file;
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
      if (backupFileOut != null)
      {
        backupFileOut.print(line);
        backupFileOut.print("\r");
      }
      line = line.trim();
      if (line.length() > 0 && !line.startsWith("--- ") && !line.equals("---"))
      {
        break;
      }
    }
    return line;
  }

  private String readScript() throws FileNotFoundException, IOException
  {
    StringBuilder script = new StringBuilder();
    BufferedReader in = new BufferedReader(new FileReader(configFile));
    String line = null;
    while ((line = in.readLine()) != null)
    {
      script.append(line);
      script.append("\n");
    }
    in.close();
    return script.toString();
  }

  private String getMessageNumberString()
  {
    String messageNumberString = String.valueOf(messageNumber);
    if (messageNumberString.length() > 5)
    {
      return messageNumberString;
    } else
    {
      messageNumberString = "0000" + messageNumberString;
      return messageNumberString.substring(messageNumberString.length() - 5);
    }
  }
}
