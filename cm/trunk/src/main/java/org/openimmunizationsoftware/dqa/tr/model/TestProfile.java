package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class TestProfile implements Serializable {
  private int testProfileId = 0;
  private TestSection testSection = null;
  private String testProfileStatus = "";
  private int profileFieldPos = 0;
  private String profileFieldName = "";
  private String usageExpected = "";
  private String usageDetected = "";
  private String acceptExpected = "";
  private String acceptDetected = "";
  private TestMessage testMessagePresent = null;
  private TestMessage testMessageAbsent = null;
  private String messageAcceptStatusDebug = "";
  private String compatibilityConformance = "";

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

  public int getProfileFieldPos() {
    return profileFieldPos;
  }

  public void setProfileFieldPos(int profileFieldPos) {
    this.profileFieldPos = profileFieldPos;
  }

  public String getProfileFieldName() {
    return profileFieldName;
  }

  public void setProfileFieldName(String profileFieldName) {
    this.profileFieldName = profileFieldName;
  }

  public String getUsageExpected() {
    return usageExpected;
  }

  public void setUsageExpected(String usageExpected) {
    this.usageExpected = usageExpected;
  }

  public String getUsageDetected() {
    return usageDetected;
  }

  public void setUsageDetected(String usageDetected) {
    this.usageDetected = usageDetected;
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
