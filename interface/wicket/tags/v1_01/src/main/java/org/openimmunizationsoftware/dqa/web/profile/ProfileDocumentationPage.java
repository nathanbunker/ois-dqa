package org.openimmunizationsoftware.dqa.web.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.db.model.PotentialIssueStatus;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues.Field;
import org.openimmunizationsoftware.dqa.web.DqaBasePage;
import org.openimmunizationsoftware.dqa.web.DqaSession;
import org.openimmunizationsoftware.dqa.web.NavigationPanel;
import org.openimmunizationsoftware.dqa.web.SecurePage;

public class ProfileDocumentationPage extends DqaBasePage implements SecurePage
{

  public ProfileDocumentationPage() {
    this(new PageParameters());
  }

  public ProfileDocumentationPage(final PageParameters parameters) {
    super(parameters, NavigationPanel.PROFILE);

    final DqaSession webSession = (DqaSession) getSession();
    final Session dataSession = webSession.getDataSession();
    final SubmitterProfile profile = webSession.getSubmitterProfile();

    final PotentialIssues potentialIssues = PotentialIssues.getPotentialIssues();

    List<Field> fieldList = potentialIssues.getAllFields();
    Collections.sort(fieldList);

    Query query = dataSession.createQuery("from PotentialIssueStatus where profile = ?");
    query.setParameter(0, profile);
    List<PotentialIssueStatus> potentialIssueStatusList = query.list();
    final Map<PotentialIssue, PotentialIssueStatus> potentialIssueStatusMap = new HashMap<PotentialIssue, PotentialIssueStatus>();
    for (PotentialIssueStatus potentialIssueStatus : potentialIssueStatusList)
    {
      potentialIssueStatusMap.put(potentialIssueStatus.getIssue(), potentialIssueStatus);
    }
    
    ListView<Field> documentationItems = new ListView<Field>("documentationItems", fieldList) {
      @Override
      protected void populateItem(ListItem<Field> item)
      {
        Field field = item.getModelObject();
        item.add(new Label("documentationItemFieldTitle", field.toString()));
        item.add(new Label("documentationItemText", potentialIssues.getDocumentation(field, potentialIssueStatusMap)).setEscapeModelStrings(false));
      }
    };
    add(documentationItems);
  }
}