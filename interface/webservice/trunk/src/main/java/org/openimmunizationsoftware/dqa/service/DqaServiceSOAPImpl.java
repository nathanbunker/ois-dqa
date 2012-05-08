/**
 * DqaServiceSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class DqaServiceSOAPImpl implements org.openimmunizationsoftware.dqa.service.DqaService_PortType{
    public org.openimmunizationsoftware.dqa.service.SubmitMessageResultType submitMessage(org.openimmunizationsoftware.dqa.service.SubmitMessageType request) throws java.rmi.RemoteException, org.openimmunizationsoftware.dqa.service.FaultType {
      return DqaServiceSupport.submitMessage(request);
    }

}
