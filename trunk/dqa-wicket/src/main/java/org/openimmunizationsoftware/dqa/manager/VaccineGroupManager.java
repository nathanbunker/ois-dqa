package org.openimmunizationsoftware.dqa.manager;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.db.model.VaccineCvxGroup;
import org.openimmunizationsoftware.dqa.db.model.VaccineGroup;

public class VaccineGroupManager
{
  private static VaccineGroupManager singleton = new VaccineGroupManager();

  public static VaccineGroupManager getVaccineGroupManager()
  {
    if (singleton == null)
    {
      singleton = new VaccineGroupManager();
    }
    return singleton;
  }

  private Map<String, VaccineGroup> vaccineGroups = new HashMap<String, VaccineGroup>();
  private Map<String, List<VaccineGroup>> vaccineGroupMapList = new HashMap<String, List<VaccineGroup>>();
  private Map<String, Set<VaccineGroup>> vaccineGroupMapSet = new HashMap<String, Set<VaccineGroup>>();
  
  public VaccineGroup getVaccineGroup(String groupCode)
  {
    return vaccineGroups.get(groupCode);
  }

  public List<VaccineGroup> getVaccineGroupList(String groupStatus)
  {
    List<VaccineGroup> vaccineGroupList = vaccineGroupMapList.get(groupStatus);
    if (vaccineGroupList == null)
    {
      vaccineGroupList = new ArrayList<VaccineGroup>();
      vaccineGroupMapList.put(groupStatus, vaccineGroupList);
    }
    return vaccineGroupList;
  }

  public VaccineGroupManager() {
    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    Query query = session.createQuery("from VaccineCvxGroup");
    List<VaccineCvxGroup> vaccineCvxGroups = query.list();
    for (VaccineCvxGroup vaccineCvxGroup : vaccineCvxGroups)
    {
      VaccineGroup vaccineGroup = vaccineCvxGroup.getVaccineGroup();
      List<VaccineGroup> targetList = vaccineGroupMapList.get(vaccineGroup.getGroupStatus());
      Set<VaccineGroup> targetSet = vaccineGroupMapSet.get(vaccineGroup.getGroupStatus());
      if (targetList == null)
      {
        targetList = new ArrayList<VaccineGroup>();
        vaccineGroupMapList.put(vaccineGroup.getGroupStatus(), targetList);
      }
      if (targetSet == null)
      {
        targetSet = new HashSet<VaccineGroup>();
        vaccineGroupMapSet.put(vaccineGroup.getGroupStatus(), targetSet);
      }
      if (!targetSet.contains(vaccineGroup))
      {
        targetList.add(vaccineGroup);
        targetSet.add(vaccineGroup);
      }
      vaccineGroup.getVaccineCvxList().add(vaccineCvxGroup.getVaccineCvx());
      vaccineGroups.put(vaccineGroup.getGroupCode(), vaccineGroup);
    }

    session.close();
  }
}
