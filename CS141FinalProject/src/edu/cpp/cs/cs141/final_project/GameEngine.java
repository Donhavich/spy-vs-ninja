/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.io.*;
import java.util.Random;

/**
 * @author Xinyuan Wang
 *
 */
public class GameEngine {
	
	/**
	 * This field represents the player in the game as a {@link Spy} object
	 */
	private Spy player;
	
	/**
	 * This field is the main grid in the game 
	 */
	private Grid grid;
	
	/**
	 * This field is a list of {@link Room} that contains all the {@link Room}s in the grid
	 */
	private Room[] rooms;
	
	/**
	 * This field is a list of {@link Ninja} that contains all the {@link Ninja}s in the grid
	 */
	private Ninja[] ninjas;
	
	/**
	 * This field is a list of {@link Item} that contains all the {@link Item}s in the grid
	 */
	private Item[] items;
	

	
	/**
	 * This is a random integer generator generates a random integer between given lower bound and upper bound (inclusive)
	 * @param min
	 * 			the lower bound of the random integer
	 * @param max
	 * 			the upper bound of the random integer
	 * @return
	 * 			A random integer between bounds
	 */
	private int randGen(int min,int max)
	{
		Random rand = new Random();
		return rand.nextInt(max-min+1)+min;
	}
	
	/**
	 * default constructor: get an empty grid ready
	 */
	public GameEngine()
	{
		grid=new Grid();
	}
	
	/**
	 * This method is to set up a new game with a new player
	 */
	public void createNewGame()
	{
		player=new Spy();
		resetGrid();
	}
	
	/**
	 * This method is to set up all the {@link Room}s in the {@link #rooms}, randomly give
	 * one of them the brief case and put them on the {@link #grid}
	 */
	private void resetRooms()
	{
		rooms=new Room[9];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				grid.setObject(rooms[3*i+j]=new Room(1+i*3,1+j*3)); 			
			}
		}		
		rooms[randGen(0,8)].giveCase(); 
	}
	
	/**
	 * This method is to set up all the {@link Item}s in the {@link #items} by giving them random
	 * location, and then put them on the {@link #grid}
	 */
	private void resetItems()
	{
	
		items=new Item[3];
		items[0]=new Bullet();
		items[1]=new Radar();
		items[2]=new Invinc();
		for(int i=0;i<3;i++)
		{
			int x,y;
			do{
				x=randGen(0,8);
				y=randGen(0,8);
			   
			}while(!(grid.getObject(x,y) instanceof EmptySpace)); //add a method in Grid to get object
			items[i].setLocation(x,y); 
			grid.setObject(items[i]);	
		}
	}
	
	/**
	 * This method is to set up all the {@link Ninja}s in the {@link #ninjas} by giving them random
	 * location that a least 4 steps from the player's initial location, and then put them on the 
	 * {@link #grid}
	 */
	private void resetNinjas()
	{
		ninjas=new Ninja[6];
		for(int i=0;i<ninjas.length;i++)
		{
			int x,y;
			do{
			  do{
				  x=randGen(0,8);
				  y=randGen(0,8);
			  }while(x+y<4);
			  y=8-y;
			}while(grid.getObject(x,y) instanceof Room);//add a method in Grid to get object
			ninjas[i]=new Ninja(x,y);
			grid.setObject(ninjas[i]);
		}
	}
		

	
	/**
	 * This method reset the whole {@link #grid} including {@link #rooms}, {@link #ninjas} and {@link #items} but
	 * only the location of the {@link #player};
	 */
	private void resetGrid()
	{
		grid.clear();//add a method in the Grid class that fill out the whole grid with EmptySpace
		resetRooms();
		player.setLocation(0, 8);
		player.changeDirection('w');
		resetItems();
		resetNinjas();
	}
	
	/**
	 * This method is to return a String that can illustrate the current state of the {@link #grid} in either
	 * debug mode or not.
	 * @param isDebug
	 * 		{@code true} if is under debug mode,
	 * 		{@code false} if is under player mode.
	 * @return
	 * 		The String showing the current condition of the {@link #grid} under certain mode.
	 */
	public String toString(boolean isDebug)
	{
		visionControl(true);
		String gridString=grid.toString(isDebug);
		visionControl(false);
		return gridString;
	}
	
	/**
	 * This method is to get the adjacent object of any object on the {@link #grid} in a given
	 * direction 
	 * @param thisObj
	 * 		the center object
	 * @param direction
	 * 			'w'-up
	 * 			's'-down
	 * 			'a'-left
	 * 			'd'-right
	 * @return the object that adjacent to {@code thisObj} and at its {@code direction}
	 * 
	 */
	private SquareObject getObjAhead(SquareObject thisObj,char direction)
	{
		int thisX=thisObj.getX(),
		    thisY=thisObj.getY();
		int aheadX=-1,
			aheadY=-1;
		switch(direction)
			{
				case 'w':
					aheadX=thisX;
					aheadY=thisY-1;
					break;
				case 's':
					aheadX=thisX;
					aheadY=thisY+1;
					break;
				case 'a':
					aheadX=thisX-1;
					aheadY=thisY;
					break;
				case 'd':
					aheadX=thisX+1;
					aheadY=thisY;
					break;
			}
		if(aheadX>-1 && aheadX<grid.size() && aheadY>-1 && aheadY<grid.size())//add a method in Grid to return the size of the grid
			return grid.getObject(aheadX, aheadY);//add a method in Grid to get object
		else
			return null;
	}
	
	/**
	 * This method control the vision of the {@link #player} under the play mode.
	 * @param control
	 * if {@code true}, the two object ahead of the {@link #player}'s current location will be marked as visible
	 * if {@code false}, the two object ahead of the {@link #player}'s current location will be marked back to invisible
	 * 
	 */
	private void visionControl(boolean control)
	{
		SquareObject obj1 = getObjAhead(player, player.getDirection()),
					 obj2 = obj1==null?null:getObjAhead(obj1,player.getDirection());
		if(control==true)
		{
			if(obj1!=null)
				obj1.visionControl(true); 
			if(obj2!=null)
				obj2.visionControl(true);
		}
		else
		{
			if(obj1!=null)
				obj1.visionControl(false);
			if(obj2!=null)
				obj2.visionControl(false);
		}
	}
	
	/**
	 * This method is used for the {@link #player} to move, and it will return the result of
	 * the movement
	 * @param direction
	 * the direction that the player is going to move:
	 * 			['w'-up]
	 * 			['s'-down]
	 * 			['a'-left]
	 * 			['d'-right]
	 * @return {@literal "noMove"} if the player can't move and nothing happens,
	 * 		   {@literal "moved"} if the player already moved,
	 * 		   {@literal "bullet"} if the player moves to the item bullet and he gets a bullet,
	 * 		   {@literal "radar"} if the player moves to the item radar and the briefcase will become visible,
	 * 	       {@literal "invincible"} if the player moves to the item invinc and the invincibility counter of the {@link #player} will turn on,
	 * 		   {@literal "getCase"} if the player checks the room and get the briefcase,
	 * 		   {@literal "noCase"} if the player checks the room and doesn't get the briefcase
	 */
	public String playerMove(char direction)
	{
		player.lookControl(false);
		boolean isMove=false;
		String reaction="noMove";
		SquareObject objAhead=getObjAhead(player,direction);
		
		if(objAhead!=null)
		{
			if(objAhead instanceof EmptySpace)
			{
			isMove=true;
			reaction="moved";
			}
			else if( objAhead instanceof Bullet)
			{
			isMove=true;
			reaction="bullet";
			player.addBullet();
			((Bullet) objAhead).beingUsed();
			}
			else if(objAhead instanceof Radar)
			{
				for(int i=0;i<9;i++)
				{
					rooms[i].radarEffect();
				}
				isMove=true;
				reaction="radar";
				((Radar) objAhead).beingUsed();
			}
			else if(objAhead instanceof Invinc)
			{
				isMove=true;
				reaction="invincible";
				player.beInvinc(); 
				((Invinc) objAhead).beingUsed();
			}
			else if(objAhead instanceof Room && direction =='s')
			{
				if(((Room) objAhead).hasCase())
				{
					reaction="getCase";
				}
				else
				{
					reaction="noCase";
				}
			}
			else if(objAhead instanceof Ninja)
			{
				reaction="moved";
			}
		}
		
		if(isMove)
		{
			grid.moveObject(player, objAhead.getX(), objAhead.getY());//add a method in Grid to move the object in the grid
			player.changeDirection(direction);
			if(player.getInvinc()>0 && !(reaction.equals("invincible")) )
				player.weakenInvinc();
			
		}
		return reaction;
	}
	
	/**
	 * This method is used when it is {@link #ninjas}'s turn to take action.
	 * @return
	 * {@code true} if a ninja successfully kills the {@link #player}
	 * {@code false} if the {@link #player} survives
	 */
	public boolean ninjaTurn()
	{
		boolean isStab=false;
		for(int i=0;i<ninjas.length;i++)
		{
			Ninja thisN=ninjas[i];
			boolean moved=false;
			if(!thisN.isDead())
			{
				if(thisN.getX()==player.getX()||thisN.getY()==player.getY())
				{
					if(Math.abs(thisN.getX()-player.getX())==1||Math.abs(thisN.getY()-player.getY())==1)
					{
						moved=true;
						if(player.getInvinc()==0);
						{
							player.beAttacked();
							isStab=true;
						}	
					}
					
					else
					{
						//add AI here
					}
				}	
				
				if(!moved)
					ninjaRamdomMove(thisN);	
			}
		}//for loop end
		putBackItem();
		return isStab;
	}
	
	/**
	 * This is a private method that is for a {@link Ninja} to move randomly.
	 * @param thisN
	 * 				The {@link Ninja} that is going to move
	 */
	private void ninjaRamdomMove(Ninja thisN)
	{
		char direction='w';
		boolean moved=false;
		while(moved==false)
		{
			int rand=randGen(1,4);
			
			switch(rand)
			{
			case 1:
				direction='w';
				break;
			case 2:
				direction='a';
				break;
			case 3:
				direction='s';
				break;
			case 4:
				direction='d';
				break;
			}
			SquareObject objAhead=getObjAhead(thisN,direction);
			if(objAhead instanceof Room || objAhead==null || objAhead instanceof Ninja)
			{
				moved=false;
			}
			else
			{
				moved=true;
				grid.moveObject(thisN, objAhead.getX(), objAhead.getY());
			}
		}
	}
	
	/**
	 * This method is used to deal with the condition that {@link #ninjas} may take the spot of {@link #items}. 
	 * This method would place all the {@link #items} if it's possible.
	 */
	private void putBackItem()
	{
		for(int i=0;i<3;i++)
		{
			if(!items[i].isUsed())
			{
				if(grid.getObject(items[i].getX(), items[i].getY()) instanceof EmptySpace)
					grid.setObject(items[i]);
			}
		}
	}

	/**
	 * This method let the {@link #player} to look at one of the four direction to check whether
	 * there is {@link Ninja} ahead. Additionally, this method may also change the direction of 
	 * the {@link #player}
	 * @param direction
	 * the direction that the player is going to look:
	 * 			['w'-up]
	 * 			['s'-down]
	 * 			['a'-left]
	 * 			['d'-right]
	 * @return
	 * 		{@code true} if any enemy is found; {@code false} if no enemy in this direction
	 */
	public boolean look(char direction)
	{
		player.changeDirection(direction);
		player.lookControl(false);
		boolean hasNinja=false;
		SquareObject nextObj = getObjAhead(player, direction);
		while(nextObj!=null)
		{
			if(nextObj instanceof Ninja)
				hasNinja=true;
			nextObj=getObjAhead(nextObj,direction);
		}
		return hasNinja;
	}
	
	/**
	 * This method allows the {@link #player} to shoot in one direction
	 * @param direction
	 * the direction that the player is going to shoot:
	 * 			['w'-up]
	 * 			['s'-down]
	 * 			['a'-left]
	 * 			['d'-right]
	 * @return
	 * 		{@literal "noBullet"} if there is no bullet in order to shoot;
	 * 		{@literal "kill"} if one of the {@link #ninjas} is killed;
	 * 		{@literal "notKill"} if the bullet hit nothing
	 */
	public String shoot(char direction)
	{
		String reaction;
		if(player.getBullet()==0)
			reaction="noBullet";
		else
		{
			player.shoot();
			SquareObject nextObj = getObjAhead(player,direction);
			while(nextObj!=null &&!(nextObj instanceof Ninja))
			{
				nextObj=getObjAhead(nextObj,direction);
			}
			if(nextObj instanceof Ninja)
			{
				reaction="kill";
				((Ninja) nextObj).beAttacked();
				grid.setObject(new EmptySpace(nextObj.getX(),nextObj.getY()));
				putBackItem();
			}
			else
				reaction="notKill";
		}
		return reaction;
	}

	/**
	 * This method allows the UI to know whether the {@link #player} has already looked in current turn
	 * @return
	 * {@code true} if the player still can look;
	 * {@code false} if the player can't look any more in this turn.
	 */
	public boolean playerCanLook()
	{
		return player.canLook();//add a method to return whether the spy has looked already in current turn
	}
	
	/**
	 * This method allows UI to know how many remaining turns for the {@link #player} being invincible
	 * @return
	 * 			the number of turns in which the {@link #player} stays invincible
	 */
	public int turnsOfInvinc()
	{
		return player.getInvinc();
	}
	
	/**
	 * This method allows UI to know how many bullet that the {@link #player} has
	 * @return
	 * 			the number of bullet that the {@link #player} has
	 */			
	public int numOfBullet()
	{
		return player.getBullet();
	}
	
	
	/**
	 * This method allows UI to know whether the {@link #player} runs out of his
	 * lives and the game is over.If The game is not over yet, the grid will be 
	 * reset 
	 * @return
	 * {@code true} if the game is over;
	 * {@code false} if this {@link #player} still got another chance to try.
	 */
	public boolean isGameOver()
	{
		if(player.getLives()<0)
			return true;
		else
		{
			resetGrid();
			return false;
		}
	}
	
	/**
	 * This method allows UI to know how many lives the {@link #player} has currently.
	 * @return 
	 * The current lives that the {@link #player} still has
	 */
	public int getLives()
	{
		return player.getLives();
	}
	
	/**
	 * This method is to save the current game to a .dat file
	 * @param filename
	 * The name (without extension.dat) of the file that is going to save data to
	 */
	public void saveGame(String filename) throws IOException
	{
		DataSave data = new DataSave(player,ninjas,rooms,items);
		// add a constructor for DataSave to take Spy,Ninja[],Room[] and Item[] as parameters
		
		FileOutputStream fileSave = new FileOutputStream(filename+".dat");
		ObjectOutputStream dataSave= new ObjectOutputStream(fileSave);
		dataSave.writeObject(data);//make DataSave and SquareObject implements Serializable
		dataSave.close();
		fileSave.close();
	}
	
	/**
	 * This method is to load the data in a file to the game  
	 * @param filename
	 * The name (without extension.dat) of the file that already has the data saved
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void loadGame(String filename) throws IOException, ClassNotFoundException
	{
		DataSave data;
		FileInputStream fileSave = new FileInputStream(filename+".dat");
		ObjectInputStream dataSave=new ObjectInputStream(fileSave);
		data=(DataSave) dataSave.readObject();
		dataSave.close();
		fileSave.close();
		player=data.getSpy();//add four methods to DataSave class to get all the information saved inside
		ninjas=data.getNinjas();
		items=data.getItems();
		rooms=data.getRooms();
		grid=new Grid(player,rooms,ninjas);
		//add a overload constructor for Grid to take Spy,Room[] and Ninja[] as parameters
		//make sure clear the grid first and put those SquareObject back to the grid
		//be sure to check ninja.isDead() before set it to the grid
		//no need to set item[]
		this.putBackItem();
		
	}



	
	

}
