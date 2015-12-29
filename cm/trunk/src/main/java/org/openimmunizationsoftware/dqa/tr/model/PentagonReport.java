package org.openimmunizationsoftware.dqa.tr.model;

import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBox;

public class PentagonReport
{
  private int pentagonReportId = 0;
  private TestConducted testConducted = null;
  private int scoreC = -1;
  private int scoreUF = -1;
  private int scoreUC = -1;
  private int scoreQF = -1;
  private int scoreQC = -1;
  private int scoreCAckConform = -1;
  private int scoreCGoodMessages = -1;
  private int scoreCBadMessages = -1;
  private int scoreCGoodData = -1;
  private int scoreCBadData = -1;
  private int scoreUFVxu2014 = -1;
  private int scoreUFVxu2015 = -1;
  private int scoreUFSensitive = -1;
  private int scoreUFCodedValues = -1;
  private int scoreUFTolerant = -1;
  private int scoreUFEhrExamples = -1;
  private int scoreUFPerformance = -1;
  private int scoreQFQbp2015 = -1;
  private int scoreQFDataAvailable = -1;
  private int scoreQFDeduplication = -1;
  private int scoreQFForecaster = -1;
  private int scoreQFPerformance = -1;
  private int scoreQFMinimumQuery = -1;
  private int scoreUCModifications = -1;
  private int scoreUCConflicts = -1;
  private int scoreUCConstraints = -1;
  private int scoreUCAcksConform = -1;
  private int scoreQCResponsesConform = -1;
  private int scoreQCSoapConforms = -1;

  public int[] getScores()
  {
    int[] scores = new int[5];
    scores[0] = scoreC < 0 ? 0 : scoreC;
    scores[1] = scoreQF < 0 ? 0 : scoreQF;
    scores[2] = scoreQC < 0 ? 0 : scoreQC;
    scores[3] = scoreUC < 0 ? 0 : scoreUC;
    scores[4] = scoreUF < 0 ? 0 : scoreUF;
    return scores;
  }

  public int getScore(String boxName)
  {
    if (boxName.equals(PentagonBox.BOX_NAME_C_GOOD_MESSAGE))
    {
      return getScoreCGoodMessages();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_C_GOOD_DATA))
    {
      return getScoreCGoodData();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_C_BAD_MESSAGE))
    {
      return getScoreCBadMessages();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_C_BAD_DATA))
    {
      return getScoreCBadData();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_C_ACK_CONFORM))
    {
      return getScoreCAckConform();
    }
    ;
    if (boxName.equals(PentagonBox.BOX_NAME_QC_SOAP_CONFORMS))
    {
      return getScoreQCSoapConforms();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_QC_RESPONSES_CONFORM))
    {
      return getScoreQCResponsesConform();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_QF_QBP2015))
    {
      return getScoreQFQbp2015();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_QF_PERFORMANCE))
    {
      return getScoreQFPerformance();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_QF_MINIMUM_QUERY))
    {
      return getScoreQFMinimumQuery();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_QF_FORECASTER))
    {
      return getScoreQFForecaster();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_QF_DEDUPLICATION))
    {
      return getScoreQFDeduplication();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_QF_DATA_AVAILABLE))
    {
      return getScoreQFDataAvailable();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UC_MODIFICATIONS))
    {
      return getScoreUCModifications();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UC_CONFLICTS))
    {
      return getScoreUCConflicts();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UC_CONSTRAINTS))
    {
      return getScoreUCConstraints();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UC_ACKS_CONFORM))
    {
      return getScoreUCAcksConform();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UF_VXU2015))
    {
      return getScoreUFVxu2015();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UF_VXU2014))
    {
      return getScoreUFVxu2014();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UF_TOLERANT))
    {
      return getScoreUFTolerant();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UF_SENSITIVE))
    {
      return getScoreUFSensitive();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UF_PERFORMANCE))
    {
      return getScoreUFPerformance();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UF_EHR_EXAMPLES))
    {
      return getScoreUFEhrExamples();
    }
    if (boxName.equals(PentagonBox.BOX_NAME_UF_CODED_VALUES))
    {
      return getScoreUFCodedValues();
    }
    return 0;
  }

  public int getScoreUCModifications()
  {
    return scoreUCModifications;
  }

  public void setScoreUCModifications(int scoreUCModifications)
  {
    this.scoreUCModifications = scoreUCModifications;
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

  public int getScoreC()
  {
    return scoreC;
  }

  public void setScoreC(int scoreC)
  {
    this.scoreC = scoreC;
  }

  public int getScoreUF()
  {
    return scoreUF;
  }

  public void setScoreUF(int scoreUF)
  {
    this.scoreUF = scoreUF;
  }

  public int getScoreUC()
  {
    return scoreUC;
  }

  public void setScoreUC(int scoreUC)
  {
    this.scoreUC = scoreUC;
  }

  public int getScoreQF()
  {
    return scoreQF;
  }

  public void setScoreQF(int scoreQF)
  {
    this.scoreQF = scoreQF;
  }

  public int getScoreQC()
  {
    return scoreQC;
  }

  public void setScoreQC(int scoreQC)
  {
    this.scoreQC = scoreQC;
  }

  public int getScoreCAckConform()
  {
    return scoreCAckConform;
  }

  public void setScoreCAckConform(int scoreCAckConform)
  {
    this.scoreCAckConform = scoreCAckConform;
  }

  public int getScoreCGoodMessages()
  {
    return scoreCGoodMessages;
  }

  public void setScoreCGoodMessages(int scoreCGoodMessages)
  {
    this.scoreCGoodMessages = scoreCGoodMessages;
  }

  public int getScoreCBadMessages()
  {
    return scoreCBadMessages;
  }

  public void setScoreCBadMessages(int scoreCBadMessages)
  {
    this.scoreCBadMessages = scoreCBadMessages;
  }

  public int getScoreCGoodData()
  {
    return scoreCGoodData;
  }

  public void setScoreCGoodData(int scoreCGoodData)
  {
    this.scoreCGoodData = scoreCGoodData;
  }

  public int getScoreCBadData()
  {
    return scoreCBadData;
  }

  public void setScoreCBadData(int scoreCBadData)
  {
    this.scoreCBadData = scoreCBadData;
  }

  public int getScoreUFVxu2014()
  {
    return scoreUFVxu2014;
  }

  public void setScoreUFVxu2014(int scoreUFVxu2014)
  {
    this.scoreUFVxu2014 = scoreUFVxu2014;
  }

  public int getScoreUFVxu2015()
  {
    return scoreUFVxu2015;
  }

  public void setScoreUFVxu2015(int scoreUFVxu2015)
  {
    this.scoreUFVxu2015 = scoreUFVxu2015;
  }

  public int getScoreUFSensitive()
  {
    return scoreUFSensitive;
  }

  public void setScoreUFSensitive(int scoreUFSensitive)
  {
    this.scoreUFSensitive = scoreUFSensitive;
  }

  public int getScoreUFCodedValues()
  {
    return scoreUFCodedValues;
  }

  public void setScoreUFCodedValues(int scoreUFCodedValues)
  {
    this.scoreUFCodedValues = scoreUFCodedValues;
  }

  public int getScoreUFTolerant()
  {
    return scoreUFTolerant;
  }

  public void setScoreUFTolerant(int scoreUFTolerant)
  {
    this.scoreUFTolerant = scoreUFTolerant;
  }

  public int getScoreUFEhrExamples()
  {
    return scoreUFEhrExamples;
  }

  public void setScoreUFEhrExamples(int scoreUFEhrExamples)
  {
    this.scoreUFEhrExamples = scoreUFEhrExamples;
  }

  public int getScoreUFPerformance()
  {
    return scoreUFPerformance;
  }

  public void setScoreUFPerformance(int scoreUFOerformance)
  {
    this.scoreUFPerformance = scoreUFOerformance;
  }

  public int getScoreQFQbp2015()
  {
    return scoreQFQbp2015;
  }

  public void setScoreQFQbp2015(int scoreQFQbp2015)
  {
    this.scoreQFQbp2015 = scoreQFQbp2015;
  }

  public int getScoreQFDataAvailable()
  {
    return scoreQFDataAvailable;
  }

  public void setScoreQFDataAvailable(int scoreQFDataAvailable)
  {
    this.scoreQFDataAvailable = scoreQFDataAvailable;
  }

  public int getScoreQFDeduplication()
  {
    return scoreQFDeduplication;
  }

  public void setScoreQFDeduplication(int scoreQFDeduplication)
  {
    this.scoreQFDeduplication = scoreQFDeduplication;
  }

  public int getScoreQFForecaster()
  {
    return scoreQFForecaster;
  }

  public void setScoreQFForecaster(int scoreQFForecaster)
  {
    this.scoreQFForecaster = scoreQFForecaster;
  }

  public int getScoreQFPerformance()
  {
    return scoreQFPerformance;
  }

  public void setScoreQFPerformance(int scoreQFPerformance)
  {
    this.scoreQFPerformance = scoreQFPerformance;
  }

  public int getScoreQFMinimumQuery()
  {
    return scoreQFMinimumQuery;
  }

  public void setScoreQFMinimumQuery(int scoreQFMinimumQuery)
  {
    this.scoreQFMinimumQuery = scoreQFMinimumQuery;
  }

  public int getScoreUCConflicts()
  {
    return scoreUCConflicts;
  }

  public void setScoreUCConflicts(int scoreUCConflicts)
  {
    this.scoreUCConflicts = scoreUCConflicts;
  }

  public int getScoreUCConstraints()
  {
    return scoreUCConstraints;
  }

  public void setScoreUCConstraints(int scoreUCUnexpConstraints)
  {
    this.scoreUCConstraints = scoreUCUnexpConstraints;
  }

  public int getScoreUCAcksConform()
  {
    return scoreUCAcksConform;
  }

  public void setScoreUCAcksConform(int scoreUCAcksConform)
  {
    this.scoreUCAcksConform = scoreUCAcksConform;
  }

  public int getScoreQCResponsesConform()
  {
    return scoreQCResponsesConform;
  }

  public void setScoreQCResponsesConform(int scoreQCResponsesConform)
  {
    this.scoreQCResponsesConform = scoreQCResponsesConform;
  }

  public int getScoreQCSoapConforms()
  {
    return scoreQCSoapConforms;
  }

  public void setScoreQCSoapConforms(int scoreQCSoapConforms)
  {
    this.scoreQCSoapConforms = scoreQCSoapConforms;
  }
}
