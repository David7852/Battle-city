package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen extends AbstractScreen
{
	private Texture texturesplash;
    private Image splashImage;


	public SplashScreen(MyGdxGame game)
	{
		super(game);
		game.mydata.loadmenutunes();

	}
	
	@Override public void show()
    {
		super.show();
		game.mydata.sorttunes(game);
		//game.mydata.musicMenu.play();

		texturesplash = new Texture(Gdx.files.internal("data/Images/Splash.png"));
		texturesplash.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // en nuestro imagen atlas, nuestra imagen splash empieza en el (0,0) en la esquina superior izquierda y tiene una dimensi�n de  512x301
		TextureRegion regionsplash = new TextureRegion(texturesplash,0,0,1280,800);
		TextureRegionDrawable splashregion = new TextureRegionDrawable(regionsplash);

        //aqu� creamos el actor de la imagen de splah. El tama�o ser� definido cuando sea llamado el m�todo resize().
		splashImage = new Image( splashregion, Scaling.stretch);
        splashImage.setFillParent(true);
        
        // esto es necesario para que el efecto fade-in funcione correctamene. Solo hacce la im�gen completamente transparente
        splashImage.getColor().a = 0f;
        
        //lista de acciones
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
            
    @Override public void render( float delta )
    {
    	super.render( delta );

    }
       
    @Override public void dispose()      
    {
    	super.dispose();
    	if(texturesplash!=null)
    		texturesplash.dispose();
    }
}
