package app;

import java.util.UUID;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String userID;
	private UserRole userRole; // not set up
	private Boolean verified;
	private String password; // actually pwd hash
	private String verCode;
	
	// Builder Constructor
	public User(String firstName, String lastName, String email, String userID, UserRole userRole, Boolean verified,
			String password, String verCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userID = userID;
    	if (userID == null) {
    		this.userID = UUID.randomUUID().toString();
    	}
    	
		this.userRole = userRole;
		this.verified = verified;
		this.password = password;
		this.verCode = verCode;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
}
