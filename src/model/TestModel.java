package model;

import java.util.ArrayList;
import java.util.List;

import model.buildings.*;
import view.GUI;

/**
 * Class for testing the model classes.
 * @author javanoob
 */
public class TestModel
{
	public World world;
	public GUI gui;
	Tile[][] ta;
	TileType[][] tta;
	Village v;
	Barracks b1;
	Barracks b2;
	Barracks b3;
	
	public TestModel()
	{
		world = new World();
		world.createVillage(0, "javanoob", "Noobien", 3, 3, true);
		
		v = world.getVillage(0);
		ta = v.getTileArray();
		
		tta = v.getTileTypeArray();
		drawWorld(tta);

		//State at the beginning.
		printBuildings();
		System.out.println(v.toString());
		
		//Round 1
		ta[3][2].build(TileType.BLACKSMITH);
		ta[2][2].build(TileType.FARM);
		ta[2][5].build(TileType.FARM);
		printRound(1);
		
		//Round 2
		ta[3][2].getBuilding().setEmployees(ta[3][2].getBuilding().maxEmployees); //Blacksmith
		ta[2][2].getBuilding().setEmployees(ta[2][2].getBuilding().maxEmployees); //Farm
		ta[2][5].getBuilding().setEmployees(ta[2][5].getBuilding().maxEmployees); //Farm
		ta[0][6].build(TileType.BLACKSMITH);
		ta[4][4].build(TileType.WEAPONSMITH);
		ta[1][0].build(TileType.BARRACKS);b1 = (Barracks)(ta[1][0].getBuilding());
		printRound(2);
		
		//Round 3
		b1.trainSoldiers(10);
		ta[0][6].getBuilding().setEmployees(ta[0][6].getBuilding().maxEmployees); //Blacksmith
		ta[4][4].getBuilding().setEmployees(ta[4][4].getBuilding().maxEmployees); //Weaponsmith
		ta[7][2].build(TileType.MARKETPLACE);
		ta[2][7].build(TileType.WEAPONSMITH);
		ta[2][0].build(TileType.BARRACKS);b2 = (Barracks)(ta[2][0].getBuilding());
		printRound(3);
		
		//Round 4
		b1.upgradeSoldiers(1, 10);
		b2.trainSoldiers(10);
		ta[7][2].getBuilding().setEmployees(ta[7][2].getBuilding().maxEmployees); //Marketplace
		ta[2][7].getBuilding().setEmployees(ta[2][7].getBuilding().maxEmployees); //Weaponsmith
		ta[3][0].build(TileType.BARRACKS);b3 = (Barracks)(ta[3][0].getBuilding());
		printRound(4);
		
		//Round 5
		b1.upgradeSoldiers(2, 10);
		b2.upgradeSoldiers(1, 10);
		b3.trainSoldiers(10);
		ta[7][3].build(TileType.MARKETPLACE);
		printRound(5);

		//Round 6
		ta[7][3].getBuilding().setEmployees(ta[7][3].getBuilding().maxEmployees); //Marketplace
		printRound(6);
		
		//Round 100
		printRound(100);
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		TestModel tm = new TestModel();
		
	}
	
	public void drawWorld(TileType[][] tt)
	{
		for(int i = 0; i < World.WORLD_HEIGHT; i++)
		{
			for(int j = 0; j < World.WORLD_WIDTH; j++)
			{
				switch(tt[j][i])
				{
				case MEADOW: System.out.print(".. ");break;
				case VILLAGE: System.out.print("(O)");break;
				case BURNTVILLAGE: System.out.print("/ö\\");break;
				case OCEAN: System.out.print("~~ ");break;
				case BARRACKS: System.out.print("[X]");break;
				case BLACKSMITH: System.out.print("[+]");break;
				case WEAPONSMITH: System.out.print("[#]");break;
				case FARM: System.out.print("[|]");break;
				case HOUSE: System.out.print("[U]");break;
				case MARKETPLACE: System.out.print("[%]");break;
				default: System.out.print("???");break;
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void printBuildings()
	{
		List<Building> bs = new ArrayList<Building>();
		ta = v.getTileArray();
		System.out.println("Buildings:");
		for(int i = 0; i < ta.length; i++)
		{
			for(int j = 0; j < ta[0].length; j++)
			{
				if(!(ta[i][j].getBuilding() instanceof Meadow))
				{
					bs.add(ta[i][j].getBuilding());
				}
			}
		}
		for(int i = 0; i < bs.size(); i++)
		{
			if(i%2 == 0){System.out.print(bs.get(i)+"		");}
			else System.out.println(bs.get(i));
		}
		if(bs.size()%2 == 1)System.out.println();
	}
	
	public void printRound(int no)
	{
		tta = v.getTileTypeArray();
		System.out.println("\n\nROUND NO. "+no);
		System.out.println("======================================================================================================");
		v.update();
		drawWorld(tta);
		printBuildings();
		System.out.println("\n"+v.toString());
		System.out.println("======================================================================================================\n\n");
	}
}
