/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.util.Scanner;

/**
 * @author Wing Hung Lau
 *
 */
public class TextUI {
	
	private GameEngine ge;
	private boolean isDebug;
	private Scanner Input = new Scanner(System.in);
	
	public TextUI(GameEngine ge)
	{
		this.ge=ge;
		isDebug = false;
	}
	
	public void start() {
		System.out.println("Game starts.");
	}
	
	public void chooseMode() {
		System.out.println("Would you like to enter Debug Mode? \n 1. Yes 	2. No");
		if(Input.nextInt() == 1) {
			isDebug = true;
		}
		else {
			isDebug = false;
		}
		System.out.println(ge.toString(isDebug));
	}
	
	public void winGame() {
		System.out.print("Spy won the game.");
	}

	public void loseGame() {
		System.out.print("Spy lost the game.");
	}
	
	public void spyLives() {
		System.out.println("Lives: " + ge.lives());
	}
	
	public void spyBullets() {
		System.out.println("Bullet: " + ge.bullets());
	}
	
	public void spyInvinc() {
		System.out.println("Invincibility: " + ge.invinc());
	}
	
	public void ninjaInfo() {
		System.out.println("Number of Ninja: " + ge.numOfNinja());
	}
	
	public void getRadar() {
		System.out.println("Spy got a radar.");
	}
	
	public void getInvinc() {
		System.out.println("Spy will be invincible for 5 turns.");
	}
	
	public void getBullet() {
		System.out.println("Spy's gun is reloaded.");
	}
	
	public void emptyRooms() {
		System.out.println("This room is empty.");
	}
	
	public void invalidInput() {
		System.out.println("Invalid input. Please input again.");
	}
	
	public void chooseMovement() {
		System.out.println("Choose your movement: [m] Move  [l] Look  [s] Shoot");
		char c = Input.next().charAt(0);
		if(c != 'm' || c != 'l' || c != 's') {
			invalidInput();
			c = Input.next().charAt(0);
		}
		else {
			ge.playerMove(c);
		}
	}
	
	public void chooseDirection() {
		System.out.println("Choose the direction that you want to move: [w] Up  [a] Left  [s] Down  [d] Right");
		char c = Input.next().charAt(0);
		if(c != 'w' || c != 'a' || c != 's' || c != 'd') {
			invalidInput();
			c = Input.next().charAt(0);
		}
	}	
}
