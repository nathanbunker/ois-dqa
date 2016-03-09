package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AttributeComment;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.AttributeValue;
import org.openimmunizationsoftware.dqa.cm.model.PositionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;

public class AttributeInstanceLogic
{
  public static AttributeInstance getAttributeInstance(int attributeInstanceId, Session dataSession)
  {
    return (AttributeInstance) dataSession.get(AttributeInstance.class, attributeInstanceId);
  }

  public static AttributeInstance getAttributeInstance(ReleaseVersion releaseVersion, AttributeValue attributeValue, Session dataSession)
  {
    Query query = dataSession.createQuery("from AttributeInstance where codeInstance.tableInstance.release = ? and  value = ?");
    query.setParameter(0, releaseVersion);
    query.setParameter(1, attributeValue);
    @SuppressWarnings("unchecked")
    List<AttributeInstance> attributeInstanceList = query.list();
    if (attributeInstanceList.size() > 0)
    {
      return attributeInstanceList.get(0);
    }
    return null;
  }

  public static void saveAttributeInstance(AttributeInstance attributeInstance, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(attributeInstance);
    transaction.commit();
  }

  public static void updateAttributeInstanceAcceptStatus(AttributeInstance attributeInstance, Session dataSession)
  {
    List<AttributeComment> attributeCommentSummaryList = AttributeCommentLogic.getAttributeCommentMostRecentList(attributeInstance, dataSession);
    int againstCount = 0;
    int confirmedCount = 0;
    for (AttributeComment attributeComment : attributeCommentSummaryList)
    {
      if (attributeComment.getPositionStatus() == PositionStatus.AGAINST)
      {
        againstCount++;
      } else if (attributeComment.getPositionStatus() == PositionStatus.FOR)
      {
        confirmedCount++;
        if (attributeComment.getUser().isSystemUser())
        {
          confirmedCount++;
        }
      } else if (attributeComment.getPositionStatus() == PositionStatus.RESEARCH && attributeComment.getUser().isSystemUser())
      {
        confirmedCount++;
      }
    }
    if (againstCount >= 2)
    {
      attributeInstance.setAcceptStatus(AcceptStatus.REJECTED);
    } else if (againstCount == 1)
    {
      attributeInstance.setAcceptStatus(AcceptStatus.OPPOSED);
    } else
    {
      if (attributeCommentSummaryList.size() > 0)
      {
        AttributeComment attributeCommentMostRecent = attributeCommentSummaryList.get(0);
        if (attributeCommentMostRecent.getPositionStatus() == PositionStatus.QUESTION)
        {
          attributeInstance.setAcceptStatus(AcceptStatus.QUESTION);
        } else if (attributeCommentMostRecent.getPositionStatus() == PositionStatus.RESEARCH)
        {
          attributeInstance.setAcceptStatus(AcceptStatus.RESEARCH);
        } else if (attributeCommentMostRecent.getPositionStatus() == PositionStatus.PROBLEM)
        {
          attributeInstance.setAcceptStatus(AcceptStatus.PROBLEM);
        } else if (attributeCommentMostRecent.getPositionStatus() == PositionStatus.PROBLEM)
        {
          attributeInstance.setAcceptStatus(AcceptStatus.PROBLEM);
        } else if (confirmedCount >= 2)
        {
          attributeInstance.setAcceptStatus(AcceptStatus.CONFIRMED);
        } else
        {
          attributeInstance.setAcceptStatus(AcceptStatus.PROPOSED);
        }
      }
    }
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(attributeInstance);
    transaction.commit();

  }

}
