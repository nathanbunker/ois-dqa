package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public class CodeTable implements Serializable
{
  private int tableId = 0;
  private CodeTable contextTable = null;

  public CodeTable getContextTable()
  {
    return contextTable;
  }

  public void setContextTable(CodeTable contextTable)
  {
    this.contextTable = contextTable;
  }

  public int getTableId()
  {
    return tableId;
  }

  public void setTableId(int tableId)
  {
    this.tableId = tableId;
  }
}
