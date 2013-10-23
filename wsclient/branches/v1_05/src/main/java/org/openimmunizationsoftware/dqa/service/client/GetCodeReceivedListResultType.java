/**
 * GetCodeReceivedListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class GetCodeReceivedListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.client.CodeReceivedType[] codeReceivedList;

    public GetCodeReceivedListResultType() {
    }

    public GetCodeReceivedListResultType(
           org.openimmunizationsoftware.dqa.service.client.CodeReceivedType[] codeReceivedList) {
           this.codeReceivedList = codeReceivedList;
    }


    /**
     * Gets the codeReceivedList value for this GetCodeReceivedListResultType.
     * 
     * @return codeReceivedList
     */
    public org.openimmunizationsoftware.dqa.service.client.CodeReceivedType[] getCodeReceivedList() {
        return codeReceivedList;
    }


    /**
     * Sets the codeReceivedList value for this GetCodeReceivedListResultType.
     * 
     * @param codeReceivedList
     */
    public void setCodeReceivedList(org.openimmunizationsoftware.dqa.service.client.CodeReceivedType[] codeReceivedList) {
        this.codeReceivedList = codeReceivedList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCodeReceivedListResultType)) return false;
        GetCodeReceivedListResultType other = (GetCodeReceivedListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codeReceivedList==null && other.getCodeReceivedList()==null) || 
             (this.codeReceivedList!=null &&
              java.util.Arrays.equals(this.codeReceivedList, other.getCodeReceivedList())));
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
        if (getCodeReceivedList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCodeReceivedList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCodeReceivedList(), i);
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
        new org.apache.axis.description.TypeDesc(GetCodeReceivedListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetCodeReceivedListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeReceivedList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeReceivedList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedType"));
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
