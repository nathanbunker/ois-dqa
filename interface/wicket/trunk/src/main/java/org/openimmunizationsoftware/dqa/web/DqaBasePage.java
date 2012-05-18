package org.openimmunizationsoftware.dqa.web;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

public class DqaBasePage extends WebPage
{
  public DqaBasePage() {
    this(new PageParameters(), NavigationPanel.HOME_LOGGED_IN);
  }

  public DqaBasePage(final PageParameters parameters, NavigationPanel.MenuLink menuLinkSelected) {
    add(new NavigationPanel("navigationPanel", menuLinkSelected));
  }
}
