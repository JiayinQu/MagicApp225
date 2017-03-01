package com.mygdx.magicappgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.magicappgame.States.MenuState;
import com.mygdx.magicappgame.States.PlayScreen;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public static final int V_WIDTH = 208;
	public static final int V_HEIGHT = 400;
	public static final float PPM = 100;

	static public Skin gameSkin;
	//skin licence: http://creativecommons.org/licenses/by/4.0/

	@Override
	public void create () {
		gameSkin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

		batch = new SpriteBatch();
		setScreen(new MenuState(this));
		//setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

}
