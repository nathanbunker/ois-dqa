package org.openimmunizationsoftware.dqa.cm.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SupportingInfo implements Serializable
{

  private int supportingInfoId = 0;
  private CodeMaster code = null;
  private User user = null;
  private Resource resource = null;
  private String abstractText = "";
  private String commentText = "";
  private Date effectiveDate = null;
  private Date entryDate = null;

  public int getSupportingInfoId()
  {
    return supportingInfoId;
  }

  public void setSupportingInfoId(int supportingInfoId)
  {
    this.supportingInfoId = supportingInfoId;
  }

  public CodeMaster getCode()
  {
    return code;
  }

  public void setCode(CodeMaster code)
  {
    this.code = code;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public Resource getResource()
  {
    return resource;
  }

  public void setResource(Resource resource)
  {
    this.resource = resource;
  }

  public String getAbstractText()
  {
    return abstractText;
  }

  public void setAbstractText(String abstractText)
  {
    this.abstractText = abstractText;
  }

  public String getCommentText()
  {
    return commentText;
  }

  public void setCommentText(String commentText)
  {
    this.commentText = commentText;
  }

  public Date getEffectiveDate()
  {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate)
  {
    this.effectiveDate = effectiveDate;
  }

  public Date getEntryDate()
  {
    return entryDate;
  }

  public void setEntryDate(Date entryDate)
  {
    this.entryDate = entryDate;
  }
}
