package Deployment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteUserDatabaseCreator {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        try {
        	Connection connection = DriverManager.getConnection(Constants.JDBC_USERS_DB_URL);
            System.out.println("Connected to the database");

            Statement statement = connection.createStatement();
       
             // Drop the 'users' table if it exists
             statement.executeUpdate("DROP TABLE IF EXISTS users");

             // Create the 'users' table
             String createUsersTableQuery = "CREATE TABLE users (" +
            		 "username TEXT PRIMARY KEY," +
            		 "password TEXT NOT NULL," +
            		 "isLogged INTEGER NOT NULL)";
             statement.executeUpdate(createUsersTableQuery);
             System.out.println("Table 'users' created");

        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
            e.printStackTrace();
        }
    }
}