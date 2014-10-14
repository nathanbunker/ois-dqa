/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.openimmunizationsoftware.dqa.web.admin;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.openimmunizationsoftware.dqa.web.DqaBasePage;
import org.openimmunizationsoftware.dqa.web.NavigationPanel;
import org.openimmunizationsoftware.dqa.web.SecurePage;

public class AdminCvxPage extends DqaBasePage implements SecurePage
{

  public AdminCvxPage()
  {
    this(new PageParameters());
  }
  
  public AdminCvxPage(PageParameters pageParameters)
  {
    super(pageParameters, NavigationPanel.ADMIN);
  }
}