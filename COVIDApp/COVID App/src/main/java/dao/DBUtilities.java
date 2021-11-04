package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtilities {
	/**
	 * getConnectionToDB - Connects to the DB, and checks the version number, 
	 * calls update if version number is less than the current version
	 * 
	 * @param connPath - String - provides the path to the dB tool (does not include DB name)
	 * @param userName - String - the username required for access to the DB tool
	 * @param pwd - String - the password required to access the DB tool
	 * @param dbName - String - this is the name of the DB currently set to COVID
	 * @param dbVersion - int - this is the version of the DB we are using. Starts at 1
	 */
	public static Connection getConnToDB(String connPath, String userName, String pwd, String dbName, int dbVersion) {
		Connection connection = null;
		
		try {
			// load the driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");
			
			// get the DriverManager
			connection = DriverManager.getConnection(connPath, userName, pwd);
		}catch(ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("Connection Failed! Check output console");
			DBUtilities.processException(e);
		}
		
		if (connection != null) {
			System.out.println("Connection made to MYSQL!");

			// Check version number
			if (getVersion(connPath, userName, pwd, dbName) < dbVersion) {
				System.out.printf("Update DB from %d to %d\n",getVersion(connPath, userName, pwd, dbName),dbVersion);
				updateDB(connPath, userName, pwd, dbName, dbVersion);
			}
		}
		
		return connection;
	}

	/**
	 * processException - Provides error messages, codes and SQL state for any SQLException
	 * passed in. Useful for debugging SQL code.
	 * 
	 * @param e - SQLException the error that will be explained
	 */
	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}

/**
 * createDB - Creates a new database in the db tool
 * 
	 * @param connPath - String - provides the path to the dB tool (does not include DB name)
	 * @param userName - String - the username required for access to the DB tool
	 * @param pwd - String - the password required to access the DB tool
	 * @param dbName - String - this is the name of the DB currently set to COVID
	 * @param dbVersion - int - this is the version of the DB we are using. Starts at 1
 */
	public static void createDB(String connPath, String userName, String pwd, String dbName, int dbVersion) {
		// Create and Use DB
		String sqldb1 = "create database if not exists " + dbName + ";";
		String sqldb2 = "use " + dbName + ";";
		
		// Add tables
		String sqlDBVer = "CREATE TABLE IF NOT EXISTS DBVer(dbVersion integer);";
		
		String sqlUser = "CREATE TABLE IF NOT EXISTS USER("
					+ "userDBID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "userID VARCHAR(45),"
					+ "firstName VARCHAR(45),"
					+ "lastName VARCHAR(45),"
					+ "email VARCHAR(45),"
					+ "pwd VARCHAR(45),"
					+ "verCode CHAR(6),"
					+ "verified BOOLEAN);";
		
		String sqlSurvey = "CREATE TABLE IF NOT EXISTS SURVEY("
				+ "surveyID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "teamID INT NULL);";
		
		String sqlQuestion = "CREATE TABLE IF NOT EXISTS QUESTION ("
				+ "questionID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "questionText BLOB);";
		
		String sqlAnswer = "CREATE TABLE IF NOT EXISTS ANSWER ("
				+ "answerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "answerValue VARCHAR(45));";
		
		String sqlQ_A = "CREATE TABLE IF NOT EXISTS QUESTION_ANSWER ("
				+ "questionID INT,"
				+ "answerID INT);";
		
		String sqlSurvery_Q = "CREATE TABLE IF NOT EXISTS SURVEY_QUESTION ("
				+ "surveyID INT,"
				+ "questionID INT);";
		
		String sqlTeam = "CREATE TABLE IF NOT EXISTS TEAM ("
				+ "teamID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "teamName VARCHAR(45), "
				+ "teamOwner INT);";
		
		String sqlRole = "CREATE TABLE IF NOT EXISTS ROLE ("
				+ "roleID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "roleName VARCHAR(45));";
		
		String sqlTeam_user = "CREATE TABLE IF NOT EXISTS TEAM_USER_ROLE ("
				+ "teamID INT,"
				+ "userID INT,"
				+ "roleID INT);";
		
		String sqlEvent = "CREATE TABLE IF NOT EXISTS EVENT ("
				+ "eventID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "teamID INT,"
				+ "surveyID INT,"
				+ "startDate DATE,"
				+ "endDate DATE);";
		
		String sqlUser_Survey_Answer = "CREATE TABLE IF NOT EXISTS USER_SURVEY_ANSWER ("
				+ "userID VARCHAR(45),"
				+ "teamID INT,"
				+ "eventID INT,"
				+ "questionID INT,"
				+ "answerID INT);";
		
		// Set Version Number
		String inDBVer = "insert into " + dbName + ".DBVer  values(" + dbVersion + ");";
		// Set Questions
		String inQ1 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('I am fully vaccinated against COVID-19.');";
		String inQ2 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('In the last 10 days, have you been identified as a close contact<br/>"
				+ " of someone who currently has COVID-19?.');";
		String inQ3 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('In the last 10 days, have you received a COVID Alert exposure <br/>"
				+ " notification on your cell phone?</td>');";
		String inQ4 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('In the last 14 days, have you travelled outside of Canada?');";
		String inQ5 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('Are you currently experiencing fever and/or Chills? <br/>"
				+ "<i>Temperature of 37.8 degrees Celcius/100 degrees Fahrenheit or more</i>');";
		String inQ6 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('Are you currently experiencing cough or barking cough? <br/>"
				+ "	<i>Continuous, more than usual, making a whistling noise when breathing <br/>"
				+ "(not related to asthma, post-infectious reactive airways, COPD, or <br/>"
				+ "other known causes or conditions you already have)</i>');";
		String inQ7 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('Are you currently experiencing shortness of breath?<br/>"
				+ "<i>Out of breath, unable to breathe deeply (not related to asthma <br/>"
				+ "or other known causes or conditions you already have)</i>');";
		String inQ8 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('Are you currently experiencing decrease in loss of taste or breath?<br/>"
				+ "<i>Not related to seasonal allergies, neurological disorders, or other <br/>"
				+ "known causes or conditions you already have</i>');";
		String inQ9 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('Are you currently experiencing muscle aches/joint pain?<br/>"
				+ "<i>Unusual, long-lasting (not related to getting a COVID-19 vaccine in the last 48 hours,<br/>"
				+ "a sudden injury, fibromyalgia, or other known causes or conditions you already have)</i>');";
		String inQ10 = "insert into " + dbName + ".QUESTION (questionText) values "
				+ "('Are you currently experiencing extreme tiredness?<br/>"
				+ "<i>Unusual, fatigue, lack of energy (not related to getting a COVID-19 vaccine in the last 48 hours, <br/>"
				+ "depression, insomnia, thyroid dysfunction, or other known causes or conditions you already have)</i>');";

		// Set Answers
		String inAns1 = "insert into " + dbName + ".ANSWER (answerValue) values ('yes');";
		String inAns2 = "insert into " + dbName + ".ANSWER (answerValue) values ('no');";
		
		// Set Question_Answers
		String inQA1 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (1,1);";
		String inQA2 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (2,2);";
		String inQA3 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (3,2);";
		String inQA4 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (4,2);";
		String inQA5 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (5,2);";
		String inQA6 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (6,2);";
		String inQA7 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (7,2);";
		String inQA8 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (8,2);";
		String inQA9 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (9,2);";
		String inQA10 = "insert into " + dbName + ".QUESTION_ANSWER (questionID, answerID) VALUES (10,2);";
	
		try (
			Connection conn = DriverManager.getConnection(connPath, userName, pwd);
			Statement stmt = conn.createStatement();
			) {
			stmt.addBatch(sqldb1);
			stmt.addBatch(sqldb2);
			// Add Tables
			stmt.addBatch(sqlDBVer);
			stmt.addBatch(sqlUser);
			stmt.addBatch(sqlSurvey);
			stmt.addBatch(sqlQuestion);
			stmt.addBatch(sqlAnswer);
			stmt.addBatch(sqlQ_A);
			stmt.addBatch(sqlSurvery_Q);
			stmt.addBatch(sqlTeam);
			stmt.addBatch(sqlRole);
			stmt.addBatch(sqlTeam_user);
			stmt.addBatch(sqlEvent);
			stmt.addBatch(sqlUser_Survey_Answer);
			
			// Insert Data
			// DBVer
			stmt.addBatch(inDBVer);
			// Question
			stmt.addBatch(inQ1);
			stmt.addBatch(inQ2);
			stmt.addBatch(inQ3);
			stmt.addBatch(inQ4);
			stmt.addBatch(inQ5);
			stmt.addBatch(inQ6);
			stmt.addBatch(inQ7);
			stmt.addBatch(inQ8);
			stmt.addBatch(inQ9);
			stmt.addBatch(inQ10);
			// Answer
			stmt.addBatch(inAns1);
			stmt.addBatch(inAns2);
			// Question_answer
			stmt.addBatch(inQA1);
			stmt.addBatch(inQA2);
			stmt.addBatch(inQA3);
			stmt.addBatch(inQA4);
			stmt.addBatch(inQA5);
			stmt.addBatch(inQA6);
			stmt.addBatch(inQA7);
			stmt.addBatch(inQA8);
			stmt.addBatch(inQA9);
			stmt.addBatch(inQA10);
			// Execute Batch of statements
			stmt.executeBatch();
		} catch (SQLException e) {
			System.out.println("error in createDB() method");
			DBUtilities.processException(e);
		}
	}
	
	/**
	 * deleteDB - deletes the current version of the database in the DB tool
	 * 
	 * @param connPath - String - provides the path to the dB tool (does not include DB name)
	 * @param userName - String - the username required for access to the DB tool
	 * @param pwd - String - the password required to access the DB tool
	 * @param dbName - String - this is the name of the DB currently set to COVID
	 * @param dbVersion - int - this is the version of the DB we are using. Starts at 1
	 */
	public static void deleteDB(String connPath, String userName, String pwd, String dbName, int dbVersion) {
		String sql =  "drop database if exists COVID;";
		
        try (Connection conn = DriverManager.getConnection(connPath, userName, pwd);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
               stmt.execute();
               System.out.println("deleteDB(): Deleted DB");
        } catch (SQLException e) {
        	System.out.println("error in deleteDB() method");
        	DBUtilities.processException(e);
        }
	}
	
	/**
	 * updateDB - updates the database. Calls deleteDB() if a DB exists, and then calls createDB()
	 * 
	 * @param connPath - String - provides the path to the dB tool (does not include DB name)
	 * @param userName - String - the username required for access to the DB tool
	 * @param pwd - String - the password required to access the DB tool
	 * @param dbName - String - this is the name of the DB currently set to COVID
	 * @param dbVersion - int - this is the version of the DB we are using. Starts at 1
	 */
	public static void updateDB(String connPath, String userName, String pwd, String dbName, int dbVersion) {
		if (getVersion(connPath, userName, pwd, dbName) > 0) {
			System.out.printf("delete DB version %d\n",getVersion(connPath, userName, pwd, dbName));
			deleteDB(connPath, userName, pwd, dbName, dbVersion);			
		}
		System.out.printf("create DB version %d\n", dbVersion);
		createDB(connPath, userName, pwd, dbName, dbVersion);
	}
	
	/**
	 * getVersion - returns the version number of the db in the db tool
	 * 
	 * @param connPath - String - provides the path to the dB tool (does not include DB name)
	 * @param userName - String - the username required for access to the DB tool
	 * @param pwd - String - the password required to access the DB tool
	 * @param dbName - String - this is the name of the DB currently set to COVID
	 * 
	 * @return dbVersion - int the version of the current DB in the DB tool. Returns 0 if no DB found.
	 */
	public static int getVersion(String connPath, String userName, String pwd, String dbName) {
		String sql = "Select MAX(dbVersion) FROM "+ dbName + ".DBVer;";
		try (
				Connection conn = DriverManager.getConnection(connPath, userName, pwd);
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int currVer = 0;
			while(result.next()){
				currVer = result.getInt(1);
	        }
			return currVer;
			
		} catch (SQLException e) {
			System.out.println("error in getVersion() method");
			DBUtilities.processException(e);
			return 0;
		}
	}
}
