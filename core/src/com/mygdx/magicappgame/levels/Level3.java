package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Ansel on 4/4/2017.
 *
 */

public class Level3 extends Level{

    public Level3(World world, Vector2 screenPos) {
        super(world, screenPos);
        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){

        levelCoord.add(new Vector2(10, 20));
        levelCoord.add(new Vector2(10, 30));
        levelCoord.add(new Vector2(10, 40));
        levelCoord.add(new Vector2(40, 40));
        levelCoord.add(new Vector2(30, 10));
        levelCoord.add(new Vector2(10, 30));
        levelCoord.add(new Vector2(40, 10));
        levelCoord.add(new Vector2(10, 40));
        levelCoord.add(new Vector2(50, 10));
        levelCoord.add(new Vector2(10, 50));
    }
}
