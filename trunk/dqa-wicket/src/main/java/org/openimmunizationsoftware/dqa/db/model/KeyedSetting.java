package org.openimmunizationsoftware.dqa.db.model;

public class KeyedSetting
{
  
  public static final String IN_FILE_ENABLE = "in.file.enabled";
  public static final String IN_FILE_DIR = "in.file.dir";
  public static final String IN_FILE_WAIT = "in.file.wait";
  public static final String IN_FILE_PROCESSED_DIR_NAME = "in.file.processed_dir.name";
  public static final String IN_FILE_RECEIVE_DIR_NAME = "in.file.receive_dir.name";
  public static final String IN_FILE_SUBMIT_DIR_NAME = "in.file.submit_dir.name";
  
  private String keyedCode = "";
  private String objectCode = "";
  private int objectId = 0;
  private String keyedValue = "";
  private int keyedId = 0;
  
  public int getKeyedId()
  {
    return keyedId;
  }
  public void setKeyedId(int keyedId)
  {
    this.keyedId = keyedId;
  }
  public String getKeyedCode()
  {
    return keyedCode;
  }
  public void setKeyedCode(String keyedCode)
  {
    this.keyedCode = keyedCode;
  }
  public String getObjectCode()
  {
    return objectCode;
  }
  public void setObjectCode(String objectCode)
  {
    this.objectCode = objectCode;
  }
  public int getObjectId()
  {
    return objectId;
  }
  public void setObjectId(int objectId)
  {
    this.objectId = objectId;
  }
  public String getKeyedValue()
  {
    return keyedValue;
  }
  public void setKeyedValue(String keyedValue)
  {
    this.keyedValue = keyedValue;
  }
}
