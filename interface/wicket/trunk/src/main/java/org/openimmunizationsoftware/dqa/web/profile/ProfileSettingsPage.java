package org.openimmunizationsoftware.dqa.web.profile;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.web.DqaBasePage;
import org.openimmunizationsoftware.dqa.web.DqaSession;
import org.openimmunizationsoftware.dqa.web.NavigationPanel;
import org.openimmunizationsoftware.dqa.web.SecurePage;

public class ProfileSettingsPage extends DqaBasePage implements SecurePage
{

  public ProfileSettingsPage(final PageParameters parameters) {
    super(parameters, NavigationPanel.PROFILE);

    DqaSession webSession = (DqaSession) getSession();
    SubmitterProfile submitterProfile = webSession.getSubmitterProfile();
    

    Form<Void> form = new Form<Void>("form") {
      private static final long serialVersionUID = 1L;

      @Override
      protected void onSubmit()
      {
        // TODO Auto-generated method stub

      }
    };
    add(form);
    Model<String> profileLabelModel = new Model<String>(submitterProfile.getProfileLabel());
    TextField<String> profileLabel = new TextField<String>("profileLabel", profileLabelModel);
    form.add(profileLabel);
  }
}
