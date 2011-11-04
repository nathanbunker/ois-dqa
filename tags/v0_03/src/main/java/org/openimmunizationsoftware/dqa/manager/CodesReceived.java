package org.openimmunizationsoftware.dqa.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.db.model.CodeMaster;
import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.CodeStatus;
import org.openimmunizationsoftware.dqa.db.model.CodeTable;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.VaccineCpt;
import org.openimmunizationsoftware.dqa.db.model.VaccineCvx;
import org.openimmunizationsoftware.dqa.db.model.VaccineMvx;

public class CodesReceived
{
  public static final int ACTION_CODE = 1;
  public static final int ADDRESS_TYPE = 2;
  public static final int BIRTH_ORDER = 3;
  public static final int BODY_ROUTE = 4;
  public static final int BODY_SITE = 5;
  public static final int COUNTY_PARISH = 6;
  public static final int COUNTRY = 7;
  public static final int CPT = 8;
  public static final int CVX = 9;
  public static final int FACILITY = 10;
  public static final int INFO_SOURCE = 11;
  public static final int LANGUAGE = 12;
  public static final int MVX = 13;
  public static final int NAME_TYPE = 14;
  public static final int SEX = 15;
  public static final int VFC_STATUS = 16;
  public static final int STATE = 17;
  public static final int COMPLETE = 18;
  public static final int CONFIDENDIALITY = 19;

  private static CodesReceived singleton = null;

  public static CodesReceived getCodesReceived()
  {
    if (singleton == null)
    {
      singleton = new CodesReceived(true);
    }
    return singleton;
  }

  public static CodesReceived getCodesReceived(SubmitterProfile profile, Session session)
  {
    getCodesReceived();
    CodesReceived codesReceived = new CodesReceived(false);
    codesReceived.parent = singleton;

    for (CodeTable codeTable : codeTables.values())
    {
      String sql = "from CodeReceived where profile = ? and table = ?";
      Query query = session.createQuery(sql);
      query.setParameter(0, profile);
      query.setParameter(1, codeTable);
      codesReceived.addToCodeTableMaps(codeTable, query.list());
    }
    return codesReceived;
  }

  public CodeReceived getCodeReceived(String receivedValue, CodeTable codeTable)
  {
    CodeReceived cr = null;
    Map<String, CodeReceived> codesReceived = codeTableMaps.get(codeTable);
    if (codesReceived != null)
    {
      cr = codesReceived.get(receivedValue);
    }
    if (cr == null && parent != null)
    {
      cr = parent.getCodeReceived(receivedValue, codeTable);
    }
    return cr;
  }

  public static CodeTable getCodeTable(int tableId)
  {
    getCodesReceived();
    CodeTable ct = codeTables.get(tableId);
    if (ct == null)
    {
      throw new NullPointerException("Code table " + tableId + " unrecognized");
    }
    return ct;
  }

  public Map<Integer, CodeTable> getCodeTables()
  {
    return codeTables;
  }

  private static Map<Integer, CodeTable> codeTables = new HashMap<Integer, CodeTable>();

  private Map<CodeTable, Map<String, CodeReceived>> codeTableMaps = new HashMap<CodeTable, Map<String, CodeReceived>>();
  private CodesReceived parent = null;

  private CodesReceived(boolean parent) {
    if (parent)
    {
      SessionFactory factory = OrganizationManager.getSessionFactory();
      Session session = factory.openSession();

      Query query = session.createQuery("from CodeTable");
      List<CodeTable> codeTableList = query.list();
      for (CodeTable codeTable : codeTableList)
      {
        codeTables.put(codeTable.getTableId(), codeTable);
        if (codeTable.getTableId() == CPT)
        {
          query = session.createQuery("from VaccineCpt");
          addToCodeTableMapsCpt(codeTable, query.list(), (SubmitterProfile) session.get(SubmitterProfile.class, 1));
        } else if (codeTable.getTableId() == CVX)
        {
          query = session.createQuery("from VaccineCvx");
          addToCodeTableMapsCvx(codeTable, query.list(), (SubmitterProfile) session.get(SubmitterProfile.class, 1));
        } else if (codeTable.getTableId() == MVX)
        {
          query = session.createQuery("from VaccineMvx");
          addToCodeTableMapsMvx(codeTable, query.list(), (SubmitterProfile) session.get(SubmitterProfile.class, 1));
        } else
        {
          String sql = "from CodeMaster where table = ?";
          query = session.createQuery(sql);
          query.setParameter(0, codeTable);
          addMastersToCodeTableMaps(codeTable, query.list(), (SubmitterProfile) session.get(SubmitterProfile.class, 1));
        }
      }
      session.close();
    }
  }

  protected void addToCodeTableMaps(CodeTable codeTable, List<CodeReceived> codesReceived)
  {
    Map<String, CodeReceived> codeReceivedMap = new HashMap<String, CodeReceived>();
    for (CodeReceived codeReceived : codesReceived)
    {
      codeReceivedMap.put(codeReceived.getReceivedValue(), codeReceived);
    }
    codeTableMaps.put(codeTable, codeReceivedMap);
  }

  protected void addMastersToCodeTableMaps(CodeTable codeTable, List<CodeMaster> codeMasters, SubmitterProfile profile)
  {
    Map<String, CodeReceived> codeReceivedMap = new HashMap<String, CodeReceived>();
    for (CodeMaster codeMaster : codeMasters)
    {
      CodeReceived codeReceived = new CodeReceived();
      codeReceived.setReceivedValue(codeMaster.getCodeValue());
      codeReceived.setCodeStatus(codeMaster.getCodeStatus());
      codeReceived.setCodeValue(codeMaster.getUseValue());
      codeReceived.setProfile(profile);
      codeReceived.setTable(codeTable);
      codeReceivedMap.put(codeReceived.getReceivedValue(), codeReceived);
    }
    codeTableMaps.put(codeTable, codeReceivedMap);
  }

  protected void addToCodeTableMapsCpt(CodeTable codeTable, List<VaccineCpt> vaccineCpts, SubmitterProfile profile)
  {
    Map<String, CodeReceived> codeReceivedMap = new HashMap<String, CodeReceived>();
    for (VaccineCpt vaccineCpt : vaccineCpts)
    {
      CodeReceived codeReceived = new CodeReceived();
      codeReceived.setReceivedValue(vaccineCpt.getCptCode());
      codeReceived.setCodeStatus(CodeStatus.VALID);
      codeReceived.setCodeValue(vaccineCpt.getCptCode());
      codeReceived.setProfile(profile);
      codeReceived.setTable(codeTable);
      codeReceivedMap.put(codeReceived.getReceivedValue(), codeReceived);
    }
    codeTableMaps.put(codeTable, codeReceivedMap);
  }

  protected void addToCodeTableMapsCvx(CodeTable codeTable, List<VaccineCvx> vaccineCvxs, SubmitterProfile profile)
  {
    Map<String, CodeReceived> codeReceivedMap = new HashMap<String, CodeReceived>();
    for (VaccineCvx vaccineCvx : vaccineCvxs)
    {
      CodeReceived codeReceived = new CodeReceived();
      codeReceived.setReceivedValue(vaccineCvx.getCvxCode());
      codeReceived.setCodeStatus(CodeStatus.VALID);
      codeReceived.setCodeValue(vaccineCvx.getCvxCode());
      codeReceived.setProfile(profile);
      codeReceived.setTable(codeTable);
      codeReceivedMap.put(codeReceived.getReceivedValue(), codeReceived);
    }
    codeTableMaps.put(codeTable, codeReceivedMap);
  }

  protected void addToCodeTableMapsMvx(CodeTable codeTable, List<VaccineMvx> vaccineMvxs, SubmitterProfile profile)
  {
    Map<String, CodeReceived> codeReceivedMap = new HashMap<String, CodeReceived>();
    for (VaccineMvx vaccineMvx : vaccineMvxs)
    {
      CodeReceived codeReceived = new CodeReceived();
      codeReceived.setReceivedValue(vaccineMvx.getMvxCode());
      codeReceived.setCodeStatus(CodeStatus.VALID);
      codeReceived.setCodeValue(vaccineMvx.getMvxCode());
      codeReceived.setProfile(profile);
      codeReceived.setTable(codeTable);
      codeReceivedMap.put(codeReceived.getReceivedValue(), codeReceived);
    }
    codeTableMaps.put(codeTable, codeReceivedMap);
  }
}
