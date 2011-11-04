package org.openimmunizationsoftware.dqa.db.model.received.types;

public class PhoneNumber
{
  private String number = "";
  private String telUseCode = "";
  private String telEquipCode = "";
  private String email = "";
  private String countryCode = "";
  private String areaCode = "";
  private String localNumber = "";
  private String extension = "";
  
  public String getNumber()
  {
    return number;
  }
  public void setNumber(String number)
  {
    this.number = number;
  }
  public String getTelUseCode()
  {
    return telUseCode;
  }
  public void setTelUseCode(String telUseCode)
  {
    this.telUseCode = telUseCode;
  }
  public String getTelEquipCode()
  {
    return telEquipCode;
  }
  public void setTelEquipCode(String telEquipCode)
  {
    this.telEquipCode = telEquipCode;
  }
  public String getEmail()
  {
    return email;
  }
  public void setEmail(String email)
  {
    this.email = email;
  }
  public String getCountryCode()
  {
    return countryCode;
  }
  public void setCountryCode(String countryCode)
  {
    this.countryCode = countryCode;
  }
  public String getAreaCode()
  {
    return areaCode;
  }
  public void setAreaCode(String areaCode)
  {
    this.areaCode = areaCode;
  }
  public String getLocalNumber()
  {
    return localNumber;
  }
  public void setLocalNumber(String localNumber)
  {
    this.localNumber = localNumber;
  }
  public String getExtension()
  {
    return extension;
  }
  public void setExtension(String extension)
  {
    this.extension = extension;
  }
}
