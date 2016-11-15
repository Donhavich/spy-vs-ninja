/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public class Room extends SquareObject {

	private boolean hasCase=false;
	
	public Room(){};
	
	public boolean hasCase()
	{
		return hasCase;
	}
	
	public void giveCase()
	{
		hasCase=true;
	}

	@Override
	public String toString(boolean isDebug) {
		if(hasCase&&isDebug)
			return "[C]";
		else
			return "[R]";
	}

	
	
}
