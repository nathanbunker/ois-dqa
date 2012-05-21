/**
 * ReportTemplateType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class ReportTemplateType  implements java.io.Serializable {
    private long templateId;

    private java.lang.String templateLabel;

    private long applicationId;

    private java.lang.String applicationLabel;

    private long reportTypeId;

    private java.lang.String reportTypeLabel;

    private long baseProfileId;

    private java.lang.String baseProfileLabel;

    public ReportTemplateType() {
    }

    public ReportTemplateType(
           long templateId,
           java.lang.String templateLabel,
           long applicationId,
           java.lang.String applicationLabel,
           long reportTypeId,
           java.lang.String reportTypeLabel,
           long baseProfileId,
           java.lang.String baseProfileLabel) {
           this.templateId = templateId;
           this.templateLabel = templateLabel;
           this.applicationId = applicationId;
           this.applicationLabel = applicationLabel;
           this.reportTypeId = reportTypeId;
           this.reportTypeLabel = reportTypeLabel;
           this.baseProfileId = baseProfileId;
           this.baseProfileLabel = baseProfileLabel;
    }


    /**
     * Gets the templateId value for this ReportTemplateType.
     * 
     * @return templateId
     */
    public long getTemplateId() {
        return templateId;
    }


    /**
     * Sets the templateId value for this ReportTemplateType.
     * 
     * @param templateId
     */
    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }


    /**
     * Gets the templateLabel value for this ReportTemplateType.
     * 
     * @return templateLabel
     */
    public java.lang.String getTemplateLabel() {
        return templateLabel;
    }


    /**
     * Sets the templateLabel value for this ReportTemplateType.
     * 
     * @param templateLabel
     */
    public void setTemplateLabel(java.lang.String templateLabel) {
        this.templateLabel = templateLabel;
    }


    /**
     * Gets the applicationId value for this ReportTemplateType.
     * 
     * @return applicationId
     */
    public long getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this ReportTemplateType.
     * 
     * @param applicationId
     */
    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the applicationLabel value for this ReportTemplateType.
     * 
     * @return applicationLabel
     */
    public java.lang.String getApplicationLabel() {
        return applicationLabel;
    }


    /**
     * Sets the applicationLabel value for this ReportTemplateType.
     * 
     * @param applicationLabel
     */
    public void setApplicationLabel(java.lang.String applicationLabel) {
        this.applicationLabel = applicationLabel;
    }


    /**
     * Gets the reportTypeId value for this ReportTemplateType.
     * 
     * @return reportTypeId
     */
    public long getReportTypeId() {
        return reportTypeId;
    }


    /**
     * Sets the reportTypeId value for this ReportTemplateType.
     * 
     * @param reportTypeId
     */
    public void setReportTypeId(long reportTypeId) {
        this.reportTypeId = reportTypeId;
    }


    /**
     * Gets the reportTypeLabel value for this ReportTemplateType.
     * 
     * @return reportTypeLabel
     */
    public java.lang.String getReportTypeLabel() {
        return reportTypeLabel;
    }


    /**
     * Sets the reportTypeLabel value for this ReportTemplateType.
     * 
     * @param reportTypeLabel
     */
    public void setReportTypeLabel(java.lang.String reportTypeLabel) {
        this.reportTypeLabel = reportTypeLabel;
    }


    /**
     * Gets the baseProfileId value for this ReportTemplateType.
     * 
     * @return baseProfileId
     */
    public long getBaseProfileId() {
        return baseProfileId;
    }


    /**
     * Sets the baseProfileId value for this ReportTemplateType.
     * 
     * @param baseProfileId
     */
    public void setBaseProfileId(long baseProfileId) {
        this.baseProfileId = baseProfileId;
    }


    /**
     * Gets the baseProfileLabel value for this ReportTemplateType.
     * 
     * @return baseProfileLabel
     */
    public java.lang.String getBaseProfileLabel() {
        return baseProfileLabel;
    }


    /**
     * Sets the baseProfileLabel value for this ReportTemplateType.
     * 
     * @param baseProfileLabel
     */
    public void setBaseProfileLabel(java.lang.String baseProfileLabel) {
        this.baseProfileLabel = baseProfileLabel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportTemplateType)) return false;
        ReportTemplateType other = (ReportTemplateType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.templateId == other.getTemplateId() &&
            ((this.templateLabel==null && other.getTemplateLabel()==null) || 
             (this.templateLabel!=null &&
              this.templateLabel.equals(other.getTemplateLabel()))) &&
            this.applicationId == other.getApplicationId() &&
            ((this.applicationLabel==null && other.getApplicationLabel()==null) || 
             (this.applicationLabel!=null &&
              this.applicationLabel.equals(other.getApplicationLabel()))) &&
            this.reportTypeId == other.getReportTypeId() &&
            ((this.reportTypeLabel==null && other.getReportTypeLabel()==null) || 
             (this.reportTypeLabel!=null &&
              this.reportTypeLabel.equals(other.getReportTypeLabel()))) &&
            this.baseProfileId == other.getBaseProfileId() &&
            ((this.baseProfileLabel==null && other.getBaseProfileLabel()==null) || 
             (this.baseProfileLabel!=null &&
              this.baseProfileLabel.equals(other.getBaseProfileLabel())));
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
        _hashCode += new Long(getTemplateId()).hashCode();
        if (getTemplateLabel() != null) {
            _hashCode += getTemplateLabel().hashCode();
        }
        _hashCode += new Long(getApplicationId()).hashCode();
        if (getApplicationLabel() != null) {
            _hashCode += getApplicationLabel().hashCode();
        }
        _hashCode += new Long(getReportTypeId()).hashCode();
        if (getReportTypeLabel() != null) {
            _hashCode += getReportTypeLabel().hashCode();
        }
        _hashCode += new Long(getBaseProfileId()).hashCode();
        if (getBaseProfileLabel() != null) {
            _hashCode += getBaseProfileLabel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportTemplateType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "ReportTemplateType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "templateId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "templateLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "applicationLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "reportTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportTypeLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "reportTypeLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseProfileId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "baseProfileId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseProfileLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "baseProfileLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
