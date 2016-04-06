package com.me.mygdxgame;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game
{
	public Datamanager mydata;
	
	@Override
	public void create() //se ejecuta cuando crea la actividad, el m�todo donde debemos cargar las im�genes, sonidos y m�sicas en memoria para poder usarlas.
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
	public void dispose() //liberar el espacio de memoria que hemos usado en el m�todo create, para ello podemos usar el m�todo dispose() de cualquier clase Texture, Sound etc.
	{
		super.dispose();
		mydata.dispose();
	}
}
