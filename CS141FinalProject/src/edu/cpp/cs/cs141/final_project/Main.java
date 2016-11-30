/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import javax.swing.JFrame;

/**
 * @author 
 *
 */
public class Main {

	/**
	 * @param args
	 * if any argument is passed, the GUI will be used
	 * else the TextUI will be used
	 */
	public static void main(String[] args) {
		GameEngine ge=new GameEngine();
		if(args.length!=0)
		{
			GUI gui=new GUI(ge);
			gui.setVisible(true);
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else
		{
			TextUI TUI=new TextUI(ge);
			TUI.mainLoop();
		}

	}

}
