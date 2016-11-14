package edu.cpp.cs.cs141.team_proj_personal;

import java.util.Random;

public class GameEngine {
	
	private Grid floor ;
		
	private int numOfNinja;
		
	private Room[] roomList;
		
	private Ninja[] ninjaList;
		
	private Spy player;
		
	private Item[] items;
		
	public GameEngine() {};
		
	private int randGen(int min,int max)
	{
		Random rand = new Random();
		return rand.nextInt(max-min+1)+min;
	}
		

	
	public void NewGame(int numOfNinja)
	{
		floor=new Grid();
		this.numOfNinja=numOfNinja;
		player=new Spy();
		floor.clear();
		resetSpy();
		setRoom();
		setItem();
		setNijia();
	}
	
	private void setRoom()
	{
		roomList=new Room[floor.size()*floor.size()/9];
		for(int i=0;i<floor.size()/3;i++)
		{
			for(int j=0;j<floor.size()/3;j++)
			{
				floor.setObject(roomList[3*i+j]=new Room(1+i*3,1+j*3));		
			}	
		}
		roomList[randGen(0,2)].placeCase();
	}
	
	private void resetSpy()
	{
		player.setLocation(0, floor.size()-1);
		player.changeDirection('w');
		floor.setObject(player);
	}
	
	private void setItem()
	{
		items=new Item[3];
		items[0]=new Bullet();
		items[1]=new Radar();
		items[2]=new Invinc();
		for(int i=0;i<3;i++)
		{
			int x,y;
			do{
				x=randGen(0,floor.size()-1);
				y=randGen(0,floor.size()-1);
			   
			}while(!(floor.getObject(x, y) instanceof EmptySpace)); 
			items[i].setLocation(x,y); 
			floor.setObject(items[i]);
		}
	}
	
	private void setNijia()
	{
		ninjaList=new Ninja[numOfNinja];
		for(int i=0;i<numOfNinja;i++)
		{
			int x,y;
			do{
			  do{
				  x=randGen(0,floor.size()-1);
				  y=randGen(0,floor.size()-1);
			  }while(x+y<4);
			  y=floor.size()-1-y;
			}while(floor.getObject(x, y) instanceof Room);
			
			floor.setObject(ninjaList[i]=new Ninja(x,y));	
		}
	}
	
	private SquareObject getObjAhead(SquareObject thisObj,char direction)
	{
		int thisX=thisObj.getX()
		   ,thisY=thisObj.getY();
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
		if(aheadX>-1 && aheadX<floor.size() && aheadY>-1 && aheadY<floor.size())
			return floor.getObject(aheadX, aheadY);
		else
			return null;
		
	}
	
	
	private void visionControl(boolean control)
	{
		SquareObject obj1 = getObjAhead(player, player.getDirection()),
					 obj2 = obj1==null?null:getObjAhead(obj1,player.getDirection());
		if(control==true)
		{
			if(obj1!=null)
				obj1.enableVision();
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
	 * 
	 * @param direction
	 * @return {@literal "noMove"} if the player can't move,
	 * 		   {@literal "empty"}
	 * 		   {@literal "bullet"}
	 * 		   {@literal "radar"}
	 * 	       {@literal "invincible"}
	 * 		   {@literal "getCase"}
	 * 		   {@literal "noCase"}
	 */
	public String playerTurn(char direction)
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
					roomList[i].enableVisionOfCase();
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
		}
		
		if(isMove)
		{
			floor.moveObject(player, objAhead.getX(), objAhead.getY());
			player.changeDirection(direction);
			if(player.isInvinc())
				player.weakenInvinc();
			
		}
		return reaction;
	}
	
	public boolean ninjaTurn()
	{
		boolean isStab=false;
		for(int i=0;i<numOfNinja;i++)
		{
			Ninja thisN=ninjaList[i];
			boolean moved=false;
			if(!thisN.isDead())
			{
				if(thisN.getX()==player.getX()||thisN.getY()==player.getY())
				{
					if(Math.abs(thisN.getX()-player.getX())==1||Math.abs(thisN.getY()-player.getY())==1)
					{
						moved=true;
						if(!player.isInvinc())
						{
							player.beKilled();
							isStab=true;
						}	
					}
				//AI
				}
				
				char direction='w';
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
						floor.moveObject(thisN, objAhead.getX(), objAhead.getY());
					}
				}
			}
			putBackItem();
		}
		return isStab;
	}
	

	private void putBackItem()
	{
		for(int i=0;i<3;i++)
		{
			if(!items[i].isUsed())
			{
				if(floor.getObject(items[i].getX(), items[i].getY()) instanceof EmptySpace)
					floor.setObject(items[i]);
			}
		}
	}
	
	public String toString(boolean isDebug)
	{
		visionControl(true);
		String printGrid = floor.toString(isDebug);
		visionControl(false);
		return printGrid;
	}
	
	public boolean look(char direction)
	{
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
	
	public String shoot(char direction)
	{
		String reaction;
		if(!player.hasBullet())
			reaction="noBullet";
		else
		{
			player.loseBullet();
			SquareObject nextObj = getObjAhead(player,direction);
			while(nextObj!=null &&!(nextObj instanceof Ninja))
			{
				nextObj=getObjAhead(nextObj,direction);
			}
			if(nextObj instanceof Ninja)
			{
				reaction="kill";
				((Ninja) nextObj).beKilled();
				floor.setObject(new EmptySpace(nextObj.getX(),nextObj.getY()));
				putBackItem();
			}
			else
				reaction="notKill";
		}
		return reaction;
	}
	
	public int invinc()
	{
		return player.getInvinc();
	}
	
	public int numOfBullet()
	{
		return player.hasBullet()?1:0;
	}
	
	public int lives()
	{
		return player.getLives();
	}
	
	public int trunsOfInvinc()
	{
		return player.getInvinc();
	}

	
	

}
