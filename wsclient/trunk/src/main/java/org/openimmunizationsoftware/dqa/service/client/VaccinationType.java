/**
 * VaccinationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openimmunizationsoftware.dqa.service.client;

public class VaccinationType  implements java.io.Serializable {
    private long vaccinationId;

    private long positionId;

    private java.lang.String skipped;

    private java.lang.String adminCodeCpt;

    private java.lang.String adminCodeCvx;

    private java.lang.String adminDate;

    private java.lang.String amount;

    private java.lang.String amountUnitCode;

    private java.lang.String bodyRouteCode;

    private java.lang.String bodySiteCode;

    private java.lang.String completionStatusCode;

    private java.lang.String confidentialityCode;

    private java.lang.String enteredByNumber;

    private java.lang.String enteredByNameFirst;

    private java.lang.String enteredByNameLast;

    private java.lang.String expirationDate;

    private long facilityId;

    private java.lang.String facilityName;

    private java.lang.String financialEligibilityCode;

    private java.lang.String givenByNumber;

    private java.lang.String givenByNameLast;

    private java.lang.String givenByNameFirst;

    private java.lang.String idSubmitter;

    private java.lang.String informationSourceCode;

    private java.lang.String lotNumber;

    private java.lang.String manufacturerCode;

    private java.lang.String orderedByNumber;

    private java.lang.String refusalCode;

    private java.lang.String systemEntryDate;

    private java.lang.String visPublicationDate;

    public VaccinationType() {
    }

    public VaccinationType(
           long vaccinationId,
           long positionId,
           java.lang.String skipped,
           java.lang.String adminCodeCpt,
           java.lang.String adminCodeCvx,
           java.lang.String adminDate,
           java.lang.String amount,
           java.lang.String amountUnitCode,
           java.lang.String bodyRouteCode,
           java.lang.String bodySiteCode,
           java.lang.String completionStatusCode,
           java.lang.String confidentialityCode,
           java.lang.String enteredByNumber,
           java.lang.String enteredByNameFirst,
           java.lang.String enteredByNameLast,
           java.lang.String expirationDate,
           long facilityId,
           java.lang.String facilityName,
           java.lang.String financialEligibilityCode,
           java.lang.String givenByNumber,
           java.lang.String givenByNameLast,
           java.lang.String givenByNameFirst,
           java.lang.String idSubmitter,
           java.lang.String informationSourceCode,
           java.lang.String lotNumber,
           java.lang.String manufacturerCode,
           java.lang.String orderedByNumber,
           java.lang.String refusalCode,
           java.lang.String systemEntryDate,
           java.lang.String visPublicationDate) {
           this.vaccinationId = vaccinationId;
           this.positionId = positionId;
           this.skipped = skipped;
           this.adminCodeCpt = adminCodeCpt;
           this.adminCodeCvx = adminCodeCvx;
           this.adminDate = adminDate;
           this.amount = amount;
           this.amountUnitCode = amountUnitCode;
           this.bodyRouteCode = bodyRouteCode;
           this.bodySiteCode = bodySiteCode;
           this.completionStatusCode = completionStatusCode;
           this.confidentialityCode = confidentialityCode;
           this.enteredByNumber = enteredByNumber;
           this.enteredByNameFirst = enteredByNameFirst;
           this.enteredByNameLast = enteredByNameLast;
           this.expirationDate = expirationDate;
           this.facilityId = facilityId;
           this.facilityName = facilityName;
           this.financialEligibilityCode = financialEligibilityCode;
           this.givenByNumber = givenByNumber;
           this.givenByNameLast = givenByNameLast;
           this.givenByNameFirst = givenByNameFirst;
           this.idSubmitter = idSubmitter;
           this.informationSourceCode = informationSourceCode;
           this.lotNumber = lotNumber;
           this.manufacturerCode = manufacturerCode;
           this.orderedByNumber = orderedByNumber;
           this.refusalCode = refusalCode;
           this.systemEntryDate = systemEntryDate;
           this.visPublicationDate = visPublicationDate;
    }


    /**
     * Gets the vaccinationId value for this VaccinationType.
     * 
     * @return vaccinationId
     */
    public long getVaccinationId() {
        return vaccinationId;
    }


    /**
     * Sets the vaccinationId value for this VaccinationType.
     * 
     * @param vaccinationId
     */
    public void setVaccinationId(long vaccinationId) {
        this.vaccinationId = vaccinationId;
    }


    /**
     * Gets the positionId value for this VaccinationType.
     * 
     * @return positionId
     */
    public long getPositionId() {
        return positionId;
    }


    /**
     * Sets the positionId value for this VaccinationType.
     * 
     * @param positionId
     */
    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }


    /**
     * Gets the skipped value for this VaccinationType.
     * 
     * @return skipped
     */
    public java.lang.String getSkipped() {
        return skipped;
    }


    /**
     * Sets the skipped value for this VaccinationType.
     * 
     * @param skipped
     */
    public void setSkipped(java.lang.String skipped) {
        this.skipped = skipped;
    }


    /**
     * Gets the adminCodeCpt value for this VaccinationType.
     * 
     * @return adminCodeCpt
     */
    public java.lang.String getAdminCodeCpt() {
        return adminCodeCpt;
    }


    /**
     * Sets the adminCodeCpt value for this VaccinationType.
     * 
     * @param adminCodeCpt
     */
    public void setAdminCodeCpt(java.lang.String adminCodeCpt) {
        this.adminCodeCpt = adminCodeCpt;
    }


    /**
     * Gets the adminCodeCvx value for this VaccinationType.
     * 
     * @return adminCodeCvx
     */
    public java.lang.String getAdminCodeCvx() {
        return adminCodeCvx;
    }


    /**
     * Sets the adminCodeCvx value for this VaccinationType.
     * 
     * @param adminCodeCvx
     */
    public void setAdminCodeCvx(java.lang.String adminCodeCvx) {
        this.adminCodeCvx = adminCodeCvx;
    }


    /**
     * Gets the adminDate value for this VaccinationType.
     * 
     * @return adminDate
     */
    public java.lang.String getAdminDate() {
        return adminDate;
    }


    /**
     * Sets the adminDate value for this VaccinationType.
     * 
     * @param adminDate
     */
    public void setAdminDate(java.lang.String adminDate) {
        this.adminDate = adminDate;
    }


    /**
     * Gets the amount value for this VaccinationType.
     * 
     * @return amount
     */
    public java.lang.String getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this VaccinationType.
     * 
     * @param amount
     */
    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }


    /**
     * Gets the amountUnitCode value for this VaccinationType.
     * 
     * @return amountUnitCode
     */
    public java.lang.String getAmountUnitCode() {
        return amountUnitCode;
    }


    /**
     * Sets the amountUnitCode value for this VaccinationType.
     * 
     * @param amountUnitCode
     */
    public void setAmountUnitCode(java.lang.String amountUnitCode) {
        this.amountUnitCode = amountUnitCode;
    }


    /**
     * Gets the bodyRouteCode value for this VaccinationType.
     * 
     * @return bodyRouteCode
     */
    public java.lang.String getBodyRouteCode() {
        return bodyRouteCode;
    }


    /**
     * Sets the bodyRouteCode value for this VaccinationType.
     * 
     * @param bodyRouteCode
     */
    public void setBodyRouteCode(java.lang.String bodyRouteCode) {
        this.bodyRouteCode = bodyRouteCode;
    }


    /**
     * Gets the bodySiteCode value for this VaccinationType.
     * 
     * @return bodySiteCode
     */
    public java.lang.String getBodySiteCode() {
        return bodySiteCode;
    }


    /**
     * Sets the bodySiteCode value for this VaccinationType.
     * 
     * @param bodySiteCode
     */
    public void setBodySiteCode(java.lang.String bodySiteCode) {
        this.bodySiteCode = bodySiteCode;
    }


    /**
     * Gets the completionStatusCode value for this VaccinationType.
     * 
     * @return completionStatusCode
     */
    public java.lang.String getCompletionStatusCode() {
        return completionStatusCode;
    }


    /**
     * Sets the completionStatusCode value for this VaccinationType.
     * 
     * @param completionStatusCode
     */
    public void setCompletionStatusCode(java.lang.String completionStatusCode) {
        this.completionStatusCode = completionStatusCode;
    }


    /**
     * Gets the confidentialityCode value for this VaccinationType.
     * 
     * @return confidentialityCode
     */
    public java.lang.String getConfidentialityCode() {
        return confidentialityCode;
    }


    /**
     * Sets the confidentialityCode value for this VaccinationType.
     * 
     * @param confidentialityCode
     */
    public void setConfidentialityCode(java.lang.String confidentialityCode) {
        this.confidentialityCode = confidentialityCode;
    }


    /**
     * Gets the enteredByNumber value for this VaccinationType.
     * 
     * @return enteredByNumber
     */
    public java.lang.String getEnteredByNumber() {
        return enteredByNumber;
    }


    /**
     * Sets the enteredByNumber value for this VaccinationType.
     * 
     * @param enteredByNumber
     */
    public void setEnteredByNumber(java.lang.String enteredByNumber) {
        this.enteredByNumber = enteredByNumber;
    }


    /**
     * Gets the enteredByNameFirst value for this VaccinationType.
     * 
     * @return enteredByNameFirst
     */
    public java.lang.String getEnteredByNameFirst() {
        return enteredByNameFirst;
    }


    /**
     * Sets the enteredByNameFirst value for this VaccinationType.
     * 
     * @param enteredByNameFirst
     */
    public void setEnteredByNameFirst(java.lang.String enteredByNameFirst) {
        this.enteredByNameFirst = enteredByNameFirst;
    }


    /**
     * Gets the enteredByNameLast value for this VaccinationType.
     * 
     * @return enteredByNameLast
     */
    public java.lang.String getEnteredByNameLast() {
        return enteredByNameLast;
    }


    /**
     * Sets the enteredByNameLast value for this VaccinationType.
     * 
     * @param enteredByNameLast
     */
    public void setEnteredByNameLast(java.lang.String enteredByNameLast) {
        this.enteredByNameLast = enteredByNameLast;
    }


    /**
     * Gets the expirationDate value for this VaccinationType.
     * 
     * @return expirationDate
     */
    public java.lang.String getExpirationDate() {
        return expirationDate;
    }


    /**
     * Sets the expirationDate value for this VaccinationType.
     * 
     * @param expirationDate
     */
    public void setExpirationDate(java.lang.String expirationDate) {
        this.expirationDate = expirationDate;
    }


    /**
     * Gets the facilityId value for this VaccinationType.
     * 
     * @return facilityId
     */
    public long getFacilityId() {
        return facilityId;
    }


    /**
     * Sets the facilityId value for this VaccinationType.
     * 
     * @param facilityId
     */
    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
    }


    /**
     * Gets the facilityName value for this VaccinationType.
     * 
     * @return facilityName
     */
    public java.lang.String getFacilityName() {
        return facilityName;
    }


    /**
     * Sets the facilityName value for this VaccinationType.
     * 
     * @param facilityName
     */
    public void setFacilityName(java.lang.String facilityName) {
        this.facilityName = facilityName;
    }


    /**
     * Gets the financialEligibilityCode value for this VaccinationType.
     * 
     * @return financialEligibilityCode
     */
    public java.lang.String getFinancialEligibilityCode() {
        return financialEligibilityCode;
    }


    /**
     * Sets the financialEligibilityCode value for this VaccinationType.
     * 
     * @param financialEligibilityCode
     */
    public void setFinancialEligibilityCode(java.lang.String financialEligibilityCode) {
        this.financialEligibilityCode = financialEligibilityCode;
    }


    /**
     * Gets the givenByNumber value for this VaccinationType.
     * 
     * @return givenByNumber
     */
    public java.lang.String getGivenByNumber() {
        return givenByNumber;
    }


    /**
     * Sets the givenByNumber value for this VaccinationType.
     * 
     * @param givenByNumber
     */
    public void setGivenByNumber(java.lang.String givenByNumber) {
        this.givenByNumber = givenByNumber;
    }


    /**
     * Gets the givenByNameLast value for this VaccinationType.
     * 
     * @return givenByNameLast
     */
    public java.lang.String getGivenByNameLast() {
        return givenByNameLast;
    }


    /**
     * Sets the givenByNameLast value for this VaccinationType.
     * 
     * @param givenByNameLast
     */
    public void setGivenByNameLast(java.lang.String givenByNameLast) {
        this.givenByNameLast = givenByNameLast;
    }


    /**
     * Gets the givenByNameFirst value for this VaccinationType.
     * 
     * @return givenByNameFirst
     */
    public java.lang.String getGivenByNameFirst() {
        return givenByNameFirst;
    }


    /**
     * Sets the givenByNameFirst value for this VaccinationType.
     * 
     * @param givenByNameFirst
     */
    public void setGivenByNameFirst(java.lang.String givenByNameFirst) {
        this.givenByNameFirst = givenByNameFirst;
    }


    /**
     * Gets the idSubmitter value for this VaccinationType.
     * 
     * @return idSubmitter
     */
    public java.lang.String getIdSubmitter() {
        return idSubmitter;
    }


    /**
     * Sets the idSubmitter value for this VaccinationType.
     * 
     * @param idSubmitter
     */
    public void setIdSubmitter(java.lang.String idSubmitter) {
        this.idSubmitter = idSubmitter;
    }


    /**
     * Gets the informationSourceCode value for this VaccinationType.
     * 
     * @return informationSourceCode
     */
    public java.lang.String getInformationSourceCode() {
        return informationSourceCode;
    }


    /**
     * Sets the informationSourceCode value for this VaccinationType.
     * 
     * @param informationSourceCode
     */
    public void setInformationSourceCode(java.lang.String informationSourceCode) {
        this.informationSourceCode = informationSourceCode;
    }


    /**
     * Gets the lotNumber value for this VaccinationType.
     * 
     * @return lotNumber
     */
    public java.lang.String getLotNumber() {
        return lotNumber;
    }


    /**
     * Sets the lotNumber value for this VaccinationType.
     * 
     * @param lotNumber
     */
    public void setLotNumber(java.lang.String lotNumber) {
        this.lotNumber = lotNumber;
    }


    /**
     * Gets the manufacturerCode value for this VaccinationType.
     * 
     * @return manufacturerCode
     */
    public java.lang.String getManufacturerCode() {
        return manufacturerCode;
    }


    /**
     * Sets the manufacturerCode value for this VaccinationType.
     * 
     * @param manufacturerCode
     */
    public void setManufacturerCode(java.lang.String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }


    /**
     * Gets the orderedByNumber value for this VaccinationType.
     * 
     * @return orderedByNumber
     */
    public java.lang.String getOrderedByNumber() {
        return orderedByNumber;
    }


    /**
     * Sets the orderedByNumber value for this VaccinationType.
     * 
     * @param orderedByNumber
     */
    public void setOrderedByNumber(java.lang.String orderedByNumber) {
        this.orderedByNumber = orderedByNumber;
    }


    /**
     * Gets the refusalCode value for this VaccinationType.
     * 
     * @return refusalCode
     */
    public java.lang.String getRefusalCode() {
        return refusalCode;
    }


    /**
     * Sets the refusalCode value for this VaccinationType.
     * 
     * @param refusalCode
     */
    public void setRefusalCode(java.lang.String refusalCode) {
        this.refusalCode = refusalCode;
    }


    /**
     * Gets the systemEntryDate value for this VaccinationType.
     * 
     * @return systemEntryDate
     */
    public java.lang.String getSystemEntryDate() {
        return systemEntryDate;
    }


    /**
     * Sets the systemEntryDate value for this VaccinationType.
     * 
     * @param systemEntryDate
     */
    public void setSystemEntryDate(java.lang.String systemEntryDate) {
        this.systemEntryDate = systemEntryDate;
    }


    /**
     * Gets the visPublicationDate value for this VaccinationType.
     * 
     * @return visPublicationDate
     */
    public java.lang.String getVisPublicationDate() {
        return visPublicationDate;
    }


    /**
     * Sets the visPublicationDate value for this VaccinationType.
     * 
     * @param visPublicationDate
     */
    public void setVisPublicationDate(java.lang.String visPublicationDate) {
        this.visPublicationDate = visPublicationDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VaccinationType)) return false;
        VaccinationType other = (VaccinationType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.vaccinationId == other.getVaccinationId() &&
            this.positionId == other.getPositionId() &&
            ((this.skipped==null && other.getSkipped()==null) || 
             (this.skipped!=null &&
              this.skipped.equals(other.getSkipped()))) &&
            ((this.adminCodeCpt==null && other.getAdminCodeCpt()==null) || 
             (this.adminCodeCpt!=null &&
              this.adminCodeCpt.equals(other.getAdminCodeCpt()))) &&
            ((this.adminCodeCvx==null && other.getAdminCodeCvx()==null) || 
             (this.adminCodeCvx!=null &&
              this.adminCodeCvx.equals(other.getAdminCodeCvx()))) &&
            ((this.adminDate==null && other.getAdminDate()==null) || 
             (this.adminDate!=null &&
              this.adminDate.equals(other.getAdminDate()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.amountUnitCode==null && other.getAmountUnitCode()==null) || 
             (this.amountUnitCode!=null &&
              this.amountUnitCode.equals(other.getAmountUnitCode()))) &&
            ((this.bodyRouteCode==null && other.getBodyRouteCode()==null) || 
             (this.bodyRouteCode!=null &&
              this.bodyRouteCode.equals(other.getBodyRouteCode()))) &&
            ((this.bodySiteCode==null && other.getBodySiteCode()==null) || 
             (this.bodySiteCode!=null &&
              this.bodySiteCode.equals(other.getBodySiteCode()))) &&
            ((this.completionStatusCode==null && other.getCompletionStatusCode()==null) || 
             (this.completionStatusCode!=null &&
              this.completionStatusCode.equals(other.getCompletionStatusCode()))) &&
            ((this.confidentialityCode==null && other.getConfidentialityCode()==null) || 
             (this.confidentialityCode!=null &&
              this.confidentialityCode.equals(other.getConfidentialityCode()))) &&
            ((this.enteredByNumber==null && other.getEnteredByNumber()==null) || 
             (this.enteredByNumber!=null &&
              this.enteredByNumber.equals(other.getEnteredByNumber()))) &&
            ((this.enteredByNameFirst==null && other.getEnteredByNameFirst()==null) || 
             (this.enteredByNameFirst!=null &&
              this.enteredByNameFirst.equals(other.getEnteredByNameFirst()))) &&
            ((this.enteredByNameLast==null && other.getEnteredByNameLast()==null) || 
             (this.enteredByNameLast!=null &&
              this.enteredByNameLast.equals(other.getEnteredByNameLast()))) &&
            ((this.expirationDate==null && other.getExpirationDate()==null) || 
             (this.expirationDate!=null &&
              this.expirationDate.equals(other.getExpirationDate()))) &&
            this.facilityId == other.getFacilityId() &&
            ((this.facilityName==null && other.getFacilityName()==null) || 
             (this.facilityName!=null &&
              this.facilityName.equals(other.getFacilityName()))) &&
            ((this.financialEligibilityCode==null && other.getFinancialEligibilityCode()==null) || 
             (this.financialEligibilityCode!=null &&
              this.financialEligibilityCode.equals(other.getFinancialEligibilityCode()))) &&
            ((this.givenByNumber==null && other.getGivenByNumber()==null) || 
             (this.givenByNumber!=null &&
              this.givenByNumber.equals(other.getGivenByNumber()))) &&
            ((this.givenByNameLast==null && other.getGivenByNameLast()==null) || 
             (this.givenByNameLast!=null &&
              this.givenByNameLast.equals(other.getGivenByNameLast()))) &&
            ((this.givenByNameFirst==null && other.getGivenByNameFirst()==null) || 
             (this.givenByNameFirst!=null &&
              this.givenByNameFirst.equals(other.getGivenByNameFirst()))) &&
            ((this.idSubmitter==null && other.getIdSubmitter()==null) || 
             (this.idSubmitter!=null &&
              this.idSubmitter.equals(other.getIdSubmitter()))) &&
            ((this.informationSourceCode==null && other.getInformationSourceCode()==null) || 
             (this.informationSourceCode!=null &&
              this.informationSourceCode.equals(other.getInformationSourceCode()))) &&
            ((this.lotNumber==null && other.getLotNumber()==null) || 
             (this.lotNumber!=null &&
              this.lotNumber.equals(other.getLotNumber()))) &&
            ((this.manufacturerCode==null && other.getManufacturerCode()==null) || 
             (this.manufacturerCode!=null &&
              this.manufacturerCode.equals(other.getManufacturerCode()))) &&
            ((this.orderedByNumber==null && other.getOrderedByNumber()==null) || 
             (this.orderedByNumber!=null &&
              this.orderedByNumber.equals(other.getOrderedByNumber()))) &&
            ((this.refusalCode==null && other.getRefusalCode()==null) || 
             (this.refusalCode!=null &&
              this.refusalCode.equals(other.getRefusalCode()))) &&
            ((this.systemEntryDate==null && other.getSystemEntryDate()==null) || 
             (this.systemEntryDate!=null &&
              this.systemEntryDate.equals(other.getSystemEntryDate()))) &&
            ((this.visPublicationDate==null && other.getVisPublicationDate()==null) || 
             (this.visPublicationDate!=null &&
              this.visPublicationDate.equals(other.getVisPublicationDate())));
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
        _hashCode += new Long(getVaccinationId()).hashCode();
        _hashCode += new Long(getPositionId()).hashCode();
        if (getSkipped() != null) {
            _hashCode += getSkipped().hashCode();
        }
        if (getAdminCodeCpt() != null) {
            _hashCode += getAdminCodeCpt().hashCode();
        }
        if (getAdminCodeCvx() != null) {
            _hashCode += getAdminCodeCvx().hashCode();
        }
        if (getAdminDate() != null) {
            _hashCode += getAdminDate().hashCode();
        }
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getAmountUnitCode() != null) {
            _hashCode += getAmountUnitCode().hashCode();
        }
        if (getBodyRouteCode() != null) {
            _hashCode += getBodyRouteCode().hashCode();
        }
        if (getBodySiteCode() != null) {
            _hashCode += getBodySiteCode().hashCode();
        }
        if (getCompletionStatusCode() != null) {
            _hashCode += getCompletionStatusCode().hashCode();
        }
        if (getConfidentialityCode() != null) {
            _hashCode += getConfidentialityCode().hashCode();
        }
        if (getEnteredByNumber() != null) {
            _hashCode += getEnteredByNumber().hashCode();
        }
        if (getEnteredByNameFirst() != null) {
            _hashCode += getEnteredByNameFirst().hashCode();
        }
        if (getEnteredByNameLast() != null) {
            _hashCode += getEnteredByNameLast().hashCode();
        }
        if (getExpirationDate() != null) {
            _hashCode += getExpirationDate().hashCode();
        }
        _hashCode += new Long(getFacilityId()).hashCode();
        if (getFacilityName() != null) {
            _hashCode += getFacilityName().hashCode();
        }
        if (getFinancialEligibilityCode() != null) {
            _hashCode += getFinancialEligibilityCode().hashCode();
        }
        if (getGivenByNumber() != null) {
            _hashCode += getGivenByNumber().hashCode();
        }
        if (getGivenByNameLast() != null) {
            _hashCode += getGivenByNameLast().hashCode();
        }
        if (getGivenByNameFirst() != null) {
            _hashCode += getGivenByNameFirst().hashCode();
        }
        if (getIdSubmitter() != null) {
            _hashCode += getIdSubmitter().hashCode();
        }
        if (getInformationSourceCode() != null) {
            _hashCode += getInformationSourceCode().hashCode();
        }
        if (getLotNumber() != null) {
            _hashCode += getLotNumber().hashCode();
        }
        if (getManufacturerCode() != null) {
            _hashCode += getManufacturerCode().hashCode();
        }
        if (getOrderedByNumber() != null) {
            _hashCode += getOrderedByNumber().hashCode();
        }
        if (getRefusalCode() != null) {
            _hashCode += getRefusalCode().hashCode();
        }
        if (getSystemEntryDate() != null) {
            _hashCode += getSystemEntryDate().hashCode();
        }
        if (getVisPublicationDate() != null) {
            _hashCode += getVisPublicationDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VaccinationType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "VaccinationType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vaccinationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "vaccinationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "positionId"));
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
        elemField.setFieldName("adminCodeCpt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "adminCodeCpt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adminCodeCvx");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "adminCodeCvx"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adminDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "adminDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amountUnitCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "amountUnitCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bodyRouteCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "bodyRouteCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bodySiteCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "bodySiteCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completionStatusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "completionStatusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confidentialityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "confidentialityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enteredByNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "enteredByNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enteredByNameFirst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "enteredByNameFirst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enteredByNameLast");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "enteredByNameLast"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expirationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "expirationDate"));
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
        elemField.setFieldName("financialEligibilityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "financialEligibilityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("givenByNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "givenByNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("givenByNameLast");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "givenByNameLast"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("givenByNameFirst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "givenByNameFirst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSubmitter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "idSubmitter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informationSourceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "informationSourceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lotNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "lotNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manufacturerCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "manufacturerCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderedByNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "orderedByNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refusalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "refusalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemEntryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "systemEntryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visPublicationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dqaws.openimmunizationsoftware.org/dqa/schema", "visPublicationDate"));
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
