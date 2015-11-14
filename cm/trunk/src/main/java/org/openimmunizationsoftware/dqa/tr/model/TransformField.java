package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class TransformField implements Serializable
{
  private int transformFieldId = 0;
  private String transformText = "";
  private boolean transformExpected = false;

  public int getTransformFieldId()
  {
    return transformFieldId;
  }

  public void setTransformFieldId(int transformFieldId)
  {
    this.transformFieldId = transformFieldId;
  }

  public String getTransformText()
  {
    return transformText;
  }

  public void setTransformText(String transformText)
  {
    this.transformText = transformText;
  }

  public boolean isTransformExpected()
  {
    return transformExpected;
  }

  public void setTransformExpected(boolean transformExpected)
  {
    this.transformExpected = transformExpected;
  }

}
