package Rest;
import java.sql.DriverManager;
import java.util.*;
import javax.print.attribute.standard.Chromaticity;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import java.sql.*;
public class TrainManager extends ServerResource{
	
	private static final String JDBC_URL = "jdbc:sqlite:C:\\Users\\User\\OneDrive\\Desktop\\mydatabase.db";
	 @Post
	    public Representation reserveTickets(Representation entity) {
	        String trainId = (String) getRequestAttributes().get("TrainID");
	        Form form = new Form(entity);
	        String nbOfTickets = form.getFirstValue("tickets");
	        String travelClass = form.getFirstValue("travelClass");
	        String trainId2 = form.getFirstValue("trainID2");

	        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	            // Check if there are enough available seats for the specified travel class
	            if (checkSeatsAvailability(connection, trainId, Integer.parseInt(nbOfTickets), travelClass) & checkSeatsAvailability(connection, trainId2, Integer.parseInt(nbOfTickets), travelClass)) {
	                // Update the reservation based on the train ID
	                updateReservation(connection, trainId, Integer.parseInt(nbOfTickets), travelClass);
	                updateReservation(connection, trainId2, Integer.parseInt(nbOfTickets), travelClass);
	                return new StringRepresentation("Reservation successful", MediaType.TEXT_PLAIN);
	            } else {
	                return new StringRepresentation("Not enough available seats for the specified class", MediaType.TEXT_PLAIN);
	            }
	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new StringRepresentation("Error processing reservation",  MediaType.TEXT_PLAIN);
	        }
	    }

	    private boolean checkSeatsAvailability(Connection connection, String trainId, int numberOfTickets, String travelClass) throws SQLException {
	       System.out.println("TrainID: "+ trainId);
	    	
	    	try (PreparedStatement preparedStatement = connection.prepareStatement(
	                "SELECT " + getColumnNameForTravelClass(travelClass) + " FROM train WHERE trainId = ?")) {

	            preparedStatement.setString(1, trainId);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int availableSeats = resultSet.getInt(getColumnNameForTravelClass(travelClass));
	                System.out.println("\n "+ "the seats are "+ Integer.toString(availableSeats));
	                return availableSeats >= numberOfTickets;
	            } else {
	            	System.out.println("Failed here ");
	                return false;
	            }
	        }
	    }

	    private void updateReservation(Connection connection, String trainId, int numberOfTickets, String travelClass) throws SQLException {
	        try (PreparedStatement preparedStatement = connection.prepareStatement(
	                "UPDATE train SET " + getColumnNameForTravelClass(travelClass) + " = " +
	                        getColumnNameForTravelClass(travelClass) + " - ? WHERE trainId = ?")) {

	            preparedStatement.setInt(1, numberOfTickets);
	            preparedStatement.setString(2, trainId);
	            preparedStatement.executeUpdate();
	        }
	    }

	    private String getColumnNameForTravelClass(String travelClass) {
	    	String Result="";
	        	if (travelClass.contentEquals("FIRST"))
	            	Result= "firstClassSeats";
	        	else if (travelClass.contentEquals("BUSINESS"))
	            	Result= "businessClassSeats";
	        	else if (travelClass.contentEquals("STANDARD"))
	            	Result= "standardClassSeats";
	        	else
	            	Result=" error ";
	        
	    System.out.println(" the result is :"+ Result);
	    return Result;
	    }

}
