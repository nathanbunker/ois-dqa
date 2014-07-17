package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openimmunizationsoftware.dqa.cm.logic.thread.SetupThread;

public class SetupServlet extends BaseServlet
{
  public SetupServlet() {
    super("Setup Servlet");
  }

  private static SetupThread setupLogic = null;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    setup(req, resp);
    if (!isAdmin())
    {
      sendToHome(req, resp);
      return;
    }
    createHeader();
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
    createFooter();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    setup(req, resp);
    if (!isAdmin())
    {
      sendToHome(req, resp);
      return;
    }
    setupLogic = new SetupThread(userSession.getUser());
    setupLogic.start();
    doGet(req, resp);
  }
}
