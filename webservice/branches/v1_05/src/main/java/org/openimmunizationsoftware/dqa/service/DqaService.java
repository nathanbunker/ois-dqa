package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import org.openimmunizationsoftware.dqa.service.DqaServiceTemplate;
import org.openimmunizationsoftware.dqa.service.GetBatchesHandler;
import org.openimmunizationsoftware.dqa.service.SubmitMessageHandler;
import org.openimmunizationsoftware.dqa.service.schema.FaultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchesResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchesType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType;

public class DqaService extends DqaServiceTemplate
{
  
  private static DqaService dqaService = null;

  public static DqaService getInstance() {
    if (dqaService == null) {
    	dqaService = new DqaService();
    }
    return dqaService;
  }
	  
  protected static SimpleDateFormat getDateFormatter()
  {
    return new SimpleDateFormat("yyyy-MM-dd");
  }

  @Override
  public SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType
  {
    return SubmitMessageHandler.getInstance().submitMessage(request);
  }
  
  @Override
  public GetBatchesResultType getBatches(GetBatchesType request) throws RemoteException, FaultType
  {
    return GetBatchesHandler.getInstance().getBatches(request);
  }
}
