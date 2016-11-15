/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author 
 *
 */
public class Bullet extends Item {
	public Bullet(String itemName,int x, int y) {
		super(itemName,x,y);
	}

	

	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[b]";
		else 
			return "[*]";
		
	}
}
