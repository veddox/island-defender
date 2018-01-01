package view;

import java.awt.*;

import javax.swing.*;

/**
 * Displays all relevant information about the different players in one place.
 * @author Daniel Vedder
 */
public class WorldInfoPanel extends JPanel 
{
	Box main;
	
	/**
	 * The constructor.
	 */
	public WorldInfoPanel()
	{
		main = new Box(BoxLayout.Y_AXIS);
		this.setBackground(Color.gray);
		//The following mammoth line creates a compound bevel border with a 10px-wide white border around it.
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white, 10, false), BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())));
		this.add(main);
		main.add(Box.createVerticalStrut(20));
		main.add(new JLabel("Alle Spieler"));
	}
}