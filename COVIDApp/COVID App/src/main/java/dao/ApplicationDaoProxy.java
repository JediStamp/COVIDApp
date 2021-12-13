package dao;

import java.sql.SQLException;
import java.util.List;

import beans.app.User;
import beans.questionnaires.QuestionAnswer;
import beans.questionnaires.QuestionSet;

public class ApplicationDaoProxy implements Application {
	private static final ApplicationDaoProxy appDAO = new ApplicationDaoProxy();
	private static boolean init = false;
	
	private ApplicationDao appDao = new ApplicationDao();
	private CreateDAO createDAO = new CreateDAO();
	private ReadDAO readDAO = new ReadDAO();
	
	private ApplicationDaoProxy() {
		
	}
	
	public static synchronized ApplicationDaoProxy getAppDAOProxy() {
    	if(init) {
    		return appDAO;
    	}
    	init = true;
    	return appDAO;
    }
	
	public synchronized void createUser(User user) { 
		createDAO.createUser(user);
	}
	
	public synchronized void saveQuestionnaireResponse(String userID, QuestionSet questionSet) { 
		createDAO.createUserSurveyAnswer(userID, questionSet);
	}
	
	public synchronized void saveTeamUserRole(User user) {
		createDAO.createTeamUserRole(user);
	}
	
	public synchronized List<User> readUsers() throws SQLException {
		return readDAO.readUsers();
	}
	
	public synchronized List<QuestionAnswer> readFullSurveyResults() throws SQLException {
		return readDAO.readFullSurveyResults(); 
	}
	
	public synchronized User getUserFromID(String userID) throws SQLException {
		return readDAO.readUser("userID", userID); 
	}
	
	public synchronized User getUserFromEmail(String email) throws SQLException {
		return readDAO.readUser("email", email); 
	}
	
	public synchronized void updateUserVerCode(String UserID, String verCode) { 
		appDao.updateUserVerCode(UserID, verCode);
	}
	
	public synchronized void updateUserVerStatus(String UserID, Boolean verStatus) { 
		appDao.updateUserVerStatus(UserID, verStatus);
	}
	
	public synchronized void updateUser(String UserID, String firstName, String lastName) { 
		appDao.updateUser(UserID, firstName, lastName);
	}
	
	public synchronized void updateUserPwd(String pwd, String userID) { 
		appDao.updateUserPwd(pwd, userID);
	}
	
	public synchronized void deleteUser(String userID){ 
		appDao.deleteUser(userID);
	}
}
