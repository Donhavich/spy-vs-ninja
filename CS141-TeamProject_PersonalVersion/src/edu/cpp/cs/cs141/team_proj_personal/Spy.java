package edu.cpp.cs.cs141.team_proj_personal;

public class Spy extends SquareObject {

	private boolean bullet;
	
	private int lives;
	
	private char direction;
	
	private int invinc;
	
	public Spy() 
	{
		bullet=true;
		lives=3;
	}
	
	

	
	public boolean hasBullet()
	{
		return bullet;
	}

	public void getBullet()
	{
		bullet=true;
	}
	
	public void loseBullet()
	{
		bullet=false;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public void beKilled()
	{
		lives--;
	}

	
	@Override
	public String toString(boolean isDebug) {
		return "[S]";
	}
	
	public char getDirection()
	{
		return direction;
	}
	
	public void changeDirection(char direction)
	{
		this.direction=direction;
	}

	public boolean isInvinc()
	{
		if(invinc>0)
			return true;
		else
			return false;
	}
	
	public void beInvinc()
	{
		invinc=6;
	}
	
	public int getInvinc()
	{
		return invinc;
	}
	
	public void weakenInvinc()
	{
		invinc--;
	}


	public void enableVision() {
	}

	public void disableVision() {	
	}
}
