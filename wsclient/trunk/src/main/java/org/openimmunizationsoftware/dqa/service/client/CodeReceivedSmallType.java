/**
 * CodeReceivedSmallType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class CodeReceivedSmallType  implements java.io.Serializable {
    private long codeId;

    private java.lang.String codeValue;

    private java.lang.String codeStatus;

    private long receivedCount;

    public CodeReceivedSmallType() {
    }

    public CodeReceivedSmallType(
           long codeId,
           java.lang.String codeValue,
           java.lang.String codeStatus,
           long receivedCount) {
           this.codeId = codeId;
           this.codeValue = codeValue;
           this.codeStatus = codeStatus;
           this.receivedCount = receivedCount;
    }


    /**
     * Gets the codeId value for this CodeReceivedSmallType.
     * 
     * @return codeId
     */
    public long getCodeId() {
        return codeId;
    }


    /**
     * Sets the codeId value for this CodeReceivedSmallType.
     * 
     * @param codeId
     */
    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }


    /**
     * Gets the codeValue value for this CodeReceivedSmallType.
     * 
     * @return codeValue
     */
    public java.lang.String getCodeValue() {
        return codeValue;
    }


    /**
     * Sets the codeValue value for this CodeReceivedSmallType.
     * 
     * @param codeValue
     */
    public void setCodeValue(java.lang.String codeValue) {
        this.codeValue = codeValue;
    }


    /**
     * Gets the codeStatus value for this CodeReceivedSmallType.
     * 
     * @return codeStatus
     */
    public java.lang.String getCodeStatus() {
        return codeStatus;
    }


    /**
     * Sets the codeStatus value for this CodeReceivedSmallType.
     * 
     * @param codeStatus
     */
    public void setCodeStatus(java.lang.String codeStatus) {
        this.codeStatus = codeStatus;
    }


    /**
     * Gets the receivedCount value for this CodeReceivedSmallType.
     * 
     * @return receivedCount
     */
    public long getReceivedCount() {
        return receivedCount;
    }


    /**
     * Sets the receivedCount value for this CodeReceivedSmallType.
     * 
     * @param receivedCount
     */
    public void setReceivedCount(long receivedCount) {
        this.receivedCount = receivedCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodeReceivedSmallType)) return false;
        CodeReceivedSmallType other = (CodeReceivedSmallType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codeId == other.getCodeId() &&
            ((this.codeValue==null && other.getCodeValue()==null) || 
             (this.codeValue!=null &&
              this.codeValue.equals(other.getCodeValue()))) &&
            ((this.codeStatus==null && other.getCodeStatus()==null) || 
             (this.codeStatus!=null &&
              this.codeStatus.equals(other.getCodeStatus()))) &&
            this.receivedCount == other.getReceivedCount();
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
        _hashCode += new Long(getCodeId()).hashCode();
        if (getCodeValue() != null) {
            _hashCode += getCodeValue().hashCode();
        }
        if (getCodeStatus() != null) {
            _hashCode += getCodeStatus().hashCode();
        }
        _hashCode += new Long(getReceivedCount()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodeReceivedSmallType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedSmallType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivedCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "receivedCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
