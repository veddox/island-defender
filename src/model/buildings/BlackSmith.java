package model.buildings;

import model.Building;
import model.Tile;

/**
 * A blacksmith building.
 * @author javanoob
 */
public class BlackSmith extends Building
{
	/**
	 * Constructor.
	 * @param parentTile The tile the building is on.
	 */
	public BlackSmith(Tile parentTile)
	{
		super(parentTile, 1.0f, 6, 3, 2, 3, 0);
		setName("Blacksmith");
	}
}
