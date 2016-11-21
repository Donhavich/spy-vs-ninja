/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */

public abstract class Item extends SquareObject {

	private boolean isUsed;
	
	private char symbol;

	
	public Item (char symbol) {
		isUsed = false;
		this.symbol=symbol;
	}
	
	
	public boolean isUsed()
	{
		return isUsed;
	}
	
	public void beingUsed()
	{
		isUsed = true;
	}
	
	
	
	@Override
	public String toString(boolean isDebug) {
		if(isDebug || this.isVisible())
			return "["+symbol+"]";
		else 
			return "[*]";

	
	}
}
