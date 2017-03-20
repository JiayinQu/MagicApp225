package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.screens.LoadingScreen;

/**
 * Created by Jiayin Qu on 2017/3/8.
 */

public class GameOverScreen implements Screen {
    private Stage stage;
    private BitmapFont font;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin;
    private TextButton playAgainButton;

    private MyGdxGame game;

    public GameOverScreen(MyGdxGame game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        buttonsAtlas = new TextureAtlas(Gdx.files.internal("button/button.pack")); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);
        font = new BitmapFont(Gdx.files.internal("font/new.fnt"),false); //** font **//

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

        style.font = font;

        Label gameOverLabel = new Label("Game Over", MyGdxGame.gameSkin);
        gameOverLabel.setFontScale(2);
        gameOverLabel.setColor(Color.RED);
        gameOverLabel.setWidth(Gdx.graphics.getWidth() / 10);
        gameOverLabel.setPosition(Gdx.graphics.getWidth() / 2, game.V_HEIGHT / 2);

        playAgainButton = new TextButton("Play Again", style);
        //** Button text and style **//
        playAgainButton.setHeight(Gdx.graphics.getHeight()/3); //** Button Height **//
        playAgainButton.setWidth(Gdx.graphics.getWidth()/4);

        playAgainButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 12);

        playAgainButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(new LoadingScreen(game));
                dispose();

            }
        });

        stage.addActor(gameOverLabel);
        stage.addActor(playAgainButton);




    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

