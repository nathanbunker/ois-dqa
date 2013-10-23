/**
 * GetMessageReceivedDetailsResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class GetMessageReceivedDetailsResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.client.MessageReceivedDetailsType messageReceivedDetails;

    private org.openimmunizationsoftware.dqa.service.client.IssueFoundType[] issueFoundList;

    public GetMessageReceivedDetailsResultType() {
    }

    public GetMessageReceivedDetailsResultType(
           org.openimmunizationsoftware.dqa.service.client.MessageReceivedDetailsType messageReceivedDetails,
           org.openimmunizationsoftware.dqa.service.client.IssueFoundType[] issueFoundList) {
           this.messageReceivedDetails = messageReceivedDetails;
           this.issueFoundList = issueFoundList;
    }


    /**
     * Gets the messageReceivedDetails value for this GetMessageReceivedDetailsResultType.
     * 
     * @return messageReceivedDetails
     */
    public org.openimmunizationsoftware.dqa.service.client.MessageReceivedDetailsType getMessageReceivedDetails() {
        return messageReceivedDetails;
    }


    /**
     * Sets the messageReceivedDetails value for this GetMessageReceivedDetailsResultType.
     * 
     * @param messageReceivedDetails
     */
    public void setMessageReceivedDetails(org.openimmunizationsoftware.dqa.service.client.MessageReceivedDetailsType messageReceivedDetails) {
        this.messageReceivedDetails = messageReceivedDetails;
    }


    /**
     * Gets the issueFoundList value for this GetMessageReceivedDetailsResultType.
     * 
     * @return issueFoundList
     */
    public org.openimmunizationsoftware.dqa.service.client.IssueFoundType[] getIssueFoundList() {
        return issueFoundList;
    }


    /**
     * Sets the issueFoundList value for this GetMessageReceivedDetailsResultType.
     * 
     * @param issueFoundList
     */
    public void setIssueFoundList(org.openimmunizationsoftware.dqa.service.client.IssueFoundType[] issueFoundList) {
        this.issueFoundList = issueFoundList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMessageReceivedDetailsResultType)) return false;
        GetMessageReceivedDetailsResultType other = (GetMessageReceivedDetailsResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageReceivedDetails==null && other.getMessageReceivedDetails()==null) || 
             (this.messageReceivedDetails!=null &&
              this.messageReceivedDetails.equals(other.getMessageReceivedDetails()))) &&
            ((this.issueFoundList==null && other.getIssueFoundList()==null) || 
             (this.issueFoundList!=null &&
              java.util.Arrays.equals(this.issueFoundList, other.getIssueFoundList())));
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
        if (getMessageReceivedDetails() != null) {
            _hashCode += getMessageReceivedDetails().hashCode();
        }
        if (getIssueFoundList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIssueFoundList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIssueFoundList(), i);
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
        new org.apache.axis.description.TypeDesc(GetMessageReceivedDetailsResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetMessageReceivedDetailsResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageReceivedDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageReceivedDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageReceivedDetailsType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueFoundList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueFoundList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueFoundType"));
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
