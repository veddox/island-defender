package model;

import main.Log;
import model.buildings.*;

/**
 * A tile on the map (VILLAGEMAP or ISLANDMAP!).
 * @author javanoob
 */
public class Tile
{
	/**The TileType of the tile.*/
	private TileType type;
	/**X-location on the shown tab (island or village tab!!).*/
	private int locX;
	/**Y-location on the shown tab (island or village tab!!).*/
	private int locY;
	/**Placeholder for the eventually later added building.*/
	private Building building;
	/**Instance of the village the tile is in.*/
	public final Village parentVillage;
	/**The map the tile is used in: ISLANDMAP or VILLAGEMAP.*/
	public final MapType parentMap;
	
	
	/**
	 * Constructor.
	 * @param locX 			X-location in the island or village.
	 * @param locY 			Y-location in the island or village.
	 * @param type 			TileType of the tile.
	 * @param parentVillage Instance of the village the tile is in.
	 * @param parentMap 	The map the tile is used in: ISLANDMAP or VILLAGEMAP.
	 */
	public Tile(int locX, int locY, TileType type, Village parentVillage, MapType parentMap)
	{
		this.locX = locX;
		this.locY = locY;
	    this.parentVillage = parentVillage;
	    this.parentMap = parentMap;
	    this.place(type);
	}
	
	/**@return The instance of the building on the tile.*/
	public Building getBuilding(){return building;}
	/**@param building The building you want to set.*/
	public void setBuilding(Building building){this.building = building;}
	/**@return The TileType of the tile.*/
	public TileType getTileType(){return type;}
	/**@return The x location on the screen.*/
	public int getLocX(){return locX;}
	/**@param The new x location on the screen.*/
	public void setLocX(int locX){this.locX = locX;}
	/**@return The y location on the screen.*/
	public int getLocY(){return locY;}
	/**@param The new y location on the screen.*/
	public void setLocY(int locY){this.locY = locY;}
	
	/**
	 * Destroys the eventually existing building or writes an error into the logfile, if there's no building that can be destroyed.
	 */
	public void destroyBuilding() //Changing resources?!?
	{
		if(parentMap == MapType.ISLANDMAP || building instanceof Meadow){Log.e("Tile.destroyBuilding()", "No building to destroy on tile ("+locX+" /"+locY+")!"); return;}
		parentVillage.setFreeInhbs(parentVillage.getFreeInhbs() + building.getEmployees());
		building = new Meadow(this);
	}
	
	/**
	 * Changes the building the tile contains to the passed one, if there is no building yet.
	 * @param type Type of the building to build.
	 */
	public void build(TileType type)
	{
		if(building == null)
		{
			building = new Meadow(this);
			this.type = TileType.MEADOW;
			return;
		}
		if(parentMap == MapType.VILLAGEMAP)
		{
			if (building instanceof Meadow)
			{
				switch(type)
				{
					case BARRACKS : building = new Barracks(this); break;
					case FARM : building = new Farm(this); break;
					case HOUSE : building = new House(this); break;
					case BLACKSMITH : building = new BlackSmith(this); break;
					case MARKETPLACE : building = new MarketPlace(this); break;
					case WEAPONSMITH : building = new WeaponSmith(this); break;
					default : Log.e("Tile.built()", "Passed building type is impossible to build here!");return;
				}
				boolean possible = parentVillage.subtractFromResources(building.costsMoney, building.costsFood, building.costsTools, building.costsWeapons);
				if(!possible)
				{
					building = new Meadow(this);
				}
				else
				{
					this.type = type;
					Log.i("Tile.build()", "Successfully built '"+building.getName()+"' on Tile ("+locX+" /"+locY+")!");
				}
			}
			else
			{
				if(type != TileType.MEADOW)Log.i("Tile.built()", "On this tile ("+locX+" /"+locY+") already has been built: "+building.getName());
			}
		}
	}
	
	/**
	 * Places a building on the tile without changing the village's resources.
	 * @param type The TileType of the building.
	 */
	public void place(TileType type)
	{
		if (parentMap == MapType.VILLAGEMAP)
		{
			switch(type)
			{
			case MEADOW : building = new Meadow(this); break;
			case BARRACKS : building = new Barracks(this); break;
			case FARM : building = new Farm(this); break;
			case HOUSE : building = new House(this); break;
			case BLACKSMITH : building = new BlackSmith(this); break;
			case MARKETPLACE : building = new MarketPlace(this); break;
			case WEAPONSMITH : building = new WeaponSmith(this); break;
			default : Log.e("Tile.built()", "Passed building type ("+type+") is impossible to place here!");return;
			}
			this.type = type;
			if(type != TileType.MEADOW)Log.i("Tile.place()", "Placed '"+building.getName()+"' on Tile ("+locX+" /"+locY+")!");
		}
	}
}
