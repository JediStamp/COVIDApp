package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Service Class
public class ApplicationDao {
	
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
