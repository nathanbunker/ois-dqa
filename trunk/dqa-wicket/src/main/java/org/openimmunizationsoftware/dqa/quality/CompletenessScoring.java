package org.openimmunizationsoftware.dqa.quality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;

public class CompletenessScoring
{

  public static final String VACCINATION_REQUIRED = "vaccinationRequired";
  public static final String VACCINATION_RECOMMENDED = "vaccinationRecommended";
  public static final String VACCINATION_OPTIONAL = "vaccinationOptional";
  public static final String VACCINATION_EXPECTED = "vaccinationExpected";
  public static final String PATIENT_REQUIRED = "patientRequired";
  public static final String PATIENT_RECOMMENDED = "patientRecommended";
  public static final String PATIENT_OPTIONAL = "patientOptional";
  public static final String PATIENT_EXPECTED = "patientExpected";

  private Map<String, ScoringSet> scoringSets = new HashMap<String, ScoringSet>();

  public ScoringSet getScoringSet(String label)
  {
    ScoringSet scoringSet = scoringSets.get(label);
    if (scoringSet == null)
    {
      scoringSet = new ScoringSet();
      scoringSet.setLabel(label);
      scoringSets.put(label, scoringSet);
    }
    return scoringSet;
  }

  public CompletenessScoring() {

    String patient = PotentialIssue.REPORT_DENOMINATOR_PATIENT_COUNT;
    String vaccination = PotentialIssue.REPORT_DENOMINATOR_VACCINATION_COUNT;
    String vaccinationAdmin = PotentialIssue.REPORT_DENOMINATOR_VACCINATION_ADMIN_COUNT;

    PotentialIssues pi = PotentialIssues.getPotentialIssues();
    ScoringSet patExp = getScoringSet(PATIENT_EXPECTED);
    ScoringSet patOpt = getScoringSet(PATIENT_OPTIONAL);
    ScoringSet patRec = getScoringSet(PATIENT_RECOMMENDED);
    ScoringSet patReq = getScoringSet(PATIENT_REQUIRED);
    ScoringSet vacExp = getScoringSet(VACCINATION_EXPECTED);
    ScoringSet vacOpt = getScoringSet(VACCINATION_OPTIONAL);
    ScoringSet vacRec = getScoringSet(VACCINATION_RECOMMENDED);
    ScoringSet vacReq = getScoringSet(VACCINATION_REQUIRED);

    patReq.setWeight(0.5);
    patExp.setWeight(0.3);
    patRec.setWeight(0.2);
    vacReq.setWeight(0.5);
    vacExp.setWeight(0.3);
    vacRec.setWeight(0.2);

    patReq.add("Patient Id", pi.PatientSubmitterIdIsMissing, patient, 10);
    patReq.add("First Name", pi.PatientFirstNameIsMissing, patient, 5);
    patReq.add("Last Name", pi.PatientLastNameIsMissing, patient, 5);
    patReq.add("Possible Test Name", pi.PatientNameMayBeTestName, patient, -10).setInvert(false);
    patReq.add("Possible Baby Name", pi.PatientNameMayBeTemporaryNewbornName, patient, -10).setInvert(false);
    patReq.add("Birth Date", pi.PatientBirthDateIsMissing, patient, 10);
    patReq.add("Sex", pi.PatientGenderIsMissing, patient, 5);
    patReq.add("Address", pi.PatientAddressIsMissing, patient, 2);
    patReq.add("Street", pi.PatientAddressStreetIsMissing, patient, 5).setIndent(true);
    patReq.add("City", pi.PatientAddressCityIsMissing, patient, 1).setIndent(true);
    patReq.add("State", pi.PatientAddressStateIsMissing, patient, 1).setIndent(true);
    patReq.add("Zip", pi.PatientAddressZipIsMissing, patient, 1).setIndent(true);

    patExp.add("Middle Name", pi.PatientAliasIsMissing, patient, 10);
    patExp.add("Phone", pi.PatientPhoneIsMissing, patient, 10);
    patExp.add("Mother's Maiden", pi.PatientMotherSMaidenNameIsMissing, patient, 10);

    patRec.add("Alias", pi.PatientAliasIsMissing, patient, 10);
    patRec.add("Birth Indicator", pi.PatientBirthIndicatorIsMissing, patient, 10);
    patRec.add("Ethnicity", pi.PatientEthnicityIsMissing, patient, 10);
    patRec.add("Race", pi.PatientRaceIsMissing, patient, 10);
    patRec.add("SSN", pi.PatientSsnIsMissing, patient, 10);
    patRec.add("Medicaid Id", pi.PatientMedicaidNumberIsMissing, patient, 5);
    patRec.add("Primary Language", pi.PatientPrimaryLanaguageIsMissing, patient, 5);

    vacReq.add("Vaccination Date", pi.VaccinationAdminDateIsMissing, vaccination, 40);
    vacReq.add("Vaccination Code", PotentialIssues.Field.VACCINATION_ADMIN_CODE, vaccination, 40);
    vacReq.add("Not Specific", pi.VaccinationAdminCodeIsNotSpecific, vaccination, -10).setDemerit();
    vacReq.add("Not Vaccine", pi.VaccinationAdminCodeIsNotVaccine, vaccination, -40).setDemerit();
    vacReq.add("Valued as Unknown", pi.VaccinationAdminCodeIsValuedAsUnknown, vaccination, -40).setDemerit();
    vacReq.add("Information Source", PotentialIssues.Field.VACCINATION_INFORMATION_SOURCE, vaccination, 40);
    vacReq.add("May be Historical", pi.VaccinationInformationSourceIsAdministeredButAppearsToHistorical, vaccination,
        -10).setDemerit();
    vacReq.add("May be Administered", pi.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered,
        vaccination, -10).setDemerit();

    vacReq.add("CVX Code", PotentialIssues.Field.VACCINATION_CVX_CODE, vaccination, 20);
    vacReq.add("Lot Number", PotentialIssues.Field.VACCINATION_LOT_NUMBER, vaccinationAdmin, 20);
    vacReq.add("Manufacturer", PotentialIssues.Field.VACCINATION_MANUFACTURER_CODE, vaccinationAdmin, 20);
    vacReq.add("Admin Amount", pi.VaccinationAdministeredAmountIsMissing, vaccinationAdmin, 10);
    vacReq.add("Invalid", pi.VaccinationAdministeredAmountIsInvalid, vaccinationAdmin, -10).setDemerit();
    vacReq.add("Missing Units", pi.VaccinationAdministeredUnitIsMissing, vaccinationAdmin, -2).setDemerit();
    vacReq.add("Facility Id", PotentialIssues.Field.VACCINATION_FACILITY_ID, vaccinationAdmin, 20);
    vacReq.add("Body Site", PotentialIssues.Field.VACCINATION_BODY_SITE, vaccinationAdmin, 10);
    vacReq.add("Body Route", PotentialIssues.Field.VACCINATION_BODY_ROUTE, vaccinationAdmin, 10);
    
    vacRec.add("Action Code", PotentialIssues.Field.VACCINATION_ACTION_CODE, vaccination, 10);
    vacRec.add("Given by Id", PotentialIssues.Field.VACCINATION_GIVEN_BY, vaccinationAdmin, 10);
    vacRec.add("Vaccination Id", PotentialIssues.Field.VACCINATION_ID, vaccinationAdmin, 10);
    vacRec.add("Completion Status", PotentialIssues.Field.VACCINATION_COMPLETION_STATUS, vaccinationAdmin, 5);    
    vacRec.add("System Entry Date", pi.VaccinationSystemEntryTimeIsMissing, vaccination, 5);
    vacReq.add("Invalid", pi.VaccinationSystemEntryTimeIsInvalid, vaccination, -5).setDemerit();
    vacReq.add("In Future", pi.VaccinationSystemEntryTimeIsInFuture, vaccination, -5).setDemerit();
    
  }

}
