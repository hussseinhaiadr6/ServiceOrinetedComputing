package Deployment;

public class UserAuthentication {
	
	public String register(String username, String password) {
		if (username == null || username.isEmpty()) {
			return "Please input an username";
		}
		
		if (password == null || password.isEmpty()) {
			return "Please input a password";
		}

		boolean wasUserAdded = UserDB.addUser(username, password);
		UserDB.displayUsers();

		if (wasUserAdded == false) {
			 return "Username " + username + " already exists";
		}

		return "User " + username + " successfully registered";
	}
	
	public String login(String username, String password) {
		if (username == null || username.isEmpty()) {
			return "Please input an username";
		}
		
		if (password == null || password.isEmpty()) {
			return "Please input a password";
		}
		
		if (!UserDB.validateCredentials(username, password)) {
			 return "Incorrect username or password";
		}
		
		UserDB.updateUserLoginStatus(username, Constants.LOGGED_IN);
		UserDB.displayUsers();

		return "User " + username + " successfully logged in";
	}
	
	public String logout(String username, String password) {
		if (username == null || username.isEmpty()) {
			return "Please select an username";
		}
		
		if (password == null || password.isEmpty()) {
			return "Please select a password";
		}

		if (!UserDB.isUserLoggedIn(username)) {
	        return "User is not logged in or the provided username is invalid";
		}

		UserDB.updateUserLoginStatus(username, Constants.LOGGED_OUT);
		UserDB.displayUsers();

		return "User " + username + " successfully logged out";
	}
}
