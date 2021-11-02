package login;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import app.User;
import dao.ApplicationDao;
import dao.DBUtilities;
//import newpackage.SendEmail;

public class LoginController {
	
	public static String[] register(User user) {
		String[] output = new String[2]; // = {errorMessage, path};
		
		// USER NOT REGISTERED -> send verification email, take to Verify page
		if(!isRegistered(user)) {
			//Add user to db
			ApplicationDao.createUser(user);
			
			
			//Send user to verification page
			sendVerificationCode(user);
			output[0] = "Please check your email for verification code...";
			System.out.println(output[0]);
			output[1] = "verify.jsp"; //http://localhost:8080/COVID_App
			

			return output;
		}	
		
		// USER ALREADY REGISTERED & pwd is correct but !VERIFIED -> send verification email, take to Verify page
		if(!isVerified(user) && checkPwd(user)) {
			//TODO: if user exists && ! verified verification page and send email with not verified error
			sendVerificationCode(user);
			output[0] = "Please check your email for verification code...";
			System.out.println(output[0]);
			output[1] = "verify.jsp";
			
			return output; 
		}
		
		//USER REGISTERED & VERIFIED, PASSWORD WRONG -> warning
		if(!checkPwd(user)) {
			// CASE: user exists && is verified && pwd ! matches -> LOGIN page with pwd doesn't match error
			output[0] = "user is already registered...";
			System.out.println(output[0]);
			output[1] = "index.jsp";
			
			return output; // 
			//TODO: if user exists && is verified && pwd ! matches LOGIN page with pwd doesn't match error
			// Want to add error message on top of page
		}
		

		//USER REGISTERED, VERIFIED, PWD matches -> take to profile page
		//TODO: correctly check pwd	
		output[0] = "user is already registered, verified, and pwd is correct, logging in...";
		System.out.println(output[0]);
		output[1] = "profile.jsp";

		return output; // 
	}

	public static void sendVerificationCode(User user) {
		// TODO: Send verification code to user and store in DB for checks
		System.out.println("imaginary verification code sent to user");
		
		SendEmail sm = new SendEmail();
		String code = sm.getRandom();
		user.setVerCode(code);
		System.out.println(code);
		// SAVE CODE TO DB
		
//		// TESTING
//		// Recipient's email ID needs to be mentioned.
//	      String to = "jkmiklos@hotmail.com";
//
//	      // Sender's email ID needs to be mentioned
//	      String from = "stamplecoskie@gmail.com";
//
//	      // Assuming you are sending email from localhost
//	      String host = "localhost";
//
//	      // Get system properties
//	      Properties properties = System.getProperties();
//
//	      // Setup mail server
//	      properties.setProperty("mail.smtp.host", host);
//
//	      // Get the default Session object.
//	      Session session = Session.getDefaultInstance(properties);
//
//	      try {
//	         // Create a default MimeMessage object.
//	         MimeMessage message = new MimeMessage(session);
//
//	         // Set From: header field of the header.
//	         message.setFrom(new InternetAddress(from));
//
//	         // Set To: header field of the header.
//	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//	         // Set Subject: header field
//	         message.setSubject("This is the Subject Line!");
//
//	         // Now set the actual message
//	         message.setText("This is actual message");
//
//	         // Send message
//	         Transport.send(message);
//	         System.out.println("Sent message successfully....");
//	      } catch (MessagingException mex) {
//	         mex.printStackTrace();
//	      }
		// END TESTING
//	}

		
		

//		
////		boolean test = 
//				sm.sendEmail(user);
//		System.out.println("real verification code sent to user");
	}
	
	public static Boolean verify(User user) {
//response.setContentType("text/html;charset=UTF-8");
    	
//    	try(PrintWriter out = response.getWriter()){
//    		String name = request.getParameter("username");
//    		String email = request.getParameter("useremail");
    		

    		
//    		if(test) {
//    			HttpSession session = request.getSession();
//    			session.setAttribute("authcode" , user);
//    			response.sendRedirect("verify.jsp");
//    		}
//    		else{
//        		  out.println("Failed to send verification email");
//        	   }
		return false;
		//TODO: check user verification code entered against code stored
		
	}
	
	public static String[] login(User user) {
		String[] output = new String[2]; // = {errorMessage, path}; 
		
		// USER NOT REGISTERED -> warning username or password is incorrect 
		if(!isRegistered(user)) {
			//TODO: Add error message on top of page
			//Send user back to login page
			output[0] = "username or password is incorrect";
			System.out.println(output[0]);
//			output[1] = "http://localhost:8080/COVID_App/html/login.jsp";
			output[1] = "login.jsp";
			
			return output; // 
//			System.out.println("User Not registered - username or password is incorrect");
//			return "http://localhost:8080/COVID_App/html/login.html";
		}
		
		// USER REGISTERED, PASSWORD WRONG -> warning username or password is incorrect
		if(!checkPwd(user)) {
			//TODO: Add error message on top of page
			//Send user back to login page
//			System.out.println("User is registered  but pwd is wrong");
//			return "http://localhost:8080/COVID_App/html/login.html";
			
			output[0] = "username or password is incorrect";
			System.out.println(output[0]);
//			output[1] = "http://localhost:8080/COVID_App/html/login.jsp";
			output[1] = "login.jsp";
			
			return output;

		}
		
		// USER REGISTERED, PWD CORRECT, !VERIFIED -> send verification email, take to Verify page
		if(!isVerified(user)) {
			//TODO: if user exists && ! verified verification page and send email with not verified error
			sendVerificationCode(user);
			output[0] = "Please check your email for verification code...";
			System.out.println(output[0]);
//			output[1] = "http://localhost:8080/COVID_App/html/verify.jsp";
			output[1] = "verify.jsp";
			
			return output;
//			System.out.println("user is not verified taking you to verification page, please check your email...");
//			return "http://localhost:8080/COVID_App/html/verify.html";
		}else {
		
		//USER REGISTERED, PWD CORRECT, VERIFIED -> take to profile page
		//TODO: correctly check pwd	
//		System.out.println("user is registered, verified, and pwd is correct, logging in...");
//		return "http://localhost:8080/COVID_App/html/profile.html";
		
		output[0] = "logging in...";
		System.out.println(output[0]);
//		output[1] = "http://localhost:8080/COVID_App/html/profile.jsp";
		output[1] = "profile.jsp";

		return output; // 
		}
	}
	
	public static void changePwd() {
		System.out.println("Changing password...");
	}
	
	/**
	 * isRegistered - Checks to see if user's email is already in the DB
	 * @param user
	 * @return
	 */
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
	
}
	
	



