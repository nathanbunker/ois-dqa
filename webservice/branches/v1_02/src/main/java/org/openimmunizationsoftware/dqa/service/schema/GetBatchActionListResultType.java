/**
 * GetBatchActionListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.schema;

public class GetBatchActionListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.schema.BatchActionType[] batchActionList;

    public GetBatchActionListResultType() {
    }

    public GetBatchActionListResultType(
           org.openimmunizationsoftware.dqa.service.schema.BatchActionType[] batchActionList) {
           this.batchActionList = batchActionList;
    }


    /**
     * Gets the batchActionList value for this GetBatchActionListResultType.
     * 
     * @return batchActionList
     */
    public org.openimmunizationsoftware.dqa.service.schema.BatchActionType[] getBatchActionList() {
        return batchActionList;
    }


    /**
     * Sets the batchActionList value for this GetBatchActionListResultType.
     * 
     * @param batchActionList
     */
    public void setBatchActionList(org.openimmunizationsoftware.dqa.service.schema.BatchActionType[] batchActionList) {
        this.batchActionList = batchActionList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBatchActionListResultType)) return false;
        GetBatchActionListResultType other = (GetBatchActionListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.batchActionList==null && other.getBatchActionList()==null) || 
             (this.batchActionList!=null &&
              java.util.Arrays.equals(this.batchActionList, other.getBatchActionList())));
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
        if (getBatchActionList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBatchActionList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBatchActionList(), i);
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
        new org.apache.axis.description.TypeDesc(GetBatchActionListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchActionListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchActionList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchActionList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchActionType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchActionsList"));
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
