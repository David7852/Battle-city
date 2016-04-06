package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
/*import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;*/

public abstract class AbstractScreen implements Screen
{
	// the fixed viewport dimensions (ratio: 1.6)
    public static final int GAME_VIEWPORT_WIDTH = 1280, GAME_VIEWPORT_HEIGHT = 800;
    public static final int MENU_VIEWPORT_WIDTH = 1280, MENU_VIEWPORT_HEIGHT = 800;

    protected final MyGdxGame game;
    protected final Stage stage;

    protected BitmapFont font;
    protected SpriteBatch batch;
    //private Skin skin;
    //private Table table;
    private TextureAtlas atlas;
    

    public AbstractScreen(MyGdxGame game )
    {
        this.game = game;
        int width = ( isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH );
        int height = ( isGameScreen() ? GAME_VIEWPORT_HEIGHT : MENU_VIEWPORT_HEIGHT );
        this.stage = new Stage( width, height, true );
    }

    protected String getName()
    {
        return getClass().getSimpleName();
    }

    protected boolean isGameScreen()
    {
        return false;
    }

    // Lazily loaded collaborators

    public BitmapFont getFont()
    {
        if( font == null ) {
            font = new BitmapFont();
        }
        return font;
    }

    public SpriteBatch getBatch()
    {
        if( batch == null ) {
            batch = new SpriteBatch();
        }
        return batch;
    }

    public TextureAtlas getAtlas()
    {
        if( atlas == null ) {
            atlas = new TextureAtlas( Gdx.files.internal( "image-atlases/pages.atlas" ) );
        }
        return atlas;
    }

/*    protected Skin getSkin()
    {
        if( skin == null ) {
            FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
            skin = new Skin( skinFile );
        }
        return skin;
    }*/

  /*  protected Table getTable()
    {
        if( table == null ) {
            table = new Table( getSkin() );
            table.setFillParent( true );
            if( Tyrian.DEV_MODE ) {
                table.debug();
            }
            stage.addActor( table );
        }
        return table;
    }*/

    
        
    @Override public void render(float delta) 
    {
    	stage.act(delta);
    	if(!game.mydata.nowplaying.isPlaying())
    		game.mydata.sorttunes(game); 
    	Gdx.gl.glClearColor(0f, 0f, 0f, 0f); 
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    stage.draw();
    }

        
    @Override public void resize(int width, int height) 
    {
    	
    }

        
    @Override public void show() 
    {
    	Gdx.input.setInputProcessor( stage );
    	
		Texture texturefondo = new Texture(Gdx.files.internal("data/Images/"+game.mydata.settings.Mypapername+".png"));
		texturefondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion regionfondo = new TextureRegion(texturefondo, 0, 0, 1280, 800);
    	Image fondo=new Image(regionfondo);
		fondo.setFillParent(true);
		
		stage.addActor(fondo);
    }

       
    @Override public void hide() 
    {
    	dispose();
    }

       
    @Override public void pause() 
    {
    	
    }

       
    @Override public void resume() 
    {
    	
    }

        
    @Override public void dispose() 
    {
        stage.dispose();
        if (font != null)
        	font.dispose();
        if( font != null ) 
        	font.dispose();
        if( batch != null ) 
        	batch.dispose();
        if( atlas != null ) 
        	atlas.dispose();
    }
}
