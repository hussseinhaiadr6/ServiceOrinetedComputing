package Rest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource; 
public class TrainResource extends ServerResource { 
	  static final String JDBC_URL = "jdbc:sqlite:C:\\Users\\User\\OneDrive\\Desktop\\mydatabase.db"; 
	  
	  


	@Get  
	public String toString() {
		String Result="";
		 try {
	            Class.forName("org.sqlite.JDBC");
	        } catch (ClassNotFoundException e) {
	            System.err.println("SQLite JDBC Driver not found.");
	            e.printStackTrace();
	            return Result;
	        }

	        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	            System.out.println("Connected to the database");
	                try (Statement statement = connection.createStatement()) {
	                    String query = "SELECT * FROM train";
	                    ResultSet resultSet = statement.executeQuery(query);

	                    System.out.println("\nTrain Data:");

	                    while (resultSet.next()) {
	                      Result=Result+ "Train ID: " + resultSet.getString("trainId")+"\n";
	                      Result=Result+"Departure Station: " + resultSet.getString("departureStation")+"\n";
	                      Result=Result+"Arrival Station: " + resultSet.getString("arrivalStation")+"\n";
	                      Result=Result+"Departure Date: " + resultSet.getString("departure_date")+"\n";
	                      Result=Result+"Departure Time: " + resultSet.getString("departure_time")+"\n";
	                      Result=Result+"Arrival Date: " + resultSet.getString("arrival_date")+"\n";
	                        Result=Result+"Arrival Time: " + resultSet.getString("arrival_time")+"\n";
	                        Result=Result+"First Class Seats: " + resultSet.getInt("firstClassSeats")+"\n";
	                        Result=Result+"Business Class Seats: " + resultSet.getInt("businessClassSeats")+"\n";
	                        Result=Result+"Standard Class Seats: " + resultSet.getInt("standardClassSeats")+"\n";
	                        Result=Result+"First Class Fares: " + resultSet.getDouble("firstClassFares")+"\n";
	                        Result=Result+"Business Class Fares: " + resultSet.getDouble("businessClassFares")+"\n";
	                        Result=Result+"Standard Class Fares: " + resultSet.getDouble("standardClassFares")+"\n";
	                        Result=Result+"-------------------------------------------"+"\n"+"\n"+"\n";
	                    }
	                }
	            
	            
	        
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		
		
		return Result;  
	}
	
	
	
	
	
	@Post
	   public Representation whatever(Representation entity) {  
			String Result ="dfffs";
			Form form = new Form(entity);  		 
			String departureStation = form.getFirstValue("departureStation");
			String arrivalStation = form.getFirstValue("arrivalStation");
			String departureDate = form.getFirstValue("departureDate");
			String arrivalDate = form.getFirstValue("arrivalDate");
			//int tickets= Integer.parseInt(form.getFirstValue("tickets"));
			//String ticketClass= form.getFirstValue("travelClass");
			
			 try (Connection connection = DriverManager.getConnection(JDBC_URL);
		          Statement statement = connection.createStatement()) {
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
					   Result=Result+ "Train ID: " + resultSet.getString("trainId")+"\n";
	                      Result=Result+"Departure Station: " + resultSet.getString("departureStation")+"\n";
	                      Result=Result+"Arrival Station: " + resultSet.getString("arrivalStation")+"\n";
	                      Result=Result+"Departure Date: " + resultSet.getString("departure_date")+"\n";
	                      Result=Result+"Departure Time: " + resultSet.getString("departure_time")+"\n";
	                      Result=Result+"Arrival Date: " + resultSet.getString("arrival_date")+"\n";
	                        Result=Result+"Arrival Time: " + resultSet.getString("arrival_time")+"\n";
	                        Result=Result+"First Class Seats: " + resultSet.getInt("firstClassSeats")+"\n";
	                        Result=Result+"Business Class Seats: " + resultSet.getInt("businessClassSeats")+"\n";
	                        Result=Result+"Standard Class Seats: " + resultSet.getInt("standardClassSeats")+"\n";
	                        Result=Result+"First Class Fares: " + resultSet.getDouble("firstClassFares")+"\n";
	                        Result=Result+"Business Class Fares: " + resultSet.getDouble("businessClassFares")+"\n";
	                        Result=Result+"Standard Class Fares: " + resultSet.getDouble("standardClassFares")+"\n";
	                        Result=Result+"-------------------------------------------"+"\n"+"\n"+"\n";
		                
		            }
			 }catch (Exception e) {
				// TODO: handle exception
			}
			 

			

	        return new StringRepresentation(Result,MediaType.TEXT_PLAIN);
	    }

}  