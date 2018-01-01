package view;

import model.TileType;
import main.Log;

/**
 * This class holds all the different island designs, each in a seperate method.
 * Every method takes the number of players as its paramater.
 * Each method also returns the symbol map for its particular island.
 * @author Daniel Vedder
 * @version 23.1.2014
 */
public abstract class IslandMapGenerator
{

	/*
	 * This class is abstract, thus has no constructor.
	 * Standard map size: 16*9 tiles
	 */
	
	/**
	 * This is the main method that is called by the MapDrawer class. All island designs must be registered here.
	 * @param islandID 		The ID of this island design
	 * @param no_villages	The number of players
	 * @param players		The String[] player name list - if this is null, a new 
	 * @return TileType[][]	The symbol map of this island.
	 */
	public static TileType[][] getIslandMap(int islandID, int no_villages, String[] players)
	{
		TileType[][] island = new TileType[16][9];
		switch (islandID) {
			case 0: island = drawStandardIsland(no_villages); break;
			case 1: island = drawIslandDesign1(no_villages); break;
		}
		return island;
	}
	
	/**
	 * Draw an empty standardised Island - just a simple rounded rectangle without villages.
	 * @return TileType[16][9] The symbol map for this island
	 */
	public static TileType[][] drawEmptyIsland()
	{
		TileType [][] symbol_map = new TileType[16][9];
		//First build a map consisting only of a 16*9 grid of grass tiles
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 9; y++) {
				symbol_map[x][y] = TileType.MEADOW;
			}
		}
		//Then build a ring of water around it
		for (int x = 0; x < 16; x+=15) {
			for (int y = 0; y < 9; y++) {
				symbol_map[x][y] = TileType.OCEAN;
			}
		}
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 9; y+=8) {
				symbol_map[x][y] = TileType.OCEAN;
			}
		}
		//Now build the coastline
		for (int x = 1; x < 15; x++) {
			symbol_map[x][1] = TileType.COAST_TOP;
		}
		for (int x = 1; x < 15; x++) {
			symbol_map[x][7] = TileType.COAST_BOTTOM;
		}
		for (int y = 1; y < 8; y++) {
			symbol_map[1][y] = TileType.COAST_LEFT;
		}
		for (int y = 1; y < 8; y++) {
			symbol_map[14][y] = TileType.COAST_RIGHT;
		}
		symbol_map[1][1] = TileType.COAST_TOPLEFT;
		symbol_map[14][1] = TileType.COAST_TOPRIGHT;
		symbol_map[14][7] = TileType.COAST_BOTTOMRIGHT;
		symbol_map[1][7] = TileType.COAST_BOTTOMLEFT;
		return symbol_map;
	}
	
	/**
	 * This method sets the specified number of villages on random locations on the specified map.
	 * @param no_villages
	 * @param TileType[16][9]
	 * 
	 *  --- NEEDS WORK ---
	 * 
	 */
	public static TileType[][] setVillages(int no_villages, TileType[][] island)
	{
		return island;
	}
	
	/**
	 * This is a test method, it simply prints out a symbol map to the console.
	 * @param TileType[16][9] symbol map
	 */
	public static void printMap(TileType[][] symbol_map)
	{
		for (int y=0; y<9; y++){
			String line = "";
			for (int x=0; x<16; x++){
				switch(symbol_map[x][y]) {
					case OCEAN: line = line+"~"; break;
					case COAST_TOP: case COAST_BOTTOM: line = line+"-"; break;
					case COAST_LEFT: case COAST_RIGHT: line = line+"|"; break;
					case COAST_TOPRIGHT: case COAST_TOPLEFT: case COAST_BOTTOMLEFT: case COAST_BOTTOMRIGHT: line = line+"+"; break;
					case MEADOW: line = line+"#"; break;
					case VILLAGE: line = line+"X"; break;
				}
			}
			System.out.println(line);
		}
	}

	/*
	 * The actual island designs can be found in the following
	 * All designs need to be registered with the drawIsland method above!
	 * 
	 * All designs need to be static and implement these characteristics:
	 * @param g A graphics object.
	 * @param int no_villages
	 * @return TileType[16][9] The symbol map for this island
	 */
	
	/**
	 * Draw the standard island (just a rounded rectangle with villages)
	 * @param g A graphics object.
	 * @param int no_villages
	 * @return TileType[16][9] The symbol map for this island
	 */
	public static TileType[][] drawStandardIsland(int no_villages)
	{
		TileType[][] island = new TileType[16][9];
		island = drawEmptyIsland();
		island = setVillages(no_villages, island);
		printMap(island);
		return island;
	}
	
	/**
	 * A slightly more embellished version of the standard island.
	 * @param g A graphics object.
	 * @param int no_villages
	 * @return TileType[16][9] The symbol map for this island
	 */
	public static TileType[][] drawIslandDesign1(int no_villages)
	{
		TileType[][] island = new TileType[16][9];
		island = drawEmptyIsland();
		island[4][1] = TileType.COAST_BAY_TOPLEFT;
		island[4][0] = TileType.COAST_TOPLEFT;
		island[5][0] = TileType.COAST_TOP;
		island[6][0] = TileType.COAST_TOP;
		island[7][0] = TileType.COAST_TOPRIGHT;
		island[7][1] = TileType.COAST_BAY_TOPRIGHT;
		island[5][1] = TileType.MEADOW;
		island[6][1] = TileType.MEADOW;
		island = setVillages(no_villages, island);
		//printMap(island);
		return island;
	}
	
}
