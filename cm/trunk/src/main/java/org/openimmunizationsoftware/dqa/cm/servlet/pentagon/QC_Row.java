package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

@SuppressWarnings("serial")
public class QC_Row extends PentagonRowHelper
{
  public QC_Row(PentagonReport pentagonReport) {
    super(pentagonReport, "Query Conformance", PentagonBox.ROW_NAME_QC);
    PentagonBoxHelper[] pb = new PentagonBoxHelper[2];

    pb[0] = new QCResponsesConform(getOrCreatePentagonBox(60, PentagonBox.BOX_NAME_QC_RESPONSES_CONFORM), this);
    pb[1] = new QCSoapConforms(getOrCreatePentagonBox(40, PentagonBox.BOX_NAME_QC_SOAP_CONFORMS), this);

    pb[0].setTitle("Responses Conform");
    pb[1].setTitle("CDC WSDL Conforms");

    pb[0].setLabel("Responses<br/>Conform");
    pb[1].setLabel("CDC WSDL<br/>Conforms");

    pb[0].setWidth(50);
    pb[1].setWidth(50);

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
