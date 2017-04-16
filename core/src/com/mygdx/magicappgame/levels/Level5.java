package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ahayes on 4/16/17.
 */

public class Level5 extends Level {

    public Level5(World world, Vector2 screenPos) {
        super(world, screenPos);
        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){

        levelCoord.add(new Vector2(5, 5));
        levelCoord.add(new Vector2(100, 10));
        levelCoord.add(new Vector2(5, 5));
        levelCoord.add(new Vector2(100, 10));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(10, 50));
        levelCoord.add(new Vector2(10, 50));
        levelCoord.add(new Vector2(50, 10));
        levelCoord.add(new Vector2(50, 10));
    }
}

