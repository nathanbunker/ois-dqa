package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public class CodeMaster implements Serializable
{
  private int codeId = 0;
  private CodeTable table = null;
  private String codeValue = "";

  public int getCodeId()
  {
    return codeId;
  }

  public void setCodeId(int codeId)
  {
    this.codeId = codeId;
  }

  public CodeTable getTable()
  {
    return table;
  }

  public void setTable(CodeTable table)
  {
    this.table = table;
  }

  public String getCodeValue()
  {
    return codeValue;
  }

  public void setCodeValue(String codeValue)
  {
    this.codeValue = codeValue;
  }
}
