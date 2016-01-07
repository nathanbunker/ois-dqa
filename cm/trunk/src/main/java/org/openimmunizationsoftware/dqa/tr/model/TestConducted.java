package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.ReportUser;
import org.openimmunizationsoftware.dqa.cm.model.UserType;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;

public class TestConducted implements Serializable {

  private int testConductedId = 0;
  private String connectionType = "";
  private String connectionUrl = "";
  private String connectionAckType = "";
  private String connectionConfig = "";
  private boolean latestTest = false;
  private boolean completeTest = false;
  private boolean manualTest = false;
  private String queryType = "";
  private String queryEnabled = "";
  private String queryPause = "";
  private String testLog = "";
  private String testStatus = "";
  private Date testStartedTime = null;
  private Date testFinishedTime = null;
  private int countUpdate = 0;
  private int countQuery = 0;
  private String profileBaseName = "";
  private String profileCompareName = "";
  private int scoreOverall = 0;
  private int scoreInterop = 0;
  private int scoreCoded = 0;
  private int scoreLocal = 0;
  private int scoreNational = 0;
  private int scoreTolerance = 0;
  private int scoreEhr = 0;
  private int scorePerform = 0;
  private int scoreAck = 0;
  private int perQueryTotal = 0;
  private int perQueryCount = 0;
  private int perQueryMin = 0;
  private int perQueryMax = 0;
  private float perQueryStd = 0.0f;
  private int perUpdateTotal = 0;
  private int perUpdateCount = 0;
  private int perUpdateMin = 0;
  private int perUpdateMax = 0;
  private float perUpdateStd = 0.0f;
  private TestParticipant testParticipant = null; 

  public boolean isManualTest()
  {
    return manualTest;
  }

  public void setManualTest(boolean manualTest)
  {
    this.manualTest = manualTest;
  }

  public void setTestParticipant(TestParticipant testParticipant)
  {
    this.testParticipant = testParticipant;
  }

  public TestParticipant getTestParticipant()
  {
    return testParticipant;
  }

  public boolean isCompleteTest() {
    return completeTest;
  }

  public void setCompleteTest(boolean completeTest) {
    this.completeTest = completeTest;
  }

  public boolean isLatestTest() {
    return latestTest;
  }

  public void setLatestTest(boolean latestTest) {
    this.latestTest = latestTest;
  }

  public String getTestStatus() {
    return testStatus;
  }

  public void setTestStatus(String testStatus) {
    this.testStatus = testStatus;
  }

  public int getScoreOverall() {
    return scoreOverall;
  }

  public void setScoreOverall(int scoreOverall) {
    this.scoreOverall = scoreOverall;
  }

  public int getScoreInterop() {
    return scoreInterop;
  }

  public void setScoreInterop(int scoreInterop) {
    this.scoreInterop = scoreInterop;
  }

  public int getScoreCoded() {
    return scoreCoded;
  }

  public void setScoreCoded(int scoreCoded) {
    this.scoreCoded = scoreCoded;
  }

  public int getScoreLocal() {
    return scoreLocal;
  }

  public void setScoreLocal(int scoreLocal) {
    this.scoreLocal = scoreLocal;
  }

  public int getScoreNational() {
    return scoreNational;
  }

  public void setScoreNational(int scoreNational) {
    this.scoreNational = scoreNational;
  }

  public int getScoreTolerance() {
    return scoreTolerance;
  }

  public void setScoreTolerance(int scoreTolerance) {
    this.scoreTolerance = scoreTolerance;
  }

  public int getScoreEhr() {
    return scoreEhr;
  }

  public void setScoreEhr(int scoreEhr) {
    this.scoreEhr = scoreEhr;
  }

  public int getScorePerform() {
    return scorePerform;
  }

  public void setScorePerform(int scorePerform) {
    this.scorePerform = scorePerform;
  }

  public int getScoreAck() {
    return scoreAck;
  }

  public void setScoreAck(int scoreAck) {
    this.scoreAck = scoreAck;
  }

  public int getPerQueryTotal() {
    return perQueryTotal;
  }

  public void setPerQueryTotal(int perQueryTotal) {
    this.perQueryTotal = perQueryTotal;
  }

  public int getPerQueryCount() {
    return perQueryCount;
  }

  public void setPerQueryCount(int perQueryCount) {
    this.perQueryCount = perQueryCount;
  }

  public int getPerQueryMin() {
    return perQueryMin;
  }

  public void setPerQueryMin(int perQueryMin) {
    this.perQueryMin = perQueryMin;
  }

  public int getPerQueryMax() {
    return perQueryMax;
  }

  public void setPerQueryMax(int perQueryMax) {
    this.perQueryMax = perQueryMax;
  }

  public float getPerQueryStd() {
    return perQueryStd;
  }

  public void setPerQueryStd(float perQueryStd) {
    this.perQueryStd = perQueryStd;
  }

  public int getPerUpdateTotal() {
    return perUpdateTotal;
  }

  public void setPerUpdateTotal(int perUpdateTotal) {
    this.perUpdateTotal = perUpdateTotal;
  }

  public int getPerUpdateCount() {
    return perUpdateCount;
  }

  public void setPerUpdateCount(int perUpdateCount) {
    this.perUpdateCount = perUpdateCount;
  }

  public int getPerUpdateMin() {
    return perUpdateMin;
  }

  public void setPerUpdateMin(int perUpdateMin) {
    this.perUpdateMin = perUpdateMin;
  }

  public int getPerUpdateMax() {
    return perUpdateMax;
  }

  public void setPerUpdateMax(int perUpdateMax) {
    this.perUpdateMax = perUpdateMax;
  }

  public float getPerUpdateStd() {
    return perUpdateStd;
  }

  public void setPerUpdateStd(float perUpdateStd) {
    this.perUpdateStd = perUpdateStd;
  }

  public int getTestConductedId() {
    return testConductedId;
  }

  public void setTestConductedId(int testConductedId) {
    this.testConductedId = testConductedId;
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

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TestConducted) {
      return this.getTestConductedId() == ((TestConducted) obj).getTestConductedId();
    }
    return super.equals(obj);
  }
}
