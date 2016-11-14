package edu.cpp.cs.cs141.team_proj_personal;

public abstract class Item extends SquareObject {
	
	private boolean isVisiable;
	private boolean isUsed;
	
	public Item() {
		isVisiable=false;
		isUsed=false;
	}
	
	public boolean isUsed()
	{
		return isUsed;
	}
	
	public void beingUsed()
	{
		isUsed=true;
	}
	

	@Override
	public void enableVision() {
		isVisiable=true;

	}

	@Override
	public void disableVision() {
		isVisiable=false;
	}
	
	public boolean isVisiable()
	{
		return isVisiable;
	}

}
