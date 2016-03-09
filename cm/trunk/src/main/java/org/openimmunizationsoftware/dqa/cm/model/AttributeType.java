package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AttributeType implements Serializable
{
  private int attributeTypeId = 0;
  private String attributeLabel = "";
  private String attributeHelp = "";
  private AttributeFormat attributeFormat = null;
  private CodeTable refTable = null;

  public CodeTable getRefTable()
  {
    return refTable;
  }

  public void setRefTable(CodeTable refTable)
  {
    this.refTable = refTable;
  }

  public int getAttributeTypeId()
  {
    return attributeTypeId;
  }

  public void setAttributeTypeId(int attributeTypeId)
  {
    this.attributeTypeId = attributeTypeId;
  }

  public String getAttributeLabel()
  {
    return attributeLabel;
  }

  public void setAttributeLabel(String attributeLabel)
  {
    this.attributeLabel = attributeLabel;
  }

  public String getAttributeHelp()
  {
    return attributeHelp;
  }

  public void setAttributeHelp(String attributeHelp)
  {
    this.attributeHelp = attributeHelp;
  }

  public String getAttributeFormatString()
  {
    if (attributeFormat == null)
    {
      return null;
    }
    return attributeFormat.getId();
  }

  public void setAttributeFormatString(String attributeFormatString)
  {
    this.attributeFormat = AttributeFormat.get(attributeFormatString);
  }

  public AttributeFormat getAttributeFormat()
  {
    return attributeFormat;
  }

  public void setAttributeFormat(AttributeFormat attributeFormat)
  {
    this.attributeFormat = attributeFormat;
  }
}
