package edu.cpp.cs.cs141.team_proj_personal;

public class Room extends SquareObject {

	private boolean hasCase;
	
	private boolean isCaseVisiable;
	
	public Room(int x, int y) {
		super(x, y);
		hasCase=false;
		isCaseVisiable=false;
	}

	
	
	public void placeCase()
	{
		hasCase=true;
	}
	
	public boolean hasCase()
	{
		return hasCase;
	}
	
	
	public void enableVisionOfCase()
	{
		if(hasCase)
		isCaseVisiable=true;
	}

	@Override
	public String toString(boolean isDebug) {
		if((isDebug&&hasCase)||isCaseVisiable)
		return "[C]";
		else
			return "[R]";
	}






}
