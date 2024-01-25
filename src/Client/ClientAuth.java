package Client;

import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ClientAuth {
	public static String register(String username, String password) {
        ClientResource resource = new ClientResource("http://localhost:8198/register");

        Form form = new Form();
        form.add("username", username);
        form.add("password", password);

        try {
            // Create a form with the needed information
            // Send a POST request with the form
        	String response = resource.post(form).getText();
        	System.out.println("Response: " + response);
        	System.out.println("User " + username + " registered!");
        	return response;
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return null;
	}

	public static String login(String username, String password) {
        ClientResource resource = new ClientResource("http://localhost:8198/login");

        Form form = new Form();
        form.add("username", username);
        form.add("password", password);

        try {
            // Create a form with the needed information
            // Send a POST request with the form
        	String response = resource.post(form).getText();
        	System.out.println("Response: " + response);
        	System.out.println("User " + username + " logged in!");
        	return response;
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return null;
	}
	
	public static String logout(String username) {
        ClientResource resource = new ClientResource("http://localhost:8198/logout");

        Form form = new Form();
        form.add("username", username);

        try {
            // Create a form with the needed information
            // Send a POST request with the form
        	String response = resource.post(form).getText();
        	System.out.println("Response: " + response);
        	System.out.println("User " + username + " logged out!");
        	return response;
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return null;
	}
}
