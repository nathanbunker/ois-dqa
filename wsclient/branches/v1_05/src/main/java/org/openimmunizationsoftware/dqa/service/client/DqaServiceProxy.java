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
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchesResultType getBatches(org.openimmunizationsoftware.dqa.service.client.GetBatchesType getBatchesRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatches(getBatchesRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchDqaReportResultType getBatchDqaReport(org.openimmunizationsoftware.dqa.service.client.GetBatchDqaReportType getBatchDqaReportRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatchDqaReport(getBatchDqaReportRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.CreateMessageBatchResultType createMessageBatch(org.openimmunizationsoftware.dqa.service.client.CreateMessageBatchType createMessageBatchRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.createMessageBatch(createMessageBatchRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.UpdateSubmitterProfileResultType updateSubmitterProfile(org.openimmunizationsoftware.dqa.service.client.UpdateSubmitterProfileType updateSubmitterProfileRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.updateSubmitterProfile(updateSubmitterProfileRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetSubmitterProfileResultType getSubmitterProfile(org.openimmunizationsoftware.dqa.service.client.GetSubmitterProfileType getSubmitterProfileRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getSubmitterProfile(getSubmitterProfileRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetReportTemplatesResultType getReportTemplates(org.openimmunizationsoftware.dqa.service.client.GetReportTemplatesType getReportTemplatesRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getReportTemplates(getReportTemplatesRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetCodeReceivedListResultType getCodeReceivedList(org.openimmunizationsoftware.dqa.service.client.GetCodeReceivedListType getCodeReceivedListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getCodeReceivedList(getCodeReceivedListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.UpdateCodeReceivedResultType updateCodeReceived(org.openimmunizationsoftware.dqa.service.client.UpdateCodeReceivedType updateCodeReceivedRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.updateCodeReceived(updateCodeReceivedRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetPotentialIssueStatusListResultType getPotentialIssueStatusList(org.openimmunizationsoftware.dqa.service.client.GetPotentialIssueStatusListType getPotentialIssueStatusListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getPotentialIssueStatusList(getPotentialIssueStatusListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(org.openimmunizationsoftware.dqa.service.client.UpdatePotentialIssueStatusType updatePotentialIssueStatusRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.updatePotentialIssueStatus(updatePotentialIssueStatusRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchResultType getBatch(org.openimmunizationsoftware.dqa.service.client.GetBatchType getBatchRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatch(getBatchRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchIssueListResultType getBatchIssueList(org.openimmunizationsoftware.dqa.service.client.GetBatchIssueListType getBatchIssueListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatchIssueList(getBatchIssueListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchReportResultType getBatchReport(org.openimmunizationsoftware.dqa.service.client.GetBatchReportType getBatchReportRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatchReport(getBatchReportRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchActionListResultType getBatchActionList(org.openimmunizationsoftware.dqa.service.client.GetBatchActionListType getBatchActionListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatchActionList(getBatchActionListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchVaccineCvxListResultType getBatchVaccineCvxList(org.openimmunizationsoftware.dqa.service.client.GetBatchVaccineCvxListType getBatchVaccineCvxListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatchVaccineCvxList(getBatchVaccineCvxListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetBatchCodeReceivedListResultType getBatchCodeReceivedList(org.openimmunizationsoftware.dqa.service.client.GetBatchCodeReceivedListType getBatchCodeReceivedListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getBatchCodeReceivedList(getBatchCodeReceivedListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetMessageReceivedListResultType getMessageReceivedList(org.openimmunizationsoftware.dqa.service.client.GetMessageReceivedListType getMessageReceivedListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getMessageReceivedList(getMessageReceivedListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetMessageReceivedDetailsResultType getMessageReceivedDetails(org.openimmunizationsoftware.dqa.service.client.GetMessageReceivedDetailsType getMessageReceivedDetailsRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getMessageReceivedDetails(getMessageReceivedDetailsRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetPatientResultType getPatient(org.openimmunizationsoftware.dqa.service.client.GetPatientType getPatientRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getPatient(getPatientRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetVaccinationListResultType getVaccinationList(org.openimmunizationsoftware.dqa.service.client.GetVaccinationListType getVaccinationListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getVaccinationList(getVaccinationListRequest);
  }
  
  public org.openimmunizationsoftware.dqa.service.client.GetNextOfKinListResultType getNextOfKinList(org.openimmunizationsoftware.dqa.service.client.GetNextOfKinListType getNextOfKinListRequest) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.client.FaultType{
    if (dqaService_PortType == null)
      _initDqaServiceProxy();
    return dqaService_PortType.getNextOfKinList(getNextOfKinListRequest);
  }
  
  
}