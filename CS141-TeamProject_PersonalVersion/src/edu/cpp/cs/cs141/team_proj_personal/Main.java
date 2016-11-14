package edu.cpp.cs.cs141.team_proj_personal;

public class Main {

	public static void main(String[] args) {
		
		GameEngine ge= new GameEngine();
		TextUI ui=new TextUI(ge);
		ui.startGame();

	}

}
