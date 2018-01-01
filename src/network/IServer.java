package network;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import model.Player;

/**
 * This interface provides methods necessary for uploading Data to the
 * connected clients. 
 * @author Peter Zilz
 *
 */
public interface IServer {
	
	
	
	public void distributeNewGameState(/*TODO new game states for each player*/) 
			throws IOException;
	
	public void forwardChatMessage(String message, List<Player> receivers)
			throws IOException;
	
	public void forwaredChangedName(String newName, Player renamedPlayer)
			throws IOException;
	
	public void forwardChangedColor(Color newColor, Player recoloredPlayer)
			throws IOException;
	
	public void createContract(Player fromPlayer, Player withPlayer /*TODO some contract data */)
			throws IOException;
	public void forwardContractChanges(Player fromPlayer, Player withPlayer /*TODO some contract data */)
			throws IOException;
	
	public void forwardDiplomaticRelationshipChange(Player fromPlayer, Player withPlayer, int newState)
			throws IOException;
	
	/**
	 * If a player opened a battle or changed his support, use this method
	 * to distribute the changes to all (other) players.
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void updateBattleInformation(/*TODO some battle data*/)
			throws IOException;
	
	/**
	 * Returns a list of all currently connected/active clients. The list should not
	 * contain disconnected clients. 
	 * @return list of addresses of clients; maybe empty but not null.
	 * @throws IOExctption if the upload doesn't work correctly
	 */
	public List<InetAddress> getClientAddressList() throws IOException;
	
}
