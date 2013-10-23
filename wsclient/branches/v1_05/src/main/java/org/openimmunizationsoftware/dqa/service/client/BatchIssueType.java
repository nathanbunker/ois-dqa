/**
 * BatchIssueType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class BatchIssueType  implements java.io.Serializable {
    private long batchIssueId;

    private long batchId;

    private long issueId;

    private java.lang.String issueText;

    private long issueCount;

    public BatchIssueType() {
    }

    public BatchIssueType(
           long batchIssueId,
           long batchId,
           long issueId,
           java.lang.String issueText,
           long issueCount) {
           this.batchIssueId = batchIssueId;
           this.batchId = batchId;
           this.issueId = issueId;
           this.issueText = issueText;
           this.issueCount = issueCount;
    }


    /**
     * Gets the batchIssueId value for this BatchIssueType.
     * 
     * @return batchIssueId
     */
    public long getBatchIssueId() {
        return batchIssueId;
    }


    /**
     * Sets the batchIssueId value for this BatchIssueType.
     * 
     * @param batchIssueId
     */
    public void setBatchIssueId(long batchIssueId) {
        this.batchIssueId = batchIssueId;
    }


    /**
     * Gets the batchId value for this BatchIssueType.
     * 
     * @return batchId
     */
    public long getBatchId() {
        return batchId;
    }


    /**
     * Sets the batchId value for this BatchIssueType.
     * 
     * @param batchId
     */
    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }


    /**
     * Gets the issueId value for this BatchIssueType.
     * 
     * @return issueId
     */
    public long getIssueId() {
        return issueId;
    }


    /**
     * Sets the issueId value for this BatchIssueType.
     * 
     * @param issueId
     */
    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }


    /**
     * Gets the issueText value for this BatchIssueType.
     * 
     * @return issueText
     */
    public java.lang.String getIssueText() {
        return issueText;
    }


    /**
     * Sets the issueText value for this BatchIssueType.
     * 
     * @param issueText
     */
    public void setIssueText(java.lang.String issueText) {
        this.issueText = issueText;
    }


    /**
     * Gets the issueCount value for this BatchIssueType.
     * 
     * @return issueCount
     */
    public long getIssueCount() {
        return issueCount;
    }


    /**
     * Sets the issueCount value for this BatchIssueType.
     * 
     * @param issueCount
     */
    public void setIssueCount(long issueCount) {
        this.issueCount = issueCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BatchIssueType)) return false;
        BatchIssueType other = (BatchIssueType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.batchIssueId == other.getBatchIssueId() &&
            this.batchId == other.getBatchId() &&
            this.issueId == other.getIssueId() &&
            ((this.issueText==null && other.getIssueText()==null) || 
             (this.issueText!=null &&
              this.issueText.equals(other.getIssueText()))) &&
            this.issueCount == other.getIssueCount();
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
        _hashCode += new Long(getBatchIssueId()).hashCode();
        _hashCode += new Long(getBatchId()).hashCode();
        _hashCode += new Long(getIssueId()).hashCode();
        if (getIssueText() != null) {
            _hashCode += getIssueText().hashCode();
        }
        _hashCode += new Long(getIssueCount()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BatchIssueType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchIssueType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchIssueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchIssueId"));
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
        elemField.setFieldName("issueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueCount"));
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
