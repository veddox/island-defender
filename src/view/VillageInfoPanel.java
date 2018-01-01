package view;

import java.awt.*;

import javax.swing.*;

/**
 * Displays all relevant information about the player's village in one place.
 * @author Daniel Vedder
 */
public class VillageInfoPanel extends JPanel
{
	Box main;
	
	/**
	 * The constructor.
	 */
	public VillageInfoPanel()
	{
		main = new Box(BoxLayout.Y_AXIS);
		this.setBackground(Color.gray);
		//The following mammoth line creates a compound bevel border with a 10px-wide white border around it.
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white, 10, false), BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())));
		this.add(main);
		main.add(Box.createVerticalStrut(20));
		main.add(new JLabel("Mein Dorf"));
	}
}
