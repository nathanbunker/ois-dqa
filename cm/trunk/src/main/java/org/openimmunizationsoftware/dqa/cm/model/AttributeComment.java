package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.Date;

public class AttributeComment implements Serializable
{
  private int attributeCommentId = 0;
  private AttributeValue value = null;
  private User user = null;
  private String commentText = "";
  private Date entryDate = null;
  private PositionStatus positionStatus = null;
  
  public int getAttributeCommentId()
  {
    return attributeCommentId;
  }

  public void setAttributeCommentId(int attributeCommentId)
  {
    this.attributeCommentId = attributeCommentId;
  }

  public AttributeValue getValue()
  {
    return value;
  }

  public void setValue(AttributeValue value)
  {
    this.value = value;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public String getCommentText()
  {
    return commentText;
  }

  public void setCommentText(String commentText)
  {
    this.commentText = commentText;
  }

  public Date getEntryDate()
  {
    return entryDate;
  }

  public void setEntryDate(Date entryDate)
  {
    this.entryDate = entryDate;
  }

  public PositionStatus getPositionStatus()
  {
    return positionStatus;
  }

  public void setPositionStatus(PositionStatus positionStatus)
  {
    this.positionStatus = positionStatus;
  }

  public String getPositionStatusString()
  {
    if (positionStatus == null)
    {
      return null;
    }
    return positionStatus.getId();
  }

  public void setPositionStatusString(String positionStatusString)
  {
    this.positionStatus = PositionStatus.get(positionStatusString);
  }
}
