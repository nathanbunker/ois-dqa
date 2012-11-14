package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;

import org.openimmunizationsoftware.dqa.service.DqaService;
import org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchResultType;
import org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchType;
import org.openimmunizationsoftware.dqa.service.schema.FaultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchReportResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchReportType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchesResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetBatchesType;
import org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListType;
import org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsType;
import org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListType;
import org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListType;
import org.openimmunizationsoftware.dqa.service.schema.GetPatientResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetPatientType;
import org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListType;
import org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesType;
import org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileType;
import org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListResultType;
import org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedResultType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedType;
import org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusResultType;
import org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileResultType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileType;

public class DqaServiceSupport {
  
  public static SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType {
    return DqaService.getInstance().submitMessage(request);
  }

  public static GetBatchesResultType getBatches(GetBatchesType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatches(request);
  }

  public static GetBatchDqaReportResultType getBatchDqaReport(GetBatchDqaReportType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatchDqaReport(request);
  }

  public static CreateMessageBatchResultType createMessageBatch(CreateMessageBatchType request) throws RemoteException, FaultType {
    return DqaService.getInstance().createMessageBatch(request);
  }

  public static UpdateSubmitterProfileResultType updateSubmitterProfile(UpdateSubmitterProfileType request) throws RemoteException, FaultType {
    return DqaService.getInstance().updateSubmitterProfile(request);
  }

  public static GetSubmitterProfileResultType getSubmitterProfile(GetSubmitterProfileType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getSubmitterProfile(request);
  }

  public static GetReportTemplatesResultType getReportTemplates(GetReportTemplatesType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getReportTemplates(request);
  }

  public static GetCodeReceivedListResultType getCodeReceivedList(GetCodeReceivedListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getCodeReceivedList(request);
  }

  public static UpdateCodeReceivedResultType updateCodeReceived(UpdateCodeReceivedType request) throws RemoteException, FaultType {
    return DqaService.getInstance().updateCodeReceived(request);
  }

  public static GetPotentialIssueStatusListResultType getPotentialIssueStatusList(GetPotentialIssueStatusListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getPotentialIssueStatusList(request);
  }

  public static UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(UpdatePotentialIssueStatusType request) throws RemoteException, FaultType {
    return DqaService.getInstance().updatePotentialIssueStatus(request);
  }

  public static GetBatchResultType getBatch(GetBatchType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatch(request);
  }

  public static GetBatchIssueListResultType getBatchIssueList(GetBatchIssueListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatchIssueList(request);
  }

  public static GetBatchReportResultType getBatchReport(GetBatchReportType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatchReport(request);
  }

  public static GetBatchActionListResultType getBatchActionList(GetBatchActionListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatchActionList(request);
  }

  public static GetBatchVaccineCvxListResultType getBatchVaccineCvxList(GetBatchVaccineCvxListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatchVaccineCvxList(request);
  }

  public static GetBatchCodeReceivedListResultType getBatchCodeReceivedList(GetBatchCodeReceivedListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getBatchCodeReceivedList(request);
  }

  public static GetMessageReceivedListResultType getMessageReceivedList(GetMessageReceivedListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getMessageReceivedList(request);
  }

  public static GetMessageReceivedDetailsResultType getMessageReceivedDetails(GetMessageReceivedDetailsType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getMessageReceivedDetails(request);
  }

  public static GetPatientResultType getPatient(GetPatientType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getPatient(request);
  }

  public static GetVaccinationListResultType getVaccinationList(GetVaccinationListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getVaccinationList(request);
  }

  public static GetNextOfKinListResultType getNextOfKinList(GetNextOfKinListType request) throws RemoteException, FaultType {
    return DqaService.getInstance().getNextOfKinList(request);
  }

}
