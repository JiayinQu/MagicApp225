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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.levels.Level;
import com.mygdx.magicappgame.levels.Level1;
import com.mygdx.magicappgame.levels.Level2;
import com.mygdx.magicappgame.levels.Level3;
import com.mygdx.magicappgame.levels.Level4;
import com.mygdx.magicappgame.levels.Level5;

import java.util.ArrayList;

/**
 * Created by Ansel on 4/15/2017.
 *
 */

public class LevelSelect implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private ArrayList <Image> levelList;
    private int count;
    private Image selectedLevel;

    private int difficulty;

    private OrthographicCamera camera;
    private Viewport viewport;
    private float xPos;
    private float yPos;



    public LevelSelect(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        stage = new Stage(viewport);

        xPos = (viewport.getWorldWidth() / 2) - 25;
        yPos = viewport.getWorldHeight() / 7;

        difficulty = 1;
        levelList = new ArrayList<Image>();
        setUpLevels();
        count = 1;
        selectedLevel = null;
    }

        private void setUpLevels() {
            Image level1 = new Image(new Texture("tetriformButtonImages/level1.png"));
            Image level2 = new Image(new Texture("tetriformButtonImages/level2.png"));
            Image level3 = new Image(new Texture("tetriformButtonImages/level3.png"));
            Image level4 = new Image(new Texture("tetriformButtonImages/level4.png"));
            Image level5 = new Image(new Texture("tetriformButtonImages/level5.png"));

            levelList.add(level5);
            levelList.add(level4);
            levelList.add(level3);
            levelList.add(level2);
            levelList.add(level1);

            for (final Image level:
                 levelList) {
                level.setPosition(xPos - level.getWidth()/6, yPos*count + 75);
                level.setWidth(level.getWidth()/1.7f);
                level.setHeight(level.getHeight()/1.7f);
                stage.addActor(level);

                level.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        level.setColor(Color.BLUE);
                        return true;
                    }
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        level.setColor(Color.WHITE);
                        selectedLevel = level;
                    }
                });

                count++;
            }
        }

        private Level whichLevel() {
            Level returnLevel = null;
            if (selectedLevel != null) {
                if (selectedLevel == levelList.get(4)) {
                    returnLevel = new Level1(game);
                    game.playScreen.setLevelNum(1);
                } else if (selectedLevel == levelList.get(3)) {
                    returnLevel = new Level2(game);
                    game.playScreen.setLevelNum(2);
                } else if (selectedLevel == levelList.get(2)) {
                    returnLevel = new Level3(game);
                    game.playScreen.setLevelNum(3);
                } else if (selectedLevel == levelList.get(1)) {
                    returnLevel = new Level4(game);
                    game.playScreen.setLevelNum(4);
                } else if (selectedLevel == levelList.get(0)) {
                    returnLevel = new Level5(game);
                    game.playScreen.setLevelNum(5);
                }
            } return returnLevel;
        }

        @Override
        public void show() {
            Gdx.input.setInputProcessor(stage);
        }

        @Override
        public void render(float delta) {
            checkSwitch();
            Gdx.gl.glClearColor(0,0,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.draw();
            camera.update();
        }

        private void checkSwitch() {
            Level whichLevel = whichLevel();
            if (whichLevel != null) {
                refresh();
                game.playScreen.setCurrentLevel(whichLevel);
                game.setScreen(game.playScreen);
            }
        }

        private void refresh() {
            selectedLevel = null;
            count = 5;
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
