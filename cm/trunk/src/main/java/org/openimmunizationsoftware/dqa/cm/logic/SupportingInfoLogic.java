package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.Resource;
import org.openimmunizationsoftware.dqa.cm.model.SupportingInfo;

public class SupportingInfoLogic
{
  public static List<SupportingInfo> getSupportingInfoList(CodeMaster code, Session dataSession)
  {
    Query query = dataSession.createQuery("from SupportingInfo where code = ? order by effectiveDate");
    query.setParameter(0, code);
    @SuppressWarnings("unchecked")
    List<SupportingInfo> supportingInfoList = query.list();
    return supportingInfoList;
  }
  
  public static List<Resource> getResourceList(Session dataSession)
  {
    Query query = dataSession.createQuery("from Resource order by displayLabel");
    @SuppressWarnings("unchecked")
    List<Resource> resourceList = query.list();
    return resourceList;
  }
}
