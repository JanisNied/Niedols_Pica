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

import main.Global;
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
            				"serialized_data TEXT NOT NULL,"
            				+ "order_number INTEGER NOT NULL"+
            		")";
            String customerLevels = 
            		"CREATE TABLE IF NOT EXISTS CustomerLevels (" +
            			    "number TEXT NOT NULL,"+
            			    "level INTEGER NOT NULL,"+
            			    "cur_xp INTEGER NOT NULL,"+
            			    "max_xp INTEGER NOT NULL"+
            		")";
            statement.execute(customerregistry);
            statement.execute(usertable);
            statement.execute(customerLevels);
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
	            String insertSql = "INSERT INTO Customers (serialized_data, order_number) VALUES (?, ?)";
	            PreparedStatement insertStmt = connection.prepareStatement(insertSql);
	            insertStmt.setBytes(1, serializedObject);
	            insertStmt.setInt(2, (int) customer.getOrderNum());
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
    public static void overrideCustomer(File db, Customer c) {
    	String url = "jdbc:sqlite:"+db;
        String sql = "UPDATE Customers SET serialized_data = ? WHERE order_number = ?";
        
        byte[] newSerializedData = null;
		try {
			newSerializedData = serialize(c);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        long specificOrderNumber = c.getOrderNum();
        
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBytes(1, newSerializedData);
            pstmt.setInt(2, (int) specificOrderNumber);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static String getDisplayName(File db, String login) {
        String displayName = null;
        String URL = "jdbc:sqlite:"+db;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT display_name FROM Users WHERE login = ?")) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    displayName = resultSet.getString("display_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return displayName;
    }
    public static int getOrders(File db, String login) {
        int orders = 0;
        String URL = "jdbc:sqlite:"+db;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT orders FROM Users WHERE login = ?")) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    orders = resultSet.getInt("orders");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public static void updateOrders(File db, String login, int newOrders) {
    	String URL = "jdbc:sqlite:"+db;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET orders = ? WHERE login = ?")) {
            preparedStatement.setInt(1, newOrders);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
            System.out.println("Orders updated successfully for login '" + login + "'.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String insertCustomerLevel(File db, Customer customer) {
		Connection connection = null;
		String returnstr = "";
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+db;
            connection = DriverManager.getConnection(url);        
            System.out.println("[DB] Connected to the database.");
            String checkUserSql = "SELECT COUNT(*) FROM CustomerLevels WHERE number = ?";
            PreparedStatement checkUserStmt = connection.prepareStatement(checkUserSql);
            checkUserStmt.setString(1, customer.getNumber());
            ResultSet resultSet = checkUserStmt.executeQuery();
            resultSet.next();  
            if (resultSet.getInt(1) == 0) {
            	 String insertSql = "INSERT INTO CustomerLevels (number, level, cur_xp, max_xp) VALUES (?, ?, ?, ?)";
                 PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                 insertStmt.setString(1, customer.getNumber());
                 insertStmt.setInt(2, 1);
                 insertStmt.setInt(3, 0);
                 insertStmt.setInt(4, Global.levelCurve(1));
                 insertStmt.executeUpdate();
                 insertStmt.close();
            } else 
            	returnstr = "exists";     
            resultSet.close();
            checkUserStmt.close();
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
        return returnstr;
    }
    public static void setSomething(File db, Customer c, String query, int something) {
    	String URL = "jdbc:sqlite:"+db;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CustomerLevels SET "+query+" = ? WHERE number = ?")) {
            preparedStatement.setInt(1, something);
            preparedStatement.setString(2, c.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getSomething(File db, Customer c, String query) {
        int orders = 0;
        String URL = "jdbc:sqlite:"+db;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT "+query+" FROM CustomerLevels WHERE number = ?")) {
            preparedStatement.setString(1, c.getNumber());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    orders = resultSet.getInt(query);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
