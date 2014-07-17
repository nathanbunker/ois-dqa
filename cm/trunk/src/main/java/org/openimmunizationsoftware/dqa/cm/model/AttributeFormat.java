package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public enum AttributeFormat implements Serializable {
  FREE_TEXT("T", "Free Text"), LONG_TEXT("L", "Long Text"), DATE("D", "Date"), SELECT_TEXT("S", "Select Text"), INTEGER("I", "Integer"), CODE_MASTER(
      "C", "Code Master"), CODE_TABLE("A", "Code Table"),

  ;
  private String id = "";
  private String label = "";

  public String getId()
  {
    return id;
  }

  public String getLabel()
  {
    return label;
  }

  private AttributeFormat(String id, String label) {
    this.id = id;
    this.label = label;
  }

  @Override
  public String toString()
  {
    return label;
  }

  public static AttributeFormat get(String id)
  {
    if (id != null)
    {
      for (AttributeFormat attributeFormat : values())
      {
        if (attributeFormat.getId().equals(id))
        {
          return attributeFormat;
        }
      }
    }
    return null;
  }

}
