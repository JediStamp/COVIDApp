package dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.User;
import app.UserBuilder;
import questionnaires.Question;
import questionnaires.QuestionAnswer;
import questionnaires.QuestionSet;
import servlets.QuestionnaireServlet;

// Service Class
public class ApplicationDao {
	
	//create new arraylist for reading questions
	static ArrayList<QuestionAnswer> readList = new ArrayList<>();
	
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
	public void createUser(User user){
		String sql = "INSERT INTO " + MyDB.dbName + ".USER (userID, firstName, lastName, email, pwd) VALUES (?, ?, ?, ?, ?);";
		
		try (
			// Make connection
			Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
			PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, user.getUserID());//used to be toString
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
	
	public void storeQuestions(String userID, QuestionSet questionSet){
		String sql = "INSERT INTO " + MyDB.dbName + ".USER_SURVEY_ANSWER (userID, teamID, eventID, questionID, answerID, time_stamp) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		
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
			statement.setTimestamp(6, questionSet.getQuestions().get(i).getTimestamp());
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
	public List<User> readUsers() throws SQLException{
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
				user = new UserBuilder()
						.setFirstName(result.getString("firstName"))
						.setLastName(result.getString("lastName"))
						.setEmail(result.getString("email"))
						.setPassword(result.getString("pwd"))
						.createUser();
				
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
	
	//read question answers from db 
//	public List<QuestionAnswer> readSurveyResults() throws SQLException{
//		User user = null;
//		List<QuestionAnswer> qa = new ArrayList<>();
//		String sql = "SELECT user_survey_answer.userID,"
//				+ "user_survey_answer.teamID,"
//				+ "user_survey_answer.eventID,"
//				+ "user_survey_answer.questionID,"
//				+ "user_survey_answer.answerID,"
//				+ "question_answer.answerID "
//				+ "FROM " + MyDB.dbName + ".USER_SURVEY_ANSWER JOIN "
//		+ MyDB.dbName + ".QUESTION_ANSWER on USER_SURVEY_ANSWER.QuestionID = QUESTION_ANSWER.QuestionID;";
//
//		try (	// Make connection
//				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
//				PreparedStatement statement = conn.prepareStatement(sql);
//				ResultSet result = statement.executeQuery(sql);
//				)
//		{
//			System.out.println("readQuestions(): ");
//			System.out.print(statement);
//							
//			while (result.next()){
//					String userID = result.getString("userID");
//				    int teamID = result.getInt("teamID"); 
//				    int eventID = result.getInt("eventID");
//				    int questionID = result.getInt("questionID");
//				    int answerID = result.getInt(5);
//				    int rightAnsID = result.getInt(6);
//				    
//				    QuestionAnswer QA = new QuestionAnswer(questionID, answerID);
//				    QA.setUserID(userID);
//				    QA.setTeamID(teamID);
//				    QA.setEventID(eventID);
//				    QA.setRightAns(rightAnsID);
//				    
//				  //add to arraylist while still in while loop
//				    readList.add(QA);
//				
//			}
//			
//			// Print to screen to see results
//			System.out.println("readQuestions(): Questions read from DB.");
//			
//		}catch(SQLException e) {
//			System.out.println("ReadUsers(): Users Not Read from DB.");
//			DBUtilities.processException(e);
//		}
//
//		return readList;
//	}	
	
	//read question answers from db 
		public List<QuestionAnswer> readFullSurveyResults() throws SQLException{
			User user = null;
			List<QuestionAnswer> qa = new ArrayList<>();
			String sql = "SELECT "
					+ "user.firstName,"
					+ "user.lastName,"
					+ "user_survey_answer.userID,"
					+ "user_survey_answer.teamID,"
					+ "user_survey_answer.eventID,"
					+ "user_survey_answer.questionID,"
					+ "user_survey_answer.answerID,"
					+ "question_answer.answerID,"
					+ "user_survey_answer.time_stamp "
					+ "FROM " + MyDB.dbName + ".user "
					+ "join "
					+ MyDB.dbName + ".user_survey_answer "
					+ "on user.userID = user_survey_answer.userID "
					+ "join " 
					+ MyDB.dbName + ".QUESTION_ANSWER "
					+ "on USER_SURVEY_ANSWER.QuestionID = QUESTION_ANSWER.QuestionID "
					+ "ORDER by time_stamp, userID, questionID;";

			try (	// Make connection
					Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
					PreparedStatement statement = conn.prepareStatement(sql);
					ResultSet result = statement.executeQuery(sql);
					)
			{
//				System.out.println("readQuestions(): ");
//				System.out.print(statement);
								
				while (result.next()){
						String fName = result.getString("firstName");
						String lName = result.getString("lastName");
						String userID = result.getString("userID");
					    int teamID = result.getInt("teamID"); 
					    int eventID = result.getInt("eventID");
					    int questionID = result.getInt("questionID");
					    int answerID = result.getInt(7);
					    int rightAnsID = result.getInt(8);
					    Timestamp time_stamp = result.getTimestamp("time_stamp");
					    QuestionAnswer QA = new QuestionAnswer(questionID, answerID);
					    QA.setfName(fName);
					    QA.setlName(lName);
					    QA.setUserID(userID);
					    QA.setTeamID(teamID);
					    QA.setEventID(eventID);
					    QA.setRightAns(rightAnsID);
					    QA.setTimestamp(time_stamp);
					    
					  //add to arraylist while still in while loop
					    readList.add(QA);
					
				}
				
				// Print to screen to see results
				System.out.println("readQuestions(): Questions read from DB.");
				
			}catch(SQLException e) {
				System.out.println("readQuestions(): Questions Not Read from DB.");
				DBUtilities.processException(e);
			}

			return readList;
		}	
	
	public User getUserFromID(String userID) throws SQLException{
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
				user = new UserBuilder()
						.setFirstName(result.getString("firstName"))
						.setLastName(result.getString("lastName"))
						.setEmail(result.getString("email"))
						.setPassword(result.getString("pwd"))
						.createUser();
				
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
	
	public User getUserFromEmail(String email) throws SQLException{
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
				user = new UserBuilder()
						.setFirstName(result.getString("firstName"))
						.setLastName(result.getString("lastName"))
						.setEmail(result.getString("email"))
						.setPassword(result.getString("pwd"))
						.createUser();
				
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
	public void updateUserVerCode(String UserID, String verCode) {
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
	
	public void updateUserVerStatus(String UserID, Boolean verStatus) {
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
	
	
	public void updateUser(String UserID, String firstName, String lastName) {
		String sql = "UPDATE " + MyDB.dbName + ".USER SET firstName=?, lastName=? WHERE userID=?;";
		
		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, UserID);
			System.out.println(statement);
				
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("User profile was updated successfully!");
			}
		}catch(SQLException e) {
			System.out.println("User profile was NOT updated");
			DBUtilities.processException(e);
		}

	}
	
	public void updateUserPwd(String pwd, String userID) {
		String sql = "UPDATE " + MyDB.dbName + ".USER SET pwd=? WHERE userID=?;";
		
		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, pwd);
			statement.setString(2, userID);
			System.out.println(statement);
				
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("User password was updated successfully!");
			}
		}catch(SQLException e) {
			System.out.println("User password was NOT updated");
			DBUtilities.processException(e);
		}

	}
	
	// Delete
	public void deleteUser(String userID){
		String sql = "DELETE FROM " + MyDB.dbName + ".USER WHERE userID=?";
		try(	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				) {
			
			statement.setString(1, userID);
			
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("User was deleted successfully!");
			}
			
		}catch(SQLException e) {
			System.out.println("User was NOT deleted");
			DBUtilities.processException(e);
		}
	}
}
