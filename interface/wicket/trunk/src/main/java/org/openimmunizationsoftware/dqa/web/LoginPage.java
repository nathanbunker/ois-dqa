package org.openimmunizationsoftware.dqa.web;


import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.UserAccount;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.web.profile.ProfilePage;

/**
 * Homepage
 */
public class LoginPage extends DqaBasePage
{

  private static final long serialVersionUID = 1L;
  private Model<Integer> model = null;
  // TODO Add any page properties or variables here

  /**
   * Constructor that is invoked when page is invoked without a session.
   * 
   * @param parameters
   *          Page parameters
   */
  public LoginPage(final PageParameters parameters) {
    super(parameters, NavigationPanel.LOGIN);

    add(new LoginForm("loginForm"));

  }
  
  public class LoginForm extends Form<ValueMap>
  {
    public LoginForm(final String id)
    {
      super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
      setMarkupId("loginForm");
      add(new TextArea<String>("username").setType(String.class));
      add(new TextArea<String>("password").setType(String.class));
    }
    private String username = "";
    private String password = "";
    public String getUsername()
    {
      return username;
    }
    public void setUsername(String username)
    {
      this.username = username;
    }
    public String getPassword()
    {
      return password;
    }
    public void setPassword(String password)
    {
      this.password = password;
    }
    
    @Override
    protected void onSubmit()
    {
      DqaSession webSession = (DqaSession) getSession();
      ValueMap values = getModelObject();

      username = (String) values.get("username");
      password = (String) values.get("password");

      Session session = webSession.getDataSession();
      Transaction tx = session.beginTransaction();
      Query query = session.createQuery("from UserAccount where username = ?");
      query.setString(0, username);
      List<UserAccount> userAccountList = query.list();
      if (userAccountList.size() > 0 && userAccountList.get(0).getPassword().equalsIgnoreCase(password))
      {
          webSession.setUsername(username);
          UserAccount userAccount = userAccountList.get(0);
          webSession.setAdmin(userAccount.getAccountType().equals(UserAccount.ACCOUNT_TYPE_ADMIN));
      }
      else
      {
        webSession.setUsername(null);
      }
      tx.commit();
      setResponsePage(new ProfilePage());
    }
    
  }
  
}
