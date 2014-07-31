package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.Date;

public class AttributeValue implements Serializable
{
  private int valueId = 0;
  private AttributeType attributeType = null;
  private CodeMaster code = null;
  private String attributeValue = "";

  public int getValueId()
  {
    return valueId;
  }

  public void setValueId(int valueId)
  {
    this.valueId = valueId;
  }

  public AttributeType getAttributeType()
  {
    return attributeType;
  }

  public void setAttributeType(AttributeType attributeType)
  {
    this.attributeType = attributeType;
  }

  public CodeMaster getCode()
  {
    return code;
  }

  public void setCode(CodeMaster code)
  {
    this.code = code;
  }

  public String getAttributeValue()
  {
    return attributeValue;
  }

  public int getAttributeValueInt()
  {
    if (attributeValue == null || attributeValue.equals(""))
    {
      return 0;
    }
    return Integer.parseInt(attributeValue);
  }

  public void setAttributeValue(String attributeValue)
  {
    this.attributeValue = attributeValue;
  }

}