package org.openimmunizationsoftware.dqa.web;

import java.util.Locale;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.openimmunizationsoftware.dqa.db.model.UserAccount;

public class DqaSession extends WebSession
{
  public static DqaSession get()
  {
    return (DqaSession) Session.get();
  }
  private UserAccount userAccount = null;
  
  public DqaSession(Request request)
  {
    super(request);
    setLocale(Locale.ENGLISH);
  }
  
  public synchronized UserAccount getUserAccount()
  {
    return userAccount;
  }
  
  public synchronized void setUserAccount(UserAccount userAccount)
  {
    this.userAccount = userAccount;
    dirty();
  }
}
