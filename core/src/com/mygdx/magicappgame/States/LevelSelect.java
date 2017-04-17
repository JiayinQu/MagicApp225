package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private ArrayList <Label> levelList;
    private int count;
    private Label selectedLevel;

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

        levelList = new ArrayList<Label>();
        setUpLevels();
        count = 1;
        selectedLevel = null;
    }

        private void setUpLevels() {
            Label level1 = new Label("Level 1", MyGdxGame.gameSkin);
            Label level2 = new Label("Level 2", MyGdxGame.gameSkin);
            Label level3 = new Label("Level 3", MyGdxGame.gameSkin);
            Label level4 = new Label("Level 4", MyGdxGame.gameSkin);
            Label level5 = new Label("Level 5", MyGdxGame.gameSkin);
            levelList.add(level5);
            levelList.add(level4);
            levelList.add(level3);
            levelList.add(level2);
            levelList.add(level1);

            for (final Label level:
                 levelList) {
                level.setPosition(xPos, yPos*count + 75);
                level.setWidth(50);
                level.setHeight(40);
                stage.addActor(level);

                level.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.app.log("button", "pressed");
                        return true;
                    }
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        selectedLevel = level;
                    }
                });

                count++;
            }
        }

        private Level whichLevel() {
            Level returnLevel = null;
            if (selectedLevel != null) {
                if (selectedLevel == levelList.get(4))
                    returnLevel = new Level1(game);
                if (selectedLevel == levelList.get(3))
                    returnLevel = new Level2(game);
                if (selectedLevel == levelList.get(2))
                    returnLevel = new Level3(game);
                if (selectedLevel == levelList.get(1))
                    returnLevel = new Level4(game);
                if (selectedLevel == levelList.get(0))
                    returnLevel = new Level5(game);
            }
            return returnLevel;
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
