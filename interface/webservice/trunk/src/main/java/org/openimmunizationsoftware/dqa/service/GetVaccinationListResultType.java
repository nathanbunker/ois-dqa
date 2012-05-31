/**
 * GetVaccinationListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class GetVaccinationListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.VaccinationType[] vaccinationList;

    public GetVaccinationListResultType() {
    }

    public GetVaccinationListResultType(
           org.openimmunizationsoftware.dqa.service.VaccinationType[] vaccinationList) {
           this.vaccinationList = vaccinationList;
    }


    /**
     * Gets the vaccinationList value for this GetVaccinationListResultType.
     * 
     * @return vaccinationList
     */
    public org.openimmunizationsoftware.dqa.service.VaccinationType[] getVaccinationList() {
        return vaccinationList;
    }


    /**
     * Sets the vaccinationList value for this GetVaccinationListResultType.
     * 
     * @param vaccinationList
     */
    public void setVaccinationList(org.openimmunizationsoftware.dqa.service.VaccinationType[] vaccinationList) {
        this.vaccinationList = vaccinationList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetVaccinationListResultType)) return false;
        GetVaccinationListResultType other = (GetVaccinationListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.vaccinationList==null && other.getVaccinationList()==null) || 
             (this.vaccinationList!=null &&
              java.util.Arrays.equals(this.vaccinationList, other.getVaccinationList())));
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
        if (getVaccinationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVaccinationList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVaccinationList(), i);
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
        new org.apache.axis.description.TypeDesc(GetVaccinationListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetVaccinationListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vaccinationList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccinationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "VaccinationType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccinationList"));
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