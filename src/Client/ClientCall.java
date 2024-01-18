package Client;
 
import java.io.IOException;
 
import org.restlet.data.Form;
//import org.restlet.data.MediaType;
//import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
 
public class ClientCall {
 public static String Hello() {
	 return "hello from here";
 }

	/**
	 * @param args
	 */
	public static String FilterTrains() {
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/train");
        Form form = new Form();
        form.add("departureStation", "StationA");
        form.add("arrivalStation", "StationB");
        form.add("departureDate", "2023-01-01");
        form.add("arrivalDate", "2023-01-01");
        form.add("tickets", "2");
        form.add("travelClass", "FIRST");



        try {
            // Create a form with the needed information
                       // Send a POST request with the form
        System.out.println("trying");
        String response = resource.post(form).getText();
        System.out.println("Response: " + response);
        System.out.println("Done!");
        return response;
          
        

        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
	
 
}