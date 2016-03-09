package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;

public class CodeMasterLogic
{
  public static CodeMaster getCodeMaster(int codeId, Session dataSession)
  {
    return (CodeMaster) dataSession.get(CodeMaster.class, codeId);
  }

  public static CodeMaster getCodeMaster(CodeTable codeTable, String codeValue, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeMaster where table = ? and codeValue = ?");
    query.setParameter(0, codeTable);
    query.setParameter(1, codeValue);
    @SuppressWarnings("unchecked")
    List<CodeMaster> codeMasterList = query.list();
    if (codeMasterList.size() > 0)
    {
      return codeMasterList.get(0);
    }
    return null;
  }

  public static List<CodeInstance> getCodeValues(CodeTableInstance codeTableInstance, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where tableInstance = ? order by codeLabel, code.codeValue");
    query.setParameter(0, codeTableInstance);
    @SuppressWarnings("unchecked")
    List<CodeInstance> codeInstanceList = query.list();
    return codeInstanceList;
  }

  public static List<CodeInstance> getCodeValues(CodeTableInstance codeTableInstance, CodeInstance contextCodeInstance, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where tableInstance = ? and context = ? order by codeLabel, code.codeValue");
    query.setParameter(0, codeTableInstance);
    query.setParameter(1, contextCodeInstance.getCode());
    @SuppressWarnings("unchecked")
    List<CodeInstance> codeInstanceList = query.list();
    return codeInstanceList;
  }

  public static void updateCodeMaster(CodeMaster codeMaster, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(codeMaster);
    transaction.commit();
  }
  
  public static void addCodeMaster(CodeMaster codeMaster, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(codeMaster);
    transaction.commit();
  }

  public synchronized static void saveCodeMaster(CodeMaster codeMaster, Session dataSession)
  {
    codeMaster.setCodeId(generateNextCode(codeMaster, dataSession));
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(codeMaster);
    transaction.commit();
  }

  private static int generateNextCode(CodeMaster codeMaster, Session dataSession)
  {
    // The ids are assigned sequentially within a bank of 10,000. Table 1 starts
    // at 10,000, Table 2 at 20,000, etc.
    // If a table has more than 10,000 values then it starts with ids in banks
    // of 10,000,000. Table 1 continues at 10,000,000 and table 2 at 20,000,000,
    // etc. This gives an effective cap of 1,000 tables with 10,010,000 codes in each. Should be more than enough room.
    Query query = dataSession.createQuery("from CodeMaster where table = ? order by codeId desc");
    query.setParameter(0, codeMaster.getTable());
    @SuppressWarnings("unchecked")
    List<CodeMaster> codeMasterList = query.list();
    int codeId;
    if (codeMasterList.size() == 0)
    {
      codeId = codeMaster.getTable().getTableId() * 10000 + 1;
    } else
    {
      codeId = codeMasterList.get(0).getCodeId() + 1;
    }
    int topId = (codeMaster.getTable().getTableId() + 1) * 10000;
    if (codeId == topId)
    {
      codeId = codeMaster.getTable().getTableId() * 1000 * 10000;
    }
    return codeId;
  }
}
