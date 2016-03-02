package org.openimmunizationsoftware.dqa.cm.model;

import java.util.Date;

public class ApplicationUser {
	private int applicationUserId = 0;
	private Application application = null;
	private User user = null;
	private UserType userType = null;
	private Agreement agreement = null;
	private Date agreementDate = null;
	private String agreementSignature = "";

	public String getAgreementSignature()
  {
    return agreementSignature;
  }

  public void setAgreementSignature(String agreementSignature)
  {
    this.agreementSignature = agreementSignature;
  }

  public Agreement getAgreement()
  {
    return agreement;
  }

  public void setAgreement(Agreement agreement)
  {
    this.agreement = agreement;
  }

  public Date getAgreementDate()
  {
    return agreementDate;
  }

  public void setAgreementDate(Date agreementDate)
  {
    this.agreementDate = agreementDate;
  }

  public int getApplicationUserId() {
		return applicationUserId;
	}

	public void setApplicationUserId(int applicationUserId) {
		this.applicationUserId = applicationUserId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUserTypeString() {
		if (userType == null) {
			return null;
		}
		return userType.getId();
	}

	public void setUserTypeString(String userTypeString) {
		this.userType = UserType.get(userTypeString);
	}

}
