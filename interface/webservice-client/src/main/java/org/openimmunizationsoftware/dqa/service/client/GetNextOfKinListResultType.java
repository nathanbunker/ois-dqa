/**
 * GetNextOfKinListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class GetNextOfKinListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.client.NextOfKinType[] nextOfKinList;

    public GetNextOfKinListResultType() {
    }

    public GetNextOfKinListResultType(
           org.openimmunizationsoftware.dqa.service.client.NextOfKinType[] nextOfKinList) {
           this.nextOfKinList = nextOfKinList;
    }


    /**
     * Gets the nextOfKinList value for this GetNextOfKinListResultType.
     * 
     * @return nextOfKinList
     */
    public org.openimmunizationsoftware.dqa.service.client.NextOfKinType[] getNextOfKinList() {
        return nextOfKinList;
    }


    /**
     * Sets the nextOfKinList value for this GetNextOfKinListResultType.
     * 
     * @param nextOfKinList
     */
    public void setNextOfKinList(org.openimmunizationsoftware.dqa.service.client.NextOfKinType[] nextOfKinList) {
        this.nextOfKinList = nextOfKinList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNextOfKinListResultType)) return false;
        GetNextOfKinListResultType other = (GetNextOfKinListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nextOfKinList==null && other.getNextOfKinList()==null) || 
             (this.nextOfKinList!=null &&
              java.util.Arrays.equals(this.nextOfKinList, other.getNextOfKinList())));
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
        if (getNextOfKinList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNextOfKinList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNextOfKinList(), i);
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
        new org.apache.axis.description.TypeDesc(GetNextOfKinListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetNextOfKinListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextOfKinList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nextOfKinList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "NextOfKinType"));
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
