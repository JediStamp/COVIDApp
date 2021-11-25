package dao;

import java.sql.SQLException;
import java.util.List;

import app.User;
import questionnaires.QuestionAnswer;
import questionnaires.QuestionSet;

public interface Application {
	
	public void createUser(User user);
	public void saveTeamUserRole(User user);
	public void saveQuestionnaireResponse(String userID, QuestionSet questionSet);
	public List<User> readUsers() throws SQLException;
	// public List<QuestionAnswer> readSurveyResults() throws SQLException;
	public List<QuestionAnswer> readFullSurveyResults() throws SQLException;
	public User getUserFromID(String userID) throws SQLException;
	public User getUserFromEmail(String email) throws SQLException;
	public void updateUserVerCode(String UserID, String verCode);
	public void updateUserVerStatus(String UserID, Boolean verStatus);
	public void updateUser(String UserID, String firstName, String lastName);
	public void updateUserPwd(String pwd, String userID);
	public void deleteUser(String userID);
	

}
