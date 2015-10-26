package org.openimmunizationsoftware.dqa.cm.logic.thread;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.logic.CodeInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AttributeAssigned;
import org.openimmunizationsoftware.dqa.cm.model.AttributeComment;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.AttributeStatus;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;
import org.openimmunizationsoftware.dqa.cm.model.AttributeValue;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.PositionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;

import static org.openimmunizationsoftware.dqa.cm.logic.AttributeTypeLogic.*;

public class SetupThread extends LogicThread
{
 



  private static final int[] ATTRIBUTE_TYPES_COMMON = { AT_CODE_LABEL, AT_USE_VALUE, AT_CODE_STATUS, AT_HL7_CODE_TABLE, AT_INCLUSION_STATUS };

  public SetupThread(User user) {
    super(user.getUserId());
  }

  public void run()
  {
    try
    {
      Query query = dataSession.createQuery("from CodeTableInstance where release = ? order by tableLabel");
      query.setParameter(0, dataSession.get(ReleaseVersion.class, 1));
      List<CodeTableInstance> codeTableInstanceList = query.list();

      out.println("<h1>Running Setup</h2>");
      out.println("<p>Setting up common attributes for every table. </p>");
      List<AttributeType> attributeTypeList = new ArrayList<AttributeType>();
      for (int attributeTypeId : ATTRIBUTE_TYPES_COMMON)
      {
        AttributeType attributeType = (AttributeType) dataSession.get(AttributeType.class, attributeTypeId);
        if (attributeType == null)
        {
          throw new NullPointerException("Unable to find attribute type " + attributeTypeId);
        }
        attributeTypeList.add(attributeType);
      }

      out.flush();

      // postpone code table 3 to the end, it's so large
      int i = 0;
      CodeTableInstance postponeCti = null;
      for (CodeTableInstance cti : codeTableInstanceList)
      {
        if (postponeCti == null && cti.getTable().getTableId() == 3)
        {
          postponeCti = cti;
        } else
        {
          i++;
          out.println("<h2>Table " + i + " of " + codeTableInstanceList.size() + ": " + cti.getTableLabel() + "</h2>");
          settingUpAttributes(cti, attributeTypeList);
          trimValues(cti);
          transferValuesToAttributes(cti);
        }
      }
      if (postponeCti != null)
      {
        i++;
        out.println("<h2>Table " + i + " of " + codeTableInstanceList.size() + ": " + postponeCti.getTableLabel() + "</h2>");
        settingUpAttributes(postponeCti, attributeTypeList);
        trimValues(postponeCti);
        transferValuesToAttributes(postponeCti);
      }

      out.println("<h3>Setup complete</h3>");
      out.println("<p>All tables have been setup.</p>");
    } catch (Exception e)
    {
      out.println("<p>Unable to setup because of exception " + e.getMessage() + "</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    } finally
    {
      out.close();
      complete = true;
    }
  }

  public void settingUpAttributes(CodeTableInstance codeTableInstance, List<AttributeType> attributeTypeList)
  {
    out.println("Adding attributes: ");

    CodeTable codeTable = codeTableInstance.getTable();
    {
      int saveCount = 0;
      for (AttributeType attributeType : attributeTypeList)
      {
        boolean required = attributeType.getAttributeTypeId() == 1 || attributeType.getAttributeTypeId() == 2
            || attributeType.getAttributeTypeId() == 3;
        saveCount = addAttribute(codeTable, saveCount, attributeType, required);
      }
      if (codeTable.getContextTable() != null)
      {
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_CONTEXT_CODE), true);
      }
      if (codeTable.getTableId() == 43) // observation identifier
      {
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_INDICATES_TABLE), false);
      } else if (codeTable.getTableId() == 61) // potential issue
      {
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_TARGET_OBJECT), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_TARGET_FIELD), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_ISSUE_TYPE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_FIELD_VALUE), false);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_CODE_TABLE), false);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_CHANGE_PRIORITY), false);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_REPORT_DENOMINATOR), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_HL7_REFERENCE), false);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_HL7_ERROR_CODE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_ISSUE_DESCRIPTION), true);
      } else if (codeTable.getTableId() == 29) // CVX code
      {
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_USE_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_lICENSED_USE_END_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_END_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_USE_MONTH_START), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_USE_MONTH_END), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_TEST_AGE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_CONCEPT_TYPE), true);
      } else if (codeTable.getTableId() == 28) // CPT code
      {
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_USE_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_lICENSED_USE_END_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_END_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_CVX_CODE), true);
      } else if (codeTable.getTableId() == 31) // MVX
      {
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_USE_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_lICENSED_USE_END_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_END_DATE), true);
      } else if (codeTable.getTableId() == 33) // Vaccine Product
      {
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_USE_START_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_lICENSED_USE_END_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_LICENSED_VALID_END_DATE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_CVX_CODE), true);
        saveCount = addAttribute(codeTable, saveCount, (AttributeType) dataSession.get(AttributeType.class, AT_MVX_CODE), true);
      }
      
    }
    out.flush();
  }

  public void transferValuesToAttributes(CodeTableInstance codeTableInstance)
  {
    Query query;
    out.println("<p>Transferring common value attributes from columns to attribute values: </p>");
    out.println("<ul>");
    outULTagOpen = true;
    CodeTable codeTable = codeTableInstance.getTable();
    {
      query = dataSession.createQuery("from CodeInstance where tableInstance.table = ?");
      query.setParameter(0, codeTable);
      List<CodeInstance> codeInstanceList = query.list();
      query = dataSession.createQuery("from AttributeAssigned where table = ? ");
      query.setParameter(0, codeTable);
      List<AttributeAssigned> attributeAssignedlIst = query.list();
      for (AttributeAssigned attributeAssigned : attributeAssignedlIst)
      {
        AttributeType attributeType = attributeAssigned.getAttributeType();
        int saveCount = 0;
        for (CodeInstance codeInstance : codeInstanceList)
        {
          saveCount += CodeInstanceLogic.transferValuesToAttributes(attributeType, codeInstance, user, dataSession);
        }
        out.println("<li>" + attributeType.getAttributeLabel() + ": " + saveCount + " values transferred</li>");
        out.flush();
      }
    }
    out.println("</ul>");
    outULTagOpen = false;
  }

 

  public void trimValues(CodeTableInstance codeTableInstance)
  {
    List<AttributeType> attributeTypeList;
    Query query;
    out.println("<p>Triming values to remove extra spaces in all text values </p>");
    CodeTable codeTable = codeTableInstance.getTable();
    {
      query = dataSession.createQuery("from CodeInstance where tableInstance.table = ?");
      query.setParameter(0, codeTable);
      List<CodeInstance> codeInstanceList = query.list();
      int saveCount = 0;
      for (CodeInstance codeInstance : codeInstanceList)
      {
        Transaction transaction = dataSession.beginTransaction();
        CodeMaster codeMaster = codeInstance.getCode();
        codeMaster.setCodeValue(codeMaster.getCodeValue().trim());
        dataSession.update(codeMaster);
        codeInstance.setCodeLabel(codeInstance.getCodeLabel().trim());
        codeInstance.setUseValue(codeInstance.getUseValue().trim());
        codeInstance.setCodeStatus(codeInstance.getCodeStatus().trim());
        codeInstance.setHl7CodeTable(trim(codeInstance.getHl7CodeTable()));
        dataSession.update(codeInstance);
        saveCount++;
        transaction.commit();
      }
      out.println("<p>" + saveCount + " values trimmed</p>");
      out.flush();
    }
  }

  private static String trim(String s)
  {
    if (s == null)
    {
      return s;
    }
    return s.trim();
  }

  public int addAttribute(CodeTable codeTable, int saveCount, AttributeType attributeType, boolean required)
  {
    Query query;
    query = dataSession.createQuery("from AttributeAssigned where table = ? and attributeType = ? ");
    query.setParameter(0, codeTable);
    query.setParameter(1, attributeType);
    List<AttributeAssigned> attributeAssignedList = query.list();
    if (attributeAssignedList.size() == 0)
    {
      AttributeAssigned attributeAssigned = new AttributeAssigned();
      attributeAssigned.setAttributeType(attributeType);
      attributeAssigned.setTable(codeTable);
      attributeAssigned.setDisplayOrder(attributeType.getAttributeTypeId());
      attributeAssigned.setAllowMultiple(false);
      if (required)
      {
        attributeAssigned.setAttributeStatus(AttributeStatus.REQUIRED);
      } else
      {
        attributeAssigned.setAttributeStatus(AttributeStatus.OPTIONAL);
      }
      Transaction transaction = dataSession.beginTransaction();
      dataSession.save(attributeAssigned);
      transaction.commit();
      saveCount++;
      out.print((saveCount > 1 ? ", " : "") + attributeType.getAttributeLabel());
    }
    return saveCount;
  }
}
