package org.openimmunizationsoftware.dqa.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.construct.ConstructFactory;
import org.openimmunizationsoftware.dqa.construct.ConstructerInterface;
import org.openimmunizationsoftware.dqa.db.model.KeyedSetting;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.ReceiveQueue;
import org.openimmunizationsoftware.dqa.db.model.SubmitStatus;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.received.NextOfKin;
import org.openimmunizationsoftware.dqa.db.model.received.Patient;
import org.openimmunizationsoftware.dqa.db.model.received.Vaccination;

public class WeeklyExportManager extends ManagerThread
{

  private static WeeklyExportManager singleton = null;

  public static WeeklyExportManager getWeeklyExportManager()
  {
    if (singleton == null)
    {
      singleton = new WeeklyExportManager();
      singleton.start();
    }
    return singleton;
  }

  private WeeklyExportManager() {
    super("Weekly Export Manager");
  }

  private Date processingStartTime;
  private Date processingEndTime;
  private int exportDayHighest;
  private int exportDayHigh;
  private int exportDayNormal;
  private int exportDayLow;
  private int exportDayLowest;
  private File exportDir = null;

  private PrintWriter out = null;

  private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

  @Override
  public void run()
  {
    internalLog.append("Starting weekly export manager\r");
    while (keepRunning)
    {
      try
      {
        boolean okayToProceed = setWeeklyParameters();
        if (okayToProceed)
        {
          Date now = new Date();
          if (processingStartTime.before(now) && processingEndTime.before(now))
          {
            runNow(now);
          } else
          {
            internalLog.append("Weekly Export Manager will not run now\r");
          }
        }
      } catch (Exception e)
      {
        e.printStackTrace();
        lastException = e;
      }

      try
      {
        synchronized (sdf)
        {
          sdf.wait(10 * 1000 * 60);
        }
      } catch (InterruptedException ie)
      {
        keepRunning = false;
        return;
      }
      internalLog.setLength(0);
    }
  }

  @Override
  public void runNow(Date now) throws IOException
  {
    internalLog.append("Running now\n");
    Calendar cal = Calendar.getInstance();
    cal.setTime(now);
    int today = cal.get(Calendar.DAY_OF_WEEK);
    if (today >= exportDayHighest)
    {
      export(SubmitterProfile.TRANSFER_PRIORITY_HIGHEST);
    }
    if (today >= exportDayHigh)
    {
      export(SubmitterProfile.TRANSFER_PRIORITY_HIGH);
    }
    if (today >= exportDayNormal)
    {
      export(SubmitterProfile.TRANSFER_PRIORITY_NORMAL);
    }
    if (today >= exportDayLow)
    {
      export(SubmitterProfile.TRANSFER_PRIORITY_LOW);
    }
    if (today >= exportDayLowest)
    {
      export(SubmitterProfile.TRANSFER_PRIORITY_LOWEST);
    }
    internalLog.append("Finished exporting\n");
  }

  private void export(String transferPriority) throws IOException
  {
    internalLog.append("Exporting profiles with " + transferPriority + " priority\n");
    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    Query query = session.createQuery("from SubmitterProfile where profileStatus = ? and transferPriority = ?");
    query.setParameter(0, SubmitterProfile.PROFILE_STATUS_PROD);
    query.setParameter(1, transferPriority);
    List<SubmitterProfile> submitterProfiles = query.list();
    for (SubmitterProfile profile : submitterProfiles)
    {
      internalLog.append("Looking for exports under profile " + profile.getProfileCode() + "\n");
      Transaction tx = session.beginTransaction();
      query = session.createQuery("from MessageBatch where submitStatus = ? and profile = ? order by endDate");
      query.setParameter(0, SubmitStatus.PREPARED);
      query.setParameter(1, profile);
      List<MessageBatch> messageBatches = query.list();
      for (MessageBatch messageBatch : messageBatches)
      {
        openOutputFile(profile, messageBatch.getEndDate());
        export(messageBatch, profile, session);
        out.close();
      }
      tx.commit();
    }
    session.close();
  }

  private void export(MessageBatch messageBatch, SubmitterProfile profile, Session session)
  {
    internalLog.append(" + " + messageBatch.getEndDate() + "\n");
    Query query = session.createQuery("from ReceiveQueue where messageBatch = ? and submitStatus = ?");
    query.setParameter(0, messageBatch);
    query.setParameter(1, SubmitStatus.PREPARED);
    List<ReceiveQueue> receiveQueues = query.list();
    internalLog.append(" + receiveQueues.size() = " + receiveQueues.size() + "\n");
    for (ReceiveQueue receiveQueue : receiveQueues)
    {
      MessageReceived messageReceived = receiveQueue.getMessageReceived();
      query = session.createQuery("from Patient where messageReceived = ?");
      query.setParameter(0, messageReceived);
      List<Patient> patients = query.list();
      if (patients.size() == 0)
      {
        // This should not happen, but if it does
        receiveQueue.setSubmitStatus(SubmitStatus.EXCLUDED);
        continue;
      }
      messageReceived.setPatient(patients.get(0));
      query = session.createQuery("from NextOfKin where messageReceived = ?");
      query.setParameter(0, messageReceived);
      List<NextOfKin> nextOfKins = query.list();
      messageReceived.setNextOfKins(nextOfKins);
      query = session.createQuery("from Vaccination where messageReceived = ?");
      query.setParameter(0, messageReceived);
      List<Vaccination> vaccinations = query.list();
      messageReceived.setVaccinations(vaccinations);
      ConstructerInterface constructor = ConstructFactory.getConstructer(profile);
      out.print(constructor.constructMessage(messageReceived));
      messageReceived.setSubmitStatus(SubmitStatus.SUBMITTED);
      receiveQueue.setSubmitStatus(SubmitStatus.SUBMITTED);
    }
    messageBatch.setSubmitStatus(SubmitStatus.SUBMITTED);
  }

  private boolean setWeeklyParameters()
  {
    {
      KeyedSettingManager ksm = KeyedSettingManager.getKeyedSettingManager();
      exportDir = new File(ksm.getKeyedValue(KeyedSetting.OUT_FILE_DIR, "c:\\data\\out"));
      if (!exportDir.exists())
      {
        internalLog.append("Internal dir '" + exportDir + "' does not exist, unable to export");
        return false;
      }
      exportDayHighest = ksm.getKeyedValueInt(KeyedSetting.WEEKLY_EXPORT_DAY_HIGHEST, 2);
      exportDayHigh = ksm.getKeyedValueInt(KeyedSetting.WEEKLY_EXPORT_DAY_HIGH, 2);
      exportDayNormal = ksm.getKeyedValueInt(KeyedSetting.WEEKLY_EXPORT_DAY_NORMAL, 2);
      exportDayLow = ksm.getKeyedValueInt(KeyedSetting.WEEKLY_EXPORT_DAY_LOW, 2);
      exportDayLowest = ksm.getKeyedValueInt(KeyedSetting.WEEKLY_EXPORT_DAY_LOWEST, 2);
      processingStartTime = getTimeToday(ksm.getKeyedValue(KeyedSetting.WEEKLY_EXPORT_START_TIME, "17:00"));
      processingEndTime = getTimeToday(ksm.getKeyedValue(KeyedSetting.WEEKLY_EXPORT_END_TIME, "19:00"));
      internalLog.append("Highest priority export day = " + exportDayHighest + "\r");
      internalLog.append("High priority export day = " + exportDayHigh + "\r");
      internalLog.append("Normal priority export day = " + exportDayNormal + "\r");
      internalLog.append("Low priority export day = " + exportDayLow + "\r");
      internalLog.append("Lowest priority export day = " + exportDayLowest + "\r");
      internalLog.append("Processing start time = " + processingStartTime + "\r");
      internalLog.append("Processing end time = " + processingEndTime + "\r");
      return true;
    }
  }

  private void openOutputFile(SubmitterProfile profile, Date generateDate) throws IOException
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(generateDate);
    String filename = createFilename(cal, profile);
    File file = new File(exportDir, filename);
    try
    {
      out = new PrintWriter(new FileWriter(file));
    } catch (IOException ioe)
    {
      internalLog.append("Unable to open file '" + filename + "'");
      lastException = ioe;
      throw ioe;
    }
  }

  private String createFilename(Calendar endOfWeek, SubmitterProfile profile)
  {
    return profile.getProfileCode() + pad(2000 - endOfWeek.get(Calendar.YEAR), 2)
        + pad(endOfWeek.get(Calendar.DAY_OF_YEAR), 3) + ".hl7";
  }

  private String pad(int i, int pad)
  {
    String s = ("000" + i);
    return s.substring(s.length() - pad);
  }

}
