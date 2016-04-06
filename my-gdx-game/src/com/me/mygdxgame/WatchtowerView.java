package com.me.mygdxgame;

import java.io.Serializable;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class WatchtowerView implements Serializable
{
	public OrthogonalTiledMapRenderer mapRenderer;//=new OrthogonalTiledMapRenderer(com.me.mygdxgame.Datagestor.map);
	int key=0,s=0;
	public WatchtowerView(OrthogonalTiledMapRenderer mapi,int k, int s) 
	{
		if(mapi!=null)
			mapRenderer=mapi;
		key=k;
		this.s=s;
	}
	
	
}
