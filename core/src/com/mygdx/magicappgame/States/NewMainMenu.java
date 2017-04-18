package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by anselcolby on 4/17/2017.
 *
 */

public class NewMainMenu implements Screen {
    private  MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport port;
    private Stage stage;

    private Image bg;
    private Label start;
    private Label levels;
    private Label quit;


    public NewMainMenu(MyGdxGame game) {
        this.game = game;
        setup();
        drawBg();

    }

    private void setup() {
        camera = new OrthographicCamera();
        port = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        camera.position.set(port.getWorldWidth(), port.getWorldHeight(), 0);
        stage = new Stage(port);
    }

    private void drawBg() {
        Texture bgTex = new Texture("tetriformMain.png");
        Drawable bgDraw = new TextureRegionDrawable(new TextureRegion(bgTex));
        bg = new Image(bgDraw);

        bg.setSize(bg.getWidth()/1.7f, bg.getHeight()/1.7f);
        bg.setPosition((port.getWorldWidth()/2) - (bg.getWidth()/2),
                (port.getWorldHeight()/2) - (bg.getHeight()/2));
        stage.addActor(bg);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        start = new Label("", MyGdxGame.gameSkin);
        start.setSize(100, 50);
        start.setPosition(160, 215);

        start.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;

            } public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("START", "released");
                game.setScreen(game.playScreen);
            }
        });

        stage.addActor(start);
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
