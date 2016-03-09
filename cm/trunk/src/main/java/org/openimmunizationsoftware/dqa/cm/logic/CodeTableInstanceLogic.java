package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;

public class CodeTableInstanceLogic
{
  public static void updateIssueCount(CodeTableInstance codeTableInstance, boolean updateCodeInstanceCount, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where tableInstance = ?");
    query.setParameter(0, codeTableInstance);
    @SuppressWarnings("unchecked")
    List<CodeInstance> codeInstanceList = query.list();
    int issueCount = 0;
    for (CodeInstance codeInstance : codeInstanceList)
    {
      if (updateCodeInstanceCount)
      {
        CodeInstanceLogic.updateIssueCount(codeInstance, dataSession);
      }
      issueCount += codeInstance.getIssueCount();
    }
    if (issueCount != codeTableInstance.getIssueCount())
    {
      Transaction transaction = dataSession.beginTransaction();
      codeTableInstance.setIssueCount(issueCount);
      dataSession.update(codeTableInstance);
      transaction.commit();
    }
  }

  public static CodeTableInstance getCodeTableInstance(int codeTableInstanceId, Session dataSession)
  {
    return (CodeTableInstance) dataSession.get(CodeTableInstance.class, codeTableInstanceId);
  }
  
  public static void updateCodeTableInstance(CodeTableInstance codeTableInstance, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(codeTableInstance);
    transaction.commit();
  }
}
