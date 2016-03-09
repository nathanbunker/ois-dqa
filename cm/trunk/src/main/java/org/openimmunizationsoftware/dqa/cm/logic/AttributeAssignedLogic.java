package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AttributeAssigned;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;
import org.openimmunizationsoftware.dqa.cm.model.CodeTable;

public class AttributeAssignedLogic
{

  @SuppressWarnings("unchecked")
  public static List<AttributeAssigned> getAttributeAssignedList(CodeTable codeTable, Session dataSession)
  {
    List<AttributeAssigned> attributeAssignedList = null;
    Query query = dataSession.createQuery("from AttributeAssigned where table = ? order by displayOrder");
    query.setParameter(0, codeTable);
    attributeAssignedList = query.list();
    return attributeAssignedList;
  }

  public static AttributeAssigned getAttributeAssigned(AttributeInstance attributeInstance, AttributeType attributeType, Session dataSession)
  {
    return getAttributeAssigned(attributeInstance.getCodeInstance().getTableInstance().getTable(), attributeType, dataSession);
  }

  @SuppressWarnings("unchecked")
  public static AttributeAssigned getAttributeAssigned(CodeTable codeTable, AttributeType attributeType, Session dataSession)
  {
    List<AttributeAssigned> attributeAssignedList = null;
    Query query = dataSession.createQuery("from AttributeAssigned where table = ? and attributeType = ? order by displayOrder");
    query.setParameter(0, codeTable);
    query.setParameter(1, attributeType);
    attributeAssignedList = query.list();
    if (attributeAssignedList.size() == 0)
    {
      return null;
    }
    return attributeAssignedList.get(0);

  }

  public static AttributeAssigned getAttributeAssigned(int attributeAssignedId, Session dataSession)
  {
    return (AttributeAssigned) dataSession.get(AttributeAssigned.class, attributeAssignedId);
  }

  public static void saveAttributeAssigned(AttributeAssigned attributeAssigned, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(attributeAssigned);
    transaction.commit();
  }

  public static void updateAttributeAssigned(AttributeAssigned attributeAssigned, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(attributeAssigned);
    transaction.commit();
  }

}
