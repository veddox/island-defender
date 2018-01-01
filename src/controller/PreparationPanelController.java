package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.IslandDefender;
import view.PreparationPanel;
/**
 * 
 * @author Peter Zilz
 * @date 2.1.2014
 */
public class PreparationPanelController implements ActionListener, KeyListener, ChangeListener{
	
	//Some constants for ActionCommands and such (should be in PreparationPanel)
	public static final String READY_AC = "ready";
	public static final String NOT_READY_AC = "notready";
	public static final String START_GAME_AC = "startGame";
	public static final String CHAT_SEND_AC = "chatSend";
	public static final String LOGOUT_AC = "preparationLogout";
	public static final String COLOR_BUTTON_PREFIX = "colorButton";
	public static final String OWN_NAME_KE = "nameChanged";
	
	
	@SuppressWarnings("unused")
	private IslandDefender main;
	private PreparationPanel panel;
	
	/**
	 * Creates a new Controller for the {@link PreparationPanel}.
	 * @param main superior window
	 * @param panel the panel to control.
	 */
	public PreparationPanelController(IslandDefender main, PreparationPanel panel){
		this.main = main;
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event){
		String ae = event.getActionCommand();
		
		if(ae.startsWith(COLOR_BUTTON_PREFIX)){
			int slot = Integer.parseInt(ae.substring(COLOR_BUTTON_PREFIX.length()));
			panel.colorButtonClicked(slot);
		}
		else if(ae.equals(READY_AC)){
			//TODO send it to the server
			panel.setActivePlayerReady(true);
		}
		else if(ae.equals(NOT_READY_AC)){
			//TODO send it to the server
			panel.setActivePlayerReady(false);
		}
		else if(ae.equals(START_GAME_AC)){
			//TODO what happens when a player clicks the button to start the game
		}
		else if(ae.equals(CHAT_SEND_AC)){
			chatMessageSent();
		}
		else if(ae.equals(LOGOUT_AC)){
			//TODO what happens when a player clicks the logout button
		}
		else{
			//TODO uncomment the following line
			//Log.w("PreparationPanelContoller.actionPerformed(ActionEvent)","Unknown ActionCommand:\n" + ae);
		}
		
	}
	
	/**
	 * Handles the actions when a message from the chat is sent.
	 * --- DISABLED ---
	 * (needs to be shifted somewhere where it helps the standard chat client)
	 */
	private void chatMessageSent(){
		
		//String message = panel.getChatField().getText();
		//TODO send this message to the server
		//panel.sendChatMessage();
		
	}
	
	/**
	 * Informs the server, that the number of players has been changed.
	 */
	public void playerNumberUpdated(){
		//TODO somehow get a list of current players.
		//TODO inform the server, that the number of players was changed.
	}
	
	/**
	 * Informs the server, that a certain player picked a new color.
	 * @param slot list place of the player that changed his color
	 * @param newColor new color for that player
	 */
	public void newColorSelected(int slot, Color newColor){
		//TODO somehow convert slot to player
		//TODO send both to the server 
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}


	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getComponent().getName().equals(OWN_NAME_KE) && !ke.isActionKey()){
			//String newName = panel.getOwnName().getText();
			//TODO send this new name to the server
		}
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		if(ce.getSource() == panel.getPlayerNumber()){
			
			panel.updatePlayerNumber();
			
		}
	}
	
}
