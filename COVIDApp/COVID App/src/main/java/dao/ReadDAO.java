package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.app.User;
import beans.app.UserBuilder;
import beans.questionnaires.QuestionAnswer;
import beans.userRoles.UserRole;

public class ReadDAO {
	//create new arraylist for reading questions
	ArrayList<QuestionAnswer> readList = new ArrayList<>();

	// Reads list of all users in the DB
	public List<User> readUsers() throws SQLException{
		User user = null;
		List<User> users = new ArrayList<>();
		String sql = "SELECT USER.*, "
				+ "team_user_role.roleID, "
				+ "team_user_role.teamID "
				+ "FROM " + MyDB.dbName + ".USER "
				+ "JOIN " + MyDB.dbName + ".team_user_role "
				+ "ON user.userID = team_user_role.userID ORDER BY lastName, firstName ASC;";
		
		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				)
		{
			// Print out statement
			System.out.println("readUsers(): ");
			System.out.print(statement);

			while (result.next()){

				user = new UserBuilder()
						.setFirstName(result.getString("firstName"))
						.setLastName(result.getString("lastName"))
						.setEmail(result.getString("email"))
						.setPassword(result.getString("pwd"))
						.setTeamID(result.getInt("teamID"))
						.setUserRole(UserRole.getRoleFromID(result.getInt("roleID")))
						.setVerCode(result.getString("verCode"))
						.setVerified(result.getInt("verified"))
						.createUser();

//				// get verification code 
//				user.setVerCode(result.getString("verCode"));

//				// get verified status
//				if (result.getInt("verified") == 1) {
//					user.setVerified(true);	
//				}else {
//					user.setVerified(false);
//				}

				// Add user to the list of users
				users.add(user);
			}

		}catch(SQLException e) {
			System.out.println("ReadUsers(): Users Not Read from DB.");
			DBUtilities.processException(e);
		}
		// Print to screen to see results
		System.out.println("ReadUsers(): Users read from DB.");
		return users;
	}	

	//read question answers from db 
	public List<QuestionAnswer> readFullSurveyResults() throws SQLException{

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

	// Read user based on key sent in (email or ID)
	public User readUser(String key, String value) throws SQLException{
		User user = null;
		String sql = "SELECT USER.*, "
		+ "team_user_role.roleID, "
		+ "team_user_role.teamID "
		+ "FROM " + MyDB.dbName + ".USER "
		+ "JOIN " + MyDB.dbName + ".team_user_role "
		+ "ON user.userID = team_user_role.userID WHERE USER." + key + "=? ;";

		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				PreparedStatement statement = conn.prepareStatement(sql);
				)
		{
			statement.setString(1, value);
			System.out.println("getUser(): ");
			System.out.println(statement);

			ResultSet result = statement.executeQuery();			
			while (result.next()){
				user = new UserBuilder()
						.setFirstName(result.getString("firstName"))
						.setLastName(result.getString("lastName"))
						.setEmail(result.getString("email"))
						.setPassword(result.getString("pwd"))
						.setTeamID(result.getInt("teamID"))
						.setUserRole(UserRole.getRoleFromID(result.getInt("roleID")))
						.setUserID(result.getString("userID"))
						.setVerCode(result.getString("verCode"))
						.createUser();

				// Print to screen to see results
				System.out.println("getUser: User read from DB.");

			}
		}catch(SQLException e) {
			System.out.println("getUsersFromEmail(): Users Not Read from DB.");
			DBUtilities.processException(e);
		}

		return user;
	}	
}
