package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

public class DqaService extends DqaServiceTemplate
{
  
  protected static SimpleDateFormat getDateFormatter()
  {
    return new SimpleDateFormat("yyyy-MM-dd");
  }

  @Override
  public SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType
  {
    SubmitMessageHandler submitMessageHandler = new SubmitMessageHandler();
    return submitMessageHandler.submitMessage(request);
  }
  
  @Override
  public GetBatchesResultType getBatches(GetBatchesType request) throws RemoteException, FaultType
  {
    GetBatchesHandler getBatchesHandler = new GetBatchesHandler();
    return getBatchesHandler.getBatches(request);
  }
}
