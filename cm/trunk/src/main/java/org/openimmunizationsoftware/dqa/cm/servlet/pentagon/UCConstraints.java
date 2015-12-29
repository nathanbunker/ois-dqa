package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;
import org.openimmunizationsoftware.dqa.tr.profile.CompatibilityConformance;
import org.openimmunizationsoftware.dqa.tr.profile.CompatibilityConformanceCount;

public class UCConstraints extends UCConflicts
{
  public UCConstraints() {
    super(BOX_NAME_UC_CONSTRAINTS);
  }

  @Override
  public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    out.println(
        "<p class=\"pentagon\">A conformant IIS may constrain or modify the national guide in certain ways in order to apply local requirements. "
            + "These legal changes are called constraints, and are restricted to only those changes that allow a system conformant to the local "
            + "constraints to also be conformant to the national specification. However these local constraints can have an impact on interoperability if "
            + "systems designed to only meet the national standard are not prepared to meet the local standard. These contstraints should be reviewed carefully "
            + "to determine the business need in balance with the need for interoperability. </p>");
  }

  @Override
  public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
  {
    List<CompatibilityConformanceCount> compatibilityConformanceCountList = getCompatibilityConformanceCountList(testConducted, dataSession);
    CompatibilityConformanceCount cccConstraint = null;
    CompatibilityConformanceCount cccMajorConstraint = null;
    for (CompatibilityConformanceCount compatibilityConformanceCount : compatibilityConformanceCountList)
    {
      if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.CONSTRAINT)
      {
        cccConstraint = compatibilityConformanceCount;
      } else if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.MAJOR_CONSTRAINT)
      {
        cccMajorConstraint = compatibilityConformanceCount;
      }
    }
    if (cccConstraint == null && cccMajorConstraint == null)
    {
      out.println("<h3 class=\"pentagon\">No Constraints Found</h3>");
      out.println("<p class=\"pentagon\">IIS interface does not appear to constrain the national standard. </p>");
    } else
    {
      if (cccMajorConstraint != null)
      {
        out.println("<h3 class=\"pentagon\">Major Constraints Found</h3>");
        out.println(
            "<p class=\"pentagon\">A review needs to be conducted of constraints to verify business need in balance with interoperability need. </p>");
      } else
      {
        out.println("<h3 class=\"pentagon\">Constraints Found</h3>");
        out.println("<p class=\"pentagon\">A review may be conducted to verify business need in balance with interoperability need. </p>");
      }
      out.println("<table class=\"pentagon\">");
      if (cccMajorConstraint != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Major Contraints</th>");
        out.println("    <td class=\"pentagon\">" + cccMajorConstraint.getCount() + "</td>");
        out.println("  </tr>");
      }
      if (cccConstraint != null)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <th class=\"pentagon\">Contstraints</th>");
        out.println("    <td class=\"pentagon\">" + cccConstraint.getCount() + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
      printCCCTable(out, dataSession, testConducted, cccMajorConstraint, "Major Constraint");
      printCCCTable(out, dataSession, testConducted, cccConstraint, "Constraint");
    }

  }

  @Override
  public void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession)
  {
    out.println("<p class=\"pentagon\">The score is first set at 100% and then score is successively reduced by 20% for "
        + "each Major Constraint and then by 5% for each Constraint. As the score diminishes the absolute size of each reduction "
        + "is likewise reduced, the first Major Constraint then has the largest negative impact on the score, with each "
        + "additional constraints reducing the score by less. </p>");
    out.println("<h4 class=\"pentagon\">How To Improve Score</h4>");
    out.println("<p class=\"pentagon\"></p>");

  }

  @Override
  public void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport, Map<String, TestSection> testSectionMap)
  {
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING) != null)
    {
      List<CompatibilityConformanceCount> compatibilityConformanceCountList = getCompatibilityConformanceCountList(testConducted, dataSession);
      CompatibilityConformanceCount cccConstraint = null;
      CompatibilityConformanceCount cccMajorConstraint = null;
      for (CompatibilityConformanceCount compatibilityConformanceCount : compatibilityConformanceCountList)
      {
        if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.CONSTRAINT)
        {
          cccConstraint = compatibilityConformanceCount;
        } else if (compatibilityConformanceCount.getCompatibilityConformance() == CompatibilityConformance.MAJOR_CONSTRAINT)
        {
          cccMajorConstraint = compatibilityConformanceCount;
        }
      }
      double scoreDouble = 100.0;
      if (cccMajorConstraint != null)
      {
        for (int i = 0; i < cccMajorConstraint.getCount(); i++)
        {
          scoreDouble = scoreDouble * 0.80;
        }
      }
      if (cccConstraint != null)
      {
        for (int i = 0; i < cccConstraint.getCount(); i++)
        {
          scoreDouble = scoreDouble * 0.95;
        }
      }
      pentagonReport.setScoreUCConstraints(((int) scoreDouble));
    }

  }

}
