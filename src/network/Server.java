package network;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import main.Log;

/**
 * @author Sebastian Reith
 */
public class Server {
	public void initServer(){
		
		try{
			Registry reg = LocateRegistry.createRegistry(1099);
			ComImpl impl = new ComImpl();
			reg.rebind("IslandDefender", impl);
			Log.i("Server.initServer()", "Server ist gestartet");
			String[] eintr = reg.list();
			for(String s : eintr) Log.i("Server.initServer() reg.list()", s);
		}
		catch(Exception e){
			Log.e("Server.initServer()", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] argumenteHastDuKeine){
		new Server().initServer();
	}
}
