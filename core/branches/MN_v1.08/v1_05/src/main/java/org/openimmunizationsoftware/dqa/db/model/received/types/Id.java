package org.openimmunizationsoftware.dqa.db.model.received.types;

import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.CodeTable;

public class Id
{
  private CodedEntity assigningAuthority = new CodedEntity(CodeTable.Type.ID_ASSIGNING_AUTHORITY);
  private CodeReceived codeReceived = null;
  private CodeTable.Type tableType = null;
  private Name name = null;
  private String number = "";
  private CodedEntity type = new CodedEntity(CodeTable.Type.ID_TYPE_CODE);
  
  public boolean isEmpty()
  {
    return number == null || number.isEmpty();
  }

  public Id(CodeTable.Type tableType) {
    this.tableType = tableType;
  }

  public CodedEntity getAssigningAuthority()
  {
    return assigningAuthority;
  }

  public String getAssigningAuthorityCode()
  {
    return assigningAuthority.getCode();
  }

  public CodeReceived getCodeReceived()
  {
    return codeReceived;
  }


  public CodeTable.Type getTableType()
  {
    return tableType;
  }
  public Name getName()
  {
    if (name == null)
    {
      name = new Name();
    }
    return name;
  }
  public String getNumber()
  {
    return number;
  }
  public CodedEntity getType()
  {
    return type;
  }
  public String getTypeCode()
  {
    return type.getCode();
  }
  public void setAssigningAuthorityCode(String assigningAuthorityCode)
  {
    this.assigningAuthority.setCode(assigningAuthorityCode);
  }
  public void setCodeReceived(CodeReceived codeReceived)
  {
    this.codeReceived = codeReceived;
  }
  public void setNumber(String number)
  {
    this.number = number;
  }
  
  public void setTypeCode(String typeCode)
  {
    this.type.setCode(typeCode);
  }
  
}
