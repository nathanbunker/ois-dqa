package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.Application;

public class ApplicationLogic {
	public static List<Application> getApplications(Session dataSession) {
		Query query = dataSession.createQuery("from Application");
		return query.list();
	}
	
	public static Application getApplication(int applicationId, Session dataSession)
	{
		return (Application) dataSession.get(Application.class, applicationId);
	}
}
