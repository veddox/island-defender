package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import main.Log;
import model.World;
import controller.GUIController;

/**
 * This is the class that unites all the different GUI elements found in "Island Defender"
 * 
 * @author Daniel Vedder
 * @date 02.01.2014
 */
public class GUI extends JFrame
{
	public static final String NEW_GAME = "new game";
	public static final String FINISH_ROUND = "finish round";
	public static final String QUIT = "quit";
	
	//Frame components
	private JMenuBar menubar;
	private JMenu file, menus, helpmenu;
	private JMenuItem new_game, finish_round, save, quit, chat_menuitem, battle_menuitem, diplomacy_menuitem, tradingpost_menuitem, help_menuitem, about;
	private JTabbedPane tabpane;
	private JPanel info_panel;
	private JScrollPane village_scroller, island_scroller;
	
	private MapDrawer village_view;
	private MapDrawer island_view;
	private VillageInfoPanel village_info;
	private WorldInfoPanel world_info;
	private HelpWindow help;

	private GUIController guiController;

	//Separate GUI windows
	ChatClient chat;
	TradingPost tradingpost;
	ConstructionMenu construction;
	
	private int activePlayer;
	World world;
	
	
	/**
	 * The constructor.
	 */
	public GUI(World new_world)
	{
		world = new_world;
		activePlayer = 0; //For test purposes
		this.setTitle("Island Defender");
		this.setSize(1000, 700);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximise the window
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addMenu();
		addTabs();
		this.setVisible(true);
		chat = new ChatClient(world.getPlayers()[activePlayer], world.getPlayers());
		tradingpost = new TradingPost(world.getPlayers()[activePlayer], world.getPlayers());
		help = new HelpWindow();
		construction = new ConstructionMenu();
	}
	
	/**
	 * Add the window menu.
	 */
	private void addMenu()
	{
		menubar = new JMenuBar();
			file = new JMenu("Spiel");
				new_game = new JMenuItem("Neues Spiel");
				finish_round = new JMenuItem("Runde beenden");
				save = new JMenuItem("Speichern");
				quit = new JMenuItem("Beenden");
			menubar.add(file);
			menus = new JMenu("Menüs");
				chat_menuitem = new JMenuItem("Chat");
				battle_menuitem = new JMenuItem("Schlachten"); //Do we need these three following menuitems here? (They can all be accessed elsewhere)
				diplomacy_menuitem = new JMenuItem("Diplomatie");
				tradingpost_menuitem = new JMenuItem("Handel");
			menubar.add(menus);
			helpmenu = new JMenu("Hilfe");
				help_menuitem = new JMenuItem("Hilfe");
				about = new JMenuItem("Über");
			menubar.add(helpmenu);
		this.setJMenuBar(menubar);
		
		new_game.setActionCommand(NEW_GAME);
		finish_round.setActionCommand(FINISH_ROUND);
		quit.setActionCommand(QUIT);
		
		tradingpost_menuitem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				tradingpost.setVisible(true);
			}
		});
		chat_menuitem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				chat.showChat();
			}
		});
		help_menuitem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				help.setVisible(true);
			}
		});
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(null, "Island Defender pre-release \nPeter Zilz, Sebastian Reith, Daniel Vedder,\nChristopher Borchert, Lukas Schönstein\n(c) 2014", "Ãœber Island Defender", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		file.add(new_game);
		file.add(finish_round);
		file.add(save);
		file.add(quit);
		menus.add(chat_menuitem);
		menus.add(battle_menuitem);
		menus.add(diplomacy_menuitem);
		menus.add(tradingpost_menuitem);
		helpmenu.add(help_menuitem);
		helpmenu.add(about);
		
		finish_round.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK)); //Quit when the user presses ALT+Q
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK)); //Quit when the user presses ALT+Q
		tradingpost_menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK)); //Show the trading menu when the user presses ALT+H
		chat_menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK)); //Show the chat when the user presses ALt+C
	}
	
	/**
	 * Add the three tabs - village & island view, information panel
	 * --- VillageView is currently disabled until development catches up :-) ---
	 */
	private void addTabs()
	{
		tabpane = new JTabbedPane();
		//village_view = new MapDrawer(world.getVillage(0).getTileTypeArray(), World.WORLD_HEIGHT, World.WORLD_WIDTH); //TODO Choose the right village depending on the player
		village_view = new MapDrawer(VillageMapTester.getExampleVillage(), World.WORLD_HEIGHT, World.WORLD_WIDTH); //Example village
		island_view = new MapDrawer(world.getNumOfVillages(), new String[]{"Daniel, Chris, Sebbo, Peter"});
		village_scroller = new JScrollPane(village_view, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		island_scroller = new JScrollPane(island_view, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//island_scroller.setSize(1000, 500);
		//island_scroller.setViewportView(island_view);
		info_panel = new JPanel();
		tabpane.addTab("Dorf", village_scroller);
		tabpane.addTab("Insel", island_scroller);
		tabpane.addTab("Information", info_panel);
		village_info = new VillageInfoPanel();
		world_info = new WorldInfoPanel();
		info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.X_AXIS));
		info_panel.add(village_info);
		info_panel.add(world_info);
		this.add(tabpane);
	}
	
	
	public void setGUIController(GUIController controller)
	{
		//set guiController
		guiController = controller;
		controller.setGui(this);
		
		//add controllers to village_map, island_map, ...
		if(guiController != null){
			village_view.setMapController(guiController.getMapDrawerController());
			island_view.setMapController(guiController.getMapDrawerController());
			chat.setChatController(guiController.getChatController());
			tradingpost.setTradeController(guiController.getTradeController());
			//TODO add matching Controller to village_info;
			//TODO add matching Controller to world_info;
			
			new_game.addActionListener(guiController);
			finish_round.addActionListener(guiController);
			quit.addActionListener(guiController);
			this.addWindowListener(guiController);
		}
	}
	
}
