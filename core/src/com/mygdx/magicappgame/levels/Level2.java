package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.magicappgame.MyGdxGame;


/**
 * Created by Ansel on 3/4/2017.
 *
 */

public class Level2 extends Level{

    public Level2(MyGdxGame game) {
        super(game);
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
