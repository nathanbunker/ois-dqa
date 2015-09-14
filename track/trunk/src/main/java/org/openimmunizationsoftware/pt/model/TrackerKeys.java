package org.openimmunizationsoftware.pt.model;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * TrackerKeys generated by hbm2java
 */
public class TrackerKeys implements java.io.Serializable
{

  private TrackerKeysId id;
  private String keyValue;
  private byte[] keyContent;

  public TrackerKeys() {
  }

  public TrackerKeys(TrackerKeysId id) {
    this.id = id;
  }

  public TrackerKeys(TrackerKeysId id, String keyValue, byte[] keyContent) {
    this.id = id;
    this.keyValue = keyValue;
    this.keyContent = keyContent;
  }

  public TrackerKeysId getId()
  {
    return this.id;
  }

  public void setId(TrackerKeysId id)
  {
    this.id = id;
  }

  public String getKeyValue()
  {
    return this.keyValue;
  }

  public void setKeyValue(String keyValue)
  {
    this.keyValue = keyValue;
  }

  public byte[] getKeyContent()
  {
    return this.keyContent;
  }

  public void setKeyContent(byte[] keyContent)
  {
    this.keyContent = keyContent;
  }

}
