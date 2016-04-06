package com.me.mygdxgame;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game
{
	public Datamanager mydata;
	
	@Override
	public void create() //se ejecuta cuando crea la actividad, el método donde debemos cargar las imágenes, sonidos y músicas en memoria para poder usarlas.
	{
		mydata=new Datamanager();
	}
	
	@Override
	public void resize(int width, int height)
	{
		if( getScreen() == null ) 
		{
			setScreen( new com.me.mygdxgame.SplashScreen( this ) );          
		}
	}
	
	@Override
	public void dispose() //liberar el espacio de memoria que hemos usado en el método create, para ello podemos usar el método dispose() de cualquier clase Texture, Sound etc.
	{
		super.dispose();
		mydata.dispose();
	}
}
