/**
 * DqaService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public interface DqaService_PortType extends java.rmi.Remote {
    public org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType submitMessage(org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchesResultType getBatches(org.openimmunizationsoftware.dqa.service.schema.GetBatchesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportResultType getBatchDqaReport(org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchResultType createMessageBatch(org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileResultType updateSubmitterProfile(org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileResultType getSubmitterProfile(org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesResultType getReportTemplates(org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListResultType getCodeReceivedList(org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedResultType updateCodeReceived(org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListResultType getPotentialIssueStatusList(org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchResultType getBatch(org.openimmunizationsoftware.dqa.service.schema.GetBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListResultType getBatchIssueList(org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchReportResultType getBatchReport(org.openimmunizationsoftware.dqa.service.schema.GetBatchReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListResultType getBatchActionList(org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListResultType getBatchVaccineCvxList(org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListResultType getBatchCodeReceivedList(org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListResultType getMessageReceivedList(org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsResultType getMessageReceivedDetails(org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetPatientResultType getPatient(org.openimmunizationsoftware.dqa.service.schema.GetPatientType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListResultType getVaccinationList(org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
    public org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListResultType getNextOfKinList(org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType;
}
