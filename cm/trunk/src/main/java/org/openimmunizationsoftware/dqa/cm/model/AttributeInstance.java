package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public class AttributeInstance implements Serializable
{
  private int attributeInstanceId = 0;
  private AttributeValue value = null;
  private CodeInstance codeInstance = null;
  private AcceptStatus acceptStatus = null;
  private String acceptStatusReason = "";

  public String getAcceptStatusReason()
  {
    return acceptStatusReason;
  }

  public void setAcceptStatusReason(String acceptStatusReason)
  {
    this.acceptStatusReason = acceptStatusReason;
  }

  public AcceptStatus getAcceptStatus()
  {
    return acceptStatus;
  }

  public void setAcceptStatus(AcceptStatus acceptStatus)
  {
    this.acceptStatus = acceptStatus;
  }

  public int getAttributeInstanceId()
  {
    return attributeInstanceId;
  }

  public void setAttributeInstanceId(int attributeInstanceId)
  {
    this.attributeInstanceId = attributeInstanceId;
  }

  public AttributeValue getValue()
  {
    return value;
  }

  public void setValue(AttributeValue value)
  {
    this.value = value;
  }

  public CodeInstance getCodeInstance()
  {
    return codeInstance;
  }

  public void setCodeInstance(CodeInstance codeInstance)
  {
    this.codeInstance = codeInstance;
  }

  public String getAcceptStatusString()
  {
    if (acceptStatus == null)
    {
      return null;
    }
    return acceptStatus.getId();
  }

  public void setAcceptStatusString(String acceptStatusString)
  {
    this.acceptStatus = AcceptStatus.get(acceptStatusString);
  }
}
