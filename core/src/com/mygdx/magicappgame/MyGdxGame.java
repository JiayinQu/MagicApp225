package com.mygdx.magicappgame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.magicappgame.States.GameOverScreen;
import com.mygdx.magicappgame.screens.LoadingScreen;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public static final int V_WIDTH = 208;
	public static final int V_HEIGHT = 400;
	public static final float PPM = 100;
    public AssetManager assets;

	static public Skin gameSkin;
	//skin licence: http://creativecommons.org/licenses/by/4.0/

	@Override
	public void create () {
		gameSkin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

        assets = new AssetManager();
		batch = new SpriteBatch();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

}
