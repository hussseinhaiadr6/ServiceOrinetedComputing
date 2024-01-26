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
	public static String reserveTrains(String outboundTrainID, String returnTrainID, String tickets, String travelClass, String ticketType) {        
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/reservation/" + outboundTrainID);
        Form form = new Form();
        form.add("tickets", tickets);
        form.add("travelClass", travelClass);
        form.add("returnTrainID", returnTrainID);
        form.add("ticketType", ticketType);

        try {
            // Create a form with the needed information
            // Send a POST request with the form
        	String response = resource.post(form).getText();
        	System.out.println("Response received from REST service: " + response);
        	
        	if (response.equals("true")) {
        		return "Successful reservation";
        	} else {
        		return "Reservation error or the train" + outboundTrainID + " is not available";
        	}
        } catch (ResourceException e) {
            e.printStackTrace();
            return "Reservation error or the train" + outboundTrainID + " is not available";
        } catch (IOException e) {
            e.printStackTrace();
            return "Reservation error or the train" + outboundTrainID + " is not available";
        }
	}
}