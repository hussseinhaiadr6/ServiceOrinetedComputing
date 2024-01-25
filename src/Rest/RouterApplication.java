package Rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentMap;

import org.restlet.*;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.*;

public class RouterApplication extends Application{
    private static MapVerifier verifier = new MapVerifier();
    private ChallengeAuthenticator authenticator;

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
        this.authenticator = createAuthenticator();
        
		// Create a router Restlet that routes each call to a new respective instance of resource.
		Router router = new Router(getContext());

		router.attach("/train", TrainResource.class);
		router.attach("/train/{TrainID}",TrainManager.class);
		
		// user authentication
		router.attach("/register", UserRegister.class);
		router.attach("/login", UserLogin.class);
		router.attach("/logout", UserLogout.class);
		
        authenticator.setNext(router);
        return authenticator;
     }
	

    private ChallengeAuthenticator createAuthenticator() {
        Context context = getContext();
        boolean optional = true;
        ChallengeScheme challengeScheme = ChallengeScheme.HTTP_BASIC;
        String realm = "Example site";

        loadLocalSecrets();

        ChallengeAuthenticator auth = new ChallengeAuthenticator(context, optional, challengeScheme, realm, verifier) {
            @Override
            protected boolean authenticate(Request request, Response response) {
                if (request.getChallengeResponse() == null) {
                    return false;
                } else {
                    return super.authenticate(request, response);
                }
            }
        };

        return auth;
    }
    
    public static void addLocalSecretToAuthenticatorVerifier(String username, String password) {
        verifier.getLocalSecrets().put(username, password.toCharArray());
    }
    
    public static void removeLocalSecretFromAuthenticatorVerifier(String username) {
        verifier.getLocalSecrets().remove(username);
    }


    public static void displayAuthenticatorVerifierLocalSecrets() {
    	System.out.println("Authenticator Map Verifier Local Secrets:");
    	ConcurrentMap<String, char[]> map = verifier.getLocalSecrets();
        for (String key : map.keySet()) {
        	System.out.println(key + " : " + String. valueOf(map.get(key)));
        }
    }
    
    public boolean authenticate(Request request, Response response) {
        if (!request.getClientInfo().isAuthenticated()) {
            authenticator.challenge(response, false);
            return false;
        }
        return true;
    }
    
    private void loadLocalSecrets() {
		try {
			Connection connection = DriverManager.getConnection(Constants.JDBC_URL);
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * from users WHERE isLogged = ?");
			preparedStatement.setInt(1, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			verifier.getLocalSecrets().put("admin", "admin".toCharArray());

			while (resultSet.next()) {
				String username = resultSet.getString("username");
				char[] password = resultSet.getString("password").toCharArray();
				verifier.getLocalSecrets().put(username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}