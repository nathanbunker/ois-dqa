package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

@SuppressWarnings("serial")
public class UF_Row extends PentagonRowHelper
{
  
  public UF_Row(PentagonReport pentagonReport)
  {
    super(pentagonReport, "Update Functionality", PentagonBox.ROW_NAME_UF);
    PentagonBoxHelper[] pb = new PentagonBoxHelper[7];

    pb[0] = new UFVxu2014(getOrCreatePentagonBox(35, PentagonBox.BOX_NAME_UF_VXU2014), this);
    pb[1] = new UFVxu2015(getOrCreatePentagonBox(25, PentagonBox.BOX_NAME_UF_VXU2015), this);
    pb[2] = new UFSensitive(getOrCreatePentagonBox(12, PentagonBox.BOX_NAME_UF_SENSITIVE), this);
    pb[3] = new UFCodedValues(getOrCreatePentagonBox(10, PentagonBox.BOX_NAME_UF_CODED_VALUES), this);
    pb[4] = new UFTolerant(getOrCreatePentagonBox(8, PentagonBox.BOX_NAME_UF_TOLERANT), this);
    pb[5] = new UFEhrExamples(getOrCreatePentagonBox(5, PentagonBox.BOX_NAME_UF_EHR_EXAMPLES), this);
    pb[6] = new UFPerformance(getOrCreatePentagonBox(5, PentagonBox.BOX_NAME_UF_PERFORMANCE), this);

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
