package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;
import java.util.Date;

public class TesterStatus implements Serializable
{
  private int testerStatusId = 0;
  private String testerName = "";
  private String readyStatus = "";
  private Date statusDate = null;
  private TestConducted testConducted = null;
  private Date etcQueryDate = null;
  private Date etcUpdateDate = null;

  public int getTesterStatusId()
  {
    return testerStatusId;
  }

  public void setTesterStatusId(int testerStatusId)
  {
    this.testerStatusId = testerStatusId;
  }

  public String getTesterName()
  {
    return testerName;
  }

  public void setTesterName(String testerName)
  {
    this.testerName = testerName;
  }

  public String getReadyStatus()
  {
    return readyStatus;
  }

  public void setReadyStatus(String testerStatus)
  {
    this.readyStatus = testerStatus;
  }

  public Date getStatusDate()
  {
    return statusDate;
  }

  public void setStatusDate(Date statusDate)
  {
    this.statusDate = statusDate;
  }

  public TestConducted getTestConducted()
  {
    return testConducted;
  }

  public void setTestConducted(TestConducted testConducted)
  {
    this.testConducted = testConducted;
  }

  public Date getEtcQueryDate()
  {
    return etcQueryDate;
  }

  public void setEtcQueryDate(Date etcQueryDate)
  {
    this.etcQueryDate = etcQueryDate;
  }

  public Date getEtcUpdateDate()
  {
    return etcUpdateDate;
  }

  public void setEtcUpdateDate(Date etcUpdateDate)
  {
    this.etcUpdateDate = etcUpdateDate;
  }
}
