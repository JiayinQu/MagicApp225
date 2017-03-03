package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.magicappgame.States.PlayScreen;

import java.util.ArrayList;

/**
 * Created by Ansel on 3/2/2017.
 */

public class Level1 {
    private int count;
    private ArrayList<Vector2> level1Coord;
    private Body bod;
    PlayScreen playScreen;

    public final static int NUM_BODIES = 9;

    public Level1(PlayScreen playScreen) {
        level1Coord = new ArrayList<Vector2>();
        count = 0;
        this.playScreen = playScreen;

        initializeCoord();
    }

    private ArrayList<Vector2> initializeCoord(){
        level1Coord = new ArrayList<Vector2>();

        level1Coord.add(new Vector2(22, 22));
        level1Coord.add(new Vector2(10, 50));
        level1Coord.add(new Vector2(50, 10));
        level1Coord.add(new Vector2(20, 30));
        level1Coord.add(new Vector2(30, 30));
        level1Coord.add(new Vector2(15, 15));
        level1Coord.add(new Vector2(20, 12));
        level1Coord.add(new Vector2(20, 25));
        level1Coord.add(new Vector2(10, 35));
        level1Coord.add(new Vector2(10, 10));

        return level1Coord;
    }

    public Body getNextBod() {
        if (count == NUM_BODIES){
            playScreen.levelComplete = true;
        }
        bod = playScreen.drawSquare(initializeCoord().get(count).x, initializeCoord().get(count).y);
        count++;
        return bod;
    }
}
