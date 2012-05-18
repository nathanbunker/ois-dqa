package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;

public class DqaService extends DqaServiceTemplate
{

  @Override
  public SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType
  {
    SubmitMessageHandler submitMessageHandler = new SubmitMessageHandler();
    return submitMessageHandler.submitMessage(request);
  }
}
