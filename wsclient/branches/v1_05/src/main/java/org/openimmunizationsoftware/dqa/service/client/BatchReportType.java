/**
 * BatchReportType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class BatchReportType  implements java.io.Serializable {
    private long reportId;

    private long batchId;

    private long compPatientScore;

    private long compScore;

    private long compVaccinationScore;

    private long messageCount;

    private long messageWithAdminCount;

    private long nextOfKinCount;

    private long overAllScore;

    private long patientCount;

    private long patientUnderageCount;

    private long qualErrorScore;

    private long qualScore;

    private long qualWarnScore;

    private float timeAverage;

    private long timeCountEarly;

    private long timeCountLate;

    private long timeCountOntime;

    private long timeCountOldData;

    private long timeCountVeryLate;

    private java.lang.String timeDateFirst;

    private java.lang.String timeDateLast;

    private long timeScore;

    private long timeScoreEarly;

    private long timeScoreLate;

    private long timeScoreOntime;

    private long timeScoreVeryLate;

    private long vaccAdminCount;

    private long vaccDeleteCount;

    private long vaccHistoricalCount;

    private long vaccNotAdminCount;

    public BatchReportType() {
    }

    public BatchReportType(
           long reportId,
           long batchId,
           long compPatientScore,
           long compScore,
           long compVaccinationScore,
           long messageCount,
           long messageWithAdminCount,
           long nextOfKinCount,
           long overAllScore,
           long patientCount,
           long patientUnderageCount,
           long qualErrorScore,
           long qualScore,
           long qualWarnScore,
           float timeAverage,
           long timeCountEarly,
           long timeCountLate,
           long timeCountOntime,
           long timeCountOldData,
           long timeCountVeryLate,
           java.lang.String timeDateFirst,
           java.lang.String timeDateLast,
           long timeScore,
           long timeScoreEarly,
           long timeScoreLate,
           long timeScoreOntime,
           long timeScoreVeryLate,
           long vaccAdminCount,
           long vaccDeleteCount,
           long vaccHistoricalCount,
           long vaccNotAdminCount) {
           this.reportId = reportId;
           this.batchId = batchId;
           this.compPatientScore = compPatientScore;
           this.compScore = compScore;
           this.compVaccinationScore = compVaccinationScore;
           this.messageCount = messageCount;
           this.messageWithAdminCount = messageWithAdminCount;
           this.nextOfKinCount = nextOfKinCount;
           this.overAllScore = overAllScore;
           this.patientCount = patientCount;
           this.patientUnderageCount = patientUnderageCount;
           this.qualErrorScore = qualErrorScore;
           this.qualScore = qualScore;
           this.qualWarnScore = qualWarnScore;
           this.timeAverage = timeAverage;
           this.timeCountEarly = timeCountEarly;
           this.timeCountLate = timeCountLate;
           this.timeCountOntime = timeCountOntime;
           this.timeCountOldData = timeCountOldData;
           this.timeCountVeryLate = timeCountVeryLate;
           this.timeDateFirst = timeDateFirst;
           this.timeDateLast = timeDateLast;
           this.timeScore = timeScore;
           this.timeScoreEarly = timeScoreEarly;
           this.timeScoreLate = timeScoreLate;
           this.timeScoreOntime = timeScoreOntime;
           this.timeScoreVeryLate = timeScoreVeryLate;
           this.vaccAdminCount = vaccAdminCount;
           this.vaccDeleteCount = vaccDeleteCount;
           this.vaccHistoricalCount = vaccHistoricalCount;
           this.vaccNotAdminCount = vaccNotAdminCount;
    }


    /**
     * Gets the reportId value for this BatchReportType.
     * 
     * @return reportId
     */
    public long getReportId() {
        return reportId;
    }


    /**
     * Sets the reportId value for this BatchReportType.
     * 
     * @param reportId
     */
    public void setReportId(long reportId) {
        this.reportId = reportId;
    }


    /**
     * Gets the batchId value for this BatchReportType.
     * 
     * @return batchId
     */
    public long getBatchId() {
        return batchId;
    }


    /**
     * Sets the batchId value for this BatchReportType.
     * 
     * @param batchId
     */
    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }


    /**
     * Gets the compPatientScore value for this BatchReportType.
     * 
     * @return compPatientScore
     */
    public long getCompPatientScore() {
        return compPatientScore;
    }


    /**
     * Sets the compPatientScore value for this BatchReportType.
     * 
     * @param compPatientScore
     */
    public void setCompPatientScore(long compPatientScore) {
        this.compPatientScore = compPatientScore;
    }


    /**
     * Gets the compScore value for this BatchReportType.
     * 
     * @return compScore
     */
    public long getCompScore() {
        return compScore;
    }


    /**
     * Sets the compScore value for this BatchReportType.
     * 
     * @param compScore
     */
    public void setCompScore(long compScore) {
        this.compScore = compScore;
    }


    /**
     * Gets the compVaccinationScore value for this BatchReportType.
     * 
     * @return compVaccinationScore
     */
    public long getCompVaccinationScore() {
        return compVaccinationScore;
    }


    /**
     * Sets the compVaccinationScore value for this BatchReportType.
     * 
     * @param compVaccinationScore
     */
    public void setCompVaccinationScore(long compVaccinationScore) {
        this.compVaccinationScore = compVaccinationScore;
    }


    /**
     * Gets the messageCount value for this BatchReportType.
     * 
     * @return messageCount
     */
    public long getMessageCount() {
        return messageCount;
    }


    /**
     * Sets the messageCount value for this BatchReportType.
     * 
     * @param messageCount
     */
    public void setMessageCount(long messageCount) {
        this.messageCount = messageCount;
    }


    /**
     * Gets the messageWithAdminCount value for this BatchReportType.
     * 
     * @return messageWithAdminCount
     */
    public long getMessageWithAdminCount() {
        return messageWithAdminCount;
    }


    /**
     * Sets the messageWithAdminCount value for this BatchReportType.
     * 
     * @param messageWithAdminCount
     */
    public void setMessageWithAdminCount(long messageWithAdminCount) {
        this.messageWithAdminCount = messageWithAdminCount;
    }


    /**
     * Gets the nextOfKinCount value for this BatchReportType.
     * 
     * @return nextOfKinCount
     */
    public long getNextOfKinCount() {
        return nextOfKinCount;
    }


    /**
     * Sets the nextOfKinCount value for this BatchReportType.
     * 
     * @param nextOfKinCount
     */
    public void setNextOfKinCount(long nextOfKinCount) {
        this.nextOfKinCount = nextOfKinCount;
    }


    /**
     * Gets the overAllScore value for this BatchReportType.
     * 
     * @return overAllScore
     */
    public long getOverAllScore() {
        return overAllScore;
    }


    /**
     * Sets the overAllScore value for this BatchReportType.
     * 
     * @param overAllScore
     */
    public void setOverAllScore(long overAllScore) {
        this.overAllScore = overAllScore;
    }


    /**
     * Gets the patientCount value for this BatchReportType.
     * 
     * @return patientCount
     */
    public long getPatientCount() {
        return patientCount;
    }


    /**
     * Sets the patientCount value for this BatchReportType.
     * 
     * @param patientCount
     */
    public void setPatientCount(long patientCount) {
        this.patientCount = patientCount;
    }


    /**
     * Gets the patientUnderageCount value for this BatchReportType.
     * 
     * @return patientUnderageCount
     */
    public long getPatientUnderageCount() {
        return patientUnderageCount;
    }


    /**
     * Sets the patientUnderageCount value for this BatchReportType.
     * 
     * @param patientUnderageCount
     */
    public void setPatientUnderageCount(long patientUnderageCount) {
        this.patientUnderageCount = patientUnderageCount;
    }


    /**
     * Gets the qualErrorScore value for this BatchReportType.
     * 
     * @return qualErrorScore
     */
    public long getQualErrorScore() {
        return qualErrorScore;
    }


    /**
     * Sets the qualErrorScore value for this BatchReportType.
     * 
     * @param qualErrorScore
     */
    public void setQualErrorScore(long qualErrorScore) {
        this.qualErrorScore = qualErrorScore;
    }


    /**
     * Gets the qualScore value for this BatchReportType.
     * 
     * @return qualScore
     */
    public long getQualScore() {
        return qualScore;
    }


    /**
     * Sets the qualScore value for this BatchReportType.
     * 
     * @param qualScore
     */
    public void setQualScore(long qualScore) {
        this.qualScore = qualScore;
    }


    /**
     * Gets the qualWarnScore value for this BatchReportType.
     * 
     * @return qualWarnScore
     */
    public long getQualWarnScore() {
        return qualWarnScore;
    }


    /**
     * Sets the qualWarnScore value for this BatchReportType.
     * 
     * @param qualWarnScore
     */
    public void setQualWarnScore(long qualWarnScore) {
        this.qualWarnScore = qualWarnScore;
    }


    /**
     * Gets the timeAverage value for this BatchReportType.
     * 
     * @return timeAverage
     */
    public float getTimeAverage() {
        return timeAverage;
    }


    /**
     * Sets the timeAverage value for this BatchReportType.
     * 
     * @param timeAverage
     */
    public void setTimeAverage(float timeAverage) {
        this.timeAverage = timeAverage;
    }


    /**
     * Gets the timeCountEarly value for this BatchReportType.
     * 
     * @return timeCountEarly
     */
    public long getTimeCountEarly() {
        return timeCountEarly;
    }


    /**
     * Sets the timeCountEarly value for this BatchReportType.
     * 
     * @param timeCountEarly
     */
    public void setTimeCountEarly(long timeCountEarly) {
        this.timeCountEarly = timeCountEarly;
    }


    /**
     * Gets the timeCountLate value for this BatchReportType.
     * 
     * @return timeCountLate
     */
    public long getTimeCountLate() {
        return timeCountLate;
    }


    /**
     * Sets the timeCountLate value for this BatchReportType.
     * 
     * @param timeCountLate
     */
    public void setTimeCountLate(long timeCountLate) {
        this.timeCountLate = timeCountLate;
    }


    /**
     * Gets the timeCountOntime value for this BatchReportType.
     * 
     * @return timeCountOntime
     */
    public long getTimeCountOntime() {
        return timeCountOntime;
    }


    /**
     * Sets the timeCountOntime value for this BatchReportType.
     * 
     * @param timeCountOntime
     */
    public void setTimeCountOntime(long timeCountOntime) {
        this.timeCountOntime = timeCountOntime;
    }


    /**
     * Gets the timeCountOldData value for this BatchReportType.
     * 
     * @return timeCountOldData
     */
    public long getTimeCountOldData() {
        return timeCountOldData;
    }


    /**
     * Sets the timeCountOldData value for this BatchReportType.
     * 
     * @param timeCountOldData
     */
    public void setTimeCountOldData(long timeCountOldData) {
        this.timeCountOldData = timeCountOldData;
    }


    /**
     * Gets the timeCountVeryLate value for this BatchReportType.
     * 
     * @return timeCountVeryLate
     */
    public long getTimeCountVeryLate() {
        return timeCountVeryLate;
    }


    /**
     * Sets the timeCountVeryLate value for this BatchReportType.
     * 
     * @param timeCountVeryLate
     */
    public void setTimeCountVeryLate(long timeCountVeryLate) {
        this.timeCountVeryLate = timeCountVeryLate;
    }


    /**
     * Gets the timeDateFirst value for this BatchReportType.
     * 
     * @return timeDateFirst
     */
    public java.lang.String getTimeDateFirst() {
        return timeDateFirst;
    }


    /**
     * Sets the timeDateFirst value for this BatchReportType.
     * 
     * @param timeDateFirst
     */
    public void setTimeDateFirst(java.lang.String timeDateFirst) {
        this.timeDateFirst = timeDateFirst;
    }


    /**
     * Gets the timeDateLast value for this BatchReportType.
     * 
     * @return timeDateLast
     */
    public java.lang.String getTimeDateLast() {
        return timeDateLast;
    }


    /**
     * Sets the timeDateLast value for this BatchReportType.
     * 
     * @param timeDateLast
     */
    public void setTimeDateLast(java.lang.String timeDateLast) {
        this.timeDateLast = timeDateLast;
    }


    /**
     * Gets the timeScore value for this BatchReportType.
     * 
     * @return timeScore
     */
    public long getTimeScore() {
        return timeScore;
    }


    /**
     * Sets the timeScore value for this BatchReportType.
     * 
     * @param timeScore
     */
    public void setTimeScore(long timeScore) {
        this.timeScore = timeScore;
    }


    /**
     * Gets the timeScoreEarly value for this BatchReportType.
     * 
     * @return timeScoreEarly
     */
    public long getTimeScoreEarly() {
        return timeScoreEarly;
    }


    /**
     * Sets the timeScoreEarly value for this BatchReportType.
     * 
     * @param timeScoreEarly
     */
    public void setTimeScoreEarly(long timeScoreEarly) {
        this.timeScoreEarly = timeScoreEarly;
    }


    /**
     * Gets the timeScoreLate value for this BatchReportType.
     * 
     * @return timeScoreLate
     */
    public long getTimeScoreLate() {
        return timeScoreLate;
    }


    /**
     * Sets the timeScoreLate value for this BatchReportType.
     * 
     * @param timeScoreLate
     */
    public void setTimeScoreLate(long timeScoreLate) {
        this.timeScoreLate = timeScoreLate;
    }


    /**
     * Gets the timeScoreOntime value for this BatchReportType.
     * 
     * @return timeScoreOntime
     */
    public long getTimeScoreOntime() {
        return timeScoreOntime;
    }


    /**
     * Sets the timeScoreOntime value for this BatchReportType.
     * 
     * @param timeScoreOntime
     */
    public void setTimeScoreOntime(long timeScoreOntime) {
        this.timeScoreOntime = timeScoreOntime;
    }


    /**
     * Gets the timeScoreVeryLate value for this BatchReportType.
     * 
     * @return timeScoreVeryLate
     */
    public long getTimeScoreVeryLate() {
        return timeScoreVeryLate;
    }


    /**
     * Sets the timeScoreVeryLate value for this BatchReportType.
     * 
     * @param timeScoreVeryLate
     */
    public void setTimeScoreVeryLate(long timeScoreVeryLate) {
        this.timeScoreVeryLate = timeScoreVeryLate;
    }


    /**
     * Gets the vaccAdminCount value for this BatchReportType.
     * 
     * @return vaccAdminCount
     */
    public long getVaccAdminCount() {
        return vaccAdminCount;
    }


    /**
     * Sets the vaccAdminCount value for this BatchReportType.
     * 
     * @param vaccAdminCount
     */
    public void setVaccAdminCount(long vaccAdminCount) {
        this.vaccAdminCount = vaccAdminCount;
    }


    /**
     * Gets the vaccDeleteCount value for this BatchReportType.
     * 
     * @return vaccDeleteCount
     */
    public long getVaccDeleteCount() {
        return vaccDeleteCount;
    }


    /**
     * Sets the vaccDeleteCount value for this BatchReportType.
     * 
     * @param vaccDeleteCount
     */
    public void setVaccDeleteCount(long vaccDeleteCount) {
        this.vaccDeleteCount = vaccDeleteCount;
    }


    /**
     * Gets the vaccHistoricalCount value for this BatchReportType.
     * 
     * @return vaccHistoricalCount
     */
    public long getVaccHistoricalCount() {
        return vaccHistoricalCount;
    }


    /**
     * Sets the vaccHistoricalCount value for this BatchReportType.
     * 
     * @param vaccHistoricalCount
     */
    public void setVaccHistoricalCount(long vaccHistoricalCount) {
        this.vaccHistoricalCount = vaccHistoricalCount;
    }


    /**
     * Gets the vaccNotAdminCount value for this BatchReportType.
     * 
     * @return vaccNotAdminCount
     */
    public long getVaccNotAdminCount() {
        return vaccNotAdminCount;
    }


    /**
     * Sets the vaccNotAdminCount value for this BatchReportType.
     * 
     * @param vaccNotAdminCount
     */
    public void setVaccNotAdminCount(long vaccNotAdminCount) {
        this.vaccNotAdminCount = vaccNotAdminCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BatchReportType)) return false;
        BatchReportType other = (BatchReportType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.reportId == other.getReportId() &&
            this.batchId == other.getBatchId() &&
            this.compPatientScore == other.getCompPatientScore() &&
            this.compScore == other.getCompScore() &&
            this.compVaccinationScore == other.getCompVaccinationScore() &&
            this.messageCount == other.getMessageCount() &&
            this.messageWithAdminCount == other.getMessageWithAdminCount() &&
            this.nextOfKinCount == other.getNextOfKinCount() &&
            this.overAllScore == other.getOverAllScore() &&
            this.patientCount == other.getPatientCount() &&
            this.patientUnderageCount == other.getPatientUnderageCount() &&
            this.qualErrorScore == other.getQualErrorScore() &&
            this.qualScore == other.getQualScore() &&
            this.qualWarnScore == other.getQualWarnScore() &&
            this.timeAverage == other.getTimeAverage() &&
            this.timeCountEarly == other.getTimeCountEarly() &&
            this.timeCountLate == other.getTimeCountLate() &&
            this.timeCountOntime == other.getTimeCountOntime() &&
            this.timeCountOldData == other.getTimeCountOldData() &&
            this.timeCountVeryLate == other.getTimeCountVeryLate() &&
            ((this.timeDateFirst==null && other.getTimeDateFirst()==null) || 
             (this.timeDateFirst!=null &&
              this.timeDateFirst.equals(other.getTimeDateFirst()))) &&
            ((this.timeDateLast==null && other.getTimeDateLast()==null) || 
             (this.timeDateLast!=null &&
              this.timeDateLast.equals(other.getTimeDateLast()))) &&
            this.timeScore == other.getTimeScore() &&
            this.timeScoreEarly == other.getTimeScoreEarly() &&
            this.timeScoreLate == other.getTimeScoreLate() &&
            this.timeScoreOntime == other.getTimeScoreOntime() &&
            this.timeScoreVeryLate == other.getTimeScoreVeryLate() &&
            this.vaccAdminCount == other.getVaccAdminCount() &&
            this.vaccDeleteCount == other.getVaccDeleteCount() &&
            this.vaccHistoricalCount == other.getVaccHistoricalCount() &&
            this.vaccNotAdminCount == other.getVaccNotAdminCount();
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
        _hashCode += new Long(getReportId()).hashCode();
        _hashCode += new Long(getBatchId()).hashCode();
        _hashCode += new Long(getCompPatientScore()).hashCode();
        _hashCode += new Long(getCompScore()).hashCode();
        _hashCode += new Long(getCompVaccinationScore()).hashCode();
        _hashCode += new Long(getMessageCount()).hashCode();
        _hashCode += new Long(getMessageWithAdminCount()).hashCode();
        _hashCode += new Long(getNextOfKinCount()).hashCode();
        _hashCode += new Long(getOverAllScore()).hashCode();
        _hashCode += new Long(getPatientCount()).hashCode();
        _hashCode += new Long(getPatientUnderageCount()).hashCode();
        _hashCode += new Long(getQualErrorScore()).hashCode();
        _hashCode += new Long(getQualScore()).hashCode();
        _hashCode += new Long(getQualWarnScore()).hashCode();
        _hashCode += new Float(getTimeAverage()).hashCode();
        _hashCode += new Long(getTimeCountEarly()).hashCode();
        _hashCode += new Long(getTimeCountLate()).hashCode();
        _hashCode += new Long(getTimeCountOntime()).hashCode();
        _hashCode += new Long(getTimeCountOldData()).hashCode();
        _hashCode += new Long(getTimeCountVeryLate()).hashCode();
        if (getTimeDateFirst() != null) {
            _hashCode += getTimeDateFirst().hashCode();
        }
        if (getTimeDateLast() != null) {
            _hashCode += getTimeDateLast().hashCode();
        }
        _hashCode += new Long(getTimeScore()).hashCode();
        _hashCode += new Long(getTimeScoreEarly()).hashCode();
        _hashCode += new Long(getTimeScoreLate()).hashCode();
        _hashCode += new Long(getTimeScoreOntime()).hashCode();
        _hashCode += new Long(getTimeScoreVeryLate()).hashCode();
        _hashCode += new Long(getVaccAdminCount()).hashCode();
        _hashCode += new Long(getVaccDeleteCount()).hashCode();
        _hashCode += new Long(getVaccHistoricalCount()).hashCode();
        _hashCode += new Long(getVaccNotAdminCount()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BatchReportType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "BatchReportType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "reportId"));
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
        elemField.setFieldName("compPatientScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "compPatientScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "compScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compVaccinationScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "compVaccinationScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageWithAdminCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "messageWithAdminCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextOfKinCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nextOfKinCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overAllScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "overAllScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patientCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "patientCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patientUnderageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "patientUnderageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qualErrorScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "qualErrorScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qualScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "qualScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qualWarnScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "qualWarnScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeAverage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeAverage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeCountEarly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeCountEarly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeCountLate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeCountLate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeCountOntime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeCountOntime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeCountOldData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeCountOldData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeCountVeryLate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeCountVeryLate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeDateFirst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeDateFirst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeDateLast");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeDateLast"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeScoreEarly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeScoreEarly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeScoreLate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeScoreLate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeScoreOntime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeScoreOntime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeScoreVeryLate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "timeScoreVeryLate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vaccAdminCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccAdminCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vaccDeleteCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccDeleteCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vaccHistoricalCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccHistoricalCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vaccNotAdminCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccNotAdminCount"));
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
