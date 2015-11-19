package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

import org.openimmunizationsoftware.dqa.tr.profile.Usage;

public class TestProfile implements Serializable {
  
  public static final String TEST_PROFILE_STATUS_NOT_RUN = "Not Run";
  public static final String TEST_PROFILE_STATUS_EXPECTED = "Expected";
  public static final String TEST_PROFILE_STATUS_NOT_EXPECTED = "Not Expected";
  
  private int testProfileId = 0;
  private TestSection testSection = null;
  private String testProfileStatus = "";
  private ProfileField profileField = null;
  private ProfileUsageValue profileUsageValue = null;
  private Usage usageExpected = Usage.NOT_DEFINED;
  private Usage usageDetected = Usage.NOT_DEFINED;
  private String acceptExpected = "";
  private String acceptDetected = "";
  private TestMessage testMessagePresent = null;
  private TestMessage testMessageAbsent = null;
  private String messageAcceptStatusDebug = "";
  private String compatibilityConformance = "";

  public Usage getUsageExpected()
  {
    return usageExpected;
  }

  public void setUsageExpected(Usage usageExpected)
  {
    this.usageExpected = usageExpected;
  }

  public Usage getUsageDetected()
  {
    return usageDetected;
  }

  public void setUsageDetected(Usage usageDetected)
  {
    this.usageDetected = usageDetected;
  }

  public String getUsageExpectedString()
  {
    return usageExpected == null ? "" : usageExpected.toString();
  }

  public void setUsageExpectedString(String usageExpectedString)
  {
    this.usageExpected = Usage.readUsage(usageExpectedString);
  }

  public String getUsageDetectedString()
  {
    return usageDetected == null ? "" : usageDetected.toString();
  }

  public void setUsageDetectedString(String usageDetectedString)
  {
    this.usageDetected = Usage.readUsage(usageDetectedString);
  }

  public ProfileField getProfileField()
  {
    return profileField;
  }

  public void setProfileField(ProfileField profileField)
  {
    this.profileField = profileField;
  }

  public ProfileUsageValue getProfileUsageValue()
  {
    return profileUsageValue;
  }

  public void setProfileUsageValue(ProfileUsageValue profileUsageValue)
  {
    this.profileUsageValue = profileUsageValue;
  }

  public int getTestProfileId() {
    return testProfileId;
  }

  public void setTestProfileId(int testProfileId) {
    this.testProfileId = testProfileId;
  }

  public TestSection getTestSection() {
    return testSection;
  }

  public void setTestSection(TestSection testSection) {
    this.testSection = testSection;
  }

  public String getTestProfileStatus() {
    return testProfileStatus;
  }

  public void setTestProfileStatus(String testProfileStatus) {
    this.testProfileStatus = testProfileStatus;
  }


  public String getAcceptExpected() {
    return acceptExpected;
  }

  public void setAcceptExpected(String acceptExpected) {
    this.acceptExpected = acceptExpected;
  }

  public String getAcceptDetected() {
    return acceptDetected;
  }

  public void setAcceptDetected(String acceptDetected) {
    this.acceptDetected = acceptDetected;
  }

  public TestMessage getTestMessagePresent() {
    return testMessagePresent;
  }

  public void setTestMessagePresent(TestMessage testMessagePresent) {
    this.testMessagePresent = testMessagePresent;
  }

  public TestMessage getTestMessageAbsent() {
    return testMessageAbsent;
  }

  public void setTestMessageAbsent(TestMessage testMessageAbsent) {
    this.testMessageAbsent = testMessageAbsent;
  }

  public String getMessageAcceptStatusDebug() {
    return messageAcceptStatusDebug;
  }

  public void setMessageAcceptStatusDebug(String messageAcceptStatusDebug) {
    this.messageAcceptStatusDebug = messageAcceptStatusDebug;
  }

  public String getCompatibilityConformance() {
    return compatibilityConformance;
  }

  public void setCompatibilityConformance(String compatibilityConformance) {
    this.compatibilityConformance = compatibilityConformance;
  }
}
