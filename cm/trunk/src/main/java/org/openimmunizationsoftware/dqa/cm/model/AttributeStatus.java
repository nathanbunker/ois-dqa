package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public enum AttributeStatus implements Serializable {
  REQUIRED("R", "Required"), OPTIONAL("O", "Optional"), DISABLED("D", "Disabled"),

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

  private AttributeStatus(String id, String label) {
    this.id = id;
    this.label = label;
  }

  @Override
  public String toString()
  {
    return label;
  }

  public static AttributeStatus get(String id)
  {
    if (id != null)
    {
      for (AttributeStatus attributeStatus : values())
      {
        if (attributeStatus.getId().equals(id))
        {
          return attributeStatus;
        }
      }
    }
    return null;
  }

}
