package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;

public final class Datamanager 
{	
	public Settings settings;
	//map
	public TiledMap map;
	//sounds
	public Sound soundclick;
	public Sound nogas;
	public Sound puddle;
	public Sound goodbyefriend;
	public Sound gas;
	public Sound recharge;
	public Sound shoot;
	public Sound breaked;
	public Sound deadleaf;
	public Sound hit;
	
	//musics
	public Music nowplaying;
	public Music musicMenu;
	public Music musicMenu2;
	public Music musicBattle;
	public Music musicBattle2;
	public Music musicBattle3;
	public Music musicBattle4;
	public Music musicBattle5;

	public Datamanager()
	{
		settings=new Settings();
		settings.getSettings();
		setcursor();
		soundclick = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/Click.mp3"));
		
	}
	public void Stopcurrentmusic()
	{
		if(nowplaying!=null)
			nowplaying.stop();
	}
	public void play(Music nextroll)
	{
		Stopcurrentmusic();
		nowplaying=nextroll;
		nowplaying.setVolume(settings.MusicV);
		nowplaying.play();
	}
	public void sorttunes(MyGdxGame game)
	{
		int choose=MathUtils.random(0,100);
		if(game.getScreen().getClass()!=com.me.mygdxgame.NetScreen.class)
		if(game.getScreen().getClass()==com.me.mygdxgame.StartGameScreen.class)
			if(choose<20)
			{
				play(musicBattle);
			}else
				if(choose<40)
				{
					play(musicBattle2);
				}else
					if(choose<60)
					{
						play(musicBattle3);
					}else
						if(choose<80)
						{
							play(musicBattle4);
						}else
								play(musicBattle5);
		else
			if(choose<50)
			{
				play(musicMenu);
			}else
				play(musicMenu2);
			
	}
	public void loadbattletunes()
	{
		musicBattle=Gdx.audio.newMusic(Gdx.files.internal("data/Music/Batalla.mp3"));
		musicBattle.setLooping(false);
		
		musicBattle2=Gdx.audio.newMusic(Gdx.files.internal("data/Music/Batalla2.mp3"));
		musicBattle2.setLooping(false);
		
		musicBattle3=Gdx.audio.newMusic(Gdx.files.internal("data/Music/Batalla3.mp3"));
		musicBattle3.setLooping(false);
		
		musicBattle4=Gdx.audio.newMusic(Gdx.files.internal("data/Music/Batalla4.mp3"));
		musicBattle4.setLooping(false);
		
		musicBattle5=Gdx.audio.newMusic(Gdx.files.internal("data/Music/Batalla5.mp3"));
		musicBattle5.setLooping(false);
		
		//sounds
		nogas = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/no gas.mp3"));
		puddle = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/puddle.mp3"));
		goodbyefriend = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/goodbye friend.mp3"));
		gas = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/gas.mp3"));
		recharge = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/recharge.mp3"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/shoot.mp3"));
		breaked = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/breaked.mp3"));
		deadleaf = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/dead leaf.mp3"));
		hit = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/hit brick.mp3"));
	}
	public void Setmusicvolume()
	{
		float volume=settings.MusicV;
		musicBattle.setVolume(volume);
		musicBattle2.setVolume(volume);
		musicBattle3.setVolume(volume);
		musicBattle4.setVolume(volume);
		musicBattle5.setVolume(volume);
		musicMenu.setVolume(volume);
		musicMenu2.setVolume(volume);
	}
	
	public void disposebattletunes()
	{
		nowplaying.stop();
		musicBattle.dispose();
		musicBattle2.dispose();
		musicBattle3.dispose();
		musicBattle4.dispose();
		musicBattle5.dispose();
		//sounds
	}
	public void loadmenutunes()
	{
		if(musicMenu==null)
		{
			musicMenu=Gdx.audio.newMusic(Gdx.files.internal("data/Music/Menu.mp3"));
			musicMenu.setLooping(false);
		}
		if(musicMenu2==null)
		{
			musicMenu2=Gdx.audio.newMusic(Gdx.files.internal("data/Music/Menu2.mp3"));
			musicMenu2.setLooping(false);
		}
		
	}
	public void disposemenutunes()
	{
		nowplaying.stop();
		musicMenu.dispose();
		musicMenu2.dispose();
		//sounds
	}
	public boolean loadmap()
	{
//		try
//		{
			map=new TmxMapLoader().load("data/Maps/defaultmap.tmx"); //una variable string "patchmap" cambia segun la direccion del mapa creado o bien via filechoose. por defecto, al iniciar siempre carga un Defaultmap.tmx
//		}catch (Exception e) 
//		{
//			return false;//si no abre el mapa, comunicar que no cargo y hacer la respectiva animacion/accion en la pantalla menu o de preferencias
//		}
		return true;		
	}
	public void disposemap()
	{
		if(map!=null)
			map.dispose();
	}
	public void setcursor()
	{
		//if(settings.Sexycursor)
		//{
			Pixmap pm;
			if(settings.Mycolor)
				pm= new Pixmap(Gdx.files.internal("data/Icons/pencilB.png"));
			else
				pm= new Pixmap(Gdx.files.internal("data/Icons/pencilR.png"));
			int xHotSpot = pm.getWidth() / 2;
			int yHotSpot = pm.getHeight() / 2;
			
			Gdx.input.setCursorImage(pm, xHotSpot, yHotSpot);
			pm.dispose();
		//}
	}
	
	
	
	public void refresh()
	{
		//actualizar los datos cuando ocurren cambios en el archivo de preferencias, como un nuevo mapa o el volumen???
		
	}
	
	public void dispose()
	{
		if(soundclick!=null)
		soundclick.dispose();
		if(nogas!=null)
		nogas.dispose();
		if(puddle!=null)
		puddle.dispose();
		if(goodbyefriend!=null)
		goodbyefriend.dispose(); 
		if(gas!=null)
		gas.dispose();
		if(recharge!=null)
		recharge.dispose();
		if(shoot!=null)
		shoot.dispose();
		if(breaked!=null)
		breaked.dispose();
		if(deadleaf!=null)
		deadleaf.dispose(); 
		if(hit!=null)
		hit.dispose();
		if(musicMenu!=null)
		musicMenu.dispose();
		if(musicMenu2!=null)
		musicMenu2.dispose();
		if(musicBattle!=null)
		musicBattle.dispose();
		if(musicBattle2!=null)
		musicBattle2.dispose();
		if(musicBattle3!=null)
		musicBattle3.dispose();
		if(musicBattle4!=null)
		musicBattle4.dispose();
		if(musicBattle5!=null)
		musicBattle5.dispose();
		if(map!=null)
		map.dispose();
	}
}
