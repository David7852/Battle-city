package com.me.mygdxgame;

public class Bullet
{
	public static long slow=50000000;
	public boolean live=true;
	
	public char itgoto;//u,l,r,d
	public int X,Y;
	
	public Bullet(int x, int y, char itgoto) 
	{
		X=x;
		Y=y;
		this.itgoto=itgoto;
	}
	public Bullet()
	{
		
	}
	
	
}
