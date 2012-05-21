/**
 * BatchVaccineCvxType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class BatchVaccineCvxType  implements java.io.Serializable {
    private long batchVaccineCvxId;

    private long batchId;

    private java.lang.String cvxCode;

    private java.lang.String cvxLabel;

    private long receivedCount;

    public BatchVaccineCvxType() {
    }

    public BatchVaccineCvxType(
           long batchVaccineCvxId,
           long batchId,
           java.lang.String cvxCode,
           java.lang.String cvxLabel,
           long receivedCount) {
           this.batchVaccineCvxId = batchVaccineCvxId;
           this.batchId = batchId;
           this.cvxCode = cvxCode;
           this.cvxLabel = cvxLabel;
           this.receivedCount = receivedCount;
    }


    /**
     * Gets the batchVaccineCvxId value for this BatchVaccineCvxType.
     * 
     * @return batchVaccineCvxId
     */
    public long getBatchVaccineCvxId() {
        return batchVaccineCvxId;
    }


    /**
     * Sets the batchVaccineCvxId value for this BatchVaccineCvxType.
     * 
     * @param batchVaccineCvxId
     */
    public void setBatchVaccineCvxId(long batchVaccineCvxId) {
        this.batchVaccineCvxId = batchVaccineCvxId;
    }


    /**
     * Gets the batchId value for this BatchVaccineCvxType.
     * 
     * @return batchId
     */
    public long getBatchId() {
        return batchId;
    }


    /**
     * Sets the batchId value for this BatchVaccineCvxType.
     * 
     * @param batchId
     */
    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }


    /**
     * Gets the cvxCode value for this BatchVaccineCvxType.
     * 
     * @return cvxCode
     */
    public java.lang.String getCvxCode() {
        return cvxCode;
    }


    /**
     * Sets the cvxCode value for this BatchVaccineCvxType.
     * 
     * @param cvxCode
     */
    public void setCvxCode(java.lang.String cvxCode) {
        this.cvxCode = cvxCode;
    }


    /**
     * Gets the cvxLabel value for this BatchVaccineCvxType.
     * 
     * @return cvxLabel
     */
    public java.lang.String getCvxLabel() {
        return cvxLabel;
    }


    /**
     * Sets the cvxLabel value for this BatchVaccineCvxType.
     * 
     * @param cvxLabel
     */
    public void setCvxLabel(java.lang.String cvxLabel) {
        this.cvxLabel = cvxLabel;
    }


    /**
     * Gets the receivedCount value for this BatchVaccineCvxType.
     * 
     * @return receivedCount
     */
    public long getReceivedCount() {
        return receivedCount;
    }


    /**
     * Sets the receivedCount value for this BatchVaccineCvxType.
     * 
     * @param receivedCount
     */
    public void setReceivedCount(long receivedCount) {
        this.receivedCount = receivedCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BatchVaccineCvxType)) return false;
        BatchVaccineCvxType other = (BatchVaccineCvxType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.batchVaccineCvxId == other.getBatchVaccineCvxId() &&
            this.batchId == other.getBatchId() &&
            ((this.cvxCode==null && other.getCvxCode()==null) || 
             (this.cvxCode!=null &&
              this.cvxCode.equals(other.getCvxCode()))) &&
            ((this.cvxLabel==null && other.getCvxLabel()==null) || 
             (this.cvxLabel!=null &&
              this.cvxLabel.equals(other.getCvxLabel()))) &&
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
        _hashCode += new Long(getBatchVaccineCvxId()).hashCode();
        _hashCode += new Long(getBatchId()).hashCode();
        if (getCvxCode() != null) {
            _hashCode += getCvxCode().hashCode();
        }
        if (getCvxLabel() != null) {
            _hashCode += getCvxLabel().hashCode();
        }
        _hashCode += new Long(getReceivedCount()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BatchVaccineCvxType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchVaccineCvxType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchVaccineCvxId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchVaccineCvxId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cvxCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "cvxCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cvxLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "cvxLabel"));
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
