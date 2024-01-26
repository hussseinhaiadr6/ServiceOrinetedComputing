package Deployment;

public class TrainFilter {
	
		public String filter(String username, String password, String departureStation, String arrivalStation, String departureDate, String arrivalDate, String tickets, String travelClass){

			if (username == null || username.isEmpty()) {
				return "Please enter username.";
			}
			
			if (password == null || password.isEmpty()) {
				return "Please enter password.";
			}
			
			if (departureStation == null || departureStation.isEmpty()) {
				return "Please choose the departureStation.";
			}
			
			if (arrivalStation == null || arrivalStation.isEmpty()) {
				return "Please choose the arrivalStation.";
			}
			
			if (departureDate == null || departureDate.isEmpty()) {
				return "Please choose the departureDate.";
			}
			
			if (arrivalDate == null || arrivalDate.isEmpty()) {
				return "Please choose the arrivalDate.";
			}
			
			if (tickets == null || Integer.parseInt(tickets) == 0) {
				return "Please select at least one ticket."; 
			}
			
			if (travelClass == null || travelClass.isEmpty()) {
				return "Please choose a travelClass.";
			}
			      
			if (UserDB.isUserLoggedIn(username)) {
				if (UserDB.validateCredentials(username, password)) {
					return Client.ClientCall.FilterTrains(departureStation, arrivalStation, departureDate, arrivalDate, tickets, travelClass);
				} else {
					return "Username or password are not invalid";
				}
			} else {
				return "User is not logged in";
			}
			
//		    return Client.ClientCall.FilterTrains("StationA","StationB","2023-01-01","2023-01-01","2","FIRST");
		}
}
