package edu.cpp.cs.cs141.final_project;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.KeyStroke;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame {
	
	private JPanel welcomePanel;
	
	private JPanel mainPanel;
	
	private JPanel helpPanel;
	
	private CardLayout mainLayout;
	
	private JTextArea grid;
	
	private GameEngine ge;
	
	private boolean isDebug;
	
	private boolean inGame;
	
	private JTextArea runningMsg;
	
	private JTextArea info;
	
	
	public static void main(String[] args)
	{
		GameEngine ge=new GameEngine();
		GUI gui=new GUI(ge);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public GUI(GameEngine ge)
	{
		setTitle("Game");
		this.ge=ge;
		isDebug=false;
		inGame=false;
		this.setSize(600,600);
		mainLayout= new CardLayout();
		getContentPane().setLayout(mainLayout);
		setWelcomePanel();
		setHelpPanel();
		setMainPanel();
		getContentPane().add(welcomePanel,"welcome");
		getContentPane().add(helpPanel,"help");	
		getContentPane().add(mainPanel,"main");
		
		
	}
	
	private void setWelcomePanel()
	{
		welcomePanel=new JPanel();
		welcomePanel.setLayout(new BoxLayout(welcomePanel,BoxLayout.Y_AXIS));
		
		JLabel welcomeMsg = new JLabel("Welcome to the game!");
		welcomeMsg.setAlignmentX(CENTER_ALIGNMENT);
		welcomeMsg.setFont(new Font("Kristen ITC", Font.BOLD, 45));
		
		JButton start,load,help,quit;
		start=new JButton("Start New Game");
		start.addActionListener(new startNewGame());

		start.setFont(new Font("Tahoma", Font.PLAIN, 25));
		load=new JButton("Load Game");
		load.setFont(new Font("Tahoma", Font.PLAIN, 25));
		help=new JButton("Help");
		help.setFont(new Font("Tahoma", Font.PLAIN, 25));
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(GUI.this.getContentPane(), "help");
				
			}
		});
		quit=new JButton("Quit");
		quit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		start.setAlignmentX(CENTER_ALIGNMENT);
		load.setAlignmentX(CENTER_ALIGNMENT);
		help.setAlignmentX(CENTER_ALIGNMENT);
		quit.setAlignmentX(CENTER_ALIGNMENT);
		welcomePanel.add(Box.createRigidArea(new Dimension(0,150)));
		welcomePanel.add(welcomeMsg);
		welcomePanel.add(Box.createRigidArea(new Dimension(0,50)));
		welcomePanel.add(start);
		welcomePanel.add(Box.createRigidArea(new Dimension(0,20)));
		welcomePanel.add(load);
		welcomePanel.add(Box.createRigidArea(new Dimension(0,20)));
		welcomePanel.add(help);
		welcomePanel.add(Box.createRigidArea(new Dimension(0,20)));
		welcomePanel.add(quit);
	}
	
	private void setHelpPanel()
	{
		GridBagLayout layout = new GridBagLayout();
		helpPanel=new JPanel(layout);
		JButton OK=new JButton("OK");
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inGame)
					mainLayout.show(GUI.this.getContentPane(), "welcome");
				else
					mainLayout.show(GUI.this.getContentPane(), "main");
			}
		});
		
		JTextArea helpText = new JTextArea();
		helpText.setText("Here Display Help Text\nHere Display Help Text");
		helpText.setBackground(UIManager.getColor("OptionPane.background"));
		helpText.setFont(new Font("Monospaced", Font.PLAIN, 22));
		helpText.setEditable(false);
		helpText.setColumns(40);
		helpText.setRows(10);
		GridBagConstraints gbc_helpText = new GridBagConstraints();
		gbc_helpText.insets = new Insets(40, 0, 5, 0);
		gbc_helpText.gridx = 0;
		gbc_helpText.gridy = 1;
		helpPanel.add(helpText, gbc_helpText);
		OK.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_OK = new GridBagConstraints();
		gbc_OK.insets = new Insets(10, 0, 40, 0);
		gbc_OK.gridx = 0;
		gbc_OK.gridy = 2;
		helpPanel.add(OK, gbc_OK);
		JLabel helpLabel = new JLabel("Help");
		helpLabel.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		GridBagConstraints gbc_helpLabel = new GridBagConstraints();
		gbc_helpLabel.fill = GridBagConstraints.VERTICAL;
		gbc_helpLabel.insets = new Insets(20, 0, 5, 0);
		gbc_helpLabel.gridx = 0;
		gbc_helpLabel.gridy = 0;
		helpPanel.add(helpLabel, gbc_helpLabel);
		
	}
	
	private void setMainPanel()
	{
		GridBagLayout layout=new GridBagLayout();
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		layout.columnWeights = new double[]{1.0};
		mainPanel=new JPanel(layout);
		JMenuBar menu=new JMenuBar();
		
		JMenu game = new JMenu("Game");
		game.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menu.add(game);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		newGame.addActionListener(new startNewGame());
		game.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Load Game");
		game.add(loadGame);
		loadGame.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		JMenuItem saveGame = new JMenuItem("Save Game");
		game.add(saveGame);
		saveGame.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		JMenuItem quit = new JMenuItem("Quit");
		game.add(quit);
		quit.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		JMenu mode = new JMenu("Mode");

		mode.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menu.add(mode);
		
		
		JCheckBoxMenuItem debugMode = new JCheckBoxMenuItem("Debug Mode");
		debugMode.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mode.add(debugMode);
		JCheckBoxMenuItem playerMode = new JCheckBoxMenuItem(" Player Modde");
		playerMode.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		playerMode.setSelected(true);
		mode.add(playerMode);

		ButtonGroup modeGroup=new ButtonGroup();
		modeGroup.add(playerMode);
		modeGroup.add(debugMode);
		
		debugMode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isDebug=e.getStateChange()==ItemEvent.SELECTED?true:false;
				GUI.this.printInfo();
			}
		});

			
		JMenu help = new JMenu("Help");
		help.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menu.add(help);
		
		JMenuItem help_button = new JMenuItem("Help");
		help_button.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		help_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(GUI.this.getContentPane(), "help");
			}
		});
		help.add(help_button);
		
		JMenuItem about = new JMenuItem("About");
		about.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		help.add(about);
		
		GridBagConstraints gbc_menu = new GridBagConstraints();
		gbc_menu.weighty = 1.0;
		gbc_menu.weightx = 1.0;
		gbc_menu.anchor = GridBagConstraints.NORTH;
		gbc_menu.gridy = 0;
		gbc_menu.gridx = 0;
		gbc_menu.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(menu, gbc_menu);
		
		grid=new JTextArea();
		grid.setBackground(UIManager.getColor("Panel.background"));
		grid.setFont(new Font("Monospaced", Font.BOLD, 25));
		grid.setRows(9);
		grid.setColumns(27);
		GridBagConstraints gbc_grid = new GridBagConstraints();
		gbc_grid.weighty = 0.5;
		gbc_grid.anchor = GridBagConstraints.NORTH;
		gbc_grid.insets = new Insets(5, 0, 5, 0);
		gbc_grid.gridy = 1;
		gbc_grid.gridx = 0;
		mainPanel.add(grid, gbc_grid);
		grid.setEditable(false);
		
		JPanel controlPanel=new JPanel();
		GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.weighty = 0.5;
		gbc_controlPanel.insets = new Insets(5, 0, 0, 0);
		gbc_controlPanel.gridy = 3;
		gbc_controlPanel.gridx = 0;
		mainPanel.add(controlPanel, gbc_controlPanel);
		
		JRadioButton move = new JRadioButton("Move");
		move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runningMsg.setText("  choose a direction to move  \n[w]up [s]down [a]left [d]right");
			}
		});
		move.setFont(new Font("Tahoma", Font.PLAIN, 22));
		move.setSelected(true);
		controlPanel.add(move);
		
		JRadioButton shoot = new JRadioButton("Shoot");
		shoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runningMsg.setText("  choose a direction to shoot \n[w]up [s]down [a]left [d]right");
			}
		});
		shoot.setFont(new Font("Tahoma", Font.PLAIN, 22));
		controlPanel.add(shoot);
		
		JRadioButton look = new JRadioButton("Look");
		look.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runningMsg.setText("  choose a direction to look  \n[w]up [s]down [a]left [d]right");
			}
		});
		look.setSelected(false);
		look.setFont(new Font("Tahoma", Font.PLAIN, 22));
		controlPanel.add(look);
		
		ButtonGroup controlGroup=new ButtonGroup();
		controlGroup.add(move);
		controlGroup.add(shoot);
		controlGroup.add(look);
		
		runningMsg=new JTextArea();
		runningMsg.setBackground(UIManager.getColor("Panel.background"));
		runningMsg.setText("  choose a direction to move  \n[w]up [s]down [a]left [d]right");
		runningMsg.setEditable(false);
		
		runningMsg.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		GridBagConstraints gbc_msg = new GridBagConstraints();
		gbc_msg.weighty = 0.5;
		gbc_msg.insets = new Insets(0, 0, 5, 0);
		gbc_msg.gridy = 2;
		gbc_msg.gridx = 0;
		mainPanel.add(runningMsg, gbc_msg);
		
		info = new JTextArea();
		info.setBackground(UIManager.getColor("Panel.background"));
		info.setFont(new Font("Tahoma", Font.PLAIN, 20));
		info.setEditable(false);
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.weighty = 0.5;
		gbc_info.insets = new Insets(0, 0, 10, 0);
		gbc_info.gridx = 0;
		gbc_info.gridy = 4;
		mainPanel.add(info, gbc_info);
		info.setColumns(10);
		
		
		grid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "up");
		grid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "left");
		grid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "down");
		grid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "right");
		
		AbstractAction upAction=new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
					GUI.this.movement('w',move.isSelected(),shoot.isSelected(),look.isSelected());
			}	
		};
		
		AbstractAction downAction=new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
					GUI.this.movement('s',move.isSelected(),shoot.isSelected(),look.isSelected());
			}	
		};
		
		AbstractAction leftAction=new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
					GUI.this.movement('a',move.isSelected(),shoot.isSelected(),look.isSelected());
			}	
		};
		

		AbstractAction rightAction=new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
					GUI.this.movement('d',move.isSelected(),shoot.isSelected(),look.isSelected());
			}	
		};
		
		grid.getActionMap().put("up", upAction);
		grid.getActionMap().put("down", downAction);
		grid.getActionMap().put("left", leftAction);
		grid.getActionMap().put("right", rightAction);
		
	}
	
	private void movement(char direction,boolean move,boolean shoot,boolean look)
	{
		if(move){
			boolean isWinning=false;
			boolean isMoved=true;
			switch(ge.playerMove(direction))
			{
			case "noMove":
				runningMsg.setText("You can't move there");
				isMoved=false;
				break;
			case "moved":
				runningMsg.setText("  choose a direction to move  \n[w]up [s]down [a]left [d]right");
				break;
			case "noCase": 
				runningMsg.setText("This room is empty.\nTry another one.");
				break;
			case "bullet": 
				runningMsg.setText("You found a bullet.\nYour gun is reloaded.");
				break;
			case "radar": 
				runningMsg.setText("You got a radar and the location of\nthe briefcase has been detected.");
				break;
			case "invincible": 
				runningMsg.setText("You will be invincible for 5 turns.");
				break;
			case "getCase":
				isWinning=true;
				JOptionPane.showMessageDialog(this, "You found the briefcase!\nYou won the game!","Wining",JOptionPane.INFORMATION_MESSAGE);
				mainLayout.show(this.getContentPane(), "welcome");
				break;
			}
			
			if(!isWinning&&isMoved)
			{
				boolean isDead=ge.ninjaTurn();
				printInfo();
				if(isDead)
				{
					if(ge.isGameOver())
					{
						JOptionPane.showMessageDialog(this, "Game Over!","Game Over",JOptionPane.INFORMATION_MESSAGE);
						mainLayout.show(this.getContentPane(), "welcome");
					}
					else
					{	
						JOptionPane.showMessageDialog(this, "You were killed by a ninja!\nTry again.","Dead",JOptionPane.INFORMATION_MESSAGE);
						ge.resetPlayer();
						printInfo();
					}
				}
			}
		}
		
		if(shoot)
		{
			switch(ge.shoot(direction))
			{
			case "noBullet":
				runningMsg.setText("You run out of bullet!");
				break;
			case "kill":
				runningMsg.setText("You killed a ninja!");
				break;
			case "notKill":
				runningMsg.setText("You hit nothing!");
				break;
			}
			printInfo();	
		}
		
		if(look)
		{
			if(ge.playerCanLook())
			{
				String directionMsg="";
				switch(direction)
				{
				case 'w':
					directionMsg="upward";
					break;
				case 's':
					directionMsg="downward";
					break;
				case 'a':
					directionMsg="leftward";
					break;
				case 'd':
					directionMsg="rightward";
					break;
				}
				if(ge.look(direction))
					runningMsg.setText("You found a ninja "+directionMsg);
				else
					runningMsg.setText("There is no ninja "+directionMsg);
				printInfo();
			}
			else
				runningMsg.setText("You can't look any more at this turn!");
		}
	}
	
	private class startNewGame implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			mainLayout.show(GUI.this.getContentPane(), "main");
			inGame=true;
			ge.createNewGame();
			GUI.this.printInfo();
			
		}
	}
	
	private void printInfo()
	{
		grid.setText(ge.toString(isDebug));
		info.setText("lives:"+ge.getLives()+"\tbullet:"+ge.numOfBullet()+"/1\tinvicible:"+ge.turnsOfInvinc());
	}
	
	
}
