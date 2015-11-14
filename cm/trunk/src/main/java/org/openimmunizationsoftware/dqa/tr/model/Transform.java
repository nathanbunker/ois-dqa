package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class Transform implements Serializable
{
  private int transformId = 0;
  private TransformField transformField = null;
  private TestConducted testConducted = null;
  private String scenarioName = "";

  public int getTransformId()
  {
    return transformId;
  }

  public void setTransformId(int transformId)
  {
    this.transformId = transformId;
  }

  public TransformField getTransformField()
  {
    return transformField;
  }

  public void setTransformField(TransformField transformField)
  {
    this.transformField = transformField;
  }

  public TestConducted getTestConducted()
  {
    return testConducted;
  }

  public void setTestConducted(TestConducted testConducted)
  {
    this.testConducted = testConducted;
  }

  public String getScenarioName()
  {
    return scenarioName;
  }

  public void setScenarioName(String scenarioName)
  {
    this.scenarioName = scenarioName;
  }

}
