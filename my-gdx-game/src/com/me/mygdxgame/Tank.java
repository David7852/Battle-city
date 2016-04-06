package com.me.mygdxgame;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Tank 
{
	public static long slow=500000000;
	public static int stomach=50;
	public static int pileofbullets=50;
	
	public Cabinet Cockpit;
	
	public boolean color;
	
	public Cell nowon,myself;
	public char lookat;//u,l,r,d
	public int fuel;
	public int bullets;
	public int X,Y;
	
	public Tank(){
	}	
	
	public Tank(int x, int y, char l, Cell n,Cell m,boolean c) 
	{
		color=c;
		X=x;Y=y;
		lookat=l;
		bullets=pileofbullets;
		fuel=stomach;
		nowon=n;
		myself=m;
		Cockpit=new Cabinet(x,y,l,fuel,bullets,c);
		/*Cockpit.getMachinist().enqueue(new Word(Keys.DOWN, true, 'd'));
		Cockpit.getMachinist().enqueue(new Word(Keys.DOWN, true, 'd'));
		Cockpit.getMachinist().enqueue(new Word(Keys.DOWN, true, 'd'));
		Cockpit.getMachinist().enqueue(new Word(Keys.DOWN, true, 'd'));
		Cockpit.getMachinist().enqueue(new Word(Keys.RIGHT, true, 'r'));
		Cockpit.getMachinist().enqueue(new Word(Keys.RIGHT, true, 'r'));
		Cockpit.getMachinist().enqueue(new Word(Keys.RIGHT, true, 'r'));
		Cockpit.getMachinist().enqueue(new Word(Keys.LEFT, true, 'l'));
		Cockpit.getMachinist().enqueue(new Word(Keys.LEFT, true, 'l'));
		Cockpit.getMachinist().enqueue(new Word(Keys.LEFT, true, 'l'));
		Cockpit.getMachinist().enqueue(new Word(Keys.LEFT, true, 'l'));
		Cockpit.getMachinist().enqueue(new Word(Keys.UP, true, 'u'));
		Cockpit.getMachinist().enqueue(new Word(Keys.UP, true, 'u'));*/
		
	}
	
	
	public void setBullets(int bullets) 
	{
		this.bullets = bullets;
		Cockpit.bullets=bullets;
	}
	public void setFuel(int fuel)
	{
		this.fuel = fuel;
		Cockpit.fuel=fuel;
	}
	public void setLookat(char lookat) 
	{
		this.lookat = lookat;
		Cockpit.lookat=lookat;
	}
		
	public int getFuel() 
	{
		return fuel;
	}
	public char getLookat() 
	{
		return lookat;
	}
	public int getBullets() 
	{
		return bullets;
	}
	public int getX() 
	{
		return X;
	}
	public int getY() 
	{
		return Y;
	}
	public void setCockpit(Cabinet cockpit) 
	{
		Cockpit = cockpit;
		fuel=Cockpit.fuel;
		bullets=Cockpit.bullets;
		X=Cockpit.X;
		Y=Cockpit.Y;
		lookat=Cockpit.lookat;
	}
	public void setMyself(Cell myself) 
	{
		this.myself = myself;
	}

	public void setposition(int x, int y)
	{
		X=x;
		Cockpit.X=x;
		Y=y;
		Cockpit.Y=y;
	}
	public void setnowon(Cell nowon) 
	{
		if(this.nowon==null||this.nowon.getTile().getId()!=3)
		{
			fuel--;
			Cockpit.fuel=fuel;
			if(this.nowon!=null&&this.nowon.getTile().getId()!=7)
				Cockpit.hide=true;
			else
				Cockpit.hide=false;
		}
		else
		{
			fuel-=2;
			Cockpit.fuel=fuel;			
		}
		
		this.nowon=nowon;
		
	}	

	

	
	
}
