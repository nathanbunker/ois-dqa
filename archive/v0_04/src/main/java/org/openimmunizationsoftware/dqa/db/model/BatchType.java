package org.openimmunizationsoftware.dqa.db.model;

public class BatchType
{
  public static final BatchType SUBMISSION = new BatchType("S", "Submission");
  public static final BatchType DAILY = new BatchType("D", "Daily");
  public static final BatchType WEEKLY = new BatchType("W", "Weekly");
  public static final BatchType MONTHLY = new BatchType("M", "Monthly");
  public static final BatchType OTHER = new BatchType("O", "Other");
  
  private String typeCode = "";
  private String typeLabel = "";
  
  private BatchType()
  {
    // default
  }
  
  private BatchType(String typeCode, String typeLabel)
  {
    this.typeCode = typeCode;
    this.typeLabel = typeLabel;
  }

  public String getTypeCode()
  {
    return typeCode;
  }
  public void setTypeCode(String typeCode)
  {
    this.typeCode = typeCode;
  }
  public String getTypeLabel()
  {
    return typeLabel;
  }
  public void setTypeLabel(String typeLabel)
  {
    this.typeLabel = typeLabel;
  }
  
}
