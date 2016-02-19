package org.openimmunizationsoftware.dqa.cm.model;

public enum MemberType
{
  IIS("IIS", "IIS Project Staff Member"), IIS_VENDOR("IIS Vendor", "IIS Vendor Staff Member"), US_TECHNICAL("IIS Technical", "CDC or NIST Technical Staff Member"),
  EHR("EHR", "EHR Vendor Staff Member"), AIRA("AIRA", "AIRA Project Staff Member")
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
  
  private MemberType(String id, String label)
  {
    this.id = id;
    this.label = label;
  }
  
  @Override
  public String toString()
  {
    return label;
  }

  public static MemberType get(String id)
  {
    if (id != null)
    {
      for (MemberType memberType : values())
      {
        if (memberType.getId().equals(id))
        {
          return memberType;
        }
      }
    }
    return null;
  }
}
