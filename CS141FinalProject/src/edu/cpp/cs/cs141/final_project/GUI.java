package edu.cpp.cs.cs141.final_project;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
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
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridLayout;

public class GUI extends JFrame {
	
	private JPanel welcomePanel;
	
	private JPanel mainPanel;
	
	private JPanel helpPanel;
	
	private CardLayout mainLayout;
	
	private JPanel grid;
	
	private GameEngine ge;
	
	private boolean isDebug;
	
	private boolean inGame;
	
	private JTextArea runningMsg;
	
	private JTextArea info;
	
	private ImageIcon spy,ninja,room,briefcase,unknown,radar,invinc,bullet,empty;
	
	public GUI(GameEngine ge)
	{
		setTitle("Find the lost PI");
		this.ge=ge;
		isDebug=false;
		inGame=false;
		this.setSize(600,700);
		mainLayout= new CardLayout();
		getContentPane().setLayout(mainLayout);
		setIcon();
		setWelcomePanel();
		setHelpPanel();
		setMainPanel();
		getContentPane().add(welcomePanel,"welcome");
		getContentPane().add(helpPanel,"help");	
		getContentPane().add(mainPanel,"main");
			
	}
	
	private void setIcon()
	{
		spy=new ImageIcon("icon/spy.png");
		ninja=new ImageIcon("icon/ninja.png");
		room=new ImageIcon("icon/room.png");
		briefcase=new ImageIcon("icon/case.png");
		unknown=new ImageIcon("icon/unknown.png");
		radar=new ImageIcon("icon/radar.png");
		invinc=new ImageIcon("icon/invinc.png");
		empty=new ImageIcon("icon/empty.png");
		bullet=new ImageIcon("icon/bullet.png");
	}
	
	private void setWelcomePanel()
	{
		welcomePanel=new MainPanel();
		welcomePanel.setLayout(new BoxLayout(welcomePanel,BoxLayout.Y_AXIS));
		
		JLabel welcomeMsg = new JLabel(new ImageIcon("icon/title.png"));
		welcomeMsg.setAlignmentX(CENTER_ALIGNMENT);
		welcomeMsg.setFont(new Font("Kristen ITC", Font.BOLD, 45));
		
		JButton start,load,help,quit;
		start=new JButton("Start New Game");
		start.addActionListener(new startNewGame());

		start.setFont(new Font("Tahoma", Font.PLAIN, 25));
		load=new JButton("Load Game");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.loadGame();
			}
		});
		load.setFont(new Font("Tahoma", Font.PLAIN, 25));
		help=new JButton("Help");
		help.setFont(new Font("Tahoma", Font.PLAIN, 25));
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(GUI.this.getContentPane(), "help");
				
			}
		});
		quit=new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI.this.dispose();
			}
		});
		quit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		start.setAlignmentX(CENTER_ALIGNMENT);
		load.setAlignmentX(CENTER_ALIGNMENT);
		help.setAlignmentX(CENTER_ALIGNMENT);
		quit.setAlignmentX(CENTER_ALIGNMENT);
		welcomePanel.add(Box.createRigidArea(new Dimension(0,20)));
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
		helpPanel=new MainPanel();
		helpPanel.setLayout(layout);
		JButton OK=new JButton("OK");
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inGame)
					mainLayout.show(GUI.this.getContentPane(), "welcome");
				else
					mainLayout.show(GUI.this.getContentPane(), "main");
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		helpPanel.add(scrollPane, gbc_scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JTextArea helpText = new JTextArea();
		scrollPane.setViewportView(helpText);
		helpText.setText(this.help());
		helpText.setBackground(Color.WHITE);
		helpText.setFont(new Font("MS PGothic", Font.PLAIN, 18));
		helpText.setEditable(false);
		helpText.setColumns(30);
		helpText.setRows(15);
		OK.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_OK = new GridBagConstraints();
		gbc_OK.insets = new Insets(30, 0, 40, 0);
		gbc_OK.gridx = 0;
		gbc_OK.gridy = 2;
		helpPanel.add(OK, gbc_OK);
		JLabel helpLabel = new JLabel("Help");
		helpLabel.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		GridBagConstraints gbc_helpLabel = new GridBagConstraints();
		gbc_helpLabel.fill = GridBagConstraints.VERTICAL;
		gbc_helpLabel.insets = new Insets(20, 0, 20, 0);
		gbc_helpLabel.gridx = 0;
		gbc_helpLabel.gridy = 0;
		helpPanel.add(helpLabel, gbc_helpLabel);
		
	}
	
	private void setMainPanel()
	{
		GridBagLayout layout=new GridBagLayout();
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		layout.columnWeights = new double[]{1.0};
		mainPanel=new MainPanel();
		mainPanel.setLayout(layout);
		JMenuBar menu=new JMenuBar();
		
		JMenu game = new JMenu("Game");
		game.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menu.add(game);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		newGame.addActionListener(new startNewGame());
		game.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Load Game");
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.this.loadGame();
			}
		});
		game.add(loadGame);
		loadGame.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		JMenuItem saveGame = new JMenuItem("Save Game");
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});
		game.add(saveGame);
		saveGame.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(GUI.this.getContentPane(), "welcome");
				GUI.this.inGame=false;
			}
		});
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
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(GUI.this, "This game is presented by\nTeam Spirit Coders:\n"+
														"Wing Hung Lau\n"+
														"Michael Tang\n"+
														"Donovan Gonzalez\n"+
														"Lynn Nguyen\n"+
														"Xinyuan Wang\n"+
														"Connor Chase\n");
			}
		});
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
		grid=new JPanel();
		GridBagConstraints gbc_grid = new GridBagConstraints();
		gbc_grid.insets = new Insets(5, 0, 5, 0);
		gbc_grid.gridy = 1;
		gbc_grid.gridx = 0;
		mainPanel.add(grid, gbc_grid);
		grid.setLayout(new GridLayout(9, 9, 0, 0));
		
		
		JPanel controlPanel=new JPanel();
		controlPanel.setOpaque(false);
		GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.weighty = 0.5;
		gbc_controlPanel.insets = new Insets(5, 0, 0, 0);
		gbc_controlPanel.gridy = 3;
		gbc_controlPanel.gridx = 0;
		mainPanel.add(controlPanel, gbc_controlPanel);
		
		JRadioButton move = new JRadioButton("Move");
		move.setOpaque(false);
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
		shoot.setOpaque(false);
		
		JRadioButton look = new JRadioButton("Look");
		look.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runningMsg.setText("  choose a direction to look  \n[w]up [s]down [a]left [d]right");
			}
		});
		look.setSelected(false);
		look.setOpaque(false);
		look.setFont(new Font("Tahoma", Font.PLAIN, 22));
		controlPanel.add(look);
		
		ButtonGroup controlGroup=new ButtonGroup();
		controlGroup.add(move);
		controlGroup.add(shoot);
		controlGroup.add(look);
		
		runningMsg=new JTextArea();
		runningMsg.setOpaque(false);
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
		info.setOpaque(false);
		
		
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
				runningMsg.setText("You got a radar and the location of\nthe PI has been detected.");
				break;
			case "invincible": 
				runningMsg.setText("You will be invincible for 5 turns.");
				break;
			case "getCase":
				isWinning=true;
				JOptionPane.showMessageDialog(this, "You found the PI!\nYou won the game!","Wining",JOptionPane.INFORMATION_MESSAGE);
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
						JOptionPane.showMessageDialog(this, "You were killed by a triangle!\nTry again.","Dead",JOptionPane.INFORMATION_MESSAGE);
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
					runningMsg.setText("You found a triangle "+directionMsg);
				else
					runningMsg.setText("There is no triangle "+directionMsg);
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
	
	
	private void saveGame()
	{
		String filename = JOptionPane.showInputDialog(this,"Enter the name of the file you want to save:","Save Game",
				JOptionPane.INFORMATION_MESSAGE);
		if(filename!=null)
			try {
				ge.saveGame(filename);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error!","Error",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	private void loadGame()
	{
		File curDir = new File(".");
		File[] allFile=curDir.listFiles();
		int numOfSaveFile=0;
		for(File x:allFile)
		{
			if(x.getName().endsWith(".dat"))
				numOfSaveFile++;	
		}
		if(numOfSaveFile==0)
			JOptionPane.showMessageDialog(this, "No valid file of saved game found!","Not found",JOptionPane.WARNING_MESSAGE);
		else
		{
			String[] fileName = new String[numOfSaveFile];
			int i=0;
			for(File x:allFile)
			{
				if(x.getName().endsWith(".dat"))
				{
					fileName[i]=x.getName().replace(".dat", "");
					i++;
				}
			}
			String file=(String)JOptionPane.showInputDialog(this,"Select the saved game you want to load:","Load Game",
					JOptionPane.PLAIN_MESSAGE,null,fileName,fileName[0]);
			if(file!=null)
			{
				try {
					ge.loadGame(file);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(this, "Error!","Error",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Error!","Error",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
				mainLayout.show(this.getContentPane(), "main");
				this.printInfo();
			}
		}	
	}
	
	private String help()
	{
			String helpString="";
			
			try(BufferedReader br=new BufferedReader(new FileReader("helpforGUI.txt")))
			{
				String temp;
				while((temp=br.readLine())!=null)
				{
					helpString+=temp;
					helpString+="\n";
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error!","Error",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			return helpString;
	}
	
	private void printInfo()
	{
		grid.removeAll();
		info.setText("lives:"+ge.getLives()+"\tbullet:"+ge.numOfBullet()+"/1\tinvicible:"+ge.turnsOfInvinc());
		String rowString=ge.toString(isDebug);
		String processString="";
		for(int i=0;i<rowString.length();i++)
		{
			if(rowString.charAt(i)=='[')
				processString+=rowString.charAt(i+1);
		}
		
		JLabel[] gridLabel=new JLabel[81];
		for(int i=0;i<81;i++)
		{
			switch(processString.charAt(i))
			{
			case '*':
				gridLabel[i]=new JLabel(unknown);
				break;
			case ' ':
				gridLabel[i]=new JLabel(empty);
				break;
			case 'S':
				gridLabel[i]=new JLabel(spy);
				break;
			case 'R':
				gridLabel[i]=new JLabel(room);
				break;
			case 'N':
				gridLabel[i]=new JLabel(ninja);
				break;
			case 'r':
				gridLabel[i]=new JLabel(radar);
				break;
			case 'i':
				gridLabel[i]=new JLabel(invinc);
				break;
			case 'b':
				gridLabel[i]=new JLabel(bullet);
				break;
			case 'C':
				gridLabel[i]=new JLabel(briefcase);
				break;
			}
		}
		
		for(JLabel l:gridLabel)
		{
			grid.add(l);
		}
	}
	
	private class MainPanel extends JPanel {
		private BufferedImage background ;
		
		public MainPanel(){
			try {
				background=ImageIO.read(new File("icon/background.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(background, 0, 0,getWidth(),getHeight(),this);
		}
	}
	
	
}
