/**
 * CreateMessageBatchType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class CreateMessageBatchType  implements java.io.Serializable {
    private long profileId;

    private java.lang.String startDate;

    private java.lang.String endDate;

    private java.lang.String sourceSubmitCode;

    private java.lang.String sourceTypeCode;

    public CreateMessageBatchType() {
    }

    public CreateMessageBatchType(
           long profileId,
           java.lang.String startDate,
           java.lang.String endDate,
           java.lang.String sourceSubmitCode,
           java.lang.String sourceTypeCode) {
           this.profileId = profileId;
           this.startDate = startDate;
           this.endDate = endDate;
           this.sourceSubmitCode = sourceSubmitCode;
           this.sourceTypeCode = sourceTypeCode;
    }


    /**
     * Gets the profileId value for this CreateMessageBatchType.
     * 
     * @return profileId
     */
    public long getProfileId() {
        return profileId;
    }


    /**
     * Sets the profileId value for this CreateMessageBatchType.
     * 
     * @param profileId
     */
    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }


    /**
     * Gets the startDate value for this CreateMessageBatchType.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this CreateMessageBatchType.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the endDate value for this CreateMessageBatchType.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this CreateMessageBatchType.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the sourceSubmitCode value for this CreateMessageBatchType.
     * 
     * @return sourceSubmitCode
     */
    public java.lang.String getSourceSubmitCode() {
        return sourceSubmitCode;
    }


    /**
     * Sets the sourceSubmitCode value for this CreateMessageBatchType.
     * 
     * @param sourceSubmitCode
     */
    public void setSourceSubmitCode(java.lang.String sourceSubmitCode) {
        this.sourceSubmitCode = sourceSubmitCode;
    }


    /**
     * Gets the sourceTypeCode value for this CreateMessageBatchType.
     * 
     * @return sourceTypeCode
     */
    public java.lang.String getSourceTypeCode() {
        return sourceTypeCode;
    }


    /**
     * Sets the sourceTypeCode value for this CreateMessageBatchType.
     * 
     * @param sourceTypeCode
     */
    public void setSourceTypeCode(java.lang.String sourceTypeCode) {
        this.sourceTypeCode = sourceTypeCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateMessageBatchType)) return false;
        CreateMessageBatchType other = (CreateMessageBatchType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.profileId == other.getProfileId() &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.sourceSubmitCode==null && other.getSourceSubmitCode()==null) || 
             (this.sourceSubmitCode!=null &&
              this.sourceSubmitCode.equals(other.getSourceSubmitCode()))) &&
            ((this.sourceTypeCode==null && other.getSourceTypeCode()==null) || 
             (this.sourceTypeCode!=null &&
              this.sourceTypeCode.equals(other.getSourceTypeCode())));
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
        _hashCode += new Long(getProfileId()).hashCode();
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getSourceSubmitCode() != null) {
            _hashCode += getSourceSubmitCode().hashCode();
        }
        if (getSourceTypeCode() != null) {
            _hashCode += getSourceTypeCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateMessageBatchType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CreateMessageBatchType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSubmitCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "sourceSubmitCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "sourceTypeCode"));
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
