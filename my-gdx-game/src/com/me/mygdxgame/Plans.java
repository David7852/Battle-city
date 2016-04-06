package com.me.mygdxgame;

public class Plans 
{
	public int attack;
	public int awere;
	public int angry;
	public int units;
	public boolean isdone=false;
	
	public Plans(int a,int b,int c) 
	{
		attack=a;
		awere=b;
		angry=c;
		units=a+b+c;
	}
	public int getAngry() 
	{
		if(angry>0)
			return angry--;
		else
			return 0;
	}public int getAttack() 
	{
		if(attack>0)
			return attack--;
		else
			return 0;
	}public int getAwere() 
	{
		if(awere>0)
			return awere--;
		else
			return 0;
	}
	public boolean isdone()
	{
		if(attack==0&&angry==0&awere==0)
			return true;
		return false;
	}
	public boolean isSameAs(Plans p)
	{
		if(attack==p.attack&&awere==p.awere&&angry==p.angry)
			return true;
		return false;
	}
	public int getUnits() 
	{
		return units--;
	}
	public Plans() {
		// TODO Auto-generated constructor stub
	}
}
