package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by Ansel on 4/15/2017.
 */

public class LevelSelect implements Screen {
    MyGdxGame game;
    private Stage stage;


    public LevelSelect(MyGdxGame game) {
        this.game = game;
        stage = new Stage();

    }

        @Override
        public void show() {
            Gdx.input.setInputProcessor(stage);

            Button level1 = new Button();
            level1.setName("Level1");
            level1.setStyle(new Button.ButtonStyle());
            level1.setHeight(30);
            level1.setWidth(30);
            level1.setColor(Color.GREEN);
            level1.setPosition(50, 300);
            stage.addActor(level1);
        }

        @Override
        public void render(float delta) {
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

        }


}
