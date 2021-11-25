package dao;

import java.sql.SQLException;
import java.util.List;

import app.User;
import questionnaires.QuestionAnswer;
import questionnaires.QuestionSet;

public class ApplicationDaoProxy implements Application {
	
	private ApplicationDao appDao = new ApplicationDao();
	private CreateDAO createDAO = new CreateDAO();
	private ReadDAO readDAO = new ReadDAO();
	
	public void createUser(User user) { 
		createDAO.createUser(user);
	}
	
	public void saveQuestionnaireResponse(String userID, QuestionSet questionSet) { 
		createDAO.createUserSurveyAnswer(userID, questionSet);
	}
	
	public void saveTeamUserRole(User user) {
		createDAO.createTeamUserRole(user);
	}
	
	public List<User> readUsers() throws SQLException {
		return readDAO.readUsers();
	}
	
	public List<QuestionAnswer> readFullSurveyResults() throws SQLException {
		return readDAO.readFullSurveyResults(); 
	}
	
	public User getUserFromID(String userID) throws SQLException {
		return readDAO.readUser("userID", userID); 
	}
	
	public User getUserFromEmail(String email) throws SQLException {
		return readDAO.readUser("email", email); 
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
