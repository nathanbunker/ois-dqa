package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class TransportWsdlCdc implements Serializable
{
  private int transportWsdlCdcId = 0;
  private TransportAnalysis transportAnalysis = null;
  private String transportStatus = "";
  private String transportNotes = "";
  private String passEyeTest = "";
  private String clientCertificate = "";
  private String ctSupports = "";
  private String ctConforms = "";
  private String ctFailure_reason = "";
  private String ssmSupports = "";
  private String ssmConforms = "";
  private String ssmFailureReason = "";
  private String sfSupports = "";
  private String sfConforms = "";
  private String sfFailureReason = "";
  
  public TransportAnalysis getTransportAnalysis()
  {
    return transportAnalysis;
  }
  public void setTransportAnalysis(TransportAnalysis transportAnalysis)
  {
    this.transportAnalysis = transportAnalysis;
  }
  public int getTransportWsdlCdcId()
  {
    return transportWsdlCdcId;
  }
  public void setTransportWsdlCdcId(int transportWsdlCdcId)
  {
    this.transportWsdlCdcId = transportWsdlCdcId;
  }

  public String getTransportStatus()
  {
    return transportStatus;
  }
  public void setTransportStatus(String transportStatus)
  {
    this.transportStatus = transportStatus;
  }
  public String getTransportNotes()
  {
    return transportNotes;
  }
  public void setTransportNotes(String transportNotes)
  {
    this.transportNotes = transportNotes;
  }
  public String getPassEyeTest()
  {
    return passEyeTest;
  }
  public void setPassEyeTest(String passEyeTest)
  {
    this.passEyeTest = passEyeTest;
  }
  public String getClientCertificate()
  {
    return clientCertificate;
  }
  public void setClientCertificate(String clientCertificate)
  {
    this.clientCertificate = clientCertificate;
  }
  public String getCtSupports()
  {
    return ctSupports;
  }
  public void setCtSupports(String ctSupports)
  {
    this.ctSupports = ctSupports;
  }
  public String getCtConforms()
  {
    return ctConforms;
  }
  public void setCtConforms(String ctConforms)
  {
    this.ctConforms = ctConforms;
  }
  public String getCtFailure_reason()
  {
    return ctFailure_reason;
  }
  public void setCtFailure_reason(String ctFailure_reason)
  {
    this.ctFailure_reason = ctFailure_reason;
  }
  public String getSsmSupports()
  {
    return ssmSupports;
  }
  public void setSsmSupports(String ssmSupports)
  {
    this.ssmSupports = ssmSupports;
  }
  public String getSsmConforms()
  {
    return ssmConforms;
  }
  public void setSsmConforms(String ssmConforms)
  {
    this.ssmConforms = ssmConforms;
  }
  public String getSsmFailureReason()
  {
    return ssmFailureReason;
  }
  public void setSsmFailureReason(String ssmFailureReason)
  {
    this.ssmFailureReason = ssmFailureReason;
  }
  public String getSfSupports()
  {
    return sfSupports;
  }
  public void setSfSupports(String sfSupports)
  {
    this.sfSupports = sfSupports;
  }
  public String getSfConforms()
  {
    return sfConforms;
  }
  public void setSfConforms(String sfConforms)
  {
    this.sfConforms = sfConforms;
  }
  public String getSfFailureReason()
  {
    return sfFailureReason;
  }
  public void setSfFailureReason(String sfFailureReason)
  {
    this.sfFailureReason = sfFailureReason;
  }

}
