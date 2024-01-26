package Deployment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB {
	private static Connection connection;
	
	public UserDB() {
		
		initConnection();
	}
	
	public static boolean addUser(String username, String password) {
		try {
			initConnection();
			if (!isUsernameAvailable(username)) {
				return false;
			}

			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO users VALUES (?, ?, ?)");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);		
			preparedStatement.setInt(3, Constants.LOGGED_OUT);		
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void updateUserLoginStatus(String username, int login_status) {
		try {
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE users SET isLogged = ? WHERE username = ?");
			preparedStatement.setInt(1, login_status);
			preparedStatement.setString(2,  username);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// returns false when: no user with the given username was found or the password doesn't match the username
	// returns true when a record containing both the username and the password is found 
	public static boolean validateCredentials(String username, String password) {
		try {
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * from users WHERE username = ? AND password = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean isUsernameAvailable(String username) {
		try {
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * from users WHERE username = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				// the username is already used
				return false;
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean isUserLoggedIn(String username) {
		try {
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * from users WHERE username = ? AND isLogged = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				// there is an user with the provided username that is loggedIn
				return true;
			}
			
			// there is no such user with the provided username or the user with the provided username is not logged in
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void displayUsers() {
		try {
			initConnection();
			Statement statement = connection.createStatement();
			
			String query = "SELECT * FROM users";
			ResultSet resultSet = statement.executeQuery(query);
			
			System.out.println("Users:");
			
			while (resultSet.next()) {
				System.out.println("Username: " + resultSet.getString("username"));
				System.out.println("Password: " + resultSet.getString("password"));
				System.out.println("IsLoggedIn: " + resultSet.getString("isLogged"));
				System.out.println("-------------------------------------------\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	private static void initConnection() {

        if (connection == null) {

    		try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.err.println("SQLite JDBC Driver not found.");
                e.printStackTrace();
                return;
            }
    		
        	try {
    			connection = DriverManager.getConnection(Constants.JDBC_USERS_DB_URL);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
	}
	
    public static void main(String[] args) {

        initConnection();
        
        if (!addUser("test1", "test1")) {
        	System.out.println("User test1 already exists");
        }

        if (!addUser("test2", "test2")) {
        	System.out.println("User test2 already exists");
        }
        
        if (!addUser("test3", "test3")) {
        	System.out.println("User test3 already exists");
        }

        displayUsers();
    }
}
