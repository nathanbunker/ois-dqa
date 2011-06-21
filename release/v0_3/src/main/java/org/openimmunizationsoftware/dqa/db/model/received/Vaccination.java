package org.openimmunizationsoftware.dqa.db.model.received;

import java.util.Date;

import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.received.types.CodedEntity;
import org.openimmunizationsoftware.dqa.db.model.received.types.Id;
import org.openimmunizationsoftware.dqa.db.model.received.types.OrganizationName;


public class Vaccination
{
  public static final String ACTION_CODE_ADD = "A";
  public static final String ACTION_CODE_DELETE = "D";
  public static final String ACTION_CODE_UPDATE = "U";

  public static final String INFO_SOURCE_ADMIN = "00";
  public static final String INFO_SOURCE_HIST = "01";

  private String actionCode = "";
  private CodedEntity admin = new CodedEntity();
  private String adminCodeCpt = "";
  private String adminCodeCvx = "";
  private Date adminDate = null;
  private Date adminDateEnd = null;
  private String amount = "";
  private CodedEntity amountUnit = new CodedEntity();
  private CodedEntity bodyRoute = new CodedEntity();
  private CodedEntity bodySite = new CodedEntity();
  private String completionStatusCode = "";
  private CodedEntity confidentiality = new CodedEntity();
  private Id enteredBy = new Id();
  private Date expirationDate = null;
  private OrganizationName facility = new OrganizationName();
  private String financialEligibilityCode = "";
  private Id givenBy = new Id();
  private String idSubmitter = "";
  private CodedEntity informationSource = new CodedEntity();
  private String lotNumber = "";
  private CodedEntity manufacturer = new CodedEntity();
  private MessageReceived messageReceived = null;
  private Id orderedBy = new Id();
  private int positionId = 0;
  private CodedEntity refusal = new CodedEntity();
  private Date systemEntryDate = null;
  private long vaccinationId = 0l;

  private Date visPublicationDate = null;

  public String getActionCode()
  {
    return actionCode;
  }

  public CodedEntity getAdmin()
  {
    return admin;
  }

  public String getAdminCode()
  {
    return admin.getCode();
  }

  public String getAdminCodeCpt()
  {
    return adminCodeCpt;
  }

  public String getAdminCodeCvx()
  {
    return adminCodeCvx;
  }

  public Date getAdminDate()
  {
    return adminDate;
  }

  public Date getAdminDateEnd()
  {
    return adminDateEnd;
  }

  public String getAmount()
  {
    return amount;
  }

  public CodedEntity getAmountUnit()
  {
    return amountUnit;
  }

  public String getAmountUnitCode()
  {
    return amountUnit.getCode();
  }

  public String getCompletionStatusCode()
  {
    return completionStatusCode;
  }

  public CodedEntity getConfidentiality()
  {
    return confidentiality;
  }

  public String getConfidentialityCode()
  {
    return confidentiality.getCode();
  }

  public Id getEnteredBy()
  {
    return enteredBy;
  }

  public String getEnteredByNameFirst()
  {
    return enteredBy.getName().getFirst();
  }

  public String getEnteredByNameLast()
  {
    return enteredBy.getName().getLast();
  }

  public String getEnteredByNumber()
  {
    return enteredBy.getNumber();
  }

  public Date getExpirationDate()
  {
    return expirationDate;
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

  public String getFinancialEligibilityCode()
  {
    return financialEligibilityCode;
  }

  public Id getGivenBy()
  {
    return givenBy;
  }

  public String getGivenByNameFirst()
  {
    return givenBy.getName().getFirst();
  }

  public String getGivenByNameLast()
  {
    return givenBy.getName().getLast();
  }

  public String getGivenByNumber()
  {
    return givenBy.getNumber();
  }

  public String getIdSubmitter()
  {
    return idSubmitter;
  }

  public CodedEntity getInformationSource()
  {
    return informationSource;
  }

  public String getInformationSourceCode()
  {
    return informationSource.getCode();
  }

  public String getLotNumber()
  {
    return lotNumber;
  }

  public CodedEntity getManufacturer()
  {
    return manufacturer;
  }

  public String getManufacturerCode()
  {
    return manufacturer.getCode();
  }

  public MessageReceived getMessageReceived()
  {
    return messageReceived;
  }

  public Id getOrderedBy()
  {
    return orderedBy;
  }

  public String getOrderedByNameFirst()
  {
    return orderedBy.getName().getFirst();
  }

  public String getOrderedByNameLast()
  {
    return orderedBy.getName().getLast();
  }

  public String getOrderedByNumber()
  {
    return orderedBy.getNumber();
  }

  public int getPositionId()
  {
    return positionId;
  }

  public CodedEntity getRefusal()
  {
    return refusal;
  }

  public String getRefusalCode()
  {
    return refusal.getCode();
  }

  public Date getSystemEntryDate()
  {
    return systemEntryDate;
  }

  public long getVaccinationId()
  {
    return vaccinationId;
  }

  public Date getVisPublicationDate()
  {
    return visPublicationDate;
  }

  public void setActionCode(String actionCode)
  {
    this.actionCode = actionCode;
  }

  public void setAdminCode(String adminCode)
  {
    admin.setCode(adminCode);
  }

  public void setAdminCodeCpt(String adminCodeCpt)
  {
    this.adminCodeCpt = adminCodeCpt;
  }

  public void setAdminCodeCvx(String adminCodeCvx)
  {
    this.adminCodeCvx = adminCodeCvx;
  }

  public void setAdminDate(Date adminDate)
  {
    this.adminDate = adminDate;
  }

  public void setAdminDateEnd(Date adminDateEnd)
  {
    this.adminDateEnd = adminDateEnd;
  }

  public void setAmount(String amount)
  {
    this.amount = amount;
  }

  public void setAmountUnitCode(String amountUnitCode)
  {
    amountUnit.setCode(amountUnitCode);
  }

  public void setCompletionStatusCode(String completionStatusCode)
  {
    this.completionStatusCode = completionStatusCode;
  }

  public void setConfidentialityCode(String confidentialityCode)
  {
    confidentiality.setCode(confidentialityCode);
  }

  public void setEnteredByNameFirst(String enteredByNameFirst)
  {
    enteredBy.getName().setFirst(enteredByNameFirst);
  }

  public void setEnteredByNameLast(String enteredByNameLast)
  {
    enteredBy.getName().setLast(enteredByNameLast);
  }

  public void setEnteredByNumber(String enteredByNumber)
  {
    enteredBy.setNumber(enteredByNumber);
  }

  public void setExpirationDate(Date expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  public void setFacilityId(String facilityId)
  {
    facility.setId(facilityId);
  }

  public void setFacilityName(String facilityName)
  {
    facility.setName(facilityName);
  }

  public void setFinancialEligibilityCode(String financialEligibilityCode)
  {
    this.financialEligibilityCode = financialEligibilityCode;
  }

  public void setGivenByNameFirst(String givenByNameFirst)
  {
    givenBy.getName().setFirst(givenByNameFirst);
  }

  public void setGivenByNameLast(String givenByNameLast)
  {
    givenBy.getName().setLast(givenByNameLast);
  }

  public void setGivenByNumber(String givenByNumber)
  {
    givenBy.setNumber(givenByNumber);
  }

  public void setIdSubmitter(String idSubmitter)
  {
    this.idSubmitter = idSubmitter;
  }

  public void setInformationSourceCode(String informationSourceCode)
  {
    informationSource.setCode(informationSourceCode);
  }

  public void setLotNumber(String lotNumber)
  {
    this.lotNumber = lotNumber;
  }

  public void setManufacturerCode(String manufacturerCode)
  {
    manufacturer.setCode(manufacturerCode);
  }

  public void setMessageReceived(MessageReceived messageReceived)
  {
    this.messageReceived = messageReceived;
  }

  public void setOrderedByNameFirst(String orderedByNameFirst)
  {
    orderedBy.getName().setFirst(orderedByNameFirst);
  }

  public void setOrderedByNameLast(String orderedByNameLast)
  {
    orderedBy.getName().setLast(orderedByNameLast);
  }

  public void setOrderedByNumber(String orderedByNumber)
  {
    orderedBy.setNumber(orderedByNumber);
  }

  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }

  public void setRefusalCode(String refusalCode)
  {
    refusal.setCode(refusalCode);
  }

  public void setSystemEntryDate(Date systemEntryDate)
  {
    this.systemEntryDate = systemEntryDate;
  }

  public void setVaccinationId(long vaccinationId)
  {
    this.vaccinationId = vaccinationId;
  }

  public void setVisPublicationDate(Date visPublicationDate)
  {
    this.visPublicationDate = visPublicationDate;
  }

  public CodedEntity getBodyRoute()
  {
    return this.bodyRoute;
  }

  public CodedEntity getBodySite()
  {
    return this.bodySite;
  }

  public String getBodyRouteCode()
  {
    return this.bodyRoute.getCode();
  }

  public String getBodySiteCode()
  {
    return this.bodySite.getCode();
  }

  public void setBodyRouteCode(String bodyRouteCode)
  {
    this.bodyRoute.setCode(bodyRouteCode);
  }

  public void setBodySiteCode(String bodySiteCode)
  {
    this.bodySite.setCode(bodySiteCode);
  }

}
