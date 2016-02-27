package org.openimmunizationsoftware.dqa.tr.model;

public class ProfileFieldValue
{
  private int profileFieldValueId = 0;
  private ProfileField profileField = null;
  private TestMessage testMessage = null;
  private String fieldValue = "";
  
  public int getProfileFieldValueId()
  {
    return profileFieldValueId;
  }
  public void setProfileFieldValueId(int profileFieldValueId)
  {
    this.profileFieldValueId = profileFieldValueId;
  }
  public ProfileField getProfileField()
  {
    return profileField;
  }
  public void setProfileField(ProfileField profileField)
  {
    this.profileField = profileField;
  }
  public TestMessage getTestMessage()
  {
    return testMessage;
  }
  public void setTestMessage(TestMessage testMessage)
  {
    this.testMessage = testMessage;
  }
  public String getFieldValue()
  {
    return fieldValue;
  }
  public void setFieldValue(String fieldValue)
  {
    this.fieldValue = fieldValue;
  }
}
