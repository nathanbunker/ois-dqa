package org.openimmunizationsoftware.dqa.tr.profile;

import static org.immunizationsoftware.dqa.transform.ScenarioManager.SCENARIO_FULL_RECORD_FOR_PROFILING;
import static org.immunizationsoftware.dqa.transform.ScenarioManager.createTestCaseMessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.immunizationsoftware.dqa.transform.TestCaseMessage;
import org.openimmunizationsoftware.dqa.tr.model.ProfileField;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsageValue;

public class ProfileManager
{

  public static final String USE_FULL_TEST_CASE = "Use Full Test Case";

  public static TestCaseMessage getPresentTestCase(ProfileField field, TestCaseMessage defaultTestCaseMessage)
  {
    if (field.getTransformsPresent() != null && field.getTransformsPresent().toUpperCase().startsWith(USE_FULL_TEST_CASE.toUpperCase()))
    {
      return defaultTestCaseMessage;
    }
    TestCaseMessage testCaseMessage = createTestCaseMessage(SCENARIO_FULL_RECORD_FOR_PROFILING);
    if (field.getTransformsPresent() == null || field.getTransformsPresent().equals(""))
    {
      testCaseMessage.setHasIssue(false);
      return testCaseMessage;
    } else
    {
      testCaseMessage.setAdditionalTransformations(field.getTransformsPresent());
      testCaseMessage.setHasIssue(true);
      return testCaseMessage;
    }
  }

  public static TestCaseMessage getAbsentTestCase(ProfileField field, TestCaseMessage defaultTestCaseMessage)
  {
    if (field.getTransformsAbsent() != null && field.getTransformsAbsent().toUpperCase().startsWith(USE_FULL_TEST_CASE.toUpperCase()))
    {
      return defaultTestCaseMessage;
    }
    TestCaseMessage testCaseMessage = createTestCaseMessage(SCENARIO_FULL_RECORD_FOR_PROFILING);
    if (field.getTransformsAbsent() == null || field.getTransformsAbsent().equals(""))
    {
      testCaseMessage.setHasIssue(false);
      return testCaseMessage;
    } else
    {
      testCaseMessage.setAdditionalTransformations(field.getTransformsAbsent());
      testCaseMessage.setHasIssue(true);
      return testCaseMessage;
    }
  }

  private static void updateFieldName(ProfileField profileField)
  {
    String fieldName = "";
    switch (profileField.getType()) {
    case FIELD:
      fieldName = profileField.getSegmentName() + "-" + profileField.getPosInSegment();
      break;
    case FIELD_PART:
      fieldName = profileField.getSegmentName() + "-" + profileField.getPosInSegment() + "." + profileField.getPosInField();
      break;
    case FIELD_PART_VALUE:
      fieldName = (profileField.getSegmentName() + "-" + profileField.getPosInSegment() + "." + profileField.getPosInField() + " "
          + profileField.getCodeValue());
      break;
    case FIELD_SUB_PART:
      fieldName = (profileField.getSegmentName() + "-" + profileField.getPosInSegment() + "." + profileField.getPosInField() + "."
          + profileField.getPosInSubField());
      break;
    case FIELD_SUB_PART_VALUE:
      fieldName = (profileField.getSegmentName() + "-" + profileField.getPosInSegment() + "." + profileField.getPosInField() + "."
          + profileField.getPosInSubField() + " " + profileField.getCodeValue());
      break;
    case FIELD_VALUE:
      fieldName = (profileField.getSegmentName() + "-" + profileField.getPosInSegment() + " " + profileField.getCodeValue());
      break;
    case SEGMENT:
      fieldName = (profileField.getSegmentName());
      break;
    case SEGMENT_GROUP:
      fieldName = (profileField.getSegmentName() + " Group");
      break;
    case DATA_TYPE:
      fieldName = (profileField.getDataTypeDef());
      break;
    case DATA_TYPE_FIELD:
      fieldName = (profileField.getDataTypeDef() + "-" + profileField.getDataTypePos());
      break;
    }
    if (profileField.getSpecialName().length() > 0)
    {
      fieldName += " " + profileField.getSpecialName();
    }
    if (profileField.getSpecialSection().length() > 0)
    {
      fieldName = profileField.getSpecialSection() + " " + fieldName;
    }
    profileField.setFieldName(fieldName);
  }

  public static void rectifyProfileFields(Session dataSession) throws FileNotFoundException, IOException
  {
    {
      Query query = dataSession.createQuery("from ProfileField where pos > 0 order by pos");
      List<ProfileField> profileFieldList = query.list();
      Map<String, List<ProfileField>> dataTypeMapList = new HashMap<String, List<ProfileField>>();
      List<ProfileField> dataTypeList = new ArrayList<ProfileField>();
      int pos = profileFieldList.size();
      ProfileField currentDataType = null;
      ProfileField currentSegment = null;
      ProfileField currentField = null;
      ProfileField currentFieldPart = null;
      for (ProfileField profileField : profileFieldList)
      {
        updateFieldName(profileField);
        switch (profileField.getType()) {
        case DATA_TYPE:
          currentDataType = profileField;
          currentSegment = null;
          currentField = null;
          currentFieldPart = null;
          break;
        case DATA_TYPE_FIELD:
          profileField.setParent(currentDataType);
          currentSegment = null;
          currentField = null;
          currentFieldPart = null;
          break;
        case SEGMENT_GROUP:
          currentDataType = null;
          currentSegment = null;
          currentField = null;
          currentFieldPart = null;
          break;
        case SEGMENT:
          currentDataType = null;
          currentSegment = profileField;
          currentField = null;
          currentFieldPart = null;
          break;
        case FIELD_VALUE:
          profileField.setParent(currentSegment);
          break;
        case FIELD:
          profileField.setParent(currentSegment);
          currentField = profileField;
          currentFieldPart = null;
          break;
        case FIELD_PART_VALUE:
          profileField.setParent(currentField);
          break;
        case FIELD_PART:
          profileField.setParent(currentField);
          currentFieldPart = profileField;
          break;
        case FIELD_SUB_PART_VALUE:
          profileField.setParent(currentFieldPart);
          break;
        case FIELD_SUB_PART:
          profileField.setParent(currentFieldPart);
          break;
        }
        {
          Transaction transaction = dataSession.beginTransaction();
          dataSession.update(profileField);
          transaction.commit();
        }
        if (profileField.isDataTypeDataType())
        {
          if (profileField.getType() == ProfileFieldType.DATA_TYPE)
          {
            dataTypeList = new ArrayList<ProfileField>();
            dataTypeMapList.put(profileField.getDataTypeDef(), dataTypeList);
          } else if (profileField.getType() == ProfileFieldType.DATA_TYPE_FIELD)
          {
            dataTypeList.add(profileField);
          }
        } else
        {
          pos = addDataTypeProfileFields(pos, dataTypeMapList, profileField, dataSession);
        }
      }
    }

    ProfileField profileFieldNull = (ProfileField) dataSession.get(ProfileField.class, 1);
    Query query = dataSession.createQuery("from ProfileUsageValue where profileField = ?");
    query.setParameter(0, profileFieldNull);
    List<ProfileUsageValue> profileUsageValueList = query.list();
    List<ProfileField> profileFieldList = query.list();
    for (ProfileUsageValue profileUsageValue : profileUsageValueList)
    {
      query = dataSession.createQuery("from ProfileField where fieldName = ?");
      query.setParameter(0, profileUsageValue.getFieldName());
      profileFieldList = query.list();
      if (profileFieldList.size() > 1)
      {
        System.out.println("Found problem: This field name is used more than once: " + profileUsageValue.getFieldName());
      } else if (profileFieldList.size() == 0)
      {
        System.out.println("Found problem: This field name not found: " + profileUsageValue.getFieldName());
      } else
      {
        Transaction transaction = dataSession.beginTransaction();
        profileUsageValue.setProfileField(profileFieldList.get(0));
        dataSession.update(profileUsageValue);
        transaction.commit();
      }
    }

  }

  private static int addDataTypeProfileFields(int pos, Map<String, List<ProfileField>> dataTypeMapList, ProfileField profileField,
      Session dataSession)
  {
    if (!profileField.getDataType().equals(""))
    {
      List<ProfileField> dataTypeProfileFieldList = dataTypeMapList.get(profileField.getDataType());
      if (dataTypeProfileFieldList != null)
      {
        ProfileFieldType typeNew = null;
        if (profileField.getType() == ProfileFieldType.FIELD)
        {
          typeNew = ProfileFieldType.FIELD_PART;
        } else if (profileField.getType() == ProfileFieldType.FIELD_PART)
        {
          typeNew = ProfileFieldType.FIELD_SUB_PART;
        }
        if (typeNew != null)
        {
          for (ProfileField pfDataType : dataTypeProfileFieldList)
          {
            ProfileField pfNew = new ProfileField();
            pfNew.setParent(profileField);
            pfNew.setPosInSegment(profileField.getPosInSegment());
            if (typeNew == ProfileFieldType.FIELD_PART)
            {
              pfNew.setPosInField(pfDataType.getDataTypePos());
            } else if (typeNew == ProfileFieldType.FIELD_SUB_PART)
            {
              pfNew.setPosInField(profileField.getPosInField());
              pfNew.setPosInSubField(pfDataType.getDataTypePos());
            }
            pfNew.setDataTypePos(pfDataType.getDataTypePos());
            pfNew.setDataTypeDef(pfDataType.getDataTypeDef());
            pfNew.setSpecialSection(profileField.getSpecialSection());
            if (pfDataType.getSpecialName().equals(""))
            {
              pfNew.setSpecialName(profileField.getSpecialName());
            } else if (profileField.getSpecialName().equals(""))
            {
              pfNew.setSpecialName(pfDataType.getSpecialName());
            } else
            {
              pfNew.setSpecialName(profileField.getSpecialName() + "-" + pfDataType.getSpecialName());
            }
            pfNew.setDataType(pfDataType.getDataType());
            pfNew.setTableName(pfDataType.getTableName());
            pfNew.setTestUsage(pfDataType.getTestUsage());
            pfNew.setBaseUsage(pfDataType.getBaseUsage());
            pfNew.setType(typeNew);
            pfNew.setSegmentName(profileField.getSegmentName());
            pfNew.setDescription(pfDataType.getDescription());
            pfNew.setCodeValue(pfDataType.getCodeValue());
            pfNew.setCodeLabel(pfDataType.getCodeLabel());
            updateFieldName(pfNew);
            pos++;
            pfNew.setPos(pos);
            {
              Transaction transaction = dataSession.beginTransaction();
              dataSession.save(pfNew);
              transaction.commit();
            }
            if (typeNew == ProfileFieldType.FIELD_PART)
            {
              pos = addDataTypeProfileFields(pos, dataTypeMapList, pfNew, dataSession);
            }
          }
        }
      }
    }
    return pos;
  }

  public static void updateMessageAcceptStatus(List<ProfileUsageValue> profileUsageValueList)
  {
    MessageAcceptStatus masSegment = MessageAcceptStatus.ONLY_IF_PRESENT;
    MessageAcceptStatus masField = null;
    MessageAcceptStatus masFieldPart = null;
    MessageAcceptStatus masFieldSubPart = null;
    String masSegmentDebug = "";
    String masFieldDebug = "";
    String masFieldPartDebug = "";
    String masFieldSubPartDebug = "";
    for (ProfileUsageValue profileUsageValue : profileUsageValueList)
    {
      StringBuilder debug = new StringBuilder();
      ProfileField field = profileUsageValue.getProfileField();
      if (field.isDataTypeDataType())
      {
        continue;
      } else if (field.getType() == ProfileFieldType.SEGMENT)
      {
        masSegment = determineMessageAcceptStatus(profileUsageValue, MessageAcceptStatus.ONLY_IF_PRESENT, debug);
        profileUsageValue.setMessageAcceptStatus(masSegment);
        masSegmentDebug = debug.toString();
      } else if (field.getType() == ProfileFieldType.FIELD)
      {
        debug.append(masSegmentDebug);
        masField = determineMessageAcceptStatus(profileUsageValue, masSegment, debug);
        profileUsageValue.setMessageAcceptStatus(masField);
        masFieldDebug = debug.toString();
      } else if (field.getType() == ProfileFieldType.FIELD_PART)
      {
        debug.append(masFieldDebug);
        masFieldPart = determineMessageAcceptStatus(profileUsageValue, masField, debug);
        profileUsageValue.setMessageAcceptStatus(masFieldPart);
        masFieldPartDebug = debug.toString();
      } else if (field.getType() == ProfileFieldType.FIELD_SUB_PART)
      {
        debug.append(masFieldPartDebug);
        masFieldSubPart = determineMessageAcceptStatus(profileUsageValue, masFieldPart, debug);
        profileUsageValue.setMessageAcceptStatus(masFieldSubPart);
        masFieldSubPartDebug = debug.toString();
      } else if (field.getType() == ProfileFieldType.FIELD_VALUE)
      {
        debug.append(masFieldSubPartDebug);
        profileUsageValue.setMessageAcceptStatus(determineMessageAcceptStatus(profileUsageValue, masField, debug));
      } else if (field.getType() == ProfileFieldType.FIELD_PART_VALUE)
      {
        debug.append(masFieldDebug);
        profileUsageValue.setMessageAcceptStatus(determineMessageAcceptStatus(profileUsageValue, masFieldPart, debug));
      } else if (field.getType() == ProfileFieldType.FIELD_SUB_PART_VALUE)
      {
        debug.append(masFieldPartDebug);
        profileUsageValue.setMessageAcceptStatus(determineMessageAcceptStatus(profileUsageValue, masFieldSubPart, debug));
      }
      if (profileUsageValue.getMessageAcceptStatus() == MessageAcceptStatus.ONLY_IF_PRESENT)
      {
        debug.append("Accept only if present: Message is only accepted when this concept is valued. ");
      } else if (profileUsageValue.getMessageAcceptStatus() == MessageAcceptStatus.IF_PRESENT_OR_ABSENT)
      {
        debug.append("Accept if present or absent: Message is accepted whether or not this concept is valued. ");
      } else if (profileUsageValue.getMessageAcceptStatus() == MessageAcceptStatus.ONLY_IF_ABSENT)
      {
        debug.append("Accept only if absent: Message is accepted only if this concept is left unvalued. ");
      }
      debug.append("");
      profileUsageValue.setMessageAcceptStatusDebug(debug.toString());
    }
  }

  public static String determineMessageAcceptStatus(ProfileUsageValue profileUsageValue, Session dataSession)
  {
    StringBuilder debug = new StringBuilder();
    MessageAcceptStatus mas = determineMessageAcceptStatus(profileUsageValue.getProfileField(), profileUsageValue.getProfileUsage(),
        profileUsageValue, debug, dataSession);
    profileUsageValue.setMessageAcceptStatus(mas);
    profileUsageValue.setMessageAcceptStatusDebug(debug.toString());
    return debug.toString();
  }

  public static MessageAcceptStatus determineMessageAcceptStatus(ProfileField profileField, ProfileUsage profileUsage,
      ProfileUsageValue profileUsageValue, StringBuilder debug, Session dataSession)
  {
    MessageAcceptStatus masHigher;
    if (profileField.getParent() == null)
    {
      masHigher = MessageAcceptStatus.ONLY_IF_PRESENT;
    } else
    {
      Query query = dataSession.createQuery("from ProfileUsageValue where profileField = ? and profileUsage = ?");
      query.setParameter(0, profileField.getParent());
      query.setParameter(1, profileUsage);
      List<ProfileUsageValue> profileUsageValueList = query.list();
      if (profileUsageValueList.size() == 0)
      {
        masHigher = determineMessageAcceptStatus(profileField.getParent(), profileUsage, null, debug, dataSession);
      } else
      {
        masHigher = determineMessageAcceptStatus(profileField.getParent(), profileUsage, profileUsageValueList.get(0), debug, dataSession);
      }
    }

    MessageAcceptStatus mas = null;
    Usage usage = Usage.NOT_DEFINED;
    if (profileUsageValue != null)
    {
      usage = profileUsageValue.getUsage();
    }
    mas = determineMas(debug, masHigher, profileField, usage);
    return mas;
  }

  public static MessageAcceptStatus determineMas(StringBuilder debug, MessageAcceptStatus masHigher, ProfileField profileField, Usage usage)
  {
    MessageAcceptStatus mas;
    if (debug != null)
    {
      debug.append("Determining accept status of concept " + profileField.getFieldName() + "\n");
    }
    debug.append(" + Usage = " + usage + " \n");
    if (usage == Usage.NOT_DEFINED)
    {
      usage = profileField.getTestUsage();
      if (debug != null)
      {
        debug.append(" + Usage is not defined, taking usage from base standard. Usage = " + usage + " \n");
      }
    }
    if (masHigher == MessageAcceptStatus.ONLY_IF_PRESENT)
    {
      debug.append(" + Containing concept: Required or message will not be accepted. \n");
    } else if (masHigher == MessageAcceptStatus.IF_PRESENT_OR_ABSENT)
    {
      debug.append(" + Containing concept: Optional, may or may not be sent and message will still be accepted. \n");
    } else if (masHigher == MessageAcceptStatus.ONLY_IF_ABSENT)
    {
      debug.append(" + Containing concept: Must never be sent or message will not be accepted. \n");
    }
    if (usage == Usage.R_SPECIAL)
    {
      debug.append(
          " + Message will only be accepted if this concept is present when any part of the containing concept is messaged even though this containing concept is optional. \n");
      mas = MessageAcceptStatus.ONLY_IF_PRESENT;
    } else if (masHigher == MessageAcceptStatus.ONLY_IF_ABSENT || usage == Usage.X)
    {
      debug.append(" + Message will only be accepted if this concept is not messaged. \n");
      mas = MessageAcceptStatus.ONLY_IF_ABSENT;
    } else if (masHigher == MessageAcceptStatus.ONLY_IF_PRESENT && usage == Usage.R)
    {
      debug.append(" + Message will only be accepted if this concept is messaged because this concept and its containing concept is required. \n");
      mas = MessageAcceptStatus.ONLY_IF_PRESENT;
    } else
    {
      debug.append(" + Message should be accepted with or without this concept being messaged. \n");
      mas = MessageAcceptStatus.IF_PRESENT_OR_ABSENT;
    }
    return mas;
  }

  public static MessageAcceptStatus determineMessageAcceptStatus(ProfileUsageValue profileUsageValue, MessageAcceptStatus masHigher,
      StringBuilder debug)
  {
    if (debug != null)
    {
      debug.append("Determining accept status of concept " + profileUsageValue.getProfileField().getFieldName() + "\n");
    }
    MessageAcceptStatus mas = null;
    Usage usage = profileUsageValue.getUsage();
    debug.append(" + Usage = " + usage + " \n");
    if (usage == Usage.NOT_DEFINED)
    {
      usage = profileUsageValue.getProfileField().getTestUsage();
      if (debug != null)
      {
        debug.append(" + Usage is not defined, taking usage from base standard. Usage = " + usage + " \n");
      }
    }
    if (masHigher == MessageAcceptStatus.ONLY_IF_PRESENT)
    {
      debug.append(" + Containing concept: Required or message will not be accepted. \n");
    } else if (masHigher == MessageAcceptStatus.IF_PRESENT_OR_ABSENT)
    {
      debug.append(" + Containing concept: Optional, may or may not be sent and message will still be accepted. \n");
    } else if (masHigher == MessageAcceptStatus.ONLY_IF_ABSENT)
    {
      debug.append(" + Containing concept: Must never be sent or message will not be accepted. \n");
    }
    if (usage == Usage.R_SPECIAL)
    {
      debug.append(
          " + Message will only be accepted if this concept is present when any part of the containing concept is messaged even though this containing concept is optional. \n");
      mas = MessageAcceptStatus.ONLY_IF_PRESENT;
    } else if (masHigher == MessageAcceptStatus.ONLY_IF_ABSENT || usage == Usage.X)
    {
      debug.append(" + Message will only be accepted if this concept is not messaged. \n");
      mas = MessageAcceptStatus.ONLY_IF_ABSENT;
    } else if (masHigher == MessageAcceptStatus.ONLY_IF_PRESENT && usage == Usage.R)
    {
      debug.append(" + Message will only be accepted if this concept is messaged because this concept and its containing concept is required. \n");
      mas = MessageAcceptStatus.ONLY_IF_PRESENT;
    } else
    {
      debug.append(" + Message should be accepted with or without this concept being messaged. \n");
      mas = MessageAcceptStatus.IF_PRESENT_OR_ABSENT;
    }
    return mas;
  }

  public static CompatibilityConformance getCompatibilityConformance(Usage profileUsage, Usage profileUsageConformance)
  {

    switch (profileUsageConformance) {
    case R:
      switch (profileUsage) {
      case R:
        return CompatibilityConformance.COMPATIBLE;
      case R_SPECIAL:
        return CompatibilityConformance.CONFLICT;
      case RE:
        return CompatibilityConformance.ALLOWANCE;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.NOT_DEFINED;
      case X:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.ALLOWANCE;
      case RE_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case O_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.CONFLICT;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case RE:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.CONSTRAINT;
      case RE:
        return CompatibilityConformance.COMPATIBLE;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.NOT_DEFINED;
      case X:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONSTRAINT;
      case RE_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case O_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.CONFLICT;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case NOT_DEFINED:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case RE:
        return CompatibilityConformance.CONSTRAINT;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.COMPATIBLE;
      case X:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONSTRAINT;
      case RE_NOT_USED:
        return CompatibilityConformance.CONSTRAINT;
      case O_NOT_USED:
        return CompatibilityConformance.COMPATIBLE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.COMPATIBLE;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case O:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case RE:
        return CompatibilityConformance.CONSTRAINT;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.COMPATIBLE;
      case X:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONSTRAINT;
      case RE_NOT_USED:
        return CompatibilityConformance.CONSTRAINT;
      case O_NOT_USED:
        return CompatibilityConformance.COMPATIBLE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.COMPATIBLE;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case X:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case RE:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case O:
        return CompatibilityConformance.CONFLICT;
      case NOT_DEFINED:
        return CompatibilityConformance.COMPATIBLE;
      case X:
        return CompatibilityConformance.COMPATIBLE;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONFLICT;
      case RE_NOT_USED:
        return CompatibilityConformance.CONFLICT;
      case O_NOT_USED:
        return CompatibilityConformance.COMPATIBLE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.COMPATIBLE;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    default:
      return CompatibilityConformance.UNABLE_TO_DETERMINE;
    }
  }

  public static CompatibilityInteroperability getCompatibilityInteroperability(ProfileUsageValue profileUsageValue,
      ProfileUsageValue profileUsageValueInteroperability)
  {

    switch (profileUsageValueInteroperability.getUsage()) {
    case R:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.COMPATIBLE;
      case RE:
        return CompatibilityInteroperability.NO_PROBLEM;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X:
        return CompatibilityInteroperability.MAJOR_PROBLEM;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.DATA_LOSS;
      case RE_NOT_USED:
        return CompatibilityInteroperability.DATA_LOSS;
      case O_NOT_USED:
        return CompatibilityInteroperability.DATA_LOSS;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.DATA_LOSS;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    case RE:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.IF_POPULATED;
      case RE:
        return CompatibilityInteroperability.COMPATIBLE;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X:
        return CompatibilityInteroperability.PROBLEM;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.DATA_LOSS;
      case RE_NOT_USED:
        return CompatibilityInteroperability.DATA_LOSS;
      case O_NOT_USED:
        return CompatibilityInteroperability.DATA_LOSS;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.DATA_LOSS;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    case O:
    case NOT_DEFINED:
    case R_NOT_ENFORCED:
    case RE_NOT_USED:
    case O_NOT_USED:
    case X_NOT_ENFORCED:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.PROBLEM;
      case RE:
        return CompatibilityInteroperability.PROBLEM;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.COMPATIBLE;
      case X:
        return CompatibilityInteroperability.PROBLEM;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case RE_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case O_NOT_USED:
        return CompatibilityInteroperability.COMPATIBLE;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    case X:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.MAJOR_PROBLEM;
      case RE:
        return CompatibilityInteroperability.PROBLEM;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X:
        return CompatibilityInteroperability.COMPATIBLE;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case RE_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case O_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.COMPATIBLE;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    case R_OR_RE:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case RE:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X:
        return CompatibilityInteroperability.MAJOR_PROBLEM;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.DATA_LOSS;
      case RE_NOT_USED:
        return CompatibilityInteroperability.DATA_LOSS;
      case O_NOT_USED:
        return CompatibilityInteroperability.DATA_LOSS;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.DATA_LOSS;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    case R_OR_X:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case RE:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case RE_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case O_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    case RE_OR_O:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.IF_POPULATED;
      case RE:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X:
        return CompatibilityInteroperability.PROBLEM;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case RE_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case O_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    case RE_OR_X:
      switch (profileUsageValue.getUsage()) {
      case R:
      case R_SPECIAL:
        return CompatibilityInteroperability.IF_POPULATED;
      case RE:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case O:
      case NOT_DEFINED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X:
        return CompatibilityInteroperability.IF_CONFIGURED;
      case R_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case RE_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case O_NOT_USED:
        return CompatibilityInteroperability.NO_PROBLEM;
      case X_NOT_ENFORCED:
        return CompatibilityInteroperability.NO_PROBLEM;
      default:
        return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
      }
    default:
      return CompatibilityInteroperability.UNABLE_TO_DETERMINE;
    }
  }

  public static ProfileUsageValue getProfileUsageValue(Session dataSession, ProfileUsage profileUsageSelected, ProfileField profileField)
  {
    ProfileUsageValue profileUsageValue = null;
    Query query = dataSession.createQuery("from ProfileUsageValue where profileField = ? and profileUsage = ?");
    query.setParameter(0, profileField);
    query.setParameter(1, profileUsageSelected);
    List<ProfileUsageValue> profileUsageValueList = query.list();
    if (profileUsageValueList.size() > 0)
    {
      profileUsageValue = profileUsageValueList.get(0);
    }
    return profileUsageValue;
  }

}
