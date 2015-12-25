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

public class UFSensitive extends PentagonBox
{
  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">IIS must be ensuring that received messages meet basic standards by performing basic checks before "
        + "accepting incoming data. Problems that are found should be messaged back to the sender in the acknowledgement message. "
        + "Doing so gives sending systems the opportunity to correct mistakes and resend. This test can also help confirm that the IIS"
        + "is not vulnerable to the receipt of bad data. Success is measured by noting if the introduction of the issue results"
        + "in a substantive change in the acknowledgment message. Due to the different ways IIS message back issues, this measure is "
        + "somewhat crude and should not be depended on to absolutely establish that an IIS is sensitive to any particular issue.  </p>");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
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
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED);
      pentagonReport.setScoreUFSensitive(testSection.getScoreLevel1());
    }

    
  }
}
