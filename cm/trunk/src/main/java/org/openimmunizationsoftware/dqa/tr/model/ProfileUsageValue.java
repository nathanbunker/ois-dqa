package org.openimmunizationsoftware.dqa.tr.model;

import org.openimmunizationsoftware.dqa.tr.profile.Enforcement;
import org.openimmunizationsoftware.dqa.tr.profile.Implementation;
import org.openimmunizationsoftware.dqa.tr.profile.MessageAcceptStatus;
import org.openimmunizationsoftware.dqa.tr.profile.Usage;

public class ProfileUsageValue
{
  private int profileUsageValueId = 0;
  private Usage usage = null;
  private Usage usageDetected = null;
  private String value = "";
  private String comments = "";
  private String notes = "";
  private Enforcement enforcement = Enforcement.NOT_DEFINED;
  private Implementation implementation = Implementation.NOT_DEFINED;
  private ProfileField profileField = null;
  private ProfileUsage profileUsage = null;
  private String fieldName = "";
  private MessageAcceptStatus messageAcceptStatus = null;
  private MessageAcceptStatus messageAcceptStatusDetected = null;
  private String messageAcceptStatusDebug = "";
  private transient ProfileUsageValue profileUsageValueCompare = null;
  private String linkDefinition = "";
  private String linkDetail = "";
  private String linkClarification = "";
  private String linkSupplement = "";

  public String getLinkDefinition()
  {
    return linkDefinition;
  }

  public void setLinkDefinition(String linkDefinition)
  {
    this.linkDefinition = linkDefinition;
  }

  public String getLinkDetail()
  {
    return linkDetail;
  }

  public void setLinkDetail(String linkDetail)
  {
    this.linkDetail = linkDetail;
  }

  public String getLinkClarification()
  {
    return linkClarification;
  }

  public void setLinkClarification(String linkClarification)
  {
    this.linkClarification = linkClarification;
  }

  public String getLinkSupplement()
  {
    return linkSupplement;
  }

  public void setLinkSupplement(String linkSupplement)
  {
    this.linkSupplement = linkSupplement;
  }

  public ProfileUsageValue getProfileUsageValueCompare()
  {
    return profileUsageValueCompare;
  }

  public void setProfileUsageValueCompare(ProfileUsageValue profileUsageValueCompare)
  {
    this.profileUsageValueCompare = profileUsageValueCompare;
  }

  public String getMessageAcceptStatusString()
  {
    return messageAcceptStatus == null ? "" : messageAcceptStatus.toString();
  }

  public void setMessageAcceptStatusString(String messageAcceptStatusString)
  {
    this.messageAcceptStatus = MessageAcceptStatus.readMessageAcceptStatus(messageAcceptStatusString);
  }

  public String getMessageAcceptStatusDetectedString()
  {
    return messageAcceptStatusDetected == null ? "" : messageAcceptStatusDetected.toString();
  }

  public void setMessageAcceptStatusDetectedString(String messageAcceptStatusDetectedString)
  {
    this.messageAcceptStatusDetected = MessageAcceptStatus.readMessageAcceptStatus(messageAcceptStatusDetectedString);
  }

  public String getMessageAcceptStatusDebug()
  {
    return messageAcceptStatusDebug;
  }

  public void setMessageAcceptStatusDebug(String messageAcceptStatusDebug)
  {
    this.messageAcceptStatusDebug = messageAcceptStatusDebug;
  }

  public MessageAcceptStatus getMessageAcceptStatus()
  {
    return messageAcceptStatus;
  }

  public void setMessageAcceptStatus(MessageAcceptStatus messageAcceptStatus)
  {
    this.messageAcceptStatus = messageAcceptStatus;
  }

  public MessageAcceptStatus getMessageAcceptStatusDetected()
  {
    return messageAcceptStatusDetected;
  }

  public void setMessageAcceptStatusDetected(MessageAcceptStatus messageAcceptStatusDetected)
  {
    this.messageAcceptStatusDetected = messageAcceptStatusDetected;
  }

  public String getFieldName()
  {
    return fieldName;
  }

  public void setFieldName(String fieldName)
  {
    this.fieldName = fieldName;
  }

  public ProfileField getProfileField()
  {
    return profileField;
  }

  public void setProfileField(ProfileField profileField)
  {
    this.profileField = profileField;
  }

  public ProfileUsage getProfileUsage()
  {
    return profileUsage;
  }

  public void setProfileUsage(ProfileUsage profileUsage)
  {
    this.profileUsage = profileUsage;
  }

  public int getProfileUsageValueId()
  {
    return profileUsageValueId;
  }

  public void setProfileUsageValueId(int profileUsageValueId)
  {
    this.profileUsageValueId = profileUsageValueId;
  }

  public String getUsageString()
  {
    return usage == null ? "" : usage.toString();
  }

  public void setUsageString(String usageString)
  {
    this.usage = Usage.readUsage(usageString);
  }

  public String getUsageDetectedString()
  {
    return usageDetected == null ? "" : usageDetected.toString();
  }

  public void setUsageDetectedString(String usageDetectedString)
  {
    this.usageDetected = Usage.readUsage(usageDetectedString);
  }

  public String getEnforcementString()
  {
    return enforcement == null ? "" : enforcement.toString();
  }

  public void setEnforcementString(String enforcementString)
  {
    this.enforcement = Enforcement.readEnforcement(enforcementString);
  }

  public String getImplementationString()
  {
    return implementation == null ? "" : implementation.toString();
  }

  public void setImplementationString(String implementationString)
  {
    this.implementation = Implementation.readImplementation(implementationString);
  }

  public Enforcement getEnforcement()
  {
    return enforcement;
  }

  public void setEnforcement(Enforcement enforcement)
  {
    this.enforcement = enforcement;
  }

  public Implementation getImplementation()
  {
    return implementation;
  }

  public void setImplementation(Implementation implementation)
  {
    this.implementation = implementation;
  }

  public Usage getUsageDetected()
  {
    return usageDetected;
  }

  public void setUsageDetected(Usage usageDetected)
  {
    this.usageDetected = usageDetected;
  }

  public String getNotes()
  {
    return notes;
  }

  public void setNotes(String notes)
  {
    this.notes = notes;
  }

  public Usage getUsage()
  {
    return usage;
  }

  public void setUsage(Usage usage)
  {
    this.usage = usage;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String getComments()
  {
    return comments;
  }

  public void setComments(String comments)
  {
    this.comments = comments;
  }
}
