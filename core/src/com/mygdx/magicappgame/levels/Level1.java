package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by Ansel on 3/2/2017.
 * This Level1 class contains information about the first level and it also
 * contains a function that gets the next Body.
 */

public class Level1 extends Level{

    public Level1(MyGdxGame game) {
        super(game);
        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(20, 20));
    }

}
