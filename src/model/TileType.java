package model;

/**
 * Type of the tile (it�s not set on which map they are!).
 * @author javanoob
 */
public enum TileType
{
	MEADOW,
	BARRACKS,
	FARM,
	HOUSE,
	BLACKSMITH,
	WEAPONSMITH,
	MARKETPLACE,
	
	VILLAGE,
	BURNTVILLAGE,
	OCEAN,
	COAST_TOP,
	COAST_BOTTOM,
	COAST_RIGHT,
	COAST_LEFT,
	COAST_TOPRIGHT,
	COAST_TOPLEFT,
	COAST_BOTTOMRIGHT,
	COAST_BOTTOMLEFT,
	COAST_BAY_TOPLEFT,
	COAST_BAY_TOPRIGHT,
	COAST_BAY_BOTTOMLEFT,
	COAST_BAY_BOTTOMRIGHT;
	
	/**
	 * Determines if a building can be built on this type of tile. Always false, if its on the IslandMap
	 * @return
	 */
	public boolean isBuildable(){
		switch(this){
		case MEADOW: return true;
		default: return false;
		}
	}
	
	/**
	 * Determines if this is a building;
	 * @return
	 */
	public boolean isBuilding(){
		switch(this){
		case BARRACKS:
		case FARM:
		case HOUSE:
		case BLACKSMITH:
		case WEAPONSMITH:
		case MARKETPLACE: return true;
		default: return false;
		}
	}
	
	/**
	 * returns true if this tile is a coast. false if it is ocean or anything else.
	 * @return
	 */
	public boolean isCoast(){
		switch(this){
		case COAST_TOP:
		case COAST_BOTTOM:
		case COAST_RIGHT:
		case COAST_LEFT:
		case COAST_TOPRIGHT:
		case COAST_TOPLEFT:
		case COAST_BOTTOMRIGHT:
		case COAST_BOTTOMLEFT:
		case COAST_BAY_TOPLEFT:
		case COAST_BAY_TOPRIGHT:
		case COAST_BAY_BOTTOMLEFT:
		case COAST_BAY_BOTTOMRIGHT: return true;
		default: return false;
		}
	}
}
