package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			DBUtilities.processException(e);
		}
		
		if (connection != null) {
			System.out.println("Connection made to MYSQL!");
			System.out.println("Create DB if not exists");
			createDB();
			
			// Check version number
			if (getVersion() < MyDB.version) {
				updateDB();
			}
		}
		
		return connection;
	}

	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}

	public static void createDB() {
		String sql1 = "create database if not exists COVID;";
		String sql2 = "use COVID;";
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
		String sql5 = "insert into " + MyDB.dbName + ".DBVer  values(" + MyDB.version + ");";
		
	
		try (
			Connection conn = DriverManager.getConnection(MyDB.connPath, MyDB.userName, MyDB.pwd);
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
	
	public static void deleteDB() {
		String sql =  "drop database if exists COVID;";
		
        try (Connection conn = DriverManager.getConnection(MyDB.connPath, MyDB.userName, MyDB.pwd);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
               stmt.execute();
        } catch (SQLException e) {
        	System.out.println("error in deleteDB() method");
        	DBUtilities.processException(e);
        }
	}
	
	public static void updateDB() {
		deleteDB();
		createDB();
	}
	
	public static int getVersion() {
		String sql = "Select MAX(dbVersion) FROM "+ MyDB.dbName + ".DBVer;";
		try (
				Connection conn = DriverManager.getConnection(MyDB.connPath, MyDB.userName, MyDB.pwd);
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
