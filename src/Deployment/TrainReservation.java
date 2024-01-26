package Deployment;

public class TrainReservation {

		public String reservation(String username, String password, String outboundTrainID, String returnTrainID, String tickets, String travelClass, String ticketType) {

			if (username == null || username.isEmpty()) {
				return "Please enter an username";
			}

			if (password == null || password.isEmpty()) {
				return "Please enter an password";
			}

			if (outboundTrainID == null || outboundTrainID.isEmpty()) {
				return "Please enter the id of the outboundTrain.";
			}

			if (Integer.parseInt(tickets) == 0) {
				return "Please select at least one ticket."; 
			}

			if (travelClass == null || travelClass.isEmpty()) {
				return "Please choose a travel class.";
			}

			if (ticketType == null || ticketType.isEmpty()) {
				return "Please choose a ticket type .";
			}
			
			if (UserDB.isUserLoggedIn(username)) {
				if (UserDB.validateCredentials(username, password)) {
					return Client.Client2Call.reserveTrains(outboundTrainID, returnTrainID, tickets, travelClass, ticketType);
				} else {
					return "Username or password are not invalid";
				}
			} else {
				return "User is not logged in";
			}
		}
}
