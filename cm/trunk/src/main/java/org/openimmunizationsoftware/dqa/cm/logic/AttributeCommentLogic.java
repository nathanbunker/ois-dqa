package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AttributeComment;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class AttributeCommentLogic
{
  public static List<AttributeComment> getAttributeCommentList(AttributeInstance attributeInstance, Session dataSession)
  {
    Query query = dataSession.createQuery("from AttributeComment where value = ? order by entryDate desc, attributeCommentId desc");
    query.setParameter(0, attributeInstance.getValue());
    List<AttributeComment> attributeCommentList = query.list();
    return attributeCommentList;
  }

  public static List<AttributeComment> getAttributeCommentMostRecentList(AttributeInstance attributeInstance, Session dataSession)
  {
    List<AttributeComment> attributeCommentList = getAttributeCommentList(attributeInstance, dataSession);
    Map<User, AttributeComment> attributeCommentMap = new HashMap<User, AttributeComment>();
    for (AttributeComment attributeComment : attributeCommentList)
    {
      if (attributeCommentMap.containsKey(attributeComment.getUser()))
      {
        continue;
      }
      attributeCommentMap.put(attributeComment.getUser(), attributeComment);
    }
    return new ArrayList<AttributeComment>(attributeCommentMap.values());
  }
  
  public static void saveAttributeComment(AttributeComment attributeComment, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(attributeComment);
    transaction.commit();
  }
}
