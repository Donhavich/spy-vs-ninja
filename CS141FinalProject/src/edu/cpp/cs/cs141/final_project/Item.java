/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
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
	
	
	public boolean isVisiable()
	{
		return isVisiable;
	}

	
}
