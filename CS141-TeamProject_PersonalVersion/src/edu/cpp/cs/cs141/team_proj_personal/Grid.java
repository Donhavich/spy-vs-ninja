package edu.cpp.cs.cs141.team_proj_personal;

public class Grid {


	final private int GRID_SIZE=9;
	
	
	private SquareObject  grid[][];

	public Grid() 
	{
		grid=new SquareObject[GRID_SIZE][GRID_SIZE];
	}
	
	public int size()
	{
		return GRID_SIZE;
	}
	
	public void setObject(SquareObject so)
	{
		grid[so.getY()][so.getX()]=so;
	}
	
	public SquareObject getObject(int x,int y)
	{
		return grid[y][x];
	}
	
	public void clear()
	{
		for(int i=0;i<grid.length;i++)
		{
			for(int j=0;j<grid.length;j++)
			{
				grid[i][j]=new EmptySpace(j,i);
			}
		}
	}
	
	public void moveObject(SquareObject moveObj,int targetX,int targetY)
	{
		grid[targetY][targetX]=moveObj;
		grid[moveObj.getY()][moveObj.getX()]=new EmptySpace(moveObj.getX(), moveObj.getY());
		moveObj.setLocation(targetX, targetY);
	}
		
	
	public void resetGrid()
	{
		this.clear();
	
	}
		
	public String toString(boolean isDebug)
	{
		String temp = "";
		for(int i=0;i<grid.length;i++)
		{
			for(int j=0;j<grid.length;j++)
			{
				temp+=grid[i][j].toString(isDebug);
			}
			temp+="\n";
		}
		return temp;
	}
	
		


}
