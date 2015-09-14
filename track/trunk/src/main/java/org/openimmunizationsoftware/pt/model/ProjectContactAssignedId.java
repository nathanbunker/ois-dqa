package org.openimmunizationsoftware.pt.model;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * ProjectContactAssignedId generated by hbm2java
 */
public class ProjectContactAssignedId implements java.io.Serializable
{

  private int contactId;
  private int projectId;

  public ProjectContactAssignedId() {
  }

  public ProjectContactAssignedId(int contactId, int projectId) {
    this.contactId = contactId;
    this.projectId = projectId;
  }

  public int getContactId()
  {
    return this.contactId;
  }

  public void setContactId(int contactId)
  {
    this.contactId = contactId;
  }

  public int getProjectId()
  {
    return this.projectId;
  }

  public void setProjectId(int projectId)
  {
    this.projectId = projectId;
  }

  public boolean equals(Object other)
  {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof ProjectContactAssignedId))
      return false;
    ProjectContactAssignedId castOther = (ProjectContactAssignedId) other;

    return (this.getContactId() == castOther.getContactId()) && (this.getProjectId() == castOther.getProjectId());
  }

  public int hashCode()
  {
    int result = 17;

    result = 37 * result + this.getContactId();
    result = 37 * result + this.getProjectId();
    return result;
  }

}
