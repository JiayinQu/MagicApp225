package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by Jiayin Qu on 2017/3/8.
 */

public class GameOverScreen implements Screen {
    private Stage stage;
    private Viewport viewport;

    private MyGdxGame game;

    public GameOverScreen(MyGdxGame game){
        this.game = game;
        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, ((MyGdxGame) game).batch);

        //Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        //Table table = new Table();
        //table.center();
        //table.setFillParent(true);

        Label gameOverLabel = new Label("Game Over", MyGdxGame.gameSkin);
        gameOverLabel.setFontScale(1.5f);
        gameOverLabel.setColor(Color.RED);

        gameOverLabel.setWidth(Gdx.graphics.getWidth() / 16);
        gameOverLabel.setPosition(35, 250);
        TextButton exitButton = new TextButton("Play Again", MyGdxGame.gameSkin);
        exitButton.getLabel().setFontScale(1f);
        exitButton.setColor(Color.RED);

        exitButton.setWidth(Gdx.graphics.getWidth() / 16);
        exitButton.setPosition(70, 150);

        stage.addActor(gameOverLabel);
        stage.addActor(exitButton);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            game.setScreen(new PlayScreen(game));
            dispose();
        }
        Gdx.gl.glClearColor(0,0,0,1);
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

