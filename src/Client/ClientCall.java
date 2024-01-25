package Client;
 
import java.io.IOException;

import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
 
public class ClientCall {
	/**
	 * @param args
	 */
	public static String FilterTrains(String username, String password, String departureStation, String arrivalStation, String departureDate, String arrivalDate, String tickets, String travelClass) {
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/train");
        
        resource.setChallengeResponse(ChallengeScheme.HTTP_BASIC, username, password);
        
        Form form = new Form();
        form.add("departureStation", departureStation);
        form.add("arrivalStation", arrivalStation);
        form.add("departureDate", departureDate);
        form.add("arrivalDate", arrivalDate);
        form.add("tickets", tickets);
        form.add("travelClass", travelClass);

        try {
            // Create a form with the needed information
            // Send a POST request with the form
        	String response = resource.post(form).getText();
        	System.out.println("Response: " + response);
        	System.out.println("Done!");
        	return response;
        } catch (ResourceException e) {
            e.printStackTrace();
            return "401 Unauthorized - Incorrect username or password";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }
}