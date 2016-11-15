/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.util.Scanner;

/**
 * @author
 *
 */
public class TextUI {
	
	private GameEngine ge;
	public Scanner Input = new Scanner(System.in);
	
	public TextUI(GameEngine ge)
	{
		this.ge=ge;
	}
	
	public void StartNewGame()
	{
		ge.createNewGame();
		System.out.println("Would you like to enter Debug Mode?");
		System.out.println("1. Yes 2. No");
		switch(Input.nextInt())
		{
		case 1:
			ge.setDebug(1);
			break;
		case 2:
			ge.setDebug(2);
			break;
		default:
			ge.setDebug(2);
		}
		System.out.println(ge.toString(ge.getDebug()));
	}

}
