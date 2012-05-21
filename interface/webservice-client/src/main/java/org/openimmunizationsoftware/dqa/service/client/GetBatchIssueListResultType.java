/**
 * GetBatchIssueListResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class GetBatchIssueListResultType  implements java.io.Serializable {
    private org.openimmunizationsoftware.dqa.service.client.BatchIssueType[] batchIssueList;

    public GetBatchIssueListResultType() {
    }

    public GetBatchIssueListResultType(
           org.openimmunizationsoftware.dqa.service.client.BatchIssueType[] batchIssueList) {
           this.batchIssueList = batchIssueList;
    }


    /**
     * Gets the batchIssueList value for this GetBatchIssueListResultType.
     * 
     * @return batchIssueList
     */
    public org.openimmunizationsoftware.dqa.service.client.BatchIssueType[] getBatchIssueList() {
        return batchIssueList;
    }


    /**
     * Sets the batchIssueList value for this GetBatchIssueListResultType.
     * 
     * @param batchIssueList
     */
    public void setBatchIssueList(org.openimmunizationsoftware.dqa.service.client.BatchIssueType[] batchIssueList) {
        this.batchIssueList = batchIssueList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBatchIssueListResultType)) return false;
        GetBatchIssueListResultType other = (GetBatchIssueListResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.batchIssueList==null && other.getBatchIssueList()==null) || 
             (this.batchIssueList!=null &&
              java.util.Arrays.equals(this.batchIssueList, other.getBatchIssueList())));
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
        if (getBatchIssueList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBatchIssueList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBatchIssueList(), i);
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
        new org.apache.axis.description.TypeDesc(GetBatchIssueListResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "GetBatchIssueListResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchIssueList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchIssueList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchIssueType"));
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
