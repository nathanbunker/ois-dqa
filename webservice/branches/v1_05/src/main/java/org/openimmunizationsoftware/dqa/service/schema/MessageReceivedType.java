/**
 * MessageReceivedType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.schema;

public class MessageReceivedType  implements java.io.Serializable {
    private long receivedId;

    private long profileId;

    private java.lang.String receivedDate;

    private java.lang.String actionCode;

    private java.lang.String actionLabel;

    private java.lang.String submitCode;

    private java.lang.String submitLabel;

    public MessageReceivedType() {
    }

    public MessageReceivedType(
           long receivedId,
           long profileId,
           java.lang.String receivedDate,
           java.lang.String actionCode,
           java.lang.String actionLabel,
           java.lang.String submitCode,
           java.lang.String submitLabel) {
           this.receivedId = receivedId;
           this.profileId = profileId;
           this.receivedDate = receivedDate;
           this.actionCode = actionCode;
           this.actionLabel = actionLabel;
           this.submitCode = submitCode;
           this.submitLabel = submitLabel;
    }


    /**
     * Gets the receivedId value for this MessageReceivedType.
     * 
     * @return receivedId
     */
    public long getReceivedId() {
        return receivedId;
    }


    /**
     * Sets the receivedId value for this MessageReceivedType.
     * 
     * @param receivedId
     */
    public void setReceivedId(long receivedId) {
        this.receivedId = receivedId;
    }


    /**
     * Gets the profileId value for this MessageReceivedType.
     * 
     * @return profileId
     */
    public long getProfileId() {
        return profileId;
    }


    /**
     * Sets the profileId value for this MessageReceivedType.
     * 
     * @param profileId
     */
    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }


    /**
     * Gets the receivedDate value for this MessageReceivedType.
     * 
     * @return receivedDate
     */
    public java.lang.String getReceivedDate() {
        return receivedDate;
    }


    /**
     * Sets the receivedDate value for this MessageReceivedType.
     * 
     * @param receivedDate
     */
    public void setReceivedDate(java.lang.String receivedDate) {
        this.receivedDate = receivedDate;
    }


    /**
     * Gets the actionCode value for this MessageReceivedType.
     * 
     * @return actionCode
     */
    public java.lang.String getActionCode() {
        return actionCode;
    }


    /**
     * Sets the actionCode value for this MessageReceivedType.
     * 
     * @param actionCode
     */
    public void setActionCode(java.lang.String actionCode) {
        this.actionCode = actionCode;
    }


    /**
     * Gets the actionLabel value for this MessageReceivedType.
     * 
     * @return actionLabel
     */
    public java.lang.String getActionLabel() {
        return actionLabel;
    }


    /**
     * Sets the actionLabel value for this MessageReceivedType.
     * 
     * @param actionLabel
     */
    public void setActionLabel(java.lang.String actionLabel) {
        this.actionLabel = actionLabel;
    }


    /**
     * Gets the submitCode value for this MessageReceivedType.
     * 
     * @return submitCode
     */
    public java.lang.String getSubmitCode() {
        return submitCode;
    }


    /**
     * Sets the submitCode value for this MessageReceivedType.
     * 
     * @param submitCode
     */
    public void setSubmitCode(java.lang.String submitCode) {
        this.submitCode = submitCode;
    }


    /**
     * Gets the submitLabel value for this MessageReceivedType.
     * 
     * @return submitLabel
     */
    public java.lang.String getSubmitLabel() {
        return submitLabel;
    }


    /**
     * Sets the submitLabel value for this MessageReceivedType.
     * 
     * @param submitLabel
     */
    public void setSubmitLabel(java.lang.String submitLabel) {
        this.submitLabel = submitLabel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageReceivedType)) return false;
        MessageReceivedType other = (MessageReceivedType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.receivedId == other.getReceivedId() &&
            this.profileId == other.getProfileId() &&
            ((this.receivedDate==null && other.getReceivedDate()==null) || 
             (this.receivedDate!=null &&
              this.receivedDate.equals(other.getReceivedDate()))) &&
            ((this.actionCode==null && other.getActionCode()==null) || 
             (this.actionCode!=null &&
              this.actionCode.equals(other.getActionCode()))) &&
            ((this.actionLabel==null && other.getActionLabel()==null) || 
             (this.actionLabel!=null &&
              this.actionLabel.equals(other.getActionLabel()))) &&
            ((this.submitCode==null && other.getSubmitCode()==null) || 
             (this.submitCode!=null &&
              this.submitCode.equals(other.getSubmitCode()))) &&
            ((this.submitLabel==null && other.getSubmitLabel()==null) || 
             (this.submitLabel!=null &&
              this.submitLabel.equals(other.getSubmitLabel())));
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
        _hashCode += new Long(getReceivedId()).hashCode();
        _hashCode += new Long(getProfileId()).hashCode();
        if (getReceivedDate() != null) {
            _hashCode += getReceivedDate().hashCode();
        }
        if (getActionCode() != null) {
            _hashCode += getActionCode().hashCode();
        }
        if (getActionLabel() != null) {
            _hashCode += getActionLabel().hashCode();
        }
        if (getSubmitCode() != null) {
            _hashCode += getSubmitCode().hashCode();
        }
        if (getSubmitLabel() != null) {
            _hashCode += getSubmitLabel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessageReceivedType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageReceivedType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivedId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "receivedId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "receivedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "actionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "actionLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submitCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "submitCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submitLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "submitLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
