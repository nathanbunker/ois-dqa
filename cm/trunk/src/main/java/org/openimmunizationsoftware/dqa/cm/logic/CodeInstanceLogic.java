package org.openimmunizationsoftware.dqa.cm.logic;

import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CHANGE_PRIORITY;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CODE_LABEL;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CODE_STATUS;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CODE_TABLE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CONCEPT_TYPE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CONTEXT_CODE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_CVX_CODE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_FIELD_VALUE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_HL7_CODE_TABLE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_HL7_ERROR_CODE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_HL7_REFERENCE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_INCLUSION_STATUS;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_INDICATES_TABLE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_ISSUE_DESCRIPTION;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_ISSUE_TYPE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_LICENSED_USE_START_DATE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_LICENSED_VALID_END_DATE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_LICENSED_VALID_START_DATE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_MVX_CODE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_REPORT_DENOMINATOR;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_TARGET_FIELD;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_TARGET_OBJECT;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_TEST_AGE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_USE_MONTH_END;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_USE_MONTH_START;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_USE_VALUE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.AT_lICENSED_USE_END_DATE;
import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.getAttributeType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AttributeAssigned;
import org.openimmunizationsoftware.dqa.cm.model.AttributeComment;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;
import org.openimmunizationsoftware.dqa.cm.model.AttributeValue;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.PositionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class CodeInstanceLogic
{

  private static Map<Integer, String> attributeTypeIdToTempName = new HashMap<Integer, String>();
  static
  {
    attributeTypeIdToTempName.put(AT_TARGET_OBJECT, "targetObject");
    attributeTypeIdToTempName.put(AT_TARGET_FIELD, "targetField");
    attributeTypeIdToTempName.put(AT_ISSUE_TYPE, "issueType");
    attributeTypeIdToTempName.put(AT_FIELD_VALUE, "fieldValue");
    attributeTypeIdToTempName.put(AT_CODE_TABLE, "codeTableId");
    attributeTypeIdToTempName.put(AT_CHANGE_PRIORITY, "changePriority");
    attributeTypeIdToTempName.put(AT_REPORT_DENOMINATOR, "reportDenominator");
    attributeTypeIdToTempName.put(AT_HL7_REFERENCE, "hl7Reference");
    attributeTypeIdToTempName.put(AT_HL7_ERROR_CODE, "hl7ErrorCode");
    attributeTypeIdToTempName.put(AT_ISSUE_DESCRIPTION, "issueDescription");

    attributeTypeIdToTempName.put(AT_LICENSED_VALID_START_DATE, "validStartDate");
    attributeTypeIdToTempName.put(AT_LICENSED_USE_START_DATE, "useStartDate");
    attributeTypeIdToTempName.put(AT_lICENSED_USE_END_DATE, "useEndDate");
    attributeTypeIdToTempName.put(AT_LICENSED_VALID_END_DATE, "validEndDate");
    attributeTypeIdToTempName.put(AT_USE_MONTH_START, "useMonthStart");
    attributeTypeIdToTempName.put(AT_USE_MONTH_END, "useMonthEnd");
    attributeTypeIdToTempName.put(AT_TEST_AGE, "testAge");
    attributeTypeIdToTempName.put(AT_CONCEPT_TYPE, "conceptType");
    attributeTypeIdToTempName.put(AT_CVX_CODE, "cvxCode");
    attributeTypeIdToTempName.put(AT_MVX_CODE, "mvxCode");

  }

  public static CodeInstance getCodeInstance(int codeInstanceId, Session dataSession)
  {
    CodeInstance codeInstance = (CodeInstance) dataSession.get(CodeInstance.class, codeInstanceId);
    populateAttributeValueList(codeInstance, dataSession);
    return codeInstance;
  }

  public static void updateCodeInstance(CodeInstance codeInstance, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(codeInstance);
    transaction.commit();
  }

  public static void saveCodeInstance(CodeInstance codeInstance, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(codeInstance);
    transaction.commit();
  }

  public static void populateAttributeValueList(CodeInstance codeInstance, Session dataSession)
  {
    List<AttributeValue> attributeValueList = new ArrayList<AttributeValue>();
    Map<AttributeType, List<AttributeInstance>> attributeTypeToValueMap = new HashMap<AttributeType, List<AttributeInstance>>();
    Query query = dataSession.createQuery("from AttributeInstance where codeInstance = ?");
    query.setParameter(0, codeInstance);
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    List<CodeInstance> codeInstanceList = query.list();
    return codeInstanceList;
  }

  public static CodeInstance getCodeInstance(CodeMaster codeMaster, ReleaseVersion releaseVersion, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where code = ? and tableInstance.release = ?");
    query.setParameter(0, codeMaster);
    query.setParameter(1, releaseVersion);
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    List<CodeInstance> codeInstanceList = query.list();
    if (codeInstanceList.size() > 0)
    {
      return codeInstanceList.get(0);
    }
    return null;
  }

  public static CodeInstance getCodeInstance(CodeTableInstance codeTableInstance, String codeValue, Session dataSession)
  {
    Query query = dataSession.createQuery("from CodeInstance where tableInstance = ? and code.codeValue = ?");
    query.setParameter(0, codeTableInstance);
    query.setParameter(1, codeValue);
    @SuppressWarnings("unchecked")
    List<CodeInstance> codeInstanceList = query.list();
    if (codeInstanceList.size() > 0)
    {
      return codeInstanceList.get(0);
    }
    return null;
  }

  public static boolean updateIssueCount(CodeInstance codeInstance, Session dataSession)
  {
    Query query = dataSession.createQuery("from AttributeInstance where codeInstance = ?");
    query.setParameter(0, codeInstance);
    @SuppressWarnings("unchecked")
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
      return true;
    }
    return false;
  }

  public static void addCodeInstance(CodeInstance codeInstance, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(codeInstance);
    transaction.commit();
  }

  public static void transferValuesToAttributes(CodeInstance codeInstance, User user, Session dataSession)
  {
    CodeTable codeTable = codeInstance.getTableInstance().getTable();
    Query query = dataSession.createQuery("from AttributeAssigned where table = ? ");
    query.setParameter(0, codeTable);
    @SuppressWarnings("unchecked")
    List<AttributeAssigned> attributeAssignedlIst = query.list();
    for (AttributeAssigned attributeAssigned : attributeAssignedlIst)
    {
      AttributeType attributeType = attributeAssigned.getAttributeType();
      CodeInstanceLogic.transferValuesToAttributes(attributeType, codeInstance, user, dataSession);
    }
  }

  public static int transferValuesToAttributes(AttributeType attributeType, CodeInstance codeInstance, User user, Session dataSession)
  {
    int saveCount = 0;
    Query query;
    Map<String, String> tempValueMap = createTempValueMap(codeInstance);
    query = dataSession.createQuery("from AttributeInstance where value.attributeType = ? and codeInstance = ?");
    query.setParameter(0, attributeType);
    query.setParameter(1, codeInstance);
    @SuppressWarnings("unchecked")
    List<AttributeInstance> attributeInstanceList = query.list();
    if (attributeInstanceList.size() == 0)
    {
      AttributeValue attributeValue = new AttributeValue();
      attributeValue.setAttributeType(attributeType);
      attributeValue.setCode(codeInstance.getCode());
      boolean hasValue = true;
      String researchComment = null;
      String problemComment = null;
      if (attributeType.getAttributeTypeId() == AT_CODE_LABEL)
      {
        attributeValue.setAttributeValue(codeInstance.getCodeLabel());
      } else if (attributeType.getAttributeTypeId() == AT_USE_VALUE)
      {
        attributeValue.setAttributeValue(codeInstance.getUseValue());
      } else if (attributeType.getAttributeTypeId() == AT_CODE_STATUS)
      {
        attributeValue.setAttributeValue(codeInstance.getCodeStatus());
        if (codeInstance.getCodeStatus().equals(""))
        {
          problemComment = "Code Status needs to be determined and set";
        }
      } else if (attributeType.getAttributeTypeId() == AT_CONTEXT_CODE)
      {
        if (codeInstance.getContext() == null)
        {
          hasValue = false;
        } else
        {
          attributeValue.setAttributeValue("" + codeInstance.getContext().getCodeId());
        }
      } else if (attributeType.getAttributeTypeId() == AT_INDICATES_TABLE)
      {
        if (codeInstance.getIndicatesTable() == null)
        {
          hasValue = false;
        } else
        {
          attributeValue.setAttributeValue("" + codeInstance.getIndicatesTable().getTableId());
        }
      } else if (attributeType.getAttributeTypeId() == AT_HL7_CODE_TABLE)
      {
        if (codeInstance.getHl7CodeTable() == null || codeInstance.getHl7CodeTable().equals(""))
        {
          hasValue = false;
        } else
        {
          attributeValue.setAttributeValue(codeInstance.getHl7CodeTable());
        }
      } else if (attributeType.getAttributeTypeId() == AT_INCLUSION_STATUS)
      {
        attributeValue.setAttributeValue(codeInstance.getInclusionStatusString());
        if (codeInstance.getInclusionStatus() == InclusionStatus.PROPOSED_INCLUDE)
        {
          problemComment = "Proposed for inclusion, please review to confirm";
        }
        else if (codeInstance.getInclusionStatus() == InclusionStatus.PROPOSED_REMOVE)
        {
          problemComment = "Proposed for removal, please review to confirm";
        }
        
      } else if (attributeTypeIdToTempName.containsKey(attributeType.getAttributeTypeId()))
      {
        String value = tempValueMap.get(attributeTypeIdToTempName.get(attributeType.getAttributeTypeId()));

        if (value == null || value.equals("") || value.equals("01/01/2100"))
        {
          hasValue = false;
        } else
        {
          attributeValue.setAttributeValue(value);
          if (value.equals("01/01/1970"))
          {
            researchComment = "Placeholder value needs to be updated to correct value.";
          }
        }
      }
      if (hasValue)
      {
        Transaction transaction = dataSession.beginTransaction();
        dataSession.save(attributeValue);
        AttributeInstance attributeInstance = new AttributeInstance();
        attributeInstance.setCodeInstance(codeInstance);
        attributeInstance.setValue(attributeValue);
        if (problemComment != null)
        {
          attributeInstance.setAcceptStatus(AcceptStatus.PROBLEM);
        } else if (researchComment != null)
        {
          attributeInstance.setAcceptStatus(AcceptStatus.RESEARCH);
        } else
        {
          attributeInstance.setAcceptStatus(AcceptStatus.CONFIRMED);
        }
        dataSession.save(attributeInstance);
        AttributeComment attributeComment = new AttributeComment();
        attributeComment.setValue(attributeValue);
        attributeComment.setUser(user);
        attributeComment.setCommentText("Initial value");
        attributeComment.setEntryDate(new Date());
        attributeComment.setPositionStatus(PositionStatus.NEUTRAL);
        dataSession.save(attributeComment);
        if (attributeType.getAttributeTypeId() == AT_CODE_LABEL && tempValueMap.get("notes") != null)
        {
          attributeComment = new AttributeComment();
          attributeComment.setValue(attributeValue);
          attributeComment.setUser(user);
          attributeComment.setCommentText("Note from original google spreadsheet: " + tempValueMap.get("notes"));
          attributeComment.setEntryDate(new Date());
          attributeComment.setPositionStatus(PositionStatus.NEUTRAL);
          dataSession.save(attributeComment);
        }
        if (researchComment != null)
        {
          attributeComment = new AttributeComment();
          attributeComment.setValue(attributeValue);
          attributeComment.setUser(user);
          attributeComment.setCommentText(researchComment);
          attributeComment.setEntryDate(new Date());
          attributeComment.setPositionStatus(PositionStatus.RESEARCH);
          dataSession.save(attributeComment);
        }
        if (problemComment != null)
        {
          attributeComment = new AttributeComment();
          attributeComment.setValue(attributeValue);
          attributeComment.setUser(user);
          attributeComment.setCommentText(problemComment);
          attributeComment.setEntryDate(new Date());
          attributeComment.setPositionStatus(PositionStatus.PROBLEM);
          dataSession.save(attributeComment);
        }
        transaction.commit();
        saveCount++;
      }
    }
    return saveCount;
  }

  public static Map<String, String> createTempValueMap(CodeInstance codeInstance)
  {
    Map<String, String> tempValueMap = new HashMap<String, String>();
    if (codeInstance.getTempValues() != null)
    {
      String tempValues = codeInstance.getTempValues().trim();
      while (tempValues != null && tempValues.length() > 0)
      {
        String nameValuePair = "";
        int i = tempValues.indexOf('|');
        if (i < 0)
        {
          nameValuePair = tempValues;
          tempValues = null;
        } else
        {
          nameValuePair = tempValues.substring(0, i);
          tempValues = tempValues.substring(i + 1).trim();
        }
        i = nameValuePair.indexOf('=');
        if (i > 0)
        {
          tempValueMap.put(nameValuePair.substring(0, i).trim(), nameValuePair.substring(i + 1).trim());
        }
      }
    }
    return tempValueMap;
  }

}
