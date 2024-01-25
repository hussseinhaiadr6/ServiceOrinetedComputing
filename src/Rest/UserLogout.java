package Rest;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation; 
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class UserLogout extends ServerResource {

	@Post
	public Representation logout(Representation entity) {
		Form form = new Form(entity); 
		String username = form.getFirstValue("username");
		
		if (!UsersDB.isUserLoggedIn(username)) {
	        return new StringRepresentation("User is not logged in or the provided username is invalid", MediaType.TEXT_PLAIN);
		}

		UsersDB.updateUserLoginStatus(username, Constants.LOGGED_OUT);
		RouterApplication.removeLocalSecretFromAuthenticatorVerifier(username);

		UsersDB.displayUsers();
		RouterApplication.displayAuthenticatorVerifierLocalSecrets();
		
        return new StringRepresentation("Logged out successfully", MediaType.TEXT_PLAIN);
	}
}
