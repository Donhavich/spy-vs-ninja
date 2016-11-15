package edu.cpp.cs.cs141.final_project;

public class Invinc extends Item {
		
	public Invinc(String itemName,int x, int y) {
		super(itemName,x,y);
	}
	
	
	public String toString(boolean isDebug)
	{
		if(isDebug)
			return "[i]";
		else 
			return "[*]";
	}

}
