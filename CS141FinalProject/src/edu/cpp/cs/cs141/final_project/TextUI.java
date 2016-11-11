/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public class TextUI {
	
	private GameEngine ge;
	
	public TextUI(GameEngine ge)
	{
		this.ge=ge;
	}
	
	public void StartNewGame()
	{
		ge.createNewGame();
		System.out.println(ge.toString(true));
	}

}
