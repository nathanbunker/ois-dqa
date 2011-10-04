package org.openimmunizationsoftware.dqa.db.model;

import java.util.Date;

public class Header
{

  public static String PROCESSING_ID_PRODUCTION = "P";
  public static String PROCESSING_ID_TRAINING = "T";
  public static String PROCESSING_ID_DEBUGGING = "D";
  
  public static String VERSION_ID_2_3_1 = "2.3.1";
  public static String VERSION_ID_2_4 = "2.4"; 
  

  private String processingId = "";
  private String sendingApplication = "";
  private String sendingFacility = "";
  private String receivingApplication = "";
  private String receivingFacility = "";
  private String messageTrigger = "";
  private Date messageDate = null;
  private String messageType = "";
  private String messageControlId = "";
  private String versionId = "";
  private String ackTypeAccept = "";
  private String ackTypeApplication = "";
  private String messageProfileId = "";

  public String getAckTypeAccept()
  {
    return ackTypeAccept;
  }

  public void setAckTypeAccept(String ackTypeAccept)
  {
    this.ackTypeAccept = ackTypeAccept;
  }

  public String getAckTypeApplication()
  {
    return ackTypeApplication;
  }

  public void setAckTypeApplication(String ackTypeApplication)
  {
    this.ackTypeApplication = ackTypeApplication;
  }

  public String getMessageProfileId()
  {
    return messageProfileId;
  }

  public void setMessageProfileId(String messageProfileId)
  {
    this.messageProfileId = messageProfileId;
  }

  public String getVersionId()
  {
    return versionId;
  }

  public void setVersionId(String versionId)
  {
    this.versionId = versionId;
  }

  public Date getMessageDate()
  {
    return messageDate;
  }

  public void setMessageDate(Date messageDate)
  {
    this.messageDate = messageDate;
  }

  public String getMessageType()
  {
    return messageType;
  }

  public void setMessageType(String messageType)
  {
    this.messageType = messageType;
  }

  public String getMessageControlId()
  {
    return messageControlId;
  }

  public void setMessageControlId(String messageControlId)
  {
    this.messageControlId = messageControlId;
  }

  public String getMessageTrigger()
  {
    return messageTrigger;
  }

  public void setMessageTrigger(String messageTrigger)
  {
    this.messageTrigger = messageTrigger;
  }

  public String getProcessingId()
  {
    return processingId;
  }

  public void setProcessingId(String processingId)
  {
    this.processingId = processingId;
  }

  public String getSendingApplication()
  {
    return sendingApplication;
  }

  public void setSendingApplication(String sendingApplication)
  {
    this.sendingApplication = sendingApplication;
  }

  public String getSendingFacility()
  {
    return sendingFacility;
  }

  public void setSendingFacility(String sendingFacility)
  {
    this.sendingFacility = sendingFacility;
  }

  public String getReceivingApplication()
  {
    return receivingApplication;
  }

  public void setReceivingApplication(String receivingApplication)
  {
    this.receivingApplication = receivingApplication;
  }

  public String getReceivingFacility()
  {
    return receivingFacility;
  }

  public void setReceivingFacility(String receivingFacility)
  {
    this.receivingFacility = receivingFacility;
  }
  
  public boolean isProcessingIdDebugging()
  {
    return processingId != null && processingId.equals(PROCESSING_ID_DEBUGGING);
  }

  public boolean isProcessingIdProduction()
  {
    return processingId != null && processingId.equals(PROCESSING_ID_PRODUCTION);
  }

  public boolean isProcessingIdTraining()
  {
    return processingId != null && processingId.equals(PROCESSING_ID_DEBUGGING);
  }

}
