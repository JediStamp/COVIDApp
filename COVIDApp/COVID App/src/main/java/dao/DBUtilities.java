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
		String sql1 = "create database if not exists " + dbName + ";";
		String sql2 = "use " + dbName + ";";
		// Add tables
		String sql3 = "create table if not exists DBVer(dbVersion integer);";
		String sql4 = "create table if not exists USERS(userDBID integer not null auto_increment primary key,"
					+ "userID varchar(50),"
					+ "firstName varchar(50),"
					+ "lastName varchar(50),"
					+ "email varchar(50),"
					+ "pwd varchar(50),"
					+ "verCode char(6),"
					+ "verified boolean,"
					+ "usrRole varchar(10));";
		// Set Version Number
		String sql5 = "insert into " + dbName + ".DBVer  values(" + dbVersion + ");";
	
		try (
			Connection conn = DriverManager.getConnection(connPath, userName, pwd);
			Statement stmt = conn.createStatement();
			) {
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);
			stmt.addBatch(sql5);
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
			System.out.printf("delete DB version %d",getVersion(connPath, userName, pwd, dbName));
			deleteDB(connPath, userName, pwd, dbName, dbVersion);			
		}
		System.out.printf("create DB version %d", dbVersion);
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
