package view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.ConstructionMenuController;

/**
 * This class represents a context menu which allows the user to build stuff in his village.
 * @author Daniel Vedder
 */
public class ConstructionContextMenu extends JMenu
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
	
	private ConstructionMenuController controller;
	
	private static final long serialVersionUID = -8620415536704713583L;
	
	private JMenuItem house, farm, blacksmith, weaponsmith, barracks, demolish;
	
	/**
	 * Creates and shows the ConstructionMenu.
	 */
	public ConstructionContextMenu()
	{
		super("Bauen...");
		addMenuItems();
	}
	
	/**
	 * Creates and adds all the entries the menu needs
	 */
	public void addMenuItems()
	{
		house = new JMenuItem("Haus");
		house.setActionCommand(HOUSE);
		farm = new JMenuItem("Bauernhof");
		farm.setActionCommand(FARM);
		blacksmith = new JMenuItem("Schmiede");
		blacksmith.setActionCommand(BLACKSMITH);
		weaponsmith = new JMenuItem("Waffenschmiede");
		weaponsmith.setActionCommand(WEAPONSMITH);
		barracks = new JMenuItem("Kaserne");
		barracks.setActionCommand(BARRACKS);
		demolish = new JMenuItem("Abreissen");
		demolish.setActionCommand(DEMOLISH);
		this.add(house);
		this.add(farm);
		this.add(blacksmith);
		this.add(weaponsmith);
		this.add(barracks);
		this.add(demolish);
	}
	
	
	public void setConstructionMenuController(ConstructionMenuController cmc){
		controller = cmc;
		cmc.setMenu(this);
		
		house.addActionListener(controller);
		farm.addActionListener(controller);
		demolish.addActionListener(controller);
		barracks.addActionListener(controller);
		weaponsmith.addActionListener(controller);
		blacksmith.addActionListener(controller);
	}
	
}
