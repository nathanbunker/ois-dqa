package org.openimmunizationsoftware.dqa.db.model.received.types;

public class Name
{
  private String last = "";
  private String first = "";
  private String middle = "";
  private String suffix = "";
  private String prefix = "";
  private String typeCode = "";
  
  public String getLast()
  {
    return last;
  }
  public void setLast(String last)
  {
    this.last = last;
  }
  public String getFirst()
  {
    return first;
  }
  public void setFirst(String first)
  {
    this.first = first;
  }
  public String getMiddle()
  {
    return middle;
  }
  public void setMiddle(String middle)
  {
    this.middle = middle;
  }
  public String getSuffix()
  {
    return suffix;
  }
  public void setSuffix(String suffix)
  {
    this.suffix = suffix;
  }
  public String getPrefix()
  {
    return prefix;
  }
  public void setPrefix(String prefix)
  {
    this.prefix = prefix;
  }
  public String getTypeCode()
  {
    return typeCode;
  }
  public void setTypeCode(String typeCode)
  {
    this.typeCode = typeCode;
  }
}
