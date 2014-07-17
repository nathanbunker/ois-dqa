package org.openimmunizationsoftware.dqa.cm.logic;

import java.io.PrintWriter;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.BaseServlet;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;

public class Logic
{
  protected PrintWriter out;
  protected UserSession userSession;
  protected Session dataSession;
  protected BaseServlet baseServlet;

  protected void setMessageError(String messageError)
  {
    baseServlet.setMessageError(messageError);
  }

  protected void setMessageConfirmation(String messageConfirmation)
  {
    baseServlet.setMessageConfirmation(messageConfirmation);
  }

  public Logic(BaseServlet baseServlet) {
    this.baseServlet = baseServlet;
    this.out = baseServlet.getOut();
    this.userSession = baseServlet.getUserSession();
    this.dataSession = baseServlet.getDataSession();
  }
}
