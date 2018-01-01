package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import main.IslandDefender;
import main.Log;
import model.Player;
import controller.PreparationPanelController;
/**
 * The PreparationPanel is the view right before the game starts. Here the
 * user can chat with the other players. Change his color or his name.<BR>
 * The host can configure the server. He can change the number of players or 
 * kick players (not implemented yet).
 * @author Peter Zilz
 * @date 2.1.2014
 */
public class PreparationPanel extends JPanel{

	private static final long serialVersionUID = 6186877502795991190L;
	/** The text that is displayed in the drop down menu of open slots. */
	public static final String OPEN_CB = "Offen";
	/** The text that is displayed, when a players status says 'ready'. */
	public static final String READY_OK = "ok";
	/** The text that is displayed, when a players status says 'not ready'. */
	public static final String READY_NOT = "";
	/** The name a player gets, when he hasn't picked a name yet. */
	public static final String DEFAULT_PLAYER_NAME = "Hans";
	/** The text the color button has. You shouldn't see it, because
	 * foreground and background always have the same color. */
	public static final String COLOR_BUTTON_TEXT = "#";
	/** The default number of players, when opening a game. */ 
	public static final int DEFAULT_PLAYER_NUMBER = 4;
	
	/** the label that says 'Spielerzahl' and describes what the JSpinner does.*/ 
	private JLabel playerCounterLabel;
	/** Determines how many players can participate in the game.
	 * Minimum is one, maximum is determined by {@link Player#MAX_PLAYER_NUMBER}.
	 * Only the host can change it, for all others it is disabled. */
	private JSpinner playerNumber;
	/** With the start button one can signal if he is ready, not ready or start the game. */
	private JButton startButton;
	
	/** To display the text of the ongoing chat. */
	private JTextArea chatArea;
	/** TextField to type your messages. */
	private JTextField chatField;
	/** To send the message */
	private JButton chatSendButton;
	
	/** A list of buttons, which have the color of each player. */
	private JButton[] colorButtons;
	/** to edit your own name */
	private JTextField ownName;
	/** A list of labels to signal which player is ready. */
	private JLabel[] readyLabels;
	/** A list with dropdown menus for the other players */
	private List<JComboBox<String>> otherPlayers;
	/** A list with Panels that contain a JComboBox or the JTextField for the own name.*/
	private JPanel[] playerNames;
	/** The Panel that contains the list or players.*/
	private JPanel listPanel;
	
	/** to retreat from the game before it starts.*/
	private JButton logoutButton;
	
	/** stores if the user of this panel created the game. Then he is the host. */
	private boolean isHost;
	/** acts as a superior controller */
	private IslandDefender main;
	/** controller of this panel */
	private PreparationPanelController listener;
	private Player activePlayer;
	private int slotOfActivePlayer = -1;
	
	/**
	 * Creates a new preparation panel. The players have yet to be added,
	 * including the active player.
	 * After calling this constructor you have to add all players given by 
	 * the server with {@link #addPlayer(Player)} and after that add the
	 * active player who just joined the game with {@link #addActivePlayer(Player)}.
	 * @param isHost indicates if this game was just started and the user is the host or if the user just logged on to it.
	 * @param main the main class which invokes this constructor and acts as a listener.  
	 */
	public PreparationPanel(boolean isHost, IslandDefender main){
		this.isHost = isHost;
		this.main = main;
		listener = new PreparationPanelController(main, this);
		
		initializeElements();
		arrangeElements();
	}
	
	/**
	 * Initializes all necessary gui elements.
	 */
	private void initializeElements(){
		
		/*the label and spinner, where the host can determine how many players 
		 * this game can have. */
		playerCounterLabel = new JLabel("Spielerzahl");
		//the spinner is set to 4 by default, has minimum 1, has maximum the maximal player number and has stepsize 1
		playerNumber = new JSpinner(new	SpinnerNumberModel(DEFAULT_PLAYER_NUMBER, 1, model.Player.MAX_PLAYER_NUMBER, 1));
		if(!isHost){
			playerNumber.setEnabled(false);
		}
		else{
			playerNumber.addChangeListener(listener);
		}
		
		//the start button
		startButton = new JButton("Bereit");
		startButton.setActionCommand(PreparationPanelController.READY_AC);
		startButton.addActionListener(listener);
		if(isHost){
			startButton.setText("Spiel starten");
			startButton.setActionCommand(PreparationPanelController.START_GAME_AC);
		}
		
		//initializing the chat elements - NOT NEEDED (we already have a chat client)
//		chatArea = new JTextArea();
//		chatArea.setAutoscrolls(true);
//		chatArea.setEditable(false);
//		chatField = new JTextField();
//		chatField.addActionListener(listener);
//		chatField.setActionCommand(PreparationPanelController.CHAT_SEND_AC);
//		chatSendButton = new JButton("Senden");
//		chatSendButton.setActionCommand(PreparationPanelController.CHAT_SEND_AC);
//		chatSendButton.addActionListener(listener);
		
		//initialize the list with the slots for the players.
		colorButtons = new JButton[DEFAULT_PLAYER_NUMBER];
		for(int i=0;i<colorButtons.length;i++){
			colorButtons[i] = new JButton(COLOR_BUTTON_TEXT);
			//The default color for open slots is white
			colorButtons[i].setForeground(Color.white);
			colorButtons[i].setBackground(Color.white);
			colorButtons[i].addActionListener(listener);
			colorButtons[i].setActionCommand(PreparationPanelController.COLOR_BUTTON_PREFIX+i);
			//Only the host can change the color of otherplayers.
			if(!isHost) colorButtons[i].setEnabled(false);
		}
		ownName = new JTextField();
		ownName.addKeyListener(listener);
		ownName.setName(PreparationPanelController.OWN_NAME_KE);
		readyLabels = new JLabel[DEFAULT_PLAYER_NUMBER];
		for(int i=0;i<readyLabels.length;i++)
			readyLabels[i] = new JLabel(READY_NOT);
		otherPlayers = new Vector<JComboBox<String>>();
		JComboBox<String> it;
		for(int i=0;i<DEFAULT_PLAYER_NUMBER;i++){
			it = new JComboBox<String>();
			it.addItem(OPEN_CB);
			it.setEditable(false);
			if(!isHost) it.setEnabled(false);
			otherPlayers.add(it);
		}
		playerNames = new JPanel[DEFAULT_PLAYER_NUMBER];
		for(int i=0;i<playerNames.length;i++){
			playerNames[i] = new JPanel();
			playerNames[i].setLayout(new BorderLayout());
			playerNames[i].add(otherPlayers.get(i), BorderLayout.CENTER); 
		}
		
		//last but not least the logout button to back out.
		logoutButton = new JButton("Ausloggen");
		logoutButton.setActionCommand(PreparationPanelController.LOGOUT_AC);
		logoutButton.addActionListener(listener);
		
	}
	
	/**
	 * Arranges all gui elements to design this panel.
	 */
	private void arrangeElements(){
		
		setLayout(new FlowLayout());
		add(playerCounterLabel);
		add(playerNumber);
		add(startButton);
		
		arrangePlayerList();
		add(listPanel);
		
		//add(arrangeChat());
		
		add(logoutButton);
	}
	
	/*
	 * Arranges the gui elements for the chat. 
	 * @return JPanel that contains the chat elements.
	 * 
	 * ---DISABLED--- 
	 * (We already have a chat client)
	 *
	private JPanel arrangeChat(){
		
		JPanel chat = new JPanel();
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		
		JPanel chatAreaPanel = new JPanel();
		chatAreaPanel.setLayout(new BoxLayout(chatAreaPanel, BoxLayout.X_AXIS));
		JScrollPane jsp = new JScrollPane(chatArea);
		jsp.setAutoscrolls(true);
		chatAreaPanel.add(jsp);
		chatAreaPanel.add(Box.createVerticalStrut(400));
		
		chat.add(chatAreaPanel);
		
		JPanel sendLine = new JPanel();
		sendLine.setLayout(new BoxLayout(sendLine, BoxLayout.X_AXIS));
		sendLine.add(chatField);
		sendLine.add(Box.createHorizontalStrut(5));
		sendLine.add(chatSendButton);
		
		chat.add(Box.createHorizontalStrut(300));
		chat.add(Box.createVerticalStrut(5));
		chat.add(sendLine);
		
		return chat;
		
	}*/
	
	//TODO implement the methods to kick a player from the server
	
	/**
	 * Arranges the list, in which all players can be listed.
	 * @return JPanel with a list for all participating players
	 */
	private void arrangePlayerList(){
		
		/* 
		 * This Panel uses the BoxLayout.
		 */
		
		int pc = colorButtons.length; //how many players
		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		JPanel row;
		//build the list row by row
		for(int i=0;i<pc;i++){
			row = new JPanel();
			row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
			
			row.add(colorButtons[i]);
			row.add(Box.createHorizontalStrut(5));
			row.add(playerNames[i]);
			row.add(Box.createHorizontalStrut(5));
			row.add(readyLabels[i]);
			
			listPanel.add(row);
		}
	}
	
	/**
	 * Adds or removes slots, so that the number of slots matches the number
	 * of the JSpinner.
	 */
	public void updatePlayerNumber(){
		
		int newNumber = (int)playerNumber.getValue();
		if(newNumber == colorButtons.length) return; //Nothing to do here.
		if(newNumber > colorButtons.length){
			//add enough slots until they match the number in the JSpinner
			while(newNumber > colorButtons.length){
				addSlot();
			}
			//notify controller and server
			listener.playerNumberUpdated();
		}
		else{
			//remove slots if possible
			while(newNumber < colorButtons.length){
				if(removeSlot()){
					//notify controller and server
					listener.playerNumberUpdated();
				}
				//If removal isn't possible, don't try it again.
				else{
					//set playerCounter back to its original state
					playerNumber.setValue(colorButtons.length);
					break;
				}
			}
			
		}
		
	}
	
	/**
	 * Adds an open slot at the end of the list.
	 */
	private void addSlot(){
		//ColorButtons
		JButton[] newColorButtons = new JButton[colorButtons.length+1];
		System.arraycopy(colorButtons, 0, newColorButtons, 0, colorButtons.length);
		colorButtons = newColorButtons;
		JButton newButton = new JButton(COLOR_BUTTON_TEXT);
		newButton.setBackground(Color.white);
		newButton.setForeground(Color.white);
		newButton.addActionListener(listener);
		newButton.setActionCommand(PreparationPanelController.COLOR_BUTTON_PREFIX+(colorButtons.length-1));
		if(!isHost) newButton.setEnabled(false);
		colorButtons[colorButtons.length-1] = newButton;
		
		//playerNames
		JComboBox<String> it = new JComboBox<String>();
		it.addItem(OPEN_CB);
		it.setEditable(false);
		if(!isHost) it.setEnabled(false);
		otherPlayers.add(it);
		
		JPanel[] newPlayerNames = new JPanel[playerNames.length+1];
		System.arraycopy(playerNames, 0, newPlayerNames, 0, playerNames.length);
		playerNames = newPlayerNames;
		JPanel newName = new JPanel();
		newName.setLayout(new BorderLayout());
		newName.add(otherPlayers.get(playerNames.length-1), BorderLayout.CENTER);
		playerNames[playerNames.length-1] = newName;
		
		//readyLabels
		JLabel[] newReadyLabels = new JLabel[readyLabels.length+1];
		System.arraycopy(readyLabels, 0, newReadyLabels, 0, readyLabels.length);
		readyLabels = newReadyLabels;
		readyLabels[readyLabels.length-1] = new JLabel(READY_NOT);
		
		//forge elements of the slot together to one row
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
		row.add(colorButtons[colorButtons.length-1]);
		row.add(Box.createHorizontalStrut(5));
		row.add(playerNames[playerNames.length-1]);
		row.add(Box.createHorizontalStrut(5));
		row.add(readyLabels[readyLabels.length-1]);
		
		//add new row to listPanel
		listPanel.setVisible(false);
		listPanel.add(row);
		listPanel.setVisible(true);
	}
	
	/**
	 * Removes the last slot if it is open.
	 * @return true if successfully removed; false if nothing was removed.
	 */
	private boolean removeSlot(){
		//check if it is possible to remove the last slot
		String selected = (String)otherPlayers.get(otherPlayers.size()-1).getSelectedItem();
		System.out.println(selected);
		if(!selected.equals(OPEN_CB)) return false;
		
		//remove last row from listPanel
		listPanel.setVisible(false);
		listPanel.remove(listPanel.getComponentCount()-1);
		listPanel.setVisible(true);
		
		//remove last from ColorButtons
		JButton[] newColorButtons = new JButton[colorButtons.length-1];
		System.arraycopy(colorButtons, 0, newColorButtons, 0, colorButtons.length-1);
		colorButtons = newColorButtons;
		
		//remove last from playerNames
		JPanel[] newPlayerNames = new JPanel[playerNames.length-1];
		System.arraycopy(playerNames, 0, newPlayerNames, 0, playerNames.length-1);
		playerNames = newPlayerNames;
		
		//remove last from otherPlayers
		otherPlayers.remove(otherPlayers.size()-1);
		
		//remove last from readyLabels
		JLabel[] newReadyLabels = new JLabel[readyLabels.length-1];
		System.arraycopy(readyLabels, 0, newReadyLabels, 0, readyLabels.length-1);
		readyLabels = newReadyLabels;
		
		System.out.println("weg");
		return true;
	}
	
	/**
	 * Adds a new player to the list of participating players.
	 * The color button is changed to his team color and
	 * the readyLabel is set to blank.
	 * If all slots are already filled nothing is done.
	 * @param newPlayer the new player
	 * @return true if successfully added; false if not.
	 */
	public boolean addPlayer(Player newPlayer){
		/*
		 * add to the first JComboBox that doesn't say "Offen"
		 * set that ComboBox to this Players Name
		 * set the color button in front of this comboBox to the color of this player
		 * if every Box is already taken, return 'false'
		 */
		int i=0;
		for(JComboBox<String> comboBox: otherPlayers){
			/* In the row where the active player is located, there is 
			 * no comboBox where this routine can check for open slots.
			 */
			if(i==slotOfActivePlayer){
				i++;
				continue;
			}
			
			if(((String)comboBox.getSelectedItem()).equals(OPEN_CB)){
				comboBox.addItem(newPlayer.getName());
				comboBox.setSelectedItem(newPlayer.getName());
				readyLabels[i].setText(READY_NOT);
				colorButtons[i].setBackground(newPlayer.getTeamColor());
				colorButtons[i].setForeground(newPlayer.getTeamColor());
				//Nobody should be able to change the color of another player.
				colorButtons[i].setEnabled(false);
				return true;
			}
			
			i++;
		}
		return false;
	}
	
	/**
	 * Adds the active Player to the list. {@link #slotOfActivePlayer} is
	 * initialized. The JComboBox in his slot is replaced with a JTextField
	 * to enter a name.
	 * @param newPlayer the active player.
	 */
	public void addActivePlayer(Player newPlayer){
		activePlayer = newPlayer;
		
		for(int i=0;i<playerNames.length;i++){
			if(otherPlayers.get(i).getSelectedItem().equals(OPEN_CB)){
				slotOfActivePlayer = i;
				playerNames[slotOfActivePlayer].removeAll();
				playerNames[slotOfActivePlayer].add(ownName);
				//perhaps the active player doesn't have a name yet.
				if(newPlayer.getName()!=null)ownName.setText(newPlayer.getName());
				else ownName.setText(DEFAULT_PLAYER_NAME);
				readyLabels[slotOfActivePlayer].setText(READY_NOT);
				colorButtons[i].setForeground(newPlayer.getTeamColor());
				colorButtons[i].setBackground(newPlayer.getTeamColor());
				
				//The player has to be able to edit his own color.
				colorButtons[i].setEnabled(true);
				break;
			}
		}
		
	}
	
	/**
	 * Changes the color of the colorButton in the specified slot.
	 * A color another player already has is not assigned a second time.
	 * @param slot slot where the color has to be changed.
	 */
	public void colorButtonClicked(int slot){
		Color first = colorButtons[slot].getBackground();
		int newID = Player.colorToDefaultID(first)+1;
		Color second = Player.idToDefaultColor(newID);
		
		//check that no players have this color already, open slots are allowed, they are simply changed
		for(int i=0;i<colorButtons.length;i++){
			if(i==slot) continue;
			
			if(colorButtons[i].getBackground().equals(second) &&
					!otherPlayers.get(i).getSelectedItem().equals(OPEN_CB)){
				newID++;
				second = Player.idToDefaultColor(newID);
				i=0;
			}
		}
		
		//give new Color
		changeColorButton(slot, second);
		
		//send to server
		listener.newColorSelected(slot, second);
		
	}
	
	/**
	 * Changes the color of a player.
	 * Doesn't change the model, only the view!
	 * @param slot slot of the player
	 * @param newColor new color for the player
	 */
	public void changeColorButton(int slot, Color newColor){
		colorButtons[slot].setForeground(newColor);
		colorButtons[slot].setBackground(newColor);
	}
	
	
	//Note from Daniel: we already have a chat client
	/*
	 * Displays a received chat message.
	 * @param playerName the name of the sender
	 * @param message the message
	 *
	public void receiveChatMessage(String playerName, String message){
		chatArea.setText(chatArea.getText()+playerName+": "+message+"\n");
	}
	
	/**
	 * If the user sent a message, the chatField is cleared and the text is 
	 * added in the chatArea above.
	 * As sender the name in the ownName textfield is displayed.
	 *
	public void sendChatMessage(){
		chatArea.setText(chatArea.getText()+ownName.getText()+": "+chatField.getText()+"\n");
		chatField.setText("");
	}*/
	
	/**
	 * If true the startButton changes its text to "Nicht bereit", 
	 * if false the startButton changes its caption to "Bereit".
	 * The label is changed to "OK" or blank.
	 * @param isReady the state the user just switched to
	 */
	public void setActivePlayerReady(boolean isReady){
		if(slotOfActivePlayer<0){
			Log.e("PreperationPanel.setActivePlayerReady(boolean)", 
					"Active Player isn't added yet!");
			return ;
		}
		setPlayerReady(slotOfActivePlayer,isReady);
	}
	
	/**
	 * Sets the status of a player to ready or not ready. Displays status.
	 * Enables or disables the textfield for the players name and his color button.
	 * @param slot specifies which player
	 * @param isReady true - change to ready; false - change to not ready
	 */
	public void setPlayerReady(int slot, boolean isReady){
		if(isReady){
			startButton.setText("Nicht bereit");
			startButton.setActionCommand(PreparationPanelController.NOT_READY_AC);
			readyLabels[slot].setText(READY_OK);
			ownName.setEnabled(false);
			colorButtons[slot].setEnabled(false);
		}
		else{
			startButton.setText("Bereit");
			startButton.setActionCommand(PreparationPanelController.READY_AC);
			readyLabels[slot].setText(READY_NOT);
			ownName.setEnabled(true);
			colorButtons[slot].setEnabled(true);
		}
	}

	public JLabel getPlayerCounterLabel() {
		return playerCounterLabel;
	}

	public void setPlayerCounterLabel(JLabel playerCounterLabel) {
		this.playerCounterLabel = playerCounterLabel;
	}

	public JSpinner getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(JSpinner playerNumber) {
		this.playerNumber = playerNumber;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JTextArea getChatArea() {
		return chatArea;
	}

	public void setChatArea(JTextArea chatArea) {
		this.chatArea = chatArea;
	}

	public JTextField getChatField() {
		return chatField;
	}

	public void setChatField(JTextField chatField) {
		this.chatField = chatField;
	}

	public JButton getChatSendButton() {
		return chatSendButton;
	}

	public void setChatSendButton(JButton chatSendButton) {
		this.chatSendButton = chatSendButton;
	}

	public JButton[] getColorButtons() {
		return colorButtons;
	}

	public void setColorButtons(JButton[] colorButtons) {
		this.colorButtons = colorButtons;
	}

	public JTextField getOwnName() {
		return ownName;
	}

	public void setOwnName(JTextField ownName) {
		this.ownName = ownName;
	}

	public JLabel[] getReadyLabels() {
		return readyLabels;
	}

	public void setReadyLabels(JLabel[] readyLabels) {
		this.readyLabels = readyLabels;
	}

	public List<JComboBox<String>> getOtherPlayers() {
		return otherPlayers;
	}

	public void setOtherPlayers(List<JComboBox<String>> otherPlayers) {
		this.otherPlayers = otherPlayers;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public void setLogoutButton(JButton logoutButton) {
		this.logoutButton = logoutButton;
	}

	public boolean isHost() {
		return isHost;
	}

	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}

	public IslandDefender getMain() {
		return main;
	}

	public void setMain(IslandDefender main) {
		this.main = main;
	}

	public PreparationPanelController getListener() {
		return listener;
	}

	public void setListener(PreparationPanelController listener) {
		this.listener = listener;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}
	
	
}
