package org.openimmunizationsoftware.dqa.tr.model;

import org.openimmunizationsoftware.dqa.tr.profile.ProfileFieldType;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileManager;
import org.openimmunizationsoftware.dqa.tr.profile.Usage;

public class ProfileField
{
  private int profileFieldId = 0;
  private ProfileField parent = null;
  private int pos = 0;
  private String fieldName = "";
  private ProfileFieldType type = null;
  private String segmentName = "";
  private String description = "";
  private String codeValue = "";
  private String codeLabel = "";
  private String transformsPresent = "";
  private String transformsAbsent = "";
  private int posInSegment = 0;
  private int posInField = 0;
  private int posInSubField = 0;
  private String specialName = "";
  private String specialSection = "";
  private String dataType = "";
  private String tableName = "";
  private String dataTypeDef = "";
  private int dataTypePos = 0;
  private Usage testUsage = Usage.NOT_DEFINED;
  private String baseUsage = "";
  private String conditionalPredicate = "";
  private String length = "";
  private String value = "";
  private String comments = "";

  public ProfileField getParent()
  {
    return parent;
  }

  public void setParent(ProfileField parent)
  {
    this.parent = parent;
  }

  public String getConditionalPredicate()
  {
    return conditionalPredicate;
  }

  public void setConditionalPredicate(String conditionalPredicate)
  {
    this.conditionalPredicate = conditionalPredicate;
  }

  public String getLength()
  {
    return length;
  }

  public void setLength(String length)
  {
    this.length = length;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String getComments()
  {
    return comments;
  }

  public void setComments(String comments)
  {
    this.comments = comments;
  }

  public int getProfileFieldId()
  {
    return profileFieldId;
  }

  public void setProfileFieldId(int profileFieldId)
  {
    this.profileFieldId = profileFieldId;
  }

  public String getTestUsageString()
  {
    return testUsage == null ? "" : testUsage.toString();
  }

  public void setTestUsageString(String testUsageString)
  {
    this.testUsage = Usage.readUsage(testUsageString);
  }

  public String getProfileFieldType()
  {
    return type == null ? "" : type.toString();
  }

  public void setProfileFieldType(String profileFieldTypeString)
  {
    this.type = ProfileFieldType.readProfileFieldType(profileFieldTypeString);
  }

  public Usage getTestUsage()
  {
    return testUsage;
  }

  public int getPos()
  {
    return pos;
  }

  public void setPos(int pos)
  {
    this.pos = pos;
  }

  public void setTestUsage(Usage testUsage)
  {
    this.testUsage = testUsage;
  }

  public String getBaseUsage()
  {
    return baseUsage;
  }

  public void setBaseUsage(String baseUsage)
  {
    this.baseUsage = baseUsage;
  }

  public String getDataTypeDef()
  {
    return dataTypeDef;
  }

  public void setDataTypeDef(String dataTypeDef)
  {
    this.dataTypeDef = dataTypeDef;
  }

  public int getDataTypePos()
  {
    return dataTypePos;
  }

  public void setDataTypePos(Integer dataTypePos)
  {
    if (dataTypePos != null)
    {
      this.dataTypePos = dataTypePos;
    }
  }

  public boolean isDataTypeDataType()
  {
    return type == ProfileFieldType.DATA_TYPE || type == ProfileFieldType.DATA_TYPE_FIELD;
  }

  public String getSpecialSection()
  {
    return specialSection;
  }

  public void setSpecialSection(String specialSection)
  {
    this.specialSection = specialSection;
  }

  public int getPosInSegment()
  {
    return posInSegment;
  }

  public void setPosInSegment(Integer posInSegment)
  {
    if (posInSegment != null)
    {
      this.posInSegment = posInSegment;
    }
  }

  public int getPosInField()
  {
    return posInField;
  }

  public void setPosInField(Integer posInField)
  {
    if (posInField != null)
    {
      this.posInField = posInField;
    }
  }

  public int getPosInSubField()
  {
    return posInSubField;
  }

  public void setPosInSubField(Integer posInSubField)
  {
    if (posInSubField != null)
    {
      this.posInSubField = posInSubField;
    }
  }

  public String getSpecialName()
  {
    return specialName;
  }

  public void setSpecialName(String specialName)
  {
    this.specialName = specialName;
  }

  public String getDataType()
  {
    return dataType;
  }

  public void setDataType(String dataType)
  {
    this.dataType = dataType;
  }

  public String getTableName()
  {
    return tableName;
  }

  public void setTableName(String tableName)
  {
    this.tableName = tableName;
  }

  public String getTransformsPresent()
  {
    return transformsPresent;
  }

  public boolean isTransformPresentFullTestCase()
  {
    return transformsPresent != null && transformsPresent.toUpperCase().startsWith(ProfileManager.USE_FULL_TEST_CASE.toUpperCase());
  }

  public boolean isTransformPresentDefined()
  {
    return transformsPresent != null && transformsPresent.length() > 0;
  }

  public boolean isTransformAbsentFullTestCase()
  {
    return transformsAbsent != null && transformsAbsent.toUpperCase().startsWith(ProfileManager.USE_FULL_TEST_CASE.toUpperCase());
  }

  public boolean isTransformAbsentDefined()
  {
    return transformsAbsent != null && transformsAbsent.length() > 0;
  }

  public void setTransformsPresent(String transformsPresent)
  {
    this.transformsPresent = transformsPresent;
  }

  public String getTransformsAbsent()
  {
    return transformsAbsent;
  }

  public void setTransformsAbsent(String transformsAbsent)
  {
    this.transformsAbsent = transformsAbsent;
  }

  public String getCodeValue()
  {
    return codeValue;
  }

  public void setCodeValue(String codeValue)
  {
    this.codeValue = codeValue;
  }

  public String getCodeLabel()
  {
    return codeLabel;
  }

  public void setCodeLabel(String codeLabel)
  {
    this.codeLabel = codeLabel;
  }

  public void setFieldName(String fieldName)
  {
    this.fieldName = fieldName;
  }

  public void setType(ProfileFieldType type)
  {
    this.type = type;
  }

  public void setSegmentName(String segmentName)
  {
    this.segmentName = segmentName;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getFieldName()
  {
    return fieldName;
  }

  public ProfileFieldType getType()
  {
    return type;
  }

  public String getSegmentName()
  {
    return segmentName;
  }

  public String getDescription()
  {
    return description;
  }

}
