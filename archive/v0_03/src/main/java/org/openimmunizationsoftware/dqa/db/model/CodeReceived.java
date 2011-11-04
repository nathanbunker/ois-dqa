package org.openimmunizationsoftware.dqa.db.model;

public class CodeReceived
{
  private long codeId = 0l;
  private SubmitterProfile profile = null;
  private CodeTable table = null;
  private String receivedValue = "";
  private String codeValue = "";
  private CodeStatus codeStatus = null;
  private int receivedCount = 0;  
  
  public CodeReceived()
  {
    // default
  }
  
  public CodeReceived(CodeReceived parent, SubmitterProfile profile)
  {
    this.profile = profile;
    this.table = parent.table;
    this.receivedValue = parent.receivedValue;
    this.codeValue = parent.codeValue;
    this.codeStatus = parent.codeStatus;
    this.receivedCount = 0;
  }
  
  public long getCodeId()
  {
    return codeId;
  }
  public void setCodeId(long codeId)
  {
    this.codeId = codeId;
  }
  public SubmitterProfile getProfile()
  {
    return profile;
  }
  public void setProfile(SubmitterProfile profile)
  {
    this.profile = profile;
  }
  public CodeTable getTable()
  {
    return table;
  }
  public void setTable(CodeTable table)
  {
    this.table = table;
  }
  public String getReceivedValue()
  {
    return receivedValue;
  }
  public void setReceivedValue(String receivedValue)
  {
    this.receivedValue = receivedValue;
  }
  public String getCodeValue()
  {
    return codeValue;
  }
  public void setCodeValue(String codeValue)
  {
    this.codeValue = codeValue;
  }
  public CodeStatus getCodeStatus()
  {
    return codeStatus;
  }
  public void setCodeStatus(CodeStatus codeStatus)
  {
    this.codeStatus = codeStatus;
  }
  public int getReceivedCount()
  {
    return receivedCount;
  }
  public void setReceivedCount(int receivedCount)
  {
    this.receivedCount = receivedCount;
  }
  
  public void incReceivedCount()
  {
    this.receivedCount++;
  }
}
