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

/**
 * This class is called Spy, it extends the {@link SquareObject} class and implements 
 * {@link Dynamic Agents} interface. The player is represented as the spy in the game.
 * This class represents the spy and his or her attributes and behaviors.
 *
 * @author Wing Hung Lau
 *
 */
public class Spy extends SquareObject implements DynamicAgents{

	/**
	 * This field is used to keep track of how many {@link #lives} the {@link Spy} has.
	 * Initially, the {@link Spy} has {@code 3} {@link #lives}.
	 */
	private int lives;
	
	/**
	 * This field is used to keep track of how many {@link #bullet} the {@link Spy} has.
	 * Initially, the {@link Spy} has {@code 1} {@link #bullet}.
	 */
	private int bullet;
	
	/**
	 * This field is used to keep track if {@link Spy} is dead or still alive.
	 * Initially, the {@link Spy} is alive, which is {@link #isDead} is {@code false}.
	 * If the {@link Spy} is {@link #beAttacked()}, {@link #isDead} becomes {@code true}.
	 */
	private boolean isDead;
	
	/**
	 * The default constructor for the class {@link Spy}. Initially a
	 * {@link Spy} has {@link #lives} {@code 3}, {@link #bullet} {@code 1}, and is alive,
	 * so {@link #isDead} {@code false}.
	 */
	public Spy(){
		super();
		lives = 3;
		bullet = 1;
		isDead = false;
	}

	/**
	 * This method is called when the {@link Spy} is attacked by the {@link Ninja}.
	 * The {@link #isDead} changes from {@code false} to {@code true}.
	 */
	public void beAttacked() {
		isDead = true;
	}
	
	/**
	 * This method is used to return the status of the {@link Spy}.
	 * @return If the {@link Spy} is alive, return {@link #isDead} is {@code false},
	 * 		   if the {@link Spy} is dead, {@link #isDead} is {@code true}.
	 */
	public boolean isDead() {
		return isDead;
	}
	
	/**
	 * This method is used to return how many lives the {@link Spy} has.
	 * @return the number of {@link #lives} of the {@link Spy}
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * This method is used to return how many bullet the {@link Spy} has.
	 * @return the number of {@link #bullet} of the {@link Spy}
	 */
	public int getBullet() {
		return bullet;
	}
	
	/**
	 * This method is used to print and show where the {@link Spy} is.
	 * 
	 * @param isDebug
	 * 		isDebug is a boolean type, it uses to store if it is debug mode or not 
	 *@return the string [S], which is represented the {@link Spy}
	 */
	@Override
	public String toString(boolean isDebug) {
		return "[S]";
	}
	
}
