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
 *
 * @author
 *
 */
public class Room extends SquareObject {
	
	/**
	 * This field represents whether {@link Room} holds the case. If {@link Room} does not
	 * contain the briefcase, {@link #hasCase} is {@code false}. Otherwise, if {@link Room}
	 * does contain the briefcase, {@link #hasCase} is true. Only one {@link Room} will
	 * have the briefcase for {@link Spy} to find.
	 */ 
	private boolean hasCase=false;
	
	private boolean isCaseVisible=false;
	
	/**
	 * This is the default constructor for the class {@link Room}. This initializes the
	 * {@link Room} class.
	 */
	public Room(int x , int y){
	 super(x,y);	
	}
	
	/**
	 * This method returns whether {@link Room} contains the briefcase to {@link Spy}.
	 * @return when {@link Spy} enters {@link Room} and if {@link Room} has the
	 * 	   briefcase, then {@link #hasCase} is {@code true}. If not, then
	 *	   {@link #hasCase} is {@code false}.
	 */
	public boolean hasCase()
	{
		return hasCase;
	}
	
	/**
	 * This method gives the briefcase if {@link Spy} enters the {@link Room}
	 * with the briefcase. If {@link Room} has the briefcase, then
	 * {@link #hasCase} is true and it is given to {@link Spy] who wins the game.
	 * If not, then {@link #hasCase} is false and {@link Spy} must continue
	 * searching for the briefcase.
	 */
	public void giveCase()
	{
		hasCase=true;
	}
	
	public void radarEffect()
	{
		isCaseVisible=true;
	}
	
	
	/**
	 * This method prints and displays which room has the briefcase.
	 * @param isDebug
	 * 		 The isDebug is used if debug mode is executed by the
	 * 		 user.
	 * @return when debug mode is executed by user, if {@link Room} has the
	 * 	   briefcase and {@link #hasCase} is true, then [C] is printed
	 * 	   over which room has the case. When the debug mode is not used,
	 * 	   only [R] is printed for all nine rooms and does not show the
	 * 	   case.	 
	 */
	@Override
	public String toString(boolean isDebug) {
		
		if(hasCase&&(isCaseVisible||isDebug))
			return "[C]";
		
		else
			return "[R]";
	}

	
	
}
