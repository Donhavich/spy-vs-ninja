package edu.cpp.cs.cs141.team_proj_personal;

public abstract class SquareObject {
	
	private int x;
	private int y;
	
	public abstract String toString(boolean isDebug);
	
	public SquareObject()
	{
		
	}
	
	public SquareObject(int x,int y)
	{
		setLocation(x,y);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public  void enableVision() {}
	public  void disableVision(){}
	
	public void setLocation(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
}
