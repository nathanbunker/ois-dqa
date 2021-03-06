/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.immunizationsoftware.dqa.tester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.immunizationsoftware.dqa.tester.connectors.Connector;

/**
 * 
 * @author nathan
 */
public class SubmitServlet extends ClientServlet
{

  protected static Connector getConnector(int id, HttpSession session) throws ServletException
  {
    List<Connector> connectors = SetupServlet.getConnectors(session);
    id--;
    if (id < connectors.size())
    {
      return connectors.get(id);
    }
    throw new IllegalArgumentException("Unable to find connection " + id);
  }

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   * 
   * @param request
   *          servlet request
   * @param response
   *          servlet response
   * @throws ServletException
   *           if a servlet-specific error occurs
   * @throws IOException
   *           if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    HttpSession session = request.getSession(true);
    String username = (String) session.getAttribute("username");
    if (username == null)
    {
      response.sendRedirect(Authenticate.APP_DEFAULT_HOME);
    } else
    {
      // For example purposes, determine what method to perform based on
      // a "method" request parameter in the URL.
      int id = 0;
      if (request.getParameter("id") != null)
      {
        id = Integer.parseInt(request.getParameter("id"));
      }
      String str = request.getParameter("method");
      if (str == null || !str.equalsIgnoreCase("Submit") || id == 0)
      {
        return;
      }
      boolean debug = request.getParameter("debug") != null;
      Connector connector = getConnector(id, session);
      String message = request.getParameter("message");
      String responseText = connector.submitMessage(cleanMessage(message), debug);
      request.setAttribute("responseText", responseText);
    }
  }

  // <editor-fold defaultstate="collapsed"
  // desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   * 
   * @param request
   *          servlet request
   * @param response
   *          servlet response
   * @throws ServletException
   *           if a servlet-specific error occurs
   * @throws IOException
   *           if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession(true);
    String username = (String) session.getAttribute("username");
    if (username == null)
    {
      response.sendRedirect(Authenticate.APP_DEFAULT_HOME);
    } else
    {
      int id = 0;
      List<Connector> connectors = SetupServlet.getConnectors(session);
      if (connectors.size() == 1)
      {
        id = 1;
      } else
      {
        if (request.getParameter("id") != null && !request.getParameter("id").equals(""))
        {
          id = Integer.parseInt(request.getParameter("id"));
        } else if (session.getAttribute("id") != null)
        {
          id = (Integer) session.getAttribute("id");
        }
      }

      String userId = request.getParameter("userid");
      String password = request.getParameter("password");
      String facilityId = request.getParameter("facilityid");
      String message = request.getParameter("message");
      if (userId == null)
      {
        userId = (String) session.getAttribute("userId");
        if (userId == null)
        {
          userId = "";
        }
      }
      if (password == null)
      {
        password = (String) session.getAttribute("password");
        if (password == null)
        {
          password = "";
        }
      }
      if (facilityId == null)
      {
        facilityId = (String) session.getAttribute("facilityId");
        if (facilityId == null)
        {
          facilityId = "";
        }
      }
      if (message == null)
      {
        message = (String) session.getAttribute("message");
        if (message == null)
        {
          message = "";
        }
      }
      session.setAttribute("userId", userId);
      session.setAttribute("facilityId", facilityId);
      session.setAttribute("password", password);
      session.setAttribute("id", id);
      session.setAttribute("message", message);
      PrintWriter out = new PrintWriter(response.getWriter());
      response.setContentType("text/html;charset=UTF-8");
      printHtmlHead(out, "Send Message", request);
      out.println("    <form action=\"SubmitServlet\" method=\"POST\">");
      out.println("      <table border=\"0\">");
      out.println("        <tr>");
      out.println("          <td>Connection</td>");
      out.println("          <td>");
      if (connectors.size() == 1)
      {
        out.println("            " + connectors.get(0).getLabelDisplay());
        out.println("            <input type=\"hidden\" name=\"id\" value=\"1\"/>");
      } else
      {
        out.println("            <select name=\"id\">");
        out.println("              <option value=\"\">select</option>");
        int i = 0;
        for (Connector connector : connectors)
        {
          i++;
          if (id == i)
          {
            out.println("              <option value=\"" + i + "\" selected=\"true\">" + connector.getLabelDisplay() + "</option>");
          } else
          {
            out.println("              <option value=\"" + i + "\">" + connector.getLabelDisplay() + "</option>");
          }
        }
        out.println("            </select>");
      }
      out.println("          </td>");
      out.println("        </tr>");
      out.println("        <tr>");
      out.println("          <td valign=\"top\">Message</td>");
      out.println("          <td><textarea name=\"message\" cols=\"70\" rows=\"10\" wrap=\"off\">" + message + "</textarea></td>");
      out.println("        </tr>");
      out.println("        <tr>");
      out.println("          <td>Debug</td>");
      out.println("          <td><input type=\"checkbox\" name=\"debug\" value=\"true\" /></td>");
      out.println("        </tr>");
      out.println("        <tr>");
      out.println("          <td colspan=\"2\" align=\"right\">");
      out.println("            <input type=\"submit\" name=\"method\" value=\"Refresh\"/>");
      out.println("            <input type=\"submit\" name=\"method\" value=\"Submit\"/>");
      out.println("          </td>");
      out.println("        </tr>");
      out.println("      </table>");
      out.println("    </form>");
      if (id != 0)
      {
        try
        {
          Connector connector = getConnector(id, session);
          String responseText = (String) request.getAttribute("responseText");
          if (responseText != null)
          {
            out.println("<p>Response from " + connector.getLabel() + ": ");
            out.print("<pre>");
            out.print(responseText);
            out.println("</pre>");
          }
          String host = "";
          try
          {
            InetAddress addr = InetAddress.getLocalHost();
            host = addr.getHostName();
          } catch (UnknownHostException e)
          {
            host = "[unknown]";
          }
          try
          {
            out.println("<p>Status for " + connector.getLabel() + ": <br><font color=\"blue\">"
                + connector.connectivityTest("Sent from client '" + host + "'") + "</font></p>");
          } catch (Exception t)
          {
            out.println("<p>Unable to test against remote server: " + t.getMessage() + "</p>");
            out.println("<pre>");
            t.printStackTrace(out);
            out.println("</pre>");
          }
        } catch (Throwable re)
        {
          re.printStackTrace(out);
        }
      }
      out.println("  <div class=\"help\">");
      out.println("  <h2>How To Use This Page</h2>");
      out.println("  <p>This page supports a simple test of the connectivity to another system. "
          + "The login parameters (username, password, and facility id) must be obtained "
          + "from the system you wish to test against. Once you have the login parameters "
          + "you should select the appropriate service and then paste a test message. "
          + "After clicking Submit you will see the results of your query. </p>");
      out.println("<p>If you wish to only ping the server, then you only need to select the service "
          + "and then click Refresh. This will give the results of the ping below. </p>");
      out.println("  </div>");
      // testTestCaseMessage(out);
      printHtmlFoot(out);
      out.close();
    }
  }

  protected void testTestCaseMessage(PrintWriter out)
  {
    TestCaseMessage tcm = new TestCaseMessage();
    tcm.setAssertResultStatus("Accept");
    tcm.setAssertResultText("Way good!");
    tcm.setComment("NAB", "Okay");
    tcm.setCustomTransformations("PID-4=HAPPY\nPID-5=SAD\n");
    tcm.setDescription("This is a description");
    tcm.setExpectedResult("This is an expected result text");
    tcm.setMessageText("MSH|\rPID|1|\rRXA|1|\rRXA|2\r");
    tcm.setOriginalMessage("MSH\rPID\rRXA\rRXR\r");
    tcm.setQuickTransformations(new String[] { "2.5.1", "BOY" });
    tcm.setTestCaseNumber("1.1");
    tcm.setTestCaseSet("CDC");
    out.print("<pre>");
    String text = tcm.createText();
    out.print(text);
    try
    {
      List<TestCaseMessage> tcmList = TestCaseMessage.createTestCaseMessageList(text);
      for (TestCaseMessage tcmIt : tcmList)
      {
        out.print(tcmIt.createText());
      }
    } catch (Exception e)
    {
      e.printStackTrace(out);
    }
    out.println("</pre>");
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   * 
   * @param request
   *          servlet request
   * @param response
   *          servlet response
   * @throws ServletException
   *           if a servlet-specific error occurs
   * @throws IOException
   *           if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    try
    {
      processRequest(request, response);
    } catch (Exception e)
    {
      request.setAttribute("responseText", e.getMessage());
    }
    doGet(request, response);
  }

  /**
   * Returns a short description of the servlet.
   * 
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo()
  {
    return "Short description";
  }// </editor-fold>

  private static String cleanMessage(String message)
  {
    if (message != null)
    {
      StringBuilder sb = new StringBuilder();
      BufferedReader reader = new BufferedReader(new StringReader(message));
      String line;
      try
      {
        while ((line = reader.readLine()) != null)
        {
          sb.append(line);
          sb.append("\r");
        }
      } catch (IOException ioe)
      {
        sb.append(ioe.getMessage());
      }
      return sb.toString();
    }

    return message;

  }
}
