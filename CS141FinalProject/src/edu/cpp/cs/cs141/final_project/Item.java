/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public abstract class Item extends SquareObject {
	
	private boolean isVisible;
	private boolean isUsed;
	
	public Item() {
		isVisible=false;
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
	
	
	public boolean isVisible()
	{
		return isVisible;
	}

	
}
