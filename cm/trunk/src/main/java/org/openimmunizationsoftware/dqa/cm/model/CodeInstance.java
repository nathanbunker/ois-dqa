package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class CodeInstance implements Serializable
{

  public static final String CODE_STATUS_VALID = "V";
  public static final String CODE_STATUS_INVALID = "I";
  public static final String CODE_STATUS_IGNORED = "G";
  public static final String CODE_STATUS_DEPRECATED = "D";

  private int codeInstanceId = 0;
  private CodeMaster code = null;
  private CodeMaster context = null;
  private CodeTable indicatesTable = null;
  private String codeLabel = "";
  private String useValue = "";
  private String codeStatus = "";
  private String hl7CodeTable = "";
  private CodeTableInstance tableInstance = null;
  private InclusionStatus inclusionStatus = null;
  private int issueCount = 0;
  private String tempValues = "";
  transient List<AttributeValue> attributeValueList = null;
  transient Map<AttributeType, List<AttributeInstance>> attributeTypeToValueMap = null;

  public String getTempValues()
  {
    return tempValues;
  }

  public void setTempValues(String tempValues)
  {
    this.tempValues = tempValues;
  }

  public String getHl7CodeTable()
  {
    return hl7CodeTable;
  }

  public void setHl7CodeTable(String hl7CodeTable)
  {
    this.hl7CodeTable = hl7CodeTable;
  }

  public int getIssueCount()
  {
    return issueCount;
  }

  public void setIssueCount(int issueCount)
  {
    this.issueCount = issueCount;
  }

  public Map<AttributeType, List<AttributeInstance>> getAttributeTypeToValueMap()
  {
    return attributeTypeToValueMap;
  }

  public void setAttributeTypeToValueMap(Map<AttributeType, List<AttributeInstance>> attributeTypeToValueMap)
  {
    this.attributeTypeToValueMap = attributeTypeToValueMap;
  }

  public String getAttributeValue(AttributeType attributeType)
  {
    String value = null;
    if (attributeTypeToValueMap != null)
    {
      List<AttributeInstance> attributeInstanceList = attributeTypeToValueMap.get(attributeType);
      if (attributeInstanceList != null && attributeInstanceList.size() > 0)
      {
        return attributeInstanceList.get(0).getValue().getAttributeValue();
      }
    }
    return value;
  }

  public List<AttributeValue> getAttributeValueList()
  {
    return attributeValueList;
  }

  public void setAttributeValueList(List<AttributeValue> attributeValueList)
  {
    this.attributeValueList = attributeValueList;
  }

  public int getCodeInstanceId()
  {
    return codeInstanceId;
  }

  public void setCodeInstanceId(int codeInstanceId)
  {
    this.codeInstanceId = codeInstanceId;
  }

  public CodeMaster getCode()
  {
    return code;
  }

  public void setCode(CodeMaster code)
  {
    this.code = code;
  }

  public CodeMaster getContext()
  {
    return context;
  }

  public void setContext(CodeMaster context)
  {
    this.context = context;
  }

  public CodeTable getIndicatesTable()
  {
    return indicatesTable;
  }

  public void setIndicatesTable(CodeTable indicatesTable)
  {
    this.indicatesTable = indicatesTable;
  }

  public String getCodeLabel()
  {
    return codeLabel;
  }

  public void setCodeLabel(String codeLabel)
  {
    this.codeLabel = codeLabel;
  }

  public String getUseValue()
  {
    return useValue;
  }

  public void setUseValue(String useValue)
  {
    this.useValue = useValue;
  }

  public String getCodeStatus()
  {
    return codeStatus;
  }

  public String getCodeStatusLabel()
  {
    if (codeStatus != null)
    {
      if (codeStatus.equals(CODE_STATUS_DEPRECATED))
      {
        return "Deprecated";
      }
      if (codeStatus.equals(CODE_STATUS_IGNORED))
      {
        return "Ignored";
      }
      if (codeStatus.equals(CODE_STATUS_INVALID))
      {
        return "Invalid";
      }
      if (codeStatus.equals(CODE_STATUS_VALID))
      {
        return "Valid";
      }
    }
    return codeStatus;
  }

  public void setCodeStatus(String codeStatus)
  {
    this.codeStatus = codeStatus;
  }

  public CodeTableInstance getTableInstance()
  {
    return tableInstance;
  }

  public void setTableInstance(CodeTableInstance tableInstance)
  {
    this.tableInstance = tableInstance;
  }

  public InclusionStatus getInclusionStatus()
  {
    return inclusionStatus;
  }

  public boolean isInclusionStatusRemove()
  {
    return getInclusionStatus() == InclusionStatus.REMOVE || getInclusionStatus() == InclusionStatus.PROPOSED_REMOVE;
  }

  public void setInclusionStatus(InclusionStatus inclusionStatus)
  {
    this.inclusionStatus = inclusionStatus;
  }

  public String getInclusionStatusString()
  {
    if (inclusionStatus == null)
    {
      return null;
    }
    return inclusionStatus.getId();
  }

  public void setInclusionStatusString(String inclusionStatusString)
  {
    this.inclusionStatus = InclusionStatus.get(inclusionStatusString);
  }
}
