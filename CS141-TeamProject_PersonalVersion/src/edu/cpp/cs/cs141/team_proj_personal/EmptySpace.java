package edu.cpp.cs.cs141.team_proj_personal;

public class EmptySpace extends SquareObject {

	private boolean isVisiable;
	
	public EmptySpace(int x,int y) {
		super(x,y);
		isVisiable=false;
	}

	public void enableVision()
	{
		isVisiable=true;
	}
	
	public void disableVision()
	{
		isVisiable=false;
	}
	
	@Override
	public String toString(boolean isDebug) {
		if(isDebug||isVisiable)
		return "[ ]";
		else
		return "[*]";
	

	}


}
