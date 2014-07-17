package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public enum InclusionStatus implements Serializable {
  INCLUDE("I", "Included"), REMOVE("R", "Removed"), PROPOSED_INCLUDE("Y", "Proposed Include"), PROPOSED_REMOVE("N", "Proposed Remove"),  

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

  private InclusionStatus(String id, String label) {
    this.id = id;
    this.label = label;
  }

  @Override
  public String toString()
  {
    return label;
  }

  public static InclusionStatus get(String id)
  {
    if (id != null)
    {
      for (InclusionStatus inclusionStatus : values())
      {
        if (inclusionStatus.getId().equals(id))
        {
          return inclusionStatus;
        }
      }
    }
    return null;
  }

}
