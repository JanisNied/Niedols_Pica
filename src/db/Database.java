package db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Customer;

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
            				"display_name TEXT NOT NULL,"+
            				"orders INTEGER NOT NULL" +
                    ")";
            
            String customerregistry = 
            		"CREATE TABLE IF NOT EXISTS Customers (" +
            				"serialized_data TEXT NOT NULL"+
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
	public static String insertStatement(File db, String login, String displayname, String password) {
		String status = "unknown";
		if (login.isEmpty() || password.isEmpty())
			status = "empty";
		else {
			Connection connection = null;
			String checkUserSql = "SELECT COUNT(*) FROM Users WHERE login = ?";
			String insertSql = "INSERT INTO Users (login, password, display_name, orders) VALUES (?, ?, ?, ?)";
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
	            	insertStmt.setString(3, displayname);
	            	insertStmt.setInt(4, 0);
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
	public static int countEntries(File db, String queryArg) {
		Connection connection = null;
		String sql = "select count(*) from "+queryArg;
		int count = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+db;
            connection = DriverManager.getConnection(url);
            System.out.println("[DB] Connected to the database.");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
            System.out.println("[DB] Count retrieved.");
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
        return count;
	}
	public static void insertCustomerStatement(File db, Customer customer) {
			Connection connection = null;
	        try {
	            Class.forName("org.sqlite.JDBC");
	            String url = "jdbc:sqlite:"+db;
	            connection = DriverManager.getConnection(url);        
	            System.out.println("[DB] Connected to the database.");
	            
	            
	            byte[] serializedObject = serialize(customer);
	            String insertSql = "INSERT INTO Customers (serialized_data) VALUES (?)";
	            PreparedStatement insertStmt = connection.prepareStatement(insertSql);
	            insertStmt.setBytes(1, serializedObject);
	            insertStmt.executeUpdate();
                System.out.println("Serialized data inserted successfully.");
	            insertStmt.close();
	        } catch (ClassNotFoundException e) {
	            System.out.println("[DB] SQLite JDBC driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("[DB] Failed to connect to the database or make changes.");
	            e.printStackTrace();
	        } catch (IOException e) {
	        	System.out.println("[DB] Failed to serialize data.");
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
	public static ArrayList<Customer> getAllCustomers(File db, boolean exceptions) {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection conn = null;
        try {
        	Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+db;
            conn = DriverManager.getConnection(url);
            String selectAllSQL = "SELECT serialized_data FROM Customers";
            try (PreparedStatement selectAllStatement = conn.prepareStatement(selectAllSQL);
                 ResultSet resultSet = selectAllStatement.executeQuery()) {
                while (resultSet.next()) {
                    byte[] serializedData = resultSet.getBytes("serialized_data");
                    Customer customer = (Customer) deserialize(serializedData);
                    if (!customer.isOrderComplete() || exceptions)
                    	customers.add(customer);
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

	private static byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }
    }
    private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return objectInputStream.readObject();
        }
    }
}
