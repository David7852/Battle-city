package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Random;

public class GameScreen extends AbstractScreen
{

    private float stateTime;
    private float x,y;
    private int lastscore,score,level,sublevel,n,m,result;
    private Random r;
    private Texture rule,pin;
    private Drawable ruledraw,pindraw;
    public long t,b;
    boolean w=true,win=true;

    String string;
    public GameScreen(MyGdxGame game)
    {
        super(game);
        long e=System.nanoTime();
        r=new Random();
        x=(GAME_VIEWPORT_WIDTH/2)+280;y=50;
        stateTime=-1f;
        t=b=e;
        level=1;
        sublevel=0;
        score=0;
        lastscore=0;
        batch=new SpriteBatch();
    }

    @Override
    public void show()
    {
        super.show();
        Texture texture;
        TextureRegion region;
        Drawable drawable,drawablehold;

        rule = new Texture(Gdx.files.internal("data/images/rule.png"));
        rule.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(rule);
        ruledraw = new TextureRegionDrawable(region);

        pin = new Texture(Gdx.files.internal("data/images/Sliderknob.png"));
        pin.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(pin);
        pindraw = new TextureRegionDrawable(region);

        //next button
        texture = new Texture(Gdx.files.internal("data/images/buttonok.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);
        drawable = new TextureRegionDrawable(region);
        region = new TextureRegion(texture,(int)(texture.getWidth()/.9),(int)(texture.getHeight()/.9));
        drawablehold = new TextureRegionDrawable(region);

        final Button nextButton = new Button(drawable,drawablehold);
        nextButton.setBounds((GAME_VIEWPORT_WIDTH/2)-100,25,100,100);
        nextButton.addListener(new InputListener()
        {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button )
            {correcto();
                return true; }
            public void touchUp(InputEvent  event, float x, float y, int pointer, int button )
            {

            }
        });

        texture = new Texture(Gdx.files.internal("data/images/buttonback.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);
        drawable = new TextureRegionDrawable(region);
        region = new TextureRegion(texture,(int)(texture.getWidth()/.9),(int)(texture.getHeight()/.9));
        drawablehold = new TextureRegionDrawable(region);

        final Button noButton = new Button(drawable,drawablehold);
        noButton.setBounds((GAME_VIEWPORT_WIDTH/2),25,100,100);
        noButton.addListener(new InputListener()
        {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button )
            {
                incorrecto();
                return true;
            }
            public void touchUp(InputEvent  event, float x, float y, int pointer, int button )
            {

            }
        });

        stage.addActor(nextButton);
        stage.addActor(noButton);

    }
    public void correcto()
    {
        w=true;
        if(!win)
        {
            win=true;
        }

        if(sublevel>=10)
        {
            level++;
            sublevel = 0;
        }
        if(level>=10)
        {
            level=1;
        }
        if(n*m==result)
        {
            if(x<=(GAME_VIEWPORT_WIDTH/2)+280-100)
            {
                score+=x-200;
                x+=50;
            }
        }
        else
        {
            x -= 120;
        }
        if(x<=200)
            x=200;
    }
    public void incorrecto()
    {
        w=true;
        if(sublevel>=10)
        {
            level++;
            sublevel = 0;
        }
        if(level>=10)
        {
            level=1;
        }
        if(n*m!=result)
        {
            if(x<=(GAME_VIEWPORT_WIDTH/2)+280-100)
            {
                score+=x-200;
                x+=50;
            }
        }
        else
        {
            x -= 120;
        }
        if(x<=200)
            x=200;
    }

    public void loose()
    {
        win=false;
        lastscore=score;
        score=0;
        level=1;
        x=(GAME_VIEWPORT_WIDTH/2)+280;
        sublevel=0;
    }

    public String getN()
    {
        String str="";
        if(level==1)
        {
            str="1";
            n=1;
        }
        if(level==2)
        {
            str="2";
            n=2;
        }
        if(level==3)
        {
            str="3";
            n=3;
        }
        if(level==4)
        {
            str="4";
            n=4;
        }
        if(level==5)
        {
            str="5";
            n=5;
        }
        if(level==6)
        {
            str="6";
            n=6;
        }
        if(level==7)
        {
            str="7";
            n=7;
        }
        if(level==8)
        {
            str="8";
            n=8;
        }
        if(level==9)
        {
            str="9";
            n=9;
        }
        return str;
    }

    public String getM()
    {
        sublevel++;
        int c;
        do
        {
            c=r.nextInt(10);
        }while(c==m);

        String str="";
        if(c==0)
        {
            str="0";
            m=0;
        }
        if(c==1)
        {
            str="1";
            m=1;
        }
        if(c==2)
        {
            str="2";
            m=2;
        }
        if(c==3)
        {
            str="3";
            m=3;
        }
        if(c==4)
        {
            str="4";
            m=4;
        }
        if(c==5)
        {
            str="5";
            m=5;
        }
        if(c==6)
        {
            str="6";
            m=6;
        }
        if(c==7)
        {
            str="7";
            m=7;
        }
        if(c==8)
        {
            str="8";
            m=8;
        }
        if(c==9)
        {
            str="9";
            m=9;
        }
        if(c==10)
        {
            str="10";
            m=10;
        }
        return str;
    }

    public String getresult()
    {
        String str;
        if(r.nextBoolean())
            result=r.nextInt(10*level);
        else
            result=n*m;
        str=""+result;
        return str;
    }

    @Override
    public void render( float delta )
    {
        super.render(delta);
        stage.getSpriteBatch().begin();
        if(win)
        {
        	stage.getSpriteBatch().draw(pin, x, GAME_VIEWPORT_HEIGHT-50, 35, 35);
        	stage.getSpriteBatch().draw(rule, (GAME_VIEWPORT_WIDTH / 2)-450, GAME_VIEWPORT_HEIGHT-160, 900, 160);
            font.drawMultiLine(stage.getSpriteBatch(), "Mejor record="+lastscore, (GAME_VIEWPORT_WIDTH / 2)-80, GAME_VIEWPORT_HEIGHT -300);

            if(w)
            {
                if(r.nextBoolean())
                    string = getN()+"x"+getM()+"="+getresult();
                else
                    string = getM()+"x"+getN()+"="+getresult();
                w = false;
            }
            coolfont.draw(stage.getSpriteBatch(), string, (GAME_VIEWPORT_WIDTH / 2)-30, GAME_VIEWPORT_HEIGHT / 2);

            if(stateTime>0&&x>200)
                x--;
            if(x<=200)
                loose();
            stateTime += Gdx.graphics.getDeltaTime();
            stage.act(delta);
        }
        else
        {
            font.drawMultiLine(stage.getSpriteBatch(), "Buen intento, pero aun puedes mejorar.\ntu record ha sido de :"+lastscore, (GAME_VIEWPORT_WIDTH / 2)-300, GAME_VIEWPORT_HEIGHT / 2);
        }

        stage.getSpriteBatch().end();
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }

}
