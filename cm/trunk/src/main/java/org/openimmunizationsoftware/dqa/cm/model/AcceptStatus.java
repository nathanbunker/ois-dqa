package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public enum AcceptStatus implements Serializable {
  CONFIRMED("C", "Confirmed", 1), PROPOSED("Y", "Proposed", 2), PROBLEM("P", "Problem", 3), QUESTION("Q", "Question", 4), RESEARCH("R", "Research", 5), OPPOSED(
      "N", "Opposed", 6), REJECTED("X", "Rejected", 7)

  ;
  private String id = "";
  private String label = "";
  private int order = 0;

  public int getOrder()
  {
    return order;
  }

  public String getId()
  {
    return id;
  }

  public String getLabel()
  {
    return label;
  }

  private AcceptStatus(String id, String label, int order) {
    this.id = id;
    this.label = label;
    this.order = order;
  }

  @Override
  public String toString()
  {
    return label;
  }

  public static AcceptStatus get(String id)
  {
    if (id != null)
    {
      for (AcceptStatus acceptStatus : values())
      {
        if (acceptStatus.getId().equals(id))
        {
          return acceptStatus;
        }
      }
    }
    return null;
  }
}
