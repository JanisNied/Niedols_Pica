package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public static void initializeDB(File db) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+db;
            connection = DriverManager.getConnection(url);
            System.out.println("[DB] Connected to the database.");
            Statement statement = connection.createStatement();
            String usertable = 
            		"CREATE TABLE IF NOT EXISTS Users (" +
            				"login TEXT NOT NULL," +
            				"password TEXT NOT NULL," +
            				"orders INTEGER NOT NULL" +
                    ")";
            
            String customerregistry = 
            		"CREATE TABLE IF NOT EXISTS Customers (" +
            				"firstname TEXT NOT NULL,"+
            				"lastname TEXT NOT NULL, "+
            				"phonenumber TEXT NOT NULL,"+
            				"address TEXT NOT NULL"+
            		")";
            statement.execute(customerregistry);
            statement.execute(usertable);
            System.out.println("[DB] Table(s) created successfully.");
            statement.close();
        } catch (ClassNotFoundException e) {
            System.out.println("[DB] SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("[DB] Failed to connect to the database or create table.");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("[DB] Connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("[DB] Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }
	public static String insertStatement(File db, String login, String password) {
		String status = "unknown";
		if (login.isEmpty() || password.isEmpty())
			status = "empty";
		else {
			Connection connection = null;
			String checkUserSql = "SELECT COUNT(*) FROM Users WHERE login = ?";
			String insertSql = "INSERT INTO Users (login, password, orders) VALUES (?, ?, ?)";
	        try {
	            Class.forName("org.sqlite.JDBC");
	            String url = "jdbc:sqlite:"+db;
	            connection = DriverManager.getConnection(url);        
	            System.out.println("[DB] Connected to the database.");
	            PreparedStatement checkUserStmt = connection.prepareStatement(checkUserSql);
	            PreparedStatement insertStmt = connection.prepareStatement(insertSql);
	            checkUserStmt.setString(1, login);
	            ResultSet resultSet = checkUserStmt.executeQuery();
	            resultSet.next();
	            if (resultSet.getInt(1) == 0) {
	            	insertStmt.setString(1, login);
	            	insertStmt.setString(2, password);
	            	insertStmt.setInt(3, 0);
	            	insertStmt.executeUpdate();
	            	System.out.println("[DB] Data inserted successfully for user: " + login);
	            	status = "success";
	            } else {
	            	System.out.println("[DB] Data already exists for user: " + login);
	            	status = "exists";
	            }
	            resultSet.close();
	            checkUserStmt.close();
	            insertStmt.close();
	        } catch (ClassNotFoundException e) {
	            System.out.println("[DB] SQLite JDBC driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("[DB] Failed to connect to the database or make changes.");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (connection != null) {
	                    connection.close();
	                    System.out.println("[DB] Connection closed.");
	                }
	            } catch (SQLException e) {
	                System.out.println("[DB] Failed to close the connection.");
	                e.printStackTrace();
	            }
	        }
		}
        return status;
	}
	public static String checkData(File db, String login, String password) {
		String status = "unknown";
		if (login.isEmpty() || password.isEmpty())
			status = "empty";
		else {
			Connection connection = null;
			String sql = "SELECT password FROM Users WHERE login = ?";
	        try {
	            Class.forName("org.sqlite.JDBC");
	            String url = "jdbc:sqlite:"+db;
	            connection = DriverManager.getConnection(url);        
	            System.out.println("[DB] Connected to the database.");
	            PreparedStatement pstmt = connection.prepareStatement(sql);
	            pstmt.setString(1, login);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	            	String psStrg = rs.getString("password");
	            	if (password.equals(psStrg)) {
	            		status = "accept";
	            	} else
	            		status = "deny";
	            }  else
	            	status = "none";
	            rs.close();
	            pstmt.close();
	        } catch (ClassNotFoundException e) {
	            System.out.println("[DB] SQLite JDBC driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("[DB] Failed to connect to the database or make changes.");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (connection != null) {
	                    connection.close();
	                    System.out.println("[DB] Connection closed.");
	                }
	            } catch (SQLException e) {
	                System.out.println("[DB] Failed to close the connection.");
	                e.printStackTrace();
	            }
	        }
		}
        return status;
	}
}
