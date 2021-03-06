/**
 * GetPotentialIssueStatusListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class GetPotentialIssueStatusListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.client.PotentialIssueStatusType[] potentialIssueStatusList;

    public GetPotentialIssueStatusListResultType() {
    }

    public GetPotentialIssueStatusListResultType(
           org.openimmunizationsoftware.dqa.service.client.PotentialIssueStatusType[] potentialIssueStatusList) {
           this.potentialIssueStatusList = potentialIssueStatusList;
    }


    /**
     * Gets the potentialIssueStatusList value for this GetPotentialIssueStatusListResultType.
     * 
     * @return potentialIssueStatusList
     */
    public org.openimmunizationsoftware.dqa.service.client.PotentialIssueStatusType[] getPotentialIssueStatusList() {
        return potentialIssueStatusList;
    }


    /**
     * Sets the potentialIssueStatusList value for this GetPotentialIssueStatusListResultType.
     * 
     * @param potentialIssueStatusList
     */
    public void setPotentialIssueStatusList(org.openimmunizationsoftware.dqa.service.client.PotentialIssueStatusType[] potentialIssueStatusList) {
        this.potentialIssueStatusList = potentialIssueStatusList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPotentialIssueStatusListResultType)) return false;
        GetPotentialIssueStatusListResultType other = (GetPotentialIssueStatusListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.potentialIssueStatusList==null && other.getPotentialIssueStatusList()==null) || 
             (this.potentialIssueStatusList!=null &&
              java.util.Arrays.equals(this.potentialIssueStatusList, other.getPotentialIssueStatusList())));
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
        if (getPotentialIssueStatusList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPotentialIssueStatusList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPotentialIssueStatusList(), i);
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
        new org.apache.axis.description.TypeDesc(GetPotentialIssueStatusListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetPotentialIssueStatusListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("potentialIssueStatusList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "potentialIssueStatusList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "PotentialIssueStatusType"));
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
