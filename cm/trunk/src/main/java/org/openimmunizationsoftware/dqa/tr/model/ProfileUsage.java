package org.openimmunizationsoftware.dqa.tr.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileCategory;

public class ProfileUsage
{
  private int profileUsageId = 0;
  private ProfileCategory category = null;
  private String label = "";
  private String version = "";
  
  public static ProfileUsage getBaseProfileUsage(Session dataSession)
  {
    ProfileUsage baseProfileUsage = null;
    Query query = dataSession.createQuery("from ProfileUsage where category = 'US' and label = 'Base'");
    List<ProfileUsage> profileUsageList = query.list();
    if (profileUsageList.size() > 0)
    {
      baseProfileUsage = profileUsageList.get(0);
    }
    return baseProfileUsage;
  }
  
  public String getCategoryString()
  {
    return category == null ? "" : category.toString();
  }

  public void setCategoryString(String categoryString)
  {
    this.category = ProfileCategory.valueOf(categoryString.toUpperCase());
  }

  public int getProfileUsageId()
  {
    return profileUsageId;
  }

  public void setProfileUsageId(int profileUsageId)
  {
    this.profileUsageId = profileUsageId;
  }

  public ProfileCategory getCategory() {
    return category;
  }

  public void setCategory(ProfileCategory category) {
    this.category = category;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
  
  @Override
  public String toString() {
    if (version == null || version.equals("")) {
      return category + " - " + label;
    }
    return category + " - " + label + " - " + version;
  }


}
