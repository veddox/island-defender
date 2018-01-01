package view;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;

import javax.swing.*;

import main.Log;

/**
 * This window displays the help and the game documentation for Island Defender.
 * @author Daniel Vedder
 *
 */
@SuppressWarnings("serial")
public class HelpWindow extends JFrame 
{
	JTextArea text;
	JScrollPane scroller;
	
	public HelpWindow()
	{
		this.setTitle("Hilfe");
		this.setSize(350, 400);
		this.setLocation(300, 150);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		addTextArea();
		addFillers();
		loadHelpFile();
	}
	
	/**
	 * Add the text area which will display the text.
	 */
	public void addTextArea()
	{
		text = new JTextArea();
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setText("Dies ist die Hilfsdatei für Island Defender.");
		scroller = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroller, BorderLayout.CENTER);
	}
	
	/**
	 * Add filler panels, just for the optics
	 */
	public void addFillers()
	{
		this.add(new JPanel(), BorderLayout.NORTH);
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.SOUTH);
		this.add(new JPanel(), BorderLayout.WEST);
	}
	
	/**
	 * Load the help text from file.
	 */
	public void loadHelpFile()
	{
		try {
			File helpfile = new File("src/Help.txt");
			BufferedReader helpfile_reader = new BufferedReader(new FileReader(helpfile));
			String helptext = "";
			String line = helpfile_reader.readLine();
			while (line != null) {
				helptext = helptext+line+"\n";
				line = helpfile_reader.readLine();
			}
			text.setText(helptext);
			helpfile_reader.close();
		}
		catch (Exception e) {
			text.setText("Error loading help file!");
			Log.e("HelpWindow", "Error loading file!");
			e.printStackTrace();
		}
	}
}
