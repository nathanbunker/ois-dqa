package org.openimmunizationsoftware.dqa.validate;

import java.text.SimpleDateFormat;
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
import org.openimmunizationsoftware.dqa.db.model.received.types.Id;
import org.openimmunizationsoftware.dqa.db.model.received.types.Name;
import org.openimmunizationsoftware.dqa.db.model.received.types.PhoneNumber;
import org.openimmunizationsoftware.dqa.manager.CodesReceived;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;
import org.openimmunizationsoftware.dqa.manager.VaccineProductManager;

public class Validator extends ValidateMessage
{
  private static final long AVERAGE_AGE_18_IN_MS = (long) (1000.0 * 60.0 * 60.0 * 24.0 * 365.25);

  private static String[] INVALID_NAMES = { "X", "U", "UN", "UK", "UNK", "UKN", "UNKN", "NONE" };
  private static String[] VALID_SUFFIX = { "SR", "JR", "II", "III", "IV" };

  private Session session = null;
  private Date now = new Date();
  private PotentialIssues.Field piAddress;
  private PotentialIssues.Field piAddressCity;
  private PotentialIssues.Field piAddressStreet;
  private PotentialIssues.Field piAddressCountry;
  private PotentialIssues.Field piAddressCounty;
  private PotentialIssues.Field piAddressState;
  private PotentialIssues.Field piAddressStreet2;
  private PotentialIssues.Field piAddressZip;

  public Validator(SubmitterProfile profile, Session session) {
    super(profile);
    this.session = session;
  }

  public void validateVaccinationUpdateMessage(MessageReceived message)
  {
    this.message = message;
    this.patient = message.getPatient();
    positionId = 1;
    skippableItem = patient;
    this.issuesFound = message.getIssuesFound();
    validatePatient();
    for (NextOfKin nextOfKin : message.getNextOfKins())
    {
      positionId = nextOfKin.getPositionId();
      skippableItem = nextOfKin;
      validateNextOfKin(nextOfKin);
    }
    if (patient.getResponsibleParty() == null)
    {
      registerIssue(pi.PatientGuardianPartyIsMissing);
    }
    for (Vaccination vaccination : message.getVaccinations())
    {
      positionId = vaccination.getPositionId();
      skippableItem = vaccination;
      validateVaccination(vaccination);
    }
  }

  private void validateNextOfKin(NextOfKin nextOfKin)
  {
    piAddress = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS;
    piAddressCity = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS_CITY;
    piAddressStreet = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS_STREET;
    piAddressCountry = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS_COUNTRY;
    piAddressCounty = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS_COUNTY;
    piAddressState = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS_STATE;
    piAddressStreet2 = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS_STREET2;
    piAddressZip = PotentialIssues.Field.NEXT_OF_KIN_ADDRESS_ZIP;
    if (validateAddress(nextOfKin.getAddress()))
    {
      Address p = patient.getAddress();
      Address n = nextOfKin.getAddress();
      if (!p.getCity().equals(n.getCity()) || !p.getState().equals(n.getState())
          || !p.getStreet().equals(n.getStreet()) || !p.getStreet2().equals(p.getStreet2()))
      {
        registerIssue(pi.NextOfKinAddressIsDifferentFromPatientAddress);
      }
    }
    boolean patientUnderAge = false;
    if (patient.getBirthDate() != null)
    {
      long age = now.getTime() - patient.getBirthDate().getTime();
      patientUnderAge = age < AVERAGE_AGE_18_IN_MS;
    }
    boolean isResponsibleParty = false;
    handleCodeReceived(nextOfKin.getRelationship(), PotentialIssues.Field.NEXT_OF_KIN_RELATIONSHIP);
    if (patientUnderAge && !nextOfKin.getRelationship().isEmpty())
    {

      String relationshipCode = nextOfKin.getRelationshipCode();
      String[] responsibleParties = { NextOfKin.RELATIONSHIP_CARE_GIVER, NextOfKin.RELATIONSHIP_FATHER,
          NextOfKin.RELATIONSHIP_GARNDPARENT, NextOfKin.RELATIONSHIP_MOTHER, NextOfKin.RELATIONSHIP_PARENT };
      for (String compare : responsibleParties)
      {
        if (compare.equals(relationshipCode))
        {
          isResponsibleParty = true;
          break;
        }
      }
      if (!isResponsibleParty)
      {
        registerIssue(pi.NextOfKinRelationshipIsNotResponsibleParty);
      }
    }

    boolean notEmptyFirst = notEmpty(nextOfKin.getNameLast(), pi.NextOfKinNameLastIsMissing);
    boolean notEmptyLast = notEmpty(nextOfKin.getNameLast(), pi.NextOfKinNameFirstIsMissing);
    if (notEmptyFirst && notEmptyLast && patientUnderAge && isResponsibleParty)
    {
      if (areEqual(nextOfKin.getNameLast(), patient.getNameLast())
          && areEqual(nextOfKin.getNameFirst(), patient.getNameFirst())
          && areEqual(nextOfKin.getNameMiddle(), patient.getNameMiddle())
          && areEqual(nextOfKin.getNameSuffix(), patient.getNameMiddle())
          && areEqual(nextOfKin.getNameSuffix(), patient.getNameSuffix()))
      {
        registerIssue(pi.PatientGuardianNameIsSameAsUnderagePatient);
      }
    }
    validatePhone(nextOfKin.getPhone(), PotentialIssues.Field.NEXT_OF_KIN_PHONE_NUMBER);
    if ((isResponsibleParty && patientUnderAge && (notEmptyFirst || notEmptyLast)) || !patientUnderAge)
    {
      if (patient.getResponsibleParty() == null)
      {
        patient.setResponsibleParty(nextOfKin);
        notEmpty(patient.getAddressCity(), piAddressCity = PotentialIssues.Field.PATIENT_GUARDIAN_ADDRESS_CITY);
        notEmpty(patient.getAddressStateCode(), piAddressCity = PotentialIssues.Field.PATIENT_GUARDIAN_ADDRESS_STATE);
        notEmpty(patient.getAddressCity(), piAddressCity = PotentialIssues.Field.PATIENT_GUARDIAN_ADDRESS_CITY);
        notEmpty(patient.getAddressZip(), piAddressCity = PotentialIssues.Field.PATIENT_GUARDIAN_ADDRESS_ZIP);

        boolean firstEmpty = notEmpty(nextOfKin.getNameFirst(), pi.PatientGuardianFirstNameIsMissing);
        boolean lastEmpty = notEmpty(nextOfKin.getNameLast(), pi.PatientGuardianLastNameIsMissing);
        if (firstEmpty && lastEmpty)
        {
          registerIssue(pi.PatientGuardianNameIsMissing);
        }
        String pFirst = patient.getNameFirst();
        String pLast = patient.getNameLast();
        String nFirst = nextOfKin.getNameFirst();
        String nLast = nextOfKin.getNameLast();
        if (pFirst != null && !pFirst.equals("") && pLast != null && !pLast.equals(""))
        {
          if (pFirst.equals(nFirst) && pLast.equals(nLast))
          {
            registerIssue(pi.PatientGuardianNameIsSameAsUnderagePatient);
          }
        }
        notEmpty(nextOfKin.getPhoneNumber(), pi.PatientGuardianPhoneIsMissing);
        notEmpty(nextOfKin.getRelationshipCode(), pi.PatientGuardianRelationshipIsMissing);
      }
    }
    // TODO NextOfKinSsnIsMissing
  }

  private void validateVaccination(Vaccination vaccination)
  {
    handleCodeReceived(vaccination.getAction(), PotentialIssues.Field.VACCINATION_ACTION_CODE);
    if (vaccination.isActionAdd())
    {
      registerIssue(pi.VaccinationActionCodeIsValuedAsAdd);
      registerIssue(pi.VaccinationActionCodeIsValuedAsAddOrUpdate);
    } else if (vaccination.isActionUpdate())
    {
      registerIssue(pi.VaccinationActionCodeIsValuedAsUpdate);
      registerIssue(pi.VaccinationActionCodeIsValuedAsAddOrUpdate);
    } else if (vaccination.isActionDelete())
    {
      registerIssue(pi.VaccinationActionCodeIsValuedAsDelete);
    }

    boolean administered = false;
    if (notEmpty(vaccination.getInformationSourceCode(), pi.VaccinationInformationSourceIsMissing))
    {
      handleCodeReceived(vaccination.getInformationSource(), PotentialIssues.Field.VACCINATION_INFORMATION_SOURCE);
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

    handleCodeReceived(vaccination.getAdminCpt(), PotentialIssues.Field.VACCINATION_CPT_CODE);
    String cptCode = vaccination.getAdminCptCode();
    vaccination.setAdminCptCode(cptCode);

    handleCodeReceived(vaccination.getAdminCvx(), PotentialIssues.Field.VACCINATION_CVX_CODE);
    String cvxCode = vaccination.getAdminCvxCode();

    CodeReceived vaccineCr = vaccination.getAdminCvx().getCodeReceived();

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
      try
      {
        int cvxId = Integer.parseInt(cvxCode);
        vaccineCvx = (VaccineCvx) session.get(VaccineCvx.class, cvxId);
      } catch (NumberFormatException nfe)
      {
        // ignore
      }
    }
    if (vaccineCvx == null && vaccineCpt != null)
    {
      vaccineCvx = vaccineCpt.getCvx();
      vaccineCr = vaccination.getAdminCpt().getCodeReceived();
    }
    vaccination.setVaccineCvx(vaccineCvx);

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
          registerIssue(pi.VaccinationAdminCodeIsNotSpecific, vaccineCr);
        }
      } else if (vaccineCvx.getCvxCode().equals(VaccineCvx.NO_VACCINE_ADMINISTERED))
      {
        registerIssue(pi.VaccinationAdminCodeIsValuedAsNotAdministered, vaccineCr);
      } else if (vaccineCvx.getCvxCode().equals(VaccineCvx.CONCEPT_TYPE_NON_VACCINE))
      {
        registerIssue(pi.VaccinationAdminCodeIsNotVaccine, vaccineCr);
      }
      if (vaccination.getAdminDate() != null)
      {
        if (vaccineCvx.getValidStartDate().after(vaccination.getAdminDate())
            || vaccination.getAdminDate().after(vaccineCvx.getValidEndDate()))
        {
          registerIssue(pi.VaccinationAdminCodeIsInvalid, vaccineCr);
        } else if (vaccineCvx.getUseStartDate().after(vaccination.getAdminDate())
            || vaccination.getAdminDate().after(vaccineCvx.getUseEndDate()))
        {
          registerIssue(pi.VaccinationAdminCodeIsDeprecated, vaccineCr);
        }
        int monthsBetween = monthsBetween(patient.getBirthDate(), vaccination.getAdminDate());
        if (monthsBetween < vaccineCvx.getUseMonthStart() || monthsBetween > vaccineCvx.getUseMonthEnd())
        {
          registerIssue(pi.VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge, vaccineCr);
        }
      }
    }
    handleCodeReceived(vaccination.getManufacturer(), PotentialIssues.Field.VACCINATION_MANUFACTURER_CODE, administered);
    VaccineMvx vaccineMvx = null;
    if (vaccination.getManufacturerCode() != null && !vaccination.getManufacturerCode().equals(""))
    {
      vaccineMvx = (VaccineMvx) session.get(VaccineMvx.class, vaccination.getManufacturerCode());
    }
    if (administered)
    {
      if (vaccineMvx != null && !vaccineMvx.getMvxCode().equals("") && vaccineCvx != null
          && !vaccineCvx.getCvxCode().equals("")
          && (vaccination.getManufacturer().isValid() || vaccination.getManufacturer().isDeprecated()))
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
          registerIssue(pi.VaccinationAdminDateIsAfterPatientDeathDate);
        }
      }
      if (patient.getBirthDate() != null)
      {
        if (vaccination.getAdminDate().before(patient.getBirthDate()))
        {
          registerIssue(pi.VaccinationAdminDateIsBeforeBirth);
        }
      }
      if (vaccination.getSystemEntryDate() != null)
      {
        if (vaccination.getAdminDate().after(vaccination.getSystemEntryDate()))
        {
          registerIssue(pi.VaccinationAdminDateIsAfterSystemEntryDate);
        }
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

    if (vaccination.getAmount() == null || vaccination.getAmount().equals("") || vaccination.getAmount().equals("999"))
    {
      if (administered)
      {
        registerIssue(pi.VaccinationAdministeredAmountIsMissing);
        registerIssue(pi.VaccinationAdministeredAmountIsValuedAsUnknown);
      }
      vaccination.setAmount("");
    } else
    {
      try
      {
        float amount = Float.parseFloat(vaccination.getAmount());
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
    handleCodeReceived(vaccination.getAmountUnit(), PotentialIssues.Field.VACCINATION_ADMINISTERED_UNIT, administered);
    handleCodeReceived(vaccination.getBodyRoute(), PotentialIssues.Field.VACCINATION_BODY_ROUTE, administered);
    handleCodeReceived(vaccination.getBodySite(), PotentialIssues.Field.VACCINATION_BODY_SITE, administered);

    // TODO VaccinationBodyRouteIsInvalidForVaccineIndicated
    // TODO VaccinationBodySiteIsInvalidForVaccineIndicated
    handleCodeReceived(vaccination.getCompletion(), PotentialIssues.Field.VACCINATION_COMPLETION_STATUS);
    if (vaccination.isCompletionCompleted())
    {
      registerIssue(pi.VaccinationCompletionStatusIsValuedAsCompleted);
    } else if (vaccination.isCompletionRefused())
    {
      registerIssue(pi.VaccinationCompletionStatusIsValuedAsRefused);
    } else if (vaccination.isCompletionNotAdministered())
    {
      registerIssue(pi.VaccinationCompletionStatusIsValuedAsNotAdministered);
    } else if (vaccination.isCompletionPartiallyAdministered())
    {
      registerIssue(pi.VaccinationCompletionStatusIsValuedAsPartiallyAdministered);
    }
    handleCodeReceived(vaccination.getConfidentiality(), PotentialIssues.Field.VACCINATION_CONFIDENTIALITY_CODE);

    if (vaccineCpt != null && vaccineCvx != null)
    {
      // TODO VaccinationCvxCodeAndCptCodeAreInconsistent
    }

    handleCodeReceived(vaccination.getFacility().getId(), PotentialIssues.Field.VACCINATION_FACILITY_ID, administered);
    handleCodeReceived(vaccination.getGivenBy(), PotentialIssues.Field.VACCINATION_GIVEN_BY, administered);
    handleCodeReceived(vaccination.getOrderedBy(), PotentialIssues.Field.VACCINATION_ORDERED_BY, administered);
    handleCodeReceived(vaccination.getEnteredBy(), PotentialIssues.Field.VACCINATION_RECORDED_BY);
    if (!notEmpty(vaccination.getIdSubmitter(), pi.VaccinationIdIsMissing))
    {
      // vaccination id is empty, need to set to default value
      if (vaccination.getAdminDate() == null)
      {
        vaccination.setIdSubmitter("dqa-none-" + vaccination.getAdminCvxCode());
      } else
      {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        vaccination.setIdSubmitter("dqa-" + sdf.format(vaccination.getAdminDate()) + "-"
            + vaccination.getAdminCvxCode());
      }
    }
    // TODO VaccinationIdOfReceiverIsMissing
    // TODO VaccinationIdOfReceiverIsUnrecognized
    // TODO VaccinationIdOfSenderIsMissing
    // TODO VaccinationIdOfSenderIsUnrecognized

    if (administered)
    {
      notEmpty(vaccination.getFacilityName(), pi.VaccinationFacilityNameIsMissing);
      notEmpty(vaccination.getExpirationDate(), pi.VaccinationLotExpirationDateIsMissing);
      if (notEmpty(vaccination.getLotNumber(), pi.VaccinationLotNumberIsMissing))
      {
        // TODO VaccinationLotNumberIsInvalid
      }
    }
    handleCodeReceived(vaccination.getRefusal(), PotentialIssues.Field.VACCINATION_REFUSAL_REASON);
    // TODO add logic to indicate if refusal reason is given but completion is
    // RE

    if (notEmpty(vaccination.getSystemEntryDate(), pi.VaccinationSystemEntryTimeIsMissing))
    {
      if (now.before(vaccination.getSystemEntryDate()))
      {
        registerIssue(pi.VaccinationSystemEntryTimeIsInFuture);
      }
    }

  }

  private void validatePatient()
  {
    piAddress = PotentialIssues.Field.PATIENT_ADDRESS;
    piAddressCity = PotentialIssues.Field.PATIENT_ADDRESS_CITY;
    piAddressStreet = PotentialIssues.Field.PATIENT_ADDRESS_STREET;
    piAddressCountry = PotentialIssues.Field.PATIENT_ADDRESS_COUNTRY;
    piAddressCounty = PotentialIssues.Field.PATIENT_ADDRESS_COUNTY;
    piAddressState = PotentialIssues.Field.PATIENT_ADDRESS_STATE;
    piAddressStreet2 = PotentialIssues.Field.PATIENT_ADDRESS_STREET2;
    piAddressZip = PotentialIssues.Field.PATIENT_ADDRESS_ZIP;
    validateAddress(patient.getAddress());

    String aliasFirst = patient.getAliasFirst();
    String aliasLast = patient.getAliasLast();
    notEmpty(aliasFirst + aliasLast, PotentialIssues.Field.PATIENT_ALIAS);

    // TODO
    // public PotentialIssue PatientDateOfBirthIsAfterSubmission = null;

    if (patient.getBirthDate() == null)
    {
      registerIssue(pi.PatientBirthDateIsMissing);
    } else
    {
      if (message.getReceivedDate().before(patient.getBirthDate()))
      {
        registerIssue(pi.PatientBirthDateIsInFuture);
      }
    }

    if (notEmpty(patient.getBirthMultiple(), pi.PatientBirthIndicatorIsMissing))
    {
      if (patient.getBirthMultiple().equals("Y"))
      {
        handleCodeReceived(patient.getBirthOrder(), PotentialIssues.Field.PATIENT_BIRTH_ORDER);
        if (patient.getBirthOrder().isEmpty())
        {
          registerIssue(pi.PatientBirthOrderIsMissingAndMultipleBirthIndicated);
        }
      } else if (patient.getBirthMultiple().equals("N"))
      {
        if (!patient.getBirthOrder().isEmpty())
        {
          registerIssue(pi.PatientBirthOrderIsInvalid);
        }
      } else
      {
        registerIssue(pi.PatientBirthIndicatorIsInvalid);
      }
    } else
    {
      if (!patient.getBirthOrder().isEmpty())
      {
        registerIssue(pi.PatientBirthOrderIsInvalid);
      }
    }
    notEmpty(patient.getBirthPlace(), pi.PatientBirthPlaceIsMissing);
    if (notEmpty(patient.getDeathDate(), pi.PatientDeathDateIsMissing))
    {
      if (patient.getBirthDate() != null && patient.getDeathDate().before(patient.getBirthDate()))
      {
        registerIssue(pi.PatientDeathDateIsBeforeBirth);
      }
      if (message.getReceivedDate().before(patient.getDeathDate()))
      {
        registerIssue(pi.PatientDeathDateIsInFuture);
      }
    }
    handleCodeReceived(patient.getEthnicity(), PotentialIssues.Field.PATIENT_ETHNICITY);
    specialNameHandling1(patient.getName());
    specialNameHandling2(patient.getName());
    specialNameHandling3(patient.getName());
    specialNameHandling5(patient.getName());
    specialNameHandling6(patient.getName());
    specialNameHandling7(patient.getName());
    if (notEmpty(patient.getNameFirst(), pi.PatientFirstNameIsMissing))
    {
      for (String invalidName : INVALID_NAMES)
      {
        if (patient.getNameFirst().equalsIgnoreCase(invalidName))
        {
          registerIssue(pi.PatientFirstNameIsInvalid);
        }
      }
      if (!validNameChars(patient.getNameFirst()))
      {
        registerIssue(pi.PatientFirstNameIsInvalid);
      }
      // TODO PatientFirstNameMayIncludeMiddleInitial
    }
    handleCodeReceived(patient.getSex(), PotentialIssues.Field.PATIENT_GENDER);
    // TODO
    // String registryStatus = patient.getRegistryStatus();
    // handleCodeReceived(registryStatus,
    // CodesReceived.getCodeTable(CodesReceived.REGISTRY_STATUS),
    // PotentialIssues.Field.PATIENT_REGISTRY_ID);
    handleCodeReceived(patient.getRegistryStatus(), PotentialIssues.Field.PATIENT_REGISTRY_STATUS);

    if (notEmpty(patient.getNameLast(), pi.PatientLastNameIsMissing))
    {
      for (String invalidName : INVALID_NAMES)
      {
        if (patient.getNameLast().equalsIgnoreCase(invalidName))
        {
          registerIssue(pi.PatientLastNameIsInvalid);
        }
      }
      if (!validNameChars(patient.getNameLast()))
      {
        registerIssue(pi.PatientLastNameIsInvalid);
      }
    }
    if (notEmpty(patient.getIdMedicaid(), pi.PatientMedicaidNumberIsMissing))
    {
      String medicaid = patient.getIdMedicaidNumber();
      medicaid = validateNumber(medicaid, pi.PatientMedicaidNumberIsInvalid);
      patient.setIdMedicaidNumber(medicaid);
    }

    String middleName = patient.getNameMiddle();
    if (patient.getNameMiddle().endsWith("."))
    {
      middleName = patient.getNameMiddle();
      middleName = middleName.substring(0, middleName.length() - 1);
      patient.setNameMiddle(middleName);
      if (!validNameChars(patient.getNameMiddle()))
      {
        // TODO registerIssue(pi.PatientFirstNameIsMiddle);
      }
    }
    if (notEmpty(middleName, pi.PatientMiddleNameIsMissing))
    {
      for (String invalidName : INVALID_NAMES)
      {
        if (patient.getNameMiddle().equalsIgnoreCase(invalidName))
        {
          // TODO middle name is invalid
          patient.setNameMiddle("");
        }
      }

      // PatientMiddleNameMayBeInitial
      middleName = patient.getNameMiddle();
      if (middleName.length() == 1)
      {
        registerIssue(pi.PatientMiddleNameMayBeInitial);
      }
    }
    if (!isEmpty(patient.getNameSuffix()))
    {
      if (patient.getNameSuffix().equalsIgnoreCase("11") || patient.getNameSuffix().equalsIgnoreCase("2nd"))
      {
        patient.setNameSuffix("II");
      } else if (patient.getNameSuffix().equalsIgnoreCase("111") || patient.getNameSuffix().equalsIgnoreCase("3rd"))
      {
        patient.setNameSuffix("III");
      } else if (patient.getNameSuffix().equalsIgnoreCase("4th"))
      {
        patient.setNameSuffix("IV");
      }
      boolean isValid = false;
      for (String valid : VALID_SUFFIX)
      {
        if (patient.getNameSuffix().equalsIgnoreCase(valid))
        {
          isValid = true;
        }
      }
      if (!isValid)
      {
        // TODO suffix is invalid
        patient.setNameSuffix("");
      }
    }
    if (notEmpty(patient.getMotherMaidenName(), pi.PatientMotherSMaidenNameIsMissing))
    {
      for (String invalidName : INVALID_NAMES)
      {
        if (patient.getMotherMaidenName().equalsIgnoreCase(invalidName))
        {
          registerIssue(pi.PatientFirstNameIsInvalid);
          patient.setMotherMaidenName("");
        }
      }
      if (patient.getMotherMaidenName().length() == 1)
      {
        registerIssue(pi.PatientFirstNameIsInvalid);
        patient.setMotherMaidenName("");
      }
    }

    // TODO PatientNameMayBeTemporaryNewbornName
    // TODO PatientNameMayBeTestName

    validatePhone(patient.getPhone(), PotentialIssues.Field.PATIENT_PHONE);

    notEmpty(patient.getFacilityName(), pi.PatientPrimaryFacilityNameIsMissing);
    handleCodeReceived(patient.getFacility().getId(), PotentialIssues.Field.PATIENT_PRIMARY_FACILITY_ID);
    handleCodeReceived(patient.getPrimaryLanguage(), PotentialIssues.Field.PATIENT_PRIMARY_LANGUAGE);
    handleCodeReceived(patient.getPhysician(), PotentialIssues.Field.PATIENT_PRIMARY_PHYSICIAN_ID);
    notEmpty(patient.getPhysician().getName(), pi.PatientPrimaryPhysicianNameIsMissing);
    handleCodeReceived(patient.getProtection(), PotentialIssues.Field.PATIENT_PROTECTION_INDICATOR);
    handleCodeReceived(patient.getPublicity(), PotentialIssues.Field.PATIENT_PUBLICITY_CODE);
    handleCodeReceived(patient.getRace(), PotentialIssues.Field.PATIENT_RACE);
    notEmpty(patient.getIdRegistry(), pi.PatientRegistryIdIsMissing);

    if (notEmpty(patient.getIdRegistryNumber(), PotentialIssues.Field.PATIENT_REGISTRY_ID))
    {
      // TODO PatientRegistryIdIsUnrecognized
    }
    if (notEmpty(patient.getIdSsnNumber(), PotentialIssues.Field.PATIENT_SSN))
    {
      String ssn = patient.getIdSsnNumber();
      ssn = validateNumber(ssn, pi.PatientSsnIsInvalid);
      patient.setIdSsnNumber(ssn);
    }
    notEmpty(patient.getIdSubmitterNumber(), PotentialIssues.Field.PATIENT_SUBMITTER_ID);

    // TODO PatientVfcEffectiveDateIsBeforeBirth
    // TODO PatientVfcEffectiveDateIsInFuture
    // TODO PatientVfcEffectiveDateIsInvalid
    // TODO PatientVfcEffectiveDateIsMissing
    handleCodeReceived(patient.getFinancialEligibility(), PotentialIssues.Field.PATIENT_VFC_STATUS);
    if (notEmpty(patient.getDeathIndicator(), pi.PatientDeathIndicatorIsMissing))
    {
      if (patient.getDeathIndicator().equals("Y") && patient.getDeathDate() == null)
      {
        registerIssue(pi.PatientDeathIndicatorIsInconsistent);
      } else if (patient.getDeathDate() == null)
      {
        registerIssue(pi.PatientDeathIndicatorIsInconsistent);
      }
    }
  }

  private String validateNumber(String ssn, PotentialIssue invalidIssue)
  {
    if (ssn.length() != 9 || ssn.equals("123456789") || ssn.equals("98765432"))
    {
      registerIssue(invalidIssue);
      ssn = "";
    } else
    {
      char lastC = 'S';
      int count = 0;
      for (char c : ssn.toCharArray())
      {
        if (lastC == c)
        {
          count++;
        } else
        {
          count = 0;
        }
        if (count >= 6)
        {
          registerIssue(invalidIssue);
          ssn = "";
          break;
        }
        lastC = c;
      }
    }
    return ssn;
  }

  private void validatePhone(PhoneNumber phone, PotentialIssues.Field piPhone)
  {
    if (notEmpty(phone.getNumber(), piPhone))
    {
      if (phone.getAreaCode().equals("") || phone.getLocalNumber().equals(""))
      {
        // TODO PatientPhoneIsIncomplete
      }
      // TODO PatientPhoneIsInvalid
    }
  }

  private boolean validateAddress(Address address)
  {
    String city = address.getCity();
    if (notEmpty(city, piAddressCity))
    {
      if (city.equalsIgnoreCase("ANYTOWN") || city.length() <= 1)
      {
        registerIssue(pi.getIssue(piAddressCity, PotentialIssue.ISSUE_TYPE_IS_INVALID));
      }
    }
    if (address.getState().getCode().equalsIgnoreCase("us"))
    {
      address.getState().setCode("TX");
      address.getCountry().setCode("USA");
    } else if (address.getState().getCode().equalsIgnoreCase("mx")
        || address.getState().getCode().equalsIgnoreCase("mex")
        || address.getState().getCode().equalsIgnoreCase("mexico"))
    {
      address.getCountry().setCode(address.getState().getCode());
    }

    handleCodeReceived(address.getCountry(), piAddressCountry);
    handleCodeReceived(address.getCountyParish(), piAddressCounty);
    handleCodeReceived(address.getState(), piAddressState);
    notEmpty(address.getStreet(), piAddressStreet);
    notEmpty(address.getStreet2(), piAddressStreet2);
    if (notEmpty(address.getZip(), piAddressZip))
    {
      String zip = address.getZip();
      int dash = zip.indexOf('-');
      if (dash != 5 && dash != -1 && zip.length() != 5)
      {
        address.setZip("");
      }
    }

    if (isEmpty(address.getStreet()) || isEmpty(city) || isEmpty(address.getZip()) || isEmpty(address.getState()))
    {
      registerIssue(pi.getIssue(piAddress, PotentialIssue.ISSUE_TYPE_IS_MISSING));
      return false;
    }
    return true;
  }

  private boolean notEmpty(Id id, PotentialIssue potentialIssue)
  {
    return notEmpty(id.getNumber(), potentialIssue);
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

  private boolean notEmpty(Name name, PotentialIssue potentialIssue)
  {
    boolean empty = true;
    if (name != null)
    {
      if (name.getFirst() != null && !name.getFirst().equals(""))
      {
        empty = false;
      } else if (name.getLast() != null && !name.getLast().equals(""))
      {
        empty = false;
      }
    }
    if (empty)
    {
      registerIssue(potentialIssue);
    }
    return !empty;
  }

  protected void handleCodeReceived(Id id, PotentialIssues.Field field)
  {

    handleCodeReceived(id, field, true);
  }

  protected void handleCodeReceived(Id id, PotentialIssues.Field field, boolean notSilent)
  {
    CodeReceived cr = null;
    if (notEmpty(id.getNumber(), field))
    {
      CodeTable codeTable = CodesReceived.getCodeTable(id.getTableType());
      cr = getCodeReceived(id.getNumber(), id.getName().getFullName(), codeTable);
      cr.incReceivedCount();
      session.save(cr);
      if (cr.getCodeStatus().isValid())
      {
        id.setCodeReceived(cr);
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
      id.setNumber(cr.getCodeValue());
    }
    id.setCodeReceived(cr);
  }

  protected void handleCodeReceived(CodedEntity codedEntity, PotentialIssues.Field field)
  {
    handleCodeReceived(codedEntity, field, true);
  }

  protected void handleCodeReceived(CodedEntity codedEntity, PotentialIssues.Field field, boolean notSilent)
  {
    CodeReceived cr = null;
    if (notEmpty(codedEntity.getCode(), field))
    {
      CodeTable codeTable = CodesReceived.getCodeTable(codedEntity.getTableType());
      cr = getCodeReceived(codedEntity.getCode(), codedEntity.getText(), codeTable);
      cr.incReceivedCount();
      session.save(cr);
      if (cr.getCodeStatus().isValid())
      {
        codedEntity.setCodeReceived(cr);
        return;
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
    codedEntity.setCodeReceived(cr);
  }

  // protected CodeReceived handleCodeReceived(String receivedValue, CodeTable
  // codeTable, PotentialIssues.Field field)
  // {
  // return handleCodeReceived(receivedValue, codeTable, field, true);
  // }
  //
  // protected CodeReceived handleCodeReceived(String value, CodeTable
  // codeTable, PotentialIssues.Field field,
  // boolean notSilent)
  // {
  // CodeReceived cr = null;
  // if (notEmpty(value, field))
  // {
  // cr = getCodeReceived(value, codeTable);
  // cr.incReceivedCount();
  // session.save(cr);
  // if (cr.getCodeStatus().isValid())
  // {
  // // Do nothing
  // // registerIssue(pi.getIssue(field,
  // // PotentialIssue.ISSUE_TYPE_IS_INVALID), cr);
  // } else if (cr.getCodeStatus().isInvalid())
  // {
  // if (notSilent)
  // {
  // registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_INVALID),
  // cr);
  // }
  // } else if (cr.getCodeStatus().isUnrecognized())
  // {
  // if (notSilent)
  // {
  // registerIssue(pi.getIssue(field,
  // PotentialIssue.ISSUE_TYPE_IS_UNRECOGNIZED), cr);
  // }
  // } else if (cr.getCodeStatus().isDeprecated())
  // {
  // if (notSilent)
  // {
  // registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_DEPRECATE),
  // cr);
  // }
  // } else if (cr.getCodeStatus().isIgnored())
  // {
  // if (notSilent)
  // {
  // registerIssue(pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_IGNORED),
  // cr);
  // }
  // }
  // }
  // if (cr == null)
  // {
  // cr = new CodeReceived();
  // }
  // return cr;
  // }
  
  private static String trunc(String s, int length)
  {
    if (s == null || s.length() < length)
    {
      return s;
    }
    return s.substring(0, length);
  }

  protected CodeReceived getCodeReceived(String receivedValue, String receivedLabel, CodeTable codeTable)
  {
    receivedValue = trunc(receivedValue, 50);
    receivedLabel = trunc(receivedLabel, 30);
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
      cr.setCodeLabel(receivedLabel);
      session.save(cr);
    } else if (!cr.getProfile().equals(profile))
    {
      cr = new CodeReceived(cr, profile, receivedLabel);
      session.save(cr);
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

  private static boolean isEmpty(String s)
  {
    return s == null || s.equals("");
  }

  private static boolean isEmpty(CodedEntity ce)
  {
    return ce == null || ce.isEmpty();
  }

  private static boolean areEqual(String field1, String field2)
  {
    return field1 != null && field1.equals(field2);
  }

  protected static void specialNameHandling1(Name name)
  {
    name.setFirst(specialNameHandling1(name.getFirst()));
    name.setLast(specialNameHandling1(name.getLast()));
    name.setMiddle(specialNameHandling1(name.getMiddle()));
    name.setSuffix(specialNameHandling1(name.getSuffix()));
  }

  protected static String specialNameHandling1(String s)
  {
    return s.replace('0', 'o');
  }

  protected static void specialNameHandling2(Name name)
  {
    name.setFirst(specialNameHandling2(name.getFirst()));
    name.setLast(specialNameHandling2(name.getLast()));
    name.setMiddle(specialNameHandling2(name.getMiddle()));
    name.setSuffix(specialNameHandling2(name.getSuffix()));
  }

  protected static String specialNameHandling2(String s)
  {
    return s.replace(',', ' ');
  }

  protected static void specialNameHandling3(Name name)
  {
    if (isEmpty(name.getMiddle()))
    {
      int pos = name.getFirst().lastIndexOf(' ');
      if (pos > 0)
      {
        String middleName = name.getFirst().substring(pos).trim();
        name.setMiddle(middleName);
      }
    }
  }

  protected static void specialNameHandling5(Name name)
  {
    name.setFirst(specialNameHandling5(name.getFirst()));
    name.setLast(specialNameHandling5(name.getLast()));
    name.setMiddle(specialNameHandling5(name.getMiddle()));
    name.setSuffix(specialNameHandling5(name.getSuffix()));
  }

  protected static String specialNameHandling5(String s)
  {
    int pos = s.indexOf('(');
    if (pos > 0)
    {
      s = s.substring(0, pos).trim();
    }
    pos = s.indexOf('{');
    if (pos > 0)
    {
      s = s.substring(0, pos).trim();
    }
    pos = s.indexOf('[');
    if (pos > 0)
    {
      s = s.substring(0, pos).trim();
    }
    return s;
  }

  protected static void specialNameHandling6(Name name)
  {
    if (name.getFirst().toUpperCase().endsWith(" JR"))
    {
      name.setSuffix("Jr");
      name.setFirst(name.getFirst().substring(0, name.getFirst().length() - " JR".length()));
    }
  }

  protected static void specialNameHandling7(Name name)
  {
    if (!isEmpty(name.getMiddle()))
    {
      int pos = name.getMiddle().lastIndexOf(".");
      if (pos > 0)
      {
        name.setMiddle(name.getMiddle().substring(0, pos));
      }
    }
  }

  protected static boolean validNameChars(String s)
  {
    for (char c : s.toUpperCase().toCharArray())
    {
      if ((c < 'A' || c > 'Z') && c != '-' && c != '\'' && c != ' ')
      {
        return false;
      }
    }
    return true;
  }
}
