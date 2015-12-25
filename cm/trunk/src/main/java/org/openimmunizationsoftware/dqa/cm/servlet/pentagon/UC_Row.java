package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class UC_Row extends PentagonRow
{
  public UC_Row(PentagonReport pentagonReport) {

    PentagonBox[] pb = new PentagonBox[4];

    pb[0] = new UCModifications();
    pb[1] = new UCConflicts();
    pb[2] = new UCConstraints();
    pb[3] = new UCAcksConform();

    pb[0].setTitle("No Modifications");
    pb[1].setTitle("No Conflicts");
    pb[2].setTitle("No Constraints");
    pb[3].setTitle("Acks Conform");

    pb[0].setLabel("No<br/>Modifications");
    pb[1].setLabel("No<br/>Conflicts");
    pb[2].setLabel("No<br/>Constraints");
    pb[3].setLabel("Acks<br/>Conform");

    pb[0].setWeight(20);
    pb[1].setWeight(20);
    pb[2].setWeight(20);
    pb[3].setWeight(20);

    pb[0].setScore(pentagonReport.getScoreUCModifications());
    pb[1].setScore(pentagonReport.getScoreUCConflicts());
    pb[2].setScore(pentagonReport.getScoreUCConstraints());
    pb[3].setScore(pentagonReport.getScoreUCAcksConform());
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
    int scoreUC = 0;
    scoreUC = addWeightToScore(scoreUC, 30, pentagonReport.getScoreUCModifications());
    scoreUC = addWeightToScore(scoreUC, 30, pentagonReport.getScoreUCConflicts());
    scoreUC = addWeightToScore(scoreUC, 20, pentagonReport.getScoreUCConstraints());
    scoreUC = addWeightToScore(scoreUC, 20, pentagonReport.getScoreUCAcksConform());
    pentagonReport.setScoreUC(scoreUC / 100);    
  }
  

}
