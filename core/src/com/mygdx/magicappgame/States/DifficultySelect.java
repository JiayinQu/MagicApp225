package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.levels.Level;
import com.mygdx.magicappgame.levels.Level1;
import com.mygdx.magicappgame.levels.Level2;
import com.mygdx.magicappgame.levels.Level3;
import com.mygdx.magicappgame.levels.Level4;
import com.mygdx.magicappgame.levels.Level5;
import com.mygdx.magicappgame.levels.Level6;
import com.mygdx.magicappgame.levels.Level7;

import java.util.ArrayList;

/**
 * Created by Jiayin Qu on 2017/4/28.
 */

public class DifficultySelect implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private ArrayList <Image> difficultyList;

    private  int count;
    private Image selectedDifficulty;

    private int difficulty;

    private OrthographicCamera camera;
    private Viewport viewport;
    private float xPos;
    private float yPos;


    public DifficultySelect(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        stage = new Stage(viewport);

        xPos = (viewport.getWorldWidth())/2;
        yPos = viewport.getWorldHeight() /2;

        difficulty = 0;
        difficultyList = new ArrayList<Image>();
        setUpDifficulty();
        count = 1;
        selectedDifficulty = null;
    }

    private void setUpDifficulty() {
        final Image normal = new Image(new Texture("tetriformButtonImages/normal.png"));
        normal.setPosition(xPos - normal.getWidth() / 2 + 70, yPos + 50);
        normal.setWidth(normal.getWidth() / 2f);
        normal.setHeight(normal.getHeight() / 2f);
        stage.addActor(normal);

        normal.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                normal.setColor(Color.BLUE);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                normal.setColor(Color.WHITE);
                difficulty = 1;
                game.setScreen(game.levelSelect);
            }
        });

        final Image hard = new Image(new Texture("tetriformButtonImages/hard.png"));
        hard.setPosition(xPos - hard.getWidth() / 2 + 70, yPos );
        hard.setWidth(hard.getWidth() / 2f);
        hard.setHeight(hard.getHeight() / 2f);
        stage.addActor(hard);

        hard.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hard.setColor(Color.BLUE);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                hard.setColor(Color.WHITE);
                difficulty = 2;
                game.setScreen(game.levelSelect);
            }
        });

        final Image legendary = new Image(new Texture("tetriformButtonImages/legendary.png"));
        legendary.setPosition(xPos - legendary.getWidth() / 2 + 70, yPos - 50);
        legendary.setWidth(legendary.getWidth() / 2f);
        legendary.setHeight(legendary.getHeight() / 2f);
        stage.addActor(legendary);

        legendary.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                legendary.setColor(Color.BLUE);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                legendary.setColor(Color.WHITE);
                difficulty = 3;
                game.setScreen(game.levelSelect);
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        camera.update();
    }


    public int getDifficulty() {
        return difficulty;
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
