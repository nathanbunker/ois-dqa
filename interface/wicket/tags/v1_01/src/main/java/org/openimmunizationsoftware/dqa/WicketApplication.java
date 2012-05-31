package org.openimmunizationsoftware.dqa;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.openimmunizationsoftware.dqa.web.AuthStrategy;
import org.openimmunizationsoftware.dqa.web.DqaSession;
import org.openimmunizationsoftware.dqa.web.HomePage;
import org.openimmunizationsoftware.dqa.web.LoginPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see org.openimmunizationsoftware.dqa.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
  
  protected void init() {
    super.init();
    getSecuritySettings().setAuthorizationStrategy(new AuthStrategy());
    getApplicationSettings().setAccessDeniedPage(LoginPage.class);
  }
  
  
  
  /**
   * Constructor
   */
  public WicketApplication() {
  }

  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  public Class<HomePage> getHomePage()
  {
    return HomePage.class;
  }

  @Override
  public Session newSession(Request request, Response response)
  {
    return new DqaSession(request);
    
  }

}