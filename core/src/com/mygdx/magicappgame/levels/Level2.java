package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.magicappgame.Shapes.BalancePlatform;

import java.util.ArrayList;

/**
 * Created by Ansel on 3/4/2017.
 *
 */

public class Level2 extends Level{



    public Level2(World world, Vector2 screenPos) {
        super(world, screenPos);
        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){

        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));

    }
}
