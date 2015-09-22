package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;
import java.util.Date;

public class TestConducted implements Serializable {

  private int testConductedId = 0;
  private String connectionLabel = "";
  private String connectionType = "";
  private String connectionUrl = "";
  private String connectionAckType = "";
  private String connectionConfig = "";
  private String queryType = "";
  private String queryEnabled = "";
  private String queryPause = "";
  private String testLog = "";
  private Date testStartedTime = null;
  private Date testFinishedTime = null;
  private int countUpdate = 0;
  private int countQuery = 0;
  private String profileBaseName = "";
  private String profileCompareName = "";

  public int getTestConductedId() {
    return testConductedId;
  }

  public void setTestConductedId(int testConductedId) {
    this.testConductedId = testConductedId;
  }

  public String getConnectionLabel() {
    return connectionLabel;
  }

  public void setConnectionLabel(String connectionLabel) {
    this.connectionLabel = connectionLabel;
  }

  public String getConnectionType() {
    return connectionType;
  }

  public void setConnectionType(String connectionType) {
    this.connectionType = connectionType;
  }

  public String getConnectionUrl() {
    return connectionUrl;
  }

  public void setConnectionUrl(String connectionUrl) {
    this.connectionUrl = connectionUrl;
  }

  public String getConnectionAckType() {
    return connectionAckType;
  }

  public void setConnectionAckType(String connectionAckType) {
    this.connectionAckType = connectionAckType;
  }

  public String getConnectionConfig() {
    return connectionConfig;
  }

  public void setConnectionConfig(String connectionConfig) {
    this.connectionConfig = connectionConfig;
  }

  public String getQueryType() {
    return queryType;
  }

  public void setQueryType(String queryType) {
    this.queryType = queryType;
  }

  public String getQueryEnabled() {
    return queryEnabled;
  }

  public void setQueryEnabled(String queryEnabled) {
    this.queryEnabled = queryEnabled;
  }

  public String getQueryPause() {
    return queryPause;
  }

  public void setQueryPause(String queryPause) {
    this.queryPause = queryPause;
  }

  public String getTestLog() {
    return testLog;
  }

  public void setTestLog(String testLog) {
    this.testLog = testLog;
  }

  public Date getTestStartedTime() {
    return testStartedTime;
  }

  public void setTestStartedTime(Date testStartedTime) {
    this.testStartedTime = testStartedTime;
  }

  public Date getTestFinishedTime() {
    return testFinishedTime;
  }

  public void setTestFinishedTime(Date testFinishedTime) {
    this.testFinishedTime = testFinishedTime;
  }

  public int getCountUpdate() {
    return countUpdate;
  }

  public void setCountUpdate(int countUpdate) {
    this.countUpdate = countUpdate;
  }

  public int getCountQuery() {
    return countQuery;
  }

  public void setCountQuery(int countQuery) {
    this.countQuery = countQuery;
  }

  public String getProfileBaseName() {
    return profileBaseName;
  }

  public void setProfileBaseName(String profileBaseName) {
    this.profileBaseName = profileBaseName;
  }

  public String getProfileCompareName() {
    return profileCompareName;
  }

  public void setProfileCompareName(String profileCompareName) {
    this.profileCompareName = profileCompareName;
  }
}
