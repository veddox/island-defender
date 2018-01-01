package network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;

import main.Log;

/**
 * @author Sebastian Reith
 */
public class Client {
	
	private InetAddress ip;
	
	public Client(InetAddress ip){
		this.ip = ip;
	}
	
	public void declareWar(int idOfOtherVillage){
		try{
			ComInterface impl = (ComInterface) Naming.lookup("rmi://" + ip.getHostAddress() + ":1099/IslandDefender");
			impl.declareWar(idOfOtherVillage);
		}
		catch(Exception e){
			Log.e("Client.connect()", "Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Client testClient = null;
		try {
			testClient = new Client(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			Log.e("Client.main()", "Error bei InetAddress.getByName: "+ e.getMessage());
			e.printStackTrace();
		}
		Log.i("Client.main()", "Client gestartet");
		testClient.declareWar(2);
		Log.i("Client.main()", "Remote-Method aufgerufen");
	}
}
