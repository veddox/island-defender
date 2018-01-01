package network;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import model.Player;

public class RmiServerImpl implements IClient {

	@Override
	public void sendChatMessage(String message, Player toPlayer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendChatMessageToPlayers(String message, List<Player> receivers)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendGameState() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendNewPickedPlayerName(String newName, Player renamedPlayer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendNewPickedPlayerColor(Color newColor, Player recoloredPlayer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void engageDiplomaticContract(Player fromPlayer, Player withPlayer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeDiplomaticContract(Player fromPlayer, Player withPlayer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendNewRelationship(Player fromPlayer, Player withPlayer,
			int newDiplomacyState) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void engageBattle(Player attackingPlayer, Player attackedPlayer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void supportBattle(Player attackingPlayer, Player attackedPlayer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServerAddress(InetAddress serverAddress) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public InetAddress getServerAddress() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
