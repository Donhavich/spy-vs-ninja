package edu.cpp.cs.cs141.team_proj_personal;

import java.util.Scanner;

public class TEST {

	public static void main(String[] args) {
		GameEngine ge=new GameEngine();
		ge.NewGame(6);
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println(ge.toString(true));
		System.out.println();
		//System.out.println(ge.toString(false));
		
		while(true)
		{
			System.out.println(ge.playerTurn(scan.next().charAt(0)));
			boolean die=ge.ninjaTurn();
			System.out.println(ge.invinc());
				System.out.println(ge.toString(true));
			//boolean look=ge.look(scan.next().charAt(0));
			//System.out.println(look?"yes":"no");
			//System.out.println(ge.toString(true));
			//System.out.println(ge.shoot(scan.next().charAt(0)));
			
			if(die)
			{
				System.out.println("die");
				System.out.println(ge.toString(true))
				;
			}
			else
				System.out.println(ge.toString(false));
				
		}
	}
}
