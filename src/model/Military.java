package model;

import java.util.Arrays;

import main.Log;

/**
 * All things about war are managed here.
 * @author javanoob
 */
public class Military
{
	/**ID of the village this class belongs to.*/
	public final int parentVillageID;
	/**Total number of soldiers in this village.*/
	private int allSoldiers;
	/**Total amount of weapons used in this village.*/
	private int allWeapons;
	/**Your troops in the village: number at a position is the number of soldiers at upgrade state position+1.*/
	private int[] army;
	
	/**
	 * Constructor.
	 * @param arentVillageID 	ID of the village this class belongs to.
	 * @param army 				The initial army you want to get: number at a position is the number of soldiers at upgrade state position+1.
	 */
	public Military (int parentVillageID, int[] army)
	{
		this.parentVillageID = parentVillageID;
		this.army = new int[army.length];
		for (int i = 0; i < army.length; i++)
		{
			this.army[i] = army[i];
			allSoldiers += army[i];
			allWeapons += army[i] * (i + 1);
		}
	}

	/**@return Total number of soldiers in this village.*/
	public int getAllSoldiers(){return allSoldiers;}
	/**@return Total number of weapons in this village.*/
	public int getAllWeapons(){return allWeapons;}
	/**@param allWeapons Total number of weapons.*/
	public void setAllWeapons(int allWeapons){this.allWeapons = allWeapons;}
	/**@return Total army in this village: number at a position is the number of soldiers at upgrade state position+1.*/
	public int[] getArmy(){return army;}
	/**@param army Total army: number at a position is the number of soldiers at upgrade state position+1.*/
	public void setArmy(int[] army){this.army = army.clone();}
	/**
	 * @param grade The grade of the soldiers you want to know the number of.
	 * @return The number.
	 */
	public int getSoldiers(int grade)
	{
		if(grade > army.length-1 || grade < 1)
		{
			Log.w("Military.getSoldiers()", "grade > length of army-array+1 or grade < 1; army.length = " + army.length);
			return -1;
		}
		return army[grade-1];
	}
	/**
	 * @param grade 	New grade-1 of the soldiers.
	 * @param number 	Number of the new soldiers at this grade.
	 */
	public void setSoldiers(int grade, int number)
	{
		if(grade > army.length-1 || grade < 1)
		{
			Log.e("Military.setSolideres()", "grade > length of army-array+1 or grade < 1; army.length = " + army.length);
			return;
		}
		this.army[grade-1] = number;
	}
	
	/**@return ID of the village this class belongs to.*/
	public int getParentVillageID(){return parentVillageID;}
	/**@return Total strength of the troops in this village. Returned strength isn't very realistic. Function will be improved.*/
	public int getStrength(){return allWeapons;}
	/**@return The pay for the soldiers in this round.*/
	public int getPay()
	{
		int pay = 0;
		int payLevel = 0;
		for(int i = 0; i < army.length; i++)
		{
			if(i%2 == 0)payLevel++;
			pay += army[i]*payLevel;
		}
		return pay;
	}

	@Override
	public String toString() {
		return "Military [allSoldiers=" + allSoldiers + ", allWeapons="
				+ allWeapons + ", army=" + Arrays.toString(army) + "]";
	}
}
