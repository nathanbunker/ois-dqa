package org.openimmunizationsoftware.dqa.tr.profile;

public enum ProfileFieldType {
  SEGMENT, SEGMENT_GROUP, FIELD, FIELD_PART, FIELD_SUB_PART, FIELD_VALUE, FIELD_PART_VALUE, FIELD_SUB_PART_VALUE, DATA_TYPE, DATA_TYPE_FIELD;
  public String toString()
  {
    if (this == SEGMENT)
    {
      return "Segment";
    }
    if (this == SEGMENT_GROUP)
    {
      return "Segment Group";
    }
    if (this == FIELD)
    {
      return "Field";
    }
    if (this == FIELD_PART)
    {
      return "Field Part";
    }
    if (this == FIELD_SUB_PART)
    {
      return "Field Sub Part";
    }
    if (this == FIELD_VALUE)
    {
      return "Field Value";
    }
    if (this == FIELD_PART_VALUE)
    {
      return "Field Part Value";
    }
    if (this == FIELD_SUB_PART_VALUE)
    {
      return "Field Sub Part Value";
    }
    if (this == DATA_TYPE)
    {
      return "Data Type";
    }
    if (this == DATA_TYPE_FIELD)
    {
      return "Data Type Field";
    }
    return super.toString();
  };

  public static ProfileFieldType readProfileFieldType(String profileFieldTypeString)
  {
    if (profileFieldTypeString == null || profileFieldTypeString.equals(""))
    {
      return null;
    } else if (profileFieldTypeString.equals("Segment"))
    {
      return SEGMENT;
    } else if (profileFieldTypeString.equals("Segment Group"))
    {
      return SEGMENT_GROUP;
    } else if (profileFieldTypeString.equals("Field"))
    {
      return FIELD;
    } else if (profileFieldTypeString.equals("Field Part"))
    {
      return FIELD_PART;
    } else if (profileFieldTypeString.equals("Field Sub Part"))
    {
      return FIELD_SUB_PART;
    } else if (profileFieldTypeString.equals("Field Value"))
    {
      return FIELD_VALUE;
    } else if (profileFieldTypeString.equals("Field Part Value"))
    {
      return FIELD_PART_VALUE;
    } else if (profileFieldTypeString.equals("Field Sub Part Value"))
    {
      return FIELD_SUB_PART_VALUE;
    } else if (profileFieldTypeString.equals("Data Type"))
    {
      return DATA_TYPE;
    } else if (profileFieldTypeString.equals("Data Type Field"))
    {
      return DATA_TYPE_FIELD;
    } else
    {
      return null;
    }
  }

}
