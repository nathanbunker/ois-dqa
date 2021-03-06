package org.openimmunizationsoftware.dqa.cm.model;

public class Application {
	private int applicationId = 0;
	private String applicationLabel = "";
	private String applicationAcronym = "";
	private Agreement agreement = null;
	
	public Agreement getAgreement()
  {
    return agreement;
  }

  public void setAgreement(Agreement agreement)
  {
    this.agreement = agreement;
  }

  private static final int APPLICATION_DQACM = 1;
	private static final int APPLICATION_DQAIS = 2;
	
	public boolean isApplicationDqacm()
	{
	  return applicationId == APPLICATION_DQACM;
	}

    public boolean isApplicationAart()
    {
      return applicationId == APPLICATION_DQAIS;
    }

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationLabel() {
		return applicationLabel;
	}

	public void setApplicationLabel(String applicationLabel) {
		this.applicationLabel = applicationLabel;
	}

	public String getApplicationAcronym() {
		return applicationAcronym;
	}

	public void setApplicationAcronym(String applicationAcronym) {
		this.applicationAcronym = applicationAcronym;
	}
	
	@Override
	public boolean equals(Object obj)
	{
	  if (obj instanceof Application)
	  {
	    Application app = (Application) obj;
	    return this.getApplicationId() == app.getApplicationId();
	  }
	  return super.equals(obj);
	}
	
	@Override
	public int hashCode()
	{
	  return getApplicationId();
	}
}
