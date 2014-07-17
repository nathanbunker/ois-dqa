package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.AllowedValue;
import org.openimmunizationsoftware.dqa.cm.model.AttributeType;

public class AllowedValueLogic
{
  public static AllowedValue getAllowedValue(AttributeType attributeType, String savedValue, Session dataSession)
  {
    Query query = dataSession.createQuery("from AllowedValue where attributeType = ? and savedValue = ?");
    query.setParameter(0, attributeType);
    query.setParameter(1, savedValue);
    List<AllowedValue> allowedValueList = query.list();
    if (allowedValueList.size() > 0)
    {
      return allowedValueList.get(0);
    }
    return null;
  }
  
  public static List<AllowedValue> getAllowedValueList(AttributeType attributeType, Session dataSession)
  {
    Query query = dataSession.createQuery("from AllowedValue where attributeType = ? order by display_order, display_text");
    query.setParameter(0, attributeType);
    List<AllowedValue> allowedValueList = query.list();
    return allowedValueList;
  }
}
