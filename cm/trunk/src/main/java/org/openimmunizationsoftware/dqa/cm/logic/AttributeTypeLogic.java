package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;

public class AttributeTypeLogic
{
  
  public static final int AT_CODE_LABEL = 1;
  public static final int AT_USE_VALUE = 2;
  public static final int AT_CODE_STATUS = 3;
  public static final int AT_CONTEXT_CODE = 4;
  public static final int AT_INDICATES_TABLE = 5;
  public static final int AT_HL7_CODE_TABLE = 6; // HL7 Code Table
  public static final int AT_LICENSED_VALID_START_DATE = 7; // Licensed Valid
                                                             // Start Date
  public static final int AT_LICENSED_USE_START_DATE = 8; // Licensed Use Start
                                                           // Date
  public static final int AT_lICENSED_USE_END_DATE = 9; // Licensed Use End
                                                         // Date
  public static final int AT_LICENSED_VALID_END_DATE = 10; // Licensed Valid
                                                            // Date
  public static final int AT_REPORTED_VALID_START_DATE = 11; // Reported Valid
                                                              // Start Date
  public static final int AT_REPORTED_USE_START_DATE = 12; // Reported Use
                                                            // State Date
  public static final int AT_REPORTED_USE_END_DATE = 13; // Reported Use End
                                                          // Date
  public static final int AT_REPORTED_VALID_END_DATE = 14; // Reported Valid
                                                            // End Date
  public static final int AT_CVX_CODE = 15; // CVX Code
  public static final int AT_USE_MONTH_START = 16; // Use Month Start
  public static final int AT_USE_MONTH_END = 17; // Use Month End
  public static final int AT_TEST_AGE = 18; // Test Age
  public static final int AT_CONCEPT_TYPE = 19; // Concept Type
  public static final int AT_CDC_DESCRIPTION = 20; // CDC Description
  public static final int AT_VACCINE_GROUP = 21; // Vaccine Group
  public static final int AT_MVX_CODE = 22; // MVX Code
  public static final int AT_VACCINE_GROUP_STATUS = 23; // Vaccine Group Status
  public static final int AT_PROFILE_STATUS = 24; // Profile Status
  public static final int AT_POTENTIAL_ISSUE_ID = 25; // Potential Issue Id
  public static final int AT_APPLICATION_TYPE = 26; // Application Type
  public static final int AT_INCLUSION_STATUS = 27; // Inclusion Status
  public static final int AT_TARGET_OBJECT = 28; // Target Object
  public static final int AT_TARGET_FIELD = 29; // Target Field
  public static final int AT_ISSUE_TYPE = 30; // Issue Type
  public static final int AT_FIELD_VALUE = 31; // Field Value
  public static final int AT_CODE_TABLE = 32; // Code Table
  public static final int AT_CHANGE_PRIORITY = 33; // Change Priority
  public static final int AT_REPORT_DENOMINATOR = 34; // Report Denominator
  public static final int AT_HL7_REFERENCE = 35; // HL7 Reference
  public static final int AT_HL7_ERROR_CODE = 36; // HL7 Error Code
  public static final int AT_ISSUE_DESCRIPTION = 37; // Issue Description
  
  
  public static AttributeType getAttributeType(int attributeTypeId, Session dataSession)
  {
    return (AttributeType) dataSession.get(AttributeType.class, attributeTypeId);
  }
  
  public static List<AttributeType> getAttributeTypeList(Session dataSession)
  {
    Query query = dataSession.createQuery("from AttributeType order by attributeLabel");
    List<AttributeType> attributeTypeList = query.list();
    return attributeTypeList;
  }
  
  public static void saveAttributeType(AttributeType attributeType, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(attributeType);
    transaction.commit();
  }
  
  public static void updateAttributeType(AttributeType attributeType, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(attributeType);
    transaction.commit();
  }
}
