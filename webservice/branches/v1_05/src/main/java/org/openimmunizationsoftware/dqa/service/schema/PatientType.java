/**
 * PatientType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.schema;

public class PatientType  implements java.io.Serializable {
    private long patientId;

    private java.lang.String skipped;

    private java.lang.String addressCity;

    private java.lang.String addressCountry;

    private java.lang.String addressCountyParish;

    private java.lang.String addressState;

    private java.lang.String addressStreet;

    private java.lang.String addressStreet2;

    private java.lang.String addressType;

    private java.lang.String addressZip;

    private java.lang.String aliasFirst;

    private java.lang.String aliasLast;

    private java.lang.String aliasMiddle;

    private java.lang.String aliasPrefix;

    private java.lang.String aliasSuffix;

    private long aliasTypeCode;

    private java.lang.String birthDate;

    private java.lang.String birthMultiple;

    private java.lang.String birthOrder;

    private java.lang.String birthPlace;

    private java.lang.String deathIndicator;

    private java.lang.String ethincityCode;

    private long facilityId;

    private java.lang.String facilityName;

    private java.lang.String financialEligibility;

    private java.lang.String idMedicaid;

    private java.lang.String idSsn;

    private java.lang.String idSubmitterAssignAuth;

    private java.lang.String idSubmitterNumber;

    private java.lang.String idSubmitterTypeCode;

    private java.lang.String motherMaidenName;

    private java.lang.String nameFirst;

    private java.lang.String nameLast;

    private java.lang.String nameMiddle;

    private java.lang.String namePrefix;

    private java.lang.String nameSuffix;

    private java.lang.String nameTypeCode;

    private java.lang.String phoneNumber;

    private java.lang.String physianNameFirst;

    private java.lang.String physianNameLast;

    private java.lang.String physicianNumber;

    private java.lang.String primaryLanguageCode;

    private java.lang.String protectionCode;

    private java.lang.String publicityCode;

    private java.lang.String raceCode;

    private java.lang.String registryStatus;

    private java.lang.String sexCode;

    public PatientType() {
    }

    public PatientType(
           long patientId,
           java.lang.String skipped,
           java.lang.String addressCity,
           java.lang.String addressCountry,
           java.lang.String addressCountyParish,
           java.lang.String addressState,
           java.lang.String addressStreet,
           java.lang.String addressStreet2,
           java.lang.String addressType,
           java.lang.String addressZip,
           java.lang.String aliasFirst,
           java.lang.String aliasLast,
           java.lang.String aliasMiddle,
           java.lang.String aliasPrefix,
           java.lang.String aliasSuffix,
           long aliasTypeCode,
           java.lang.String birthDate,
           java.lang.String birthMultiple,
           java.lang.String birthOrder,
           java.lang.String birthPlace,
           java.lang.String deathIndicator,
           java.lang.String ethincityCode,
           long facilityId,
           java.lang.String facilityName,
           java.lang.String financialEligibility,
           java.lang.String idMedicaid,
           java.lang.String idSsn,
           java.lang.String idSubmitterAssignAuth,
           java.lang.String idSubmitterNumber,
           java.lang.String idSubmitterTypeCode,
           java.lang.String motherMaidenName,
           java.lang.String nameFirst,
           java.lang.String nameLast,
           java.lang.String nameMiddle,
           java.lang.String namePrefix,
           java.lang.String nameSuffix,
           java.lang.String nameTypeCode,
           java.lang.String phoneNumber,
           java.lang.String physianNameFirst,
           java.lang.String physianNameLast,
           java.lang.String physicianNumber,
           java.lang.String primaryLanguageCode,
           java.lang.String protectionCode,
           java.lang.String publicityCode,
           java.lang.String raceCode,
           java.lang.String registryStatus,
           java.lang.String sexCode) {
           this.patientId = patientId;
           this.skipped = skipped;
           this.addressCity = addressCity;
           this.addressCountry = addressCountry;
           this.addressCountyParish = addressCountyParish;
           this.addressState = addressState;
           this.addressStreet = addressStreet;
           this.addressStreet2 = addressStreet2;
           this.addressType = addressType;
           this.addressZip = addressZip;
           this.aliasFirst = aliasFirst;
           this.aliasLast = aliasLast;
           this.aliasMiddle = aliasMiddle;
           this.aliasPrefix = aliasPrefix;
           this.aliasSuffix = aliasSuffix;
           this.aliasTypeCode = aliasTypeCode;
           this.birthDate = birthDate;
           this.birthMultiple = birthMultiple;
           this.birthOrder = birthOrder;
           this.birthPlace = birthPlace;
           this.deathIndicator = deathIndicator;
           this.ethincityCode = ethincityCode;
           this.facilityId = facilityId;
           this.facilityName = facilityName;
           this.financialEligibility = financialEligibility;
           this.idMedicaid = idMedicaid;
           this.idSsn = idSsn;
           this.idSubmitterAssignAuth = idSubmitterAssignAuth;
           this.idSubmitterNumber = idSubmitterNumber;
           this.idSubmitterTypeCode = idSubmitterTypeCode;
           this.motherMaidenName = motherMaidenName;
           this.nameFirst = nameFirst;
           this.nameLast = nameLast;
           this.nameMiddle = nameMiddle;
           this.namePrefix = namePrefix;
           this.nameSuffix = nameSuffix;
           this.nameTypeCode = nameTypeCode;
           this.phoneNumber = phoneNumber;
           this.physianNameFirst = physianNameFirst;
           this.physianNameLast = physianNameLast;
           this.physicianNumber = physicianNumber;
           this.primaryLanguageCode = primaryLanguageCode;
           this.protectionCode = protectionCode;
           this.publicityCode = publicityCode;
           this.raceCode = raceCode;
           this.registryStatus = registryStatus;
           this.sexCode = sexCode;
    }


    /**
     * Gets the patientId value for this PatientType.
     * 
     * @return patientId
     */
    public long getPatientId() {
        return patientId;
    }


    /**
     * Sets the patientId value for this PatientType.
     * 
     * @param patientId
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }


    /**
     * Gets the skipped value for this PatientType.
     * 
     * @return skipped
     */
    public java.lang.String getSkipped() {
        return skipped;
    }


    /**
     * Sets the skipped value for this PatientType.
     * 
     * @param skipped
     */
    public void setSkipped(java.lang.String skipped) {
        this.skipped = skipped;
    }


    /**
     * Gets the addressCity value for this PatientType.
     * 
     * @return addressCity
     */
    public java.lang.String getAddressCity() {
        return addressCity;
    }


    /**
     * Sets the addressCity value for this PatientType.
     * 
     * @param addressCity
     */
    public void setAddressCity(java.lang.String addressCity) {
        this.addressCity = addressCity;
    }


    /**
     * Gets the addressCountry value for this PatientType.
     * 
     * @return addressCountry
     */
    public java.lang.String getAddressCountry() {
        return addressCountry;
    }


    /**
     * Sets the addressCountry value for this PatientType.
     * 
     * @param addressCountry
     */
    public void setAddressCountry(java.lang.String addressCountry) {
        this.addressCountry = addressCountry;
    }


    /**
     * Gets the addressCountyParish value for this PatientType.
     * 
     * @return addressCountyParish
     */
    public java.lang.String getAddressCountyParish() {
        return addressCountyParish;
    }


    /**
     * Sets the addressCountyParish value for this PatientType.
     * 
     * @param addressCountyParish
     */
    public void setAddressCountyParish(java.lang.String addressCountyParish) {
        this.addressCountyParish = addressCountyParish;
    }


    /**
     * Gets the addressState value for this PatientType.
     * 
     * @return addressState
     */
    public java.lang.String getAddressState() {
        return addressState;
    }


    /**
     * Sets the addressState value for this PatientType.
     * 
     * @param addressState
     */
    public void setAddressState(java.lang.String addressState) {
        this.addressState = addressState;
    }


    /**
     * Gets the addressStreet value for this PatientType.
     * 
     * @return addressStreet
     */
    public java.lang.String getAddressStreet() {
        return addressStreet;
    }


    /**
     * Sets the addressStreet value for this PatientType.
     * 
     * @param addressStreet
     */
    public void setAddressStreet(java.lang.String addressStreet) {
        this.addressStreet = addressStreet;
    }


    /**
     * Gets the addressStreet2 value for this PatientType.
     * 
     * @return addressStreet2
     */
    public java.lang.String getAddressStreet2() {
        return addressStreet2;
    }


    /**
     * Sets the addressStreet2 value for this PatientType.
     * 
     * @param addressStreet2
     */
    public void setAddressStreet2(java.lang.String addressStreet2) {
        this.addressStreet2 = addressStreet2;
    }


    /**
     * Gets the addressType value for this PatientType.
     * 
     * @return addressType
     */
    public java.lang.String getAddressType() {
        return addressType;
    }


    /**
     * Sets the addressType value for this PatientType.
     * 
     * @param addressType
     */
    public void setAddressType(java.lang.String addressType) {
        this.addressType = addressType;
    }


    /**
     * Gets the addressZip value for this PatientType.
     * 
     * @return addressZip
     */
    public java.lang.String getAddressZip() {
        return addressZip;
    }


    /**
     * Sets the addressZip value for this PatientType.
     * 
     * @param addressZip
     */
    public void setAddressZip(java.lang.String addressZip) {
        this.addressZip = addressZip;
    }


    /**
     * Gets the aliasFirst value for this PatientType.
     * 
     * @return aliasFirst
     */
    public java.lang.String getAliasFirst() {
        return aliasFirst;
    }


    /**
     * Sets the aliasFirst value for this PatientType.
     * 
     * @param aliasFirst
     */
    public void setAliasFirst(java.lang.String aliasFirst) {
        this.aliasFirst = aliasFirst;
    }


    /**
     * Gets the aliasLast value for this PatientType.
     * 
     * @return aliasLast
     */
    public java.lang.String getAliasLast() {
        return aliasLast;
    }


    /**
     * Sets the aliasLast value for this PatientType.
     * 
     * @param aliasLast
     */
    public void setAliasLast(java.lang.String aliasLast) {
        this.aliasLast = aliasLast;
    }


    /**
     * Gets the aliasMiddle value for this PatientType.
     * 
     * @return aliasMiddle
     */
    public java.lang.String getAliasMiddle() {
        return aliasMiddle;
    }


    /**
     * Sets the aliasMiddle value for this PatientType.
     * 
     * @param aliasMiddle
     */
    public void setAliasMiddle(java.lang.String aliasMiddle) {
        this.aliasMiddle = aliasMiddle;
    }


    /**
     * Gets the aliasPrefix value for this PatientType.
     * 
     * @return aliasPrefix
     */
    public java.lang.String getAliasPrefix() {
        return aliasPrefix;
    }


    /**
     * Sets the aliasPrefix value for this PatientType.
     * 
     * @param aliasPrefix
     */
    public void setAliasPrefix(java.lang.String aliasPrefix) {
        this.aliasPrefix = aliasPrefix;
    }


    /**
     * Gets the aliasSuffix value for this PatientType.
     * 
     * @return aliasSuffix
     */
    public java.lang.String getAliasSuffix() {
        return aliasSuffix;
    }


    /**
     * Sets the aliasSuffix value for this PatientType.
     * 
     * @param aliasSuffix
     */
    public void setAliasSuffix(java.lang.String aliasSuffix) {
        this.aliasSuffix = aliasSuffix;
    }


    /**
     * Gets the aliasTypeCode value for this PatientType.
     * 
     * @return aliasTypeCode
     */
    public long getAliasTypeCode() {
        return aliasTypeCode;
    }


    /**
     * Sets the aliasTypeCode value for this PatientType.
     * 
     * @param aliasTypeCode
     */
    public void setAliasTypeCode(long aliasTypeCode) {
        this.aliasTypeCode = aliasTypeCode;
    }


    /**
     * Gets the birthDate value for this PatientType.
     * 
     * @return birthDate
     */
    public java.lang.String getBirthDate() {
        return birthDate;
    }


    /**
     * Sets the birthDate value for this PatientType.
     * 
     * @param birthDate
     */
    public void setBirthDate(java.lang.String birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Gets the birthMultiple value for this PatientType.
     * 
     * @return birthMultiple
     */
    public java.lang.String getBirthMultiple() {
        return birthMultiple;
    }


    /**
     * Sets the birthMultiple value for this PatientType.
     * 
     * @param birthMultiple
     */
    public void setBirthMultiple(java.lang.String birthMultiple) {
        this.birthMultiple = birthMultiple;
    }


    /**
     * Gets the birthOrder value for this PatientType.
     * 
     * @return birthOrder
     */
    public java.lang.String getBirthOrder() {
        return birthOrder;
    }


    /**
     * Sets the birthOrder value for this PatientType.
     * 
     * @param birthOrder
     */
    public void setBirthOrder(java.lang.String birthOrder) {
        this.birthOrder = birthOrder;
    }


    /**
     * Gets the birthPlace value for this PatientType.
     * 
     * @return birthPlace
     */
    public java.lang.String getBirthPlace() {
        return birthPlace;
    }


    /**
     * Sets the birthPlace value for this PatientType.
     * 
     * @param birthPlace
     */
    public void setBirthPlace(java.lang.String birthPlace) {
        this.birthPlace = birthPlace;
    }


    /**
     * Gets the deathIndicator value for this PatientType.
     * 
     * @return deathIndicator
     */
    public java.lang.String getDeathIndicator() {
        return deathIndicator;
    }


    /**
     * Sets the deathIndicator value for this PatientType.
     * 
     * @param deathIndicator
     */
    public void setDeathIndicator(java.lang.String deathIndicator) {
        this.deathIndicator = deathIndicator;
    }


    /**
     * Gets the ethincityCode value for this PatientType.
     * 
     * @return ethincityCode
     */
    public java.lang.String getEthincityCode() {
        return ethincityCode;
    }


    /**
     * Sets the ethincityCode value for this PatientType.
     * 
     * @param ethincityCode
     */
    public void setEthincityCode(java.lang.String ethincityCode) {
        this.ethincityCode = ethincityCode;
    }


    /**
     * Gets the facilityId value for this PatientType.
     * 
     * @return facilityId
     */
    public long getFacilityId() {
        return facilityId;
    }


    /**
     * Sets the facilityId value for this PatientType.
     * 
     * @param facilityId
     */
    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
    }


    /**
     * Gets the facilityName value for this PatientType.
     * 
     * @return facilityName
     */
    public java.lang.String getFacilityName() {
        return facilityName;
    }


    /**
     * Sets the facilityName value for this PatientType.
     * 
     * @param facilityName
     */
    public void setFacilityName(java.lang.String facilityName) {
        this.facilityName = facilityName;
    }


    /**
     * Gets the financialEligibility value for this PatientType.
     * 
     * @return financialEligibility
     */
    public java.lang.String getFinancialEligibility() {
        return financialEligibility;
    }


    /**
     * Sets the financialEligibility value for this PatientType.
     * 
     * @param financialEligibility
     */
    public void setFinancialEligibility(java.lang.String financialEligibility) {
        this.financialEligibility = financialEligibility;
    }


    /**
     * Gets the idMedicaid value for this PatientType.
     * 
     * @return idMedicaid
     */
    public java.lang.String getIdMedicaid() {
        return idMedicaid;
    }


    /**
     * Sets the idMedicaid value for this PatientType.
     * 
     * @param idMedicaid
     */
    public void setIdMedicaid(java.lang.String idMedicaid) {
        this.idMedicaid = idMedicaid;
    }


    /**
     * Gets the idSsn value for this PatientType.
     * 
     * @return idSsn
     */
    public java.lang.String getIdSsn() {
        return idSsn;
    }


    /**
     * Sets the idSsn value for this PatientType.
     * 
     * @param idSsn
     */
    public void setIdSsn(java.lang.String idSsn) {
        this.idSsn = idSsn;
    }


    /**
     * Gets the idSubmitterAssignAuth value for this PatientType.
     * 
     * @return idSubmitterAssignAuth
     */
    public java.lang.String getIdSubmitterAssignAuth() {
        return idSubmitterAssignAuth;
    }


    /**
     * Sets the idSubmitterAssignAuth value for this PatientType.
     * 
     * @param idSubmitterAssignAuth
     */
    public void setIdSubmitterAssignAuth(java.lang.String idSubmitterAssignAuth) {
        this.idSubmitterAssignAuth = idSubmitterAssignAuth;
    }


    /**
     * Gets the idSubmitterNumber value for this PatientType.
     * 
     * @return idSubmitterNumber
     */
    public java.lang.String getIdSubmitterNumber() {
        return idSubmitterNumber;
    }


    /**
     * Sets the idSubmitterNumber value for this PatientType.
     * 
     * @param idSubmitterNumber
     */
    public void setIdSubmitterNumber(java.lang.String idSubmitterNumber) {
        this.idSubmitterNumber = idSubmitterNumber;
    }


    /**
     * Gets the idSubmitterTypeCode value for this PatientType.
     * 
     * @return idSubmitterTypeCode
     */
    public java.lang.String getIdSubmitterTypeCode() {
        return idSubmitterTypeCode;
    }


    /**
     * Sets the idSubmitterTypeCode value for this PatientType.
     * 
     * @param idSubmitterTypeCode
     */
    public void setIdSubmitterTypeCode(java.lang.String idSubmitterTypeCode) {
        this.idSubmitterTypeCode = idSubmitterTypeCode;
    }


    /**
     * Gets the motherMaidenName value for this PatientType.
     * 
     * @return motherMaidenName
     */
    public java.lang.String getMotherMaidenName() {
        return motherMaidenName;
    }


    /**
     * Sets the motherMaidenName value for this PatientType.
     * 
     * @param motherMaidenName
     */
    public void setMotherMaidenName(java.lang.String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }


    /**
     * Gets the nameFirst value for this PatientType.
     * 
     * @return nameFirst
     */
    public java.lang.String getNameFirst() {
        return nameFirst;
    }


    /**
     * Sets the nameFirst value for this PatientType.
     * 
     * @param nameFirst
     */
    public void setNameFirst(java.lang.String nameFirst) {
        this.nameFirst = nameFirst;
    }


    /**
     * Gets the nameLast value for this PatientType.
     * 
     * @return nameLast
     */
    public java.lang.String getNameLast() {
        return nameLast;
    }


    /**
     * Sets the nameLast value for this PatientType.
     * 
     * @param nameLast
     */
    public void setNameLast(java.lang.String nameLast) {
        this.nameLast = nameLast;
    }


    /**
     * Gets the nameMiddle value for this PatientType.
     * 
     * @return nameMiddle
     */
    public java.lang.String getNameMiddle() {
        return nameMiddle;
    }


    /**
     * Sets the nameMiddle value for this PatientType.
     * 
     * @param nameMiddle
     */
    public void setNameMiddle(java.lang.String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }


    /**
     * Gets the namePrefix value for this PatientType.
     * 
     * @return namePrefix
     */
    public java.lang.String getNamePrefix() {
        return namePrefix;
    }


    /**
     * Sets the namePrefix value for this PatientType.
     * 
     * @param namePrefix
     */
    public void setNamePrefix(java.lang.String namePrefix) {
        this.namePrefix = namePrefix;
    }


    /**
     * Gets the nameSuffix value for this PatientType.
     * 
     * @return nameSuffix
     */
    public java.lang.String getNameSuffix() {
        return nameSuffix;
    }


    /**
     * Sets the nameSuffix value for this PatientType.
     * 
     * @param nameSuffix
     */
    public void setNameSuffix(java.lang.String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }


    /**
     * Gets the nameTypeCode value for this PatientType.
     * 
     * @return nameTypeCode
     */
    public java.lang.String getNameTypeCode() {
        return nameTypeCode;
    }


    /**
     * Sets the nameTypeCode value for this PatientType.
     * 
     * @param nameTypeCode
     */
    public void setNameTypeCode(java.lang.String nameTypeCode) {
        this.nameTypeCode = nameTypeCode;
    }


    /**
     * Gets the phoneNumber value for this PatientType.
     * 
     * @return phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this PatientType.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the physianNameFirst value for this PatientType.
     * 
     * @return physianNameFirst
     */
    public java.lang.String getPhysianNameFirst() {
        return physianNameFirst;
    }


    /**
     * Sets the physianNameFirst value for this PatientType.
     * 
     * @param physianNameFirst
     */
    public void setPhysianNameFirst(java.lang.String physianNameFirst) {
        this.physianNameFirst = physianNameFirst;
    }


    /**
     * Gets the physianNameLast value for this PatientType.
     * 
     * @return physianNameLast
     */
    public java.lang.String getPhysianNameLast() {
        return physianNameLast;
    }


    /**
     * Sets the physianNameLast value for this PatientType.
     * 
     * @param physianNameLast
     */
    public void setPhysianNameLast(java.lang.String physianNameLast) {
        this.physianNameLast = physianNameLast;
    }


    /**
     * Gets the physicianNumber value for this PatientType.
     * 
     * @return physicianNumber
     */
    public java.lang.String getPhysicianNumber() {
        return physicianNumber;
    }


    /**
     * Sets the physicianNumber value for this PatientType.
     * 
     * @param physicianNumber
     */
    public void setPhysicianNumber(java.lang.String physicianNumber) {
        this.physicianNumber = physicianNumber;
    }


    /**
     * Gets the primaryLanguageCode value for this PatientType.
     * 
     * @return primaryLanguageCode
     */
    public java.lang.String getPrimaryLanguageCode() {
        return primaryLanguageCode;
    }


    /**
     * Sets the primaryLanguageCode value for this PatientType.
     * 
     * @param primaryLanguageCode
     */
    public void setPrimaryLanguageCode(java.lang.String primaryLanguageCode) {
        this.primaryLanguageCode = primaryLanguageCode;
    }


    /**
     * Gets the protectionCode value for this PatientType.
     * 
     * @return protectionCode
     */
    public java.lang.String getProtectionCode() {
        return protectionCode;
    }


    /**
     * Sets the protectionCode value for this PatientType.
     * 
     * @param protectionCode
     */
    public void setProtectionCode(java.lang.String protectionCode) {
        this.protectionCode = protectionCode;
    }


    /**
     * Gets the publicityCode value for this PatientType.
     * 
     * @return publicityCode
     */
    public java.lang.String getPublicityCode() {
        return publicityCode;
    }


    /**
     * Sets the publicityCode value for this PatientType.
     * 
     * @param publicityCode
     */
    public void setPublicityCode(java.lang.String publicityCode) {
        this.publicityCode = publicityCode;
    }


    /**
     * Gets the raceCode value for this PatientType.
     * 
     * @return raceCode
     */
    public java.lang.String getRaceCode() {
        return raceCode;
    }


    /**
     * Sets the raceCode value for this PatientType.
     * 
     * @param raceCode
     */
    public void setRaceCode(java.lang.String raceCode) {
        this.raceCode = raceCode;
    }


    /**
     * Gets the registryStatus value for this PatientType.
     * 
     * @return registryStatus
     */
    public java.lang.String getRegistryStatus() {
        return registryStatus;
    }


    /**
     * Sets the registryStatus value for this PatientType.
     * 
     * @param registryStatus
     */
    public void setRegistryStatus(java.lang.String registryStatus) {
        this.registryStatus = registryStatus;
    }


    /**
     * Gets the sexCode value for this PatientType.
     * 
     * @return sexCode
     */
    public java.lang.String getSexCode() {
        return sexCode;
    }


    /**
     * Sets the sexCode value for this PatientType.
     * 
     * @param sexCode
     */
    public void setSexCode(java.lang.String sexCode) {
        this.sexCode = sexCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PatientType)) return false;
        PatientType other = (PatientType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.patientId == other.getPatientId() &&
            ((this.skipped==null && other.getSkipped()==null) || 
             (this.skipped!=null &&
              this.skipped.equals(other.getSkipped()))) &&
            ((this.addressCity==null && other.getAddressCity()==null) || 
             (this.addressCity!=null &&
              this.addressCity.equals(other.getAddressCity()))) &&
            ((this.addressCountry==null && other.getAddressCountry()==null) || 
             (this.addressCountry!=null &&
              this.addressCountry.equals(other.getAddressCountry()))) &&
            ((this.addressCountyParish==null && other.getAddressCountyParish()==null) || 
             (this.addressCountyParish!=null &&
              this.addressCountyParish.equals(other.getAddressCountyParish()))) &&
            ((this.addressState==null && other.getAddressState()==null) || 
             (this.addressState!=null &&
              this.addressState.equals(other.getAddressState()))) &&
            ((this.addressStreet==null && other.getAddressStreet()==null) || 
             (this.addressStreet!=null &&
              this.addressStreet.equals(other.getAddressStreet()))) &&
            ((this.addressStreet2==null && other.getAddressStreet2()==null) || 
             (this.addressStreet2!=null &&
              this.addressStreet2.equals(other.getAddressStreet2()))) &&
            ((this.addressType==null && other.getAddressType()==null) || 
             (this.addressType!=null &&
              this.addressType.equals(other.getAddressType()))) &&
            ((this.addressZip==null && other.getAddressZip()==null) || 
             (this.addressZip!=null &&
              this.addressZip.equals(other.getAddressZip()))) &&
            ((this.aliasFirst==null && other.getAliasFirst()==null) || 
             (this.aliasFirst!=null &&
              this.aliasFirst.equals(other.getAliasFirst()))) &&
            ((this.aliasLast==null && other.getAliasLast()==null) || 
             (this.aliasLast!=null &&
              this.aliasLast.equals(other.getAliasLast()))) &&
            ((this.aliasMiddle==null && other.getAliasMiddle()==null) || 
             (this.aliasMiddle!=null &&
              this.aliasMiddle.equals(other.getAliasMiddle()))) &&
            ((this.aliasPrefix==null && other.getAliasPrefix()==null) || 
             (this.aliasPrefix!=null &&
              this.aliasPrefix.equals(other.getAliasPrefix()))) &&
            ((this.aliasSuffix==null && other.getAliasSuffix()==null) || 
             (this.aliasSuffix!=null &&
              this.aliasSuffix.equals(other.getAliasSuffix()))) &&
            this.aliasTypeCode == other.getAliasTypeCode() &&
            ((this.birthDate==null && other.getBirthDate()==null) || 
             (this.birthDate!=null &&
              this.birthDate.equals(other.getBirthDate()))) &&
            ((this.birthMultiple==null && other.getBirthMultiple()==null) || 
             (this.birthMultiple!=null &&
              this.birthMultiple.equals(other.getBirthMultiple()))) &&
            ((this.birthOrder==null && other.getBirthOrder()==null) || 
             (this.birthOrder!=null &&
              this.birthOrder.equals(other.getBirthOrder()))) &&
            ((this.birthPlace==null && other.getBirthPlace()==null) || 
             (this.birthPlace!=null &&
              this.birthPlace.equals(other.getBirthPlace()))) &&
            ((this.deathIndicator==null && other.getDeathIndicator()==null) || 
             (this.deathIndicator!=null &&
              this.deathIndicator.equals(other.getDeathIndicator()))) &&
            ((this.ethincityCode==null && other.getEthincityCode()==null) || 
             (this.ethincityCode!=null &&
              this.ethincityCode.equals(other.getEthincityCode()))) &&
            this.facilityId == other.getFacilityId() &&
            ((this.facilityName==null && other.getFacilityName()==null) || 
             (this.facilityName!=null &&
              this.facilityName.equals(other.getFacilityName()))) &&
            ((this.financialEligibility==null && other.getFinancialEligibility()==null) || 
             (this.financialEligibility!=null &&
              this.financialEligibility.equals(other.getFinancialEligibility()))) &&
            ((this.idMedicaid==null && other.getIdMedicaid()==null) || 
             (this.idMedicaid!=null &&
              this.idMedicaid.equals(other.getIdMedicaid()))) &&
            ((this.idSsn==null && other.getIdSsn()==null) || 
             (this.idSsn!=null &&
              this.idSsn.equals(other.getIdSsn()))) &&
            ((this.idSubmitterAssignAuth==null && other.getIdSubmitterAssignAuth()==null) || 
             (this.idSubmitterAssignAuth!=null &&
              this.idSubmitterAssignAuth.equals(other.getIdSubmitterAssignAuth()))) &&
            ((this.idSubmitterNumber==null && other.getIdSubmitterNumber()==null) || 
             (this.idSubmitterNumber!=null &&
              this.idSubmitterNumber.equals(other.getIdSubmitterNumber()))) &&
            ((this.idSubmitterTypeCode==null && other.getIdSubmitterTypeCode()==null) || 
             (this.idSubmitterTypeCode!=null &&
              this.idSubmitterTypeCode.equals(other.getIdSubmitterTypeCode()))) &&
            ((this.motherMaidenName==null && other.getMotherMaidenName()==null) || 
             (this.motherMaidenName!=null &&
              this.motherMaidenName.equals(other.getMotherMaidenName()))) &&
            ((this.nameFirst==null && other.getNameFirst()==null) || 
             (this.nameFirst!=null &&
              this.nameFirst.equals(other.getNameFirst()))) &&
            ((this.nameLast==null && other.getNameLast()==null) || 
             (this.nameLast!=null &&
              this.nameLast.equals(other.getNameLast()))) &&
            ((this.nameMiddle==null && other.getNameMiddle()==null) || 
             (this.nameMiddle!=null &&
              this.nameMiddle.equals(other.getNameMiddle()))) &&
            ((this.namePrefix==null && other.getNamePrefix()==null) || 
             (this.namePrefix!=null &&
              this.namePrefix.equals(other.getNamePrefix()))) &&
            ((this.nameSuffix==null && other.getNameSuffix()==null) || 
             (this.nameSuffix!=null &&
              this.nameSuffix.equals(other.getNameSuffix()))) &&
            ((this.nameTypeCode==null && other.getNameTypeCode()==null) || 
             (this.nameTypeCode!=null &&
              this.nameTypeCode.equals(other.getNameTypeCode()))) &&
            ((this.phoneNumber==null && other.getPhoneNumber()==null) || 
             (this.phoneNumber!=null &&
              this.phoneNumber.equals(other.getPhoneNumber()))) &&
            ((this.physianNameFirst==null && other.getPhysianNameFirst()==null) || 
             (this.physianNameFirst!=null &&
              this.physianNameFirst.equals(other.getPhysianNameFirst()))) &&
            ((this.physianNameLast==null && other.getPhysianNameLast()==null) || 
             (this.physianNameLast!=null &&
              this.physianNameLast.equals(other.getPhysianNameLast()))) &&
            ((this.physicianNumber==null && other.getPhysicianNumber()==null) || 
             (this.physicianNumber!=null &&
              this.physicianNumber.equals(other.getPhysicianNumber()))) &&
            ((this.primaryLanguageCode==null && other.getPrimaryLanguageCode()==null) || 
             (this.primaryLanguageCode!=null &&
              this.primaryLanguageCode.equals(other.getPrimaryLanguageCode()))) &&
            ((this.protectionCode==null && other.getProtectionCode()==null) || 
             (this.protectionCode!=null &&
              this.protectionCode.equals(other.getProtectionCode()))) &&
            ((this.publicityCode==null && other.getPublicityCode()==null) || 
             (this.publicityCode!=null &&
              this.publicityCode.equals(other.getPublicityCode()))) &&
            ((this.raceCode==null && other.getRaceCode()==null) || 
             (this.raceCode!=null &&
              this.raceCode.equals(other.getRaceCode()))) &&
            ((this.registryStatus==null && other.getRegistryStatus()==null) || 
             (this.registryStatus!=null &&
              this.registryStatus.equals(other.getRegistryStatus()))) &&
            ((this.sexCode==null && other.getSexCode()==null) || 
             (this.sexCode!=null &&
              this.sexCode.equals(other.getSexCode())));
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
        _hashCode += new Long(getPatientId()).hashCode();
        if (getSkipped() != null) {
            _hashCode += getSkipped().hashCode();
        }
        if (getAddressCity() != null) {
            _hashCode += getAddressCity().hashCode();
        }
        if (getAddressCountry() != null) {
            _hashCode += getAddressCountry().hashCode();
        }
        if (getAddressCountyParish() != null) {
            _hashCode += getAddressCountyParish().hashCode();
        }
        if (getAddressState() != null) {
            _hashCode += getAddressState().hashCode();
        }
        if (getAddressStreet() != null) {
            _hashCode += getAddressStreet().hashCode();
        }
        if (getAddressStreet2() != null) {
            _hashCode += getAddressStreet2().hashCode();
        }
        if (getAddressType() != null) {
            _hashCode += getAddressType().hashCode();
        }
        if (getAddressZip() != null) {
            _hashCode += getAddressZip().hashCode();
        }
        if (getAliasFirst() != null) {
            _hashCode += getAliasFirst().hashCode();
        }
        if (getAliasLast() != null) {
            _hashCode += getAliasLast().hashCode();
        }
        if (getAliasMiddle() != null) {
            _hashCode += getAliasMiddle().hashCode();
        }
        if (getAliasPrefix() != null) {
            _hashCode += getAliasPrefix().hashCode();
        }
        if (getAliasSuffix() != null) {
            _hashCode += getAliasSuffix().hashCode();
        }
        _hashCode += new Long(getAliasTypeCode()).hashCode();
        if (getBirthDate() != null) {
            _hashCode += getBirthDate().hashCode();
        }
        if (getBirthMultiple() != null) {
            _hashCode += getBirthMultiple().hashCode();
        }
        if (getBirthOrder() != null) {
            _hashCode += getBirthOrder().hashCode();
        }
        if (getBirthPlace() != null) {
            _hashCode += getBirthPlace().hashCode();
        }
        if (getDeathIndicator() != null) {
            _hashCode += getDeathIndicator().hashCode();
        }
        if (getEthincityCode() != null) {
            _hashCode += getEthincityCode().hashCode();
        }
        _hashCode += new Long(getFacilityId()).hashCode();
        if (getFacilityName() != null) {
            _hashCode += getFacilityName().hashCode();
        }
        if (getFinancialEligibility() != null) {
            _hashCode += getFinancialEligibility().hashCode();
        }
        if (getIdMedicaid() != null) {
            _hashCode += getIdMedicaid().hashCode();
        }
        if (getIdSsn() != null) {
            _hashCode += getIdSsn().hashCode();
        }
        if (getIdSubmitterAssignAuth() != null) {
            _hashCode += getIdSubmitterAssignAuth().hashCode();
        }
        if (getIdSubmitterNumber() != null) {
            _hashCode += getIdSubmitterNumber().hashCode();
        }
        if (getIdSubmitterTypeCode() != null) {
            _hashCode += getIdSubmitterTypeCode().hashCode();
        }
        if (getMotherMaidenName() != null) {
            _hashCode += getMotherMaidenName().hashCode();
        }
        if (getNameFirst() != null) {
            _hashCode += getNameFirst().hashCode();
        }
        if (getNameLast() != null) {
            _hashCode += getNameLast().hashCode();
        }
        if (getNameMiddle() != null) {
            _hashCode += getNameMiddle().hashCode();
        }
        if (getNamePrefix() != null) {
            _hashCode += getNamePrefix().hashCode();
        }
        if (getNameSuffix() != null) {
            _hashCode += getNameSuffix().hashCode();
        }
        if (getNameTypeCode() != null) {
            _hashCode += getNameTypeCode().hashCode();
        }
        if (getPhoneNumber() != null) {
            _hashCode += getPhoneNumber().hashCode();
        }
        if (getPhysianNameFirst() != null) {
            _hashCode += getPhysianNameFirst().hashCode();
        }
        if (getPhysianNameLast() != null) {
            _hashCode += getPhysianNameLast().hashCode();
        }
        if (getPhysicianNumber() != null) {
            _hashCode += getPhysicianNumber().hashCode();
        }
        if (getPrimaryLanguageCode() != null) {
            _hashCode += getPrimaryLanguageCode().hashCode();
        }
        if (getProtectionCode() != null) {
            _hashCode += getProtectionCode().hashCode();
        }
        if (getPublicityCode() != null) {
            _hashCode += getPublicityCode().hashCode();
        }
        if (getRaceCode() != null) {
            _hashCode += getRaceCode().hashCode();
        }
        if (getRegistryStatus() != null) {
            _hashCode += getRegistryStatus().hashCode();
        }
        if (getSexCode() != null) {
            _hashCode += getSexCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PatientType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "PatientType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "patientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skipped");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "skipped"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressCity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressCountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressCountyParish");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressCountyParish"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressState");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressStreet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressStreet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressStreet2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressStreet2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "addressZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aliasFirst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "aliasFirst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aliasLast");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "aliasLast"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aliasMiddle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "aliasMiddle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aliasPrefix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "aliasPrefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aliasSuffix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "aliasSuffix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aliasTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "aliasTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "birthDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthMultiple");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "birthMultiple"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "birthOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthPlace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "birthPlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deathIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "deathIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ethincityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "ethincityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("facilityId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "facilityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("facilityName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "facilityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("financialEligibility");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "financialEligibility"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMedicaid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "idMedicaid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSsn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "idSsn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSubmitterAssignAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "idSubmitterAssignAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSubmitterNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "idSubmitterNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSubmitterTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "idSubmitterTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motherMaidenName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "motherMaidenName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameFirst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nameFirst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameLast");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nameLast"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameMiddle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nameMiddle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("namePrefix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "namePrefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameSuffix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nameSuffix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "nameTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "phoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physianNameFirst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "physianNameFirst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physianNameLast");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "physianNameLast"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physicianNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "physicianNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryLanguageCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "primaryLanguageCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "protectionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "publicityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("raceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "raceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registryStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "registryStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "sexCode"));
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
