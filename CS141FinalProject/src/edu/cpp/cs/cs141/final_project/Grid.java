/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.util.Random;

/**
 * @author Donovan A. Gonzalez
 *
 */
public class Grid {
  private SquareObject [][] Floor = new SquareObject [9][9];
  private Item placeHolder;
  
  public Grid(){
	
  }

  public void setObject(SquareObject entity) {
	  	if(isEmpty(entity.getX(),entity.getY()))
	  		Floor[entity.getY()][entity.getX()] = entity;
	  	else
	  		System.out.println("Space occupied");
}
  public boolean isEmpty(int x, int y) {
		if(Floor[y][x] == null)
			return true;
		else
			return false;
}

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
