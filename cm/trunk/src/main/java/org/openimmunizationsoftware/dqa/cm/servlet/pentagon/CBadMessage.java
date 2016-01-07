package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class CBadMessage extends PentagonBoxHelper
{
  public CBadMessage(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() == 100)
    {
      out.println(
          "<p class=\"pentagon\">All clearly bad messages were not accepted by the IIS. This means that the IIS identified the issue, messaged it back "
              + "for this report, and this report was able to understand that there was a problem. This means that this report can place "
              + "higher confidence on the status of data submitted based on the acknowledgment message. </p>");
    } else
    {
      out.println(
          "<p class=\"pentagon\">Messages that have severe data quality or format issues that should prevent them from being read and properly saved by most IIS "
              + "are considered to be bad messages. While there may be some exceptions in certain cases most IIS should be identifying and "
              + "acknowledging the problems in these messages when replying. This score reflects the ability of the IIS to identify and communicate "
              + "issues that are present in bad messages or the ability of this report to understand the not accept message from the IIS.</p> ");
    }
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    if (pentagonBox.getReportScore() < 100)
    {
      out.println("<h4 class=\"pentagon\">Fail - Bad Message Was Accepted</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus <> 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListFail(out, testMessageList);
    }
    if (pentagonBox.getReportScore() > 0)
    {
      out.println("<h4 class=\"pentagon\">Pass - Bad Message Was Rejected</h4>");
      Query query = dataSession.createQuery(
          "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
      query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      query.setParameter(1, pentagonReport.getTestConducted());
      List<TestMessage> testMessageList = query.list();
      printTestMessageListPass(out, testMessageList);
    }
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession,
      UserSession userSession)
  {
    out.println(
        "<p class=\"pentagon\">The percentage of bad messages that were rejected out of the total number of bad messages that are in the test case. </p>");
    out.println("<p class=\"pentagon\">Bad messages were made by removing or changing one field content that met the following requirements:  </p>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">Must be a CDC Guide required field.  </li>");
    out.println("  <li class=\"pentagon\">If valued, the value must be unusable for an IIS.  </li>");
    out.println("  <li class=\"pentagon\">IIS will not be able to properly store a patient or vaccination concept. </li>");
    out.println("</ul>");
    out.println("<p class=\"pentagon\">The selection of bad messages makes the following assumptions about IIS: </p>");
    out.println("<ul class=\"pentagon\">");
    out.println("  <li class=\"pentagon\">In order to store patient information there must be a Patient Id, Name, and Date of Birth (dob).  </li>");
    out.println("  <li class=\"pentagon\">In order to store vaccination information there must be a Vaccination Date and Vaccination Code. </li>");
    out.println("</ul>");
    out.println("<p class=\"pentagon\">The examples shown above are reasonable expectations for situations when most IIS would be expected to "
        + "not accept part or all of the data in a message.</p>");

  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      pentagonBox.setReportScore(testSection.getScoreLevel1());
    }

  }

  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">Ensure that major issues, such as critical data missing, are noted in the acknowledgment response. "
        + "IIS should return an error to the submitter when missing data prevents the acceptance of critical patient or vaccination information. </p>");
  }
}
