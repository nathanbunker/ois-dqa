package org.openimmunizationsoftware.dqa.db.model;

public class CodeTable
{
  private int tableId = 0;
  private String tableLabel = "";
  private String defaultCodeValue = "";
  
  public int getTableId()
  {
    return tableId;
  }
  public void setTableId(int tableId)
  {
    this.tableId = tableId;
  }
  public String getTableLabel()
  {
    return tableLabel;
  }
  public void setTableLabel(String tableLabel)
  {
    this.tableLabel = tableLabel;
  }
  public String getDefaultCodeValue()
  {
    return defaultCodeValue;
  }
  public void setDefaultCodeValue(String defaultCodeValue)
  {
    this.defaultCodeValue = defaultCodeValue;
  }
}
