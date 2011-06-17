package org.openimmunizationsoftware.dqa.db.model.received;

import java.util.Date;

import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.received.types.Address;
import org.openimmunizationsoftware.dqa.db.model.received.types.CodedEntity;
import org.openimmunizationsoftware.dqa.db.model.received.types.Id;
import org.openimmunizationsoftware.dqa.db.model.received.types.Name;
import org.openimmunizationsoftware.dqa.db.model.received.types.OrganizationName;
import org.openimmunizationsoftware.dqa.db.model.received.types.PhoneNumber;


public class Patient
{
  private Address address = new Address();
  private Name alias = new Name();
  private Date birthDate = null;
  private String birthMuliple = "";
  private String birthOrder = "";
  private String birthPlace = "";
  private Date deathDate = null;
  private String deathIndicator = "";
  private CodedEntity ethnicity = new CodedEntity();
  private OrganizationName facility = new OrganizationName();
  private String financialEligibility = "";
  private String idMedicaid = "";
  private String idSsn = "";
  private Id idSubmitter = new Id();
  private MessageReceived messageReceived = null;
  private String motherMaidenName = "";
  private Name name = new Name();
  private long patientId = 0;
  private PhoneNumber phone = new PhoneNumber();
  private Id physician = new Id();
  private CodedEntity primaryLanguage = new CodedEntity();
  private CodedEntity protection = new CodedEntity();
  private CodedEntity publicity = new CodedEntity();
  private CodedEntity race = new CodedEntity();
  private String registryStatus = "";
  private String sexCode = "";

  public Address getAddress()
  {
    return address;
  }

  public String getAddressCity()
  {
    return address.getCity();
  }

  public String getAddressCountry()
  {
    return address.getCountry();
  }

  public String getAddressCountyParish()
  {
    return address.getCountyParish();
  }

  public String getAddressState()
  {
    return address.getState();
  }

  public String getAddressStreet()
  {
    return address.getStreet();
  }

  public String getAddressStreet2()
  {
    return address.getStreet2();
  }

  public String getAddressType()
  {
    return address.getType();
  }

  public String getAddressZip()
  {
    return address.getZip();
  }

  public Name getAlias()
  {
    return alias;
  }

  public String getAliasFirst()
  {
    return alias.getFirst();
  }

  public String getAliasLast()
  {
    return alias.getLast();
  }

  public String getAliasMiddle()
  {
    return alias.getMiddle();
  }

  public String getAliasPrefix()
  {
    return alias.getPrefix();
  }

  public String getAliasSuffix()
  {
    return alias.getSuffix();
  }

  public String getAliasTypeCode()
  {
    return alias.getTypeCode();
  }

  public Date getBirthDate()
  {
    return birthDate;
  }

  public String getBirthMuliple()
  {
    return birthMuliple;
  }

  public String getBirthOrder()
  {
    return birthOrder;
  }

  public String getBirthPlace()
  {
    return birthPlace;
  }

  public Date getDeathDate()
  {
    return deathDate;
  }

  public String getDeathIndicator()
  {
    return deathIndicator;
  }

  public CodedEntity getEthnicity()
  {
    return ethnicity;
  }

  public String getEthnicityCode()
  {
    return ethnicity.getCode();
  }

  public OrganizationName getFacility()
  {
    return facility;
  }

  public String getFacilityId()
  {
    return facility.getId();
  }

  public String getFacilityName()
  {
    return facility.getName();
  }

  public String getFinancialEligibility()
  {
    return financialEligibility;
  }

  public String getIdMedicaid()
  {
    return idMedicaid;
  }

  public String getIdSsn()
  {
    return idSsn;
  }

  public Id getIdSubmitter()
  {
    return idSubmitter;
  }

  public String getIdSubmitterAssigningAuthority()
  {
    return idSubmitter.getAssigningAuthority();
  }

  public String getIdSubmitterNumber()
  {
    return idSubmitter.getNumber();
  }

  public String getIdSubmitterTypeCode()
  {
    return idSubmitter.getTypeCode();
  }

  public MessageReceived getMessageReceived()
  {
    return messageReceived;
  }

  public String getMotherMaidenName()
  {
    return motherMaidenName;
  }

  public Name getName()
  {
    return name;
  }

  public String getNameFirst()
  {
    return name.getFirst();
  }

  public String getNameLast()
  {
    return name.getLast();
  }

  public String getNameMiddle()
  {
    return name.getMiddle();
  }

  public String getNamePrefix()
  {
    return name.getPrefix();
  }

  public String getNameSuffix()
  {
    return name.getSuffix();
  }

  public String getNameTypeCode()
  {
    return name.getTypeCode();
  }

  public long getPatientId()
  {
    return patientId;
  }

  public PhoneNumber getPhone()
  {
    return phone;
  }

  public String getPhoneNumber()
  {
    return phone.getNumber();
  }

  public Id getPhysician()
  {
    return physician;
  }

  public String getPhysicianNameFirst()
  {
    return physician.getName().getFirst();
  }

  public String getPhysicianNameLast()
  {
    return physician.getName().getLast();
  }

  public String getPhysicianNumber()
  {
    return physician.getNumber();
  }

  public CodedEntity getPrimaryLanguage()
  {
    return primaryLanguage;
  }

  public String getPrimaryLanguageCode()
  {
    return primaryLanguage.getCode();
  }

  public CodedEntity getProtection()
  {
    return protection;
  }

  public String getProtectionCode()
  {
    return protection.getCode();
  }

  public CodedEntity getPublicity()
  {
    return publicity;
  }

  public String getPublicityCode()
  {
    return publicity.getCode();
  }

  public CodedEntity getRace()
  {
    return race;
  }

  public String getRaceCode()
  {
    return race.getCode();
  }

  public String getRegistryStatus()
  {
    return registryStatus;
  }

  public String getSexCode()
  {
    return sexCode;
  }

  public void setAddressCity(String addressCity)
  {
    address.setCity(addressCity);
  }

  public void setAddressCountry(String addressCountry)
  {
    address.setCountry(addressCountry);
  }

  public void setAddressCountyParish(String addressCountyParish)
  {
    address.setCountyParish(addressCountyParish);
  }

  public void setAddressState(String addressState)
  {
    address.setState(addressState);
  }

  public void setAddressStreet(String addressStreet)
  {
    address.setStreet(addressStreet);
  }

  public void setAddressStreet2(String addressStreet2)
  {
    address.setStreet2(addressStreet2);
  }

  public void setAddressType(String addressType)
  {
    address.setType(addressType);
  }

  public void setAddressZip(String addressZip)
  {
    address.setZip(addressZip);
  }

  public void setAliasFirst(String nameFirst)
  {
    alias.setFirst(nameFirst);
  }

  public void setAliasLast(String nameLast)
  {
    alias.setLast(nameLast);
  }

  public void setAliasMiddle(String nameMiddle)
  {
    alias.setMiddle(nameMiddle);
  }

  public void setAliasPrefix(String namePrefix)
  {
    alias.setPrefix(namePrefix);
  }

  public void setAliasSuffix(String nameSuffix)
  {
    alias.setSuffix(nameSuffix);
  }

  public void setAliasTypeCode(String nameTypeCode)
  {
    alias.setTypeCode(nameTypeCode);
  }

  public void setBirthDate(Date birthDate)
  {
    this.birthDate = birthDate;
  }

  public void setBirthMuliple(String birthMuliple)
  {
    this.birthMuliple = birthMuliple;
  }

  public void setBirthOrder(String birthOrder)
  {
    this.birthOrder = birthOrder;
  }

  public void setBirthPlace(String birthPlace)
  {
    this.birthPlace = birthPlace;
  }

  public void setDeathDate(Date deathDate)
  {
    this.deathDate = deathDate;
  }

  public void setDeathIndicator(String deathIndicator)
  {
    this.deathIndicator = deathIndicator;
  }

  public void setEthnicityCode(String ethnicityCode)
  {
    ethnicity.setCode(ethnicityCode);
  }

  public void setFacilityId(String facilityId)
  {
    facility.setId(facilityId);
  }

  public void setFacilityName(String facilityName)
  {
    facility.setName(facilityName);
  }

  public void setFinancialEligibility(String financialEligibility)
  {
    this.financialEligibility = financialEligibility;
  }

  public void setIdMedicaid(String idMedicaid)
  {
    this.idMedicaid = idMedicaid;
  }

  public void setIdSsn(String idSsn)
  {
    this.idSsn = idSsn;
  }

  public void setIdSubmitterAssigningAuthority(String assigningAuthority)
  {
    idSubmitter.setAssigningAuthority(assigningAuthority);
  }

  public void setIdSubmitterNumber(String number)
  {
    idSubmitter.setNumber(number);
  }

  public void setIdSubmitterTypeCode(String typeCode)
  {
    idSubmitter.setTypeCode(typeCode);
  }

  public void setMessageReceived(MessageReceived messageReceived)
  {
    this.messageReceived = messageReceived;
  }

  public void setMotherMaidenName(String motherMaidenName)
  {
    this.motherMaidenName = motherMaidenName;
  }

  public void setNameFirst(String nameFirst)
  {
    name.setFirst(nameFirst);
  }

  public void setNameLast(String nameLast)
  {
    name.setLast(nameLast);
  }

  public void setNameMiddle(String nameMiddle)
  {
    name.setMiddle(nameMiddle);
  }

  public void setNamePrefix(String namePrefix)
  {
    name.setPrefix(namePrefix);
  }

  public void setNameSuffix(String nameSuffix)
  {
    name.setSuffix(nameSuffix);
  }

  public void setNameTypeCode(String nameTypeCode)
  {
    name.setTypeCode(nameTypeCode);
  }

  public void setPatientId(long patientId)
  {
    this.patientId = patientId;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    phone.setNumber(phoneNumber);
  }

  public void setPhysicianNameFirst(String physicianNameFirst)
  {
    physician.getName().setFirst(physicianNameFirst);
  }

  public void setPhysicianNameLast(String physicianNameLast)
  {
    physician.getName().setLast(physicianNameLast);
  }

  public void setPhysicianNumber(String physicianNumber)
  {
    physician.setNumber(physicianNumber);
  }

  public void setPrimaryLanguageCode(String primaryLanguageCode)
  {
    primaryLanguage.setCode(primaryLanguageCode);
  }

  public void setProtectionCode(String protectionCode)
  {
    protection.setCode(protectionCode);
  }

  public void setPublicityCode(String publicityCode)
  {
    publicity.setCode(publicityCode);
  }

  public void setRaceCode(String raceCode)
  {
    race.setCode(raceCode);
  }

  public void setRegistryStatus(String registryStatus)
  {
    this.registryStatus = registryStatus;
  }

  public void setSexCode(String sexCode)
  {
    this.sexCode = sexCode;
  }
}
