package Deployment;

public class UserAuthentication {
	
	public String register(String username, String password) {
		if (username == null || username.isEmpty()) {
			return "Please select an username";
		}
		
		if (password == null || password.isEmpty()) {
			return "Please select a password";
		}
		
		return Client.ClientAuth.register(username, password);
	}
	
	public String login(String username, String password) {
		if (username == null || username.isEmpty()) {
			return "Please select an username";
		}
		
		if (password == null || password.isEmpty()) {
			return "Please select a password";
		}
		
		return Client.ClientAuth.login(username, password);
	}
	
	public String logout(String username, String password) {
		if (username == null || username.isEmpty()) {
			return "Please select an username";
		}
		
		if (password == null || password.isEmpty()) {
			return "Please select a password";
		}

		return Client.ClientAuth.logout(username);
	}
}
