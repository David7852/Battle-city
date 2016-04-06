package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class MenuScreen extends AbstractScreen
{   
	long e,f;
	public MenuScreen(MyGdxGame game)
	{
		super(game);
	}

	@Override public void show() 
	{
		super.show();	
		
		//title
		Texture TextureTitle=new Texture(Gdx.files.internal("data/Images/Title.png"));
		TextureTitle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionTitle=new TextureRegion(TextureTitle,0,0,666,254);
		Image Title=new Image(regionTitle);
		Title.setBounds(0,800-254,666,254);
		
		//dec
		Texture Texturetank=new Texture(Gdx.files.internal("data/Images/tankdec.png"));
		Texturetank.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regiontank=new TextureRegion(Texturetank,0,0,700,800);
		Image tank=new Image(regiontank);
		tank.setBounds(MENU_VIEWPORT_WIDTH-700,0,700,800);
		
		//button play
		Texture TextureButtonplayup=new Texture(Gdx.files.internal("data/Images/Buttonplayup.png"));
		TextureButtonplayup.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtonplayup=new TextureRegion(TextureButtonplayup,0,0,333,150);
		Drawable Buttonplayregionup = new TextureRegionDrawable(RegionButtonplayup);
		
		Texture TextureButtonplaydown=new Texture(Gdx.files.internal("data/Images/Buttonplaydown.png"));
		TextureButtonplaydown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtonplaydown=new TextureRegion(TextureButtonplaydown,0,0,333,150);
		Drawable Buttonplayregiondown = new TextureRegionDrawable(RegionButtonplaydown);
		
		Texture TextureButtonplaychecked=new Texture(Gdx.files.internal("data/Images/Buttonplaychecked.png"));
		TextureButtonplaychecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtonplaychecked=new TextureRegion(TextureButtonplaychecked,0,0,333,150);
		Drawable Buttonplayregionchecked = new TextureRegionDrawable(RegionButtonplaychecked);
		
		final Button Buttonplay=new Button(Buttonplayregionup,Buttonplayregiondown,Buttonplayregionchecked);
		Buttonplay.setBounds(128, (float) (stage.getHeight()/2.16), 333, 150);
		Buttonplay.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {return true; }
			public void touchUp (InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				if(Buttonplay.isChecked())
				{
					game.mydata.soundclick.play(game.mydata.settings.SoundV);
					game.mydata.disposemenutunes();
					//game.setScreen(new NetScreen(game));
					game.mydata.loadbattletunes();
					game.mydata.loadmap();
		        	game.setScreen( new StartGameScreen( game ) );
				}
		    } 
		});

		//button settings
		Texture TextureButtonsettingsup=new Texture(Gdx.files.internal("data/Images/Buttonsettingsup.png"));
		TextureButtonsettingsup.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtonsettingsup=new TextureRegion(TextureButtonsettingsup,0,0,366, 180);
		Drawable Buttonsettingsregionup = new TextureRegionDrawable(RegionButtonsettingsup);
		
		Texture TextureButtonsettingsdown=new Texture(Gdx.files.internal("data/Images/Buttonsettingsdown.png"));
		TextureButtonsettingsdown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtonsettingsdown=new TextureRegion(TextureButtonsettingsdown,0,0,366, 180);
		Drawable Buttonsettingsregiondown = new TextureRegionDrawable(RegionButtonsettingsdown);
		
		Texture TextureButtonsettingschecked=new Texture(Gdx.files.internal("data/Images/Buttonsettingschecked.png"));
		TextureButtonsettingschecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtonsettingschecked=new TextureRegion(TextureButtonsettingschecked,0,0,366, 180);
		Drawable Buttonsettingsregionchecked = new TextureRegionDrawable(RegionButtonsettingschecked);
		
		final Button Buttonsettings=new Button(Buttonsettingsregionup,Buttonsettingsregiondown,Buttonsettingsregionchecked);
		Buttonsettings.setBounds(128, 160, 366, 180);
		Buttonsettings.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {
				e=System.nanoTime();
				return true; }
			public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				f=System.nanoTime();
				if(f-e<550000000)
				//if(Buttonsettings.isChecked())
				{
					game.mydata.soundclick.play(game.mydata.settings.SoundV);
					game.setScreen( new SettingsScreen( game ) );//cambiar a pantalla preferencias
				}
		    } 
		});
		//Buttondrawmap
		Texture TextureButtondrawmapup=new Texture(Gdx.files.internal("data/Images/Buttondrawmapup.png"));
		TextureButtondrawmapup.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtondrawmapup=new TextureRegion(TextureButtondrawmapup,0,0,289,139);
		TextureRegionDrawable Buttondrawmapregionup = new TextureRegionDrawable(RegionButtondrawmapup);
		
		Texture TextureButtondrawmapdown=new Texture(Gdx.files.internal("data/Images/Buttondrawmapdown.png"));
		TextureButtondrawmapdown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtondrawmapdown=new TextureRegion(TextureButtondrawmapdown,0,0,289,139);
		TextureRegionDrawable Buttondrawmapregiondown = new TextureRegionDrawable(RegionButtondrawmapdown);
		
		Texture TextureButtondrawmapchecked=new Texture(Gdx.files.internal("data/Images/Buttondrawmapchecked.png"));
		TextureButtondrawmapchecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionButtondrawmapchecked=new TextureRegion(TextureButtondrawmapchecked,0,0,289,139);
		TextureRegionDrawable Buttondrawmapregionchecked = new TextureRegionDrawable(RegionButtondrawmapchecked);
				
		final Button Buttondrawmap=new Button(Buttondrawmapregionup,Buttondrawmapregiondown,Buttondrawmapregionchecked);
		Buttondrawmap.setBounds(128, 10, 289, 139);
		Buttondrawmap.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    { return true;}
			public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				if(Buttondrawmap.isChecked())
				{
			    	game.mydata.soundclick.play(game.mydata.settings.SoundV);
			    	try 
			    	{ 
			    	   /* directorio/ejecutable es el path del ejecutable y un nombre */ 
			    	   Runtime.getRuntime().exec("./bin/data/Tiled/Tiled.exe");
			    	} 
			    	catch (Exception e) 
			    		{ 
					       Gdx.app.log("","no encontrado");//agregar un actor con fadein
					    }
				}
		    }
		});
		
		//ruledecoration
		Texture TextureRule=new Texture(Gdx.files.internal("data/Images/rule.png"));
		TextureRule.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image Rule=new Image(TextureRule);
		Rule.setBounds(-390, 100, 600, 600);
		
		
		//pencildecoration
		Texture TexturePencil=new Texture(Gdx.files.internal("data/Images/pencil.png"));
		TexturePencil.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image Pencil=new Image(TexturePencil);
		Pencil.setBounds(50, -161, 147, 321);
		
		stage.addActor(Title);
		stage.addActor(tank);
		stage.addActor(Buttonplay);
		stage.addActor(Buttonsettings);
		stage.addActor(Buttondrawmap);
		stage.addActor(Rule);
		stage.addActor(Pencil);
	}
	
    @Override public void render( float delta )
    {
    	super.render( delta );
    }
       
    @Override public void dispose()      
    {
    	super.dispose();
    }
}
