/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		isDebug = false;
	}
	
	public void mainLoop()
	{
		welcomeMessage();
		char choice;
		do
		{
			choice=mainMenu();
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
	

	private char mainMenu()
	{
		System.out.println("1.New Game");
		System.out.println("2.Load Game");
		System.out.println("3.Help");
		System.out.println("4.exit");
		char choice= getChar();
		while(choice!='1'&&choice!='2'&&choice!='3'&&choice!='4')
		{
			invalidInput();
			choice = getChar();
		}
		return choice;
	}
	
	private void StartNewGame() 
	{
		
		ge.createNewGame();
		gameLoop();
		
	}
	
	
	
	private void loadGame()
	{
		System.out.println("Please enter the name of the file that you have saved the game:");
		String fileName=Input.nextLine();
		boolean loaded=false;
		while(!loaded)
		{
			try 
			{
				ge.loadGame(fileName);
				loaded=true;
			} 
			catch (java.io.FileNotFoundException e) 
			{
				System.out.println("File was not found! Please try again!");
				fileName=Input.nextLine();
			} 
			catch (ClassNotFoundException|IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		gameLoop();
	}
	
	private void gameLoop()
	{
		boolean winning = false;
		
		mainloop:
		while(!ge.isGameOver() && !winning) 
		{
			boolean isDead = false;
			while(!isDead && !winning) {
				
				this.printInfo();
				char move = chooseMovement();
				
				if(move == 'm') 
				{
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
					
					if(!winning) 
					{
						isDead = ge.ninjaTurn();
						if(isDead)
						{
							this.printInfo();
							ge.resetPlayer();
							this.playerBeingKilled();
						}
					}
				}
				
				else if(move == 'l') 
				{
					if(ge.playerCanLook())
					{
						char direction = chooseDirection();
						if(ge.look(direction)) 
							ninjasAppear();
						else
							ninjasNotAppear();
					}
					else
					{
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
				
				else if(move == '!')
				{
					System.out.println("Please enter the name of the file you want to save in:");
					String filename =Input.nextLine();
					try 
					{
						ge.saveGame(filename);
					} 
					catch (IOException e) 
					{
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


	
	public void welcomeMessage() {
		System.out.println("---------------------------------------------Death Task---------------------------------------------------");
		System.out.println("As a top spy in the world, you are sent to carry out this dangerous but yet glorious task by your country!");
	}
	
	public boolean switchMode(char c) {
		if(c=='*')
		{
			if(isDebug)
				isDebug=false;
			else
				isDebug=true;
			this.printInfo();
			return true;
		}
		else
			return false;
	}
	
	
	private void pause()
	{
		System.out.println("[Press Enter to continue]");
		Input.nextLine();
	}
	
	private void winGame() {
		System.out.println("You got the briefcase! You won the game!");
		pause();
	}

	private void loseGame() {
		System.out.println("The game is over! You lost!");
		pause();
	}
	
	private void getRadar() {
		System.out.println("You got a radar and the location of the briefcase has been deteched.");
		pause();
	}
	
	private void getInvinc() {
		System.out.println("You will be invincible for 5 turns.");
		pause();
	}
	
	private void getBullet() {
		System.out.println("You found a bullet. Your gun is reloaded.");
		pause();
	}
	
	private void emptyRooms() {
		System.out.println("Sorry, this room is empty. Try another one.");
		pause();
	}
	
	private void invalidInput() {
		System.out.println("Invalid input. Please input again.");
		pause();
	}
	
	private void ninjasAppear() {
		System.out.println("DANGER AHEAD!");
		pause();
	}
	
	private void ninjasNotAppear() {
		System.out.println("Safe ahead.");
		pause();
	}
	
	private void zeroBullet() {
		System.out.println("You run out of bullet.");
		pause();
	}
	
	private void killedNinjas() {
		System.out.println("You killed a ninja.");
		pause();
	}
	
	private void failedToKillNinjas() {
		System.out.println("You hit nothing.");
		pause();
	}
	
	public char chooseMovement() {
		System.out.println("Choose your movement: [m] Move  [l] Look  [s] Shoot \n[*] Switch mode [!] Save & Quit");
		char c = getChar();
		while( c!='!' && c != 'm' && c != 'l' && c != 's') {
			if(this.switchMode(c))
				System.out.println("Choose your movement: [m] Move  [l] Look  [s] Shoot \n[*] Switch mode [!] Save & Quit");
			else
			{
				invalidInput();
			}
			c = getChar();
		}
		return c;
	}
	
	private char getChar()
	{
		String temp=Input.nextLine();
		while(temp.length()!=1)
		{
			this.invalidInput();
			temp=Input.nextLine();
		}
		return temp.charAt(0);
	}
	
	public char chooseDirection() {
		System.out.println("Choose a direction: [w] Up  [a] Left  [s] Down  [d] Right \n[*] Switch mode");
		char c = getChar();
		while(c != 'w' && c != 'a' && c != 's' && c != 'd') {
			if(this.switchMode(c))
				System.out.println("Choose a direction: [w] Up  [a] Left  [s] Down  [d] Right \n[*] Switch mode");
			else
			{
				invalidInput();
			}
			c =getChar();
		}
		return c;
	}	
	
	private void cantMove()
	{
		System.out.println("You can't move there. Try again!");
		pause();
	}
	
	private void printInfo()
	{
		System.out.println();
		System.out.println(ge.toString(isDebug));
		System.out.println("lives:"+ge.getLives()+"|bullet:"+ge.numOfBullet()+"|invincible:"+ge.turnsOfInvinc()+"\n");
		
	}
	private void playerBeingKilled()
	{
		System.out.println("You were KILLED by an ninja!");
		pause();
	}
	
	private void help()
	{
			String helpString;
			
			try(BufferedReader br=new BufferedReader(new FileReader("help.txt")))
			{
				while((helpString=br.readLine())!=null)
				{
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
	
	