package org.openimmunizationsoftware.dqa.validate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.db.model.CodeStatus;
import org.openimmunizationsoftware.dqa.db.model.CodeTable;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.VaccineCpt;
import org.openimmunizationsoftware.dqa.db.model.VaccineCvx;
import org.openimmunizationsoftware.dqa.db.model.VaccineMvx;
import org.openimmunizationsoftware.dqa.db.model.VaccineProduct;
import org.openimmunizationsoftware.dqa.db.model.received.NextOfKin;
import org.openimmunizationsoftware.dqa.db.model.received.Vaccination;
import org.openimmunizationsoftware.dqa.db.model.received.types.Address;
import org.openimmunizationsoftware.dqa.db.model.received.types.CodedEntity;
import org.openimmunizationsoftware.dqa.manager.CodesReceived;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;
import org.openimmunizationsoftware.dqa.manager.VaccineProductManager;

public class Validator extends ValidateMessage
{
  private Session session = null;

  public Validator(SubmitterProfile profile, Session session) {
    super(profile);
    this.session = session;
  }

  public void validateVaccinationUpdateMessage(MessageReceived message)
  {
    positionId = 1;
    this.message = message;
    this.patient = message.getPatient();
    this.issuesFound = message.getIssuesFound();
    validatePatient();
    positionId = 1;
    for (NextOfKin nextOfKin : message.getNextOfKins())
    {
      positionId++;
      validateNextOfKin(nextOfKin);
    }
    positionId = 1;
    for (Vaccination vaccination : message.getVaccinations())
    {
      positionId++;
      validateVaccination(vaccination);
    }
  }

  private void validateNextOfKin(NextOfKin nextOfKin)
  {
    // NextOfKinAddressIsDifferentFromPatientAddress
    // NextOfKinAddressIsMissing
    // NextOfKinAddressCityIsInvalid
    // NextOfKinAddressCityIsMissing
    // NextOfKinAddressCountryIsDeprecated
    // NextOfKinAddressCountryIsIgnored
    // NextOfKinAddressCountryIsInvalid
    // NextOfKinAddressCountryIsMissing
    // NextOfKinAddressCountryIsUnrecognized
    // NextOfKinAddressCountyIsDeprecated
    // NextOfKinAddressCountyIsIgnored
    // NextOfKinAddressCountyIsInvalid
    // NextOfKinAddressCountyIsMissing
    // NextOfKinAddressCountyIsUnrecognized
    // NextOfKinAddressStateIsDeprecated
    // NextOfKinAddressStateIsIgnored
    // NextOfKinAddressStateIsInvalid
    // NextOfKinAddressStateIsMissing
    // NextOfKinAddressStateIsUnrecognized
    // NextOfKinAddressStreetIsMissing
    // NextOfKinAddressStreet2IsMissing
    // NextOfKinNameIsMissing
    // NextOfKinNameIsSameAsUnderagePatient
    // NextOfKinNameFirstIsMissing
    // NextOfKinNameLastIsMissing
    // NextOfKinPhoneNumberIsIncomplete
    // NextOfKinPhoneNumberIsInvalid
    // NextOfKinPhoneNumberIsMissing
    // NextOfKinRelationshipIsDeprecated
    // NextOfKinRelationshipIsIgnored
    // NextOfKinRelationshipIsInvalid
    // NextOfKinRelationshipIsMissing
    // NextOfKinRelationshipIsNotResponsibleParty
    // NextOfKinRelationshipIsUnrecognized
    // NextOfKinSsnIsMissing

  }

  private void validateVaccination(Vaccination vaccination)
  {
    String actionCode = vaccination.getActionCode();
    actionCode = handleCodeReceived(actionCode, CodesReceived.getCodeTable(CodesReceived.ACTION_CODE),
        PotentialIssues.Field.VACCINATION_ACTION_CODE);
    vaccination.setActionCode(actionCode);
    if (actionCode != null)
    {
      if (actionCode.equals(Vaccination.ACTION_CODE_ADD))
      {
        registerIssue(pi.VaccinationActionCodeIsValuedAsAdd);
        registerIssue(pi.VaccinationActionCodeIsValuedAsAddOrUpdate);
      } else if (actionCode.equals(Vaccination.ACTION_CODE_ADD))
      {
        registerIssue(pi.VaccinationActionCodeIsValuedAsUpdate);
      } else if (actionCode.equals(Vaccination.ACTION_CODE_ADD))
      {
        registerIssue(pi.VaccinationActionCodeIsValuedAsDelete);
      }
    }

    boolean administered = false;
    if (notEmpty(vaccination.getInformationSourceCode(), pi.VaccinationInformationSourceIsMissing))
    {
      handleCodeReceived(vaccination.getInformationSourceCode(), CodesReceived.getCodeTable(CodesReceived.INFO_SOURCE),
          PotentialIssues.Field.VACCINATION_INFORMATION_SOURCE);
      administered = Vaccination.INFO_SOURCE_ADMIN.equals(vaccination.getInformationSourceCode());
      if (administered)
      {
        registerIssue(pi.VaccinationInformationSourceIsValuedAsAdministered);
      } else if (Vaccination.INFO_SOURCE_HIST.equals(vaccination.getInformationSourceCode()))
      {
        registerIssue(pi.VaccinationInformationSourceIsValuedAsHistorical);
      }
    }
    // TODO VaccinationInformationSourceIsAdministeredButAppearsToHistorical
    // TODO VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered

    String cptCode = vaccination.getAdminCodeCpt();
    cptCode = handleCodeReceived(cptCode, CodesReceived.getCodeTable(CodesReceived.CPT),
        PotentialIssues.Field.VACCINATION_CPT_CODE);
    vaccination.setAdminCodeCpt(cptCode);

    String cvxCode = vaccination.getAdminCodeCvx();
    cvxCode = handleCodeReceived(cvxCode, CodesReceived.getCodeTable(CodesReceived.CVX),
        PotentialIssues.Field.VACCINATION_CVX_CODE);
    vaccination.setAdminCodeCvx(cvxCode);

    // TODO Need to figure out status for CPT can CVX
    // CVX \ CPT | Deprecated | Ignored | Invalid | Missing | Not Specific |
    // Unrecognized
    // +--------------+------------+---------+---------+---------+--------------+-------------
    // | Deprecated | CVX |
    // | Ignored | CVX |
    // | Invalid | CPT |
    // | Missing | CPT |
    // | Not Specific | CVX |
    // | Unrecognized | CPT |
    // |
    // |
    // VaccinationAdminCodeIsDeprecated
    // VaccinationAdminCodeIsIgnored
    // VaccinationAdminCodeIsInvalid
    // VaccinationAdminCodeIsMissing
    // VaccinationAdminCodeIsUnrecognized
    VaccineCvx vaccineCvx = null;
    VaccineCpt vaccineCpt = null;
    if (cptCode != null && !cptCode.equals(""))
    {
      Query query = session
          .createQuery("from VaccineCpt where cptCode = ? and validStartDate <= ? and validEndDate > ?");
      query.setString(0, cptCode);
      query.setDate(1, vaccination.getAdminDate());
      query.setDate(2, vaccination.getAdminDate());
      List<VaccineCpt> vaccineCpts = query.list();
      if (vaccineCpts.size() > 0)
      {
        vaccineCpt = vaccineCpts.get(0);
      }
    }
    if (cvxCode != null && !cvxCode.equals(""))
    {
      vaccineCvx = (VaccineCvx) session.get(VaccineCvx.class, cvxCode);
    }
    if (vaccineCvx == null && vaccineCpt != null)
    {
      vaccineCvx = vaccineCpt.getCvx();
    }

    if (notEmpty(vaccination.getAdminDate(), pi.VaccinationAdminDateIsMissing))
    {
      Calendar cal = Calendar.getInstance();
      int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
      int lastDayofMonth = cal.getMaximum(Calendar.DAY_OF_MONTH);
      if (dayOfMonth == 1)
      {
        registerIssue(pi.VaccinationAdminDateIsOnFirstDayOfMonth);
      } else if (dayOfMonth == 15)
      {
        registerIssue(pi.VaccinationAdminDateIsOn15ThDayOfMonth);
      } else if (dayOfMonth == lastDayofMonth)
      {
        registerIssue(pi.VaccinationAdminDateIsOnLastDayOfMonth);
      }
    }
    if (vaccineCvx != null)
    {
      if (vaccineCvx.getConceptType().equals(VaccineCvx.CONCEPT_TYPE_UNSPECIFIED))
      {
        if (administered)
        {
          registerIssue(pi.VaccinationAdminCodeIsNotSpecific);
        }
      } else if (vaccineCvx.getCvxCode().equals(VaccineCvx.NO_VACCINE_ADMINISTERED))
      {
        registerIssue(pi.VaccinationAdminCodeIsValuedAsNotAdministered);
      } else if (vaccineCvx.getCvxCode().equals(VaccineCvx.CONCEPT_TYPE_NON_VACCINE))
      {
        registerIssue(pi.VaccinationAdminCodeIsNotVaccine);
      }
      if (vaccination.getAdminDate() != null)
      {
        if (vaccineCvx.getValidStartDate().after(vaccination.getAdminDate())
            || vaccination.getAdminDate().after(vaccineCvx.getValidEndDate()))
        {
          registerIssue(pi.VaccinationProductIsInvalid);
        } else if (vaccineCvx.getUseStartDate().after(vaccination.getAdminDate())
            || vaccination.getAdminDate().after(vaccineCvx.getUseEndDate()))
        {
          registerIssue(pi.VaccinationProductIsDeprecated);
        }
        int monthsBetween = monthsBetween(patient.getBirthDate(), vaccination.getAdminDate());
        if (monthsBetween < vaccineCvx.getUseMonthStart() || monthsBetween > vaccineCvx.getUseMonthEnd())
        {
          registerIssue(pi.VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge);
        }
      }
    }
    if (administered)
    {
      handleCodeReceived(vaccination.getManufacturerCode(), CodesReceived.getCodeTable(CodesReceived.MVX),
          PotentialIssues.Field.VACCINATION_MANUFACTURER_CODE);
    } else
    {

    }
    VaccineMvx vaccineMvx = null;
    if (vaccination.getManufacturerCode() != null && !vaccination.getManufacturerCode().equals(""))
    {
      vaccineMvx = (VaccineMvx) session.get(VaccineMvx.class, vaccination.getManufacturerCode());
    }
    if (administered)
    {
      if (vaccineMvx != null && !vaccineMvx.getMvxCode().equals("") && vaccineCvx != null
          && !vaccineCvx.getCvxCode().equals(""))
      {
        VaccineProduct vaccineProduct = VaccineProductManager.getVaccineProductManager().getVaccineProduct(vaccineCvx,
            vaccineMvx);
        if (vaccineProduct == null)
        {
          registerIssue(pi.VaccinationProductIsUnrecognized);
        } else if (vaccineProduct.getValidStartDate().after(vaccination.getAdminDate())
            || vaccination.getAdminDate().after(vaccineProduct.getValidEndDate()))
        {
          registerIssue(pi.VaccinationProductIsInvalid);
        } else if (vaccineProduct.getUseStartDate().after(vaccination.getAdminDate())
            || vaccination.getAdminDate().after(vaccineProduct.getUseEndDate()))
        {
          registerIssue(pi.VaccinationProductIsDeprecated);
        }
      } else
      {
        registerIssue(pi.VaccinationProductIsMissing);
      }
    }
    // TODO Pull table from CDC that lists valid manufacturers for CVX and CPT

    // TODO VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes

    if (vaccination.getAdminDate() != null)
    {
      if (administered)
      {
        if (vaccination.getExpirationDate() != null)
        {
          if (vaccination.getAdminDate().after(vaccination.getExpirationDate()))
          {
            registerIssue(pi.VaccinationAdminDateIsAfterLotExpirationDate);
          }
        }
      }
      if (vaccination.getAdminDate().after(message.getReceivedDate()))
      {
        registerIssue(pi.VaccinationAdminDateIsAfterMessageSubmitted);
      }
      if (patient.getDeathDate() != null)
      {
        if (vaccination.getAdminDate().after(patient.getDeathDate()))
        {
          registerIssue(pi.VaccinationAdminDateIsAfterSystemEntryDate);
        }
      }
      if (vaccination.getSystemEntryDate() != null)
      {
        registerIssue(pi.VaccinationAdminDateIsBeforeBirth);
      }
      if (notEmpty(vaccination.getAdminDateEnd(), pi.VaccinationAdminDateEndIsMissing))
      {
        if (!vaccination.getAdminDateEnd().equals(vaccination.getAdminDate()))
        {
          registerIssue(pi.VaccinationAdminDateEndIsDifferentFromStartDate);
        }
      }

    }
    // TODO
    // registerIssue(pi.VaccinationAdminDateIsBeforeExpectedReportingWindow);

    if (notEmpty(vaccination.getAmount(), pi.VaccinationAdministeredAmountIsMissing))
    {
      if (vaccination.getAmount().equals("999"))
      {
        if (administered)
        {
          registerIssue(pi.VaccinationAdministeredAmountIsValuedAsUnknown);
        }
      } else
      {
        try
        {
          int amount = Integer.parseInt(vaccination.getAmount());
          if (amount == 0)
          {
            if (administered)
            {
              registerIssue(pi.VaccinationAdministeredAmountIsValuedAsZero);
            }
          }
        } catch (NumberFormatException nfe)
        {
          if (administered)
          {
            registerIssue(pi.VaccinationAdministeredAmountIsInvalid);
          }
          vaccination.setAmount("");
        }
      }
    }

    if (vaccination.getAmount() != null && !vaccination.getAmount().equals(""))
    {
      if (administered)
      {
        registerIssue(pi.VaccinationAdministeredUnitIsMissing);
      }
    }

    handleCodeReceived(vaccination.getBodyRoute(), CodesReceived.getCodeTable(CodesReceived.BODY_ROUTE),
        PotentialIssues.Field.VACCINATION_BODY_ROUTE, administered);
    handleCodeReceived(vaccination.getBodySite(), CodesReceived.getCodeTable(CodesReceived.BODY_SITE),
        PotentialIssues.Field.VACCINATION_BODY_SITE, administered);

    // TODO VaccinationBodyRouteIsInvalidForVaccineIndicated
    // TODO VaccinationBodySiteIsInvalidForVaccineIndicated
    String completionStatusCode = vaccination.getCompletionStatusCode();
    completionStatusCode = handleCodeReceived(completionStatusCode, CodesReceived.getCodeTable(CodesReceived.COMPLETE),
        PotentialIssues.Field.VACCINATION_COMPLETION_STATUS);
    vaccination.setCompletionStatusCode(completionStatusCode);
    if (completionStatusCode != null && !completionStatusCode.equals(""))
    {
      if (completionStatusCode.equals("CP"))
      {
        registerIssue(pi.VaccinationCompletionStatusIsValuedAsCompleted);
      } else if (completionStatusCode.equals("RE"))
      {
        registerIssue(pi.VaccinationCompletionStatusIsValuedAsRefused);
      } else if (completionStatusCode.equals("NA"))
      {
        registerIssue(pi.VaccinationCompletionStatusIsValuedAsNotAdministered);
      } else if (completionStatusCode.equals("PA"))
      {
        registerIssue(pi.VaccinationCompletionStatusIsValuedAsPartiallyAdministered);
      }
    }
    handleCodeReceived(vaccination.getConfidentiality(), CodesReceived.getCodeTable(CodesReceived.CONFIDENDIALITY),
        PotentialIssues.Field.VACCINATION_CONFIDENTIALITY_CODE);
    
    if (vaccineCpt != null && vaccineCvx != null)
    {
      // TODO VaccinationCvxCodeAndCptCodeAreInconsistent      
    }
    
    String facilityId = vaccination.getFacilityId();
    facilityId = handleCodeReceived(facilityId, CodesReceived.getCodeTable(CodesReceived.FACILITY),
        PotentialIssues.Field.VACCINATION_FACILITY_ID);
    vaccination.setFacilityId(facilityId);
    notEmpty(vaccination.getFacilityName(), pi.VaccinationFacilityNameIsMissing);
    
    // VaccinationGivenByIsDeprecated
    // VaccinationGivenByIsIgnored
    // VaccinationGivenByIsInvalid
    // VaccinationGivenByIsMissing
    // VaccinationGivenByIsUnrecognized
    // VaccinationIdIsMissing
    // VaccinationIdOfReceiverIsMissing
    // VaccinationIdOfReceiverIsUnrecognized
    // VaccinationIdOfSenderIsMissing
    // VaccinationIdOfSenderIsUnrecognized

    // VaccinationLotExpirationDateIsInvalid
    // VaccinationLotExpirationDateIsMissing
    // VaccinationLotNumberIsInvalid
    // VaccinationLotNumberIsMissing
    // VaccinationOrderedByIsDeprecated
    // VaccinationOrderedByIsIgnored
    // VaccinationOrderedByIsInvalid
    // VaccinationOrderedByIsMissing
    // VaccinationOrderedByIsUnrecognized
    // VaccinationRecordedByIsDeprecated
    // VaccinationRecordedByIsIgnored
    // VaccinationRecordedByIsInvalid
    // VaccinationRecordedByIsMissing
    // VaccinationRecordedByIsUnrecognized
    // VaccinationRefusalReasonIsDeprecated
    // VaccinationRefusalReasonIsIgnored
    // VaccinationRefusalReasonIsInvalid
    // VaccinationRefusalReasonIsMissing
    // VaccinationRefusalReasonIsUnrecognized
    // VaccinationSystemEntryTimeIsInFuture
    // VaccinationSystemEntryTimeIsInvalid
    // VaccinationSystemEntryTimeIsMissing

  }

  private void validatePatient()
  {
    Address address = patient.getAddress();
    String city = address.getCity();
    if (notEmpty(city, PotentialIssues.Field.PATIENT_ADDRESS_CITY))
    {
      if (city.equalsIgnoreCase("ANYTOWN") || city.length() <= 1)
      {
        registerIssue(pi.getIssue(PotentialIssues.Field.PATIENT_ADDRESS_CITY, PotentialIssue.ISSUE_TYPE_IS_INVALID));
      }
    }

    String country = address.getCountry();
    country = handleCodeReceived(country, CodesReceived.getCodeTable(CodesReceived.COUNTRY),
        PotentialIssues.Field.PATIENT_ADDRESS_COUNTRY);
    address.setCountyParish(country);

    String countyParish = address.getCountyParish();
    countyParish = handleCodeReceived(countyParish, CodesReceived.getCodeTable(CodesReceived.COUNTY_PARISH),
        PotentialIssues.Field.PATIENT_ADDRESS_COUNTY);
    address.setCountyParish(countyParish);

    String state = address.getState();
    state = handleCodeReceived(countyParish, CodesReceived.getCodeTable(CodesReceived.STATE),
        PotentialIssues.Field.PATIENT_ADDRESS_STATE);
    address.setState(state);

    String street = address.getStreet();
    notEmpty(street, PotentialIssues.Field.PATIENT_ADDRESS_STREET);

    String street2 = address.getStreet2();
    notEmpty(street2, PotentialIssues.Field.PATIENT_ADDRESS_STREET2);

    String zip = address.getZip();
    if (notEmpty(zip, PotentialIssues.Field.PATIENT_ADDRESS_ZIP))
    {
      // TODO Zip valid?
      if (false)
      {
        registerIssue(pi.getIssue(PotentialIssues.Field.PATIENT_ADDRESS_ZIP, PotentialIssue.ISSUE_TYPE_IS_INVALID));
      }
    }

    if (street.equals("") || zip.equals("") || city.equals("") || state.equals(""))
    {
      registerIssue(pi.PatientAddressIsMissing);
    }

    String aliasFirst = patient.getAliasFirst();
    String aliasLast = patient.getAliasLast();
    notEmpty(aliasFirst + aliasLast, PotentialIssues.Field.PATIENT_ALIAS);

    // TODO
    // public PotentialIssue PatientDateOfBirthIsAfterSubmission = null;

    if (patient.getBirthDate() == null)
    {
      registerIssue(pi.PatientDateOfBirthIsMissing);
    } else
    {
      Date now = new Date();
      if (now.before(patient.getBirthDate()))
      {
        registerIssue(pi.PatientDateOfBirthIsInFuture);
      }
    }
    // public PotentialIssue PatientBirthIndicatorIsInvalid = null;
    // public PotentialIssue PatientBirthIndicatorIsMissing = null;
    // public PotentialIssue PatientBirthOrderIsInvalid = null;
    // public PotentialIssue PatientBirthOrderIsMissing = null;
    // public PotentialIssue PatientBirthOrderIsMissingAndMultipleBirthIndicated
    // = null;
    // public PotentialIssue PatientBirthPlaceIsMissing = null;
    // public PotentialIssue PatientDeathDateIsBeforeBirth = null;
    // public PotentialIssue PatientDeathDateIsInFuture = null;
    // public PotentialIssue PatientDeathDateIsInvalid = null;
    // public PotentialIssue PatientDeathDateIsMissing = null;
    // PatientEthnicityIsDeprecated
    // PatientEthnicityIsIgnored
    // PatientEthnicityIsInvalid
    // PatientEthnicityIsMissing
    // PatientEthnicityIsUnrecognized
    // PatientFirstNameIsInvalid
    // PatientFirstNameIsMissing
    // PatientFirstNameMayIncludeMiddleInitial
    // PatientGenderIsDeprecated
    // PatientGenderIsIgnored
    // PatientGenderIsInvalid
    // PatientGenderIsMissing
    // PatientGenderIsUnrecognized
    // PatientImmunizationRegistryStatusIsDeprecated
    // PatientImmunizationRegistryStatusIsIgnored
    // PatientImmunizationRegistryStatusIsInvalid
    // PatientImmunizationRegistryStatusIsMissing
    // PatientImmunizationRegistryStatusIsUnrecognized
    // PatientLastNameIsInvalid
    // PatientLastNameIsMissing
    // PatientMedicaidNumberIsInvalid
    // PatientMedicaidNumberIsMissing
    // PatientMiddleNameIsMissing
    // PatientMiddleNameMayBeInitial
    // PatientMotherSMaidenNameIsMissing
    // PatientNameMayBeTemporaryNewbornName
    // PatientNameMayBeTestName
    // PatientPhoneIsIncomplete
    // PatientPhoneIsInvalid
    // PatientPhoneIsMissing
    // PatientPrimaryFacilityIdIsDeprecated
    // PatientPrimaryFacilityIdIsIgnored
    // PatientPrimaryFacilityIdIsInvalid
    // PatientPrimaryFacilityIdIsMissing
    // PatientPrimaryFacilityIdIsUnrecognized
    // PatientPrimaryFacilityNameIsMissing
    // PatientPrimaryLanaguageIsDeprecated
    // PatientPrimaryLanaguageIsIgnored
    // PatientPrimaryLanaguageIsInvalid
    // PatientPrimaryLanaguageIsMissing
    // PatientPrimaryLanaguageIsUnrecognized
    // PatientPrimaryPhysicianIdIsDeprecated
    // PatientPrimaryPhysicianIdIsIgnored
    // PatientPrimaryPhysicianIdIsInvalid
    // PatientPrimaryPhysicianIdIsMissing
    // PatientPrimaryPhysicianIdIsUnrecognized
    // PatientPrimaryPhysicianNameIsMissing
    // PatientProtectionIndicatorIsDeprecated
    // PatientProtectionIndicatorIsIgnored
    // PatientProtectionIndicatorIsInvalid
    // PatientProtectionIndicatorIsMissing
    // PatientProtectionIndicatorIsUnrecognized
    // PatientProtectionIndicatorIsValuedAsNo
    // PatientProtectionIndicatorIsValuedAsYes
    // PatientPublicityCodeIsDeprecated
    // PatientPublicityCodeIsIgnored
    // PatientPublicityCodeIsInvalid
    // PatientPublicityCodeIsMissing
    // PatientPublicityCodeIsUnrecognized
    // PatientRaceIsDeprecated
    // PatientRaceIsIgnored
    // PatientRaceIsInvalid
    // PatientRaceIsMissing
    // PatientRaceIsUnrecognized
    // PatientRegistryIdIsMissing
    // PatientRegistryIdIsUnrecognized
    // PatientSsnIsInvalid
    // PatientSsnIsMissing
    // PatientSubmitterIdIsMissing
    // PatientVfcEffectiveDateIsBeforeBirth
    // PatientVfcEffectiveDateIsInFuture
    // PatientVfcEffectiveDateIsInvalid
    // PatientVfcEffectiveDateIsMissing
    // PatientVfcStatusIsDeprecated
    // PatientVfcStatusIsIgnored
    // PatientVfcStatusIsInvalid
    // PatientVfcStatusIsMissing
    // PatientVfcStatusIsUnrecognized
    // PatientWicIdIsInvalid
    // PatientWicIdIsMissing
    // VaccinationDeathIndicatorIsInconsistent
    // VaccinationDeathIndicatorIsMissing

  }

  private boolean notEmpty(CodedEntity codedEntity, PotentialIssue potentialIssue)
  {
    return notEmpty(codedEntity.getCode(), potentialIssue);
  }

  private boolean notEmpty(Date date, PotentialIssue potentialIssue)
  {
    if (date == null)
    {
      registerIssue(potentialIssue);
      return false;
    }
    return true;
  }

  private boolean notEmpty(String fieldValue, PotentialIssues.Field field)
  {
    return notEmpty(fieldValue, pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_MISSING));
  }

  private boolean notEmpty(String fieldValue, PotentialIssue potentialIssue)
  {
    boolean empty = fieldValue == null || fieldValue.equals("");
    if (empty)
    {
      registerIssue(potentialIssue);
    }
    return !empty;
  }
  protected CodeReceived handleCodeReceived(CodedEntity codedEntity, CodeTable codeTable, PotentialIssues.Field field)
  {
    
    return handleCodeReceived(codedEntity, codeTable, field, true);
  }
  
  protected CodeReceived handleCodeReceived(CodedEntity codedEntity, CodeTable codeTable, PotentialIssues.Field field,
      boolean notSilent)
  {
    CodeReceived cr = null;
    if (notEmpty(codedEntity.getCode(), field))
    {
      cr = getCodeReceived(codedEntity.getCode(), codeTable);
      cr.incReceivedCount();
      session.save(cr);
      if (cr.getCodeStatus().isValid())
      {
        // registerIssue(pi.getIssue(field,
        // PotentialIssue.ISSUE_TYPE_IS_INVALID), cr);
      } else if (cr.getCodeStatus().isInvalid())
      {
        if (notSilent)
        {
          registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_INVALID), cr);
        }
      } else if (cr.getCodeStatus().isUnrecognized())
      {
        if (notSilent)
        {
          registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_UNRECOGNIZED), cr);
        }
      } else if (cr.getCodeStatus().isDeprecated())
      {
        if (notSilent)
        {
          registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_DEPRECATE), cr);
        }
      } else if (cr.getCodeStatus().isIgnored())
      {
        if (notSilent)
        {
          registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_IGNORED), cr);
        }
      }
      codedEntity.setCode(cr.getCodeValue());
    }
    return cr;
  }

  protected String handleCodeReceived(String receivedValue, CodeTable codeTable, PotentialIssues.Field field)
  {
    String value = "";
    if (notEmpty(receivedValue, field))
    {
      CodeReceived cr = getCodeReceived(receivedValue, codeTable);
      cr.incReceivedCount();
      session.save(cr);
      if (cr.getCodeStatus().isValid())
      {
        // registerIssue(pi.getIssue(field,
        // PotentialIssue.ISSUE_TYPE_IS_INVALID), cr);
      } else if (cr.getCodeStatus().isInvalid())
      {
        registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_INVALID), cr);
      } else if (cr.getCodeStatus().isUnrecognized())
      {
        registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_UNRECOGNIZED), cr);
      } else if (cr.getCodeStatus().isDeprecated())
      {
        registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_DEPRECATE), cr);
      } else if (cr.getCodeStatus().isIgnored())
      {
        registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_IGNORED), cr);
      }
      value = cr.getCodeValue();
    }
    return value;
  }

  protected CodeReceived getCodeReceived(String receivedValue, CodeTable codeTable)
  {
    CodesReceived crs = profile.getCodesReceived(session);
    CodeReceived cr = crs.getCodeReceived(receivedValue, codeTable);
    if (cr == null)
    {
      cr = new CodeReceived();
      cr.setProfile(profile);
      cr.setTable(codeTable);
      cr.setReceivedValue(receivedValue);
      cr.setCodeValue(codeTable.getDefaultCodeValue());
      cr.setCodeStatus(CodeStatus.UNRECOGNIZED);
    } else if (!cr.getProfile().equals(profile))
    {
      cr = new CodeReceived(cr, profile);
      // first time code was received
    }
    return cr;
  }

  protected static int monthsBetween(Date startDate, Date endDate)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(endDate);
    int endMonth = cal.get(Calendar.MONTH);
    int endYear = cal.get(Calendar.YEAR);
    cal.setTime(startDate);
    int startMonth = cal.get(Calendar.MONTH);
    int startYear = cal.get(Calendar.YEAR);
    return ((endYear - startYear) * cal.getMaximum(Calendar.MONTH)) + (endMonth - startMonth);
  }
}
