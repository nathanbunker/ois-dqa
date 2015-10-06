package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class Comparison implements Serializable {
  private int comparisonId = 0;
  private ComparisonField comparisonField = null;
  private TestMessage testMessage = null;
  private String valueOriginal = "";
  private String valueCompare = "";
  private String comparisonStatus = "";

  public int getComparisonId() {
    return comparisonId;
  }

  public void setComparisonId(int comparisonId) {
    this.comparisonId = comparisonId;
  }

  public ComparisonField getComparisonField() {
    return comparisonField;
  }

  public void setComparisonField(ComparisonField comparisonField) {
    this.comparisonField = comparisonField;
  }

  public TestMessage getTestMessage() {
    return testMessage;
  }

  public void setTestMessage(TestMessage testMessage) {
    this.testMessage = testMessage;
  }

  public String getValueOriginal() {
    return valueOriginal;
  }

  public void setValueOriginal(String valueOriginal) {
    this.valueOriginal = valueOriginal;
  }

  public String getValueCompare() {
    return valueCompare;
  }

  public void setValueCompare(String valueCompare) {
    this.valueCompare = valueCompare;
  }

  public String getComparisonStatus() {
    return comparisonStatus;
  }

  public void setComparisonStatus(String comparisonStatus) {
    this.comparisonStatus = comparisonStatus;
  }
}
