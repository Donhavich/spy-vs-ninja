/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author Donovan A. Gonzalez
 *
 */
public class Grid {
  private SquareObjects [][] Floor = new SquareObjects [9][9];
  
  public Grid(Spy player, Ninja [] ninjas, Room [] rooms, Item [] items){
	  setRooms(rooms);
	  setSpy(player);
	  setNinjas(ninjas);
	  setItems(items);
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
	setEntityCoord();
}

private void setSpy(Spy player){
	Floor[8][0] = player;
	setEntityCoord();
}

private void setNinjas(Ninja[] ninjas){
	Random RNG = new Random();
	for(int i = 0; i< ninjas.length; i++){
		int randomRow = RNG.nextInt(9);
		int randomColumn = RNG.nextInt(9);
		if (Floor[randomRow][randomColumn] == null && Math.sqrt((getSpy().getX()-randomColumn)^2+(getSpy().getY()-randomRow)^2) >= 3)
			Floor[randomRow][randomColumn] = ninjas[i];
	}
	setEntityCoord();			   
}

private void setPowerUps(Item [] items){
	Random RNG = new Random();
	for(int i = 0; i< items.length; i++){
		int randomRow = RNG.nextInt(9);
		int randomColumn = RNG.nextInt(9);
		if (Floor[randomRow][randomColumn] == null)
			Floor[randomRow][randomColumn] = items[i];
	}
	setEntityCoord();
}

public Spy getSpy(){
	for(int r = 0; r < grid.length;r++){
        for(int c = 0; c < grid[0].length; c++){
          if(Floor[r][c] instanceof Spy)
            return Floor[r][c];
        }
     }
  }
}

public Ninja getNinja(){
for(int r = 0; r < grid.length;r++){
        for(int c = 0; c < grid[0].length; c++){
          if(Floor[r][c] instanceof Ninja)
            return Floor[r][c];
        }
     }
  }
}

public Item getItem(){
for(int r = 0; r < grid.length;r++){
        for(int c = 0; c < grid[0].length; c++){
	     if(Floor[r][c] instanceof Item)
		    return Floor[r][c];
        }
     }
  }
}

public Room getRoom(){
for(int r = 0; r < grid.length;r++){
        for(int c = 0; c < grid[0].length; c++){
          if(Floor[r][c] instanceof Room)
            return Floor[r][c];
        }
     }
  }
}
