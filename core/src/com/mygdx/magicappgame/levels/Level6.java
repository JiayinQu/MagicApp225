package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by ahayes on 4/27/17.
 */

public class Level6 extends Level{

    public Level6(MyGdxGame game) {
        super(game);
        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){

        levelCoord.add(new Vector2(7, 7));
        levelCoord.add(new Vector2(40, 40));
        levelCoord.add(new Vector2(10, 50));
        levelCoord.add(new Vector2(30, 30));
        levelCoord.add(new Vector2(20, 45));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(40, 10));
        levelCoord.add(new Vector2(10, 10));
        levelCoord.add(new Vector2(50, 8));
        levelCoord.add(new Vector2(50, 8));
    }
}
