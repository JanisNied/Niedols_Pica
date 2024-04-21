package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import main.Global;

public class Database {
	public static void initializeDB() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+Global.database;
            connection = DriverManager.getConnection(url);
            System.out.println("[DB] Connected to the database.");
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                    "login TEXT NOT NULL," +
                    "password TEXT NOT NULL" +
                    ")";
            statement.execute(sql);
            System.out.println("[DB] Table created successfully.");
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

}
