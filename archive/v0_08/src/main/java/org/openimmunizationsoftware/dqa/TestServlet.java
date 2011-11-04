package org.openimmunizationsoftware.dqa;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openimmunizationsoftware.dqa.manager.WeeklyBatchManager;
import org.openimmunizationsoftware.dqa.manager.WeeklyExportManager;

public class TestServlet extends HttpServlet
{
  private String logText = "";
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    String action = req.getParameter("action");
    if (action != null)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      if (action.equals("Generate Weekly Batch"))
      {
        try
        {
          WeeklyBatchManager.getWeeklyBatchManager().runNow(sdf.parse(req.getParameter("generateDate")));
          logText = "Generated ";
        } catch (ParseException pe)
        {
          throw new ServletException(pe);
        }
      } else if (action.equals("Export Weekly Batch"))
      {
        try
        {
          WeeklyExportManager.getWeeklyExportManager().runNow(sdf.parse(req.getParameter("exportDate")));
        } catch (ParseException pe)
        {
          throw new ServletException(pe);
        }
      }
    }
    String generateDate = req.getParameter("generateDate");
    if (generateDate == null)
    {
      generateDate = "";
    }
    String exportDate = req.getParameter("exportDate");
    if (exportDate == null)
    {
      exportDate = "";
    }
    PrintWriter out = new PrintWriter(resp.getOutputStream());
    out.println("<html>");
    out.println("  <head>");
    out.println("    <title>DQA Software Test</title>");
    out.println("  </head>");
    out.println("  <body>");
    out.println("    <h1>DQA Software Test</h1>");
    out.println("    <h2>Generate Weekly Batch</h2>");
    out.println("    <form action=\"test\" method=\"POST\">");
    out.println("    Generate Date <input type=\"text\" name=\"generateDate\" value=\"" + generateDate + "\"/><br>");
    out.println("    <input type=\"submit\" name=\"action\" value=\"Generate Weekly Batch\"/>");
    out.println("    </form>");
    if (!generateDate.equals(""))
    {
      out.println("<pre>");
      out.println(IncomingServlet.weeklyBatchManager.getInternalLog().toString());
      out.println("</pre>");
    }
    out.println("    <h2>Export Weekly Batch</h2>");
    out.println("    <form action=\"test\">");
    out.println("    Export Date <input type=\"text\" name=\"exportDate\" value=\"" + exportDate + "\"/><br>");
    out.println("    <input type=\"submit\" name=\"action\" value=\"Export Weekly Batch\"/>");
    out.println("    </form>");
    if (!exportDate.equals(""))
    {
      out.println("<pre>");
      out.println(IncomingServlet.weeklyExportManager.getInternalLog().toString());
      out.println("</pre>");
    }
    out.println("  </body>");
    out.println("</html>");
    out.close();
  }
}
