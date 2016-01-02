package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.util.Map;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class QF_Row extends PentagonRowHelper
{
  public QF_Row(PentagonReport pentagonReport) {
    super(pentagonReport, "Query Functionality", PentagonBox.ROW_NAME_QF);
    PentagonBoxHelper[] pb = new PentagonBoxHelper[5];

    pb[0] = new QFQbp2015(getOrCreatePentagonBox(35, PentagonBox.BOX_NAME_QF_QBP2015), this);
    pb[1] = new QFDataAvailable(getOrCreatePentagonBox(25, PentagonBox.BOX_NAME_QF_DATA_AVAILABLE), this);
    pb[2] = new QFDeduplication(getOrCreatePentagonBox(15, PentagonBox.BOX_NAME_QF_DEDUPLICATION), this);
    pb[3] = new QFForecaster(getOrCreatePentagonBox(15, PentagonBox.BOX_NAME_QF_FORECASTER), this);
    pb[4] = new QFPerformance(getOrCreatePentagonBox(10, PentagonBox.BOX_NAME_QF_PERFORMANCE), this);
    // pb[5] = new QFMinimumQuery();

    pb[0].setTitle("NIST 2015 QBP Supported");
    pb[1].setTitle("Data is Available");
    pb[2].setTitle("Deduplication Engaged");
    pb[3].setTitle("Forecaster Engaged");
    pb[4].setTitle("Performance");
    // pb[5].setTitle("Query Check");

    pb[0].setLabel("NIST 2015<br/>QBP Supported");
    pb[1].setLabel("Data is Available");
    pb[2].setLabel("Deduplication Engaged");
    pb[3].setLabel("Forecaster Engaged");
    pb[4].setLabel("Performance");
    // pb[5].setLabel("Query Check");

    pb[0].setWidth(20);
    pb[1].setWidth(20);
    pb[2].setWidth(20);
    pb[3].setWidth(20);
    pb[4].setWidth(20);
    // pb[5].setWidth(15);

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
