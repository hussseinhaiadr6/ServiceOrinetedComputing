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
            	result = result + stringifyTrainRecord(resultSet);
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
//		int tickets= Integer.parseInt(form.getFirstValue("tickets"));
//		String ticketClass= form.getFirstValue("travelClass");
		            
		try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL);
				Statement statement = connection.createStatement()) {
			System.out.println("Connected to the database");
			ResultSet resultSet = statement.executeQuery(
				  "SELECT * FROM train " +
			                "WHERE (departureStation = '" + departureStation + "' " +
			                "AND arrivalStation = '" + arrivalStation + "' " +
			                "AND arrival_date = '" + departureDate + "') " +
			                "OR " +
			                "(departureStation = '" + arrivalStation + "' " +
			                "AND arrivalStation = '" + departureStation + "' " +
			                "AND arrival_date = '" + arrivalDate + "')");
			  				  
			while (resultSet.next()) {
			 	result = result + stringifyTrainRecord(resultSet);
			}
		 } catch (SQLException e) {
			 	e.printStackTrace();
		 }
		
		return new StringRepresentation(result, MediaType.TEXT_PLAIN);
    }

	// Convert a train database record into a string 
	private String stringifyTrainRecord(ResultSet resultSet) {
		String result = "";
		
		try {
			result = result + "Train ID: " + resultSet.getString("trainId") + "\n"
							+ "Departure Station: " + resultSet.getString("departureStation") + "\n"
							+ "Arrival Station: " + resultSet.getString("arrivalStation") + "\n"
							+ "Departure Date: " + resultSet.getString("departure_date") + "\n"
							+ "Departure Time: " + resultSet.getString("departure_time") + "\n"
							+ "Arrival Date: " + resultSet.getString("arrival_date") + "\n"
							+ "Arrival Time: " + resultSet.getString("arrival_time") + "\n"
							+ "First Class Seats: " + resultSet.getInt("firstClassSeats") + "\n"
							+ "Business Class Seats: " + resultSet.getInt("businessClassSeats") + "\n"
							+ "Standard Class Seats: " + resultSet.getInt("standardClassSeats") + "\n"
							+ "First Class Fares: " + resultSet.getDouble("firstClassFares") + "\n"
            				+ "Business Class Fares: " + resultSet.getDouble("businessClassFares") + "\n"
            				+ "Standard Class Fares: " + resultSet.getDouble("standardClassFares") + "\n"
            				+"-------------------------------------------"+"\n"+"\n"+"\n"; 
		} catch (SQLException e) {
			 e.printStackTrace();
		}
		
        return result;
	}
}  