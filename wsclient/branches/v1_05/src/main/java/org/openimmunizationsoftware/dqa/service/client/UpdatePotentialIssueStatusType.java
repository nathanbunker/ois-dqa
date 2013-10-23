/**
 * UpdatePotentialIssueStatusType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class UpdatePotentialIssueStatusType  implements java.io.Serializable {
    private long issueId;

    private java.lang.String actionCode;

    private long expectMin;

    private long expectMax;

    public UpdatePotentialIssueStatusType() {
    }

    public UpdatePotentialIssueStatusType(
           long issueId,
           java.lang.String actionCode,
           long expectMin,
           long expectMax) {
           this.issueId = issueId;
           this.actionCode = actionCode;
           this.expectMin = expectMin;
           this.expectMax = expectMax;
    }


    /**
     * Gets the issueId value for this UpdatePotentialIssueStatusType.
     * 
     * @return issueId
     */
    public long getIssueId() {
        return issueId;
    }


    /**
     * Sets the issueId value for this UpdatePotentialIssueStatusType.
     * 
     * @param issueId
     */
    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }


    /**
     * Gets the actionCode value for this UpdatePotentialIssueStatusType.
     * 
     * @return actionCode
     */
    public java.lang.String getActionCode() {
        return actionCode;
    }


    /**
     * Sets the actionCode value for this UpdatePotentialIssueStatusType.
     * 
     * @param actionCode
     */
    public void setActionCode(java.lang.String actionCode) {
        this.actionCode = actionCode;
    }


    /**
     * Gets the expectMin value for this UpdatePotentialIssueStatusType.
     * 
     * @return expectMin
     */
    public long getExpectMin() {
        return expectMin;
    }


    /**
     * Sets the expectMin value for this UpdatePotentialIssueStatusType.
     * 
     * @param expectMin
     */
    public void setExpectMin(long expectMin) {
        this.expectMin = expectMin;
    }


    /**
     * Gets the expectMax value for this UpdatePotentialIssueStatusType.
     * 
     * @return expectMax
     */
    public long getExpectMax() {
        return expectMax;
    }


    /**
     * Sets the expectMax value for this UpdatePotentialIssueStatusType.
     * 
     * @param expectMax
     */
    public void setExpectMax(long expectMax) {
        this.expectMax = expectMax;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdatePotentialIssueStatusType)) return false;
        UpdatePotentialIssueStatusType other = (UpdatePotentialIssueStatusType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.issueId == other.getIssueId() &&
            ((this.actionCode==null && other.getActionCode()==null) || 
             (this.actionCode!=null &&
              this.actionCode.equals(other.getActionCode()))) &&
            this.expectMin == other.getExpectMin() &&
            this.expectMax == other.getExpectMax();
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
        _hashCode += new Long(getIssueId()).hashCode();
        if (getActionCode() != null) {
            _hashCode += getActionCode().hashCode();
        }
        _hashCode += new Long(getExpectMin()).hashCode();
        _hashCode += new Long(getExpectMax()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdatePotentialIssueStatusType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdatePotentialIssueStatusType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "actionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectMin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "expectMin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectMax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "expectMax"));
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
