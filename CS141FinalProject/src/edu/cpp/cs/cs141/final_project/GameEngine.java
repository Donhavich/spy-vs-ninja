/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

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
		player=new Spy();//add default constructor
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
		items[0]=new Bullet();//add default consturctor
		items[1]=new Radar();//add default consturctor
		items[2]=new Invinc();//add default consturctor
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
		for(int i=0;i<6;i++)
		{
			int x,y;
			do{
			  do{
				  x=randGen(0,8);
				  y=randGen(0,8);
			  }while(x+y<4);
			  y=8-y;
			}while(grid.getObject(x,y) instanceof Room);//add a method in Grid to get object
			ninjas[i]=new Ninja();
			ninjas[i].setLocation(x,y);
			grid.setObject(ninjas[i]);
		}
	}
		
	/**
	 * This method set the current {@link #player} to the left corner of the {@link #grid}
	 */
			
	private void setSpy()
	{
		player.setLocation(0, 8);
	}
	
	/**
	 * This method reset the whole {@link #grid} including {@link #rooms}, {@link #ninjas} and {@link #items} but
	 * only the location of the {@link #player};
	 */
	private void resetGrid()
	{
		grid.clear();//add a method in the Grid class that fill out the whole grid with EmptySpace
		resetRooms();
		setSpy();
		resetItems();
		resetNinjas();
	}
	public String toString(boolean isDebug)
	{
		return grid.toString(isDebug);  
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
					 obj2 = obj1==null?null:getObjAhead(obj1,player.getDirection());//add a direction field and related method in the Spy class
		if(control==true)
		{
			if(obj1!=null)
				obj1.enableVision();//add methods in SquareObject to control the vision 
			if(obj2!=null)
				obj2.enableVision();
		}
		else
		{
			if(obj1!=null)
				obj1.disableVision();
			if(obj2!=null)
				obj2.disableVision();
		}
	}
	
	/**
	 * This method is used for the {@link #player} to move, and it will return the result of
	 * the movement
	 * @param the direction that the player is going to move
	 * 			'w'-up
	 * 			's'-down
	 * 			'a'-left
	 * 			'd'-right
	 * @return {@literal "noMove"} if the player can't move and nothing happens,
	 * 		   {@literal "empty"} if the player move to an empty square,
	 * 		   {@literal "bullet"} if the player move to the item bullet and he gets a bullet,
	 * 		   {@literal "radar"} if the player move to the item radar and the briefcase will become visible,
	 * 	       {@literal "invincible"} if the player move to the item invinc and the invincibility counter of the {@link #player} will turn on,
	 * 		   {@literal "getCase"} if the player check the room and get the briefcase,
	 * 		   {@literal "noCase"} if the player check the room and doesn't get the briefcase
	 */
	public String playerMove(char direction)
	{
		boolean isMove=false;
		String reaction="noMove";
		SquareObject objAhead=getObjAhead(player,direction);
		
		if(objAhead!=null)
		{
			if(objAhead instanceof EmptySpace)
			{
			isMove=true;
			reaction="empty";
			}
			else if( objAhead instanceof Bullet)
			{
			isMove=true;
			reaction="bullet";
			player.getBullet();
			((Bullet) objAhead).beingUsed();
			}
			else if(objAhead instanceof Radar)
			{
				for(int i=0;i<9;i++)
				{
					rooms[i].enableVisionOfCase(); //add a method in the Room class to enable the vision of the briefcase
				}
				isMove=true;
				reaction="radar";
				((Radar) objAhead).beingUsed();
			}
			else if(objAhead instanceof Invinc)
			{
				isMove=true;
				reaction="invincible";
				player.beInvinc(); //add a method to the Player to make it become invincible
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
		}
		
		if(isMove)
		{
			grid.moveObject(player, objAhead.getX(), objAhead.getY());//add a method in Grid to move the object in the grid
			player.changeDirection(direction);//add a method in Player class that allows to change his direction
			if(player.isInvinc())
				player.weakenInvinc();//add an method in Spy class to weaken the invincibility
			
		}
		return reaction;
	}



	
	

}
