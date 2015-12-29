package org.openimmunizationsoftware.dqa.tr.profile;

public class CompatibilityConformanceCount
{
  private CompatibilityConformance compatibilityConformance = null;
  private long count = 0;
  public CompatibilityConformance getCompatibilityConformance()
  {
    return compatibilityConformance;
  }
  public void setCompatibilityConformance(CompatibilityConformance compatibilityConformance)
  {
    this.compatibilityConformance = compatibilityConformance;
  }
  public long getCount()
  {
    return count;
  }
  public void setCount(long count)
  {
    this.count = count;
  }
}
