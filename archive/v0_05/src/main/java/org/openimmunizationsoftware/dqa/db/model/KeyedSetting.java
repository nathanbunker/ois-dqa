package org.openimmunizationsoftware.dqa.db.model;

public class KeyedSetting
{
  
  public static final String IN_FILE_ENABLE = "in.file.enabled";
  public static final String IN_FILE_DIR = "in.file.dir";
  public static final String IN_FILE_WAIT = "in.file.wait";
  public static final String IN_FILE_ACCEPTED_DIR_NAME = "in.file.accepted_dir.name";
  public static final String IN_FILE_RECEIVE_DIR_NAME = "in.file.receive_dir.name";
  public static final String IN_FILE_SUBMIT_DIR_NAME = "in.file.submit_dir.name";
  public static final String IN_FILE_DQA_DIR_NAME = "in.file.dqa_dir.name";
  public static final String OUT_FILE_DIR = "out.file.dir";
  public static final String OUT_HL7_MSH_RECEIVING_APPLICATION = "out.hl7.msh.receiving_application";
  public static final String OUT_HL7_MSH_RECEIVING_FACILITY = "out.hl7.msh.receiving_facility";
  public static final String OUT_HL7_MSH_SENDING_APPLICATION = "out.hl7.msh.sending_application";
  public static final String OUT_HL7_MSH_PROCESSING_ID = "out.hl7.msh.processing_id";
  public static final String OUT_HL7_MSH_VERSION_ID = "out.hl7.msh.version_id";
  public static final String WEEKLY_BATCH_DAY = "weekly.batch.day";
  public static final String WEEKLY_BATCH_START_TIME = "weekly.batch.start_time";
  public static final String WEEKLY_BATCH_END_TIME = "weekly.batch.end_time";
  public static final String WEEKLY_EXPORT_DAY_HIGHEST = "weekly.export.day.highest";
  public static final String WEEKLY_EXPORT_DAY_HIGH = "weekly.export.day.high";
  public static final String WEEKLY_EXPORT_DAY_NORMAL = "weekly.export.day.normal";
  public static final String WEEKLY_EXPORT_DAY_LOW = "weekly.export.day.low";
  public static final String WEEKLY_EXPORT_DAY_LOWEST = "weekly.export.day.lowest";
  public static final String WEEKLY_EXPORT_START_TIME = "weekly.export.start_time";
  public static final String WEEKLY_EXPORT_END_TIME = "weekly.export.end_time";
  
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
