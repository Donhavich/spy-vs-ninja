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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is the {@link TextUI} class, which stands for user interface.
 * The {@link TextUI} class is in charge of the interactive part of the program.
 * Its duty is mainly displaying information and getting input from the user.
 * Also, it connects the {@link Main} class with the {@link GameEngine} class.
 * 
 * @author Wing Hung Lau, Xinyuan Wang
 *
 */
public class TextUI {
	
	/**
	 * This field is the {@link GameEngine}, the {@link TextUI} controls the {@link GameEngine}.
	 */
	private GameEngine ge;
	
	/**
	 * This field is used to determine the mode of the game.
	 */
	private boolean isDebug;
	
	/**
	 * The {@link #Input} has the type of {@link Scanner} in the {@link TextUI} class.
	 * It is an input stream and a private field of the {@link TextUI} class.
	 */
	private Scanner Input = new Scanner(System.in);
	
	/**
	 * The default constructor for the class {@link TextUI}.
	 * Initially, {@link #isDebug} is {@code false}.
	 * @param ge
	 */
	public TextUI(GameEngine ge) {
		this.ge = ge;
		isDebug = false;
	}
	
	/**
	 * The method is the main loop of the game,
	 * it controls the order of the game.
	 */
	public void mainLoop()
	{
		welcomeMessage();
		char choice;
		do {
			choice = mainMenu();
			switch(choice)
			{
			case '1':
				StartNewGame();
				break;
			case'2':
				loadGame();
				break;
			case'3':
				help();
			}	
		}while(choice!='4');
		System.exit(0);
	}
	
	/**
	 * The method is used to show the menu of the game,
	 * it provides different options to the user to choose from.
	 */
	private char mainMenu() {
		String msg="1.New Game\n2.Load Game\n3.Help\n4.Exit\n";
		char choice= getChar(msg);
		while(choice!='1'&&choice!='2'&&choice!='3'&&choice!='4') {
			invalidInput();
			choice = getChar(msg);
		}
		return choice;
	}
	
	/**
	 * The method is used to start a new game.
	 * Since it is starting a new game, it will create a new game.
	 */
	private void StartNewGame() {
		ge.createNewGame();
		gameLoop();
	}
	
	/**
	 * The method is used to load the game after the user saves the game.
	 * It will ask the user to input the file name, so the program will check and load the game if the file is found.
	 */
	private void loadGame() {
		System.out.println("Please enter the name of the file that you have saved the game:");
		String fileName=Input.nextLine();
		boolean loaded=false;
		while(!loaded)
		{
			try {
				ge.loadGame(fileName);
				loaded=true;
			} 
			catch (java.io.FileNotFoundException e) {
				System.out.println("File was not found! Please try again!");
				fileName=Input.nextLine();
			} 
			catch (ClassNotFoundException|IOException e) {
				e.printStackTrace();
			}
		}
		gameLoop();
	}
	
	/**
	 * The method is the loop of the game, the game keeps going when either the {@link Spy} is still searching
	 * for the briefcase or the {@link Spy} still has lives. The user can choose whatever movement they want:
	 * move, look or shoot. There will be different actions happened and taken place for the three movements.
	 */
	private void gameLoop() {
		boolean winning = false;
		mainloop:
		while(!ge.isGameOver() && !winning) {
			boolean isDead = false;
			while(!isDead && !winning) {
				this.printInfo();
				char move = chooseMovement();
				if(move == 'm') {
					boolean noMove;
					do {
						noMove=false;
						char direction = chooseDirection();
						String action = ge.playerMove(direction);
						switch(action) 
						{
						case "noMove": 
							noMove = true;
							cantMove();
							break;
						case "noCase": 
							emptyRooms();
							break;
						case "bullet": 
							getBullet();
							break;
						case "radar": 
							getRadar();
							break;
						case "invincible": 
							getInvinc();
							break;
						case "getCase": 
							winGame();
							winning = true;
							break;
						}
					} while(noMove);
					
					if(!winning) {
						isDead = ge.ninjaTurn();
						if(isDead) {
							this.printInfo();
							ge.resetPlayer();
							this.playerBeingKilled();
						}
					}
				}
				else if(move == 'l') {
					if(ge.playerCanLook()) {
						char direction = chooseDirection();
						if(ge.look(direction)) 
							ninjasAppear();
						else
							ninjasNotAppear();
					}
					else {
						System.out.println("You can't look any more at this turn! Please try other movements!");
						pause();
					}
				}			
				else if(move == 's') {
					char direction = chooseDirection();
					String action = ge.shoot(direction);
					switch(action) 
					{
					case "noBullet" : 
						zeroBullet();
						break;
					case "kill" : 
						killedNinjas();
						break;
					case "notKill" : 
						failedToKillNinjas();
						break;
					}
				}
				else if(move == '!') {
					System.out.println("Please enter the name of the file you want to save in:");
					String filename =Input.nextLine();
					try {
						ge.saveGame(filename);
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
					break mainloop;
				}
			}
		}
		
		if(ge.isGameOver())
			this.loseGame();
		else
			pause();
	}

	/**
	 * This method is only used to print out the welcome message and greet the user.
	 */
	public void welcomeMessage() {
		System.out.println("---------------------------------------------Death Task---------------------------------------------------");
		System.out.println("As a top spy in the world, you are sent to carry out this dangerous but yet glorious task by your country!");
	}
	
	/**
	 * This method is used to switch the mode of the game, from not debug mode to debug mode, and vice versa.
	 */
	public boolean switchMode(char c) {
		if(c == '*') {
			if(isDebug)
				isDebug = false;
			else
				isDebug = true;
			this.printInfo();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * This method is used to inform the user the game is currently paused,
	 * and provide instruction to how to continue to play the game.
	 */
	private void pause() {
		System.out.println("[Press Enter to continue]");
		Input.nextLine();
	}
	
	/**
	 * This method is used to inform the user he/she wins the game because he/she gets the briefcase.
	 */
	private void winGame() {
		System.out.println("You got the briefcase! You won the game!");
		pause();
	}

	/**
	 * This method is used to inform the user he/she loses the game.
	 */
	private void loseGame() {
		System.out.println("The game is over! You lost!");
		pause();
	}
	
	/**
	 * This method is used to inform the user he/she gets the {@link Radar}, and where the briefcase is.
	 */
	private void getRadar() {
		System.out.println("You got a radar and the location of the briefcase has been detected.");
		pause();
	}
	
	/**
	 * This method is used to inform the user gets the {@link Invinc} and he/she will be invincible for 5 turns.
	 */
	private void getInvinc() {
		System.out.println("You will be invincible for 5 turns.");
		pause();
	}
	
	/**
	 * This method is used to inform the user gets the {@link Bullet} and his/her gun will be reloaded.
	 */
	private void getBullet() {
		System.out.println("You found a bullet. Your gun is reloaded.");
		pause();
	}
	
	/**
	 * This method is used to inform the room that the user chooses to enter is an empty room.
	 */
	private void emptyRooms() {
		System.out.println("Sorry, this room is empty. Try another one.");
		pause();
	}
	
	/**
	 * This method is used to inform the user his/her input is not valid, they have to input again.
	 */
	private void invalidInput() {
		System.out.println("Invalid input. Please input again.");
		pause();
	}
	
	/**
	 * This method is used to inform the user to be careful since there is/are {@link Ninja}(s).
	 */
	private void ninjasAppear() {
		System.out.println("DANGER AHEAD!");
		pause();
	}
	
	/**
	 * This method is used to inform the user he/she is safe since there is/are no {@link Ninja}(s).
	 */
	private void ninjasNotAppear() {
		System.out.println("Safe ahead.");
		pause();
	}
	
	/**
	 * This method is used to inform the user his/her gun runs out of bullet.
	 */
	private void zeroBullet() {
		System.out.println("You run out of bullet.");
		pause();
	}
	
	/**
	 * This method is used to inform the user that he/she kills a {@link Ninja}.
	 */
	private void killedNinjas() {
		System.out.println("You killed a ninja.");
		pause();
	}
	
	/**
	 * This method is used to inform the user that he/she misses his/her shot,
	 * so he/she fails to kill a {@link Ninja}.
	 */
	private void failedToKillNinjas() {
		System.out.println("You hit nothing.");
		pause();
	}
	
	/**
	 * This method is used to provide different options for the user to choose from.
	 * If the user's input is not one of the options, we will ask the user to input again until
	 * the input is valid.
	 */
	public char chooseMovement() {
		String msg="Choose your movement: [m] Move  [l] Look  [s] Shoot \n[*] Switch mode [!] Save & Quit";
		char c = getChar(msg);
		while( c!='!' && c != 'm' && c != 'l' && c != 's') {
			if(this.switchMode(c));
			else {
				invalidInput();
			}
			c = getChar(msg);
		}
		return c;
	}
	
	/**
	 * This method is used to convert the user's input string into char and return that char.
	 */
	private char getChar(String msg) {
		System.out.println(msg);
		String temp=Input.nextLine();
		while(temp.length()!=1) {
			this.invalidInput();
			System.out.println(msg);
			temp=Input.nextLine();
		}
		return temp.charAt(0);
	}
	
	/**
	 * This method is used to provide different direction options for the user to choose from.
	 * If the user's input is not one of the options, we will ask the user to input again until
	 * the input is valid.
	 */
	public char chooseDirection() {
		String msg="Choose a direction: [w] Up  [a] Left  [s] Down  [d] Right \n[*] Switch mode";
		char c = getChar(msg);
		while(c != 'w' && c != 'a' && c != 's' && c != 'd') {
			if(this.switchMode(c));
			else {
				invalidInput();
			}
			c = getChar(msg);
		}
		return c;
	}	
	
	/**
	 * This method is used to inform the direction that the user chooses to move to is invalid.
	 */
	private void cantMove() {
		System.out.println("You can't move there. Try again!");
		pause();
	}
	
	/**
	 * This method is used to inform the direction that the user chooses to move to is invalid.
	 */
	private void printInfo() {
		System.out.println();
		System.out.println(ge.toString(isDebug));
		System.out.println("Lives : "+ge.getLives()+" | Bullet : "+ge.numOfBullet()+" | Invincible : "+ge.turnsOfInvinc()+"\n");
	}
	
	/**
	 * This method is used to inform the user that he/she is killed by an {@link Ninja}.
	 */
	private void playerBeingKilled() {
		System.out.println("You were KILLED by an ninja!");
		pause();
	}
	
	/**
	 * This method is used to provide a guideline to the user if he/she is confused with the options.
	 */
	private void help() {
		String helpString;
		try(BufferedReader br = new BufferedReader(new FileReader("help.txt"))) {
			while((helpString=br.readLine())!=null) {
				System.out.println(helpString);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pause();
	}
	
}
	
	
