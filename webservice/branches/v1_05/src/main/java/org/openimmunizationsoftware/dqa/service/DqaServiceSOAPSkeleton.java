/**
 * DqaServiceSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class DqaServiceSOAPSkeleton implements org.openimmunizationsoftware.dqa.service.DqaService_PortType, org.apache.axis.wsdl.Skeleton {
    private org.openimmunizationsoftware.dqa.service.DqaService_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "submitMessageRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageType"), org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("submitMessage", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "submitMessageResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "submitMessage"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/submitMessage");
        _myOperationsList.add(_oper);
        if (_myOperations.get("submitMessage") == null) {
            _myOperations.put("submitMessage", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("submitMessage")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchesType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchesType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatches", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchesResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchesResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatches"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatches");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatches") == null) {
            _myOperations.put("getBatches", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatches")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchDqaReportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchDqaReportType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatchDqaReport", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchDqaReportResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchDqaReportResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatchDqaReport"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatchDqaReport");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatchDqaReport") == null) {
            _myOperations.put("getBatchDqaReport", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatchDqaReport")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "createMessageBatchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CreateMessageBatchType"), org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createMessageBatch", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "createMessageBatchResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CreateMessageBatchResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "createMessageBatch"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/createMessageBatch");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createMessageBatch") == null) {
            _myOperations.put("createMessageBatch", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createMessageBatch")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateSubmitterProfileRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateSubmitterProfileType"), org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("updateSubmitterProfile", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateSubmitterProfileResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateSubmitterProfileResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "updateSubmitterProfile"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/updateSubmitterProfile");
        _myOperationsList.add(_oper);
        if (_myOperations.get("updateSubmitterProfile") == null) {
            _myOperations.put("updateSubmitterProfile", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("updateSubmitterProfile")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getSubmitterProfileRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetSubmitterProfileType"), org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getSubmitterProfile", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getSubmitterProfileResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetSubmitterProfileResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getSubmitterProfile"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getSubmitterProfile");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getSubmitterProfile") == null) {
            _myOperations.put("getSubmitterProfile", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getSubmitterProfile")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getReportTemplatesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetReportTemplatesType"), org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getReportTemplates", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getReportTemplatesResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetReportTemplatesResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getReportTemplates"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getReportTemplates");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getReportTemplates") == null) {
            _myOperations.put("getReportTemplates", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getReportTemplates")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getCodeReceivedListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetCodeReceivedListType"), org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getCodeReceivedList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getCodeReceivedListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetCodeReceivedListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getCodeReceivedList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getCodeReceivedList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getCodeReceivedList") == null) {
            _myOperations.put("getCodeReceivedList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getCodeReceivedList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateCodeReceivedRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateCodeReceivedType"), org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("updateCodeReceived", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updateCodeReceivedResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateCodeReceivedResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "updateCodeReceived"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/updateCodeReceived");
        _myOperationsList.add(_oper);
        if (_myOperations.get("updateCodeReceived") == null) {
            _myOperations.put("updateCodeReceived", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("updateCodeReceived")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPotentialIssueStatusListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPotentialIssueStatusListType"), org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getPotentialIssueStatusList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPotentialIssueStatusListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPotentialIssueStatusListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getPotentialIssueStatusList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getPotentialIssueStatusList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getPotentialIssueStatusList") == null) {
            _myOperations.put("getPotentialIssueStatusList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getPotentialIssueStatusList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updatePotentialIssueStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdatePotentialIssueStatusType"), org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("updatePotentialIssueStatus", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "updatePotentialIssueStatusResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdatePotentialIssueStatusResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "updatePotentialIssueStatus"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/updatePotentialIssueStatus");
        _myOperationsList.add(_oper);
        if (_myOperations.get("updatePotentialIssueStatus") == null) {
            _myOperations.put("updatePotentialIssueStatus", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("updatePotentialIssueStatus")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatch", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatch"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatch");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatch") == null) {
            _myOperations.put("getBatch", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatch")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchIssueListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchIssueListType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatchIssueList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchIssueListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchIssueListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatchIssueList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatchIssueList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatchIssueList") == null) {
            _myOperations.put("getBatchIssueList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatchIssueList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchReportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchReportType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchReportType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatchReport", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchReportResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchReportResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatchReport"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatchReport");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatchReport") == null) {
            _myOperations.put("getBatchReport", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatchReport")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchActionListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchActionListType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatchActionList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchActionListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchActionListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatchActionList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatchActionList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatchActionList") == null) {
            _myOperations.put("getBatchActionList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatchActionList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchVaccineCvxListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchVaccineCvxListType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatchVaccineCvxList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchVaccineCvxListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchVaccineCvxListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatchVaccineCvxList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatchVaccineCvxList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatchVaccineCvxList") == null) {
            _myOperations.put("getBatchVaccineCvxList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatchVaccineCvxList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchCodeReceivedListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchCodeReceivedListType"), org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBatchCodeReceivedList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getBatchCodeReceivedListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchCodeReceivedListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getBatchCodeReceivedList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getBatchCodeReceivedList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBatchCodeReceivedList") == null) {
            _myOperations.put("getBatchCodeReceivedList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBatchCodeReceivedList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedListType"), org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getMessageReceivedList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getMessageReceivedList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getMessageReceivedList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getMessageReceivedList") == null) {
            _myOperations.put("getMessageReceivedList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getMessageReceivedList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedDetailsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedDetailsType"), org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getMessageReceivedDetails", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getMessageReceivedDetailsResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedDetailsResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getMessageReceivedDetails"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getMessageReceivedDetails");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getMessageReceivedDetails") == null) {
            _myOperations.put("getMessageReceivedDetails", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getMessageReceivedDetails")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPatientRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPatientType"), org.openimmunizationsoftware.dqa.service.schema.GetPatientType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getPatient", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getPatientResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPatientResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getPatient"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getPatient");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getPatient") == null) {
            _myOperations.put("getPatient", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getPatient")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getVaccinationListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetVaccinationListType"), org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getVaccinationList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getVaccinationListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetVaccinationListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getVaccinationList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getVaccinationList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getVaccinationList") == null) {
            _myOperations.put("getVaccinationList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getVaccinationList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getNextOfKinListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetNextOfKinListType"), org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getNextOfKinList", _params, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "getNextOfKinListResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetNextOfKinListResultType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "getNextOfKinList"));
        _oper.setSoapAction("http://dqaws.openimmunizationsoftware.org/dqa/getNextOfKinList");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getNextOfKinList") == null) {
            _myOperations.put("getNextOfKinList", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getNextOfKinList")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("fault");
        _fault.setQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "Fault"));
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.schema.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
    }

    public DqaServiceSOAPSkeleton() {
        this.impl = new org.openimmunizationsoftware.dqa.service.DqaServiceSOAPImpl();
    }

    public DqaServiceSOAPSkeleton(org.openimmunizationsoftware.dqa.service.DqaService_PortType impl) {
        this.impl = impl;
    }
    public org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType submitMessage(org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType ret = impl.submitMessage(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchesResultType getBatches(org.openimmunizationsoftware.dqa.service.schema.GetBatchesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchesResultType ret = impl.getBatches(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportResultType getBatchDqaReport(org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchDqaReportResultType ret = impl.getBatchDqaReport(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchResultType createMessageBatch(org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.CreateMessageBatchResultType ret = impl.createMessageBatch(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileResultType updateSubmitterProfile(org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.UpdateSubmitterProfileResultType ret = impl.updateSubmitterProfile(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileResultType getSubmitterProfile(org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetSubmitterProfileResultType ret = impl.getSubmitterProfile(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesResultType getReportTemplates(org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetReportTemplatesResultType ret = impl.getReportTemplates(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListResultType getCodeReceivedList(org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetCodeReceivedListResultType ret = impl.getCodeReceivedList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedResultType updateCodeReceived(org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.UpdateCodeReceivedResultType ret = impl.updateCodeReceived(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListResultType getPotentialIssueStatusList(org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetPotentialIssueStatusListResultType ret = impl.getPotentialIssueStatusList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusResultType updatePotentialIssueStatus(org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.UpdatePotentialIssueStatusResultType ret = impl.updatePotentialIssueStatus(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchResultType getBatch(org.openimmunizationsoftware.dqa.service.schema.GetBatchType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchResultType ret = impl.getBatch(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListResultType getBatchIssueList(org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchIssueListResultType ret = impl.getBatchIssueList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchReportResultType getBatchReport(org.openimmunizationsoftware.dqa.service.schema.GetBatchReportType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchReportResultType ret = impl.getBatchReport(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListResultType getBatchActionList(org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchActionListResultType ret = impl.getBatchActionList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListResultType getBatchVaccineCvxList(org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchVaccineCvxListResultType ret = impl.getBatchVaccineCvxList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListResultType getBatchCodeReceivedList(org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetBatchCodeReceivedListResultType ret = impl.getBatchCodeReceivedList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListResultType getMessageReceivedList(org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedListResultType ret = impl.getMessageReceivedList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsResultType getMessageReceivedDetails(org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetMessageReceivedDetailsResultType ret = impl.getMessageReceivedDetails(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetPatientResultType getPatient(org.openimmunizationsoftware.dqa.service.schema.GetPatientType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetPatientResultType ret = impl.getPatient(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListResultType getVaccinationList(org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetVaccinationListResultType ret = impl.getVaccinationList(request);
        return ret;
    }

    public org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListResultType getNextOfKinList(org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.schema.FaultType
    {
        org.openimmunizationsoftware.dqa.service.schema.GetNextOfKinListResultType ret = impl.getNextOfKinList(request);
        return ret;
    }

}
