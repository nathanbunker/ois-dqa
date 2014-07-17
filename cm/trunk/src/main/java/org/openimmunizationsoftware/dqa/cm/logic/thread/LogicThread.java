package org.openimmunizationsoftware.dqa.cm.logic.thread;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.cm.CentralControl;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class LogicThread extends Thread
{

  protected PrintWriter out;
  protected StringWriter stringWriter;
  protected boolean outULTagOpen = false;
  protected Session dataSession;
  protected User user;
  protected boolean complete = false;

  public boolean isComplete()
  {
    return complete;
  }

  public LogicThread(int userId) {
    SessionFactory factory = CentralControl.getSessionFactory();
    this.dataSession = factory.openSession();
    this.user = (User) dataSession.get(User.class, userId);
    stringWriter = new StringWriter();
    out = new PrintWriter(stringWriter);
  }

  public void printOutput(PrintWriter printOut)
  {
    printOut.print(stringWriter.toString());
    if (outULTagOpen)
    {
      printOut.print("</ul>");
    }
  }
  
}
