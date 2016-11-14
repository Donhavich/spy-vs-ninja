/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public class Grid {
  private SquareObjects [][] Floor = new SquareObjects [9][9];
  
  public Grid(Spy player, Ninja [] ninjas, Room [] rooms, Item [] items){
    
  }
  public String ToString(boolean isDebug){
      String gridDisplay;
      for(int r = 0; r < grid.length;r++){
        for(int c = 0; c < grid[0].length; c++){
          if(grid[r][c] == null){
            if(isDebug)
              gridDisplay += "[ ]";
            else
              gridDisplay += "[*]";
          }
          else
            gridDisplay += grid[r][c].ToString(isDebug);
        }
        gridDisplay += "\n";
      }
  }
  
  private void setEntityCoord(){
     for(int r = 0; r < grid.length;r++){
        for(int c = 0; c < grid[0].length; c++){
          if(Floor[r][c] instanceof SquareObject){
            Floor[r][c].setX(c);
            Floor[r][c].setY(r);
          }
        }
     }
  }
}
 private void setRooms(Room [] rooms){
		int i = 0;
		for(int r = 1: r < Floor.length; r+=3){
			for(int c = 1; Floor[0].length; c+=3){
				Floor[r][c] = rooms[i];
				i++;
			}
		}
	}
}

private void setSpy(Spy player){
	Floor[8][0] = player;
}

private void setNinjas(Ninja[] ninjas){
	int i = 0;
	if(Math.sqrt((r-)^2+(c-)^2 > 3)
			   
}
	
