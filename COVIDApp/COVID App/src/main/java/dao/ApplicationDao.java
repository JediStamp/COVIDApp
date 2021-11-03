package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.User;
import questionnaires.Question;
import questionnaires.QuestionSet;
import servlets.QuestionnaireServlet;

// Service Class
public class ApplicationDao {
	
	// Create Methods -------------------------------------------------------------------
	/**
	 * CreateUser - Adds a user to the database. Currently adds password to the same spot... 
	 * should probably not do that
	 * 
	 * @param userID
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param pwd
	 */
	public static void createUser(User user){
		String sql = "INSERT INTO " + MyDB.dbName + ".USER (userID, firstName, lastName, email, pwd) VALUES (?, ?, ?, ?, ?);";
		
		try (
			// Make connection
			Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
			PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, user.getUserID().toString());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			System.out.println(statement);
			int rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("CreateUser(): A new user was inserted successfully!");
			}
		} catch (SQLException e) {
			System.out.println("CreateUser(): A new user was not inserted.");
			DBUtilities.processException(e);
		}
	}
	
	//create another insert method to insert questonnaire answers into the db
	
	public static void storeQuestions(String userID, QuestionSet questionSet){
		String sql = "INSERT INTO " + MyDB.dbName + ".USER_SURVEY_ANSWER (userID, teamID, eventID, questionID, answerID) VALUES (?, ?, ?, ?, ?);";
		
		try (
			// Make connection
			Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
			PreparedStatement statement = conn.prepareStatement(sql);
			
				)
		{
			
			System.out.println("before for loop in ApplicationDAO()" + questionSet.getQuestions().size());
			
			for(int i = 0; i < questionSet.getQuestions().size(); i++) {
				
			statement.setString(1, userID);
			statement.setInt(2, questionSet.getTeamID());  
			statement.setInt(3, questionSet.getEventID());  
			statement.setInt(4, questionSet.getQuestions().get(i).getQuestionID());  //get question ID 
			statement.setInt(5, questionSet.getQuestions().get(i).getAnswerID());    //get answer ID
			System.out.println(statement);
			int rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("storeQuestions(): stored use questions succesfully!");
			}
			}
		} catch (SQLException e) {
			System.out.println("storeQuestions(): questions not inserted.");
			DBUtilities.processException(e);
		
			}
			
			//check if answer is yes or no
//			if() {
//				statement.setInt(5, 1);
//			}else {
//				statement.setInt(5, 2);
//			}
			

	}
	
	// Read Methods ---------------------------------------------------------------------
	public static List<User> readUsers() throws SQLException{
		User user = null;
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM " + MyDB.dbName + ".USER ORDER BY lastName, firstName ASC;";

		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				)
		{
			System.out.println("readUsers(): ");
			System.out.print(statement);
			
			while (result.next()){
				user = new User(result.getString("firstName"), 
						result.getString("lastName"),
						result.getString("email"),
						result.getString("pwd"));
				
				// get verification code 
				user.setVerCode(result.getString("verCode"));
				
				// get verified status
				if (result.getInt("verified") == 1) {
					user.setVerified(true);	
				}else {
					user.setVerified(false);
				}
				
				// get user role

				users.add(user);
				
				// Print to screen to see results
				System.out.println("ReadUsers(): Users read from DB.");
//				System.out.println("First Name: " + user.getFirstName());
//				System.out.println("Last Name: " + user.getLastName());
				System.out.println("Email: " + user.getEmail());
//				System.out.println("Password: " + user.getPassword());
//				System.out.println("Verification Code: " + user.getVerCode());
//				System.out.println("Verified Status: " + user.getVerified());
				
			}
		}catch(SQLException e) {
			System.out.println("ReadUsers(): Users Not Read from DB.");
			DBUtilities.processException(e);
		}

		return users;
	}	
	
	public static User getUserFromID(String userID) throws SQLException{
		User user = null;
		String sql = "SELECT * FROM " + MyDB.dbName + ".USER WHERE userID=?;";

		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, userID);
			System.out.println("getUserFromID(): ");
			System.out.println(statement);
			
			ResultSet result = statement.executeQuery();
			while (result.next()){
				user = new User(result.getString("firstName"), 
						result.getString("lastName"),
						result.getString("email"),
						result.getString("pwd"));
				
				//get userID
				user.setUserID(result.getString("userID"));
				
				// get verification code 
				user.setVerCode(result.getString("verCode"));
				
				// get verified status
				if (result.getInt("verified") == 1) {
					user.setVerified(true);	
				}else {
					user.setVerified(false);
				}
				
				// get user role

				
				// Print to screen to see results
				System.out.println("getUserFromID(): User read from DB.");
				System.out.println("Email: " + user.getEmail());
			
			}
		}catch(SQLException e) {
			System.out.println("getUserFromID(): Users Not Read from DB.");
			DBUtilities.processException(e);
		}

		return user;
	}	
	
	public static User getUserFromEmail(String email) throws SQLException{
		User user = null;
		String sql = "SELECT * FROM " + MyDB.dbName + ".USER WHERE email=? ;";

		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, email);
			System.out.println("getUserFromEmail(): ");
			System.out.println(statement);
			
			ResultSet result = statement.executeQuery();			
			while (result.next()){
				user = new User(result.getString("firstName"), 
						result.getString("lastName"),
						result.getString("email"),
						result.getString("pwd"));
				
				//get userID
				user.setUserID(result.getString("userID"));
				
				// get verification code 
				user.setVerCode(result.getString("verCode"));
				
				// get verified status
				if (result.getInt("verified") == 1) {
					user.setVerified(true);	
				}else {
					user.setVerified(false);
				}
				
				// get user role

				
				// Print to screen to see results
				System.out.println("getUserFromEmail(): User read from DB.");
				System.out.println("Email: " + user.getEmail());
			
			}
		}catch(SQLException e) {
			System.out.println("getUsersFromEmail(): Users Not Read from DB.");
			DBUtilities.processException(e);
		}

		return user;
	}	
	
	// Update Methods -------------------------------------------------------------------
	public static void updateUserVerCode(String UserID, String verCode) {
		String sql = "UPDATE " + MyDB.dbName + ".USER SET verCode=? WHERE userID=?;";
		
		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, verCode);
			statement.setString(2, UserID);
			System.out.println("updateUserVerCode(): ");
			System.out.println(statement);
				
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("User Verification Code was added successfully!");
			}
//			System.out.println("User Verification Code was NOT added.");
		}catch(SQLException e) {
			System.out.println("User Verification Code was NOT added.");
			DBUtilities.processException(e);
		}

	}
	
	public static void updateUserVerStatus(String UserID, Boolean verStatus) {
		String sql = "UPDATE " + MyDB.dbName + ".USER SET verified=? WHERE userID=?;";
		
		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setBoolean(1, verStatus);
			statement.setString(2, UserID);
			System.out.println(statement);
				
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("User verified status was updated successfully!");
			}
		}catch(SQLException e) {
			System.out.println("User verified status was NOT updated");
			DBUtilities.processException(e);
		}

	}
	
//	public static void updateLog(String logTitle, String logContent, String newTimeStmp, String oldTimeStmp){
//		String sql = "UPDATE " + DBConnection.dbName + ".textLog SET logTitle=?, logContent=?, timeStmp=? WHERE timeStmp=?;";
//		
//		try {
//			// Make connection
//			Connection connection = DBConnection.getConnectionToDatabase();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			
//			statement.setString(1, logTitle);
//			statement.setString(2, logContent);
//			statement.setString(3, newTimeStmp);
//			statement.setString(4, oldTimeStmp);
//			System.out.println(statement);
//			
//			int rowsUpdated = statement.executeUpdate();
//			if (rowsUpdated > 0) {
//			    System.out.println("An existing log was updated successfully!");
//			}
//			
//			connection = null;
//		} catch (SQLException e) {
//			System.out.println("No logs were updateed.");
//			e.printStackTrace();
//		}
//	}
	
//	// Delete
//	public static void deleteLog(String timeStmp){
//		String sql = "DELETE FROM " + DBConnection.dbName + ".textlog WHERE timeStmp=?";
//		try {
//			// Make connection
//			Connection connection = DBConnection.getConnectionToDatabase();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			
//			statement.setString(1, timeStmp);
//			
//			int rowsDeleted = statement.executeUpdate();
//			if (rowsDeleted > 0) {
//			    System.out.println("A log was deleted successfully!");
//			}
//			
//			connection = null;
//		}catch(SQLException e) {
//			System.out.println("No logs were deleted.");
//			e.printStackTrace();
//		}
//	}
}
