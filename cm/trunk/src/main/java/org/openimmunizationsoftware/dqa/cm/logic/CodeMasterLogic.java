package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;

public class CodeMasterLogic
{
  public static CodeMaster getCodeMaster(int codeId, Session dataSession)
  {
    return (CodeMaster) dataSession.get(CodeMaster.class, codeId);
  }

  public static List<CodeInstance> getCodeValues(CodeTableInstance codeTableInstance, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where tableInstance = ? order by codeLabel, code.codeValue");
    query.setParameter(0, codeTableInstance);
    List<CodeInstance> codeInstanceList = query.list();
    return codeInstanceList;
  }

  public static List<CodeInstance> getCodeValues(CodeTableInstance codeTableInstance, CodeInstance contextCodeInstance, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where tableInstance = ? and context = ? order by codeLabel, code.codeValue");
    query.setParameter(0, codeTableInstance);
    query.setParameter(1, contextCodeInstance.getCode());
    List<CodeInstance> codeInstanceList = query.list();
    return codeInstanceList;
  }
}
