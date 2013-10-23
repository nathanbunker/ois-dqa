/**
 * SubmitMessageType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class SubmitMessageType  implements java.io.Serializable {
    private java.lang.String profileCode;

    private java.lang.String profileLabel;

    private long orgLocalCode;

    private java.lang.String messageRequest;

    private java.lang.String processMode;

    private java.lang.String batchInstruction;

    public SubmitMessageType() {
    }

    public SubmitMessageType(
           java.lang.String profileCode,
           java.lang.String profileLabel,
           long orgLocalCode,
           java.lang.String messageRequest,
           java.lang.String processMode,
           java.lang.String batchInstruction) {
           this.profileCode = profileCode;
           this.profileLabel = profileLabel;
           this.orgLocalCode = orgLocalCode;
           this.messageRequest = messageRequest;
           this.processMode = processMode;
           this.batchInstruction = batchInstruction;
    }


    /**
     * Gets the profileCode value for this SubmitMessageType.
     * 
     * @return profileCode
     */
    public java.lang.String getProfileCode() {
        return profileCode;
    }


    /**
     * Sets the profileCode value for this SubmitMessageType.
     * 
     * @param profileCode
     */
    public void setProfileCode(java.lang.String profileCode) {
        this.profileCode = profileCode;
    }


    /**
     * Gets the profileLabel value for this SubmitMessageType.
     * 
     * @return profileLabel
     */
    public java.lang.String getProfileLabel() {
        return profileLabel;
    }


    /**
     * Sets the profileLabel value for this SubmitMessageType.
     * 
     * @param profileLabel
     */
    public void setProfileLabel(java.lang.String profileLabel) {
        this.profileLabel = profileLabel;
    }


    /**
     * Gets the orgLocalCode value for this SubmitMessageType.
     * 
     * @return orgLocalCode
     */
    public long getOrgLocalCode() {
        return orgLocalCode;
    }


    /**
     * Sets the orgLocalCode value for this SubmitMessageType.
     * 
     * @param orgLocalCode
     */
    public void setOrgLocalCode(long orgLocalCode) {
        this.orgLocalCode = orgLocalCode;
    }


    /**
     * Gets the messageRequest value for this SubmitMessageType.
     * 
     * @return messageRequest
     */
    public java.lang.String getMessageRequest() {
        return messageRequest;
    }


    /**
     * Sets the messageRequest value for this SubmitMessageType.
     * 
     * @param messageRequest
     */
    public void setMessageRequest(java.lang.String messageRequest) {
        this.messageRequest = messageRequest;
    }


    /**
     * Gets the processMode value for this SubmitMessageType.
     * 
     * @return processMode
     */
    public java.lang.String getProcessMode() {
        return processMode;
    }


    /**
     * Sets the processMode value for this SubmitMessageType.
     * 
     * @param processMode
     */
    public void setProcessMode(java.lang.String processMode) {
        this.processMode = processMode;
    }


    /**
     * Gets the batchInstruction value for this SubmitMessageType.
     * 
     * @return batchInstruction
     */
    public java.lang.String getBatchInstruction() {
        return batchInstruction;
    }


    /**
     * Sets the batchInstruction value for this SubmitMessageType.
     * 
     * @param batchInstruction
     */
    public void setBatchInstruction(java.lang.String batchInstruction) {
        this.batchInstruction = batchInstruction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubmitMessageType)) return false;
        SubmitMessageType other = (SubmitMessageType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.profileCode==null && other.getProfileCode()==null) || 
             (this.profileCode!=null &&
              this.profileCode.equals(other.getProfileCode()))) &&
            ((this.profileLabel==null && other.getProfileLabel()==null) || 
             (this.profileLabel!=null &&
              this.profileLabel.equals(other.getProfileLabel()))) &&
            this.orgLocalCode == other.getOrgLocalCode() &&
            ((this.messageRequest==null && other.getMessageRequest()==null) || 
             (this.messageRequest!=null &&
              this.messageRequest.equals(other.getMessageRequest()))) &&
            ((this.processMode==null && other.getProcessMode()==null) || 
             (this.processMode!=null &&
              this.processMode.equals(other.getProcessMode()))) &&
            ((this.batchInstruction==null && other.getBatchInstruction()==null) || 
             (this.batchInstruction!=null &&
              this.batchInstruction.equals(other.getBatchInstruction())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getProfileCode() != null) {
            _hashCode += getProfileCode().hashCode();
        }
        if (getProfileLabel() != null) {
            _hashCode += getProfileLabel().hashCode();
        }
        _hashCode += new Long(getOrgLocalCode()).hashCode();
        if (getMessageRequest() != null) {
            _hashCode += getMessageRequest().hashCode();
        }
        if (getProcessMode() != null) {
            _hashCode += getProcessMode().hashCode();
        }
        if (getBatchInstruction() != null) {
            _hashCode += getBatchInstruction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubmitMessageType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgLocalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "orgLocalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "processMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchInstruction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchInstruction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
