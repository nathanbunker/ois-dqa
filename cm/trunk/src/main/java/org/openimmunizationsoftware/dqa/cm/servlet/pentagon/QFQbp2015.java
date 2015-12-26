package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class QFQbp2015 extends PentagonBox
{

  public QFQbp2015()
  {
    super("QFQbp2015");
  }


  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    // TODO Auto-generated method stub
    
  }


  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT);
      pentagonReport.setScoreQFQbp2015(testSection.getScoreLevel2());
    }

    
  }
}
