package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MapDrawer;

/**
 * 
 * @author peter
 * @date 6.8.14
 */
public class IslandController implements ActionListener{
	
	
	private MapDrawer island;
	private MapDrawerController mapController;

	public IslandController(MapDrawerController mapController){
		this.mapController = mapController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO do something useful
		System.out.println("Did something on the island map.");
	}
	
	public MapDrawer getIsland() {
		return island;
	}

	public void setIsland(MapDrawer island) {
		this.island = island;
	}

}
