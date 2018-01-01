package model;

import java.awt.Color;

/**
 * The player-class contains all information about a player, like id, color,
 * etc.
 * @author Peter Zilz
 * @date 2.1.2014
 */
public class Player {
	
	/** Every player has a unique id, which doesn't change during the game. */
	private final int id;
	/** The color of the player. May be relevant for the view. */
	private Color teamColor;
	/** A counter to create the IDs for every created player. */
	private static int idCounter = 0;
	/** The maximum number of Players is set to eight. */
	public static int MAX_PLAYER_NUMBER=8;
	
	/** name of the player */
	private String name; 

	/**
	 * Creates a new player without a name and with default color. 
	 * His ID is given by the idCounter.
	 * After that the counter is increased by one.
	 * @see #idToDefaultColor(int)
	 */
	public Player(){
		id = idCounter;
		idCounter++;
		teamColor = Player.idToDefaultColor(id);
	}
	
	/**
	 * Creates a new player with default color. 
	 * His ID is given by the idCounter.
	 * After that the counter is increased by one.
	 * @see #idToDefaultColor(int)
	 * @param name name of the new player
	 */
	public Player(String name){
		this();
		this.name = name;
	}
	
	/**
	 * Maps an ID to a color. This color doesn't have to be the actual color 
	 * of the player with the given ID. This method just serves to easily convert
	 * an ID to a color.<BR>
	 * There are just eight colors defined. If the ID goes higher, it starts
	 * from the beginning.
	 * @param id ID of a player.
	 * @return Default color of the player with this ID.
	 */
	public static Color idToDefaultColor(int id){
		//this id to color mapping is arbitrary
		switch(id%8){
		case 0: return Color.red;
		case 1: return Color.blue;
		case 2: return Color.green;
		case 3: return Color.yellow;
		case 4: return Color.magenta;
		case 5: return Color.cyan;
		case 6: return Color.orange;
		case 7: return Color.gray;
		default: return Color.black;
		}
	}
	
	/**
	 * Reverse operation to {@link #idToDefaultColor(int)}. Takes a color 
	 * and tries to map it to one of the 8 defualt IDs. If it is not a
	 * default color, 8 is returned.
	 * @param color color to convert.
	 * @return 0 to 7 for the default colors; 8 for no default color
	 */
	public static int colorToDefaultID(Color color){
		
		if(color.equals(Color.red)) return 0;
		if(color.equals(Color.blue)) return 1;
		if(color.equals(Color.green)) return 2;
		if(color.equals(Color.yellow)) return 3;
		if(color.equals(Color.magenta)) return 4;
		if(color.equals(Color.cyan)) return 5;
		if(color.equals(Color.orange)) return 6;
		if(color.equals(Color.gray)) return 7;
		
		return 8;
	}
	
	
	/**
	 * Returns the color of this player, which is the color of his team.
	 * @return team color of this player.
	 */
	public Color getTeamColor() {
		return teamColor;
	}
	
	/**
	 * Sets the team color of this player.
	 * @param teamColor new color for this player
	 */
	public void setTeamColor(Color teamColor) {
		this.teamColor = teamColor;
	}
	
	/**
	 * Returns the unique ID if this player. There is no setter, because
	 * the ID is final.
	 * @return ID of the player.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the player.
	 * @return name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gives the player a new name.
	 * @param name new name for this player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
