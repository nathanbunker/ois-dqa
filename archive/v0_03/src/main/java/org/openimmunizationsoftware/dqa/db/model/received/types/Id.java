package org.openimmunizationsoftware.dqa.db.model.received.types;

public class Id
{
  private String number = "";
  private String assigningAuthority = "";
  private String typeCode = "";
  private Name name = null;
  
  public String getNumber()
  {
    return number;
  }
  public void setNumber(String number)
  {
    this.number = number;
  }
  public String getAssigningAuthority()
  {
    return assigningAuthority;
  }
  public void setAssigningAuthority(String assigningAuthority)
  {
    this.assigningAuthority = assigningAuthority;
  }
  public String getTypeCode()
  {
    return typeCode;
  }
  public void setTypeCode(String typeCode)
  {
    this.typeCode = typeCode;
  }
  
  public Name getName()
  {
    if (name == null)
    {
      name = new Name();
    }
    return name;
  }
}
