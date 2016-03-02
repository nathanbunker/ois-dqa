package org.openimmunizationsoftware.dqa.cm.model;

public class Agreement
{
  private int agreementId = 0;
  private String agreementTitle = "";
  private String agreementText = "";

  public int getAgreementId()
  {
    return agreementId;
  }

  public void setAgreementId(int agreementId)
  {
    this.agreementId = agreementId;
  }

  public String getAgreementTitle()
  {
    return agreementTitle;
  }

  public void setAgreementTitle(String agreementTitle)
  {
    this.agreementTitle = agreementTitle;
  }

  public String getAgreementText()
  {
    return agreementText;
  }

  public void setAgreementText(String agreementText)
  {
    this.agreementText = agreementText;
  }
}
