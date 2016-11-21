/**
 * 
 */
package edu.cpp.cs.cs141.final_project;


/**
 * This class is called Grid, which represents the area in which the game take place 
 *
 * @author Donovan A. Gonzalez
 *
 */
public class Grid {
 /**
  * This constant field establishes the size of the {@link Floor} field when the constructor is called
  * 
  */
 private final int FLOOR_SIZE = 9;
 /**
  * This field is what the program will act on as it will contain multiple objects that are subclasses
  * of the {@link SquareObject} class
  */
  private SquareObject [][] Floor;
  /**
   * The purpose of this field is to hold an {@link Item} object when ever a {@link Ninja} object 
   * is moving to the same index as that of the {@link Item} object
   */
  private Item [] placeHolder = new Item [3];
  /**
   * This default constructor initializes the 2-Dimensional array to be used for the program
   */
  public Grid(){
	  Floor = new SquareObject [FLOOR_SIZE][FLOOR_SIZE];
  }
  /**
   * This method assigns all objects that are all subclasses of the superclass {@link SquareObject} to the array
   * based on the coordinates given to the entity object
   * @param entity
   * 	This parameter is the object that is passed to the method
   */
  public void setObject(SquareObject entity) {
	  	if(isEmpty(entity.getX(),entity.getY()))
	  		Floor[entity.getY()][entity.getX()] = entity;
	  	else
	  		System.out.println("Space occupied");
  }
  /**
   * This method returns a boolean value depending on whether the given index for the array contains 
   * a {@code null} or not. 
   * @param x
   * 	This is parameter is the column index for the {@link Floor} array 
   * @param y
   * 	This is parameter is the row index for the {@link Floor} array
   * @return
   * 	this method will return {@code true} if the given index contains a {@code null} and {@code false}
   * if there is something else
   */
  public boolean isEmpty(int x, int y) {
		if(Floor[y][x] == null)
			return true;
		else
			return false;
}
  /**
   *  This method is an overloaded method of {@link toString} that returns the current
   *  state of the game but it depends on the value of isDebug.
   * @param isDebug
   * 	This parameter determines how the state of the game will be written
   * @return
   * 	This method returns the state of the game  
   */
public String toString(boolean isDebug){
    String gridDisplay = "";
    for(int r = 0; r < Floor.length;r++){
      for(int c = 0; c < Floor[0].length; c++){
        if(Floor[r][c] == null){
          if(isDebug)
            gridDisplay += "[ ]";
          else
            gridDisplay += "[*]";
        }
        else
          gridDisplay += Floor[r][c].toString(isDebug);
      }
      gridDisplay += "\n";
    }
    return gridDisplay;
}

}
