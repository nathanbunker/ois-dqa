package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class AssertionField implements Serializable
{
  private int assertionFieldId = 0;
  private String assertionType = "";
  private String assertionDescription = "";

  public int getAssertionFieldId()
  {
    return assertionFieldId;
  }

  public void setAssertionFieldId(int assertionFieldId)
  {
    this.assertionFieldId = assertionFieldId;
  }

  public String getAssertionType()
  {
    return assertionType;
  }

  public void setAssertionType(String assertionType)
  {
    this.assertionType = assertionType;
  }

  public String getAssertionDescription()
  {
    return assertionDescription;
  }

  public void setAssertionDescription(String assertionDescription)
  {
    this.assertionDescription = assertionDescription;
  }
}
