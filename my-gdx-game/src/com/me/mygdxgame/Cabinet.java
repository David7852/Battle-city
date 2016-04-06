package com.me.mygdxgame;


import sun.misc.Queue;

public class Cabinet 
{
	public boolean hide=false,closeEnemy=false,farhome=false,hungry=false,lowamo=false,stuck=false,nobullets=false,dead=false;
	
	private Captain Sparrow;//who fill the queue
	private Queue StuttererMachinist;//queue of words
	public boolean color;
	public int fuel;
	public int bullets;
	public int X,Y;
	public char lookat;

	public Cabinet(int x, int y, char l, int f,int b, boolean c) 
	{
		color=c;
		X=x;Y=y;
		lookat=l;
		fuel=f;
		bullets=b;
		StuttererMachinist=new Queue();
		Sparrow=new Captain(this,color);
	}
	
	public Captain getCaptain() 
	{
		return Sparrow;
	}public Queue getMachinist() 
	{
		return StuttererMachinist;
	}
	public void setMachinist(Queue stuttererMachinist) 
	{
		StuttererMachinist = stuttererMachinist;
	}
	
	public Word getorders()//UNICO extractor del maquinista
	{
		if(!StuttererMachinist.isEmpty())
		{
			try 
			{
				return (Word) StuttererMachinist.dequeue(0);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
				return null;
			}
		}else
			Sparrow.actualplan=Strategy.lazy;
			return null;
	}
	
	
}
