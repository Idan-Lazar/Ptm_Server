package test;

// edit these imports according to your project
import API.ChachManger;
import API.ClientHandler;
import API.MyChachManger;
import API.MyCh;
import API.MyServer;
import API.MySolver;
import API.Server;
import API.Solver;

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		dt.setServerClass(MyServer.class);
		dt.setClientHandlerInterface(ClientHandler.class);
		dt.setClientHandlerClass(MyCh.class);
		dt.setCacheManagerInterface(ChachManger.class);
		dt.setCacheManagerClass(MyChachManger.class);
		dt.setSolverInterface(Solver.class);
		dt.setSolverClass(MySolver.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new MyServer(port);
		s.start(new MyCh());
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}

}

