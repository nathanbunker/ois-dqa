/**
 * PotentialIssueStatusType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class PotentialIssueStatusType  implements java.io.Serializable {
    private long issueId;

    private java.lang.String issueLabel;

    private java.lang.String issueDescription;

    private java.lang.String changePriority;

    private long profileId;

    private java.lang.String actionCode;

    private java.lang.String actionLabel;

    private long expectMin;

    private long expectMax;

    public PotentialIssueStatusType() {
    }

    public PotentialIssueStatusType(
           long issueId,
           java.lang.String issueLabel,
           java.lang.String issueDescription,
           java.lang.String changePriority,
           long profileId,
           java.lang.String actionCode,
           java.lang.String actionLabel,
           long expectMin,
           long expectMax) {
           this.issueId = issueId;
           this.issueLabel = issueLabel;
           this.issueDescription = issueDescription;
           this.changePriority = changePriority;
           this.profileId = profileId;
           this.actionCode = actionCode;
           this.actionLabel = actionLabel;
           this.expectMin = expectMin;
           this.expectMax = expectMax;
    }


    /**
     * Gets the issueId value for this PotentialIssueStatusType.
     * 
     * @return issueId
     */
    public long getIssueId() {
        return issueId;
    }


    /**
     * Sets the issueId value for this PotentialIssueStatusType.
     * 
     * @param issueId
     */
    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }


    /**
     * Gets the issueLabel value for this PotentialIssueStatusType.
     * 
     * @return issueLabel
     */
    public java.lang.String getIssueLabel() {
        return issueLabel;
    }


    /**
     * Sets the issueLabel value for this PotentialIssueStatusType.
     * 
     * @param issueLabel
     */
    public void setIssueLabel(java.lang.String issueLabel) {
        this.issueLabel = issueLabel;
    }


    /**
     * Gets the issueDescription value for this PotentialIssueStatusType.
     * 
     * @return issueDescription
     */
    public java.lang.String getIssueDescription() {
        return issueDescription;
    }


    /**
     * Sets the issueDescription value for this PotentialIssueStatusType.
     * 
     * @param issueDescription
     */
    public void setIssueDescription(java.lang.String issueDescription) {
        this.issueDescription = issueDescription;
    }


    /**
     * Gets the changePriority value for this PotentialIssueStatusType.
     * 
     * @return changePriority
     */
    public java.lang.String getChangePriority() {
        return changePriority;
    }


    /**
     * Sets the changePriority value for this PotentialIssueStatusType.
     * 
     * @param changePriority
     */
    public void setChangePriority(java.lang.String changePriority) {
        this.changePriority = changePriority;
    }


    /**
     * Gets the profileId value for this PotentialIssueStatusType.
     * 
     * @return profileId
     */
    public long getProfileId() {
        return profileId;
    }


    /**
     * Sets the profileId value for this PotentialIssueStatusType.
     * 
     * @param profileId
     */
    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }


    /**
     * Gets the actionCode value for this PotentialIssueStatusType.
     * 
     * @return actionCode
     */
    public java.lang.String getActionCode() {
        return actionCode;
    }


    /**
     * Sets the actionCode value for this PotentialIssueStatusType.
     * 
     * @param actionCode
     */
    public void setActionCode(java.lang.String actionCode) {
        this.actionCode = actionCode;
    }


    /**
     * Gets the actionLabel value for this PotentialIssueStatusType.
     * 
     * @return actionLabel
     */
    public java.lang.String getActionLabel() {
        return actionLabel;
    }


    /**
     * Sets the actionLabel value for this PotentialIssueStatusType.
     * 
     * @param actionLabel
     */
    public void setActionLabel(java.lang.String actionLabel) {
        this.actionLabel = actionLabel;
    }


    /**
     * Gets the expectMin value for this PotentialIssueStatusType.
     * 
     * @return expectMin
     */
    public long getExpectMin() {
        return expectMin;
    }


    /**
     * Sets the expectMin value for this PotentialIssueStatusType.
     * 
     * @param expectMin
     */
    public void setExpectMin(long expectMin) {
        this.expectMin = expectMin;
    }


    /**
     * Gets the expectMax value for this PotentialIssueStatusType.
     * 
     * @return expectMax
     */
    public long getExpectMax() {
        return expectMax;
    }


    /**
     * Sets the expectMax value for this PotentialIssueStatusType.
     * 
     * @param expectMax
     */
    public void setExpectMax(long expectMax) {
        this.expectMax = expectMax;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PotentialIssueStatusType)) return false;
        PotentialIssueStatusType other = (PotentialIssueStatusType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.issueId == other.getIssueId() &&
            ((this.issueLabel==null && other.getIssueLabel()==null) || 
             (this.issueLabel!=null &&
              this.issueLabel.equals(other.getIssueLabel()))) &&
            ((this.issueDescription==null && other.getIssueDescription()==null) || 
             (this.issueDescription!=null &&
              this.issueDescription.equals(other.getIssueDescription()))) &&
            ((this.changePriority==null && other.getChangePriority()==null) || 
             (this.changePriority!=null &&
              this.changePriority.equals(other.getChangePriority()))) &&
            this.profileId == other.getProfileId() &&
            ((this.actionCode==null && other.getActionCode()==null) || 
             (this.actionCode!=null &&
              this.actionCode.equals(other.getActionCode()))) &&
            ((this.actionLabel==null && other.getActionLabel()==null) || 
             (this.actionLabel!=null &&
              this.actionLabel.equals(other.getActionLabel()))) &&
            this.expectMin == other.getExpectMin() &&
            this.expectMax == other.getExpectMax();
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
        _hashCode += new Long(getIssueId()).hashCode();
        if (getIssueLabel() != null) {
            _hashCode += getIssueLabel().hashCode();
        }
        if (getIssueDescription() != null) {
            _hashCode += getIssueDescription().hashCode();
        }
        if (getChangePriority() != null) {
            _hashCode += getChangePriority().hashCode();
        }
        _hashCode += new Long(getProfileId()).hashCode();
        if (getActionCode() != null) {
            _hashCode += getActionCode().hashCode();
        }
        if (getActionLabel() != null) {
            _hashCode += getActionLabel().hashCode();
        }
        _hashCode += new Long(getExpectMin()).hashCode();
        _hashCode += new Long(getExpectMax()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PotentialIssueStatusType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "PotentialIssueStatusType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changePriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "changePriority"));
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
        elemField.setFieldName("expectMin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "expectMin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectMax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "expectMax"));
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
