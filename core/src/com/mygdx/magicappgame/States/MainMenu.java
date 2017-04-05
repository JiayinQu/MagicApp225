package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by Fouad El Hamdouni on 16/03/2017.
 */

public class MainMenu implements Screen{

    MyGdxGame game;
    private Stage stage; //** stage holds the Button **//
    private BitmapFont font;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton button, instructionsButton, exitButton;


    public MainMenu(MyGdxGame game)
    {
        this.game=game;

    }

    @Override
    public void show() {


        buttonsAtlas = new TextureAtlas(Gdx.files.internal("button/button.pack")); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        font = new BitmapFont(Gdx.files.internal("font/new.fnt"),false); //** font **//

        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

        style.font = font;

        button = new TextButton("START", style);
        //** Button text and style **//
        button.setHeight(Gdx.graphics.getHeight()/3); //** Button Height **//
        button.setWidth(Gdx.graphics.getWidth()/4); //** Button Width **//

        instructionsButton = new TextButton("Instructions", style);
        instructionsButton.setHeight(Gdx.graphics.getHeight()/3);
        instructionsButton.setWidth(Gdx.graphics.getWidth()/4);




        exitButton = new TextButton("Exit", style);
        //** Button text and style **//
        exitButton.setHeight(Gdx.graphics.getHeight()/3); //** Button Height **//
        exitButton.setWidth(Gdx.graphics.getWidth()/4); //** Button Width **//


        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
                game.setScreen(game.playScreen);

            }
        });

        instructionsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Instructions");

            }
        });



        exitButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Exited");
                Gdx.app.exit();

            }
        });


        MoveToAction moveAction = new MoveToAction();//Add dynamic movement effects to button
        moveAction.setPosition(Gdx.graphics.getWidth()/2-button.getWidth()/2, Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight() / 6);
        moveAction.setDuration(.50f);
        button.addAction(moveAction);

        MoveToAction moveToActionInstr = new MoveToAction();
        moveToActionInstr.setPosition(Gdx.graphics.getWidth()/2-instructionsButton.getWidth()/2, Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight() / 8);
        moveToActionInstr.setDuration(.50f);
        instructionsButton.addAction(moveToActionInstr);

        MoveToAction moveToActionExit = new MoveToAction();
        moveToActionExit.setPosition(Gdx.graphics.getWidth()/2-exitButton.getWidth()/2, Gdx.graphics.getHeight()/12);
        moveToActionExit.setDuration(.50f);
        exitButton.addAction(moveToActionExit);


        stage.addActor(button);
        stage.addActor(exitButton);
        stage.addActor(instructionsButton);

    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        game.batch.begin();
        stage.draw();
        game.batch.end();

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
