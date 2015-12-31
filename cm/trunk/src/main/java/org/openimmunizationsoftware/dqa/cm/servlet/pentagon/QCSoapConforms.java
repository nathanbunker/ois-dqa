package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;
import org.openimmunizationsoftware.dqa.tr.model.TransportAnalysis;
import org.openimmunizationsoftware.dqa.tr.model.TransportOther;
import org.openimmunizationsoftware.dqa.tr.model.TransportWsdlCdc;

public class QCSoapConforms extends PentagonBox
{
  public QCSoapConforms() {
    super(BOX_NAME_QC_SOAP_CONFORMS);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">In 2015, AIRA launched an Interoperability Testing Project to determine the level of alignment between "
        + "current Immunization Information Systems (IIS) and the community's interoperability standards. The testing process connects with "
        + "IIS directly and submits sample messages to IIS development platforms. When any two systems connect to exchange data, they must "
        + "use an agreed upon transport layer to connect. To this end, a CDC-led expert panel was tasked with selecting transport layer and "
        + "defining a technical specification. In 2011, the panel selected Soap  and defined a formal specification known as a \"CDC WSDL.\" </p>");

  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    TransportWsdlCdc transportWsdlCdc = null;
    {
      Query query = dataSession.createQuery("from TransportWsdlCdc where transportAnalysis.connectionLabel = ?");
      query.setParameter(0, pentagonReport.getTestConducted().getConnectionLabel());
      List<TransportWsdlCdc> transportWsdlCdcList = query.list();
      if (transportWsdlCdcList.size() > 0)
      {
        transportWsdlCdc = transportWsdlCdcList.get(0);
      }
    }
    TransportOther transportOther = null;
    {
      Query query = dataSession.createQuery("from TransportOther where transportAnalysis.connectionLabel = ?");
      query.setParameter(0, pentagonReport.getTestConducted().getConnectionLabel());
      List<TransportOther> transportOtherList = query.list();
      if (transportOtherList.size() > 0)
      {
        transportOther = transportOtherList.get(0);
      }
    }
    TransportAnalysis transportAnalysis = null;
    if (transportWsdlCdc != null)
    {
      transportAnalysis = transportWsdlCdc.getTransportAnalysis();
    } else if (transportOther != null)
    {
      transportAnalysis = transportOther.getTransportAnalysis();
    }
    if (transportAnalysis == null)
    {
      out.println("<h3 class=\"pentagon\">Transport Analysis</h3>");
      out.println("<p class=\"pentagon\">Not completed</p>");
    } else if (transportWsdlCdc != null)
    {
      out.println("<h3 class=\"pentagon\">CDC WSDL Analysis</h3>");
      if (!transportAnalysis.getReportComplete().equalsIgnoreCase("Yes"))
      {
        out.println("<p class=\"pentagon\">Report is not complete yet. </p>");
      } else if (transportAnalysis.getTransportType().equalsIgnoreCase("Non-participator CDC WSDL"))
      {
        out.println("<p class=\"pentagon\">Did not participate in status check project.</p>");
      } else if (transportAnalysis.getTransportType().equalsIgnoreCase("CDC WSDL"))
      {
        if (!transportWsdlCdc.getTransportStatus().equalsIgnoreCase("Done"))
        {
          out.println("<p class=\"pentagon\">Report is not done yet.</p>");
        } else if (transportWsdlCdc.getPassEyeTest().equalsIgnoreCase("No"))
        {
          out.println("<p class=\"pentagon\">WSDL does not look like CDC WSDL. No further analysis could be performed. </p>");
        } else
        {
          out.println("<table class=\"pentagon\">");
          out.println("  <tr>");
          out.println("    <th>Connectivity Test</th>");
          out.println("    <th>Submit Single Message</th>");
          out.println("    <th>Security Fault</th>");
          out.println("  </tr>");
          out.println("  <tr>");
          if (transportWsdlCdc.getCtConforms().equalsIgnoreCase("Yes"))
          {
            out.println("    <td class=\"pass\" style=\"text-align: center;\">Pass</td>");
          } else
          {
            out.println("    <td class=\"fail\" style=\"text-align: center;\">Fail</td>");
          }
          if (transportWsdlCdc.getSsmConforms().equalsIgnoreCase("Yes"))
          {
            out.println("    <td class=\"pass\" style=\"text-align: center;\">Pass</td>");
          } else
          {
            out.println("    <td class=\"fail\" style=\"text-align: center;\">Fail</td>");
          }
          if (transportWsdlCdc.getSfConforms().equalsIgnoreCase("Yes"))
          {
            out.println("    <td class=\"pass\" style=\"text-align: center;\">Pass</td>");
          } else
          {
            out.println("    <td class=\"fail\" style=\"text-align: center;\">Fail</td>");
          }
          out.println("  </tr>");
          out.println("</table>");
        }
      }
    } else if (transportOther != null)
    {
      out.println("<h3 class=\"pentagon\">Other Transport Analysis</h3>");
    }
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
    TestConducted testConducted = pentagonReport.getTestConducted();
    TransportWsdlCdc transportWsdlCdc = null;
    {
      Query query = dataSession.createQuery("from TransportWsdlCdc where transportAnalysis.connectionLabel = ?");
      query.setParameter(0, testConducted.getConnectionLabel());
      List<TransportWsdlCdc> transportWsdlCdcList = query.list();
      if (transportWsdlCdcList.size() > 0)
      {
        transportWsdlCdc = transportWsdlCdcList.get(0);
      }
    }
    if (transportWsdlCdc != null)
    {
      TransportAnalysis transportAnalysis = transportWsdlCdc.getTransportAnalysis();
      if (transportAnalysis.getTransportType().equalsIgnoreCase("CDC WSDL") && transportAnalysis.getReportComplete().equalsIgnoreCase("Yes"))
      {
        int score = 0;
        if (transportWsdlCdc.getSsmConforms().equalsIgnoreCase("Yes"))
        {
          score += 70;
        }
        if (transportWsdlCdc.getCtConforms().equalsIgnoreCase("Yes"))
        {
          score += 20;
        }
        if (transportWsdlCdc.getSfConforms().equalsIgnoreCase("Yes"))
        {
          score += 10;
        }
        pentagonReport.setScoreQCSoapConforms(score);
      }
    }
  }
}
