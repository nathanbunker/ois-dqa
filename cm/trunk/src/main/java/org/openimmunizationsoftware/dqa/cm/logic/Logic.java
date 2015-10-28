package org.openimmunizationsoftware.dqa.cm.logic;

import java.io.PrintWriter;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;

public class Logic
{
  protected PrintWriter out;
  protected UserSession userSession;
  protected Session dataSession;

  protected void setMessageError(String messageError)
  {
    userSession.setMessageError(messageError);
  }

  protected void setMessageConfirmation(String messageConfirmation)
  {
    userSession.setMessageConfirmation(messageConfirmation);
  }

  public Logic(UserSession userSession) {
    this.userSession = userSession;
    this.out = userSession.getOut();
    this.dataSession = userSession.getDataSession();
  }
}
