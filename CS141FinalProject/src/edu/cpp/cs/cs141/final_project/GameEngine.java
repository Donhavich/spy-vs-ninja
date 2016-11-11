/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.util.Random;


/**
 * @author
 *
 */
public class GameEngine {
	
	private Spy player;
	
	private Grid grid;
	
	private Room[] rooms;
	
	private Ninja[] ninjas;
	
	private Item[] items;
	
	//random number generator
	private int randGen(int min,int max)
	{
		Random rand = new Random();
		return rand.nextInt(max-min+1)+min;
	}
	
	public GameEngine(){}
	
	private void resetRooms()
	{
		rooms=new Room[9];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				grid.setObject(rooms[3*i+j]=new Room(1+i*3,1+j*3)); //set rooms on the grid
				//the Grid class should have a method that pass a SquareObject as param and put it in the array of grid
				
			}
		}		
		rooms[randGen(0,8)].giveCase(); //randomly give the briefcase to one of the room
		//the Room class should have a method that mark the room as a room that has the briefcase
	}
	
	private void resetItems()
	{
		items=new Item[3];
		items[0]=new Bullet();
		items[1]=new Radar();
		items[2]=new PowerUp();
		for(int i=0;i<3;i++)
		{
			int x,y;
			do{
				x=randGen(0,8);
				y=randGen(0,8);
			   
			}while(!grid.isEmpty(x,y)); //the Grid class should have a method that check whether spot (x,y) is empty

			items[i].setLoction(x,y); // the Item class should have a method to set its Location to (x,y)
			grid.setObject(item[i]);	
		}
	}
	
	private void resetNinjas()
	{
		ninjas=new Ninja[6];
		for(int i=0;i<6;i++)
		{
			int x,y;
			do{
			  do{
				  x=randGen(0,8);
				  y=randGen(0,8);
			  }while(x+y<4);
			  y=8-y;
			}while(!grid.isEmpty(x,y));
			ninjas[i].setLocation(x,y);// the Ninja class should have a method to set its Location to (x,y)
			grid.setObejct(ninjas[i]);
		}
	}
		
		
	
	public void createNewGame()
	{
		player=new Spy(0,8);//create a new player and set its location at the bottom left
		//the constructor of the spy should be able to set his location to (x,y) 
		grid.setObject(player);
		resetRooms();
		resetItems();
		resetNinjas();
	}
	
	public String toString(boolean isDebug)
	{
		return grid.ToString(isDebug); //every final Class of the SquareObject should have a toString
										//that returns a printable string 
	}
	

}
