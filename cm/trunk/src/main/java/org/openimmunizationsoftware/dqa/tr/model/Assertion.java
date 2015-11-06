package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class Assertion implements Serializable
{
  private int assertionId = 0;
  private AssertionField assertionField = null;
  private TestMessage testMessage = null;
  private String assertionResult = "";
  private String locationPath = "";

  public int getAssertionId()
  {
    return assertionId;
  }

  public void setAssertionId(int assertionId)
  {
    this.assertionId = assertionId;
  }

  public AssertionField getAssertionField()
  {
    return assertionField;
  }

  public void setAssertionField(AssertionField assertionField)
  {
    this.assertionField = assertionField;
  }

  public TestMessage getTestMessage()
  {
    return testMessage;
  }

  public void setTestMessage(TestMessage testMessage)
  {
    this.testMessage = testMessage;
  }

  public String getAssertionResult()
  {
    return assertionResult;
  }

  public void setAssertionResult(String assertionResult)
  {
    this.assertionResult = assertionResult;
  }

  public String getLocationPath()
  {
    return locationPath;
  }

  public void setLocationPath(String locationPath)
  {
    this.locationPath = locationPath;
  }
}
