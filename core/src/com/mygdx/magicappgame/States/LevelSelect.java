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
import com.badlogic.gdx.utils.Array;
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
 * Created by Ansel on 4/15/2017.
 *
 */

public class LevelSelect implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private ArrayList <Image> oddLevelList;
    private ArrayList <Image> evenLevelList;
    //private ArrayList <Image> difficultyList;

    //private  int count;
    private int countOdd;
    private int countEven;
    private Image selectedLevel;

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

        xPos = (viewport.getWorldWidth() / 2) - 100;
        yPos = viewport.getWorldHeight() / 5;

        oddLevelList = new ArrayList<Image>();
        evenLevelList = new ArrayList<Image>();
        //difficultyList = new ArrayList<Image>();
        setUpLevels();
        //count = 1;
        countOdd = 1;
        countEven = 1;
        selectedLevel = null;
    }

        private void setUpLevels() {
            Image level1 = new Image(new Texture("tetriformButtonImages/level1.png"));
            Image level2 = new Image(new Texture("tetriformButtonImages/level2.png"));
            Image level3 = new Image(new Texture("tetriformButtonImages/level3.png"));
            Image level4 = new Image(new Texture("tetriformButtonImages/level4.png"));
            Image level5 = new Image(new Texture("tetriformButtonImages/level5.png"));
            Image level6 = new Image(new Texture("tetriformButtonImages/level6.png"));
            Image level7 = new Image(new Texture("tetriformButtonImages/level7.png"));
            Image level8 = new Image(new Texture("tetriformButtonImages/level8.png"));
            Image level9 = new Image(new Texture("tetriformButtonImages/level9.png"));
            Image level10 = new Image(new Texture("tetriformButtonImages/level10.png"));

            evenLevelList.add(level10);
            oddLevelList.add(level9);
            evenLevelList.add(level8);
            oddLevelList.add(level7);
            evenLevelList.add(level6);
            oddLevelList.add(level5);
            evenLevelList.add(level4);
            oddLevelList.add(level3);
            evenLevelList.add(level2);
            oddLevelList.add(level1);

            for (final Image level : oddLevelList) {
                level.setPosition(xPos - level.getWidth() / 2 + 100, yPos * countOdd/2  + 150);
                level.setWidth(level.getWidth() / 2.5f);
                level.setHeight(level.getHeight() / 2.5f);
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
                countOdd++;
            }

            for (final Image level : evenLevelList) {
                level.setPosition(xPos - level.getWidth() / 2 + 250, yPos * countEven/2  + 150);
                level.setWidth(level.getWidth() / 2.5f);
                level.setHeight(level.getHeight() / 2.5f);
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
                countEven++;
            }

            /*Image normal = new Image(new Texture("tetriformButtonImages/normal.png"));
            Image hard = new Image(new Texture("tetriformButtonImages/hard.png"));
            Image legendary = new Image(new Texture("tetriformButtonImages/legendary.png"));

            difficultyList.add(normal);
            difficultyList.add(hard);
            difficultyList.add(legendary);

            for (final Image challenge: difficultyList){
                challenge.setPosition(xPos * count * (float) 1.2 +30, yPos);
                challenge.setWidth(challenge.getWidth() / 2.5f);
                challenge.setHeight(challenge.getHeight()/2.5f);
                stage.addActor(challenge);

                challenge.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        challenge.setColor(Color.BLUE);
                        return true;
                    }

                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        challenge.setColor(Color.WHITE);
                    }
                });
                count++;
            }*/
        }


        private Level whichLevel() {
            Level returnLevel = null;
            if (selectedLevel != null) {
                if (selectedLevel == oddLevelList.get(4)) {
                    returnLevel = new Level1(game);
                    game.playScreen.setLevelNum(1);
                } else if (selectedLevel == evenLevelList.get(4)) {
                    returnLevel = new Level2(game);
                    game.playScreen.setLevelNum(2);
                } else if (selectedLevel == oddLevelList.get(3)) {
                    returnLevel = new Level3(game);
                    game.playScreen.setLevelNum(3);
                } else if (selectedLevel == evenLevelList.get(3)) {
                    returnLevel = new Level4(game);
                    game.playScreen.setLevelNum(4);
                } else if (selectedLevel == oddLevelList.get(2)) {
                    returnLevel = new Level5(game);
                    game.playScreen.setLevelNum(5);
                } else if (selectedLevel == evenLevelList.get(2)) {
                    returnLevel = new Level6(game);
                    game.playScreen.setLevelNum(6);
                }else if (selectedLevel == oddLevelList.get(1)) {
                    returnLevel = new Level7(game);
                    game.playScreen.setLevelNum(7);
                }else if (selectedLevel == evenLevelList.get(1)) {
                    //returnLevel = new Level8(game);
                    game.playScreen.setLevelNum(8);
                }else if (selectedLevel == oddLevelList.get(0)) {
                    //returnLevel = new Level9(game);
                    game.playScreen.setLevelNum(9);
                }else if (selectedLevel == evenLevelList.get(0)) {
                    //returnLevel = new Level10(game);
                    game.playScreen.setLevelNum(10);
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
            countOdd = 5;
            countEven = 5;
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
