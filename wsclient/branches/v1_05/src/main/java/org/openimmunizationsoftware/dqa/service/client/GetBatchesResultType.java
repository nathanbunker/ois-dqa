/**
 * GetBatchesResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class GetBatchesResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.client.MessageBatchType[] messageBatchList;

    public GetBatchesResultType() {
    }

    public GetBatchesResultType(
           org.openimmunizationsoftware.dqa.service.client.MessageBatchType[] messageBatchList) {
           this.messageBatchList = messageBatchList;
    }


    /**
     * Gets the messageBatchList value for this GetBatchesResultType.
     * 
     * @return messageBatchList
     */
    public org.openimmunizationsoftware.dqa.service.client.MessageBatchType[] getMessageBatchList() {
        return messageBatchList;
    }


    /**
     * Sets the messageBatchList value for this GetBatchesResultType.
     * 
     * @param messageBatchList
     */
    public void setMessageBatchList(org.openimmunizationsoftware.dqa.service.client.MessageBatchType[] messageBatchList) {
        this.messageBatchList = messageBatchList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBatchesResultType)) return false;
        GetBatchesResultType other = (GetBatchesResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageBatchList==null && other.getMessageBatchList()==null) || 
             (this.messageBatchList!=null &&
              java.util.Arrays.equals(this.messageBatchList, other.getMessageBatchList())));
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
        if (getMessageBatchList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessageBatchList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessageBatchList(), i);
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
        new org.apache.axis.description.TypeDesc(GetBatchesResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchesResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageBatchList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageBatchList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageBatchType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "item"));
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
