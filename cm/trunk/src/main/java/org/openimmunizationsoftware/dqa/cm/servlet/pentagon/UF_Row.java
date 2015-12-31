package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class UF_Row extends PentagonRow
{
  
  public UF_Row(PentagonReport pentagonReport)
  {
    super("Update Functionality");
    PentagonBox[] pb = new PentagonBox[7];

    pb[0] = new UFVxu2014();
    pb[1] = new UFVxu2015();
    pb[2] = new UFSensitive();
    pb[3] = new UFCodedValues();
    pb[4] = new UFTolerant();
    pb[5] = new UFEhrExamples();
    pb[6] = new UFPerformance();

    pb[0].setTitle("NIST 2014 VXU Accepted");
    pb[1].setTitle("NIST 2015 VXU Accepted");
    pb[2].setTitle("Sensitive");
    pb[3].setTitle("Coded Values");
    pb[4].setTitle("Tolerant");
    pb[5].setTitle("EHR Examples");
    pb[6].setTitle("Performance");

    pb[0].setLabel("NIST 2014<br/>VXU Accepted");
    pb[1].setLabel("NIST 2015<br/>VXU Accepted");
    pb[2].setLabel("Sensitive");
    pb[3].setLabel("Coded Values");
    pb[4].setLabel("Tolerant");
    pb[5].setLabel("EHR Examples");
    pb[6].setLabel("Performance");

    pb[0].setWidth(15);
    pb[1].setWidth(15);
    pb[2].setWidth(14);
    pb[3].setWidth(14);
    pb[4].setWidth(14);
    pb[5].setWidth(14);
    pb[6].setWidth(14);
    
    pb[0].setWeight(35);
    pb[1].setWeight(25);
    pb[2].setWeight(12);
    pb[3].setWeight(10);
    pb[4].setWeight(8);
    pb[5].setWeight(5);
    pb[6].setWeight(5);

    pb[0].setScore(pentagonReport.getScoreUFVxu2014());
    pb[1].setScore(pentagonReport.getScoreUFVxu2015());
    pb[2].setScore(pentagonReport.getScoreUFSensitive());
    pb[3].setScore(pentagonReport.getScoreUFCodedValues());
    pb[4].setScore(pentagonReport.getScoreUFTolerant());
    pb[5].setScore(pentagonReport.getScoreUFEhrExamples());
    pb[6].setScore(pentagonReport.getScoreUFPerformance());
    
    for (PentagonBox pentagonBox : pb)
    {
      this.add(pentagonBox);
    }
  }
  
  @Override
  public void calculateScores(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport,
      Map<String, TestSection> testSectionMap)
  {
    calculateBoxScores(testConducted, dataSession, pentagonReport);
    int scoreUF = 0;
    scoreUF = addWeightToScore(scoreUF, this.get(0).getWeight(), pentagonReport.getScoreUFVxu2014());
    scoreUF = addWeightToScore(scoreUF, this.get(1).getWeight(), pentagonReport.getScoreUFVxu2015());
    scoreUF = addWeightToScore(scoreUF, this.get(2).getWeight(), pentagonReport.getScoreUFSensitive());
    scoreUF = addWeightToScore(scoreUF, this.get(3).getWeight(), pentagonReport.getScoreUFCodedValues());
    scoreUF = addWeightToScore(scoreUF, this.get(4).getWeight(), pentagonReport.getScoreUFTolerant());
    scoreUF = addWeightToScore(scoreUF, this.get(5).getWeight(), pentagonReport.getScoreUFEhrExamples());
    scoreUF = addWeightToScore(scoreUF, this.get(6).getWeight(), pentagonReport.getScoreUFPerformance());
    pentagonReport.setScoreUF(scoreUF / 100);    
  }
  

}