package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;

public class CodeTableLogic
{
  public static List<CodeTableInstance> getCodeTables(ReleaseVersion releaseVersion, Session dataSession)
  {
    List<CodeTableInstance> codeTableInstanceList = null;
    Query query = dataSession.createQuery("from CodeTableInstance where release = ? order by tableLabel");
    query.setParameter(0, releaseVersion);
    codeTableInstanceList = query.list();
    return codeTableInstanceList;
  }
  
  public static CodeTable getCodeTable(int tableId, Session dataSession)
  {
    return (CodeTable) dataSession.get(CodeTable.class, tableId);
  }

  public static CodeTableInstance getCodeTableInstance(CodeTable codeTable, ReleaseVersion releaseVersion, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeTableInstance where table = ? and release = ?");
    query.setParameter(0, codeTable);
    query.setParameter(1, releaseVersion);
    List<CodeTableInstance> codeTableInstanceList = query.list();
    if (codeTableInstanceList.size() > 0)
    {
      return codeTableInstanceList.get(0);
    }
    return null;
  }

}
