package com.mygdx.magicappgame.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;

import java.awt.TextField;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDown;
import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;

/**
 * Created by Jiayin Qu on 2017/2/12.
 *
 */
public class Hud {

    public Stage stage;

    private static Integer level;
    private static Integer boxLeft;
    private Integer worldTimer;
    private float timeCount;


    private static Label levelLabel;
    private static Label boxLabel;
    private Label boxLeftLabel;
    private Label worldLabel;
    private Label countdownLabel;
    private Label timeLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 0;
        timeCount = 0;
        level = 1;
        boxLeft = 10;

        Viewport viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("%02d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        boxLabel = new Label(String.format("%02d", boxLeft), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        worldLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        boxLeftLabel = new Label("BOXES LEFT", new Label.LabelStyle(new BitmapFont(),Color.WHITE));

        table.columnDefaults(0).width(150);
        table.add(timeLabel);
        table.add(countdownLabel).expandX();
        table.row();
        table.add(worldLabel);
        table.add(levelLabel).expandX();
        table.row();
        table.add(boxLeftLabel);
        table.add(boxLabel).expandX();
        table.setPosition(0, 160);

        stage.addActor(table);
    }


    public void update (float dt){
        timeCount += dt;
        if(timeCount >=1){
            worldTimer ++;
            countdownLabel.setText(String.format("%03d",worldTimer));
            timeCount = 0;
        }
    }

    public static void addLevel(){
        level ++;
        levelLabel.setText(String.format("%02d", level));
    }

    public static void minusBox(){
        boxLeft --;
        boxLabel.setText(String.format("%02d", boxLeft));
    }

    public void resetTime(){
        worldTimer = 0;
        countdownLabel.setText(String.format("%03d",worldTimer));
    }

    public void resetLevel(){
        level = 1;
        levelLabel.setText(String.format("%02d", level));
    }

    public void resetBox(){
        boxLeft = 10;
        boxLabel.setText(String.format("%02d",boxLeft));
    }

    public Integer getTime(){
        return worldTimer;
    }

}
