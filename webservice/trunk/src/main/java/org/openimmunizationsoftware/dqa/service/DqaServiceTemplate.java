package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;

import org.openimmunizationsoftware.dqa.service.schema.BatchActionType;
import org.openimmunizationsoftware.dqa.service.schema.BatchCodeReceivedType;
import org.openimmunizationsoftware.dqa.service.schema.BatchIssueType;
import org.openimmunizationsoftware.dqa.service.schema.BatchReportType;
import org.openimmunizationsoftware.dqa.service.schema.BatchVaccineCvxType;
import org.openimmunizationsoftware.dqa.service.schema.CodeReceivedType;
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
import org.openimmunizationsoftware.dqa.service.schema.IssueFoundType;
import org.openimmunizationsoftware.dqa.service.schema.IssueType;
import org.openimmunizationsoftware.dqa.service.schema.MessageBatchType;
import org.openimmunizationsoftware.dqa.service.schema.MessageReceivedDetailsType;
import org.openimmunizationsoftware.dqa.service.schema.MessageReceivedType;
import org.openimmunizationsoftware.dqa.service.schema.NextOfKinType;
import org.openimmunizationsoftware.dqa.service.schema.PatientType;
import org.openimmunizationsoftware.dqa.service.schema.PotentialIssueStatusType;
import org.openimmunizationsoftware.dqa.service.schema.ReportTemplateType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitterProfileType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedResultType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedType;
import org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusResultType;
import org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileResultType;
import org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileType;
import org.openimmunizationsoftware.dqa.service.schema.VaccinationType;

public class DqaServiceTemplate
{
  public SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType
  {
    IssueType[] errorList = new IssueType[] {};
    IssueType[] warningList = new IssueType[] {};
    return new SubmitMessageResultType("messageResponse", "responseStatus", "responseText", 0L, 0L, "hashId", errorList, warningList, "processReport");
  }

  public GetBatchesResultType getBatches(GetBatchesType request) throws RemoteException, FaultType {
    MessageBatchType[] messageBatchList = new MessageBatchType[] {};
    return new GetBatchesResultType(messageBatchList);
  }

  public GetBatchDqaReportResultType getBatchDqaReport(GetBatchDqaReportType request) throws RemoteException, FaultType {
    return new GetBatchDqaReportResultType("dqaReport");
  }

  public CreateMessageBatchResultType createMessageBatch(CreateMessageBatchType request) throws RemoteException, FaultType {
    return new CreateMessageBatchResultType(0L);
  }

  public UpdateSubmitterProfileResultType updateSubmitterProfile(UpdateSubmitterProfileType request) throws RemoteException, FaultType {
    return new UpdateSubmitterProfileResultType(0L);
  }

  public GetSubmitterProfileResultType getSubmitterProfile(GetSubmitterProfileType request) throws RemoteException, FaultType {
    SubmitterProfileType submitterProfile = new SubmitterProfileType("profileCode", 0L, "profileLabel", "profileStatus", 0L, "orgLocalLabel", "transferPriority", "accessKey", 0L);
    return new GetSubmitterProfileResultType(submitterProfile);
  }

  public GetReportTemplatesResultType getReportTemplates(GetReportTemplatesType request) throws RemoteException, FaultType {
    ReportTemplateType[] templateList = new ReportTemplateType[] {};
    return new GetReportTemplatesResultType(templateList);
  }

  public GetCodeReceivedListResultType getCodeReceivedList(GetCodeReceivedListType request) throws RemoteException, FaultType {
    CodeReceivedType[] codeReceivedList = new CodeReceivedType[] {};
    return new GetCodeReceivedListResultType(codeReceivedList);
  }

  public UpdateCodeReceivedResultType updateCodeReceived(UpdateCodeReceivedType request) throws RemoteException, FaultType {
    return new UpdateCodeReceivedResultType();
  }

  public GetPotentialIssueStatusListResultType getPotentialIssueStatusList(GetPotentialIssueStatusListType request) throws RemoteException, FaultType {
    PotentialIssueStatusType[] potentialIssueStatusList = new PotentialIssueStatusType[] {};
    return new GetPotentialIssueStatusListResultType(potentialIssueStatusList);
  }

  public UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(UpdatePotentialIssueStatusType request) throws RemoteException, FaultType {
    return new UpdatePotentialIssueStatusResultType();
  }

  public GetBatchResultType getBatch(GetBatchType request) throws RemoteException, FaultType {
    MessageBatchType messageBatch = new MessageBatchType(0L, "batchTitle", "typeCode", "typeLabel", "startDate", "endDate", "submitCode", "submitLabel", 0L, "profileCode", 0L, 0L);
    return new GetBatchResultType(messageBatch );
  }

  public GetBatchIssueListResultType getBatchIssueList(GetBatchIssueListType request) throws RemoteException, FaultType {
    BatchIssueType[] batchIssueList = new BatchIssueType[] {};
    return new GetBatchIssueListResultType(batchIssueList);
  }

  public GetBatchReportResultType getBatchReport(GetBatchReportType request) throws RemoteException, FaultType {
    BatchReportType batchReport = new BatchReportType(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0.0f, 0L, 0L, 0L, 0L, 0L, "timeDateFirst", "timeDateLast", 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
    return new GetBatchReportResultType(batchReport);
  }

  public GetBatchActionListResultType getBatchActionList(GetBatchActionListType request) throws RemoteException, FaultType {
    BatchActionType[] batchActionList = new BatchActionType[] {};
    return new GetBatchActionListResultType(batchActionList);
  }

  public GetBatchVaccineCvxListResultType getBatchVaccineCvxList(GetBatchVaccineCvxListType request) throws RemoteException, FaultType {
    BatchVaccineCvxType[] batchVaccineCvxList = new BatchVaccineCvxType[] {};
    return new GetBatchVaccineCvxListResultType(batchVaccineCvxList);
  }

  public GetBatchCodeReceivedListResultType getBatchCodeReceivedList(GetBatchCodeReceivedListType request) throws RemoteException, FaultType {
    BatchCodeReceivedType[] batchCodeReceivedList = new BatchCodeReceivedType[] {};
    return new GetBatchCodeReceivedListResultType(batchCodeReceivedList);
  }

  public GetMessageReceivedListResultType getMessageReceivedList(GetMessageReceivedListType request) throws RemoteException, FaultType {
    MessageReceivedType[] messageReceivedList = new MessageReceivedType[] {};
    return new GetMessageReceivedListResultType(messageReceivedList);
  }

  public GetMessageReceivedDetailsResultType getMessageReceivedDetails(GetMessageReceivedDetailsType request) throws RemoteException, FaultType {
    MessageReceivedDetailsType messageReceivedDetails = new MessageReceivedDetailsType(0L, 0L, "receivedDate", "actionCode", "actionLabel", "submitCode", "submitLabel", "requestText", "responseText");
    IssueFoundType[] issueFoundList = new IssueFoundType[] {};
    return new GetMessageReceivedDetailsResultType(messageReceivedDetails, issueFoundList);
  }

  public GetPatientResultType getPatient(GetPatientType request) throws RemoteException, FaultType {
    PatientType patient = new PatientType(0L, "skipped", "addressCity", "addressCountry", "addressCountyParish", "addressState", "addressStreet", "addressStreet2", "addressType", "addressZip", "aliasFirst", "aliasLast", "aliasMiddle", "aliasPrefix", "aliasSuffix", 0L, "birthDate", "birthMultiple", "birthOrder", "birthPlace", "deathIndicator", "ethincityCode", 0L, "facilityName", "financialEligibility", "idMedicaid", "idSsn", "idSubmitterAssignAuth", "idSubmitterNumber", "idSubmitterTypeCode", "motherMaidenName", "nameFirst", "nameLast", "nameMiddle", "namePrefix", "nameSuffix", "nameTypeCode", "phoneNumber", "physianNameFirst", "physianNameLast", "physicianNumber", "primaryLanguageCode", "protectionCode", "publicityCode", "raceCode", "registryStatus", "sexCode");
    return new GetPatientResultType(patient);
  }

  public GetVaccinationListResultType getVaccinationList(GetVaccinationListType request) throws RemoteException, FaultType {
    VaccinationType[] vaccinationList = new VaccinationType[] {};
    return new GetVaccinationListResultType(vaccinationList);
  }

  public GetNextOfKinListResultType getNextOfKinList(GetNextOfKinListType request) throws RemoteException, FaultType {
    NextOfKinType[] nextOfKinList = new NextOfKinType[] {};
    return new GetNextOfKinListResultType(nextOfKinList);
  }

}
