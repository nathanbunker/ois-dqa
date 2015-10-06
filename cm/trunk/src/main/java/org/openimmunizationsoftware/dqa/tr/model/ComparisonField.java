package org.openimmunizationsoftware.dqa.tr.model;

public class ComparisonField {
  private int comparisonFieldId = 0;
  private String fieldName = "";
  private String fieldLabel = "";
  private String priorityLabel = "";

  public int getComparisonFieldId() {
    return comparisonFieldId;
  }
  public void setComparisonFieldId(int comparisonFieldId) {
    this.comparisonFieldId = comparisonFieldId;
  }
  public String getFieldName() {
    return fieldName;
  }
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
  public String getFieldLabel() {
    return fieldLabel;
  }
  public void setFieldLabel(String fieldLabel) {
    this.fieldLabel = fieldLabel;
  }
  public String getPriorityLabel() {
    return priorityLabel;
  }
  public void setPriorityLabel(String priorityLabel) {
    this.priorityLabel = priorityLabel;
  }
}
