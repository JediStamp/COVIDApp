package dao;

import java.sql.SQLException;
import java.util.List;

import app.User;
import questionnaires.QuestionAnswer;
import questionnaires.QuestionSet;

public class ApplicationDaoProxy implements Application {
	
	private ApplicationDao appDao = new ApplicationDao();
	
	public void createUser(User user) { 
		appDao.createUser(user);
	}
	public void storeQuestions(String userID, QuestionSet questionSet) { 
		appDao.storeQuestions(userID, questionSet);
	}
	public List<User> readUsers() throws SQLException {
		return appDao.readUsers();
	}
//	public static List<QuestionAnswer> readSurveyResults() throws SQLException {
//		return appDao.readSurveyResults(); 	
//	}
	public List<QuestionAnswer> readFullSurveyResults() throws SQLException {
		return appDao.readFullSurveyResults(); 
	}
	public User getUserFromID(String userID) throws SQLException {
		return appDao.getUserFromID(userID); 
	}
	public User getUserFromEmail(String email) throws SQLException {
		return appDao.getUserFromEmail(email); 
	}
	public void updateUserVerCode(String UserID, String verCode) { 
		appDao.updateUserVerCode(UserID, verCode);
	}
	public void updateUserVerStatus(String UserID, Boolean verStatus) { 
		appDao.updateUserVerStatus(UserID, verStatus);
	}
	public void updateUser(String UserID, String firstName, String lastName) { 
		appDao.updateUser(UserID, firstName, lastName);
	}
	public void updateUserPwd(String pwd, String userID) { 
		appDao.updateUserPwd(pwd, userID);
	}
	public void deleteUser(String userID){ 
		appDao.deleteUser(userID);
	}
}
