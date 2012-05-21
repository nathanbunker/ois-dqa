/**
 * GetSubmitterProfileResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class GetSubmitterProfileResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.client.SubmitterProfileType submitterProfile;

    public GetSubmitterProfileResultType() {
    }

    public GetSubmitterProfileResultType(
           org.openimmunizationsoftware.dqa.service.client.SubmitterProfileType submitterProfile) {
           this.submitterProfile = submitterProfile;
    }


    /**
     * Gets the submitterProfile value for this GetSubmitterProfileResultType.
     * 
     * @return submitterProfile
     */
    public org.openimmunizationsoftware.dqa.service.client.SubmitterProfileType getSubmitterProfile() {
        return submitterProfile;
    }


    /**
     * Sets the submitterProfile value for this GetSubmitterProfileResultType.
     * 
     * @param submitterProfile
     */
    public void setSubmitterProfile(org.openimmunizationsoftware.dqa.service.client.SubmitterProfileType submitterProfile) {
        this.submitterProfile = submitterProfile;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetSubmitterProfileResultType)) return false;
        GetSubmitterProfileResultType other = (GetSubmitterProfileResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.submitterProfile==null && other.getSubmitterProfile()==null) || 
             (this.submitterProfile!=null &&
              this.submitterProfile.equals(other.getSubmitterProfile())));
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
        if (getSubmitterProfile() != null) {
            _hashCode += getSubmitterProfile().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSubmitterProfileResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetSubmitterProfileResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submitterProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "submitterProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitterProfileType"));
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
