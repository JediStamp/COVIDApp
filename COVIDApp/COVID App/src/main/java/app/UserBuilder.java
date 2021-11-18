package app;

import java.util.UUID;

/**
 * FUTURE WORK TO USE BUILDER PATTERN
 * @author jkmik
 *
 */
public class UserBuilder {
	private String firstName;
	private String lastName;
	private String email;
	private String userID;
	private Role userRole; // not set up
	private Boolean verified;
	private String password; // Temporary - not secure
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

	public UserBuilder setUserRole(Role userRole) {
		this.userRole = userRole;
		return this;
	}

	public UserBuilder setVerified(Boolean verified) {
		this.verified = verified;
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
		return new User(firstName, lastName, email, userID, userRole, verified, password, verCode);
	}
}
