package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;

public class DqaServiceSupport {
  
  private static DqaServiceTemplate serviceTemplate = new DqaServiceTemplate();
  
  public static DqaServiceTemplate getServiceTemplate() {
    return serviceTemplate;
  }

  public static void setServiceTemplate(DqaServiceTemplate serviceTemplate) {
    DqaServiceSupport.serviceTemplate = serviceTemplate;
  }

  public static SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType {
    return serviceTemplate.submitMessage(request);
  }

  public static GetBatchesResultType getBatches(GetBatchesType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatches(request);
  }

  public static GetBatchDqaReportResultType getBatchDqaReport(GetBatchDqaReportType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatchDqaReport(request);
  }

  public static CreateMessageBatchResultType createMessageBatch(CreateMessageBatchType request) throws RemoteException, FaultType {
    return serviceTemplate.createMessageBatch(request);
  }

  public static UpdateSubmitterProfileResultType updateSubmitterProfile(UpdateSubmitterProfileType request) throws RemoteException, FaultType {
    return serviceTemplate.updateSubmitterProfile(request);
  }

  public static GetSubmitterProfileResultType getSubmitterProfile(GetSubmitterProfileType request) throws RemoteException, FaultType {
    return serviceTemplate.getSubmitterProfile(request);
  }

  public static GetReportTemplatesResultType getReportTemplates(GetReportTemplatesType request) throws RemoteException, FaultType {
    return serviceTemplate.getReportTemplates(request);
  }

  public static GetCodeReceivedListResultType getCodeReceivedList(GetCodeReceivedListType request) throws RemoteException, FaultType {
    return serviceTemplate.getCodeReceivedList(request);
  }

  public static UpdateCodeReceivedResultType updateCodeReceived(UpdateCodeReceivedType request) throws RemoteException, FaultType {
    return serviceTemplate.updateCodeReceived(request);
  }

  public static GetPotentialIssueStatusListResultType getPotentialIssueStatusList(GetPotentialIssueStatusListType request) throws RemoteException, FaultType {
    return serviceTemplate.getPotentialIssueStatusList(request);
  }

  public static UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(UpdatePotentialIssueStatusType request) throws RemoteException, FaultType {
    return serviceTemplate.updatePotentialIssueStatus(request);
  }

  public static GetBatchResultType getBatch(GetBatchType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatch(request);
  }

  public static GetBatchIssueListResultType getBatchIssueList(GetBatchIssueListType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatchIssueList(request);
  }

  public static GetBatchReportResultType getBatchReport(GetBatchReportType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatchReport(request);
  }

  public static GetBatchActionListResultType getBatchActionList(GetBatchActionListType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatchActionList(request);
  }

  public static GetBatchVaccineCvxListResultType getBatchVaccineCvxList(GetBatchVaccineCvxListType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatchVaccineCvxList(request);
  }

  public static GetBatchCodeReceivedListResultType getBatchCodeReceivedList(GetBatchCodeReceivedListType request) throws RemoteException, FaultType {
    return serviceTemplate.getBatchCodeReceivedList(request);
  }

  public static GetMessageReceivedListResultType getMessageReceivedList(GetMessageReceivedListType request) throws RemoteException, FaultType {
    return serviceTemplate.getMessageReceivedList(request);
  }

  public static GetMessageReceivedDetailsResultType getMessageReceivedDetails(GetMessageReceivedDetailsType request) throws RemoteException, FaultType {
    return serviceTemplate.getMessageReceivedDetails(request);
  }

  public static GetPatientResultType getPatient(GetPatientType request) throws RemoteException, FaultType {
    return serviceTemplate.getPatient(request);
  }

  public static GetVaccinationListResultType getVaccinationList(GetVaccinationListType request) throws RemoteException, FaultType {
    return serviceTemplate.getVaccinationList(request);
  }

  public static GetNextOfKinListResultType getNextOfKinList(GetNextOfKinListType request) throws RemoteException, FaultType {
    return serviceTemplate.getNextOfKinList(request);
  }

}
