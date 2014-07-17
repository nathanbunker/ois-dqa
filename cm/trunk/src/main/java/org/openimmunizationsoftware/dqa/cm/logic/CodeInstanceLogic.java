package org.openimmunizationsoftware.dqa.cm.logic;

import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CODE_LABEL;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CODE_STATUS;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CONTEXT_CODE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_HL7_CODE_TABLE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_INCLUSION_STATUS;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_INDICATES_TABLE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_USE_VALUE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.getAttributeType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;
import org.openimmunizationsoftware.dqa.cm.model.AttributeValue;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;

public class CodeInstanceLogic
{
  public static CodeInstance getCodeInstance(int codeInstanceId, Session dataSession)
  {
    CodeInstance codeInstance = (CodeInstance) dataSession.get(CodeInstance.class, codeInstanceId);
    populateAttributeValueList(codeInstance, dataSession);
    return codeInstance;
  }
  
  public static void saveCodeInstance(CodeInstance codeInstance, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(codeInstance);
    transaction.commit();
  }

  public static void populateAttributeValueList(CodeInstance codeInstance, Session dataSession)
  {
    List<AttributeValue> attributeValueList = new ArrayList<AttributeValue>();
    Map<AttributeType, List<AttributeInstance>> attributeTypeToValueMap = new HashMap<AttributeType, List<AttributeInstance>>();
    Query query = dataSession.createQuery("from AttributeInstance where codeInstance = ?");
    query.setParameter(0, codeInstance);
    List<AttributeInstance> attributeInstanceList = query.list();
    for (AttributeInstance ai : attributeInstanceList)
    {
      AttributeValue av = ai.getValue();
      attributeValueList.add(av);
      List<AttributeInstance> avList = attributeTypeToValueMap.get(av.getAttributeType());
      if (avList == null)
      {
        avList = new ArrayList<AttributeInstance>();
        attributeTypeToValueMap.put(av.getAttributeType(), avList);
      }
      avList.add(ai);
    }
    for (List<AttributeInstance> avList : attributeTypeToValueMap.values())
    {
      Collections.sort(avList, new Comparator<AttributeInstance>() {
        @Override
        public int compare(AttributeInstance ai1, AttributeInstance ai2)
        {
          return new Integer(ai1.getAcceptStatus().getOrder()).compareTo(new Integer(ai2.getAcceptStatus().getOrder()));
        }
      });
    }
    codeInstance.setAttributeTypeToValueMap(attributeTypeToValueMap);
    codeInstance.setAttributeValueList(attributeValueList);
  }
  
  public static void populateTableValuesFromAttributeInstance(CodeInstance codeInstance, Session dataSession)
  {
    String contextCode = codeInstance.getAttributeValue(getAttributeType(AT_CONTEXT_CODE, dataSession));
    if (contextCode != null)
    {
      int contextId = Integer.parseInt(contextCode);
      CodeMaster contextCodeMaster = CodeMasterLogic.getCodeMaster(contextId, dataSession);
      codeInstance.setContext(contextCodeMaster);
    }
    String indicatesTable = codeInstance.getAttributeValue(getAttributeType(AT_INDICATES_TABLE, dataSession));
    if (indicatesTable != null)
    {
      int indicatesTableId = Integer.parseInt(indicatesTable);
      CodeTable indicatesCodeTable = CodeTableLogic.getCodeTable(indicatesTableId, dataSession);
      codeInstance.setIndicatesTable(indicatesCodeTable);
    }
    String codeLabel = codeInstance.getAttributeValue(getAttributeType(AT_CODE_LABEL, dataSession));
    if (codeLabel != null)
    {
      codeInstance.setCodeLabel(codeLabel);
    }
    String useValue = codeInstance.getAttributeValue(getAttributeType(AT_USE_VALUE, dataSession));
    if (useValue != null)
    {
      codeInstance.setUseValue(useValue);
    }
    String codeStatus = codeInstance.getAttributeValue(getAttributeType(AT_CODE_STATUS, dataSession));
    if (codeStatus != null)
    {
      codeInstance.setCodeStatus(codeStatus);
    }
    String hl7CodeTable = codeInstance.getAttributeValue(getAttributeType(AT_HL7_CODE_TABLE, dataSession));
    if (hl7CodeTable != null)
    {
      codeInstance.setHl7CodeTable(hl7CodeTable);
    }
    String inclusionStatus = codeInstance.getAttributeValue(getAttributeType(AT_INCLUSION_STATUS, dataSession));
    if (inclusionStatus != null)
    {
      codeInstance.setInclusionStatus(InclusionStatus.get(inclusionStatus));
    }
  }
  
  

  public static List<CodeInstance> search(ReleaseVersion releaseVersion, String codeValue, String codeLabel, Session dataSession)
  {
    Query query = dataSession
        .createQuery("from CodeInstance where tableInstance.release = ? and lower(code.codeValue) like lower(?) and lower(codeLabel) like lower(?) order by tableInstance.tableLabel, code.codeValue");
    query.setParameter(0, releaseVersion);
    query.setParameter(1, codeValue + "%");
    query.setParameter(2, codeLabel + "%");
    List<CodeInstance> codeInstanceList = query.list();
    return codeInstanceList;
  }

  public static CodeInstance getCodeInstance(CodeMaster codeMaster, ReleaseVersion releaseVersion, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where code = ? and tableInstance.release = ?");
    query.setParameter(0, codeMaster);
    query.setParameter(1, releaseVersion);
    List<CodeInstance> codeInstanceList = query.list();
    if (codeInstanceList.size() > 0)
    {
      return codeInstanceList.get(0);
    }
    return null;
  }
  
  public static CodeInstance getCodeInstance(CodeTable codeTable, ReleaseVersion releaseVersion, String codeValue, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where tableInstance.table = ? and tableInstance.release = ? and code.codeValue = ?");
    query.setParameter(0, codeTable);
    query.setParameter(1, releaseVersion);
    query.setParameter(2, codeValue);
    List<CodeInstance> codeInstanceList = query.list();
    if (codeInstanceList.size() > 0)
    {
      return codeInstanceList.get(0);
    }
    return null;
  }

  public static void updateIssueCount(CodeInstance codeInstance, Session dataSession)
  {
    Query query = dataSession.createQuery("from AttributeInstance where codeInstance = ?");
    query.setParameter(0, codeInstance);
    List<AttributeInstance> attributeInstanceList = query.list();
    int issueCount = 0;
    for (AttributeInstance attributeInstance : attributeInstanceList)
    {
      if (attributeInstance.getAcceptStatus() != AcceptStatus.CONFIRMED && attributeInstance.getAcceptStatus() != AcceptStatus.REJECTED)
      {
        issueCount++;
      }
    }
    if (issueCount != codeInstance.getIssueCount())
    {
      Transaction transaction = dataSession.beginTransaction();
      codeInstance.setIssueCount(issueCount);
      dataSession.update(codeInstance);
      transaction.commit();
    }
  }

}
