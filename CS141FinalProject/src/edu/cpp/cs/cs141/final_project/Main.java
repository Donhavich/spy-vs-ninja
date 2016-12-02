/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Final Project Milestone 1
 *
 * Description of assignment:
 * 		The first milestone for the final project is to set up the whole architecture,
 * 		including the list of classes, with all the fields and methods.
 *
 * Team: Spirit Coders 
 * 		Wing Hung Lau
 * 		Michael Tang
 * 		Donovan Gonzalez
 * 		Lynn Nguyen
 * 		Xinyuan Wang
 * 		Connor Chase
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
