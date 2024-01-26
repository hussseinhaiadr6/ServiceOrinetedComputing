package Rest;
import org.restlet.*;
import org.restlet.routing.Router;

public class RouterApplication extends Application{

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {        
		// Create a router Restlet that routes each call to a new respective instance of resource.
		Router router = new Router(getContext());
		
		router.attach("/train", TrainResource.class);
		router.attach("/reservation/{outboundTrainID}",TrainManager.class);
				
        return router;
     }
}