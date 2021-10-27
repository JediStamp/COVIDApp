package login;

import app.User;

public class LoginController {
	
	public static String register(User user) {
		// USER NOT REGISTERED -> send verification email, take to Verify page
		if(!isRegistered(user)) {
			//Send user to verification page
			System.out.println("user is not registered, taking you to verification page, please check your email...");
			return "http://localhost:8080/COVID_App/html/verify.html";
		}	
		
		// USER ALREADY REGISTERED !VERIFIED -> send verification email, take to Verify page
		if(!isVerified(user)) {
			//TODO: if user exists && ! verified verification page and send email with not verified error
			System.out.println("user is already registered, but not verified taking you to verification page, please check your email...");
			return "http://localhost:8080/COVID_App/html/verify.html";
		}
		
		//USER REGISTERED & VERIFIED, PASSWORD WRONG -> take to login page
		if(!checkPwd(user)) {
			// CASE: user exists && is verified && pwd ! matches -> LOGIN page with pwd doesn't match error
			System.out.println("user is already registered, verified, and pwd is wrong, taking you to login page...");
			return "http://localhost:8080/COVID_App/html/login.html";
			//TODO: if user exists && is verified && pwd ! matches LOGIN page with pwd doesn't match error
			// Want to add error message on top of page
		}
		
		//USER REGISTERED, VERIFIED, PWD matches -> take to profile page
		//TODO: correctly check pwd	
		System.out.println("user is already registered, verified, and pwd is correct, logging in...");
		return "http://localhost:8080/COVID_App/html/profile.html";
	}
	
	public static Boolean verify(User user) {
		return false;
		//TODO: check user verification code entered against code stored
	}
	
	public static String login(User user) {
		// USER NOT REGISTERED -> send verification email, take to Verify page
		if(!isRegistered(user)) {
			//Send user to verification page
			System.out.println("user is not registered, taking you to registration page, please check your email...");
			return "http://localhost:8080/COVID_App/html/index.html";
		}
		
		// USER REGISTERED PASSWORD WRONG -> stay, add an error to retry
		if(!checkPwd(user)) {
			// CASE: user exists && pwd ! matches -> LOGIN page with pwd doesn't match error
			System.out.println("user is already registered  but pwd is wrong, taking you to login page...");
			return "http://localhost:8080/COVID_App/html/login.html";
			//TODO: if user exists && is verified && pwd ! matches LOGIN page with pwd doesn't match error
			// Want to add error message on top of page
		}
		
		// USER ALREADY REGISTERED !VERIFIED -> send verification email, take to Verify page
		if(!isVerified(user)) {
			//TODO: if user exists && ! verified verification page and send email with not verified error
			System.out.println("user is not verified taking you to verification page, please check your email...");
			return "http://localhost:8080/COVID_App/html/verify.html";
		}
		
		//USER REGISTERED, VERIFIED, PWD matches -> take to profile page
		//TODO: correctly check pwd	
		System.out.println("user is registered, verified, and pwd is correct, logging in...");
		return "http://localhost:8080/COVID_App/html/profile.html";
	}
	
	public static void changePwd() {
		System.out.println("Changing password...");
	}
	
	public static Boolean isRegistered(User user) {
		//TODO: This should check if user is in database
		if (user.getEmail().equals("user@email.com")){
			System.out.println("User already exists in db");
			return true;
		}else
			System.out.println("adding user to db");
		//TODO: This should add user to database
		//TODO: Send verification email
		//TODO: Store verification code somewhere
		return false;
	}
	
	public static Boolean isVerified(User user) {
		//TODO: remove or ver when user verification implemented
		if(user.getVerified() || user.getFirstName().equals("ver")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Boolean checkPwd(User user) {
		//TODO: Proper password check implementation
		if(user.getPassword().equals("pwd")) {
			return true;
		}else {
			return false;
		}
	}
	
}
	
	



