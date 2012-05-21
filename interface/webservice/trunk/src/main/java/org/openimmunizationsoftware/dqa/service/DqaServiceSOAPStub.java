/**
 * DqaServiceSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class DqaServiceSOAPStub extends org.apache.axis.client.Stub implements org.openimmunizationsoftware.dqa.service.DqaService_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[22];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("submitMessage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "submitMessageRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageType"), org.openimmunizationsoftware.dqa.service.SubmitMessageType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.SubmitMessageResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "submitMessageResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatches");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchesType"), org.openimmunizationsoftware.dqa.service.GetBatchesType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchesResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchesResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatchDqaReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchDqaReportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchDqaReportType"), org.openimmunizationsoftware.dqa.service.GetBatchDqaReportType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchDqaReportResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchDqaReportResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchDqaReportResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createMessageBatch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "createMessageBatchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CreateMessageBatchType"), org.openimmunizationsoftware.dqa.service.CreateMessageBatchType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CreateMessageBatchResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.CreateMessageBatchResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "createMessageBatchResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateSubmitterProfile");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateSubmitterProfileRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateSubmitterProfileType"), org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateSubmitterProfileResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateSubmitterProfileResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSubmitterProfile");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getSubmitterProfileRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetSubmitterProfileType"), org.openimmunizationsoftware.dqa.service.GetSubmitterProfileType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetSubmitterProfileResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetSubmitterProfileResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getSubmitterProfileResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReportTemplates");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getReportTemplatesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetReportTemplatesType"), org.openimmunizationsoftware.dqa.service.GetReportTemplatesType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetReportTemplatesResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetReportTemplatesResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getReportTemplatesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCodeReceivedList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getCodeReceivedListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetCodeReceivedListType"), org.openimmunizationsoftware.dqa.service.GetCodeReceivedListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetCodeReceivedListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetCodeReceivedListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getCodeReceivedListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateCodeReceived");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateCodeReceivedRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateCodeReceivedType"), org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateCodeReceivedResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateCodeReceivedResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPotentialIssueStatusList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPotentialIssueStatusListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPotentialIssueStatusListType"), org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPotentialIssueStatusListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPotentialIssueStatusListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updatePotentialIssueStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updatePotentialIssueStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdatePotentialIssueStatusType"), org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdatePotentialIssueStatusResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updatePotentialIssueStatusResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchType"), org.openimmunizationsoftware.dqa.service.GetBatchType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatchIssueList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchIssueListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchIssueListType"), org.openimmunizationsoftware.dqa.service.GetBatchIssueListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchIssueListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchIssueListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchIssueListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatchReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchReportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchReportType"), org.openimmunizationsoftware.dqa.service.GetBatchReportType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchReportResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchReportResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchReportResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatchActionList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchActionListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchActionListType"), org.openimmunizationsoftware.dqa.service.GetBatchActionListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchActionListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchActionListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchActionListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatchVaccineCvxList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchVaccineCvxListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchVaccineCvxListType"), org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchVaccineCvxListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchVaccineCvxListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatchCodeReceivedList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchCodeReceivedListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchCodeReceivedListType"), org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchCodeReceivedListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchCodeReceivedListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMessageReceivedList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedListType"), org.openimmunizationsoftware.dqa.service.GetMessageReceivedListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetMessageReceivedListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMessageReceivedDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedDetailsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedDetailsType"), org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedDetailsResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedDetailsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPatient");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPatientRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPatientType"), org.openimmunizationsoftware.dqa.service.GetPatientType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPatientResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetPatientResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPatientResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVaccinationList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getVaccinationListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetVaccinationListType"), org.openimmunizationsoftware.dqa.service.GetVaccinationListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetVaccinationListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetVaccinationListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getVaccinationListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getNextOfKinList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getNextOfKinListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetNextOfKinListType"), org.openimmunizationsoftware.dqa.service.GetNextOfKinListType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetNextOfKinListResultType"));
        oper.setReturnClass(org.openimmunizationsoftware.dqa.service.GetNextOfKinListResultType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getNextOfKinListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"),
                      "org.openimmunizationsoftware.dqa.service.FaultType",
                      new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"), 
                      true
                     ));
        _operations[21] = oper;

    }

    public DqaServiceSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public DqaServiceSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public DqaServiceSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchActionListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchActionType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchActionType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchActionsList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchActionType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchActionType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchCodeReceivedListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchCodeReceivedType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchCodeReceivedType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchCodeReceivedList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchCodeReceivedType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchCodeReceivedType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchIssueListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchIssueType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchIssueType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchIssuesList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchIssueType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchIssueType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchReportType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchReportType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchVaccineCvxListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchVaccineCvxType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchVaccineCvxType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchVaccineCvxList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchVaccineCvxType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.BatchVaccineCvxType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.CodeReceivedType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeReceivedList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedSmallListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.CodeReceivedSmallType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedSmallType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeReceived");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedSmallType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.CodeReceivedSmallType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.CodeReceivedType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CreateMessageBatchResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.CreateMessageBatchResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CreateMessageBatchType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.CreateMessageBatchType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.FaultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchActionListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchActionListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchActionListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchActionListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchCodeReceivedListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchCodeReceivedListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchDqaReportResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchDqaReportResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchDqaReportType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchDqaReportType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchesResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchesResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchesType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchesType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchIssueListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchIssueListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchIssueListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchIssueListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchReportResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchReportResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchReportType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchReportType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchVaccineCvxListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchVaccineCvxListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetCodeReceivedListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetCodeReceivedListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetCodeReceivedListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetCodeReceivedListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedDetailsResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedDetailsType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetMessageReceivedListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetMessageReceivedListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetNextOfKinListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetNextOfKinListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetNextOfKinListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetNextOfKinListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPatientResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetPatientResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPatientType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetPatientType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPotentialIssueStatusListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPotentialIssueStatusListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetReportTemplatesResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetReportTemplatesResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetReportTemplatesType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetReportTemplatesType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetSubmitterProfileResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetSubmitterProfileResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetSubmitterProfileType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetSubmitterProfileType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetVaccinationListResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetVaccinationListResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetVaccinationListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.GetVaccinationListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueFoundListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.IssueFoundType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueFoundType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueFoundList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueFoundType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.IssueFoundType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.IssueType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.IssueType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageBatchListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.MessageBatchType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageBatchType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageBatchList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageBatchType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.MessageBatchType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageReceivedDetailsType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.MessageReceivedDetailsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageReceivedListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.MessageReceivedType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageReceivedType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageReceivedList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageReceivedType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.MessageReceivedType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "NextOfKinListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.NextOfKinType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "NextOfKinType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nextOfKinList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "NextOfKinType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.NextOfKinType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "PatientType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.PatientType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "PotentialIssueStatusListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.PotentialIssueStatusType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "PotentialIssueStatusType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "potentialIssueStatusList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "PotentialIssueStatusType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.PotentialIssueStatusType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "ReportTemplatesListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.ReportTemplateType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "ReportTemplateType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "reportTemplateList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "ReportTemplateType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.ReportTemplateType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.SubmitMessageResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.SubmitMessageType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitterProfileType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.SubmitterProfileType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateCodeReceivedResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateCodeReceivedType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdatePotentialIssueStatusResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdatePotentialIssueStatusType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateSubmitterProfileResultType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateSubmitterProfileType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "VaccinationListType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.VaccinationType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "VaccinationType");
            qName2 = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccinationList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "VaccinationType");
            cachedSerQNames.add(qName);
            cls = org.openimmunizationsoftware.dqa.service.VaccinationType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public org.openimmunizationsoftware.dqa.service.SubmitMessageResultType submitMessage(org.openimmunizationsoftware.dqa.service.SubmitMessageType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/submitMessage");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "submitMessage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.SubmitMessageResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.SubmitMessageResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.SubmitMessageResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchesResultType getBatches(org.openimmunizationsoftware.dqa.service.GetBatchesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatches");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatches"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchesResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchesResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchesResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchDqaReportResultType getBatchDqaReport(org.openimmunizationsoftware.dqa.service.GetBatchDqaReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatchDqaReport");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatchDqaReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchDqaReportResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchDqaReportResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchDqaReportResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.CreateMessageBatchResultType createMessageBatch(org.openimmunizationsoftware.dqa.service.CreateMessageBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/createMessageBatch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "createMessageBatch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.CreateMessageBatchResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.CreateMessageBatchResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.CreateMessageBatchResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileResultType updateSubmitterProfile(org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/updateSubmitterProfile");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateSubmitterProfile"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.UpdateSubmitterProfileResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetSubmitterProfileResultType getSubmitterProfile(org.openimmunizationsoftware.dqa.service.GetSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getSubmitterProfile");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getSubmitterProfile"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetSubmitterProfileResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetSubmitterProfileResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetSubmitterProfileResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetReportTemplatesResultType getReportTemplates(org.openimmunizationsoftware.dqa.service.GetReportTemplatesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getReportTemplates");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getReportTemplates"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetReportTemplatesResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetReportTemplatesResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetReportTemplatesResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetCodeReceivedListResultType getCodeReceivedList(org.openimmunizationsoftware.dqa.service.GetCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getCodeReceivedList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getCodeReceivedList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetCodeReceivedListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetCodeReceivedListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetCodeReceivedListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedResultType updateCodeReceived(org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/updateCodeReceived");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateCodeReceived"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.UpdateCodeReceivedResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListResultType getPotentialIssueStatusList(org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getPotentialIssueStatusList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getPotentialIssueStatusList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetPotentialIssueStatusListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/updatePotentialIssueStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updatePotentialIssueStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.UpdatePotentialIssueStatusResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchResultType getBatch(org.openimmunizationsoftware.dqa.service.GetBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchIssueListResultType getBatchIssueList(org.openimmunizationsoftware.dqa.service.GetBatchIssueListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatchIssueList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatchIssueList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchIssueListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchIssueListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchIssueListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchReportResultType getBatchReport(org.openimmunizationsoftware.dqa.service.GetBatchReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatchReport");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatchReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchReportResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchReportResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchReportResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchActionListResultType getBatchActionList(org.openimmunizationsoftware.dqa.service.GetBatchActionListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatchActionList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatchActionList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchActionListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchActionListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchActionListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListResultType getBatchVaccineCvxList(org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatchVaccineCvxList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatchVaccineCvxList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchVaccineCvxListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListResultType getBatchCodeReceivedList(org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getBatchCodeReceivedList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBatchCodeReceivedList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetBatchCodeReceivedListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetMessageReceivedListResultType getMessageReceivedList(org.openimmunizationsoftware.dqa.service.GetMessageReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getMessageReceivedList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMessageReceivedList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetMessageReceivedListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetMessageReceivedListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetMessageReceivedListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsResultType getMessageReceivedDetails(org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getMessageReceivedDetails");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMessageReceivedDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetMessageReceivedDetailsResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetPatientResultType getPatient(org.openimmunizationsoftware.dqa.service.GetPatientType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getPatient");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getPatient"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetPatientResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetPatientResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetPatientResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetVaccinationListResultType getVaccinationList(org.openimmunizationsoftware.dqa.service.GetVaccinationListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getVaccinationList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getVaccinationList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetVaccinationListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetVaccinationListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetVaccinationListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.openimmunizationsoftware.dqa.service.GetNextOfKinListResultType getNextOfKinList(org.openimmunizationsoftware.dqa.service.GetNextOfKinListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dqaws.openimmunizationsoftware.org/dqa/getNextOfKinList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getNextOfKinList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.openimmunizationsoftware.dqa.service.GetNextOfKinListResultType) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.openimmunizationsoftware.dqa.service.GetNextOfKinListResultType) org.apache.axis.utils.JavaUtils.convert(_resp, org.openimmunizationsoftware.dqa.service.GetNextOfKinListResultType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.openimmunizationsoftware.dqa.service.FaultType) {
              throw (org.openimmunizationsoftware.dqa.service.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
