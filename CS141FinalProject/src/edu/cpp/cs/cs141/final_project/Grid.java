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
      for(int r = 0; r < grid.length;r++)
      {
        for(int c = 0; c < grid[0].length; c++)
        {
          if(grid[r][c] == null)
          {
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
  
  public void setPlayerCoord(){
     for(int r = 0; r < grid.length;r++)
      {
        for(int c = 0; c < grid[0].length; c++)
        {
          if(Floor[r][c] instanceof Spy){
            Floor[r][c].setX(c);
            Floor[r][c].setY(r);
          }
        }
      }
  }
    public void setNinjaCoord(){
     for(int r = 0; r < grid.length;r++)
      {
        for(int c = 0; c < grid[0].length; c++)
        {
          if(Floor[r][c] instanceof Ninja){
            Floor[r][c].setX(c);
            Floor[r][c].setY(r);
          }
        }
      }
  }
    public void setRoomCoord(){
     for(int r = 0; r < grid.length;r++)
      {
        for(int c = 0; c < grid[0].length; c++)
        {
          if(Floor[r][c] instanceof Room){
            Floor[r][c].setX(c);
            Floor[r][c].setY(r);
          }
        }
      }
  }
    public void setItemCoord(){
     for(int r = 0; r < grid.length;r++)
      {
        for(int c = 0; c < grid[0].length; c++)
        {
          if(Floor[r][c] instanceof Item){
            Floor[r][c].setX(c);
            Floor[r][c].setY(r);
          }
        }
      }
  }
}
