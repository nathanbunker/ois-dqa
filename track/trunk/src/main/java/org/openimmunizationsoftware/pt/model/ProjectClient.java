package org.openimmunizationsoftware.pt.model;

// Generated Dec 12, 2012 3:50:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * ProjectClient generated by hbm2java
 */
public class ProjectClient implements java.io.Serializable
{

  private ProjectClientId id;
  private String clientName;
  private Integer sortOrder;
  private String visible;
  private String clientAcronym;

  public ProjectClient() {
  }

  public ProjectClient(ProjectClientId id, String clientName) {
    this.id = id;
    this.clientName = clientName;
  }

  public ProjectClient(ProjectClientId id, String clientName, Integer sortOrder, String visible, String clientAcronym) {
    this.id = id;
    this.clientName = clientName;
    this.sortOrder = sortOrder;
    this.visible = visible;
    this.clientAcronym = clientAcronym;
  }

  public ProjectClientId getId() {
    return this.id;
  }

  public void setId(ProjectClientId id) {
    this.id = id;
  }

  public String getClientName() {
    return this.clientName;
  }

  public String getClientNameForDropdown() {
    if (getId().getClientCode().indexOf('-') > 0) {
      return "&nbsp;&nbsp;-&nbsp;&nbsp;" + getClientName();
    }
    return getClientName();
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public Integer getSortOrder() {
    return this.sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  public String getVisible() {
    return this.visible;
  }

  public void setVisible(String visible) {
    this.visible = visible;
  }

  public String getClientAcronym() {
    return this.clientAcronym;
  }

  public void setClientAcronym(String clientAcronym) {
    this.clientAcronym = clientAcronym;
  }

}
