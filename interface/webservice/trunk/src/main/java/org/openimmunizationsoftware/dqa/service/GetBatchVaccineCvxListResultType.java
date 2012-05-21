/**
 * GetBatchVaccineCvxListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class GetBatchVaccineCvxListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.BatchVaccineCvxType[] batchVaccineCvxList;

    public GetBatchVaccineCvxListResultType() {
    }

    public GetBatchVaccineCvxListResultType(
           org.openimmunizationsoftware.dqa.service.BatchVaccineCvxType[] batchVaccineCvxList) {
           this.batchVaccineCvxList = batchVaccineCvxList;
    }


    /**
     * Gets the batchVaccineCvxList value for this GetBatchVaccineCvxListResultType.
     * 
     * @return batchVaccineCvxList
     */
    public org.openimmunizationsoftware.dqa.service.BatchVaccineCvxType[] getBatchVaccineCvxList() {
        return batchVaccineCvxList;
    }


    /**
     * Sets the batchVaccineCvxList value for this GetBatchVaccineCvxListResultType.
     * 
     * @param batchVaccineCvxList
     */
    public void setBatchVaccineCvxList(org.openimmunizationsoftware.dqa.service.BatchVaccineCvxType[] batchVaccineCvxList) {
        this.batchVaccineCvxList = batchVaccineCvxList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBatchVaccineCvxListResultType)) return false;
        GetBatchVaccineCvxListResultType other = (GetBatchVaccineCvxListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.batchVaccineCvxList==null && other.getBatchVaccineCvxList()==null) || 
             (this.batchVaccineCvxList!=null &&
              java.util.Arrays.equals(this.batchVaccineCvxList, other.getBatchVaccineCvxList())));
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
        if (getBatchVaccineCvxList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBatchVaccineCvxList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBatchVaccineCvxList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBatchVaccineCvxListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchVaccineCvxListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchVaccineCvxList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchVaccineCvxList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchVaccineCvxType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchVaccineCvxList"));
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
