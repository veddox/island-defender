package network;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import model.Player;
import model.World;
import view.PreparationPanel;

/**
 * This interface provides the methods to use for all messages that go
 * via network to the server.<BR>
 * Downloads can be requested and then {@link IServer} has to send the 
 * requested data.
 * @author Peter Zilz 
 *
 */
public interface IClient {
	
	
	/**
	 * Sends a message to another player. The client doesn't need to know
	 * the receiver's address. He sends the data to the server and
	 * the server has to forward it to the corresponding address.
	 * @param message String that contains the message
	 * @param toPlayer the receiving player.
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void sendChatMessage(String message, Player toPlayer) throws IOException;
	
	/**
	 * Sends a message to a few other players. The client doesn't need to know
	 * the receivers's addresses. He sends the data to the server and
	 * the server has to forward it to the corresponding addresses.<BR>
	 * It is practical to use {@link #sendChatMessage(String, Player)}
	 * to implement this method.
	 * @param message String that contains the message
	 * @param receivers list of players that shall receive the message.
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void sendChatMessageToPlayers(String message, List<Player> receivers) throws IOException;
	
	/**
	 * Sends the game state at the end of a round to the server. This state has
	 * to include all planned actions.
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void sendGameState(/*TODO add some GameState object as input parameter*/) throws IOException;
	
	/**
	 * Sends a name to the server, a player picked for himself. This
	 * is usually done before the game starts in the {@link PreparationPanel}.
	 * @param newName the new name a player picked for himself
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void sendNewPickedPlayerName(String newName, Player renamedPlayer) throws IOException;
	
	/**
	 * Sends a Color to the server, a player picked for himself. This
	 * is usually done before the game starts in the {@link PreparationPanel}.
	 * @param newColor the new Color a player picked for himself
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void sendNewPickedPlayerColor(Color newColor, Player recoloredPlayer) throws IOException;
	
	/**
	 * Creates a new diplomatic contract between two players. They can both
	 * negotiate with one another and change that contract with
	 * {@link #changeDiplomaticContract(Player, Player)} until they both agree
	 * or end the negotiation.
	 * @param fromPlayer the engaging player that makes the first move
	 * @param withPlayer the engaged player
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void engageDiplomaticContract(Player fromPlayer, Player withPlayer /*TODO add some contract object */) throws IOException;
	/**
	 * Changes the current state of an existing contract.
	 * @param fromPlayer the player that makes the changes
	 * @param withPlayer the other participating player
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void changeDiplomaticContract(Player fromPlayer, Player withPlayer /*TODO add some contract object */) throws IOException;
	
	/**
	 * Changes the diplomatic relationship between two players.
	 * @param fromPlayer
	 * @param withPlayer
	 * @param newDiplomacyState the new relationship state, use:<BR>
	 * - {@link World#WAR} or<BR>
	 * - {@link World#PEACE} <BR>
	 * - but not {@link World#CONFEDERATION}, for that a contract is necessary
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void sendNewRelationship(Player fromPlayer, Player withPlayer, int newDiplomacyState) throws IOException;
	
	/**
	 * Create a battle, where one player attacks another.
	 * @param attackingPlayer the player that creates the attack
	 * @param attackedPlayer the player under attack
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void engageBattle(Player attackingPlayer, Player attackedPlayer /*TODO add some attack object*/ ) throws IOException;
	/**
	 * Support a battle with more or fewer troops. Or quit supporting a battle.
	 * @param attackingPlayer the player that sends more or fewer troops.
	 * @param attackedPlayer the player under attack
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void supportBattle(Player attackingPlayer, Player attackedPlayer /*TODO add some attack object*/ ) throws IOException;
	
	
	/**
	 * Sets the Address for the server. If it wasn't already set by the
	 * constructor of an implementing class.
	 * @param serverAddress address of the new server
	 * @throws IOException if the upload doesn't work correctly
	 */
	public void setServerAddress(InetAddress serverAddress) throws IOException;
	/**
	 * Returns the address of the current server.
	 * @return address of the server; null if the server address isn't set yet.
	 * @throws IOException if the upload doesn't work correctly
	 */
	public InetAddress getServerAddress() throws IOException;
	
	
}
