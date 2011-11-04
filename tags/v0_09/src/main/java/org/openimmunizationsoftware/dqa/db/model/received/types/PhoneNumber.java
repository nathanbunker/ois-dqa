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
    if (number == null || number.equals(""))
    {
      if (localNumber != null && !localNumber.equals(""))
      {
        StringBuilder sb = new StringBuilder();
        if (areaCode != null && !areaCode.equals(""))
        {
          sb.append("(");
          sb.append(areaCode);
          sb.append(")");
        }
        if (localNumber != null && !localNumber.equals(""))

        {
          if (localNumber.length() == 7)
          {
            sb.append(localNumber.substring(0, 3));
            sb.append("-");
            sb.append(localNumber.substring(3, 7));
          } else
          {
            sb.append(localNumber);
          }
        }
        return sb.toString();
      }
      return "";
    }
    return number;
  }

  public void setNumber(String number)
  {
    this.number = number;
    if ((number != null && !number.equals("")) && (localNumber == null || localNumber.equals("")))
    {
      if (areaCode == null || areaCode.equals(""))
      {
        StringBuilder justDigits = new StringBuilder();
        for (char c : number.toCharArray())
        {
          if (c >= '0' && c <= '9')
          {
            justDigits.append(c);
          }
        }
        if (justDigits.length() == 7)
        {
          this.localNumber = justDigits.toString();
        } else if (justDigits.length() == 10)
        {
          this.areaCode = justDigits.toString().substring(0, 3);
          this.localNumber = justDigits.toString().substring(3, 10);
        }
      }
    }
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
