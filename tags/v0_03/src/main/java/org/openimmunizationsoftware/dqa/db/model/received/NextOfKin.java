package org.openimmunizationsoftware.dqa.db.model.received;

import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.received.types.Address;
import org.openimmunizationsoftware.dqa.db.model.received.types.CodedEntity;
import org.openimmunizationsoftware.dqa.db.model.received.types.Name;
import org.openimmunizationsoftware.dqa.db.model.received.types.PhoneNumber;


public class NextOfKin
{
  private Address address = new Address();
  private MessageReceived messageReceived = null;
  private Name name = new Name();
  private PhoneNumber phone = new PhoneNumber();
  private int positionId = 0;
  private long receivedId = 0l;
  private long nextOfKinId;
  private CodedEntity relationship = new CodedEntity();

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

  public void getAddressState(String addressStreet)
  {
    address.setStreet(addressStreet);
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

  public MessageReceived getMessageReceived()
  {
    return messageReceived;
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

  public PhoneNumber getPhone()
  {
    return phone;
  }

  public String getPhoneNumber()
  {
    return phone.getNumber();
  }

  public int getPositionId()
  {
    return positionId;
  }

  public long getReceivedId()
  {
    return receivedId;
  }

  public long getNextOfKinId()
  {
    return nextOfKinId;
  }

  public CodedEntity getRelationship()
  {
    return relationship;
  }

  public String getRelationshipCode()
  {
    return relationship.getCode();
  }

  public void setRelationshipCode(String relationshipCode)
  {
    relationship.setCode(relationshipCode);
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

  public void setMessageReceived(MessageReceived messageReceived)
  {
    this.messageReceived = messageReceived;
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

  public void setPhoneNumber(String phoneNumber)
  {
    phone.setNumber(phoneNumber);
  }

  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }

  public void setReceivedId(long receivedId)
  {
    this.receivedId = receivedId;
  }

  public void setNextOfKinId(long nextOfKinId)
  {
    this.nextOfKinId = nextOfKinId;
  }

}
