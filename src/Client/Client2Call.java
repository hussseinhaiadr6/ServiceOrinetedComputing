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
	public static void main(String[] args) {
        // Create the client resource
        ClientResource resource = new ClientResource("http://localhost:8198/train/T001");
        Form form = new Form();
        form.add("tickets", "2");
        form.add("travelClass", "FIRST");
        form.add("trainID2","T004");



        try {
            // Create a form with the needed information
                       // Send a POST request with the form
        System.out.println("trying");
          resource.post(form).write(System.out);
          System.out.println("Done!");
        

        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
 
}