package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;

public class Resource implements Serializable
{
  private int resourceId = 0;
  private String displayLabel = "";
  private String url = "";
  
  public int getResourceId()
  {
    return resourceId;
  }
  public void setResourceId(int resourceId)
  {
    this.resourceId = resourceId;
  }
  public String getDisplayLabel()
  {
    return displayLabel;
  }
  public void setDisplayLabel(String displayLabel)
  {
    this.displayLabel = displayLabel;
  }
  public String getUrl()
  {
    return url;
  }
  public void setUrl(String url)
  {
    this.url = url;
  }
}
