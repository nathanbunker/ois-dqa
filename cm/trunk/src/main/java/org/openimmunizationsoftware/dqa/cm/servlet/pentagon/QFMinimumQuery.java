package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class QFMinimumQuery extends PentagonBox
{
  public QFMinimumQuery()
  {
    super(BOX_NAME_QF_MINIMUM_QUERY);
  }


  @Override
  public void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub

  }
  
  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    // TODO Auto-generated method stub
    
  }
}
