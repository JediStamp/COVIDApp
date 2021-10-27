package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Service Class
public class ApplicationDao {
	
	// Create Methods -------------------------------------------------------------------
	public static void createUser(String userID, String firstName, String lastName, String email, String pwd){
		String sql = "INSERT INTO " + MyDB.dbName + ".USERS (userID, firstName, lastName, email, pwd) VALUES (?, ?, ?, ?, ?);";
		
		try {
			// Make connection
			Connection connection = DBUtilities.getConnectionToDB( 
					MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, userID);
			statement.setString(2, firstName);
			statement.setString(3, lastName);
			statement.setString(4, email);
			statement.setString(5, pwd);
			System.out.println(statement);
			int rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("A new user was inserted successfully!");
			}
			connection = null;
		} catch (SQLException e) {
			System.out.println("A new user was not inserted.");
			e.printStackTrace();
		}
	}
	
	// Read Methods ---------------------------------------------------------------------
//	public static List<DBLog> readLog() throws SQLException{
//		DBLog myLog = null;
//		List<DBLog> myLogs = new ArrayList<>();
//		String sql = "SELECT * FROM " + DBConnection.dbName + ".textlog ORDER BY timeStmp ASC;";
//
//		try (	// Make connection
//				Connection connection = DBConnection.getConnectionToDatabase();
//				Statement statement = connection.createStatement();
//				ResultSet result = statement.executeQuery(sql);
//				)
//		{
//			while (result.next()){
//				myLog = new DBLog();
//				myLog.setInternalID(result.getString("internalID"));
//				myLog.setTitle(result.getString("logTitle"));
//				myLog.setLog_content(result.getString("logContent"));
//				myLog.setTimestamp(result.getString("timeStmp"));
//
//				myLogs.add(myLog);
//				//			    System.out.println("Logs read.");
//				//			    System.out.println(myLog.getInternalID());
//				//			    System.out.println(myLog.getTitle());
//				//			    System.out.println(myLog.getLog_content());
//				//			    System.out.println(myLog.getTimestamp());
//			}
//		}catch(SQLException e) {
//			System.out.println("Logs Not read.");
//			DBUtilities.processException(e);
//		}
//
//		return myLogs;
//	}	
	
//	// Update
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
