package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.User;

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
		String sql = "INSERT INTO " + MyDB.dbName + ".USERS (userID, firstName, lastName, email, pwd) VALUES (?, ?, ?, ?, ?);";
		
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
			e.printStackTrace();
		}
	}
	
	// Read Methods ---------------------------------------------------------------------
	public static List<User> readUsers() throws SQLException{
		User user = null;
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM " + MyDB.dbName + ".USERS ORDER BY lastName, firstName ASC;";

		try (	// Make connection
				Connection conn = DBUtilities.getConnToDB( MyDB.connPath,  MyDB.userName,  MyDB.pwd, MyDB.dbName ,MyDB.version);
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				)
		{
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
				System.out.println("First Name: " + user.getFirstName());
				System.out.println("Last Name: " + user.getLastName());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Password: " + user.getPassword());
				System.out.println("Verification Code: " + user.getVerCode());
				System.out.println("Verified Status: " + user.getVerified());
				
			}
		}catch(SQLException e) {
			System.out.println("ReadUsers(): Users Not Read from DB.");
			DBUtilities.processException(e);
		}

		return users;
	}	
	
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
