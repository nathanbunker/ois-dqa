/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.openimmunizationsoftware.dqa.cm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.openimmunizationsoftware.dqa.cm.logic.UserLogic;
import org.openimmunizationsoftware.dqa.cm.logic.thread.UpdateCountThread;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class CentralControl  {
	  
	  private static SessionFactory factory;
	  
	  public static synchronized SessionFactory getSessionFactory()
	  {
	    if (factory == null)
	    {
	      factory = new AnnotationConfiguration().configure().buildSessionFactory();
	    }
	    return factory;
	  }
	  
	  private static UpdateCountThread updateCountThread = null;
	  
	  public static synchronized UpdateCountThread getUpdateCountThread(Session dataSession)
	  {
	    if (updateCountThread == null)
	    {
	      User user = UserLogic.getUser(UserLogic.DQACM, dataSession);
	      updateCountThread = new UpdateCountThread(user);
	      updateCountThread.start();
	    }
	    return updateCountThread;
	  }
}
