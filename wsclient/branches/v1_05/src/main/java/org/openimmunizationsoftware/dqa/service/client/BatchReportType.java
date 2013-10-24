
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for batchReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="batchReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="compPatientScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="compScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="compVaccinationScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="messageCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="messageWithAdminCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nextOfKinCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="overAllScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="patientCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="patientUnderageCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qualErrorScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qualScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qualWarnScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="reportId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeAverage" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="timeCountEarly" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeCountLate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeCountOldData" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeCountOntime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeCountVeryLate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeDateFirst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeDateLast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeScore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeScoreEarly" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeScoreLate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeScoreOntime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="timeScoreVeryLate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="vaccAdminCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="vaccDeleteCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="vaccHistoricalCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="vaccNotAdminCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchReportType", propOrder = {
    "batchId",
    "compPatientScore",
    "compScore",
    "compVaccinationScore",
    "messageCount",
    "messageWithAdminCount",
    "nextOfKinCount",
    "overAllScore",
    "patientCount",
    "patientUnderageCount",
    "qualErrorScore",
    "qualScore",
    "qualWarnScore",
    "reportId",
    "timeAverage",
    "timeCountEarly",
    "timeCountLate",
    "timeCountOldData",
    "timeCountOntime",
    "timeCountVeryLate",
    "timeDateFirst",
    "timeDateLast",
    "timeScore",
    "timeScoreEarly",
    "timeScoreLate",
    "timeScoreOntime",
    "timeScoreVeryLate",
    "vaccAdminCount",
    "vaccDeleteCount",
    "vaccHistoricalCount",
    "vaccNotAdminCount"
})
public class BatchReportType {

    protected long batchId;
    protected long compPatientScore;
    protected long compScore;
    protected long compVaccinationScore;
    protected long messageCount;
    protected long messageWithAdminCount;
    protected long nextOfKinCount;
    protected long overAllScore;
    protected long patientCount;
    protected long patientUnderageCount;
    protected long qualErrorScore;
    protected long qualScore;
    protected long qualWarnScore;
    protected long reportId;
    protected float timeAverage;
    protected long timeCountEarly;
    protected long timeCountLate;
    protected long timeCountOldData;
    protected long timeCountOntime;
    protected long timeCountVeryLate;
    protected String timeDateFirst;
    protected String timeDateLast;
    protected long timeScore;
    protected long timeScoreEarly;
    protected long timeScoreLate;
    protected long timeScoreOntime;
    protected long timeScoreVeryLate;
    protected long vaccAdminCount;
    protected long vaccDeleteCount;
    protected long vaccHistoricalCount;
    protected long vaccNotAdminCount;

    /**
     * Gets the value of the batchId property.
     * 
     */
    public long getBatchId() {
        return batchId;
    }

    /**
     * Sets the value of the batchId property.
     * 
     */
    public void setBatchId(long value) {
        this.batchId = value;
    }

    /**
     * Gets the value of the compPatientScore property.
     * 
     */
    public long getCompPatientScore() {
        return compPatientScore;
    }

    /**
     * Sets the value of the compPatientScore property.
     * 
     */
    public void setCompPatientScore(long value) {
        this.compPatientScore = value;
    }

    /**
     * Gets the value of the compScore property.
     * 
     */
    public long getCompScore() {
        return compScore;
    }

    /**
     * Sets the value of the compScore property.
     * 
     */
    public void setCompScore(long value) {
        this.compScore = value;
    }

    /**
     * Gets the value of the compVaccinationScore property.
     * 
     */
    public long getCompVaccinationScore() {
        return compVaccinationScore;
    }

    /**
     * Sets the value of the compVaccinationScore property.
     * 
     */
    public void setCompVaccinationScore(long value) {
        this.compVaccinationScore = value;
    }

    /**
     * Gets the value of the messageCount property.
     * 
     */
    public long getMessageCount() {
        return messageCount;
    }

    /**
     * Sets the value of the messageCount property.
     * 
     */
    public void setMessageCount(long value) {
        this.messageCount = value;
    }

    /**
     * Gets the value of the messageWithAdminCount property.
     * 
     */
    public long getMessageWithAdminCount() {
        return messageWithAdminCount;
    }

    /**
     * Sets the value of the messageWithAdminCount property.
     * 
     */
    public void setMessageWithAdminCount(long value) {
        this.messageWithAdminCount = value;
    }

    /**
     * Gets the value of the nextOfKinCount property.
     * 
     */
    public long getNextOfKinCount() {
        return nextOfKinCount;
    }

    /**
     * Sets the value of the nextOfKinCount property.
     * 
     */
    public void setNextOfKinCount(long value) {
        this.nextOfKinCount = value;
    }

    /**
     * Gets the value of the overAllScore property.
     * 
     */
    public long getOverAllScore() {
        return overAllScore;
    }

    /**
     * Sets the value of the overAllScore property.
     * 
     */
    public void setOverAllScore(long value) {
        this.overAllScore = value;
    }

    /**
     * Gets the value of the patientCount property.
     * 
     */
    public long getPatientCount() {
        return patientCount;
    }

    /**
     * Sets the value of the patientCount property.
     * 
     */
    public void setPatientCount(long value) {
        this.patientCount = value;
    }

    /**
     * Gets the value of the patientUnderageCount property.
     * 
     */
    public long getPatientUnderageCount() {
        return patientUnderageCount;
    }

    /**
     * Sets the value of the patientUnderageCount property.
     * 
     */
    public void setPatientUnderageCount(long value) {
        this.patientUnderageCount = value;
    }

    /**
     * Gets the value of the qualErrorScore property.
     * 
     */
    public long getQualErrorScore() {
        return qualErrorScore;
    }

    /**
     * Sets the value of the qualErrorScore property.
     * 
     */
    public void setQualErrorScore(long value) {
        this.qualErrorScore = value;
    }

    /**
     * Gets the value of the qualScore property.
     * 
     */
    public long getQualScore() {
        return qualScore;
    }

    /**
     * Sets the value of the qualScore property.
     * 
     */
    public void setQualScore(long value) {
        this.qualScore = value;
    }

    /**
     * Gets the value of the qualWarnScore property.
     * 
     */
    public long getQualWarnScore() {
        return qualWarnScore;
    }

    /**
     * Sets the value of the qualWarnScore property.
     * 
     */
    public void setQualWarnScore(long value) {
        this.qualWarnScore = value;
    }

    /**
     * Gets the value of the reportId property.
     * 
     */
    public long getReportId() {
        return reportId;
    }

    /**
     * Sets the value of the reportId property.
     * 
     */
    public void setReportId(long value) {
        this.reportId = value;
    }

    /**
     * Gets the value of the timeAverage property.
     * 
     */
    public float getTimeAverage() {
        return timeAverage;
    }

    /**
     * Sets the value of the timeAverage property.
     * 
     */
    public void setTimeAverage(float value) {
        this.timeAverage = value;
    }

    /**
     * Gets the value of the timeCountEarly property.
     * 
     */
    public long getTimeCountEarly() {
        return timeCountEarly;
    }

    /**
     * Sets the value of the timeCountEarly property.
     * 
     */
    public void setTimeCountEarly(long value) {
        this.timeCountEarly = value;
    }

    /**
     * Gets the value of the timeCountLate property.
     * 
     */
    public long getTimeCountLate() {
        return timeCountLate;
    }

    /**
     * Sets the value of the timeCountLate property.
     * 
     */
    public void setTimeCountLate(long value) {
        this.timeCountLate = value;
    }

    /**
     * Gets the value of the timeCountOldData property.
     * 
     */
    public long getTimeCountOldData() {
        return timeCountOldData;
    }

    /**
     * Sets the value of the timeCountOldData property.
     * 
     */
    public void setTimeCountOldData(long value) {
        this.timeCountOldData = value;
    }

    /**
     * Gets the value of the timeCountOntime property.
     * 
     */
    public long getTimeCountOntime() {
        return timeCountOntime;
    }

    /**
     * Sets the value of the timeCountOntime property.
     * 
     */
    public void setTimeCountOntime(long value) {
        this.timeCountOntime = value;
    }

    /**
     * Gets the value of the timeCountVeryLate property.
     * 
     */
    public long getTimeCountVeryLate() {
        return timeCountVeryLate;
    }

    /**
     * Sets the value of the timeCountVeryLate property.
     * 
     */
    public void setTimeCountVeryLate(long value) {
        this.timeCountVeryLate = value;
    }

    /**
     * Gets the value of the timeDateFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeDateFirst() {
        return timeDateFirst;
    }

    /**
     * Sets the value of the timeDateFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeDateFirst(String value) {
        this.timeDateFirst = value;
    }

    /**
     * Gets the value of the timeDateLast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeDateLast() {
        return timeDateLast;
    }

    /**
     * Sets the value of the timeDateLast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeDateLast(String value) {
        this.timeDateLast = value;
    }

    /**
     * Gets the value of the timeScore property.
     * 
     */
    public long getTimeScore() {
        return timeScore;
    }

    /**
     * Sets the value of the timeScore property.
     * 
     */
    public void setTimeScore(long value) {
        this.timeScore = value;
    }

    /**
     * Gets the value of the timeScoreEarly property.
     * 
     */
    public long getTimeScoreEarly() {
        return timeScoreEarly;
    }

    /**
     * Sets the value of the timeScoreEarly property.
     * 
     */
    public void setTimeScoreEarly(long value) {
        this.timeScoreEarly = value;
    }

    /**
     * Gets the value of the timeScoreLate property.
     * 
     */
    public long getTimeScoreLate() {
        return timeScoreLate;
    }

    /**
     * Sets the value of the timeScoreLate property.
     * 
     */
    public void setTimeScoreLate(long value) {
        this.timeScoreLate = value;
    }

    /**
     * Gets the value of the timeScoreOntime property.
     * 
     */
    public long getTimeScoreOntime() {
        return timeScoreOntime;
    }

    /**
     * Sets the value of the timeScoreOntime property.
     * 
     */
    public void setTimeScoreOntime(long value) {
        this.timeScoreOntime = value;
    }

    /**
     * Gets the value of the timeScoreVeryLate property.
     * 
     */
    public long getTimeScoreVeryLate() {
        return timeScoreVeryLate;
    }

    /**
     * Sets the value of the timeScoreVeryLate property.
     * 
     */
    public void setTimeScoreVeryLate(long value) {
        this.timeScoreVeryLate = value;
    }

    /**
     * Gets the value of the vaccAdminCount property.
     * 
     */
    public long getVaccAdminCount() {
        return vaccAdminCount;
    }

    /**
     * Sets the value of the vaccAdminCount property.
     * 
     */
    public void setVaccAdminCount(long value) {
        this.vaccAdminCount = value;
    }

    /**
     * Gets the value of the vaccDeleteCount property.
     * 
     */
    public long getVaccDeleteCount() {
        return vaccDeleteCount;
    }

    /**
     * Sets the value of the vaccDeleteCount property.
     * 
     */
    public void setVaccDeleteCount(long value) {
        this.vaccDeleteCount = value;
    }

    /**
     * Gets the value of the vaccHistoricalCount property.
     * 
     */
    public long getVaccHistoricalCount() {
        return vaccHistoricalCount;
    }

    /**
     * Sets the value of the vaccHistoricalCount property.
     * 
     */
    public void setVaccHistoricalCount(long value) {
        this.vaccHistoricalCount = value;
    }

    /**
     * Gets the value of the vaccNotAdminCount property.
     * 
     */
    public long getVaccNotAdminCount() {
        return vaccNotAdminCount;
    }

    /**
     * Sets the value of the vaccNotAdminCount property.
     * 
     */
    public void setVaccNotAdminCount(long value) {
        this.vaccNotAdminCount = value;
    }

}
