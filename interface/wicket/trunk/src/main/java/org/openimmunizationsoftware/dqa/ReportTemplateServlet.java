package org.openimmunizationsoftware.dqa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.PotentialIssueStatus;
import org.openimmunizationsoftware.dqa.db.model.ReportTemplate;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.quality.model.ModelFactory;
import org.openimmunizationsoftware.dqa.quality.model.ModelForm;

public class ReportTemplateServlet extends HttpServlet
{

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    String templateId = req.getParameter("templateId");
    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    PrintWriter out = new PrintWriter(resp.getOutputStream());
    resp.setContentType("text/html");
    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>DQA Config</title>");
    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
    out.println("  </head>");
    out.println("  <body>");

    if (templateId != null)
    {
      ReportTemplate reportTemplate = (ReportTemplate) session.get(ReportTemplate.class, Integer.parseInt(templateId));
      SubmitterProfile baseProfile = reportTemplate.getBaseProfile();
      out.println("      <h2>Report Template</h2>");
      out.println("      <table>");
      out.println("        <tr>");
      out.println("          <th>Label</th>");
      out.println("          <td>" + reportTemplate.getTemplateLabel() + "</td>");
      out.println("        </tr>");
      out.println("        <tr>");
      out.println("          <th>Id</th>");
      out.println("          <td>" + reportTemplate.getTemplateId() + "</td>");
      out.println("        </tr>");
      out.println("        <tr>");
      out.println("          <th>Report Type</th>");
      out.println("          <td>" + reportTemplate.getReportType().getReportTypeLabel() + "</td>");
      out.println("        </tr>");
      out.println("        <tr>");
      out.println("          <th>Base Profile</th>");
      out.println("          <td>" + baseProfile.getProfileLabel() + "</td>");
      out.println("        </tr>");
      out.println("      </table>");
      if (reportTemplate.getReportDefinition() != null)
      {
        out.println("      <h2>Report Definition</h2>");
        ModelForm modelForm = ModelFactory.createModelForm(baseProfile);
        modelForm.getModelSection("completeness.vaccineGroup.expected");
      }

      out.println("      <h2>Potential Issue Status</h2>");

      Query query = session
          .createQuery("from PotentialIssueStatus where profile = ? order by issue.targetObject, issue.targetField, issue.issueType, issue.fieldValue");
      query.setParameter(0, reportTemplate.getBaseProfile());
      List<PotentialIssueStatus> potentialIssueStatusList = query.list();
      PotentialIssueStatus lastPotentialIssueStatus = null;

      for (PotentialIssueStatus potentialIssueStatus : potentialIssueStatusList)
      {

        if (lastPotentialIssueStatus == null
            || !lastPotentialIssueStatus.getIssue().getTargetObject().equals(potentialIssueStatus.getIssue().getTargetObject()))

        {
          if (lastPotentialIssueStatus != null)
          {
            out.println("    </table>");
          }
          out.println("<h4>" + potentialIssueStatus.getIssue().getTargetObject() + "</h4>");
          out.println("    <table width=\"500\">");
          out.println("<tr><td width=\"60%\" bgcolor=\"#DDDDDD\">");
          out.println(potentialIssueStatus.getIssue().getTargetObject());
          out.println(potentialIssueStatus.getIssue().getTargetField());
          if (potentialIssueStatus.getIssue().getHl7Reference() != null && !potentialIssueStatus.getIssue().getHl7Reference().equals(""))
          {
            out.println("(" + potentialIssueStatus.getIssue().getHl7Reference() + ")");
          }
          out.println("</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td></tr>");
        } else if (!lastPotentialIssueStatus.getIssue().getTargetField().equals(potentialIssueStatus.getIssue().getTargetField()))
        {
          out.println("<tr><td width=\"60%\" bgcolor=\"#DDDDDD\">");
          out.println(potentialIssueStatus.getIssue().getTargetObject());
          out.println(potentialIssueStatus.getIssue().getTargetField());
          if (potentialIssueStatus.getIssue().getHl7Reference() != null && !potentialIssueStatus.getIssue().getHl7Reference().equals(""))
          {
            out.println("(" + potentialIssueStatus.getIssue().getHl7Reference() + ")");
          }
          out.println("</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td><td width=\"10%\" bgcolor=\"#DDDDDD\">&nbsp;</td></tr>");
        }
        out.println("<tr><td width=\"60%\">&nbsp;&nbsp;- " + potentialIssueStatus.getIssue().getIssueType() + " "
            + potentialIssueStatus.getIssue().getFieldValue() + "</td>");
        if (potentialIssueStatus.getAction().isError())
        {
          out.println("<td width=\"10%\" bgcolor=\"#FFFF33\">" + potentialIssueStatus.getAction().getActionLabel() + "</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
        } else if (potentialIssueStatus.getAction().isWarn())
        {
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\" bgcolor=\"#FFFF33\">" + potentialIssueStatus.getAction().getActionLabel() + "</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
        } else if (potentialIssueStatus.getAction().isSkip())
        {
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\" bgcolor=\"#FFFF33\">" + potentialIssueStatus.getAction().getActionLabel() + "</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
        } else if (potentialIssueStatus.getAction().isAccept())
        {
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\">&nbsp;</td>");
          out.println("<td width=\"10%\" bgcolor=\"#FFFF33\">" + potentialIssueStatus.getAction().getActionLabel() + "</td>");
        }
        out.println("</tr>");

        lastPotentialIssueStatus = potentialIssueStatus;
      }
      out.println("    </table>");
    } else
    {
      out.println("    <h1>Report Templates</h1>");
      out.println("    <table width=\"700\">");
      out.println("      <tr>");
      out.println("        <th>Template</th>");
      out.println("        <th>Type</th>");
      out.println("        <th>Profile</th>");
      out.println("      </tr>");
      Query query = session.createQuery("from ReportTemplate order by templateLabel");
      List<ReportTemplate> reportTemplateList = query.list();
      for (ReportTemplate reportTemplate : reportTemplateList)
      {
        String link = "reportTemplate?templateId=" + reportTemplate.getTemplateId();
        out.println("      <tr>");
        out.println("        <td><a href=\"" + link + "\">" + reportTemplate.getTemplateLabel() + "</a></td>");
        out.println("        <td><a href=\"" + link + "\">" + reportTemplate.getReportType().getReportTypeLabel() + "</a></td>");
        out.println("        <td><a href=\"" + link + "\">" + reportTemplate.getBaseProfile().getProfileLabel() + "</a></td>");
        out.println("      </tr>");
      }
      out.println("    </table>");

    }
    out.println("    <hr>");
    out.println("    <p>Version " + SoftwareVersion.VERSION + "</p>");
    out.println("  </body>");
    out.println("</html>");
    out.close();
    session.flush();
    session.close();
  }
}
