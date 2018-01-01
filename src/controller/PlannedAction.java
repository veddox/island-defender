package controller;

import java.util.HashMap;
/**
 * 
 * @author peter
 * @date 6.8.14
 */
public class PlannedAction extends HashMap<String,String>{
	
	//Keys
	public static final String Action_TYPE = "type";
	public static final String BUILDING_TYPE = "buildingType";
	public static final String LOCATION_X = "LOCATION_X";
	public static final String LOCATION_Y = "LOCATION_Y";
	
	//Values
	public static final String BUILD = "build";
	public static final String DEMOLISH = "demolish";
	
	
	private static final long serialVersionUID = 8034566298290226916L;
	
	
	
	@Override
	public String toString(){
		String s = "";
		for(String key : this.keySet()){
			s+=key+":"+get(key)+" ";
		}
		return s;
	}
}
