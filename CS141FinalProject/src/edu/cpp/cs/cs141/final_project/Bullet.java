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
	
	public void useAmmo(Player p) {
		
	}

	@Override
	public String itemEffect() {
		return null;
	}
	

	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[b]";
		else 
			return "[*]";
		
	}
}
