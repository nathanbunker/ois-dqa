package org.openimmunizationsoftware.pt.model;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * ProjectContactPositionId generated by hbm2java
 */
public class ProjectContactPositionId implements java.io.Serializable
{

  private int contactId;
  private Date positionDate;

  public ProjectContactPositionId() {
  }

  public ProjectContactPositionId(int contactId, Date positionDate) {
    this.contactId = contactId;
    this.positionDate = positionDate;
  }

  public int getContactId()
  {
    return this.contactId;
  }

  public void setContactId(int contactId)
  {
    this.contactId = contactId;
  }

  public Date getPositionDate()
  {
    return this.positionDate;
  }

  public void setPositionDate(Date positionDate)
  {
    this.positionDate = positionDate;
  }

  public boolean equals(Object other)
  {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof ProjectContactPositionId))
      return false;
    ProjectContactPositionId castOther = (ProjectContactPositionId) other;

    return (this.getContactId() == castOther.getContactId())
        && ((this.getPositionDate() == castOther.getPositionDate()) || (this.getPositionDate() != null && castOther.getPositionDate() != null && this
            .getPositionDate().equals(castOther.getPositionDate())));
  }

  public int hashCode()
  {
    int result = 17;

    result = 37 * result + this.getContactId();
    result = 37 * result + (getPositionDate() == null ? 0 : this.getPositionDate().hashCode());
    return result;
  }

}
