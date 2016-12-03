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
 * This class is called Spy, it extends the {@link SquareObject} class and implements 
 * {@link Dynamic Agents} interface. The player is represented as the spy in the game.
 * This class represents the spy and his or her attributes and behaviors.
 *
 * @author Wing Hung Lau
 *
 */
public class Spy extends SquareObject{

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
	 * This field is used to keep track of the direction of the {@link Spy}.
	 */
	private char direction;
	
	/**
	 * This field is used to determine if the {@link Spy} can look or not.
	 * If the {@link Spy} can look {@link #canLook} is {@code true},
	 * if not, {@link #canLook} is {@code false}.
	 */
	private boolean canLook;
	
	/**
	 * This field is used to keep track of how many invincibility the {@link Spy} has.
	 * Initially, the {@link Spy} has {@code 5} {@link #invinc}.
	 */
	private int invinc;
	
	/**
	 * The default constructor for the class {@link Spy}. Initially a
	 * {@link Spy} has {@link #lives} {@code 3}, {@link #bullet} {@code 1}, {@link #invinc} {@code 0},
	 * and is alive, so {@link #isDead} {@code false}.
	 */
	public Spy(){
		lives = 3;
		bullet = 1;
		invinc = 0;
		this.resetSpy();
	}
	
	/**
	 * This method is used to reset the {@link Spy}.
	 * Initially, {@link #isDead} {@code false}, {@link #canLook} {@code true},
	 * {@link #direction} is towards the upward direction, and to the initial position
	 * to the bottom left corner on the {@link Grid}.
	 */
	public void resetSpy() {
		isDead=false;
		canLook=true;
		direction='w';
		this.setLocation(0, 8);	
	}

	/**
	 * This method is called when the {@link Spy} is attacked by the {@link Ninja}.
	 * The {@link #isDead} changes from {@code false} to {@code true}.
	 */
	public void beAttacked() {
		isDead = true;
		lives--;
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
	 * This method is used to return the {@link #direction} of the {@link Spy}.
	 * @return the {@link #direction} of the {@link Spy}
	 */
	public char getDirection() {
		return direction;
	}
	
	/**
	 * This method is used to set the {@link #invinc} of the {@link Spy} from {@code 0} to {@code 5}.
	 */
	public void beInvinc() {
		invinc = 5;
	}
	
	/**
	 * This method is used to return the {@link #invinc} of the {@link Spy}.
	 * @return the {@link #invinc} of the {@link Spy}
	 */
	public int getInvinc() {
		return invinc;
	}
	
	/**
	 * This method is used to control and set the {@link #canLook} of the {@link Spy}.
	 * @param control
	 */
	public void lookControl(boolean control)
	{
		canLook=control;
	}
	
	/**
	 * This method is used to return the {@link #canLook} of the {@link Spy}.
	 * @return the {@link #canLook} of the {@link Spy}
	 */
	public boolean canLook()
	{
		return canLook;
	}
	
	/**
	 * This method is used to keep track of the turn of {@link #invinc}.
	 * After each turn of {@link #invinc} is used, {@link #invinc} will decrease by one.
	 */
	public void weakenInvinc() {
		invinc--;
	}
	
	/**
	 * This method is used to add the number of {@link #bullet}.
	 * If the number of {@link #bullet} is {@code 0}, and the {@link Spy} pick up {@link Bullet},
	 * the number of {@link #bullet} will increase by one.
	 */
	public void addBullet()
	{
		if(bullet==0)
			bullet++;
	}
	
	/**
	 * This method is used to keep track of the number of {@link #bullet}.
	 * Every time the {@link Spy} attacks and shoots the {@ link Ninja}, 
	 * the number of {@link #bullet} will decrease by one.
	 */
	public void shoot()
	{
		bullet--;
	}
	
	/**
	 * This method is used to change the {@link #direction} of the {@link Spy}.
	 * 
	 * @param Direction
	 */
	public void changeDirection(char Direction) {
		direction = Direction;
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
