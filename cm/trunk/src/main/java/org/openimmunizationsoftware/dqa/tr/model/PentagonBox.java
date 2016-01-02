package org.openimmunizationsoftware.dqa.tr.model;

public class PentagonBox
{
  public static final String ROW_NAME_C = "C";
  public static final String ROW_NAME_QC = "QC";
  public static final String ROW_NAME_UC = "UC";
  public static final String ROW_NAME_UF = "UF";
  public static final String ROW_NAME_QF = "QF";
  
  public static final String BOX_NAME_C_GOOD_MESSAGE = "CGoodMessage";
  public static final String BOX_NAME_C_GOOD_DATA = "CGoodData";
  public static final String BOX_NAME_C_BAD_MESSAGE = "CBadMessage";
  public static final String BOX_NAME_C_BAD_DATA = "CBadData";
  public static final String BOX_NAME_C_ACK_CONFORM = "CAckConform";
  public static final String BOX_NAME_QC_SOAP_CONFORMS = "QCSoapConforms";
  public static final String BOX_NAME_QC_RESPONSES_CONFORM = "QCResponsesConform";
  public static final String BOX_NAME_QF_QBP2015 = "QFQbp2015";
  public static final String BOX_NAME_QF_PERFORMANCE = "QFPerformance";
  public static final String BOX_NAME_QF_MINIMUM_QUERY = "QFMinimumQuery";
  public static final String BOX_NAME_QF_FORECASTER = "QFForecaster";
  public static final String BOX_NAME_QF_DEDUPLICATION = "QFDeduplication";
  public static final String BOX_NAME_QF_DATA_AVAILABLE = "QFDataAvailable";
  public static final String BOX_NAME_UC_MODIFICATIONS = "UCModifications";
  public static final String BOX_NAME_UC_CONFLICTS = "UCConflicts";
  public static final String BOX_NAME_UC_CONSTRAINTS = "UCConstraints";
  public static final String BOX_NAME_UC_ACKS_CONFORM = "UCAcksConform";
  public static final String BOX_NAME_UF_VXU2015 = "UFVxu2015";
  public static final String BOX_NAME_UF_VXU2014 = "UFVxu2014";
  public static final String BOX_NAME_UF_TOLERANT = "UFTolerant";
  public static final String BOX_NAME_UF_SENSITIVE = "UFSensitive";
  public static final String BOX_NAME_UF_PERFORMANCE = "UFPerformance";
  public static final String BOX_NAME_UF_EHR_EXAMPLES = "UFEhrExamples";
  public static final String BOX_NAME_UF_CODED_VALUES = "UFCodedValues";

  private int pentgonBoxId = 0;
  private PentagonReport pentagonReport = null;
  private String boxRow = "";
  private String boxName = "";
  private int reportWeight = -1;
  private int reportScore = -1;
  private int reportScoreGap = -1;
  private int priorityRow = -1;
  private int priorityOverall = -1;
  
  public int getReportScoreGap()
  {
    return reportScoreGap;
  }

  public void setReportScoreGap(int reportScoreGap)
  {
    this.reportScoreGap = reportScoreGap;
  }

  public int getReportScore()
  {
    return reportScore;
  }

  public void setReportScore(int reportScore)
  {
    this.reportScore = reportScore;
  }

  public int getPentgonBoxId()
  {
    return pentgonBoxId;
  }

  public void setPentgonBoxId(int pentgonBoxId)
  {
    this.pentgonBoxId = pentgonBoxId;
  }

  public PentagonReport getPentagonReport()
  {
    return pentagonReport;
  }

  public void setPentagonReport(PentagonReport pentagonReport)
  {
    this.pentagonReport = pentagonReport;
  }

  public String getBoxRow()
  {
    return boxRow;
  }

  public void setBoxRow(String boxRow)
  {
    this.boxRow = boxRow;
  }

  public String getBoxName()
  {
    return boxName;
  }

  public void setBoxName(String boxName)
  {
    this.boxName = boxName;
  }

  public int getReportWeight()
  {
    return reportWeight;
  }

  public void setReportWeight(int reportWeight)
  {
    this.reportWeight = reportWeight;
  }

  public int getPriorityRow()
  {
    return priorityRow;
  }

  public void setPriorityRow(int priorityRow)
  {
    this.priorityRow = priorityRow;
  }

  public int getPriorityOverall()
  {
    return priorityOverall;
  }

  public void setPriorityOverall(int priorityOverall)
  {
    this.priorityOverall = priorityOverall;
  }
}
