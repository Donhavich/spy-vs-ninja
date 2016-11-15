/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public abstract class Item extends SquareObject {
	
	private String itemName;
	private boolean isUsed;
	private boolean isVisible;
	
	public Item (String itemName) {
		this.itemName = itemName;
		isUsed = false;
		isVisible = false;
	}
	
	public String getItemName() {
		return itemName;
	}
	public boolean isUsed()
	{
		return isUsed;
	}
	
	public void beingUsed()
	{
		isUsed = true;
	}
	
	
	public boolean isVisible()
	{
		return isVisible;
	}
	

	
}
