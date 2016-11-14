package edu.cpp.cs.cs141.team_proj_personal;

public class Ninja extends SquareObject {
	
	private boolean isVisiable;
	
	private boolean isDead;

	public Ninja(int x,int y)
	{
		super(x,y);
		isVisiable=false;
		isDead=false;
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	public void beKilled()
	{
		isDead=true;
	}

	
	public String toString(boolean isDebug)
	{
		if(isDebug||isVisiable)
			return "[N]";
		else
			return "[*]";
	}
	
	@Override
	public void enableVision()
	{
		isVisiable=true;
	}
	
	@Override
	public void disableVision()
	{
		isVisiable=false;
	}



	

}
