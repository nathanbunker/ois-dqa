package org.openimmunizationsoftware.dqa.tr.profile;

public enum CompatibilityConformance {
  COMPATIBLE, ALLOWANCE, CONSTRAINT, MAJOR_CONSTRAINT, CONFLICT, MAJOR_CONFLICT, UNABLE_TO_DETERMINE, NOT_DEFINED;

  public String toString() {
    switch (this) {
    case ALLOWANCE:
      return "Allowance";
    case COMPATIBLE:
      return "Compatible";
    case CONFLICT:
      return "Conflict";
    case CONSTRAINT:
      return "Constraint";
    case MAJOR_CONFLICT:
      return "Major Conflict";
    case MAJOR_CONSTRAINT:
      return "Major Constraint";
    case UNABLE_TO_DETERMINE:
      return "Unable to Determine";
    case NOT_DEFINED:
      return "Not Defined";
    }
    return super.toString();
  };
  
  public static CompatibilityConformance readCompatibilityConformance(String compatibilityConformanceString)
  {
    if (compatibilityConformanceString == null || compatibilityConformanceString.equals(""))
    {
      return NOT_DEFINED;
    }
    if (compatibilityConformanceString.equals("Allowance"))
    {
      return ALLOWANCE;
    }
    if (compatibilityConformanceString.equals("Compatible"))
    {
      return COMPATIBLE;
    }
    if (compatibilityConformanceString.equals("Conflict"))
    {
      return CONFLICT;
    }
    if (compatibilityConformanceString.equals("Constraint"))
    {
      return CONSTRAINT;
    }
    if (compatibilityConformanceString.equals("Major Conflict"))
    {
      return MAJOR_CONFLICT;
    }
    if (compatibilityConformanceString.equals("Major Constraint"))
    {
      return MAJOR_CONSTRAINT;
    }
    if (compatibilityConformanceString.equals("Unable to Determine"))
    {
      return UNABLE_TO_DETERMINE;
    }
    return NOT_DEFINED;
  }
}
