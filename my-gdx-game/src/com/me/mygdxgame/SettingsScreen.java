package com.me.mygdxgame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class SettingsScreen extends AbstractScreen
{
	Image Mypaper1,Mypaper2,Mypaper3,Mypaper4;
	Settings backupsettings;

	public SettingsScreen(MyGdxGame game) 
	{
		super(game);
		backupsettings=new Settings();
		backupsettings.getSettings();
	}
	
	@Override public void show() 
	{
		super.show();
		batch=new  SpriteBatch();
		
		font=new BitmapFont(Gdx.files.internal("data/fonts/choco.fnt"));
		
		//settingstitle
		Texture TextureTitle=new Texture(Gdx.files.internal("data/Images/SettingsTitle.png"));
		TextureRegion regionTitle=new TextureRegion(TextureTitle,0,0,750,162);
		TextureTitle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image Title=new Image(regionTitle);
		Title.setBounds(stage.getWidth()/2-375,stage.getHeight()-160,750,162);
		
		//music&soundtitle
		Texture TextureTitlesm=new Texture(Gdx.files.internal("data/Images/sound&musicTitle.png"));
		TextureRegion regionTitlesm=new TextureRegion(TextureTitlesm,0,0,350,190);
		TextureTitlesm.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image Titlesm=new Image(regionTitlesm);
		Titlesm.setBounds( 0, (float)(stage.getHeight()/1.75),350,190);
		
		//looktitle
		Texture TextureTitlelo=new Texture(Gdx.files.internal("data/Images/lookTitle.png"));
		TextureRegion regionTitlelo=new TextureRegion(TextureTitlelo,0,0,300,180);
		TextureTitlesm.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image Titlelo=new Image(regionTitlelo);
		Titlelo.setBounds( stage.getWidth()-310, (float)(stage.getHeight()/1.5),300,180);
		
		//staples
		Texture Texturestaples=new Texture(Gdx.files.internal("data/Images/staples.png"));
		TextureRegion regionstaples=new TextureRegion(Texturestaples,0,0,500,500);
		Texturestaples.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image staples=new Image(regionstaples);
		staples.setBounds( stage.getWidth()/2-230, (float)(stage.getHeight()/2-200),500,500);
		
		
		
		
		//sliders
		
		//musicslider
		Texture TextureSliderbackground=new Texture(Gdx.files.internal("data/Images/Sliderbackground.png"));
		TextureSliderbackground.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionSliderbackground=new TextureRegion(TextureSliderbackground,0,0,280,75);
		Drawable Sliderbackgroundregion = new TextureRegionDrawable(RegionSliderbackground);
		
		Texture TextureSliderknob=new Texture(Gdx.files.internal("data/Images/Sliderknob.png"));
		TextureSliderknob.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionSliderknob=new TextureRegion(TextureSliderknob,0,0,35,35);
		Drawable Sliderknobregion = new TextureRegionDrawable(RegionSliderknob);
		
		Texture TextureSliderknob2=new Texture(Gdx.files.internal("data/Images/Sliderknob2.png"));
		TextureSliderknob2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionSliderknob2=new TextureRegion(TextureSliderknob2,0,0,35,35);
		Drawable Sliderknobregion2 = new TextureRegionDrawable(RegionSliderknob2);
		
		SliderStyle StyleMusic=new SliderStyle(Sliderbackgroundregion, Sliderknobregion2);
		final Slider MusicSlider=new Slider(0, 1, (float) 0.05, false, StyleMusic);
		MusicSlider.setValue(game.mydata.settings.MusicV);
		MusicSlider.setBounds(stage.getWidth()/20, (float)(stage.getHeight()/3), 280, 75);
		
		//soundslider
		SliderStyle StyleSound=new SliderStyle(Sliderbackgroundregion, Sliderknobregion);
		final Slider SoundSlider=new Slider(0, (float) 0.75, (float) 0.05, false, StyleSound);
		SoundSlider.setValue(game.mydata.settings.SoundV);
		SoundSlider.setBounds(stage.getWidth()/20, (float)(stage.getHeight()/2.25), 280, 75);
		
		

		
		
		
		//combobox
		Texture textureselection=new Texture(Gdx.files.internal("data/Images/Shiny yellow.png"));
		textureselection.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion RegionSelection=new TextureRegion(textureselection,0,0,304,70);
		Drawable SelectionRegion=new TextureRegionDrawable(RegionSelection);
		
		ListStyle mypaperliststyle=new ListStyle(font, Color.BLACK, Color.BLACK, SelectionRegion);
		final List<String> mypaperlist=new List<String>(mypaperliststyle);
		mypaperlist.setItems("Nonna's notes","A lemonade flood","Executive desktop","Recycled planes");
		mypaperlist.setBounds(stage.getWidth()-275, (float)(stage.getHeight()/2.25) , 155, 120);
		if(game.mydata.settings.Mypapername.equals("Paper 1"))
			mypaperlist.setSelectedIndex(0);
		else
			if(game.mydata.settings.Mypapername.equals("Paper 2"))
				mypaperlist.setSelectedIndex(1);
			else
				if(game.mydata.settings.Mypapername.equals("Paper 3"))
					mypaperlist.setSelectedIndex(2);
				else
					if(game.mydata.settings.Mypapername.equals("Paper 4"))
						mypaperlist.setSelectedIndex(3);
		
		mypaperlist.addListener( new ChangeListener() 
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				game.mydata.soundclick.play(game.mydata.settings.SoundV);
				String s="Paper 1";
				if(mypaperlist.getSelectedIndex()==0)
				{
					stage.getActors().first().remove();
					if(Mypaper1==null)
					{
						Texture texturefondo1 = new Texture(Gdx.files.internal("data/Images/Paper 1.png"));
						texturefondo1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
						TextureRegion regionfondo1 = new TextureRegion(texturefondo1, 0, 0, 1280, 800);
						Mypaper1=new Image(regionfondo1);
						Mypaper1.setFillParent(true);
						s="Paper 1";
					}
					stage.addActor(Mypaper1);
					stage.getActors().reverse();
				}else
				if(mypaperlist.getSelectedIndex()==1)
				{
					stage.getActors().first().remove();
					if(Mypaper2==null)
					{
						Texture texturefondo2 = new Texture(Gdx.files.internal("data/Images/Paper 2.png"));
						texturefondo2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
						TextureRegion regionfondo2 = new TextureRegion(texturefondo2, 0, 0, 1280, 800);
						Mypaper2=new Image(regionfondo2);
						Mypaper2.setFillParent(true);
						s="Paper 2";
					}
					
					stage.addActor(Mypaper2);
					stage.getActors().reverse();
				}else
					if(mypaperlist.getSelectedIndex()==2)
					{
						stage.getActors().first().remove();
						if(Mypaper3==null)
						{
							Texture texturefondo3 = new Texture(Gdx.files.internal("data/Images/Paper 3.png"));
							texturefondo3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
							TextureRegion regionfondo3 = new TextureRegion(texturefondo3, 0, 0, 1280, 800);
							Mypaper3=new Image(regionfondo3);
							Mypaper3.setFillParent(true);
							s="Paper 3";
						}
						stage.addActor(Mypaper3);
						stage.getActors().reverse();
					}else
					{
						stage.getActors().first().remove();
						if(Mypaper4==null)
						{
							Texture texturefondo4 = new Texture(Gdx.files.internal("data/Images/Paper 4.png"));
							texturefondo4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
							TextureRegion regionfondo4 = new TextureRegion(texturefondo4, 0, 0, 1280, 800);
							Mypaper4=new Image(regionfondo4);
							Mypaper4.setFillParent(true);
							s="Paper 4";
						}
						stage.addActor(Mypaper4);
						stage.getActors().reverse();
					}
				//Gdx.app.getPreferences("BattleNotebook").putString("Mypapername", s);
				game.mydata.settings.Mypapername=s;
			}
        });
		//checkboxb
		Texture Texturecheckoff=new Texture(Gdx.files.internal("data/Images/CheckRadio.png"));
		Texturecheckoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion Regioncheckoff=new TextureRegion(Texturecheckoff,0,0,35,35);
		Drawable CheckoffRegion=new TextureRegionDrawable(Regioncheckoff);
		
		Texture Texturecheckon=new Texture(Gdx.files.internal("data/Images/CheckRadioSelected1.png"));
		Texturecheckon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion Regioncheckon=new TextureRegion(Texturecheckon,0,0,35,35);
		Drawable CheckonRegion=new TextureRegionDrawable(Regioncheckon);
		
		Texture Texturecheckon2=new Texture(Gdx.files.internal("data/Images/CheckRadioSelected2.png"));
		Texturecheckon2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion Regioncheckon2=new TextureRegion(Texturecheckon2,0,0,35,35);
		Drawable CheckonRegion2=new TextureRegionDrawable(Regioncheckon2);
		
		CheckBoxStyle CheckmycolorstyleB=new CheckBoxStyle(CheckoffRegion, CheckonRegion, font, Color.BLACK);
		CheckBoxStyle CheckmycolorstyleR=new CheckBoxStyle(CheckoffRegion, CheckonRegion2, font, Color.BLACK);
		final CheckBox CheckmycolorBlue=new CheckBox("Blue server", CheckmycolorstyleB);
		CheckmycolorBlue.setPosition(stage.getWidth()-275, (float)(stage.getHeight()/2.5)-75);
		CheckmycolorBlue.addListener( new ChangeListener() 
		{
			public void changed(ChangeEvent event, Actor actor) 
			{
				if(CheckmycolorBlue.isChecked())
				{
					game.mydata.settings.Mycolor=true;
					//Gdx.app.getPreferences("BattleNotebook").putBoolean("Mycolor", true);
					game.mydata.setcursor();
				}
			}
        });
		
		//checkboxr
		final CheckBox CheckmycolorRed=new CheckBox("Red client", CheckmycolorstyleR);
		CheckmycolorRed.setPosition(stage.getWidth()-275, (float)(stage.getHeight()/2.5)-115);
		CheckmycolorRed.addListener( new ChangeListener() 
		{
			public void changed(ChangeEvent event, Actor actor) 
			{
				if(CheckmycolorRed.isChecked())
				{
					game.mydata.settings.Mycolor=false;
					//Gdx.app.getPreferences("BattleNotebook").putBoolean("Mycolor", false);
					game.mydata.setcursor();
				}
				//poner esto en el boton back.
			}
        });
		if(!game.mydata.settings.Mycolor)
		{
			CheckmycolorBlue.setChecked(false);
			CheckmycolorRed.setChecked(true);
		}
		
		//buttongroupmycolor
		ButtonGroup bgmycolor=new ButtonGroup();
		bgmycolor.add(CheckmycolorBlue);
		bgmycolor.add(CheckmycolorRed);
		
		
		//Buttonmute
		
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
		buttonmute.setBounds(0, 0,100,104);
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
						MusicSlider.setValue(game.mydata.settings.olM);
						SoundSlider.setValue(game.mydata.settings.olS);
						game.mydata.settings.mute=false;
						
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
						game.mydata.settings.olM=MusicSlider.getValue();
						game.mydata.settings.olS=SoundSlider.getValue();
						MusicSlider.setValue(0);
						SoundSlider.setValue(0);
						game.mydata.settings.mute=true;
						
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
		//slideractions
		
		MusicSlider.addListener( new ChangeListener() 
		{
			public void changed(ChangeEvent event, Actor actor) 
			{
				float m=MusicSlider.getValue();
				//Gdx.app.getPreferences("BattleNotebook").putFloat("MusicV", m);
				game.mydata.nowplaying.setVolume(m);
				game.mydata.settings.MusicV=m;
				if(game.mydata.settings.MusicV==0&&game.mydata.settings.SoundV==0)
				{
					ButtonStyle buttonstyle;
					game.mydata.settings.mute=true;
					
					Texture texturebuttonmuteoff=new Texture(Gdx.files.internal("data/Images/Buttonmuteon.png"));
					texturebuttonmuteoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteoff=new TextureRegion(texturebuttonmuteoff,0,0,100,104);
					Drawable buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteoff);
					
					Texture texturebuttonmuteoffdown=new Texture(Gdx.files.internal("data/Images/Buttonmuteondown.png"));
					texturebuttonmuteoffdown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteoffdown=new TextureRegion(texturebuttonmuteoffdown,0,0,100,104);
					Drawable buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteoffdown);
					
					buttonstyle=new ButtonStyle(buttonmuteregion, buttonmutedownregion, buttonmuteregion);
					buttonmute.setStyle(buttonstyle);
				}
				else
				{
					game.mydata.settings.mute=false;
					ButtonStyle buttonstyle;
					
					Texture texturebuttonmuteoff=new Texture(Gdx.files.internal("data/Images/Buttonmuteoff.png"));
					texturebuttonmuteoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteoff=new TextureRegion(texturebuttonmuteoff,0,0,100,104);
					Drawable buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteoff);
					
					Texture texturebuttonmuteondown=new Texture(Gdx.files.internal("data/Images/Buttonmuteoffdown.png"));
					texturebuttonmuteondown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteondown=new TextureRegion(texturebuttonmuteondown,0,0,100,104);
					Drawable buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteondown);
					
					
					
					buttonstyle=new ButtonStyle(buttonmuteregion, buttonmutedownregion, buttonmuteregion);
					buttonmute.setStyle(buttonstyle);
				}
			}
        });
	
		SoundSlider.addListener( new ChangeListener() 
		{
			public void changed(ChangeEvent event, Actor actor) 
			{
				float s=SoundSlider.getValue();
				//Gdx.app.getPreferences("BattleNotebook").putFloat("SoundV", s);
				game.mydata.settings.SoundV=s;
				if(game.mydata.settings.MusicV==0&&game.mydata.settings.SoundV==0)
				{
					game.mydata.settings.mute=true;
					ButtonStyle buttonstyle;
					
					Texture texturebuttonmuteoff=new Texture(Gdx.files.internal("data/Images/Buttonmuteon.png"));
					texturebuttonmuteoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteoff=new TextureRegion(texturebuttonmuteoff,0,0,100,104);
					Drawable buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteoff);
					
					Texture texturebuttonmuteoffdown=new Texture(Gdx.files.internal("data/Images/Buttonmuteondown.png"));
					texturebuttonmuteoffdown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteoffdown=new TextureRegion(texturebuttonmuteoffdown,0,0,100,104);
					Drawable buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteoffdown);
					
					
					buttonstyle=new ButtonStyle(buttonmuteregion, buttonmutedownregion, buttonmuteregion);
					buttonmute.setStyle(buttonstyle);
				}
				else
				{
					game.mydata.settings.mute=false;
					ButtonStyle buttonstyle;
					

					Texture texturebuttonmuteoff=new Texture(Gdx.files.internal("data/Images/Buttonmuteoff.png"));
					texturebuttonmuteoff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteoff=new TextureRegion(texturebuttonmuteoff,0,0,100,104);
					Drawable buttonmuteregion=new TextureRegionDrawable(regionbuttonmuteoff);
					
					Texture texturebuttonmuteondown=new Texture(Gdx.files.internal("data/Images/Buttonmuteoffdown.png"));
					texturebuttonmuteondown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					TextureRegion regionbuttonmuteondown=new TextureRegion(texturebuttonmuteondown,0,0,100,104);
					Drawable buttonmutedownregion=new TextureRegionDrawable(regionbuttonmuteondown);
					
					buttonstyle=new ButtonStyle(buttonmuteregion, buttonmutedownregion, buttonmuteregion);
					buttonmute.setStyle(buttonstyle);
				}
			}
        });
		
		
		//ButtonOk
		Texture texturebuttonokup=new Texture(Gdx.files.internal("data/Images/Buttonok.png"));
		texturebuttonokup.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonokup=new TextureRegion(texturebuttonokup,0,0,100,104);
		Drawable buttonokupregion=new TextureRegionDrawable(regionbuttonokup);
		
		Texture texturebuttonokchecked=new Texture(Gdx.files.internal("data/Images/Buttonokchecked.png"));
		texturebuttonokchecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonokchecked=new TextureRegion(texturebuttonokchecked,0,0,100,104);
		Drawable buttonokcheckedregion=new TextureRegionDrawable(regionbuttonokchecked);
		
		ButtonStyle buttonokstyle=new ButtonStyle(buttonokupregion, buttonokcheckedregion, buttonokcheckedregion);
		final Button buttonok=new Button(buttonokstyle);
		buttonok.setBounds(stage.getWidth()/2, 0,100,104);
		buttonok.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {return true;}
			public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				if(buttonok.isChecked())
				{
					game.mydata.soundclick.play(game.mydata.settings.SoundV);
					game.mydata.settings.setSettings();
					game.setScreen( new MenuScreen( game ) );//cambiar a pantalla preferencias
				}
		    } 
		});
		
		
		//Buttonback
		Texture texturebuttonback=new Texture(Gdx.files.internal("data/Images/Buttonback.png"));
		texturebuttonback.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonback=new TextureRegion(texturebuttonback,0,0,100,104);
		Drawable buttonbackregion=new TextureRegionDrawable(regionbuttonback);
		
		Texture texturebuttonbackchecked=new Texture(Gdx.files.internal("data/Images/Buttonbackchecked.png"));
		texturebuttonbackchecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionbuttonbackchecked=new TextureRegion(texturebuttonbackchecked,0,0,100,104);
		Drawable buttonbackcheckedregion=new TextureRegionDrawable(regionbuttonbackchecked);
		
		ButtonStyle buttonbackstyle=new ButtonStyle(buttonbackregion, buttonbackcheckedregion, buttonbackcheckedregion);
		final Button buttonback=new Button(buttonbackstyle);
		buttonback.setBounds(stage.getWidth()/2-100, 0,100,104);
		buttonback.addListener(new InputListener()
		{
			public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {return true;}
			public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
		    {   
				if(buttonback.isChecked())
				{
					game.mydata.settings=backupsettings;
					game.mydata.soundclick.play(game.mydata.settings.SoundV);
					game.mydata.nowplaying.setVolume(game.mydata.settings.MusicV);
					game.mydata.setcursor();
					game.setScreen( new MenuScreen( game ) );//cambiar a pantalla preferencias
				}
		    } 
		});
		stage.addActor(MusicSlider);
		stage.addActor(SoundSlider);
		stage.addActor(buttonmute);
		stage.addActor(staples);
		stage.addActor(Titlelo);
		stage.addActor(Title);
		stage.addActor(Titlesm);
		stage.addActor(mypaperlist);
		stage.addActor(CheckmycolorBlue);
		stage.addActor(CheckmycolorRed);
		stage.addActor(buttonok);
		stage.addActor(buttonback);
	}
    @Override public void render( float delta )
    {
    	super.render( delta );
    	batch.begin();
		font.draw(batch, "Music", 10, (float)(Gdx.graphics.getHeight()/3+48));
		font.draw(batch, "Sound", 10, (float)(Gdx.graphics.getHeight()/2.25+45));
		
		font.draw(batch, "I want to use paper from...",(float)(Gdx.graphics.getWidth()/1.3), (float)(Gdx.graphics.getHeight()/1.55) );
		font.draw(batch, "I prefer to be a...",(float)(Gdx.graphics.getWidth()/1.3), (float)(Gdx.graphics.getHeight()/2.5) );
		
		batch.end();
		
    }
       
    @Override public void dispose()      
    {
    	super.dispose();
    }
}
