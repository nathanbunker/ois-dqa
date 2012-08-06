/**
 * DqaService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class DqaService_ServiceLocator extends org.apache.axis.client.Service implements org.openimmunizationsoftware.dqa.service.DqaService_Service {

    public DqaService_ServiceLocator() {
    }


    public DqaService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DqaService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DqaServiceSOAP
    private java.lang.String DqaServiceSOAP_address = "http://localhost:8080/dqa/services/DqaServiceSOAP";

    public java.lang.String getDqaServiceSOAPAddress() {
        return DqaServiceSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DqaServiceSOAPWSDDServiceName = "DqaServiceSOAP";

    public java.lang.String getDqaServiceSOAPWSDDServiceName() {
        return DqaServiceSOAPWSDDServiceName;
    }

    public void setDqaServiceSOAPWSDDServiceName(java.lang.String name) {
        DqaServiceSOAPWSDDServiceName = name;
    }

    public org.openimmunizationsoftware.dqa.service.DqaService_PortType getDqaServiceSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DqaServiceSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDqaServiceSOAP(endpoint);
    }

    public org.openimmunizationsoftware.dqa.service.DqaService_PortType getDqaServiceSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.openimmunizationsoftware.dqa.service.DqaServiceSOAPStub _stub = new org.openimmunizationsoftware.dqa.service.DqaServiceSOAPStub(portAddress, this);
            _stub.setPortName(getDqaServiceSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDqaServiceSOAPEndpointAddress(java.lang.String address) {
        DqaServiceSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.openimmunizationsoftware.dqa.service.DqaService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.openimmunizationsoftware.dqa.service.DqaServiceSOAPStub _stub = new org.openimmunizationsoftware.dqa.service.DqaServiceSOAPStub(new java.net.URL(DqaServiceSOAP_address), this);
                _stub.setPortName(getDqaServiceSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DqaServiceSOAP".equals(inputPortName)) {
            return getDqaServiceSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "DqaService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/", "DqaServiceSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DqaServiceSOAP".equals(portName)) {
            setDqaServiceSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
