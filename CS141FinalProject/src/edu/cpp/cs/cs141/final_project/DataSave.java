package edu.cpp.cs.cs141.final_project;

import java.io.*;
/**
 * This is the DataSave class which implements Serializable and holds the classes of
 * attributes and behaviors in the game that is needed to be saved for the user to load
 * upon entering the game.
 * 
 * 
 * @author Lynn Nguyen
 *
 */
public class DataSave implements Serializable {
	
	/**
	 * This field represents the player as {@link Spy} object
	 */
	private Spy player;
	
	/**
	 * This field represents the array of enemy ninjas as {@link Ninja} that are
	 * randomly placed on the {@link Grid}
	 */
	private Ninja[] ninjas;
	
	/**
	 * This field represents the array of nine rooms as {@link Room} placed on
	 * the {@link Grid}.
	 */
	
	private Room[] rooms;
	
	/**
	 * This field represents the array of items {@link Bullet}, {@link Invinc},
	 * and {@link Radar} that are placed on the {@link Grid}.
	 */
	
	private Item[] items;
	
	/**
	 * The default constructor for the class {@link DataSave}. This assigns the 
	 * parameters to the objects used for {@link DataSave}.
	 * 
	 * @param player
	 * 				The behaviors of {@link #player} for {@link DataSave}
	 * @param ninjas
	 * 				The behaviors of {@link #ninjas} for {@link DataSave}
	 * @param rooms
	 * 				The behaviors of {@link #rooms} for {@link DataSave}
	 * @param items
	 * 				The behaviors of {@link #items} for {@link DataSave}
	 */
	public DataSave(Spy player,Ninja[] ninjas,Room[] rooms,Item[] items)
	{
		this.player=player;
		this.ninjas=ninjas;
		this.rooms=rooms;
		this.items=items;
	}
	
	/**
	 * This method returns {@link #player} that needs to be saved
	 * @return The behaviors of {@link Spy} such as the number of bullets
	 * left, the number of lives left, turns of invincibility used, and location
	 * on the {@link Grid}.
	 */
	public Spy getSpy()
	{
		return player;
	}
	
	/**
	 * This method returns {@link #ninjas} that needs to be saved
	 * @return The behaviors of {@link Ninja} such as location on
	 * the {@link Grid}
	 */
	public Ninja[] getNinjas()
	{
		return ninjas;
	}
	
	/**
	 * This method uses the {@link #rooms} that needs to be saved
	 * @return The behaviors of {@link Room} such as which room
	 * holds the briefcase of documents
	 */
	public Room[] getRooms()
	{
		return rooms;
	}
	
	/**
	 * This method uses the {@link #items} that needs to be saved
	 * @return The behaviors of the {@link Item}'s saved such as the
	 * locations of {@link Bullet}, {@link Invinc}, and {@link Radar} on
	 * the {@link Grid} if they haven't been picked up by the {@link Spy}
	 */
	public Item[] getItems()
	{
		return items;
	}
	
}
