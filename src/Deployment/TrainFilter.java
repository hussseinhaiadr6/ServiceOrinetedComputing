package Deployment;
import Client.*;
import java.io.IOException;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TrainFilter {
	
		public String sayHello(String input){
			String Result ="";
			Result=Client.ClientCall.FilterTrains("StationA","StationB","2023-01-01","2023-01-01","2","FIRST");
			 
		        
		        return Result;
		    }
	

}
