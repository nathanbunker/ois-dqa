package org.openimmunizationsoftware.dqa.db.model;

import java.util.Date;

import org.openimmunizationsoftware.dqa.db.model.received.types.CodedEntity;

public class Header
{

  public static String PROCESSING_ID_DEBUGGING = "D";
  public static String PROCESSING_ID_PRODUCTION = "P";
  public static String PROCESSING_ID_TRAINING = "T";

  public static String VERSION_ID_2_3_1 = "2.3.1";
  public static String VERSION_ID_2_4 = "2.4";

  private CodedEntity ackTypeAccept = new CodedEntity(CodeTable.Type.ACKNOWLEDGEMENT_TYPE);
  private CodedEntity ackTypeApplication = new CodedEntity(CodeTable.Type.ACKNOWLEDGEMENT_TYPE);
  private CodedEntity characterSet = new CodedEntity(CodeTable.Type.CHARACTER_SET);
  private CodedEntity characterSetAlt = new CodedEntity(CodeTable.Type.CHARACTER_SET);
  private CodedEntity country = new CodedEntity(CodeTable.Type.ADDRESS_COUNTRY);
  private String messageControlId = "";
  private Date messageDate = null;
  private String messageProfileId = "";
  private String messageStructure = "";
  private String messageTrigger = "";
  private String messageType = "";
  private CodedEntity processingId = new CodedEntity(CodeTable.Type.MESSAGE_PROCESSING_ID);
  private String receivingApplication = "";
  private String receivingFacility = "";
  private String sendingApplication = "";
  private String sendingFacility = "";
  private String versionId = "";

  public CodedEntity getAckTypeAccept()
  {
    return ackTypeAccept;
  }

  public String getAckTypeAcceptCode()
  {
    return ackTypeAccept.getCode();
  }

  public CodedEntity getAckTypeApplication()
  {
    return ackTypeApplication;
  }

  public String getAckTypeApplicationCode()
  {
    return ackTypeApplication.getCode();
  }

  public CodedEntity getCharacterSet()
  {
    return characterSet;
  }
  
  public CodedEntity getCharacterSetAlt()
  {
    return characterSetAlt;
  }
  
  public String getCharacterSetCode()
  {
    return characterSet.getCode();
  }

  public String getCharacterSetAltCode()
  {
    return characterSetAlt.getCode();
  }

  public CodedEntity getCountry()
  {
    return country;
  }
  
  public String getCountryCode()
  {
    return country.getCode();
  }

  public String getMessageControlId()
  {
    return messageControlId;
  }

  public Date getMessageDate()
  {
    return messageDate;
  }

  public String getMessageProfileId()
  {
    return messageProfileId;
  }

  public String getMessageStructure()
  {
    return messageStructure;
  }

  public String getMessageTrigger()
  {
    return messageTrigger;
  }

  public String getMessageType()
  {
    return messageType;
  }

  public CodedEntity getProcessingId()
  {
    return processingId;
  }

  public String getProcessingIdCode()
  {
    return processingId.getCode();
  }

  public String getReceivingApplication()
  {
    return receivingApplication;
  }

  public String getReceivingFacility()
  {
    return receivingFacility;
  }

  public String getSendingApplication()
  {
    return sendingApplication;
  }

  public String getSendingFacility()
  {
    return sendingFacility;
  }

  public String getVersionId()
  {
    return versionId;
  }

  public boolean isProcessingIdDebugging()
  {
    return processingId.getCode().equals(PROCESSING_ID_DEBUGGING);
  }

  public boolean isProcessingIdProduction()
  {
    return processingId.getCode().equals(PROCESSING_ID_PRODUCTION);
  }

  public boolean isProcessingIdTraining()
  {
    return processingId.getCode().equals(PROCESSING_ID_DEBUGGING);
  }

  public void setAckTypeAcceptCode(String ackTypeAccept)
  {
    this.ackTypeAccept.setCode(ackTypeAccept);
  }

  public void setAckTypeApplicationCode(String ackTypeApplication)
  {
    this.ackTypeApplication.setCode(ackTypeApplication);
  }

  public void setCharacterSetCode(String characterSet)
  {
    this.characterSet.setCode(characterSet);
  }
  
  public void setCharacterSetAltCode(String characterSetAlt)
  {
    this.characterSetAlt.setCode(characterSetAlt);
  }
  
  public void setCountryCode(String countryCode)
  {
    this.country.setCode(countryCode);
  }

  public void setMessageControlId(String messageControlId)
  {
    this.messageControlId = messageControlId;
  }

  public void setMessageDate(Date messageDate)
  {
    this.messageDate = messageDate;
  }

  public void setMessageProfileId(String messageProfileId)
  {
    this.messageProfileId = messageProfileId;
  }

  public void setMessageStructure(String messageStructure)
  {
    this.messageStructure = messageStructure;
  }

  public void setMessageTrigger(String messageTrigger)
  {
    this.messageTrigger = messageTrigger;
  }

  public void setMessageType(String messageType)
  {
    this.messageType = messageType;
  }

  public void setProcessingIdCode(String processingId)
  {
    this.processingId.setCode(processingId);
  }

  public void setReceivingApplication(String receivingApplication)
  {
    this.receivingApplication = receivingApplication;
  }

  public void setReceivingFacility(String receivingFacility)
  {
    this.receivingFacility = receivingFacility;
  }

  public void setSendingApplication(String sendingApplication)
  {
    this.sendingApplication = sendingApplication;
  }

  public void setSendingFacility(String sendingFacility)
  {
    this.sendingFacility = sendingFacility;
  }

  public void setVersionId(String versionId)
  {
    this.versionId = versionId;
  }

}
