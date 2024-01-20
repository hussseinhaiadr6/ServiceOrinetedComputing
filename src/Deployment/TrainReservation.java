package Deployment;
import Client.*;
import java.io.IOException;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TrainReservation {
	
		public String reserve(){
			String Result ="";
			Result=Client.Client2Call.reserveTrains("T003", "T002", "1", "BUSINESS");
			 
		        
		        return Result;
		    }
	

}
