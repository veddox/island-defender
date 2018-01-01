package model.buildings;

import main.Log;
import model.Building;
import model.Military;
import model.Tile;

/**
 * Daeng! Daeng! Swords are crossed in the Barracks!
 * @author javanoob
 */
public class Barracks extends Building
{
	/**True if the player did not train new soldiers in this round.*/
	private boolean onlyUpgrade;
	/**The grade of the soldiers trained or instructed in this round.*/
	private int grade;
	/**Military of the parent village.*/
	private Military military;
	
	/**
	 * Constructor.
	 * @param parentTile The tile the building is on.
	 */
	public Barracks(Tile parentTile)
	{
		super(parentTile, 1.0f, 10, 4, 3, 3, 0);
		setName("Barracks");
		military = parentTile.parentVillage.military;
	}
	
	/**@return Whether the player did only upgrade soldiers.*/
	public boolean didOnlyUpgrade(){return onlyUpgrade;}
	/**@param onlyUpgrade Whether barracks should only upgrade soldiers or train new ones.*/
	public void doOnlyUpgrade(boolean onlyUpgrade){this.onlyUpgrade = onlyUpgrade;}
	/**@return The grade of the soldiers trained or upgraded in this round.*/
	public int getGrade(){return grade;}
	/**@param grade The grade training for in this round.*/
	public void setGrade(int grade){this.grade = grade;}
	/**
	 * This method is unused for building type barracks! Use trainSoldiers() / fireSoldiers() instead!
	 * @param employees UNUSED!
	 */
	@Override
	@Deprecated
	public void setEmployees(int employees){Log.i("Barracks.setEmployees()", "This method isn't used for building type 'barracks'!");}

	/**
	 * Changes the variables related with training soldiers, if possible. 
	 * @param number Number of soldiers to train.
	 */
	public void trainSoldiers(int number)
	{
		grade = 1;
		if(number < 0){Log.i("Barracks.trainSoldiers()", "You can't train a negative number of soldiers, can you? Yes? But use fire Soldiers() instead!!");return;}
		else if(employees+number > maxEmployees){Log.w("Barracks.trainSoldiers()", "Too many soldiers for one Barrack!");return;}
		else if(number > parentTile.parentVillage.getFreeInhbs()){Log.w("Barracks.trainSoldiers()", "Not enough jobless inhabitants to train that number of soldiers!");return;}
		else if(number > parentTile.parentVillage.getWeapons()){Log.w("Barracks.trainSoldiers()", "Not enough weapons to train that number of soldiers!");return;}
		else
		{
			employees += number;
			military.setSoldiers(grade, military.getSoldiers(grade)+number);
			parentTile.parentVillage.setFreeInhbs(parentTile.parentVillage.getFreeInhbs()-number);
			parentTile.parentVillage.setWeapons(parentTile.parentVillage.getWeapons()-number);
			military.setAllWeapons(military.getAllWeapons()+number);
		}
		Log.i("Barracks.trainSoldiers()", "Trained "+number+" soldiers in building: "+getName());
	}
	
	/**
	 * Upgrades existing soldiers.
	 * @param grade The grade the soldiers are BEFORE upgrading.
	 * @param number The number of soldiers to upgrade.
	 */
	public void upgradeSoldiers(int grade, int number)
	{
		this.grade = grade;
		if(number < 0){Log.i("Barracks.upgradeSoldiers()", "You can't upgrade a negative number of soldiers, can you? Yes? But use downgradeSoldiers() instead!!");return;}
		else if(grade+1 > military.getArmy().length){Log.i("Barracks.upgradeSoldiers()", "These soldiers are already upgraded as high as possible!");return;}
		else if(number > parentTile.parentVillage.getWeapons()){Log.i("Barracks.upgradeSoldiers()", "Not enough weapons to upgrade this number of Soldiers!");return;}
		else if(military.getSoldiers(grade) < number){Log.w("Barracks.upgradeSoldiers()", "There are too less soldiers to update at grade "+grade);return;}
		else
		{
			military.setSoldiers(grade, military.getSoldiers(grade)-number);
			parentTile.parentVillage.setWeapons(parentTile.parentVillage.getWeapons()-number);
			military.setSoldiers(grade+1, military.getSoldiers(grade+1)+number);
			military.setAllWeapons(military.getAllWeapons()+number);
		}
		Log.i("Barracks.upgradeSoldiers()", "Upgraded "+number+" soldiers from grade "+grade+" to "+(grade+1)+" in building: "+getName());
	}
	
	/**
	 * Downgrades soldiers.
	 * @param grade 	The soldiers' CURRENT grade.
	 * @param number 	The number of soldiers to downgrade.
	 */
	public void downgradeSoldiers(int grade, int number)
	{
		if(military.getSoldiers(grade) < number){Log.w("Barracks.downgradeSoldiers()", "There are not enough soldiers on this grade!");return;}
		else if(grade-1 < 0){Log.w("Barracks.downgradeSoldiers()", "These soldiers are on the lowest grade yet!");return;}
		else if(number < 0){Log.i("Barracks.downgradeSoldiers()", "You can't downgrade a negative number of soldiers, can you? Yes? But use upgradeSoldiers() instead!!");return;}
		else{upgradeSoldiers(grade-1, -number);}
		Log.i("Barracks.downgradeSoldiers()", "Downgraded "+number+" soldiers from grade "+grade+" to "+(grade-1)+" in building: "+getName());
	}
	
	/**
	 * Fires soldiers completely.
	 * @param grade		The soldiers current grade.
	 * @param number	The number of soldiers to fire at the specified grade.
	 */
	public void fireSoldiers(int grade, int number)
	{
		if(number > military.getSoldiers(grade)){Log.w("Barracks.fireSoldiers()", "Not enough soldiers to fire on this grade!");return;}
		else if(employees-number < 0){Log.w("Barracks.fireSoldiers()", "There are not enough soldiers which you could fire in building: "+getName());return;}
		else if(number < 0){Log.i("Barracks.fireSoldiers()", "You can't fire a negative number of soldiers, can you? Yes? But use trainSoldiers() instead!!");return;}
		else
		{
			for(int i = grade; i > 1; i--){downgradeSoldiers(i, number);}
			trainSoldiers(-number);
		}
		Log.i("Barracks.fireSoldiers()", "Fired "+number+" soldiers of grade "+grade+" in building: "+getName());
	}
}
