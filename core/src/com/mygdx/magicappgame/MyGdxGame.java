package com.mygdx.magicappgame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.magicappgame.States.LevelSelect;
import com.mygdx.magicappgame.States.GameOver;
import com.mygdx.magicappgame.States.MainMenu;
import com.mygdx.magicappgame.States.PlayScreen;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public static final int V_WIDTH = 416;
	public static final int V_HEIGHT = 400;

	public PlayScreen playScreen;
	public GameOver gameOverScreen;
	public LevelSelect levelSelect;
	public MainMenu mainMenu;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setupStates();
		setScreen(mainMenu);
	}

	@Override
	public void render () {
		super.render();
	}

	private void setupStates() {
		gameOverScreen = new GameOver(this);
		mainMenu = new MainMenu(this);
		levelSelect = new LevelSelect(this);

		playScreen = new PlayScreen(this);
		playScreen.setUpLevels();
	}

}
