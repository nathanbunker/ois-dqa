package org.openimmunizationsoftware.dqa.manager;

import org.openimmunizationsoftware.dqa.manager.PotentialIssues;

import junit.framework.TestCase;

public class TestPotentialIssues extends TestCase
{
  public void testPotentialIssues()
  {
    PotentialIssues pi = PotentialIssues.getPotentialIssues();

    assertNotNull(pi.GeneralAuthorizationException);
    assertNotNull(pi.GeneralConfigurationException);
    assertNotNull(pi.GeneralParseException);
    assertNotNull(pi.GeneralProcessingException);
    assertNotNull(pi.Hl7SegmentIsUnrecognized);
    assertNotNull(pi.Hl7SegmentIsInvalid);
    assertNotNull(pi.Hl7SegmentsOutOfOrder);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsDeprecated);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsIgnored);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsInvalid);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsMissing);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsUnrecognized);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsValuedAsAlways);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsValuedAsNever);
    assertNotNull(pi.Hl7MshAcceptAckTypeIsValuedAsOnlyOnErrors);
    assertNotNull(pi.Hl7MshAltCharacterSetIsDeprecated);
    assertNotNull(pi.Hl7MshAltCharacterSetIsIgnored);
    assertNotNull(pi.Hl7MshAltCharacterSetIsInvalid);
    assertNotNull(pi.Hl7MshAltCharacterSetIsMissing);
    assertNotNull(pi.Hl7MshAltCharacterSetIsUnrecognized);
    assertNotNull(pi.Hl7MshAppAckTypeIsDeprecated);
    assertNotNull(pi.Hl7MshAppAckTypeIsIgnored);
    assertNotNull(pi.Hl7MshAppAckTypeIsInvalid);
    assertNotNull(pi.Hl7MshAppAckTypeIsMissing);
    assertNotNull(pi.Hl7MshAppAckTypeIsUnrecognized);
    assertNotNull(pi.Hl7MshAppAckTypeIsValuedAsAlways);
    assertNotNull(pi.Hl7MshAppAckTypeIsValuedAsNever);
    assertNotNull(pi.Hl7MshAppAckTypeIsValuedAsOnlyOnErrors);
    assertNotNull(pi.Hl7MshCharacterSetIsDeprecated);
    assertNotNull(pi.Hl7MshCharacterSetIsIgnored);
    assertNotNull(pi.Hl7MshCharacterSetIsInvalid);
    assertNotNull(pi.Hl7MshCharacterSetIsMissing);
    assertNotNull(pi.Hl7MshCharacterSetIsUnrecognized);
    assertNotNull(pi.Hl7MshCountryCodeIsDeprecated);
    assertNotNull(pi.Hl7MshCountryCodeIsIgnored);
    assertNotNull(pi.Hl7MshCountryCodeIsInvalid);
    assertNotNull(pi.Hl7MshCountryCodeIsMissing);
    assertNotNull(pi.Hl7MshCountryCodeIsUnrecognized);
    assertNotNull(pi.Hl7MshEncodingCharacterIsInvalid);
    assertNotNull(pi.Hl7MshEncodingCharacterIsMissing);
    assertNotNull(pi.Hl7MshEncodingCharacterIsNonStandard);
    assertNotNull(pi.Hl7MshMessageControlIdIsMissing);
    assertNotNull(pi.Hl7MshMessageDateIsInFuture);
    assertNotNull(pi.Hl7MshMessageDateIsInvalid);
    assertNotNull(pi.Hl7MshMessageDateIsMissing);
    assertNotNull(pi.Hl7MshMessageProfileIdIsDeprecated);
    assertNotNull(pi.Hl7MshMessageProfileIdIsIgnored);
    assertNotNull(pi.Hl7MshMessageProfileIdIsInvalid);
    assertNotNull(pi.Hl7MshMessageProfileIdIsMissing);
    assertNotNull(pi.Hl7MshMessageProfileIdIsUnrecognized);
    assertNotNull(pi.Hl7MshMessageStructureIsMissing);
    assertNotNull(pi.Hl7MshMessageStructureIsUnrecognized);
    assertNotNull(pi.Hl7MshMessageTriggerIsMissing);
    assertNotNull(pi.Hl7MshMessageTriggerIsUnrecognized);
    assertNotNull(pi.Hl7MshMessageTypeIsMissing);
    assertNotNull(pi.Hl7MshMessageTypeIsUnrecognized);
    assertNotNull(pi.Hl7MshMessageTypeIsUnsupported);
    assertNotNull(pi.Hl7MshProcessingIdIsDeprecated);
    assertNotNull(pi.Hl7MshProcessingIdIsIgnored);
    assertNotNull(pi.Hl7MshProcessingIdIsInvalid);
    assertNotNull(pi.Hl7MshProcessingIdIsMissing);
    assertNotNull(pi.Hl7MshProcessingIdIsUnrecognized);
    assertNotNull(pi.Hl7MshProcessingIdIsValuedAsDebug);
    assertNotNull(pi.Hl7MshProcessingIdIsValuedAsProduction);
    assertNotNull(pi.Hl7MshProcessingIdIsValuedAsTraining);
    assertNotNull(pi.Hl7MshReceivingApplicationIsInvalid);
    assertNotNull(pi.Hl7MshReceivingApplicationIsMissing);
    assertNotNull(pi.Hl7MshReceivingFacilityIsInvalid);
    assertNotNull(pi.Hl7MshReceivingFacilityIsMissing);
    assertNotNull(pi.Hl7MshSegmentIsMissing);
    assertNotNull(pi.Hl7MshSendingApplicationIsInvalid);
    assertNotNull(pi.Hl7MshSendingApplicationIsMissing);
    assertNotNull(pi.Hl7MshSendingFacilityIsInvalid);
    assertNotNull(pi.Hl7MshSendingFacilityIsMissing);
    assertNotNull(pi.Hl7MshVersionIsMissing);
    assertNotNull(pi.Hl7MshVersionIsUnrecognized);
    assertNotNull(pi.Hl7MshVersionIsValuedAs2_3_1);
    assertNotNull(pi.Hl7MshVersionIsValuedAs2_4);
    assertNotNull(pi.Hl7MshVersionIsValuedAs2_5);
    assertNotNull(pi.Hl7Nk1SegmentIsMissing);
    assertNotNull(pi.Hl7Nk1SegmentIsRepeated);
    assertNotNull(pi.Hl7Nk1SetIdIsMissing);
    assertNotNull(pi.Hl7ObxSegmentIsMissing);
    assertNotNull(pi.Hl7OrcSegmentIsMissing);
    assertNotNull(pi.Hl7OrcSegmentIsRepeated);
    assertNotNull(pi.Hl7Pd1SegmentIsMissing);
    assertNotNull(pi.Hl7PidSegmentIsMissing);
    assertNotNull(pi.Hl7PidSegmentIsRepeated);
    assertNotNull(pi.Hl7Pv1SegmentIsMissing);
    assertNotNull(pi.Hl7Pv1SegmentIsRepeated);
    assertNotNull(pi.Hl7RxaAdminSubIdCounterIsMissing);
    assertNotNull(pi.Hl7RxaGiveSubIdIsMissing);
    assertNotNull(pi.Hl7RxaSegmentIsMissing);
    assertNotNull(pi.Hl7RxaSegmentIsRepeated);
    assertNotNull(pi.Hl7RxrSegmentIsMissing);
    assertNotNull(pi.Hl7RxrSegmentIsRepeated);
    assertNotNull(pi.NextOfKinAddressIsDifferentFromPatientAddress);
    assertNotNull(pi.NextOfKinAddressIsMissing);
    assertNotNull(pi.NextOfKinAddressCityIsInvalid);
    assertNotNull(pi.NextOfKinAddressCityIsMissing);
    assertNotNull(pi.NextOfKinAddressCountryIsDeprecated);
    assertNotNull(pi.NextOfKinAddressCountryIsIgnored);
    assertNotNull(pi.NextOfKinAddressCountryIsInvalid);
    assertNotNull(pi.NextOfKinAddressCountryIsMissing);
    assertNotNull(pi.NextOfKinAddressCountryIsUnrecognized);
    assertNotNull(pi.NextOfKinAddressCountyIsDeprecated);
    assertNotNull(pi.NextOfKinAddressCountyIsIgnored);
    assertNotNull(pi.NextOfKinAddressCountyIsInvalid);
    assertNotNull(pi.NextOfKinAddressCountyIsMissing);
    assertNotNull(pi.NextOfKinAddressCountyIsUnrecognized);
    assertNotNull(pi.NextOfKinAddressStateIsDeprecated);
    assertNotNull(pi.NextOfKinAddressStateIsIgnored);
    assertNotNull(pi.NextOfKinAddressStateIsInvalid);
    assertNotNull(pi.NextOfKinAddressStateIsMissing);
    assertNotNull(pi.NextOfKinAddressStateIsUnrecognized);
    assertNotNull(pi.NextOfKinAddressStreetIsMissing);
    assertNotNull(pi.NextOfKinAddressStreet2IsMissing);
    assertNotNull(pi.NextOfKinAddressTypeIsDeprecated);
    assertNotNull(pi.NextOfKinAddressTypeIsIgnored);
    assertNotNull(pi.NextOfKinAddressTypeIsInvalid);
    assertNotNull(pi.NextOfKinAddressTypeIsMissing);
    assertNotNull(pi.NextOfKinAddressTypeIsUnrecognized);
    assertNotNull(pi.NextOfKinAddressZipIsInvalid);
    assertNotNull(pi.NextOfKinAddressZipIsMissing);
    assertNotNull(pi.NextOfKinNameIsMissing);
    assertNotNull(pi.NextOfKinNameFirstIsMissing);
    assertNotNull(pi.NextOfKinNameLastIsMissing);
    assertNotNull(pi.NextOfKinPhoneNumberIsIncomplete);
    assertNotNull(pi.NextOfKinPhoneNumberIsInvalid);
    assertNotNull(pi.NextOfKinPhoneNumberIsMissing);
    assertNotNull(pi.NextOfKinRelationshipIsDeprecated);
    assertNotNull(pi.NextOfKinRelationshipIsIgnored);
    assertNotNull(pi.NextOfKinRelationshipIsInvalid);
    assertNotNull(pi.NextOfKinRelationshipIsMissing);
    assertNotNull(pi.NextOfKinRelationshipIsNotResponsibleParty);
    assertNotNull(pi.NextOfKinRelationshipIsUnrecognized);
    assertNotNull(pi.NextOfKinSsnIsMissing);
    assertNotNull(pi.ObservationValueTypeIsDeprecated);
    assertNotNull(pi.ObservationValueTypeIsIgnored);
    assertNotNull(pi.ObservationValueTypeIsInvalid);
    assertNotNull(pi.ObservationValueTypeIsMissing);
    assertNotNull(pi.ObservationValueTypeIsUnrecognized);
    assertNotNull(pi.ObservationObservationIdentifierCodeIsDeprecated);
    assertNotNull(pi.ObservationObservationIdentifierCodeIsIgnored);
    assertNotNull(pi.ObservationObservationIdentifierCodeIsInvalid);
    assertNotNull(pi.ObservationObservationIdentifierCodeIsMissing);
    assertNotNull(pi.ObservationObservationIdentifierCodeIsUnrecognized);
    assertNotNull(pi.ObservationObservationValueIsMissing);
    assertNotNull(pi.ObservationDateTimeOfObservationIsMissing);
    assertNotNull(pi.ObservationDateTimeOfObservationIsInvalid);
    assertNotNull(pi.PatientAddressIsMissing);
    assertNotNull(pi.PatientAddressCityIsInvalid);
    assertNotNull(pi.PatientAddressCityIsMissing);
    assertNotNull(pi.PatientAddressCountryIsDeprecated);
    assertNotNull(pi.PatientAddressCountryIsIgnored);
    assertNotNull(pi.PatientAddressCountryIsInvalid);
    assertNotNull(pi.PatientAddressCountryIsMissing);
    assertNotNull(pi.PatientAddressCountryIsUnrecognized);
    assertNotNull(pi.PatientAddressCountyIsDeprecated);
    assertNotNull(pi.PatientAddressCountyIsIgnored);
    assertNotNull(pi.PatientAddressCountyIsInvalid);
    assertNotNull(pi.PatientAddressCountyIsMissing);
    assertNotNull(pi.PatientAddressCountyIsUnrecognized);
    assertNotNull(pi.PatientAddressStateIsDeprecated);
    assertNotNull(pi.PatientAddressStateIsIgnored);
    assertNotNull(pi.PatientAddressStateIsInvalid);
    assertNotNull(pi.PatientAddressStateIsMissing);
    assertNotNull(pi.PatientAddressStateIsUnrecognized);
    assertNotNull(pi.PatientAddressStreetIsMissing);
    assertNotNull(pi.PatientAddressStreet2IsMissing);
    assertNotNull(pi.PatientAddressTypeIsMissing);
    assertNotNull(pi.PatientAddressZipIsInvalid);
    assertNotNull(pi.PatientAddressZipIsMissing);
    assertNotNull(pi.PatientAliasIsMissing);
    assertNotNull(pi.PatientBirthDateIsAfterSubmission);
    assertNotNull(pi.PatientBirthDateIsInFuture);
    assertNotNull(pi.PatientBirthDateIsInvalid);
    assertNotNull(pi.PatientBirthDateIsMissing);
    assertNotNull(pi.PatientBirthDateIsUnderage);
    assertNotNull(pi.PatientBirthDateIsVeryLongAgo);
    assertNotNull(pi.PatientBirthIndicatorIsInvalid);
    assertNotNull(pi.PatientBirthIndicatorIsMissing);
    assertNotNull(pi.PatientBirthOrderIsInvalid);
    assertNotNull(pi.PatientBirthOrderIsMissing);
    assertNotNull(pi.PatientBirthOrderIsMissingAndMultipleBirthIndicated);
    assertNotNull(pi.PatientBirthPlaceIsMissing);
    assertNotNull(pi.PatientBirthRegistryIdIsInvalid);
    assertNotNull(pi.PatientBirthRegistryIdIsMissing);
    assertNotNull(pi.PatientClassIsDeprecated);
    assertNotNull(pi.PatientClassIsIgnored);
    assertNotNull(pi.PatientClassIsInvalid);
    assertNotNull(pi.PatientClassIsMissing);
    assertNotNull(pi.PatientClassIsUnrecognized);
    assertNotNull(pi.PatientDeathDateIsBeforeBirth);
    assertNotNull(pi.PatientDeathDateIsInFuture);
    assertNotNull(pi.PatientDeathDateIsInvalid);
    assertNotNull(pi.PatientDeathDateIsMissing);
    assertNotNull(pi.PatientDeathIndicatorIsInconsistent);
    assertNotNull(pi.PatientDeathIndicatorIsMissing);
    assertNotNull(pi.PatientEthnicityIsDeprecated);
    assertNotNull(pi.PatientEthnicityIsIgnored);
    assertNotNull(pi.PatientEthnicityIsInvalid);
    assertNotNull(pi.PatientEthnicityIsMissing);
    assertNotNull(pi.PatientEthnicityIsUnrecognized);
    assertNotNull(pi.PatientGenderIsDeprecated);
    assertNotNull(pi.PatientGenderIsIgnored);
    assertNotNull(pi.PatientGenderIsInvalid);
    assertNotNull(pi.PatientGenderIsMissing);
    assertNotNull(pi.PatientGenderIsUnrecognized);
    assertNotNull(pi.PatientGuardianAddressIsMissing);
    assertNotNull(pi.PatientGuardianAddressCityIsMissing);
    assertNotNull(pi.PatientGuardianAddressStateIsMissing);
    assertNotNull(pi.PatientGuardianAddressStreetIsMissing);
    assertNotNull(pi.PatientGuardianAddressZipIsMissing);
    assertNotNull(pi.PatientGuardianNameIsMissing);
    assertNotNull(pi.PatientGuardianNameIsSameAsUnderagePatient);
    assertNotNull(pi.PatientGuardianNameFirstIsMissing);
    assertNotNull(pi.PatientGuardianNameLastIsMissing);
    assertNotNull(pi.PatientGuardianResponsiblePartyIsMissing);
    assertNotNull(pi.PatientGuardianPhoneIsMissing);
    assertNotNull(pi.PatientGuardianRelationshipIsMissing);
    assertNotNull(pi.PatientImmunizationRegistryStatusIsDeprecated);
    assertNotNull(pi.PatientImmunizationRegistryStatusIsIgnored);
    assertNotNull(pi.PatientImmunizationRegistryStatusIsInvalid);
    assertNotNull(pi.PatientImmunizationRegistryStatusIsMissing);
    assertNotNull(pi.PatientImmunizationRegistryStatusIsUnrecognized);
    assertNotNull(pi.PatientMedicaidNumberIsInvalid);
    assertNotNull(pi.PatientMedicaidNumberIsMissing);
    assertNotNull(pi.PatientMiddleNameIsMissing);
    assertNotNull(pi.PatientMiddleNameMayBeInitial);
    assertNotNull(pi.PatientMotherSMaidenNameIsMissing);
    assertNotNull(pi.PatientNameMayBeTemporaryNewbornName);
    assertNotNull(pi.PatientNameMayBeTestName);
    assertNotNull(pi.PatientNameFirstIsInvalid);
    assertNotNull(pi.PatientNameFirstIsMissing);
    assertNotNull(pi.PatientNameFirstMayIncludeMiddleInitial);
    assertNotNull(pi.PatientNameLastIsInvalid);
    assertNotNull(pi.PatientNameLastIsMissing);
    assertNotNull(pi.PatientNameTypeCodeIsDeprecated);
    assertNotNull(pi.PatientNameTypeCodeIsIgnored);
    assertNotNull(pi.PatientNameTypeCodeIsInvalid);
    assertNotNull(pi.PatientNameTypeCodeIsMissing);
    assertNotNull(pi.PatientNameTypeCodeIsUnrecognized);
    assertNotNull(pi.PatientPhoneIsIncomplete);
    assertNotNull(pi.PatientPhoneIsInvalid);
    assertNotNull(pi.PatientPhoneIsMissing);
    assertNotNull(pi.PatientPhoneTelUseCodeIsDeprecated);
    assertNotNull(pi.PatientPhoneTelUseCodeIsIgnored);
    assertNotNull(pi.PatientPhoneTelUseCodeIsInvalid);
    assertNotNull(pi.PatientPhoneTelUseCodeIsMissing);
    assertNotNull(pi.PatientPhoneTelUseCodeIsUnrecognized);
    assertNotNull(pi.PatientPhoneTelEquipCodeIsDeprecated);
    assertNotNull(pi.PatientPhoneTelEquipCodeIsIgnored);
    assertNotNull(pi.PatientPhoneTelEquipCodeIsInvalid);
    assertNotNull(pi.PatientPhoneTelEquipCodeIsMissing);
    assertNotNull(pi.PatientPhoneTelEquipCodeIsUnrecognized);
    assertNotNull(pi.PatientPrimaryFacilityIdIsDeprecated);
    assertNotNull(pi.PatientPrimaryFacilityIdIsIgnored);
    assertNotNull(pi.PatientPrimaryFacilityIdIsInvalid);
    assertNotNull(pi.PatientPrimaryFacilityIdIsMissing);
    assertNotNull(pi.PatientPrimaryFacilityIdIsUnrecognized);
    assertNotNull(pi.PatientPrimaryFacilityNameIsMissing);
    assertNotNull(pi.PatientPrimaryLanguageIsDeprecated);
    assertNotNull(pi.PatientPrimaryLanguageIsIgnored);
    assertNotNull(pi.PatientPrimaryLanguageIsInvalid);
    assertNotNull(pi.PatientPrimaryLanguageIsMissing);
    assertNotNull(pi.PatientPrimaryLanguageIsUnrecognized);
    assertNotNull(pi.PatientPrimaryPhysicianIdIsDeprecated);
    assertNotNull(pi.PatientPrimaryPhysicianIdIsIgnored);
    assertNotNull(pi.PatientPrimaryPhysicianIdIsInvalid);
    assertNotNull(pi.PatientPrimaryPhysicianIdIsMissing);
    assertNotNull(pi.PatientPrimaryPhysicianIdIsUnrecognized);
    assertNotNull(pi.PatientPrimaryPhysicianNameIsMissing);
    assertNotNull(pi.PatientProtectionIndicatorIsDeprecated);
    assertNotNull(pi.PatientProtectionIndicatorIsIgnored);
    assertNotNull(pi.PatientProtectionIndicatorIsInvalid);
    assertNotNull(pi.PatientProtectionIndicatorIsMissing);
    assertNotNull(pi.PatientProtectionIndicatorIsUnrecognized);
    assertNotNull(pi.PatientProtectionIndicatorIsValuedAsNo);
    assertNotNull(pi.PatientProtectionIndicatorIsValuedAsYes);
    assertNotNull(pi.PatientPublicityCodeIsDeprecated);
    assertNotNull(pi.PatientPublicityCodeIsIgnored);
    assertNotNull(pi.PatientPublicityCodeIsInvalid);
    assertNotNull(pi.PatientPublicityCodeIsMissing);
    assertNotNull(pi.PatientPublicityCodeIsUnrecognized);
    assertNotNull(pi.PatientRaceIsDeprecated);
    assertNotNull(pi.PatientRaceIsIgnored);
    assertNotNull(pi.PatientRaceIsInvalid);
    assertNotNull(pi.PatientRaceIsMissing);
    assertNotNull(pi.PatientRaceIsUnrecognized);
    assertNotNull(pi.PatientRegistryIdIsMissing);
    assertNotNull(pi.PatientRegistryIdIsUnrecognized);
    assertNotNull(pi.PatientRegistryStatusIsDeprecated);
    assertNotNull(pi.PatientRegistryStatusIsIgnored);
    assertNotNull(pi.PatientRegistryStatusIsInvalid);
    assertNotNull(pi.PatientRegistryStatusIsMissing);
    assertNotNull(pi.PatientRegistryStatusIsUnrecognized);
    assertNotNull(pi.PatientSsnIsInvalid);
    assertNotNull(pi.PatientSsnIsMissing);
    assertNotNull(pi.PatientSubmitterIdIsMissing);
    assertNotNull(pi.PatientSubmitterIdAuthorityIsMissing);
    assertNotNull(pi.PatientSubmitterIdTypeCodeIsMissing);
    assertNotNull(pi.PatientVfcEffectiveDateIsBeforeBirth);
    assertNotNull(pi.PatientVfcEffectiveDateIsInFuture);
    assertNotNull(pi.PatientVfcEffectiveDateIsInvalid);
    assertNotNull(pi.PatientVfcEffectiveDateIsMissing);
    assertNotNull(pi.PatientVfcStatusIsDeprecated);
    assertNotNull(pi.PatientVfcStatusIsIgnored);
    assertNotNull(pi.PatientVfcStatusIsInvalid);
    assertNotNull(pi.PatientVfcStatusIsMissing);
    assertNotNull(pi.PatientVfcStatusIsUnrecognized);
    assertNotNull(pi.PatientWicIdIsInvalid);
    assertNotNull(pi.PatientWicIdIsMissing);
    assertNotNull(pi.VaccinationActionCodeIsDeprecated);
    assertNotNull(pi.VaccinationActionCodeIsIgnored);
    assertNotNull(pi.VaccinationActionCodeIsInvalid);
    assertNotNull(pi.VaccinationActionCodeIsMissing);
    assertNotNull(pi.VaccinationActionCodeIsUnrecognized);
    assertNotNull(pi.VaccinationActionCodeIsValuedAsAdd);
    assertNotNull(pi.VaccinationActionCodeIsValuedAsAddOrUpdate);
    assertNotNull(pi.VaccinationActionCodeIsValuedAsDelete);
    assertNotNull(pi.VaccinationActionCodeIsValuedAsUpdate);
    assertNotNull(pi.VaccinationAdminCodeIsDeprecated);
    assertNotNull(pi.VaccinationAdminCodeIsIgnored);
    assertNotNull(pi.VaccinationAdminCodeIsInvalid);
    assertNotNull(pi.VaccinationAdminCodeIsMissing);
    assertNotNull(pi.VaccinationAdminCodeIsNotSpecific);
    assertNotNull(pi.VaccinationAdminCodeIsNotVaccine);
    assertNotNull(pi.VaccinationAdminCodeIsUnrecognized);
    assertNotNull(pi.VaccinationAdminCodeIsValuedAsNotAdministered);
    assertNotNull(pi.VaccinationAdminCodeIsValuedAsUnknown);
    assertNotNull(pi.VaccinationAdminCodeTableIsMissing);
    assertNotNull(pi.VaccinationAdminCodeTableIsInvalid);
    assertNotNull(pi.VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes);
    assertNotNull(pi.VaccinationAdminDateIsAfterLotExpirationDate);
    assertNotNull(pi.VaccinationAdminDateIsAfterMessageSubmitted);
    assertNotNull(pi.VaccinationAdminDateIsAfterPatientDeathDate);
    assertNotNull(pi.VaccinationAdminDateIsAfterSystemEntryDate);
    assertNotNull(pi.VaccinationAdminDateIsBeforeBirth);
    assertNotNull(pi.VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange);
    assertNotNull(pi.VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange);
    assertNotNull(pi.VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge);
    assertNotNull(pi.VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge);
    assertNotNull(pi.VaccinationAdminDateIsInvalid);
    assertNotNull(pi.VaccinationAdminDateIsMissing);
    assertNotNull(pi.VaccinationAdminDateIsOn15ThDayOfMonth);
    assertNotNull(pi.VaccinationAdminDateIsOnFirstDayOfMonth);
    assertNotNull(pi.VaccinationAdminDateIsOnLastDayOfMonth);
    assertNotNull(pi.VaccinationAdminDateIsReportedLate);
    assertNotNull(pi.VaccinationAdminDateEndIsDifferentFromStartDate);
    assertNotNull(pi.VaccinationAdminDateEndIsMissing);
    assertNotNull(pi.VaccinationAdministeredAmountIsInvalid);
    assertNotNull(pi.VaccinationAdministeredAmountIsMissing);
    assertNotNull(pi.VaccinationAdministeredAmountIsValuedAsZero);
    assertNotNull(pi.VaccinationAdministeredAmountIsValuedAsUnknown);
    assertNotNull(pi.VaccinationAdministeredUnitIsDeprecated);
    assertNotNull(pi.VaccinationAdministeredUnitIsIgnored);
    assertNotNull(pi.VaccinationAdministeredUnitIsInvalid);
    assertNotNull(pi.VaccinationAdministeredUnitIsMissing);
    assertNotNull(pi.VaccinationAdministeredUnitIsUnrecognized);
    assertNotNull(pi.VaccinationBodyRouteIsDeprecated);
    assertNotNull(pi.VaccinationBodyRouteIsIgnored);
    assertNotNull(pi.VaccinationBodyRouteIsInvalid);
    assertNotNull(pi.VaccinationBodyRouteIsInvalidForVaccineIndicated);
    assertNotNull(pi.VaccinationBodyRouteIsMissing);
    assertNotNull(pi.VaccinationBodyRouteIsUnrecognized);
    assertNotNull(pi.VaccinationBodySiteIsDeprecated);
    assertNotNull(pi.VaccinationBodySiteIsIgnored);
    assertNotNull(pi.VaccinationBodySiteIsInvalid);
    assertNotNull(pi.VaccinationBodySiteIsInvalidForVaccineIndicated);
    assertNotNull(pi.VaccinationBodySiteIsMissing);
    assertNotNull(pi.VaccinationBodySiteIsUnrecognized);
    assertNotNull(pi.VaccinationCompletionStatusIsDeprecated);
    assertNotNull(pi.VaccinationCompletionStatusIsIgnored);
    assertNotNull(pi.VaccinationCompletionStatusIsInvalid);
    assertNotNull(pi.VaccinationCompletionStatusIsMissing);
    assertNotNull(pi.VaccinationCompletionStatusIsUnrecognized);
    assertNotNull(pi.VaccinationCompletionStatusIsValuedAsCompleted);
    assertNotNull(pi.VaccinationCompletionStatusIsValuedAsNotAdministered);
    assertNotNull(pi.VaccinationCompletionStatusIsValuedAsPartiallyAdministered);
    assertNotNull(pi.VaccinationCompletionStatusIsValuedAsRefused);
    assertNotNull(pi.VaccinationConfidentialityCodeIsDeprecated);
    assertNotNull(pi.VaccinationConfidentialityCodeIsIgnored);
    assertNotNull(pi.VaccinationConfidentialityCodeIsInvalid);
    assertNotNull(pi.VaccinationConfidentialityCodeIsMissing);
    assertNotNull(pi.VaccinationConfidentialityCodeIsUnrecognized);
    assertNotNull(pi.VaccinationConfidentialityCodeIsValuedAsRestricted);
    assertNotNull(pi.VaccinationCptCodeIsDeprecated);
    assertNotNull(pi.VaccinationCptCodeIsIgnored);
    assertNotNull(pi.VaccinationCptCodeIsInvalid);
    assertNotNull(pi.VaccinationCptCodeIsMissing);
    assertNotNull(pi.VaccinationCptCodeIsUnrecognized);
    assertNotNull(pi.VaccinationCvxCodeIsDeprecated);
    assertNotNull(pi.VaccinationCvxCodeIsIgnored);
    assertNotNull(pi.VaccinationCvxCodeIsInvalid);
    assertNotNull(pi.VaccinationCvxCodeIsMissing);
    assertNotNull(pi.VaccinationCvxCodeIsUnrecognized);
    assertNotNull(pi.VaccinationCvxCodeAndCptCodeAreInconsistent);
    assertNotNull(pi.VaccinationFacilityIdIsDeprecated);
    assertNotNull(pi.VaccinationFacilityIdIsIgnored);
    assertNotNull(pi.VaccinationFacilityIdIsInvalid);
    assertNotNull(pi.VaccinationFacilityIdIsMissing);
    assertNotNull(pi.VaccinationFacilityIdIsUnrecognized);
    assertNotNull(pi.VaccinationFacilityNameIsMissing);
    assertNotNull(pi.VaccinationFillerOrderNumberIsDeprecated);
    assertNotNull(pi.VaccinationFillerOrderNumberIsIgnored);
    assertNotNull(pi.VaccinationFillerOrderNumberIsInvalid);
    assertNotNull(pi.VaccinationFillerOrderNumberIsMissing);
    assertNotNull(pi.VaccinationFillerOrderNumberIsUnrecognized);
    assertNotNull(pi.VaccinationFinancialEligibilityCodeIsDeprecated);
    assertNotNull(pi.VaccinationFinancialEligibilityCodeIsIgnored);
    assertNotNull(pi.VaccinationFinancialEligibilityCodeIsInvalid);
    assertNotNull(pi.VaccinationFinancialEligibilityCodeIsMissing);
    assertNotNull(pi.VaccinationFinancialEligibilityCodeIsUnrecognized);
    assertNotNull(pi.VaccinationGivenByIsDeprecated);
    assertNotNull(pi.VaccinationGivenByIsIgnored);
    assertNotNull(pi.VaccinationGivenByIsInvalid);
    assertNotNull(pi.VaccinationGivenByIsMissing);
    assertNotNull(pi.VaccinationGivenByIsUnrecognized);
    assertNotNull(pi.VaccinationIdIsMissing);
    assertNotNull(pi.VaccinationIdOfReceiverIsMissing);
    assertNotNull(pi.VaccinationIdOfReceiverIsUnrecognized);
    assertNotNull(pi.VaccinationIdOfSenderIsMissing);
    assertNotNull(pi.VaccinationIdOfSenderIsUnrecognized);
    assertNotNull(pi.VaccinationInformationSourceIsAdministeredButAppearsToHistorical);
    assertNotNull(pi.VaccinationInformationSourceIsDeprecated);
    assertNotNull(pi.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered);
    assertNotNull(pi.VaccinationInformationSourceIsIgnored);
    assertNotNull(pi.VaccinationInformationSourceIsInvalid);
    assertNotNull(pi.VaccinationInformationSourceIsMissing);
    assertNotNull(pi.VaccinationInformationSourceIsUnrecognized);
    assertNotNull(pi.VaccinationInformationSourceIsValuedAsAdministered);
    assertNotNull(pi.VaccinationInformationSourceIsValuedAsHistorical);
    assertNotNull(pi.VaccinationLotExpirationDateIsInvalid);
    assertNotNull(pi.VaccinationLotExpirationDateIsMissing);
    assertNotNull(pi.VaccinationLotNumberIsInvalid);
    assertNotNull(pi.VaccinationLotNumberIsMissing);
    assertNotNull(pi.VaccinationManufacturerCodeIsDeprecated);
    assertNotNull(pi.VaccinationManufacturerCodeIsIgnored);
    assertNotNull(pi.VaccinationManufacturerCodeIsInvalid);
    assertNotNull(pi.VaccinationManufacturerCodeIsMissing);
    assertNotNull(pi.VaccinationManufacturerCodeIsUnrecognized);
    assertNotNull(pi.VaccinationOrderControlCodeIsDeprecated);
    assertNotNull(pi.VaccinationOrderControlCodeIsIgnored);
    assertNotNull(pi.VaccinationOrderControlCodeIsInvalid);
    assertNotNull(pi.VaccinationOrderControlCodeIsMissing);
    assertNotNull(pi.VaccinationOrderControlCodeIsUnrecognized);
    assertNotNull(pi.VaccinationOrderFacilityIdIsDeprecated);
    assertNotNull(pi.VaccinationOrderFacilityIdIsIgnored);
    assertNotNull(pi.VaccinationOrderFacilityIdIsInvalid);
    assertNotNull(pi.VaccinationOrderFacilityIdIsMissing);
    assertNotNull(pi.VaccinationOrderFacilityIdIsUnrecognized);
    assertNotNull(pi.VaccinationOrderFacilityNameIsMissing);
    assertNotNull(pi.VaccinationOrderedByIsDeprecated);
    assertNotNull(pi.VaccinationOrderedByIsIgnored);
    assertNotNull(pi.VaccinationOrderedByIsInvalid);
    assertNotNull(pi.VaccinationOrderedByIsMissing);
    assertNotNull(pi.VaccinationOrderedByIsUnrecognized);
    assertNotNull(pi.VaccinationPlacerOrderNumberIsDeprecated);
    assertNotNull(pi.VaccinationPlacerOrderNumberIsIgnored);
    assertNotNull(pi.VaccinationPlacerOrderNumberIsInvalid);
    assertNotNull(pi.VaccinationPlacerOrderNumberIsMissing);
    assertNotNull(pi.VaccinationPlacerOrderNumberIsUnrecognized);
    assertNotNull(pi.VaccinationProductIsDeprecated);
    assertNotNull(pi.VaccinationProductIsInvalid);
    assertNotNull(pi.VaccinationProductIsMissing);
    assertNotNull(pi.VaccinationProductIsUnrecognized);
    assertNotNull(pi.VaccinationRecordedByIsDeprecated);
    assertNotNull(pi.VaccinationRecordedByIsIgnored);
    assertNotNull(pi.VaccinationRecordedByIsInvalid);
    assertNotNull(pi.VaccinationRecordedByIsMissing);
    assertNotNull(pi.VaccinationRecordedByIsUnrecognized);
    assertNotNull(pi.VaccinationRefusalReasonConflictsCompletionStatus);
    assertNotNull(pi.VaccinationRefusalReasonIsDeprecated);
    assertNotNull(pi.VaccinationRefusalReasonIsIgnored);
    assertNotNull(pi.VaccinationRefusalReasonIsInvalid);
    assertNotNull(pi.VaccinationRefusalReasonIsMissing);
    assertNotNull(pi.VaccinationRefusalReasonIsUnrecognized);
    assertNotNull(pi.VaccinationSystemEntryTimeIsInFuture);
    assertNotNull(pi.VaccinationSystemEntryTimeIsInvalid);
    assertNotNull(pi.VaccinationSystemEntryTimeIsMissing);
  }
}
