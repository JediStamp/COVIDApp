package login;


import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
		// {errorMessage, path, userID, firstName, lastName, email};
		String[] output = new String[6]; 
		
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
			output[3] = user.getFirstName();
			output[4] = user.getLastName();
			output[5] = user.getEmail();
			return output;
		}	
		
		// CASE: USER ALREADY REGISTERED & pwd is correct but !VERIFIED 
		// -> send verification email, take to Verify page
		else if(!isVerified(user) && checkPwd(user)) {
			
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
				output[3] = user.getFirstName();
				output[4] = user.getLastName();
				output[5] = user.getEmail();
				
			} catch (SQLException e) {
				DBUtilities.processException(e);
			}
			
			return output; 
		}
		
		// CASE: USER REGISTERED & VERIFIED, PASSWORD WRONG 
		// -> warning
		else if(!checkPwd(user)) {

			try {
				//get user from DB
				user = ApplicationDao.getUserFromEmail(user.getEmail());
				
				//Provide output message for user
				output[0] = "user is already registered...";
				System.out.println(output[0]);
				
				output[1] = "index.jsp";
				
				//Include userID
				output[2] = user.getUserID().toString();
				output[3] = user.getFirstName();
				output[4] = user.getLastName();
				output[5] = user.getEmail();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return output;
		}
		
		// CASE: USER REGISTERED, VERIFIED, PWD matches 
		// -> take to profile page
		else {
			try {
				//get user from DB
				user = ApplicationDao.getUserFromEmail(user.getEmail());

				//Provide output message for user
				output[0] = "user is already registered, verified, and pwd is correct, logging in...";
				System.out.println(output[0]);

				output[1] = "profile.jsp";

				//Include userID
				output[2] = user.getUserID().toString();
				output[3] = user.getFirstName();
				output[4] = user.getLastName();
				output[5] = user.getEmail();

			} catch (SQLException e) {
				DBUtilities.processException(e);
			}

			return output;
		}
	}

	public static String[] verify(String userID, String codeInput) {
		// {errorMessage, path, userID, firstName, lastName, email};
		String[] output = new String[6]; 
		
		try {
			// Read user from DB
			User user = ApplicationDao.getUserFromID(userID);
			
			// CASE: code matches
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
				output[3] = user.getFirstName();
				output[4] = user.getLastName();
				output[5] = user.getEmail();
			}
			// Case: code doesn't match
			// -> stay on verification page with error message
			else {
				//Provide output message for user
				output[0] = "Please re-enter verification code...";
				System.out.println(output[0]);
				
				//Send user to verification page
				output[1] = "verify.jsp"; 
				
				//Include userID
				output[2] = user.getUserID().toString();
				output[3] = user.getFirstName();
				output[4] = user.getLastName();
				output[5] = user.getEmail();
			}
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		
			return output;
	}
	
	public static String[] login(User userAttempt) {
		// {errorMessage, path, userID, firstName, lastName, email};
		String[] output = new String[6];
		output[5] = userAttempt.getEmail();

		try {

			// CASE: USER NOT REGISTERED 
			// -> warning username or password is incorrect 
			if(!isRegistered(userAttempt)) {

				//Send user back to login page
				output[0] = "username or password is incorrect";
				System.out.println(output[0]);
				output[1] = "login.jsp";

				//Include userID
				output[2] = userAttempt.getUserID().toString();

				return output; 
			}

			// CASE: USER REGISTERED, PASSWORD WRONG 
			// -> warning username or password is incorrect
			else if(!checkPwd(userAttempt)) {

				//Send user back to login page
				output[0] = "username or password is incorrect";
				System.out.println(output[0]);

				output[1] = "login.jsp";

				//Include userID
				output[2] = userAttempt.getUserID().toString();

				return output;
			}
			else {

				// Read user from DB
				User user = ApplicationDao.getUserFromEmail(userAttempt.getEmail());

				// Include userID
				output[2] = user.getUserID().toString();
				output[3] = user.getFirstName();
				output[4] = user.getLastName();
				output[5] = user.getEmail();
				
				// CASE: USER REGISTERED, PWD CORRECT, !VERIFIED 
				// -> send verification email, take to Verify page
				if(!isVerified(user)) {

					sendVerificationCode(user);
					output[0] = "Please check your email for verification code...";
					System.out.println(output[0]);

					output[1] = "verify.jsp";

					return output;
				}else {

					// CASE: USER REGISTERED, PWD CORRECT, VERIFIED 
					// -> take to profile page

					output[0] = "";
					System.out.println(output[0]);

					output[1] = "profile.jsp";

					return output; 
				}
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
					
					// User is Registered already
					System.out.println("User already exists in db");
					return true;
				}
			}
			
			// No match, user is not registered
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
		sendEmail(user); //this line was needed.

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
	
	public static boolean sendEmail(User user) {
		boolean test = false;
		
		String toEmail = user.getEmail();
		String fromEmail = "apate0871@gmail.com";
		String password = "Patriots_87";
		
		try {
			
			//set properties for our email provider
			Properties pr = new Properties();
			pr.setProperty("mail.smtp.host", "smtp.gmail.com");
			pr.setProperty("mail.smtp.port", "587");
			pr.setProperty("mail.smtp.auth", "true");
			pr.setProperty("mail.smtp.starttls.enable", "true");
			pr.put("mail.smtp.socketFactory.port", "465");
			pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			//get session
			Session session = Session.getInstance(pr, new Authenticator(){
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});
			
			//set email message details
            Message mess = new MimeMessage(session);
            
            mess.setFrom(new InternetAddress (fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            
            mess.setSubject("User Email Verification");
            mess.setText("Registered Success please use this code: " + user.getVerCode());
            
            Transport.send(mess);
            
            test = true;
            
            
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return test;
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

	
	



