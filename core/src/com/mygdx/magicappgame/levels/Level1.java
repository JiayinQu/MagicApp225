package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.magicappgame.Shapes.BalancePlatform;
import com.mygdx.magicappgame.States.PlayScreen;

import java.util.ArrayList;

/**
 * Created by Ansel on 3/2/2017.
 * This Level1 class contains information about the first level and it also
 * contains a function that gets the next Body.
 */

public class Level1 extends Level{

    public Level1(World world, Vector2 screenPos) {
        super(world, screenPos);

        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){

        levelCoord.add(new Vector2(22, 22));
        levelCoord.add(new Vector2(10, 50));
        levelCoord.add(new Vector2(50, 10));
        levelCoord.add(new Vector2(20, 30));
        levelCoord.add(new Vector2(30, 30));
        levelCoord.add(new Vector2(15, 15));
        levelCoord.add(new Vector2(20, 12));
        levelCoord.add(new Vector2(20, 25));
        levelCoord.add(new Vector2(10, 35));
        levelCoord.add(new Vector2(10, 10));

    }

}
