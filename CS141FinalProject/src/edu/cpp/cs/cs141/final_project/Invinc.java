package edu.cpp.cs.cs141.final_project;

public class Invinc extends Item {
	
	private int pointX;
	private int pointY;
	private int hp;
	
	
	public Invinc(String itemName, int pointX, int pointY, int hp) {
		super(itemName);	
		this.pointX = pointX;
		this.pointY = pointY;
		this.hp = hp;
	}
	
	public void setLoc(int locX, int locY) {
		locX = pointX;
		locY = pointY;
	}
	
	public int getHp() {
		return hp;
	}
	public void usePotion(int increaseHp ) {
		hp += increaseHp; 
	}
	
	public String toString(boolean isDebug)
	{
		if(isDebug)
			return "[i]";
		else 
			return "[*]";
	}

}
