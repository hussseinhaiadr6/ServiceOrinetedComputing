package Rest;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class UserLogin extends ServerResource {
	
	@Post
	public Representation login(Representation entity) {
		Form form = new Form(entity); 
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");

		if (!UsersDB.validateCredentials(username, password)) {
			 return new StringRepresentation("Incorrect username or password", MediaType.TEXT_PLAIN);
		}
		
		UsersDB.updateUserLoginStatus(username, Constants.LOGGED_IN);
		RouterApplication.addLocalSecretToAuthenticatorVerifier(username, password);

		UsersDB.displayUsers();
		RouterApplication.displayAuthenticatorVerifierLocalSecrets();
		
        return new StringRepresentation("Logged in successfully", MediaType.TEXT_PLAIN);
		
	}

}
