package org.openimmunizationsoftware.pt.model;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * ProjectContact generated by hbm2java
 */
public class ProjectContact implements java.io.Serializable
{
  
  private int contactId;
  private String nameLast;
  private String nameFirst;
  private String nameTitle;
  private String organizationName;
  private String departmentName;
  private String positionTitle;
  private String numberPhone;
  private String numberCell;
  private String numberPager;
  private String numberFax;
  private String email;
  private String addressStreet1;
  private String addressStreet2;
  private String addressCity;
  private String addressState;
  private String addressZip;
  private String addressCountry;
  private Float addressLat;
  private Float addressLong;
  private String contactInfo;
  private String timeZone;
  private String emailAlert;
  private String providerId;

  public ProjectContact() {
  }
  
  public String getName()
  {
    return nameFirst + " " + nameLast;
  }

  public ProjectContact(int contactId, String nameLast, String nameFirst, String emailAlert, String providerId) {
    this.contactId = contactId;
    this.nameLast = nameLast;
    this.nameFirst = nameFirst;
    this.emailAlert = emailAlert;
    this.providerId = providerId;
  }

  public ProjectContact(int contactId, String nameLast, String nameFirst, String nameTitle, String organizationName, String departmentName,
      String positionTitle, String numberPhone, String numberCell, String numberPager, String numberFax, String email, String addressStreet1,
      String addressStreet2, String addressCity, String addressState, String addressZip, String addressCountry, Float addressLat, Float addressLong,
      String contactInfo, String timeZone, String emailAlert, String providerId) {
    this.contactId = contactId;
    this.nameLast = nameLast;
    this.nameFirst = nameFirst;
    this.nameTitle = nameTitle;
    this.organizationName = organizationName;
    this.departmentName = departmentName;
    this.positionTitle = positionTitle;
    this.numberPhone = numberPhone;
    this.numberCell = numberCell;
    this.numberPager = numberPager;
    this.numberFax = numberFax;
    this.email = email;
    this.addressStreet1 = addressStreet1;
    this.addressStreet2 = addressStreet2;
    this.addressCity = addressCity;
    this.addressState = addressState;
    this.addressZip = addressZip;
    this.addressCountry = addressCountry;
    this.addressLat = addressLat;
    this.addressLong = addressLong;
    this.contactInfo = contactInfo;
    this.timeZone = timeZone;
    this.emailAlert = emailAlert;
    this.providerId = providerId;
  }

  public int getContactId()
  {
    return this.contactId;
  }

  public void setContactId(int contactId)
  {
    this.contactId = contactId;
  }

  public String getNameLast()
  {
    return this.nameLast;
  }

  public void setNameLast(String nameLast)
  {
    this.nameLast = nameLast;
  }

  public String getNameFirst()
  {
    return this.nameFirst;
  }

  public void setNameFirst(String nameFirst)
  {
    this.nameFirst = nameFirst;
  }

  public String getNameTitle()
  {
    return this.nameTitle;
  }

  public void setNameTitle(String nameTitle)
  {
    this.nameTitle = nameTitle;
  }

  public String getOrganizationName()
  {
    return this.organizationName;
  }

  public void setOrganizationName(String organizationName)
  {
    this.organizationName = organizationName;
  }

  public String getDepartmentName()
  {
    return this.departmentName;
  }

  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }

  public String getPositionTitle()
  {
    return this.positionTitle;
  }

  public void setPositionTitle(String positionTitle)
  {
    this.positionTitle = positionTitle;
  }

  public String getNumberPhone()
  {
    return this.numberPhone;
  }

  public void setNumberPhone(String numberPhone)
  {
    this.numberPhone = numberPhone;
  }

  public String getNumberCell()
  {
    return this.numberCell;
  }

  public void setNumberCell(String numberCell)
  {
    this.numberCell = numberCell;
  }

  public String getNumberPager()
  {
    return this.numberPager;
  }

  public void setNumberPager(String numberPager)
  {
    this.numberPager = numberPager;
  }

  public String getNumberFax()
  {
    return this.numberFax;
  }

  public void setNumberFax(String numberFax)
  {
    this.numberFax = numberFax;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getAddressStreet1()
  {
    return this.addressStreet1;
  }

  public void setAddressStreet1(String addressStreet1)
  {
    this.addressStreet1 = addressStreet1;
  }

  public String getAddressStreet2()
  {
    return this.addressStreet2;
  }

  public void setAddressStreet2(String addressStreet2)
  {
    this.addressStreet2 = addressStreet2;
  }

  public String getAddressCity()
  {
    return this.addressCity;
  }

  public void setAddressCity(String addressCity)
  {
    this.addressCity = addressCity;
  }

  public String getAddressState()
  {
    return this.addressState;
  }

  public void setAddressState(String addressState)
  {
    this.addressState = addressState;
  }

  public String getAddressZip()
  {
    return this.addressZip;
  }

  public void setAddressZip(String addressZip)
  {
    this.addressZip = addressZip;
  }

  public String getAddressCountry()
  {
    return this.addressCountry;
  }

  public void setAddressCountry(String addressCountry)
  {
    this.addressCountry = addressCountry;
  }

  public Float getAddressLat()
  {
    return this.addressLat;
  }

  public void setAddressLat(Float addressLat)
  {
    this.addressLat = addressLat;
  }

  public Float getAddressLong()
  {
    return this.addressLong;
  }

  public void setAddressLong(Float addressLong)
  {
    this.addressLong = addressLong;
  }

  public String getContactInfo()
  {
    return this.contactInfo;
  }

  public void setContactInfo(String contactInfo)
  {
    this.contactInfo = contactInfo;
  }

  public String getTimeZone()
  {
    return this.timeZone;
  }

  public void setTimeZone(String timeZone)
  {
    this.timeZone = timeZone;
  }

  public String getEmailAlert()
  {
    return this.emailAlert;
  }

  public void setEmailAlert(String emailAlert)
  {
    this.emailAlert = emailAlert;
  }

  public String getProviderId()
  {
    return this.providerId;
  }

  public void setProviderId(String providerId)
  {
    this.providerId = providerId;
  }

}
