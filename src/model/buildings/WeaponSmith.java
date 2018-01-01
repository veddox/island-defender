package model.buildings;

import model.Building;
import model.Tile;

/**
 * A weaponsmith building.
 * @author javanoob
 */
public class WeaponSmith extends Building
{
	/**
	 * Constructor.
	 * @param parentTile The tile the building is on.
	 */
	public WeaponSmith(Tile parentTile)
	{
		super(parentTile, 1.0f, 5, 3, 3, 5, 0);
		setName("Weaponsmith");
	}
}
