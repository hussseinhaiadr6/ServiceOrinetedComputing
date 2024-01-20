package Client;
 
import java.io.IOException;
 
import org.restlet.data.Form;
//import org.restlet.data.MediaType;
//import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
 
public class Client2Call {
 
	/**
	 * @param args
	 */
	public static String reserveTrains(String id1, String id2, String Ticket, String Class) {
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/train/"+id1);
        Form form = new Form();
        form.add("tickets", Ticket);
        form.add("travelClass", Class);
        form.add("trainID2",id2);



        try {
            // Create a form with the needed information
                       // Send a POST request with the form
        System.out.println("trying");
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

	public static String FilterTrains() {
		// TODO Auto-generated method stub
		return null;
	}
	
 
}