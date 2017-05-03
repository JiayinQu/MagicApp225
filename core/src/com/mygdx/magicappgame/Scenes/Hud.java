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


    private static Label levelLabel;
    private static Label boxLabel;
    private Label boxLeftLabel;
    private Label worldLabel;

    private Image instructionImage;

    /**
     * Constructor to initiate a hud.
     * @param sb
     */
    public Hud(SpriteBatch sb) {
        level = 1;
        boxLeft = 10;

        Viewport viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.setFillParent(true);

        levelLabel = new Label(String.format("%02d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        boxLabel = new Label(String.format("%02d", boxLeft), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        worldLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        boxLeftLabel = new Label("BOXES LEFT", new Label.LabelStyle(new BitmapFont(),Color.WHITE));

        table.columnDefaults(0).width(150);
        table.add(worldLabel);
        table.add(levelLabel).expandX();
        table.row();
        table.add(boxLeftLabel);
        table.add(boxLabel).expandX();
        table.setPosition(60, 160);

        stage.addActor(table);

        final Texture instruction = new Texture ("instruction.jpg");
        Drawable instructionDrawable = new TextureRegionDrawable(new TextureRegion(instruction));
        instructionImage = new Image(instructionDrawable);
        instructionImage.setWidth(180);
        instructionImage.setHeight(35);
        instructionImage.setPosition(viewport.getWorldWidth()/2 - instructionImage.getWidth()/2,200);

        stage.addActor(instructionImage);
    }

    /**
     * Set level label to the current level
     * @param levelNum
     */
    public static void getLevel(int levelNum){
        if(levelNum == 1){
            level = 1;
        }
        if(levelNum == 2){
            level = 2;
        }
        if(levelNum == 3){
            level = 3;
        }
        if(levelNum == 4){
            level = 4;
        }
        if(levelNum == 5){
            level = 5;
        }
        if(levelNum == 6){
            level = 6;
        }
        if(levelNum == 7){
            level = 7;
        }
        if(levelNum == 8){
            level = 8;
        }
        if(levelNum == 9){
            level = 9;
        }
        if(levelNum == 10){
            level = 10;
        }
        levelLabel.setText(String.format("%02d", level));
    }


    /**
     * Set box left label on the screen.
     */
    public static void minusBox(){
        boxLeft --;
        boxLabel.setText(String.format("%02d", boxLeft));
    }

    /**
     * Reset level to level 1
     */
    public void resetLevel(){
        level = 1;
        levelLabel.setText(String.format("%02d", level));
    }

    /**
     * Reset box left to 10
     */
    public void resetBox(){
        boxLeft = 10;
        boxLabel.setText(String.format("%02d",boxLeft));
    }

    public void instructionDisappear(){
        instructionImage.remove();
    }


}
