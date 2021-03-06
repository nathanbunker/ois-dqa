package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsageValue;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestProfile;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;
import org.openimmunizationsoftware.dqa.tr.profile.CompatibilityConformance;
import org.openimmunizationsoftware.dqa.tr.profile.CompatibilityConformanceCount;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileManager;

public class UCConflicts extends PentagonBoxHelper
{
  public UCConflicts(PentagonBox pentagonBox, PentagonRowHelper pentagonRowHelper) {
    super(pentagonBox, pentagonRowHelper);
  }

  @Override
  public void printOverview(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">A conformant IIS interface must not conflict with the national guide. A conformant HL7 interface "
        + "may define certain constraints on the national guide, however it can not loosen R or RE requirements, nor can it implement X requirements. "
        + "It can either leave them them the same or further constrain them. This section further differentiates between Minor Conflicts and Major Conflicts with the "
        + "latter being more serious in nature. </p>");
    out.println("<p class=\"pentagon\">The section compares the documented usage according to your IG against the detected usage discovered through testing with your interface.</p> ");
    printDiscoveryProcessForConstraintOrConflict(out);
  }

  @Override
  public void printDetails(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    List<CompatibilityConformanceCount> compatibilityConformanceCountList = getCompatibilityConformanceCountList(pentagonReport.getTestConducted(),
        dataSession);
    CompatibilityConformanceCount cccConflict = null;
    CompatibilityConformanceCount cccMajorConflict = null;
    for (CompatibilityConformanceCount compatibilityConformanceCount : compatibilityConformanceCountList)
    {
      if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.CONFLICT)
      {
        cccConflict = compatibilityConformanceCount;
      } else if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.MAJOR_CONFLICT)
      {
        cccMajorConflict = compatibilityConformanceCount;
      }
    }
    if (cccConflict == null && cccMajorConflict == null)
    {
      out.println("<h4 class=\"pentagon\">No Conflicts Found</h4>");
      out.println("<p class=\"pentagon\">IIS interface does not appear to be in conflict with national standards. </p>");
    } else
    {
      if (cccMajorConflict != null)
      {
        out.println("<h4 class=\"pentagon\">Major Conflicts Found</h4>");
        out.println("<p class=\"pentagon\">A critical review needs to be conducted to see if this IIS conforms to the national specification. </p>");
      } else
      {
        out.println("<h4 class=\"pentagon\">Minor Conflicts Found</h4>");
        out.println("<p class=\"pentagon\">A review needs to be conducted to verify that IIS conforms to the national specification. </p>");
      }
      out.println("<table class=\"pentagon\">");
      if (cccMajorConflict != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Major Conflicts</th>");
        out.println("    <td class=\"pentagon\">" + cccMajorConflict.getCount() + "</td>");
        out.println("  </tr>");
      }
      if (cccConflict != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Minor Conflicts</th>");
        out.println("    <td class=\"pentagon\">" + cccConflict.getCount() + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
      printCCCTable(out, dataSession, pentagonReport.getTestConducted(), cccMajorConflict, "Major Conflicts", userSession);
      printCCCTable(out, dataSession, pentagonReport.getTestConducted(), cccConflict, "Minor Conflicts", userSession);
    }

  }

  public void printCCCTable(PrintWriter out, Session dataSession, TestConducted testConducted, CompatibilityConformanceCount ccc, String label, UserSession userSession)
  {
    if (ccc != null)
    {
      ProfileUsage profileUsageBase = ProfileUsage.getBaseProfileUsage(dataSession);
      out.println("<h3 class=\"pentagon\">" + label + "</h3>");
      Query query = dataSession.createQuery(
          "from TestProfile where testSection.testConducted = ? and testSection.testSectionType = ? and compatibilityConformanceString = ?");
      query.setParameter(0, testConducted);
      query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING);
      query.setParameter(2, ccc.getCompatibilityConformance().toString());
      @SuppressWarnings("unchecked")
      List<TestProfile> testProfileList = query.list();
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Field</th>");
      out.println("    <th class=\"pentagon\">Description</th>");
      out.println("    <th class=\"pentagon\">CDC Usage</th>");
      out.println("    <th class=\"pentagon\">IIS Usage</th>");
      out.println("    <th class=\"pentagon\">Present Test Case</th>");
      out.println("    <th class=\"pentagon\">Absent Test Case</th>");
      out.println("  </tr>");
      for (TestProfile testProfile : testProfileList)
      {
        ProfileUsageValue profileUsageValueBase = ProfileManager.getProfileUsageValue(dataSession, profileUsageBase, testProfile.getProfileField());
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\">" + testProfile.getProfileField().getFieldName() + "</td>");
        out.println("    <td class=\"pentagon\">" + testProfile.getProfileField().getDescription() + "</td>");
        out.println("    <td class=\"pentagon\">" + (profileUsageValueBase == null ? "" : profileUsageValueBase.getUsage()) + "</td>");
        out.println("    <td class=\"pentagon\">" + testProfile.getUsageDetected() + "</td>");
        out.println("    <td class=\"pentagon\">" + createLink(testProfile.getTestMessagePresent(), userSession) + "</td>");
        out.println("    <td class=\"pentagon\">" + createLink(testProfile.getTestMessageAbsent(), userSession) + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public static String createLink(TestMessage testMessage, UserSession userSession)
  {
    if (testMessage.getResultStatus().equals("PASS"))
    {
      if (testMessage.isResultAccepted())
      {
        return PentagonBoxHelper.createAjaxLink(testMessage, "Accepted", userSession);
      } else
      {
        return PentagonBoxHelper.createAjaxLink(testMessage, "Rejected as Expected", userSession);
      }
    } else
    {
      if (testMessage.isResultAccepted())
      {
        return PentagonBoxHelper.createAjaxLink(testMessage, "Accepted, Not Expected", userSession);
      } else
      {
        return PentagonBoxHelper.createAjaxLink(testMessage, "Rejected, Not Expected", userSession);
      }
    }
  }

  @Override
  public void printCalculation(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is first set at 100% and then score is successively reduced by 20% for "
        + "each Major Conflict and then by 5% for each Minor Conflict. As the score diminishes the absolute size of each reduction "
        + "is likewise reduced, the first Major Conflict then has the largest negative impact on the score, with each "
        + "additional conflict reducing the score by less. </p>");
  }

  @Override
  public void calculateScore(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();
    TestConducted testConducted = pentagonReport.getTestConducted();
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING) != null)
    {
      List<CompatibilityConformanceCount> compatibilityConformanceCountList = getCompatibilityConformanceCountList(testConducted, dataSession);
      CompatibilityConformanceCount cccConflict = null;
      CompatibilityConformanceCount cccMajorConflict = null;
      for (CompatibilityConformanceCount compatibilityConformanceCount : compatibilityConformanceCountList)
      {
        if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.CONFLICT)
        {
          cccConflict = compatibilityConformanceCount;
        } else if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.MAJOR_CONFLICT)
        {
          cccMajorConflict = compatibilityConformanceCount;
        }
      }
      double scoreDouble = 100.0;
      if (cccMajorConflict != null)
      {
        for (int i = 0; i < cccMajorConflict.getCount(); i++)
        {
          scoreDouble = scoreDouble * 0.80;
        }
      }
      if (cccConflict != null)
      {
        for (int i = 0; i < cccConflict.getCount(); i++)
        {
          scoreDouble = scoreDouble * 0.95;
        }
      }
      pentagonBox.setReportScore(((int) scoreDouble));
    }
  }

  public List<CompatibilityConformanceCount> getCompatibilityConformanceCountList(TestConducted testConducted, Session dataSession)
  {
    List<CompatibilityConformanceCount> compatibilityConformanceCountList = new ArrayList<CompatibilityConformanceCount>();
    Query query = dataSession.createQuery("select tp.compatibilityConformanceString, count(tp.compatibilityConformanceString) "
        + "from TestProfile tp where tp.testSection.testConducted = ? and tp.testSection.testSectionType = ? "
        + "group by tp.compatibilityConformanceString");
    query.setParameter(0, testConducted);
    query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING);
    @SuppressWarnings("unchecked")
    List<Object[]> objectsList = query.list();
    for (Object[] objects : objectsList)
    {
      CompatibilityConformanceCount compatibilityConformanceCount = new CompatibilityConformanceCount();
      compatibilityConformanceCount.setCompatibilityConformance(CompatibilityConformance.readCompatibilityConformance((String) objects[0]));
      Long l = (Long) objects[1];
      compatibilityConformanceCount.setCount(l);
      compatibilityConformanceCountList.add(compatibilityConformanceCount);
    }
    return compatibilityConformanceCountList;
  }

  @Override
  public void printImprove(PrintWriter out, Session dataSession, PentagonReport pentagonReport, HttpSession webSession, UserSession userSession)
  {
    // TODO Auto-generated method stub

  }
}
