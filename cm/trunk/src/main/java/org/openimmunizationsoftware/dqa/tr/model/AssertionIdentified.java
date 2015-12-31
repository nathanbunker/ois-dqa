package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class AssertionIdentified implements Serializable
{
  private int assertionIdentifiedId = 0;
  private AssertionField assertionField = null;
  private PentagonReport pentagonReport = null;
  private TestMessage testMessage = null;
  private int assertionCount = 0;
  private String assertionResult = "";
  private String testType = "";

  public TestMessage getTestMessage()
  {
    return testMessage;
  }

  public void setTestMessage(TestMessage testMessage)
  {
    this.testMessage = testMessage;
  }

  public String getTestType()
  {
    return testType;
  }

  public void setTestType(String testType)
  {
    this.testType = testType;
  }

  public int getAssertionIdentifiedId()
  {
    return assertionIdentifiedId;
  }

  public void setAssertionIdentifiedId(int assertionId)
  {
    this.assertionIdentifiedId = assertionId;
  }

  public AssertionField getAssertionField()
  {
    return assertionField;
  }

  public void setAssertionField(AssertionField assertionField)
  {
    this.assertionField = assertionField;
  }

  public PentagonReport getPentagonReport()
  {
    return pentagonReport;
  }

  public void setPentagonReport(PentagonReport pentagonReport)
  {
    this.pentagonReport = pentagonReport;
  }

  public int getAssertionCount()
  {
    return assertionCount;
  }

  public void setAssertionCount(int assertionCount)
  {
    this.assertionCount = assertionCount;
  }

  public String getAssertionResult()
  {
    return assertionResult;
  }

  public void setAssertionResult(String assertionResult)
  {
    this.assertionResult = assertionResult;
  }
}
