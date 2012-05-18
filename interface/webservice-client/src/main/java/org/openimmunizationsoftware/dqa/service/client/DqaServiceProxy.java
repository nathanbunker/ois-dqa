package org.openimmunizationsoftware.dqa.service.client;

public class DqaServiceProxy implements org.openimmunizationsoftware.dqa.service.client.DqaService_PortType {
  private String _endpoint = null;
  private org.openimmunizationsoftware.dqa.service.client.DqaService_PortType dqaService_PortType = null;
  
  public DqaServiceProxy() {
    _initDqaServiceProxy();
  }
  
  public DqaServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDqaServiceProxy();
  }
  
  private void _initDqaServiceProxy() {
    try {
      dqaService_PortType = (new org.openimmunizationsoftware.dqa.service.client.DqaService_ServiceLocator()).getDqaServiceSOAP();
      if (dqaService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dqaService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dqaService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dqaService_PortType != null)
      ((javax.xml.rpc.Stub)dqaService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.openimmunizationsoftware.dqa.service.client.DqaService_PortType getDqaService_PortType() {
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType;
  }
  
  public org.openimmunizationsoftware.dqa.service.client.SubmitMessageResultType submitMessage(org.openimmunizationsoftware.dqa.service.client.SubmitMessageType submitMessageRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.submitMessage(submitMessageRequest);
  }
  
  
}