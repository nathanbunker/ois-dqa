package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.Application;

public class AartServlet extends HomeServlet
{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    try
    {
      Application defaultApplication = (Application) dataSession.get(Application.class, 2);
      createHeader(webSession, defaultApplication);
      out.println("<div class=\"leftColumn\">");
      out.println("<div class=\"topBox\">");
      printLogin(userSession.getUser(), userSession);
      out.println("</div>");
      out.println("</div>");
    } catch (Exception e)
    {
      e.printStackTrace();
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }
    createFooter(webSession);
  }

}
