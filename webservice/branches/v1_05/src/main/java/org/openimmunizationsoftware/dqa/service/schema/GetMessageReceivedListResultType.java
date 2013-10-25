/**
 * GetMessageReceivedListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.schema;

public class GetMessageReceivedListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.schema.MessageReceivedType[] messageReceivedList;

    public GetMessageReceivedListResultType() {
    }

    public GetMessageReceivedListResultType(
           org.openimmunizationsoftware.dqa.service.schema.MessageReceivedType[] messageReceivedList) {
           this.messageReceivedList = messageReceivedList;
    }


    /**
     * Gets the messageReceivedList value for this GetMessageReceivedListResultType.
     * 
     * @return messageReceivedList
     */
    public org.openimmunizationsoftware.dqa.service.schema.MessageReceivedType[] getMessageReceivedList() {
        return messageReceivedList;
    }


    /**
     * Sets the messageReceivedList value for this GetMessageReceivedListResultType.
     * 
     * @param messageReceivedList
     */
    public void setMessageReceivedList(org.openimmunizationsoftware.dqa.service.schema.MessageReceivedType[] messageReceivedList) {
        this.messageReceivedList = messageReceivedList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMessageReceivedListResultType)) return false;
        GetMessageReceivedListResultType other = (GetMessageReceivedListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageReceivedList==null && other.getMessageReceivedList()==null) || 
             (this.messageReceivedList!=null &&
              java.util.Arrays.equals(this.messageReceivedList, other.getMessageReceivedList())));
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
        if (getMessageReceivedList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessageReceivedList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessageReceivedList(), i);
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
        new org.apache.axis.description.TypeDesc(GetMessageReceivedListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageReceivedList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageReceivedList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageReceivedType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageReceivedList"));
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
