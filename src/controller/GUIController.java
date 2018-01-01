package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import main.Log;
import model.World;
import view.GUI;

/**
 * 
 * @author peter
 * @date 6.8.14
 */
public class GUIController implements ActionListener, WindowListener{
	
	
	//check if plans are possible
	//collect planned actions
	//show planned actions
	//send plans and game state to model
	//receive new game state
	//update view to new game state
	//change from planned to done a.k.a. normal
	
	private GUI gui;
	
	private World world;
	
	private Set<PlannedAction> actionCollector;
	
	private MapDrawerController mapDrawerController;
	private ChatController chatController;
	private TradeController tradeController;
	
	public GUIController(){
		actionCollector = new HashSet<PlannedAction>();
		mapDrawerController = new MapDrawerController(this);
		chatController = new ChatController(this);
		tradeController = new TradeController(this);
	}
	
	public void addPlannedAction(PlannedAction plan){
		actionCollector.add(plan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ae = e.getActionCommand();
		
		if(ae.equals(GUI.FINISH_ROUND)){
			Log.v("GUI", "Finishing round...");
			//TODO gather all data
			//TODO send it to the model
			//TODO receive new game state
			//TODO update view
		}
		else if(ae.equals(GUI.NEW_GAME)){
			//TODO ...
		}
		else if(ae.equals(GUI.QUIT)){
			closeWindow();
		}
	}
	
	private void closeWindow(){
		int click = JOptionPane.showConfirmDialog(gui, 
				"Wirklich beenden?", 
				"Wirklich beenden?", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE);
		if(click == JOptionPane.YES_OPTION){
			//TODO maybe save game state or something?
			System.exit(0);
		}
	}
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		//invoked after the window is already closed.
//		closeWindow();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		//invoked if someone hits Alt-F4
		closeWindow();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
	
	
	
	//------------getter und setter-----------
	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public MapDrawerController getMapDrawerController() {
		return mapDrawerController;
	}

	public void setMapDrawerController(MapDrawerController mapDrawerController) {
		this.mapDrawerController = mapDrawerController;
	}

	public ChatController getChatController() {
		return chatController;
	}

	public void setChatController(ChatController chatController) {
		this.chatController = chatController;
	}

	public TradeController getTradeController() {
		return tradeController;
	}

	public void setTradeController(TradeController tradeController) {
		this.tradeController = tradeController;
	}

}
