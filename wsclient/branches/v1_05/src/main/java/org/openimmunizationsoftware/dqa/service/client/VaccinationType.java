
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vaccinationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vaccinationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adminCodeCpt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adminCodeCvx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adminDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amountUnitCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bodyRouteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bodySiteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="completionStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="confidentialityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enteredByNameFirst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enteredByNameLast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enteredByNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="facilityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="facilityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="financialEligibilityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="givenByNameFirst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="givenByNameLast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="givenByNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSubmitter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="informationSourceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lotNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manufacturerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderedByNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="refusalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skipped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemEntryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vaccinationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="visPublicationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vaccinationType", propOrder = {
    "adminCodeCpt",
    "adminCodeCvx",
    "adminDate",
    "amount",
    "amountUnitCode",
    "bodyRouteCode",
    "bodySiteCode",
    "completionStatusCode",
    "confidentialityCode",
    "enteredByNameFirst",
    "enteredByNameLast",
    "enteredByNumber",
    "expirationDate",
    "facilityId",
    "facilityName",
    "financialEligibilityCode",
    "givenByNameFirst",
    "givenByNameLast",
    "givenByNumber",
    "idSubmitter",
    "informationSourceCode",
    "lotNumber",
    "manufacturerCode",
    "orderedByNumber",
    "positionId",
    "refusalCode",
    "skipped",
    "systemEntryDate",
    "vaccinationId",
    "visPublicationDate"
})
public class VaccinationType {

    protected String adminCodeCpt;
    protected String adminCodeCvx;
    protected String adminDate;
    protected String amount;
    protected String amountUnitCode;
    protected String bodyRouteCode;
    protected String bodySiteCode;
    protected String completionStatusCode;
    protected String confidentialityCode;
    protected String enteredByNameFirst;
    protected String enteredByNameLast;
    protected String enteredByNumber;
    protected String expirationDate;
    protected long facilityId;
    protected String facilityName;
    protected String financialEligibilityCode;
    protected String givenByNameFirst;
    protected String givenByNameLast;
    protected String givenByNumber;
    protected String idSubmitter;
    protected String informationSourceCode;
    protected String lotNumber;
    protected String manufacturerCode;
    protected String orderedByNumber;
    protected long positionId;
    protected String refusalCode;
    protected String skipped;
    protected String systemEntryDate;
    protected long vaccinationId;
    protected String visPublicationDate;

    /**
     * Gets the value of the adminCodeCpt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminCodeCpt() {
        return adminCodeCpt;
    }

    /**
     * Sets the value of the adminCodeCpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminCodeCpt(String value) {
        this.adminCodeCpt = value;
    }

    /**
     * Gets the value of the adminCodeCvx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminCodeCvx() {
        return adminCodeCvx;
    }

    /**
     * Sets the value of the adminCodeCvx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminCodeCvx(String value) {
        this.adminCodeCvx = value;
    }

    /**
     * Gets the value of the adminDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminDate() {
        return adminDate;
    }

    /**
     * Sets the value of the adminDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminDate(String value) {
        this.adminDate = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the amountUnitCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmountUnitCode() {
        return amountUnitCode;
    }

    /**
     * Sets the value of the amountUnitCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmountUnitCode(String value) {
        this.amountUnitCode = value;
    }

    /**
     * Gets the value of the bodyRouteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodyRouteCode() {
        return bodyRouteCode;
    }

    /**
     * Sets the value of the bodyRouteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodyRouteCode(String value) {
        this.bodyRouteCode = value;
    }

    /**
     * Gets the value of the bodySiteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodySiteCode() {
        return bodySiteCode;
    }

    /**
     * Sets the value of the bodySiteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodySiteCode(String value) {
        this.bodySiteCode = value;
    }

    /**
     * Gets the value of the completionStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompletionStatusCode() {
        return completionStatusCode;
    }

    /**
     * Sets the value of the completionStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompletionStatusCode(String value) {
        this.completionStatusCode = value;
    }

    /**
     * Gets the value of the confidentialityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfidentialityCode() {
        return confidentialityCode;
    }

    /**
     * Sets the value of the confidentialityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfidentialityCode(String value) {
        this.confidentialityCode = value;
    }

    /**
     * Gets the value of the enteredByNameFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnteredByNameFirst() {
        return enteredByNameFirst;
    }

    /**
     * Sets the value of the enteredByNameFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnteredByNameFirst(String value) {
        this.enteredByNameFirst = value;
    }

    /**
     * Gets the value of the enteredByNameLast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnteredByNameLast() {
        return enteredByNameLast;
    }

    /**
     * Sets the value of the enteredByNameLast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnteredByNameLast(String value) {
        this.enteredByNameLast = value;
    }

    /**
     * Gets the value of the enteredByNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnteredByNumber() {
        return enteredByNumber;
    }

    /**
     * Sets the value of the enteredByNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnteredByNumber(String value) {
        this.enteredByNumber = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the facilityId property.
     * 
     */
    public long getFacilityId() {
        return facilityId;
    }

    /**
     * Sets the value of the facilityId property.
     * 
     */
    public void setFacilityId(long value) {
        this.facilityId = value;
    }

    /**
     * Gets the value of the facilityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacilityName() {
        return facilityName;
    }

    /**
     * Sets the value of the facilityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacilityName(String value) {
        this.facilityName = value;
    }

    /**
     * Gets the value of the financialEligibilityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinancialEligibilityCode() {
        return financialEligibilityCode;
    }

    /**
     * Sets the value of the financialEligibilityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinancialEligibilityCode(String value) {
        this.financialEligibilityCode = value;
    }

    /**
     * Gets the value of the givenByNameFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenByNameFirst() {
        return givenByNameFirst;
    }

    /**
     * Sets the value of the givenByNameFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenByNameFirst(String value) {
        this.givenByNameFirst = value;
    }

    /**
     * Gets the value of the givenByNameLast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenByNameLast() {
        return givenByNameLast;
    }

    /**
     * Sets the value of the givenByNameLast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenByNameLast(String value) {
        this.givenByNameLast = value;
    }

    /**
     * Gets the value of the givenByNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenByNumber() {
        return givenByNumber;
    }

    /**
     * Sets the value of the givenByNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenByNumber(String value) {
        this.givenByNumber = value;
    }

    /**
     * Gets the value of the idSubmitter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSubmitter() {
        return idSubmitter;
    }

    /**
     * Sets the value of the idSubmitter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSubmitter(String value) {
        this.idSubmitter = value;
    }

    /**
     * Gets the value of the informationSourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformationSourceCode() {
        return informationSourceCode;
    }

    /**
     * Sets the value of the informationSourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformationSourceCode(String value) {
        this.informationSourceCode = value;
    }

    /**
     * Gets the value of the lotNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Sets the value of the lotNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLotNumber(String value) {
        this.lotNumber = value;
    }

    /**
     * Gets the value of the manufacturerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets the value of the manufacturerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturerCode(String value) {
        this.manufacturerCode = value;
    }

    /**
     * Gets the value of the orderedByNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderedByNumber() {
        return orderedByNumber;
    }

    /**
     * Sets the value of the orderedByNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderedByNumber(String value) {
        this.orderedByNumber = value;
    }

    /**
     * Gets the value of the positionId property.
     * 
     */
    public long getPositionId() {
        return positionId;
    }

    /**
     * Sets the value of the positionId property.
     * 
     */
    public void setPositionId(long value) {
        this.positionId = value;
    }

    /**
     * Gets the value of the refusalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefusalCode() {
        return refusalCode;
    }

    /**
     * Sets the value of the refusalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefusalCode(String value) {
        this.refusalCode = value;
    }

    /**
     * Gets the value of the skipped property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkipped() {
        return skipped;
    }

    /**
     * Sets the value of the skipped property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkipped(String value) {
        this.skipped = value;
    }

    /**
     * Gets the value of the systemEntryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemEntryDate() {
        return systemEntryDate;
    }

    /**
     * Sets the value of the systemEntryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemEntryDate(String value) {
        this.systemEntryDate = value;
    }

    /**
     * Gets the value of the vaccinationId property.
     * 
     */
    public long getVaccinationId() {
        return vaccinationId;
    }

    /**
     * Sets the value of the vaccinationId property.
     * 
     */
    public void setVaccinationId(long value) {
        this.vaccinationId = value;
    }

    /**
     * Gets the value of the visPublicationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisPublicationDate() {
        return visPublicationDate;
    }

    /**
     * Sets the value of the visPublicationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisPublicationDate(String value) {
        this.visPublicationDate = value;
    }

}
