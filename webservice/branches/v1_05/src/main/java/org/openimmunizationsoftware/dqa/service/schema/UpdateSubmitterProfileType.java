/**
 * UpdateSubmitterProfileType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.schema;

public class UpdateSubmitterProfileType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.schema.SubmitterProfileType submitterProfileList;

    private java.lang.String autoCreate;

    public UpdateSubmitterProfileType() {
    }

    public UpdateSubmitterProfileType(
           org.openimmunizationsoftware.dqa.service.schema.SubmitterProfileType submitterProfileList,
           java.lang.String autoCreate) {
           this.submitterProfileList = submitterProfileList;
           this.autoCreate = autoCreate;
    }


    /**
     * Gets the submitterProfileList value for this UpdateSubmitterProfileType.
     * 
     * @return submitterProfileList
     */
    public org.openimmunizationsoftware.dqa.service.schema.SubmitterProfileType getSubmitterProfileList() {
        return submitterProfileList;
    }


    /**
     * Sets the submitterProfileList value for this UpdateSubmitterProfileType.
     * 
     * @param submitterProfileList
     */
    public void setSubmitterProfileList(org.openimmunizationsoftware.dqa.service.schema.SubmitterProfileType submitterProfileList) {
        this.submitterProfileList = submitterProfileList;
    }


    /**
     * Gets the autoCreate value for this UpdateSubmitterProfileType.
     * 
     * @return autoCreate
     */
    public java.lang.String getAutoCreate() {
        return autoCreate;
    }


    /**
     * Sets the autoCreate value for this UpdateSubmitterProfileType.
     * 
     * @param autoCreate
     */
    public void setAutoCreate(java.lang.String autoCreate) {
        this.autoCreate = autoCreate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateSubmitterProfileType)) return false;
        UpdateSubmitterProfileType other = (UpdateSubmitterProfileType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.submitterProfileList==null && other.getSubmitterProfileList()==null) || 
             (this.submitterProfileList!=null &&
              this.submitterProfileList.equals(other.getSubmitterProfileList()))) &&
            ((this.autoCreate==null && other.getAutoCreate()==null) || 
             (this.autoCreate!=null &&
              this.autoCreate.equals(other.getAutoCreate())));
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
        if (getSubmitterProfileList() != null) {
            _hashCode += getSubmitterProfileList().hashCode();
        }
        if (getAutoCreate() != null) {
            _hashCode += getAutoCreate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateSubmitterProfileType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "UpdateSubmitterProfileType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submitterProfileList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "submitterProfileList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitterProfileType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoCreate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "autoCreate"));
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
