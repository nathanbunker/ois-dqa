package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class QF_Row extends PentagonRow
{
  public QF_Row(PentagonReport pentagonReport) {
    super("Query Functionality");
    PentagonBox[] pb = new PentagonBox[5];

    pb[0] = new QFQbp2015();
    pb[1] = new QFDataAvailable();
    pb[2] = new QFDeduplication();
    pb[3] = new QFForecaster();
    pb[4] = new QFPerformance();
//    pb[5] = new QFMinimumQuery();

    pb[0].setTitle("NIST 2015 QBP Supported");
    pb[1].setTitle("Data is Available");
    pb[2].setTitle("Deduplication Engaged");
    pb[3].setTitle("Forecaster Engaged");
    pb[4].setTitle("Performance");
//    pb[5].setTitle("Query Check");

    pb[0].setLabel("NIST 2015<br/>QBP Supported");
    pb[1].setLabel("Data is Available");
    pb[2].setLabel("Deduplication Engaged");
    pb[3].setLabel("Forecaster Engaged");
    pb[4].setLabel("Performance");
//    pb[5].setLabel("Query Check");

    pb[0].setWidth(20);
    pb[1].setWidth(20);
    pb[2].setWidth(20);
    pb[3].setWidth(20);
    pb[4].setWidth(20);
//    pb[5].setWidth(15);
    
    pb[0].setWeight(30);
    pb[1].setWeight(20);
    pb[2].setWeight(20);
    pb[3].setWeight(20);
    pb[4].setWeight(10);
//    pb[5].setWeight(15);
    
    pb[0].setScore(pentagonReport.getScoreQFQbp2015());
    pb[1].setScore(pentagonReport.getScoreQFDataAvailable());
    pb[2].setScore(pentagonReport.getScoreQFDeduplication());
    pb[3].setScore(pentagonReport.getScoreQFForecaster());
    pb[4].setScore(pentagonReport.getScoreQFPerformance());
//    pb[5].setScore(pentagonReport.getScoreQFMinimumQuery());
    for (PentagonBox pentagonBox : pb)
    {
      this.add(pentagonBox);
    }
  }

  @Override
  public void calculateScores(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport,
      Map<String, TestSection> testSectionMap)
  {
    calculateBoxScores(testConducted, dataSession, pentagonReport, testSectionMap);
    int scoreQF = 0;
    scoreQF = addWeightToScore(scoreQF, 30, pentagonReport.getScoreQFQbp2015());
    scoreQF = addWeightToScore(scoreQF, 20, pentagonReport.getScoreQFDataAvailable());
    scoreQF = addWeightToScore(scoreQF, 15, pentagonReport.getScoreQFDeduplication());
    scoreQF = addWeightToScore(scoreQF, 15, pentagonReport.getScoreQFForecaster());
    scoreQF = addWeightToScore(scoreQF, 10, pentagonReport.getScoreQFPerformance());
    scoreQF = addWeightToScore(scoreQF, 10, pentagonReport.getScoreQFMinimumQuery());
    pentagonReport.setScoreQF(scoreQF / 100);

  }

}
