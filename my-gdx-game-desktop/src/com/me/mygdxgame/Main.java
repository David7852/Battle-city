package com.me.mygdxgame;

import java.awt.Toolkit;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main 
{
	public static void main(String[] args) 
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Battle Notebook";
		Toolkit tk = Toolkit.getDefaultToolkit();
		int w=tk.getScreenSize().width, h=tk.getScreenSize().height;
		if(w>=1280&&h>=800)
		{
			w=1280;h=800;	
		}else
		{
			w=(int)(float)(tk.getScreenSize().width/1.125);
			h=(int)(float)(tk.getScreenSize().height/1.125);
		}
		cfg.width = w;
		cfg.height = h;
		cfg.x=10;
		cfg.y=10;
		cfg.allowSoftwareMode=true;
		cfg.resizable=true;
		cfg.addIcon("data/Icons/Icon 128.png", Files.FileType.Internal);
		cfg.addIcon("data/Icons/Icon 32.png", Files.FileType.Internal);
		cfg.addIcon("data/Icons/Icon 16.png", Files.FileType.Internal);
		                                            
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
