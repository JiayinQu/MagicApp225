package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;

import java.util.ArrayList;

/**
 * Created by anselcolby on 4/17/2017.
 *
 */

public class MainMenu implements Screen {

    private  MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport port;
    private Stage stage;
    private ArrayList<Image> imgList;
    private boolean firstCall;
    private boolean speakerOff;
    private Image speakerImage;
    private Sound startSound;

    /**
     * Constructor to initiate main menu screen
     * @param game the initial game class
     */
    public MainMenu(MyGdxGame game) {
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
        speakerOff = false;

        startSound = Gdx.audio.newSound(Gdx.files.internal("sound/Hearbeat.mp3"));

    }

    /**
     * Set up an arraylist that hold all buttons.
     */
    private void setupImg() {
        imgList = new ArrayList<Image>();

        imgList.add(new Image(new Texture("tetriformButtonImages/tetriformPlay.png")));
        imgList.add(new Image(new Texture("tetriformButtonImages/tetriformLevelSelect.png")));
        imgList.add(new Image(new Texture("tetriformButtonImages/tetriformQuit.png")));
    }

    /**
     * Show the title and buttons on the screen.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        if(!speakerOff){
            startSound.play(1.0f);
            startSound.loop();
        }

        //Add title Tetriform
        Image titleIMG = new Image(new Texture("tetriformButtonImages/tetriformTitle.png"));
        titleIMG.setSize(titleIMG.getWidth()/1.7f, titleIMG.getHeight()/1.7f);
        titleIMG.setPosition((port.getWorldWidth()/2)-(titleIMG.getWidth()/2), (port.getWorldHeight()/6)*5-(titleIMG.getHeight()/2));
        stage.addActor(titleIMG);

        //Add a speaker button that can control whether to turn on the sound.
        final Texture speakerButton =  new Texture("speaker.png");
        Drawable speakerDrawable = new TextureRegionDrawable(new TextureRegion(speakerButton));
        speakerImage = new Image(speakerDrawable);
        speakerImage.setHeight(25);
        speakerImage.setWidth(25);
        speakerImage.setColor(Color.BLUE);
        speakerImage.setPosition((port.getWorldWidth() / 2) - speakerImage.getWidth() / 2, 50);
        stage.addActor(speakerImage);

        speakerImage.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("speaker", "Pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(!speakerOff){
                    speakerImage.setColor(Color.WHITE);
                    startSound.stop();
                    speakerOff = true;
                }else if(speakerOff){
                    speakerImage.setColor(Color.BLUE);
                    startSound.play(1.0f);
                    speakerOff = false;
                }

            }
        });

        //Other buttons.
        int multiplier = 4;
        for (final Image img:
             imgList) {

            if (firstCall) {
                img.setSize(img.getWidth() / 1.7f, img.getHeight() / 1.7f);
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
                        //startSound.stop();
                    } else if (img == imgList.get(1)) {
                        firstCall = false;
                        game.setScreen(game.levelSelect);
                    } else if (img == imgList.get(2)) {
                        Gdx.app.exit();
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

    /**
     * @return the state of the speaker
     */
    boolean getSpeaker(){
        return speakerOff;
    }

    void setSpeaker(boolean speakerState){
        speakerOff = speakerState;
    }

    void setSpeakerColor(Color color){
        speakerImage.setColor(color);
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
        startSound.dispose();

    }
}
