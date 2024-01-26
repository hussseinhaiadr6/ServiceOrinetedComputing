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
	public static String reserveTrains(String id1, String id2, String tickets, String travelClass) {        
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/train/" + id1);
        Form form = new Form();
        form.add("tickets", tickets);
        form.add("travelClass", travelClass);
        form.add("trainID2", id2);
                
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