package edu.cpp.cs.cs141.team_proj_personal;

import java.util.Scanner;

public class TextUI {

	private boolean isDebug=false;
	
	private GameEngine ge;
	
	private Scanner scan=new Scanner(System.in);
	
	public TextUI(GameEngine ge)
	{
		this.ge=ge;
	}
	
	private String getInput(String message,String...validInput)
	{
		System.out.println(message);
		String input;
		boolean isValid=false;
		input=scan.nextLine();
		
		while(input.equals("gamemode")||input.equals("debugmode"))
		{
			isDebug= input.equals("gamemode")? false:true;
			printInfo();
			System.out.println(message);
			input=scan.nextLine();
		}
		
		while(!isValid)
		{
			for(String s:validInput)
			{
				if(input.equals(s))
				{
					isValid=true;
					break;
				}
				
			}
			if(!isValid)
			{
				System.out.println("Invalid input. Try again!");
				input= getInput(message,validInput);
			}
		}
		return input;
	}
	
	private void printInfo()
	{
		System.out.print(ge.toString(isDebug));
		System.out.println("[Lives:"+ge.lives()+"] [Bullet:"+ge.numOfBullet()+"] [Invincible turns:"+ge.invinc()+"]");
	}
	
	public void startGame()
	{
		welcomeMessage();
		ge.NewGame(6);
		boolean isKilled=false,
				isWinning=false,
				isOver=false;
		
		while(!isKilled&&!isWinning)
		{
			String input;
			String outputMessage;
			printInfo();
			outputMessage="Choose what you want to do in this turn?\n[m]move [l]look [s]shoot";
			input=getInput(outputMessage,"m","l","s");
			
			if(input.equals("m"))
			{
				boolean noMove=false;
				do
				{
					outputMessage="Choose the direction you want to move?\n[w]up [s]down [a]left [d]right";
					input=getInput(outputMessage,"w","a","s","d");
					String respond=ge.playerTurn(input.charAt(0));
					switch(respond)
					{
					case "noMove":
						noMove=true;
						break;
					case "noCase":
						System.out.println("Sorry, there is no case in this room");
						break;
					case "bullet:":
						System.out.println("You got a chance to reload your gun.");
						break;
					case "radar":
						System.out.println("You got a radar. The location of the briefcase has been deteched on your map!");
						break;
					case "invincible":
						System.out.println("You will be invincible in 5 turns.");
						break;
					case "getCase":
						System.out.println("You got the briefcase. You win!");
						isWinning=true;
						break;
					}
				}while(noMove);
				
				if(!isWinning)
				{
					isKilled=ge.ninjaTurn();
				}
			}//end of move loop
		}
				

	}
	
	public void welcomeMessage()
	{
		System.out.println("Welcome to the game");
	}
	
}	
