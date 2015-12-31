package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class C_Row extends PentagonRow
{
  public C_Row(PentagonReport pentagonReport) {

    super("Confidence");
    PentagonBox[] pb = new PentagonBox[5];

    pb[0] = new CAckConform();
    pb[1] = new CGoodMessage();
    pb[2] = new CBadMessage();
    pb[3] = new CGoodData();
    pb[4] = new CBadData();

    pb[0].setTitle("ACKs Conform to NIST 2015");
    pb[1].setTitle("Good Messages Accepted");
    pb[2].setTitle("Bad Messages Rejected");
    pb[3].setTitle("Good Data Stored");
    pb[4].setTitle("Bad Data Not Stored");

    pb[0].setLabel("ACKs Conform to NIST 2015");
    pb[1].setLabel("Good Messages <br/>Accepted");
    pb[2].setLabel("Bad Messages <br/>Rejected");
    pb[3].setLabel("Good Data <br/>Stored");
    pb[4].setLabel("Bad Data <br/>Not Stored");

    pb[0].setWidth(20);
    pb[1].setWidth(20);
    pb[2].setWidth(20);
    pb[3].setWidth(20);
    pb[4].setWidth(20);

    pb[0].setWeight(30);
    pb[1].setWeight(20);
    pb[2].setWeight(20);
    pb[3].setWeight(25);
    pb[4].setWeight(5);

    pb[0].setScore(pentagonReport.getScoreCAckConform());
    pb[1].setScore(pentagonReport.getScoreCGoodMessages());
    pb[2].setScore(pentagonReport.getScoreCBadMessages());
    pb[3].setScore(pentagonReport.getScoreCGoodData());
    pb[4].setScore(pentagonReport.getScoreCBadData());

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
    int scoreC = 0;
    scoreC = addWeightToScore(scoreC, 20, pentagonReport.getScoreCAckConform());
    scoreC = addWeightToScore(scoreC, 20, pentagonReport.getScoreCGoodMessages());
    scoreC = addWeightToScore(scoreC, 20, pentagonReport.getScoreCBadMessages());
    scoreC = addWeightToScore(scoreC, 18, pentagonReport.getScoreCGoodData());
    scoreC = addWeightToScore(scoreC, 12, pentagonReport.getScoreCBadData());
    pentagonReport.setScoreC(scoreC / 100);
  }

}
