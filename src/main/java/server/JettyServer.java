package server;

import org.eclipse.jetty.server.Server;

public class JettyServer {
	public static void main(String[] args) {
		Server server = new Server(8080);
		
		Handler handler = new Handler();
		server.setHandler(handler);
		
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
