/**
 * MessageBatchType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class MessageBatchType  implements java.io.Serializable {
    private long batchId;

    private java.lang.String batchTitle;

    private java.lang.String typeCode;

    private java.lang.String typeLabel;

    private java.lang.String startDate;

    private java.lang.String endDate;

    private java.lang.String submitCode;

    private java.lang.String submitLabel;

    private long profileId;

    private java.lang.String profileCode;

    private long overallScore;

    private java.lang.Long messageCount;

    public MessageBatchType() {
    }

    public MessageBatchType(
           long batchId,
           java.lang.String batchTitle,
           java.lang.String typeCode,
           java.lang.String typeLabel,
           java.lang.String startDate,
           java.lang.String endDate,
           java.lang.String submitCode,
           java.lang.String submitLabel,
           long profileId,
           java.lang.String profileCode,
           long overallScore,
           java.lang.Long messageCount) {
           this.batchId = batchId;
           this.batchTitle = batchTitle;
           this.typeCode = typeCode;
           this.typeLabel = typeLabel;
           this.startDate = startDate;
           this.endDate = endDate;
           this.submitCode = submitCode;
           this.submitLabel = submitLabel;
           this.profileId = profileId;
           this.profileCode = profileCode;
           this.overallScore = overallScore;
           this.messageCount = messageCount;
    }


    /**
     * Gets the batchId value for this MessageBatchType.
     * 
     * @return batchId
     */
    public long getBatchId() {
        return batchId;
    }


    /**
     * Sets the batchId value for this MessageBatchType.
     * 
     * @param batchId
     */
    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }


    /**
     * Gets the batchTitle value for this MessageBatchType.
     * 
     * @return batchTitle
     */
    public java.lang.String getBatchTitle() {
        return batchTitle;
    }


    /**
     * Sets the batchTitle value for this MessageBatchType.
     * 
     * @param batchTitle
     */
    public void setBatchTitle(java.lang.String batchTitle) {
        this.batchTitle = batchTitle;
    }


    /**
     * Gets the typeCode value for this MessageBatchType.
     * 
     * @return typeCode
     */
    public java.lang.String getTypeCode() {
        return typeCode;
    }


    /**
     * Sets the typeCode value for this MessageBatchType.
     * 
     * @param typeCode
     */
    public void setTypeCode(java.lang.String typeCode) {
        this.typeCode = typeCode;
    }


    /**
     * Gets the typeLabel value for this MessageBatchType.
     * 
     * @return typeLabel
     */
    public java.lang.String getTypeLabel() {
        return typeLabel;
    }


    /**
     * Sets the typeLabel value for this MessageBatchType.
     * 
     * @param typeLabel
     */
    public void setTypeLabel(java.lang.String typeLabel) {
        this.typeLabel = typeLabel;
    }


    /**
     * Gets the startDate value for this MessageBatchType.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this MessageBatchType.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the endDate value for this MessageBatchType.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this MessageBatchType.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the submitCode value for this MessageBatchType.
     * 
     * @return submitCode
     */
    public java.lang.String getSubmitCode() {
        return submitCode;
    }


    /**
     * Sets the submitCode value for this MessageBatchType.
     * 
     * @param submitCode
     */
    public void setSubmitCode(java.lang.String submitCode) {
        this.submitCode = submitCode;
    }


    /**
     * Gets the submitLabel value for this MessageBatchType.
     * 
     * @return submitLabel
     */
    public java.lang.String getSubmitLabel() {
        return submitLabel;
    }


    /**
     * Sets the submitLabel value for this MessageBatchType.
     * 
     * @param submitLabel
     */
    public void setSubmitLabel(java.lang.String submitLabel) {
        this.submitLabel = submitLabel;
    }


    /**
     * Gets the profileId value for this MessageBatchType.
     * 
     * @return profileId
     */
    public long getProfileId() {
        return profileId;
    }


    /**
     * Sets the profileId value for this MessageBatchType.
     * 
     * @param profileId
     */
    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }


    /**
     * Gets the profileCode value for this MessageBatchType.
     * 
     * @return profileCode
     */
    public java.lang.String getProfileCode() {
        return profileCode;
    }


    /**
     * Sets the profileCode value for this MessageBatchType.
     * 
     * @param profileCode
     */
    public void setProfileCode(java.lang.String profileCode) {
        this.profileCode = profileCode;
    }


    /**
     * Gets the overallScore value for this MessageBatchType.
     * 
     * @return overallScore
     */
    public long getOverallScore() {
        return overallScore;
    }


    /**
     * Sets the overallScore value for this MessageBatchType.
     * 
     * @param overallScore
     */
    public void setOverallScore(long overallScore) {
        this.overallScore = overallScore;
    }


    /**
     * Gets the messageCount value for this MessageBatchType.
     * 
     * @return messageCount
     */
    public java.lang.Long getMessageCount() {
        return messageCount;
    }


    /**
     * Sets the messageCount value for this MessageBatchType.
     * 
     * @param messageCount
     */
    public void setMessageCount(java.lang.Long messageCount) {
        this.messageCount = messageCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageBatchType)) return false;
        MessageBatchType other = (MessageBatchType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.batchId == other.getBatchId() &&
            ((this.batchTitle==null && other.getBatchTitle()==null) || 
             (this.batchTitle!=null &&
              this.batchTitle.equals(other.getBatchTitle()))) &&
            ((this.typeCode==null && other.getTypeCode()==null) || 
             (this.typeCode!=null &&
              this.typeCode.equals(other.getTypeCode()))) &&
            ((this.typeLabel==null && other.getTypeLabel()==null) || 
             (this.typeLabel!=null &&
              this.typeLabel.equals(other.getTypeLabel()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.submitCode==null && other.getSubmitCode()==null) || 
             (this.submitCode!=null &&
              this.submitCode.equals(other.getSubmitCode()))) &&
            ((this.submitLabel==null && other.getSubmitLabel()==null) || 
             (this.submitLabel!=null &&
              this.submitLabel.equals(other.getSubmitLabel()))) &&
            this.profileId == other.getProfileId() &&
            ((this.profileCode==null && other.getProfileCode()==null) || 
             (this.profileCode!=null &&
              this.profileCode.equals(other.getProfileCode()))) &&
            this.overallScore == other.getOverallScore() &&
            ((this.messageCount==null && other.getMessageCount()==null) || 
             (this.messageCount!=null &&
              this.messageCount.equals(other.getMessageCount())));
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
        _hashCode += new Long(getBatchId()).hashCode();
        if (getBatchTitle() != null) {
            _hashCode += getBatchTitle().hashCode();
        }
        if (getTypeCode() != null) {
            _hashCode += getTypeCode().hashCode();
        }
        if (getTypeLabel() != null) {
            _hashCode += getTypeLabel().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getSubmitCode() != null) {
            _hashCode += getSubmitCode().hashCode();
        }
        if (getSubmitLabel() != null) {
            _hashCode += getSubmitLabel().hashCode();
        }
        _hashCode += new Long(getProfileId()).hashCode();
        if (getProfileCode() != null) {
            _hashCode += getProfileCode().hashCode();
        }
        _hashCode += new Long(getOverallScore()).hashCode();
        if (getMessageCount() != null) {
            _hashCode += getMessageCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessageBatchType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "MessageBatchType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "typeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "typeLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submitCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "submitCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submitLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "submitLabel"));
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
        elemField.setFieldName("profileCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overallScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "overallScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
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
