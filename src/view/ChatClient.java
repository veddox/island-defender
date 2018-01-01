package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import controller.ChatController;

/**
 * This is the chat client, with which players can communicate during the game.
 * @author Daniel Vedder
 */
public class ChatClient extends JFrame
{
	private Box main; //the main panel
	private JButton send;
	private JTextArea viewer;
	private JTextField enter;
	private JComboBox choose_recipient; //Who should hear what I say?
	private String[] player_list;
	private String player_name; //This player
	
	private ChatController chatController;
	
	/**
	 * The constructor.
	 * @param String player name
	 */
	public ChatClient(String player, String[] playernames)
	{
		this.setTitle("Chat");
		this.setSize(300, 350);
		this.setLocation((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-150, (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-175);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		player_name = player;
		player_list = playernames;
		createLayout();
		receiveMessage("Test", "Alle", "Does this work?"); //For test purposes
	}
	
	/**
	 * Add all the components to the frame.
	 */
	public void createLayout()
	{
		this.add(new JPanel(), BorderLayout.WEST); //The following two are merely for design purposes
		this.add(new JPanel(), BorderLayout.EAST);
		main = new Box(BoxLayout.Y_AXIS);
		this.add(main, BorderLayout.CENTER);
		
		choose_recipient = new JComboBox(player_list); //Add the ComboBox to choose the recipient
		choose_recipient.setSelectedIndex(0);
		
		viewer = new JTextArea(20, 10); //Add the chat viewer
		viewer.setEditable(false);
		viewer.setLineWrap(true);
		viewer.setWrapStyleWord(true);
		viewer.setText("--- Willkommen bei IslandDefender! ---");
		JScrollPane scroller = new JScrollPane(viewer, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		ActionListener sendingListener = new ActionListener() { //This listener sends the message when the user hits ENTER or clicks the send button
			public void actionPerformed(ActionEvent ae) {
				//these lines are mainly for testing purposes!
				viewer.append("\n"+player_name+" @"+choose_recipient.getSelectedItem()+": "+enter.getText());
				sendMessage(choose_recipient.getSelectedItem().toString(), enter.getText());
				enter.setText("");
			}
		};
		
		enter = new JTextField(20); //Add the text field into which new messages are entered
		enter.addActionListener(sendingListener);
		send = new JButton("Nachricht Senden"); //Add the send button
		send.addActionListener(sendingListener);
		
		main.add(Box.createVerticalStrut(10));
		main.add(choose_recipient);
		main.add(Box.createVerticalStrut(10));
		main.add(scroller);
		main.add(Box.createVerticalStrut(10));
		main.add(enter);
		main.add(Box.createVerticalStrut(10));
		main.add(send);
		main.add(Box.createVerticalStrut(10));
	}
	
	/**
	 * Show the chat client.
	 */
	public void showChat()
	{
		setVisible(true);
		enter.requestFocusInWindow();
	}
	
	/**
	 * Send a message to the selected player.
	 * @param String recipient
	 * @param String message
	 */
	public void sendMessage(String recipient, String message)
	{
		//Can't do anything proper just yet
	}
	
	/**
	 * Receive a message and display it
	 * @param String sender
	 * @param String message
	 */
	public void receiveMessage(String sender, String receiver, String message)
	{
		viewer.append("\n"+sender+" @"+receiver+": "+message);
	}
	
	public void setChatController(ChatController controller){
		chatController = controller;
		chatController.setChatClient(this);
		
		//TODO add to elements of this ChatClient which need a controller
	}
}
