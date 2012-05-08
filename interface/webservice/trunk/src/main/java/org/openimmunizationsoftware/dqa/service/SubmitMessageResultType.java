/**
 * SubmitMessageResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service;

public class SubmitMessageResultType  implements java.io.Serializable {
    private java.lang.String messageResponse;

    private java.lang.String responseStatus;

    private java.lang.String responseText;

    private long batchId;

    private long receivedId;

    private java.lang.String hashId;

    private org.openimmunizationsoftware.dqa.service.IssueType[] errorList;

    private org.openimmunizationsoftware.dqa.service.IssueType[] warningList;

    private java.lang.String processReport;

    public SubmitMessageResultType() {
    }

    public SubmitMessageResultType(
           java.lang.String messageResponse,
           java.lang.String responseStatus,
           java.lang.String responseText,
           long batchId,
           long receivedId,
           java.lang.String hashId,
           org.openimmunizationsoftware.dqa.service.IssueType[] errorList,
           org.openimmunizationsoftware.dqa.service.IssueType[] warningList,
           java.lang.String processReport) {
           this.messageResponse = messageResponse;
           this.responseStatus = responseStatus;
           this.responseText = responseText;
           this.batchId = batchId;
           this.receivedId = receivedId;
           this.hashId = hashId;
           this.errorList = errorList;
           this.warningList = warningList;
           this.processReport = processReport;
    }


    /**
     * Gets the messageResponse value for this SubmitMessageResultType.
     * 
     * @return messageResponse
     */
    public java.lang.String getMessageResponse() {
        return messageResponse;
    }


    /**
     * Sets the messageResponse value for this SubmitMessageResultType.
     * 
     * @param messageResponse
     */
    public void setMessageResponse(java.lang.String messageResponse) {
        this.messageResponse = messageResponse;
    }


    /**
     * Gets the responseStatus value for this SubmitMessageResultType.
     * 
     * @return responseStatus
     */
    public java.lang.String getResponseStatus() {
        return responseStatus;
    }


    /**
     * Sets the responseStatus value for this SubmitMessageResultType.
     * 
     * @param responseStatus
     */
    public void setResponseStatus(java.lang.String responseStatus) {
        this.responseStatus = responseStatus;
    }


    /**
     * Gets the responseText value for this SubmitMessageResultType.
     * 
     * @return responseText
     */
    public java.lang.String getResponseText() {
        return responseText;
    }


    /**
     * Sets the responseText value for this SubmitMessageResultType.
     * 
     * @param responseText
     */
    public void setResponseText(java.lang.String responseText) {
        this.responseText = responseText;
    }


    /**
     * Gets the batchId value for this SubmitMessageResultType.
     * 
     * @return batchId
     */
    public long getBatchId() {
        return batchId;
    }


    /**
     * Sets the batchId value for this SubmitMessageResultType.
     * 
     * @param batchId
     */
    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }


    /**
     * Gets the receivedId value for this SubmitMessageResultType.
     * 
     * @return receivedId
     */
    public long getReceivedId() {
        return receivedId;
    }


    /**
     * Sets the receivedId value for this SubmitMessageResultType.
     * 
     * @param receivedId
     */
    public void setReceivedId(long receivedId) {
        this.receivedId = receivedId;
    }


    /**
     * Gets the hashId value for this SubmitMessageResultType.
     * 
     * @return hashId
     */
    public java.lang.String getHashId() {
        return hashId;
    }


    /**
     * Sets the hashId value for this SubmitMessageResultType.
     * 
     * @param hashId
     */
    public void setHashId(java.lang.String hashId) {
        this.hashId = hashId;
    }


    /**
     * Gets the errorList value for this SubmitMessageResultType.
     * 
     * @return errorList
     */
    public org.openimmunizationsoftware.dqa.service.IssueType[] getErrorList() {
        return errorList;
    }


    /**
     * Sets the errorList value for this SubmitMessageResultType.
     * 
     * @param errorList
     */
    public void setErrorList(org.openimmunizationsoftware.dqa.service.IssueType[] errorList) {
        this.errorList = errorList;
    }


    /**
     * Gets the warningList value for this SubmitMessageResultType.
     * 
     * @return warningList
     */
    public org.openimmunizationsoftware.dqa.service.IssueType[] getWarningList() {
        return warningList;
    }


    /**
     * Sets the warningList value for this SubmitMessageResultType.
     * 
     * @param warningList
     */
    public void setWarningList(org.openimmunizationsoftware.dqa.service.IssueType[] warningList) {
        this.warningList = warningList;
    }


    /**
     * Gets the processReport value for this SubmitMessageResultType.
     * 
     * @return processReport
     */
    public java.lang.String getProcessReport() {
        return processReport;
    }


    /**
     * Sets the processReport value for this SubmitMessageResultType.
     * 
     * @param processReport
     */
    public void setProcessReport(java.lang.String processReport) {
        this.processReport = processReport;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubmitMessageResultType)) return false;
        SubmitMessageResultType other = (SubmitMessageResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageResponse==null && other.getMessageResponse()==null) || 
             (this.messageResponse!=null &&
              this.messageResponse.equals(other.getMessageResponse()))) &&
            ((this.responseStatus==null && other.getResponseStatus()==null) || 
             (this.responseStatus!=null &&
              this.responseStatus.equals(other.getResponseStatus()))) &&
            ((this.responseText==null && other.getResponseText()==null) || 
             (this.responseText!=null &&
              this.responseText.equals(other.getResponseText()))) &&
            this.batchId == other.getBatchId() &&
            this.receivedId == other.getReceivedId() &&
            ((this.hashId==null && other.getHashId()==null) || 
             (this.hashId!=null &&
              this.hashId.equals(other.getHashId()))) &&
            ((this.errorList==null && other.getErrorList()==null) || 
             (this.errorList!=null &&
              java.util.Arrays.equals(this.errorList, other.getErrorList()))) &&
            ((this.warningList==null && other.getWarningList()==null) || 
             (this.warningList!=null &&
              java.util.Arrays.equals(this.warningList, other.getWarningList()))) &&
            ((this.processReport==null && other.getProcessReport()==null) || 
             (this.processReport!=null &&
              this.processReport.equals(other.getProcessReport())));
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
        if (getMessageResponse() != null) {
            _hashCode += getMessageResponse().hashCode();
        }
        if (getResponseStatus() != null) {
            _hashCode += getResponseStatus().hashCode();
        }
        if (getResponseText() != null) {
            _hashCode += getResponseText().hashCode();
        }
        _hashCode += new Long(getBatchId()).hashCode();
        _hashCode += new Long(getReceivedId()).hashCode();
        if (getHashId() != null) {
            _hashCode += getHashId().hashCode();
        }
        if (getErrorList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWarningList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWarningList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWarningList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProcessReport() != null) {
            _hashCode += getProcessReport().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubmitMessageResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "SubmitMessageResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "responseStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "responseText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "batchId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivedId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "receivedId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "hashId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "errorList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueList"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("warningList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "warningList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "IssueType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "issueList"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processReport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "processReport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
