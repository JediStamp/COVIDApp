package app;

import java.util.UUID;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String userID;
	private Role userRole; // not set up
	private Boolean verified;
	private String password; // Temporary - not secure
	private String verCode;
	
	public User(String firstName, String lastName, String email, String password) {
		userID = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		verified = false;
	}
	
	public User(String email, String password) {
		userID = UUID.randomUUID().toString();
		this.firstName = "ver";
		this.lastName = "test";
		this.email = email;
		this.password = password;
		verified = true;
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
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
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
