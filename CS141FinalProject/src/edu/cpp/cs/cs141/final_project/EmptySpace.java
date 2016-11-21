/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public class EmptySpace extends SquareObject {

	
	public EmptySpace(){};
	
	public EmptySpace(int x,int y)
	
	{
		super(x,y);
	}
	


	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[ ]";
		else
			return "[*]";
	}

	
	
}
