package login;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import app.Email;
import app.User;
import app.UserBuilder;
import dao.ApplicationDao;
import dao.ApplicationDaoProxy;
import dao.DBUtilities;
import servlets.Observer;

public class LoginController implements Observable{
	
	/**
	 * register - This takes you through the registration process for the different cases
	 * 
	 * @param user
	 * @return
	 */
	static ExecutorService executor = Executors.newFixedThreadPool(5);


	private ArrayList<Observer> observers;
	private User user;
	private String[] output = new String[2];
	
	public LoginController() {
		observers = new ArrayList<Observer>();
		user = new UserBuilder().createUser();
	}

	ApplicationDaoProxy appDaoProxy = new ApplicationDaoProxy();

	public String[] register(User user) {
		this.user = user;
		
		// CASE: USER NOT REGISTERED 
		// -> send verification email, take to Verify page
		if(!isRegistered()) {

			// Add user to dB
			appDaoProxy.createUser(user);
			
			// Add user / team / role to dB
			appDaoProxy.saveTeamUserRole(user);

			// Send user verification code & Send user to verification page
			sendVerificationCode();
			
			// Provide output message for user
			output[0] = "Please check your email for verification code...";
			System.out.println(output[0]);
			
			// Send user to verification page
			output[1] = "verify.jsp"; 
			
			// Update user details
			notifyObservers();
			
			return output;
		}	
		
		// CASE: USER ALREADY REGISTERED & pwd is correct but !VERIFIED 
		// -> send verification email, take to Verify page
		else if(!isVerified() && checkPwd()) {
			
			try {

				//get user from DB
				user = appDaoProxy.getUserFromEmail(user.getEmail());

				// Send user verification code & Send user to verification page
				sendVerificationCode();
				
				// Provide output message for user
				output[0] = "Please check your email for verification code...";
				System.out.println(output[0]);
				
				// Send user to verification page
				output[1] = "verify.jsp"; 

				// Update user details
				notifyObservers(); 
				
			} catch (SQLException e) {
				DBUtilities.processException(e);
			}

			return output; 
		}
		
		// CASE: USER REGISTERED & VERIFIED, PASSWORD WRONG 
		// -> warning
		else if(!checkPwd()) {

			try {

				//get user from DB
				user = appDaoProxy.getUserFromEmail(user.getEmail());
				
				// Provide output message for user
				output[0] = "user is already registered...";
				System.out.println(output[0]);
				
				output[1] = "index.jsp";
				
				// Update user details
				notifyObservers(); 
				
			} catch (SQLException e) {
				DBUtilities.processException(e);
			}
	
			return output;
		}
		
		// CASE: USER REGISTERED, VERIFIED, PWD matches 
		// -> take to profile page
		else {
			try {
				//get user from DB
				user = appDaoProxy.getUserFromEmail(user.getEmail());

				//Provide output message for user
				output[0] = "user is already registered, verified, and pwd is correct, logging in...";
				System.out.println(output[0]);

				output[1] = "profile.jsp";

				// Update user details
				notifyObservers(); 

			} catch (SQLException e) {
				DBUtilities.processException(e);
			}

			return output;
		}
	}

	public String[] verify(String userID, String codeInput) {
		
		try {
			// Read user from DB
			user = appDaoProxy.getUserFromID(userID);
			
			// CASE: code matches
			// -> go to profile page
			if (user.getVerCode().equals(codeInput)) {
				
				// change user to verified
				user.setVerified(true);
				
				// change status in DB
				appDaoProxy.updateUserVerStatus(user.getUserID(), user.getVerified());
				
				// Provide output message for user
				output[0] = "welcome";
				System.out.println(output[0]);
				
				System.out.println("Verification status updated, user email is: " + user.getEmail());
				
				if (user.getPassword().equals("0")) {
					// Send user to set password
					output[1] = "changePass.jsp";
				}else {
					// Send user to verification page
					output[1] = "profile.jsp";					
				} 
				
				// Update user details
				notifyObservers(); 
			}
			
			// Case: code doesn't match
			// -> stay on verification page with error message
			else {
				// Provide output message for user
				output[0] = "Please re-enter verification code...";
				System.out.println(output[0]);
				
				// Send user to verification page
				output[1] = "verify.jsp"; 
				
				// Update user details
				notifyObservers(); 
			}
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		
			return output;
	}
	
	public String[] login(User userAttempt) {
		this.user = userAttempt;
		try {

			// CASE: USER NOT REGISTERED 
			// -> warning user name or password is incorrect 
			if(!isRegistered()) {

				// Send user back to login page
				output[0] = "username or password is incorrect";
				System.out.println(output[0]);
				output[1] = "login.jsp";

				// Update user details
				notifyObservers(); 
				
				return output; 
			}

			// CASE: USER REGISTERED, PASSWORD WRONG 
			// -> warning user name or password is incorrect
			else if(!checkPwd()) {

				// Send user back to login page
				output[0] = "username or password is incorrect";
				System.out.println(output[0]);

				output[1] = "login.jsp";

				// Update user details
				notifyObservers(); 

				return output;
			}
			
			// CASE: USER REGISTERED, PWD CORRECT
			else {

				// Read user from DB
				user = appDaoProxy.getUserFromEmail(userAttempt.getEmail());

				// CASE: USER REGISTERED, PWD CORRECT, !VERIFIED 
				// -> send verification email, take to Verify page
				if(!isVerified()) {

					sendVerificationCode();
					output[0] = "Please check your email for verification code...";
					System.out.println(output[0]);

					output[1] = "verify.jsp";
					
					// Update user details
					notifyObservers(); 

					return output;
				}else {

					// CASE: USER REGISTERED, PWD CORRECT, VERIFIED 
					// -> take to profile page

					output[0] = "";
					System.out.println(output[0]);

					output[1] = "profile.jsp";
					
					// Update user details
					notifyObservers(); 

					return output; 
				}
			}
			
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		
		return output;
	}
	
	public Boolean isRegistered() {
		// Check if user is in database
		try {
			List<User> users = appDaoProxy.readUsers();
			for (int i = 0; i < users.size(); i++) {
				
				//check each entry until you find a match
				if (user.getEmail().equalsIgnoreCase(users.get(i).getEmail())){
					
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
	
	
	public Boolean isVerified() {
		try {
			List<User> users = appDaoProxy.readUsers();
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
	
	public void sendVerificationCode() {
		String verCode;

		// Generate verification code
		verCode = genVerificationCode();
		
		// Save it to the user
		user.setVerCode(verCode);
		
		// Save into DB
		appDaoProxy.updateUserVerCode(user.getUserID().toString(),  verCode);
		
		// Update user details
		notifyObservers(); 
		
		// Send verification code to user via email
		sendEmail(); 

	}
	
	/**
	 * verificationCode - creates a 6 character verification code
	 * @return - String of length 6 chars
	 */
	public String genVerificationCode () {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		System.out.println("Verification code is: " + number);
		return String.format("%06d", number);
	}
	
	public boolean sendEmail() {
		boolean test = false;
		
		// Enqueue recordings for closed captioning.
        EmailSpooler spooler = new EmailSpooler();
        Email email = new Email(user.getEmail(), user.getVerCode());
        spooler.enqueue(email);
        spooler.shutdown();
		
		return test;
	}
	
	
	public Boolean checkPwd() {
		// Check if user is in database
		try {
			List<User> users = appDaoProxy.readUsers();
			for (int i = 0; i < users.size(); i++) {
				//check each entry until you find a match
				if (user.getEmail().equalsIgnoreCase(users.get(i).getEmail()) && 
						user.getPassword().equals(users.get(i).getPassword())){
					System.out.println("User exists and pwd is correct");
					return true;
				}
			}
			// No match
			System.out.println("LoginController.checkPwd(): pwd does not match");
			return false;

		} catch (SQLException e) {
			System.out.println("LoginController.checkPwd(): error reading users");
			DBUtilities.processException(e);
			return false;
		}
	}
	
	public String[] changePwd(User user) {
		this.user = user;
		
		System.out.println("Changing password...");
		if(isRegistered()) {
			try {


				//this.user = ApplicationDao.getUserFromEmail(user.getEmail());
				
				// Update user's verification status
				//ApplicationDao.updateUserVerStatus( user.getUserID(), false);
				//user.setVerified(false);
				
				//Update user password
				//ApplicationDao.updateUserPwd("0", user.getUserID());
				//user.setPassword(null);

				user = appDaoProxy.getUserFromEmail(user.getEmail());
				appDaoProxy.updateUserVerStatus( user.getUserID(), false);
				//Update user password
				appDaoProxy.updateUserPwd("0", user.getUserID());
			
				//Send user verification code & Send user to verification page
				sendVerificationCode();
				
				//Provide output message for user
				output[0] = "Please check your email for verification code...";
				System.out.println(output[0]);
				
				//Send user to verification page
				output[1] = "verifyPasswordChange.jsp"; 
				
				// Update user details
				notifyObservers(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				DBUtilities.processException(e);
			}
		}
		else {
				//Provide output message for user
				output[0] = "User not registered";
				
				//Send user to verification page
				output[1] = "index.jsp"; 
			}
		return output;
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			System.out.println("Notifying observer: " + observer);
			observer.update(user);
		}
	}
}

	
	



