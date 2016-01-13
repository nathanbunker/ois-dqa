package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;

public class TestMessage implements Serializable
{
  private int testMessageId = 0;
  private TestSection testSection = null;
  private int testPosition = 0;
  private String testType = "";
  private String testCaseSet = "";
  private String testCaseNumber = "";
  private String testCaseCategory = "";
  private String testCaseDescription = "";
  private String testCaseAssertResult = "";
  private String prepPatientType = "";
  private String prepTransformsQuick = "";
  private String prepTransformsCustom = "";
  private String prepTransformsAddition = "";
  private String prepTransformsCauseIssue = "";
  private String prepCauseIssueNames = "";
  private String prepHasIssue = "";
  private String prepMajorChagnesMade = "";
  private String prepNotExpectedToConform = "";
  private String prepMessageAcceptStatusDebug = "";
  private String prepScenarioName = "";
  private String prepMessageDerivedFrom = "";
  private String prepMessageOriginal = "";
  private String prepMessageOriginalResponse = "";
  private String prepMessageActual = "";
  private String resultMessageActual = "";
  private String resultStatus = "";
  private boolean resultAccepted = false;
  private String resultExceptionText = "";
  private String resultAcceptedMessage = "";
  private String resultResponseType = "";
  private String resultAckType = "";
  private String resultAckConformance = "";
  private String resultQueryType = "";
  private String resultStoreStatus = "";
  private String resultForecastStatus = "";
  private boolean resultManualTest = false;
  private int forecastTestPanelId = 0;
  private int forecastTestPanelCaseId = 0;

  public boolean isResultManualTest()
  {
    return resultManualTest;
  }

  public void setResultManualTest(boolean resultManualTest)
  {
    this.resultManualTest = resultManualTest;
  }

  public String getResultForecastStatus()
  {
    return resultForecastStatus;
  }

  public void setResultForecastStatus(String resultForecastStatus)
  {
    this.resultForecastStatus = resultForecastStatus;
  }

  public String getResultQueryType()
  {
    return resultQueryType;
  }

  public void setResultQueryType(String resultQueryType)
  {
    this.resultQueryType = resultQueryType;
  }

  public String getPrepMessageOriginalResponse()
  {
    return prepMessageOriginalResponse;
  }

  public void setPrepMessageOriginalResponse(String prepMessageOriginalResponse)
  {
    this.prepMessageOriginalResponse = prepMessageOriginalResponse;
  }

  public String getResultStoreStatus()
  {
    return resultStoreStatus;
  }

  public String getResultStoreStatusForDisplay()
  {
    return getResultStoreStatusForDisplay(resultStoreStatus);
  }

  public static String getResultStoreStatusForDisplay(String resultStoreStatus)
  {
    if (resultStoreStatus == null)
    {
      return "";
    }
    if (resultStoreStatus.equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_NOT_RETURNED))
    {
      return "Accepted but Not Returned";
    }
    if (resultStoreStatus.equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED))
    {
      return "Accepted and Returned";
    }
    if (resultStoreStatus.equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_NOT_ACCEPTED_RETURNED))
    {
      return "Not Accepted but Returned";
    }
    if (resultStoreStatus.equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_NOT_ACCEPTED_NOT_RETURNED))
    {
      return "Not Accepted and Not Returned";
    }
    return resultStoreStatus;
  }

  public void setResultStoreStatus(String resultStoreStatus)
  {
    this.resultStoreStatus = resultStoreStatus;
  }

  public String getResultAckConformance()
  {
    return resultAckConformance;
  }

  public void setResultAckConformance(String resultAckConformance)
  {
    this.resultAckConformance = resultAckConformance;
  }

  public boolean isResultAccepted()
  {
    return resultAccepted;
  }

  public void setResultAccepted(boolean resultAccepted)
  {
    this.resultAccepted = resultAccepted;
  }

  public int getTestMessageId()
  {
    return testMessageId;
  }

  public void setTestMessageId(int testMessageId)
  {
    this.testMessageId = testMessageId;
  }

  public TestSection getTestSection()
  {
    return testSection;
  }

  public void setTestSection(TestSection testSection)
  {
    this.testSection = testSection;
  }

  public int getTestPosition()
  {
    return testPosition;
  }

  public void setTestPosition(int testPosition)
  {
    this.testPosition = testPosition;
  }

  public String getTestType()
  {
    return testType;
  }

  public void setTestType(String testType)
  {
    this.testType = testType;
  }

  public String getTestCaseSet()
  {
    return testCaseSet;
  }

  public void setTestCaseSet(String testCaseSet)
  {
    this.testCaseSet = testCaseSet;
  }

  public String getTestCaseNumber()
  {
    return testCaseNumber;
  }

  public void setTestCaseNumber(String testCaseNumber)
  {
    this.testCaseNumber = testCaseNumber;
  }

  public String getTestCaseCategory()
  {
    return testCaseCategory;
  }

  public void setTestCaseCategory(String testCaseCategory)
  {
    this.testCaseCategory = testCaseCategory;
  }

  public String getTestCaseDescription()
  {
    return testCaseDescription;
  }

  public void setTestCaseDescription(String testCaseDescription)
  {
    this.testCaseDescription = testCaseDescription;
  }

  public String getTestCaseAssertResult()
  {
    return testCaseAssertResult;
  }

  public void setTestCaseAssertResult(String testCaseAssertResult)
  {
    this.testCaseAssertResult = testCaseAssertResult;
  }

  public String getPrepPatientType()
  {
    return prepPatientType;
  }

  public void setPrepPatientType(String prepPatientType)
  {
    this.prepPatientType = prepPatientType;
  }

  public String getPrepTransformsQuick()
  {
    return prepTransformsQuick;
  }

  public void setPrepTransformsQuick(String prepTransformsQuick)
  {
    this.prepTransformsQuick = prepTransformsQuick;
  }

  public String getPrepTransformsCustom()
  {
    return prepTransformsCustom;
  }

  public void setPrepTransformsCustom(String prepTransformsCustom)
  {
    this.prepTransformsCustom = prepTransformsCustom;
  }

  public String getPrepTransformsAddition()
  {
    return prepTransformsAddition;
  }

  public void setPrepTransformsAddition(String prepTransformsAddition)
  {
    this.prepTransformsAddition = prepTransformsAddition;
  }

  public String getPrepTransformsCauseIssue()
  {
    return prepTransformsCauseIssue;
  }

  public void setPrepTransformsCauseIssue(String prepTransformsCauseIssue)
  {
    this.prepTransformsCauseIssue = prepTransformsCauseIssue;
  }

  public String getPrepCauseIssueNames()
  {
    return prepCauseIssueNames;
  }

  public void setPrepCauseIssueNames(String prepCauseIssueNames)
  {
    this.prepCauseIssueNames = prepCauseIssueNames;
  }

  public String getPrepHasIssue()
  {
    return prepHasIssue;
  }

  public void setPrepHasIssue(String prepHasIssue)
  {
    this.prepHasIssue = prepHasIssue;
  }

  public String getPrepMajorChagnesMade()
  {
    return prepMajorChagnesMade;
  }

  public void setPrepMajorChagnesMade(String prepMajorChagnesMade)
  {
    this.prepMajorChagnesMade = prepMajorChagnesMade;
  }

  public String getPrepNotExpectedToConform()
  {
    return prepNotExpectedToConform;
  }

  public void setPrepNotExpectedToConform(String prepNotExpectedToConform)
  {
    this.prepNotExpectedToConform = prepNotExpectedToConform;
  }

  public String getPrepMessageAcceptStatusDebug()
  {
    return prepMessageAcceptStatusDebug;
  }

  public void setPrepMessageAcceptStatusDebug(String prepMessageAcceptStatusDebug)
  {
    this.prepMessageAcceptStatusDebug = prepMessageAcceptStatusDebug;
  }

  public String getPrepScenarioName()
  {
    return prepScenarioName;
  }

  public void setPrepScenarioName(String prepScenarioName)
  {
    this.prepScenarioName = prepScenarioName;
  }

  public String getPrepMessageDerivedFrom()
  {
    return prepMessageDerivedFrom;
  }

  public void setPrepMessageDerivedFrom(String prepMessageDerivedFrom)
  {
    this.prepMessageDerivedFrom = prepMessageDerivedFrom;
  }

  public String getPrepMessageOriginal()
  {
    return prepMessageOriginal;
  }

  public void setPrepMessageOriginal(String prepMessageOriginal)
  {
    this.prepMessageOriginal = prepMessageOriginal;
  }

  public String getPrepMessageActual()
  {
    return prepMessageActual;
  }

  public void setPrepMessageActual(String prepMessageActual)
  {
    this.prepMessageActual = prepMessageActual;
  }

  public String getResultMessageActual()
  {
    return resultMessageActual;
  }

  public void setResultMessageActual(String resultMessageActual)
  {
    this.resultMessageActual = resultMessageActual;
  }

  public String getResultStatus()
  {
    return resultStatus;
  }

  public void setResultStatus(String resultStatus)
  {
    this.resultStatus = resultStatus;
  }

  public String getResultExceptionText()
  {
    return resultExceptionText;
  }

  public void setResultExceptionText(String resultExceptionText)
  {
    this.resultExceptionText = resultExceptionText;
  }

  public String getResultAcceptedMessage()
  {
    return resultAcceptedMessage;
  }

  public void setResultAcceptedMessage(String resultAcceptedMessage)
  {
    this.resultAcceptedMessage = resultAcceptedMessage;
  }

  public String getResultResponseType()
  {
    return resultResponseType;
  }

  public void setResultResponseType(String resultResponseType)
  {
    this.resultResponseType = resultResponseType;
  }

  public String getResultAckType()
  {
    return resultAckType;
  }

  public void setResultAckType(String resultAckType)
  {
    this.resultAckType = resultAckType;
  }

  public int getForecastTestPanelCaseId()
  {
    return forecastTestPanelCaseId;
  }

  public void setForecastTestPanelCaseId(int forecastTestPanelCaseId)
  {
    this.forecastTestPanelCaseId = forecastTestPanelCaseId;
  }

  public int getForecastTestPanelId()
  {
    return forecastTestPanelId;
  }

  public void setForecastTestPanelId(int forecastTestPanelId)
  {
    this.forecastTestPanelId = forecastTestPanelId;
  }

}
