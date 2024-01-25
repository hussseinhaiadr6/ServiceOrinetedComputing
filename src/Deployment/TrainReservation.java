package Deployment;

public class TrainReservation {
	
		public String reserve(String username, String password, String trainID1, String trainID2, String ticketsNo, String travelClass) {
			
			if (username == null || username.isEmpty()) {
				return "Please select an username";
			}
			
			if (password == null || password.isEmpty()) {
				return "Please enter an password";
			}
			
			if (trainID2 == null || trainID2.isEmpty()) {
				return "Please choose the trainID.";
			}

			if (Integer.parseInt(ticketsNo) == 0) {
				return "Please select at least one ticket."; 
			}

			if (travelClass == null || travelClass.isEmpty()) {
				return "Please choose a travelClass.";
			}
			
//		    return Client.Client2Call.reserveTrains("T003", "T002", "1", "BUSINESS");
			return Client.Client2Call.reserveTrains(username, password, trainID1, trainID2, ticketsNo, travelClass);
		}
}
