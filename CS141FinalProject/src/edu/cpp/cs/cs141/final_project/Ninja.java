/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Final Project	
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

/**
 * This class is called Ninja, it extends the {@link SquareObject} class and implements 
 * {@link Dynamic Agents} interface. The enemy is represented as the Ninja in the game.
 * This class represents the Ninja's attributes and behaviors.
 *
 * @author Wing Hung Lau
 *
 */
public class Ninja extends SquareObject {

	/**
	 * This field is used to keep track if {@link Ninja} is dead or still alive.
	 * Initially, the {@link Ninja} is alive, which is {@link #isDead} is {@code false}.
	 * If the {@link Ninja} is {@link #beAttacked()} by the {@link Spy}
	 * ,{@link #isDead} becomes {@code true}.
	 */
	private boolean isDead;
	
	/**
	 * The default constructor for the class {@link Ninja}. Initially a
	 * {@link Ninja} is alive, so {@link #isDead} {@code false}.
	 * @param x
	 * @param y
	 */
	public Ninja(int x, int y){
		super(x,y);
		isDead = false;
	}
	
	/**
	 * This method is called when the {@link Ninja} is attacked by the {@link Spy}.
	 * The {@link #isDead} changes from {@code false} to {@code true}.
	 */
	public void beAttacked() {
		isDead = true;
	}
	
	/**
	 * This method is used to return the status of the {@link Ninja}.
	 * @return If the {@link Ninja} is alive, return {@link #isDead} is {@code false},
	 * 		   if the {@link Ninja} is dead, {@link #isDead} is {@code true}.
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * This method is used to print and show where the {@link Ninja} is.
	 * @param isDebug
	 * 		isDebug is a boolean type, it uses to store if it is debug mode or not 
	 * @return  the string [N] is used to represent the {@link Ninja}
	 *  		when it is the debug mode, when it is not the debug mode, 
	 * 		    the string [*] is used.
	 */
	@Override
	public String toString(boolean isDebug) {
		if(isDebug||this.isVisible())
			return "[N]";
		else
			return "[*]";
	}
	
}
