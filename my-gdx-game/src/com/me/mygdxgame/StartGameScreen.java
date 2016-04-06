package com.me.mygdxgame;

/*import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class StartGameScreen extends AbstractScreen
{	
	private Texture texturesplash;
    private Image splashImage;
	
    public Stage stage2;
    public Button buttonback;
	public ArrayList<Tank> mytanks;
	public ArrayList<Tank> eviltanks;
	public ArrayList<Bullet> thebullets;
	public OrthogonalTiledMapRenderer mapRenderer;
	public General Zod;//,od; 
	public long t,b,f;
	boolean w=false;//w=true si juego termino

	//public static ServerSocket Alfred;
	//public static Socket Batman;
	
	public TiledMapTileLayer maplayer, tanklayer, bulletlayer;
	
	public StartGameScreen(MyGdxGame game/*,ServerSocket Alfred,Socket Batman*/)
	{
		super(game);
		mapRenderer = new OrthogonalTiledMapRenderer(game.mydata.map);
		
		stage2=new Stage( GAME_VIEWPORT_WIDTH, GAME_VIEWPORT_HEIGHT, true );
		mytanks=new ArrayList<Tank>();
		eviltanks=new  ArrayList<Tank>();
		thebullets=new  ArrayList<Bullet>();
		
		maplayer=(TiledMapTileLayer)mapRenderer.getMap().getLayers().get(0);
		tanklayer=(TiledMapTileLayer)mapRenderer.getMap().getLayers().get(1);
		bulletlayer=(TiledMapTileLayer)mapRenderer.getMap().getLayers().get(2);
		
		Zod=new General(maplayer, tanklayer, bulletlayer, eviltanks, mytanks , thebullets,!game.mydata.settings.Mycolor);
		//od=new General(maplayer,tanklayer, bulletlayer, eviltanks,mytanks, thebullets,!game.mydata.settings.Mycolor);
		
		long e=System.nanoTime();
		t=b=e;
		
		batch=new  SpriteBatch();
/*		this.Batman=Batman;
		/*
		if(game.mydata.settings.Mycolor)//si soy server
		{
			this.Alfred=Alfred;
			new Thread(Alfredsend).start();
			new Thread(Alfredreceive).start();
		}else
		{
			new Thread(Batmansend).start();
			new Thread(Batmanreceive).start();		
		}*/
		
	}
	@Override
	public void show()
	{
		super.show();
		Gdx.input.setInputProcessor( stage2 );
		//button mute
		Drawable buttonmuteregion;
		Drawable buttonmutedownregion;
		if(game.mydata.settings.mute)
		{
			Texture texturebuttonmuteon=new Texture(Gdx.files.internal("data/Images/Buttonmuteon.png"));
			texturebuttonmuteon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			TextureRegion regionbuttonmuteon=new TextureRegion(texturebuttonmuteon,0,0,100,104);
			buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteon);
			
			Texture texturebuttonmuteondown=new Texture(Gdx.files.internal("data/Images/Buttonmuteondown.png"));
			texturebuttonmuteondown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			TextureRegion regionbuttonmuteondown=new TextureRegion(texturebuttonmuteondown,0,0,100,104);
			buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteondown);
		}else
			{
				Texture texturebuttonmuteoff=new Texture(Gdx.files.internal("data/Images/Buttonmuteoff.png"));
				texturebuttonmuteoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
				TextureRegion regionbuttonmuteoff=new TextureRegion(texturebuttonmuteoff,0,0,100,104);
				buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteoff);
				
				Texture texturebuttonmuteoffdown=new Texture(Gdx.files.internal("data/Images/Buttonmuteoffdown.png"));
				texturebuttonmuteoffdown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
				TextureRegion regionbuttonmuteoffdown=new TextureRegion(texturebuttonmuteoffdown,0,0,100,104);
				buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteoffdown);
			}
		
		ButtonStyle buttonmutestyle=new ButtonStyle(buttonmuteregion, buttonmutedownregion, buttonmuteregion);
		final Button buttonmute=new Button(buttonmutestyle);
		buttonmute.setBounds(stage.getWidth()-100 , 0,100/2,104/2);
		buttonmute.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {return true;}
			public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				if(buttonmute.isChecked())
				{					
					ButtonStyle buttonstyle;
					if(game.mydata.settings.mute)//si el sonido esta mudo, se va a activar
					{
						game.mydata.nowplaying.setVolume(game.mydata.settings.olM);
						game.mydata.settings.MusicV=game.mydata.settings.olM;
						game.mydata.settings.SoundV=game.mydata.settings.olS;
						game.mydata.settings.mute=!(game.mydata.settings.mute);
						
						Texture texturebuttonmuteoff=new Texture(Gdx.files.internal("data/Images/Buttonmuteoff.png"));
						texturebuttonmuteoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
						TextureRegion regionbuttonmuteoff=new TextureRegion(texturebuttonmuteoff,0,0,100,104);
						Drawable buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteoff);
						
						Texture texturebuttonmuteondown=new Texture(Gdx.files.internal("data/Images/Buttonmuteoffdown.png"));
						texturebuttonmuteondown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
						TextureRegion regionbuttonmuteondown=new TextureRegion(texturebuttonmuteondown,0,0,100,104);
						Drawable buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteondown);
						
						buttonstyle=new ButtonStyle(buttonmuteregion, buttonmutedownregion, buttonmuteregion);
						buttonmute.setChecked(false);
						
					}else//sino, se va a desactivar el mute
					{
						game.mydata.settings.olM=game.mydata.settings.MusicV;
						game.mydata.settings.olS=game.mydata.settings.SoundV;
						game.mydata.settings.MusicV=0;
						game.mydata.settings.SoundV=0;
						game.mydata.settings.mute=!(game.mydata.settings.mute);
						
						Texture texturebuttonmuteoff=new Texture(Gdx.files.internal("data/Images/Buttonmuteon.png"));
						texturebuttonmuteoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
						TextureRegion regionbuttonmuteoff=new TextureRegion(texturebuttonmuteoff,0,0,100,104);
						Drawable buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteoff);
						
						Texture texturebuttonmuteoffdown=new Texture(Gdx.files.internal("data/Images/Buttonmuteondown.png"));
						texturebuttonmuteoffdown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
						TextureRegion regionbuttonmuteoffdown=new TextureRegion(texturebuttonmuteoffdown,0,0,100,104);
						Drawable buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteoffdown);
						
						buttonstyle=new ButtonStyle(buttonmuteregion, buttonmutedownregion, buttonmuteregion);
						buttonmute.setChecked(false);
					}
					buttonmute.setStyle(buttonstyle);
					game.mydata.soundclick.play(game.mydata.settings.SoundV);
					game.mydata.nowplaying.setVolume(game.mydata.settings.MusicV);
				}
		    } 
		});
		
		
		//button back
		Texture texturebuttonback=new Texture(Gdx.files.internal("data/Images/Buttonback.png"));
		texturebuttonback.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonback=new TextureRegion(texturebuttonback,0,0,100,104);
		Drawable buttonbackregion=new TextureRegionDrawable(regionbuttonback);
		
		Texture texturebuttonbackchecked=new Texture(Gdx.files.internal("data/Images/Buttonbackchecked.png"));
		texturebuttonbackchecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonbackchecked=new TextureRegion(texturebuttonbackchecked,0,0,100,104);
		Drawable buttonbackcheckedregion=new TextureRegionDrawable(regionbuttonbackchecked);
		
		ButtonStyle buttonbackstyle=new ButtonStyle(buttonbackregion, buttonbackcheckedregion, buttonbackcheckedregion);
		buttonback=new Button(buttonbackstyle);
		buttonback.setBounds(stage.getWidth()-1250 , 0,100/2,104/2);
		buttonback.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {return true;}
			public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				if(buttonback.isChecked())
				{
					game.mydata.soundclick.play(game.mydata.settings.SoundV);
					game.setScreen( new MenuScreen( game ) );//cambiar a pantalla preferencias
				}
		    } 
		});
		//button next
		/*for(int i=0;i<50;i++)
		{
		Texture texturebuttonnext=new Texture(Gdx.files.internal("data/Images/Buttonnext.png"));
		texturebuttonnext.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonnext=new TextureRegion(texturebuttonnext,0,0,100,104);
		Drawable buttonnextregion=new TextureRegionDrawable(regionbuttonnext);
		
		Texture texturebuttonnextchecked=new Texture(Gdx.files.internal("data/Images/Buttonnextchecked.png"));
		texturebuttonnextchecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonnextchecked=new TextureRegion(texturebuttonnextchecked,0,0,100,104);
		Drawable buttonnextcheckedregion=new TextureRegionDrawable(regionbuttonnextchecked);
		
		ButtonStyle buttonnextstyle=new ButtonStyle(buttonnextregion, buttonnextcheckedregion, buttonnextcheckedregion);
		Button buttonnext=new Button(buttonnextstyle);
		buttonnext.setBounds(50*i , 50*1,100/2,104/2);
		buttonnext.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {return true;}
			public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				if(buttonback.isChecked())
				{
					game.mydata.soundclick.play(game.mydata.settings.SoundV);
					game.setScreen( new MenuScreen( game ) );//cambiar a pantalla preferencias
				}
		    } 
		});stage2.addActor(buttonnext);

		}*/
		stage2.addActor(buttonback);
		stage2.addActor(buttonmute);

	}
	
	
	public void movingtanks(long e,ArrayList<Tank> dalist)
	{
		int k;
		boolean s;
		char lookat;
		Word Orders;
		for(Tank dumb: dalist)
		{
			int X=dumb.getX(),Y=dumb.getY();
			if(dumb.Cockpit.getMachinist().isEmpty())
			{
				k=0;
				s=false;
				lookat=dumb.getLookat();
			}else
				{
					Orders=dumb.Cockpit.getorders();
					k=Orders.getKey();
					s=Orders.getShoot();
					lookat=Orders.getLookat();
				}
			
			if(dumb.getFuel()>0)		
				if(k==Keys.UP)
				{
					if(dumb.getFuel()==1)
						game.mydata.nogas.play(game.mydata.settings.SoundV);
					if(Y<24&&tanklayer.getCell(X, Y+1)==null)
					{
						if(maplayer.getCell(X,Y+1)==null)
						{
							tanklayer.setCell(X,Y+1,dumb.myself);		
							tanklayer.setCell(X,Y,null);
							dumb.setnowon(maplayer.getCell(X, Y+1));
							dumb.setposition(X,Y+1);
						}else
							if(maplayer.getCell(X, Y+1).getTile().getId()==7)//bosque
							{
								game.mydata.deadleaf.play(game.mydata.settings.SoundV);
								tanklayer.setCell(X,Y+1,null);		
								tanklayer.setCell(X,Y,null);
								dumb.setnowon(maplayer.getCell(X, Y+1));
								dumb.setposition(X,Y+1);
							}else
								if(maplayer.getCell(X,Y+1).getTile().getId()==3)//agua
								{
									if(dumb.getFuel()==2)
										game.mydata.nogas.play(game.mydata.settings.SoundV);
									else
									game.mydata.puddle.play(game.mydata.settings.SoundV);
									tanklayer.setCell(X,Y+1,dumb.myself);		
									tanklayer.setCell(X,Y,null);
									dumb.setnowon(maplayer.getCell(X, Y+1));
									dumb.setposition(X,Y+1);
								}else
									if(maplayer.getCell(X, Y+1).getTile().getId()==8)//gas
									{
										game.mydata.gas.play(game.mydata.settings.SoundV);
										tanklayer.setCell(X,Y+1,dumb.myself);		
										tanklayer.setCell(X,Y,null);
										maplayer.setCell(X,Y+1,null);
										dumb.setFuel(Tank.stomach);
										dumb.setnowon(maplayer.getCell(X,Y+1));
										dumb.setposition(X,Y+1);
										Zod.gasconsumed(X,Y+1);
										//od.gasconsumed(X,Y+1);
									}else
										if(maplayer.getCell(X, Y+1).getTile().getId()==9)//pila
										{
											game.mydata.recharge.play(game.mydata.settings.SoundV);
											tanklayer.setCell(X,Y+1,dumb.myself);		
											tanklayer.setCell(X,Y,null);
											maplayer.setCell(X,Y+1,null);
											dumb.setBullets(Tank.pileofbullets);
											dumb.setnowon(maplayer.getCell(X,Y+1));
											dumb.setposition(X,Y+1);
											Zod.bulletconsumed(X, Y+1);
											//od.bulletconsumed(X, Y+1);
										}					
					}
				}else
					if(k==Keys.DOWN)
					{
						if(dumb.getFuel()==1)
							game.mydata.nogas.play(game.mydata.settings.SoundV);
						if(Y>0&&tanklayer.getCell(X, Y-1)==null)
						{
							if(maplayer.getCell(X,Y-1)==null)
							{
								tanklayer.setCell(X,Y-1,dumb.myself);		
								tanklayer.setCell(X,Y,null);
								dumb.setnowon(maplayer.getCell(X, Y-1));
								dumb.setposition(X,Y-1);
							}else
								if(maplayer.getCell(X, Y-1).getTile().getId()==7)//bosque
								{
									game.mydata.deadleaf.play(game.mydata.settings.SoundV);
									tanklayer.setCell(X,Y-1,null);		
									tanklayer.setCell(X,Y,null);
									dumb.setnowon(maplayer.getCell(X, Y-1));
									dumb.setposition(X,Y-1);
								}else
									if(maplayer.getCell(X, Y-1).getTile().getId()==3)//agua
									{
										if(dumb.getFuel()==2)
											game.mydata.nogas.play(game.mydata.settings.SoundV);
										else
										game.mydata.puddle.play(game.mydata.settings.SoundV);
										tanklayer.setCell(X,Y-1,dumb.myself);		
										tanklayer.setCell(X,Y,null);
										dumb.setnowon(maplayer.getCell(X, Y-1));
										dumb.setposition(X,Y-1);
									}else
										if(maplayer.getCell(X, Y-1).getTile().getId()==8)//gas
										{
											game.mydata.gas.play(game.mydata.settings.SoundV);
											tanklayer.setCell(X,Y-1,dumb.myself);		
											tanklayer.setCell(X,Y,null);
											maplayer.setCell(X, Y-1,null);
											dumb.setFuel(Tank.stomach);
											dumb.setnowon(maplayer.getCell(X, Y-1));
											dumb.setposition(X,Y-1);
											Zod.gasconsumed(X,Y-1);
											//od.gasconsumed(X,Y-1);
										}else
											if(maplayer.getCell(X, Y-1).getTile().getId()==9)//pila
											{
												game.mydata.recharge.play(game.mydata.settings.SoundV);
												tanklayer.setCell(X,Y-1,dumb.myself);		
												tanklayer.setCell(X,Y,null);
												maplayer.setCell(X, Y-1,null);
												dumb.setBullets(Tank.pileofbullets);
												dumb.setnowon(maplayer.getCell(X, Y-1));
												dumb.setposition(X,Y-1);
												Zod.bulletconsumed(X, Y-1);
												//od.bulletconsumed(X, Y+1);
											}
						}
					}else
						if(k==Keys.LEFT)
						{
							if(dumb.getFuel()==1)
								game.mydata.nogas.play(game.mydata.settings.SoundV);
							if(X>0&&tanklayer.getCell(X-1, Y)==null)
							{
								if(maplayer.getCell(X-1,Y)==null)
								{
									tanklayer.setCell(X-1,Y,dumb.myself);		
									tanklayer.setCell(X,Y,null);
									dumb.setnowon(maplayer.getCell(X-1, Y));
									dumb.setposition(X-1,Y);
								}else
									if(maplayer.getCell(X-1, Y).getTile().getId()==7)//bosque
									{
										game.mydata.deadleaf.play(game.mydata.settings.SoundV);
										tanklayer.setCell(X-1,Y,null);		
										tanklayer.setCell(X,Y,null);
										dumb.setnowon(maplayer.getCell(X-1, Y));
										dumb.setposition(X-1,Y);
									}else
										if(maplayer.getCell(X-1, Y).getTile().getId()==3)//agua
										{
											if(dumb.getFuel()==2)
												game.mydata.nogas.play(game.mydata.settings.SoundV);
											else
											game.mydata.puddle.play(game.mydata.settings.SoundV);
											tanklayer.setCell(X-1,Y,dumb.myself);		
											tanklayer.setCell(X,Y,null);
											dumb.setnowon(maplayer.getCell(X-1, Y));
											dumb.setposition(X-1,Y);
										}else
											if(maplayer.getCell(X-1, Y).getTile().getId()==8)//gas
											{
												game.mydata.gas.play(game.mydata.settings.SoundV);
												tanklayer.setCell(X-1,Y,dumb.myself);		
												tanklayer.setCell(X,Y,null);
												maplayer.setCell(X-1, Y,null);
												dumb.setFuel(Tank.stomach);
												dumb.setnowon(maplayer.getCell(X-1, Y));
												dumb.setposition(X-1,Y);
												Zod.gasconsumed(X-1,Y);
												//od.gasconsumed(X-1,Y);
											}else
												if(maplayer.getCell(X-1, Y).getTile().getId()==9)//pila
												{
													game.mydata.recharge.play(game.mydata.settings.SoundV);
													tanklayer.setCell(X-1,Y,dumb.myself);		
													tanklayer.setCell(X,Y,null);
													maplayer.setCell(X-1, Y,null);
													dumb.setBullets(Tank.pileofbullets);
													dumb.setnowon(maplayer.getCell(X-1, Y));
													dumb.setposition(X-1,Y);
													Zod.bulletconsumed(X-1, Y);
													//od.bulletconsumed(X-1, Y);
												}
							}
						}else
							if(k==Keys.RIGHT)
							{
								if(dumb.getFuel()==1)
									game.mydata.nogas.play(game.mydata.settings.SoundV);
								if(X<34&&tanklayer.getCell(X+1, Y)==null)
								{
									if(maplayer.getCell(X+1,Y)==null)
									{
										tanklayer.setCell(X+1,Y,dumb.myself);		
										tanklayer.setCell(X,Y,null);
										dumb.setnowon(maplayer.getCell(X+1, Y));
										dumb.setposition(X+1,Y);
									}else
										if(maplayer.getCell(X+1, Y).getTile().getId()==7)//bosque
										{
											game.mydata.deadleaf.play(game.mydata.settings.SoundV);
											tanklayer.setCell(X+1,Y,null);		
											tanklayer.setCell(X,Y,null);
											dumb.setnowon(maplayer.getCell(X+1, Y));
											dumb.setposition(X+1,Y);
										}else
											if(maplayer.getCell(X+1, Y).getTile().getId()==3)//agua
											{
												if(dumb.getFuel()==2)
													game.mydata.nogas.play(game.mydata.settings.SoundV);
												else
												game.mydata.puddle.play(game.mydata.settings.SoundV);
												tanklayer.setCell(X+1,Y,dumb.myself);		
												tanklayer.setCell(X,Y,null);
												dumb.setnowon(maplayer.getCell(X+1, Y));
												dumb.setposition(X+1,Y);
											}else
												if(maplayer.getCell(X+1, Y).getTile().getId()==8)//gas
												{
													game.mydata.gas.play(game.mydata.settings.SoundV);
													tanklayer.setCell(X+1,Y,dumb.myself);		
													tanklayer.setCell(X,Y,null);
													maplayer.setCell(X+1, Y,null);
													dumb.setFuel(Tank.stomach);
													dumb.setnowon(maplayer.getCell(X+1, Y));
													dumb.setposition(X+1,Y);
													Zod.gasconsumed(X+1,Y);
													//od.gasconsumed(X+1,Y);
												}else
													if(maplayer.getCell(X+1, Y).getTile().getId()==9)//pila
													{
														game.mydata.recharge.play(game.mydata.settings.SoundV);
														tanklayer.setCell(X+1,Y,dumb.myself);		
														tanklayer.setCell(X,Y,null);
														maplayer.setCell(X+1, Y,null);
														dumb.setBullets(Tank.pileofbullets);
														dumb.setnowon(maplayer.getCell(X+1, Y));
														dumb.setposition(X+1,Y);
														Zod.bulletconsumed(X+1, Y);
														//od.bulletconsumed(X+1, Y);
													}
								}
							}
			//rotating
			dumb.setLookat(lookat);
			X=dumb.getX();
			Y=dumb.getY();
			if(lookat=='u'&&tanklayer.getCell(X,Y)!=null)
			{
				tanklayer.getCell(X, Y).setRotation(0);
				tanklayer.getCell(X, Y).setFlipVertically(false);
			}else
				if(lookat=='d'&&tanklayer.getCell(X,Y)!=null)
				{
					tanklayer.getCell(X, Y).setRotation(0);
					tanklayer.getCell(X, Y).setFlipVertically(true);
				}
				else
					if(lookat=='l'&&tanklayer.getCell(X,Y)!=null)
					{
						tanklayer.getCell(X, Y).setFlipVertically(false);
						tanklayer.getCell(X, Y).setRotation(1);
					}else
						if(lookat=='r'&&tanklayer.getCell(X,Y)!=null)
						{
							tanklayer.getCell(X, Y).setFlipVertically(true);
							tanklayer.getCell(X, Y).setRotation(1);
						}	
			//shooting
			if(s&&dumb.getBullets()>0)
			{
				game.mydata.shoot.play(game.mydata.settings.SoundV);
				dumb.setBullets(dumb.getBullets()-1);
				createbullet(X,Y,lookat);
			}					
		}
	}	
	public void createbullet(int X,int Y,char lookat)
	{
		Bullet b=new Bullet();
		if(lookat=='u')
		{
			b=new Bullet(X, Y, 'u');
		}else
			if(lookat=='d')
			{
				b=new Bullet(X, Y, 'd');
			}else
				if(lookat=='l')
				{
					b=new Bullet(X, Y, 'l');
				}else
					{
						b=new Bullet(X, Y, 'r');
					}
		thebullets.add(b);
	}
	public void movingbullets()
	{
			for(Bullet bit : thebullets)
			{
				int X=bit.X,Y=bit.Y;
				//if(bit.live)//????
					if(bit.itgoto=='u')
					{
						Cell myself=new Cell();
						myself.setTile(mapRenderer.getMap().getTileSets().getTile(6));
						//comprobar borde
						if(Y==24)
						{
							bit.live=false;
							bulletlayer.setCell(X, Y, null);
						}
						else
						{
							if(bit.live)
							//comprobar capa tank
							if(tanklayer.getCell(X, Y+1)!=null)
								if(tanklayer.getCell(X, Y+1).getTile().getId()==1||tanklayer.getCell(X, Y+1).getTile().getId()==2)
								{
									if(tanklayer.getCell(X, Y+1).getTile().getId()==1)
									{
										if(game.mydata.settings.Mycolor)
											for(Tank dumb:mytanks)
											{
												if(dumb.getX()==X&&dumb.getY()==Y+1)
												{
													tanklayer.setCell(X, Y+1, null);
													bulletlayer.setCell(X, Y, null);
													bit.live=false;
													mytanks.remove(dumb);
													game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
													break;
												}
											}
										else
										{
											for(Tank dumb:eviltanks)
											{
												if(dumb.getX()==X&&dumb.getY()==Y+1)
												{
													tanklayer.setCell(X, Y+1, null);
													bulletlayer.setCell(X, Y, null);
													bit.live=false;										
													eviltanks.remove(dumb);
													game.mydata.breaked.play(game.mydata.settings.SoundV);
													break;
												}
											}
										}
									}else
									{
										if(!game.mydata.settings.Mycolor)
											for(Tank dumb:mytanks)
											{
												if(dumb.getX()==X&&dumb.getY()==Y+1)
												{
													tanklayer.setCell(X, Y+1, null);
													bulletlayer.setCell(X, Y, null);
													bit.live=false;
													
													game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
													
													mytanks.remove(dumb);
													break;
												}
											}
										else
										{
											for(Tank dumb:eviltanks)
											{
												if(dumb.getX()==X&&dumb.getY()==Y+1)
												{
													tanklayer.setCell(X, Y+1, null);
													bulletlayer.setCell(X, Y, null);
													bit.live=false;										
													eviltanks.remove(dumb);
													game.mydata.breaked.play(game.mydata.settings.SoundV);
													break;
												}
											}
										}
									}
								}
							//comprobar capa mapa
							if(bit.live)
							if(maplayer.getCell(X,Y+1)==null||maplayer.getCell(X,Y+1).getTile().getId()==3||maplayer.getCell(X,Y+1).getTile().getId()==8||maplayer.getCell(X,Y+1).getTile().getId()==9)
							{
								bulletlayer.setCell(X, Y+1, myself);
								bulletlayer.setCell(X, Y, null);
								bit.Y++;
							}else//en caso de bosque
								if(maplayer.getCell(X,Y+1).getTile().getId()==7)
								{
									bulletlayer.setCell(X, Y+1, null);
									bulletlayer.setCell(X, Y, null);
									bit.Y++;
								}else//en caso de choque
									{
										if(maplayer.getCell(X,Y+1).getTile().getId()==5)
										{
											game.mydata.hit.play(game.mydata.settings.SoundV);
											bulletlayer.setCell(X, Y, null);
										}else
											if(maplayer.getCell(X,Y+1).getTile().getId()==4)
											{
												game.mydata.breaked.play(game.mydata.settings.SoundV);
												maplayer.setCell(X, Y+1, null);
												bulletlayer.setCell(X, Y, null);
											}else
												if(maplayer.getCell(X,Y+1).getTile().getId()==10)
												{
													if(game.mydata.settings.Mycolor)//ganar
													{
														win();
													}else//perder
													{
														loose();
													}
												}else
													if(maplayer.getCell(X,Y+1).getTile().getId()==11)
													{
														if(!game.mydata.settings.Mycolor)//ganar
														{
															win();
														}else//perder
														{
															loose();
														}
													}
										bit.live=false;
									}
						}
						
					}else
						if(bit.itgoto=='d')
						{
							Cell myself=new Cell();
							myself.setTile(mapRenderer.getMap().getTileSets().getTile(6));
							myself.setFlipVertically(true);
							//comprobar borde
							if(Y==0)
							{
								bit.live=false;
								bulletlayer.setCell(X, Y, null);
							}
							else
							{
								//comprobar capa tank
								if(bit.live)
								if(tanklayer.getCell(X, Y-1)!=null)
									if(tanklayer.getCell(X, Y-1).getTile().getId()==1||tanklayer.getCell(X, Y-1).getTile().getId()==2)
									{
										if(tanklayer.getCell(X, Y-1).getTile().getId()==1)
										{
											if(game.mydata.settings.Mycolor)
												for(Tank dumb:mytanks)
												{
													if(dumb.getX()==X&&dumb.getY()==Y-1)
													{
														tanklayer.setCell(X, Y-1, null);
														bulletlayer.setCell(X, Y, null);
														game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
														bit.live=false;										
														mytanks.remove(dumb);
														break;
													}
												}
											else
											{
												for(Tank dumb:eviltanks)
												{
													if(dumb.getX()==X&&dumb.getY()==Y-1)
													{
														tanklayer.setCell(X, Y-1, null);
														bulletlayer.setCell(X, Y, null);
														game.mydata.breaked.play(game.mydata.settings.SoundV);
														bit.live=false;										
														eviltanks.remove(dumb);
														break;
													}
												}
											}
										}else
										{
											if(!game.mydata.settings.Mycolor)
												for(Tank dumb:mytanks)
												{
													if(dumb.getX()==X&&dumb.getY()==Y-1)
													{
														tanklayer.setCell(X, Y-1, null);
														bulletlayer.setCell(X, Y, null);
														game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
														bit.live=false;										
														mytanks.remove(dumb);
														break;
													}
												}
											else
											{
												for(Tank dumb:eviltanks)
												{
													if(dumb.getX()==X&&dumb.getY()==Y-1)
													{
														tanklayer.setCell(X, Y-1, null);
														bulletlayer.setCell(X, Y, null);
														game.mydata.breaked.play(game.mydata.settings.SoundV);
														bit.live=false;										
														eviltanks.remove(dumb);
														break;
													}
												}
											}
										}
									}
								//comprobar capa mapa
								if(bit.live)
								if(maplayer.getCell(X,Y-1)==null||maplayer.getCell(X,Y-1).getTile().getId()==3||maplayer.getCell(X,Y-1).getTile().getId()==8||maplayer.getCell(X,Y-1).getTile().getId()==9)
								{
									bulletlayer.setCell(X, Y-1, myself);
									bulletlayer.setCell(X, Y, null);
									bit.Y--;
								}else//en caso de bosque
									if(maplayer.getCell(X,Y-1).getTile().getId()==7)
									{
										bulletlayer.setCell(X, Y-1, null);
										bulletlayer.setCell(X, Y, null);
										bit.Y--;
									}else//en caso de choque
										{
											if(maplayer.getCell(X,Y-1).getTile().getId()==5)
											{
												game.mydata.hit.play(game.mydata.settings.SoundV);
												bulletlayer.setCell(X, Y, null);
											}else
												if(maplayer.getCell(X,Y-1).getTile().getId()==4)
												{
													game.mydata.breaked.play(game.mydata.settings.SoundV);
													maplayer.setCell(X, Y-1, null);
													bulletlayer.setCell(X, Y, null);
												}else
													if(maplayer.getCell(X,Y-1).getTile().getId()==10)
													{
														if(game.mydata.settings.Mycolor)//ganar
														{
															win();
														}else//perder
														{
															loose();
														}
													}else
														if(maplayer.getCell(X,Y-1).getTile().getId()==11)
														{
															if(!game.mydata.settings.Mycolor)//ganar
															{
																win();
															}else//perder
															{
																loose();
															}
														}
											bit.live=false;
										}	
							}
							
						}else
							if(bit.itgoto=='l')
							{
								Cell myself=new Cell();
								myself.setTile(mapRenderer.getMap().getTileSets().getTile(6));
								myself.setRotation(1);
								//comprobar borde
								if(X==0)
								{
									bit.live=false;
									bulletlayer.setCell(X, Y, null);
								}
								else
								{
									//comprobar capa tank
									if(bit.live)
									if(tanklayer.getCell(X-1, Y)!=null)
										if(tanklayer.getCell(X-1, Y).getTile().getId()==1||tanklayer.getCell(X-1, Y).getTile().getId()==2)
										{
											if(tanklayer.getCell(X-1, Y).getTile().getId()==1)
											{
												if(game.mydata.settings.Mycolor)
													for(Tank dumb:mytanks)
													{
														if(dumb.getX()==X-1&&dumb.getY()==Y)
														{
															tanklayer.setCell(X-1, Y, null);
															bulletlayer.setCell(X, Y, null);
															game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
															bit.live=false;										
															mytanks.remove(dumb);
															break;
														}
													}
												else
												{
													for(Tank dumb:eviltanks)
													{
														if(dumb.getX()==X-1&&dumb.getY()==Y)
														{
															tanklayer.setCell(X-1, Y, null);
															bulletlayer.setCell(X, Y, null);
															game.mydata.breaked.play(game.mydata.settings.SoundV);
															bit.live=false;										
															eviltanks.remove(dumb);
															break;
														}
													}
												}
											}else
											{
												if(!game.mydata.settings.Mycolor)
													for(Tank dumb:mytanks)
													{
														if(dumb.getX()==X-1&&dumb.getY()==Y)
														{
															tanklayer.setCell(X-1, Y, null);
															bulletlayer.setCell(X, Y, null);
															game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
															bit.live=false;										
															mytanks.remove(dumb);
															break;
														}
													}
												else
												{
													for(Tank dumb:eviltanks)
													{
														if(dumb.getX()==X-1&&dumb.getY()==Y)
														{
															tanklayer.setCell(X-1, Y, null);
															bulletlayer.setCell(X, Y, null);
															game.mydata.breaked.play(game.mydata.settings.SoundV);
															bit.live=false;										
															eviltanks.remove(dumb);
															break;
														}
													}
												}
											}
										}
									//comprobar capa mapa
									if(bit.live)
									if(maplayer.getCell(X-1, Y)==null||maplayer.getCell(X-1, Y).getTile().getId()==3||maplayer.getCell(X-1, Y).getTile().getId()==8||maplayer.getCell(X-1, Y).getTile().getId()==9)
									{
										bulletlayer.setCell(X-1, Y, myself);
										bulletlayer.setCell(X, Y, null);
										bit.X--;
									}else//en caso de bosque
										if(maplayer.getCell(X-1, Y).getTile().getId()==7)
										{
											bulletlayer.setCell(X-1, Y, null);
											bulletlayer.setCell(X, Y, null);
											bit.X--;
										}else//en caso de choque
											{
												if(maplayer.getCell(X-1, Y).getTile().getId()==5)
												{
													game.mydata.hit.play(game.mydata.settings.SoundV);
													bulletlayer.setCell(X, Y, null);
												}else
													if(maplayer.getCell(X-1, Y).getTile().getId()==4)
													{
														game.mydata.breaked.play(game.mydata.settings.SoundV);
														maplayer.setCell(X-1, Y, null);
														bulletlayer.setCell(X, Y, null);
													}else
														if(maplayer.getCell(X-1, Y).getTile().getId()==10)
														{
															if(game.mydata.settings.Mycolor)//ganar
															{
																win();
															}else//perder
															{
																loose();
															}
														}else
															if(maplayer.getCell(X-1, Y).getTile().getId()==11)
															{
																if(!game.mydata.settings.Mycolor)//ganar
																{
																	win();
																}else//perder
																{
																	loose();
																}
															}
												bit.live=false;
											}	
								}
							}else
								if(bit.itgoto=='r')
								{
									Cell myself=new Cell();
									myself.setTile(mapRenderer.getMap().getTileSets().getTile(6));
									myself.setFlipVertically(true);
									myself.setRotation(1);
									//comprobar borde
									if(X==34)
									{
										bit.live=false;
										bulletlayer.setCell(X, Y, null);
									}
									else
									{
										//comprobar capa tank
										if(bit.live)
										if(tanklayer.getCell(X+1, Y)!=null)
											if(tanklayer.getCell(X+1, Y).getTile().getId()==1||tanklayer.getCell(X+1, Y).getTile().getId()==2)
											{
												if(tanklayer.getCell(X+1, Y).getTile().getId()==1)
												{
													if(game.mydata.settings.Mycolor)
														for(Tank dumb:mytanks)
														{
															if(dumb.getX()==X+1&&dumb.getY()==Y)
															{
																tanklayer.setCell(X+1, Y, null);
																bulletlayer.setCell(X, Y, null);
																game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
																bit.live=false;										
																mytanks.remove(dumb);
																break;
															}
														}
													else
													{
														for(Tank dumb:eviltanks)
														{
															if(dumb.getX()==X+1&&dumb.getY()==Y)
															{
																tanklayer.setCell(X+1, Y, null);
																bulletlayer.setCell(X, Y, null);
																game.mydata.breaked.play(game.mydata.settings.SoundV);
																bit.live=false;										
																eviltanks.remove(dumb);
																break;
															}
														}
													}
												}else
												{
													if(!game.mydata.settings.Mycolor)
														for(Tank dumb:mytanks)
														{
															if(dumb.getX()==X+1&&dumb.getY()==Y)
															{
																tanklayer.setCell(X+1, Y, null);
																bulletlayer.setCell(X, Y, null);
																game.mydata.goodbyefriend.play(game.mydata.settings.SoundV);
																bit.live=false;										
																mytanks.remove(dumb);
																break;
															}
														}
													else
													{
														for(Tank dumb:eviltanks)
														{
															if(dumb.getX()==X+1&&dumb.getY()==Y)
															{
																tanklayer.setCell(X+1, Y, null);
																bulletlayer.setCell(X, Y, null);
																game.mydata.breaked.play(game.mydata.settings.SoundV);
																bit.live=false;										
																eviltanks.remove(dumb);
																break;
															}
														}
													}
												}
											}
										//comprobar capa mapa
											//avanzar normalmente
										if(bit.live)
										if(maplayer.getCell(X+1, Y)==null||maplayer.getCell(X+1, Y).getTile().getId()==3||maplayer.getCell(X+1, Y).getTile().getId()==8||maplayer.getCell(X+1, Y).getTile().getId()==9)
										{
											bulletlayer.setCell(X+1, Y, myself);
											bulletlayer.setCell(X, Y, null);
											bit.X++;
										}else//en caso de bosque
											if(maplayer.getCell(X+1, Y).getTile().getId()==7)
											{
												bulletlayer.setCell(X+1, Y, null);
												bulletlayer.setCell(X, Y, null);
												bit.X++;
											}else//en caso de choque
												{
													if(maplayer.getCell(X+1, Y).getTile().getId()==5)
													{
														game.mydata.hit.play(game.mydata.settings.SoundV);
														bulletlayer.setCell(X, Y, null);
													}else
														if(maplayer.getCell(X+1, Y).getTile().getId()==4)
														{
															game.mydata.breaked.play(game.mydata.settings.SoundV);
															maplayer.setCell(X+1, Y, null);
															bulletlayer.setCell(X, Y, null);
														}else
															if(maplayer.getCell(X+1, Y).getTile().getId()==10)
															{
																if(game.mydata.settings.Mycolor)//ganar
																{
																	win();
																	
																}else//perder
																{
																	loose();
																}
															}else
																if(maplayer.getCell(X+1, Y).getTile().getId()==11)
																{
																	if(!game.mydata.settings.Mycolor)//ganar
																	{
																		win();
																	}else//perder
																	{
																		loose();
																	}
																}
													bit.live=false;
												}	
									}
							}
			}
			
			for(Bullet bit:thebullets)
			{
				if(bit!=null&&!bit.live)
				{
					thebullets.remove(bit);
					break;
				}
			}
	}

	public boolean checkifiwin()//en caso de varios tanques
	{
		if(eviltanks.size()==0)
			return true;
		/*int k=0;
		for(Tank dumb:eviltanks)
			if(dumb.fuel<=0)
				k++;
		if(k==eviltanks.size())
			return true;*/	
		return false;
	}
	public boolean checkifiloose()//en caso de varios tanques
	{
		if(mytanks.size()==0)
			return true;
		/*int k=0;
		for(Tank dumb:mytanks)
			if(dumb.fuel<=0)
				k++;
		if(k==mytanks.size())
			return true;*/
		return false;
	}
	public void movingtanks(long e,TiledMapTileLayer maplayer, TiledMapTileLayer tanklayer,int k,int s,ArrayList<Tank> dalist)
	{
		for(Tank dumb: dalist)
		{
		if(e>=t+(Tank.slow))
		{
			if(k==Keys.UP&&dumb.fuel>0)
			{
				if(dumb.fuel==1)
					game.mydata.nogas.play(game.mydata.settings.SoundV);
				dumb.lookat='u';
				if(dumb.Y<24&&tanklayer.getCell(dumb.X, dumb.Y+1)==null)
				{
					if(maplayer.getCell(dumb.X,dumb.Y+1)==null)
					{
						
						tanklayer.setCell(dumb.X,dumb.Y+1,dumb.myself);		
						tanklayer.setCell(dumb.X,dumb.Y,null);
						dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y+1));
						dumb.setposition(dumb.X,dumb.Y+1);
					}else
						if(maplayer.getCell(dumb.X, dumb.Y+1).getTile().getId()==7)//bosque
						{
							game.mydata.deadleaf.play(game.mydata.settings.SoundV);
							tanklayer.setCell(dumb.X,dumb.Y+1,null);		
							tanklayer.setCell(dumb.X,dumb.Y,null);
							dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y+1));
							dumb.setposition(dumb.X,dumb.Y+1);
						}else
							if(maplayer.getCell(dumb.X, dumb.Y+1).getTile().getId()==3)//agua
							{
								if(dumb.fuel==2)
									game.mydata.nogas.play(game.mydata.settings.SoundV);
								else
								game.mydata.puddle.play(game.mydata.settings.SoundV);
								tanklayer.setCell(dumb.X,dumb.Y+1,dumb.myself);		
								tanklayer.setCell(dumb.X,dumb.Y,null);
								dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y+1));
								dumb.setposition(dumb.X,dumb.Y+1);
							}else
								if(maplayer.getCell(dumb.X, dumb.Y+1).getTile().getId()==8)//gas
								{
									game.mydata.gas.play(game.mydata.settings.SoundV);
									tanklayer.setCell(dumb.X,dumb.Y+1,dumb.myself);		
									tanklayer.setCell(dumb.X,dumb.Y,null);
									maplayer.setCell(dumb.X, dumb.Y+1,null);
									dumb.fuel=Tank.stomach;
									dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y+1));
									dumb.setposition(dumb.X,dumb.Y+1);
								}else
									if(maplayer.getCell(dumb.X, dumb.Y+1).getTile().getId()==9)//pila
									{
										game.mydata.recharge.play(game.mydata.settings.SoundV);
										tanklayer.setCell(dumb.X,dumb.Y+1,dumb.myself);		
										tanklayer.setCell(dumb.X,dumb.Y,null);
										maplayer.setCell(dumb.X, dumb.Y+1,null);
										dumb.bullets=Tank.pileofbullets;
										dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y+1));
										dumb.setposition(dumb.X,dumb.Y+1);
									}					
				}
			}else
				if(k==Keys.DOWN&&dumb.fuel>0)
				{
					if(dumb.fuel==1)
						game.mydata.nogas.play(game.mydata.settings.SoundV);
					dumb.lookat='d';
					if(dumb.Y>0&&tanklayer.getCell(dumb.X, dumb.Y-1)==null)
					{
						if(maplayer.getCell(dumb.X,dumb.Y-1)==null)
						{
							tanklayer.setCell(dumb.X,dumb.Y-1,dumb.myself);		
							tanklayer.setCell(dumb.X,dumb.Y,null);
							dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y-1));
							dumb.setposition(dumb.X,dumb.Y-1);
						}else
							if(maplayer.getCell(dumb.X, dumb.Y-1).getTile().getId()==7)//bosque
							{
								game.mydata.deadleaf.play(game.mydata.settings.SoundV);
								tanklayer.setCell(dumb.X,dumb.Y-1,null);		
								tanklayer.setCell(dumb.X,dumb.Y,null);
								dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y-1));
								dumb.setposition(dumb.X,dumb.Y-1);
							}else
								if(maplayer.getCell(dumb.X, dumb.Y-1).getTile().getId()==3)//agua
								{
									if(dumb.fuel==2)
										game.mydata.nogas.play(game.mydata.settings.SoundV);
									else
									game.mydata.puddle.play(game.mydata.settings.SoundV);
									tanklayer.setCell(dumb.X,dumb.Y-1,dumb.myself);		
									tanklayer.setCell(dumb.X,dumb.Y,null);
									dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y-1));
									dumb.setposition(dumb.X,dumb.Y-1);
								}else
									if(maplayer.getCell(dumb.X, dumb.Y-1).getTile().getId()==8)//gas
									{
										game.mydata.gas.play(game.mydata.settings.SoundV);
										tanklayer.setCell(dumb.X,dumb.Y-1,dumb.myself);		
										tanklayer.setCell(dumb.X,dumb.Y,null);
										maplayer.setCell(dumb.X, dumb.Y-1,null);
										dumb.fuel=Tank.stomach;
										dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y-1));
										dumb.setposition(dumb.X,dumb.Y-1);
									}else
										if(maplayer.getCell(dumb.X, dumb.Y-1).getTile().getId()==9)//pila
										{
											game.mydata.recharge.play(game.mydata.settings.SoundV);
											tanklayer.setCell(dumb.X,dumb.Y-1,dumb.myself);		
											tanklayer.setCell(dumb.X,dumb.Y,null);
											maplayer.setCell(dumb.X, dumb.Y-1,null);
											dumb.bullets=Tank.pileofbullets;
											dumb.setnowon(maplayer.getCell(dumb.X, dumb.Y-1));
											dumb.setposition(dumb.X,dumb.Y-1);
										}
					}
					
				}else
					if(k==Keys.LEFT&&dumb.fuel>0)
					{
						if(dumb.fuel==1)
							game.mydata.nogas.play(game.mydata.settings.SoundV);
						dumb.lookat='l';
						if(dumb.X>0&&tanklayer.getCell(dumb.X-1, dumb.Y)==null)
						{
							if(maplayer.getCell(dumb.X-1,dumb.Y)==null)
							{
								tanklayer.setCell(dumb.X-1,dumb.Y,dumb.myself);		
								tanklayer.setCell(dumb.X,dumb.Y,null);
								dumb.setnowon(maplayer.getCell(dumb.X-1, dumb.Y));
								dumb.setposition(dumb.X-1,dumb.Y);
							}else
								if(maplayer.getCell(dumb.X-1, dumb.Y).getTile().getId()==7)//bosque
								{
									game.mydata.deadleaf.play(game.mydata.settings.SoundV);
									tanklayer.setCell(dumb.X-1,dumb.Y,null);		
									tanklayer.setCell(dumb.X,dumb.Y,null);
									dumb.setnowon(maplayer.getCell(dumb.X-1, dumb.Y));
									dumb.setposition(dumb.X-1,dumb.Y);
								}else
									if(maplayer.getCell(dumb.X-1, dumb.Y).getTile().getId()==3)//agua
									{
										if(dumb.fuel==2)
											game.mydata.nogas.play(game.mydata.settings.SoundV);
										else
										game.mydata.puddle.play(game.mydata.settings.SoundV);
										tanklayer.setCell(dumb.X-1,dumb.Y,dumb.myself);		
										tanklayer.setCell(dumb.X,dumb.Y,null);
										dumb.setnowon(maplayer.getCell(dumb.X-1, dumb.Y));
										dumb.setposition(dumb.X-1,dumb.Y);
									}else
										if(maplayer.getCell(dumb.X-1, dumb.Y).getTile().getId()==8)//gas
										{
											game.mydata.gas.play(game.mydata.settings.SoundV);
											tanklayer.setCell(dumb.X-1,dumb.Y,dumb.myself);		
											tanklayer.setCell(dumb.X,dumb.Y,null);
											maplayer.setCell(dumb.X-1, dumb.Y,null);
											dumb.fuel=Tank.stomach;
											dumb.setnowon(maplayer.getCell(dumb.X-1, dumb.Y));
											dumb.setposition(dumb.X-1,dumb.Y);
										}else
											if(maplayer.getCell(dumb.X-1, dumb.Y).getTile().getId()==9)//pila
											{
												game.mydata.recharge.play(game.mydata.settings.SoundV);
												tanklayer.setCell(dumb.X-1,dumb.Y,dumb.myself);		
												tanklayer.setCell(dumb.X,dumb.Y,null);
												maplayer.setCell(dumb.X-1, dumb.Y,null);
												dumb.bullets=Tank.pileofbullets;
												dumb.setnowon(maplayer.getCell(dumb.X-1, dumb.Y));
												dumb.setposition(dumb.X-1,dumb.Y);
											}
						}
					}else
						if(k==Keys.RIGHT&&dumb.fuel>0)
						{
							if(dumb.fuel==1)
								game.mydata.nogas.play(game.mydata.settings.SoundV);
							dumb.lookat='r';
							if(dumb.X<34&&tanklayer.getCell(dumb.X+1, dumb.Y)==null)
							{
								if(maplayer.getCell(dumb.X+1,dumb.Y)==null)
								{
									tanklayer.setCell(dumb.X+1,dumb.Y,dumb.myself);		
									tanklayer.setCell(dumb.X,dumb.Y,null);
									dumb.setnowon(maplayer.getCell(dumb.X+1, dumb.Y));
									dumb.setposition(dumb.X+1,dumb.Y);
								}else
									if(maplayer.getCell(dumb.X+1, dumb.Y).getTile().getId()==7)//bosque
									{
										game.mydata.deadleaf.play(game.mydata.settings.SoundV);
										tanklayer.setCell(dumb.X+1,dumb.Y,null);		
										tanklayer.setCell(dumb.X,dumb.Y,null);
										dumb.setnowon(maplayer.getCell(dumb.X+1, dumb.Y));
										dumb.setposition(dumb.X+1,dumb.Y);
									}else
										if(maplayer.getCell(dumb.X+1, dumb.Y).getTile().getId()==3)//agua
										{
											if(dumb.fuel==2)
												game.mydata.nogas.play(game.mydata.settings.SoundV);
											else
											game.mydata.puddle.play(game.mydata.settings.SoundV);
											tanklayer.setCell(dumb.X+1,dumb.Y,dumb.myself);		
											tanklayer.setCell(dumb.X,dumb.Y,null);
											dumb.setnowon(maplayer.getCell(dumb.X+1, dumb.Y));
											dumb.setposition(dumb.X+1,dumb.Y);
										}else
											if(maplayer.getCell(dumb.X+1, dumb.Y).getTile().getId()==8)//gas
											{
												game.mydata.gas.play(game.mydata.settings.SoundV);
												tanklayer.setCell(dumb.X+1,dumb.Y,dumb.myself);		
												tanklayer.setCell(dumb.X,dumb.Y,null);
												maplayer.setCell(dumb.X+1, dumb.Y,null);
												dumb.fuel=Tank.stomach;
												dumb.setnowon(maplayer.getCell(dumb.X+1, dumb.Y));
												dumb.setposition(dumb.X+1,dumb.Y);
											}else
												if(maplayer.getCell(dumb.X+1, dumb.Y).getTile().getId()==9)//pila
												{
													game.mydata.recharge.play(game.mydata.settings.SoundV);
													tanklayer.setCell(dumb.X+1,dumb.Y,dumb.myself);		
													tanklayer.setCell(dumb.X,dumb.Y,null);
													maplayer.setCell(dumb.X+1, dumb.Y,null);
													dumb.bullets=Tank.pileofbullets;
													dumb.setnowon(maplayer.getCell(dumb.X+1, dumb.Y));
													dumb.setposition(dumb.X+1,dumb.Y);
												}
							}
						}
							if(s==Keys.SPACE&&dumb.bullets>0)
							{
								game.mydata.shoot.play(game.mydata.settings.SoundV);
								dumb.bullets--;
								createbullet(dumb,(TiledMapTileLayer)mapRenderer.getMap().getLayers().get(2));
							}
		}else
		{
			if(dumb.fuel>0)//suponiendo que al quedar sin combustible no pueda mover la cabeza
				if(k==(Keys.UP))
				{
					dumb.lookat='u';
				}else
					if(k==(Keys.DOWN))
					{
						dumb.lookat='d';
					}else
						if(k==(Keys.LEFT))
						{
							dumb.lookat='l';
						}else
							if(k==(Keys.RIGHT))
							{
								dumb.lookat='r';	
							}
		}
		//rotating		
		if(dumb.lookat=='u'&&tanklayer.getCell(dumb.X,dumb.Y)!=null)
		{
			tanklayer.getCell(dumb.X, dumb.Y).setRotation(0);
			tanklayer.getCell(dumb.X, dumb.Y).setFlipVertically(false);
		}else
			if(dumb.lookat=='d'&&tanklayer.getCell(dumb.X,dumb.Y)!=null)
			{
				tanklayer.getCell(dumb.X, dumb.Y).setRotation(0);
				tanklayer.getCell(dumb.X, dumb.Y).setFlipVertically(true);
			}
			else
				if(dumb.lookat=='l'&&tanklayer.getCell(dumb.X,dumb.Y)!=null)
				{
					tanklayer.getCell(dumb.X, dumb.Y).setFlipVertically(false);
					tanklayer.getCell(dumb.X, dumb.Y).setRotation(1);
				}else
					if(dumb.lookat=='r'&&tanklayer.getCell(dumb.X,dumb.Y)!=null)
					{
						tanklayer.getCell(dumb.X, dumb.Y).setFlipVertically(true);
						tanklayer.getCell(dumb.X, dumb.Y).setRotation(1);
					}		
		}
	}
	public void createbullet(Tank t, TiledMapTileLayer bulletlayer)
	{
		Bullet b=new Bullet();
		if(t.lookat=='u')
		{
			b=new Bullet(t.X, t.Y, 'u');
			//bulletlayer.setCell(t.X, t.Y, c);
		}else
			if(t.lookat=='d')
			{
				b=new Bullet(t.X, t.Y, 'd');
				//bulletlayer.setCell(t.X, t.Y, c);
			}else
				if(t.lookat=='l')
				{
					b=new Bullet(t.X, t.Y, 'l');
					//bulletlayer.setCell(t.X, t.Y, c);
				}else
					{
						b=new Bullet(t.X, t.Y, 'r');
						//bulletlayer.setCell(t.X, t.Y, c);
					}
		thebullets.add(b);
	}
	
	public void win()
	{
		w=true;
		texturesplash = new Texture(Gdx.files.internal("data/Images/you win.png"));
		texturesplash.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // en nuestro imagen atlas, nuestra imagen splash empieza en el (0,0) en la esquina superior izquierda y tiene una dimensi�n de  512x301
		TextureRegion regionsplash = new TextureRegion(texturesplash,0,0,1280,800);
		TextureRegionDrawable splashregion = new TextureRegionDrawable(regionsplash);

        //aqu� creamos el actor de la imagen de splah. El tama�o ser� definido cuando sea llamado el m�todo resize().
		splashImage = new Image( splashregion, Scaling.stretch);
        splashImage.setFillParent(true);
        splashImage.addAction( sequence( fadeIn( 0.20f ), delay( 2.25f ), fadeOut( 1f ),         
                new Action() //accion personalizada 
        		{       
                    @Override        
                    public boolean act(float delta)//logica de la accion        
                    {        
                        // la �ltima acci�n nos direcciona hacia la siguiente pantalla (menu)
                        game.setScreen( new MenuScreen( game ) );
                        return true;
                    } 
                } ) );
    
            // por �ltimo a�adimos el actor al stage.
            stage.addActor( splashImage );  
	}
	public void loose()
	{
		w=true;
		texturesplash = new Texture(Gdx.files.internal("data/Images/you loose.png"));
		texturesplash.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // en nuestro imagen atlas, nuestra imagen splash empieza en el (0,0) en la esquina superior izquierda y tiene una dimensi�n de  512x301
		TextureRegion regionsplash = new TextureRegion(texturesplash,0,0,1280,800);
		TextureRegionDrawable splashregion = new TextureRegionDrawable(regionsplash);

        //aqu� creamos el actor de la imagen de splah. El tama�o ser� definido cuando sea llamado el m�todo resize().
		splashImage = new Image( splashregion, Scaling.stretch);
        splashImage.setFillParent(true);
        splashImage.addAction( sequence( fadeIn( 0.20f ), delay( 3f ), fadeOut( 1f ),         
                new Action() //accion personalizada 
        		{       
                    @Override        
                    public boolean act(float delta)//logica de la accion        
                    {        
                        // la �ltima acci�n nos direcciona hacia la siguiente pantalla (menu)
                        game.setScreen( new MenuScreen( game ) );
                        return true;
                    } 
                } ) );
    
            // por �ltimo a�adimos el actor al stage.
            stage.addActor( splashImage );  
	}

	@Override 
	public void render( float delta )
    {
		
		if(!w)
		Zod.planning();
		//od.planning();
		//if(game.mydata.settings.Mycolor)//si soy un server/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			long e=System.nanoTime();
			
				//dada una lista de balas, dos de tanques, si el tiempo es el correcto, lee los maquinistas de esos tanques y actualiza las capas
			if(e>=b+Bullet.slow&&!w)
			{
				b=e;
				movingbullets();
			}
				//si el tiempo que ha pasado es el correcto, se movieron los tanques.
			if(e>=f+Tank.slow&&!w)
			{
				f=e;
				movingtanks(e,eviltanks);
			}
			
			
			int k=0,s=0;
			if(	Gdx.input.isKeyPressed(Keys.UP))
			{
				k=Keys.UP;

				TiledMap map;map=new TmxMapLoader().load("data/Maps/defaultmap2.tmx");
				mapRenderer = new OrthogonalTiledMapRenderer(map);

			}else
				if(Gdx.input.isKeyPressed(Keys.DOWN))
				{
					k=Keys.DOWN;
				}else
					if(Gdx.input.isKeyPressed(Keys.RIGHT))
					{
						k=Keys.RIGHT;
					}else
						if(Gdx.input.isKeyPressed(Keys.LEFT))
						{
							k=Keys.LEFT;
						}
							if(Gdx.input.isKeyPressed(Keys.SPACE))
							{
								s=Keys.SPACE;
							}
			
			movingtanks(e,(TiledMapTileLayer)mapRenderer.getMap().getLayers().get(0),(TiledMapTileLayer)mapRenderer.getMap().getLayers().get(1),k,s,mytanks);
			if(e>=t+Tank.slow&&!w)
				t=e;
			else
				if(t+Tank.slow<e&&!w)
					t=t+Tank.slow;
			
			
			if(checkifiwin()&&!w)
			{
				win();
			}
			if(checkifiloose()&&!w)
			{
				loose();
			}

		super.render( delta );
		if(!w)
		{
		stage.getCamera().update();

		mapRenderer.setView((OrthographicCamera) stage.getCamera());
		//mapRenderer.getViewBounds().setSize(300, 300);
		//mapRenderer.getViewBounds().setCenter(220, 200);
		mapRenderer.render();
		
		stage2.act(delta);
		stage2.draw();
		
		}
    }
	@Override
	public void dispose() 
	{
		/*try 
		{
			if(Batman!=null)
				Batman.close();
			if(Alfred!=null)
				Alfred.close();
		} catch (IOException e) {e.printStackTrace();}*/
		game.mydata.disposebattletunes();
		game.mydata.disposemap();
		game.mydata.loadmenutunes();
		mapRenderer.dispose();
		super.dispose();
	}

	/*
	public static Runnable Batmansend = new Runnable()//como cliente, envio lo que aprieto
	{
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			
			while (Batman != null&&!Batman.isClosed())//mientras se este conectado
			{
				
					try
					{
						//intenta escribir las teclas
						WatchtowerView mapi=new WatchtowerView(null, k,s);
						oos = new ObjectOutputStream(Batman.getOutputStream());
						oos.writeObject(mapi);
						//oos = new DataOutputStream(Batman.getOutputStream());
						//oos.writeInt(s);
						if(Batman.isClosed())
							break;
						
					}
					catch (Exception ex) {}
				
			}
		}
	};
	
	
	public  Runnable Batmanreceive = new Runnable()//como cliente, recivo lo que debo ver
	{
		@Override
		public void run()
		{
			ObjectInputStream ois;
			
			while (Batman != null&&!Batman.isClosed())//mientras se este conectado
			{
				
					try
					{
						ois = new ObjectInputStream(Batman.getInputStream());
						WatchtowerView mapi=(WatchtowerView) ois.readObject();
						mapRenderer=mapi.mapRenderer;
						mapRenderer.render();
										
						//ois = new DataInputStream(Batman.getInputStream());
						//int s = ois.readInt();
						if(Batman.isClosed())
							break;
					}
					catch (Exception ex) {}
				
			}
		}
	};
	
	
	public Runnable Alfredsend = new Runnable()//como servidor, envio el mapa para que el cliente lo muestre
	{
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			
			while (Batman != null&&!Batman.isClosed()&&Alfred!=null&&!Alfred.isClosed())//mientras se este conectado
			{
				
					try
					{
						//intenta escribir las teclas
						WatchtowerView mapi= new WatchtowerView(mapRenderer,0,0);
						oos = new ObjectOutputStream(Batman.getOutputStream());
						oos.writeObject(mapi);
						
						//oos = new DataOutputStream(Batman.getOutputStream());
						//oos.writeInt(s);
						if (Alfred.isClosed())
							break;
						
					}
					catch (Exception ex) {}
				
			}
		}
	};
	
	
	public static Runnable Alfredreceive = new Runnable()//como servidor, recivo las teclas del cliente
	{
		@Override
		public void run()
		{
			ObjectInputStream ois;
			
			while (Batman != null&&!Batman.isClosed()&&Alfred!=null&&!Alfred.isClosed())//mientras se este conectado
			{
				try
				{
					ois = new ObjectInputStream(Batman.getInputStream());
					WatchtowerView mapi = (WatchtowerView) ois.readObject();
					evilkey=mapi.key;
					evilshoot=mapi.s;				
					//ois = new DataInputStream(Batman.getInputStream());
					//int s = ois.readInt();
					if (Alfred.isClosed())
						break;
				}
				catch (Exception ex) {}
			}
		}
	};
	
	
	*/
	
	
	
}