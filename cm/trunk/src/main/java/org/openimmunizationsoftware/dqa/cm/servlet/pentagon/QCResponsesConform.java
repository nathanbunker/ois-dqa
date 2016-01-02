package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class QCResponsesConform extends UCAcksConform
{
  public QCResponsesConform(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() >= 100)
    {
      out.println("<h3 class=\"pentagon\">All Acks Conform</h3>");
    } else
    {
      UCAcksConform.printContentsConformanceProblems(out, dataSession, pentagonReport, "query");
    }

  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The National Institute of Standards and Technology (NIST) has an Immunization Test Suite "
        + "for testing and verifying the format of HL7 messages used in immunization message. In cooperation with CDC and AIRA, "
        + "NIST has carefully reviewed the HL7 Standards and the CDC Guide to identify exactly how query response messags should "
        + "be formatted. This score reflects how many error level issues were identified in each response received. </p>");
  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    UCAcksConform.printScoreExplanation(out, dataSession, pentagonReport, "query");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();
    TestConducted testConducted = pentagonReport.getTestConducted();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015) != null)
    {
      calculateScore(testConducted, dataSession, pentagonReport, "query");
    }
  }

  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Update the IIS query response message so that it conforms to the CDC Guide. "
        + "Focus on the errors that appear the most often. Be sure to verify the response messages using the NIST test site. </p>");
  }

}
