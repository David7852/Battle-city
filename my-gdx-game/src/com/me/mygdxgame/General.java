package com.me.mygdxgame;

import heuristics.AStarHeuristic;
import heuristics.ClosestHeuristic;

import java.awt.Point;
import java.util.ArrayList;

import aStar.AStar;
import aStar.AreaMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class General 
{
	public boolean color;
	public static Plans myplan, conquer, destroy, protect, balance;
	
	public int aux1=0,aux2=0,aux3=0,aux4=0;
	
	public ArrayList<Point> badbase,base,gas,bullets;//forest,rocks,water?
	public int orders;
	
	public TiledMapTileLayer maplayer, tanklayer, bulletlayer;
	public ArrayList<Tank> mytanks;
	public ArrayList<Tank> eviltanks;
	public ArrayList<Bullet> thebullets;
	
	public int crypticmap[][];
	public AreaMap map;
	public AStarHeuristic heuristic;
	public AStar cartographer;
	
	public General(TiledMapTileLayer maplayer,TiledMapTileLayer tanklayer,TiledMapTileLayer bulletlayer,ArrayList<Tank> myts,ArrayList<Tank> evilts,ArrayList<Bullet> bullets,boolean c)//el constructor del general obtiene las referencias del stargamescreen 
	{
		color=c;
		//operadores cognitivos (unicos)
		heuristic = new ClosestHeuristic();
		map=new AreaMap();
		cartographer=new AStar();
		
		myplan=new Plans(0,0,0);
		//planes defecto  a, d, r
		conquer=new Plans(3,0,1);
		destroy=new Plans(0,0,4);
		protect=new Plans(1,3,0);
		balance=new Plans(2,1,1);
		
		this.maplayer=maplayer;
		this.tanklayer=tanklayer;
		this.bulletlayer=bulletlayer;
		
		mytanks=myts;
		eviltanks=evilts;
		thebullets=bullets;
		orders=0;
		//se setean los tanks y se crea el primer criptico
		fill();
		//con dicho criptico, se entiende el mapa como un grafo
	}
	public void refill()//updates the cryptic map, the graph map and the cartographer
	{
		for(int i=0;i<35;i++)
			for(int j=0;j<25;j++)
				if(tanklayer.getCell(i, j)!=null&&tanklayer.getCell(i, j).getTile().getId()==1)
					crypticmap[i][j]=1;
				else
					if(tanklayer.getCell(i, j)!=null&&tanklayer.getCell(i, j).getTile().getId()==2)
						crypticmap[i][j]=2;
					else 
						if(maplayer.getCell(i, j)==null||maplayer.getCell(i, j).getTile().getId()==0)
							crypticmap[i][j]=0;
						else
							crypticmap[i][j]=maplayer.getCell(i, j).getTile().getId();
		map.SetAreaMap(35, 25, crypticmap,color);
		cartographer.ReadTheStars(map, heuristic);
	}
	
	public void fill()//crea un criptico y rellena los tanques
	{
		crypticmap=new int[35][25];
		badbase=new ArrayList<Point>();
		base=new ArrayList<Point>();
		gas=new ArrayList<Point>();
		bullets=new ArrayList<Point>();
		if(color)
		{
			for(int i=0;i<35;i++)
				for(int j=0;j<25;j++)
				{
					if(maplayer.getCell(i, j)==null||maplayer.getCell(i, j).getTile().getId()==0)
						crypticmap[i][j]=0;
					else
					{
						if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==11)
						{
							base.add(new Point(i, j));
						}else
							if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==10)
							{
								badbase.add(new Point(i, j));
							}else
								if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==8)
								{
									gas.add(new Point(i, j));
								}else
									if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==9)
									{
										bullets.add(new Point(i, j));
									}
						crypticmap[i][j]=maplayer.getCell(i, j).getTile().getId();
					}
					if(tanklayer.getCell(i, j)!=null&&tanklayer.getCell(i, j).getTile().getId()==1)
						{
							Tank dumb=new Tank(i, j, 'u', maplayer.getCell(i, j),tanklayer.getCell(i, j),true);
							mytanks.add(dumb);
							crypticmap[i][j]=1;
						}
					else
						if(tanklayer.getCell(i, j)!=null&&tanklayer.getCell(i, j).getTile().getId()==2)
						{
							Tank dumb=new Tank(i, j, 'u',  maplayer.getCell(i, j),tanklayer.getCell(i, j),false);
							eviltanks.add(dumb);
							crypticmap[i][j]=2;
						}
				}
		}
		else
		{
			for(int i=0;i<35;i++)
				for(int j=0;j<25;j++)
				{
					if(maplayer.getCell(i, j)==null||maplayer.getCell(i, j).getTile().getId()==0)
						crypticmap[i][j]=0;
					else
					{
							if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==11)
							{
								badbase.add(new Point(i, j));
							}else
								if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==10)
								{
									base.add(new Point(i, j));
								}else
									if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==8)
									{
										gas.add(new Point(i, j));
									}else
										if(maplayer.getCell(i, j)!=null&&maplayer.getCell(i, j).getTile().getId()==9)
										{
											bullets.add(new Point(i, j));
										}
							crypticmap[i][j]=maplayer.getCell(i, j).getTile().getId();
					}
					if(tanklayer.getCell(i, j)!=null&&tanklayer.getCell(i, j).getTile().getId()==1)
						{
							Tank dumb=new Tank(i, j, 'u', maplayer.getCell(i, j),tanklayer.getCell(i, j),false);
							eviltanks.add(dumb);
							crypticmap[i][j]=2;
						}
					else
						if(tanklayer.getCell(i, j)!=null&&tanklayer.getCell(i, j).getTile().getId()==2)
						{
							Tank dumb=new Tank(i, j, 'u', maplayer.getCell(i, j),tanklayer.getCell(i, j),true);
							mytanks.add(dumb);
							crypticmap[i][j]=1;
						}
				}
		}
	}
	public void selectplan()//select the strategy
	{
		if(myplan.isdone())
		if(mytanks.size()==eviltanks.size()||orders==0)
		{
			myplan=conquer;
		}else
			if(mytanks.size()==eviltanks.size()-1)
			{
				myplan=destroy;
			}else
				if(mytanks.size()>eviltanks.size())
				{
					myplan=conquer;
				}else
					if(mytanks.size()<eviltanks.size())
					{
						myplan=balance;
					}
	}
	
	public void planning()//gives tanks an orders to play
	{
		refill();
		selectplan();
		for(Tank dumb: mytanks)
		{
			if(myplan.attack>0)
			{
				if(!dumb.Cockpit.hungry&&!dumb.Cockpit.lowamo&&dumb.Cockpit.getMachinist().isEmpty())
				{
					if(dumb.Cockpit.getCaptain().selecter(Strategy.Attack))
						myplan.getAttack();
				}
			}else
				if(myplan.awere>0)
				{
					
				}else
					if(myplan.angry>0)
					{
						if(!dumb.Cockpit.lowamo)
						{
							if(dumb.Cockpit.getCaptain().selecter(Strategy.Angry))
								myplan.getAngry();
						}
					}
			if(myplan.isdone())
				orders++;
			if(dumb.Cockpit.getCaptain().actualplan==Strategy.Attack&&dumb.Cockpit.getMachinist().isEmpty())
			{
				int badbaseX=badbase.get(0).x,badbaseY=badbase.get(0).y;
				ArrayList<Point> vert=new ArrayList<Point>(),hor=new ArrayList<Point>();
				for(int i=0;i<25;i++)//for vertical subiendo
					if(crypticmap[badbaseX][i]!=5||(color&&crypticmap[badbaseX][i]==1||crypticmap[badbaseX][i]==11)||(!color&&crypticmap[badbaseX][i]==2||crypticmap[badbaseX][i]==10))
						vert.add(new Point(badbaseX,i));
					else
						break;
				for(int i=0;i>0;i--)//for vertical bajando
					if(crypticmap[badbaseX][i]!=5||(color&&crypticmap[badbaseX][i]==1||crypticmap[badbaseX][i]==11)||(!color&&crypticmap[badbaseX][i]==2||crypticmap[badbaseX][i]==10))
						vert.add(new Point(badbaseX,i));
					else
						break;
				for(int i=0;i<35;i++)//for horizontal subiendo
					if(crypticmap[i][badbaseY]!=5||(color&&crypticmap[i][badbaseY]==1||crypticmap[i][badbaseY]==11)||(!color&&crypticmap[i][badbaseY]==2||crypticmap[i][badbaseY]==10))
						hor.add(new Point(i,badbaseY));
					else
						break;
				for(int i=0;i>0;i--)//for horizontal bajando
					if(crypticmap[i][badbaseY]!=5||(color&&crypticmap[i][badbaseY]==1||crypticmap[i][badbaseY]==11)||(!color&&crypticmap[i][badbaseY]==2||crypticmap[i][badbaseY]==10))
						hor.add(new Point(i,badbaseY));
					else
						break;
				dumb.Cockpit.getCaptain().Siege(vert, hor, cartographer.calcShortestPath(dumb.getX(), dumb.getY(), badbaseX, badbaseY));
			}
			if(dumb.Cockpit.getCaptain().actualplan==Strategy.Angry&&dumb.Cockpit.getMachinist().isEmpty())
			{
				int dist=Integer.MAX_VALUE,clos=0;
				Point p=new Point();
				for(Tank bad:eviltanks)
				{
					int d=0;
					if(cartographer.calcShortestPath(dumb.getX(), dumb.getY(), bad.getX(), bad.getY())!=null)
						d=cartographer.calcShortestPath(dumb.getX(), dumb.getY(), bad.getX(), bad.getY()).getLength();
					if(d<dist)
					{
						dist=d;
						clos=eviltanks.indexOf(bad);
						p.x=bad.getX();
						p.y=bad.getY();
					}
				}
				dumb.Cockpit.getCaptain().hunt(p, cartographer.calcShortestPath(dumb.getX(), dumb.getY(), eviltanks.get(clos).getX(), eviltanks.get(clos).getY()), crypticmap, eviltanks.get(clos).getLookat());
			}
			if(dumb.Cockpit.hungry)
			{
				dumb.Cockpit.getCaptain().BASICmove(cartographer.calcShortestPath(dumb.getX(), dumb.getY(), gas.get(0).x, gas.get(0).y));
			}else
				if(dumb.Cockpit.lowamo)
				{
					dumb.Cockpit.getCaptain().BASICmove(cartographer.calcShortestPath(dumb.getX(), dumb.getY(), bullets.get(0).x, bullets.get(0).y));
				}
		}
	}
	
	
	public void gasconsumed(int x, int y)
	{
		//if(gas.size()>0)
		//for(Point p:gas)
			//if(p!=null&&p.getX()==x&&p.getY()==y)
				//gas.remove(p);
	}
	public void bulletconsumed(int x, int y)
	{
		if(bullets.size()>0)
		for(Point p:bullets)
			if(p!=null&&p.getX()==x&&p.getY()==y)
				bullets.remove(p);
	}
}
