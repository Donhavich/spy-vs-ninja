/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.io.*;
/**
 * @author
 *
 */
public class DataSave implements Serializable {
	
	private Spy player;
	
	private Ninja[] ninjas;
	
	private Room[] rooms;
	
	private Item[] items;
	
	public DataSave(Spy player,Ninja[] ninjas,Room[] rooms,Item[] items)
	{
		this.player=player;
		this.ninjas=ninjas;
		this.rooms=rooms;
		this.items=items;
	}
	
	public Spy getSpy()
	{
		return player;
	}
	
	public Ninja[] getNinjas()
	{
		return ninjas;
	}
	
	public Room[] getRooms()
	{
		return rooms;
	}
	
	public Item[] getItems()
	{
		return items;
	}
	
}
