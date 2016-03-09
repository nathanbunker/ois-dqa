package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

@SuppressWarnings("serial")
public class UC_Row extends PentagonRowHelper
{
  public UC_Row(PentagonReport pentagonReport) {
    super(pentagonReport, "Update Conformance", PentagonBox.ROW_NAME_UC);
    PentagonBoxHelper[] pb = new PentagonBoxHelper[4];

    pb[0] = new UCModifications(getOrCreatePentagonBox(30, PentagonBox.BOX_NAME_UC_MODIFICATIONS), this);
    pb[1] = new UCConflicts(getOrCreatePentagonBox(30, PentagonBox.BOX_NAME_UC_CONFLICTS), this);
    pb[2] = new UCConstraints(getOrCreatePentagonBox(20, PentagonBox.BOX_NAME_UC_CONSTRAINTS), this);
    pb[3] = new UCAcksConform(getOrCreatePentagonBox(20, PentagonBox.BOX_NAME_UC_ACKS_CONFORM), this);

    pb[0].setTitle("No Modifications");
    pb[1].setTitle("No Conflicts");
    pb[2].setTitle("No Constraints");
    pb[3].setTitle("Acks Conform");

    pb[0].setLabel("No<br/>Modifications");
    pb[1].setLabel("No<br/>Conflicts");
    pb[2].setLabel("No<br/>Constraints");
    pb[3].setLabel("Acks<br/>Conform");

    pb[0].setWidth(25);
    pb[1].setWidth(25);
    pb[2].setWidth(25);
    pb[3].setWidth(25);

    for (PentagonBoxHelper pentagonBox : pb)
    {
      this.add(pentagonBox);
    }
  }
  
  @Override
  public void calculateScores(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport,
      Map<String, TestSection> testSectionMap)
  {
    calculateBoxScores(testConducted, dataSession, pentagonReport);
  }
  

}
