package Rest;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class UserRegister extends ServerResource {
	@Post
	public Representation register(Representation entity) {
		Form form = new Form(entity); 
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");

		if (!UsersDB.isUsernameAvailable(username)) {
	        return new StringRepresentation("Username " + username + " already exists", MediaType.TEXT_PLAIN);
		}
		
		UsersDB.addUser(username, password);

		UsersDB.displayUsers();
		RouterApplication.displayAuthenticatorVerifierLocalSecrets();

		return new StringRepresentation("Registered successfully", MediaType.TEXT_PLAIN);
	}
}
