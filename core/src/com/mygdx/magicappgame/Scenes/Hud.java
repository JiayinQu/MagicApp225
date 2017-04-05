package com.mygdx.magicappgame.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by Jiayin Qu on 2017/2/12.
 *
 */
public class Hud {

    public Stage stage;

    private static Integer level;
    private Integer worldTimer;
    private float timeCount;

    private static Label scoreLabel;
    private static Label levelLabel;
    private Label worldLabel;
    private Label countdownLabel;
    private Label timeLabel;


    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        level = 1;

        Viewport viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        //scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("%02d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(timeLabel).expand().padTop(10f);
        table.add(worldLabel).expandX().padTop(10f);
        table.row();
        table.add(countdownLabel).expandX();
        table.add(levelLabel).expandX();

        stage.addActor(table);
    }


    public void update (float dt){
        timeCount += dt;
        if(timeCount >=1){
            worldTimer --;
            countdownLabel.setText(String.format("%03d",worldTimer));
            timeCount = 0;
        }
    }

    public static void addLevel(){
        level ++;
        levelLabel.setText(String.format("%02d", level));
    }

    public boolean timeOver(){
        return worldTimer == 0;
    }


}
