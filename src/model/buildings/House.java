package model.buildings;

import model.Building;
import model.Tile;

/**
 * A little House to live in.
 * @author javanoob
 */
public class House extends Building
{
	/**
	 * Constructor.
	 * @param parentTile The tile the building is on.
	 */
	public House(Tile parentTile)
	{
		super(parentTile, 0.2f, 10, 2, 1, 2, 0);
		setName("House");
	}
	
	@Override
	public void setEmployees(int employees)
	{
		if(employees <= maxEmployees && employees >= 0){this.employees = employees;}
	}
}
