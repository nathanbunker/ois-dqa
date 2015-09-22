package org.openimmunizationsoftware.dqa.cm.model;

public class ApplicationUser {
	private int applicationUserId = 0;
	private Application application = null;
	private User user = null;
	private UserType userType = null;

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
