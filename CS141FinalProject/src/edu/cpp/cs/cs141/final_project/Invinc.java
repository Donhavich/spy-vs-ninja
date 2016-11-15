package edu.cpp.cs.cs141.final_project;

public class Invinc extends Item {
		
	public Invinc(String itemName) {
		super(itemName);
	}
	
	
	public String toString(boolean isDebug)
	{
		if(isDebug)
			return "[i]";
		else 
			return "[*]";
	}

}
