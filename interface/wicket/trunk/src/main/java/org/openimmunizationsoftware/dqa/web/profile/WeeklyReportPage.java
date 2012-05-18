package org.openimmunizationsoftware.dqa.web.profile;

import org.apache.wicket.PageParameters;
import org.openimmunizationsoftware.dqa.web.DqaBasePage;
import org.openimmunizationsoftware.dqa.web.NavigationPanel;
import org.openimmunizationsoftware.dqa.web.SecurePage;

public class WeeklyReportPage extends DqaBasePage  implements SecurePage
{

  public WeeklyReportPage(final PageParameters parameters) {
    super(parameters, NavigationPanel.PROFILE);
  }
}
