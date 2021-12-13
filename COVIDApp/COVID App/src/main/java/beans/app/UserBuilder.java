package beans.app;

import beans.userRoles.UserRole;

public class UserBuilder {
	private String firstName;
	private String lastName;
	private String email;
	private String userID;
	private UserRole userRole;
	private int teamID;
	private Boolean verified;
	private String password; 
	private String verCode;
	
	public UserBuilder() {
		
	}

	public UserBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public UserBuilder setUserRole(UserRole userRole) {
		this.userRole = userRole;
		return this;
	}
	
	public UserBuilder setTeamID(int teamID) {
		this.teamID = teamID;
		return this;
	}

	public UserBuilder setVerified(Boolean verified) {
		this.verified = verified;
		return this;
	}
	
	//overload setVerified to take int from dB
	public UserBuilder setVerified(int verified) {
		if (verified == 1) {
			this.setVerified(true);	
		}else {
			this.setVerified(false);
		}
		return this;
	}

	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder setVerCode(String verCode) {
		this.verCode = verCode;
		return this;
	}
	
	public User createUser() {
		return new User(firstName, lastName, email, userID, userRole, teamID, verified, password, verCode);
	}
}
