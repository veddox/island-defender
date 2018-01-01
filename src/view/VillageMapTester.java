package view;

import model.TileType;;

/**
 * This is just a test class, it's sole purpose is to deliver a mock-up village to the MapDrawer class.
 * @author Daniel Vedder
 * @date 6.8.2014
 */
public abstract class VillageMapTester 
{
	/**
	 * Just an example village map.
	 * @return symbol_map
	 */
	public static TileType[][] getExampleVillage()
	{
		TileType[][] symbol_map = new TileType[16][9];
		//Fill everything with grass
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 9; y++) {
				symbol_map[x][y] = TileType.MEADOW;
			}
		}
		for (int i = 0; i < 8; i+=2) {
			symbol_map[i+4][2] = TileType.HOUSE;
		}
		for (int i = 0; i < 8; i+=2) {
			symbol_map[i+4][6] = TileType.FARM;
		}
		symbol_map[8][4] = TileType.MARKETPLACE;
		symbol_map[4][4] = TileType.BARRACKS;
		symbol_map[10][4] = TileType.BLACKSMITH;
		symbol_map[6][4] = TileType.WEAPONSMITH;
		return symbol_map;
	}
}