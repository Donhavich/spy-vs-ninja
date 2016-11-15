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
		for(int i=0;i<9;i++)
		{
			rooms[i]=new Room();
		}		
		rooms[randGen(0,8)].giveCase(); //randomly give the briefcase to one of the room
	}
	
	private void resetItems()
	{
		items=new Item[3];
		items[0]=new Bullet();
		items[1]=new Radar();
		items[2]=new Invinc();
	}
	
	private void resetNinjas()
	{
		ninjas=new Ninja[6];
		for(int i=0;i<6;i++)
		{
			ninjas[i]=new Ninja();
		}
	}
		
		
	
	public void createNewGame()
	{
		player=new Spy();
		resetRooms();
		resetItems();
		resetNinjas();
		grid=new Grid(player,ninjas,rooms,items);
	}
	
	public String toString(boolean isDebug)
	{
		return grid.toString(isDebug); //every final Class of the SquareObject should have a toString
										//that returns a printable string 
		
	}
	

}
