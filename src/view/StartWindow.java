package view;

import main.IslandDefender;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

/**
 * This is the very first window that is shown when "Island Defender" is started.
 * It gives the player the option of joining an existing game or starting a new one.
 * 
 * @author Daniel Vedder
 */
public class StartWindow extends JFrame 
{
	private static final long serialVersionUID = 8206656731495433000L;
	Box main_panel, fill_panel1, fill_panel2; //The latter two are merely for layout work...
	JButton new_game, join_game, enter_game;
	JTextField ip_input;
	InetAddress host_ip; //The Host IP address that a player needs to enter should he wish to join a game
	IslandDefender island_defender;
	
	/**
	 * The constructor.
	 * @param IslandDefender id
	 */
	public StartWindow(IslandDefender id)
	{
		island_defender = id;
		this.setTitle("Island Defender - Setup");
		this.setSize(300, 150);
		this.setLocation((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-150, (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-75);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		createComponents();
	}
	
	/**
	 * This method creates all the components that the start window has: two buttons and a text field.
	 */
	private void createComponents()
	{
		fill_panel1 = new Box(BoxLayout.Y_AXIS); //The fill panels are purely for layout purposes
		this.add(fill_panel1, BorderLayout.WEST);
		fill_panel1.add(Box.createHorizontalStrut(45));
		main_panel = new Box(BoxLayout.Y_AXIS);
		this.add(main_panel, BorderLayout.CENTER);
		addButtons();fill_panel2 = new Box(BoxLayout.Y_AXIS); //The fill panels are purely for layout purposes
		this.add(fill_panel2, BorderLayout.EAST);
		fill_panel2.add(Box.createHorizontalStrut(45));
	}
	
	/**
	 * Add the two buttons that the startup window needs: "Join a Game" and "Start a new Game"
	 */
	private void addButtons()
	{
		new_game = new JButton("Ein neues Spiel starten");
		new_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				callPrepPanel(true); //Start the prep panel as a host
			}
		});
		join_game = new JButton("Einem Spiel beitreten");
		join_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addIPMask(); //Call up the IP mask
			}
		});
		main_panel.add(Box.createVerticalStrut(30));
		main_panel.add(new_game);
		main_panel.add(Box.createVerticalStrut(10));
		main_panel.add(join_game);
	}
	
	/**
	 * Add the IP mask: a text field that requires the host IP as input.
	 */
	private void addIPMask()
	{
		setSize(300, 190);
		main_panel.remove(join_game); //Delete the old button
		enter_game = new JButton("Spiel betreten!"); //and add a new one that leads on to the actual game
		enter_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				callPrepPanel(false); //Start the prep panel as a client user
			}
		});
		main_panel.add(enter_game);
		main_panel.add(Box.createVerticalStrut(10));
		main_panel.add(new JLabel("Geben Sie die Host-IP an:"));
		ip_input = new JTextField(20);
		main_panel.add(ip_input);
		main_panel.add(Box.createVerticalStrut(20));
	}
	
	/**
	 * Call up the preparation panel. The panel settings depends on the user input in the start window.
	 * @param boolean isHost
	 */
	public void callPrepPanel(boolean isHost)
	{
		remove(main_panel); //remove all the previous components
		remove(fill_panel1);
		remove(fill_panel2);
		setSize(500, 200);
		this.setLocation((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-250, (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-100);
		add(new PreparationPanel(isHost, island_defender)); //and add the preparation panel
	}
}
