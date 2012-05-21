/**
 * DqaService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public interface DqaService_PortType extends java.rmi.Remote {
    public org.openimmunizationsoftware.dqa.service.SubmitMessageResultType submitMessage(org.openimmunizationsoftware.dqa.service.SubmitMessageType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchesResultType getBatches(org.openimmunizationsoftware.dqa.service.GetBatchesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchDqaReportResultType getBatchDqaReport(org.openimmunizationsoftware.dqa.service.GetBatchDqaReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.CreateMessageBatchResultType createMessageBatch(org.openimmunizationsoftware.dqa.service.CreateMessageBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileResultType updateSubmitterProfile(org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetSubmitterProfileResultType getSubmitterProfile(org.openimmunizationsoftware.dqa.service.GetSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetReportTemplatesResultType getReportTemplates(org.openimmunizationsoftware.dqa.service.GetReportTemplatesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetCodeReceivedListResultType getCodeReceivedList(org.openimmunizationsoftware.dqa.service.GetCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedResultType updateCodeReceived(org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListResultType getPotentialIssueStatusList(org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchResultType getBatch(org.openimmunizationsoftware.dqa.service.GetBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchIssueListResultType getBatchIssueList(org.openimmunizationsoftware.dqa.service.GetBatchIssueListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchReportResultType getBatchReport(org.openimmunizationsoftware.dqa.service.GetBatchReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchActionListResultType getBatchActionList(org.openimmunizationsoftware.dqa.service.GetBatchActionListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListResultType getBatchVaccineCvxList(org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListResultType getBatchCodeReceivedList(org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetMessageReceivedListResultType getMessageReceivedList(org.openimmunizationsoftware.dqa.service.GetMessageReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsResultType getMessageReceivedDetails(org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetPatientResultType getPatient(org.openimmunizationsoftware.dqa.service.GetPatientType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetVaccinationListResultType getVaccinationList(org.openimmunizationsoftware.dqa.service.GetVaccinationListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
    public org.openimmunizationsoftware.dqa.service.GetNextOfKinListResultType getNextOfKinList(org.openimmunizationsoftware.dqa.service.GetNextOfKinListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType;
}
