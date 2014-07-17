package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public enum UserType implements Serializable {
  ADMIN("A", "Admin"), EXPERT("E", "Expert"), PENDING("P", "Pending"), DELETED ("D", "Deleted")

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

  private UserType(String id, String label) {
    this.id = id;
    this.label = label;
  }

  @Override
  public String toString()
  {
    return label;
  }

  public static UserType get(String id)
  {
    if (id != null)
    {
      for (UserType userType : values())
      {
        if (userType.getId().equals(id))
        {
          return userType;
        }
      }
    }
    return null;
  }

}
