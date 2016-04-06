package com.me.mygdxgame;

public class Word 
{
	private int key=0;
	private boolean shoot=false;
	private char lookat='u';
	
	public Word(int k, boolean s,char l) 
	{
		key=k;shoot=s;lookat=l;
	}
	public int getKey() 
	{
		
		return key;
	}
	public boolean getShoot() 
	{
		return shoot;
	}public char getLookat() 
	{
		return lookat;
	}public void setKey(int key) 
	{
		this.key = key;
	}
	public void setLookat(char lookat) 
	{
		this.lookat = lookat;
	}public void setShoot(boolean s) 
	{
		this.shoot = s;
	}
}
