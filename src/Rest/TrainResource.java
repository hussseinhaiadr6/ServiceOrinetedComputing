package Rest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource; 

public class TrainResource extends ServerResource { 

	@Get  
	public Representation getAllTrains() {
		String result = "";
		
		try {
			Class.forName("org.sqlite.JDBC");
	    } catch (ClassNotFoundException e) {
	    	System.err.println("SQLite JDBC Driver not found.");
	    	e.printStackTrace();
	    	new EmptyRepresentation();
	    }

		try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL);
				Statement statement = connection.createStatement()) {
			System.out.println("Connected to the database");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM train");
            while (resultSet.next()) {
            	result = result + stringifyTrainRecord(resultSet, null);
		    }
        } catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new StringRepresentation(result,MediaType.TEXT_PLAIN);
	}
	
	@Post
	public Representation filterTrains(Representation entity) {
		String result = "";

		Form form = new Form(entity);
		String departureStation = form.getFirstValue("departureStation");
		String arrivalStation = form.getFirstValue("arrivalStation");
		String departureDate = form.getFirstValue("departureDate");
		String arrivalDate = form.getFirstValue("arrivalDate");
		String tickets = form.getFirstValue("tickets");
		String travelClass = form.getFirstValue("travelClass");
		            
		try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL);
				Statement statement = connection.createStatement()) {
			System.out.println("Connected to the database");
			ResultSet resultSet = statement.executeQuery(
				  "SELECT * FROM train " +
			                "WHERE (departureStation = '" + departureStation + "' " +
			                "AND arrivalStation = '" + arrivalStation + "' " +
			                "AND arrival_date = '" + departureDate + "' " +
			                "AND " + getColumnNameForTravelClass(travelClass) + " >= '" + tickets + "') " +
			                "OR " +
			                "(departureStation = '" + arrivalStation + "' " +
			                "AND arrivalStation = '" + departureStation + "' " +
			                "AND arrival_date = '" + arrivalDate + "' " +
							"AND " + getColumnNameForTravelClass(travelClass) + " >= '" + tickets + "') ");
			
			if (!resultSet.next()) {
				result = "No available trains.";
			} else {
				do {
				 	result = result + stringifyTrainRecord(resultSet, travelClass);
				} while (resultSet.next());
			}
		 } catch (SQLException e) {
			 	e.printStackTrace();
		 }
		
		return new StringRepresentation(result, MediaType.TEXT_PLAIN);
    }

	// Convert a train database record into a string 
	private String stringifyTrainRecord(ResultSet resultSet, String travelClass) {
		String result = "";
		
		try {
			result = result + "Train ID: " + resultSet.getString("trainId") + "\n"
							+ "Departure Station: " + resultSet.getString("departureStation") + "\n"
							+ "Arrival Station: " + resultSet.getString("arrivalStation") + "\n"
							+ "Departure Date: " + resultSet.getString("departure_date") + "\n"
							+ "Departure Time: " + resultSet.getString("departure_time") + "\n"
							+ "Arrival Date: " + resultSet.getString("arrival_date") + "\n"
							+ "Arrival Time: " + resultSet.getString("arrival_time") + "\n";
			if (travelClass == null) {
				result = result + "First Class Seats: " + resultSet.getInt("firstClassSeats") + "\n"
								+ "Business Class Seats: " + resultSet.getInt("businessClassSeats") + "\n"
								+ "Standard Class Seats: " + resultSet.getInt("standardClassSeats") + "\n"
								+ "First Class Non-Flexible Fares: " + resultSet.getDouble("firstClassFares") + "\n"
		        				+ "Business Class Non-Flexible Fares: " + resultSet.getDouble("businessClassFares") + "\n"
		        				+ "Standard Class Non-Flexible Fares: " + resultSet.getDouble("standardClassFares") + "\n"
								+ "First Class Flexible Fares: " + (resultSet.getDouble("firstClassFares") + 30) + "\n"
								+ "Business Class Flexible Fares: " + (resultSet.getDouble("businessClassFares") + 30) + "\n"
								+ "Standard Class Flexible Fares: " + (resultSet.getDouble("standardClassFares") + 30) + "\n";
			} else if (travelClass.contentEquals("FIRST")) {
				result = result + "First Class Seats: " + resultSet.getInt("firstClassSeats") + "\n"
								+ "First Class Non-Flexible Fares: " + resultSet.getDouble("firstClassFares") + "\n"
								+ "First Class Flexible Fares: " + (resultSet.getDouble("firstClassFares") + 30) + "\n";
			}  else if (travelClass.contentEquals("BUSINESS")) {
	        	result = result + "Business Class Seats: " + resultSet.getInt("businessClassSeats") + "\n"
        						+ "Business Class Non-Flexible Fares: " + resultSet.getDouble("businessClassFares") + "\n"
        						+ "Business Class Flexible Fares: " + (resultSet.getDouble("businessClassFares") + 30) + "\n";
	    	} else if (travelClass.contentEquals("STANDARD")) {
	        	result = result + "Standard Class Seats: " + resultSet.getInt("standardClassSeats") + "\n"
								+ "Standard Class Non-Flexible Fares: " + resultSet.getDouble("standardClassFares") + "\n"
								+ "Standard Class Flexible Fares: " + (resultSet.getDouble("standardClassFares") + 30) + "\n";
	    	}
			
            result = result + "-------------------------------------------\n\n\n"; 
		} catch (SQLException e) {
			 e.printStackTrace();
		}
		
        return result;
	}
	
    private String getColumnNameForTravelClass(String travelClass) {
    	String result = "";
    	if (travelClass.contentEquals("FIRST")) {
        	result = "firstClassSeats";
    	} else if (travelClass.contentEquals("BUSINESS")) {
        	result = "businessClassSeats";
    	} else if (travelClass.contentEquals("STANDARD")) {
        	result = "standardClassSeats";
    	} else {
        	result= "standardClassSeats";
    	}
        
	    System.out.println(" the result is :"+ result);
	    return result;
    }
}  