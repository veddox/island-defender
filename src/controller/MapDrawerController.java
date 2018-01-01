package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.TileType;
import view.MapDrawer;

/**
 * 
 * @author peter
 * @date 6.8.14
 */
public class MapDrawerController implements ActionListener{

	private GUIController guiController;

	private ConstructionMenuController constrController;
	private VillageController vController;
	private IslandController iController;
	
	private MapDrawer village;
	private MapDrawer island;
	
	public MapDrawerController(GUIController guiController){
		this.guiController = guiController;
		
		vController = new VillageController(this);
		iController = new IslandController(this);
		constrController = new ConstructionMenuController(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ae = e.getActionCommand();
		
		if(ae.equals(MapDrawer.SHOW_VILLAGE_DETAILS)){
			//TODO show details about a building in this village
			System.out.println("show details about a building in this village");
		}
		else if(ae.equals(MapDrawer.SHOW_ISLAND_DETAILS)){
			//TODO show details about a village on this island
			System.out.println("show details about a village on this island");
		}
		
	}
	
	public void build(String buildingType){
		//bulidingType says what to build
		//if this method is invoked, village_view is in use
		//find out where to build
		int[] pos = village.getClickedTilePosition();
		
		//check if it is possible to build there
		TileType tile = village.getClickedTileType();
		if(tile.isBuildable()){
			//create plan and give it to guiController
			PlannedAction plan = new PlannedAction();
			plan.put(PlannedAction.Action_TYPE, PlannedAction.BUILD);
			plan.put(PlannedAction.BUILDING_TYPE, buildingType);
			plan.put(PlannedAction.LOCATION_X, pos[0]+"");
			plan.put(PlannedAction.LOCATION_Y, pos[1]+"");
			guiController.addPlannedAction(plan);
			
			//TODO show the user, that his plans are considered
		}
		else{
			//inform user that his plans are impossible
			village.showNotPossibleDialog();
		}
		
	}
	
	public void demolish(){
		//if this method is invoked, village_view is in use
		//find out where to demolish
		int[] pos = village.getClickedTilePosition();

		//check if it is possible 
		TileType tile = village.getClickedTileType();
		if(tile.isBuilding()){
			//create plan and give it to guiController
			PlannedAction plan = new PlannedAction();
			plan.put(PlannedAction.Action_TYPE, PlannedAction.DEMOLISH);
			plan.put(PlannedAction.LOCATION_X, pos[0]+"");
			plan.put(PlannedAction.LOCATION_Y, pos[1]+"");
			guiController.addPlannedAction(plan);
			
			//TODO show the user, that his plan is considered
		}
		else{
			//inform user that his plans are impossible
			village.showNotPossibleDialog();
		}

		
	}
	
	
	//------------getter und setter------------
	
	public ConstructionMenuController getConstructionMenuController() {
		return constrController;
	}
	public void setConstructionMenuController(ConstructionMenuController constrController) {
		this.constrController = constrController;
	}


	public void setMapDrawer(MapDrawer md){
		if(md.isIsland()){
			iController.setIsland(md);
			island = md;
		}
		else{
			vController.setVillage(md);
			village = md;
		}
	}


	public ConstructionMenuController getConstrController() {
		return constrController;
	}
	public void setConstrController(ConstructionMenuController constrController) {
		this.constrController = constrController;
	}


	public VillageController getVillageController() {
		return vController;
	}
	public void setVillageController(VillageController vController) {
		this.vController = vController;
	}


	public IslandController getIslandController() {
		return iController;
	}
	public void setIslandController(IslandController iController) {
		this.iController = iController;
	}


	public GUIController getGuiController() {
		return guiController;
	}
	public void setGuiController(GUIController guiController) {
		this.guiController = guiController;
	}
	


}
