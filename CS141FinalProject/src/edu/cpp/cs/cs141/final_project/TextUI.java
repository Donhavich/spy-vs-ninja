/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.io.IOException;
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
		isDebug = true;
	}
	
	public void welcomeMessage() {
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
	}
	
	public void winGame() {
		System.out.print("Spy won the game.");
	}

	public void loseGame() {
		System.out.print("Spy lost the game.");
	}
	
	public void spyLives() {
		System.out.println("Lives: " + ge.getLives());
	}
	
	public void spyBullets() {
		System.out.println("Bullet: " + ge.numOfBullet());
	}
	
	public void spyInvinc() {
		System.out.println("Invincibility: " + ge.turnsOfInvinc());
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
	
	public void ninjasAppear() {
		System.out.println("There is/are ninja(s) ahead of you.");
	}
	
	public void ninjasNotAppear() {
		System.out.println("There is/are no ninja(s).");
	}
	
	public void zeroBullet() {
		System.out.println("There is zero bullet.");
	}
	
	public void killedNinjas() {
		System.out.println("You killed a ninja.");
	}
	
	public void failedToKillNinjas() {
		System.out.println("You failed to kill a ninja.");
	}
	
	public char chooseMovement() {
		System.out.println("Choose your movement: [m] Move  [l] Look  [s] Shoot");
		char c = Input.next().charAt(0);
		while(c != 'm' && c != 'l' && c != 's') {
			invalidInput();
			c = Input.next().charAt(0);
		}
		return c;
	}
	
	public char chooseDirection() {
		System.out.println("Choose the direction that you want to move: [w] Up  [a] Left  [s] Down  [d] Right");
		char c = Input.next().charAt(0);
		while(c != 'w' && c != 'a' && c != 's' && c != 'd') {
			invalidInput();
			c = Input.next().charAt(0);
		}
		return c;
	}	
	
	private String Info()
	{
		return "lives:"+ge.getLives()+"|bullet:"+ge.numOfBullet()+"|invincible:"+ge.turnsOfInvinc();
		
	}
	
	public void StartNewGame() throws IOException, ClassNotFoundException {
		boolean winning = false;
	
		welcomeMessage();
		ge.createNewGame();
		chooseMode();
		
		while(!ge.isGameOver() && !winning) {
			boolean isDead = false;
			ge.resetPlayer();
			while(!isDead && !winning) {
				System.out.println(ge.toString(isDebug));
				System.out.println(this.Info());
				char move = chooseMovement();
				if(move == 'm') {
					boolean noMove;
					do {
						noMove=false;
						char direction = chooseDirection();
						String action = ge.playerMove(direction);
						switch(action) {
						case "noMove": noMove = true;
						System.out.println("cant move there");
							break;
						case "noCase": emptyRooms();
							break;
						case "bullet:": getBullet();
							break;
						case "radar": getRadar();
							break;
						case "invincible": getInvinc();
							break;
						case "getCase": winGame();
							winning = true;
							break;
						}
					} while(noMove);
					if(!winning) {
						isDead = ge.ninjaTurn();
						if(isDead)
						{
							System.out.println("you are dead");
							Input.nextLine();
							Input.nextLine();
						}
					}
				}
				else if(move == 'l') {
					char direction = chooseDirection();
					if(ge.look(direction)) {
						ninjasAppear();
					}
					else {
						ninjasNotAppear();
					}
				}
				else if(move == 's') {
					char direction = chooseDirection();
					String action = ge.shoot(direction);
					switch(action) {
					case "noBullet" : zeroBullet();
						break;
					case "kill" : killedNinjas();
						break;
					case "notKill" : failedToKillNinjas();
						break;
					}
				}
			}
		}
		if(!winning) {
			loseGame();
		}
	}
}
