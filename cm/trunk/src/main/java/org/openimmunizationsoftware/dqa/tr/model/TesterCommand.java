package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;
import java.util.Date;

public class TesterCommand implements Serializable
{
  private int testerCommandId = 0;
  private String testerName = "";
  private TestParticipant testParticipant = null;
  private String commandText = "";
  private String commandOptions = "";
  private Date runDate = null;

  public int getTesterCommandId()
  {
    return testerCommandId;
  }

  public void setTesterCommandId(int testerStatusId)
  {
    this.testerCommandId = testerStatusId;
  }

  public String getTesterName()
  {
    return testerName;
  }

  public void setTesterName(String testerName)
  {
    this.testerName = testerName;
  }

  public TestParticipant getTestParticipant()
  {
    return testParticipant;
  }

  public void setTestParticipant(TestParticipant testParticipant)
  {
    this.testParticipant = testParticipant;
  }

  public String getCommandText()
  {
    return commandText;
  }

  public void setCommandText(String commandText)
  {
    this.commandText = commandText;
  }

  public String getCommandOptions()
  {
    return commandOptions;
  }

  public void setCommandOptions(String commandOptions)
  {
    this.commandOptions = commandOptions;
  }

  public Date getRunDate()
  {
    return runDate;
  }

  public void setRunDate(Date runDate)
  {
    this.runDate = runDate;
  }
}
