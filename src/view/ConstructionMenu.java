package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.ConstructionMenuController;

/**
 * This class allows the user to build stuff in his village
 * @author Daniel Vedder
 */
public class ConstructionMenu extends JFrame implements ActionListener
{
	
	//Constants for the ActionCommands
	public static final String DONE = "done";
	public static final String HOUSE = "house";
	public static final String FARM = "farm";
	public static final String BLACKSMITH = "blacksmith";
	public static final String WEAPONSMITH = "weaponsmith";
	public static final String BARRACKS = "barracks";
	public static final String DEMOLISH = "demolish";
	public static final String NOTHING_SELECTED = "nothing";
	
	private ActionListener controller;
	
	private static final long serialVersionUID = -8620415536704713583L;
	
	
	//graphical components
	private JPanel main;
	private JButton done;
	private ButtonGroup buttons;
	private JRadioButton house, farm, blacksmith, weaponsmith, barracks, demolish;
	
	/**
	 * Creates the ConstructionMenu.
	 */
	public ConstructionMenu()
	{
		this.setTitle("Baumenü");
		this.setSize(160, 210);
		this.setLocation(200, 150);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.add(new JPanel(), BorderLayout.WEST); //For design purposes
		this.add(new JPanel(), BorderLayout.EAST);
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		this.add(main, BorderLayout.CENTER);
		addRadioButtons();
		done = new JButton("Fertig");
		main.add(done);
		done.setActionCommand(DONE);
		done.addActionListener(this);
	}
	
	/**
	 * Creates and adds all the radio buttons this class needs
	 */
	public void addRadioButtons()
	{
		buttons = new ButtonGroup();
		house = new JRadioButton("Haus");
		house.setActionCommand(HOUSE);
		farm = new JRadioButton("Bauernhof");
		farm.setActionCommand(FARM);
		blacksmith = new JRadioButton("Schmiede");
		blacksmith.setActionCommand(BLACKSMITH);
		weaponsmith = new JRadioButton("Waffenschmiede");
		weaponsmith.setActionCommand(WEAPONSMITH);
		barracks = new JRadioButton("Kaserne");
		barracks.setActionCommand(BARRACKS);
		demolish = new JRadioButton("Abreissen");
		demolish.setActionCommand(DEMOLISH);
		buttons.add(house);
		buttons.add(farm);
		buttons.add(blacksmith);
		buttons.add(weaponsmith);
		buttons.add(barracks);
		buttons.add(demolish);
		main.add(house);
		main.add(farm);
		main.add(blacksmith);
		main.add(weaponsmith);
		main.add(barracks);
		main.add(demolish);
	}
	
	/**
	 * Adds an ActionListener to the done-button. Preferably an {@link ConstructionMenuController}.
	 * @param al Preferably an {@link ConstructionMenuController}.
	 */
	public void setActionListener(ActionListener al){
		controller = al;
	}
	
	/**
	 * Returns the ActionCommand of the selected JRadioButton.
	 * @return ActionCommand of the selected JRadioButton
	 */
	public String getSelected(){
		//Iterate over every button in the ButtonGroup and return the ActionCommand of the selected one.
		AbstractButton ab;
		for(Enumeration<AbstractButton> e = buttons.getElements(); e.hasMoreElements(); ){
			ab = e.nextElement();
			if(ab.isSelected()){
				return ab.getActionCommand();
			}
		}
		
		//If nothing is chosen, then send this error message.
		//Could be the case if the user clicks done before selecting something.
		return NOTHING_SELECTED;
	}

	/**
	 * Reacts to the done button and triggers the {@link ConstructionMenuController}.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(DONE) && controller!=null){
			//Trigger the controller.
			controller.actionPerformed(new ActionEvent(this, 1, getSelected()));
		}
	}
}
