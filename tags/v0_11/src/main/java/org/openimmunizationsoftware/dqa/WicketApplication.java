package org.openimmunizationsoftware.dqa;

import org.apache.wicket.protocol.http.WebApplication;
import org.openimmunizationsoftware.dqa.web.Login;
import org.openimmunizationsoftware.dqa.web.Submit;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.openimmunizationsoftware.dqa.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<Login> getHomePage()
	{
		return Login.class;
	}

}
