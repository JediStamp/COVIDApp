package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtilities {
	public static Connection getConnectionToDatabase(String connPath, String userName, String pwd) {
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
			e.printStackTrace();
		}
		
		if (connection != null) {
			System.out.println("Connection made to DB!");
		}
		
		return connection;
	}

	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}
	
	
}
