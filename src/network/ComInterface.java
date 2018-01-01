package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Sebastian Reith
 */
public interface ComInterface extends Remote {
	public void declareWar(int idOfOtherVillage) throws RemoteException;
	
	
}
