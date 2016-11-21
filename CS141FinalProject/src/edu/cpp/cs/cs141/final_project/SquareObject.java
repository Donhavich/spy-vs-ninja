/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public abstract class SquareObject {
	
	private int x;
	private int y;
	private boolean isVisible;
	
	public SquareObject(){}
	
	public SquareObject(int x,int y)
	{
		setLocation(x,y);
	}

  /**
  * Assigns the location of the objects on the grid.
  */
  public void setLocation(int x,int y)
  {
	  this.x=x;
	  this.y=y;
  }
  
  /**
  * Returns the individual x and y coordinates of the object.
  */
  public int getX()
  {
	  return x;
  }
  
  public int getY()
  {
	  return y;
  }
  
  public boolean isVisible()
  {
	  return isVisible;
  }
  
  public void visionControl(boolean isVisible)
  {
	  this.isVisible=isVisible;
  }

  
  public abstract String toString(boolean isDebug);
  
  
}
