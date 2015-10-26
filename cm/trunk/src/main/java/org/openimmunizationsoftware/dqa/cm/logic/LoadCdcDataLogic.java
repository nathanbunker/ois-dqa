package org.openimmunizationsoftware.dqa.cm.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class LoadCdcDataLogic
{

  public static enum LoadStatus {
    NO_CHANGE, ADDED, NOT_UPDATED
  }

  public static class LoadResult
  {
    private CodeInstance codeInstance = null;
    private LoadStatus loadStatus = null;

    public CodeInstance getCodeInstance()
    {
      return codeInstance;
    }

    public void setCodeInstance(CodeInstance codeInstance)
    {
      this.codeInstance = codeInstance;
    }

    public LoadStatus getLoadStatus()
    {
      return loadStatus;
    }

    public void setLoadStatus(LoadStatus loadStatus)
    {
      this.loadStatus = loadStatus;
    }
  }

  public static List<LoadResult> loadCdcData(CodeTableInstance codeTableInstance, User user, String data, Session dataSession)
  {
    List<LoadResult> loadResultList = new ArrayList<LoadResult>();
    final CodeTable table = codeTableInstance.getTable();
    try
    {
      BufferedReader reader = new BufferedReader(new StringReader(data));
      String line;
      while ((line = reader.readLine()) != null)
      {
        if (line.trim().startsWith("* "))
        {
          continue;
        }
        String fields[] = line.split("\\|");
        String codeValue = readField(0, fields);
        String codeLabel = readField(1, fields);
        CodeInstance codeInstance = CodeInstanceLogic.getCodeInstance(codeTableInstance, codeValue, dataSession);
        LoadResult loadResult = new LoadResult();
        if (codeInstance == null)
        {
          CodeMaster codeMaster = CodeMasterLogic.getCodeMaster(table, codeValue, dataSession);
          if (codeMaster == null)
          {
            codeMaster = new CodeMaster();
            codeMaster.setTable(table);
            codeMaster.setCodeValue(codeValue);
            CodeMasterLogic.saveCodeMaster(codeMaster, dataSession);
          }
          codeInstance = new CodeInstance();
          codeInstance.setCode(codeMaster);
          codeInstance.setUseValue(codeMaster.getCodeValue());
          codeInstance.setCodeLabel(codeLabel);
          codeInstance.setTableInstance(codeTableInstance);
          codeInstance.setInclusionStatus(InclusionStatus.PROPOSED_INCLUDE);
          codeInstance.setCodeStatus(CodeInstance.CODE_STATUS_VALID);
          CodeInstanceLogic.addCodeInstance(codeInstance, dataSession);
          CodeInstanceLogic.transferValuesToAttributes(codeInstance, user, dataSession);
          CodeInstanceLogic.updateIssueCount(codeInstance, dataSession);
          loadResult.setCodeInstance(codeInstance);
          loadResult.setLoadStatus(LoadStatus.ADDED);
        } else
        {
          loadResult.setCodeInstance(codeInstance);
          loadResult.setLoadStatus(LoadStatus.NO_CHANGE);
        }
        loadResultList.add(loadResult);
      }
    } catch (IOException ioe)
    {
      throw new RuntimeException(ioe);
    }
    return loadResultList;
  }

  private static String readField(int pos, String[] fields)
  {
    if (pos < fields.length)
    {
      String value = fields[pos];
      if (value == null)
      {
        return "";
      }
      return value.trim();
    }
    return "";
  }
}
