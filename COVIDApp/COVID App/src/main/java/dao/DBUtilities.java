package dao;

import java.sql.SQLException;

public class DBUtilities {
	// NOTE CAN ADD DB CONNECTION TO THIS CLASS

	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}
}
