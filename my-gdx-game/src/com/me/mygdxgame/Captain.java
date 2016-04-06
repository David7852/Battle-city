package com.me.mygdxgame;

import java.awt.Point;
import java.util.ArrayList;

import sun.misc.Queue;

import aStar.Path;

import com.badlogic.gdx.Input.Keys;

public class Captain 
{
	public boolean color;
	public Strategy actualplan=Strategy.lazy;
	public int gasofactualpath=0,bulletsofactualpath=0;
	public Cabinet WhereAmTheBoss;
	
	public Captain(Cabinet Cockpit,boolean c) 
	{
		WhereAmTheBoss=Cockpit;
		color=c;
	}
	
	public Strategy getActualplan() 
	{
		
		return actualplan;
	}	
	
	public boolean selecter(Strategy newact)//si el selector devuelve true, entonces, dependiendo del enum, el general ordena al capitan una orden acorde al actualplan. si es falso, no ordena nada.
	{
		if(newact!=actualplan)
		{
			if(newact==Strategy.Alert)//modo alerta o de amenaza 
			{
				actualplan=newact;
				return true;
			}else
				if(newact==Strategy.Awere&&actualplan!=Strategy.Alert)//modo defensa, que se bloquea si un tanque esa cerca de la base y no se termina hasta que este
				{
					actualplan=newact;
					return true;
				}else
					//if(actualplan==Strategy.Awere&&WhereAmTheBoss.getMachinist().isEmpty())//reparar
						if(newact==Strategy.Attack&&actualplan!=Strategy.Alert)//modo asedio, si el defensa no esta bloqueado, cambia
						{
							actualplan=newact;
							return true;
						}else
							if(newact==Strategy.Angry&&actualplan!=Strategy.Alert)
							{
								actualplan=newact;
								return true;
							}
			return false;
		}else
			if(WhereAmTheBoss.getMachinist().isEmpty())
			{
				actualplan=newact;
				return true;
			}
		return false;
	}
	
	public void BASICmove(Path destiny)
	{
		if(destiny!=null)
		{
			int x=WhereAmTheBoss.X,y=WhereAmTheBoss.Y,costongas=0,costonbullets=0,key=0;
			char lookat=WhereAmTheBoss.lookat;
			boolean shoot=false;
			
			for(int i=0;i<destiny.getLength();i++,key=0,shoot=false)
			{	
				if(i==0&&((destiny.getID(i)==4)||(destiny.getID(i)==2&&color)||(destiny.getID(i)==1&&!color)||(destiny.getID(i)==10&&color)||(destiny.getID(i)==11&&!color)))
				{
					if(destiny.getX(i)>x)
					{
						lookat='r';
						shoot=true;
						costonbullets++;
					}
					else
						if(destiny.getX(i)<x)
						{
							lookat='l';
							shoot=true;
							costonbullets++;
						}
					if(destiny.getY(i)>y)
					{
						lookat='u';
						shoot=true;
						costonbullets++;
					}else
						if(destiny.getY(i)<y)
						{
							lookat='d';
							shoot=true;
							costonbullets++;
						}
					if(destiny.getID(i)==3)
						costongas++;
					WhereAmTheBoss.getMachinist().enqueue(new Word(0,shoot,lookat));					
					key=0;shoot=false;
				}
				int auxX=x,auxY=y;
					if(destiny.getX(i)>x)
					{
						key=Keys.RIGHT;lookat='r';
						costongas++;
						auxX=x+1;
					}
					else
						if(destiny.getX(i)<x)
						{
							key=Keys.LEFT;lookat='l';
							costongas++;
							auxX=x-1;
						}
					if(destiny.getY(i)>y)
						{
							key=Keys.UP;lookat='u';
							costongas++;
							auxY=y+1;
						}
						else
							if(destiny.getY(i)<y)
							{
								key=Keys.DOWN;lookat='d';
								costongas++;
								auxY=y-1;
							}
					if(i<destiny.getLength()-1&&((destiny.getID(i+1)==4)||(destiny.getID(i+1)==2&&color)||(destiny.getID(i+1)==1&&!color)||(destiny.getID(i+1)==10&&color)||(destiny.getID(i+1)==11&&!color)))
					{
						if(destiny.getX(i+1)>auxX)
						{
							lookat='r';
							shoot=true;
							costonbullets++;
						}
						else
							if(destiny.getX(i+1)<auxX)
							{
								lookat='l';
								shoot=true;
								costonbullets++;
							}								
						if(destiny.getY(i+1)>auxY)
						{
							lookat='u';
							shoot=true;
							costonbullets++;
						}else
							if(destiny.getY(i+1)<auxY)
							{
								lookat='d';
								shoot=true;
								costonbullets++;
							}	
					}					
					if(destiny.getID(i)==3)
						costongas++;
					WhereAmTheBoss.getMachinist().enqueue(new Word(key,shoot,lookat));
					x=destiny.getX(i);
					y=destiny.getY(i);
			}
			bulletsofactualpath=costonbullets;
			gasofactualpath=costongas;
		}
	}
	public boolean Siege(ArrayList<Point> vert,ArrayList<Point> hor,Path destiny)//vert and hor are the free way to a base
	{
		if(destiny!=null)
		{	
			Queue Machinist=new Queue();
			int x=WhereAmTheBoss.X,y=WhereAmTheBoss.Y,costongas=0,costonbullets=0,key=0;
			Point auxpoint=new Point();
			char lookat=WhereAmTheBoss.lookat;
			boolean shoot=false;
			
			for(int i=0;i<destiny.getLength();i++,key=0,shoot=false)
			{	
				if(i==0&&((destiny.getID(i)==4)||(destiny.getID(i)==2&&color)||(destiny.getID(i)==1&&!color)||(destiny.getID(i)==10&&color)||(destiny.getID(i)==11&&!color)))
				{
					if(destiny.getX(i)>x)
					{
						lookat='r';
						shoot=true;
						costonbullets++;
					}
					else
						if(destiny.getX(i)<x)
						{
							lookat='l';
							shoot=true;
							costonbullets++;
						}
					if(destiny.getY(i)>y)
					{
						lookat='u';
						shoot=true;
						costonbullets++;
					}else
						if(destiny.getY(i)<y)
						{
							lookat='d';
							shoot=true;
							costonbullets++;
						}
					if(destiny.getID(i)==3)
						costongas++;
					Machinist.enqueue(new Word(0,shoot,lookat));					
					key=0;shoot=false;
				}
				int auxX=x,auxY=y;
					if(destiny.getX(i)>x)
					{
						key=Keys.RIGHT;lookat='r';
						costongas++;
						auxX=x+1;
					}
					else
						if(destiny.getX(i)<x)
						{
							key=Keys.LEFT;lookat='l';
							costongas++;
							auxX=x-1;
						}
					if(destiny.getY(i)>y)
						{
							key=Keys.UP;lookat='u';
							costongas++;
							auxY=y+1;
						}
						else
							if(destiny.getY(i)<y)
							{
								key=Keys.DOWN;lookat='d';
								costongas++;
								auxY=y-1;
							}
					if(i<destiny.getLength()-1&&((destiny.getID(i+1)==4)||(destiny.getID(i+1)==2&&color)||(destiny.getID(i+1)==1&&!color)||(destiny.getID(i+1)==10&&color)||(destiny.getID(i+1)==11&&!color)))
					{
						if(destiny.getX(i+1)>auxX)
						{
							lookat='r';
							shoot=true;
							costonbullets++;
						}
						else
							if(destiny.getX(i+1)<auxX)
							{
								lookat='l';
								shoot=true;
								costonbullets++;
							}								
						if(destiny.getY(i+1)>auxY)
						{
							lookat='u';
							shoot=true;
							costonbullets++;
						}else
							if(destiny.getY(i+1)<auxY)
							{
								lookat='d';
								shoot=true;
								costonbullets++;
							}	
					}					
					if(destiny.getID(i)==3)
						costongas++;
					
					auxpoint.setLocation(x, y);
					if((vert.contains(auxpoint)||hor.contains(auxpoint))&&!shoot)
					{
						shoot=true;
						costonbullets++;
					}
					Machinist.enqueue(new Word(key,shoot,lookat));
					x=destiny.getX(i);
					y=destiny.getY(i);															
			}
			Machinist.enqueue(new Word(0,true,lookat));//porsia
			costonbullets++;//porsia
			if(costonbullets<=WhereAmTheBoss.bullets&&costongas<=WhereAmTheBoss.fuel)
			{
				WhereAmTheBoss.setMachinist(Machinist);
				bulletsofactualpath=costonbullets;
				gasofactualpath=costongas;
				return true;
			}else
			{
				WhereAmTheBoss.hungry=true;//si hungry o lowamo es true en algun momento, el general muy probablemente los ordenara a ir a buscar municiones o combustible.
				WhereAmTheBoss.lowamo=true;
			}
		}
		return false;
	}
	public void hunt(Point kennedy,Path destiny,int[][] crypticmap,char kennedisat)
	{
			int x=WhereAmTheBoss.X,y=WhereAmTheBoss.Y,costongas=0,costonbullets=0,key=0;
			char lookat=WhereAmTheBoss.lookat;
			boolean shoot=false;
			if(destiny!=null)
			for(int i=0;i<1;i++,key=0,shoot=false)
			{	
				if(i==0&&((destiny.getID(i)==4)||(destiny.getID(i)==2&&color)||(destiny.getID(i)==1&&!color)||(destiny.getID(i)==10&&color)||(destiny.getID(i)==11&&!color)))
				{
					if(destiny.getX(i)>x)
					{
						lookat='r';
						shoot=true;
					}
					else
						if(destiny.getX(i)<x)
						{
							lookat='l';
							shoot=true;
						}
					if(destiny.getY(i)>y)
					{
						lookat='u';
						shoot=true;
					}else
						if(destiny.getY(i)<y)
						{
							lookat='d';
							shoot=true;
						}
					if(destiny.getID(i)==3)
					WhereAmTheBoss.getMachinist().enqueue(new Word(0,shoot,lookat));					
					key=0;shoot=false;
				}
				int auxX=x,auxY=y;
					if(destiny.getX(i)>x)
					{
						key=Keys.RIGHT;lookat='r';
						auxX=x+1;
					}
					else
						if(destiny.getX(i)<x)
						{
							key=Keys.LEFT;lookat='l';
							auxX=x-1;
						}
					if(destiny.getY(i)>y)
						{
							key=Keys.UP;lookat='u';
							auxY=y+1;
						}
						else
							if(destiny.getY(i)<y)
							{
								key=Keys.DOWN;lookat='d';
								auxY=y-1;
							}
					if(i<destiny.getLength()-1&&((destiny.getID(i+1)==4)||(destiny.getID(i+1)==2&&color)||(destiny.getID(i+1)==1&&!color)||(destiny.getID(i+1)==10&&color)||(destiny.getID(i+1)==11&&!color)))
					{
						if(destiny.getX(i+1)>auxX)
						{
							lookat='r';
							shoot=true;
						}
						else
							if(destiny.getX(i+1)<auxX)
							{
								lookat='l';
								shoot=true;
							}								
						if(destiny.getY(i+1)>auxY)
						{
							lookat='u';
							shoot=true;
						}else
							if(destiny.getY(i+1)<auxY)
							{
								lookat='d';
								shoot=true;
							}	
					}					
					WhereAmTheBoss.getMachinist().enqueue(new Word(key,shoot,lookat));
					x=destiny.getX(i);
					y=destiny.getY(i);
			}
			int distance=0;
			ArrayList<Point> red=new ArrayList<Point>();
			if(lookat=='u'&&kennedy.y>y)
			{
				for(int j=y+1;j<25;j++)
				{
					int i=x;
					if((color&&crypticmap[i][j]==1||crypticmap[i][j]==11)||(!color&&crypticmap[i][j]==2||crypticmap[i][j]==10))
						distance=0;
					if(crypticmap[i][j]==5)//si se encuentra un obstaculo en la vertical de tiro, este sera la nueva distancia
						distance=j;
				}
				if(distance>0)//si distancia es 0, no dispares
					{
						for(int i=0;i<35;i++)
							for(int j=y;j<=distance;j++)
								if( (i>=x-(int)distance/10||i<=x+(int)distance/10) )
									red.add(new Point(i,j));
						if(kennedy.x==x||(red.contains(kennedy) && ((kennedisat=='r'&&kennedy.x<x)||(kennedisat=='l'&&kennedy.x>x)) ))//si el tanque enemigo esta tentado a aproximarse a la linea roja
						{
							for(int i=0;i<=Math.ceil(distance/10);i++)
							{
								WhereAmTheBoss.getMachinist().enqueue(new Word(0, true, 'u'));
							}
						}
					}				
			}else
				if(lookat=='d'&&kennedy.y<y)
				{
					for(int j=y-1;j>=0;j--)
					{
						int i=x;
						if((color&&crypticmap[i][j]==1||crypticmap[i][j]==11)||(!color&&crypticmap[i][j]==2||crypticmap[i][j]==10))
							distance=0;
						if(crypticmap[i][j]==5)//si se encuentra un obstaculo en la vertical de tiro, este sera la nueva distancia
							distance=j;
					}
					if(distance>0)//si distancia es 0, no dispares
						{
							for(int i=0;i<35;i++)
								for(int j=y;j>=distance;j--)
									if( (i>=x-(int)distance/10||i<=x+(int)distance/10) )
										red.add(new Point(i,j));
							if(kennedy.x==x||(red.contains(kennedy) && ((kennedisat=='r'&&kennedy.x<x)||(kennedisat=='l'&&kennedy.x>x)) ))//si el tanque enemigo esta tentado a aproximarse a la linea roja
							{
								for(int i=0;i<=Math.ceil(distance/10);i++)
								{
									WhereAmTheBoss.getMachinist().enqueue(new Word(0, true, 'd'));
								}
							}
						}
					
				}else
					if(lookat=='r'&&kennedy.x>x)
					{
						for(int i=x+1;i<35;i++)
						{
							int j=y;
							if((color&&crypticmap[i][j]==1||crypticmap[i][j]==11)||(!color&&crypticmap[i][j]==2||crypticmap[i][j]==10))
								distance=0;
							if(crypticmap[i][j]==5)//si se encuentra un obstaculo en la vertical de tiro, este sera la nueva distancia
								distance=i;
						}
						if(distance>0)//si distancia es 0, no dispares
							{
								for(int i=x;i<=distance;i++)
									for(int j=0;j<25;j++)
										if( (j>=y-(int)distance/10||i<=y+(int)distance/10) )
											red.add(new Point(i,j));
								if(kennedy.x==x||(red.contains(kennedy) && ((kennedisat=='u'&&kennedy.x<x)||(kennedisat=='d'&&kennedy.x>x)) ))//si el tanque enemigo esta tentado a aproximarse a la linea roja
								{
									for(int i=0;i<=Math.ceil(distance/10);i++)
									{
										WhereAmTheBoss.getMachinist().enqueue(new Word(0, true, 'r'));
									}
								}
							}
					}else
						if(lookat=='l'&&kennedy.x<x)
						{
							for(int i=x+1;i>=0;i--)
							{
								int j=y;
								if((color&&crypticmap[i][j]==1||crypticmap[i][j]==11)||(!color&&crypticmap[i][j]==2||crypticmap[i][j]==10))
									distance=0;
								if(crypticmap[i][j]==5)//si se encuentra un obstaculo en la vertical de tiro, este sera la nueva distancia
									distance=i;
							}
							if(distance>0)//si distancia es 0, no dispares
								{
									for(int i=x;i>=distance;i++)
										for(int j=0;j>0;j++)
											if( (j>=y-(int)distance/10||i<=y+(int)distance/10) )
												red.add(new Point(i,j));
									if(kennedy.x==x||(red.contains(kennedy) && ((kennedisat=='u'&&kennedy.x<x)||(kennedisat=='d'&&kennedy.x>x)) ))//si el tanque enemigo esta tentado a aproximarse a la linea roja
									{
										for(int i=0;i<=Math.ceil(distance/10);i++)
										{
											WhereAmTheBoss.getMachinist().enqueue(new Word(0, true, 'l'));
										}
									}
								}
						}
	}
}
