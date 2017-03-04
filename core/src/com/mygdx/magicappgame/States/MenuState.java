package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;


/**
 * Created by Jiayin Qu on 2017/2/26.
 *
 */

public class MenuState implements Screen{
    private Stage stage;

    private MyGdxGame game;

    MenuState(MyGdxGame BalanceGame, final PlayScreen playScreen){
        game = BalanceGame;
        Viewport viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        TextButton playButton = new TextButton("Start",MyGdxGame.gameSkin);
        playButton.getLabel().setFontScale(0.5f);
        playButton.setWidth(Gdx.graphics.getWidth()/16);
        //playButton.setPosition(Gdx.graphics.getWidth()/2 - playButton.getWidth()/2,Gdx.graphics.getHeight()/2 - playButton.getHeight()/2);
        playButton.setPosition(90,60);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event,float x, float y, int pointer, int button ){
                game.setScreen(playScreen);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });
        stage.addActor(playButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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
