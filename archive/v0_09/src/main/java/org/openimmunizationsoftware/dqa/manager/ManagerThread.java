package org.openimmunizationsoftware.dqa.manager;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class ManagerThread extends Thread
{
  protected StringBuilder internalLog = new StringBuilder();
  protected boolean keepRunning = true;
  protected String label = "";
  protected Throwable lastException = null;
  protected int progressCount = 0;
  protected long progressStart = 0l;

  public ManagerThread(String label) {
    this.label = label;
  }

  public StringBuilder getInternalLog()
  {
    return internalLog;
  }

  public String getLabel()
  {
    return label;
  }

  public Throwable getLastException()
  {
    return lastException;
  }

  public int getProgressCount()
  {
    return progressCount;
  }

  public long getProgressStart()
  {
    return progressStart;
  }

  protected Date getTimeToday(String startTimeString)
  {
    String timeString = startTimeString;
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    String[] parts = startTimeString.split("\\:");
    if (parts.length == 2)
    {
      try
      {
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
        cal.set(Calendar.SECOND, 0);
        date = cal.getTime();
      } catch (NumberFormatException nfe)
      {
        // bad number, ignore
      }
    }
    return date;
  }

  public boolean isKeepRunning()
  {
    return keepRunning;
  }

  public synchronized void runNow(Date now) throws IOException
  {
    throw new IllegalArgumentException("This method has not been written");
  }

}
