package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;

public class GetBatchesHandler
{
  public GetBatchesResultType getBatches(GetBatchesType request) throws RemoteException, FaultType
  {
    List<MessageBatchType> messageBatchTypeList = new ArrayList<MessageBatchType>();
    String profileCode = request.getProfileCode();
    if (profileCode == null || profileCode.equals(""))
    {
      FaultType faultType = new FaultType();
      faultType.setFaultString("profileCode is required");
      throw faultType;
    }
    String batchType = request.getBatchType();
    if (batchType == null || batchType.equals(""))
    {
      FaultType faultType = new FaultType();
      faultType.setFaultString("batchType is required");
      throw faultType;
    }
    Date startDate = parseDate("Start Date", request.getStartDate());
    Date endDate = parseDate("End Date", request.getEndDate());
    String submitCode = request.getSubmitCode();
    if (submitCode != null && submitCode.equals(""))
    {
      submitCode = null;
    }
    
    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    try
    {
      List<MessageBatch> messageBatchList = null;
      if (startDate != null && endDate != null)
      {
        if (submitCode != null) 
        {
          Query query = session.createQuery("from MessageBatch where profile.profileCode = ? and batchType = ? and startDate >= ? and startDate < ? and submitCode = ?");
          query.setParameter(0, profileCode);
          query.setParameter(1, batchType);
          query.setParameter(2, startDate);
          query.setParameter(3, endDate);
          query.setParameter(4, submitCode);
          messageBatchList = query.list();
        }
        else
        {
          Query query = session.createQuery("from MessageBatch where profile.profileCode = ? and batchType = ? and startDate >= ? and startDate < ?");
          query.setParameter(0, profileCode);
          query.setParameter(1, batchType);
          query.setParameter(2, startDate);
          query.setParameter(3, endDate);
          messageBatchList = query.list();          
        }
      }
      else
      {
        Query query = session.createQuery("from MessageBatch where profile.profileCode = ? and batchType = ?");
        query.setParameter(0, profileCode);
        query.setParameter(1, batchType);
        messageBatchList = query.list();                  
      }
      for (MessageBatch messageBatch : messageBatchList)
      {
        MessageBatchType messageBatchType = new MessageBatchType();
        messageBatchType.setBatchId(messageBatch.getBatchId());
        messageBatchType.setBatchTitle(messageBatch.getBatchTitle());
        messageBatchType.setTypeCode(messageBatch.getBatchType().getTypeCode());
      }
    } finally
    {
      session.close();
    }

    return new GetBatchesResultType(messageBatchTypeList.toArray(new MessageBatchType[] {}));
  }

  private Date parseDate(String field, String dateString) throws FaultType
  {
    Date result = null;
    if (dateString != null && !dateString.equals(""))
    {
      SimpleDateFormat sdf = DqaService.getDateFormatter();
      try {
      result = sdf.parse(dateString);
      }
      catch (ParseException pe)
      {
        FaultType faultType = new FaultType();
        faultType.setFaultString(field + " not a valid date");
        throw faultType;
      }
    }
    return result;
  }
}
