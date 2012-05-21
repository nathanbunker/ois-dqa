/**
 * SubmitterProfileType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class SubmitterProfileType  implements java.io.Serializable {
    private java.lang.String profileCode;

    private java.lang.Long profileId;

    private java.lang.String profileLabel;

    private java.lang.String profileStatus;

    private long orgLocalCode;

    private java.lang.String orgLocalLabel;

    private java.lang.String transferPriority;

    private java.lang.String accessKey;

    private java.lang.Long templateId;

    public SubmitterProfileType() {
    }

    public SubmitterProfileType(
           java.lang.String profileCode,
           java.lang.Long profileId,
           java.lang.String profileLabel,
           java.lang.String profileStatus,
           long orgLocalCode,
           java.lang.String orgLocalLabel,
           java.lang.String transferPriority,
           java.lang.String accessKey,
           java.lang.Long templateId) {
           this.profileCode = profileCode;
           this.profileId = profileId;
           this.profileLabel = profileLabel;
           this.profileStatus = profileStatus;
           this.orgLocalCode = orgLocalCode;
           this.orgLocalLabel = orgLocalLabel;
           this.transferPriority = transferPriority;
           this.accessKey = accessKey;
           this.templateId = templateId;
    }


    /**
     * Gets the profileCode value for this SubmitterProfileType.
     * 
     * @return profileCode
     */
    public java.lang.String getProfileCode() {
        return profileCode;
    }


    /**
     * Sets the profileCode value for this SubmitterProfileType.
     * 
     * @param profileCode
     */
    public void setProfileCode(java.lang.String profileCode) {
        this.profileCode = profileCode;
    }


    /**
     * Gets the profileId value for this SubmitterProfileType.
     * 
     * @return profileId
     */
    public java.lang.Long getProfileId() {
        return profileId;
    }


    /**
     * Sets the profileId value for this SubmitterProfileType.
     * 
     * @param profileId
     */
    public void setProfileId(java.lang.Long profileId) {
        this.profileId = profileId;
    }


    /**
     * Gets the profileLabel value for this SubmitterProfileType.
     * 
     * @return profileLabel
     */
    public java.lang.String getProfileLabel() {
        return profileLabel;
    }


    /**
     * Sets the profileLabel value for this SubmitterProfileType.
     * 
     * @param profileLabel
     */
    public void setProfileLabel(java.lang.String profileLabel) {
        this.profileLabel = profileLabel;
    }


    /**
     * Gets the profileStatus value for this SubmitterProfileType.
     * 
     * @return profileStatus
     */
    public java.lang.String getProfileStatus() {
        return profileStatus;
    }


    /**
     * Sets the profileStatus value for this SubmitterProfileType.
     * 
     * @param profileStatus
     */
    public void setProfileStatus(java.lang.String profileStatus) {
        this.profileStatus = profileStatus;
    }


    /**
     * Gets the orgLocalCode value for this SubmitterProfileType.
     * 
     * @return orgLocalCode
     */
    public long getOrgLocalCode() {
        return orgLocalCode;
    }


    /**
     * Sets the orgLocalCode value for this SubmitterProfileType.
     * 
     * @param orgLocalCode
     */
    public void setOrgLocalCode(long orgLocalCode) {
        this.orgLocalCode = orgLocalCode;
    }


    /**
     * Gets the orgLocalLabel value for this SubmitterProfileType.
     * 
     * @return orgLocalLabel
     */
    public java.lang.String getOrgLocalLabel() {
        return orgLocalLabel;
    }


    /**
     * Sets the orgLocalLabel value for this SubmitterProfileType.
     * 
     * @param orgLocalLabel
     */
    public void setOrgLocalLabel(java.lang.String orgLocalLabel) {
        this.orgLocalLabel = orgLocalLabel;
    }


    /**
     * Gets the transferPriority value for this SubmitterProfileType.
     * 
     * @return transferPriority
     */
    public java.lang.String getTransferPriority() {
        return transferPriority;
    }


    /**
     * Sets the transferPriority value for this SubmitterProfileType.
     * 
     * @param transferPriority
     */
    public void setTransferPriority(java.lang.String transferPriority) {
        this.transferPriority = transferPriority;
    }


    /**
     * Gets the accessKey value for this SubmitterProfileType.
     * 
     * @return accessKey
     */
    public java.lang.String getAccessKey() {
        return accessKey;
    }


    /**
     * Sets the accessKey value for this SubmitterProfileType.
     * 
     * @param accessKey
     */
    public void setAccessKey(java.lang.String accessKey) {
        this.accessKey = accessKey;
    }


    /**
     * Gets the templateId value for this SubmitterProfileType.
     * 
     * @return templateId
     */
    public java.lang.Long getTemplateId() {
        return templateId;
    }


    /**
     * Sets the templateId value for this SubmitterProfileType.
     * 
     * @param templateId
     */
    public void setTemplateId(java.lang.Long templateId) {
        this.templateId = templateId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubmitterProfileType)) return false;
        SubmitterProfileType other = (SubmitterProfileType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.profileCode==null && other.getProfileCode()==null) || 
             (this.profileCode!=null &&
              this.profileCode.equals(other.getProfileCode()))) &&
            ((this.profileId==null && other.getProfileId()==null) || 
             (this.profileId!=null &&
              this.profileId.equals(other.getProfileId()))) &&
            ((this.profileLabel==null && other.getProfileLabel()==null) || 
             (this.profileLabel!=null &&
              this.profileLabel.equals(other.getProfileLabel()))) &&
            ((this.profileStatus==null && other.getProfileStatus()==null) || 
             (this.profileStatus!=null &&
              this.profileStatus.equals(other.getProfileStatus()))) &&
            this.orgLocalCode == other.getOrgLocalCode() &&
            ((this.orgLocalLabel==null && other.getOrgLocalLabel()==null) || 
             (this.orgLocalLabel!=null &&
              this.orgLocalLabel.equals(other.getOrgLocalLabel()))) &&
            ((this.transferPriority==null && other.getTransferPriority()==null) || 
             (this.transferPriority!=null &&
              this.transferPriority.equals(other.getTransferPriority()))) &&
            ((this.accessKey==null && other.getAccessKey()==null) || 
             (this.accessKey!=null &&
              this.accessKey.equals(other.getAccessKey()))) &&
            ((this.templateId==null && other.getTemplateId()==null) || 
             (this.templateId!=null &&
              this.templateId.equals(other.getTemplateId())));
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
        if (getProfileCode() != null) {
            _hashCode += getProfileCode().hashCode();
        }
        if (getProfileId() != null) {
            _hashCode += getProfileId().hashCode();
        }
        if (getProfileLabel() != null) {
            _hashCode += getProfileLabel().hashCode();
        }
        if (getProfileStatus() != null) {
            _hashCode += getProfileStatus().hashCode();
        }
        _hashCode += new Long(getOrgLocalCode()).hashCode();
        if (getOrgLocalLabel() != null) {
            _hashCode += getOrgLocalLabel().hashCode();
        }
        if (getTransferPriority() != null) {
            _hashCode += getTransferPriority().hashCode();
        }
        if (getAccessKey() != null) {
            _hashCode += getAccessKey().hashCode();
        }
        if (getTemplateId() != null) {
            _hashCode += getTemplateId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubmitterProfileType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitterProfileType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "profileStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgLocalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "orgLocalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgLocalLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "orgLocalLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transferPriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "transferPriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "accessKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "templateId"));
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
