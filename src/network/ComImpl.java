package network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import main.Log;

/**
 * @author Sebastian Reith
 */
public class ComImpl extends UnicastRemoteObject implements ComInterface {

	public ComImpl() throws RemoteException{
		
	}
	
	@Override
	public synchronized void declareWar(int idOfOtherVillage) throws RemoteException {
		Log.i("ComImpl", "Juhu! id == " + idOfOtherVillage);

	}
	

}
