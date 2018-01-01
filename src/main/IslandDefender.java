package main;

import controller.GUIController;
import view.GUI;
import view.StartWindow;
import model.World;

/**
 * This class launches the game "Island Defender".
 * 
 * @author Daniel Vedder
 * @author Peter Zilz
 * @author Sebastian Reith
 * @author Christopher Borchert
 * @date 02.01.2014
 * @date 06.08.2014
 */
public class IslandDefender
{
	GUI gui;
	World world;
	GUIController controller;
	
	/**
	 * The constructor.
	 */
	public IslandDefender()
	{
		startup();
		world = new World();
		gui = new GUI(world); 
		controller = new GUIController();
		
		gui.setGUIController(controller);
	}
	
	/**
	 * The main() method - initialize a new instance of the game.
	 * @param args Not needed
	 */
	public static void main(String[] args)
	{
		IslandDefender id = new IslandDefender();
	}
	
	/**
	 * Start the game.
	 */
	public void startup()
	{
		StartWindow start = new StartWindow(this);
	}
}
