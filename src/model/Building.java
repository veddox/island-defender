package model;

import main.Log;

/**
 * This is a building. Just if you didn't know that already. 
 * @author javanoob
 */
public class Building
{
	/**The name of the building.*/
	private String name;
	/**The tile the building is on.*/
	public final Tile parentTile;
	/**The number of employees in this building at the moment.*/
	protected int employees;
	/**The amount of whatever one worker produces per round.*/
	public final float rate;
	/**The maximum number of employees.*/
	public final int maxEmployees;
	/**Building costs that much money.*/
	public final int costsMoney;
	/**Building costs that much food.*/
	public final int costsFood;
	/**Building costs that much tools.*/
	public final int costsTools;
	/**Building costs that much weapons.*/
	public final int costsWeapons;
	
	/**
	 * Constructor.
	 * @param parentTile 	The tile the building is on.
	 * @param rate 			How fast the production will be.
	 * @param maxEmployees 	The maximum amount of workers that can work there.
	 * @param costsMoney 	The money building this building costs.
	 * @param costsFood 	The food building this building costs.
	 * @param costsTools 	The tools building this building costs.
	 * @param costsWeapons 	The weapons building this building costs.
	 */
	public Building(Tile parentTile, float rate, int maxEmployees, int costsMoney, int costsFood, int costsTools, int costsWeapons)
	{
		this.parentTile = parentTile;
		this.employees = 0;
		this.rate = rate;
		this.maxEmployees = maxEmployees;
		this.costsMoney = costsMoney;
		this.costsFood = costsFood;
		this.costsTools = costsTools;
		this.costsWeapons = costsWeapons;
	}
	/**@return The name of the building.*/
	public String getName(){return name;}
	/**@param name The new building name.*/
	public void setName(String name){this.name = name;}
	/**@return The number of workers in this building at the moment.*/
	public int getEmployees(){return employees;}
	/**@param employees The new number of workers.*/
	public void setEmployees(int employees)
	{
		if(employees > maxEmployees){Log.w("Building.setEmployees()", "Abortet. Too many employees to work in: "+name);return;}
		if(this.employees < employees)
		{
			if(parentTile.parentVillage.getFreeInhbs()+this.employees < employees){Log.w("Building.setEmployees()", "Abortet. Not enough jobless inhabitants to have more workers in: "+name);return;}
			parentTile.parentVillage.setFreeInhbs(parentTile.parentVillage.getFreeInhbs() - employees);
		}
		else{parentTile.parentVillage.setFreeInhbs(parentTile.parentVillage.getFreeInhbs() + (this.employees-employees));}
		this.employees = employees;
		Log.i("Building.setEmployees()", employees+" (of max. "+maxEmployees+") workers are now employed in building: "+getName());
	}
	
	/**
	 * How much the building produced in this round.
	 * @return The amount.
	 */
	public int getProducedAmount(){return Math.round(rate * employees);}
	
	@Override
	public String toString()
	{
		return "[name=" + name + ", employees=" + employees
				+ ", maxEmployees=" + maxEmployees + "]";
	}
}
