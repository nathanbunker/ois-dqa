package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AllowedValue implements Serializable
{
  private int allowedValueId = 0;
  private AttributeType attributeType = null;
  private String savedValue = "";
  private String displayText = "";
  private int displayOrder = 0;

  public int getDisplayOrder()
  {
    return displayOrder;
  }

  public void setDisplayOrder(int displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  public int getAllowedValueId()
  {
    return allowedValueId;
  }

  public void setAllowedValueId(int allowedValueId)
  {
    this.allowedValueId = allowedValueId;
  }

  public AttributeType getAttributeType()
  {
    return attributeType;
  }

  public void setAttributeType(AttributeType attributeType)
  {
    this.attributeType = attributeType;
  }

  public String getSavedValue()
  {
    return savedValue;
  }

  public void setSavedValue(String savedValue)
  {
    this.savedValue = savedValue;
  }

  public String getDisplayText()
  {
    return displayText;
  }

  public void setDisplayText(String displayText)
  {
    this.displayText = displayText;
  }
}
