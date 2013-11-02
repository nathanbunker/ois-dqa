package org.openimmunizationsoftware.dqa.db.model.received;

import java.io.Serializable;
import java.util.Date;

import org.openimmunizationsoftware.dqa.db.model.CodeTable;
import org.openimmunizationsoftware.dqa.db.model.received.types.CodedEntity;

public class VaccinationVIS  implements Skippable, Serializable
{
  private int visId = 0;
  private Vaccination vaccination = null;
  private int positionId = 0;
  private boolean skipped = false;
  private CodedEntity document = new CodedEntity(CodeTable.Type.VACCINATION_VIS_DOC_TYPE);
  private CodedEntity cvx = new CodedEntity(CodeTable.Type.VACCINATION_VIS_CVX_CODE);
  private Date publishedDate = null;
  private Date presentedDate = null;

  public String getCvxCode()
  {
    return cvx.getCode();
  }
  public void setCvxCode(String cvxCode)
  {
    cvx.setCode(cvxCode);
  }
  public CodedEntity getCvx()
  {
    return cvx;
  }
  public int getVisId()
  {
    return visId;
  }
  public void setVisId(int visId)
  {
    this.visId = visId;
  }
  public Vaccination getVaccination()
  {
    return vaccination;
  }
  public void setVaccination(Vaccination vaccination)
  {
    this.vaccination = vaccination;
  }
  public int getPositionId()
  {
    return positionId;
  }
  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }
  public boolean isSkipped()
  {
    return skipped;
  }
  public void setSkipped(boolean skipped)
  {
    this.skipped = skipped;
  }
  public String getDocumentCode()
  {
    return document.getCode();
  }
  public void setDocumentCode(String documentCode)
  {
    this.document.setCode(documentCode);
  }
  public Date getPublishedDate()
  {
    return publishedDate;
  }
  public void setPublishedDate(Date publishedDate)
  {
    this.publishedDate = publishedDate;
  }
  public Date getPresentedDate()
  {
    return presentedDate;
  }
  public void setPresentedDate(Date presentedDate)
  {
    this.presentedDate = presentedDate;
  }
  public CodedEntity getDocument()
  {
    return document;
  }
}
