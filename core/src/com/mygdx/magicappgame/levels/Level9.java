package com.mygdx.magicappgame.levels;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.magicappgame.MyGdxGame;

/**
 * Created by ahayes on 4/30/17.
 */

public class Level9  extends Level {

    public Level9(MyGdxGame game) {
        super(game);
        initializeCoord();
    }

    /**
     * Sets up the heights and widths for the Bodies in
     * this level.
     */
    private void initializeCoord(){

        levelCoord.add(new Vector2(30, 60));
        levelCoord.add(new Vector2(30, 30));
        levelCoord.add(new Vector2(1, 70));
        levelCoord.add(new Vector2(70, 1));
        levelCoord.add(new Vector2(10, 70));
        levelCoord.add(new Vector2(5, 5));
        levelCoord.add(new Vector2(20, 20));
        levelCoord.add(new Vector2(60, 10));
        levelCoord.add(new Vector2(5, 5));
        levelCoord.add(new Vector2(30, 30));
    }
}
