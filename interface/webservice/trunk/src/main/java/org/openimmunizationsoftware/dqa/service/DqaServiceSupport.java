package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;

public class DqaServiceSupport {
  
  private static DqaServiceTemplate serviceTemplate = new DqaServiceTemplate();
  
  public static DqaServiceTemplate getServiceTemplate()
  {
    return serviceTemplate;
  }

  public static void setServiceTemplate(DqaServiceTemplate serviceTemplate)
  {
    DqaServiceSupport.serviceTemplate = serviceTemplate;
  }

  public static SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType {
    return serviceTemplate.submitMessage(request);
  }
  
}
