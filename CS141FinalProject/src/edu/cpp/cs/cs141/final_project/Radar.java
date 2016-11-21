/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public class Radar extends Item {
	
	private int pointX;
	private int pointY;
	
	public Radar(String itemName, int pointX, int pointY) {
		super(itemName);
		this.pointX = pointX;
		this.pointY = pointY;
	}
	
	public void setLoc(int locX, int locY) {
		locX = pointX;
		locY = pointY;
	}
	
	public void useRadar() {
		
	}

	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[r]";
		else 
			return "[*]";

	
	}
}
