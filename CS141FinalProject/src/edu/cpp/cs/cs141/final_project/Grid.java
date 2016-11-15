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

  
  public Grid(Spy player, Ninja [] ninjas, Room [] rooms, Item [] items){
	  clear();
	  setRooms(rooms);
	  setSpy(player);
	  setItems(items);
	  setEntityCoord();
	  setNinjas(ninjas);
	  setEntityCoord();
  }
  
  public String toString(boolean isDebug){
      String gridDisplay="";
      for(int r = 0; r < Floor.length;r++){
        for(int c = 0; c < Floor[0].length; c++){
            gridDisplay += Floor[r][c].toString(isDebug);
        }
        gridDisplay += "\n";
      }
      return gridDisplay;
  }
  
 private void setEntityCoord()
 {
     for(int r = 0; r < Floor.length;r++)
     {
        for(int c = 0; c < Floor[0].length; c++)
        {
          Floor[r][c].setLocation(c,r);
        }
     }
  }

private void setRooms(Room [] rooms)
{
		int i = 0;
		for(int r = 1; r < Floor.length; r+=3)
		{
			for(int c = 1; c< Floor[0].length; c+=3)
			{
				Floor[r][c] = rooms[i];
				i++;
			}
		}
	}


private void setSpy(Spy player){
	Floor[8][0] = player;
	setEntityCoord();
}

private void setNinjas(Ninja[] ninjas){
	Random RNG = new Random();
	for(int i = 0; i< ninjas.length; i++){
		boolean isSet=false;
		while(!isSet)
		{
			int randomRow = RNG.nextInt(9);
			int randomColumn = RNG.nextInt(9);
			if (!(Floor[8-randomRow][randomColumn] instanceof Room)
				&&!(Floor[8-randomRow][randomColumn] instanceof Ninja)&& randomRow+randomColumn>4)
			{
				Floor[8-randomRow][randomColumn] = ninjas[i];
				isSet=true;
			}
		}
	}			   
}

private void setItems(Item [] items)
{
	Random RNG = new Random();
	for(int i = 0; i< items.length; i++)
	{
		int randomRow,randomColumn;
		do{
		randomRow = RNG.nextInt(9);
		randomColumn = RNG.nextInt(9);
		}
		while(!(Floor[randomRow][randomColumn] instanceof EmptySpace));
			Floor[randomRow][randomColumn] = items[i];
	}
}


private void clear()
{
	for(int i=0;i<Floor.length;i++)
	{
		for(int j=0;j<Floor[0].length;j++)
		{
			Floor[i][j]=new EmptySpace();
		}
	}
}


}
		

