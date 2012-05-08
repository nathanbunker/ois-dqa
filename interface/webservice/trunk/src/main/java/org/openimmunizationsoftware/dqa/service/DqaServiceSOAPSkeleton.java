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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "submitMessageRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageType"), org.openimmunizationsoftware.dqa.service.SubmitMessageType.class, false, false), 
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
        _fault.setClassName("org.openimmunizationsoftware.dqa.service.FaultType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "FaultType"));
        _oper.addFault(_fault);
    }

    public DqaServiceSOAPSkeleton() {
        this.impl = new org.openimmunizationsoftware.dqa.service.DqaServiceSOAPImpl();
    }

    public DqaServiceSOAPSkeleton(org.openimmunizationsoftware.dqa.service.DqaService_PortType impl) {
        this.impl = impl;
    }
    public org.openimmunizationsoftware.dqa.service.SubmitMessageResultType submitMessage(org.openimmunizationsoftware.dqa.service.SubmitMessageType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType
    {
        org.openimmunizationsoftware.dqa.service.SubmitMessageResultType ret = impl.submitMessage(request);
        return ret;
    }

}
