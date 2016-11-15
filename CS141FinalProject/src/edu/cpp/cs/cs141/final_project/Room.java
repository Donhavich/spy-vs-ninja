/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment: Final Project Milestone 1
 * 
 * Description of assignment: We are creating the architecture of the classes of our game
 * and printing out the display of our grid.
 *
 * Team Spirit Coders
 *	Connor Chase
 *	Donovan Gonzalez
 * 	Wing Hung Lau
 * 	Lynn Nguyen
 * 	Michael Tang
 * 	Xinyuan Wang
 */
package edu.cpp.cs.cs141.final_project;

/**
 * The class Room extends the {@link SquareObject} class. There are nine of {@link Room} printed
 * and equally distributed across the {@link Grid} but only one {@link Room} holds the briefcase
 * of documents that the {@link Spy} must retrieve to win the game. 
 * @author
 *
 */
public class Room extends SquareObject {
	
	/**
	 * 
	 */ 

	private boolean hasCase=false;
	
	public Room(){};
	
	public boolean hasCase()
	{
		return hasCase;
	}
	
	public void giveCase()
	{
		hasCase=true;
	}

	@Override
	public String toString(boolean isDebug) {
		if(hasCase&&isDebug)
			return "[C]";
		else
			return "[R]";
	}

	
	
}
