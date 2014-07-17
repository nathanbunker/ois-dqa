package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AttributeValue;
import org.openimmunizationsoftware.dqa.cm.model.CodeMaster;

public class AttributeValueLogic
{
  public static AttributeValue getAttributeValue(int valueId, Session dataSession)
  {
    return (AttributeValue) dataSession.get(AttributeValue.class, valueId);
  }

  public static AttributeValue getAttributeValue(CodeMaster codeMaster, String attributeValue, Session dataSession)
  {
    Query query = dataSession.createQuery("from AttributeValue where code = ? and attributeValue = ?");
    query.setParameter(0, codeMaster);
    query.setParameter(1, attributeValue);
    List<AttributeValue> attributeValueList = query.list();
    if (attributeValueList.size() > 0)
    {
      return attributeValueList.get(0);
    }
    return null;
  }

  public static void saveAttributeValue(AttributeValue attributeValue, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(attributeValue);
    transaction.commit();
  }
}
