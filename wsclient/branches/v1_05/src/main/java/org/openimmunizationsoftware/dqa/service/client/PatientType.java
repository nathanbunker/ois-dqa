
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for patientType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="patientType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressCountyParish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressStreet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressStreet2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aliasFirst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aliasLast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aliasMiddle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aliasPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aliasSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aliasTypeCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthMultiple" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deathIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ethincityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="facilityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="facilityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="financialEligibility" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idMedicaid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSsn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSubmitterAssignAuth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSubmitterNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSubmitterTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motherMaidenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameFirst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameLast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameMiddle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physianNameFirst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physianNameLast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicianNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="protectionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publicityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="raceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registryStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sexCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skipped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patientType", propOrder = {
    "addressCity",
    "addressCountry",
    "addressCountyParish",
    "addressState",
    "addressStreet",
    "addressStreet2",
    "addressType",
    "addressZip",
    "aliasFirst",
    "aliasLast",
    "aliasMiddle",
    "aliasPrefix",
    "aliasSuffix",
    "aliasTypeCode",
    "birthDate",
    "birthMultiple",
    "birthOrder",
    "birthPlace",
    "deathIndicator",
    "ethincityCode",
    "facilityId",
    "facilityName",
    "financialEligibility",
    "idMedicaid",
    "idSsn",
    "idSubmitterAssignAuth",
    "idSubmitterNumber",
    "idSubmitterTypeCode",
    "motherMaidenName",
    "nameFirst",
    "nameLast",
    "nameMiddle",
    "namePrefix",
    "nameSuffix",
    "nameTypeCode",
    "patientId",
    "phoneNumber",
    "physianNameFirst",
    "physianNameLast",
    "physicianNumber",
    "primaryLanguageCode",
    "protectionCode",
    "publicityCode",
    "raceCode",
    "registryStatus",
    "sexCode",
    "skipped"
})
public class PatientType {

    protected String addressCity;
    protected String addressCountry;
    protected String addressCountyParish;
    protected String addressState;
    protected String addressStreet;
    protected String addressStreet2;
    protected String addressType;
    protected String addressZip;
    protected String aliasFirst;
    protected String aliasLast;
    protected String aliasMiddle;
    protected String aliasPrefix;
    protected String aliasSuffix;
    protected long aliasTypeCode;
    protected String birthDate;
    protected String birthMultiple;
    protected String birthOrder;
    protected String birthPlace;
    protected String deathIndicator;
    protected String ethincityCode;
    protected long facilityId;
    protected String facilityName;
    protected String financialEligibility;
    protected String idMedicaid;
    protected String idSsn;
    protected String idSubmitterAssignAuth;
    protected String idSubmitterNumber;
    protected String idSubmitterTypeCode;
    protected String motherMaidenName;
    protected String nameFirst;
    protected String nameLast;
    protected String nameMiddle;
    protected String namePrefix;
    protected String nameSuffix;
    protected String nameTypeCode;
    protected long patientId;
    protected String phoneNumber;
    protected String physianNameFirst;
    protected String physianNameLast;
    protected String physicianNumber;
    protected String primaryLanguageCode;
    protected String protectionCode;
    protected String publicityCode;
    protected String raceCode;
    protected String registryStatus;
    protected String sexCode;
    protected String skipped;

    /**
     * Gets the value of the addressCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets the value of the addressCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCity(String value) {
        this.addressCity = value;
    }

    /**
     * Gets the value of the addressCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCountry() {
        return addressCountry;
    }

    /**
     * Sets the value of the addressCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCountry(String value) {
        this.addressCountry = value;
    }

    /**
     * Gets the value of the addressCountyParish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCountyParish() {
        return addressCountyParish;
    }

    /**
     * Sets the value of the addressCountyParish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCountyParish(String value) {
        this.addressCountyParish = value;
    }

    /**
     * Gets the value of the addressState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressState() {
        return addressState;
    }

    /**
     * Sets the value of the addressState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressState(String value) {
        this.addressState = value;
    }

    /**
     * Gets the value of the addressStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressStreet() {
        return addressStreet;
    }

    /**
     * Sets the value of the addressStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressStreet(String value) {
        this.addressStreet = value;
    }

    /**
     * Gets the value of the addressStreet2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressStreet2() {
        return addressStreet2;
    }

    /**
     * Sets the value of the addressStreet2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressStreet2(String value) {
        this.addressStreet2 = value;
    }

    /**
     * Gets the value of the addressType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * Sets the value of the addressType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressType(String value) {
        this.addressType = value;
    }

    /**
     * Gets the value of the addressZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressZip() {
        return addressZip;
    }

    /**
     * Sets the value of the addressZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressZip(String value) {
        this.addressZip = value;
    }

    /**
     * Gets the value of the aliasFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasFirst() {
        return aliasFirst;
    }

    /**
     * Sets the value of the aliasFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasFirst(String value) {
        this.aliasFirst = value;
    }

    /**
     * Gets the value of the aliasLast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasLast() {
        return aliasLast;
    }

    /**
     * Sets the value of the aliasLast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasLast(String value) {
        this.aliasLast = value;
    }

    /**
     * Gets the value of the aliasMiddle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasMiddle() {
        return aliasMiddle;
    }

    /**
     * Sets the value of the aliasMiddle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasMiddle(String value) {
        this.aliasMiddle = value;
    }

    /**
     * Gets the value of the aliasPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasPrefix() {
        return aliasPrefix;
    }

    /**
     * Sets the value of the aliasPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasPrefix(String value) {
        this.aliasPrefix = value;
    }

    /**
     * Gets the value of the aliasSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasSuffix() {
        return aliasSuffix;
    }

    /**
     * Sets the value of the aliasSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasSuffix(String value) {
        this.aliasSuffix = value;
    }

    /**
     * Gets the value of the aliasTypeCode property.
     * 
     */
    public long getAliasTypeCode() {
        return aliasTypeCode;
    }

    /**
     * Sets the value of the aliasTypeCode property.
     * 
     */
    public void setAliasTypeCode(long value) {
        this.aliasTypeCode = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the birthMultiple property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthMultiple() {
        return birthMultiple;
    }

    /**
     * Sets the value of the birthMultiple property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthMultiple(String value) {
        this.birthMultiple = value;
    }

    /**
     * Gets the value of the birthOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthOrder() {
        return birthOrder;
    }

    /**
     * Sets the value of the birthOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthOrder(String value) {
        this.birthOrder = value;
    }

    /**
     * Gets the value of the birthPlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * Sets the value of the birthPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthPlace(String value) {
        this.birthPlace = value;
    }

    /**
     * Gets the value of the deathIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeathIndicator() {
        return deathIndicator;
    }

    /**
     * Sets the value of the deathIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeathIndicator(String value) {
        this.deathIndicator = value;
    }

    /**
     * Gets the value of the ethincityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEthincityCode() {
        return ethincityCode;
    }

    /**
     * Sets the value of the ethincityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEthincityCode(String value) {
        this.ethincityCode = value;
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
     * Gets the value of the financialEligibility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinancialEligibility() {
        return financialEligibility;
    }

    /**
     * Sets the value of the financialEligibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinancialEligibility(String value) {
        this.financialEligibility = value;
    }

    /**
     * Gets the value of the idMedicaid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMedicaid() {
        return idMedicaid;
    }

    /**
     * Sets the value of the idMedicaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMedicaid(String value) {
        this.idMedicaid = value;
    }

    /**
     * Gets the value of the idSsn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSsn() {
        return idSsn;
    }

    /**
     * Sets the value of the idSsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSsn(String value) {
        this.idSsn = value;
    }

    /**
     * Gets the value of the idSubmitterAssignAuth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSubmitterAssignAuth() {
        return idSubmitterAssignAuth;
    }

    /**
     * Sets the value of the idSubmitterAssignAuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSubmitterAssignAuth(String value) {
        this.idSubmitterAssignAuth = value;
    }

    /**
     * Gets the value of the idSubmitterNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSubmitterNumber() {
        return idSubmitterNumber;
    }

    /**
     * Sets the value of the idSubmitterNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSubmitterNumber(String value) {
        this.idSubmitterNumber = value;
    }

    /**
     * Gets the value of the idSubmitterTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSubmitterTypeCode() {
        return idSubmitterTypeCode;
    }

    /**
     * Sets the value of the idSubmitterTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSubmitterTypeCode(String value) {
        this.idSubmitterTypeCode = value;
    }

    /**
     * Gets the value of the motherMaidenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    /**
     * Sets the value of the motherMaidenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotherMaidenName(String value) {
        this.motherMaidenName = value;
    }

    /**
     * Gets the value of the nameFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameFirst() {
        return nameFirst;
    }

    /**
     * Sets the value of the nameFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameFirst(String value) {
        this.nameFirst = value;
    }

    /**
     * Gets the value of the nameLast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameLast() {
        return nameLast;
    }

    /**
     * Sets the value of the nameLast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameLast(String value) {
        this.nameLast = value;
    }

    /**
     * Gets the value of the nameMiddle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameMiddle() {
        return nameMiddle;
    }

    /**
     * Sets the value of the nameMiddle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameMiddle(String value) {
        this.nameMiddle = value;
    }

    /**
     * Gets the value of the namePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamePrefix() {
        return namePrefix;
    }

    /**
     * Sets the value of the namePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamePrefix(String value) {
        this.namePrefix = value;
    }

    /**
     * Gets the value of the nameSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * Sets the value of the nameSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameSuffix(String value) {
        this.nameSuffix = value;
    }

    /**
     * Gets the value of the nameTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameTypeCode() {
        return nameTypeCode;
    }

    /**
     * Sets the value of the nameTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameTypeCode(String value) {
        this.nameTypeCode = value;
    }

    /**
     * Gets the value of the patientId property.
     * 
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * Sets the value of the patientId property.
     * 
     */
    public void setPatientId(long value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the physianNameFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysianNameFirst() {
        return physianNameFirst;
    }

    /**
     * Sets the value of the physianNameFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysianNameFirst(String value) {
        this.physianNameFirst = value;
    }

    /**
     * Gets the value of the physianNameLast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysianNameLast() {
        return physianNameLast;
    }

    /**
     * Sets the value of the physianNameLast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysianNameLast(String value) {
        this.physianNameLast = value;
    }

    /**
     * Gets the value of the physicianNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicianNumber() {
        return physicianNumber;
    }

    /**
     * Sets the value of the physicianNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicianNumber(String value) {
        this.physicianNumber = value;
    }

    /**
     * Gets the value of the primaryLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryLanguageCode() {
        return primaryLanguageCode;
    }

    /**
     * Sets the value of the primaryLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryLanguageCode(String value) {
        this.primaryLanguageCode = value;
    }

    /**
     * Gets the value of the protectionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtectionCode() {
        return protectionCode;
    }

    /**
     * Sets the value of the protectionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtectionCode(String value) {
        this.protectionCode = value;
    }

    /**
     * Gets the value of the publicityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicityCode() {
        return publicityCode;
    }

    /**
     * Sets the value of the publicityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicityCode(String value) {
        this.publicityCode = value;
    }

    /**
     * Gets the value of the raceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaceCode() {
        return raceCode;
    }

    /**
     * Sets the value of the raceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaceCode(String value) {
        this.raceCode = value;
    }

    /**
     * Gets the value of the registryStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryStatus() {
        return registryStatus;
    }

    /**
     * Sets the value of the registryStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryStatus(String value) {
        this.registryStatus = value;
    }

    /**
     * Gets the value of the sexCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexCode() {
        return sexCode;
    }

    /**
     * Sets the value of the sexCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexCode(String value) {
        this.sexCode = value;
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

}
