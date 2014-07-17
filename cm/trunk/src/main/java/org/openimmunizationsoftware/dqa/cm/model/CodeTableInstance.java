package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public class CodeTableInstance implements Serializable
{
  private int tableInstanceId = 0;
  private CodeTable table = null;
  private ReleaseVersion release = null;
  private String tableLabel = "";
  private boolean enforceUniqe = false;
  private InclusionStatus inclusionStatus = null;
  private int issueCount = 0;

  public int getIssueCount()
  {
    return issueCount;
  }

  public void setIssueCount(int issueCount)
  {
    this.issueCount = issueCount;
  }
  public InclusionStatus getInclusionStatus()
  {
    return inclusionStatus;
  }

  public void setInclusionStatus(InclusionStatus inclusionStatus)
  {
    this.inclusionStatus = inclusionStatus;
  }

  public int getTableInstanceId()
  {
    return tableInstanceId;
  }

  public void setTableInstanceId(int tableInstanceId)
  {
    this.tableInstanceId = tableInstanceId;
  }

  public CodeTable getTable()
  {
    return table;
  }

  public void setTable(CodeTable table)
  {
    this.table = table;
  }

  public ReleaseVersion getRelease()
  {
    return release;
  }

  public void setRelease(ReleaseVersion release)
  {
    this.release = release;
  }

  public String getTableLabel()
  {
    return tableLabel;
  }

  public void setTableLabel(String tableLabel)
  {
    this.tableLabel = tableLabel;
  }

  public boolean isEnforceUniqe()
  {
    return enforceUniqe;
  }

  public void setEnforceUniqe(boolean enforceUniqe)
  {
    this.enforceUniqe = enforceUniqe;
  }

  public String getInclusionStatusString()
  {
    if (inclusionStatus == null)
    {
      return null;
    }
    return inclusionStatus.getId();
  }

  public void setInclusionStatusString(String inclusionStatusString)
  {
    this.inclusionStatus = InclusionStatus.get(inclusionStatusString);
  }
}
