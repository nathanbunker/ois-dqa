package org.openimmunizationsoftware.dqa.db.model;

public class SubmitStatus
{
  public static final SubmitStatus EXCLUDED = new SubmitStatus("E", "Excluded");
  public static final SubmitStatus QUEUED = new SubmitStatus("Q", "Queued");
  public static final SubmitStatus HOLD = new SubmitStatus("G", "Hold");
  public static final SubmitStatus PREPARED = new SubmitStatus("P", "Prepared");
  public static final SubmitStatus SUBMITTED = new SubmitStatus("S", "Submitted");
  
  public SubmitStatus()
  {
    // default
  }
  
  public SubmitStatus(String submitCode, String submitLabel)
  {
    this.submitCode = submitCode;
    this.submitLabel = submitLabel;
  }
  
  private String submitCode = "";
  private String submitLabel = "";
  
  public String getSubmitCode()
  {
    return submitCode;
  }
  public void setSubmitCode(String submitCode)
  {
    this.submitCode = submitCode;
  }
  public String getSubmitLabel()
  {
    return submitLabel;
  }
  public void setSubmitLabel(String submitLabel)
  {
    this.submitLabel = submitLabel;
  }
}
