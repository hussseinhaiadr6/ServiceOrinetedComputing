package Deployment;

public class TrainReservation {

		public String reserve(String username, String password, String trainID1, String trainID2, String tickets, String travelClass) {

			if (username == null || username.isEmpty()) {
				return "Please select an username";
			}

			if (password == null || password.isEmpty()) {
				return "Please enter an password";
			}

			if (trainID2 == null || trainID2.isEmpty()) {
				return "Please choose the trainID.";
			}

			if (Integer.parseInt(tickets) == 0) {
				return "Please select at least one ticket."; 
			}

			if (travelClass == null || travelClass.isEmpty()) {
				return "Please choose a travelClass.";
			}

//		    return Client.Client2Call.reserveTrains("T003", "T002", "1", "BUSINESS");
			if (UserDB.isUserLoggedIn(username)) {
				if (UserDB.validateCredentials(username, password)) {
					return Client.Client2Call.reserveTrains(trainID1, trainID2, tickets, travelClass);
				} else {
					return "Username or password are not invalid";
				}
			} else {
				return "User is not logged in";
			}
		}
}
