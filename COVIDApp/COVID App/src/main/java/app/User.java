package app;

import java.util.UUID;

import userRoles.Member;
import userRoles.UserRole;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String userID;
	private UserRole currRole;
	private Boolean verified;
	private String password; // actually pwd hash
	private String verCode;
	private int teamID;
	
	// Builder Constructor
	public User(String firstName, String lastName, String email, String userID, UserRole currRole, int teamID, Boolean verified,
			String password, String verCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userID = userID;
		
    	if (userID == null) {
    		this.userID = UUID.randomUUID().toString();
    	}
    	
		this.currRole = currRole;
		if (currRole == null) {
			this.currRole = new Member();
		}
		
		this.teamID = teamID;
		if (teamID == 0) {
			this.teamID = 1; 
		}
		
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
		return currRole;
	}
	public void setUserRole(UserRole userRole) {
		this.currRole = userRole;
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

	public int getTeamID() {
		return teamID;
	}

	public void setCurrTeam(int teamID) {
		this.teamID = teamID;
	}
}
