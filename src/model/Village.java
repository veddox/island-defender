package model;

import java.util.Random;

import main.Log;
import model.buildings.*;

/**
 * This is a village with its buildings etc.
 * @author javanoob
 */
public class Village
{
	/**Unique ID of the village.*/
	public final int villageID;
	/**Name of the player of the village.*/
	public final String player;
	/**Name of the village.*/
	public final String villageName;
	/**X-location of the village in the map.*/
	public final int locX;
	/**Y-location of the village in the map.*/
	public final int locY;
	/**Amount of tiles in x-direction.*/
	public final int width;
	/**Amount of tiles in y-direction.*/ 
	public final int height;
	
	/**Number of inhabitants in the village.*/
	private int inhabitants;
	/**"Amount" of money in the village.*/
	private int money;
	/**Amount of food in the village.*/
	private int food;
	/**Amount of tools in the village.*/
	private int tools;
	/**Amount of weapons in the village.*/
	private int weapons;
	
	/**True if the village is already attacked in this round.*/
	private boolean attacked;
	/**Amount of inhabitants in the village who are not working.*/
	private int freeInhbs;
	/**Maximum number of inhabitants in the village.*/
	private int maxInhbs;
	/**The world in which the village is located.*/
	private World parentWorld;
	/**Array of the tiles in the village.*/
	private Tile[][] tileArray;
	/**The military section of the village.*/
	public Military military;
	/**Trade in the village.*/
	public Trade trade;
	/**Random number generator. Useful.*/
	public Random random;
	
	/**
	 * Calls a second constructor with some constant extra values for ingame content.
	 * @param parentWorld 	World in which the village is located.
	 * @param villageID 	Unique ID of the village.
	 * @param player 		Name of the Player of the village.
	 * @param villageName 	Name of the village.
	 * @param locX 			X-position of the village in the map.
	 * @param locY 			Y-position of the village in the map.
	 */
	public Village (World parentWorld, int villageID, String player, String villageName, int locX, int locY)
	{	
		this(parentWorld, villageID, player, villageName, locX, locY, 100, 1000, 1000, 1000, 1000, new int[World.CNT_SOLIDER_STATES], World.WORLD_WIDTH, World.WORLD_HEIGHT);
	}
	
	/**
	 * Simpler constructor available! Get it for free and save costly space in your parameter list! Only NOW!
	 * @param parentWorld 	World in which the village is located.
	 * @param villageID 	Unique ID of the village.
	 * @param player 		Name of the Player of the village.
	 * @param villageName 	Name of the village.
	 * @param locX 			X-position of the village in the map.
	 * @param locY 			Y-position of the village in the map.
	 * @param inhabitants 	Initially amount of inhabitants.
	 * @param money 		Initially amount of money.
	 * @param food 			Initially amount of food.
	 * @param tools 		Initially amount of tools.
	 * @param weapons 		Initially amount of weapons.
	 * @param myArmy[] 		Array of my soldiers: number at a position is the number of soldiers at upgrade level position+1.
	 * @param widht 		Amount of tiles in x-direction for the new village.
	 * @param hight 		Amount of tiles in y-direction for the new village.
	 */
	public Village (World parentWorld, int villageID, String player, String villageName, int locX, int locY, int inhabitants, int money, int food, int tools, int weapons, int[] myArmy, int width, int height)
	{
		this.parentWorld = parentWorld;
		this.villageID = villageID;
		this.player = player;
		this.villageName = villageName;
		this.locX = locX;
		this.locY = locY;
		
		this.inhabitants = inhabitants;
		this.money = money;
		this.food = food;
		this.tools = tools;
		this.weapons = weapons;
		this.width = width;
		this.height = height;
		
		attacked = false;
		freeInhbs = inhabitants;
		maxInhbs = inhabitants;
		
		tileArray = new Tile[width][height];
		random = new Random();
		trade = new Trade(villageID);
		military = new Military(villageID, myArmy);
		
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				tileArray[j][i] = new Tile(j, i, TileType.MEADOW, this, MapType.VILLAGEMAP);
			}
		}
		for(int i = 0; i < Math.round(inhabitants/10); i++)
		{
			int rndX, rndY;
			do
			{
				rndX = random.nextInt(width);
				rndY = random.nextInt(height);
			}
			while(tileArray[rndX][rndY].getTileType() == TileType.HOUSE);
			tileArray[rndX][rndY].place(TileType.HOUSE);
			tileArray[rndX][rndY].getBuilding().setEmployees(tileArray[rndX][rndY].getBuilding().maxEmployees);
		}
	}
	

	/**@return Unique ID of the village.*/
	public int getVillageID(){return villageID;}
	/**@return Name of the Player of the village.*/
	public String getPlayer(){return player;}
	/**@return Name of the village.*/
	public String getVillageName(){return villageName;}
	/**@return X-location of the village in the map.*/
	public int getlocX(){return locX;}
	/**@return Y-location of the village in the map.*/
	public int getlocY(){return locY;}
	
	/**@return Amount of inhabitants in the village.*/
	public int getInhabitants(){return inhabitants;}
	/**@param inhabitants Amount of inhabitants.*/
	public void setInhabitants(int inhabitants){this.inhabitants = inhabitants;}
	
	/**@return Amount of money in the village.*/
	public int getMoney(){return money;}
	/**@param money Amount of money.*/
	public void setMoney(int money){this.money = money;}
	
	/**@return Amount of tools in the village.*/
	public int getTools(){return tools;}
	/**@param tools Amount of tools.*/
	public void setTools(int tools){this.tools = tools;}
	
	/**@return Amount of weapons in the village.*/
	public int getWeapons(){return weapons;}
	/**@param weapons Amount of weapons.*/
	public void setWeapons(int weapons){this.weapons = weapons;}
	
	/**@return Whether the village is attacked in this round or not.*/
	public boolean isAttacked(){return attacked;}
	/**@param attacked Whether to attack or not.*/
	public void setAttacked(boolean attacked){this.attacked = attacked;}
	
	/**@return The maximum number of inhabitants in the village.*/
	public int getMaxInhbs(){return maxInhbs;}
	/**@param maxInhbs New maximum number of inhabitants in the village.*/
	public void setMaxInhbs(int maxInhbs){this.maxInhbs = maxInhbs;}
	
	/**@return Amount of non-working inhabitants in the village.*/
	public int getFreeInhbs(){return freeInhbs;}
	/**@param freeInhbs Amount of non-working inhabitants.*/
	public void setFreeInhbs(int freeInhbs){this.freeInhbs = freeInhbs;}
	
	/**@return Amount of tiles of the village in the x-direction.*/
	public int getWidht(){return width;}
	/**@return Amount of tiles of the village in y-direction.*/
	public int getHight(){return height;}
	
	/**@return Amount of food in the village.*/
	public int getFood(){return food;}
	/**@param food Amount of food.*/
	public void setFood(int food){this.food = food;}
	
	
	/**@return World in which the village is located.*/
	public World getWorld(){return parentWorld;}
	/**@return Tile array of the village.*/
	public Tile[][] getTileArray(){return tileArray;}
	/**@return TileType array of the village.*/
	public TileType[][] getTileTypeArray()
	{
		TileType[][] tt = new TileType[width][height];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				tt[i][j] = tileArray[i][j].getTileType();
			}
		}
		return tt;
	}
	
	/**
	 * Controls costing and resources and subtracts them if possible.
	 * @param subMoney 		Money to subtract.
	 * @param subFood 		Food to subtract.
	 * @param subTools 		Tools to subtract.
	 * @param subWeapons 	Weapons to subtract.
	 */
	public boolean subtractFromResources(int subMoney, int subFood, int subTools, int subWeapons)
	{
		int dif = getMoney() - subMoney;
		if(dif >= 0){setMoney(dif);}
		else {Log.w("Tile.build()::Village.subtractFromResources()", "Can't affort! Missing: "+(-dif)+" money."); return false;}
		dif = getFood() - subFood;
		if(dif >= 0){setFood(dif);}
		else {Log.w("Tile.build()::Village.subtractFromResources()", "Built stopped! Missing: "+(-dif)+" food."); return false;}
		dif = getTools() - subTools;
		if(dif >= 0){setTools(dif);}
		else {Log.w("Tile.build()::Village.subtractFromResources()", "The workers have nothing to work with! Missing: "+(-dif)+" tools."); return false;}
		dif = getWeapons() - subWeapons;
		if(dif >= 0){setWeapons(dif);}
		else {Log.w("Tile.build()::Village.subtractFromResources()", "You need more weapons first! Missing: "+(-dif)+" weapons."); return false;}
		return true;
	}
	
	/**
	 * Controls the production of the buildings and the related resource and variable changes.
	 */
	public void update()
	{
		int homeless = 0;
		int missMoney = 0;
		int missFood = 0;
		int missTools = 0;
		int missWeapons = 0;
		
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				Building building = tileArray[i][j].getBuilding();
				int produced = building.getProducedAmount();
				switch(tileArray[i][j].getTileType())
				{
					case FARM : 
					{
						if(getTools() < 1){produced = 0;missTools++;}
						setFood(getFood() + produced);
					} break;
					case BLACKSMITH : 
					{
						if(getFood() < 1){produced = 0;missFood++;}
						setTools(getTools() + produced); break;
					}
					case HOUSE :
					{
						if(inhabitants+produced > maxInhbs){homeless += (inhabitants+produced-maxInhbs);produced = maxInhbs - inhabitants;}
						setInhabitants(getInhabitants() + produced);
						setFreeInhbs(getFreeInhbs() + produced);
					} break;
					case WEAPONSMITH :
					{
						if(getTools() < 2*produced){missTools += (getTools()-2*produced);}
						else
						{
							setWeapons(getWeapons() + produced);
							setTools(getTools() - 2*produced);
						}
					} break;
					case BARRACKS :
					{
					
					} break;
					default:{}
				}
			}
		}
		if(homeless > 0){Log.w("Village.update()", "Village is full! "+homeless+" new villagers couldn't live anywhere in the village!");}
		if(missTools > 0){Log.w("Village.update()", "There were "+missTools+" tools less than needed during this round!");}
		if(missFood > 0){Log.w("Village.update()", "There was "+missFood+" food less than needed during this round!");}
		if(missMoney > 0){Log.w("Village.update()", "There was "+missMoney+" money less than needed during this round!");}
		if(missWeapons > 0){Log.w("Village.update()", "There were "+missWeapons+" weapons less than needed during this round!");}
//		if(getMoney() < military.getAllSoldiers()) //TODO implementieren!!!
//		{
//			missMoney = getMoney() - military.getAllSoldiers();
//			for(int i = 0; i < missMoney/10; i++)
//			{
//				
//			}
//		}
		int foodDif = getFood() - getInhabitants();
		if(!(foodDif < 0)){setFood(foodDif);}
		else
		{
			Log.w("Village.update()", "Your inhabitants don´t have enough food! Missing: "+(-foodDif));
			foodDif = -foodDif;
			int dead = foodDif / 2;
			if(dead == 0){dead = 1;}
			System.out.println("LOOSER?? >"+getInhabitants()+" inhbs. "+dead+" will die!!");
			boolean oneDead = false;
			boolean workerWanted = false;
			
			for(int i = 0; i < dead; i++)
			{
				while(!oneDead)
				{
					int rndX = random.nextInt(this.width);
					int rndY = random.nextInt(this.height);
					Building building = tileArray[rndX][rndY].getBuilding();
					if(inhabitants == freeInhbs && workerWanted)
					{
						System.out.println("One jobless inhabitant died!");
						setFreeInhbs(getFreeInhbs()-1);
						workerWanted = false;
						oneDead = true;
					}
					else if(building.getEmployees() == 0){continue;}
					else if(!(building instanceof House) && workerWanted)
					{
						System.out.println("Building ("+building.getName()+") found!");
						if(building instanceof Barracks)
						{
							int rndi = random.nextInt(military.getArmy().length);
							while(military.getSoldiers(rndi+1) == 0){rndi = random.nextInt(military.getArmy().length);}
							((Barracks)(building)).fireSoldiers(rndi+1, 1);
						}else{building.setEmployees(building.getEmployees()-1);}
						Log.w("Village.update()", "One worker less in building: " + building.getName());
						setFreeInhbs(getFreeInhbs()-1);
						workerWanted = false;
						oneDead = true;
					}
					else if(building instanceof House && !workerWanted)
					{
						System.out.println("House found!");
						building.setEmployees(building.getEmployees()-1);
						workerWanted = true;
					}
				}
				oneDead = false;
				setInhabitants(getInhabitants()-1);
				System.out.println(i+" dead!");
			}
			Log.w("Village.update()", dead+" inhabitant(s) died hungry!");
			setFood(0);
			if(getInhabitants() == 0)
			{
				setFreeInhbs(0);
				Log.i("Village.update()", "Game over! All your inhabitants are dead!");
				Log.i("Village.update()", "()--<>----o----o---~*~---o----o----<>--()");
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "Village 0 [ inhabitants="
				+ inhabitants + ", freeInhbs="
						+ freeInhbs + ", money=" + money + ", food=" + food
				+ ", tools=" + tools + ", weapons=" + weapons + ", \n            military=" + military + " ]";
	}
}