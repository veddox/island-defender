package model;

import main.Log;

/**
 * @author Sebastian Reith
 */

public class World {
	// public static final int ERROR = -1;
	public static final int ERROR = 0;
	public static final int PEACE = 1;
	public static final int CONFEDERATION = 2;
	public static final int WAR = 3;

	// Count of different levels (upgrade-states) of soliders
	public static final int CNT_SOLIDER_STATES = 5;
	
	public static final int WORLD_WIDTH = 16;
	public static final int WORLD_HEIGHT = 9;

	private Village[] villages;
	private Village[][] tiles;
	private int numOfVillages = 0;
	private int[][] diplomacyStates;

	public World() {
		villages = new Village[Player.MAX_PLAYER_NUMBER];
		tiles = new Village[WORLD_WIDTH][WORLD_HEIGHT];
		diplomacyStates = new int[Player.MAX_PLAYER_NUMBER][Player.MAX_PLAYER_NUMBER];
	}

	/**
	 * returns diplomacyState
	 * 
	 * @param vil1
	 * @param vil2
	 * @return diplomacyState
	 */
	public int getDiplomacyState(Village vil1, Village vil2) {
		if (vil1.getVillageID() > vil2.getVillageID()) {
			return diplomacyStates[vil1.getVillageID()][vil2.getVillageID()];
		} else if (vil2.getVillageID() > vil2.getVillageID()) {
			return diplomacyStates[vil2.getVillageID()][vil1.getVillageID()];
		} else {
			Log.e("World.getDiplomacyState()", "both Villages have same ID");
			return -1;
		}
	}

	/**
	 * sets diplomacyStates between two Villages
	 * 
	 * @param vil1 one Village
	 * @param vil2 another Village
	 * @param state diplomacyState
	 */
	public void setDiplomacyState(Village vil1, Village vil2, int state) {
		if (vil1.getVillageID() > vil2.getVillageID()) {
			diplomacyStates[vil1.getVillageID()][vil2.getVillageID()] = state;
		} else if (vil2.getVillageID() > vil2.getVillageID()) {
			diplomacyStates[vil2.getVillageID()][vil1.getVillageID()] = state;
		} else {
			Log.e("World.setDiplomacyState()", "both Villages have same ID");
		}
	}

	/**
	 * creates a new Village
	 * 
	 * @param ID
	 * @param player
	 * @param name
	 * @param x
	 * @param y
	 */
	public void createVillage(int ID, String player, String name, int x, int y, boolean myOwn) {
		if (villages[ID] != null) {
			Log.e("World.createVillage()", "ID already in use!");
			return;
		}
		villages[ID] = new Village(this, ID, player, name, x, y);
		tiles[x][y] = villages[ID];

		// initialise diplomacyState to all other Villages with PEACE
		for (int i = 0; i < numOfVillages; i++) {
			setDiplomacyState(villages[ID], villages[i], PEACE);
		}
		numOfVillages++;
	}

	/**
	 * returns number of Villages
	 * 
	 * @return numOfVillages number of Villages
	 */
	public int getNumOfVillages() {
		return numOfVillages;
	}

	/**
	 * returns village with passed id
	 * 
	 * @param id ID of Village
	 * @return village with passed id
	 */
	public Village getVillage(int id) {
		return villages[id];
	}

	/**
	 * returns village with passed position
	 * 
	 * @param x
	 * @param y
	 * @return village with passed position
	 */
	public Village getVillage(int x, int y) {
		return tiles[x][y];
	}

	/**
	 * returns Village[] villages by id
	 * 
	 * @return Village[] villages by id
	 */
	public Village[] getVillages() {
		return villages;
	}
	
	/**
	 * returns String[] player-names by id
	 * 
	 * @return String[] player-names by id
	 */
	public String[] getPlayers(){
		String[] ret = new String[Player.MAX_PLAYER_NUMBER];
		for (int i = 0; i<numOfVillages; i++ )
			ret[i] = villages[i].player;
		//return ret;
		//For test purposes
		String[] testing = {"Daniel", "Peter", "Christopher", "Sebastian"};
		return testing;
	}

	/**
	 * returns Village[][] villages by position
	 * 
	 * @return Village[][] villages by position
	 */
	public Village[][] getWorldTiles() {
		return tiles;
	}

	/**
	 * @return TileType[][] with TileType of every Position
	 */
	public TileType[][] getWorldTileType() {
		TileType[][] tileTypes = new TileType[WORLD_WIDTH][WORLD_HEIGHT];
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				if (tiles[i][j] != null) {
					tileTypes[i][j] = TileType.VILLAGE;
				}
				// Frame of Oceans
				else if (i == 0 || i == WORLD_WIDTH - 1 || j == 0
						|| j == WORLD_HEIGHT - 1) {
					tileTypes[i][j] = TileType.OCEAN;
				} else {
					tileTypes[i][j] = TileType.MEADOW;
				}
			}
		}
		return tileTypes;
	}

}
