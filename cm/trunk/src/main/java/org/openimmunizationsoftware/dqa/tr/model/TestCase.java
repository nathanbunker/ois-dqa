package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;

public class TestCase implements Serializable
{
  private int testCaseId = 0;
  private TestParticipant testParticipant = null;
  private TestComment authorComment = null;
  private TestComment revewiedComment = null;
  private String testCaseContent = "";
  private String testSectionType = "";
  private TestCaseUseStatus useStatus = null;

  public int getTestCaseId()
  {
    return testCaseId;
  }

  public void setTestCaseId(int testCaseId)
  {
    this.testCaseId = testCaseId;
  }

  public TestParticipant getTestParticipant()
  {
    return testParticipant;
  }

  public void setTestParticipant(TestParticipant testParticipant)
  {
    this.testParticipant = testParticipant;
  }

  public TestComment getAuthorComment()
  {
    return authorComment;
  }

  public void setAuthorComment(TestComment authorComment)
  {
    this.authorComment = authorComment;
  }

  public TestComment getRevewiedComment()
  {
    return revewiedComment;
  }

  public void setRevewiedComment(TestComment revewiedComment)
  {
    this.revewiedComment = revewiedComment;
  }

  public String getTestCaseContent()
  {
    return testCaseContent;
  }

  public void setTestCaseContent(String testCaseContent)
  {
    this.testCaseContent = testCaseContent;
  }

  public String getTestSectionType()
  {
    return testSectionType;
  }

  public void setTestSectionType(String testSectionType)
  {
    this.testSectionType = testSectionType;
  }

  public TestCaseUseStatus getUseStatus()
  {
    return useStatus;
  }

  public void setUseStatus(TestCaseUseStatus useStatus)
  {
    this.useStatus = useStatus;
  }

  public String getUseStatusString()
  {
    return useStatus == null ? "" : useStatus.toString();
  }

  public void setUseStatusString(String useStatusString)
  {
    if (useStatusString == null || useStatusString.equals(""))
    {
      this.useStatus = null;
    } else
    {
      this.useStatus = TestCaseUseStatus.valueOf(useStatusString);
    }
  }
}
