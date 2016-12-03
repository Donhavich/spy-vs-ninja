/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez*
 *
 * Description of assignment:
 * 		Create and design a turned based Spy vs Ninja game by using 
 * 		Object oriented techniques and follows the specifications 
 * 		based on the rubric. 
 *
 * Team: Spirit Coders 
 * 		Wing Hung Lau
 * 		Michael Tang
 * 		Donovan Gonzalez
 * 		Lynn Nguyen
 * 		Xinyuan Wang
 * 		Connor Chase
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
   * This default constructor initializes the {@link #Floor} array to be used for the program
   */
  public Grid(){
	  Floor = new SquareObject [FLOOR_SIZE][FLOOR_SIZE];
	  this.clear();
  }
  
  public Grid(Spy player,Ninja[] ninjas,Room[] rooms){
	  this();
	  this.setObject(player);
	  for(Ninja thisN:ninjas)
	  {
		  if(!(thisN.isDead()))
		  this.setObject(thisN);
	  }
	  for(Room thisR:rooms)
		  this.setObject(thisR);
  }
  
  
  
  
  /**
   * This method assigns all objects that are all subclasses of the superclass {@link SquareObject} to the array
   * based on the coordinates given to the entity object
   * @param entity
   * 	This parameter is the object that is passed to the method
   */
  public void setObject(SquareObject entity) {
	  		Floor[entity.getY()][entity.getX()] = entity;

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
		if(Floor[y][x] instanceof EmptySpace)
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
          gridDisplay += Floor[r][c].toString(isDebug);
      }
      gridDisplay += "\n";
    }
    return gridDisplay;
}
/**
 * This method returns an instance of the {@link SquareObject} within the 
 * given index from the parameters
 * @param x
 * 		The x-coordinate of the {@link #Floor} array 
 * @param y
 * 		The y-coordinate of the {@link #Floor} array
 * @return
 * 		An instance of the {@link SquareObject} is returned
 */		

public SquareObject getObject(int x, int y) {
	return Floor[y][x];
}
/**
 * This method resets the {@link #Floor} array be assigning all indexes an
 * instance of the {@link EmptySpace} class
 */
public void clear() {
	for(int r = 0; r < FLOOR_SIZE; r++){
		for(int c = 0; c < FLOOR_SIZE; c++)
			Floor[r][c] = new EmptySpace(c,r);
	}
	
}
/**
 * This method returns the size of the {@link #Floor} array's size
 * @return
 * 		An int value of the {@link #Floor} array's size from the field
 * 		{@code #FLOOR_SIZE}
 */
public int size() {
	return FLOOR_SIZE;
}
/**
 * This method moves the passed {@link Ninja} object to a new location
 * given by the parameters x and y
 * @param thisN
 * 		The {@link Ninja} object that is moving to a new location
 * @param x
 * 		The new x-coordinate for the {@link Ninja} object
 * @param y
 * 		The new y-coordinate for the {@link Ninja} object
 */
public void moveObject(Ninja thisN, int x, int y) {
	Floor[thisN.getY()][thisN.getX()] = new EmptySpace(thisN.getX(),thisN.getY());
	thisN.setLocation(x, y);
	Floor[thisN.getY()][thisN.getX()] = thisN;
	
}
/**
 This method moves the passed {@link Spy} object to a new location
 * given by the parameters x and y
 * @param thisN
 * 		The {@link Spy} object that is moving to a new location
 * @param x
 * 		The new x-coordinate for the {@link Spy} object
 * @param y
 * 		The new y-coordinate for the {@link Spy} object
 */
public void moveObject(Spy player, int x, int y) {
	Floor[player.getY()][player.getX()] = new EmptySpace(player.getX(),player.getY());
	player.setLocation(x, y);
	Floor[player.getY()][player.getX()] = player;
	
}

}
