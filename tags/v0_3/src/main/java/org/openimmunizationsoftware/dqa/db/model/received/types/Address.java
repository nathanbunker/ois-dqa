package org.openimmunizationsoftware.dqa.db.model.received.types;

public class Address
{
  private String street = "";
  private String street2 = "";
  private String city = "";
  private String state = "";
  private String zip = "";
  private String country = "";
  private String countyParish = "";
  private String type = "";
  
  public String getStreet()
  {
    return street;
  }
  public void setStreet(String street)
  {
    this.street = street;
  }
  public String getStreet2()
  {
    return street2;
  }
  public void setStreet2(String street2)
  {
    this.street2 = street2;
  }
  public String getCity()
  {
    return city;
  }
  public void setCity(String city)
  {
    this.city = city;
  }
  public String getState()
  {
    return state;
  }
  public void setState(String state)
  {
    this.state = state;
  }
  public String getZip()
  {
    return zip;
  }
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  public String getCountry()
  {
    return country;
  }
  public void setCountry(String country)
  {
    this.country = country;
  }
  public String getCountyParish()
  {
    return countyParish;
  }
  public void setCountyParish(String countyParish)
  {
    this.countyParish = countyParish;
  }
  public String getType()
  {
    return type;
  }
  public void setType(String type)
  {
    this.type = type;
  }
}
