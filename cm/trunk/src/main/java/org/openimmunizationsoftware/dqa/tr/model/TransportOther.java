package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class TransportOther implements Serializable
{
  private int transportWsdlOtherId = 0;
  private TransportAnalysis transportAnalysis = null;
  private String clientCertificate = "";
  private String operation = "";
  private String username = "";
  private String password = "";
  private String facilityid = "";
  private String otherParameter = "";
  private String hl7Message = "";
  private String couldUseCdcWsdl = "";
  private String findingsReport = "";

  public TransportAnalysis getTransportAnalysis()
  {
    return transportAnalysis;
  }

  public void setTransportAnalysis(TransportAnalysis transportAnalysis)
  {
    this.transportAnalysis = transportAnalysis;
  }

  public int getTransportWsdlOtherId()
  {
    return transportWsdlOtherId;
  }

  public void setTransportWsdlOtherId(int transportWsdlOtherId)
  {
    this.transportWsdlOtherId = transportWsdlOtherId;
  }

  public String getClientCertificate()
  {
    return clientCertificate;
  }

  public void setClientCertificate(String clientCertificate)
  {
    this.clientCertificate = clientCertificate;
  }

  public String getOperation()
  {
    return operation;
  }

  public void setOperation(String operation)
  {
    this.operation = operation;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getFacilityid()
  {
    return facilityid;
  }

  public void setFacilityid(String facilityid)
  {
    this.facilityid = facilityid;
  }

  public String getOtherParameter()
  {
    return otherParameter;
  }

  public void setOtherParameter(String otherParameter)
  {
    this.otherParameter = otherParameter;
  }

  public String getHl7Message()
  {
    return hl7Message;
  }

  public void setHl7Message(String hl7Message)
  {
    this.hl7Message = hl7Message;
  }

  public String getCouldUseCdcWsdl()
  {
    return couldUseCdcWsdl;
  }

  public void setCouldUseCdcWsdl(String couldUseCdcWsdl)
  {
    this.couldUseCdcWsdl = couldUseCdcWsdl;
  }

  public String getFindingsReport()
  {
    return findingsReport;
  }

  public void setFindingsReport(String findingsReport)
  {
    this.findingsReport = findingsReport;
  }
}
