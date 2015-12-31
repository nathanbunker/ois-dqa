package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public abstract class PentagonRow extends ArrayList<PentagonBox>
{
  private String label = "";

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  protected PentagonRow(String label) {
    this.label = label;
  }

  protected void calculateBoxScores(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport,
      Map<String, TestSection> testSectionMap)
  {
    for (PentagonBox pentagonBox : this)
    {
      pentagonBox.calculateScore(testConducted, dataSession, pentagonReport, testSectionMap);
    }
  }

  public abstract void calculateScores(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport,
      Map<String, TestSection> testSectionMap);

  protected int addWeightToScore(int scoreC, int weight, int score)
  {
    if (score >= 0)
    {
      scoreC += weight * score;
    }
    return scoreC;
  }

  public static ArrayList<PentagonRow> createPentagonRowList(PentagonReport pentagonReport)
  {
    ArrayList<PentagonRow> pentagonRowList = new ArrayList<PentagonRow>();
    pentagonRowList.add(createConfidenceRow(pentagonReport));
    pentagonRowList.add(createUpdateFunctionalityRow(pentagonReport));
    pentagonRowList.add(createUpdateConformanceRow(pentagonReport));
    pentagonRowList.add(createQueryFunctionalityRow(pentagonReport));
    pentagonRowList.add(createQueryConformanceRow(pentagonReport));
    return pentagonRowList;
  }

  public static PentagonRow createConfidenceRow(PentagonReport pentagonReport)
  {
    return new C_Row(pentagonReport);
  }

  public static PentagonRow createQueryFunctionalityRow(PentagonReport pentagonReport)
  {
    return new QF_Row(pentagonReport);
  }

  public static PentagonRow createQueryConformanceRow(PentagonReport pentagonReport)
  {
    return new QC_Row(pentagonReport);
  }

  public static PentagonRow createUpdateConformanceRow(PentagonReport pentagonReport)
  {
    return new UC_Row(pentagonReport);
  }

  public static PentagonRow createUpdateFunctionalityRow(PentagonReport pentagonReport)
  {
    return new UF_Row(pentagonReport);
  }

  public PentagonBox getPentagonBox(String boxName)
  {
    for (PentagonBox pentagonBox : this)
    {
      if (pentagonBox.getBoxName().equals(boxName))
      {
        return pentagonBox;
      }
    }
    return null;
  }

}
