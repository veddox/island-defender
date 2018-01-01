package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ConstructionMenu;
import view.MapDrawer;

/**
 * 
 * @author peter
 * @date 6.8.14
 */
public class VillageController implements ActionListener{
	
	//TODO delete guiController
	private GUIController guiController;
	
	private MapDrawer village;
	
	private MapDrawerController mapController;
	
	public VillageController(MapDrawerController mapController){
		this.mapController = mapController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ae = e.getActionCommand();
		
		if(ae.equals(ConstructionMenu.DONE)){
			//TODO and now???
			System.out.println("Action Selected");
		}
		
//		if(ae.equals(ConstructionMenu.BARRACKS)){
//			//TODO check that there can be build a barrack
//			int[] pos = village.getPopupMousePosition();
//			PlannedAction pa = createBuildingPlan(pos, ConstructionMenu.BARRACKS);
//			guiController.addPlannedAction(pa);
//			
//			System.out.println("build barracks at ("+pos[0]+", "+pos[1]+")");
//		}
//		else if(ae.equals(ConstructionMenu.BLACKSMITH)){
//			//TODO build blacksmith
//			System.out.println("build blacksmith");
//		}
//		else if(ae.equals(ConstructionMenu.DEMOLISH)){
//			//TODO demolish building
//			System.out.println("demolish building");
//		}
//		else if(ae.equals(ConstructionMenu.FARM)){
//			//TODO build farm
//			System.out.println("build farm");
//		}
//		else if(ae.equals(ConstructionMenu.HOUSE)){
//			//TODO build house
//			System.out.println("build house");
//		}
//		else if(ae.equals(ConstructionMenu.WEAPONSMITH)){
//			//TODO build weaponsmith
//			System.out.println("build weaponsmith");
//		}
		
		if(ae.equals(ConstructionMenu.NOTHING_SELECTED)){
			//TODO do nothing or simply colse the window?
			System.out.println("nothing selected");
			return ;
		}
		
		
		int[] pos = village.getPopupMousePosition();
		PlannedAction pa = createBuildingPlan(pos, ae);
		guiController.addPlannedAction(pa);
		
		System.out.println("build "+ae+" at ("+pos[0]+", "+pos[1]+")");

		
	}

	private PlannedAction createBuildingPlan(int[] pos, String buildingType){
		
		PlannedAction pa = new PlannedAction();
		
		pa.put(PlannedAction.Action_TYPE, "build");
		pa.put(PlannedAction.BUILDING_TYPE, buildingType);
		pa.put(PlannedAction.LOCATION_X, pos[0]+"");
		pa.put(PlannedAction.LOCATION_Y, pos[1]+"");
		
		return pa;
	}

	public void setGuiController(GUIController guiController) {
		this.guiController = guiController;
	}

	public MapDrawer getVillage() {
		return village;
	}

	public void setVillage(MapDrawer village) {
		this.village = village;
	}

}
