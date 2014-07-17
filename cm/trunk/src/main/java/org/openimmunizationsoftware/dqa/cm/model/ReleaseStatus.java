package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public enum ReleaseStatus implements Serializable {
  PROPOSED("P", "Proposed"), CURRENT("C", "Current"), DEPRECATED("D", "Deprecated")

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

  private ReleaseStatus(String id, String label) {
    this.id = id;
    this.label = label;
  }

  @Override
  public String toString()
  {
    return label;
  }

  public static ReleaseStatus get(String id)
  {
    if (id != null)
    {
      for (ReleaseStatus releaseStatus : values())
      {
        if (releaseStatus.getId().equals(id))
        {
          return releaseStatus;
        }
      }
    }
    return null;
  }


}
