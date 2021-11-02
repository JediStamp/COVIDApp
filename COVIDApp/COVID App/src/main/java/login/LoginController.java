package login;


import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import app.User;
import dao.ApplicationDao;
import dao.DBUtilities;

public class LoginController {
	
	/**
	 * register - This takes you through the registration process for the different cases
	 * 
	 * @param user
	 * @return
	 */
	public static String[] register(User user) {
		String[] output = new String[3]; // = {errorMessage, path, userID};
		
		// CASE: USER NOT REGISTERED 
		// -> send verification email, take to Verify page
		if(!isRegistered(user)) {
			
			//Add user to db
			ApplicationDao.createUser(user);

			//Send user verification code & Send user to verification page
			sendVerificationCode(user);
			
			//Provide output message for user
			output[0] = "Please check your email for verification code...";
			System.out.println(output[0]);
			
			//Send user to verification page
			output[1] = "verify.jsp"; 
			
			//Include userID
			output[2] = user.getUserID().toString();
			return output;
		}	
		
		// CASE: USER ALREADY REGISTERED & pwd is correct but !VERIFIED 
		// -> send verification email, take to Verify page
		if(!isVerified(user) && checkPwd(user)) {
			
			
			try {
				//get user from DB
				user = ApplicationDao.getUserFromEmail(user.getEmail());
				
				//Send user verification code & Send user to verification page
				sendVerificationCode(user);
				
				//Provide output message for user
				output[0] = "Please check your email for verification code...";
				System.out.println(output[0]);
				
				//Send user to verification page
				output[1] = "verify.jsp"; 
				
				//Include userID
				output[2] = user.getUserID().toString();
			} catch (SQLException e) {
				DBUtilities.processException(e);
			}
			
			return output; 
		}
		
		// CASE: USER REGISTERED & VERIFIED, PASSWORD WRONG 
		// -> warning
		if(!checkPwd(user)) {

			try {
				//get user from DB
				user = ApplicationDao.getUserFromEmail(user.getEmail());
				
				//Provide output message for user
				output[0] = "user is already registered...";
				System.out.println(output[0]);
				
				output[1] = "index.jsp";
				
				//Include userID
				output[2] = user.getUserID().toString();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return output;
		}
		
		// CASE: USER REGISTERED, VERIFIED, PWD matches 
		// -> take to profile page

		try {
			//get user from DB
			user = ApplicationDao.getUserFromEmail(user.getEmail());
			
			//Provide output message for user
			output[0] = "user is already registered, verified, and pwd is correct, logging in...";
			System.out.println(output[0]);
			
			output[1] = "profile.jsp";
			
			//Include userID
			output[2] = user.getUserID().toString();
			
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		
		return output;
	}

	public static String[] verify(String userID, String codeInput) {
		String[] output = new String[3]; // = {errorMessage, path, userID};
		
		try {
			// Read user from DB
			User user = ApplicationDao.getUserFromID(userID);
			
			// CASE: matches
			// -> go to profile page
			if (user.getVerCode().equals(codeInput)) {
				
				// change user to verified
				user.setVerified(true);
				
				// change status in DB
				ApplicationDao.updateUserVerStatus(user.getUserID(), user.getVerified());
				
				//Provide output message for user
				output[0] = "welcome";
				System.out.println(output[0]);
				
				//Send user to verification page
				output[1] = "profile.jsp"; 
				
				//Include userID
				output[2] = user.getUserID();
			}
			// Case: doesn't match
			// -> stay on verification page with error message
			else {
				//Provide output message for user
				output[0] = "Please re-enter verification code...";
				System.out.println(output[0]);
				
				//Send user to verification page
				output[1] = "verify.jsp"; 
				
				//Include userID
				output[2] = user.getUserID().toString();
			}
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		
			return output;
	}
	
	public static String[] login(User user) {
		String[] output = new String[3]; // = {errorMessage, path, userID};
		
		try {
			// Read user from DB
			user = ApplicationDao.getUserFromEmail(user.getEmail());
			
			// CASE: USER NOT REGISTERED 
			// -> warning username or password is incorrect 
			if(!isRegistered(user)) {
				
				//Send user back to login page
				output[0] = "username or password is incorrect";
				System.out.println(output[0]);
				output[1] = "login.jsp";
				
				//Include userID
				output[2] = user.getUserID().toString();
				
				return output; 
			}
		
			// CASE: USER REGISTERED, PASSWORD WRONG 
			// -> warning username or password is incorrect
			if(!checkPwd(user)) {
	
				//Send user back to login page
				output[0] = "username or password is incorrect";
				System.out.println(output[0]);
	
				output[1] = "login.jsp";
				
				//Include userID
				output[2] = user.getUserID().toString();
				
				return output;
			}
		
		// CASE: USER REGISTERED, PWD CORRECT, !VERIFIED 
		// -> send verification email, take to Verify page
		if(!isVerified(user)) {

			sendVerificationCode(user);
			output[0] = "Please check your email for verification code...";
			System.out.println(output[0]);

			output[1] = "verify.jsp";
			
			//Include userID
			output[2] = user.getUserID().toString();
			
			return output;
		}else {
		
		// CASE: USER REGISTERED, PWD CORRECT, VERIFIED 
		// -> take to profile page
		
		output[0] = "logging in...";
		System.out.println(output[0]);

		output[1] = "profile.jsp";
		
		//Include userID
		output[2] = user.getUserID().toString();

		return output; 
		}
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		return output;
	}
	
	
	
	public static Boolean isRegistered(User user) {
		// Check if user is in database
		try {
			List<User> users = ApplicationDao.readUsers();
			for (int i = 0; i < users.size(); i++) {
				//check each entry until you find a match
				if (user.getEmail().equals(users.get(i).getEmail())){
					System.out.println("User already exists in db");
					return true;
				}
			}
			// No match
			System.out.println("LoginController.isRegistered(): User can be added");
			return false;
			
		} catch (SQLException e) {
			System.out.println("LoginController.isRegistered(): error reading users");
			DBUtilities.processException(e);
			return false; // Not sure what to return if there is an error tbh
		}
	}
	
	
	public static Boolean isVerified(User user) {
		try {
			List<User> users = ApplicationDao.readUsers();
			for (int i = 0; i < users.size(); i++) {
				//check each entry until you find a match
				if (user.getEmail().equals(users.get(i).getEmail()) && 
						user.getPassword().equals(users.get(i).getPassword())){
					System.out.println("User exists and pwd is correct");
					if (users.get(i).getVerified()) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			// No match
			System.out.println("LoginController.isVerified(): user/pwd not found");
			return false;

		} catch (SQLException e) {
			System.out.println("LoginController.isVerified(): error reading users");
			DBUtilities.processException(e);
			return false;
		}
	}
	
	public static void sendVerificationCode(User user) {
		String verCode;

		// Generate verification code
		verCode = genVerificationCode();
		
		// Save it to the user
		user.setVerCode(verCode);
		
		// Save into DB
		ApplicationDao.updateUserVerCode(user.getUserID().toString(),  verCode);
		
		// TODO: Send verification code to user via email

	}
	
	/**
	 * verificationCode - creates a 6 character verification code
	 * @return - String of length 6 chars
	 */
	public static String genVerificationCode () {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		System.out.println("Verification code is: " + number);
		return String.format("%06d", number);
	}
	
	
	public static Boolean checkPwd(User user) {
		// Check if user is in database
		try {
			List<User> users = ApplicationDao.readUsers();
			for (int i = 0; i < users.size(); i++) {
				//check each entry until you find a match
				if (user.getEmail().equals(users.get(i).getEmail()) && 
						user.getPassword().equals(users.get(i).getPassword())){
					System.out.println("User exists and pwd is correct");
					return true;
				}
			}
			// No match
			System.out.println("LoginController.chkPwd(): pwd does not match");
			return false;

		} catch (SQLException e) {
			System.out.println("LoginController.chkPwd(): error reading users");
			DBUtilities.processException(e);
			return false;
		}
	}
	
	public static void changePwd() {
		System.out.println("Changing password...");
	}
}

	
	



