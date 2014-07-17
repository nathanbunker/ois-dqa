package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public enum PositionStatus implements Serializable {
  FOR("F", "For"), AGAINST("A", "Against"), NEUTRAL("N", "Neutral"), RESEARCH("R", "Research"), QUESTION("Q", "Question"), PROBLEM("P", "Problem");

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

  private PositionStatus(String id, String label) {
    this.id = id;
    this.label = label;
  }

  @Override
  public String toString()
  {
    return label;
  }

  public static PositionStatus get(String id)
  {
    if (id != null)
    {
      for (PositionStatus positionStatus : values())
      {
        if (positionStatus.getId().equals(id))
        {
          return positionStatus;
        }
      }
    }
    return null;
  }

}
