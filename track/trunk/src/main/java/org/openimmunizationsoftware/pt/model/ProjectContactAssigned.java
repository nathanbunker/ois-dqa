package org.openimmunizationsoftware.pt.model;

import java.util.Date;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * ProjectContactAssigned generated by hbm2java
 */
public class ProjectContactAssigned implements java.io.Serializable
{

  private ProjectContactAssignedId id;
  private Integer priority;
  private String emailAlert;
  private Integer updateDue = 0;
  private Date updateLast;
  
  public Date getUpdateLast()
  {
    return updateLast;
  }

  public void setUpdateLast(Date updateLast)
  {
    this.updateLast = updateLast;
  }

  public Integer getUpdateDue()
  {
    return updateDue;
  }

  public void setUpdateDue(Integer updateDue)
  {
    this.updateDue = updateDue;
  }

  private ProjectContact projectContact = null;
  private Project project = null;

  public ProjectContact getProjectContact()
  {
    return projectContact;
  }

  public void setProjectContact(ProjectContact projectContact)
  {
    this.projectContact = projectContact;
  }

  public Project getProject()
  {
    return project;
  }

  public void setProject(Project project)
  {
    this.project = project;
  }

  public ProjectContactAssigned() {
  }

  public ProjectContactAssigned(ProjectContactAssignedId id, String emailAlert) {
    this.id = id;
    this.emailAlert = emailAlert;
  }

  public ProjectContactAssigned(ProjectContactAssignedId id, Integer priority, String emailAlert) {
    this.id = id;
    this.priority = priority;
    this.emailAlert = emailAlert;
  }

  public ProjectContactAssignedId getId()
  {
    return this.id;
  }

  public void setId(ProjectContactAssignedId id)
  {
    this.id = id;
  }

  public Integer getPriority()
  {
    return this.priority;
  }

  public void setPriority(Integer priority)
  {
    this.priority = priority;
  }

  public String getEmailAlert()
  {
    return this.emailAlert;
  }

  public void setEmailAlert(String emailAlert)
  {
    this.emailAlert = emailAlert;
  }

}
