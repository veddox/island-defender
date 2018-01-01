package model.buildings;

import model.Building;
import model.Tile;

/**
 * You wanna trade? You gonna come here!
 * @author javanoob
 */
public class MarketPlace extends Building
{
	/**
	 * Constructor.
	 * @param parentTile The tile the building is on.
	 */
	public MarketPlace(Tile parentTile)
	{
		super(parentTile, 0.0f, 3, 2, 2, 1, 0);
		setName("Marketplace");
	}
}
