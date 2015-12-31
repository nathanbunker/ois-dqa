package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class QC_Row extends PentagonRow
{
  public QC_Row(PentagonReport pentagonReport) {
    super("Query Conformance");
    PentagonBox[] pb = new PentagonBox[2];

    pb[0] = new QCResponsesConform();
    pb[1] = new QCSoapConforms();

    pb[0].setTitle("Responses Conform");
    pb[1].setTitle("CDC WSDL Conforms");

    pb[0].setLabel("Responses<br/>Conform");
    pb[1].setLabel("CDC WSDL<br/>Conforms");

    pb[0].setWidth(50);
    pb[1].setWidth(50);

    pb[0].setWeight(60);
    pb[1].setWeight(40);

    pb[0].setScore(pentagonReport.getScoreQCResponsesConform());
    pb[1].setScore(pentagonReport.getScoreQCSoapConforms());
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
    int scoreQC = 0;
    scoreQC = addWeightToScore(scoreQC, 60, pentagonReport.getScoreQCResponsesConform());
    scoreQC = addWeightToScore(scoreQC, 40, pentagonReport.getScoreQCSoapConforms());
    pentagonReport.setScoreQC(scoreQC / 100);
  }
  

}
