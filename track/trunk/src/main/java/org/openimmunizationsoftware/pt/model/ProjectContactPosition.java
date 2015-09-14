package org.openimmunizationsoftware.pt.model;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * ProjectContactPosition generated by hbm2java
 */
public class ProjectContactPosition implements java.io.Serializable
{

  private ProjectContactPositionId id;
  private String positionLabel;
  private String positionDetail;
  private Float positionLat;
  private Float positionLog;

  public ProjectContactPosition() {
  }

  public ProjectContactPosition(ProjectContactPositionId id) {
    this.id = id;
  }

  public ProjectContactPosition(ProjectContactPositionId id, String positionLabel, String positionDetail, Float positionLat, Float positionLog) {
    this.id = id;
    this.positionLabel = positionLabel;
    this.positionDetail = positionDetail;
    this.positionLat = positionLat;
    this.positionLog = positionLog;
  }

  public ProjectContactPositionId getId()
  {
    return this.id;
  }

  public void setId(ProjectContactPositionId id)
  {
    this.id = id;
  }

  public String getPositionLabel()
  {
    return this.positionLabel;
  }

  public void setPositionLabel(String positionLabel)
  {
    this.positionLabel = positionLabel;
  }

  public String getPositionDetail()
  {
    return this.positionDetail;
  }

  public void setPositionDetail(String positionDetail)
  {
    this.positionDetail = positionDetail;
  }

  public Float getPositionLat()
  {
    return this.positionLat;
  }

  public void setPositionLat(Float positionLat)
  {
    this.positionLat = positionLat;
  }

  public Float getPositionLog()
  {
    return this.positionLog;
  }

  public void setPositionLog(Float positionLog)
  {
    this.positionLog = positionLog;
  }

}
