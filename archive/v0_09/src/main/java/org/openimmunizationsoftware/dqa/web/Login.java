package org.openimmunizationsoftware.dqa.web;


import java.awt.TextField;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;

/**
 * Homepage
 */
public class Login extends WebPage
{

  private static final long serialVersionUID = 1L;

  // TODO Add any page properties or variables here

  /**
   * Constructor that is invoked when page is invoked without a session.
   * 
   * @param parameters
   *          Page parameters
   */
  public Login(final PageParameters parameters) {
    Form form = new Form("login")
    {
      @Override
      protected void onSubmit() {
        SessionFactory factory = OrganizationManager.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.createQuery("from userAccount where username = ?");
        
        Organization organization = (Organization) session.get(Organization.class, 1);
        SubmitterProfile submitterProfile = organization.getPrimaryProfile();
        // Add the simplest type of label
        
        
        session.close();
        
      };
    };
    add(form);

    
  }
}
