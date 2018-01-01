package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ConstructionContextMenu;
import view.ConstructionMenu;

public class ConstructionMenuController implements ActionListener{
	
	private ConstructionContextMenu menu;
	
	private MapDrawerController mapController;
	
	public ConstructionMenuController(){}
	public ConstructionMenuController(MapDrawerController mapDrawerController){
		mapController = mapDrawerController;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent aev) {
		String ae = aev.getActionCommand();
		
		if(ae.equals(ConstructionContextMenu.NOTHING_SELECTED)){
			//do nothing
			System.out.println("nothing selected");
		}
		else if(ae.equals(ConstructionContextMenu.DEMOLISH)){
			mapController.demolish();
		}
		else{
			mapController.build(ae);
		}
		
	}

	public MapDrawerController getMapController() {
		return mapController;
	}
	public void setMapController(MapDrawerController mapController) {
		this.mapController = mapController;
	}




	public void setMenu(ConstructionContextMenu menu) {
		this.menu = menu;
	}
	
	
}
