package org.openimmunizationsoftware.dqa.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.hibernate.property.Getter;
import org.openimmunizationsoftware.dqa.web.profile.DataReceivedPage;
import org.openimmunizationsoftware.dqa.web.profile.ErrorsWarningsPage;
import org.openimmunizationsoftware.dqa.web.profile.ProfilePage;
import org.openimmunizationsoftware.dqa.web.profile.ProfileSettingsPage;
import org.openimmunizationsoftware.dqa.web.profile.WeeklyReportPage;

public class NavigationPanel extends Panel
{
  public static class MenuLink implements Serializable
  {
    private String linkLabel = "";
    private Class pageClass = null;
    private boolean selected = false;
    private List<MenuLink> childList = new ArrayList<NavigationPanel.MenuLink>();

    public MenuLink(MenuLink menuLinkCopy) {
      this.linkLabel = menuLinkCopy.linkLabel;
      this.pageClass = menuLinkCopy.pageClass;
      childList = new ArrayList<NavigationPanel.MenuLink>(menuLinkCopy.childList);
    }

    public List<MenuLink> getChildList()
    {
      return childList;
    }

    public boolean isSelected()
    {
      return selected;
    }

    public void setSelected(boolean selected)
    {
      this.selected = selected;
    }

    public String getLinkLabel()
    {
      return linkLabel;
    }

    public void setLinkLabel(String linkLabel)
    {
      this.linkLabel = linkLabel;
    }

    public Class getPageClass()
    {
      return pageClass;
    }

    public void setPageClass(Class pageClass)
    {
      this.pageClass = pageClass;
    }

    private MenuLink(String linkLabel, Class pageClass) {
      this.linkLabel = linkLabel;
      this.pageClass = pageClass;
    }

  }

  public static MenuLink HOME_LOGGED_OUT = new MenuLink("DQA", HomePage.class);

  public static MenuLink HOME_LOGGED_IN = new MenuLink("DQA", HomePage.class);
  public static MenuLink LOGOUT = new MenuLink("Log Out", LogoutPage.class);

  public static MenuLink LOGIN = new MenuLink("Log In", LoginPage.class);

  public static MenuLink PROFILE = new MenuLink("Profile", ProfilePage.class);

  public static MenuLink PROFILE_SETTINGS = new MenuLink("Profile Settings", ProfileSettingsPage.class);
  public static MenuLink DATA_RECEIVED = new MenuLink("Data Received", DataReceivedPage.class);
  public static MenuLink ERRORS_WARNINGS = new MenuLink("Errors & Warnings", ErrorsWarningsPage.class);
  public static MenuLink WEEKLY_REPORT = new MenuLink("Weekly Report", WeeklyReportPage.class);
  public static MenuLink PERSONAL_SETTINGS = new MenuLink("Personal Settings", PersonalSettingsPage.class);

  public static MenuLink ADMIN = new MenuLink("ADMIN", HomePage.class);

  private List<MenuLink> createMenu(DqaSession webSession)
  {
    List<MenuLink> menuLinkList = new ArrayList<NavigationPanel.MenuLink>();
    if (webSession.isLoggedIn())
    {
      MenuLink home = new MenuLink(HOME_LOGGED_IN);
      menuLinkList.add(home);
      home.getChildList().add(PERSONAL_SETTINGS);
      home.getChildList().add(LOGOUT);

      MenuLink profile = new MenuLink(PROFILE);
      menuLinkList.add(profile);
      if (webSession.getSubmitterProfile() != null)
      {
        profile.getChildList().add(DATA_RECEIVED);
        profile.getChildList().add(ERRORS_WARNINGS);
        profile.getChildList().add(WEEKLY_REPORT);
        if (webSession.isAdmin())
        {
          profile.getChildList().add(PROFILE_SETTINGS);
        }
      }

      if (webSession.isAdmin())
      {
        MenuLink admin = new MenuLink(ADMIN);
        menuLinkList.add(admin);
      }
    } else
    {
      menuLinkList.add(HOME_LOGGED_OUT);
      menuLinkList.add(LOGIN);
    }
    return menuLinkList;
  }

  public NavigationPanel(String id, MenuLink menuLinkSelected) {
    super(id);
    DqaSession webSession = (DqaSession) getSession();
    List<MenuLink> menuLinkList = createMenu(webSession);
    if (menuLinkSelected == null)
    {
      menuLinkSelected = HOME_LOGGED_OUT;
    }
    for (MenuLink menuLink : menuLinkList)
    {
      menuLink.setSelected(menuLinkSelected.getLinkLabel().equals(menuLink.getLinkLabel()));
    }
    webSession.setMenuLinkSelected(menuLinkSelected);

    ListView<MenuLink> menuLinkItems = new ListView<MenuLink>("menuLinkItems", menuLinkList) {

      @Override
      protected void populateItem(ListItem<MenuLink> item)
      {
        MenuLink menuLink = item.getModelObject();
        DqaSession webSession = (DqaSession) getSession();
        BookmarkablePageLink link = new BookmarkablePageLink("link", menuLink.getPageClass());
        item.add(link);
        link.add(new Label("label", menuLink.getLinkLabel()));
        if (menuLink.isSelected())
        {
          link.add(new SimpleAttributeModifier("id", "current"));
        }

        ListView<MenuLink> childMenuLinkItems = new ListView<MenuLink>("childMenuLinkItems", menuLink.childList) {
          @Override
          protected void populateItem(ListItem<MenuLink> item)
          {
            MenuLink menuLink = item.getModelObject();
            BookmarkablePageLink link = new BookmarkablePageLink("childLink", menuLink.getPageClass());
            item.add(link);
            link.add(new Label("childLabel", menuLink.getLinkLabel()));
          }
        };
        item.add(childMenuLinkItems);

      }

    };
    add(menuLinkItems);

  }

}
