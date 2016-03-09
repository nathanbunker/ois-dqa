package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

@SuppressWarnings("serial")
public class C_Row extends PentagonRowHelper
{
  public C_Row(PentagonReport pentagonReport) {

    super(pentagonReport, "Confidence", PentagonBox.ROW_NAME_C);
    PentagonBoxHelper[] pb = new PentagonBoxHelper[5];

    pb[0] = new CAckConform(getOrCreatePentagonBox(20, PentagonBox.BOX_NAME_C_ACK_CONFORM), this);
    pb[1] = new CGoodMessage(getOrCreatePentagonBox(20, PentagonBox.BOX_NAME_C_GOOD_MESSAGE), this);
    pb[2] = new CBadMessage(getOrCreatePentagonBox(20, PentagonBox.BOX_NAME_C_BAD_MESSAGE), this);
    pb[3] = new CGoodData(getOrCreatePentagonBox(18, PentagonBox.BOX_NAME_C_GOOD_DATA), this);
    pb[4] = new CBadData(getOrCreatePentagonBox(12, PentagonBox.BOX_NAME_C_BAD_DATA), this);

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

    for (PentagonBoxHelper pentagonBoxHelper : pb)
    {
      this.add(pentagonBoxHelper);
    }

  }
  
  

  @Override
  public void calculateScores(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport,
      Map<String, TestSection> testSectionMap)
  {
    calculateBoxScores(testConducted, dataSession, pentagonReport);
  }

}
