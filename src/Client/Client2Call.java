package Client;
 
import java.io.IOException;

import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
 
public class Client2Call {
 
	/**
	 * @param args
	 */
	public static String reserveTrains(String username, String password, String id1, String id2, String ticketsNo, String travelClass) {        
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/train/" + id1);
        Form form = new Form();
        form.add("tickets", ticketsNo);
        form.add("travelClass", travelClass);
        form.add("trainID", id2);
        
        resource.setChallengeResponse(ChallengeScheme.HTTP_BASIC, username, password);
        
        try {
            // Create a form with the needed information
            // Send a POST request with the form
        	String response = resource.post(form).getText();
        	System.out.println("Done!");
        	return response;
        } catch (ResourceException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
	}
}