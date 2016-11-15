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
	


	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[ ]";
		else
			return "[*]";
	}

	
	
}
