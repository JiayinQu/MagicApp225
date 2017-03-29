package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.magicappgame.Shapes.BalancePlatform;
import com.mygdx.magicappgame.Tools.WorldContactListener;

import java.util.ArrayList;

/**
 * Created by Ansel on 3/4/2017.
 * This is the superclass for Level1, Level2 etc.
 * Contains the drawing methods for the shapes and more.
 */

public class Level {
    private World world;
    private Vector2 screenPos;
    public Boolean levelComplete;
    public long lastObjectTime;
    private int count;
    ArrayList<Vector2> levelCoord;
    public BalancePlatform plat;
    private Body bod;
    private WorldContactListener objectContactListener;

    public boolean Contacted;

    protected Fixture fixture;

    private final static int NUM_BODIES = 10;

    Level (World world, Vector2 screenPos) {
        this.world = world;
        this.screenPos = screenPos;

        levelCoord = new ArrayList<Vector2>();
        count = 0;
        //plat = new BalancePlatform(world);
        levelComplete = false;
    }

    /**
     * Draws a rectangle on the screen
     * @param width of the square
     * @param height of the square
     * @return the body that was drawn on the screen
     */
    Body drawSquare(float width, float height) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(screenPos);

        Body bod = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fdef.friction = .2f;
        bod.createFixture(fdef);

        bod.createFixture(fdef).setUserData(this);

        // Textures -- needs to be fixed
        /*
        if(squareTexList.size()!=0){
            squareTexList.get(squareTexList.size()-1).setBounds(0,0,drawSquareTex().getWidth(),drawSquareTex().getHeight());
            squareTexList.get(squareTexList.size()-1).setSize(width * 2,height * 2);
            squareTexList.get(squareTexList.size()-1).setOrigin(drawSquareTex().getWidth()/2, drawSquareTex().getHeight()/2);
        }
        */
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

    public void clearLevel() {
        levelCoord.clear();
    }

    public void hit(){
        Contacted = true;
        System.out.println("I've been hit");
    }

}
