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

public void moveNinjas(){
	Random RNG = new Random();
	for(int r = 0; r < Floor.length; r++){
		for(int c = 0; c < Floor[0].length; c++){
			if(Floor[r][c] instanceof Ninja){
				switch(RNG.nextInt(4) + 1)
				{
					case 1:
						if(r+1 < Floor.length && Floor[r+1][c] == null){
							Floor[r+1][c] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
					case 2:
						if(c+1 < Floor[0].length && Floor[r][c+1] == null){
							Floor[r][c+1] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
					case 3:
						if(r-1 < Floor.length && Floor[r-1][c] == null){
							Floor[r-1][c] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
					case 4:
						if(c-1 < Floor[0].length && Floor[r][c-1] == null){
							Floor[r][c-1] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
				}
			}
		}
	}
	
}

public void moveSpy(int move){
for(int r = 0; r < Floor.length; r++){
		for(int c = 0; c < Floor[0].length; c++){
			if(Floor[r][c] instanceof Spy){
				switch(move)
				{
					case 1:
						if(r+1 < Floor.length && Floor[r+1][c] == null){
							Floor[r+1][c] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
					case 2:
						if(c+1 < Floor[0].length && Floor[r][c+1] == null){
							Floor[r][c+1] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
					case 3:
						if(r-1 < Floor.length && Floor[r-1][c] == null){
							Floor[r-1][c] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
					case 4:
						if(c-1 < Floor[0].length && Floor[r][c-1] == null){
							Floor[r][c-1] = Floor[r][c];
							Floor[r][c] = null;	
						}
						break;
				}
			}
		}
	}	
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
