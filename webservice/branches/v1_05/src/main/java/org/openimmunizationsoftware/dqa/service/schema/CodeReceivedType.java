/**
 * CodeReceivedType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.schema;

public class CodeReceivedType  implements java.io.Serializable {
    private long codeId;

    private java.lang.String codeLabel;

    private long profileId;

    private long tableId;

    private java.lang.String tableLabel;

    private java.lang.String receivedValue;

    private java.lang.String codeValue;

    private java.lang.String codeStatus;

    private java.lang.String codeLabel2;

    private long receivedCount;

    public CodeReceivedType() {
    }

    public CodeReceivedType(
           long codeId,
           java.lang.String codeLabel,
           long profileId,
           long tableId,
           java.lang.String tableLabel,
           java.lang.String receivedValue,
           java.lang.String codeValue,
           java.lang.String codeStatus,
           java.lang.String codeLabel2,
           long receivedCount) {
           this.codeId = codeId;
           this.codeLabel = codeLabel;
           this.profileId = profileId;
           this.tableId = tableId;
           this.tableLabel = tableLabel;
           this.receivedValue = receivedValue;
           this.codeValue = codeValue;
           this.codeStatus = codeStatus;
           this.codeLabel2 = codeLabel2;
           this.receivedCount = receivedCount;
    }


    /**
     * Gets the codeId value for this CodeReceivedType.
     * 
     * @return codeId
     */
    public long getCodeId() {
        return codeId;
    }


    /**
     * Sets the codeId value for this CodeReceivedType.
     * 
     * @param codeId
     */
    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }


    /**
     * Gets the codeLabel value for this CodeReceivedType.
     * 
     * @return codeLabel
     */
    public java.lang.String getCodeLabel() {
        return codeLabel;
    }


    /**
     * Sets the codeLabel value for this CodeReceivedType.
     * 
     * @param codeLabel
     */
    public void setCodeLabel(java.lang.String codeLabel) {
        this.codeLabel = codeLabel;
    }


    /**
     * Gets the profileId value for this CodeReceivedType.
     * 
     * @return profileId
     */
    public long getProfileId() {
        return profileId;
    }


    /**
     * Sets the profileId value for this CodeReceivedType.
     * 
     * @param profileId
     */
    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }


    /**
     * Gets the tableId value for this CodeReceivedType.
     * 
     * @return tableId
     */
    public long getTableId() {
        return tableId;
    }


    /**
     * Sets the tableId value for this CodeReceivedType.
     * 
     * @param tableId
     */
    public void setTableId(long tableId) {
        this.tableId = tableId;
    }


    /**
     * Gets the tableLabel value for this CodeReceivedType.
     * 
     * @return tableLabel
     */
    public java.lang.String getTableLabel() {
        return tableLabel;
    }


    /**
     * Sets the tableLabel value for this CodeReceivedType.
     * 
     * @param tableLabel
     */
    public void setTableLabel(java.lang.String tableLabel) {
        this.tableLabel = tableLabel;
    }


    /**
     * Gets the receivedValue value for this CodeReceivedType.
     * 
     * @return receivedValue
     */
    public java.lang.String getReceivedValue() {
        return receivedValue;
    }


    /**
     * Sets the receivedValue value for this CodeReceivedType.
     * 
     * @param receivedValue
     */
    public void setReceivedValue(java.lang.String receivedValue) {
        this.receivedValue = receivedValue;
    }


    /**
     * Gets the codeValue value for this CodeReceivedType.
     * 
     * @return codeValue
     */
    public java.lang.String getCodeValue() {
        return codeValue;
    }


    /**
     * Sets the codeValue value for this CodeReceivedType.
     * 
     * @param codeValue
     */
    public void setCodeValue(java.lang.String codeValue) {
        this.codeValue = codeValue;
    }


    /**
     * Gets the codeStatus value for this CodeReceivedType.
     * 
     * @return codeStatus
     */
    public java.lang.String getCodeStatus() {
        return codeStatus;
    }


    /**
     * Sets the codeStatus value for this CodeReceivedType.
     * 
     * @param codeStatus
     */
    public void setCodeStatus(java.lang.String codeStatus) {
        this.codeStatus = codeStatus;
    }


    /**
     * Gets the codeLabel2 value for this CodeReceivedType.
     * 
     * @return codeLabel2
     */
    public java.lang.String getCodeLabel2() {
        return codeLabel2;
    }


    /**
     * Sets the codeLabel2 value for this CodeReceivedType.
     * 
     * @param codeLabel2
     */
    public void setCodeLabel2(java.lang.String codeLabel2) {
        this.codeLabel2 = codeLabel2;
    }


    /**
     * Gets the receivedCount value for this CodeReceivedType.
     * 
     * @return receivedCount
     */
    public long getReceivedCount() {
        return receivedCount;
    }


    /**
     * Sets the receivedCount value for this CodeReceivedType.
     * 
     * @param receivedCount
     */
    public void setReceivedCount(long receivedCount) {
        this.receivedCount = receivedCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodeReceivedType)) return false;
        CodeReceivedType other = (CodeReceivedType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codeId == other.getCodeId() &&
            ((this.codeLabel==null && other.getCodeLabel()==null) || 
             (this.codeLabel!=null &&
              this.codeLabel.equals(other.getCodeLabel()))) &&
            this.profileId == other.getProfileId() &&
            this.tableId == other.getTableId() &&
            ((this.tableLabel==null && other.getTableLabel()==null) || 
             (this.tableLabel!=null &&
              this.tableLabel.equals(other.getTableLabel()))) &&
            ((this.receivedValue==null && other.getReceivedValue()==null) || 
             (this.receivedValue!=null &&
              this.receivedValue.equals(other.getReceivedValue()))) &&
            ((this.codeValue==null && other.getCodeValue()==null) || 
             (this.codeValue!=null &&
              this.codeValue.equals(other.getCodeValue()))) &&
            ((this.codeStatus==null && other.getCodeStatus()==null) || 
             (this.codeStatus!=null &&
              this.codeStatus.equals(other.getCodeStatus()))) &&
            ((this.codeLabel2==null && other.getCodeLabel2()==null) || 
             (this.codeLabel2!=null &&
              this.codeLabel2.equals(other.getCodeLabel2()))) &&
            this.receivedCount == other.getReceivedCount();
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
        _hashCode += new Long(getCodeId()).hashCode();
        if (getCodeLabel() != null) {
            _hashCode += getCodeLabel().hashCode();
        }
        _hashCode += new Long(getProfileId()).hashCode();
        _hashCode += new Long(getTableId()).hashCode();
        if (getTableLabel() != null) {
            _hashCode += getTableLabel().hashCode();
        }
        if (getReceivedValue() != null) {
            _hashCode += getReceivedValue().hashCode();
        }
        if (getCodeValue() != null) {
            _hashCode += getCodeValue().hashCode();
        }
        if (getCodeStatus() != null) {
            _hashCode += getCodeStatus().hashCode();
        }
        if (getCodeLabel2() != null) {
            _hashCode += getCodeLabel2().hashCode();
        }
        _hashCode += new Long(getReceivedCount()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodeReceivedType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "CodeReceivedType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tableId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "tableId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tableLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "tableLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivedValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "receivedValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "codeLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivedCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "receivedCount"));
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
