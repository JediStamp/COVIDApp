package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import app.User;
import questionnaires.QuestionSet;

public class CreateDAO {
	// Adds a new user to the database
	public void createUser(User user){

		// INSERT statement missing parameters
		String sql = "INSERT INTO " + MyDB.dbName + ".USER (userID, firstName, lastName, email, pwd) "
				+ "VALUES (?, ?, ?, ?, ?);";

		try (
				// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			// Fill in parameters
			statement.setString(1, user.getUserID());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());

			// Print out complete statement
			System.out.println(statement);

			// Make sure that statement actually caused rows to be inserted
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("CreateUser(): A new user was inserted successfully!");
			}

		} catch (SQLException e) {
			System.out.println("CreateUser(): A new user was not inserted.");
			DBUtilities.processException(e);
		}
	}

	// Adds team user role to the database 
	public void createTeamUserRole(User user) {

		// INSERT statement missing parameters
		String sql = "INSERT INTO " + MyDB.dbName + ".TEAM_USER_ROLE (teamID, userID, roleID) "
				+ "VALUES (?, ?, ?);";

		try (
				// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			// Fill in parameters
			statement.setInt(1, user.getTeamID());
			statement.setString(2, user.getUserID());
			statement.setInt(3, user.getUserRole().getRoleID());
			
			// Print out complete statement
			System.out.println(statement);
			
			// Make sure that statement actually caused rows to be inserted
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("CreateTeamUserRole(): A new team user role was inserted successfully!");
			}

		} catch (SQLException e) {
			System.out.println("CreateUser(): Team user role was not inserted.");
			DBUtilities.processException(e);
		}
	}

	// Saves User Survey Answers to the Database
	public void createUserSurveyAnswer(String userID, QuestionSet questionSet){
		
		// INSERT statement missing parameters
		String sql = "INSERT INTO " + MyDB.dbName + ".USER_SURVEY_ANSWER "
				+ "(userID, teamID, eventID, questionID, answerID, time_stamp) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";

		try (
				// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{

			// For each question answered
			for(int i = 0; i < questionSet.getQuestions().size(); i++) {
				// Fill in parameters
				statement.setString(1, userID);
				statement.setInt(2, questionSet.getTeamID());  
				statement.setInt(3, questionSet.getEventID());  
				statement.setInt(4, questionSet.getQuestions().get(i).getQuestionID());  //get question ID 
				statement.setInt(5, questionSet.getQuestions().get(i).getAnswerID());    //get answer ID
				statement.setTimestamp(6, questionSet.getQuestions().get(i).getTimestamp());
				
				// Print out complete statement
				System.out.println(statement);
				
				// Make sure that statement actually caused rows to be inserted
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("createUserSurveyAnswer(): stored user survey response succesfully!");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("createUserSurveyAnswer(): user survey responses not inserted.");
			DBUtilities.processException(e);
		}
	}
}
