package model.buildings;

import model.Building;
import model.Tile;

/**
 * Just a little meadow.
 * @author javanoob
 */
public class Meadow extends Building
{
	/**
	 * Constructor.
	 * @param parentTile The tile the building is on.
	 */
	public Meadow(Tile parentTile)
	{
		super(parentTile, 0.0f, 0, 0, 0, 0, 0);
	}
}
