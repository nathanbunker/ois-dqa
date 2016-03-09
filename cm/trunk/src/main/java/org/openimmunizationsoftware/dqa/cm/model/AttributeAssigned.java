package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AttributeAssigned implements Serializable
{
  private int attributeAssignedId = 0;
  private AttributeType attributeType = null;
  private CodeTable table = null;
  private int displayOrder = 0;
  private boolean allowMultiple = false;
  private AttributeStatus attributeStatus = null;

  public boolean isAllowMultiple()
  {
    return allowMultiple;
  }

  public void setAllowMultiple(boolean allowMultiple)
  {
    this.allowMultiple = allowMultiple;
  }


  public int getAttributeAssignedId()
  {
    return attributeAssignedId;
  }

  public void setAttributeAssignedId(int attributeAssignedId)
  {
    this.attributeAssignedId = attributeAssignedId;
  }

  public AttributeType getAttributeType()
  {
    return attributeType;
  }

  public void setAttributeType(AttributeType attributeType)
  {
    this.attributeType = attributeType;
  }

  public CodeTable getTable()
  {
    return table;
  }

  public void setTable(CodeTable table)
  {
    this.table = table;
  }

  public int getDisplayOrder()
  {
    return displayOrder;
  }

  public void setDisplayOrder(int displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  public AttributeStatus getAttributeStatus()
  {
    return attributeStatus;
  }

  public void setAttributeStatus(AttributeStatus attributeStatus)
  {
    this.attributeStatus = attributeStatus;
  }

  public String getAttributeStatusString()
  {
    if (attributeStatus == null)
    {
      return null;
    }
    return attributeStatus.getId();
  }

  public void setAttributeStatusString(String attributeStatusString)
  {
    this.attributeStatus = AttributeStatus.get(attributeStatusString);
  }
}
