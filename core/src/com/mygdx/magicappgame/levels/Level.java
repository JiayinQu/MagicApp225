package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.Shapes.BalancePlatform;
import com.mygdx.magicappgame.States.MainMenu;
import com.mygdx.magicappgame.States.PlayScreen;
import com.mygdx.magicappgame.Tools.WorldContactListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ansel on 3/4/2017.
 * This is the superclass for Level1, Level2 etc.
 * Contains the drawing methods for the shapes and more.
 */

public class Level {
    private World world;
    private Vector2 screenPos;
    public Boolean levelComplete;
    public int count;
    ArrayList<Vector2> levelCoord;
    private Body bod;
    private MyGdxGame game;

    private Level currentLevel;
    private int levelNum;
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private Level4 level4;
    private Level5 level5;

    private final static int NUM_BODIES = 10;

    Level (MyGdxGame game) {
        this.game = game;
        setup();
    }

    private void setup() {
        world = game.playScreen.getWorld();
        screenPos = game.playScreen.getScreenPos();
        levelCoord = new ArrayList<Vector2>();
        count = 0;
        levelComplete = false;
        currentLevel = game.playScreen.getCurrentLevel();
        levelNum = 1;
    }

    /**
     * Draws a rectangle on the screen
     * @param width of the square
     * @param height of the square
     * @return the body that was drawn on the screen
     */
    private Body drawSquare(float width, float height) {
        Random rand = new Random();
        int variance = rand.nextInt(40) - 20;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(screenPos.x + variance, screenPos.y);

        Body bod = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fdef.friction = .2f;
        bod.createFixture(fdef);

        shape.dispose();

        return bod;
    }

    public Body getNextBod() {
        if (count+1 == NUM_BODIES){
            levelComplete = true;
        }
        bod = drawSquare(levelCoord.get(count).x, levelCoord.get(count).y);
        count++;
        return bod;
    }

    public Level getNextLevel() {
        if (levelNum == 1)
            return new Level2(game);
        if (levelNum == 2)
            return new Level3(game);
        if (levelNum == 3)
            return new Level4(game);
        if (levelNum == 4)
            return new Level5(game);
        else
            return new Level1(game);
    }

    public void setLevelNum(int num) {
        levelNum = num;
    }

    public void increaseLevelNum() {
        levelNum++;
    }

    public void setImage(Sprite sprite){
        bod.setUserData(sprite);
    }


    public void clearLevel() {
        levelCoord.clear();
    }

    public void hit(){
        //boolean contacted = true;
    }

    public float getWidth(){
        return levelCoord.get(count-1).x;
    }

    public float getHeight(){
        return levelCoord.get(count-1).y;
    }

}
