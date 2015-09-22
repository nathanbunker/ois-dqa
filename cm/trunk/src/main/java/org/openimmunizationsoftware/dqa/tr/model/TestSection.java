package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class TestSection implements Serializable {
  private int testSectionId = 0;
  private TestConducted testConducted = null;
  private String testSectionType = "";
  private boolean testEnabled = false;
  private int scoreLevel1 = 0;
  private int scoreLevel2 = 0;
  private int scoreLevel3 = 0;
  private int progressLevel1 = 0;
  private int progressLevel2 = 0;
  private int progressLevel3 = 0;
  private int countLevel1 = 0;
  private int countLevel2 = 0;
  private int countLevel3 = 0;

  public int getTestSectionId() {
    return testSectionId;
  }

  public void setTestSectionId(int testSectionId) {
    this.testSectionId = testSectionId;
  }

  public TestConducted getTestConducted() {
    return testConducted;
  }

  public void setTestConducted(TestConducted testConducted) {
    this.testConducted = testConducted;
  }

  public String getTestSectionType() {
    return testSectionType;
  }

  public void setTestSectionType(String testSectionType) {
    this.testSectionType = testSectionType;
  }

  public boolean isTestEnabled() {
    return testEnabled;
  }

  public void setTestEnabled(boolean testEnabled) {
    this.testEnabled = testEnabled;
  }

  public int getScoreLevel1() {
    return scoreLevel1;
  }

  public void setScoreLevel1(int scoreLevel1) {
    this.scoreLevel1 = scoreLevel1;
  }

  public int getScoreLevel2() {
    return scoreLevel2;
  }

  public void setScoreLevel2(int scoreLevel2) {
    this.scoreLevel2 = scoreLevel2;
  }

  public int getScoreLevel3() {
    return scoreLevel3;
  }

  public void setScoreLevel3(int scoreLevel3) {
    this.scoreLevel3 = scoreLevel3;
  }

  public int getProgressLevel1() {
    return progressLevel1;
  }

  public void setProgressLevel1(int progressLevel1) {
    this.progressLevel1 = progressLevel1;
  }

  public int getProgressLevel2() {
    return progressLevel2;
  }

  public void setProgressLevel2(int progressLevel2) {
    this.progressLevel2 = progressLevel2;
  }

  public int getProgressLevel3() {
    return progressLevel3;
  }

  public void setProgressLevel3(int progressLevel3) {
    this.progressLevel3 = progressLevel3;
  }

  public int getCountLevel1() {
    return countLevel1;
  }

  public void setCountLevel1(int countLevel1) {
    this.countLevel1 = countLevel1;
  }

  public int getCountLevel2() {
    return countLevel2;
  }

  public void setCountLevel2(int countLevel2) {
    this.countLevel2 = countLevel2;
  }

  public int getCountLevel3() {
    return countLevel3;
  }

  public void setCountLevel3(int countlevel3) {
    this.countLevel3 = countlevel3;
  }
}
