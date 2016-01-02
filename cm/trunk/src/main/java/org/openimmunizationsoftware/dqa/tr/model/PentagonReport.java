package org.openimmunizationsoftware.dqa.tr.model;

import java.util.HashMap;
import java.util.Map;

import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBoxHelper;

public class PentagonReport
{
  private int pentagonReportId = 0;
  private TestConducted testConducted = null;
  
  private transient Map<String, TestSection> testSectionMap = null;
  private transient Map<String, PentagonBox> pentagonBoxMap = new HashMap<String, PentagonBox>();

  public Map<String, PentagonBox> getPentagonBoxMap()
  {
    return pentagonBoxMap;
  }

  public void setPentagonBoxMap(Map<String, PentagonBox> pentagonBoxMap)
  {
    this.pentagonBoxMap = pentagonBoxMap;
  }

  public Map<String, TestSection> getTestSectionMap()
  {
    return testSectionMap;
  }

  public void setTestSectionMap(Map<String, TestSection> testSectionMap)
  {
    this.testSectionMap = testSectionMap;
  }

  public int[] getScores()
  {
    int scoreC = getScore(PentagonBox.ROW_NAME_C);
    int scoreQF = getScore(PentagonBox.ROW_NAME_QF);
    int scoreQC = getScore(PentagonBox.ROW_NAME_QC);
    int scoreUC = getScore(PentagonBox.ROW_NAME_UC);
    int scoreUF = getScore(PentagonBox.ROW_NAME_UF);
    int[] scores = new int[5];
    scores[0] = scoreC < 0 ? 0 : scoreC;
    scores[1] = scoreQF < 0 ? 0 : scoreQF;
    scores[2] = scoreQC < 0 ? 0 : scoreQC;
    scores[3] = scoreUC < 0 ? 0 : scoreUC;
    scores[4] = scoreUF < 0 ? 0 : scoreUF;
    return scores;
  }
  
  public PentagonBox getPentagonBox(String boxName)
  {
    return pentagonBoxMap.get(boxName);
  }

  public int getScore(String boxName)
  {
    PentagonBox pentagonBox = pentagonBoxMap.get(boxName);
    if (pentagonBox != null)
    {
      return pentagonBox.getReportScore();
    }
    return -1;
  }

  public int getPentagonReportId()
  {
    return pentagonReportId;
  }

  public void setPentagonReportId(int pentagonReportId)
  {
    this.pentagonReportId = pentagonReportId;
  }

  public TestConducted getTestConducted()
  {
    return testConducted;
  }

  public void setTestConducted(TestConducted testConducted)
  {
    this.testConducted = testConducted;
  }

}
