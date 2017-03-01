package com.mygdx.magicappgame.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by Jiayin Qu on 2017/2/12.
 */
public class Hud {

    public Stage stage;
    private Viewport viewport;


    public Hud(SpriteBatch sb){

        viewport = new FitViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        stage.addActor(table);
    }
}
