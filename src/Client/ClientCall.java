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
	public static String FilterTrains(String Start,String deprt, String depDate,String arrivDate, String Ticket, String Class) {
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/train");
        Form form = new Form();
        form.add("departureStation", Start);
        form.add("arrivalStation", deprt);
        form.add("departureDate", depDate);
        form.add("arrivalDate", arrivDate);
        form.add("tickets", Ticket);
        form.add("travelClass", Class);



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