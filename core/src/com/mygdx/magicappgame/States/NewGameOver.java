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

import java.util.ArrayList;

/**
 * Created by Jiayin Qu on 2017/4/23.
 */

public class NewGameOver implements Screen {
    private MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport port;
    private Stage stage;
    private ArrayList<Image> imgList;
    private boolean firstCall;

    /**
     * Constructor to initiate game over screen.
     * @param game
     */
    public NewGameOver(MyGdxGame game) {
        this.game = game;
        setup();
        setupImg();
    }

    /**
     * Set up the screen and fit the camera.
     */
    private void setup() {
        camera = new OrthographicCamera();
        port = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        camera.position.set(port.getWorldWidth(), port.getWorldHeight(), 0);
        stage = new Stage(port);
        firstCall = true;

    }

    /**
     * Set up a arraylist that hold all buttons.
     */
    private void setupImg() {
        imgList = new ArrayList<Image>();

        imgList.add(new Image(new Texture("tetriformButtonImages/tryAgain.png")));
        imgList.add(new Image(new Texture("tetriformButtonImages/backToMenu.png")));
    }

    /**
     * Show Game Over title and button on the screen. 
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Image titleIMG = new Image(new Texture("tetriformButtonImages/lose.png"));
        titleIMG.setSize(titleIMG.getWidth()/1.7f, titleIMG.getHeight()/1.7f);
        titleIMG.setPosition((port.getWorldWidth()/2)-(titleIMG.getWidth()/2), (port.getWorldHeight()/6)*5-(titleIMG.getHeight()/2));
        stage.addActor(titleIMG);

        int multiplier = 4;
        for (final Image img: imgList) {
            if (firstCall) {
                img.setSize(img.getWidth() / 1.3f, img.getHeight() / 1.3f);
                img.setPosition((port.getWorldWidth() / 2) - (img.getWidth() / 2), (port.getWorldHeight() / 6) * multiplier - (img.getHeight() / 2));
                multiplier--;
            }

            stage.addActor(img);

            img.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    img.setColor(Color.BLUE);
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    img.setColor(Color.WHITE);
                    if (img == imgList.get(0)) {
                        firstCall = false;
                        game.setScreen(game.playScreen);
                    } else if (img == imgList.get(1)) {
                        firstCall = false;
                        boolean speakerState = game.newMainMenu.getSpeaker();
                        game.setScreen(game.newMainMenu);
                        game.newMainMenu.setSpeaker(speakerState);
                        if(speakerState == false){
                            game.newMainMenu.setSpeakerColor(Color.BLUE);
                        }else if (speakerState == true){
                            game.newMainMenu.setSpeakerColor(Color.WHITE);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
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
