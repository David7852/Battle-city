package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;

public class Settings 
{
	public float MusicV, SoundV,olM,olS;
	public String Mypapername;
	public boolean Mycolor, mute;
	
    public Settings()
    {
    	if(!Gdx.app.getPreferences("BattleNotebook").contains("Default"))
    		setSettingsAsDefault();
    }
    public void getSettings()
    {
    	MusicV=Gdx.app.getPreferences("BattleNotebook").getFloat("MusicV");
    	SoundV=Gdx.app.getPreferences("BattleNotebook").getFloat("SoundV");
    	olM=Gdx.app.getPreferences("BattleNotebook").getFloat("olM");
    	olS=Gdx.app.getPreferences("BattleNotebook").getFloat("olS");
    	Mycolor=Gdx.app.getPreferences("BattleNotebook").getBoolean("Mycolor");
    	mute=Gdx.app.getPreferences("BattleNotebook").getBoolean("Mute");
    	Mypapername=Gdx.app.getPreferences("BattleNotebook").getString("Mypapername");
    }
    public void setSettings()
    {
    	Gdx.app.getPreferences("BattleNotebook").putBoolean("Default",false);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("MusicV", MusicV);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("SoundV", SoundV);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("olM",olM);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("olS",olS);
    	Gdx.app.getPreferences("BattleNotebook").putString("Mypapername", Mypapername);
    	Gdx.app.getPreferences("BattleNotebook").putBoolean("Mycolor", Mycolor);//true azul
    	Gdx.app.getPreferences("BattleNotebook").putBoolean("Mute",mute);
    	Gdx.app.getPreferences("BattleNotebook").flush();
    }

    public static void setSettingsAsDefault()
    {
    	Gdx.app.getPreferences("BattleNotebook").putBoolean("Default",true);
    	Gdx.app.getPreferences("BattleNotebook").putBoolean("Mute", false);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("MusicV", (float) 0.30);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("SoundV", (float) 0.15);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("olM",(float) 0.30);
    	Gdx.app.getPreferences("BattleNotebook").putFloat("olS", (float) 0.15);
    	Gdx.app.getPreferences("BattleNotebook").putString("Mypapername", "Paper 4");
    	Gdx.app.getPreferences("BattleNotebook").putBoolean("Mycolor", true);//true azul
    	Gdx.app.getPreferences("BattleNotebook").putBoolean("Sexycursor", true);//true para... true
    	Gdx.app.getPreferences("BattleNotebook").putInteger("Port", 7373);
    	Gdx.app.getPreferences("BattleNotebook").flush();
    }
    
    public void muteORunmute()
    {
    	if(mute)//si ya esta mudo, se activa el sonido
    	{
    		MusicV=olM;
    		SoundV=olS;
    		Gdx.app.getPreferences("BattleNotebook").putFloat("MusicV", MusicV);
    		Gdx.app.getPreferences("BattleNotebook").putFloat("SoundV", SoundV);
    		Gdx.app.getPreferences("BattleNotebook").putBoolean("Mute",false);
    		mute=false;
    	}else//si no lo esta, se silencia
	    	{
	    		Gdx.app.getPreferences("BattleNotebook").putFloat("olM",MusicV);
	    		Gdx.app.getPreferences("BattleNotebook").putFloat("olS",SoundV);
	    		olM=MusicV;
	    		olS=SoundV;	    		
	    		MusicV=0;SoundV=0;
	    		Gdx.app.getPreferences("BattleNotebook").putFloat("MusicV", (float) 0);
	    		Gdx.app.getPreferences("BattleNotebook").putFloat("SoundV", (float) 0);
	    		Gdx.app.getPreferences("BattleNotebook").putBoolean("Mute",true);
	    		mute=true;
	    	}
    }
}
