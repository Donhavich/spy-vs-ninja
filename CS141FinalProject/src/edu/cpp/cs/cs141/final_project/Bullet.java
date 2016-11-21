/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author 
 *
 */
public class Bullet extends Item {
	
	private int pointX;
	private int pointY;
	private int ammo;
	
	public Bullet(String itemName, int pointX, int pointY, int ammo) {
		super(itemName);
		this.pointX = pointX;
		this.pointY = pointY;
		this.ammo = ammo;
	}
	
	public void setLoc(int locX, int locY) {
		locX = pointX;
		locY = pointY;
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public void useAmmo(int increaseAmmo) {
		ammo += increaseAmmo;
	}
	
	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[b]";
		else 
			return "[*]";

	
	}

}
