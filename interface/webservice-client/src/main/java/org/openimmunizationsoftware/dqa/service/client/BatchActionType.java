/**
 * BatchActionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class BatchActionType  implements java.io.Serializable {
    private long batchActionId;

    private long batchId;

    private java.lang.String actionCode;

    private java.lang.String actionLabel;

    private long actionCount;

    public BatchActionType() {
    }

    public BatchActionType(
           long batchActionId,
           long batchId,
           java.lang.String actionCode,
           java.lang.String actionLabel,
           long actionCount) {
           this.batchActionId = batchActionId;
           this.batchId = batchId;
           this.actionCode = actionCode;
           this.actionLabel = actionLabel;
           this.actionCount = actionCount;
    }


    /**
     * Gets the batchActionId value for this BatchActionType.
     * 
     * @return batchActionId
     */
    public long getBatchActionId() {
        return batchActionId;
    }


    /**
     * Sets the batchActionId value for this BatchActionType.
     * 
     * @param batchActionId
     */
    public void setBatchActionId(long batchActionId) {
        this.batchActionId = batchActionId;
    }


    /**
     * Gets the batchId value for this BatchActionType.
     * 
     * @return batchId
     */
    public long getBatchId() {
        return batchId;
    }


    /**
     * Sets the batchId value for this BatchActionType.
     * 
     * @param batchId
     */
    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }


    /**
     * Gets the actionCode value for this BatchActionType.
     * 
     * @return actionCode
     */
    public java.lang.String getActionCode() {
        return actionCode;
    }


    /**
     * Sets the actionCode value for this BatchActionType.
     * 
     * @param actionCode
     */
    public void setActionCode(java.lang.String actionCode) {
        this.actionCode = actionCode;
    }


    /**
     * Gets the actionLabel value for this BatchActionType.
     * 
     * @return actionLabel
     */
    public java.lang.String getActionLabel() {
        return actionLabel;
    }


    /**
     * Sets the actionLabel value for this BatchActionType.
     * 
     * @param actionLabel
     */
    public void setActionLabel(java.lang.String actionLabel) {
        this.actionLabel = actionLabel;
    }


    /**
     * Gets the actionCount value for this BatchActionType.
     * 
     * @return actionCount
     */
    public long getActionCount() {
        return actionCount;
    }


    /**
     * Sets the actionCount value for this BatchActionType.
     * 
     * @param actionCount
     */
    public void setActionCount(long actionCount) {
        this.actionCount = actionCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BatchActionType)) return false;
        BatchActionType other = (BatchActionType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.batchActionId == other.getBatchActionId() &&
            this.batchId == other.getBatchId() &&
            ((this.actionCode==null && other.getActionCode()==null) || 
             (this.actionCode!=null &&
              this.actionCode.equals(other.getActionCode()))) &&
            ((this.actionLabel==null && other.getActionLabel()==null) || 
             (this.actionLabel!=null &&
              this.actionLabel.equals(other.getActionLabel()))) &&
            this.actionCount == other.getActionCount();
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
        _hashCode += new Long(getBatchActionId()).hashCode();
        _hashCode += new Long(getBatchId()).hashCode();
        if (getActionCode() != null) {
            _hashCode += getActionCode().hashCode();
        }
        if (getActionLabel() != null) {
            _hashCode += getActionLabel().hashCode();
        }
        _hashCode += new Long(getActionCount()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BatchActionType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchActionType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchActionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchActionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "actionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "actionLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "actionCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
