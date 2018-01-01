package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Log;
import controller.TradeController;

/**
 * Players can send each other goods using this trading post.
 * @author Daniel Vedder
 */
public class TradingPost extends JFrame
{
	private Box main; //the main panel
	private JButton confirm;
	private JTextField enter;
	private JComboBox choose_recipient, choose_item; //Whom do I want to trade with?
	private String[] player_list;
	private String player_name; //This player
	
	private TradeController tradeController;
	
	/**
	 * The constructor.
	 * @param String player name
	 */
	public TradingPost(String player, String[] playernames)
	{
		this.setTitle("Handelsposten");
		this.setSize(300, 190);
		this.setLocation(300, 100);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		player_name = player;
		player_list = playernames;
		createLayout();
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
		String[] items = {"Geld", "Essen", "Werkzeuge", "Waffen"};
		choose_item = new JComboBox(items);
		choose_item.setSelectedIndex(0);
		
		enter = new JTextField(20); //Add the text field into which new messages are entered
		confirm = new JButton("Bestätigen"); //Add the send button
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int submit = JOptionPane.showConfirmDialog(null, choose_recipient.getSelectedItem()+" bekommt "+enter.getText()+" "+choose_item.getSelectedItem()+"?", "Handelsposten", JOptionPane.YES_NO_OPTION);
				if (submit == JOptionPane.YES_OPTION) {
					//NEEDS WORK!
					Log.i("Handelsposten", "Handel getätigt! "+choose_recipient.getSelectedItem()+" bekommt "+enter.getText()+" "+choose_item.getSelectedItem()+".");
					setVisible(false);
				}
			}
		});
		
		main.add(Box.createVerticalStrut(10));
		main.add(choose_recipient);
		main.add(Box.createVerticalStrut(10));
		main.add(choose_item);
		main.add(Box.createVerticalStrut(10));
		main.add(new JLabel("Anzahl:"));
		main.add(Box.createVerticalStrut(10));
		main.add(enter);
		main.add(Box.createVerticalStrut(10));
		main.add(confirm);
		main.add(Box.createVerticalStrut(10));
	}
	
	public void setTradeController(TradeController controller){
		tradeController = controller;
		tradeController.setTradingPost(this);
		
		//TODO add to elements of TradingPost which need a controller
	}
}
