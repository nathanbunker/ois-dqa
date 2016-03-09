package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.TestReportServlet;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.Transform;

public class UCModifications extends PentagonBoxHelper
{
  public UCModifications(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Modifications are changes made to each test message to meet local IIS reequirements. Some of these "
        + "modifications are for fields that would be expected to be changed, such as identification fields in the MSH segment. "
        + "Others are to meet local requirements for form or content. Thus, modifications are identified as either "
        + "expected or unexpected modifications. IIS should work to reduce the unexpected modifications as much as possible as "
        + "these are likely to be off-standard or at the very least a difference from the national implementation guide. Local "
        + "law and/or policies may prevent removal of all modifications.</p>");
  }

  @SuppressWarnings("unchecked")
  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    List<Transform> transformExpected = null;
    {
      Query query = dataSession
          .createQuery("from Transform where testConducted = ? and transformField.transformExpected = ? order by transformField.transformText");
      query.setParameter(0, pentagonReport.getTestConducted());
      query.setParameter(1, true);
      transformExpected = query.list();
    }
    List<Transform> transformCustom = null;
    {
      Query query = dataSession
          .createQuery("from Transform where testConducted = ? and transformField.transformExpected = ? order by transformField.transformText");
      query.setParameter(0, pentagonReport.getTestConducted());
      query.setParameter(1, false);
      transformCustom = query.list();
    }
    if (transformExpected.size() == 0 && transformCustom.size() == 0)
    {
      out.println("<h4 class=\"pentagon\">No Local Requirements Discovered</h4>");
      out.println("<p class=\"pentagon\">This interface did not require any modifications to messages in order for it to be accepted. </p>");
    } else
    {
      out.println("<h4 class=\"pentagon\">Local Requirements Discovered</h4>");
      out.println("<p class=\"pentagon\">This interface has local requirements which must be applied "
          + "before transmitting them to the IIS. Each test case in the Interoperability Testing Project is built against the National IG. "
          + "Before submitting a test case to an IIS, the message is transformed to meet local requirements. These transformations can range from setting "
          + "the correct submitter facility in the message header to modifying the structure of "
          + "the HL7 message to meet local requirements. </p>");
      if (transformExpected.size() > 0)
      {
        out.println("<h4 class=\"pentagon\">Anticipated Local Requirements</h4>");
        out.println("<p class=\"pentagon\">" + "Changes to certain fields such as MSH-4 and RXA-11.4 are anticipated "
            + "as IIS may request specific identifier values in these fields. </p>");
        TestReportServlet.printTransforms(out, transformExpected);
      }
      if (transformCustom.size() > 0)
      {
        out.println("<h4 class=\"pentagon\">Unanticipated Local Requirements</h4>");
        out.println("<p class=\"pentagon\">These changes were not anticipated based on the national standard or in "
            + "NIST testing. Please examine the need for these changes carefully as they are "
            + "likely to result in significant effort by EHR-s and other trading partners to achieve interoperability.</p>");
        TestReportServlet.printTransforms(out, transformCustom);
      }
    }
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p>Scoring in this section is based on a raw count of discovered local requirements.</p>");
    out.println("<p>The score is calculated using the intersection table below.  For example, an interface with 1 unanticipated "
            + "local requirements and 6 anticipated local requirements would have a score of 65%.</p>");
    
    out.println("<table class=\"pentagon\">"
              + "  <tr class=\"pentagon\" align=\"center\">" 
              + "    <td style='border:0px;' width=\"20%\">&nbsp;</td>"
              + "    <td style='border:0px;'>&nbsp;</td>"
              + "    <td  class=\"pentagon\" colspan=4><b># of Unanticipated Local Requirements</b></td>" 
              + "  </tr>"
              + "  <tr class=\"pentagon\" align=\"center\">"
              + "    <td style='border:0px;'>&nbsp;</td>"
              + "    <td style='border:0px;'>&nbsp;</td>"
              + "    <td class=\"pentagon\"><b>0</b></td>" 
              + "    <td class=\"pentagon\"><b>1</b></td>" 
              + "    <td class=\"pentagon\"><b>2</b></td>" 
              + "    <td class=\"pentagon\"><b>3 or more</b></td>" 
              + "  </tr>"
              + "  <tr class=\"pentagon\" align=\"center\">"
              + "    <td class=\"pentagon\" rowspan=3><b># of Anticipated Local Requirements</b></td>"
              + "    <td class=\"pentagon\"><b>5 or less</b></td>" 
              + "    <td class=\"pentagon\">100%</td>" 
              + "    <td class=\"pentagon\">80%</td>" 
              + "    <td class=\"pentagon\">60%</td>" 
              + "    <td class=\"pentagon\">30%</td>" 
              + "  </tr>"
              + "  <tr class=\"pentagon\" align=\"center\">"
              + "    <td class=\"pentagon\"><b>6 or 7</b></td>" 
              + "    <td class=\"pentagon\">85%</td>" 
              + "    <td class=\"pentagon\">65%</td>" 
              + "    <td class=\"pentagon\">45%</td>" 
              + "    <td class=\"pentagon\">15%</td>" 
              + "  </tr>"
              + "  <tr class=\"pentagon\" align=\"center\">"
              + "    <td class=\"pentagon\"><b>8 or more</b></td>" 
              + "    <td class=\"pentagon\">70%</td>" 
              + "    <td class=\"pentagon\">50%</td>" 
              + "    <td class=\"pentagon\">30%</td>" 
              + "    <td class=\"pentagon\">0%</td>" 
              + "  </tr>"
              + "</table>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    TestConducted testConducted = pentagonReport.getTestConducted();
    Query query = dataSession.createQuery("from Transform where testConducted = ?");
    query.setParameter(0, testConducted);
    @SuppressWarnings("unchecked")
    List<Transform> transformList = query.list();
    int countExpected = 0;
    for (Transform transform : transformList)
    {
      if (transform.getTransformField().isTransformExpected())
      {
        countExpected++;
      }
    }
    int countUnexpected = transformList.size() - countExpected;
    int totalScore = 0;
    if (countUnexpected == 0)
    {
      totalScore += 70;
    } else if (countUnexpected == 1)
    {
      totalScore += 50;
    } else if (countUnexpected == 2)
    {
      totalScore += 30;
    }
    if (countExpected <= 5)
    {
      totalScore += 30;
    } else if (countExpected <= 7)
    {
      totalScore += 15;
    }
    pentagonBox.setReportScore(totalScore);
  }
  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub
    
  }
}
