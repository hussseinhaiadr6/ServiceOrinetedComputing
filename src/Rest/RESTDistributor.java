package Rest;

import org.restlet.Server;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.naming.Context;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.restlet.Component;
import org.restlet.data.Parameter;
import org.restlet.data.Protocol;
import org.restlet.engine.ssl.SslContextFactory;
import org.restlet.util.Series;

public class RESTDistributor {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Create a new Restlet component and add a HTTP server connector to it  
		Component component = new Component();  
		component.getServers().add(Protocol.HTTP, 8198); 

//		Server server = component.getServers().add(Protocol.HTTP, 8198); 
		
//	    Series<Parameter> parameters = server.getContext().getParameters();

//	    X509TrustManager tm = new X509TrustManager() {
//	        public void checkClientTrusted(X509Certificate[] chain,
//	                        String authType)
//	                        throws CertificateException {
//	        }
//
//	        public X509Certificate[] getAcceptedIssuers() {
//	            return new X509Certificate[0];
//	        }
//
//	        public void checkServerTrusted(X509Certificate[] chain,
//	                        String authType)
//	                        throws CertificateException {
//	            // This will never throw an exception.
//	            // This doesn't check anything at all: it's insecure.
//	        }
//	    };

//	    final SSLContext sslContext = SSLContext.getInstance("TLS");
//	    sslContext.init(null, new TrustManager[] {tm}, null);
//
//	    server.getContext().getAttributes().put("sslContextFactory", new SslContextFactory() {
//	        public void init(Series<Parameter> parameters) { }
//	        public SSLContext createSslContext() { return sslContext; }
//	    });

	    
//        parameters.add("sslContextFactory",
//        "org.restlet.engine.ssl.DefaultSslContextFactory");
//
//        parameters.add("keyStorePath", "C:\\Users\\alina\\OneDrive\\Desktop\\service-oriented-computing\\project\\ServiceOrinetedComputing\\DESKTOP-10BBI55.jks");
//        parameters.add("keyStorePassword", "password");
//        parameters.add("keyPassword", "password");
//        parameters.add("keyStoreType", "JKS");
        
		// Attach the application to the component and start it  
		component.getDefaultHost().attach(new RouterApplication());
		component.start();
	}
}
