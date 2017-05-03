package com.mygdx.magicappgame.States;

/**
 * Created by Fouad El Hamdouni on 01/03/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.magicappgame.MyGdxGame;

public class LoadingScreen implements Screen {

    private final MyGdxGame game;

    private ShapeRenderer shapeRenderer;
    private float progress;
    private Stage stage;

    public LoadingScreen(final MyGdxGame game) {
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
    }

    private void queueAssets() {
        Gdx.files.internal("badlogic.png");
        Gdx.files.internal("skin/flat-earth-ui.json");
        Gdx.files.internal("skin/flat-earth-ui.atlas");
    }

    @Override
    public void show() {
        System.out.println("LOADING");

        stage = new Stage();

        Label loadingLabel = new Label("LOADING IN PROGRESS...", MyGdxGame.gameSkin);
        loadingLabel.setFontScale(1.5f);
        loadingLabel.setColor(Color.BLUE);
        loadingLabel.setWidth(Gdx.graphics.getWidth() / 16);
        loadingLabel.setFontScale(1);
        loadingLabel.setPosition(0, 15);

        this.progress = 1f;
        queueAssets();

        stage.addActor(loadingLabel);

    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, game.assets.getProgress(), .01f);
        if (game.assets.update() && progress >= game.assets.getProgress() - .001f) {
            game.setScreen(game.newMainMenu);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(10, game.V_HEIGHT / 2, game.V_WIDTH * 3, 50);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(10, game.V_HEIGHT / 2, progress * (game.V_WIDTH *3), 50);
        shapeRenderer.end();

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
        shapeRenderer.dispose();
    }
}
