package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public abstract class PentagonRowHelper extends ArrayList<PentagonBoxHelper>
{
  private PentagonReport pentagonReport = null;
  private PentagonBox pentagonBox = null;
  private String boxRow = "";
  private String label = "";

  public PentagonBox getPentagonBox()
  {
    return pentagonBox;
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  protected PentagonRowHelper(PentagonReport pentagonReport, String label, String boxRow) {
    this.pentagonReport = pentagonReport;
    this.label = label;
    this.boxRow = boxRow;
    this.pentagonBox = pentagonReport.getPentagonBox(boxRow);
    if (pentagonBox == null)
    {
      pentagonBox = new PentagonBox();
      pentagonBox.setPentagonReport(pentagonReport);
      pentagonBox.setBoxRow(boxRow);
      pentagonReport.getPentagonBoxMap().put(boxRow, pentagonBox);
    }
  }

  protected void calculateBoxScores(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport)
  {
    int scoreC = 0;
    for (PentagonBoxHelper pentagonBoxHelper : this)
    {
      pentagonBoxHelper.calculateScore(dataSession, pentagonReport);
      scoreC = addWeightToScore(scoreC, pentagonBoxHelper.getWeight(), pentagonBoxHelper.getPentagonBox().getReportScore());
    }
    pentagonBox.setReportScore(scoreC / 100);
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

  public static ArrayList<PentagonRowHelper> createPentagonRowList(PentagonReport pentagonReport)
  {
    ArrayList<PentagonRowHelper> pentagonRowList = new ArrayList<PentagonRowHelper>();
    pentagonRowList.add(createConfidenceRow(pentagonReport));
    pentagonRowList.add(createUpdateFunctionalityRow(pentagonReport));
    pentagonRowList.add(createUpdateConformanceRow(pentagonReport));
    pentagonRowList.add(createQueryFunctionalityRow(pentagonReport));
    pentagonRowList.add(createQueryConformanceRow(pentagonReport));
    return pentagonRowList;
  }

  public static PentagonRowHelper createConfidenceRow(PentagonReport pentagonReport)
  {
    return new C_Row(pentagonReport);
  }

  public static PentagonRowHelper createQueryFunctionalityRow(PentagonReport pentagonReport)
  {
    return new QF_Row(pentagonReport);
  }

  public static PentagonRowHelper createQueryConformanceRow(PentagonReport pentagonReport)
  {
    return new QC_Row(pentagonReport);
  }

  public static PentagonRowHelper createUpdateConformanceRow(PentagonReport pentagonReport)
  {
    return new UC_Row(pentagonReport);
  }

  public static PentagonRowHelper createUpdateFunctionalityRow(PentagonReport pentagonReport)
  {
    return new UF_Row(pentagonReport);
  }

  public PentagonBoxHelper getPentagonBox(String boxName)
  {
    for (PentagonBoxHelper pentagonBox : this)
    {
      if (pentagonBox.getBoxName().equals(boxName))
      {
        return pentagonBox;
      }
    }
    return null;
  }

  public PentagonBox getOrCreatePentagonBox(int reportWeight, String boxName)
  {
    PentagonBox pentagonBox = pentagonReport.getPentagonBox(boxName);
    if (pentagonBox == null)
    {
      pentagonBox = new PentagonBox();
      pentagonBox.setPentagonReport(pentagonReport);
      pentagonBox.setBoxRow(boxRow);
      pentagonBox.setBoxName(boxName);
      pentagonBox.setReportWeight(reportWeight);
      pentagonReport.getPentagonBoxMap().put(boxName, pentagonBox);
    }
    return pentagonBox;
  }

}
