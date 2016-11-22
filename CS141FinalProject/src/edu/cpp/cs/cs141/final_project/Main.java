/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.io.IOException;

/**
 * @author 
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		GameEngine ge=new GameEngine();
		TextUI TUI=new TextUI(ge);
		TUI.StartNewGame();

	}

}
