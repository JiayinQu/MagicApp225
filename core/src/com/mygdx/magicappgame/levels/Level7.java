package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by ahayes on 4/27/17.
 */

public class Level7 extends Level {

    public Level7(MyGdxGame game) {
        super(game);
        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){

        levelCoord.add(new Vector2(70, 128));
        levelCoord.add(new Vector2(5, 5));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(5, 5));
        levelCoord.add(new Vector2(10, 73));
        levelCoord.add(new Vector2(5, 5));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(40, 2));
    }
}
