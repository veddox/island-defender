package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.TileType;
import controller.MapDrawerController;

/**
 * This class is responsible for drawing a game map - be it the island or the village map.
 * ATTENTION! This class has two constructors!
 * @author Daniel Vedder
 */
public class MapDrawer extends JPanel
{
	private static final long serialVersionUID = 6168200863970644292L;
	public static final int TILE_HEIGHT = 100;
	public static final String SHOW_VILLAGE_DETAILS = "show_village_details";
	public static final String SHOW_ISLAND_DETAILS = "show_island_details";
	
	private TileType[][] symbolMap;
	private int height;
	private int width;
	private int no_villages;
	private String[] players;
	private Image meadow_img, house_img, farm_img, blacksmith_img, weaponsmith_img, barracks_img, marketplace_img, village_img, burntvillage_img, ocean_img;
	private Image coast_top_img, coast_right_img, coast_left_img, coast_bottom_img, coast_topright_img, coast_topleft_img, coast_bottomright_img, coast_bottomleft_img, coast_bay_topleft_img, coast_bay_topright_img, coast_bay_bottomleft_img, coast_bay_bottomright_img;
	private JPopupMenu villagePopupMenu, islandPopupMenu;
	private JMenuItem village_tile_info;
	private JMenuItem island_tile_info; 
	private boolean is_island; //is this an island map?
	
	private int popupMouseX, popupMouseY;
	
	private ConstructionContextMenu ccm;
	private MapDrawerController mapController;
	
	/**
	 * The constructor used to construct village maps.
	 * @param TileType[][] symbolMap (only for village maps, null if this is to be an island map)
	 * @param int height
	 * @param int width
	 */
	public MapDrawer(TileType[][] symbolMapIn, int h, int w)
	{
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(w*TILE_HEIGHT, h*TILE_HEIGHT));
		
		is_island = false;
		symbolMap = symbolMapIn;
		height = h;
		width = w;
		
		villagePopupMenu = new JPopupMenu(); //Create a context menu
		ccm = new ConstructionContextMenu();
		villagePopupMenu.add(ccm);
		village_tile_info = new JMenuItem("Details");
		village_tile_info.setActionCommand(SHOW_VILLAGE_DETAILS);
		villagePopupMenu.add(village_tile_info);
		
		//adds a custom MouseAdapter
		addMouseControl();
		
		loadImages();
		repaint();
	}
	
	/**
	 * The constructor used to construct island maps.
	 * @param int no_players
	 */
	public MapDrawer(int no_players, String[] playerNames)
	{
		is_island = true;
		no_villages = no_players;
		players = playerNames;
		height = 9; //standard map size for island maps
		width = 16; //standard map size for island maps
		
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(width*TILE_HEIGHT, height*TILE_HEIGHT));
		
		islandPopupMenu = new JPopupMenu(); //Create a context menu
		island_tile_info = new JMenuItem("Details");
		island_tile_info.setActionCommand(SHOW_ISLAND_DETAILS);
		islandPopupMenu.add(island_tile_info);

		//adds a custom MouseAdapter
		addMouseControl();
		
		loadImages();
		repaint();
	}
	
	/**
	 * Load all the images needed to draw a village map.
	 */
	public void loadImages()
	{
		try {
			meadow_img = ImageIO.read(new File("Grafiken/meadow.png"));
			house_img = ImageIO.read(new File("Grafiken/house1.png"));
			farm_img = ImageIO.read(new File("Grafiken/farm4.png"));
			blacksmith_img = ImageIO.read(new File("Grafiken/blacksmith.png"));
			weaponsmith_img = ImageIO.read(new File("Grafiken/weaponsmith.png"));;
			barracks_img = ImageIO.read(new File("Grafiken/barracks.png"));
			marketplace_img = ImageIO.read(new File("Grafiken/marketplace1.png"));
			village_img = ImageIO.read(new File("Grafiken/village.png"));
			burntvillage_img = ImageIO.read(new File("Grafiken/burntvillage.png"));
			ocean_img = ImageIO.read(new File("Grafiken/ocean.png"));
			coast_top_img = ImageIO.read(new File("Grafiken/coast_top.png"));
			coast_topleft_img = ImageIO.read(new File("Grafiken/coast_topleft.png"));
			coast_topright_img = ImageIO.read(new File("Grafiken/coast_topright.png"));
			coast_bottom_img = ImageIO.read(new File("Grafiken/coast_bottom.png"));
			coast_bottomleft_img = ImageIO.read(new File("Grafiken/coast_bottomleft.png"));
			coast_bottomright_img = ImageIO.read(new File("Grafiken/coast_bottomright.png"));
			coast_left_img = ImageIO.read(new File("Grafiken/coast_left.png"));
			coast_right_img = ImageIO.read(new File("Grafiken/coast_right.png"));
			coast_bay_topright_img = ImageIO.read(new File("Grafiken/coast_bay_topright.png"));
			coast_bay_topleft_img = ImageIO.read(new File("Grafiken/coast_bay_topleft.png"));
			coast_bay_bottomleft_img = ImageIO.read(new File("Grafiken/coast_bay_bottomleft.png"));
			coast_bay_bottomright_img = ImageIO.read(new File("Grafiken/coast_bay_bottomright.png"));
		}
		catch(IOException ioe) {
			System.out.println("IO error in loadVillageImages() - class MapDrawer");
			ioe.printStackTrace();
		}
	}
	
	/**
	 * This method is needed for all the graphic painting.
	 */
	public void paintComponent(Graphics g)
	{
		this.setSize(width*TILE_HEIGHT, height*TILE_HEIGHT);
		if (is_island) { //This draws an island map from the preset options in the IslandMapGenerator class
			symbolMap = IslandMapGenerator.getIslandMap(1, no_villages, players);
		}
		//This part of the method draws a map from the symbolmap given to it
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Image img = null;
				switch (symbolMap[x][y]) {
					case MEADOW: img = meadow_img; break;
					case HOUSE: img = house_img; break;
					case FARM: img = farm_img; break;
					case BLACKSMITH: img = blacksmith_img; break;
					case WEAPONSMITH: img = weaponsmith_img; break;
					case BARRACKS: img = barracks_img; break;
					case MARKETPLACE: img = marketplace_img;  break;
					case VILLAGE: img = village_img; break;
					case BURNTVILLAGE: img = burntvillage_img; break;
					case OCEAN: img = ocean_img; break;
					case COAST_TOP: img = coast_top_img; break;
					case COAST_BOTTOM: img = coast_bottom_img; break;
					case COAST_LEFT: img = coast_left_img; break;
					case COAST_RIGHT: img = coast_right_img; break;
					case COAST_TOPRIGHT: img = coast_topright_img; break;
					case COAST_TOPLEFT: img = coast_topleft_img; break;
					case COAST_BOTTOMRIGHT: img = coast_bottomright_img; break;
					case COAST_BOTTOMLEFT: img = coast_bottomleft_img; break;
					case COAST_BAY_BOTTOMLEFT: img = coast_bay_bottomleft_img; break;
					case COAST_BAY_BOTTOMRIGHT: img = coast_bay_bottomright_img; break;
					case COAST_BAY_TOPRIGHT: img = coast_bay_topright_img; break;
					case COAST_BAY_TOPLEFT: img = coast_bay_topleft_img; break;
				}
				g.drawImage(img, x*TILE_HEIGHT, y*TILE_HEIGHT, null);
			}
		}
	}
	
	/**
	 * Launch a popup menu on right-clicking inside the map.
	 */
	public void addMouseControl()
	{
		this.addMouseListener(new MouseAdapter() {
			//Due to OS differences, both mousePressed() and mouseReleased() have to be checked
			public void mousePressed(MouseEvent me) {
				showPopup(me);
			}
			public void mouseReleased(MouseEvent me) {
				showPopup(me);
			}
			//Launch the correct context menu
			public void showPopup(MouseEvent me) {
				if(me.isPopupTrigger()) {
					
					//store mouse position for the controller
					popupMouseX = me.getX();
					popupMouseY = me.getY();
					
					if (is_island) {
						islandPopupMenu.show(me.getComponent(), popupMouseX, popupMouseY);
					}
					else {
						villagePopupMenu.show(me.getComponent(), popupMouseX, popupMouseY);
					}
				}
			}
		});
		
	}
	
	
	/**
	 * Returns the mouse position of when the popup was opened
	 * @return
	 */
	public int[] getPopupMousePosition(){
		return new int[]{popupMouseX,popupMouseY};
	}
	
	public int[] getClickedTilePosition(){
		int[] mousePosition = getPopupMousePosition();
		return new int[]{ (mousePosition[0] - (mousePosition[0]%TILE_HEIGHT)) / TILE_HEIGHT,
				(mousePosition[1] - (mousePosition[1]%TILE_HEIGHT)) / TILE_HEIGHT };
	}
	
	public TileType getClickedTileType(){
		int[] tilePosition = getClickedTilePosition();
		return symbolMap[tilePosition[0]][tilePosition[1]]; 
	}
	
	public boolean isIsland(){
		return is_island;
	}
	
	public void showNotPossibleDialog(){
		JOptionPane.showMessageDialog(
				this, 
				"Diese Aktion kann leider nicht ausgefuehrt werden", 
				"Impossible", 
				JOptionPane.ERROR_MESSAGE);
	}
	
	//-------------getter und setter-----------------
	
	public MapDrawerController getMapController() {
		return mapController;
	}

	public void setMapController(MapDrawerController mapController) {
		this.mapController = mapController;
		mapController.setMapDrawer(this);
		
		if(is_island){
			island_tile_info.addActionListener(mapController);
		}
		else{
			village_tile_info.addActionListener(mapController);
			ccm.setConstructionMenuController(mapController.getConstructionMenuController());
		}
	}
}
