package org.openimmunizationsoftware.dqa.web;


import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.openimmunizationsoftware.dqa.db.model.UserAccount;

/**
 * Homepage
 */
public class Login extends WebPage
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
  public Login(final PageParameters parameters) {
    
    add(new LoginForm("loginForm"));

    model = new Model<Integer>()
    {
      private int counter = 0;
      public Integer getObject()
      {
        return counter++;
      }
    };
    
    add(new Label("counter", model));

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
      ValueMap values = getModelObject();

      UserAccount userAccount = new UserAccount();
      userAccount.setUsername((String) values.get("username"));
      userAccount.setPassword((String) values.get("password"));

//      SessionFactory factory = OrganizationManager.getSessionFactory();
//      Session session = factory.openSession();
//      Transaction tx = session.beginTransaction();
//      Query query = session.createQuery("from userAccount where username = ?");
//      
//      Organization organization = (Organization) session.get(Organization.class, 1);
//      SubmitterProfile submitterProfile = organization.getPrimaryProfile();
//      // Add the simplest type of label
      
//      tx.commit();
//      session.close();

      if (model != null)
      {
        model.getObject();
      }
      // Clear out the text component
      values.put("username", "works!");
      values.put("password", "");

    }
    
  }
  
  private static boolean isNotBlank(String s)
  {
    return s != null && s.length() > 0;
  }
}
