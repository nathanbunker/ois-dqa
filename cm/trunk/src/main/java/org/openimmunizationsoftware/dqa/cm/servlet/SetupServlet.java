package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.logic.thread.SetupThread;
import org.openimmunizationsoftware.dqa.cm.model.User;

@SuppressWarnings("serial")
public class SetupServlet extends BaseServlet
{
  public SetupServlet() {
    super("Setup Servlet");
  }

  private static SetupThread setupLogic = null;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    PrintWriter out = userSession.getOut();
    if (!userSession.isAdmin())
    {
      sendToHome(req, resp);
      return;
    }
    try
    {
      createHeader(webSession);
      out.println("<p>This page is temporary and runs logic to setup the database intially. This will be removed once the application is"
          + " up and running without issues. </p>");
      if (setupLogic == null || setupLogic.isComplete())
      {
        out.println("<form method=\"POST\" action=\"setup\">");
        out.println("<input type=\"submit\" name=\"action\" value=\"start\">");
        out.println("</form>");
      }
      if (setupLogic != null)
      {
        out.println("<h1>Setup Log</h1>");
        setupLogic.printOutput(out);
      }
    } catch (Exception e)
    {
      handleError(e, webSession);
    } finally
    {
      createFooter(webSession);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    if (!userSession.isAdmin())
    {
      sendToHome(req, resp);
      return;
    }
    User user = UserLogic.getUserWithUsername(UserLogic.DQA_INITIAL, dataSession);
    setupLogic = new SetupThread(user);
    setupLogic.start();
    doGet(req, resp);
  }
}
