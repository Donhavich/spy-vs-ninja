/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public class Ninja extends SquareObject {

	
	boolean isDead=false;
	public Ninja(){}
	
	public void beAttacked()
	{
		isDead=true;
	}
	
	public boolean isDead(){
		return isDead;
	}

	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[N]";
		else
			return "[*]";
	}
	
}
