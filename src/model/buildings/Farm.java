package model.buildings;

import model.Building;
import model.Tile;

/**
 * A farm for you.
 * @author javanoob
 */
public class Farm extends Building
{
	/**
	 * Constructor.
	 * @param parentTile The tile the building is on.
	 */
	public Farm(Tile parentTile)
	{
		super(parentTile, 1.0f, 5, 1, 1, 1, 0);
		setName("Farm");
	}
}
