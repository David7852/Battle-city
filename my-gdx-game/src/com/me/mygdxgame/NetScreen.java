package com.me.mygdxgame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class NetScreen extends AbstractScreen
{
	long t=0;
	String ing="";
	static boolean  w=true,			z=false;
	private Texture texturesplash;
    private Image splashImage;
	public static ServerSocket Alfred;
	public static Socket Batman;
	public int port=Gdx.app.getPreferences("BattleNotebook").getInteger("Port");
    
	
	
	private static Runnable accept = new Runnable()
	{
		@Override
		public void run()
		{
			//new Thread(send).start();
			//new Thread(receive).start();
			
			while (true)
			{
				try
				{
					Batman = Alfred.accept();
					
					w=true;
					
					
				}
				catch (Exception ex) {}
			}
		}
	};
	
/*	private static Runnable send = new Runnable()
	{
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			
			while (true)
			{
				for (int i = 0; i < list_sockets.size(); i++)
				{
					try
					{
						oos = new ObjectOutputStream(list_sockets.get(i).getOutputStream());
						int client_state = list_client_states.get(i);
						oos.writeObject(client_state);
						
						oos = new ObjectOutputStream(list_sockets.get(i).getOutputStream());
						oos.writeObject(list_data);
						
						if (client_state == 1) // Kicked by Server
						{
							disconnectClient(i);
							i--;
						}
						else if (client_state == 2) // Server Disconnected
						{
							disconnectClient(i);
							i--;
						}
					}
					catch (Exception ex) {}
				}
			}
		}
	};
	
	private static Runnable receive = new Runnable()
	{
		@Override
		public void run()
		{
			ObjectInputStream ois;
			
			while (true)
			{
				for (int i = 0; i < list_sockets.size(); i++)
				{
					try
					{
						ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
						int receive_state = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
						DataPackage dp = (DataPackage) ois.readObject();
						
						list_data.set(i, dp);
						
						if (receive_state == 1) // Client Disconnected by User
						{
							disconnectClient(i);
							i--;
						}
					}
					catch (Exception ex) // Client Disconnected (Client Didn't Notify Server About Disconnecting)
					{
						disconnectClient(i);
						i--;
					}
				}
			}
		}
	};*/
	/*
	public static void disconnectClient(int index)
	{
		try
		{
			list_clients_model.removeElementAt(index);
			list_client_states.remove(index);
			list_data.remove(index);
			list_sockets.remove(index);
		}
		catch (Exception ex) {}
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public NetScreen(MyGdxGame game) 
	{
		super(game);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void show() 
	{
		super.show();
		batch=new  SpriteBatch();
		font=new BitmapFont(Gdx.files.internal("data/fonts/chocoM.fnt"));
		
		
		
		
		
		texturesplash = new Texture(Gdx.files.internal("data/Images/prepare.png"));
		texturesplash.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionsplash = new TextureRegion(texturesplash,0,0,1280,800);
		TextureRegionDrawable splashregion = new TextureRegionDrawable(regionsplash);

		splashImage = new Image( splashregion, Scaling.stretch);
        splashImage.setFillParent(true);
        
        splashImage.getColor().a = 0f;
        splashImage.addAction( sequence( 
        fadeIn( 1f ), 
        new Action()
		{   
            @Override
            public boolean act(float delta) {
            	stage.getActors().first().remove();
            	return true;
            }
        }, 
        delay( 0f ),
        new Action()
        {   
        @Override        
        	public boolean act(float delta)     
            {   
	            z=true;//termina la accion
	            return true;
            } 
        } ) );
		
		if(game.mydata.settings.Mycolor)//i am a server
		{
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
			buttonback.setBounds(MENU_VIEWPORT_WIDTH/2-50, 0,100,104);
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
			stage.addActor(buttonback);
			
			//conexion socket	
			
				try 
				{
					Alfred = new ServerSocket(port, 0, InetAddress.getLocalHost());
					new Thread(accept).start();
					
				} catch (UnknownHostException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
				
			//if conexion_exito :: w=true
			
		}else//i am a client
			{
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
			buttonback.setBounds(MENU_VIEWPORT_WIDTH/2-500 , GAME_VIEWPORT_HEIGHT/2-52,100,104);
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
			Texture texture=new Texture(Gdx.files.internal("data/Images/Textfieldback.png"));
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			TextureRegion region=new TextureRegion(texture,0,0,800,158);
			Drawable backregion=new TextureRegionDrawable(region);
			
			Texture TextureSliderknob=new Texture(Gdx.files.internal("data/Images/Sliderknob.png"));
			TextureSliderknob.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			TextureRegion RegionSliderknob=new TextureRegion(TextureSliderknob,0,0,35,35);
			Drawable Sliderknobregion = new TextureRegionDrawable(RegionSliderknob);
			
			Texture textureselection=new Texture(Gdx.files.internal("data/Images/Shiny yellow.png"));
			textureselection.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			TextureRegion RegionSelection=new TextureRegion(textureselection,0,0,304,70);
			Drawable SelectionRegion=new TextureRegionDrawable(RegionSelection);
			
			TextFieldStyle textfieldstyle=new TextFieldStyle(font,Color.BLACK,Sliderknobregion,SelectionRegion,backregion);
			textfieldstyle.background.setLeftWidth(textfieldstyle.background.getLeftWidth() + 280);
			textfieldstyle.cursor.setMinWidth(15);
			final TextField line=new TextField("", textfieldstyle);
			line.setBounds(MENU_VIEWPORT_WIDTH/2-400, GAME_VIEWPORT_HEIGHT/2-79, 800, 158);
			line.setMessageText("Enter a hostname");
			
			Texture texturebuttonnext=new Texture(Gdx.files.internal("data/Images/Buttonnext.png"));
			texturebuttonnext.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			TextureRegion regionbuttonnext=new TextureRegion(texturebuttonnext,0,0,100,104);
			Drawable buttonnextregion=new TextureRegionDrawable(regionbuttonnext);
			
			Texture texturebuttonnextchecked=new Texture(Gdx.files.internal("data/Images/Buttonnextchecked.png"));
			texturebuttonnextchecked.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			TextureRegion regionbuttonnextchecked=new TextureRegion(texturebuttonnextchecked,0,0,100,104);
			Drawable buttonnextcheckedregion=new TextureRegionDrawable(regionbuttonnextchecked);
			
			ButtonStyle buttonnextstyle=new ButtonStyle(buttonnextregion, buttonnextcheckedregion, buttonnextcheckedregion);
			final Button buttonnext=new Button(buttonnextstyle);
			buttonnext.setBounds( MENU_VIEWPORT_WIDTH/2+400, GAME_VIEWPORT_HEIGHT/2-52,100,104);
			buttonnext.addListener(new InputListener()
			{
				public boolean touchDown ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
			    {return true;}
				public void touchUp ( InputEvent  event, float x, float y, int pointer, int button ) //evento del boton
			    {   
					if(buttonnext.isChecked())
					{						
						game.mydata.soundclick.play(game.mydata.settings.SoundV);
						//conexion socket
						try
						{
							w=true;
							String	local;
							try 
							{
								local = InetAddress.getLocalHost().getHostAddress() + ":" + port;
							} catch (UnknownHostException e) {w=false;} catch (IOException e) {w=false;}
							//if conexion_exito :: w=true
							local=line.getText();
							Batman = new Socket(InetAddress.getLocalHost().getHostAddress(), port);
						}catch (Exception ex)
						{
							w=false;
						}
					}
			    } 
			});
			stage.addActor(buttonback);
			stage.addActor(buttonnext);			
			stage.addActor(line);
				
			}
		
	}
	@Override public void render( float delta )
	{
	    	super.render( delta );
	    	batch.begin();
	    	long e=System.nanoTime();
	    	String str;
	    	if(game.mydata.settings.Mycolor)//i am a server
			{
	    		str="Esperando conexion del cliente";
	    		if(e>=t+Tank.slow*2)
				{
					t=e;
					ing=ing+".";
					if(ing.equals("...."))
						ing="";
				}
	    		try {
					font.draw(batch,  "Hostname: "+ InetAddress.getLocalHost().getHostAddress() + ":" + port, MENU_VIEWPORT_WIDTH/2-240, GAME_VIEWPORT_HEIGHT/2);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					font.draw(batch,"Network error", MENU_VIEWPORT_WIDTH/2-250, GAME_VIEWPORT_HEIGHT-50);
				}
	    		
			}else//i am a client
				{
					str="Esperando respuesta del servidor";
					if(e>=t+Tank.slow*2)
					{
						t=e;
						ing=ing+".";
						if(ing.equals("...."))
							ing="";
					}
				}
	    	font.draw(batch, str+ing, MENU_VIEWPORT_WIDTH/2-250, GAME_VIEWPORT_HEIGHT-50);
			batch.end();
			if(w&&splashImage!=null)//si la conexion fue exitosa w=true;
			{ 
				stage.addActor( splashImage );
				w=false;
			}
			if(z)//si z=true, fadeout complete
				{
					game.mydata.loadbattletunes();
					game.mydata.loadmap();
					//game.setScreen( new StartGameScreen( game,Alfred,Batman ) );
				}
	}	       
	@Override public void dispose()      
	{
	    	super.dispose();
	    	if(texturesplash!=null)
	    		texturesplash.dispose();
	}
	  @Override public void hide() 
	    {
	    	dispose();
	    }
}
